package biz.belcorp.ssicc.reportes.web.scsicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ReporteCOMDetalleComisionRecuperacionForm extends BaseReporteForm
		implements Serializable {

	private static final long serialVersionUID = 3786001564335441692L;

	private String codigoPais;
	
	private String descPais;

	private String codigoComision;

	private String codigoPeriodo;

	private String codigoRegion;

	private String[] zonaList;

	private String tipoReporte;
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm#getCodigoPais()
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm#setCodigoPais(java.lang.String)
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return
	 */
	public String getDescPais() {
		return descPais;
	}

	/**
	 * @param descPais
	 */
	public void setDescPais(String descPais) {
		this.descPais = descPais;
	}

	/**
	 * @return
	 */
	public String getCodigoComision() {
		return codigoComision;
	}

	/**
	 * @param codigoComision
	 */
	public void setCodigoComision(String codigoComision) {
		this.codigoComision = codigoComision;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm#getCodigoPeriodo()
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm#setCodigoPeriodo(java.lang.String)
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return
	 */
	public String getCodigoRegion() {
		return codigoRegion;
	}

	/**
	 * @param codigoRegion
	 */
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}

	/**
	 * @return
	 */
	public String[] getZonaList() {
		return zonaList;
	}

	/**
	 * @param zonaList
	 */
	public void setZonaList(String[] zonaList) {
		this.zonaList = zonaList;
	}

	/**
	 * @return
	 */
	public String getTipoReporte() {
		return tipoReporte;
	}

	/**
	 * @param tipoReporte
	 */
	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}

	
}
