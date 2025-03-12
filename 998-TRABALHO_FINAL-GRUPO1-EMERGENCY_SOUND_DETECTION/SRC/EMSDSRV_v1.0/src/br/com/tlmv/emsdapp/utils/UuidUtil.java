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
 * UuidUtil.java
 */

package br.com.tlmv.emsdapp.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import br.com.tlmv.emsdapp.AppDefs;

public class UuidUtil 
{
//Public
	
	public static String generateUUID()
	{
		try {
		    String uuid = UUID.randomUUID().toString().replaceAll("-", "");
			return uuid;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//MESSAGE_ID = "YYYYMMDD_HHmmss_999999"						;; 22 digits [0-9]
	//
	public static String generateMessageId()
	{
		try {
			SimpleDateFormat df1 = new SimpleDateFormat(AppDefs.DEF_DATE_MASC_INV);
			SimpleDateFormat df2 = new SimpleDateFormat(AppDefs.DEF_TIME_MASC_SIMPL);
						
		    Date dataAtual = new Date();
		    long dataAtualMili = dataAtual.getTime();
		    
		    long valMessageIdPart1 = dataAtualMili % 1000000;
		    
		    String strMessageIdPart1 = df1.format(dataAtual);
		    String strMessageIdPart2 = df2.format(dataAtual);
		    String strMessageIdPart3 = StringUtil.valField(valMessageIdPart1, 6);
		    
		    String messageId = strMessageIdPart1 + "_" + strMessageIdPart2 + "_" + strMessageIdPart3;
			return messageId;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;		
	}

	//SESSION_UUID = "HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH"			;; 32 hexadecimal digits [0-9][A-F]
	//
	public static String sessionUUID()
	{
		try {
			String uuid = UuidUtil.generateUUID();
		    return uuid.substring(0, 32);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//FILE_UUID = "HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH"			;; 32 hexadecimal digits [0-9][A-F]
	//
	public static String fileUUID()
	{
		try {
			String uuid = UuidUtil.generateUUID();
		    return uuid.substring(0, 32);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//MODEL_UUID = "HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH"			;; 32 hexadecimal digits [0-9][A-F]
	//
	public static String modelUUID()
	{
		try {
			String uuid = UuidUtil.generateUUID();
		    return uuid.substring(0, 32);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
