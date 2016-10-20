package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * @author Jose Pulido
 * @company Sigcomt
 * 
 */
public class ReporteRECValidacionSolicitudesRecForm extends BaseReporteForm
		implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codigoPais;

	private String descripcionPais;

	private String fechaValidacion;

	private String fechaValidacionFinal;

	private Date fechaValidacionD;

	private Date fechaValidacionFinalD;

	private String codigoPeriodo;

	private String descripcionRegionList;

	private String descripcionZonaList;

	private String descripcionTerritorioList;

	private String descripcionUserList;

	private String descripcionOperacionList;

	private String[] userList;

	private String[] regionList;

	private String[] zonaList;

	private String[] territorioList;

	private String[] operacionList;

	private String tipoReporte;

	/**
	 * @return Returns the descripcionRegionList.
	 */
	public String getDescripcionRegionList() {
		return descripcionRegionList;
	}

	/**
	 * @param descripcionRegionList
	 *            The descripcionRegionList to set.
	 */
	public void setDescripcionRegionList(String descripcionRegionList) {
		String temp = StringUtils.replace(descripcionRegionList, "&&", "\n");
		this.descripcionRegionList = temp;
	}

	/**
	 * @return Returns the descripcionTerritorioList.
	 */
	public String getDescripcionTerritorioList() {
		return descripcionTerritorioList;
	}

	/**
	 * @param descripcionTerritorioList
	 *            The descripcionTerritorioList to set.
	 */
	public void setDescripcionTerritorioList(String descripcionTerritorioList) {
		String temp = StringUtils
				.replace(descripcionTerritorioList, "&&", "\n");
		this.descripcionTerritorioList = temp;
	}

	/**
	 * @return Returns the descripcionUserList.
	 */
	public String getDescripcionUserList() {
		return descripcionUserList;
	}

	/**
	 * @param descripcionUserList
	 *            The descripcionUserList to set.
	 */
	public void setDescripcionUserList(String descripcionUserList) {
		String temp = StringUtils.replace(descripcionUserList, "&&", "\n");
		this.descripcionUserList = temp;
	}

	/**
	 * @return Returns the descripcionZonaList.
	 */
	public String getDescripcionZonaList() {
		return descripcionZonaList;
	}

	/**
	 * @param descripcionZonaList
	 *            The descripcionZonaList to set.
	 */
	public void setDescripcionZonaList(String descripcionZonaList) {
		String temp = StringUtils.replace(descripcionZonaList, "&&", "\n");
		this.descripcionZonaList = temp;
	}

	/**
	 * @return Returns the regionList.
	 */
	public String[] getRegionList() {
		return regionList;
	}

	/**
	 * @param regionList
	 *            The regionList to set.
	 */
	public void setRegionList(String[] regionList) {
		this.regionList = regionList;
	}

	/**
	 * @return Returns the territorioList.
	 */
	public String[] getTerritorioList() {
		return territorioList;
	}

	/**
	 * @param territorioList
	 *            The territorioList to set.
	 */
	public void setTerritorioList(String[] territorioList) {
		this.territorioList = territorioList;
	}

	/**
	 * @return Returns the userList.
	 */
	public String[] getUserList() {
		return userList;
	}

	/**
	 * @param userList
	 *            The userList to set.
	 */
	public void setUserList(String[] userList) {
		this.userList = userList;
	}

	/**
	 * @return Returns the zonaList.
	 */
	public String[] getZonaList() {
		return zonaList;
	}

	/**
	 * @param zonaList
	 *            The zonaList to set.
	 */
	public void setZonaList(String[] zonaList) {
		this.zonaList = zonaList;
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
	 * @return Returns the codigoPeriodo.
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo
	 *            The codigoPeriodo to set.
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * 
	 * @return Returns the descripcionPais.
	 */
	public String getDescripcionPais() {
		return descripcionPais;
	}

	/**
	 * @param descripcionPais
	 *            The descripcionPais to set.
	 */
	public void setDescripcionPais(String descripcionPais) {
		this.descripcionPais = descripcionPais;
	}

	/**
	 * @return Returns the fechaValidacion.
	 */
	public String getFechaValidacion() {
		return fechaValidacion;
	}

	/**
	 * @param fechaValidacion
	 *            The fechaValidacion to set.
	 */
	public void setFechaValidacion(String fechaValidacion) {
		this.fechaValidacion = fechaValidacion;
	}

	/**
	 * @return Returns the fechaValidacionFinal.
	 */
	public String getFechaValidacionFinal() {
		return fechaValidacionFinal;
	}

	/*
	 * @param fechaValidacionFinal The fechaValidacionFinal to set.
	 */
	public void setFechaValidacionFinal(String fechaValidacionFinal) {
		this.fechaValidacionFinal = fechaValidacionFinal;
	}

	/**
	 * @return Returns the tipoReporte.
	 */
	public String getTipoReporte() {
		return tipoReporte;
	}

	/**
	 * @param tipoReporte
	 *            The tipoReporte to set.
	 */
	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}

	/**
	 * @return Returns the operacionList.
	 */
	public String[] getOperacionList() {
		return operacionList;
	}

	/**
	 * @param operacionList
	 *            The operacionList to set.
	 */
	public void setOperacionList(String[] operacionList) {
		this.operacionList = operacionList;
	}

	/**
	 * @return Returns the descripcionOperacionList.
	 */
	public String getDescripcionOperacionList() {
		return descripcionOperacionList;
	}

	/**
	 * @param descripcionOperacionList
	 *            The descripcionOperacionList to set.
	 */
	public void setDescripcionOperacionList(String descripcionOperacionList) {
		this.descripcionOperacionList = descripcionOperacionList;
	}

	/**
	 * @return the fechaValidacionD
	 */
	public Date getFechaValidacionD() {
		return fechaValidacionD;
	}

	/**
	 * @param fechaValidacionD
	 *            the fechaValidacionD to set
	 */
	public void setFechaValidacionD(Date fechaValidacionD) {
		this.fechaValidacionD = fechaValidacionD;
	}

	/**
	 * @return the fechaValidacionFinalD
	 */
	public Date getFechaValidacionFinalD() {
		return fechaValidacionFinalD;
	}

	/**
	 * @param fechaValidacionFinalD
	 *            the fechaValidacionFinalD to set
	 */
	public void setFechaValidacionFinalD(Date fechaValidacionFinalD) {
		this.fechaValidacionFinalD = fechaValidacionFinalD;
	}
}