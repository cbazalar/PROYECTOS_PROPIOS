package biz.belcorp.ssicc.reportes.web.scsicc.form;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ReporteCOBPrimerPedidoConDeudaForm extends BaseReporteForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1458483645937259106L;
	private String codigoPais;
	private String campanaDesde;
	private String campanaHasta;
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
	 * @return the campanaDesde
	 */
	public String getCampanaDesde() {
		return campanaDesde;
	}
	/**
	 * @param campanaDesde the campanaDesde to set
	 */
	public void setCampanaDesde(String campanaDesde) {
		this.campanaDesde = campanaDesde;
	}
	/**
	 * @return the campanaHasta
	 */
	public String getCampanaHasta() {
		return campanaHasta;
	}
	/**
	 * @param campanaHasta the campanaHasta to set
	 */
	public void setCampanaHasta(String campanaHasta) {
		this.campanaHasta = campanaHasta;
	}
	
	

	
}


