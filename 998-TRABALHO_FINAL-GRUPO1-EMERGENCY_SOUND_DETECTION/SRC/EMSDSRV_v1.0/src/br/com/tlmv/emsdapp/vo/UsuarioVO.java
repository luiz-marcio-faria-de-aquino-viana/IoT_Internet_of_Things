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
 * UsuarioVO.java
 */

package br.com.tlmv.emsdapp.vo;

import java.sql.ResultSet;

//CREATE TABLE usuario
//(
//    usuario_id numeric(10,0) NOT NULL,
//    nome character varying(80) NOT NULL,
//    sobrenome character varying(80) NOT NULL,
//    login character varying(35) NOT NULL,
//    senha character varying(35) NOT NULL,
//    tipo_usuario character varying(1) NOT NULL DEFAULT 'U'::character varying,
//    PRIMARY KEY (usuario_id)
//)
public class UsuarioVO extends RecordVO
{
//Private
	private Integer usuarioId;
	private String nome;
	private String sobrenome;
	private String login;
	private String senha;
	private String tipoUsuario;
	
//Public 	
	public static final String SQLTBL_USUARIO			 					= "usuario";
	//
	public static final int SQLFLD_USUARIO_USUARIO_ID 						= 1;
	public static final int SQLFLD_USUARIO_NOME 							= 2;
	public static final int SQLFLD_USUARIO_SOBRENOME 						= 3;
	public static final int SQLFLD_USUARIO_LOGIN 							= 4;
	public static final int SQLFLD_USUARIO_SENHA 							= 5;
	public static final int SQLFLD_USUARIO_TIPO_USUARIO 					= 6;

	/* Methodes */
	
	public UsuarioVO(
		Integer usuarioId,
		String nome,
		String sobrenome,
		String login,
		String senha,
		String tipoUsuario)
	{
		this.usuarioId = usuarioId;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.login = login;
		this.senha = senha;
		this.tipoUsuario = tipoUsuario;

		this.init(
			Integer.toString(this.usuarioId),
			this.nome);
	}
	
	public UsuarioVO(ResultSet rs)
		throws Exception
	{
		this.usuarioId = rs.getInt(UsuarioVO.SQLFLD_USUARIO_USUARIO_ID);
		this.nome = rs.getString(UsuarioVO.SQLFLD_USUARIO_NOME);
		this.sobrenome = rs.getString(UsuarioVO.SQLFLD_USUARIO_SOBRENOME);
		this.login = rs.getString(UsuarioVO.SQLFLD_USUARIO_LOGIN);
		this.senha = rs.getString(UsuarioVO.SQLFLD_USUARIO_SENHA);
		this.tipoUsuario = rs.getString(UsuarioVO.SQLFLD_USUARIO_TIPO_USUARIO);

		this.init(
			Integer.toString(this.usuarioId),
			this.nome);
	}
	
	/* Getters/Setters */

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	
}
