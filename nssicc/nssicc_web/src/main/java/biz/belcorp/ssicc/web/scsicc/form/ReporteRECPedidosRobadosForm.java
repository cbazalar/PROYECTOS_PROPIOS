/*
 * 
 */
package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;
import java.util.Date;

import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

// TODO: Auto-generated Javadoc
/**
 * The Class ReporteRECPedidosRobadosForm.
 *
 * @author <a href="mailto:aoviedo@sigcomt.com">Aurelio Oviedo</a>
 */
public class ReporteRECPedidosRobadosForm extends BaseReporteForm implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The codigo pais. */
	private String codigoPais;
	
	/** The tipo reporte. */
	private String tipoReporte;

	/** The codigo periodo. */
	private String codigoPeriodo;
	
	/** The fecha anulacion. */
	private String fechaAnulacion;
	
	/** The fecha anulacion date. */
	private Date fechaAnulacionDate;

	/** The region list. */
	private String[] regionList;

	/** The zona list. */
	private String[] zonaList;

	/** The consultora. */
	private String consultora;

	/** The archivo. */
	private UploadedFile archivo;
	
	/** The directorio temporal. */
	private String directorioTemporal;  //directorio temporal del servidor donde se guardara el archivo	
	
	/** The nombre archivo. */
	private String nombreArchivo;	//nombre del archivo a subirse al servidor	
	
	/** The extension archivo. */
	private String extensionArchivo;	//extension del archivo

	/**
	 * Gets the codigo pais.
	 *
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * Sets the codigo pais.
	 *
	 * @param codigoPais the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * Gets the tipo reporte.
	 *
	 * @return the tipoReporte
	 */
	public String getTipoReporte() {
		return tipoReporte;
	}

	/**
	 * Sets the tipo reporte.
	 *
	 * @param tipoReporte the tipoReporte to set
	 */
	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}

	/**
	 * Gets the codigo periodo.
	 *
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * Sets the codigo periodo.
	 *
	 * @param codigoPeriodo the codigoPeriodo to set
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * Gets the fecha anulacion.
	 *
	 * @return the fechaAnulacion
	 */
	public String getFechaAnulacion() {
		return fechaAnulacion;
	}

	/**
	 * Sets the fecha anulacion.
	 *
	 * @param fechaAnulacion the fechaAnulacion to set
	 */
	public void setFechaAnulacion(String fechaAnulacion) {
		this.fechaAnulacion = fechaAnulacion;
	}

	/**
	 * Gets the region list.
	 *
	 * @return the regionList
	 */
	public String[] getRegionList() {
		return regionList;
	}

	/**
	 * Sets the region list.
	 *
	 * @param regionList the regionList to set
	 * @struts.validator type="required"
	 */
	public void setRegionList(String[] regionList) {
		this.regionList = regionList;
	}

	/**
	 * Gets the zona list.
	 *
	 * @return the zonaList
	 */
	public String[] getZonaList() {
		return zonaList;
	}

	/**
	 * Sets the zona list.
	 *
	 * @param zonaList the zonaList to set
	 */
	public void setZonaList(String[] zonaList) {
		this.zonaList = zonaList;
	}

	/**
	 * Gets the consultora.
	 *
	 * @return the consultora
	 */
	public String getConsultora() {
		return consultora;
	}

	/**
	 * Sets the consultora.
	 *
	 * @param consultora the consultora to set
	 */
	public void setConsultora(String consultora) {
		this.consultora = consultora;
	}

	/**
	 * Gets the archivo.
	 *
	 * @return the archivo
	 */
	public UploadedFile getArchivo() {
		return archivo;
	}

	/**
	 * Sets the archivo.
	 *
	 * @param archivo the new archivo
	 */
	public void setArchivo(UploadedFile archivo) {
		this.archivo = archivo;
	}

	/**
	 * Gets the directorio temporal.
	 *
	 * @return the directorio temporal
	 */
	public String getDirectorioTemporal() {
		return directorioTemporal;
	}

	/**
	 * Sets the directorio temporal.
	 *
	 * @param directorioTemporal the new directorio temporal
	 */
	public void setDirectorioTemporal(String directorioTemporal) {
		this.directorioTemporal = directorioTemporal;
	}

	/**
	 * Gets the nombre archivo.
	 *
	 * @return the nombre archivo
	 */
	public String getNombreArchivo() {
		return nombreArchivo;
	}

	/**
	 * Sets the nombre archivo.
	 *
	 * @param nombreArchivo the new nombre archivo
	 */
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	/**
	 * Gets the extension archivo.
	 *
	 * @return the extension archivo
	 */
	public String getExtensionArchivo() {
		return extensionArchivo;
	}

	/**
	 * Sets the extension archivo.
	 *
	 * @param extensionArchivo the new extension archivo
	 */
	public void setExtensionArchivo(String extensionArchivo) {
		this.extensionArchivo = extensionArchivo;
	}

	/**
	 * Gets the fecha anulacion date.
	 *
	 * @return the fecha anulacion date
	 */
	public Date getFechaAnulacionDate() {
		return fechaAnulacionDate;
	}

	/**
	 * Sets the fecha anulacion date.
	 *
	 * @param fechaAnulacionDate the new fecha anulacion date
	 */
	public void setFechaAnulacionDate(Date fechaAnulacionDate) {
		this.fechaAnulacionDate = fechaAnulacionDate;
	}


}