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
 * WhatchdogResponseMsg.java
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
import br.com.tlmv.emsdapp.utils.FileUtil;
import br.com.tlmv.emsdapp.utils.StringUtil;
import br.com.tlmv.emsdapp.utils.XmlUtil;

//RESPMSG_WHATCHDOG
//
//<?xml version="1.0" encoding="ISO-8859-1"?>
//<Response>
//	<ResponseId><![CDATA[#TAG_MESSAGE_ID#]]></ResponseId>
//	<Response><![CDATA[#TAG_MESSAGE_TYPE#]]></Response>
//	<Status>
//		<StatusCode><![CDATA[#TAG_STATUS_CODE#]]></StatusCode>
//		<StatusMessage><![CDATA[#TAG_STATUS_MESSAGE#]]></StatusMessage>
//	</Status>
//	<ResponseData>
//		<SessionUUID><![CDATA[#TAG_SESSION_UUID#]]></SessionUUID>
//		<SessionDateTime><![CDATA[#TAG_SESSION_DATETIME#]]></SessionDateTime>
//		<SessionTimeMili><![CDATA[#TAG_SESSION_TIME_MILI#]]></SessionTimeMili>
//	</ResponseData>
//</Response>
public class WhatchdogResponseMsg extends BaseMsg 
{
//Private
	private String sessionUUID = null;
	private Date sessionDateTime  = null;
	private long sessionTimeMili = 0;
	
//Public
	
	public WhatchdogResponseMsg()
	{
		super();
	}
	
	public WhatchdogResponseMsg(
		int messageTypeId,
		String messageType,
		String sessionUUID,
		Date sessionDateTime,
		long sessionTimeMili)
	{
		super(messageTypeId, messageType);
		init(sessionUUID, sessionDateTime, sessionTimeMili);
	}
	
	/* Methodes */
	
	public void init(
		String sessionUUID,
		Date sessionDateTime,
		long sessionTimeMili)
	{
		this.sessionUUID = sessionUUID;
		this.sessionDateTime = sessionDateTime;		
		this.sessionTimeMili = sessionTimeMili;		
	}

	public String replaceTags(String strXml)
	{
		String outXml = this.replaceBaseTags(strXml);
		outXml = StringUtil.replaceAll(outXml, AppDefs.FLD_RESPMSG_WHATCHDOG_SESSION_UUID, this.getSessionUUID());
		outXml = StringUtil.replaceAll(outXml, AppDefs.FLD_RESPMSG_WHATCHDOG_SESSION_DATETIME, this.getSessionDateTimeStr());
		outXml = StringUtil.replaceAll(strXml, AppDefs.FLD_RESPMSG_WHATCHDOG_SESSION_TIME_MILI, this.getSessionTimeMiliStr());
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
		
		SimpleDateFormat df = new SimpleDateFormat(AppDefs.DEF_DATETIME_FILE_MASC);
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		
		InputSource is = new InputSource(new StringReader(strXml));
	    Document doc = db.parse(is);

		//TAG: Request
	    Node nRequest = doc.getFirstChild();		    
	    if(nRequest != null) {
			String tmpResponseId = XmlUtil.getChildNodeValueByName(nRequest, AppDefs.XMLTAG_RESPONSE_RESPONSE_ID, false, null);
			String tmpResponse = XmlUtil.getChildNodeValueByName(nRequest, AppDefs.XMLTAG_RESPONSE_RESPONSE, false, null);
				    	
			super.setMessageId(tmpResponseId);
			super.setMessageType(tmpResponse);

			//TAG: Status
		    Node nStatus = XmlUtil.getChildNode(nRequest, AppDefs.XMLTAG_RESPONSE_STATUS);		    
		    if(nStatus != null) {
				String tmpStatusCode = XmlUtil.getChildNodeValueByName(nStatus, AppDefs.XMLTAG_RESPONSE_STATUS_STATUS_CODE, false, null);
				String tmpStatusMessage = XmlUtil.getChildNodeValueByName(nStatus, AppDefs.XMLTAG_RESPONSE_STATUS_STATUS_MESSAGE, false, null);

				super.setStatusCode(Integer.parseInt(tmpStatusCode));
				super.setStatusMessage(tmpStatusMessage);
				
				//TAG: ResponseData
			    Node nResponseData = XmlUtil.getChildNode(nRequest, AppDefs.XMLTAG_RESPONSE_RESPONSE_DATA);		    
			    if(nResponseData != null) {
					String tmpSessionUUID = XmlUtil.getChildNodeValueByName(nResponseData, AppDefs.XMLTAG_RESPONSE_RESPONSE_DATA_SESSION_UUID, false, null);
					String tmpSessionDateTime = XmlUtil.getChildNodeValueByName(nResponseData, AppDefs.XMLTAG_RESPONSE_RESPONSE_DATA_MODEL_UUID, false, null);
					String tmpSessionTimeMili = XmlUtil.getChildNodeValueByName(nResponseData, AppDefs.XMLTAG_RESPONSE_RESPONSE_DATA_MODEL_DATE_TIME, false, null);

					Date valSessionDateTime = df.parse(tmpSessionDateTime);
					
					this.setSessionUUID(tmpSessionUUID);
					this.setSessionDateTime(valSessionDateTime);
					this.setSessionTimeMili(Long.parseLong(tmpSessionTimeMili));
					
					return true;
			    }
		    }
	    }
	    return false;
	}
	
	/* Getters/Setters */
	
	public Date getSessionDateTime() {
		return sessionDateTime;
	}

	public String getSessionDateTimeStr() {
		SimpleDateFormat df1 = new SimpleDateFormat(AppDefs.DEF_DATETIME_MASC_INV);

		String str = (this.sessionDateTime != null) ? df1.format(sessionDateTime) : null;
		return str;
	}

	public void setSessionDateTime(Date sessionDateTime) {
		this.sessionDateTime = sessionDateTime;
	}

	public long getSessionTimeMili() {
		return sessionTimeMili;
	}

	public void setSessionTimeMili(long sessionTimeMili) {
		this.sessionTimeMili = sessionTimeMili;
	}

	public String getSessionTimeMiliStr() {
		String str = Long.toString(this.sessionTimeMili);
		return str;
	}

	public String getSessionUUID() {
		return sessionUUID;
	}

	public void setSessionUUID(String sessionUUID) {
		this.sessionUUID = sessionUUID;
	}
	
}
