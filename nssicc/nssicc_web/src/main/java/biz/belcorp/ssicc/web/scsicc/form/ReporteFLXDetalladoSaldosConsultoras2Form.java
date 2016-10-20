package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;


/**
 * The Class ReporteFLXDetalladoSaldosConsultoras2Form.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 16/12/2013
 */
public class ReporteFLXDetalladoSaldosConsultoras2Form extends BaseReporteForm implements Serializable{

	private static final long serialVersionUID = 1L;

	private String codigoPais;

	private String codigoCampana;

	private String[] regionList;

	private String[] zonaList;		

	private String descripcionRegionList;

	private String descripcionZonaList;
	
	private String codigoCliente;
		
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @struts.validator type = "required"
	 * @param codigoPais the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public String getCodigoCampana() {
		return codigoCampana;
	}

	/**
	 * @struts.validator type = "required"
	 * @param codigoPais the codigoCampana to set
	 */
	public void setCodigoCampana(String codigoCampana) {
		this.codigoCampana = codigoCampana;
	}

	public String[] getRegionList() {
		return regionList;
	}

	public void setRegionList(String[] regionList) {
		this.regionList = regionList;
	}

	public String[] getZonaList() {
		return zonaList;
	}
		
	
	public void setZonaList(String[] zonaList) {
		this.zonaList = zonaList;
	}

	public String getDescripcionRegionList() {
		return descripcionRegionList;
	}

	public void setDescripcionRegionList(String descripcionRegionList) {
		this.descripcionRegionList = descripcionRegionList;
	}

	public String getDescripcionZonaList() {
		return descripcionZonaList;
	}

	public void setDescripcionZonaList(String descripcionZonaList) {
		this.descripcionZonaList = descripcionZonaList;
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

}