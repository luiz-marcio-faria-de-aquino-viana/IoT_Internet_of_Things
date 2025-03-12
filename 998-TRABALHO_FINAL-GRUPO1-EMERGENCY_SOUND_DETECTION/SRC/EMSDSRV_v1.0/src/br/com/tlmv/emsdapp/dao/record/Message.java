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

public class Message  extends Record implements Serializable 
{	
//Public Static
	public static String QRY_BASE_SELECT_BYPK = 
		"select " +
			"message_id, " +
			"req_resp_message, " + 
			"message_uuid, " +
			"message_type_id, " +
			"message_type, " +
			"status_code, " +
			"status_message, " +
			"username, " +
			"password, " +
			"session_uuid, " +
			"session_date_time, " + 
			"session_time_mili, " +
			"equipamento_id, " +
			"model_uuid, " +
			"model_date_time, " + 
			"model_file, " +
			"data_file_uuid, " +
			"data_file_name, " +
			"data_file_size, " +
			"data_file, " +
			"latitude, " +
			"longitude " +
		"from message " +
		"where message_id = ? ";
	
	public static String QRY_BASE_SELECT_ALL = 
		"select " +
			"message_id, " +
			"req_resp_message, " + 
			"message_uuid, " +
			"message_type_id, " +
			"message_type, " +
			"status_code, " +
			"status_message, " +
			"username, " +
			"password, " +
			"session_uuid, " +
			"session_date_time, " + 
			"session_time_mili, " +
			"equipamento_id, " +
			"model_uuid, " +
			"model_date_time, " + 
			"model_file, " +
			"data_file_uuid, " +
			"data_file_name, " +
			"data_file_size, " +
			"data_file, " +
			"latitude, " +
			"longitude " +
		"from message " +
		"order by message_type, message_type_id ";
	
	public static String QRY_BASE_INSERT = 
		"insert into message( " +
			"message_id, " +
			"req_resp_message, " + 
			"message_uuid, " +
			"message_type_id, " +
			"message_type, " +
			"status_code, " +
			"status_message, " +
			"username, " +
			"password, " +
			"session_uuid, " +
			"session_date_time, " + 
			"session_time_mili, " +
			"equipamento_id, " +
			"model_uuid, " +
			"model_date_time, " + 
			"model_file, " +
			"data_file_uuid, " +
			"data_file_name, " +
			"data_file_size, " +
			"data_file, " +
			"latitude, " +
			"longitude) " +
		"values (nextval('seq_message'),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	public static String QRY_BASE_UPDATE = 
		"update message set " +
			"req_resp_message, " + 
			"message_uuid = ?, " +
			"message_type_id = ?, " +
			"message_type = ?, " +
			"status_code = ?, " +
			"status_message = ?, " +
			"username = ?, " +
			"password = ?, " +
			"session_uuid = ?, " +
			"session_date_time = ?, " + 
			"session_time_mili = ?, " +
			"equipamento_id = ?, " +
			"model_uuid = ?, " +
			"model_date_time = ?, " + 
			"model_file = ?, " +
			"data_file_uuid = ?, " +
			"data_file_name = ?, " +
			"data_file_size = ?, " +
			"data_file = ?, " +
			"latitude = ?, " +
			"longitude = ? " +
		"where message_id = ? ";
		
//Private
	private static final long serialVersionUID = 1L;

	private Integer messageId;
	private String reqRespMessage; 
	private String messageUuid;
	private Integer messageTypeId;
	private String messageType;
	private Integer statusCode;
	private String statusMessage;
	private String username;
	private String password;
	private String sessionUuid;
	private Date sessionDateTime; 
	private Long sessionTimeMili;
	private Integer equipamentoId;
	private String modelUuid;
	private Date modelDateTime; 
	private String modelFile;
	private String dataFileUuid;
	private String dataFileName;
	private Long dataFileSize;
	private String dataFile;
	private Double latitude;
	private Double longitude;

//Public
	
	public Message(
		Integer messageId,
		String reqRespMessage, 
		String messageUuid,
		Integer messageTypeId,
		String messageType,
		Integer statusCode,
		String statusMessage,
		String username,
		String password,
		String sessionUuid,
		Date sessionDateTime, 
		Long sessionTimeMili,
		Integer equipamentoId,
		String modelUuid,
		Date modelDateTime, 
		String modelFile,
		String dataFileUuid,
		String dataFileName,
		Long dataFileSize,
		String dataFile,
		Double latitude,
		Double longitude)
	{
		super(StringUtil.valToStr(messageId), messageType);
		
		this.messageId = messageId;
		this.reqRespMessage = reqRespMessage; 
		this.messageUuid = messageUuid;
		this.messageTypeId = messageTypeId;
		this.messageType = messageType;
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
		this.username = username;
		this.password = password;
		this.sessionUuid = sessionUuid;
		this.sessionDateTime = sessionDateTime; 
		this.sessionTimeMili = sessionTimeMili;
		this.equipamentoId = equipamentoId;
		this.modelUuid = modelUuid;
		this.modelDateTime = modelDateTime; 
		this.modelFile = modelFile;
		this.dataFileUuid = dataFileUuid;
		this.dataFileName = dataFileName;
		this.dataFileSize = dataFileSize;
		this.dataFile = dataFile;
		this.latitude = latitude;
		this.longitude = longitude; 
	}
	
	public Message(ResultSet rs)
		throws Exception
	{
		super(StringUtil.valToStr(rs.getInt(1)), rs.getString(5));
		
		this.loadData(rs);		
	}
	
	/* Methodes */
	
	public void loadData(ResultSet rs)
	{
		DbUtil o = new DbUtil(rs);
		
		try {
			this.messageId = o.getNextInt();
			this.reqRespMessage = o.getNextStr(); 
			this.messageUuid = o.getNextStr();
			this.messageTypeId = o.getNextInt();
			this.messageType = o.getNextStr();
			this.statusCode = o.getNextInt();
			this.statusMessage = o.getNextStr();
			this.username = o.getNextStr();
			this.password = o.getNextStr();
			this.sessionUuid = o.getNextStr();
			this.sessionDateTime = o.getNextDate(); 
			this.sessionTimeMili = o.getNextLng();
			this.equipamentoId = o.getNextInt();
			this.modelUuid = o.getNextStr();
			this.modelDateTime = o.getNextDate(); 
			this.modelFile = o.getNextStr();
			this.dataFileUuid = o.getNextStr();
			this.dataFileName = o.getNextStr();
			this.dataFileSize = o.getNextLng();
			this.dataFile = o.getNextStr();
			this.latitude = o.getNextDbl();
			this.longitude = o.getNextDbl(); 
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

	public Integer getMessageId() {
		return messageId;
	}

	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}

	public String getReqRespMessage() {
		return reqRespMessage;
	}

	public void setReqRespMessage(String reqRespMessage) {
		this.reqRespMessage = reqRespMessage;
	}

	public String getMessageUuid() {
		return messageUuid;
	}

	public void setMessageUuid(String messageUuid) {
		this.messageUuid = messageUuid;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSessionUuid() {
		return sessionUuid;
	}

	public void setSessionUuid(String sessionUuid) {
		this.sessionUuid = sessionUuid;
	}

	public Date getSessionDateTime() {
		return sessionDateTime;
	}

	public void setSessionDateTime(Date sessionDateTime) {
		this.sessionDateTime = sessionDateTime;
	}

	public Long getSessionTimeMili() {
		return sessionTimeMili;
	}

	public void setSessionTimeMili(Long sessionTimeMili) {
		this.sessionTimeMili = sessionTimeMili;
	}

	public Integer getEquipamentoId() {
		return equipamentoId;
	}

	public void setEquipamentoId(Integer equipamentoId) {
		this.equipamentoId = equipamentoId;
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

	public void setModelDateTime(Date modelDateTime) {
		this.modelDateTime = modelDateTime;
	}

	public String getModelFile() {
		return modelFile;
	}

	public void setModelFile(String modelFile) {
		this.modelFile = modelFile;
	}

	public String getDataFileUuid() {
		return dataFileUuid;
	}

	public void setDataFileUuid(String dataFileUuid) {
		this.dataFileUuid = dataFileUuid;
	}

	public String getDataFileName() {
		return dataFileName;
	}

	public void setDataFileName(String dataFileName) {
		this.dataFileName = dataFileName;
	}

	public Long getDataFileSize() {
		return dataFileSize;
	}

	public void setDataFileSize(Long dataFileSize) {
		this.dataFileSize = dataFileSize;
	}

	public String getDataFile() {
		return dataFile;
	}

	public void setDataFile(String dataFile) {
		this.dataFile = dataFile;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

}
