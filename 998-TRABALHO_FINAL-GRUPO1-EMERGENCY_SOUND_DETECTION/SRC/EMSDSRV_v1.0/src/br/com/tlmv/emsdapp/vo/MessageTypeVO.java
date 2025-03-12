/*
 * COPPE / UFRJ - Universidade Federal do Rio de Janeiro
 * PESC - Programa de Engenharia de Sistemas e Computacao
 *
 * Disciplina: CPS730 - Internet das Coisas
 * Professor: Claudio Miceli
 *
 * Trabalho Final - Emergency Sound Detection
 * Data: 26/AGO/2022
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
 * MessageTypeVO.java
 */

package br.com.tlmv.emsdapp.vo;

import java.sql.ResultSet;
import java.util.Date;

//CREATE TABLE message_type
//(
//    message_type_id numeric(10,0) NOT NULL,
//    message_name character varying(30) NOT NULL,
//    message_type character varying(30) NOT NULL,
//    message_file character varying(255) NOT NULL,
//    message_data_class character varying(255) NOT NULL,
//    message_command_class character varying(255) NOT NULL,
//    PRIMARY KEY (message_type_id)
//)
public class MessageTypeVO extends RecordVO
{
//Private
	private Integer messageTypeId;
	private String messageName;
	private String messageType;
	private String messageFile;
	private String messageDataClass;
	private String messageCommandClass;
	
//Public 	
	public static final String SQLTBL_MESSAGE_TYPE 							= "message_type";
	//
	public static final int SQLFLD_MESSAGE_TYPE_MESSAGE_TYPE_ID 			= 1;
	public static final int SQLFLD_MESSAGE_TYPE_MESSAGE_NAME 				= 2;
	public static final int SQLFLD_MESSAGE_TYPE_MESSAGE_TYPE 				= 3;
	public static final int SQLFLD_MESSAGE_TYPE_MESSAGE_FILE				= 4;
	public static final int SQLFLD_MESSAGE_TYPE_MESSAGE_DATA_CLASS 			= 5;
	public static final int SQLFLD_MESSAGE_TYPE_MESSAGE_COMAND_CLASS 		= 6;

	/* Methodes */
	
	public MessageTypeVO(
		Integer messageTypeId,
		String messageName,
		String messageType,
		String messageFile,
		String messageDataClass,
		String messageCommandClass)
	{
		this.messageTypeId = messageTypeId;
		this.messageName = messageName;
		this.messageType = messageType;
		this.messageFile = messageFile;
		this.messageDataClass = messageDataClass;
		this.messageCommandClass = messageCommandClass;

		this.init(
			Integer.toString(this.messageTypeId),
			this.messageName);
	}
	
	public MessageTypeVO(ResultSet rs)
		throws Exception
	{
		this.messageTypeId = rs.getInt(MessageTypeVO.SQLFLD_MESSAGE_TYPE_MESSAGE_TYPE_ID);
		this.messageName = rs.getString(MessageTypeVO.SQLFLD_MESSAGE_TYPE_MESSAGE_NAME);
		this.messageType = rs.getString(MessageTypeVO.SQLFLD_MESSAGE_TYPE_MESSAGE_TYPE);
		this.messageFile = rs.getString(MessageTypeVO.SQLFLD_MESSAGE_TYPE_MESSAGE_FILE);
		this.messageDataClass = rs.getString(MessageTypeVO.SQLFLD_MESSAGE_TYPE_MESSAGE_DATA_CLASS);
		this.messageCommandClass = rs.getString(MessageTypeVO.SQLFLD_MESSAGE_TYPE_MESSAGE_COMAND_CLASS);

		this.init(
			Integer.toString(this.messageTypeId),
			this.messageName);
	}
	
	/* Getters/Setters */
	
	public Integer getMessageTypeId() {
		return messageTypeId;
	}

	public void setMessageTypeId(Integer messageTypeId) {
		this.messageTypeId = messageTypeId;

		this.init(
			Integer.toString(this.messageTypeId),
			this.messageName);
	}

	public String getMessageName() {
		return messageName;
	}

	public void setMessageName(String messageName) {
		this.messageName = messageName;

		this.init(
			Integer.toString(this.messageTypeId),
			this.messageName);
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getMessageFile() {
		return messageFile;
	}

	public void setMessageFile(String messageFile) {
		this.messageFile = messageFile;
	}

	public String getMessageDataClass() {
		return messageDataClass;
	}

	public void setMessageDataClass(String messageDataClass) {
		this.messageDataClass = messageDataClass;
	}

	public String getMessageCommandClass() {
		return messageCommandClass;
	}

	public void setMessageCommandClass(String messageCommandClass) {
		this.messageCommandClass = messageCommandClass;
	}
	
}
