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
 * EncodeUtil.java
 */

package br.com.tlmv.emsdapp.utils;

import java.net.URLDecoder;
import java.net.URLEncoder;

public class EncodeUtil 
{
//Public
	
	public static String encode(String strIn) {
		String outStr = URLEncoder.encode(strIn);
		return outStr;
	}
	
	public static String decode(String strIn) {
		String outStr = URLDecoder.decode(strIn);
		return outStr;
	}

}
