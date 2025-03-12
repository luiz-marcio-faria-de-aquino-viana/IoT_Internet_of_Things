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
 * DAO.java
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

public class DAO 
{
//Private	
	private Connection conn;
	
	private String dsname;
	private String driver;
	private String url;
	private String user;
	private String pwd;
	
//Public
	
	/* Constructors */
	
	public DAO(String dsname, String driver, String url, String user, String pwd)
	{
		this.dsname = dsname;
		this.driver = driver;
		this.url = url;
		this.user = user;
		this.pwd = pwd;		
	}
	
	/* Methodes */
	
	public boolean open()
	{
		try 
		{
			Properties props = new Properties();
			props.setProperty("user", this.user);
			props.setProperty("password", this.pwd);
			props.setProperty("ssl", "false");
			conn = DriverManager.getConnection(this.url, props);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
		
	public boolean close()
	{
		try 
		{
			if(conn != null)
				conn.close();
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
		
	/* Getters/Setters */
	
	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}
		
}
