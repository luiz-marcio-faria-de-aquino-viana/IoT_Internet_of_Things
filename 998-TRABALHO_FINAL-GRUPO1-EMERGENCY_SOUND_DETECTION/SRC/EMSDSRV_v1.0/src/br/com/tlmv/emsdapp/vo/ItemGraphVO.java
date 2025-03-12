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
 * ItemGraphVO.java
 */

package br.com.tlmv.emsdapp.vo;

import br.com.tlmv.emsdapp.AppDefs;

public class ItemGraphVO 
{
//Private
	private Integer imageId = -1;
	private String imageName = "";
  	private String imageFileName = "";
  	private Double score = 0.0;
  	private Integer edgeLevelId = 0;
  	private Integer yn = 0;
  	private ItemGraphVO ptrLeft = null;
  	private ItemGraphVO ptrRight = null;

//Public
  	
  	public ItemGraphVO (
  		Integer imageId,
  		String imageName,
  		String imageFileName,
  		Double score,
  		Integer edgeLevelId,
  		Integer yn,
  		ItemGraphVO ptrLeft,
  		ItemGraphVO ptrRight)
	{
		init(
		    imageId,
		    imageName,
		    imageFileName,
		    score,
		    edgeLevelId,
		    yn);		
	}

	public Integer init(
	    Integer imageId0,
	    String imageName0,
	    String imageFileName0,
	    Double score0,
	    Integer edgeLevelId0,
	    Integer yn0)
	{
	    imageId = imageId0;
	    imageName = imageName0;
	    imageFileName = imageFileName0;
	    score = score0;
	    edgeLevelId = edgeLevelId0;    
	    yn = yn0;
	    ptrLeft = null;
	    ptrRight = null;
    
    	return AppDefs.RSOK;
	}

	/* Methodes */
  
	public Integer debug()
	{
		System.out.println(
			"ImageId=" + imageId + 
			",ImageName=" + imageName + 
			",ImageFileName=" + imageFileName + 
			",Score=" + score +
			",EdgeLevelId=" + edgeLevelId +
			",Yn=" + yn);
    
		return AppDefs.RSOK;
	}

	/* Getters/Setters */
  
  	public void setImageId(Integer imageId) {
		this.imageId = imageId;
	}
	
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}
	
	public void setScore(Double score) {
		this.score = score;
	}
	
	public void setEdgeLevelId(Integer edgeLevelId) {
		this.edgeLevelId = edgeLevelId;
	}
	
	public void setYn(Integer yn) {
		this.yn = yn;
	}
	
	public void setPtrLeft(ItemGraphVO ptrLeft) {
		this.ptrLeft = ptrLeft;
	}
	
	public void setPtrRight(ItemGraphVO ptrRight) {
		this.ptrRight = ptrRight;
	}
  
}
