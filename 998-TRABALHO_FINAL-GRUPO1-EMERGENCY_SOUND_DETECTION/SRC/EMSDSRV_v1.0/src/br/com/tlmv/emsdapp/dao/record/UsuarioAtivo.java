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
 * UsuarioAtivo.java
 */

package br.com.tlmv.emsdapp.dao.record;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.Date;

import br.com.tlmv.emsdapp.AppDefs;
import br.com.tlmv.emsdapp.utils.DbUtil;
import br.com.tlmv.emsdapp.utils.StringUtil;

public class UsuarioAtivo extends Record implements Serializable
{
//Public Static
	public static String QRY_BASE_SELECT_BYPK = 
		"select " +
			"usuario_ativo_id, " +
			"instalacao_id, " +
			"usuario_id, " +
			"equipamento_id, " +
			"endereco_ethernet, " +
			"data_ultimo_acesso, " +
			"data_inicio, " +
			"usuario_ativo " +
		"from usuario_ativo " +
		"where usuario_ativo_id = ? ";
		
	public static String QRY_BASE_SELECT_ALL = 
		"select " +
			"usuario_ativo_id, " +
			"instalacao_id, " +
			"usuario_id, " +
			"equipamento_id, " +
			"endereco_ethernet, " +
			"data_ultimo_acesso, " +
			"data_inicio, " +
			"usuario_ativo " +
		"from usuario_ativo " +
		"order by data_ultimo_acesso, data_inicio, usuario_ativo_id ";
	
	public static String QRY_BASE_INSERT = 
		"insert into usuario_ativo( " +
			"usuario_ativo_id, " +
			"instalacao_id, " +
			"usuario_id, " +
			"equipamento_id, " +
			"endereco_ethernet, " +
			"data_ultimo_acesso, " +
			"data_inicio, " +
			"usuario_ativo " +
		"values (nextval('seq_usuario_ativo')," + AppDefs.DEF_INSTALACAO_PADRAO_ID_STR + ",?,?,?,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'S')";
	
	public static String QRY_BASE_UPDATE = 
		"update usuario_ativo set " +
			"data_ultimo_acesso = CURRENT_TIMESTAMP, " +
			"usuario_ativo = 'S' " +			
		"where usuario_ativo_id = ? ";
	
//Private
	private static final long serialVersionUID = 1L;
	
	private Integer usuarioAtivoId;
	private Integer instalacaoId;
	private Integer usuarioId;
	private Integer equipamentoId;
	private String enderecoEthernet;
	private Date dataUltimoAcesso;
	private Date dataInicio;
	private String usuarioAtivo;

//Public
	
	public UsuarioAtivo(
		Integer usuarioAtivoId,
		Integer instalacaoId,
		Integer usuarioId,
		Integer equipamentoId,
		String enderecoEthernet,
		Date dataUltimoAcesso,
		Date dataInicio,
		String usuarioAtivo)
	{
		super(
			usuarioAtivoId, 
			String.format(
				"UsuarioId: %s - EthernetAddress: %s - Dt.Inicio: %s - Dt.Ult.Acesso: %s - Ativo: %s", 
				usuarioId,
				enderecoEthernet,
				StringUtil.valToStr(dataInicio),
				StringUtil.valToStr(dataUltimoAcesso),
				usuarioAtivo) );
		
		this.usuarioAtivoId = usuarioAtivoId;
		this.instalacaoId = instalacaoId;
		this.usuarioId = usuarioId;
		this.equipamentoId = equipamentoId;
		this.enderecoEthernet = enderecoEthernet;
		this.dataUltimoAcesso = dataUltimoAcesso;
		this.dataInicio = dataInicio;
		this.usuarioAtivo = usuarioAtivo;
	}
	
	public UsuarioAtivo(ResultSet rs)
		throws Exception
	{
		super(
			rs.getString(1), 
			String.format(
				"UsuarioId: %s - EthernetAddress: %s - Dt.Inicio: %s - Dt.Ult.Acesso: %s - Ativo: %s", 
				rs.getInt(3),
				rs.getString(4),
				StringUtil.valToStr(rs.getTimestamp(6)),
				StringUtil.valToStr(rs.getTimestamp(5)),
				rs.getString(7)) );
		this.loadData(rs);		
	}
	
	/* Methodes */
	
	public void loadData(ResultSet rs)
	{
		DbUtil o = new DbUtil(rs);
		
		try {
			this.usuarioAtivoId = o.getNextInt();
			this.instalacaoId = o.getNextInt();
			this.usuarioId = o.getNextInt();
			this.enderecoEthernet = o.getNextStr();
			this.dataUltimoAcesso = o.getNextDate();
			this.equipamentoId = o.getNextInt();
			this.dataInicio = o.getNextDate();
			this.usuarioAtivo = o.getNextStr();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	/* Getters/Setters */
	
	public String getUsuarioAtivo() {
		return usuarioAtivo;
	}

	public void setUsuarioAtivo(String usuarioAtivo) {
		this.usuarioAtivo = usuarioAtivo;
	}

	public Integer getInstalacaoId() {
		return instalacaoId;
	}

	public void setInstalacaoId(Integer instalacaoId) {
		this.instalacaoId = instalacaoId;
	}

	public Integer getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}

	public Integer getUsuarioAtivoId() {
		return usuarioAtivoId;
	}

	public void setUsuarioAtivoId(Integer usuarioAtivoId) {
		this.usuarioAtivoId = usuarioAtivoId;
	}

	public String getEnderecoEthernet() {
		return enderecoEthernet;
	}

	public void setEnderecoEthernet(String enderecoEthernet) {
		this.enderecoEthernet = enderecoEthernet;
	}

	public Date getDataUltimoAcesso() {
		return dataUltimoAcesso;
	}

	public void setDataUltimoAcesso(Date dataUltimoAcesso) {
		this.dataUltimoAcesso = dataUltimoAcesso;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Integer getEquipamentoId() {
		return equipamentoId;
	}

	public void setEquipamentoId(Integer equipamentoId) {
		this.equipamentoId = equipamentoId;
	}
	
}
