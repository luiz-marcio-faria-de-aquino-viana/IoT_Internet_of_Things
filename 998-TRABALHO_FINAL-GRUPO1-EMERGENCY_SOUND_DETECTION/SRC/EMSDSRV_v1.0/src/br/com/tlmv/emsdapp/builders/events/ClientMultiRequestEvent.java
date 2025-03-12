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
 * ClientModelUpdateRequestEvent.java
 */

package br.com.tlmv.emsdapp.builders.events;

import java.util.Date;

import br.com.tlmv.emsdapp.AppDefs;
import br.com.tlmv.emsdapp.AppMain;
import br.com.tlmv.emsdapp.AppServerSender;
import br.com.tlmv.emsdapp.builders.LoginBuilder;
import br.com.tlmv.emsdapp.builders.ModelUpdateBuilder;
import br.com.tlmv.emsdapp.builders.SendDataBuilder;
import br.com.tlmv.emsdapp.builders.WhatchdogBuilder;
import br.com.tlmv.emsdapp.utils.UuidUtil;

public class ClientMultiRequestEvent implements Runnable
{
//Private
	private AppServerSender sender = null;
	private long ticks = 0;
	
	private Thread thread = null;
	private boolean isRunning = false;

	private long soundStep = -1; 
	
	private long nextSoundStep() {
		double step_dbl = Math.floor(Math.random() * AppDefs.DEF_MAX_SOUND_STEP);
		soundStep = (long)step_dbl;
		return soundStep;
	}
	
//Public
	
	public ClientMultiRequestEvent(AppServerSender sender) {
		this.sender = sender;
	}

	/* Methodes */
	
	public void sendLoginRequest()
	{
		Integer remoteUnitId = AppDefs.DEF_REMOTEUNIT_PADRAO;
		String username = AppDefs.DEF_LOGIN_PADRAO;
		String password = AppDefs.DEF_PASSWORD_PADRAO;
				
		String xmlData = LoginBuilder.buildLoginRequestMsg(
			remoteUnitId,
			username,
			password);
		
		this.sender.setStrMessage(xmlData);		
	}
	
	public void sendModelUpdateRequest()
	{
		String sessionUuid = AppMain.getApp().getSessionUUID();
		String xmlData = ModelUpdateBuilder.buildModelUpdateRequestMsg(sessionUuid);
		
		this.sender.setStrMessage(xmlData);
	}
	
	public void sendWhatdogRequest()
	{
		String sessionUuid = AppMain.getApp().getSessionUUID();
		String xmlData = WhatchdogBuilder.buildWhatchdogRequestMsg(sessionUuid);
		
		this.sender.setStrMessage(xmlData);
	}
	
	/* Thread Methodes */

	public void delay()
	{
		try {
			Thread.sleep(AppDefs.DEF_MAX_TEST_DELAY);
		}
		catch(Exception e) { }
	}
	
	public void startThread()
	{
		thread = new Thread(this);
		thread.start();
	}
	
	public void stopThread()
	{
		if(thread != null) {
			this.isRunning = false;

			try {
				thread.interrupt();
				thread.notifyAll();
			}
			catch(Exception e) { }
		}
	}
	
	public void waitThread()
	{
		if(thread != null) {
			if( this.isRunning ) {
				try {
					thread.wait();
				}
				catch(Exception e) { }
			}
		}
	}
	
	/* Threads */
	
	@Override
	public void run() 
	{
		try {
			this.isRunning = true;

			while( isRunning ) {
				delay();

				String sessionUUID = AppMain.getApp().getSessionUUID();
				if(sessionUUID == null) {
					sendLoginRequest();
				}				
				else {					
					System.out.println("*** NOTHING TODO! ***");
					
//					String modelUUID = AppMain.getApp().getModelUUID();
//					if(modelUUID == null) {					
//						sendModelUpdateRequest();
//					}
//					else {	
//						if((ticks % AppDefs.DEF_MODELUPDATE_STEP) == 0) {
//							sendModelUpdateRequest();
//						}
//						else if((ticks % AppDefs.DEF_WHATCHDOG_STEP) == 0) {
//							sendWhatdogRequest();
//						}
//					}
				}

				ticks += 1;
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

}
