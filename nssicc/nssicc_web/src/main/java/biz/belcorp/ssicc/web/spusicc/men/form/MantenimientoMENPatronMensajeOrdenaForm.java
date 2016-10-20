package biz.belcorp.ssicc.web.spusicc.men.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoMENPatronMensajeOrdenaForm extends BaseEditForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7801942452460041581L;
	private String codigoPais;
	private String campanha;
	private String codigoDocumento;
	private String [] seccion;
	private String [] mensajes;
	private String [] mensajesDisponibles;
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
	 * @return the campanha
	 */
	public String getCampanha() {
		return campanha;
	}
	/**
	 * @param campanha the campanha to set
	 */
	public void setCampanha(String campanha) {
		this.campanha = campanha;
	}
	/**
	 * @return the codigoDocumento
	 */
	public String getCodigoDocumento() {
		return codigoDocumento;
	}
	/**
	 * @param codigoDocumento the codigoDocumento to set
	 */
	public void setCodigoDocumento(String codigoDocumento) {
		this.codigoDocumento = codigoDocumento;
	}
	/**
	 * @return the seccion
	 */
	public String[] getSeccion() {
		return seccion;
	}
	/**
	 * @param seccion the seccion to set
	 */
	public void setSeccion(String[] seccion) {
		this.seccion = seccion;
	}
	/**
	 * @return the mensajes
	 */
	public String[] getMensajes() {
		return mensajes;
	}
	/**
	 * @param mensajes the mensajes to set
	 */
	public void setMensajes(String[] mensajes) {
		this.mensajes = mensajes;
	}
	/**
	 * @return the mensajesDisponibles
	 */
	public String[] getMensajesDisponibles() {
		return mensajesDisponibles;
	}
	/**
	 * @param mensajesDisponibles the mensajesDisponibles to set
	 */
	public void setMensajesDisponibles(String[] mensajesDisponibles) {
		this.mensajesDisponibles = mensajesDisponibles;
	}
	
	

}
