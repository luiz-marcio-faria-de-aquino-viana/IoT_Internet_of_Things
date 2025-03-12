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
 * TipoDocumentoDAO.java
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

import br.com.tlmv.emsdapp.dao.record.TipoDocumento;

public class TipoDocumentoDAO 
{
//Private
	
	private DAO dao;
	
//Public
	
	/* Constructors */
	
	public TipoDocumentoDAO(DAO dao)
	{
		this.dao = dao;
	}
	
	/* Methodes */
	
	public TipoDocumento findSequenciaByPk(Integer sequenciaId)
	{
		TipoDocumento result = null;
		
		String sql = TipoDocumento.QRY_BASE_SELECT_BYPK;
		
		try {
			Connection conn = dao.getConn();
			
			int n = 1;
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(n++, sequenciaId);
			
			ResultSet rs = stmt.executeQuery();
			if( rs.next() )
				result = new TipoDocumento(rs);
		}
		catch(Exception e) {
			e.printStackTrace();
		}		
		return result;
	}
	
	public ArrayList<TipoDocumento> findAllSequencia()
	{
		ArrayList<TipoDocumento> lsResult = new ArrayList<TipoDocumento>();
		
		String sql = TipoDocumento.QRY_BASE_SELECT_ALL;
		
		try {
			Connection conn = dao.getConn();
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while( rs.next() ) {
				TipoDocumento o = new TipoDocumento(rs);
				lsResult.add(o);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}		
		return lsResult;
	}

	public void insertUpdateSequencia(
		Integer sequenciaId,
		String nomeSequencia,
		Integer valorInicial,
		Integer valorFinal,
		Integer valorAtual)
	{
		TipoDocumento o = findSequenciaByPk(sequenciaId);
		if(o == null) {
			try {
				String sql = TipoDocumento.QRY_BASE_INSERT;
				
				Connection conn = dao.getConn();
				
				int n = 1;
				
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setInt(n++, sequenciaId);
				stmt.setString(n++, nomeSequencia);
				stmt.setInt(n++, valorInicial);
				stmt.setInt(n++, valorFinal);
				stmt.setInt(n++, valorAtual);
				
				int rowcount = stmt.executeUpdate();
			}
			catch(Exception e) {
				e.printStackTrace();
			}		
		}
		else {
			try {
				String sql = TipoDocumento.QRY_BASE_UPDATE;
				
				Connection conn = dao.getConn();
				
				int n = 1;
				
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(n++, nomeSequencia);
				stmt.setInt(n++, valorInicial);
				stmt.setInt(n++, valorFinal);
				stmt.setInt(n++, valorAtual);
				stmt.setInt(n++, sequenciaId);
				
				int rowcount = stmt.executeUpdate();
			}
			catch(Exception e) {
				e.printStackTrace();
			}		
			
		}
		
	}	
		
}
