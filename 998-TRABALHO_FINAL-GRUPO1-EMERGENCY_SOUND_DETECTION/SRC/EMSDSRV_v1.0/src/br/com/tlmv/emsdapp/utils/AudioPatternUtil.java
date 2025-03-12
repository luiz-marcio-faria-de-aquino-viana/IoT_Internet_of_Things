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

import java.util.List;

import br.com.tlmv.emsdapp.AppDefs;
import br.com.tlmv.emsdapp.vo.AudioPatternVO;

public class AudioPatternUtil 
{
//Private
	
//Public	  
	
	  
	public static Double classifyImagePattern(
		AudioPatternVO[] arrPattern, 
	    Integer szArrPattern, 
	    Integer[] data, 
	    Integer szData, 
	    Integer[] image, 
	    Integer szImage)
	{
	    Double scoreTotal = 0.0;

	    for(int i = 0; i < szData; i++) {
	    	Integer faixaVal = data[i];
	    	AudioPatternVO oPattern = arrPattern[i];
	    	Integer patFaixaVal = oPattern.getFaixaVal();

	    	if(patFaixaVal == faixaVal) {
	    		scoreTotal = scoreTotal + 1.0;
	    		image[i] = -1;
	    	}
	    	else {
	    		image[i] = 0;
	    	}
	    }
	    
	    Double scoreMean = scoreTotal / szData;
	    return scoreMean;
	  }

	  public static Integer printImagePattern(
		AudioPatternVO[] arrPattern, 
	    Integer szArrPattern, 
	    Integer[] data, 
	    Integer szData)
	  {	  
		  Integer i = 0;
		  for(i = 0; i < szData; i++) {
			  Integer faixaVal = data[i];

			  AudioPatternVO oPattern = arrPattern[i];
			  oPattern.setFaixaVal(faixaVal);
		  }
		  return AppDefs.RSOK;
	  }  
	  
}
