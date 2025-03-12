/*
 * COPPE / UFRJ - Universidade Federal do Rio de Janeiro
 * PESC - Programa de Engenharia de Sistemas e Computacao
 *
 * Disciplina: CPS730 - Internet das Coisas
 * Professor: Claudio Miceli
 *
 * Trabalho Final - Emergency Sound Detection
 * Data: 27/AGO/2022
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
 * SequenciaVO.java
 */

package br.com.tlmv.emsdapp.vo;

import java.sql.ResultSet;

//CREATE TABLE sequencia
//(
//    sequencia_id numeric(10,0) NOT NULL,
//    nome character varying(30) NOT NULL,
//    valor_inicial numeric(10,0) NOT NULL,
//    valor_final numeric(10,0) NOT NULL,
//    valor_atual numeric(10,0) NOT NULL,
//    PRIMARY KEY (sequencia_id)
//)
public class SequenciaVO extends RecordVO
{
//Private
	private Integer sequenciaId;
	private String nome;
	private Integer valorInicial;
	private Integer valorFinal;
	private Integer valorAtual;
	
//Public 	
	public static final String SQLTBL_SEQUENCIA			 					= "sequencia";
	//
	public static final int SQLFLD_CONSTANTE_SISTEMA_SEQUENCIA_ID 			= 1;
	public static final int SQLFLD_CONSTANTE_SISTEMA_NOME 					= 2;
	public static final int SQLFLD_CONSTANTE_SISTEMA_VALOR_INICIAL 			= 3;
	public static final int SQLFLD_CONSTANTE_SISTEMA_VALOR_FINAL 			= 4;
	public static final int SQLFLD_CONSTANTE_SISTEMA_VALOR_ATUAL 			= 5;

	/* Methodes */
	
	public SequenciaVO(
		Integer sequenciaId,
		String nome,
		Integer valorInicial,
		Integer valorFinal,
		Integer valorAtual)
	{
		this.sequenciaId = sequenciaId;
		this.nome = nome;
		this.valorInicial = valorInicial;
		this.valorFinal = valorFinal;
		this.valorAtual = valorAtual;

		this.init(
			Integer.toString(this.sequenciaId),
			this.nome);
	}
	
	public SequenciaVO(ResultSet rs)
		throws Exception
	{
		this.sequenciaId = rs.getInt(SequenciaVO.SQLFLD_CONSTANTE_SISTEMA_SEQUENCIA_ID);
		this.nome = rs.getString(SequenciaVO.SQLFLD_CONSTANTE_SISTEMA_NOME);
		this.valorInicial = rs.getInt(SequenciaVO.SQLFLD_CONSTANTE_SISTEMA_VALOR_INICIAL);
		this.valorFinal = rs.getInt(SequenciaVO.SQLFLD_CONSTANTE_SISTEMA_VALOR_FINAL);
		this.valorAtual = rs.getInt(SequenciaVO.SQLFLD_CONSTANTE_SISTEMA_VALOR_ATUAL);

		this.init(
			Integer.toString(this.sequenciaId),
			this.nome);
	}
	
	/* Getters/Setters */
	
	public Integer getSequenciaId() {
		return sequenciaId;
	}

	public void setSequenciaId(Integer sequenciaId) {
		this.sequenciaId = sequenciaId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getValorInicial() {
		return valorInicial;
	}

	public void setValorInicial(Integer valorInicial) {
		this.valorInicial = valorInicial;
	}

	public Integer getValorFinal() {
		return valorFinal;
	}

	public void setValorFinal(Integer valorFinal) {
		this.valorFinal = valorFinal;
	}

	public Integer getValorAtual() {
		return valorAtual;
	}

	public void setValorAtual(Integer valorAtual) {
		this.valorAtual = valorAtual;
	}
	
}
