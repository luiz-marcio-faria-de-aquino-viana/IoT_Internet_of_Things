/*
 * COPPE / UFRJ - Universidade Federal do Rio de Janeiro
 * PESC - Programa de Engenharia de Sistemas e Computacao
 *
 * Disciplina: CPS730 - Internet das Coisas
 * Professor: Claudio Miceli
 *
 * Trabalho Final - Emergency Sound Detection
 * Data: 22/SET/2022
 *
 * Nome: Luiz Marcio Faria de Aquino Viana, M.Sc.
 * Formacao: Engenheiro Eletricista com Enfase em Engenharia de Sistemas e Computação
 * DRE: 120048833
 * CPF: 024.723.347-10
 * RG: 08855128-8 IFP-RJ
 * Registro: 2000103581 CREA-RJ
 * E-mail: luiz.marcio.viana@gmail.com
 *         lmarcio@cos.ufrj.br
 * Tel.: +55-21-99983-7207
 *
 * AppServerSender.java
 */

package br.com.tlmv.emsdapp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Date;

import br.com.tlmv.emsdapp.algor.EmergencySoundWiSARD;
import br.com.tlmv.emsdapp.msg.BaseMsg;
import br.com.tlmv.emsdapp.msg.LoginRequestMsg;
import br.com.tlmv.emsdapp.msg.LoginResponseMsg;
import br.com.tlmv.emsdapp.msg.ModelUpdateRequestMsg;
import br.com.tlmv.emsdapp.msg.ModelUpdateResponseMsg;
import br.com.tlmv.emsdapp.msg.SendDataRequestMsg;
import br.com.tlmv.emsdapp.msg.SendDataResponseMsg;
import br.com.tlmv.emsdapp.msg.WhatchdogRequestMsg;
import br.com.tlmv.emsdapp.msg.WhatchdogResponseMsg;
import br.com.tlmv.emsdapp.utils.FileUtil;
import br.com.tlmv.emsdapp.utils.StringUtil;
import br.com.tlmv.emsdapp.utils.UuidUtil;

public class AppServerExec implements Runnable
{
//Private
	private Thread thread = null;
	private boolean isRunning = false;

	private ArrayList<String> lsBaseData = null;
	
//Public
	
	public AppServerExec()
	{
		init();
	}

	/* BASE_MESSAGE_LIST */
	
	public synchronized void addLastData(String xmlData)
	{
		lsBaseData.add(xmlData);
	}
	
	public synchronized String getFirstData()
	{
		if(lsBaseData.size() > 0) {
			int pos = lsBaseData.size();			

			String xmlData = lsBaseData.get(0);
			lsBaseData.remove(0);
			return xmlData;
		}
		return null;
	}
	
	/* Methodes */
	
	public void init()
	{
		lsBaseData = new ArrayList<String>();
	}
	
	public void startThread()
	{
		thread = new Thread(this);
		thread.start();
	}
	
	/* Threads */
		
	@Override
	public void run() 
	{
		AppContext context = AppMain.getApp().getContext(); 
		
		AppConfig config = AppMain.getApp().getConfig(); 
		
		this.isRunning = true;

		try {
			if( isRunning ) {
				String xmlData = this.getFirstData();
				while(xmlData != null) {				
					BaseMsg oMsg = new BaseMsg();
					
					if( !oMsg.baseResponseParser(xmlData) )
						oMsg.baseRequestParser(xmlData);					
					oMsg.debug();
					
					if( AppDefs.REQMSG_LOGIN_STR.equals(oMsg.getMessageType()) ) {
						LoginRequestMsg reqMsg = new LoginRequestMsg();
						reqMsg.parser(xmlData);									
						System.out.println("Login - Requested from: " + reqMsg.getUsername());
						
						String sessionUUID = UuidUtil.sessionUUID();
						//AppMain.getApp().setSessionUUID(sessionUUID);
						
						LoginResponseMsg respMsg = new LoginResponseMsg(
							AppDefs.DEF_ID_NONE_VALINT,
							AppDefs.RESPMSG_LOGIN_STR,
							sessionUUID);
						String outData = respMsg.toXml();
						
						AppServerSender sender = AppMain.getApp().getServerSender();
						sender.sendMessage(outData);
					}
					else if( AppDefs.RESPMSG_LOGIN_STR.equals(oMsg.getMessageType()) ) {
						LoginResponseMsg respMsg = new LoginResponseMsg();
						respMsg.parser(xmlData);
						System.out.println("Login - Response SessionUUID: " + respMsg.getSessionUUID());
						
						String sessionUUID = respMsg.getSessionUUID();
						AppMain.getApp().setSessionUUID(sessionUUID);
					}
					else if( AppDefs.REQMSG_MODELUPDATE_STR.equals(oMsg.getMessageType()) ) {
						ModelUpdateRequestMsg reqMsg = new ModelUpdateRequestMsg();
						reqMsg.parser(xmlData);
						System.out.println("ModelUpdate - Requested from: " + reqMsg.getSessionUUID());
											
						int messageTypeId = AppDefs.DEF_ID_NONE_VALINT;
						String messageType = AppDefs.RESPMSG_MODELUPDATE_STR;
						String sessionUuid = UuidUtil.sessionUUID();
						String modelUuid = AppMain.getApp().getModelUUID();
						Date modelDateTime = new Date();
						String modelDataBase64 = AppDefs.DEF_ID_NONE_VALSTR;
						
						ModelUpdateResponseMsg respMsg = new ModelUpdateResponseMsg(
							messageTypeId,
							messageType,
							sessionUuid,
							modelUuid,
							modelDateTime,
							modelDataBase64);
						String outData = respMsg.toXml();
						
						AppServerSender sender = AppMain.getApp().getServerSender();
						sender.sendMessage(outData);
					}
					else if( AppDefs.RESPMSG_MODELUPDATE_STR.equals(oMsg.getMessageType()) ) {
						ModelUpdateResponseMsg respMsg = new ModelUpdateResponseMsg();
						respMsg.parser(xmlData);
						System.out.println("ModelUpdate - Response ModelUUID: " + respMsg.getModelUuid());
	
						String sessionUUID = respMsg.getSessionUuid();
						String modelUuid = respMsg.getModelUuid();
						Date modelDateTime = respMsg.getModelDateTime();
						String modelDataBase64 = AppDefs.DEF_ID_NONE_VALSTR;						
	
						System.out.println("-- LOADING MODEL... --");
						
						EmergencySoundWiSARD emsFt = AppMain.getApp().getEmsTf();
						emsFt.loadModel(modelUuid);

						System.out.println("-- MODEL LOADED! --");
					}
					else if( AppDefs.REQMSG_SENDDATA_STR.equals(oMsg.getMessageType()) ) {
						SendDataRequestMsg reqMsg = new SendDataRequestMsg();
						reqMsg.parser(xmlData);			
						System.out.println(
							"SendData - Request FileName: " + reqMsg.getFileName() + 
							";Latitude: " + reqMsg.getLatitudeStr() +
							";Longitude: " + reqMsg.getLongitudeStr() );

						int messageTypeId = AppDefs.DEF_ID_NONE_VALINT;
						String messageType = AppDefs.TEMPL_RESPONSE_SENDDATA_STR;
						String sessionUuid = AppMain.getApp().getSessionUUID();
						String fileUuid = UuidUtil.fileUUID();
						
						SendDataResponseMsg respMsg = new SendDataResponseMsg(
							messageTypeId,
							messageType,
							sessionUuid,
							fileUuid);
						String outData = respMsg.toXml();
						
						AppServerSender sender = AppMain.getApp().getServerSender();
						sender.sendMessage(outData);					
					}
					else if( AppDefs.RESPMSG_SENDDATA_STR.equals(oMsg.getMessageType()) ) {
						SendDataResponseMsg respMsg = new SendDataResponseMsg();
						respMsg.parser(xmlData);						
						System.out.println("SendData - Response FileUUID: " + respMsg.getFileUUID() );					
					}
//					else if( AppDefs.REQMSG_WHATCHDOG_STR.equals(oMsg.getMessageType()) ) {
//						WhatchdogRequestMsg reqMsg = new WhatchdogRequestMsg();
//						reqMsg.parser(xmlData);	
//						System.out.println("Whatchdog - Requested from: " + reqMsg.getSessionUuid());
//					
//						int messageTypeId = AppDefs.DEF_ID_NONE_VALINT;
//						String messageType = AppDefs.RESPMSG_WHATCHDOG_STR;
//						String sessionUuid = UuidUtil.sessionUUID();
//						Date sessionDateTime = new Date();
//						Long sessionTimeMili = sessionDateTime.getTime();
//						
//						WhatchdogResponseMsg respMsg = new WhatchdogResponseMsg(
//							messageTypeId,
//							messageType,
//							sessionUuid,
//							sessionDateTime,
//							sessionTimeMili);
//						String outData = respMsg.toXml();
//						
//						AppServerSender sender = AppMain.getApp().getServerSender();
//						sender.setStrMessage(outData);					
//					}
//					else if( AppDefs.RESPMSG_WHATCHDOG_STR.equals(oMsg.getMessageType()) ) {
//						WhatchdogResponseMsg respMsg = new WhatchdogResponseMsg();
//						respMsg.parser(xmlData);						
//						System.out.println("Whatchdog - Response SessionUUID: " + respMsg.getSessionUUID());
//												
//						String sessionUUID = respMsg.getSessionUUID();
//						Date sessionDateTime = respMsg.getSessionDateTime();
//						Long sessionTimeMili = respMsg.getSessionTimeMili();
//
//						System.out.println(
//							"Whatchdog - SessionUUID: " + sessionUUID + 
//							",SessionDateTime: " + respMsg.getSessionDateTimeStr() + 
//							",SessionTimeMili: " + respMsg.getSessionTimeMiliStr());
//					}
					
					xmlData = null;
					
					try {
						Thread.sleep(AppDefs.THREAD_MAX_SLEEP_TIME);
					}
					catch(Exception e) { }				
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			this.thread = null;
			this.isRunning = false;
		}
	}

	/* Getters/Setters */
	
	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

}
