/**
 * 
 */
package biz.belcorp.ssicc.web.spusicc.pedidos.form;

import java.io.Serializable;


import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

/**
 * @author Sigcomt
 *
 */
public class ProcesoPEDCargaCUVRecuperarSiguienteSemanaForm extends BaseProcesoForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6436763065653444706L;
	
	private String codigoPais;
	private String codigoPeriodo;
	private String codigoCUV;
	
//	private FormFile archivo;	//objeto que se utilizara para el upload del Archivo
	private UploadedFile archivo;
	private String directorioTemporal;  //directorio temporal del servidor donde se guardara el archivo
	private String nombreArchivo;	//nombre del archivo a subirse al servidor
	private String extensionArchivo;	//extension del archivo
	
	private String numRegistros;
	private String numRegistrosError;
	
	private String numeroLote;
	private String numRegistrosValido;
	private String numeroCarga;
	
	private boolean flagBotonValidar;
	private boolean flagBotonActualizar;
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
	 * @return the codigoCUV
	 */
	public String getCodigoCUV() {
		return codigoCUV;
	}
	/**
	 * @param codigoCUV the codigoCUV to set
	 */
	public void setCodigoCUV(String codigoCUV) {
		this.codigoCUV = codigoCUV;
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
	 * @return the numRegistrosValido
	 */
	public String getNumRegistrosValido() {
		return numRegistrosValido;
	}
	/**
	 * @param numRegistrosValido the numRegistrosValido to set
	 */
	public void setNumRegistrosValido(String numRegistrosValido) {
		this.numRegistrosValido = numRegistrosValido;
	}
	/**
	 * @return the numeroCarga
	 */
	public String getNumeroCarga() {
		return numeroCarga;
	}
	/**
	 * @param numeroCarga the numeroCarga to set
	 */
	public void setNumeroCarga(String numeroCarga) {
		this.numeroCarga = numeroCarga;
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
	 * @return the flagBotonActualizar
	 */
	public boolean isFlagBotonActualizar() {
		return flagBotonActualizar;
	}
	/**
	 * @param flagBotonActualizar the flagBotonActualizar to set
	 */
	public void setFlagBotonActualizar(boolean flagBotonActualizar) {
		this.flagBotonActualizar = flagBotonActualizar;
	}
		
}
