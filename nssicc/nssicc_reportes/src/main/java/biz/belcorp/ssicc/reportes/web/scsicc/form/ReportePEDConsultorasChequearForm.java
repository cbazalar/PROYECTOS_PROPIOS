package biz.belcorp.ssicc.reportes.web.scsicc.form;
import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * 
 * @author RRG
 * 
 */

public class ReportePEDConsultorasChequearForm extends BaseReporteForm {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 4263076801901679949L;

	private String codigoPeriodo;

	private String[] regionList;

	private String[] zonaList;	
	
	private String fechaInicioFacturacion;

	private String fechaFinFacturacion;
	
	private Date fechaInicioFacturacionD;

	private Date fechaFinFacturacionD;
	    
    private String formatoExportacion;
    
    private String codigoPais;

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
	 * @return the regionList
	 */
	public String[] getRegionList() {
		return regionList;
	}

	/**
	 * @param regionList the regionList to set
	 */
	public void setRegionList(String[] regionList) {
		this.regionList = regionList;
	}

	/**
	 * @return the zonaList
	 */
	public String[] getZonaList() {
		return zonaList;
	}

	/**
	 * @param zonaList the zonaList to set
	 */
	public void setZonaList(String[] zonaList) {
		this.zonaList = zonaList;
	}

	/**
	 * @return the fechaInicioFacturacion
	 */
	public String getFechaInicioFacturacion() {
		return fechaInicioFacturacion;
	}

	/**
	 * @param fechaInicioFacturacion the fechaInicioFacturacion to set
	 */
	public void setFechaInicioFacturacion(String fechaInicioFacturacion) {
		this.fechaInicioFacturacion = fechaInicioFacturacion;
	}

	/**
	 * @return the fechaFinFacturacion
	 */
	public String getFechaFinFacturacion() {
		return fechaFinFacturacion;
	}

	/**
	 * @param fechaFinFacturacion the fechaFinFacturacion to set
	 */
	public void setFechaFinFacturacion(String fechaFinFacturacion) {
		this.fechaFinFacturacion = fechaFinFacturacion;
	}

	/**
	 * @return the formatoExportacion
	 */
	public String getFormatoExportacion() {
		return formatoExportacion;
	}

	/**
	 * @param formatoExportacion the formatoExportacion to set
	 */
	public void setFormatoExportacion(String formatoExportacion) {
		this.formatoExportacion = formatoExportacion;
	}

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
	 * @return the fechaInicioFacturacionD
	 */
	public Date getFechaInicioFacturacionD() {
		return fechaInicioFacturacionD;
	}

	/**
	 * @param fechaInicioFacturacionD the fechaInicioFacturacionD to set
	 */
	public void setFechaInicioFacturacionD(Date fechaInicioFacturacionD) {
		this.fechaInicioFacturacionD = fechaInicioFacturacionD;
	}

	/**
	 * @return the fechaFinFacturacionD
	 */
	public Date getFechaFinFacturacionD() {
		return fechaFinFacturacionD;
	}

	/**
	 * @param fechaFinFacturacionD the fechaFinFacturacionD to set
	 */
	public void setFechaFinFacturacionD(Date fechaFinFacturacionD) {
		this.fechaFinFacturacionD = fechaFinFacturacionD;
	}

	
	 




}

