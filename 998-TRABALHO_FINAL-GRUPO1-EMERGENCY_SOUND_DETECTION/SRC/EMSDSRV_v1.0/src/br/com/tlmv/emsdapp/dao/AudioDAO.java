/*
 * COPPE / UFRJ - Universidade Federal do Rio de Janeiro
 * PESC - Programa de Engenharia de Sistemas e Computacao
 *
 * Disciplina: CPS730 - Internet das Coisas
 * Professor: Claudio Miceli
 *
 * Trabalho Final - Emergency Sound Detection
 * Data: 15/AGO/2022
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
 * AudioDAO.java
 */

package br.com.tlmv.emsdapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import br.com.tlmv.emsdapp.dao.record.Audio;
import br.com.tlmv.emsdapp.dao.record.Instalacao;

public class AudioDAO 
{
//Private
	
	private DAO dao;
	
//Public
	
	/* Constructors */
	
	public AudioDAO(DAO dao)
	{
		this.dao = dao;
	}
	
	/* Methodes */
	
	public Audio findAudioByPk(Integer audioId)
	{
		Audio result = null;
		
		String sql = Audio.QRY_BASE_SELECT_BYPK;
		
		try {
			Connection conn = dao.getConn();
			
			int n = 1;
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(n++, audioId);
			
			ResultSet rs = stmt.executeQuery();
			if( rs.next() )
				result = new Audio(rs);
		}
		catch(Exception e) {
			e.printStackTrace();
		}		
		return result;
	}
	
	public List<Audio> findAudioByClassId(Integer classId)
	{
		ArrayList<Audio> lsResult = new ArrayList<Audio>();
		
		String sql = Audio.QRY_BASE_SELECT_BYCLASSID;
		
		try {
			Connection conn = dao.getConn();
			
			int n = 1;
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(n++, classId);
			
			ResultSet rs = stmt.executeQuery();
			while( rs.next() ) {
				Audio o = new Audio(rs);
				lsResult.add(o);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}		
		return lsResult;
	}
	
	public ArrayList<Audio> findAllAudio()
	{
		ArrayList<Audio> lsResult = new ArrayList<Audio>();
		
		String sql = Audio.QRY_BASE_SELECT_ALL;
		
		try {
			Connection conn = dao.getConn();
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while( rs.next() ) {
				Audio o = new Audio(rs);
				lsResult.add(o);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}		
		return lsResult;
	}

}

