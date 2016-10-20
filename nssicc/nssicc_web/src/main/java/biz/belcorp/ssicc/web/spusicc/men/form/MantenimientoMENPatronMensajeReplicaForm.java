package biz.belcorp.ssicc.web.spusicc.men.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoMENPatronMensajeReplicaForm extends BaseEditForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5230437732302434117L;
	private String codigoPais;
	private String campanhaOrigen;
	private String campanhaDestino;
	private String codigoDocumento;
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
	 * @return the campanhaOrigen
	 */
	public String getCampanhaOrigen() {
		return campanhaOrigen;
	}
	/**
	 * @param campanhaOrigen the campanhaOrigen to set
	 */
	public void setCampanhaOrigen(String campanhaOrigen) {
		this.campanhaOrigen = campanhaOrigen;
	}
	/**
	 * @return the campanhaDestino
	 */
	public String getCampanhaDestino() {
		return campanhaDestino;
	}
	/**
	 * @param campanhaDestino the campanhaDestino to set
	 */
	public void setCampanhaDestino(String campanhaDestino) {
		this.campanhaDestino = campanhaDestino;
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
	
	

}
