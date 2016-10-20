/**
 * 
 */
package biz.belcorp.ssicc.web.spusicc.percepciones.form;

import java.io.Serializable;

import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

/**
 * @author Sigcomt
 *
 */
public class ProcesoPERCargarPagosBancariosMasivosForm extends BaseProcesoForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4699911546101938617L;
	
	private String codigoPais;
	private String codigoSociedad;	
	private String codigoCuentaCorrienteBancaria;	
	
	private boolean flagUpload;	
	private boolean flagValidar;	
	private boolean flagProcesar;			
	private boolean flagMostrarErrores;
	
	private UploadedFile archivo;	          //objeto que se utilizara para el upload del Archivo	
	private String directorioTemporal;    //directorio temporal del servidor donde se guardara el archivo	
	private String nombreArchivo;	      //nombre del archivo a subirse al servidor	
	private String extensionArchivo;	  //extension del archivo
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
	 * @return the codigoSociedad
	 */
	public String getCodigoSociedad() {
		return codigoSociedad;
	}
	/**
	 * @param codigoSociedad the codigoSociedad to set
	 */
	public void setCodigoSociedad(String codigoSociedad) {
		this.codigoSociedad = codigoSociedad;
	}
	/**
	 * @return the codigoCuentaCorrienteBancaria
	 */
	public String getCodigoCuentaCorrienteBancaria() {
		return codigoCuentaCorrienteBancaria;
	}
	/**
	 * @param codigoCuentaCorrienteBancaria the codigoCuentaCorrienteBancaria to set
	 */
	public void setCodigoCuentaCorrienteBancaria(
			String codigoCuentaCorrienteBancaria) {
		this.codigoCuentaCorrienteBancaria = codigoCuentaCorrienteBancaria;
	}
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
	
	
	

}
