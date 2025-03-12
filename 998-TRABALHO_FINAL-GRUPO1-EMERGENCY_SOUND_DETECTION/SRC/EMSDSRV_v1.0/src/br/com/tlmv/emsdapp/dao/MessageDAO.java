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
 * MessageDAO.java
 */

package br.com.tlmv.emsdapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import br.com.tlmv.emsdapp.dao.record.Message;

public class MessageDAO 
{
//Private
	
	private DAO dao;
	
//Public
	
	/* Constructors */
	
	public MessageDAO(DAO dao)
	{
		this.dao = dao;
	}
	
	/* Methodes */
	
	public Message findMessageByPk(Integer messageId)
	{
		Message result = null;
		
		String sql = Message.QRY_BASE_SELECT_BYPK;
		
		try {
			Connection conn = dao.getConn();
			
			int n = 1;
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(n++, messageId);
			
			ResultSet rs = stmt.executeQuery();
			if( rs.next() )
				result = new Message(rs);
		}
		catch(Exception e) {
			e.printStackTrace();
		}		
		return result;
	}
	
	public ArrayList<Message> findAllMessage()
	{
		ArrayList<Message> lsResult = new ArrayList<Message>();
		
		String sql = Message.QRY_BASE_SELECT_ALL;
		
		try {
			Connection conn = dao.getConn();
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while( rs.next() ) {
				Message o = new Message(rs);
				lsResult.add(o);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}		
		return lsResult;
	}

	public void insertUpdateSequencia(
		Integer messageId,
		String reqRespMessage, 
		String messageUuid,
		Integer messageTypeId,
		String messageType,
		Integer statusCode,
		String statusMessage,
		String username,
		String password,
		String sessionUuid,
		java.sql.Timestamp sessionDateTime, 
		Long sessionTimeMili,
		Integer equipamentoId,
		String modelUuid,
		java.sql.Timestamp modelDateTime, 
		String modelFile,
		String dataFileUuid,
		String dataFileName,
		Long dataFileSize,
		String dataFile,
		Double latitude,
		Double longitude)
	{
		Message o = findMessageByPk(messageId);
		if(o == null) {
			try {
				String sql = Message.QRY_BASE_INSERT;
				
				Connection conn = dao.getConn();

				int n = 1;
				
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setInt(n++, messageId);
				stmt.setString(n++, reqRespMessage); 
				stmt.setString(n++, messageUuid);
				stmt.setInt(n++, messageTypeId);
				stmt.setString(n++, messageType);
				stmt.setInt(n++, statusCode);
				stmt.setString(n++, statusMessage);
				stmt.setString(n++, username);
				stmt.setString(n++, password);
				stmt.setString(n++, sessionUuid);
				stmt.setTimestamp(n++, sessionDateTime); 
				stmt.setLong(n++, sessionTimeMili);
				stmt.setInt(n++, equipamentoId);
				stmt.setString(n++, modelUuid);
				stmt.setTimestamp(n++, modelDateTime); 
				stmt.setString(n++, modelFile);
				stmt.setString(n++, dataFileUuid);
				stmt.setString(n++, dataFileName);
				stmt.setLong(n++, dataFileSize);
				stmt.setString(n++, dataFile);
				stmt.setDouble(n++, latitude);
				stmt.setDouble(n++, longitude);
				
				int rowcount = stmt.executeUpdate();
			}
			catch(Exception e) {
				e.printStackTrace();
			}		
		}
		else {
			try {
				String sql = Message.QRY_BASE_UPDATE;
				
				Connection conn = dao.getConn();
				
				int n = 1;
				
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(n++, reqRespMessage); 
				stmt.setString(n++, messageUuid);
				stmt.setInt(n++, messageTypeId);
				stmt.setString(n++, messageType);
				stmt.setInt(n++, statusCode);
				stmt.setString(n++, statusMessage);
				stmt.setString(n++, username);
				stmt.setString(n++, password);
				stmt.setString(n++, sessionUuid);
				stmt.setTimestamp(n++, sessionDateTime); 
				stmt.setLong(n++, sessionTimeMili);
				stmt.setInt(n++, equipamentoId);
				stmt.setString(n++, modelUuid);
				stmt.setTimestamp(n++, modelDateTime); 
				stmt.setString(n++, modelFile);
				stmt.setString(n++, dataFileUuid);
				stmt.setString(n++, dataFileName);
				stmt.setLong(n++, dataFileSize);
				stmt.setString(n++, dataFile);
				stmt.setDouble(n++, latitude);
				stmt.setDouble(n++, longitude);
				stmt.setInt(n++, messageId);
				
				int rowcount = stmt.executeUpdate();
			}
			catch(Exception e) {
				e.printStackTrace();
			}		
			
		}
		
	}	
		
}
