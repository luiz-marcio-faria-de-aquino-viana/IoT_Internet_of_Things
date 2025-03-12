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
 * ModelUpdateRequestMsg.java
 */

package br.com.tlmv.emsdapp.msg;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import br.com.tlmv.emsdapp.AppContext;
import br.com.tlmv.emsdapp.AppDefs;
import br.com.tlmv.emsdapp.AppMain;
import br.com.tlmv.emsdapp.utils.FileUtil;
import br.com.tlmv.emsdapp.utils.StringUtil;
import br.com.tlmv.emsdapp.utils.XmlUtil;

//REQMSG_MODELUPDATE
//
//<?xml version="1.0" encoding="ISO-8859-1"?>
//<Request>
//	<RequestId><![CDATA[#TAG_MESSAGE_ID#]]></RequestId>
//	<Command><![CDATA[#TAG_MESSAGE_TYPE#]]></Command>
//	<Params>
//		<SessionUUID><![CDATA[#TAG_SESSION_UUID#]]></SessionUUID>
//	</Params>
//</Request>
public class ModelUpdateRequestMsg extends BaseMsg {
//Private
	private String sessionUUID = null;
	
//Public
	
	public ModelUpdateRequestMsg()
	{
		super();
	}
	
	public ModelUpdateRequestMsg(
		int messageTypeId,
		String messageType,
		String sessionUUID)
	{
		super(messageTypeId, messageType);
		init(sessionUUID);
	}
	
	/* Methodes */
	
	public void init(String sessionUUID)
	{
		this.sessionUUID = sessionUUID;
	}

	public String replaceTags(String strXml)
	{
		String outXml = this.replaceBaseTags(strXml);
		outXml = StringUtil.replaceAll(outXml, AppDefs.FLD_REQMSG_MODELUPDATE_SESSION_UUID, this.getSessionUUID());
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
				String tmpSessionUUID = XmlUtil.getChildNodeValueByName(nParams, AppDefs.XMLTAG_REQUEST_PARAMS_SESSION_UUID, false, null);

				this.setSessionUUID(tmpSessionUUID);
				
				return true;
		    }
	    }
	    return false;
	}
	
	/* Getters/Setters */

	public String getSessionUUID() {
		return sessionUUID;
	}

	public void setSessionUUID(String sessionUUID) {
		this.sessionUUID = sessionUUID;
	}

}
