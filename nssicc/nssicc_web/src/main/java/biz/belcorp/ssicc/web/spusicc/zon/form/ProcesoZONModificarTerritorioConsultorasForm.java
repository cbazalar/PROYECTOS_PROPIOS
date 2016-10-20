package biz.belcorp.ssicc.web.spusicc.zon.form;

import org.apache.struts.upload.FormFile;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;


public class ProcesoZONModificarTerritorioConsultorasForm extends BaseProcesoForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6620747518430162915L;

	private String codigoMarca;
	private String codigoCanal;
	private String periodoCorporativo;
	private String[] codigosRegion;
	
	private UploadedFile archivo;	//objeto que se utilizara para el upload del Archivo
	private String directorioTemporal;  //directorio temporal del servidor donde se guardara el archivo
	private String nombreArchivo;	//nombre del archivo a subirse al servidor
	private String extensionArchivo;	//extension del archivo
	
	private String codigoUsuario;
	
	private Integer totalRegistros;
	private Integer totalError;
	private Integer totalOK;
	
	/**
	 * ejecutar Reporte luego de la ejecución del proceso
	 */
	private String ejecutaReporte;  
	
	/**
	 * Para dar formato al Reporte.
	 */
	private String formatoExportacion;
	

	public String getCodigoMarca() {
		return codigoMarca;
	}
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}
	public String getCodigoCanal() {
		return codigoCanal;
	}
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}
	public String getPeriodoCorporativo() {
		return periodoCorporativo;
	}
	public void setPeriodoCorporativo(String periodoCorporativo) {
		this.periodoCorporativo = periodoCorporativo;
	}
	public String[] getCodigosRegion() {
		return codigosRegion;
	}
	/**
	 */
	public void setCodigosRegion(String[] codigoRegion) {
		this.codigosRegion = codigoRegion;
	}

	public UploadedFile getArchivo() {
		return archivo;
	}
	public void setArchivo(UploadedFile archivo) {
		this.archivo = archivo;
	}
	public String getNombreArchivo() {
		return nombreArchivo;
	}
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	public String getDirectorioTemporal() {
		return directorioTemporal;
	}
	public void setDirectorioTemporal(String directorioTemporal) {
		this.directorioTemporal = directorioTemporal;
	}
	/**
	 * @return the extensionArchivo
	 */
	public String getExtensionArchivo() {
		return extensionArchivo;
	}
	/**
	 * @param extensionArchivo the extensionArchivo to set
	 */
	public void setExtensionArchivo(String extensionArchivo) {
		this.extensionArchivo = extensionArchivo;
	}
	
	public String getEjecutaReporte() {
		return ejecutaReporte;
	}
	public void setEjecutaReporte(String ejecutaReporte) {
		this.ejecutaReporte = ejecutaReporte;
	}
	public String getFormatoExportacion() {
		return formatoExportacion;
	}
	public void setFormatoExportacion(String formatoExportacion) {
		this.formatoExportacion = formatoExportacion;
	}
	
	public String getCodigoUsuario() {
		return codigoUsuario;
	}
	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}
	public Integer getTotalRegistros() {
		return totalRegistros;
	}
	public void setTotalRegistros(Integer totalRegistros) {
		this.totalRegistros = totalRegistros;
	}
	public Integer getTotalError() {
		return totalError;
	}
	public void setTotalError(Integer totalError) {
		this.totalError = totalError;
	}
	public Integer getTotalOK() {
		return totalOK;
	}
	public void setTotalOK(Integer totalOK) {
		this.totalOK = totalOK;
	}
	
	
}
