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
 * ListUtil.java
 */

package br.com.tlmv.emsdapp.utils;

import java.util.ArrayList;

import br.com.tlmv.emsdapp.vo.RecordVO;

public class ListUtil 
{
//Public
	
	//ListUtil: String Type

	public static int findItemDataPosInList(ArrayList<RecordVO> lsItem, String idItem)
	{
		for(int i = 0; i < lsItem.size(); i++)
		{
			RecordVO o = lsItem.get(i);

			if( idItem.compareToIgnoreCase(o.getRecordId()) == 0 )
				return i;
		}
		return -1;
	}
	
	public static int findItemDataPosInListByDescricao(ArrayList<RecordVO> lsItem, String descrItem)
	{
		for(int i = 0; i < lsItem.size(); i++)
		{
			RecordVO o = lsItem.get(i);

			if( descrItem.compareToIgnoreCase(o.getDescricao()) == 0 )
				return i;
		}
		return -1;
	}

	public static int findItemDataPosInList(RecordVO[] lsItem, String idItem)
	{
		for(int i = 0; i < lsItem.length; i++)
		{
			RecordVO o = lsItem[i];

			if( idItem.compareToIgnoreCase(o.getRecordId()) == 0 )
				return i;
		}
		return -1;
	}

	public static int findItemDataPosInListByDescricao(RecordVO[] lsItem, String descrItem)
	{
		for(int i = 0; i < lsItem.length; i++)
		{
			RecordVO o = lsItem[i];

			if( descrItem.compareToIgnoreCase(o.getDescricao()) == 0 )
				return i;
		}
		return -1;
	}

	public static int findPosInList(ArrayList<String> lsStr, String inStr)
	{
		for(int i = 0; i < lsStr.size(); i++)
		{
			String str = lsStr.get(i);
			
			if(inStr.compareToIgnoreCase(str) == 0)
				return i;
		}
		return -1;
	}
	
	public static int findPosInList(String[] lsStr, String inStr)
	{
		for(int i = 0; i < lsStr.length; i++)
		{
			String str = lsStr[i];
			
			if(inStr.compareToIgnoreCase(str) == 0)
				return i;
		}
		return -1;
	}
	
	public static int findPosInListStartWith(String[] lsStr, String inStr)
	{
		String inStrL = inStr.toLowerCase();
		
		for(int i = 0; i < lsStr.length; i++)
		{
			String strL = lsStr[i].toLowerCase();
			
			if( inStrL.startsWith(strL) )
				return i;
		}
		return -1;
	}
	
	public static int findPosInArray(int[] lsVal, int inVal)
	{
		for(int i = 0; i < lsVal.length; i++)
		{
			if(inVal == lsVal[i])
				return i;
		}
		return -1;
	}
	
}
