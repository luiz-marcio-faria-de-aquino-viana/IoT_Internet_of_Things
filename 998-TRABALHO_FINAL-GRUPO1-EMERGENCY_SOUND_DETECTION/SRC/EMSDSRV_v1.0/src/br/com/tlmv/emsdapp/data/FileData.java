/*
 * COPPE / UFRJ - Universidade Federal do Rio de Janeiro
 * PESC - Programa de Engenharia de Sistemas e Computacao
 *
 * Disciplina: CPS730 - Internet das Coisas
 * Professor: Claudio Miceli
 *
 * Trabalho Final - Emergency Sound Detection
 * Data: 25/AGO/2022
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
 * FileData.java
 */

package br.com.tlmv.emsdapp.data;

import java.util.Date;

import br.com.tlmv.emsdapp.AppDefs;

public class FileData extends ItemData
{
//Private
	private String fullFileName;
	private String fileName;
	private String extension;
	private Date dataModificacao;
	private boolean isFile;

//Public
	
	public FileData(
		String fullFileName,
		String fileName,
		String extension,
		Date dataModificacao,
		boolean isFile)
	{
		super(fullFileName, fileName);

		this.fullFileName = fullFileName;
		this.fileName = fileName;
		this.extension = extension;
		this.dataModificacao = dataModificacao;		
		this.isFile = isFile;
	}

	/* Methodes */
	
	@Override
	public String toString() 
	{
		return this.getFileName();
	}
	
	/* Getters/Setters */

	public String getFullFileName() {
		return fullFileName;
	}

	public void setFullFileName(String fullFileName) {
		this.fullFileName = fullFileName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public Date getDataModificacao() {
		return dataModificacao;
	}

	public void setDataModificacao(Date dataModificacao) {
		this.dataModificacao = dataModificacao;
	}

	public boolean isFile() {
		return isFile;
	}

	public void setFile(boolean isFile) {
		this.isFile = isFile;
	}
	
}
