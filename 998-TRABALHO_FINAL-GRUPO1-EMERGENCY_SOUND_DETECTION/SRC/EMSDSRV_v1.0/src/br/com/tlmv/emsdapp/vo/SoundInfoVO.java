/*
* COPPE / UFRJ - Universidade Federal do Rio de Janeiro
* PESC - Programa de Engenharia de Sistemas e Computacao
*
* Disciplina: CPS730 - Internet das Coisas
* Professor: Claudio Miceli
*
* Trabalho Final - Emergency Sound Detection
* Data: 24/AGO/2022
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
* SoundInfoVO.java
*/

package br.com.tlmv.emsdapp.vo;

import java.util.ArrayList;
import java.util.List;

import br.com.tlmv.emsdapp.AppDefs;

public class SoundInfoVO 
{
//Private 
	private List<Double> lsOriginalSound = null;
	private Double maxValueOriginalSound = 0.0;
	private Double minValueOriginalSound = 0.0;
	private Double diffValueOriginalSound = 0.0;
	
	private List<Double> lsFixedSound = null;

//Public
	
	public List<Double> convertToDouble(List<Byte> lsSoundData)
	{
		lsOriginalSound = new ArrayList<Double>();
		maxValueOriginalSound = 0.0;
		minValueOriginalSound = 0.0;
		diffValueOriginalSound = 0.0;
		
        for(int i = 0; i < lsSoundData.size(); i += 2) {
        	byte soundData1 = lsSoundData.get(i);
        	byte soundData2 = lsSoundData.get(i+1);
        	
        	double soundData = ((double)soundData2) * 256 + ((double)soundData1);

        	if(soundData > maxValueOriginalSound)
        		maxValueOriginalSound = (Double)soundData;
        	if(soundData < minValueOriginalSound)
        		minValueOriginalSound = (Double)soundData;
        	
        	lsOriginalSound.add(soundData);
        }	    
        
        diffValueOriginalSound = maxValueOriginalSound - minValueOriginalSound;
        
        return lsOriginalSound;
	}

	public List<Double> fixedToDouble()
	{
		lsFixedSound = new ArrayList<Double>();
		
        for(int i = 0; i < lsOriginalSound.size(); i++) {
        	double soundData = lsOriginalSound.get(i);

        	double soundData_BASE0 = soundData + Math.abs(minValueOriginalSound);
        	double soundData_SCALE = AppDefs.DEF_AUDIO_MAX_VAL * (soundData_BASE0 / diffValueOriginalSound);
        	
        	lsFixedSound.add(soundData_SCALE);
        }	        	                
        return lsFixedSound;
	}
	
	public List<Double> rescaleToDouble(double divBy)
	{
		ArrayList<Double> lsScl = new ArrayList<Double>();
		
        for(int i = 0; i < lsFixedSound.size(); i++) {
        	double soundData = lsFixedSound.get(i);

        	double soundData_RESCALE = Math.floor(soundData / divBy);        	
        	lsScl.add(soundData_RESCALE);
        }	        	                
        return lsScl;		
	}
	   
	public void debug(List<Double> lsSoundData)
	{
        for(int i = 0; i < lsSoundData.size(); i++) {
        	Double soundData = lsSoundData.get(i);
	        System.out.println("POS:" + i + "; VALUE:" + soundData);
	        
	        if(i > 30) break;
        }	        	                
        System.out.print("\n\n");
	}
	
}
