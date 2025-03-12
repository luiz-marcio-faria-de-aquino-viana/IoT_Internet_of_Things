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
 * ClientSenderTest.java
 */

package br.com.tlmv.emsdapp.builders.test;

import br.com.tlmv.emsdapp.AppDefs;
import br.com.tlmv.emsdapp.AppMain;
import br.com.tlmv.emsdapp.AppServerSender;
import br.com.tlmv.emsdapp.builders.LoginBuilder;
import br.com.tlmv.emsdapp.builders.ModelUpdateBuilder;
import br.com.tlmv.emsdapp.builders.SendDataBuilder;
import br.com.tlmv.emsdapp.builders.WhatchdogBuilder;
import br.com.tlmv.emsdapp.utils.UuidUtil;

public class ClientSenderTest 
{
//Private
	private AppServerSender sender = null; 
			
//Public
	
	public ClientSenderTest(AppServerSender sender) {
		this.sender = sender;
	}
	
	/* Test Methodes */
	
	public void testLoginRequest()
	{
		String xmlData = LoginBuilder.buildLoginRequestMsg(1001, "lmarcio", "lmarcio");
		
		this.sender.setStrMessage(xmlData);
	}
	
	public void testModelUpdateRequest()
	{
		String sessionUuid = UuidUtil.sessionUUID();
		String xmlData = ModelUpdateBuilder.buildModelUpdateRequestMsg(sessionUuid);
		
		this.sender.setStrMessage(xmlData);
	}
	
	public void testSendDataRequest()
	{
		String sessionUuid = UuidUtil.sessionUUID();
		String fileName = "344-3-1-0.wav";
		long fileSize = 121004; 
		String fileDataBase64 = AppDefs.DEF_ID_NONE_VALSTR;
		double latitude = 22.54;
		double longitude = 43.12;
		
		String xmlData = SendDataBuilder.buildSendDataRequestMsg(
				sessionUuid, 
				fileName, 
				fileSize, 
				fileDataBase64, 
				latitude, 
				longitude);
		
		this.sender.setStrMessage(xmlData);
	}

	public void testWhatdogRequest()
	{
		String sessionUuid = UuidUtil.sessionUUID();
		String xmlData = WhatchdogBuilder.buildWhatchdogRequestMsg(sessionUuid);
		
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

		this.testLoginRequest();
		this.delay();
		
		this.testModelUpdateRequest();
		this.delay();
		
		this.testSendDataRequest();
		this.delay();
		
		this.testWhatdogRequest();		
		this.delay();	
	}
	
}
