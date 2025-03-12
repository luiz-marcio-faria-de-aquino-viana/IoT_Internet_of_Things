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
 * ModelUpdateBuilder.java
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

public class ModelUpdateBuilder 
{
//Public

	//	<?xml version="1.0" encoding="ISO-8859-1"?>
	//	<Request>
	//		<RequestId><![CDATA[#TAG_MESSAGE_ID#]]></RequestId>
	//		<Command><![CDATA[#TAG_MESSAGE_TYPE#]]></Command>
	//		<Params>
	//			<SessionUUID><![CDATA[#TAG_SESSION_UUID#]]></SessionUUID>
	//		</Params>
	//	</Request>
	public static String buildModelUpdateRequestMsg(
		String sessionUuid) 
	{
		String messageId = UuidUtil.generateMessageId();
		int messageTypeId = AppDefs.REQMSG_MODELUPDATE_VAL;
		String messageType = AppDefs.REQMSG_MODELUPDATE_STR;
	
		AppContext context = AppMain.getApp().getContext();
		
		String templFile = context.getTemplatesDir() + "/" + AppDefs.TEMPL_REQUEST_MODELUPDATE_FILE;
		String xmlData = FileUtil.readData(templFile);
		
		xmlData = StringUtil.replaceAll(xmlData, AppDefs.FLD_REQRESPMSG_BASE_MESSAGE_ID, messageId);
		//xmlData = StringUtil.replaceAll(xmlData, AppDefs.FLD_REQRESPMSG_BASE_MESSAGE_TYPE_ID, Integer.toString(messageTypeId));
		xmlData = StringUtil.replaceAll(xmlData, AppDefs.FLD_REQRESPMSG_BASE_MESSAGE_TYPE, messageType);
		//xmlData = StringUtil.replaceAll(xmlData, AppDefs.FLD_REQRESPMSG_BASE_STATUS_CODE, Integer.toString(AppDefs.RSCODE_NONE));
		//xmlData = StringUtil.replaceAll(xmlData, AppDefs.FLD_REQRESPMSG_BASE_STATUS_MESSAGE, AppDefs.RSMSG_NONE);
		xmlData = StringUtil.replaceAll(xmlData, AppDefs.FLD_REQMSG_MODELUPDATE_SESSION_UUID, sessionUuid);
		
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
	//			<ModelUUID><![CDATA[#TAG_MODEL_UUID#]]></ModelUUID>
	//			<ModelDateTime><![CDATA[#TAG_MODEL_DATE_TIME#]]></ModelDateTime>
	//			<ModelDataBase64><![CDATA[#TAG_MODEL_DATA_BASE64#]]></ModelDataBase64>
	//		</ResponseData>
	//	</Response>
	public static String buildModelUpdateResponseMsg(
		String sessionUuid,
		String modelUuid,
		Date modelDateTime,
		String modelDataBase64) 
	{
		SimpleDateFormat df = new SimpleDateFormat(AppDefs.DEF_DATETIME_FILE_MASC);
		
		String messageId = UuidUtil.generateMessageId();
		int messageTypeId = AppDefs.RESPMSG_MODELUPDATE_VAL;
		String messageType = AppDefs.RESPMSG_MODELUPDATE_STR;
	
		AppContext context = AppMain.getApp().getContext();
		
		String templFile = context.getTemplatesDir() + "/" + AppDefs.TEMPL_RESPONSE_MODELUPDATE_FILE;
		String xmlData = FileUtil.readData(templFile);

		String modelFileName = modelUuid + ".model";
		
		xmlData = StringUtil.replaceAll(xmlData, AppDefs.FLD_REQRESPMSG_BASE_MESSAGE_ID, messageId);
		//xmlData = StringUtil.replaceAll(xmlData, AppDefs.FLD_REQRESPMSG_BASE_MESSAGE_TYPE_ID, Integer.toString(messageTypeId));
		xmlData = StringUtil.replaceAll(xmlData, AppDefs.FLD_REQRESPMSG_BASE_MESSAGE_TYPE, messageType);
		xmlData = StringUtil.replaceAll(xmlData, AppDefs.FLD_REQRESPMSG_BASE_STATUS_CODE, Integer.toString(AppDefs.RSCODE_NONE));
		xmlData = StringUtil.replaceAll(xmlData, AppDefs.FLD_REQRESPMSG_BASE_STATUS_MESSAGE, AppDefs.RSMSG_NONE);
		xmlData = StringUtil.replaceAll(xmlData, AppDefs.FLD_RESPMSG_MODELUPDATE_SESSION_UUID, sessionUuid);
		xmlData = StringUtil.replaceAll(xmlData, AppDefs.FLD_RESPMSG_MODELUPDATE_MODEL_UUID, modelUuid);
		xmlData = StringUtil.replaceAll(xmlData, AppDefs.FLD_RESPMSG_MODELUPDATE_MODEL_DATETIME, df.format(modelDateTime));
		xmlData = StringUtil.replaceAll(xmlData, AppDefs.FLD_RESPMSG_MODELUPDATE_MODEL_DATA_BASE64, modelDataBase64);
		
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
	//			<ModelUUID><![CDATA[#TAG_MODEL_UUID#]]></ModelUUID>
	//			<ModelDateTime><![CDATA[#TAG_MODEL_DATE_TIME#]]></ModelDateTime>
	//			<ModelDataBase64><![CDATA[#TAG_MODEL_DATA_BASE64#]]></ModelDataBase64>
	//		</ResponseData>
	//	</Response>
	public static String buildModelUpdateResponseError(
		int statusCode,
		String statusMessage) 
	{
		String messageId = UuidUtil.generateMessageId();
		int messageTypeId = AppDefs.RESPMSG_MODELUPDATE_VAL;
		String messageType = AppDefs.RESPMSG_MODELUPDATE_STR;
	
		AppContext context = AppMain.getApp().getContext();
		
		String templFile = context.getTemplatesDir() + "/" + AppDefs.TEMPL_RESPONSE_MODELUPDATE_FILE;
		String xmlData = FileUtil.readData(templFile);
		
		xmlData = StringUtil.replaceAll(xmlData, AppDefs.FLD_REQRESPMSG_BASE_MESSAGE_ID, messageId);
		//xmlData = StringUtil.replaceAll(xmlData, AppDefs.FLD_REQRESPMSG_BASE_MESSAGE_TYPE_ID, Integer.toString(messageTypeId));
		xmlData = StringUtil.replaceAll(xmlData, AppDefs.FLD_REQRESPMSG_BASE_MESSAGE_TYPE, messageType);
		xmlData = StringUtil.replaceAll(xmlData, AppDefs.FLD_REQRESPMSG_BASE_STATUS_CODE, Integer.toString(statusCode));
		xmlData = StringUtil.replaceAll(xmlData, AppDefs.FLD_REQRESPMSG_BASE_STATUS_MESSAGE, statusMessage);
		xmlData = StringUtil.replaceAll(xmlData, AppDefs.FLD_RESPMSG_MODELUPDATE_SESSION_UUID, AppDefs.DEF_ID_NONE_VALSTR);
		xmlData = StringUtil.replaceAll(xmlData, AppDefs.FLD_RESPMSG_MODELUPDATE_MODEL_UUID, AppDefs.DEF_ID_NONE_VALSTR);
		xmlData = StringUtil.replaceAll(xmlData, AppDefs.FLD_RESPMSG_MODELUPDATE_MODEL_DATETIME, AppDefs.DEF_ID_NONE_VALSTR);
		xmlData = StringUtil.replaceAll(xmlData, AppDefs.FLD_RESPMSG_MODELUPDATE_MODEL_DATA_BASE64, AppDefs.DEF_ID_NONE_VALSTR);
		
		return xmlData;
	}

}
