/*
 * COPPE / UFRJ - Universidade Federal do Rio de Janeiro
 * PESC - Programa de Engenharia de Sistemas e Computacao
 *
 * Disciplina: CPS730 - Internet das Coisas
 * Professor: Claudio Miceli
 *
 * Trabalho Final - Emergency Sound Detection
 * Data: 25/AGO/2022
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
 * DbUtil.java
 */

package br.com.tlmv.emsdapp.utils;

import java.sql.ResultSet;

public class DbUtil 
{
//Private
	private ResultSet rs = null;
	private Integer pos = 1;
	
//Public
	
	public DbUtil(ResultSet rs)
	{
		this.rs = rs;
		this.pos = 1;
	}
	
	/* Methodes */
	
	public Integer getNextInt()
	{
		Integer result = null;
		
		try {
			result = this.rs.getInt(this.pos++);
		}
		catch(Exception e) { }
		
		return result;
	}
	
	public String getNextStr()
	{
		String result = null;
		
		try {
			result = this.rs.getString(this.pos++);
		}
		catch(Exception e) { }
		
		return result;
	}
	
	public Double getNextDbl()
	{
		Double result = null;
		
		try {
			result = this.rs.getDouble(this.pos++);
		}
		catch(Exception e) { }
		
		return result;
	}
	
	public Boolean getNextBol()
	{
		Boolean result = null;
		
		try {
			result = this.rs.getBoolean(this.pos++);
		}
		catch(Exception e) { }
		
		return result;
	}
	
	public Long getNextLng()
	{
		Long result = null;
		
		try {
			result = this.rs.getLong(this.pos++);
		}
		catch(Exception e) { }
		
		return result;
	}
	
	public java.sql.Date getNextDate()
	{
		java.sql.Date result = null;
		
		try {
			result = this.rs.getDate(this.pos++);
		}
		catch(Exception e) { }
		
		return result;
	}
	
	public java.sql.Timestamp getNextTimestamp()
	{
		java.sql.Timestamp result = null;
		
		try {
			result = this.rs.getTimestamp(this.pos++);
		}
		catch(Exception e) { }
		
		return result;
	}
	
}
