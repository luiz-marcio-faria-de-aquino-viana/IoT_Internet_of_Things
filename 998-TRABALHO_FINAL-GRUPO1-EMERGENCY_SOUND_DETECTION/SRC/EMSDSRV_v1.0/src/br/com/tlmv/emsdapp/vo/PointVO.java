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
 * PointVO.java
 */

package br.com.tlmv.emsdapp.vo;

public class PointVO 
{
//Private  
	private Double x = 0.0;
	private Double y = 0.0;
	private Byte c = 0;

	private Double xval = 0.0;
  	private Double yval = 0.0;
  
//Public
  	
  	public PointVO init(Double x0, Double y0, Byte c0)
  	{
  		this.x = x0;
  		this.y = y0;
  		this.c = c0;
    
  		this.xval = 0.0;
  		this.yval = 0.0;
    
  		return this;
  	}
  
  	/* Methodes */
  
  	public Double getDist(Double x0, Double y0)
  	{
  		Double d = Math.sqrt((x0 - x) * (x0 - x) + (y0 - y) * (y0 - y));
  		return d;
  	}

  	public Double getDist(PointVO pt)
  	{
  		Double d = getDist(pt.getX(), pt.getY());
  		return d;
  	}

  	public String debug() 
  	{
  		String str = "Point: " + x + "," + y + ",Color: " + c;
  		System.out.println(str);
  		return str;
  	}
	
	/* Getters/Setters */
  
	public Double getY() {
		return y;
	}
	
	public void setY(Double y) {
		this.y = y;
	}
	
	public Byte getC() {
		return c;
	}
	
	public void setC(Byte c) {
		this.c = c;
	}
	
	public Double getXval() {
		return xval;
	}
	
	public void setXval(Double xval) {
		this.xval = xval;
	}
	
	public Double getYval() {
		return yval;
	}
	
	public void setYval(Double yval) {
		this.yval = yval;
	}
	
	public void setX(Double x) {
		this.x = x;
	}

	public Double getX() {
		return x;
	}

}