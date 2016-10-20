package biz.belcorp.ssicc.service.spusicc.pedido.ws.beans;

import java.io.Serializable;

public class NovedadWebServiceParameter implements Serializable {
	
	private static final long serialVersionUID = -6480085629480704141L;
	String codigoConsultora;
	String codigoPlataforma;
	String fecha;
	Long   identificadorEntrega;
	String latitud;
	String longitud;                           
	String novedad;                           
	String numeroPedido;                
	String observacion;                     
	String paisISO;                            
	String tipoEntrega;                    
	String pedidoInventariado;
	
	
	/**
	 * @return the codigoConsultora
	 */
	public String getCodigoConsultora() {
		return codigoConsultora;
	}
	/**
	 * @param codigoConsultora the codigoConsultora to set
	 */
	public void setCodigoConsultora(String codigoConsultora) {
		this.codigoConsultora = codigoConsultora;
	}
	/**
	 * @return the codigoPlataforma
	 */
	public String getCodigoPlataforma() {
		return codigoPlataforma;
	}
	/**
	 * @param codigoPlataforma the codigoPlataforma to set
	 */
	public void setCodigoPlataforma(String codigoPlataforma) {
		this.codigoPlataforma = codigoPlataforma;
	}
	
	
	
	/**
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}
	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	/**
	 * @return the identificadorEntrega
	 */
	public Long getIdentificadorEntrega() {
		return identificadorEntrega;
	}
	/**
	 * @param identificadorEntrega the identificadorEntrega to set
	 */
	public void setIdentificadorEntrega(Long identificadorEntrega) {
		this.identificadorEntrega = identificadorEntrega;
	}
	/**
	 * @return the latitud
	 */
	public String getLatitud() {
		return latitud;
	}
	/**
	 * @param latitud the latitud to set
	 */
	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}
	/**
	 * @return the longitud
	 */
	public String getLongitud() {
		return longitud;
	}
	/**
	 * @param longitud the longitud to set
	 */
	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}
	/**
	 * @return the novedad
	 */
	public String getNovedad() {
		return novedad;
	}
	/**
	 * @param novedad the novedad to set
	 */
	public void setNovedad(String novedad) {
		this.novedad = novedad;
	}
	/**
	 * @return the numeroPedido
	 */
	public String getNumeroPedido() {
		return numeroPedido;
	}
	/**
	 * @param numeroPedido the numeroPedido to set
	 */
	public void setNumeroPedido(String numeroPedido) {
		this.numeroPedido = numeroPedido;
	}
	/**
	 * @return the observacion
	 */
	public String getObservacion() {
		return observacion;
	}
	/**
	 * @param observacion the observacion to set
	 */
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	/**
	 * @return the paisISO
	 */
	public String getPaisISO() {
		return paisISO;
	}
	/**
	 * @param paisISO the paisISO to set
	 */
	public void setPaisISO(String paisISO) {
		this.paisISO = paisISO;
	}
	/**
	 * @return the tipoEntrega
	 */
	public String getTipoEntrega() {
		return tipoEntrega;
	}
	/**
	 * @param tipoEntrega the tipoEntrega to set
	 */
	public void setTipoEntrega(String tipoEntrega) {
		this.tipoEntrega = tipoEntrega;
	}
	/**
	 * @return the pedidoInventariado
	 */
	public String getPedidoInventariado() {
		return pedidoInventariado;
	}
	/**
	 * @param pedidoInventariado the pedidoInventariado to set
	 */
	public void setPedidoInventariado(String pedidoInventariado) {
		this.pedidoInventariado = pedidoInventariado;
	}

	
	
	

}
