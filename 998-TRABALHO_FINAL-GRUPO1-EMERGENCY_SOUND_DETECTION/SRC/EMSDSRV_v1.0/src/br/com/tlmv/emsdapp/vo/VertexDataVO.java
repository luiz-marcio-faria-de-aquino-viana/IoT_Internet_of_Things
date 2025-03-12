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
 * VertexDataVO.java
 */

package br.com.tlmv.emsdapp.vo;

public class VertexDataVO   
{
//Private
	private int imageId = -1;
	private String imageName = "";
	private String imageFileName = "";
	private String cdImageFileName = "";
	private String regImageFileName = "";
	private EdgeDataVO edgeLevel = null;
	private Integer yn = 0;
	private Double score = 0.0;

//Public
	
	public Integer init(int imageId0, String imageName0, String imageFileName0)
	{
	    imageId = imageId0;
	    imageName = imageName0;
	    imageFileName = imageFileName0;
	    cdImageFileName = "";
	    regImageFileName = "";
	    edgeLevel = null;
	    yn = 0;
	    score = 0.0;
	    return imageId;
	}
	  
	public String debug() 
	{
		String outStr = "[" + imageId + "," + imageName + "," + imageFileName + "," + edgeLevel.getChangeLevelName() + "," + score + "]";
	    System.out.println(outStr);
	    return outStr;
	}
	
	public String debugSimpl(Integer maxEdgeLevel)
	{
	    Integer edgeLevelId = edgeLevel.getChangeLevelId();
	
	    String outStr = "[" + imageId + "," + imageName + "," + edgeLevelId + "," + score + "]";
	    if(edgeLevelId <= maxEdgeLevel)
	      outStr = "(*) " + outStr;
	    System.out.println(outStr);
	    return outStr;
	}
	
	/* Getters/Setters */

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}
	
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}
	
	public void setCdImageFileName(String cdImageFileName) {
		this.cdImageFileName = cdImageFileName;
	}
	
	public void setRegImageFileName(String regImageFileName) {
		this.regImageFileName = regImageFileName;
	}
	
	public void setEdgeLevel(EdgeDataVO edgeLevel) {
		this.edgeLevel = edgeLevel;
	}
	
	public void setYn(Integer yn) {
		this.yn = yn;
	}
	
	public void setScore(Double score) {
		this.score = score;
	}

}
