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
 * Audio.java
 */

package br.com.tlmv.emsdapp.dao.record;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.Date;

import br.com.tlmv.emsdapp.utils.DbUtil;
import br.com.tlmv.emsdapp.utils.StringUtil;

public class Audio extends Record implements Serializable
{
//Public Static
	public static String QRY_BASE_SELECT_BYPK = 
		"select " +
			"audio_id, " +
			"slice_file_name, " +
			"fs_id, " +
			"start1, " +
			"end1, " +
			"salience, " +
			"fold, " +
			"class_id, " +
			"class1, " +
			"y " +
		"from audio " +
		"where audio_id = ? ";
		
	public static String QRY_BASE_SELECT_BYCLASSID = 
		"select " +
			"audio_id, " +
			"slice_file_name, " +
			"fs_id, " +
			"start1, " +
			"end1, " +
			"salience, " +
			"fold, " +
			"class_id, " +
			"class1, " +
			"y " +
		"from audio " +
		"where class_id = ? ";
			
	public static String QRY_BASE_SELECT_ALL = 
		"select " +
			"audio_id, " +
			"slice_file_name, " +
			"fs_id, " +
			"start1, " +
			"end1, " +
			"salience, " +
			"fold, " +
			"class_id, " +
			"class1, " +
			"y " +
		"from audio " +
		"order by audio_id ";
	
//Private
	private static final long serialVersionUID = 1L;

	private Integer audioId;
	private String sliceFileName;
	private Integer fsId;
	private Double start1;
	private Double end1;
	private Integer salience;
	private Integer fold;
	private Integer classId;
	private String class1;
	private Integer y;

//Public

	public Audio(
		Integer audioId,
		String sliceFileName,
		Integer fsId,
		Double start1,
		Double end1,
		Integer salience,
		Integer fold,
		Integer classId,
		String class1,
		Integer y)
	{
		super(StringUtil.valToStr(audioId), sliceFileName);
		
		this.audioId = audioId;
		this.sliceFileName = sliceFileName;
		this.fsId = fsId;
		this.start1 = start1;
		this.end1 = end1;
		this.salience = salience;
		this.fold = fold;
		this.classId = classId;
		this.class1 = class1;
		this.y = y;
	}
	
	public Audio(ResultSet rs)
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
			this.audioId = o.getNextInt();
			this.sliceFileName = o.getNextStr();
			this.fsId = o.getNextInt();
			this.start1 = o.getNextDbl();
			this.end1 = o.getNextDbl();
			this.salience = o.getNextInt();
			this.fold = o.getNextInt();
			this.classId = o.getNextInt();
			this.class1 = o.getNextStr();
			this.y = o.getNextInt();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
		
	/* Getters/Setters */
	
	public Integer getAudioId() {
		return audioId;
	}

	public void setAudioId(Integer audioId) {
		this.audioId = audioId;
	}

	public String getSliceFileName() {
		return sliceFileName;
	}

	public void setSliceFileName(String sliceFileName) {
		this.sliceFileName = sliceFileName;
	}

	public Integer getFsId() {
		return fsId;
	}

	public void setFsId(Integer fsId) {
		this.fsId = fsId;
	}

	public Double getStart1() {
		return start1;
	}

	public void setStart1(Double start1) {
		this.start1 = start1;
	}

	public Double getEnd1() {
		return end1;
	}

	public void setEnd1(Double end1) {
		this.end1 = end1;
	}

	public Integer getSalience() {
		return salience;
	}

	public void setSalience(Integer salience) {
		this.salience = salience;
	}

	public Integer getFold() {
		return fold;
	}

	public void setFold(Integer fold) {
		this.fold = fold;
	}

	public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	public String getClass1() {
		return class1;
	}

	public void setClass1(String class1) {
		this.class1 = class1;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

}
