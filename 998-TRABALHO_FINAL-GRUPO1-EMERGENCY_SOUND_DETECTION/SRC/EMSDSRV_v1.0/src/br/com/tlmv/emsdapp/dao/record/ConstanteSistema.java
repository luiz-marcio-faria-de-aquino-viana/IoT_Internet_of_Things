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
 * ConstanteSistema.java
 */

package br.com.tlmv.emsdapp.dao.record;

import java.io.Serializable;
import java.sql.ResultSet;

import br.com.tlmv.emsdapp.utils.DbUtil;

public class ConstanteSistema extends Record implements Serializable
{
//Public Static
	public static String QRY_BASE_SELECT_BYPK = 
		"select " +
			"constante_sistema_id, " +
			"valor, " +
			"descricao " +
		"from constante_sistema " +
		"where constante_sistema_id = ? ";
		
	public static String QRY_BASE_SELECT_ALL = 
		"select " +
			"constante_sistema_id, " +
			"valor, " +
			"descricao " +
		"from constante_sistema " +
		"order by constante_sistema_id ";			
	
	public static String QRY_BASE_INSERT = 
		"insert into constante_sistema( " +
			"constante_sistema_id, " +
			"valor, " +
			"descricao) " +
		"values (?,?,?)";
	
	public static String QRY_BASE_UPDATE = 
		"update constante_sistema set " +
			"valor = ?, " +
			"descricao = ? " +
		"where constante_sistema_id = ? ";
			
//Private
	private static final long serialVersionUID = 1L;

	private String constanteSistemaId;
	private String valor;
	private String descricao;
	
//Public

	public ConstanteSistema(
		String constanteSistemaId,
		String valor,
		String descricao)
	{
		super(constanteSistemaId, valor);
		
		this.constanteSistemaId = constanteSistemaId;
		this.valor = valor;
		this.descricao = descricao;
	}
	
	public ConstanteSistema(ResultSet rs)
		throws Exception
	{
		super(rs.getString(1), rs.getString(3));
		
		this.loadData(rs);		
	}
	
	/* Methodes */
	
	public void loadData(ResultSet rs)
	{
		DbUtil o = new DbUtil(rs);
		
		try {
			this.constanteSistemaId = o.getNextStr();
			this.valor = o.getNextStr();
			this.descricao = o.getNextStr();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/* Getters/Setters */

	public String getConstanteSistemaId() {
		return constanteSistemaId;
	}
	public void setConstanteSistemaId(String constanteSistemaId) {
		this.constanteSistemaId = constanteSistemaId;
	}

	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
