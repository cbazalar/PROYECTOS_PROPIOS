package biz.belcorp.ssicc.web.spusicc.comision.form;

import java.io.Serializable;

import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;


/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="ProcesoCOMCargaBonosForm.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli</a>
 * 
 */

public class ProcesoCOMCargaBonosForm extends BaseProcesoForm  implements Serializable{
	
	private String codigoPais;
	private UploadedFile archivo;	//objeto que se utilizara para el upload del Archivo
	private String directorioTemporal;  //directorio temporal del servidor donde se guardara el archivo
	private String nombreArchivo;	//nombre del archivo a subirse al servidor
	private String extensionArchivo;	//extension del archivo
	private String indicadorContinuar;
	private String indicadorHabMensaje;	
	private String codigoConcepto;
	private String rutaFichero;
	
	/**
	 * @return the rutaFichero
	 */
	public String getRutaFichero() {
		return rutaFichero;
	}

	/**
	 * @param rutaFichero the rutaFichero to set
	 */
	public void setRutaFichero(String rutaFichero) {
		this.rutaFichero = rutaFichero;
	}

	/**
	 * @return the codigoConcepto
	 */
	public String getCodigoConcepto() {
		return codigoConcepto;
	}

	/**
	 * @param codigoConcepto the codigoConcepto to set
	 */
	public void setCodigoConcepto(String codigoConcepto) {
		this.codigoConcepto = codigoConcepto;
	}

	/**
	 * @return the indicadorHabMensaje
	 */
	public String getIndicadorHabMensaje() {
		return indicadorHabMensaje;
	}

	/**
	 * @param indicadorHabMensaje the indicadorHabMensaje to set
	 */
	public void setIndicadorHabMensaje(String indicadorHabMensaje) {
		this.indicadorHabMensaje = indicadorHabMensaje;
	}

	/**
	 * @return the indicadorContinuar
	 */
	public String getIndicadorContinuar() {
		return indicadorContinuar;
	}

	/**
	 * @param indicadorContinuar the indicadorContinuar to set
	 */
	public void setIndicadorContinuar(String indicadorContinuar) {
		this.indicadorContinuar = indicadorContinuar;
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

	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 */  
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public UploadedFile getArchivo() {
		return archivo;
	}

	public void setArchivo(UploadedFile archivo) {
		this.archivo = archivo;
	}
	
}