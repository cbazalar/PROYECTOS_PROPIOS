package biz.belcorp.ssicc.web.spusicc.men.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;



/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="MantenimientoMENConfiguracionProcesosSearchForm.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli</a>
 * 
 */

public class MantenimientoMENConfiguracionProcesosSearchForm extends BaseSearchForm{
	
	private static final long serialVersionUID = 1L;

	private String codigoPais;
	private String codigoPlantilla;
	private String codigoProceso;
	private String observaciones;
	private String indicadorActivo;
	private String tipoProceso;
	private String ordenEjecucion;

	/**
	 * @return the codigoProceso
	 */
	public String getCodigoProceso() {
		return codigoProceso;
	}

	/**
	 * @param codigoProceso the codigoProceso to set
	 */
	public void setCodigoProceso(String codigoProceso) {
		this.codigoProceso = codigoProceso;
	}

	/**
	 * @return the observaciones
	 */
	public String getObservaciones() {
		return observaciones;
	}

	/**
	 * @param observaciones the observaciones to set
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	/**
	 * @return the indicadorActivo
	 */
	public String getIndicadorActivo() {
		return indicadorActivo;
	}

	/**
	 * @param indicadorActivo the indicadorActivo to set
	 */
	public void setIndicadorActivo(String indicadorActivo) {
		this.indicadorActivo = indicadorActivo;
	}

	/**
	 * @return the tipoProceso
	 */
	public String getTipoProceso() {
		return tipoProceso;
	}

	/**
	 * @param tipoProceso the tipoProceso to set
	 */
	public void setTipoProceso(String tipoProceso) {
		this.tipoProceso = tipoProceso;
	}

	/**
	 * @return the ordenEjecucion
	 */
	public String getOrdenEjecucion() {
		return ordenEjecucion;
	}

	/**
	 * @param ordenEjecucion the ordenEjecucion to set
	 */
	public void setOrdenEjecucion(String ordenEjecucion) {
		this.ordenEjecucion = ordenEjecucion;
	}

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
	 * @return the codigoPlantilla
	 */
	public String getCodigoPlantilla() {
		return codigoPlantilla;
	}



	/**
	 * @param codigoPlantilla the codigoPlantilla to set
	 * 
	 */
	public void setCodigoPlantilla(String codigoPlantilla) {
		this.codigoPlantilla = codigoPlantilla;
	}


	/* (non-Javadoc)
	 * @see org.apache.struts.validator.ValidatorForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	/*public void reset(ActionMapping mapping, HttpServletRequest request) {
		codigoPlantilla=observaciones=this.ordenEjecucion=codigoProceso="";
		this.indicadorActivo = "";
		this.tipoProceso="";
	}*/
	
	
}
