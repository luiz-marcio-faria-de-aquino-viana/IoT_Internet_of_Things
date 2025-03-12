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
 * BaseMsg.java
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
import br.com.tlmv.emsdapp.data.ConnectionData;
import br.com.tlmv.emsdapp.utils.StringUtil;
import br.com.tlmv.emsdapp.utils.UuidUtil;
import br.com.tlmv.emsdapp.utils.XmlUtil;

public class BaseMsg 
{
//Private
	private String messageId = AppDefs.DEF_ID_NONE_VALSTR;
	private int messageTypeId = AppDefs.DEF_ID_NONE_VALINT;
	private String messageType = AppDefs.DEF_ID_NONE_VALSTR;
	private int statusCode = AppDefs.RSCODE_NONE;
	private String statusMessage = AppDefs.RSMSG_NONE;
	
//Public
	
	/* Methodes */
	
	public BaseMsg()
	{
		initBase(AppDefs.DEF_ID_NONE_VALINT, AppDefs.DEF_ID_NONE_VALSTR);
	}
		
	public BaseMsg(
		int messageTypeId,
		String messageType)
	{
		initBase(messageTypeId, messageType);
	}
	
	public void initBase(int messageTypeId, String messageType)
	{
		this.messageId = UuidUtil.generateMessageId();
		this.messageTypeId = messageTypeId;		
		this.messageType = messageType;		
		this.statusCode = AppDefs.RSCODE_NONE;
		this.statusMessage = AppDefs.RSMSG_NONE;
	}
	
	/* Utility Functions */

	public boolean isRequest()
	{
		if( (this.messageTypeId >= AppDefs.REQMSG_MINVAL) &&
			(this.messageTypeId <= AppDefs.REQMSG_MAXVAL) ) {
			return true;
		}
		return false;
	}

	public boolean isResponse()
	{
		if( (this.messageTypeId >= AppDefs.RESPMSG_MINVAL) &&
			(this.messageTypeId <= AppDefs.RESPMSG_MAXVAL) ) {
			return true;
		}
		return false;
	}
	
	public void setStatus(
		int statusCode,
		String statusMessage) {
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;		
	}
	
	public String replaceBaseTags(String strXml)
	{
		String outXml = StringUtil.replaceAll(strXml, AppDefs.FLD_REQRESPMSG_BASE_MESSAGE_ID, this.getMessageId());
		outXml = StringUtil.replaceAll(outXml, AppDefs.FLD_REQRESPMSG_BASE_MESSAGE_TYPE_ID, this.getMessageTypeIdStr());
		outXml = StringUtil.replaceAll(outXml, AppDefs.FLD_REQRESPMSG_BASE_MESSAGE_TYPE, this.getMessageType());
		outXml = StringUtil.replaceAll(outXml, AppDefs.FLD_REQRESPMSG_BASE_STATUS_CODE, this.getStatusCodeStr());
		outXml = StringUtil.replaceAll(outXml, AppDefs.FLD_REQRESPMSG_BASE_STATUS_MESSAGE, this.getStatusMessage());
		return outXml;
	}
	
	//BASE_REQUEST
	//
	//	<?xml version="1.0" encoding="ISO-8859-1"?>
	//	<Request>
	//		<RequestId><![CDATA[#TAG_MESSAGE_ID#]]></RequestId>
	//		<Command><![CDATA[#TAG_MESSAGE_TYPE#]]></Command>
	//			:
	//	</Request>
	public boolean baseRequestParser(String strXml) 
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
				    	
			this.setMessageId(tmpRequestId);
			this.setMessageType(tmpCommand);

			return true;
	    }
	    return false;
	}
	
	//BASE_RESPONSE
	//
	//<?xml version="1.0" encoding="ISO-8859-1"?>
	//<Response>
	//		<ResponseId><![CDATA[#TAG_MESSAGE_ID#]]></ResponseId>
	//		<Response><![CDATA[#TAG_MESSAGE_TYPE#]]></Response>
	//		<Status>
	//			<StatusCode><![CDATA[#TAG_STATUS_CODE#]]></StatusCode>
	//			<StatusMessage><![CDATA[#TAG_STATUS_MESSAGE#]]></StatusMessage>
	//		</Status>
	//			:
	//</Response>
	public boolean baseResponseParser(String strXml) 
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
			String tmpResponseId = XmlUtil.getChildNodeValueByName(nRequest, AppDefs.XMLTAG_RESPONSE_RESPONSE_ID, false, null);
			String tmpResponse = XmlUtil.getChildNodeValueByName(nRequest, AppDefs.XMLTAG_RESPONSE_RESPONSE, false, null);
				    	
			this.setMessageId(tmpResponseId);
			this.setMessageType(tmpResponse);

			//TAG: Status
		    Node nStatus = XmlUtil.getChildNode(nRequest, AppDefs.XMLTAG_RESPONSE_STATUS);		    
		    if(nStatus != null) {
				String tmpStatusCode = XmlUtil.getChildNodeValueByName(nStatus, AppDefs.XMLTAG_RESPONSE_STATUS_STATUS_CODE, false, null);
				String tmpStatusMessage = XmlUtil.getChildNodeValueByName(nStatus, AppDefs.XMLTAG_RESPONSE_STATUS_STATUS_MESSAGE, false, null);

				this.setStatusCode(Integer.parseInt(tmpStatusCode));
				this.setStatusMessage(tmpStatusMessage);
				
				return true;
		    }
	    }
	    return false;
	}

	public void debug()
	{
		String outStr = 
			"BASEMSG - MessageId: " + this.getMessageId() + "," +
			"MessageTypeId: " + this.getMessageTypeIdStr() + "," +
			"MessageType: " + this.getMessageType() + "," +
			"statusCode: " + this.getStatusCodeStr() + "," +
			"statusMessage: " + this.getStatusMessage();
		System.out.println(outStr);
	}
	
	/* Getters/Setters */

	public String getMessageId() {
		return messageId;
	}
	
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	
	public int getMessageTypeId() {
		return messageTypeId;
	}
	
	public String getMessageTypeIdStr() {
		return Integer.toString(messageTypeId);
	}
	
	public void setMessageTypeId(int messageTypeId) {
		this.messageTypeId = messageTypeId;
	}
	
	public String getMessageType() {
		return messageType;
	}
	
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	
	public int getStatusCode() {
		return statusCode;
	}
	
	public String getStatusCodeStr() {
		return Integer.toString(statusCode);
	}
	
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	
	public String getStatusMessage() {
		return statusMessage;
	}
	
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}	
	
}
