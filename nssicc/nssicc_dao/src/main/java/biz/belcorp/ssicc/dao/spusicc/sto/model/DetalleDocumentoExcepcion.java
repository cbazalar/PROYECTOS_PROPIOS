package biz.belcorp.ssicc.dao.spusicc.sto.model;

import java.io.Serializable;

public class DetalleDocumentoExcepcion implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8671696155237894740L;
	private String codigoPais;
	private String codigoValidacion;
	private String descripcionTipoDocumento;
	private String numeroLote;
	private String numeroDocumento;
	private String indAprobacion;	
	private String indGestionable;	
	private String fechaAprobacion;
	private String usuarioApro;
	private String indLevaError;
	
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	/**
	 * @return the codigoValidacion
	 */
	public String getCodigoValidacion() {
		return codigoValidacion;
	}
	/**
	 * @param codigoValidacion the codigoValidacion to set
	 */
	public void setCodigoValidacion(String codigoValidacion) {
		this.codigoValidacion = codigoValidacion;
	}
	/**
	 * @return the descripcionTipoDocumento
	 */
	public String getDescripcionTipoDocumento() {
		return descripcionTipoDocumento;
	}
	/**
	 * @param descripcionTipoDocumento the descripcionTipoDocumento to set
	 */
	public void setDescripcionTipoDocumento(String descripcionTipoDocumento) {
		this.descripcionTipoDocumento = descripcionTipoDocumento;
	}
	/**
	 * @return the numeroLote
	 */
	public String getNumeroLote() {
		return numeroLote;
	}
	/**
	 * @param numeroLote the numeroLote to set
	 */
	public void setNumeroLote(String numeroLote) {
		this.numeroLote = numeroLote;
	}
	/**
	 * @return the numeroDocumento
	 */
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	/**
	 * @param numeroDocumento the numeroDocumento to set
	 */
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	/**
	 * @return the indAprobacion
	 */
	public String getIndAprobacion() {
		return indAprobacion;
	}
	/**
	 * @param indAprobacion the indAprobacion to set
	 */
	public void setIndAprobacion(String indAprobacion) {
		this.indAprobacion = indAprobacion;
	}
	/**
	 * @return the indGestionable
	 */
	public String getIndGestionable() {
		return indGestionable;
	}
	/**
	 * @param indGestionable the indGestionable to set
	 */
	public void setIndGestionable(String indGestionable) {
		this.indGestionable = indGestionable;
	}
	/**
	 * @return the fechaAprobacion
	 */
	public String getFechaAprobacion() {
		return fechaAprobacion;
	}
	/**
	 * @param fechaAprobacion the fechaAprobacion to set
	 */
	public void setFechaAprobacion(String fechaAprobacion) {
		this.fechaAprobacion = fechaAprobacion;
	}
	/**
	 * @return the usuarioApro
	 */
	public String getUsuarioApro() {
		return usuarioApro;
	}
	/**
	 * @param usuarioApro the usuarioApro to set
	 */
	public void setUsuarioApro(String usuarioApro) {
		this.usuarioApro = usuarioApro;
	}
	/**
	 * @return the indLevaError
	 */
	public String getIndLevaError() {
		return indLevaError;
	}
	/**
	 * @param indLevaError the indLevaError to set
	 */
	public void setIndLevaError(String indLevaError) {
		this.indLevaError = indLevaError;
	}
	

}
