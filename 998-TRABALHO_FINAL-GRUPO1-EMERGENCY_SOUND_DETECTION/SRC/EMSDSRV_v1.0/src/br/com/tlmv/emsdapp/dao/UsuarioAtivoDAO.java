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
 * UsuarioAtivoDAO.java
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

import br.com.tlmv.emsdapp.dao.record.UsuarioAtivo;

public class UsuarioAtivoDAO 
{
//Private
	
	private DAO dao;
	
//Public
	
	/* Constructors */
	
	public UsuarioAtivoDAO(DAO dao)
	{
		this.dao = dao;
	}
	
	/* Methodes */
	
	public UsuarioAtivo findUsuarioAtivoByPk(String usuarioAtivoId)
	{
		UsuarioAtivo result = null;
		
		String sql = UsuarioAtivo.QRY_BASE_SELECT_BYPK;
		
		try {
			Connection conn = dao.getConn();
			
			int n = 1;
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(n++, usuarioAtivoId);
			
			ResultSet rs = stmt.executeQuery();
			if( rs.next() )
				result = new UsuarioAtivo(rs);
		}
		catch(Exception e) {
			e.printStackTrace();
		}		
		return result;
	}
	
	public ArrayList<UsuarioAtivo> findAllUsuarioAtivo()
	{
		ArrayList<UsuarioAtivo> lsResult = new ArrayList<UsuarioAtivo>();
		
		String sql = UsuarioAtivo.QRY_BASE_SELECT_ALL;
		
		try {
			Connection conn = dao.getConn();
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while( rs.next() ) {
				UsuarioAtivo o = new UsuarioAtivo(rs);
				lsResult.add(o);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}		
		return lsResult;
	}

	public void insertUpdateUsuarioAtivo(
		String usuarioAtivoId,
		Integer instalacaoId,
		Integer usuarioId,
		Integer equipamentoId,
		String enderecoEthernet,
		java.sql.Timestamp dataUltimoAcesso,
		java.sql.Timestamp dataInicio,
		String usuarioAtivo)
	{
		UsuarioAtivo o = findUsuarioAtivoByPk(usuarioAtivoId);
		if(o == null) {
			try {
				String sql = UsuarioAtivo.QRY_BASE_INSERT;
				
				Connection conn = dao.getConn();
				
				int n = 1;
								
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(n++, usuarioAtivoId);
				stmt.setInt(n++, instalacaoId);
				stmt.setInt(n++, usuarioId);
				stmt.setInt(n++, equipamentoId);
				stmt.setString(n++, enderecoEthernet);
				stmt.setTimestamp(n++, dataUltimoAcesso);
				stmt.setTimestamp(n++, dataInicio);
				stmt.setString(n++, usuarioAtivo);
				
				int rowcount = stmt.executeUpdate();
			}
			catch(Exception e) {
				e.printStackTrace();
			}		
		}
		else {
			try {
				String sql = UsuarioAtivo.QRY_BASE_UPDATE;
				
				Connection conn = dao.getConn();
				
				int n = 1;
				
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(n++, usuarioAtivoId);
				
				int rowcount = stmt.executeUpdate();
			}
			catch(Exception e) {
				e.printStackTrace();
			}		
			
		}
		
	}	
		
}
