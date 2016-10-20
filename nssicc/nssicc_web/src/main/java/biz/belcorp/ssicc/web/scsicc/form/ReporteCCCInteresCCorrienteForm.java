package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;
import java.util.Date;

import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ReporteCCCInteresCCorrienteForm extends BaseReporteForm implements Serializable {

	private static final long serialVersionUID = -639435424095546733L;
	
	private String codigoPais;
	private String tipoReporteInteres;
	private String fechaInicio;
	private String fechaFin;
	private Date fechaInicioDate;
	private Date fechaFinDate;
	private String []regiones;
	private String [] zonas;
	private String [] codClientes;
	private UploadedFile clienteFile; 
	private String directorioTemporal;
	
	
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
	 * @return the tipoReporteInteres
	 */
	public String getTipoReporteInteres() {
		return tipoReporteInteres;
	}
	/**
	 * @param tipoReporteInteres the tipoReporteInteres to set
	 */
	public void setTipoReporteInteres(String tipoReporteInteres) {
		this.tipoReporteInteres = tipoReporteInteres;
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
	 * @return the clienteFile
	 */
	public UploadedFile getClienteFile() {
		return clienteFile;
	}
	/**
	 * @param clienteFile the clienteFile to set
	 */
	public void setClienteFile(UploadedFile clienteFile) {
		this.clienteFile = clienteFile;
	}
	/**
	 * @return the directorioTemporal
	 */
	public String getDirectorioTemporal() {
		return directorioTemporal;
	}
	/**
	 * @param directorioTemporal the directorioTemporal to set
	 */
	public void setDirectorioTemporal(String directorioTemporal) {
		this.directorioTemporal = directorioTemporal;
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
	/**
	 * @return the codClientes
	 */
	public String[] getCodClientes() {
		return codClientes;
	}
	/**
	 * @param codClientes the codClientes to set
	 */
	public void setCodClientes(String[] codClientes) {
		this.codClientes = codClientes;
	}
	
	

}
