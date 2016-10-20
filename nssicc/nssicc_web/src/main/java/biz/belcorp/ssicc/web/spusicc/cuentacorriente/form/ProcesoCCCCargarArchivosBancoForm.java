package biz.belcorp.ssicc.web.spusicc.cuentacorriente.form;

import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

public class ProcesoCCCCargarArchivosBancoForm extends BaseProcesoForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3001915864115129754L;
	private String codigoPais;	
	private String codigoBanco;
	private String tipoOrigen;
	private String cantidadRegistrosCargados;
	private String importeRegistrosCargados;
	
	private boolean flagMostrarErrores;
	
	private UploadedFile archivo;	          //objeto que se utilizara para el upload del Archivo	
	private String directorioTemporal;    //directorio temporal del servidor donde se guardara el archivo	
	private String nombreArchivo;	      //nombre del archivo a subirse al servidor	
	private String extensionArchivo;	  //extension del archivo

	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 *            The codigoPais to set.
	 * @struts.validator type = "required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	
	/**
	 * @return Returns the codigoBanco.
	 */
	public String getCodigoBanco() {
		return codigoBanco;
	}

	
	/**
	 * @param codigoBanco
	 *            The codigoBanco to set.
	 * @struts.validator type = "required"
	 */
	public void setCodigoBanco(String codigoBanco) {
		this.codigoBanco = codigoBanco;
	}	
	
	/**
	 * @return the tipoOrigen
	 */
	public String getTipoOrigen() {
		return tipoOrigen;
	}

	/**
	 * @param tipoOrigen the tipoOrigen to set
	 * @struts.validator type = "required"
	 */
	public void setTipoOrigen(String tipoOrigen) {
		this.tipoOrigen = tipoOrigen;
	}

	/**
	 * @return the cantidadRegistrosCargados
	 */
	public String getCantidadRegistrosCargados() {
		return cantidadRegistrosCargados;
	}

	/**
	 * @param cantidadRegistrosCargados the cantidadRegistrosCargados to set
	 */
	public void setCantidadRegistrosCargados(String cantidadRegistrosCargados) {
		this.cantidadRegistrosCargados = cantidadRegistrosCargados;
	}

	/**
	 * @return the importeRegistrosCargados
	 */
	public String getImporteRegistrosCargados() {
		return importeRegistrosCargados;
	}

	/**
	 * @param importeRegistrosCargados the importeRegistrosCargados to set
	 */
	public void setImporteRegistrosCargados(String importeRegistrosCargados) {
		this.importeRegistrosCargados = importeRegistrosCargados;
	}
	/**
	 * @return the flagMostrarErrores
	 */
	public boolean isFlagMostrarErrores() {
		return flagMostrarErrores;
	}

	/**
	 * @param flagMostrarErrores the flagMostrarErrores to set
	 */
	public void setFlagMostrarErrores(boolean flagMostrarErrores) {
		this.flagMostrarErrores = flagMostrarErrores;
	}


	/**
	 * @return Returns the archivo.
	 */
	public UploadedFile getArchivo() {
		return archivo;
	}
	/**
	 * @param archivo The archivo to set.
	 * @struts.validator type="required"
	 */
	public void setArchivo(UploadedFile archivo) {
		this.archivo = archivo;
	}
	/**
	 * @return Returns the directorioTemporal.
	 */
	public String getDirectorioTemporal() {
		return directorioTemporal;
	}
	/**
	 * @param directorioTemporal The directorioTemporal to set.
	 */
	public void setDirectorioTemporal(String directorioTemporal) {
		this.directorioTemporal = directorioTemporal;
	}
	/**
	 * @return Returns the extensionArchivo.
	 */
	public String getExtensionArchivo() {
		return extensionArchivo;
	}
	/**
	 * @param extensionArchivo The extensionArchivo to set.
	 */
	public void setExtensionArchivo(String extensionArchivo) {
		this.extensionArchivo = extensionArchivo;
	}
	/**
	 * @return Returns the nombreArchivo.
	 */
	public String getNombreArchivo() {
		return nombreArchivo;
	}
	/**
	 * @param nombreArchivo The nombreArchivo to set.
	 */
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	
}
