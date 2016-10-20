package biz.belcorp.ssicc.web.spusicc.mae.form;

import java.io.Serializable;

import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

public class ProcesoMAECargaBloqueoDesbloqueoMasivoForm extends BaseProcesoForm implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	
	private UploadedFile archivo;	//objeto que se utilizara para el upload del Archivo
	private String directorioTemporal;  //directorio temporal del servidor donde se guardara el archivo
	private String nombreArchivo;	//nombre del archivo a subirse al servidor
	private String extensionArchivo;	//extension del archivo
	
	private String indicadorValido;
	private String numRegistros;
	private String numRegistrosError;
	private String numRegistrosValido;
	
	private String accionBloqueo;
	private String tipoBloqueo;
	private String motivoBloqueo;
	private String observacionesBloqueo;
	
	private boolean flagBotonValidar;
	private boolean flagBotonActualizar;
	
	private String codigoArea;
	private String indicadorDesbloqueo;

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
	 * @return the accionBloqueo
	 */
	public String getAccionBloqueo() {
		return accionBloqueo;
	}

	/**
	 * @param accionBloqueo the accionBloqueo to set
	 */
	public void setAccionBloqueo(String accionBloqueo) {
		this.accionBloqueo = accionBloqueo;
	}

	/**
	 * @return the tipoBloqueo
	 */
	public String getTipoBloqueo() {
		return tipoBloqueo;
	}

	/**
	 * @param tipoBloqueo the tipoBloqueo to set
	 */
	public void setTipoBloqueo(String tipoBloqueo) {
		this.tipoBloqueo = tipoBloqueo;
	}

	/**
	 * @return the motivoBloqueo
	 */
	public String getMotivoBloqueo() {
		return motivoBloqueo;
	}

	/**
	 * @param motivoBloqueo the motivoBloqueo to set
	 */
	public void setMotivoBloqueo(String motivoBloqueo) {
		this.motivoBloqueo = motivoBloqueo;
	}

	/**
	 * @return the observacionesBloqueo
	 */
	public String getObservacionesBloqueo() {
		return observacionesBloqueo;
	}

	/**
	 * @param observacionesBloqueo the observacionesBloqueo to set
	 */
	public void setObservacionesBloqueo(String observacionesBloqueo) {
		this.observacionesBloqueo = observacionesBloqueo;
	}

	/**
	 * @return the indicadorValido
	 */
	public String getIndicadorValido() {
		return indicadorValido;
	}

	/**
	 * @param indicadorValido the indicadorValido to set
	 */
	public void setIndicadorValido(String indicadorValido) {
		this.indicadorValido = indicadorValido;
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
	 * @return the codigoArea
	 */
	public String getCodigoArea() {
		return codigoArea;
	}

	/**
	 * @param codigoArea the codigoArea to set
	 */
	public void setCodigoArea(String codigoArea) {
		this.codigoArea = codigoArea;
	}

	/**
	 * @return the indicadorDesbloqueo
	 */
	public String getIndicadorDesbloqueo() {
		return indicadorDesbloqueo;
	}

	/**
	 * @param indicadorDesbloqueo the indicadorDesbloqueo to set
	 */
	public void setIndicadorDesbloqueo(String indicadorDesbloqueo) {
		this.indicadorDesbloqueo = indicadorDesbloqueo;
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
}