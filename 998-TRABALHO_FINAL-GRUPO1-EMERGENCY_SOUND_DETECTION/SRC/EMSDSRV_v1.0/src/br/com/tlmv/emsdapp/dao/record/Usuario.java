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
 * Usuario.java
 */

package br.com.tlmv.emsdapp.dao.record;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import br.com.tlmv.emsdapp.AppDefs;
import br.com.tlmv.emsdapp.utils.DbUtil;
import br.com.tlmv.emsdapp.utils.StringUtil;

public class Usuario extends Record implements Serializable
{
//Public Static
	public static String QRY_BASE_SELECT_BYPK = 
		"select " +
			"usuario_id, " +
			"instalacao_id, " +
			"nome, " +
			"sobrenome, " +
			"login, " +
			"senha, " +
			"tipoUsuario, " +
			"telefone1, " +
			"telefone2, " +
			"email, " +
			"visivel, " +
			"autor_desativacao_id, " +
			"data_desativacao " +
		"from usuario " +
		"where usuario_id = ? ";
		
	public static String QRY_BASE_SELECT_BYLOGIN = 
		"select " +
			"usuario_id, " +
			"instalacao_id, " +
			"nome, " +
			"sobrenome, " +
			"login, " +
			"senha, " +
			"tipoUsuario, " +
			"telefone1, " +
			"telefone2, " +
			"email, " +
			"visivel, " +
			"autor_desativacao_id, " +
			"data_desativacao " +
		"from usuario " +
		"where upper(login) = ? ";
		
	public static String QRY_BASE_SELECT_ALL = 
		"select " +
			"usuario_id, " +
			"instalacao_id, " +
			"nome, " +
			"sobrenome, " +
			"login, " +
			"senha, " +
			"tipoUsuario, " +
			"telefone1, " +
			"telefone2, " +
			"email, " +
			"visivel, " +
			"autor_desativacao_id, " +
			"data_desativacao " +
		"from usuario " +
		"order by nome, sobrenome, usuario_id ";
	
	public static String QRY_BASE_INSERT = 
		"insert into usuario( " +
			"usuario_id, " +
			"instalacao_id, " +
			"nome, " +
			"sobrenome, " +
			"login, " +
			"senha, " +
			"tipoUsuario, " +
			"telefone1, " +
			"telefone2, " +
			"email, " +
			"visivel, " +
			"autor_desativacao_id, " +
			"data_desativacao) " +
		"values (nextval('seq_usuario')," + AppDefs.DEF_INSTALACAO_PADRAO_ID_STR + ",?,?,?,?,?,?,?,?,?,?)";
	
	public static String QRY_BASE_UPDATE = 
		"update usuario set " +
			"instalacao_id = ?, " +
			"nome = ?, " +
			"sobrenome = ?, " +
			"login = ?, " +
			"senha = ?, " +
			"tipoUsuario = ?, " +
			"telefone1 = ?, " +
			"telefone2 = ?, " +
			"email = ?, " +
			"visivel = ?, " +
			"autor_desativacao_id = ?, " +
			"data_desativacao = ? " +
		"where usuario_id = ? ";

	public static String QRY_BASE_DELETE = 
		"update usuario set " +
			"data_desativacao = CURRENT_TIMESTAMP, " +
			"autor_desativacao_id = " + AppDefs.DEF_USUARIO_PADRAO_ID_STR + " " +
		"where usuario_id = ? ";
	
	public static String SEQ_NEXTVAL = 
		"select nextval('seq_usuario'); ";
	
	public static String SEQ_CURRVAL = 
		"select currval('seq_usuario'); ";			

//Private
		
	private static final long serialVersionUID = 1L;
	
	private Integer usuarioId;
	private Integer instalacaoId;
	private String nome;
	private String sobrenome;
	private String login;
	private String senha;
	private String tipoUsuario;		//A-Administrador / U-Usuario Basico
	private String email;	
	private String telefone1;
	private String telefone2;
	private String visivel;			//S-Visivel / N-Nao Visivel
	private Integer autorDesativacaoId;
	private Date dataDesativacao;

//Public
	
	public Usuario(
		Integer usuarioId,
		Integer instalacaoId,
		String nome,
		String sobrenome,
		String login,
		String senha,
		String tipoUsuario,
		String email,
		String telefone1,
		String telefone2,
		String visivel,
		Integer autorDesativacaoId,
		java.sql.Timestamp dataDesativacao)
	{
		super(StringUtil.valToStr(usuarioId), nome);
		
		this.usuarioId = usuarioId;
		this.instalacaoId = instalacaoId;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.login = login;
		this.senha = senha;
		this.tipoUsuario = tipoUsuario;
		this.email = email;
		this.telefone1 = telefone1;
		this.telefone2 = telefone2;
		this.visivel = visivel;
		this.autorDesativacaoId = autorDesativacaoId;
		this.dataDesativacao = dataDesativacao;
	}
	
	public Usuario(ResultSet rs)
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
			this.usuarioId = o.getNextInt();
			this.instalacaoId = o.getNextInt();
			this.nome = o.getNextStr();
			this.sobrenome = o.getNextStr();
			this.login = o.getNextStr();
			this.senha = o.getNextStr();
			this.tipoUsuario = o.getNextStr();
			this.email = o.getNextStr();
			this.telefone1 = o.getNextStr();
			this.telefone2 = o.getNextStr();
			this.visivel = o.getNextStr();
			this.autorDesativacaoId = o.getNextInt();
			this.dataDesativacao = o.getNextTimestamp();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/* Getters/Setters */
	
	public Integer getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getNomeCompleto() {
		String nomeCompleto = nome;
		if(sobrenome != null)
			nomeCompleto += " " + sobrenome;
		return nomeCompleto;
	}
	
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTelefone1() {
		return telefone1;
	}
	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	public String getTelefone2() {
		return telefone2;
	}
	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getVisivel() {
		return visivel;
	}
	public void setVisivel(String visivel) {
		this.visivel = visivel;
	}
	public Integer getInstalacaoId() {
		return instalacaoId;
	}
	public void setInstalacaoId(Integer instalacaoId) {
		this.instalacaoId = instalacaoId;
	}
	public Integer getAutorDesativacaoId() {
		return autorDesativacaoId;
	}
	public void setAutorDesativacaoId(Integer autorDesativacaoId) {
		this.autorDesativacaoId = autorDesativacaoId;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public Date getDataDesativacao() {
		return dataDesativacao;
	}

	public void setDataDesativacao(Date dataDesativacao) {
		this.dataDesativacao = dataDesativacao;
	}

}
