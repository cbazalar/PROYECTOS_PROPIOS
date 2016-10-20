package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;

import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ReporteAPEArmadoEbanuladosForm extends BaseReporteForm
		implements Serializable {

	private static final long serialVersionUID = -436659594834350713L;

	private String codigoPais;
	
	private String codigoFactura;
	
	private UploadedFile facturaFile;
	
	/** The directorio temporal. */
	private String directorioTemporal;  //directorio temporal del servidor donde se guardara el archivo	
	
	/** The nombre archivo. */
	private String nombreArchivo;	//nombre del archivo a subirse al servidor	
	
	/** The extension archivo. */
	private String extensionArchivo;	
	
	private String valorFile = "0";
	
	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 *            The codigoPais to set.
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the codigoFactura
	 */
	public String getCodigoFactura() {
		return codigoFactura;
	}

	/**
	 * @param codigoFactura the codigoFactura to set
	 */
	public void setCodigoFactura(String codigoFactura) {
		this.codigoFactura = codigoFactura;
	}

	/**
	 * @return the facturaFile
	 */
	
	public String getValorFile() {
		return valorFile;
	}

	/**
	 * @return
	 */
	public UploadedFile getFacturaFile() {
		return facturaFile;
	}

	/**
	 * @param facturaFile
	 */
	public void setFacturaFile(UploadedFile facturaFile) {
		this.facturaFile = facturaFile;
	}

	/**
	 * @return
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
	 * @return
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
	 * @return
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

	/**
	 * @param valorFile
	 */
	public void setValorFile(String valorFile) {
		this.valorFile = valorFile;
	}	
}