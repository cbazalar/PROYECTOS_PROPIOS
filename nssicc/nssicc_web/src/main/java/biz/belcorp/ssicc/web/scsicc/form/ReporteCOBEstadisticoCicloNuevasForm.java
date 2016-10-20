package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ReporteCOBEstadisticoCicloNuevasForm extends BaseReporteForm implements Serializable {
	
	private static final long serialVersionUID = -5378272206047809162L;
	
	
	private String codigoPais;
	private String codCicloNueva;
	private String tipoVista;
	private String periodoInicio;
	private String periodoFin;
	private String [] regiones;
	private String [] zonas;
	
	
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
	 * @return the codCicloNueva
	 */
	public String getCodCicloNueva() {
		return codCicloNueva;
	}
	/**
	 * @param codCicloNueva the codCicloNueva to set
	 */
	public void setCodCicloNueva(String codCicloNueva) {
		this.codCicloNueva = codCicloNueva;
	}
	/**
	 * @return the tipoVista
	 */
	public String getTipoVista() {
		return tipoVista;
	}
	/**
	 * @param tipoVista the tipoVista to set
	 */
	public void setTipoVista(String tipoVista) {
		this.tipoVista = tipoVista;
	}
	/**
	 * @return the periodoInicio
	 */
	public String getPeriodoInicio() {
		return periodoInicio;
	}
	/**
	 * @param periodoInicio the periodoInicio to set
	 */
	public void setPeriodoInicio(String periodoInicio) {
		this.periodoInicio = periodoInicio;
	}
	/**
	 * @return the periodoFin
	 */
	public String getPeriodoFin() {
		return periodoFin;
	}
	/**
	 * @param periodoFin the periodoFin to set
	 */
	public void setPeriodoFin(String periodoFin) {
		this.periodoFin = periodoFin;
	}
	/**
	 * @return the regiones
	 */
	public String[] getRegiones() {
		return regiones;
	}
	/**
	 * @param regiones the regiones to set
	 */
	public void setRegiones(String[] regiones) {
		this.regiones = regiones;
	}
	/**
	 * @return the zonas
	 */
	public String[] getZonas() {
		return zonas;
	}
	/**
	 * @param zonas the zonas to set
	 */
	public void setZonas(String[] zonas) {
		this.zonas = zonas;
	}	

}
