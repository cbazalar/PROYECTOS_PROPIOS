package biz.belcorp.ssicc.reportes.web.scsicc.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ReporteVENFacturaNotaCreditoVenezuelaForm extends BaseReporteForm implements Serializable{

	private static final long serialVersionUID = 7608544188031640888L;
	
	private String codigoPais;
	private String tipoReporte;	
	private String oidPais;	
	private String fechaInicio;
	private String fechaFin;
	private Date fechaInicioDate;	
	private Date fechaFinDate;
	
	
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
	/**
	 * @return the oidPais
	 */
	public String getOidPais() {
		return oidPais;
	}
	/**
	 * @param oidPais the oidPais to set
	 */
	public void setOidPais(String oidPais) {
		this.oidPais = oidPais;
	}
	/**
	 * @return the fechaInicio
	 */
	public String getFechaInicio() {
		return fechaInicio;
	}
	/**
	 * @param fechaInicio the fechaInicio to set
	 */
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	/**
	 * @return the fechaFin
	 */
	public String getFechaFin() {
		return fechaFin;
	}
	/**
	 * @param fechaFin the fechaFin to set
	 */
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	/**
	 * @return the fechaInicioDate
	 */
	public Date getFechaInicioDate() {
		return fechaInicioDate;
	}
	/**
	 * @param fechaInicioDate the fechaInicioDate to set
	 */
	public void setFechaInicioDate(Date fechaInicioDate) {
		this.fechaInicioDate = fechaInicioDate;
	}
	/**
	 * @return the fechaFinDate
	 */
	public Date getFechaFinDate() {
		return fechaFinDate;
	}
	/**
	 * @param fechaFinDate the fechaFinDate to set
	 */
	public void setFechaFinDate(Date fechaFinDate) {
		this.fechaFinDate = fechaFinDate;
	}

}
