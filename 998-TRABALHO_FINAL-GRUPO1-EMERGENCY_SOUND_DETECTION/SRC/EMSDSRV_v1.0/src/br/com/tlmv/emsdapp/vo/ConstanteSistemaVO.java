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
 * ConstanteSistemaVO.java
 */

package br.com.tlmv.emsdapp.vo;

import java.sql.ResultSet;

//CREATE TABLE constante_sistema
//(
//    constante_sistema_id character varying(30) NOT NULL,
//    valor character varying(255) NOT NULL,
//    descricao character varying(255),
//    PRIMARY KEY (constante_sistema_id)
//)
public class ConstanteSistemaVO extends RecordVO
{
//Private
	private String constanteSistemaId;
	private String valor;
	private String descricao;
	
//Public 	
	public static final String SQLTBL_CONSTANTE_SISTEMA 					= "constante_sistema";
	//
	public static final int SQLFLD_CONSTANTE_SISTEMA_CONSTANTE_SISTEMA_ID 	= 1;
	public static final int SQLFLD_CONSTANTE_SISTEMA_VALOR 					= 2;
	public static final int SQLFLD_CONSTANTE_SISTEMA_DESCRICAO 				= 3;

	/* Methodes */
	
	public ConstanteSistemaVO(String constanteSistemaId, String valor, String descricao)
	{
		this.constanteSistemaId = constanteSistemaId;
		this.valor = valor;
		this.descricao = descricao;

		this.init(
			this.constanteSistemaId,
			this.valor);
	}
	
	public ConstanteSistemaVO(ResultSet rs)
		throws Exception
	{
		this.constanteSistemaId = rs.getString(ConstanteSistemaVO.SQLFLD_CONSTANTE_SISTEMA_CONSTANTE_SISTEMA_ID);
		this.valor = rs.getString(ConstanteSistemaVO.SQLFLD_CONSTANTE_SISTEMA_VALOR);
		this.descricao = rs.getString(ConstanteSistemaVO.SQLFLD_CONSTANTE_SISTEMA_DESCRICAO);

		this.init(
			this.constanteSistemaId,
			this.valor);
	}
	
	/* Getters/Setters */
	
	public String getConstanteSistemaId() {
		return constanteSistemaId;
	}
	public void setConstanteSistemaId(String constanteSistemaId) {
		this.constanteSistemaId = constanteSistemaId;
		
		this.init(
			this.constanteSistemaId,
			this.valor);		
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
		
		this.init(
			this.constanteSistemaId,
			this.valor);		
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
