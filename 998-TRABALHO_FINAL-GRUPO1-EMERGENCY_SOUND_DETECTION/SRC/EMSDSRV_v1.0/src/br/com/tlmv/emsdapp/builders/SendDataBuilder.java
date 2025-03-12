/*
 * COPPE / UFRJ - Universidade Federal do Rio de Janeiro
 * PESC - Programa de Engenharia de Sistemas e Computacao
 *
 * Disciplina: CPS730 - Internet das Coisas
 * Professor: Claudio Miceli
 *
 * Trabalho Final - Emergency Sound Detection
 * Data: 22/SET/2022
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
 * SendDataBuilder.java
 */

package br.com.tlmv.emsdapp.builders;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.tlmv.emsdapp.AppContext;
import br.com.tlmv.emsdapp.AppDefs;
import br.com.tlmv.emsdapp.AppMain;
import br.com.tlmv.emsdapp.utils.FileUtil;
import br.com.tlmv.emsdapp.utils.StringUtil;
import br.com.tlmv.emsdapp.utils.UuidUtil;

public class SendDataBuilder 
{
//Public

	//	<Request>
	//		<RequestId><![CDATA[#TAG_MESSAGE_ID#]]></RequestId>
	//		<Command><![CDATA[#TAG_MESSAGE_TYPE#]]></Command>
	//		<Params>
	//			<SessionUUID><![CDATA[#TAG_SESSION_UUID#]]></SessionUUID>
	//			<FileName><![CDATA[#TAG_FILE_NAME#]]></FileName>
	//			<FileSize><![CDATA[#TAG_FILE_SIZE#]]></FileSize>
	//			<FileDataBase64><![CDATA[#TAG_FILE_DATA_BASE64#]]></FileDataBase64>
	//		</Params>
	//		<Position>
	//			<Latitude><![CDATA[#TAG_LATITUDE#]]></Latitude>
	//			<Longitude><![CDATA[#TAG_LONGITUDE#]]></Longitude>
	//		</Position>
	//	</Request>
	public static String buildSendDataRequestMsg(
		String sessionUuid,
		String fileName,
		long fileSize,
		String fileDataBase64,
		double latitude,
		double longitude) 
	{
		String messageId = UuidUtil.generateMessageId();
		int messageTypeId = AppDefs.REQMSG_SENDDATA_VAL;
		String messageType = AppDefs.REQMSG_SENDDATA_STR;
	
		AppContext context = AppMain.getApp().getContext();
		
		String templFile = context.getTemplatesDir() + "/" + AppDefs.TEMPL_REQUEST_SENDDATA_FILE;
		String xmlData = FileUtil.readData(templFile);
		
		xmlData = StringUtil.replaceAll(xmlData, AppDefs.FLD_REQRESPMSG_BASE_MESSAGE_ID, messageId);
		//xmlData = StringUtil.replaceAll(xmlData, AppDefs.FLD_REQRESPMSG_BASE_MESSAGE_TYPE_ID, Integer.toString(messageTypeId));
		xmlData = StringUtil.replaceAll(xmlData, AppDefs.FLD_REQRESPMSG_BASE_MESSAGE_TYPE, messageType);
		//xmlData = StringUtil.replaceAll(xmlData, AppDefs.FLD_REQRESPMSG_BASE_STATUS_CODE, Integer.toString(AppDefs.RSCODE_NONE));
		//xmlData = StringUtil.replaceAll(xmlData, AppDefs.FLD_REQRESPMSG_BASE_STATUS_MESSAGE, AppDefs.RSMSG_NONE);
		xmlData = StringUtil.replaceAll(xmlData, AppDefs.FLD_REQMSG_SENDDATA_SESSION_UUID, sessionUuid);
		xmlData = StringUtil.replaceAll(xmlData, AppDefs.FLD_REQMSG_SENDDATA_FILE_NAME, fileName);
		xmlData = StringUtil.replaceAll(xmlData, AppDefs.FLD_REQMSG_SENDDATA_FILE_SIZE, Long.toString(fileSize));
		xmlData = StringUtil.replaceAll(xmlData, AppDefs.FLD_REQMSG_SENDDATA_FILE_DATA_BASE64, fileDataBase64);
		xmlData = StringUtil.replaceAll(xmlData, AppDefs.FLD_REQMSG_SENDDATA_LATITUDE, Double.toString(latitude));
		xmlData = StringUtil.replaceAll(xmlData, AppDefs.FLD_REQMSG_SENDDATA_LONGITUDE, Double.toString(longitude));
		
		return xmlData;
	}

	//	<?xml version="1.0" encoding="ISO-8859-1"?>
	//	<Response>
	//		<ResponseId><![CDATA[#TAG_MESSAGE_ID#]]></ResponseId>
	//		<Response><![CDATA[#TAG_MESSAGE_TYPE#]]></Response>
	//		<Status>
	//			<StatusCode><![CDATA[#TAG_STATUS_CODE#]]></StatusCode>
	//			<StatusMessage><![CDATA[#TAG_STATUS_MESSAGE#]]></StatusMessage>
	//		</Status>
	//		<ResponseData>
	//			<SessionUUID><![CDATA[#TAG_SESSION_UUID#]]></SessionUUID>
	//			<FileUUID><![CDATA[#TAG_FILE_UUID#]]></FileUUID>
	//		</ResponseData>
	//	</Response>
	public static String buildSendDataResponseMsg(
		String sessionUuid,
		String fileUuid) 
	{
		SimpleDateFormat df = new SimpleDateFormat(AppDefs.DEF_DATETIME_FILE_MASC);
		
		String messageId = UuidUtil.generateMessageId();
		int messageTypeId = AppDefs.RESPMSG_SENDDATA_VAL;
		String messageType = AppDefs.RESPMSG_SENDDATA_STR;
	
		AppContext context = AppMain.getApp().getContext();
		
		String templFile = context.getTemplatesDir() + "/" + AppDefs.TEMPL_RESPONSE_SENDDATA_FILE;
		String xmlData = FileUtil.readData(templFile);
		
		xmlData = StringUtil.replaceAll(xmlData, AppDefs.FLD_REQRESPMSG_BASE_MESSAGE_ID, messageId);
		//xmlData = StringUtil.replaceAll(xmlData, AppDefs.FLD_REQRESPMSG_BASE_MESSAGE_TYPE_ID, Integer.toString(messageTypeId));
		xmlData = StringUtil.replaceAll(xmlData, AppDefs.FLD_REQRESPMSG_BASE_MESSAGE_TYPE, messageType);
		xmlData = StringUtil.replaceAll(xmlData, AppDefs.FLD_REQRESPMSG_BASE_STATUS_CODE, Integer.toString(AppDefs.RSCODE_NONE));
		xmlData = StringUtil.replaceAll(xmlData, AppDefs.FLD_REQRESPMSG_BASE_STATUS_MESSAGE, AppDefs.RSMSG_NONE);
		xmlData = StringUtil.replaceAll(xmlData, AppDefs.FLD_RESPMSG_SENDDATA_SESSION_UUID, sessionUuid);
		xmlData = StringUtil.replaceAll(xmlData, AppDefs.FLD_RESPMSG_SENDDATA_FILE_UUID, fileUuid);
		
		return xmlData;
	}
	
	//	<?xml version="1.0" encoding="ISO-8859-1"?>
	//	<Response>
	//		<ResponseId><![CDATA[#TAG_MESSAGE_ID#]]></ResponseId>
	//		<Response><![CDATA[#TAG_MESSAGE_TYPE#]]></Response>
	//		<Status>
	//			<StatusCode><![CDATA[#TAG_STATUS_CODE#]]></StatusCode>
	//			<StatusMessage><![CDATA[#TAG_STATUS_MESSAGE#]]></StatusMessage>
	//		</Status>
	//		<ResponseData>
	//			<SessionUUID><![CDATA[#TAG_SESSION_UUID#]]></SessionUUID>
	//			<FileUUID><![CDATA[#TAG_FILE_UUID#]]></FileUUID>
	//		</ResponseData>
	//	</Response>
	public static String buildSendDataResponseError(
		int statusCode,
		String statusMessage) 
	{
		String messageId = UuidUtil.generateMessageId();
		int messageTypeId = AppDefs.RESPMSG_SENDDATA_VAL;
		String messageType = AppDefs.RESPMSG_SENDDATA_STR;
	
		AppContext context = AppMain.getApp().getContext();
		
		String templFile = context.getTemplatesDir() + "/" + AppDefs.TEMPL_RESPONSE_SENDDATA_FILE;
		String xmlData = FileUtil.readData(templFile);
		
		xmlData = StringUtil.replaceAll(xmlData, AppDefs.FLD_REQRESPMSG_BASE_MESSAGE_ID, messageId);
		//xmlData = StringUtil.replaceAll(xmlData, AppDefs.FLD_REQRESPMSG_BASE_MESSAGE_TYPE_ID, Integer.toString(messageTypeId));
		xmlData = StringUtil.replaceAll(xmlData, AppDefs.FLD_REQRESPMSG_BASE_MESSAGE_TYPE, messageType);
		xmlData = StringUtil.replaceAll(xmlData, AppDefs.FLD_REQRESPMSG_BASE_STATUS_CODE, Integer.toString(statusCode));
		xmlData = StringUtil.replaceAll(xmlData, AppDefs.FLD_REQRESPMSG_BASE_STATUS_MESSAGE, statusMessage);
		xmlData = StringUtil.replaceAll(xmlData, AppDefs.FLD_RESPMSG_SENDDATA_SESSION_UUID, AppDefs.DEF_ID_NONE_VALSTR);
		xmlData = StringUtil.replaceAll(xmlData, AppDefs.FLD_RESPMSG_SENDDATA_FILE_UUID, AppDefs.DEF_ID_NONE_VALSTR);
		
		return xmlData;
	}

}
