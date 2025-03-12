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
 * ConstanteSistemaDAO.java
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

import br.com.tlmv.emsdapp.dao.record.ConstanteSistema;

public class ConstanteSistemaDAO 
{
//Private
	
	private DAO dao;
	
//Public
	
	/* Constructors */
	
	public ConstanteSistemaDAO(DAO dao)
	{
		this.dao = dao;
	}
	
	/* Methodes */
	
	public ConstanteSistema findConstanteSistemaByPk(String constanteSistemaId)
	{
		ConstanteSistema result = null;
		
		String sql = ConstanteSistema.QRY_BASE_SELECT_BYPK;
		
		try {
			Connection conn = dao.getConn();
			
			int n = 1;
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(n++, constanteSistemaId);
			
			ResultSet rs = stmt.executeQuery();
			if( rs.next() )
				result = new ConstanteSistema(rs);
		}
		catch(Exception e) {
			e.printStackTrace();
		}		
		return result;
	}
	
	public ArrayList<ConstanteSistema> findAllConstanteSistema()
	{
		ArrayList<ConstanteSistema> lsResult = new ArrayList<ConstanteSistema>();
		
		String sql = ConstanteSistema.QRY_BASE_SELECT_ALL;
		
		try {
			Connection conn = dao.getConn();
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while( rs.next() ) {
				ConstanteSistema o = new ConstanteSistema(rs);
				lsResult.add(o);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}		
		return lsResult;
	}
		
}
