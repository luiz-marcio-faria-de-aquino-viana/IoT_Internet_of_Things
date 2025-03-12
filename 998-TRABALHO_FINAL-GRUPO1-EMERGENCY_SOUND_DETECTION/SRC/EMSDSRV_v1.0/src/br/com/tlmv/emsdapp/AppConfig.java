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
 * AppConfig.java
 */

package br.com.tlmv.emsdapp;

import java.io.StringReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import br.com.tlmv.emsdapp.data.ConnectionData;
import br.com.tlmv.emsdapp.utils.FileUtil;
import br.com.tlmv.emsdapp.utils.XmlUtil;

public class AppConfig 
{
//Private
	private String configFile = null;
	//
	private String dsName = null;
	private String driver = null;
	private String url = null;
	private String username = null;
	private String password = null;
	//
	private ConnectionData databaseConnection = null;

//Public
	
	public AppConfig(String fileName)
		throws Exception
	{
		this.configFile = FileUtil.readData(fileName);
		loadData(this.configFile);
	}
	
	/* Methodes */
	
	public void loadData(String xmlData)
		throws Exception
	{
		AppContext ctx = AppMain.getApp().getContext();
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		
		InputSource is = new InputSource(new StringReader(xmlData));
	    Document doc = db.parse(is);

	    //LOAD: Config Data
	    
		//TAG: Configuracao
	    Node nConfig = doc.getFirstChild();		    
	    if(nConfig != null) {
			//TAG: DatabaseConnection
		    Node nDbConn = XmlUtil.getChildNode(nConfig, AppDefs.CFGTAG_CONFIGURACAO_DATABASE_CONNECTION);		    
		    if(nDbConn != null) {
				//Database Connection Parameters
				this.dsName = XmlUtil.getChildNodeValueByName(nDbConn, AppDefs.CFGTAG_CONFIGURACAO_DATABASE_CONNECTION_DSNAME, false, null);
				this.driver = XmlUtil.getChildNodeValueByName(nDbConn, AppDefs.CFGTAG_CONFIGURACAO_DATABASE_CONNECTION_DRIVER, false, null);
				this.url = XmlUtil.getChildNodeValueByName(nDbConn, AppDefs.CFGTAG_CONFIGURACAO_DATABASE_CONNECTION_URL, false, null);
				this.username = XmlUtil.getChildNodeValueByName(nDbConn, AppDefs.CFGTAG_CONFIGURACAO_DATABASE_CONNECTION_USERNAME, false, null);
				this.password = XmlUtil.getChildNodeValueByName(nDbConn, AppDefs.CFGTAG_CONFIGURACAO_DATABASE_CONNECTION_PASSWORD, false, null);
	
			    this.databaseConnection = new ConnectionData(
			    	this.dsName,
		    		this.driver,
		    		this.url,
		    		this.username,
		    		this.password);	    
		    }
	    }
	}
	
	public void saveData(String fileName, String xmlData)
	{
		try {
			DateFormat df = new SimpleDateFormat(AppDefs.DEF_DATETIME_FILE_MASC);

			Date dataAtual = new Date();
			
			if( FileUtil.isExistFile(fileName) ) {
				String bakFileName = String.format("%s.%s", fileName, df.format(dataAtual)); 
				FileUtil.renameFile(fileName, bakFileName);
			}
			FileUtil.writeData(fileName, xmlData, false);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public String toXml()
	{
		StringBuilder sb1 = new StringBuilder();
		sb1.append(XmlUtil.createXmlElem(AppDefs.CFGTAG_CONFIGURACAO_DATABASE_CONNECTION_DSNAME, this.dsName, 1, false));
		sb1.append(XmlUtil.createXmlElem(AppDefs.CFGTAG_CONFIGURACAO_DATABASE_CONNECTION_DRIVER, this.driver, 1, false));
		sb1.append(XmlUtil.createXmlElem(AppDefs.CFGTAG_CONFIGURACAO_DATABASE_CONNECTION_URL, this.url, 1, false));
		sb1.append(XmlUtil.createXmlElem(AppDefs.CFGTAG_CONFIGURACAO_DATABASE_CONNECTION_USERNAME, this.username, 1, false));
		sb1.append(XmlUtil.createXmlElem(AppDefs.CFGTAG_CONFIGURACAO_DATABASE_CONNECTION_PASSWORD, this.password, 1, false));

		StringBuilder sb2 = new StringBuilder();
		sb2.append(XmlUtil.createXmlElem(AppDefs.CFGTAG_CONFIGURACAO_DATABASE_CONNECTION, sb1.toString(), 1, false));

		StringBuilder sb3 = new StringBuilder();
		sb3.append(XmlUtil.createXmlDocElemISO8859(AppDefs.CFGTAG_CONFIGURACAO, sb3.toString()));
		return sb3.toString();
	}
	
	/* Methodes */
	
	/* Getters/Setters */
	
	public String getDsName() {
		return dsName;
	}
	public void setDsName(String dsName) {
		this.dsName = dsName;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}	

}
