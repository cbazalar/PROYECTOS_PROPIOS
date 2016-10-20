package biz.belcorp.ssicc.web.spusicc.sto.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class BusquedaSTOCodigoVentaPedidoForm  extends BaseSearchForm
implements Serializable{

	private static final long serialVersionUID = -6942831458315733488L;
	
	private String codigoPais;    
    private String codigoVenta;
    private String codigoSAP;
    private String descripcion;
    private String numeroCruce;
    private String indice;
    private String numPedido;
    private String codConsejera;
   
    
	public String getCodigoPais() {
		return codigoPais;
	}
	
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
   
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
	
	public String getNumeroCruce() {
		return numeroCruce;
	}
	
	public void setNumeroCruce(String numeroCruce) {
		this.numeroCruce = numeroCruce;
	}
	
	public String getIndice() {
		return indice;
	}
	
	public void setIndice(String indice) {
		this.indice = indice;
	}
	
	public String getNumPedido() {
		return numPedido;
	}
	
	public void setNumPedido(String numPedido) {
		this.numPedido = numPedido;
	}
	
	public String getCodConsejera() {
		return codConsejera;
	}
	
	public void setCodConsejera(String codConsejera) {
		this.codConsejera = codConsejera;
	}

}
