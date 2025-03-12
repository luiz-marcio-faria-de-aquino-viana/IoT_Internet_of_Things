/*
 * COPPE / UFRJ - Universidade Federal do Rio de Janeiro
 * PESC - Programa de Engenharia de Sistemas e Computacao
 *
 * Disciplina: CPS730 - Internet das Coisas
 * Professor: Claudio Miceli
 *
 * Trabalho Final - Emergency Sound Detection
 * Data: 19/SET/2022
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
 * AppServerListener.java
 */

package br.com.tlmv.emsdapp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class AppServerListener implements Runnable
{
//Private
	private int socket_inport = -1;
	
	private Thread thread = null;
	private boolean isRunning = false;

	private AppServerExec serverExec = null;
	
//Public
	
	public AppServerListener(int socket_inport)
	{
		init(socket_inport);
	}
	
	/* Methodes */
	
	public void init(int socket_inport)
	{
		this.socket_inport = socket_inport;
		
		this.thread = null;
		this.isRunning = false;
		
		this.serverExec = new AppServerExec();
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
				int bufsz = AppDefs.BUFFER_SIZE;
				byte[] buf = new byte[bufsz];
				
				try {
					DatagramPacket packet = new DatagramPacket(buf, bufsz); 
	
					DatagramSocket socket = new DatagramSocket(socket_inport);
					socket.receive(packet);
					socket.close();
					
					String xmlData = new String(buf, 0, packet.getLength());
					System.out.println(xmlData);
					
					serverExec.addLastData(xmlData);
					this.serverExec.startThread();
				}
				catch(Exception e) {
					e.printStackTrace();
				}
				
				try {
					Thread.sleep(AppDefs.THREAD_MIN_SLEEP_TIME);
				}
				catch(Exception e) { }
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
