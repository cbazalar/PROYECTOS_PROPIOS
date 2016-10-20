package biz.belcorp.ssicc.web.spusicc.inc.form;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

public class ProcesoINCCargaPremiosExcelForm extends BaseProcesoForm  implements Serializable{
	
	private static final long serialVersionUID = 454171191952455035L;
	
	private String codigoPais;
	private String oidConcurso;
	private UploadedFile archivo;	//objeto que se utilizara para el upload del Archivo
	private String directorioTemporal;  //directorio temporal del servidor donde se guardara el archivo
	private String nombreArchivo;	//nombre del archivo a subirse al servidor
	private String extensionArchivo;	//extension del archivo
	private String numRegistros;
	private String numRegistrosError;
	private String numeroLote;
	private String formatoArchivo;
	private String indicadorValido;
	private String numRegistrosValido;
	private String numNivelesFaltante;
	private String flagVisualiza;
	

	/**
	 * @return oidConcurso
	 */
	public String getOidConcurso() {
		return oidConcurso;
	}

	/**
	 * @param oidConcurso
	 */  
	public void setOidConcurso(String oidConcurso) {
		this.oidConcurso = oidConcurso;
	}

	/**
	 * @return codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 */  
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return directorioTemporal
	 */
	public String getDirectorioTemporal() {
		return directorioTemporal;
	}

	/**
	 * @param directorioTemporal
	 */
	public void setDirectorioTemporal(String directorioTemporal) {
		this.directorioTemporal = directorioTemporal;
	}

	/**
	 * @return nombreArchivo
	 */
	public String getNombreArchivo() {
		return nombreArchivo;
	}

	/**
	 * @param nombreArchivo
	 */
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	/**
	 * @return extensionArchivo
	 */
	public String getExtensionArchivo() {
		return extensionArchivo;
	}

	/**
	 * @param extensionArchivo
	 */
	public void setExtensionArchivo(String extensionArchivo) {
		this.extensionArchivo = extensionArchivo;
	}
	
	/* (non-Javadoc)
	 * @see org.apache.struts.validator.ValidatorForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.oidConcurso = null;
	}

	/**
	 * @return numRegistros
	 */
	public String getNumRegistros() {
		return numRegistros;
	}

	/**
	 * @param numRegistros
	 */
	public void setNumRegistros(String numRegistros) {
		this.numRegistros = numRegistros;
	}

	/**
	 * @return numRegistrosError
	 */
	public String getNumRegistrosError() {
		return numRegistrosError;
	}

	/**
	 * @param numRegistrosError
	 */
	public void setNumRegistrosError(String numRegistrosError) {
		this.numRegistrosError = numRegistrosError;
	}

	/**
	 * @return numeroLote
	 */
	public String getNumeroLote() {
		return numeroLote;
	}

	/**
	 * @param numeroLote
	 */
	public void setNumeroLote(String numeroLote) {
		this.numeroLote = numeroLote;
	}

	/**
	 * @return formatoArchivo
	 */
	public String getFormatoArchivo() {
		return formatoArchivo;
	}

	/**
	 * @param formatoArchivo
	 */
	public void setFormatoArchivo(String formatoArchivo) {
		this.formatoArchivo = formatoArchivo;
	}

	/**
	 * @return indicadorValido
	 */
	public String getIndicadorValido() {
		return indicadorValido;
	}

	/**
	 * @param indicadorValido
	 */
	public void setIndicadorValido(String indicadorValido) {
		this.indicadorValido = indicadorValido;
	}

	/**
	 * @return numRegistrosValido
	 */
	public String getNumRegistrosValido() {
		return numRegistrosValido;
	}

	/**
	 * @param numRegistrosValido
	 */
	public void setNumRegistrosValido(String numRegistrosValido) {
		this.numRegistrosValido = numRegistrosValido;
	}

	/**
	 * @return numNivelesFaltante
	 */
	public String getNumNivelesFaltante() {
		return numNivelesFaltante;
	}

	/**
	 * @param numNivelesFaltante
	 */
	public void setNumNivelesFaltante(String numNivelesFaltante) {
		this.numNivelesFaltante = numNivelesFaltante;
	}

	/**
	 * @return flagVisualiza
	 */
	public String getFlagVisualiza() {
		return flagVisualiza;
	}

	/**
	 * @param flagVisualiza
	 */
	public void setFlagVisualiza(String flagVisualiza) {
		this.flagVisualiza = flagVisualiza;
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