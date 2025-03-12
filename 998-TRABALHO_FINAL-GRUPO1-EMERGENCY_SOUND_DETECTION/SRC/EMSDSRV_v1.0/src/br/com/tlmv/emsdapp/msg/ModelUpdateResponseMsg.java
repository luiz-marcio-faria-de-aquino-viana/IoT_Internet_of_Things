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
 * ModelUpdateResponseMsg.java
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

//RESPMSG_MODELUPDATE
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
//		<ModelUUID><![CDATA[#TAG_MODEL_UUID#]]></ModelUUID>
//		<ModelDateTime><![CDATA[#TAG_MODEL_DATE_TIME#]]></ModelDateTime>
//		<ModelDataBase64><![CDATA[#TAG_MODEL_DATA_BASE64#]]></ModelDataBase64>
//	</ResponseData>
//</Response>
public class ModelUpdateResponseMsg extends BaseMsg {
//Private
	private String sessionUuid = null;
	private String modelUuid = null;
	private Date modelDateTime = null;
	private String modelDataBase64 = null;
	
//Public
	
	public ModelUpdateResponseMsg()
	{
		super();
	}
	
	public ModelUpdateResponseMsg(
		int messageTypeId,
		String messageType,
		String sessionUuid,
		String modelUuid,
		Date modelDateTime,
		String modelDataBase64)
	{
		super(messageTypeId, messageType);
		init(sessionUuid,
			 modelUuid,
			 modelDateTime,
			 modelDataBase64);
	}
	
	/* Methodes */
	
	public void init(String sessionUuid,
					 String modelUuid,
					 Date modelDateTime,
					 String modelDataBase64)
	{
		this.sessionUuid = sessionUuid;
		this.modelUuid = modelUuid;
		this.modelDateTime = modelDateTime;
		this.modelDataBase64 = modelDataBase64;
	}

	public String replaceTags(String strXml)
	{
		String outXml = this.replaceBaseTags(strXml);
		outXml = StringUtil.replaceAll(outXml, AppDefs.FLD_RESPMSG_MODELUPDATE_SESSION_UUID, this.getSessionUuid());
		outXml = StringUtil.replaceAll(outXml, AppDefs.FLD_RESPMSG_MODELUPDATE_MODEL_UUID, this.getModelUuid());
		outXml = StringUtil.replaceAll(outXml, AppDefs.FLD_RESPMSG_MODELUPDATE_MODEL_DATETIME, this.getModelDateTimeStr());
		outXml = StringUtil.replaceAll(outXml, AppDefs.FLD_RESPMSG_MODELUPDATE_MODEL_DATA_BASE64, this.getModelDataBase64());
		return outXml;
	}
	
	public String toXml()
	{
		AppContext ctx = AppMain.getApp().getContext();
		
		String templFile = ctx.getTemplRespModelUpdateFile();
		
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
					String tmpModelUUID = XmlUtil.getChildNodeValueByName(nResponseData, AppDefs.XMLTAG_RESPONSE_RESPONSE_DATA_MODEL_UUID, false, null);
					String tmpModelDateTime = XmlUtil.getChildNodeValueByName(nResponseData, AppDefs.XMLTAG_RESPONSE_RESPONSE_DATA_MODEL_DATE_TIME, false, null);
					String tmpModelDataBase64 = XmlUtil.getChildNodeValueByName(nResponseData, AppDefs.XMLTAG_RESPONSE_RESPONSE_DATA_MODEL_DATA_BASE64, false, null);

					//Date valModelDateTime = df.parse(tmpModelDateTime);
					Date valModelDateTime = new Date();
					
					this.setSessionUuid(tmpSessionUUID);
					this.setModelUuid(tmpModelUUID);
					this.setModelDateTime(valModelDateTime);
					this.setModelDataBase64(tmpModelDataBase64);
					
					return true;
			    }
		    }
	    }
	    return false;
	}

	/* Getters/Setters */

	public String getSessionUuid() {
		return sessionUuid;
	}

	public void setSessionUuid(String sessionUuid) {
		this.sessionUuid = sessionUuid;
	}

	public String getModelUuid() {
		return modelUuid;
	}

	public void setModelUuid(String modelUuid) {
		this.modelUuid = modelUuid;
	}

	public Date getModelDateTime() {
		return modelDateTime;
	}

	public String getModelDateTimeStr() {
		if(modelDateTime == null) return "";
		
		SimpleDateFormat df = new SimpleDateFormat(AppDefs.DEF_DATETIME_FILE_MASC);
		String str = df.format(modelDateTime);
		return str;
	}

	public void setModelDateTime(Date modelDateTime) {
		this.modelDateTime = modelDateTime;
	}

	public String getModelDataBase64() {
		return modelDataBase64;
	}

	public void setModelDataBase64(String modelDataBase64) {
		this.modelDataBase64 = modelDataBase64;
	}

}
