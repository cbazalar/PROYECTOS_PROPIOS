package biz.belcorp.ssicc.web.spusicc.lec.form;

import java.io.Serializable;

import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

public class ProcesoLECActualizacionMasivaBloqueoPagosForm 	extends BaseProcesoForm implements Serializable {

	    private static final long serialVersionUID = -1284864331725887854L;
		private String codigoPais;
		private String tipoProceso;
		private String tipoPago;
		private String tipoMotivo;
	    private String codigoPeriodo;
		private UploadedFile archivo;	//objeto que se utilizara para el upload del Archivo
		private String directorioTemporal;  //directorio temporal del servidor donde se guardara el archivo
		private String nombreArchivo;	//nombre del archivo a subirse al servidor
		private String extensionArchivo;	//extension del archivo
		
		private String numRegistros;
		private String numRegistrosError;
		private String numRegistrosValido;
		
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
		 * @return the tipoProceso
		 */
		public String getTipoProceso() {
			return tipoProceso;
		}
		/**
		 * @param tipoProceso the tipoProceso to set
		 */
		public void setTipoProceso(String tipoProceso) {
			this.tipoProceso = tipoProceso;
		}
		/**
		 * @return the tipoPago
		 */
		public String getTipoPago() {
			return tipoPago;
		}
		/**
		 * @param tipoPago the tipoPago to set
		 */
		public void setTipoPago(String tipoPago) {
			this.tipoPago = tipoPago;
		}
		/**
		 * @return the tipoMotivo
		 */
		public String getTipoMotivo() {
			return tipoMotivo;
		}
		/**
		 * @param tipoMotivo the tipoMotivo to set
		 */
		public void setTipoMotivo(String tipoMotivo) {
			this.tipoMotivo = tipoMotivo;
		}
	
		
	}