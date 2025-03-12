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
 * DatabaseConnection.java
 */

package br.com.tlmv.emsdapp.data;

import javax.xml.soap.Node;

import br.com.tlmv.emsdapp.AppDefs;
import br.com.tlmv.emsdapp.utils.XmlUtil;
import br.com.tlmv.emsdapp.vo.RecordVO;

public class ConnectionData extends ItemData
{
//Private
	private String dsname;
	private String driver;
	private String url;
	private String user;
	private String pwd;
	
//Public

	public ConnectionData()
	{
		super(AppDefs.PG_DSNAME, AppDefs.PG_DSNAME);

		this.dsname = AppDefs.PG_DSNAME;
		this.driver = AppDefs.PG_DRIVER;
		this.url = AppDefs.PG_URL;
		this.user = AppDefs.PG_USER;
		this.pwd = AppDefs.PG_PWD;
	}		

	public ConnectionData(
		String dsname,
		String driver,
		String url,
		String user,
		String pwd)
	{
		super(dsname, dsname);
		
		this.dsname = dsname;
		this.driver = driver;
		this.url = url;
		this.user = user;
		this.pwd = pwd;
	}

	public ConnectionData(ConnectionData o)
	{
		super(o.getItemDataId(), o.getDescricao());
		
		this.dsname = o.getDsname();
		this.driver = o.getDriver();
		this.url = o.getUrl();
		this.user = o.getUser();
		this.pwd = o.getPwd();
	}

	public ConnectionData(Node nDatabase)
	{
		this();
		
		loadData(nDatabase, this);
	}
	
	/* Methodes */

	public void loadData(Node nDatabase, ConnectionData oDefault)
	{
		//CONFIG
		this.dsname = XmlUtil.getChildNodeValueByName(nDatabase, AppDefs.CFGTAG_CONFIGURACAO_DATABASE_CONNECTION_DSNAME, false, oDefault.getDsname());
		this.driver = XmlUtil.getChildNodeValueByName(nDatabase, AppDefs.CFGTAG_CONFIGURACAO_DATABASE_CONNECTION_DRIVER, false, oDefault.getDriver());
		this.url = XmlUtil.getChildNodeValueByName(nDatabase, AppDefs.CFGTAG_CONFIGURACAO_DATABASE_CONNECTION_URL, false, oDefault.getUrl());
		this.user = XmlUtil.getChildNodeValueByName(nDatabase, AppDefs.CFGTAG_CONFIGURACAO_DATABASE_CONNECTION_USERNAME, false, oDefault.getUser());
		this.pwd = XmlUtil.getChildNodeValueByName(nDatabase, AppDefs.CFGTAG_CONFIGURACAO_DATABASE_CONNECTION_PASSWORD, false, oDefault.getPwd());
		
		//Item Data
		super.setItemDataId(this.dsname);
		super.setDescricao(this.dsname);
	}
		
	public String toXml()
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append(XmlUtil.createXmlElem(AppDefs.CFGTAG_CONFIGURACAO_DATABASE_CONNECTION_DSNAME, this.dsname, 3, false));
		sb.append(XmlUtil.createXmlElem(AppDefs.CFGTAG_CONFIGURACAO_DATABASE_CONNECTION_DRIVER, this.driver, 3, false));
		sb.append(XmlUtil.createXmlElem(AppDefs.CFGTAG_CONFIGURACAO_DATABASE_CONNECTION_URL, this.url, 3, false));
		sb.append(XmlUtil.createXmlElem(AppDefs.CFGTAG_CONFIGURACAO_DATABASE_CONNECTION_USERNAME, this.user, 3, false));
		sb.append(XmlUtil.createXmlElem(AppDefs.CFGTAG_CONFIGURACAO_DATABASE_CONNECTION_PASSWORD, this.pwd, 3, false));
		
		String result = XmlUtil.createXmlElemStartEndTags(AppDefs.CFGTAG_CONFIGURACAO_DATABASE_CONNECTION, sb.toString(), 2);		
		return result;
	}
	
	public String toString()
	{
		String str = String.format("%s", this.getDescricao());
		return str;
	}
    
    /* Getters/Setters */
    
	public String getDsname() {
		return this.dsname;
	}

	public void setDsname(String dsname) {
		this.dsname = dsname;
	}

	public String getDriver() {
		return this.driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return this.user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
    
}
