package biz.belcorp.ssicc.web.spusicc.men.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoMENPatronMensajeConsultaForm extends BaseEditForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6311593843125830682L;
	private String codigoPais;
	private String campanha;
	private String codigoDocumento;
	//
	private String descripcionMensaje;
	private String textoMensaje;
	private String textoMensajeHtml;
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
	 * @return the descripcionMensaje
	 */
	public String getDescripcionMensaje() {
		return descripcionMensaje;
	}
	/**
	 * @param descripcionMensaje the descripcionMensaje to set
	 */
	public void setDescripcionMensaje(String descripcionMensaje) {
		this.descripcionMensaje = descripcionMensaje;
	}
	/**
	 * @return the textoMensaje
	 */
	public String getTextoMensaje() {
		return textoMensaje;
	}
	/**
	 * @param textoMensaje the textoMensaje to set
	 */
	public void setTextoMensaje(String textoMensaje) {
		this.textoMensaje = textoMensaje;
	}
	/**
	 * @return the textoMensajeHtml
	 */
	public String getTextoMensajeHtml() {
		return textoMensajeHtml;
	}
	/**
	 * @param textoMensajeHtml the textoMensajeHtml to set
	 */
	public void setTextoMensajeHtml(String textoMensajeHtml) {
		this.textoMensajeHtml = textoMensajeHtml;
	}
	
	

}
