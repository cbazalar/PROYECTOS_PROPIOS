/**
 * 
 */
package biz.belcorp.ssicc.web.spusicc.reclamos.form;

import java.io.Serializable;

import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

/**
 * @author Sigcomt
 *
 */
public class ProcesoRECAnulaMasivaBorecForm extends BaseProcesoForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 535550191984536000L;
	
private String codigoPais;
	
	private String numeroLote;
	
	private UploadedFile archivo;	//objeto que se utilizara para el upload del Archivo
	
	private String directorioTemporal;  //directorio temporal del servidor donde se guardara el archivo
	
	private String nombreArchivo;	//nombre del archivo a subirse al servidor
	
	private String extensionArchivo;	//extension del archivo
	
	private boolean flagVerificar;
	
	private boolean flagProcesar;
	
	private boolean flagLimpiar;
	
	private boolean flagRetornar;
	
	private String usuario;

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
	 * @return the numeroLote
	 */
	public String getNumeroLote() {
		return numeroLote;
	}

	/**
	 * @param numeroLote the numeroLote to set
	 */
	public void setNumeroLote(String numeroLote) {
		this.numeroLote = numeroLote;
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

	/**
	 * @return the flagVerificar
	 */
	public boolean isFlagVerificar() {
		return flagVerificar;
	}

	/**
	 * @param flagVerificar the flagVerificar to set
	 */
	public void setFlagVerificar(boolean flagVerificar) {
		this.flagVerificar = flagVerificar;
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
	 * @return the flagLimpiar
	 */
	public boolean isFlagLimpiar() {
		return flagLimpiar;
	}

	/**
	 * @param flagLimpiar the flagLimpiar to set
	 */
	public void setFlagLimpiar(boolean flagLimpiar) {
		this.flagLimpiar = flagLimpiar;
	}

	/**
	 * @return the flagRetornar
	 */
	public boolean isFlagRetornar() {
		return flagRetornar;
	}

	/**
	 * @param flagRetornar the flagRetornar to set
	 */
	public void setFlagRetornar(boolean flagRetornar) {
		this.flagRetornar = flagRetornar;
	}

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
}
