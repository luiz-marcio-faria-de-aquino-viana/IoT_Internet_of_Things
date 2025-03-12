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

import br.com.tlmv.emsdapp.AppDefs;
import br.com.tlmv.emsdapp.AppMain;
import br.com.tlmv.emsdapp.AppServerSender;
import br.com.tlmv.emsdapp.builders.LoginBuilder;
import br.com.tlmv.emsdapp.builders.ModelUpdateBuilder;
import br.com.tlmv.emsdapp.builders.SendDataBuilder;
import br.com.tlmv.emsdapp.builders.WhatchdogBuilder;
import br.com.tlmv.emsdapp.utils.UuidUtil;

public class ClientModelUpdateRequestEvent implements Runnable
{
//Private
	private AppServerSender sender = null;
	
	private Thread thread = null;
	private boolean isRunning = false;

//Public
	
	public ClientModelUpdateRequestEvent(AppServerSender sender) {
		this.sender = sender;
	}

	/* Methodes */
	
	public void sendModelUpdateRequest()
	{
		String sessionUuid = UuidUtil.sessionUUID();
		String xmlData = ModelUpdateBuilder.buildModelUpdateRequestMsg(sessionUuid);
		
		this.sender.setStrMessage(xmlData);
	}
	
	/* Thread Methodes */

	public void delay()
	{
		try {
			Thread.sleep(AppDefs.DEF_MIN_TEST_DELAY);
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
				sendModelUpdateRequest();
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
