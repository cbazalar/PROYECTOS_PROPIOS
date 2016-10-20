package biz.belcorp.ssicc.reportes.web.scsicc.form;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * 
 * @author RRG
 * 
 */

public class ReporteRECListadoRecAreasForm extends BaseReporteForm implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5990040642740265316L;
	private String codigoPais;
	private String codigoPeriodoInicial;
	private String codigoPeriodoFinal;
	private String tipoPeriodo;
	private String[] operacionList;
	private String[] tipoOperacionList;
	private String[] regionList;
	private String[] zonaList;
	private String descripcionOperacionList;
	private String descripcionTipoOperacionList;
	private String descripcionRegionList;
	private String descripcionZonaList;
	private String tipoReporte;

	public ReporteRECListadoRecAreasForm() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String periodo = sdf.format(new Date(System.currentTimeMillis()));

		this.codigoPeriodoInicial = periodo;
		this.codigoPeriodoFinal = periodo;
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
	 * @return the codigoPeriodoInicial
	 */
	public String getCodigoPeriodoInicial() {
		return codigoPeriodoInicial;
	}

	/**
	 * @param codigoPeriodoInicial the codigoPeriodoInicial to set
	 */
	public void setCodigoPeriodoInicial(String codigoPeriodoInicial) {
		this.codigoPeriodoInicial = codigoPeriodoInicial;
	}

	/**
	 * @return the codigoPeriodoFinal
	 */
	public String getCodigoPeriodoFinal() {
		return codigoPeriodoFinal;
	}

	/**
	 * @param codigoPeriodoFinal the codigoPeriodoFinal to set
	 */
	public void setCodigoPeriodoFinal(String codigoPeriodoFinal) {
		this.codigoPeriodoFinal = codigoPeriodoFinal;
	}

	/**
	 * @return the tipoPeriodo
	 */
	public String getTipoPeriodo() {
		return tipoPeriodo;
	}

	/**
	 * @param tipoPeriodo the tipoPeriodo to set
	 */
	public void setTipoPeriodo(String tipoPeriodo) {
		this.tipoPeriodo = tipoPeriodo;
	}

	/**
	 * @return the operacionList
	 */
	public String[] getOperacionList() {
		return operacionList;
	}

	/**
	 * @param operacionList the operacionList to set
	 */
	public void setOperacionList(String[] operacionList) {
		this.operacionList = operacionList;
	}

	/**
	 * @return the tipoOperacionList
	 */
	public String[] getTipoOperacionList() {
		return tipoOperacionList;
	}

	/**
	 * @param tipoOperacionList the tipoOperacionList to set
	 */
	public void setTipoOperacionList(String[] tipoOperacionList) {
		this.tipoOperacionList = tipoOperacionList;
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
	 * @return the descripcionOperacionList
	 */
	public String getDescripcionOperacionList() {
		return descripcionOperacionList;
	}

	/**
	 * @param descripcionOperacionList the descripcionOperacionList to set
	 */
	public void setDescripcionOperacionList(String descripcionOperacionList) {
		this.descripcionOperacionList = descripcionOperacionList;
	}

	/**
	 * @return the descripcionTipoOperacionList
	 */
	public String getDescripcionTipoOperacionList() {
		return descripcionTipoOperacionList;
	}

	/**
	 * @param descripcionTipoOperacionList the descripcionTipoOperacionList to set
	 */
	public void setDescripcionTipoOperacionList(String descripcionTipoOperacionList) {
		this.descripcionTipoOperacionList = descripcionTipoOperacionList;
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

	/**
	 * @return the tipoReporte
	 */
	public String getTipoReporte() {
		return tipoReporte;
	}

	/**
	 * @param tipoReporte the tipoReporte to set
	 */
	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}
	
	
}
