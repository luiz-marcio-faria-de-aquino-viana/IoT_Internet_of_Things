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
 * FmtUtil.java
 */

package br.com.tlmv.emsdapp.utils;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;

public class FmtUtil
{
//Public
		
	public static String bToStr(Boolean val)
	{
		if(val == null) return "N";
		
		String strval = ( val ) ? "S" : "N";
		return strval;
	}
		
	public static String intToStr(Integer val)
	{
		if(val == null) return "0";
		
		String strval = Integer.toString(val);
		return strval;
	}
	
	public static String lngToStr(Long val)
	{
		if(val == null) return "0";

		String strval = Long.toString(val);
		return strval;
	}
	
	public static String dblToStr(NumberFormat nf, Double val)
	{
		if(val == null) return nf.format(0.0);

		String strval = nf.format(val);
		return strval;
	}
	
	public static String dateToStr(DateFormat df, Date val)
	{
		if(val == null) return df.format(val);

		String strval = df.format(val);
		return strval;
	}
	
}
