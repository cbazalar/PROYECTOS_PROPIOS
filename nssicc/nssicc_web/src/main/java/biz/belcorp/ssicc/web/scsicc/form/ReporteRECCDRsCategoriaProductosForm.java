package biz.belcorp.ssicc.web.scsicc.form;


import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * 
 * @author <a href="mailto:jvelasquez@sigcomt.com">Jorge Velasquez</a>
 * 
 */
public class ReporteRECCDRsCategoriaProductosForm extends BaseReporteForm{

	private static final long serialVersionUID = 1L;


	private String[] marcaList;

	private String codigoPais;

	private String codigoPeriodoInicial;

	private String codigoPeriodoFinal;
	

	private String[] codigoOperacion;


	private String[] tipoOperacionList;

	private String[] regionList;

	private String[] zonaList;

	private String[] territorioList;

	private String descripcionOperacion;

	private String descripcionTipoOperacionList;

	private String descripcionRegionList;

	private String descripcionZonaList;

	private String descripcionTerritorioList;

	private String descripcionUnidadNegocioList;

	private String descripcionNegocioList;
	
	private String descripcionMarcaList;

	private String tipoReporte;
	
	private String codigoSap;

	private Date fechaDesde;
	private Date fechaHasta;



	/**
	 * @return the fechaDesde
	 */
	public Date getFechaDesde() {
		return fechaDesde;
	}

	/**
	 * @param fechaDesde the fechaDesde to set
	 */
	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	/**
	 * @return the fechaHasta
	 */
	public Date getFechaHasta() {
		return fechaHasta;
	}

	/**
	 * @param fechaHasta the fechaHasta to set
	 */
	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	/**
	 * @return Returns the codigoOperacion.
	 */
	public String[] getCodigoOperacion() {
		return codigoOperacion;
	}

	/**
	 * @param codigoOperacion
	 *            The codigoOperacion to set.
	 */
	public void setCodigoOperacion(String []codigoOperacion) {
		this.codigoOperacion = codigoOperacion;
	}

	/**
	 * 
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
	 * @return Returns the regionList.
	 */
	public String[] getRegionList() {
		return regionList;
	}

	/**
	 * @param regionList
	 *            The regionList to set.
	 */
	public void setRegionList(String[] regionList) {
		this.regionList = regionList;
	}

	/**
	 * @return Returns the territorioList.
	 */
	public String[] getTerritorioList() {
		return territorioList;
	}

	/**
	 * @param territorioList
	 *            The territorioList to set.
	 */
	public void setTerritorioList(String[] territorioList) {
		this.territorioList = territorioList;
	}

	/**
	 * @return Returns the tipoOperacionList.
	 */
	public String[] getTipoOperacionList() {
		return tipoOperacionList;
	}

	/**
	 * @param tipoOperacionList
	 *            The tipoOperacionList to set.
	 */
	public void setTipoOperacionList(String[] tipoOperacionList) {
		this.tipoOperacionList = tipoOperacionList;
	}

	/**
	 * @return Returns the tipoReporte.
	 */
	public String getTipoReporte() {
		return tipoReporte;
	}

	/**
	 * @param tipoReporte
	 *            The tipoReporte to set.
	 */
	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}

	/**
	 * @return Returns the zonaList.
	 */
	public String[] getZonaList() {
		return zonaList;
	}

	/**
	 * @param zonaList
	 *            The zonaList to set.
	 */
	public void setZonaList(String[] zonaList) {
		this.zonaList = zonaList;
	}

	/**
	 * @return Returns the marcaList.
	 */
	public String[] getMarcaList() {
		return marcaList;
	}

	/**
	 * @param marcaList The marcaList to set.
	 */
	public void setMarcaList(String[] marcaList) {
		this.marcaList = marcaList;
	}

	/**
	 * @return Returns the codigoPeriodoFinal.
	 */
	public String getCodigoPeriodoFinal() {
		return codigoPeriodoFinal;
	}

	/**
	 * @param codigoPeriodoFinal The codigoPeriodoFinal to set.
	 */
	public void setCodigoPeriodoFinal(String codigoPeriodoFinal) {
		this.codigoPeriodoFinal = codigoPeriodoFinal;
	}

	/**
	 * @return Returns the codigoPeriodoInicial.
	 */
	public String getCodigoPeriodoInicial() {
		return codigoPeriodoInicial;
	}

	/**
	 * @param codigoPeriodoInicial The codigoPeriodoInicial to set.
	 * 
	 */
	public void setCodigoPeriodoInicial(String codigoPeriodoInicial) {
		this.codigoPeriodoInicial = codigoPeriodoInicial;
	}

	/**
	 * @return Returns the descripcionNegocioList.
	 */
	public String getDescripcionNegocioList() {
		return descripcionNegocioList;
	}
	/**
	 * @param descripcionNegocioList The descripcionNegocioList to set.
	 */
	public void setDescripcionNegocioList(String descripcionNegocioList) {
		String temp = StringUtils.replace(descripcionNegocioList, "&&","\n" );
		this.descripcionNegocioList = temp;
	}
	/**
	 * @return Returns the descripcionOperacion.
	 */
	public String getDescripcionOperacion() {
		return descripcionOperacion;
	}
	/**
	 * @param descripcionOperacion The descripcionOperacion to set.
	 */
	public void setDescripcionOperacion(String descripcionOperacion) {
		String temp = StringUtils.replace(descripcionOperacion, "&&","\n" );
		this.descripcionOperacion = temp;
	}
	/**
	 * @return Returns the descripcionRegionList.
	 */
	public String getDescripcionRegionList() {
		return descripcionRegionList;
	}
	/**
	 * @param descripcionRegionList The descripcionRegionList to set.
	 */
	public void setDescripcionRegionList(String descripcionRegionList) {
		String temp = StringUtils.replace(descripcionRegionList, "&&","\n" );
		this.descripcionRegionList = temp;
	}
	/**
	 * @return Returns the descripcionTerritorioList.
	 */
	public String getDescripcionTerritorioList() {
		return descripcionTerritorioList;
	}
	/**
	 * @param descripcionTerritorioList The descripcionTerritorioList to set.
	 */
	public void setDescripcionTerritorioList(String descripcionTerritorioList) {
		String temp = StringUtils.replace(descripcionTerritorioList, "&&","\n" );
		this.descripcionTerritorioList = temp;
	}
	/**
	 * @return Returns the descripcionTipoOperacionList.
	 */
	public String getDescripcionTipoOperacionList() {
		return descripcionTipoOperacionList;
	}
	/**
	 * @param descripcionTipoOperacionList The descripcionTipoOperacionList to set.
	 */
	public void setDescripcionTipoOperacionList(String descripcionTipoOperacionList) {
		String temp = StringUtils.replace(descripcionTipoOperacionList, "&&","\n" );
		this.descripcionTipoOperacionList = temp;
	}
	/**
	 * @return Returns the descripcionUnidadNegocioList.
	 */
	public String getDescripcionUnidadNegocioList() {
		return descripcionUnidadNegocioList;
	}
	/**
	 * @param descripcionUnidadNegocioList The descripcionUnidadNegocioList to set.
	 */
	public void setDescripcionUnidadNegocioList(String descripcionUnidadNegocioList) {
		String temp = StringUtils.replace(descripcionUnidadNegocioList, "&&","\n" );
		this.descripcionUnidadNegocioList = temp;
	}
	/**
	 * @return Returns the descripcionZonaList.
	 */
	public String getDescripcionZonaList() {
		return descripcionZonaList;
	}
	/**
	 * @param descripcionZonaList The descripcionZonaList to set.
	 */
	public void setDescripcionZonaList(String descripcionZonaList) {
		String temp = StringUtils.replace(descripcionZonaList, "&&","\n" );
		this.descripcionZonaList = temp;
	}
	/**
	 * @return Returns the descripcionMarcaList.
	 */
	public String getDescripcionMarcaList() {
		return descripcionMarcaList;
	}
	/**
	 * @param descripcionMarcaList The descripcionMarcaList to set.
	 */
	public void setDescripcionMarcaList(String descripcionMarcaList) {
		String temp = StringUtils.replace(descripcionMarcaList, "&&","\n" );
		this.descripcionMarcaList = temp;
	}

	/**
	 * @return the codigoSap
	 */
	public String getCodigoSap() {
		return codigoSap;
	}
	/**
	 * @param codigoSap the codigoSap to set
	 */
	public void setCodigoSap(String codigoSap) {
		this.codigoSap = codigoSap;
	}
	
}
