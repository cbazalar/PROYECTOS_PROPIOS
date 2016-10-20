package biz.belcorp.ssicc.web.spusicc.let.form;

import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

public class ProcesoLETCargaObjetivosExcelForm extends BaseProcesoForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1329475508305874416L;
	private UploadedFile archivo;	//objeto que se utilizara para el upload del Archivo
	private String directorioTemporal;  //directorio temporal del servidor donde se guardara el archivo
	private String nombreArchivo;	//nombre del archivo a subirse al servidor
	private String extensionArchivo;	//extension del archivo
	
	private boolean flagMostrarErrores;
	private boolean flagUpload;
	private boolean flagUploadOk;
	
	private String codigoMarca;
	private String codigoCanal;
	private String codigoPeriodo;
	private String codigoRegion[];
	private String tipoCarga;
	//private String indicadorValidaLiderSeccion;
	

	/**
	 * @return
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}


	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return
	 */
	public String getCodigoMarca() {
		return codigoMarca;
	}


	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}

	/**
	 * @return
	 */
	public String getCodigoCanal() {
		return codigoCanal;
	}

	/**
	 * @struts.validator type = "required"
	 * @param codigoCanal
	 */
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}

	/**
	 * @return
	 */
	public String[] getCodigoRegion() {
		return codigoRegion;
	}

	/**
	 * @param codigoRegion
	 */
	public void setCodigoRegion(String[] codigoRegion) {
		this.codigoRegion = codigoRegion;
	}

	/**
	 * @return
	 */
	public boolean isFlagUploadOk() {
		return flagUploadOk;
	}

	/**
	 * @param flagUploadOk
	 */
	public void setFlagUploadOk(boolean flagUploadOk) {
		this.flagUploadOk = flagUploadOk;
	}

	/**
	 * @return
	 */
	public boolean isFlagUpload() {
		return flagUpload;
	}

	/**
	 * @param flagUpload
	 */
	public void setFlagUpload(boolean flagUpload) {
		this.flagUpload = flagUpload;
	}

	/**
	 * @return
	 */
	public boolean isFlagMostrarErrores() {
		return flagMostrarErrores;
	}

	/**
	 * @param flagMostrarErrores
	 */
	public void setFlagMostrarErrores(boolean flagMostrarErrores) {
		this.flagMostrarErrores = flagMostrarErrores;
	}

	/**
	 * @return
	 */
	public UploadedFile getArchivo() {
		return archivo;
	}
	
	/**
	 * @param archivo
	 * @struts.validator type = "required"
	 */
	public void setArchivo(UploadedFile archivo) {
		this.archivo = archivo;
	}
	
	/**
	 * @return
	 */
	public String getDirectorioTemporal() {
		return directorioTemporal;
	}
	
	/**
	 * @param directorioTemporal
	 */
	public void setDirectorioTemporal(String directorioTemporal) {
		this.directorioTemporal = directorioTemporal;
	}
	
	/**
	 * @return
	 */
	public String getNombreArchivo() {
		return nombreArchivo;
	}
	
	/**
	 * @param nombreArchivo
	 */
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	
	/**
	 * @return
	 */
	public String getExtensionArchivo() {
		return extensionArchivo;
	}
	
	/**
	 * @param extensionArchivo
	 */
	public void setExtensionArchivo(String extensionArchivo) {
		this.extensionArchivo = extensionArchivo;
	}

	/**
	 * @return the tipoCarga
	 */
	public String getTipoCarga() {
		return tipoCarga;
	}

	/**
	 * @param tipoCarga the tipoCarga to set
	 */
	public void setTipoCarga(String tipoCarga) {
		this.tipoCarga = tipoCarga;
	}
}
