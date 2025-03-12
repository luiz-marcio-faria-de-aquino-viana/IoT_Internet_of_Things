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
 * EdgeDataVO.java
 */

package br.com.tlmv.emsdapp.vo;

public class EdgeDataVO  
{
//Private
	private Integer rootLevelId = 0;

	private Integer changeLevelId = -1;
	private String changeLevelName = "";
	private Double changeLevelMinScore = 0.0;
	private Double changeLevelMaxScore = 0.0;

//Public
  
  	public Integer init(
		  Integer changeLevelId0, 
		  String changeLevelName0, 
		  Double changeLevelMinScore0, 
		  Double changeLevelMaxScore0)
  	{
	  changeLevelId = changeLevelId0;
	  changeLevelName = changeLevelName0;
	  changeLevelMinScore = changeLevelMinScore0;
	  changeLevelMaxScore = changeLevelMaxScore0;
	  return changeLevelId;
  	}

	public Integer checkLevel(Double score)
	{
		Integer result = 1;
		if( (score >= changeLevelMinScore) && (score < changeLevelMaxScore) )
			result = 0;
		return result;
	} 

	public String debug() 
	{
		String outStr = "[" + changeLevelId + "," + changeLevelName + ",[" + changeLevelMinScore + "-" + changeLevelMaxScore + "]]";
		System.out.println(outStr);
		return outStr;
	}

	public Integer getRootLevelId() {
		return rootLevelId;
	}
	
	public void setRootLevelId(Integer rootLevelId) {
		this.rootLevelId = rootLevelId;
	}
	
	public Integer getChangeLevelId() {
		return changeLevelId;
	}
	
	public void setChangeLevelId(Integer changeLevelId) {
		this.changeLevelId = changeLevelId;
	}
	
	public String getChangeLevelName() {
		return changeLevelName;
	}
	
	public void setChangeLevelName(String changeLevelName) {
		this.changeLevelName = changeLevelName;
	}
	
	public Double getChangeLevelMinScore() {
		return changeLevelMinScore;
	}
	
	public void setChangeLevelMinScore(Double changeLevelMinScore) {
		this.changeLevelMinScore = changeLevelMinScore;
	}
	
	public Double getChangeLevelMaxScore() {
		return changeLevelMaxScore;
	}
	
	public void setChangeLevelMaxScore(Double changeLevelMaxScore) {
		this.changeLevelMaxScore = changeLevelMaxScore;
	}

}
