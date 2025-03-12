/*
 * COPPE / UFRJ - Universidade Federal do Rio de Janeiro
 * PESC - Programa de Engenharia de Sistemas e Computacao
 *
 * Disciplina: CPS730 - Internet das Coisas
 * Professor: Claudio Miceli
 *
 * Trabalho Final - Emergency Sound Detection
 * Data: 15/AGO/2022
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
 * Instalacao.java
 */

package br.com.tlmv.emsdapp.dao.record;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.Date;

import br.com.tlmv.emsdapp.utils.DbUtil;
import br.com.tlmv.emsdapp.utils.StringUtil;

public class Instalacao extends Record implements Serializable
{
//Public Static
	public static String QRY_BASE_SELECT_BYPK = 
		"select " +
			"instalacao_id, " +
			"nome, " +
			"nome_servidor, " +
			"servidor_porta_entrada, " +
			"servidor_porta_saida, " +
			"cliente_porta_entrada, " +
			"cliente_porta_saida " +
		"from instalacao " +
		"where instalacao_id = ? ";
		
	public static String QRY_BASE_SELECT_ALL = 
		"select " +
			"instalacao_id, " +
			"nome, " +
			"nome_servidor, " +
			"servidor_porta_entrada, " +
			"servidor_porta_saida, " +
			"cliente_porta_entrada, " +
			"cliente_porta_saida " +
		"from instalacao " +
		"order by nome, instalacao_id ";
	
	public static String QRY_BASE_INSERT = 
		"insert into instalacao( " +
			"instalacao_id, " +
			"nome, " +
			"nome_servidor, " +
			"servidor_porta_entrada, " +
			"servidor_porta_saida, " +
			"cliente_porta_entrada, " +
			"cliente_porta_saida) " +
		"values (nextval('seq_instalacao'),?,?,?,?,?,?)";
	
	public static String QRY_BASE_UPDATE = 
		"update instalacao set " +
			"nome = ?, " +
			"nome_servidor = ?, " +
			"servidor_porta_entrada = ?, " +
			"servidor_porta_saida = ?, " +
			"cliente_porta_entrada = ?, " +
			"cliente_porta_saida = ? " +
		"where instalacao_id = ? ";
	
	public static String SEQ_NEXTVAL = 
		"select nextval('seq_instalacao'); ";
	
	public static String SEQ_CURRVAL = 
		"select currval('seq_instalacao'); ";			

//Private
	private static final long serialVersionUID = 1L;

	private Integer instalacaoId;
	private String nome;
	private String nomeServidor;
	private Integer servidorPortaEntrada;
	private Integer servidorPortaSaida;
	private Integer clientePortaEntrada;
	private Integer clientePortaSaida;

//Public

	public Instalacao(
		Integer instalacaoId,
		String nome,
		String nomeServidor,
		Integer servidorPortaEntrada,
		Integer servidorPortaSaida,
		Integer clientePortaEntrada,
		Integer clientePortaSaida)
	{
		super(StringUtil.valToStr(instalacaoId), nome);
		
		this.instalacaoId = instalacaoId;
		this.nome = nome;
		this.nomeServidor = nomeServidor;
		this.servidorPortaEntrada = servidorPortaEntrada;
		this.servidorPortaSaida = servidorPortaSaida;
		this.clientePortaEntrada = clientePortaEntrada;
		this.clientePortaSaida = clientePortaSaida;
	}
	
	public Instalacao(ResultSet rs)
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
			this.instalacaoId = o.getNextInt();
			this.nome = o.getNextStr();
			this.nomeServidor = o.getNextStr();
			this.servidorPortaEntrada = o.getNextInt();
			this.servidorPortaSaida = o.getNextInt();
			this.clientePortaEntrada = o.getNextInt();
			this.clientePortaSaida = o.getNextInt();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
		
	/* Getters/Setters */
	
	public Integer getInstalacaoId() {
		return instalacaoId;
	}
	public void setInstalacaoId(Integer instalacaoId) {
		this.instalacaoId = instalacaoId;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeServidor() {
		return nomeServidor;
	}

	public void setNomeServidor(String nomeServidor) {
		this.nomeServidor = nomeServidor;
	}

	public Integer getServidorPortaEntrada() {
		return servidorPortaEntrada;
	}

	public void setServidorPortaEntrada(Integer servidorPortaEntrada) {
		this.servidorPortaEntrada = servidorPortaEntrada;
	}

	public Integer getServidorPortaSaida() {
		return servidorPortaSaida;
	}

	public void setServidorPortaSaida(Integer servidorPortaSaida) {
		this.servidorPortaSaida = servidorPortaSaida;
	}

	public Integer getClientePortaEntrada() {
		return clientePortaEntrada;
	}

	public void setClientePortaEntrada(Integer clientePortaEntrada) {
		this.clientePortaEntrada = clientePortaEntrada;
	}

	public Integer getClientePortaSaida() {
		return clientePortaSaida;
	}

	public void setClientePortaSaida(Integer clientePortaSaida) {
		this.clientePortaSaida = clientePortaSaida;
	}

}
