package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

/**
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar</a>
 * 
 */
public class ConsultaBASProcesoBatchActuaSearchForm extends BaseSearchForm implements Serializable {

	private String codigoPais;

	private String codigoSistema;

	private String codigoProcesoBatch;
	
	private String codigoProcesoBatchAnterior;
	
	private String log;
	
	private String descEstadoProceso;
	
	private String recomendacion;

	
	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 *            The codigoPais to set.
	 * 
	 * @struts.validator type = "required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return Returns the codigoSistema.
	 */
	public String getCodigoSistema() {
		return codigoSistema;
	}

	/**
	 * @param codigoSistema
	 *            The codigoSistema to set.
	 */
	public void setCodigoSistema(String codigoSistema) {
		this.codigoSistema = codigoSistema;
	}

	/**
	 * @return Returns the codigoProcesoBatch.
	 */
	public String getCodigoProcesoBatch() {
		return codigoProcesoBatch;
	}

	/**
	 * @param codigoProcesoBatch The codigoProcesoBatch to set.
	 */
	public void setCodigoProcesoBatch(String codigoProcesoBatch) {
		this.codigoProcesoBatch = codigoProcesoBatch;
	}

	/**
	 * @return Returns the descEstadoProceso.
	 */
	public String getDescEstadoProceso() {
		return descEstadoProceso;
	}

	/**
	 * @param descEstadoProceso The descEstadoProceso to set.
	 */
	public void setDescEstadoProceso(String descEstadoProceso) {
		this.descEstadoProceso = descEstadoProceso;
	}

	/**
	 * @return Returns the log.
	 */
	public String getLog() {
		return log;
	}

	/**
	 * @param log The log to set.
	 */
	public void setLog(String log) {
		this.log = log;
	}

	/**
	 * @return the recomendacion
	 */
	public String getRecomendacion() {
		return recomendacion;
	}

	/**
	 * @param recomendacion the recomendacion to set
	 */
	public void setRecomendacion(String recomendacion) {
		this.recomendacion = recomendacion;
	}
	
	

	/**
	 * @return the codigoProcesoBatchAnterior
	 */
	public String getCodigoProcesoBatchAnterior() {
		return codigoProcesoBatchAnterior;
	}

	/**
	 * @param codigoProcesoBatchAnterior the codigoProcesoBatchAnterior to set
	 */
	public void setCodigoProcesoBatchAnterior(String codigoProcesoBatchAnterior) {
		this.codigoProcesoBatchAnterior = codigoProcesoBatchAnterior;
	}

}
