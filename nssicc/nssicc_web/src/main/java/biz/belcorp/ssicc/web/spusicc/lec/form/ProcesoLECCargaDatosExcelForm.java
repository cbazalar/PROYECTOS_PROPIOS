package biz.belcorp.ssicc.web.spusicc.lec.form;

import java.io.Serializable;

import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

public class ProcesoLECCargaDatosExcelForm 	extends BaseProcesoForm implements Serializable {
		private static final long serialVersionUID = 1L;
		
		private String codigoPais;
		private String codigoPrograma;
		private String tipoCarga;
	    private String codigoPeriodo;
		private UploadedFile archivo;	//objeto que se utilizara para el upload del Archivo
		private String directorioTemporal;  //directorio temporal del servidor donde se guardara el archivo
		private String nombreArchivo;	//nombre del archivo a subirse al servidor
		private String extensionArchivo;	//extension del archivo
		
		private String numRegistros;
		private String numRegistrosError;
		private String numRegistrosValido;
		private String numRegistrosInsertados;
		private String numRegistrosNoInsertados;
		private String numRegistrosExisten;
		private String numRegistrosNoExisten;
		
		private String numeroCarga;
		
		private boolean flagBotonValidar;
		private boolean flagBotonActualizar;
		
		private String prefijoCarga;
		
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
		 * @return the codigoPrograma
		 */
		public String getCodigoPrograma() {
			return codigoPrograma;
		}
		/**
		 * @param codigoPrograma the codigoPrograma to set
		 */
		public void setCodigoPrograma(String codigoPrograma) {
			this.codigoPrograma = codigoPrograma;
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
		 * @return the numRegistrosExisten
		 */
		public String getNumRegistrosExisten() {
			return numRegistrosExisten;
		}
		/**
		 * @param numRegistrosExisten the numRegistrosExisten to set
		 */
		public void setNumRegistrosExisten(String numRegistrosExisten) {
			this.numRegistrosExisten = numRegistrosExisten;
		}
		/**
		 * @return the numRegistrosNoExisten
		 */
		public String getNumRegistrosNoExisten() {
			return numRegistrosNoExisten;
		}
		/**
		 * @param numRegistrosNoExisten the numRegistrosNoExisten to set
		 */
		public void setNumRegistrosNoExisten(String numRegistrosNoExisten) {
			this.numRegistrosNoExisten = numRegistrosNoExisten;
		}
		/**
		 * @return the prefijoCarga
		 */
		public String getPrefijoCarga() {
			return prefijoCarga;
		}
		/**
		 * @param prefijoCarga the prefijoCarga to set
		 */
		public void setPrefijoCarga(String prefijoCarga) {
			this.prefijoCarga = prefijoCarga;
		}
		public String getNumRegistrosInsertados() {
			return numRegistrosInsertados;
		}
		public void setNumRegistrosInsertados(String numRegistrosInsertados) {
			this.numRegistrosInsertados = numRegistrosInsertados;
		}
		public String getNumRegistrosNoInsertados() {
			return numRegistrosNoInsertados;
		}
		public void setNumRegistrosNoInsertados(String numRegistrosNoInsertados) {
			this.numRegistrosNoInsertados = numRegistrosNoInsertados;
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