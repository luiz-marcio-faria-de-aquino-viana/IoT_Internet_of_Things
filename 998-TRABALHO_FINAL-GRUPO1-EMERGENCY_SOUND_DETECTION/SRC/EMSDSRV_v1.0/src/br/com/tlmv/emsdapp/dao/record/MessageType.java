/*
 * COPPE / UFRJ - Universidade Federal do Rio de Janeiro
 * PESC - Programa de Engenharia de Sistemas e Computacao
 *
 * Disciplina: CPS730 - Internet das Coisas
 * Professor: Claudio Miceli
 *
 * Trabalho Final - Emergency Sound Detection
 * Data: 20/SET/2022
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
 * MessageType.java
 */

package br.com.tlmv.emsdapp.dao.record;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.Date;

import br.com.tlmv.emsdapp.AppDefs;
import br.com.tlmv.emsdapp.utils.DbUtil;
import br.com.tlmv.emsdapp.utils.StringUtil;

public class MessageType  extends Record implements Serializable 
{	
//Public Static
	public static String QRY_BASE_SELECT_BYPK = 
		"select " +
			"message_type_id, " +
			"message_name, " +
			"message_type, " +
			"message_file, " +
			"message_data_class, " +
			"message_command_class " +
		"from message_type " +
		"where message_type_id = ? ";
	
	public static String QRY_BASE_SELECT_BYMESSAGETYPE = 
		"select " +
			"message_type_id, " +
			"message_name, " +
			"message_type, " +
			"message_file, " +
			"message_data_class, " +
			"message_command_class " +
		"from message_type " +
		"where upper(message_type) = ? ";
		
	public static String QRY_BASE_SELECT_ALL = 
		"select " +
			"message_type_id, " +
			"message_name, " +
			"message_type, " +
			"message_file, " +
			"message_data_class, " +
			"message_command_class " +
		"from message_type " +
		"order by message_type, message_type_id ";
	
	public static String QRY_BASE_INSERT = 
		"insert into message_type( " +
			"message_type_id, " +
			"message_name, " +
			"message_type, " +
			"message_file, " +
			"message_data_class, " +
			"message_command_class) " +
		"values (?,?,?,?,?,?)";
	
	public static String QRY_BASE_UPDATE = 
		"update message_type set " +
			"message_name = ?, " +
			"message_type = ?, " +
			"message_file = ?, " +
			"message_data_class = ?, " +
			"message_command_class = ? " +
		"where message_type_id = ? ";
		
//Private
	private static final long serialVersionUID = 1L;

	private Integer messageTypeId;
	private String messageName;
	private String messageType;
	private String messageFile;
	private String messageDataClass;
	private String messageCommandClass;
	
//Public
	
	public MessageType(
		Integer messageTypeId,
		String messageName,
		String messageType,
		String messageFile,
		String messageDataClass,
		String messageCommandClass)
	{
		super(StringUtil.valToStr(messageTypeId), messageName);
		
		this.messageTypeId = messageTypeId;
		this.messageName = messageName;
		this.messageType = messageType;
		this.messageFile = messageFile;
		this.messageDataClass = messageDataClass;
		this.messageCommandClass = messageCommandClass;
	}
	
	public MessageType(ResultSet rs)
		throws Exception
	{
		super(StringUtil.valToStr(rs.getInt(1)), rs.getString(2));
		
		this.loadData(rs);		
	}
	
	/* Methodes */
	
	public void loadData(ResultSet rs)
	{
		DbUtil o = new DbUtil(rs);
		
		try {
			this.messageTypeId = o.getNextInt();
			this.messageName = o.getNextStr();
			this.messageType = o.getNextStr();
			this.messageFile = o.getNextStr();
			this.messageDataClass = o.getNextStr();
			this.messageCommandClass = o.getNextStr();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	/* Getters/Setters */
	
	public Integer getMessageTypeId() {
		return messageTypeId;
	}

	public void setMessageTypeId(Integer messageTypeId) {
		this.messageTypeId = messageTypeId;
	}

	public String getMessageName() {
		return messageName;
	}

	public void setMessageName(String messageName) {
		this.messageName = messageName;
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
