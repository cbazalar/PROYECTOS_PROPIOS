package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * 
 * @author RRG
 * 
 */

public class ReporteCOBCarteraSinGestionForm extends BaseReporteForm implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6906352748959871273L;

	private String codigoPais;
	private String codigoSociedad;
	private String codigoEtapaDeuda;
	private String tipoVista;
	private String estadoCartera;
	private String codigoCobrador;
	private String descripcionCobrador;
	private String codigoPeriodo;
	private String[] regionList;
	private String[] zonaList;
	private String descripcionRegionList;
	private String descripcionZonaList;
	public ReporteCOBCarteraSinGestionForm() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String periodo = sdf.format(new Date(System.currentTimeMillis()));

		if (StringUtils.isEmpty(this.codigoPeriodo)) {
			this.codigoPeriodo = periodo;
		}

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
	 * @return the codigoSociedad
	 */
	public String getCodigoSociedad() {
		return codigoSociedad;
	}
	/**
	 * @param codigoSociedad the codigoSociedad to set
	 */
	public void setCodigoSociedad(String codigoSociedad) {
		this.codigoSociedad = codigoSociedad;
	}
	/**
	 * @return the codigoEtapaDeuda
	 */
	public String getCodigoEtapaDeuda() {
		return codigoEtapaDeuda;
	}
	/**
	 * @param codigoEtapaDeuda the codigoEtapaDeuda to set
	 */
	public void setCodigoEtapaDeuda(String codigoEtapaDeuda) {
		this.codigoEtapaDeuda = codigoEtapaDeuda;
	}
	/**
	 * @return the tipoVista
	 */
	public String getTipoVista() {
		return tipoVista;
	}
	/**
	 * @param tipoVista the tipoVista to set
	 */
	public void setTipoVista(String tipoVista) {
		this.tipoVista = tipoVista;
	}
	/**
	 * @return the estadoCartera
	 */
	public String getEstadoCartera() {
		return estadoCartera;
	}
	/**
	 * @param estadoCartera the estadoCartera to set
	 */
	public void setEstadoCartera(String estadoCartera) {
		this.estadoCartera = estadoCartera;
	}
	/**
	 * @return the codigoCobrador
	 */
	public String getCodigoCobrador() {
		return codigoCobrador;
	}
	/**
	 * @param codigoCobrador the codigoCobrador to set
	 */
	public void setCodigoCobrador(String codigoCobrador) {
		this.codigoCobrador = codigoCobrador;
	}
	/**
	 * @return the descripcionCobrador
	 */
	public String getDescripcionCobrador() {
		return descripcionCobrador;
	}
	/**
	 * @param descripcionCobrador the descripcionCobrador to set
	 */
	public void setDescripcionCobrador(String descripcionCobrador) {
		this.descripcionCobrador = descripcionCobrador;
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
	 * @return the descripcionRegionList
	 */
	public String getDescripcionRegionList() {
		return descripcionRegionList;
	}
	/**
	 * @param descripcionRegionList the descripcionRegionList to set
	 */
	public void setDescripcionRegionList(String descripcionRegionList) {
		this.descripcionRegionList = descripcionRegionList;
	}
	/**
	 * @return the descripcionZonaList
	 */
	public String getDescripcionZonaList() {
		return descripcionZonaList;
	}
	/**
	 * @param descripcionZonaList the descripcionZonaList to set
	 */
	public void setDescripcionZonaList(String descripcionZonaList) {
		this.descripcionZonaList = descripcionZonaList;
	}
	
	
	

}
