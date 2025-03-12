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
 * AlgorKMean.java
 */

package br.com.tlmv.emsdapp.vo;

public class CentroidVO 
{
//Private  
	private Double x = 0.0;
	private Double y = 0.0;
  
	private Double xc = 0.0;
	private Double yc = 0.0;
  
	private Double w = 0.0;
	private Double h = 0.0;
  
	private Double minx = 0.0;
	private Double miny = 0.0;
  
	private Double maxx = 0.0;
	private Double maxy = 0.0;
  
	private Double mi = 0.0;
	private Integer yn = 0;
  
	private Double score = 0.0;
	private Double cost = 0.0; 
	private Double dist = 0.0;
  
//Public
	
	public CentroidVO(Double x0, Double y0, Double w0, Double h0)
	{
		init(x0, y0, w0, h0);
	}
	
	public CentroidVO init(Double x0, Double y0, Double w0, Double h0)
	{
	    this.x = x0;
	    this.y = y0;
	    
	    this.xc = x0;
	    this.yc = y0;
	    
	    this.w = w0;
	    this.h = h0;
	    
	    this.score = 0.0;
	    
	    this.cost = 0.0;
	    
	    this.dist = 0.0;
	    
	    this.minx = xc - (w / 2);
	    this.miny = yc - (h / 2);
	    
	    this.maxx = xc + (w / 2);
	    this.maxy = yc + (h / 2);
	    
	    return this;
	}
  
	public CentroidVO repos(Double x0, Double y0)
	{
	    this.x = x0;
	    this.y = y0;
	    
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
  		String str = 
	      "Centroid: " + x + "," + y + 
	      ",CentrReg: " + xc + "," + yc + 
	      ",Width: " + w + ",Height: " + h + 
	      ",MI: " + mi + 
	      ",Yn: " + yn + 
	      ",Score: " + score +
	      ",Cost: " + cost +
	      ",Dist: " + dist;
	
	    System.out.println(str);
	    return str;
  	}
  
  	/* Getters/Setters */
  
	public Double getMinx() {
		return minx;
	}
	
	public void setMinx(Double minx) {
		this.minx = minx;
	}
	
	public Double getMiny() {
		return miny;
	}
	
	public void setMiny(Double miny) {
		this.miny = miny;
	}
	
	public Double getMaxx() {
		return maxx;
	}
	
	public void setMaxx(Double maxx) {
		this.maxx = maxx;
	}
	
	public Double getMaxy() {
		return maxy;
	}
	
	public void setMaxy(Double maxy) {
		this.maxy = maxy;
	}
	
	public Double getMi() {
		return mi;
	}
	
	public void setMi(Double mi) {
		this.mi = mi;
	}
	
	public void setX(Double x) {
		this.x = x;
	}
	
	public void setY(Double y) {
		this.y = y;
	}
	
	public void setXc(Double xc) {
		this.xc = xc;
	}
	
	public void setYc(Double yc) {
		this.yc = yc;
	}
	
	public void setW(Double w) {
		this.w = w;
	}
	
	public void setH(Double h) {
		this.h = h;
	}
	
	public void setYn(Integer yn) {
		this.yn = yn;
	}
	
	public void setScore(Double score) {
		this.score = score;
	}
	
	public void setCost(Double cost) {
		this.cost = cost;
	}
	
	public void setDist(Double dist) {
		this.dist = dist;
	}
	
}
