package biz.belcorp.ssicc.web.spusicc.zon.form;

import java.util.List;

import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

@SuppressWarnings({ "rawtypes"})
public class ProcesoZONActualizarUnidadesGeograficasForm extends BaseProcesoForm{

	/**
	 * JPPS
	 */
	private static final long serialVersionUID = 8507364840597470294L;
	
	private UploadedFile archivo;	//objeto que se utilizara para el upload del Archivo
	private String directorioTemporal;  //directorio temporal del servidor donde se guardara el archivo
	private String nombreArchivo;	//nombre del archivo a subirse al servidor
	private String extensionArchivo;	//extension del archivo
	
	
	private List unidades;  //coleccion que manejara las unidades geograficas
	
	private boolean flagCargarDatos;
	private boolean flagValidarDatos;
	private boolean flagAprobar;
	private boolean flagMostrarPDFCargaDatos;
	private boolean flagMostrarPDFValidarDatos;
	private boolean flagProcesar;
	
	private String indicadorGeoreferenciaColArchivo;
	
	public UploadedFile getArchivo() {
		return archivo;
	}

	public void setArchivo(UploadedFile archivo) {
		this.archivo = archivo;
	}

	public String getDirectorioTemporal() {
		return directorioTemporal;
	}

	public void setDirectorioTemporal(String directorioTemporal) {
		this.directorioTemporal = directorioTemporal;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public List getUnidades() {
		return unidades;
	}

	public void setUnidades(List unidades) {
		this.unidades = unidades;
	}

	public boolean isFlagAprobar() {
		return flagAprobar;
	}

	public void setFlagAprobar(boolean flagAprobar) {
		this.flagAprobar = flagAprobar;
	}

	public boolean isFlagCargarDatos() {
		return flagCargarDatos;
	}

	public void setFlagCargarDatos(boolean flagCargarDatos) {
		this.flagCargarDatos = flagCargarDatos;
	}

	public boolean isFlagProcesar() {
		return flagProcesar;
	}

	public void setFlagProcesar(boolean flagProcesar) {
		this.flagProcesar = flagProcesar;
	}

	public boolean isFlagValidarDatos() {
		return flagValidarDatos;
	}

	public void setFlagValidarDatos(boolean flagValidarDatos) {
		this.flagValidarDatos = flagValidarDatos;
	}

	public boolean isFlagMostrarPDFCargaDatos() {
		return flagMostrarPDFCargaDatos;
	}

	public void setFlagMostrarPDFCargaDatos(boolean flagMostrarPDFCargaDatos) {
		this.flagMostrarPDFCargaDatos = flagMostrarPDFCargaDatos;
	}

	public boolean isFlagMostrarPDFValidarDatos() {
		return flagMostrarPDFValidarDatos;
	}

	public void setFlagMostrarPDFValidarDatos(boolean flagMostrarPDFValidarDatos) {
		this.flagMostrarPDFValidarDatos = flagMostrarPDFValidarDatos;
	}

	public String getExtensionArchivo() {
		return extensionArchivo;
	}

	public void setExtensionArchivo(String extensionArchivo) {
		this.extensionArchivo = extensionArchivo;
	}

	/**
	 * @return the indicadorGeoreferenciaColArchivo
	 */
	public String getIndicadorGeoreferenciaColArchivo() {
		return indicadorGeoreferenciaColArchivo;
	}

	/**
	 * @param indicadorGeoreferenciaColArchivo the indicadorGeoreferenciaColArchivo to set
	 */
	public void setIndicadorGeoreferenciaColArchivo(
			String indicadorGeoreferenciaColArchivo) {
		this.indicadorGeoreferenciaColArchivo = indicadorGeoreferenciaColArchivo;
	}


	
}
