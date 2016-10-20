package biz.belcorp.ssicc.reportes.web.scsicc.form;

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

public class ReporteRECListadoReclamosForm extends BaseReporteForm implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6436843627073702960L;
	private String[] marcaList;
	private String codigoPais;
	private String codigoPeriodoInicial;
	private String codigoPeriodoFinal;
	private String tipoPeriodo;
	private String[] codigoOperacion;
	private String[] tipoOperacionList;
	private String[] regionList;
	private String[] zonaList;
	private String[] territorioList;
	private String[] unidadNegocioList;
	private String[] negocioList;
	private String codigoConsultora;
	private String descripcionConsultora;
	private String descripcionOperacion;
	private String descripcionTipoOperacionList;
	private String descripcionRegionList;
	private String descripcionZonaList;
	private String descripcionTerritorioList;
	private String descripcionUnidadNegocioList;
	private String descripcionNegocioList;
	private String descripcionMarcaList;
	private String descripcionMotivoDevolucionList;
	private String tipoReporte;
	private String tipoMovimiento;
	
	/**
	 * Campos por Default
	 */
	public ReporteRECListadoReclamosForm() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String periodo = sdf.format(new Date(System.currentTimeMillis()));

		if (StringUtils.isEmpty(this.codigoPeriodoInicial))
			this.codigoPeriodoInicial = periodo;

		if (StringUtils.isEmpty(this.codigoPeriodoFinal))
			this.codigoPeriodoFinal = periodo;

	}
	
	/**
	 * @return the marcaList
	 */
	public String[] getMarcaList() {
		return marcaList;
	}
	/**
	 * @param marcaList the marcaList to set
	 */
	public void setMarcaList(String[] marcaList) {
		this.marcaList = marcaList;
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
	 * @return the codigoOperacion
	 */
	public String[] getCodigoOperacion() {
		return codigoOperacion;
	}
	/**
	 * @param codigoOperacion the codigoOperacion to set
	 */
	public void setCodigoOperacion(String[] codigoOperacion) {
		this.codigoOperacion = codigoOperacion;
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
	 * @return the territorioList
	 */
	public String[] getTerritorioList() {
		return territorioList;
	}
	/**
	 * @param territorioList the territorioList to set
	 */
	public void setTerritorioList(String[] territorioList) {
		this.territorioList = territorioList;
	}
	/**
	 * @return the unidadNegocioList
	 */
	public String[] getUnidadNegocioList() {
		return unidadNegocioList;
	}
	/**
	 * @param unidadNegocioList the unidadNegocioList to set
	 */
	public void setUnidadNegocioList(String[] unidadNegocioList) {
		this.unidadNegocioList = unidadNegocioList;
	}
	/**
	 * @return the negocioList
	 */
	public String[] getNegocioList() {
		return negocioList;
	}
	/**
	 * @param negocioList the negocioList to set
	 */
	public void setNegocioList(String[] negocioList) {
		this.negocioList = negocioList;
	}
	/**
	 * @return the codigoConsultora
	 */
	public String getCodigoConsultora() {
		return codigoConsultora;
	}
	/**
	 * @param codigoConsultora the codigoConsultora to set
	 */
	public void setCodigoConsultora(String codigoConsultora) {
		this.codigoConsultora = codigoConsultora;
	}
	/**
	 * @return the descripcionConsultora
	 */
	public String getDescripcionConsultora() {
		return descripcionConsultora;
	}
	/**
	 * @param descripcionConsultora the descripcionConsultora to set
	 */
	public void setDescripcionConsultora(String descripcionConsultora) {
		this.descripcionConsultora = descripcionConsultora;
	}
	/**
	 * @return the descripcionOperacion
	 */
	public String getDescripcionOperacion() {
		return descripcionOperacion;
	}
	/**
	 * @param descripcionOperacion the descripcionOperacion to set
	 */
	public void setDescripcionOperacion(String descripcionOperacion) {
		this.descripcionOperacion = descripcionOperacion;
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
	 * @return the descripcionTerritorioList
	 */
	public String getDescripcionTerritorioList() {
		return descripcionTerritorioList;
	}
	/**
	 * @param descripcionTerritorioList the descripcionTerritorioList to set
	 */
	public void setDescripcionTerritorioList(String descripcionTerritorioList) {
		this.descripcionTerritorioList = descripcionTerritorioList;
	}
	/**
	 * @return the descripcionUnidadNegocioList
	 */
	public String getDescripcionUnidadNegocioList() {
		return descripcionUnidadNegocioList;
	}
	/**
	 * @param descripcionUnidadNegocioList the descripcionUnidadNegocioList to set
	 */
	public void setDescripcionUnidadNegocioList(String descripcionUnidadNegocioList) {
		this.descripcionUnidadNegocioList = descripcionUnidadNegocioList;
	}
	/**
	 * @return the descripcionNegocioList
	 */
	public String getDescripcionNegocioList() {
		return descripcionNegocioList;
	}
	/**
	 * @param descripcionNegocioList the descripcionNegocioList to set
	 */
	public void setDescripcionNegocioList(String descripcionNegocioList) {
		this.descripcionNegocioList = descripcionNegocioList;
	}
	/**
	 * @return the descripcionMarcaList
	 */
	public String getDescripcionMarcaList() {
		return descripcionMarcaList;
	}
	/**
	 * @param descripcionMarcaList the descripcionMarcaList to set
	 */
	public void setDescripcionMarcaList(String descripcionMarcaList) {
		this.descripcionMarcaList = descripcionMarcaList;
	}
	/**
	 * @return the descripcionMotivoDevolucionList
	 */
	public String getDescripcionMotivoDevolucionList() {
		return descripcionMotivoDevolucionList;
	}
	/**
	 * @param descripcionMotivoDevolucionList the descripcionMotivoDevolucionList to set
	 */
	public void setDescripcionMotivoDevolucionList(
			String descripcionMotivoDevolucionList) {
		this.descripcionMotivoDevolucionList = descripcionMotivoDevolucionList;
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
	/**
	 * @return the tipoMovimiento
	 */
	public String getTipoMovimiento() {
		return tipoMovimiento;
	}
	/**
	 * @param tipoMovimiento the tipoMovimiento to set
	 */
	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}
	
	
	

}
