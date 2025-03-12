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
 * ServerSenderTest.java
 */

package br.com.tlmv.emsdapp.builders.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.tlmv.emsdapp.AppDefs;
import br.com.tlmv.emsdapp.AppMain;
import br.com.tlmv.emsdapp.AppServerSender;
import br.com.tlmv.emsdapp.builders.LoginBuilder;
import br.com.tlmv.emsdapp.builders.ModelUpdateBuilder;
import br.com.tlmv.emsdapp.builders.SendDataBuilder;
import br.com.tlmv.emsdapp.builders.WhatchdogBuilder;
import br.com.tlmv.emsdapp.utils.UuidUtil;

public class ServerSenderTest {
//Private
	private AppServerSender sender = null; 
			
//Public
	
	public ServerSenderTest(AppServerSender sender) {
		this.sender = sender;
	}

	/* Test Methodes */
	
	public void testLoginResponse()
	{
		String sessionUuid = UuidUtil.sessionUUID();
		String xmlData = LoginBuilder.buildLoginResponseMsg(sessionUuid);
		
		this.sender.setStrMessage(xmlData);
	}
	
	public void testLoginResponseError()
	{
		String xmlData = LoginBuilder.buildLoginResponseError(
				AppDefs.RSCODE_ERROR_INVALID_USERNAME_PASSWORD, 
				AppDefs.RSMSG_ERROR_INVALID_USERNAME_PASSWORD);
		
		this.sender.setStrMessage(xmlData);
	}
	
	public void testModelUpdateResponse()
	{	
		String sessionUuid = UuidUtil.sessionUUID();
		String modelUuid = UuidUtil.modelUUID();
		Date modelDateTime = new Date();
		String modelDataBase64 = AppDefs.DEF_ID_NONE_VALSTR;

		String xmlData = ModelUpdateBuilder.buildModelUpdateResponseMsg(
			sessionUuid, 
			modelUuid, 
			modelDateTime, 
			modelDataBase64);
		
		this.sender.setStrMessage(xmlData);
	}
	
	public void testModelUpdateResponseError()
	{	
		int statusCode = AppDefs.RSCODE_ERROR_INVALID_SESSION_UUID;
		String statusMessage = AppDefs.RSMSG_ERROR_INVALID_SESSION_UUID;

		String xmlData = ModelUpdateBuilder.buildModelUpdateResponseError(
			statusCode, 
			statusMessage);
		
		this.sender.setStrMessage(xmlData);
	}
	
	public void testSendDataResponse()
	{
		String sessionUuid = UuidUtil.sessionUUID();
		String fileUuid = UuidUtil.fileUUID();
		
		String xmlData = SendDataBuilder.buildSendDataResponseMsg(
				sessionUuid, 
				fileUuid);
		
		this.sender.setStrMessage(xmlData);
	}

	public void testSendDataResponseError()
	{
		int statusCode = AppDefs.RSCODE_ERROR_INVALID_SESSION_UUID;
		String statusMessage = AppDefs.RSMSG_ERROR_INVALID_SESSION_UUID;

		String xmlData = SendDataBuilder.buildSendDataResponseError(
			statusCode, 
			statusMessage);

		this.sender.setStrMessage(xmlData);
	}

	public void testWhatdogResponse()
	{
		String sessionUuid = UuidUtil.sessionUUID();
		Date sessionDateTime = new Date();
		long sessionTimeMili = sessionDateTime.getTime();
		String xmlData = WhatchdogBuilder.buildWhatchdogResponseMsg(
				sessionUuid, 
				sessionDateTime, 
				sessionTimeMili);
		
		this.sender.setStrMessage(xmlData);
	}

	public void testWhatdogResponseError()
	{
		int statusCode = AppDefs.RSCODE_ERROR_INVALID_SESSION_UUID;
		String statusMessage = AppDefs.RSMSG_ERROR_INVALID_SESSION_UUID;

		String xmlData = WhatchdogBuilder.buildWhatchdogResponseError(statusCode, statusMessage);
				
		this.sender.setStrMessage(xmlData);
	}

	/* Test Execution */

	public void delay()
	{
		try {
			Thread.sleep(AppDefs.DEF_MAX_TEST_DELAY);
		}
		catch(Exception e) { }
	}
	
	public void execute()
	{
		this.delay();
		
		this.testLoginResponse();
		this.delay();
		
		this.testLoginResponseError();
		this.delay();
		
		this.testModelUpdateResponse();
		this.delay();
		
		this.testModelUpdateResponseError();
		this.delay();
		
		this.testSendDataResponse();
		this.delay();
		
		this.testSendDataResponseError();
		this.delay();
		
		this.testWhatdogResponse();
		this.delay();

		this.testWhatdogResponseError();
		this.delay();
	}
	
}
