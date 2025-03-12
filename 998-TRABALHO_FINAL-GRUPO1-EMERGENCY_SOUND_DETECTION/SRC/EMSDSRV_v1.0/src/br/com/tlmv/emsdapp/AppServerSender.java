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

import br.com.tlmv.emsdapp.utils.FileUtil;
import br.com.tlmv.emsdapp.utils.StringUtil;
import br.com.tlmv.emsdapp.utils.UuidUtil;

public class AppServerSender implements Runnable
{
//Private
	private String socket_addr = "127.0.0.1";
	private int socket_outport = -1;
	
	private String strMessage = null;
	
	private Thread thread = null;
	private boolean isRunning = false;
	
//Public
	
	public AppServerSender(String socket_addr, int socket_outport)
	{
		init(socket_addr, socket_outport);
	}
	
	/* Methodes */
	
	public void init(String socket_addr, int socket_outport)
	{
		this.socket_addr = socket_addr;
		this.socket_outport = socket_outport;
		
		this.thread = null;
		this.isRunning = false;
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

	public void sendMessage(String strMessage) {
		this.strMessage = strMessage;
		this.startThread();
	}

	/* Threads */
		
	@Override
	public void run() 
	{
		this.isRunning = true;
			
		try {
			InetAddress addr = InetAddress.getByName(this.socket_addr);

			if( isRunning ) {
				try {
					String strTmp = this.strMessage;
					if(strTmp != null) {
						int szStrTmp = strTmp.length();	

						try {
							DatagramPacket packet = new DatagramPacket(strTmp.getBytes(), szStrTmp, addr, socket_outport); 
							DatagramSocket socket = new DatagramSocket();
							socket.send(packet);
							socket.close();					
						}
						catch(Exception e) {
							e.printStackTrace();
						}
						
						strTmp = null;
						szStrTmp = -1;
					}
							
					this.strMessage = null;

					try {
						Thread.sleep(AppDefs.DEF_MAX_TEST_DELAY_EX);
					}
					catch(Exception e) { }				
				}
				catch(Exception e)
				{
					e.printStackTrace();
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

	public String getStrMessage() {
		return strMessage;
	}

	public void setStrMessage(String strMessage) {
		this.strMessage = strMessage;
	}

}
