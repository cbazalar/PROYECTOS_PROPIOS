package biz.belcorp.ssicc.web.spusicc.sto.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoSTOMensajeValidacionForm extends BaseEditForm{
	
	private static final long serialVersionUID = 4588433808396889484L;
	
	private String codigoPais;
	private String tipoDocumento;
	private String descripcionDocumento;
	private String codigoValidacion;
	private String descripcionValidacion;
	private String codigoMensaje;
	private String descripcionCortaMensaje;
	private String descripcionLargaMensaje;
	private String descripcionWebMensaje;
	private String indicadorTipoMensaje;
	private String codigoIdioma;
	
	
	
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais the codigoPais to set
	 * @struts.validator type="required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the tipoDocumento
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}

	/**
	 * @param tipoDocumento the tipoDocumento to set
	 * @struts.validator type="required"
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	/**
	 * @return the codigoValidacion
	 */
	public String getCodigoValidacion() {
		return codigoValidacion;
	}

	/**
	 * @param codigoValidacion the codigoValidacion to set
	 * @struts.validator type="required"
	 */
	public void setCodigoValidacion(String codigoValidacion) {
		this.codigoValidacion = codigoValidacion;
	}

	/**
	 * @return the descripcionValidacion
	 */
	public String getDescripcionValidacion() {
		return descripcionValidacion;
	}

	/**
	 * @param descripcionValidacion the descripcionValidacion to set
	 */
	public void setDescripcionValidacion(String descripcionValidacion) {
		this.descripcionValidacion = descripcionValidacion;
	}

	/**
	 * @return the descripcionDocumento
	 */
	public String getDescripcionDocumento() {
		return descripcionDocumento;
	}

	/**
	 * @param descripcionDocumento the descripcionDocumento to set
	 */
	public void setDescripcionDocumento(String descripcionDocumento) {
		this.descripcionDocumento = descripcionDocumento;
	}

	/**
	 * @return the codigoMensaje
	 */
	public String getCodigoMensaje() {
		return codigoMensaje;
	}

	/**
	 * @param codigoMensaje the codigoMensaje to set
	 * @struts.validator type="required"
	 */
	public void setCodigoMensaje(String codigoMensaje) {
		this.codigoMensaje = codigoMensaje;
	}

	/**
	 * @return the descripcionCortaMensaje
	 */
	public String getDescripcionCortaMensaje() {
		return descripcionCortaMensaje;
	}

	/**
	 * @param descripcionCortaMensaje the descripcionCortaMensaje to set
	 */
	public void setDescripcionCortaMensaje(String descripcionCortaMensaje) {
		this.descripcionCortaMensaje = descripcionCortaMensaje;
	}

	/**
	 * @return the descripcionLargaMensaje
	 */
	public String getDescripcionLargaMensaje() {
		return descripcionLargaMensaje;
	}

	/**
	 * @param descripcionLargaMensaje the descripcionLargaMensaje to set
	 */
	public void setDescripcionLargaMensaje(String descripcionLargaMensaje) {
		this.descripcionLargaMensaje = descripcionLargaMensaje;
	}

	/**
	 * @return the descripcionWebMensaje
	 */
	public String getDescripcionWebMensaje() {
		return descripcionWebMensaje;
	}

	/**
	 * @param descripcionWebMensaje the descripcionWebMensaje to set
	 */
	public void setDescripcionWebMensaje(String descripcionWebMensaje) {
		this.descripcionWebMensaje = descripcionWebMensaje;
	}

	/**
	 * @return the indicadorTipoMensaje
	 */
	public String getIndicadorTipoMensaje() {
		return indicadorTipoMensaje;
	}

	/**
	 * @param indicadorTipoMensaje the indicadorTipoMensaje to set
	 */
	public void setIndicadorTipoMensaje(String indicadorTipoMensaje) {
		this.indicadorTipoMensaje = indicadorTipoMensaje;
	}

	/**
	 * @return the codigoIdioma
	 */
	public String getCodigoIdioma() {
		return codigoIdioma;
	}

	/**
	 * @param codigoIdioma the codigoIdioma to set
	 */
	public void setCodigoIdioma(String codigoIdioma) {
		this.codigoIdioma = codigoIdioma;
	}
	
}
