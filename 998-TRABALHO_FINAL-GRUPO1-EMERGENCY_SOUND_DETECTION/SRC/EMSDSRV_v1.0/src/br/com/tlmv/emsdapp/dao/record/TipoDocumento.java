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
 * TipoDocumento.java
 */

package br.com.tlmv.emsdapp.dao.record;

import java.io.Serializable;
import java.sql.ResultSet;

import br.com.tlmv.emsdapp.utils.DbUtil;
import br.com.tlmv.emsdapp.utils.StringUtil;

public class TipoDocumento extends Record implements Serializable
{
//Public Static
	public static String QRY_BASE_SELECT_BYPK = 
		"select " +
			"tipo_documento_id, " +
			"extensao, " +
			"mime, " +
			"tipo " +
		"from tipo_documento " +
		"where tipo_documento_id = ? ";
		
	public static String QRY_BASE_SELECT_ALL = 
		"select " +
			"tipo_documento_id, " +
			"extensao, " +
			"mime, " +
			"tipo " +
		"from tipo_documento " +
		"order by extensao, tipo_documento_id ";
	
	public static String QRY_BASE_INSERT = 
		"insert into tipo_documento( " +
			"tipo_documento_id, " +
			"extensao, " +
			"mime, " +
			"tipo " +
		"values (?,?,?,?)";
	
	public static String QRY_BASE_UPDATE = 
		"update tipo_documento set " +
			"tipo_documento_id, " +
			"extensao, " +
			"mime, " +
			"tipo " +
		"where tipo_documento_id = ? ";
							
//Private
	private static final long serialVersionUID = 1L;
	
	private Integer tipoDocumentoId;
	private String extensao;
	private String mime;
	private String tipo;		//NULL=NDEF / I=IMAGEM / V=VIDEO

//Public
	
	public TipoDocumento(
		Integer tipoDocumentoId,
		String extensao,
		String mime,
		String tipo)			//NULL=NDEF / I=IMAGEM / V=VIDEO
	{
		super(StringUtil.valToStr(tipoDocumentoId), mime);
		
		this.tipoDocumentoId = tipoDocumentoId;
		this.extensao = extensao;
		this.mime = mime;
		this.tipo = tipo;
	}
	
	public TipoDocumento(ResultSet rs)
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
			this.tipoDocumentoId = o.getNextInt();
			this.extensao = o.getNextStr();
			this.mime = o.getNextStr();
			this.tipo = o.getNextStr();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/* Getters/Setters */
	
	public Integer getTipoDocumentoId() {
		return tipoDocumentoId;
	}

	public void setTipoDocumentoId(Integer tipoDocumentoId) {
		this.tipoDocumentoId = tipoDocumentoId;
	}

	public String getExtensao() {
		return extensao;
	}

	public void setExtensao(String extensao) {
		this.extensao = extensao;
	}

	public String getMime() {
		return mime;
	}

	public void setMime(String mime) {
		this.mime = mime;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
