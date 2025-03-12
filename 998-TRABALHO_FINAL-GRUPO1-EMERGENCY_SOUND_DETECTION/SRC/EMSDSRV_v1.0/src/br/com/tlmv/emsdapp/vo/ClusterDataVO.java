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
 * ImagePatternVO.java
 */

package br.com.tlmv.emsdapp.vo;

public class ClusterDataVO
{
//Private
	private Integer x = 0; 
	private Integer y = 0; 
	private Integer w = 0;

//Prublic
	
	public ClusterDataVO(
		Integer x, 
	  	Integer y, 
	  	Integer w) 
	{
		init(x, y, w); 
	}

	public void init(
		Integer x, 
		Integer y, 
		Integer w) 
	{
		this.x = x;
		this.y = y;
		this.w = w;
	}

	public String debug()
	{
		String str = "(X:" + x + ",Y:" + y + ",W:" + w + ")";  
		System.out.println(str);
		return str;
	}
  
	/* Getters/Setters */
  
	public void setX(Integer x) {
		this.x = x;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public void setW(Integer w) {
		this.w = w;
	}

}

