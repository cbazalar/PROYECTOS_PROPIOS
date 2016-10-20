/**
 * 
 */
package biz.belcorp.ssicc.web.spusicc.mae.form;

import java.io.Serializable;

import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

/**
 * @author Sigcomt
 *
 */
public class ProcesoMAECargaInformacionMasivoForm extends BaseProcesoForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8897894372832006206L;
	
	private String codigoPais;
	private UploadedFile archivo;
	private String directorioTemporal;
	private String nombreArchivo;
	private String extensionArchivo;
	private String codigoPeriodo;
	private String tipoCarga;
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
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}
	/**
	 * @param codigoPeriodo the codigoPeriodo to set
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
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
