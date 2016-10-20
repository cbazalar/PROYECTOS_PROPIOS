package biz.belcorp.ssicc.web.scdf.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class ProductoForm extends BaseEditForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected String codigoPais;

	protected String codigo;

	protected String descripcion;

	protected String statusTransferencia;

	protected boolean indicadorGeneracionStickers;

	protected String puntajeSticker;

	protected String estado;

	/** Default empty constructor. */
	public ProductoForm() {
	}

	/**
	 * Getter for property codigo.
	 * 
	 * @return Value of property codigo.
	 */
	public String getCodigo() {
		return this.codigo;
	}

	/**
	 * Setter for property codigo.
	 * 
	 * @param codigo
	 *            New value of property codigo.
	 * @struts.validator type="required"
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * Getter for property descripcion.
	 * 
	 * @return Value of property descripcion.
	 */
	public String getDescripcion() {
		return this.descripcion;
	}

	/**
	 * Setter for property descripcion.
	 * 
	 * @param descripcion
	 *            New value of property descripcion.
	 * @struts.validator type="required"
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Getter for property estado.
	 * 
	 * @return Value of property estado.
	 */
	public String getEstado() {
		return this.estado;
	}

	/**
	 * Setter for property estado.
	 * 
	 * @param estado
	 *            New value of property estado.
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 *            The codigoPais to set.
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return Returns the indicadorGeneracionStickers.
	 */
	public boolean getIndicadorGeneracionStickers() {
		return indicadorGeneracionStickers;
	}

	/**
	 * @param indicadorGeneracionStickers
	 *            The indicadorGeneracionStickers to set.
	 */
	public void setIndicadorGeneracionStickers(
			boolean indicadorGeneracionStickers) {
		this.indicadorGeneracionStickers = indicadorGeneracionStickers;
	}

	/**
	 * @return Returns the statusTransferencia.
	 */
	public String getStatusTransferencia() {
		return statusTransferencia;
	}

	/**
	 * @param statusTransferencia
	 *            The statusTransferencia to set.
	 */
	public void setStatusTransferencia(String statusTransferencia) {
		this.statusTransferencia = statusTransferencia;
	}

	/**
	 * @return Returns the puntajeSticker.
	 */
	public String getPuntajeSticker() {
		return puntajeSticker;
	}

	/**
	 * @param puntajeSticker
	 *            The puntajeSticker to set.
	 * @struts.validator type="required"
	 * @struts.validator type="integer"
	 */
	public void setPuntajeSticker(String puntajeSticker) {
		this.puntajeSticker = puntajeSticker;
	}

}
