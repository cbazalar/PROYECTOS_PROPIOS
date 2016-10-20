package biz.belcorp.ssicc.dao.spusicc.reclamos.model;

import java.io.Serializable;

public class ProdutosFFNNEE implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -9095273511336720332L;
	private String codigoSAP;
    private String codigoPeriodoInicio;
    private String codigoPeriodoFin;
    private String codigoRegion;
	public String getCodigoSAP() {
		return codigoSAP;
	}
	public void setCodigoSAP(String codigoSAP) {
		this.codigoSAP = codigoSAP;
	}
	public String getCodigoPeriodoInicio() {
		return codigoPeriodoInicio;
	}
	public void setCodigoPeriodoInicio(String codigoPeriodoInicio) {
		this.codigoPeriodoInicio = codigoPeriodoInicio;
	}
	public String getCodigoPeriodoFin() {
		return codigoPeriodoFin;
	}
	public void setCodigoPeriodoFin(String codigoPeriodoFin) {
		this.codigoPeriodoFin = codigoPeriodoFin;
	}
	public String getCodigoRegion() {
		return codigoRegion;
	}
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}
    
    
	
}
