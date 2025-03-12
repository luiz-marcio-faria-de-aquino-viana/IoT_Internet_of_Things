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
 * RecordVO.java
 */

package br.com.tlmv.emsdapp.vo;

import java.util.Date;

import br.com.tlmv.emsdapp.utils.StringUtil;

public class RecordVO 
{
//Private
	private String recordId;
	private String descricao;
	
//Public
	
	public RecordVO()
	{
		this.recordId = "-1";
		this.descricao = "";

		this.init(this.recordId, this.descricao);
	}
	
	public RecordVO(
		String recordId,
		String descricao)
	{
		this.init(recordId, descricao);
	}
		
	/* Methodes */
	
	public void init(
		String recordId,
		String descricao)
	{
		this.recordId = recordId;
		this.descricao = descricao;		

		this.init(this.recordId, this.descricao);
	}
	
	public String toString()
	{
		String str = this.descricao;
		return str;
	}
	
	/* Getters/Setters */
		
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	
}

