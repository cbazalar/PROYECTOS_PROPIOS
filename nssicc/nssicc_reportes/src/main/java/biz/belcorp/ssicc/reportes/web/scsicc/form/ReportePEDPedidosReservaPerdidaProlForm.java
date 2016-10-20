package biz.belcorp.ssicc.reportes.web.scsicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ReportePEDPedidosReservaPerdidaProlForm extends BaseReporteForm implements Serializable {

	private static final long serialVersionUID = -5266581632286058669L;
	
	
	private String codigoPais;
	private String codigoSociedad;
	private String codigoPeriodo;
	private String codTipoReporte;	
	
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
	 * @return the codTipoReporte
	 */
	public String getCodTipoReporte() {
		return codTipoReporte;
	}
	/**
	 * @param codTipoReporte the codTipoReporte to set
	 */
	public void setCodTipoReporte(String codTipoReporte) {
		this.codTipoReporte = codTipoReporte;
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
	
	
}
