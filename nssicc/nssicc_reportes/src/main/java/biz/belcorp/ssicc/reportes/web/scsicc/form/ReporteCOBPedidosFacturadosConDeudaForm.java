package biz.belcorp.ssicc.reportes.web.scsicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * 
 * @author RRG
 * 
 */

public class ReporteCOBPedidosFacturadosConDeudaForm extends BaseReporteForm
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 148584321277823367L;
	private String codigoPais;
	private String codigoPeriodoDesde;
	private String codigoPeriodoHasta;
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
	 * @return the codigoPeriodoDesde
	 */
	public String getCodigoPeriodoDesde() {
		return codigoPeriodoDesde;
	}
	/**
	 * @param codigoPeriodoDesde the codigoPeriodoDesde to set
	 */
	public void setCodigoPeriodoDesde(String codigoPeriodoDesde) {
		this.codigoPeriodoDesde = codigoPeriodoDesde;
	}
	/**
	 * @return the codigoPeriodoHasta
	 */
	public String getCodigoPeriodoHasta() {
		return codigoPeriodoHasta;
	}
	/**
	 * @param codigoPeriodoHasta the codigoPeriodoHasta to set
	 */
	public void setCodigoPeriodoHasta(String codigoPeriodoHasta) {
		this.codigoPeriodoHasta = codigoPeriodoHasta;
	}

	
}
