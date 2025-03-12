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
 * InstalacaoDAO.java
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

import br.com.tlmv.emsdapp.dao.record.Instalacao;

public class InstalacaoDAO 
{
//Private
	
	private DAO dao;
	
//Public
	
	/* Constructors */
	
	public InstalacaoDAO(DAO dao)
	{
		this.dao = dao;
	}
	
	/* Methodes */
	
	public Instalacao findInstalacaoByPk(Integer instalacaoId)
	{
		Instalacao result = null;
		
		String sql = Instalacao.QRY_BASE_SELECT_BYPK;
		
		try {
			Connection conn = dao.getConn();
			
			int n = 1;
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(n++, instalacaoId);
			
			ResultSet rs = stmt.executeQuery();
			if( rs.next() )
				result = new Instalacao(rs);
		}
		catch(Exception e) {
			e.printStackTrace();
		}		
		return result;
	}
	
	public ArrayList<Instalacao> findAllInstalacao()
	{
		ArrayList<Instalacao> lsResult = new ArrayList<Instalacao>();
		
		String sql = Instalacao.QRY_BASE_SELECT_ALL;
		
		try {
			Connection conn = dao.getConn();
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while( rs.next() ) {
				Instalacao o = new Instalacao(rs);
				lsResult.add(o);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}		
		return lsResult;
	}

	public void insertUpdateInstalacao(
		Integer instalacaoId,
		String nome,
		String nomeServidor,
		Integer portaEntrada,
		Integer portaSaida)
	{
		Instalacao o = findInstalacaoByPk(instalacaoId);
		if(o == null) {
			try {
				String sql = Instalacao.QRY_BASE_INSERT;
				
				Connection conn = dao.getConn();
				
				int n = 1;
				
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setInt(n++, instalacaoId);
				stmt.setString(n++, nome);
				stmt.setString(n++, nomeServidor);
				stmt.setInt(n++, portaEntrada);
				stmt.setInt(n++, portaSaida);
				
				int rowcount = stmt.executeUpdate();
			}
			catch(Exception e) {
				e.printStackTrace();
			}		
		}
		else {
			try {
				String sql = Instalacao.QRY_BASE_UPDATE;
				
				Connection conn = dao.getConn();
				
				int n = 1;
				
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(n++, nome);
				stmt.setString(n++, nomeServidor);
				stmt.setInt(n++, portaEntrada);
				stmt.setInt(n++, portaSaida);
				stmt.setInt(n++, instalacaoId);
				
				int rowcount = stmt.executeUpdate();
			}
			catch(Exception e) {
				e.printStackTrace();
			}		
			
		}
		
	}	
		
}
