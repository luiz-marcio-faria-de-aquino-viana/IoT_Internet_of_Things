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
 * DeviceVO.java
 */

package br.com.tlmv.emsdapp.vo;

import java.sql.ResultSet;
import java.util.Date;

//CREATE TABLE device
//(
//    device_id numeric(10,0) NOT NULL,
//    ethernet_address character varying(12) NOT NULL,
//    creation_date timestamp without time zone NOT NULL,
//    last_access timestamp without time zone NOT NULL,
//    num_access numeric(10,0) NOT NULL DEFAULT 0,
//    PRIMARY KEY (device_id)
//)
public class DeviceVO extends RecordVO 
{
//Private
	private Integer deviceId;
	private String ethernetAddress;
	private Date creationDate;
	private Date lastAccess;
	private Long numAccess;
	
//Public 	
	public static final String SQLTBL_DEVICE 								= "device";
	//
	public static final int SQLFLD_DEVICE_DEVICE_ID 						= 1;
	public static final int SQLFLD_DEVICE_ETHERNET_ADDRESS 					= 2;
	public static final int SQLFLD_DEVICE_CREATION_DATE 					= 3;
	public static final int SQLFLD_DEVICE_LAST_ACCESS 						= 4;
	public static final int SQLFLD_DEVICE_NUM_ACCESS 						= 5;

	/* Methodes */
	
	public DeviceVO(
		Integer deviceId,
		String ethernetAddress,
		Date creationDate,
		Date lastAccess,
		Long numAccess)
	{
		this.deviceId = deviceId;
		this.ethernetAddress = ethernetAddress;
		this.creationDate = creationDate;
		this.lastAccess = lastAccess;
		this.numAccess = numAccess;

		this.init(
			Integer.toString(this.deviceId),
			this.ethernetAddress);
	}
	
	public DeviceVO(ResultSet rs)
		throws Exception
	{
		this.deviceId = rs.getInt(DeviceVO.SQLFLD_DEVICE_DEVICE_ID);
		this.ethernetAddress = rs.getString(DeviceVO.SQLFLD_DEVICE_ETHERNET_ADDRESS);
		this.creationDate = rs.getDate(DeviceVO.SQLFLD_DEVICE_CREATION_DATE);
		this.lastAccess = rs.getDate(DeviceVO.SQLFLD_DEVICE_LAST_ACCESS);
		this.numAccess = rs.getLong(DeviceVO.SQLFLD_DEVICE_NUM_ACCESS);

		this.init(
			Integer.toString(this.deviceId),
			this.ethernetAddress);
	}
	
	/* Getters/Setters */
	
	public Integer getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;

		this.init(
			Integer.toString(this.deviceId),
			this.ethernetAddress);
	}

	public String getEthernetAddress() {
		return ethernetAddress;
	}

	public void setEthernetAddress(String ethernetAddress) {
		this.ethernetAddress = ethernetAddress;

		this.init(
			Integer.toString(this.deviceId),
			this.ethernetAddress);
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getLastAccess() {
		return lastAccess;
	}

	public void setLastAccess(Date lastAccess) {
		this.lastAccess = lastAccess;
	}

	public Long getNumAccess() {
		return numAccess;
	}

	public void setNumAccess(Long numAccess) {
		this.numAccess = numAccess;
	}
	
}
