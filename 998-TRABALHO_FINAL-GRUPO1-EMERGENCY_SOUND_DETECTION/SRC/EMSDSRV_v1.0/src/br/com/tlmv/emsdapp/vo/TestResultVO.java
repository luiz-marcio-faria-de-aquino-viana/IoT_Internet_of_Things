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
 * TestResultVO.java
 */

package br.com.tlmv.emsdapp.vo;

public class TestResultVO 
{
//Private  
	private Double scoreTotal = 0.0;
	private Double scoreMin = 1.0;
	private Double scoreMax = 0.0;
	private Double n = 1.0; 

//Public
	
	public Double init()
	{
		scoreTotal = 0.0;
		scoreMin = 1.0;
		scoreMax = 0.0;
		n = 1.0;
		
		return scoreTotal;
	}

	public Double setScore(Double score0)
	{
		scoreTotal = scoreTotal + score0;
		n = n + 1.0;

		if(score0 > scoreMax)
			scoreMax = score0;
		
		if(score0 < scoreMin)
			scoreMin = score0;
		
		return scoreTotal;
	} 

	public Double getMean()
	{
		Double mean = scoreTotal / n;
		return mean;
	}

	public String debug() 
	{
		Double mean = getMean();

		String outStr = "[Total: " + scoreTotal + ",N: " + n + ",Mean: " + mean + ",Min: " + scoreMin + ",Max: " + scoreMax + "]";
		System.out.println(outStr);
		return outStr;
	}
  
}
