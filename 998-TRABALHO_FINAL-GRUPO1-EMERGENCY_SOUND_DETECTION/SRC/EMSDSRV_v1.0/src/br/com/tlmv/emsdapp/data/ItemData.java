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
 * ItemData.java
 */

package br.com.tlmv.emsdapp.data;

import java.util.Date;

import br.com.tlmv.emsdapp.utils.StringUtil;

public class ItemData 
{
//Private
	private String itemDataId;
	private String descricao;
	private int intVal;
	private int intVal2;
	private int intVal3;
	private double dblVal;
	private long lngVal;
	private Date dateVal;
	private String strVal;
	private boolean bShowAttr;
	
//Public
	
	public ItemData()
	{
		this.itemDataId = "-1";
		this.descricao = "";
	}
	
	public ItemData(
		Integer itemDataId,
		String descricao)
	{
		this.itemDataId = StringUtil.valToStr(itemDataId);
		this.descricao = descricao;
	}
		
	public ItemData(
		Integer itemDataId,
		String descricao,
		boolean bShowAttr)
	{
		this.itemDataId = StringUtil.valToStr(itemDataId);
		this.descricao = descricao;
		this.bShowAttr = bShowAttr;
	}
	
	public ItemData(
		String itemDataId,
		String descricao)
	{
		this.itemDataId = itemDataId;
		this.descricao = descricao;
	}
	
	public ItemData(
		String itemDataId,
		String descricao,
		boolean bShowAttr)
	{
		this.itemDataId = itemDataId;
		this.descricao = descricao;
		this.bShowAttr = bShowAttr;
	}
	
	public ItemData(
		String itemDataId,
		String descricao,
		int intVal)
	{
		this.itemDataId = itemDataId;
		this.descricao = descricao;
		this.intVal = intVal;
	}
	
	public ItemData(
		String itemDataId,
		String descricao,
		int intVal,
		int intVal2)
	{
		this.itemDataId = itemDataId;
		this.descricao = descricao;
		this.intVal = intVal;
		this.intVal2 = intVal2;
	}
	
	public ItemData(
		String itemDataId,
		String descricao,
		int intVal,
		int intVal2,
		int intVal3)
	{
		this.itemDataId = itemDataId;
		this.descricao = descricao;
		this.intVal = intVal;
		this.intVal2 = intVal2;
		this.intVal3 = intVal3;
	}
		
	public ItemData(
		String itemDataId,
		String descricao,
		String strVal,
		int intVal,
		int intVal2)
	{
		this.itemDataId = itemDataId;
		this.descricao = descricao;
		this.strVal = strVal;
		this.intVal = intVal;
		this.intVal2 = intVal2;
	}
	
	public ItemData(
		String itemDataId,
		String descricao,
		String strVal,
		int intVal,
		int intVal2,
		int intVal3)
	{
		this.itemDataId = itemDataId;
		this.descricao = descricao;
		this.strVal = strVal;
		this.intVal = intVal;
		this.intVal2 = intVal2;
		this.intVal3 = intVal3;
	}
			
	public ItemData(ItemData o)
	{
		this.itemDataId = o.itemDataId;
		this.descricao = o.descricao;
		this.intVal = intVal;
		this.dblVal = dblVal;
		this.lngVal = lngVal;
		this.dateVal = dateVal;
		this.strVal = strVal;
		this.bShowAttr = bShowAttr;
	}

	/* Methodes */
	
	public String toString()
	{
		String str = this.descricao;

		if( bShowAttr )
			str += " - valor: " + this.strVal;
		return str;
	}
	
	/* Getters/Setters */
		
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getItemDataId() {
		return itemDataId;
	}

	public void setItemDataId(String itemDataId) {
		this.itemDataId = itemDataId;
	}

	public int getIntVal() {
		return intVal;
	}

	public void setIntVal(int intVal) {
		this.intVal = intVal;
	}

	public double getDblVal() {
		return dblVal;
	}

	public void setDblVal(double dblVal) {
		this.dblVal = dblVal;
	}

	public long getLngVal() {
		return lngVal;
	}

	public void setLngVal(long lngVal) {
		this.lngVal = lngVal;
	}

	public Date getDateVal() {
		return dateVal;
	}

	public void setDateVal(Date dateVal) {
		this.dateVal = dateVal;
	}

	public String getStrVal() {
		return strVal;
	}

	public void setStrVal(String strVal) {
		this.strVal = strVal;
	}

	public boolean isShowAttr() {
		return bShowAttr;
	}

	public void setShowAttr(boolean bShowAttr) {
		this.bShowAttr = bShowAttr;
	}

	public int getIntVal2() {
		return intVal2;
	}

	public void setIntVal2(int intVal2) {
		this.intVal2 = intVal2;
	}

	public int getIntVal3() {
		return intVal3;
	}

	public void setIntVal3(int intVal3) {
		this.intVal3 = intVal3;
	}	
	
}
