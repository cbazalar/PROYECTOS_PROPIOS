package biz.belcorp.ssicc.web.spusicc.cuentacorriente.form;

import java.io.Serializable;

import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

public class MantenimientoCCCFacturarInteresForm extends BaseProcesoForm  implements Serializable{
			 
	private static final long serialVersionUID = 1L;
	
	 private UploadedFile archivo;	//objeto que se utilizara para el upload del Archivo
	 private String directorioTemporal;  //directorio temporal del servidor donde se guardara el archivo
	 private String nombreArchivo;	//nombre del archivo a subirse al servidor
	 private String extensionArchivo;	//extension del archivo
		
	 private String numRegistros;
	 private String numRegistrosError;
	 private String numRegistrosValidos;
	    
	 private boolean flagBotonValidar;
	 private boolean flagBotonProcesar;
	 
	/**
	 * @return the archivo
	 */
	public UploadedFile getArchivo() {
		return archivo;
	}
	/**
	 * @param archivo the archivo to set
	 */
	public void setArchivo(UploadedFile archivo) {
		this.archivo = archivo;
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
	 * @return the nombreArchivo
	 */
	public String getNombreArchivo() {
		return nombreArchivo;
	}
	/**
	 * @param nombreArchivo the nombreArchivo to set
	 */
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
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
	/**
	 * @return the numRegistros
	 */
	public String getNumRegistros() {
		return numRegistros;
	}
	/**
	 * @param numRegistros the numRegistros to set
	 */
	public void setNumRegistros(String numRegistros) {
		this.numRegistros = numRegistros;
	}
	/**
	 * @return the numRegistrosError
	 */
	public String getNumRegistrosError() {
		return numRegistrosError;
	}
	/**
	 * @param numRegistrosError the numRegistrosError to set
	 */
	public void setNumRegistrosError(String numRegistrosError) {
		this.numRegistrosError = numRegistrosError;
	}
	/**
	 * @return the numRegistrosValidos
	 */
	public String getNumRegistrosValidos() {
		return numRegistrosValidos;
	}
	/**
	 * @param numRegistrosValidos the numRegistrosValidos to set
	 */
	public void setNumRegistrosValidos(String numRegistrosValidos) {
		this.numRegistrosValidos = numRegistrosValidos;
	}
	/**
	 * @return the flagBotonValidar
	 */
	public boolean isFlagBotonValidar() {
		return flagBotonValidar;
	}
	/**
	 * @param flagBotonValidar the flagBotonValidar to set
	 */
	public void setFlagBotonValidar(boolean flagBotonValidar) {
		this.flagBotonValidar = flagBotonValidar;
	}
	/**
	 * @return the flagBotonProcesar
	 */
	public boolean isFlagBotonProcesar() {
		return flagBotonProcesar;
	}
	/**
	 * @param flagBotonProcesar the flagBotonProcesar to set
	 */
	public void setFlagBotonProcesar(boolean flagBotonProcesar) {
		this.flagBotonProcesar = flagBotonProcesar;
	}	
}