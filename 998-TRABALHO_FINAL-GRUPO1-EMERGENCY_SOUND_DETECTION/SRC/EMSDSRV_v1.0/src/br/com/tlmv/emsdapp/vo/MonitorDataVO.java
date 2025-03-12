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
 * MonitorDataVO.java
 */

package br.com.tlmv.emsdapp.vo;

import java.util.Date;

import br.com.tlmv.emsdapp.AppDefs;

public class MonitorDataVO {
//Private
	private Integer monitorDataId = -1;
	private String name = "";
	private Long startTimeMili = -1L;
	private Long endTimeMili = -1L;
	private Long elapsedTimeMili = -1L;
	private Boolean bAddToCounter = true;

	public MonitorDataVO(
		Integer monitorDataId,
		String name,
		Long startTimeMili,
		Long endTimeMili,
		Long elapsedTimeMili,
		Boolean bAddToCounter)
	{
		init(monitorDataId, 
			 name, 
			 bAddToCounter);
	}
	
	
	public Integer init(
		Integer monitorDataId0, 
		String name0,
		Boolean bAddToCounter0)
	{
	    monitorDataId = monitorDataId0;
	    name = name0;
	    startTimeMili = -1L;
	    endTimeMili = -1L;
	    elapsedTimeMili = -1L;
	    bAddToCounter = bAddToCounter0;
	    
	    return AppDefs.RSOK;
	}

	/* Methodes */
  
	public Long start()
	{
		Date dataAtual = new Date();
		startTimeMili = dataAtual.getTime();
		endTimeMili = startTimeMili;
		elapsedTimeMili = endTimeMili - startTimeMili;
		return startTimeMili;
	}

	public Long finish()
	{
		if(startTimeMili != -1L) {
			Date dataAtual = new Date();
			endTimeMili = dataAtual.getTime();
			elapsedTimeMili = endTimeMili - startTimeMili;
		}
		return startTimeMili;
	}
  
  	public String debug()
  	{
  		String str = "Id: " + monitorDataId + ",Name: " + name + ",StartTime: " + startTimeMili + ",EndTime: " + endTimeMili + ",ElapsedTime: " + elapsedTimeMili;     
  		System.out.println(str);
  		return str;
  	}
  
  	/* Getters/Setters */
  
  	public Long getStartTimeMili() {
  		return startTimeMili;
  	}

	public void setStartTimeMili(Long startTimeMili) {
		this.startTimeMili = startTimeMili;
	}
	
	public Long getEndTimeMili() {
		return endTimeMili;
	}
	
	public void setEndTimeMili(Long endTimeMili) {
		this.endTimeMili = endTimeMili;
	}
	
	public Long getElapsedTimeMili() {
		return elapsedTimeMili;
	}
	
	public void setElapsedTimeMili(Long elapsedTimeMili) {
		this.elapsedTimeMili = elapsedTimeMili;
	}
	
	public Boolean getbAddToCounter() {
		return bAddToCounter;
	}
	
	public void setbAddToCounter(Boolean bAddToCounter) {
		this.bAddToCounter = bAddToCounter;
	}
	
	public void setMonitorDataId(Integer monitorDataId) {
		this.monitorDataId = monitorDataId;
	}
	
	public void setName(String name) {
		this.name = name;
	}

}

