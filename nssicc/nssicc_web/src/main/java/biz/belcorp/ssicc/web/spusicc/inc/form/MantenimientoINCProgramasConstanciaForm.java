package biz.belcorp.ssicc.web.spusicc.inc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoINCProgramasConstanciaForm extends BaseEditForm implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String codigoPais;
	private String codigo;
	private String campanyaInicio;
	private String campanyaFin;
	private String codigoMultiPunto;
	private String descripcionMultiPunto;
	private String montoMinimo;
	private String indicadorActivo;
	private boolean flagCampanyaProcesoMayor;
	
	// agregue
	private String evaluacionPrograma;
	private String campanyaMaxima;
	
	/* (non-Javadoc)
	 * @see org.apache.struts.validator.ValidatorForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
//	public void reset(ActionMapping mapping, HttpServletRequest request) {
//		this.codigo = null;
//		this.campanyaInicio = null;
//		this.campanyaFin = null;
//		this.codigoMultiPunto = null;
//		this.montoMinimo = null;
//		this.indicadorActivo = Constants.NUMERO_UNO;
//		this.descripcionMultiPunto = null;
//		this.flagCampanyaProcesoMayor = false;
//	}

	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais the codigoPais to set
	 * 
	 * @struts.validator type="required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 * 
	 * @struts.validator type="required"
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the campanyaInicio
	 */
	public String getCampanyaInicio() {
		return campanyaInicio;
	}

	/**
	 * @param campanyaInicio the campanyaInicio to set
	 * 
	 * @struts.validator type="required"
	 */
	public void setCampanyaInicio(String campanyaInicio) {
		this.campanyaInicio = campanyaInicio;
	}

	/**
	 * @return the campanyaFin
	 */
	public String getCampanyaFin() {
		return campanyaFin;
	}

	/**
	 * @param campanyaFin the campanyaFin to set
	 */
	public void setCampanyaFin(String campanyaFin) {
		this.campanyaFin = campanyaFin;
	}

	/**
	 * @return the codigoMultiPunto
	 */
	public String getCodigoMultiPunto() {
		return codigoMultiPunto;
	}

	/**
	 * @param codigoMultiPunto the codigoMultiPunto to set
	 * 
	 * @struts.validator type="required"
	 */
	public void setCodigoMultiPunto(String codigoMultiPunto) {
		this.codigoMultiPunto = codigoMultiPunto;
	}

	/**
	 * @return the montoMinimo
	 */
	public String getMontoMinimo() {
		return montoMinimo;
	}

	/**
	 * @param montoMinimo the montoMinimo to set
	 */
	public void setMontoMinimo(String montoMinimo) {
		this.montoMinimo = montoMinimo;
	}

	/**
	 * @return the indicadorActivo
	 */
	public String getIndicadorActivo() {
		return indicadorActivo;
	}

	/**
	 * @param indicadorActivo the indicadorActivo to set
	 * 
	 * @struts.validator type="required"
	 */
	public void setIndicadorActivo(String indicadorActivo) {
		this.indicadorActivo = indicadorActivo;
	}

	/**
	 * @return the descripcionMultiPunto
	 */
	public String getDescripcionMultiPunto() {
		return descripcionMultiPunto;
	}

	/**
	 * @param descripcionMultiPunto the descripcionMultiPunto to set
	 */
	public void setDescripcionMultiPunto(String descripcionMultiPunto) {
		this.descripcionMultiPunto = descripcionMultiPunto;
	}

	/**
	 * @return the flagCampanyaProcesoMayor
	 */
	public boolean isFlagCampanyaProcesoMayor() {
		return flagCampanyaProcesoMayor;
	}

	/**
	 * @param flagCampanyaProcesoMayor the flagCampanyaProcesoMayor to set
	 */
	public void setFlagCampanyaProcesoMayor(boolean flagCampanyaProcesoMayor) {
		this.flagCampanyaProcesoMayor = flagCampanyaProcesoMayor;
	}

	/**
	 * @return the verificacionPrograma
	 */
	public String getEvaluacionPrograma() {
		return evaluacionPrograma;
	}

	/**
	 * @param verificacionPrograma the verificacionPrograma to set
	 */
	public void setEvaluacionPrograma(String evaluacionPrograma) {
		this.evaluacionPrograma = evaluacionPrograma;
	}

	/**
	 * @return the campanyaMaximo
	 */
	public String getCampanyaMaxima() {
		return campanyaMaxima;
	}

	/**
	 * @param campanyaMaximo the campanyaMaximo to set
	 */
	public void setCampanyaMaxima(String campanyaMaxima) {
		this.campanyaMaxima = campanyaMaxima;
	}
}