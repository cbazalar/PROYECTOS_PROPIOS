package biz.belcorp.ssicc.web.scsicc.form;



import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;


public class ReporteSTORechazosSTODemandaForm extends BaseReporteForm  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;	
	private String codigoPeriodo;
	private String fechaFacturacion;
	private Date fechaFacturacionD;
	

	private String regionList;
	private String zonaList;
	
	
		
	
	



	public Date getFechaFacturacionD() {
		return fechaFacturacionD;
	}

	public void setFechaFacturacionD(Date fechaFacturacionD) {
		this.fechaFacturacionD = fechaFacturacionD;
	}

	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}


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
	 * @return the fechaFacturacion
	 */
	public String getFechaFacturacion() {
		return fechaFacturacion;
	}

	/**
	 * @param fechaFacturacion the fechaFacturacion to set
	 */
	public void setFechaFacturacion(String fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}

	/**
	 * @return the regionList
	 */
	public String getRegionList() {
		return regionList;
	}

	/**
	 * @param regionList the regionList to set
	 */
	public void setRegionList(String regionList) {
		this.regionList = regionList;
	}

	/**
	 * @return the zonaList
	 */
	public String getZonaList() {
		return zonaList;
	}

	/**
	 * @param zonaList the zonaList to set
	 */
	public void setZonaList(String zonaList) {
		this.zonaList = zonaList;
	}

	
}

