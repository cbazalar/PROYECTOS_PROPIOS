package biz.belcorp.ssicc.web.spusicc.mae.form;

import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;


public class ProcesoMAECargaMasivaDireccionForm extends BaseProcesoForm {
	
	private static final long serialVersionUID = -7741899570667046328L;

	private String codigoPais;
	private String codigoPeriodo;
	
	private UploadedFile archivo;	//objeto que se utilizara para el upload del Archivo
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
	private boolean flagBotonReporte;
	
	
	public void reset() {
		this.numeroLote=null;
	}
	
	public String getNumRegistrosValido() {
		return numRegistrosValido;
	}

	public void setNumRegistrosValido(String numRegistrosValido) {
		this.numRegistrosValido = numRegistrosValido;
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
	 * @struts.validator type="required"
	 */  
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
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

	/**
	 * @return the flagBotonReporte
	 */
	public boolean isFlagBotonReporte() {
		return flagBotonReporte;
	}

	/**
	 * @param flagBotonReporte the flagBotonReporte to set
	 */
	public void setFlagBotonReporte(boolean flagBotonReporte) {
		this.flagBotonReporte = flagBotonReporte;
	}


	public UploadedFile getArchivo() {
		return archivo;
	}

	public void setArchivo(UploadedFile archivo) {
		this.archivo = archivo;
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

}
