/*
 * 
 */
package biz.belcorp.ssicc.web.scsicc.form;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

// TODO: Auto-generated Javadoc
/**
 * The Class ReporteSTOEnvioNotificacionUsuariosForm.
 *
 * @author Belcorp
 * @version 1.0
 * 12/11/2014
 */

public class ReporteSTOEnvioNotificacionUsuariosForm extends BaseReporteForm {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The codigo pais. */
	private String codigoPais;

	/** The codigo periodo. */
	private String codigoPeriodo;

	/** The region list. */
	private String[] regionList;

	/** The zona list. */
	private String[] zonaList;

	/** The territorio list. */
	private String[] territorioList;

	/** The seccion list. */
	private String[] seccionList;

	/** The cod status. */
	private String codStatus;

	/** The descripcion region list. */
	private String descripcionRegionList;

	/** The descripcion zona list. */
	private String descripcionZonaList;

	/** The descripcion seccion list. */
	private String descripcionSeccionList;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.struts.validator.ValidatorForm#reset(org.apache.struts.action
	 * .ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	/**
	 * Reset.
	 *
	 * @param mapping the mapping
	 * @param request the request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String periodo = sdf.format(new Date(System.currentTimeMillis()));
		this.codigoPeriodo = (String) request.getSession().getAttribute(
				"periodoActual");
	}

	/**
	 * Gets the codigo pais.
	 *
	 * @return codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * Sets the codigo pais.
	 *
	 * @param codigoPais The codigoPais to set.
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * Gets the codigo periodo.
	 *
	 * @return codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * Sets the codigo periodo.
	 *
	 * @param codigoPeriodo The codigoPeriodo to set.
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * Gets the region list.
	 *
	 * @return regionList
	 */
	public String[] getRegionList() {
		return regionList;
	}

	/**
	 * Sets the region list.
	 *
	 * @param regionList the new region list
	 */
	public void setRegionList(String[] regionList) {
		this.regionList = regionList;
	}

	/**
	 * Gets the zona list.
	 *
	 * @return zonaList
	 */
	public String[] getZonaList() {
		return zonaList;
	}

	/**
	 * Sets the zona list.
	 *
	 * @param zonaList the new zona list
	 */
	public void setZonaList(String[] zonaList) {
		this.zonaList = zonaList;
	}

	/**
	 * Gets the territorio list.
	 *
	 * @return territorioList
	 */
	public String[] getTerritorioList() {
		return territorioList;
	}

	/**
	 * Sets the territorio list.
	 *
	 * @param territorioList the new territorio list
	 */
	public void setTerritorioList(String[] territorioList) {
		this.territorioList = territorioList;
	}

	/**
	 * Gets the seccion list.
	 *
	 * @return seccionList
	 */
	public String[] getSeccionList() {
		return seccionList;
	}

	/**
	 * Sets the seccion list.
	 *
	 * @param seccionList the new seccion list
	 */
	public void setSeccionList(String[] seccionList) {
		this.seccionList = seccionList;
	}

	/**
	 * Gets the cod status.
	 *
	 * @return codStatus
	 */
	public String getCodStatus() {
		return codStatus;
	}

	/**
	 * Sets the cod status.
	 *
	 * @param codStatus the new cod status
	 */
	public void setCodStatus(String codStatus) {
		this.codStatus = codStatus;
	}

	/**
	 * Gets the descripcion region list.
	 *
	 * @return descripcionRegionList
	 */
	public String getDescripcionRegionList() {
		return descripcionRegionList;
	}

	/**
	 * Sets the descripcion region list.
	 *
	 * @param descripcionRegionList the new descripcion region list
	 */
	public void setDescripcionRegionList(String descripcionRegionList) {
		this.descripcionRegionList = descripcionRegionList;
	}

	/**
	 * Gets the descripcion zona list.
	 *
	 * @return descripcionZonaList
	 */
	public String getDescripcionZonaList() {
		return descripcionZonaList;
	}

	/**
	 * Sets the descripcion zona list.
	 *
	 * @param descripcionZonaList the new descripcion zona list
	 */
	public void setDescripcionZonaList(String descripcionZonaList) {
		this.descripcionZonaList = descripcionZonaList;
	}

	/**
	 * Gets the descripcion seccion list.
	 *
	 * @return descripcionSeccionList
	 */
	public String getDescripcionSeccionList() {
		return descripcionSeccionList;
	}

	/**
	 * Sets the descripcion seccion list.
	 *
	 * @param descripcionSeccionList the new descripcion seccion list
	 */
	public void setDescripcionSeccionList(String descripcionSeccionList) {
		this.descripcionSeccionList = descripcionSeccionList;
	}

}
