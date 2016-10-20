package biz.belcorp.ssicc.web.spusicc.sto.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class BusquedaSTOCantidadDevuelveForm extends BaseSearchForm implements
		Serializable {

	
	private static final long serialVersionUID = 7334692324695489189L;
	
	private String codigoCliente;
	private String codigoVenta;
	private String descripcionProducto;

	
	public String getCodigoVenta() {
		return codigoVenta;
	}

	public void setCodigoVenta(String codigoVenta) {
		this.codigoVenta = codigoVenta;
	}
	
	public String getDescripcionProducto() {
		return descripcionProducto;
	}
	
	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

}
