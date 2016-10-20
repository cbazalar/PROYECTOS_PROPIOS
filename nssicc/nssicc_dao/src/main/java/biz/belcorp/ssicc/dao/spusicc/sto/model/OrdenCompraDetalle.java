package biz.belcorp.ssicc.dao.spusicc.sto.model;

import java.io.Serializable;



public class OrdenCompraDetalle implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codPais;
	private String codPeriodo;
	private String codCliente;
	private String codVenta;
	private String tipoPosicion;
	private Integer valUniDemandadas;
	private String estadoProceso;
	private String numLote;
	private String numDocumento;
	private String codMotivoRechazo;
	private String numSecuencia;
	private String detalle;
	private String descripcionProducto;
	private String precio;
	private String total;
	private String indicadorError;
	private String stock;
	
	/**
	 * @return Returns the codCliente.
	 */
	public String getCodCliente() {
		return codCliente;
	}
	/**
	 * @param codCliente The codCliente to set.
	 */
	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
	}
	/**
	 * @return Returns the codMotivoRechazo.
	 */
	public String getCodMotivoRechazo() {
		return codMotivoRechazo;
	}
	/**
	 * @param codMotivoRechazo The codMotivoRechazo to set.
	 */
	public void setCodMotivoRechazo(String codMotivoRechazo) {
		this.codMotivoRechazo = codMotivoRechazo;
	}
	/**
	 * @return Returns the codPais.
	 */
	public String getCodPais() {
		return codPais;
	}
	/**
	 * @param codPais The codPais to set.
	 */
	public void setCodPais(String codPais) {
		this.codPais = codPais;
	}
	/**
	 * @return Returns the codPeriodo.
	 */
	public String getCodPeriodo() {
		return codPeriodo;
	}
	/**
	 * @param codPeriodo The codPeriodo to set.
	 */
	public void setCodPeriodo(String codPeriodo) {
		this.codPeriodo = codPeriodo;
	}
	
	/**
	 * @return Returns the codVenta.
	 */
	public String getCodVenta() {
		return codVenta;
	}
	/**
	 * @param codVenta The codVenta to set.
	 */
	public void setCodVenta(String codVenta) {
		this.codVenta = codVenta;
	}
	/**
	
	/**
	 * @return Returns the estadoProceso.
	 */
	public String getEstadoProceso() {
		return estadoProceso;
	}
	/**
	 * @param estadoProceso The estadoProceso to set.
	 */
	public void setEstadoProceso(String estadoProceso) {
		this.estadoProceso = estadoProceso;
	}
	/**
	 * @return Returns the numDocumento.
	 */
	public String getNumDocumento() {
		return numDocumento;
	}
	/**
	 * @param numDocumento The numDocumento to set.
	 */
	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}
	/**
	 * @return Returns the numLote.
	 */
	public String getNumLote() {
		return numLote;
	}
	/**
	 * @param numLote The numLote to set.
	 */
	public void setNumLote(String numLote) {
		this.numLote = numLote;
	}
	/**
	 * @return Returns the tipoPosicion.
	 */
	public String getTipoPosicion() {
		return tipoPosicion;
	}
	/**
	 * @param tipoPosicion The tipoPosicion to set.
	 */
	public void setTipoPosicion(String tipoPosicion) {
		this.tipoPosicion = tipoPosicion;
	}
	/**
	 * @return Returns the valUniDemandadas.
	 */
	public Integer getValUniDemandadas() {
		return valUniDemandadas;
	}
	/**
	 * @param valUniDemandadas The valUniDemandadas to set.
	 */
	public void setValUniDemandadas(Integer valUniDemandadas) {
		this.valUniDemandadas = valUniDemandadas;
	}
	/**
	 * @return Returns the numSecuencia.
	 */
	public String getNumSecuencia() {
		return numSecuencia;
	}
	/**
	 * @param numSecuencia The numSecuencia to set.
	 */
	public void setNumSecuencia(String numSecuencia) {
		this.numSecuencia = numSecuencia;
	}
	/**
	 * @return Returns the detalle.
	 */
	public String getDetalle() {
		return detalle;
	}
	/**
	 * @param detalle The detalle to set.
	 */
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	/**
	 * @return the descripcionProducto
	 */
	public String getDescripcionProducto() {
		return descripcionProducto;
	}
	/**
	 * @param descripcionProducto the descripcionProducto to set
	 */
	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}
	/**
	 * @return the precio
	 */
	public String getPrecio() {
		return precio;
	}
	/**
	 * @param precio the precio to set
	 */
	public void setPrecio(String precio) {
		this.precio = precio;
	}
	/**
	 * @return the total
	 */
	public String getTotal() {
		return total;
	}
	/**
	 * @param total the total to set
	 */
	public void setTotal(String total) {
		this.total = total;
	}
	/**
	 * @return the indicadorError
	 */
	public String getIndicadorError() {
		return indicadorError;
	}
	/**
	 * @param indicadorError the indicadorError to set
	 */
	public void setIndicadorError(String indicadorError) {
		this.indicadorError = indicadorError;
	}
	/**
	 * @return
	 */
	public String getStock() {
		return stock;
	}
	/**
	 * @param stock
	 */
	public void setStock(String stock) {
		this.stock = stock;
	}
	
	
}
