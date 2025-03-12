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
 * WhatchdogRequestMsg.java
 */

package br.com.tlmv.emsdapp.msg;

import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import br.com.tlmv.emsdapp.AppContext;
import br.com.tlmv.emsdapp.AppDefs;
import br.com.tlmv.emsdapp.AppMain;
import br.com.tlmv.emsdapp.data.ConnectionData;
import br.com.tlmv.emsdapp.utils.FileUtil;
import br.com.tlmv.emsdapp.utils.StringUtil;
import br.com.tlmv.emsdapp.utils.XmlUtil;

//REQMSG_LOGIN
//
//<?xml version="1.0" encoding="ISO-8859-1"?>
//<Request>
//	<RequestId><![CDATA[#TAG_MESSAGE_ID#]]></RequestId>
//	<Command><![CDATA[#TAG_MESSAGE_TYPE#]]></Command>
//	<Params>
//		<RemoteUnitId><![CDATA[#TAG_REMOTE_UNIT_ID#]]></RemoteUnitId>
//		<Username><![CDATA[#TAG_USERNAME#]]></Username>
//		<Password><![CDATA[#TAG_PASSWORD#]]></Password>
//	</Params>
//</Request>
public class LoginRequestMsg extends BaseMsg 
{
//Private
	private String remoteUnitId = null;
	private String username = null;
	private String password = null;
	
//Public
	
	public LoginRequestMsg()
	{
		super();
	}

	public LoginRequestMsg(
		int messageTypeId,
		String messageType,
		String remoteUnitId,
		String username,
		String password)
	{
		super(messageTypeId, messageType);
		init(remoteUnitId, 
			 username,
			 password);
	}
	
	/* Methodes */
	
	public void init(String remoteUnitId,
					 String username,
					 String password)
	{
		this.remoteUnitId = remoteUnitId;
		this.username = username;
		this.password = password;
	}

	public String replaceTags(String strXml)
	{
		String outXml = this.replaceBaseTags(strXml);
		outXml = StringUtil.replaceAll(outXml, AppDefs.FLD_REQMSG_LOGIN_REMOTE_UNIT_ID, this.getRemoteUnitId());
		outXml = StringUtil.replaceAll(outXml, AppDefs.FLD_REQMSG_LOGIN_USERNAME, this.getUsername());
		outXml = StringUtil.replaceAll(outXml, AppDefs.FLD_REQMSG_LOGIN_PASSWORD, this.getPassword());
		return outXml;
	}
	
	public String toXml()
	{
		AppContext ctx = AppMain.getApp().getContext();
		
		String templFile = ctx.getTemplRespLoginFile();
		
		String strXml = FileUtil.readData(templFile);

		String outXml = this.replaceTags(strXml);
		return outXml;
	}

	public boolean parser(String strXml) 
		throws Exception
	{
		AppContext ctx = AppMain.getApp().getContext();
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		
		InputSource is = new InputSource(new StringReader(strXml));
	    Document doc = db.parse(is);

		//TAG: Request
	    Node nRequest = doc.getFirstChild();		    
	    if(nRequest != null) {
			String tmpRequestId = XmlUtil.getChildNodeValueByName(nRequest, AppDefs.XMLTAG_REQUEST_REQUEST_ID, false, null);
			String tmpCommand = XmlUtil.getChildNodeValueByName(nRequest, AppDefs.XMLTAG_REQUEST_COMMAND, false, null);
				    	
			super.setMessageId(tmpRequestId);
			super.setMessageType(tmpCommand);

			//TAG: Params
		    Node nParams = XmlUtil.getChildNode(nRequest, AppDefs.XMLTAG_REQUEST_PARAMS);		    
		    if(nParams != null) {
				String tmpRemoteUnitId = XmlUtil.getChildNodeValueByName(nParams, AppDefs.XMLTAG_REQUEST_PARAMS_REMOTEUNIT_ID, false, null);
				String tmpUsername = XmlUtil.getChildNodeValueByName(nParams, AppDefs.XMLTAG_REQUEST_PARAMS_USERNAME, false, null);
				String tmpPassword = XmlUtil.getChildNodeValueByName(nParams, AppDefs.XMLTAG_REQUEST_PARAMS_PASSWORD, false, null);

				this.setRemoteUnitId(tmpRemoteUnitId);
				this.setUsername(tmpUsername);
				this.setPassword(tmpPassword);
				
				return true;
		    }
	    }
	    return false;
	}
		
	/* Getters/Setters */
	
	public String getRemoteUnitId() {
		return remoteUnitId;
	}

	public void setRemoteUnitId(String remoteUnitId) {
		this.remoteUnitId = remoteUnitId;
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
