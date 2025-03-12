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
 * Equipamento.java
 */

package br.com.tlmv.emsdapp.dao.record;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.Date;

import br.com.tlmv.emsdapp.AppDefs;
import br.com.tlmv.emsdapp.utils.DbUtil;
import br.com.tlmv.emsdapp.utils.StringUtil;

public class Equipamento extends Record implements Serializable 
{	
//Public Static
	public static String QRY_BASE_SELECT_BYPK = 
		"select " +
			"equipamento_id, " +
			"instalacao_id, " +
			"endereco_ethernet, " +
			"data_criacao, " +
			"data_ultimo_acesso, " +
			"numero_acessos, " +
			"ultimo_modelo_uuid, " +
			"data_ultimo_modelo, " +				
			"data_desativacao, " +
			"autor_desativacao_id " +
		"from equipamento " +
		"where equipamento_id = ? ";
	
	public static String QRY_BASE_SELECT_ALL = 
		"select " +
			"equipamento_id, " +
			"instalacao_id, " +
			"endereco_ethernet, " +
			"data_criacao, " +
			"data_ultimo_acesso, " +
			"numero_acessos, " +
			"ultimo_modelo_uuid, " +
			"data_ultimo_modelo, " +				
			"data_desativacao, " +
			"autor_desativacao_id " +
		"from equipamento " +
		"where data_desativacao is null " +
		"order by endereco_ethernet, equipamento_id ";
	
	public static String QRY_BASE_INSERT = 
		"insert into equipamento( " +
			"equipamento_id, " +
			"instalacao_id, " +
			"endereco_ethernet, " +
			"data_criacao, " +
			"data_ultimo_acesso, " +
			"numero_acessos, " +
			"ultimo_modelo_uuid, " +
			"data_ultimo_modelo, " +				
			"data_desativacao, " +
			"autor_desativacao_id " +
		"values (nextval('seq_equipamento')," + AppDefs.DEF_INSTALACAO_PADRAO_ID_STR + ",?,?,?,?,?,?,NULL,NULL)";
	
	public static String QRY_BASE_UPDATE = 
		"update equipamento set " +
			"endereco_ethernet = ?, " +
			"data_ultimo_acesso = ?, " +
			"numero_acessos = ?, " +
			"ultimo_modelo_uuid = ?, " +
			"data_ultimo_modelo = ?, " +				
		"where equipamento_id = ? ";
		
	public static String QRY_BASE_DELETE = 
		"update equipamento set " +
			"data_desativacao = CURRENT_TIMESTAMP, " +
			"autor_desativacao_id = " + AppDefs.DEF_USUARIO_PADRAO_ID_STR + " " +
		"where equipamento_id = ? ";
			
	public static String SEQ_NEXTVAL = 
		"select nextval('seq_equipamento'); ";
	
	public static String SEQ_CURRVAL = 
		"select currval('seq_equipamento'); ";			
			
//Private
	private static final long serialVersionUID = 1L;

	private Integer equipamentoId;
	private Integer instalacaoId;
	private String enderecoEthernet;
	private Date dataCriacao;
	private Date dataUltimoAcesso;
	private Integer numeroAcessos;
	private String ultimoModeloUuid;
	private Date dataUltimoModelo;
	private Date dataDesativacao;
	private Integer autorDesativacaoId;
	
//Public
	
	public Equipamento(
		Integer equipamentoId,
		Integer instalacaoId,
		String enderecoEthernet,
		Date dataCriacao,
		Date dataUltimoAcesso,
		Integer numeroAcessos,
		String ultimoModeloUuid,
		Date dataUltimoModelo,
		Date dataDesativacao,
		Integer autorDesativacaoId)
	{
		super(StringUtil.valToStr(equipamentoId), enderecoEthernet);
		
		this.equipamentoId = equipamentoId;
		this.instalacaoId = instalacaoId;
		this.enderecoEthernet = enderecoEthernet;
		this.dataCriacao = dataCriacao;
		this.dataUltimoAcesso = dataUltimoAcesso;
		this.numeroAcessos = numeroAcessos;
		this.ultimoModeloUuid = ultimoModeloUuid;
		this.dataUltimoModelo = dataUltimoModelo;
		this.dataDesativacao = dataDesativacao;
		this.autorDesativacaoId = autorDesativacaoId;
	}
	
	public Equipamento(ResultSet rs)
		throws Exception
	{
		super(StringUtil.valToStr(rs.getInt(1)), rs.getString(3));
		
		this.loadData(rs);		
	}
	
	/* Methodes */
	
	public void loadData(ResultSet rs)
	{
		DbUtil o = new DbUtil(rs);
		
		try {
			this.equipamentoId = o.getNextInt();
			this.instalacaoId = o.getNextInt();
			this.enderecoEthernet = o.getNextStr();
			this.dataCriacao = o.getNextDate();
			this.dataUltimoAcesso = o.getNextDate();
			this.numeroAcessos = o.getNextInt();
			this.ultimoModeloUuid = o.getNextStr();
			this.dataUltimoModelo = o.getNextDate();
			this.dataDesativacao = o.getNextDate();
			this.autorDesativacaoId = o.getNextInt();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	/* Getters/Setters */
	
	public Integer getEquipamentoId() {
		return equipamentoId;
	}

	public void setEquipamentoId(Integer equipamentoId) {
		this.equipamentoId = equipamentoId;
	}

	public Integer getInstalacaoId() {
		return instalacaoId;
	}

	public void setInstalacaoId(Integer instalacaoId) {
		this.instalacaoId = instalacaoId;
	}

	public String getEnderecoEthernet() {
		return enderecoEthernet;
	}

	public void setEnderecoEthernet(String enderecoEthernet) {
		this.enderecoEthernet = enderecoEthernet;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getDataUltimoAcesso() {
		return dataUltimoAcesso;
	}

	public void setDataUltimoAcesso(Date dataUltimoAcesso) {
		this.dataUltimoAcesso = dataUltimoAcesso;
	}

	public Integer getNumeroAcessos() {
		return numeroAcessos;
	}

	public void setNumeroAcessos(Integer numeroAcessos) {
		this.numeroAcessos = numeroAcessos;
	}

	public String getUltimoModeloUuid() {
		return ultimoModeloUuid;
	}

	public void setUltimoModeloUuid(String ultimoModeloUuid) {
		this.ultimoModeloUuid = ultimoModeloUuid;
	}

	public Date getDataUltimoModelo() {
		return dataUltimoModelo;
	}

	public void setDataUltimoModelo(Date dataUltimoModelo) {
		this.dataUltimoModelo = dataUltimoModelo;
	}

	public Date getDataDesativacao() {
		return dataDesativacao;
	}

	public void setDataDesativacao(Date dataDesativacao) {
		this.dataDesativacao = dataDesativacao;
	}

	public Integer getAutorDesativacaoId() {
		return autorDesativacaoId;
	}

	public void setAutorDesativacaoId(Integer autorDesativacaoId) {
		this.autorDesativacaoId = autorDesativacaoId;
	}

}
