package biz.belcorp.ssicc.web.framework.util;

import java.io.Serializable;

public class DatosCabeceraCDRTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private String operacion;
	private String codigoVentaCambia;
	private String productoCambia;
	private String puFactura;
	private String cantidadCambia;
	private String motivo;
	private boolean igualEnvio;
	private String codigoVentaDesea;
	private String productoDesea;
	private String precioDesea;
	private String cantidadDesea;
	private String listaIdentSolicPos;
	private String desOperacion;
	private String desMotivo;
	private boolean selectedItemsOferta = false;
	
	public DatosCabeceraCDRTO() {
		super();
	}
	
	public String getOperacion() {
		return operacion;
	}
	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}
	public String getCodigoVentaCambia() {
		return codigoVentaCambia;
	}
	public void setCodigoVentaCambia(String codigoVentaCambia) {
		this.codigoVentaCambia = codigoVentaCambia;
	}
	public String getProductoCambia() {
		return productoCambia;
	}
	public void setProductoCambia(String productoCambia) {
		this.productoCambia = productoCambia;
	}
	public String getPuFactura() {
		return puFactura;
	}
	public void setPuFactura(String puFactura) {
		this.puFactura = puFactura;
	}
	public String getCantidadCambia() {
		return cantidadCambia;
	}
	public void setCantidadCambia(String cantidadCambia) {
		this.cantidadCambia = cantidadCambia;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public boolean isIgualEnvio() {
		return igualEnvio;
	}
	public void setIgualEnvio(boolean igualEnvio) {
		this.igualEnvio = igualEnvio;
	}
	public String getCodigoVentaDesea() {
		return codigoVentaDesea;
	}
	public void setCodigoVentaDesea(String codigoVentaDesea) {
		this.codigoVentaDesea = codigoVentaDesea;
	}
	public String getProductoDesea() {
		return productoDesea;
	}
	public void setProductoDesea(String productoDesea) {
		this.productoDesea = productoDesea;
	}
	public String getPrecioDesea() {
		return precioDesea;
	}
	public void setPrecioDesea(String precioDesea) {
		this.precioDesea = precioDesea;
	}
	public String getCantidadDesea() {
		return cantidadDesea;
	}
	public void setCantidadDesea(String cantidadDesea) {
		this.cantidadDesea = cantidadDesea;
	}

	public String getListaIdentSolicPos() {
		return listaIdentSolicPos;
	}

	public void setListaIdentSolicPos(String listaIdentSolicPos) {
		this.listaIdentSolicPos = listaIdentSolicPos;
	}

	public String getDesOperacion() {
		return desOperacion;
	}

	public void setDesOperacion(String desOperacion) {
		this.desOperacion = desOperacion;
	}

	public String getDesMotivo() {
		return desMotivo;
	}

	public void setDesMotivo(String desMotivo) {
		this.desMotivo = desMotivo;
	}

	public boolean isSelectedItemsOferta() {
		return selectedItemsOferta;
	}

	public void setSelectedItemsOferta(boolean selectedItemsOferta) {
		this.selectedItemsOferta = selectedItemsOferta;
	}
}
