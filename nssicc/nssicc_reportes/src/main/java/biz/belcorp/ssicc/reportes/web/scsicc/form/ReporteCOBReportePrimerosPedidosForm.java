package biz.belcorp.ssicc.reportes.web.scsicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * 
 * @author RRG
 * 
 */

public class ReporteCOBReportePrimerosPedidosForm extends BaseReporteForm
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1018627156836839812L;
	private String codigoPais;
	private String codigoPeriodoInicio;
	private String codigoPeriodoFin;
	private String[] codigoRegion;
	private String[] codigoZona;
	private String[] codigoSeccion;
	private String descripcionRegion;
	private String descripcionZona;
	private String descripcionSeccion;
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
	 * @return the codigoPeriodoInicio
	 */
	public String getCodigoPeriodoInicio() {
		return codigoPeriodoInicio;
	}
	/**
	 * @param codigoPeriodoInicio the codigoPeriodoInicio to set
	 */
	public void setCodigoPeriodoInicio(String codigoPeriodoInicio) {
		this.codigoPeriodoInicio = codigoPeriodoInicio;
	}
	/**
	 * @return the codigoPeriodoFin
	 */
	public String getCodigoPeriodoFin() {
		return codigoPeriodoFin;
	}
	/**
	 * @param codigoPeriodoFin the codigoPeriodoFin to set
	 */
	public void setCodigoPeriodoFin(String codigoPeriodoFin) {
		this.codigoPeriodoFin = codigoPeriodoFin;
	}
	/**
	 * @return the codigoRegion
	 */
	public String[] getCodigoRegion() {
		return codigoRegion;
	}
	/**
	 * @param codigoRegion the codigoRegion to set
	 */
	public void setCodigoRegion(String[] codigoRegion) {
		this.codigoRegion = codigoRegion;
	}
	/**
	 * @return the codigoZona
	 */
	public String[] getCodigoZona() {
		return codigoZona;
	}
	/**
	 * @param codigoZona the codigoZona to set
	 */
	public void setCodigoZona(String[] codigoZona) {
		this.codigoZona = codigoZona;
	}
	/**
	 * @return the codigoSeccion
	 */
	public String[] getCodigoSeccion() {
		return codigoSeccion;
	}
	/**
	 * @param codigoSeccion the codigoSeccion to set
	 */
	public void setCodigoSeccion(String[] codigoSeccion) {
		this.codigoSeccion = codigoSeccion;
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

	
}
