package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * 
 * @author RRG
 * 
 */
public class ReporteSGRSolicitudesRechazadasForm extends BaseReporteForm	implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8525573518407132227L;

	private String codigoPais;
	private String codigoRegion;
	private String codigoZona;
	private String codigoSeccion;
	private String fechaInicio;
	private String fechaFinal;
	private Date fechaInicioD;
	private Date fechaFinalD;
	private String codigoPeriodoInicio;
	private String codigoPeriodoFin;
	private String descripcionRegion;
	private String descripcionZona;

	public ReporteSGRSolicitudesRechazadasForm() {
		this.fechaInicio = this.fechaFinal = null;
		this.codigoPeriodoInicio = this.codigoPeriodoFin = null;
	}

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
	 * @return the codigoRegion
	 */
	public String getCodigoRegion() {
		return codigoRegion;
	}

	/**
	 * @param codigoRegion the codigoRegion to set
	 */
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}

	/**
	 * @return the codigoZona
	 */
	public String getCodigoZona() {
		return codigoZona;
	}

	/**
	 * @param codigoZona the codigoZona to set
	 */
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}

	/**
	 * @return the codigoSeccion
	 */
	public String getCodigoSeccion() {
		return codigoSeccion;
	}

	/**
	 * @param codigoSeccion the codigoSeccion to set
	 */
	public void setCodigoSeccion(String codigoSeccion) {
		this.codigoSeccion = codigoSeccion;
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
	 * @return the fechaFinal
	 */
	public String getFechaFinal() {
		return fechaFinal;
	}

	/**
	 * @param fechaFinal the fechaFinal to set
	 */
	public void setFechaFinal(String fechaFinal) {
		this.fechaFinal = fechaFinal;
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
	 * @return the fechaInicioD
	 */
	public Date getFechaInicioD() {
		return fechaInicioD;
	}

	/**
	 * @param fechaInicioD the fechaInicioD to set
	 */
	public void setFechaInicioD(Date fechaInicioD) {
		this.fechaInicioD = fechaInicioD;
	}

	/**
	 * @return the fechaFinalD
	 */
	public Date getFechaFinalD() {
		return fechaFinalD;
	}

	/**
	 * @param fechaFinalD the fechaFinalD to set
	 */
	public void setFechaFinalD(Date fechaFinalD) {
		this.fechaFinalD = fechaFinalD;
	}
	
	
	
	
}

