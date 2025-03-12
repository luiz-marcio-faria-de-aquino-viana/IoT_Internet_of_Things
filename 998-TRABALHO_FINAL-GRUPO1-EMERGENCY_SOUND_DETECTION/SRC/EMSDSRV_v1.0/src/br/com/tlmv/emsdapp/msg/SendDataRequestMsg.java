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
 * SendDataRequestMsg.java
 */

package br.com.tlmv.emsdapp.msg;

import java.io.StringReader;
import java.text.NumberFormat;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import br.com.tlmv.emsdapp.AppContext;
import br.com.tlmv.emsdapp.AppDefs;
import br.com.tlmv.emsdapp.AppMain;
import br.com.tlmv.emsdapp.utils.FileUtil;
import br.com.tlmv.emsdapp.utils.FormatUtil;
import br.com.tlmv.emsdapp.utils.StringUtil;
import br.com.tlmv.emsdapp.utils.XmlUtil;

//REQMSG_SENDDATA
//
//<?xml version="1.0" encoding="ISO-8859-1"?>
//<Request>
//	<RequestId><![CDATA[#TAG_MESSAGE_ID#]]></RequestId>
//	<Command><![CDATA[#TAG_MESSAGE_TYPE#]]></Command>
//	<Params>
//		<SessionUUID><![CDATA[#TAG_SESSION_UUID#]]></SessionUUID>
//		<FileName><![CDATA[#TAG_FILE_NAME#]]></FileName>
//		<FileSize><![CDATA[#TAG_FILE_SIZE#]]></FileSize>
//		<FileDataBase64><![CDATA[#TAG_FILE_DATA_BASE64#]]></FileDataBase64>
//	</Params>
//	<Position>
//		<Latitude><![CDATA[#TAG_LATITUDE#]]></Latitude>
//		<Longitude><![CDATA[#TAG_LONGITUDE#]]></Longitude>
//	</Position>
//</Request>
public class SendDataRequestMsg extends BaseMsg {
//Private
	private String sessionUuid = null;
	private String fileName = null;
	private Long fileSize = null;
	private String fileDataBase64 = null;
	private Double latitude = null;
	private Double longitude = null;
	
//Public
	
	public SendDataRequestMsg()
	{
		super();
	}
	
	public SendDataRequestMsg(
		int messageTypeId,
		String messageType,
		String sessionUuid,
		String fileName,
		Long fileSize,
		String fileDataBase64,
		Double latitude,
		Double longitude)
	{
		super(messageTypeId, messageType);
		init(sessionUuid, 
			 fileName, 
			 fileSize, 
			 fileDataBase64,
			 latitude,
			 longitude);
	}
	
	/* Methodes */
	
	public void init(String sessionUuid, String fileName, Long fileSize, String fileDataBase64, Double latitude, Double longitude)
	{
		this.sessionUuid = sessionUuid;
		this.fileName = fileName;
		this.fileSize = fileSize;
		this.fileDataBase64 = fileDataBase64;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public String replaceTags(String strXml)
	{
		String outXml = this.replaceBaseTags(strXml);
		outXml = StringUtil.replaceAll(outXml, AppDefs.FLD_REQMSG_SENDDATA_SESSION_UUID, this.getSessionUuid());
		outXml = StringUtil.replaceAll(outXml, AppDefs.FLD_REQMSG_SENDDATA_FILE_NAME, this.getFileName());
		outXml = StringUtil.replaceAll(outXml, AppDefs.FLD_REQMSG_SENDDATA_FILE_SIZE, this.getFileSizeStr());
		outXml = StringUtil.replaceAll(outXml, AppDefs.FLD_REQMSG_SENDDATA_FILE_DATA_BASE64, this.getFileDataBase64());
		outXml = StringUtil.replaceAll(outXml, AppDefs.FLD_REQMSG_SENDDATA_LATITUDE, this.getLatitudeStr());
		outXml = StringUtil.replaceAll(outXml, AppDefs.FLD_REQMSG_SENDDATA_LONGITUDE, this.getLongitudeStr());
		return outXml;
	}
	
	public String toXml()
	{
		AppContext ctx = AppMain.getApp().getContext();
		
		String templFile = ctx.getTemplReqSendDataFile();
		
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
				String tmpFileName = XmlUtil.getChildNodeValueByName(nParams, AppDefs.XMLTAG_REQUEST_PARAMS_FILE_NAME, false, null);
				String tmpFileSize = XmlUtil.getChildNodeValueByName(nParams, AppDefs.XMLTAG_REQUEST_PARAMS_FILE_SIZE, false, null);
				String tmpFileDataBase64 = XmlUtil.getChildNodeValueByName(nParams, AppDefs.XMLTAG_REQUEST_PARAMS_FILE_DATA_BASE64, false, null);

				this.setSessionUuid(tmpSessionUUID);
				this.setFileName(tmpFileName);
				this.setFileSize(Long.parseLong(tmpFileSize));
				this.setFileDataBase64(tmpFileDataBase64);
				
				//TAG: Params
			    Node nPosition = XmlUtil.getChildNode(nRequest, AppDefs.XMLTAG_REQUEST_POSITION);		    
			    if(nPosition != null) {
					String tmpLatitude = XmlUtil.getChildNodeValueByName(nPosition, AppDefs.XMLTAG_REQUEST_POSITION_LATITUDE, false, null);
					String tmpLongitude = XmlUtil.getChildNodeValueByName(nPosition, AppDefs.XMLTAG_REQUEST_POSITION_LONGITUDE, false, null);

					this.setLatitude(Double.parseDouble(tmpLatitude));
					this.setLongitude(Double.parseDouble(tmpLongitude));
					
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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public String getFileSizeStr() {
		NumberFormat nf = FormatUtil.newNumberFormatEnUs(6);
		String str = nf.format(fileSize);
		return str;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileDataBase64() {
		return fileDataBase64;
	}

	public void setFileDataBase64(String fileDataBase64) {
		this.fileDataBase64 = fileDataBase64;
	}

	public Double getLatitude() {
		return latitude;
	}
	
	public String getLatitudeStr() {
		NumberFormat nf = FormatUtil.newNumberFormatEnUs(6);
		String str = nf.format(latitude);
		return str;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public String getLongitudeStr() {
		NumberFormat nf = FormatUtil.newNumberFormatEnUs(6);
		String str = nf.format(longitude);
		return str;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

}
