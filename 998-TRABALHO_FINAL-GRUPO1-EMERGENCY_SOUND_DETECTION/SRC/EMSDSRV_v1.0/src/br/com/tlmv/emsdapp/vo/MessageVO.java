/*
 * COPPE / UFRJ - Universidade Federal do Rio de Janeiro
 * PESC - Programa de Engenharia de Sistemas e Computacao
 *
 * Disciplina: CPS730 - Internet das Coisas
 * Professor: Claudio Miceli
 *
 * Trabalho Final - Emergency Sound Detection
 * Data: 27/AGO/2022
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
 * MessageVO.java
 */

package br.com.tlmv.emsdapp.vo;

import java.sql.ResultSet;
import java.util.Date;

//CREATE TABLE message
//(
//    message_id numeric(10,0) NOT NULL,
//    sendrecv_message character varying(1) NOT NULL,
//    sendrecv_datetime timestamp without time zone NOT NULL,
//    message_type_id numeric(10,0) NOT NULL,
//    message_metadata_file character varying(255) NOT NULL,
//    message_sound_file character varying(255) NOT NULL,
//    message_model_file character varying(255) NOT NULL,
//    message_uuid character varying(35) NOT NULL,
//    message_orig_uuid character varying(35),
//    PRIMARY KEY (message_uuid)
//)
public class MessageVO extends RecordVO
{
//Private
	private Integer messageId;
	private String sendRecvMessage;
	private Date sendRecvDateTime;
	private Integer messageTypeId;
	private String messageMetadataFile;
	private String messageSoundFile;
	private String messageModelFile;
	private String messageUuid;
	private String messageOrigUuid;

//Public 	
	public static final String SQLTBL_MESSAGE 								= "message";
	//
	public static final int SQLFLD_MESSAGE_MESSAGE_ID 						= 1;
	public static final int SQLFLD_MESSAGE_SEND_RECV_MESSAGE 				= 2;
	public static final int SQLFLD_MESSAGE_SEND_RECV_DATETIME 				= 3;
	public static final int SQLFLD_MESSAGE_MESSAGE_TYPE_ID					= 4;
	public static final int SQLFLD_MESSAGE_MESSAGE_METADATA_FILE 			= 5;
	public static final int SQLFLD_MESSAGE_MESSAGE_SOUND_FILE 				= 6;
	public static final int SQLFLD_MESSAGE_MESSAGE_MODEL_FILE 				= 7;
	public static final int SQLFLD_MESSAGE_MESSAGE_UUID		 				= 8;
	public static final int SQLFLD_MESSAGE_MESSAGE_ORIG_UUID 				= 9;
	
	/* Methodes */
	
	public MessageVO(
		Integer messageId,
		String sendRecvMessage,
		Date sendRecvDateTime,
		Integer messageTypeId,
		String messageMetadataFile,
		String messageSoundFile,
		String messageModelFile,
		String messageUuid,
		String messageOrigUuid)
	{
		this.messageId = messageId;
		this.sendRecvMessage = sendRecvMessage;
		this.sendRecvDateTime = sendRecvDateTime;
		this.messageTypeId = messageTypeId;
		this.messageMetadataFile = messageMetadataFile;
		this.messageSoundFile = messageSoundFile;
		this.messageModelFile = messageModelFile;
		this.messageUuid = messageUuid;
		this.messageOrigUuid = messageOrigUuid;

		this.init(
			Integer.toString(this.messageId),
			Integer.toString(this.messageTypeId) );
	}
	
	public MessageVO(ResultSet rs)
		throws Exception
	{
		java.sql.Date tmpSendRecvDateTime = rs.getDate(MessageVO.SQLFLD_MESSAGE_SEND_RECV_DATETIME);
		
		this.messageId = rs.getInt(MessageVO.SQLFLD_MESSAGE_MESSAGE_ID);
		this.sendRecvMessage = rs.getString(MessageVO.SQLFLD_MESSAGE_SEND_RECV_MESSAGE);
		this.sendRecvDateTime = new Date(tmpSendRecvDateTime.getTime());
		this.messageTypeId = rs.getInt(MessageVO.SQLFLD_MESSAGE_MESSAGE_ID);
		this.messageMetadataFile = rs.getString(MessageVO.SQLFLD_MESSAGE_MESSAGE_METADATA_FILE);
		this.messageSoundFile = rs.getString(MessageVO.SQLFLD_MESSAGE_MESSAGE_SOUND_FILE);
		this.messageModelFile = rs.getString(MessageVO.SQLFLD_MESSAGE_MESSAGE_MODEL_FILE);
		this.messageUuid = rs.getString(MessageVO.SQLFLD_MESSAGE_MESSAGE_UUID);
		this.messageOrigUuid = rs.getString(MessageVO.SQLFLD_MESSAGE_MESSAGE_ORIG_UUID);

		this.init(
			Integer.toString(this.messageId),
			Integer.toString(this.messageTypeId) );
	}
	
	/* Getters/Setters */
	
	public Integer getMessageTypeId() {
		return messageTypeId;
	}

	public void setMessageTypeId(Integer messageTypeId) {
		this.messageTypeId = messageTypeId;

		this.init(
			Integer.toString(this.messageId),
			Integer.toString(this.messageTypeId) );
	}

	public Integer getMessageId() {
		return messageId;
	}

	public void setMessageId(Integer messageId) {
		this.messageId = messageId;

		this.init(
			Integer.toString(this.messageId),
			Integer.toString(this.messageTypeId) );
	}

	public String getSendRecvMessage() {
		return sendRecvMessage;
	}

	public void setSendRecvMessage(String sendRecvMessage) {
		this.sendRecvMessage = sendRecvMessage;
	}

	public Date getSendRecvDateTime() {
		return sendRecvDateTime;
	}

	public void setSendRecvDateTime(Date sendRecvDateTime) {
		this.sendRecvDateTime = sendRecvDateTime;
	}

	public String getMessageMetadataFile() {
		return messageMetadataFile;
	}

	public void setMessageMetadataFile(String messageMetadataFile) {
		this.messageMetadataFile = messageMetadataFile;
	}

	public String getMessageSoundFile() {
		return messageSoundFile;
	}

	public void setMessageSoundFile(String messageSoundFile) {
		this.messageSoundFile = messageSoundFile;
	}

	public String getMessageModelFile() {
		return messageModelFile;
	}

	public void setMessageModelFile(String messageModelFile) {
		this.messageModelFile = messageModelFile;
	}

	public String getMessageUuid() {
		return messageUuid;
	}

	public void setMessageUuid(String messageUuid) {
		this.messageUuid = messageUuid;
	}

	public String getMessageOrigUuid() {
		return messageOrigUuid;
	}

	public void setMessageOrigUuid(String messageOrigUuid) {
		this.messageOrigUuid = messageOrigUuid;
	}
	
}
