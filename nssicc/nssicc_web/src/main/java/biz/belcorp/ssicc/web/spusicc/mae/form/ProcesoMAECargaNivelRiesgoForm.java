package biz.belcorp.ssicc.web.spusicc.mae.form;

import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

public class ProcesoMAECargaNivelRiesgoForm extends BaseProcesoForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6131590140456632294L;
	
	private UploadedFile archivo;	//objeto que se utilizara para el upload del Archivo
	private String directorioTemporal;  //directorio temporal del servidor donde se guardara el archivo
	private String nombreArchivo;	//nombre del archivo a subirse al servidor
	private String extensionArchivo;	//extension del archivo
	
	private String numRegistros;
	private String numRegistrosError;
	
	private String numRegistrosValido;
	private String numeroCarga;
	
	private boolean flagBotonValidar;
	private boolean flagBotonActualizar;
	public UploadedFile getArchivo() {
		return archivo;
	}
	public void setArchivo(UploadedFile archivo) {
		this.archivo = archivo;
	}
	public String getDirectorioTemporal() {
		return directorioTemporal;
	}
	public void setDirectorioTemporal(String directorioTemporal) {
		this.directorioTemporal = directorioTemporal;
	}
	public String getNombreArchivo() {
		return nombreArchivo;
	}
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	public String getExtensionArchivo() {
		return extensionArchivo;
	}
	public void setExtensionArchivo(String extensionArchivo) {
		this.extensionArchivo = extensionArchivo;
	}
	public String getNumRegistros() {
		return numRegistros;
	}
	public void setNumRegistros(String numRegistros) {
		this.numRegistros = numRegistros;
	}
	public String getNumRegistrosError() {
		return numRegistrosError;
	}
	public void setNumRegistrosError(String numRegistrosError) {
		this.numRegistrosError = numRegistrosError;
	}
	public String getNumRegistrosValido() {
		return numRegistrosValido;
	}
	public void setNumRegistrosValido(String numRegistrosValido) {
		this.numRegistrosValido = numRegistrosValido;
	}
	public String getNumeroCarga() {
		return numeroCarga;
	}
	public void setNumeroCarga(String numeroCarga) {
		this.numeroCarga = numeroCarga;
	}
	public boolean isFlagBotonValidar() {
		return flagBotonValidar;
	}
	public void setFlagBotonValidar(boolean flagBotonValidar) {
		this.flagBotonValidar = flagBotonValidar;
	}
	public boolean isFlagBotonActualizar() {
		return flagBotonActualizar;
	}
	public void setFlagBotonActualizar(boolean flagBotonActualizar) {
		this.flagBotonActualizar = flagBotonActualizar;
	}
	
	
	
}
