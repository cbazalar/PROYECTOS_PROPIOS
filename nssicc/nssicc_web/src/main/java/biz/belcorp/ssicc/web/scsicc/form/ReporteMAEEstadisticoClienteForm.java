package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * 
 * @author
 * 
 */
public class ReporteMAEEstadisticoClienteForm extends BaseReporteForm implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7636252984301000103L;
	private String codigoPais;
	private String codigoPeriodo;
	private String[] region;
	private String[] zona;
	private String[] seccion;
	private String[] territorio;
	private String descripcionRegion;
	private String descripcionZona;
	private String descripcionSeccion;
	private String descripcionTerritorio;
	private String tipoReporte;
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
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}
	/**
	 * @param codigoPeriodo the codigoPeriodo to set
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	/**
	 * @return the region
	 */
	public String[] getRegion() {
		return region;
	}
	/**
	 * @param region the region to set
	 */
	public void setRegion(String[] region) {
		this.region = region;
	}
	/**
	 * @return the zona
	 */
	public String[] getZona() {
		return zona;
	}
	/**
	 * @param zona the zona to set
	 */
	public void setZona(String[] zona) {
		this.zona = zona;
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
	 * @return the territorio
	 */
	public String[] getTerritorio() {
		return territorio;
	}
	/**
	 * @param territorio the territorio to set
	 */
	public void setTerritorio(String[] territorio) {
		this.territorio = territorio;
	}
	/**
	 * @return the descripcionRegion
	 */
	public String getDescripcionRegion() {
		return descripcionRegion;
	}
	/**
	 * @param descripcionRegion the descripcionRegion to set
	 */
	public void setDescripcionRegion(String descripcionRegion) {
		this.descripcionRegion = descripcionRegion;
	}
	/**
	 * @return the descripcionZona
	 */
	public String getDescripcionZona() {
		return descripcionZona;
	}
	/**
	 * @param descripcionZona the descripcionZona to set
	 */
	public void setDescripcionZona(String descripcionZona) {
		this.descripcionZona = descripcionZona;
	}
	/**
	 * @return the descripcionSeccion
	 */
	public String getDescripcionSeccion() {
		return descripcionSeccion;
	}
	/**
	 * @param descripcionSeccion the descripcionSeccion to set
	 */
	public void setDescripcionSeccion(String descripcionSeccion) {
		this.descripcionSeccion = descripcionSeccion;
	}
	/**
	 * @return the descripcionTerritorio
	 */
	public String getDescripcionTerritorio() {
		return descripcionTerritorio;
	}
	/**
	 * @param descripcionTerritorio the descripcionTerritorio to set
	 */
	public void setDescripcionTerritorio(String descripcionTerritorio) {
		this.descripcionTerritorio = descripcionTerritorio;
	}
	/**
	 * @return the tipoReporte
	 */
	public String getTipoReporte() {
		return tipoReporte;
	}
	/**
	 * @param tipoReporte the tipoReporte to set
	 */
	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}
	
	

}
