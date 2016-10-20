package biz.belcorp.ssicc.web.spusicc.sto.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class BusquedaSTOCodigoVentaMatrizForm extends BaseSearchForm implements Serializable{
	
	private static final long serialVersionUID = -7592021259596295968L;
	
	private String codigoPeriodo;
    private String codigoVenta;
    private String codigoSAP;
    private String descripcion;
    private String matriz;
    private String numeroCruce;
    private String indice;
    
	public String getCodigoVenta() {
		return codigoVenta;
	}
	public void setCodigoVenta(String codigoVenta) {
		this.codigoVenta = codigoVenta;
	}
	public String getCodigoSAP() {
		return codigoSAP;
	}
	public void setCodigoSAP(String codigoSAP) {
		this.codigoSAP = codigoSAP;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	public String getMatriz() {
		return matriz;
	}
	public void setMatriz(String matriz) {
		this.matriz = matriz;
	}
	public String getNumeroCruce() {
		return numeroCruce;
	}
	public void setNumeroCruce(String numeroCruce) {
		this.numeroCruce = numeroCruce;
	}
	/**
	 * @return the indice
	 */
	public String getIndice() {
		return indice;
	}
	/**
	 * @param indice the indice to set
	 */
	public void setIndice(String indice) {
		this.indice = indice;
	}

}
