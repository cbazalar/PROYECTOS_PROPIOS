package biz.belcorp.ssicc.web.framework.base.form;

import org.primefaces.model.UploadedFile;

/**
 * The Class BaseCargaArchivoForm.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 05/05/2014
 */
public class BaseCargaArchivoForm extends BaseSearchForm {
   
	/**
	 * 
	 */
	private static final long serialVersionUID = 2272467458078777387L;
	protected boolean flagUpload;	
	protected boolean flagValidar;	
	protected boolean flagProcesar;			
	protected boolean flagMostrarErrores;
	
		
	protected String directorioTemporal;  //directorio temporal del servidor donde se guardara el archivo	
	protected String nombreArchivo;	//nombre del archivo a subirse al servidor	
	private String extensionArchivo;	//extension del archivo
	
	
	/**
	 * @return the flagUpload
	 */
	public boolean isFlagUpload() {
		return flagUpload;
	}
	/**
	 * @param flagUpload the flagUpload to set
	 */
	public void setFlagUpload(boolean flagUpload) {
		this.flagUpload = flagUpload;
	}
	/**
	 * @return the flagValidar
	 */
	public boolean isFlagValidar() {
		return flagValidar;
	}
	/**
	 * @param flagValidar the flagValidar to set
	 */
	public void setFlagValidar(boolean flagValidar) {
		this.flagValidar = flagValidar;
	}
	/**
	 * @return the flagProcesar
	 */
	public boolean isFlagProcesar() {
		return flagProcesar;
	}
	/**
	 * @param flagProcesar the flagProcesar to set
	 */
	public void setFlagProcesar(boolean flagProcesar) {
		this.flagProcesar = flagProcesar;
	}
	/**
	 * @return the flagMostrarErrores
	 */
	public boolean isFlagMostrarErrores() {
		return flagMostrarErrores;
	}
	/**
	 * @param flagMostrarErrores the flagMostrarErrores to set
	 */
	public void setFlagMostrarErrores(boolean flagMostrarErrores) {
		this.flagMostrarErrores = flagMostrarErrores;
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
	
	
	
}