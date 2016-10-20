package biz.belcorp.ssicc.web.spusicc.inc.form;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

public class ProcesoINCMigracionPuntosConsultoraForm extends BaseProcesoForm  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String numeroConcursoOrigen;
	private String numeroConcursoDestino;
	private String indicadorMigracion;
	
	private String codigoCliente;
	private String nombreCliente;
	
	private String [] regiones;
	private String [] zonas;
	private String[] selectedItemsAmbito;
	
	private UploadedFile archivo;	//objeto que se utilizara para el upload del Archivo
	private String directorioTemporal;  //directorio temporal del servidor donde se guardara el archivo
	private String nombreArchivo;	//nombre del archivo a subirse al servidor
	private String extensionArchivo;	//extension del archivo
	
	private String numRegistros;
	private String numRegistrosError;
	
	private String numRegistrosValido;
	private String numeroCarga;
	
	private boolean flagBotonValidar;
	private boolean flagBotonActualizar;
	
	private String oidPais;

	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.indicadorMigracion = "1"; //REGIONES y ZONAS
		this.regiones = null;
		this.zonas = null;
		this.selectedItemsAmbito = null;
	}
	
	public String getNumRegistrosValido() {
		return numRegistrosValido;
	}

	public void setNumRegistrosValido(String numRegistrosValido) {
		this.numRegistrosValido = numRegistrosValido;
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
	 * @return the numeroConcursoOrigen
	 */
	public String getNumeroConcursoOrigen() {
		return numeroConcursoOrigen;
	}

	/**
	 * @param numeroConcursoOrigen the numeroConcursoOrigen to set
	 * @struts.validator type="required"
	 */
	public void setNumeroConcursoOrigen(String numeroConcursoOrigen) {
		this.numeroConcursoOrigen = numeroConcursoOrigen;
	}

	/**
	 * @return the numeroConcursoDestino
	 */
	public String getNumeroConcursoDestino() {
		return numeroConcursoDestino;
	}

	/**
	 * @param numeroConcursoDestino the numeroConcursoDestino to set
	 * @struts.validator type="required"
	 */
	public void setNumeroConcursoDestino(String numeroConcursoDestino) {
		this.numeroConcursoDestino = numeroConcursoDestino;
	}

	/**
	 * @return the indicadorMigracion
	 */
	public String getIndicadorMigracion() {
		return indicadorMigracion;
	}

	/**
	 * @param indicadorMigracion the indicadorMigracion to set
	 */
	public void setIndicadorMigracion(String indicadorMigracion) {
		this.indicadorMigracion = indicadorMigracion;
	}

	/**
	 * @return the codigoCliente
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}

	/**
	 * @param codigoCliente the codigoCliente to set
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	/**
	 * @return the nombreCliente
	 */
	public String getNombreCliente() {
		return nombreCliente;
	}

	/**
	 * @param nombreCliente the nombreCliente to set
	 */
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	/**
	 * @return the regiones
	 */
	public String[] getRegiones() {
		return regiones;
	}

	/**
	 * @param regiones the regiones to set
	 */
	public void setRegiones(String[] regiones) {
		this.regiones = regiones;
	}

	/**
	 * @return the zonas
	 */
	public String[] getZonas() {
		return zonas;
	}

	/**
	 * @param zonas the zonas to set
	 */
	public void setZonas(String[] zonas) {
		this.zonas = zonas;
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
	 * @return the oidPais
	 */
	public String getOidPais() {
		return oidPais;
	}

	/**
	 * @param oidPais the oidPais to set
	 */
	public void setOidPais(String oidPais) {
		this.oidPais = oidPais;
	}

	/**
	 * @return the selectedItemsAmbito
	 */
	public String[] getSelectedItemsAmbito() {
		return selectedItemsAmbito;
	}

	/**
	 * @param selectedItemsAmbito the selectedItemsAmbito to set
	 */
	public void setSelectedItemsAmbito(String[] selectedItemsAmbito) {
		this.selectedItemsAmbito = selectedItemsAmbito;
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
	 * @param numRegistrosError the numRegistrosError to set
	 */
	public void setNumRegistrosError(String numRegistrosError) {
		this.numRegistrosError = numRegistrosError;
	}		
}