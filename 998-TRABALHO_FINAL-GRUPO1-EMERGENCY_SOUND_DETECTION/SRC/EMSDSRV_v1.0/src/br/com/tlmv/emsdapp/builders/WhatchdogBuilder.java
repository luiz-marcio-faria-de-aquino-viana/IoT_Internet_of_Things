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
 * WhatchdogBuilder.java
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

public class WhatchdogBuilder
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
	public static String buildWhatchdogRequestMsg(
		String sessionUuid) 
	{
		String messageId = UuidUtil.generateMessageId();
		int messageTypeId = AppDefs.REQMSG_WHATCHDOG_VAL;
		String messageType = AppDefs.REQMSG_WHATCHDOG_STR;
	
		AppContext context = AppMain.getApp().getContext();
		
		String templFile = context.getTemplatesDir() + "/" + AppDefs.TEMPL_REQUEST_WHATCHDOG_FILE;
		String xmlData = FileUtil.readData(templFile);
		
		xmlData = StringUtil.replaceAll(xmlData, AppDefs.FLD_REQRESPMSG_BASE_MESSAGE_ID, messageId);
		//xmlData = StringUtil.replaceAll(xmlData, AppDefs.FLD_REQRESPMSG_BASE_MESSAGE_TYPE_ID, Integer.toString(messageTypeId));
		xmlData = StringUtil.replaceAll(xmlData, AppDefs.FLD_REQRESPMSG_BASE_MESSAGE_TYPE, messageType);
		//xmlData = StringUtil.replaceAll(xmlData, AppDefs.FLD_REQRESPMSG_BASE_STATUS_CODE, Integer.toString(AppDefs.RSCODE_NONE));
		//xmlData = StringUtil.replaceAll(xmlData, AppDefs.FLD_REQRESPMSG_BASE_STATUS_MESSAGE, AppDefs.RSMSG_NONE);
		xmlData = StringUtil.replaceAll(xmlData, AppDefs.FLD_REQMSG_WHATCHDOG_SESSION_UUID, sessionUuid);
		
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
	//			<SessionDateTime><![CDATA[#TAG_SESSION_DATETIME#]]></SessionDateTime>
	//			<SessionTimeMili><![CDATA[#TAG_SESSION_TIME_MILI#]]></SessionTimeMili>
	//		</ResponseData>
	//	</Response>
	public static String buildWhatchdogResponseMsg(
		String sessionUuid,
		Date sessionDateTime,
		Long sessionTimeMili)
	{
		SimpleDateFormat df = new SimpleDateFormat(AppDefs.DEF_DATETIME_FILE_MASC);
		
		String messageId = UuidUtil.generateMessageId();
		int messageTypeId = AppDefs.RESPMSG_WHATCHDOG_VAL;
		String messageType = AppDefs.RESPMSG_WHATCHDOG_STR;
	
		AppContext context = AppMain.getApp().getContext();
		
		String templFile = context.getTemplatesDir() + "/" + AppDefs.TEMPL_RESPONSE_WHATCHDOG_FILE;
		String xmlData = FileUtil.readData(templFile);
		
		xmlData = StringUtil.replaceAll(xmlData, AppDefs.FLD_REQRESPMSG_BASE_MESSAGE_ID, messageId);
		//xmlData = StringUtil.replaceAll(xmlData, AppDefs.FLD_REQRESPMSG_BASE_MESSAGE_TYPE_ID, Integer.toString(messageTypeId));
		xmlData = StringUtil.replaceAll(xmlData, AppDefs.FLD_REQRESPMSG_BASE_MESSAGE_TYPE, messageType);
		xmlData = StringUtil.replaceAll(xmlData, AppDefs.FLD_REQRESPMSG_BASE_STATUS_CODE, Integer.toString(AppDefs.RSCODE_NONE));
		xmlData = StringUtil.replaceAll(xmlData, AppDefs.FLD_REQRESPMSG_BASE_STATUS_MESSAGE, AppDefs.RSMSG_NONE);
		xmlData = StringUtil.replaceAll(xmlData, AppDefs.FLD_RESPMSG_WHATCHDOG_SESSION_UUID, sessionUuid);
		xmlData = StringUtil.replaceAll(xmlData, AppDefs.FLD_RESPMSG_WHATCHDOG_SESSION_DATETIME, df.format(sessionDateTime));
		xmlData = StringUtil.replaceAll(xmlData, AppDefs.FLD_RESPMSG_WHATCHDOG_SESSION_TIME_MILI, Long.toString(sessionTimeMili));
		
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
	//			<SessionDateTime><![CDATA[#TAG_SESSION_DATETIME#]]></SessionDateTime>
	//			<SessionTimeMili><![CDATA[#TAG_SESSION_TIME_MILI#]]></SessionTimeMili>
	//		</ResponseData>
	//	</Response>
	public static String buildWhatchdogResponseError(
		int statusCode,
		String statusMessage) 
	{
		String messageId = UuidUtil.generateMessageId();
		int messageTypeId = AppDefs.RESPMSG_WHATCHDOG_VAL;
		String messageType = AppDefs.RESPMSG_WHATCHDOG_STR;
	
		AppContext context = AppMain.getApp().getContext();
		
		String templFile = context.getTemplatesDir() + "/" + AppDefs.TEMPL_RESPONSE_WHATCHDOG_FILE;
		String xmlData = FileUtil.readData(templFile);
		
		xmlData = StringUtil.replaceAll(xmlData, AppDefs.FLD_REQRESPMSG_BASE_MESSAGE_ID, messageId);
		//xmlData = StringUtil.replaceAll(xmlData, AppDefs.FLD_REQRESPMSG_BASE_MESSAGE_TYPE_ID, Integer.toString(messageTypeId));
		xmlData = StringUtil.replaceAll(xmlData, AppDefs.FLD_REQRESPMSG_BASE_MESSAGE_TYPE, messageType);
		xmlData = StringUtil.replaceAll(xmlData, AppDefs.FLD_REQRESPMSG_BASE_STATUS_CODE, Integer.toString(statusCode));
		xmlData = StringUtil.replaceAll(xmlData, AppDefs.FLD_REQRESPMSG_BASE_STATUS_MESSAGE, statusMessage);
		xmlData = StringUtil.replaceAll(xmlData, AppDefs.FLD_RESPMSG_WHATCHDOG_SESSION_UUID, AppDefs.DEF_ID_NONE_VALSTR);
		xmlData = StringUtil.replaceAll(xmlData, AppDefs.FLD_RESPMSG_WHATCHDOG_SESSION_DATETIME, AppDefs.DEF_ID_NONE_VALSTR);
		xmlData = StringUtil.replaceAll(xmlData, AppDefs.FLD_RESPMSG_WHATCHDOG_SESSION_TIME_MILI, AppDefs.DEF_ID_NONE_VALSTR);
		
		return xmlData;
	}

}
