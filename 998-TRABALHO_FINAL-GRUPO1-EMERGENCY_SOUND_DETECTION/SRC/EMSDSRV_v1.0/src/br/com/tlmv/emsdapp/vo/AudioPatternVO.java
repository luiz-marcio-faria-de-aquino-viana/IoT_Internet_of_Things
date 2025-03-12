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

import br.com.tlmv.emsdapp.AppConfig;
import br.com.tlmv.emsdapp.AppDefs;
import br.com.tlmv.emsdapp.AppMain;

public class AudioPatternVO  
{
//Private
	private Integer nFAIXAS = AppDefs.DEF_AUDIO_DEPTH;       		// final audio depth
	
	private Integer[] freq = null;

//Public
	
	public void init()
	{
		freq = new Integer[nFAIXAS];
		for(int i = 0; i < nFAIXAS; i++) {
			freq[i] = 0;
		}
	}

	public Integer setFaixaVal(Integer faixaVal)
	{
		freq[faixaVal] = freq[faixaVal] + 1;
		return faixaVal;
	} 

	public Integer getFaixaVal()
	{
		Integer faixaVal = 0;
    	Integer maxFreq = 0;
    	for(int i = 0; i < nFAIXAS; i++) {
    		if(maxFreq < freq[i]) {
    			faixaVal = i;
    			maxFreq = freq[i];
    		}
    	}
    	return faixaVal;
	} 

	public String debug()
	{
		Integer faixaVal = getFaixaVal();
    	String outStr = "[" + faixaVal + "," + freq[faixaVal] + "];";
    	
    	System.out.print(outStr);
    	return outStr;
	}

	public String  debugAll()
	{
		String outStr = "(";
		for(int i = 0; i < nFAIXAS; i++) {
			outStr = outStr + "[" + i + "," + freq[i] + "]";
		}
		outStr = outStr + ");";

		System.out.print(outStr);
		return outStr;
	}

}
