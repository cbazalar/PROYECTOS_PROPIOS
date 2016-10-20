/**
 * 
 */
package biz.belcorp.ssicc.reportes.web.scsicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * @author fochoa
 *
 */
public class ReporteCOBHistoricoOperacionCrediticiaForm extends BaseReporteForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5726788764482797197L;

	private String codigoPais;
	private String anhio;
	private String mes;
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
	 * @return the anhio
	 */
	public String getAnhio() {
		return anhio;
	}
	/**
	 * @param anhio the anhio to set
	 */
	public void setAnhio(String anhio) {
		this.anhio = anhio;
	}
	/**
	 * @return the mes
	 */
	public String getMes() {
		return mes;
	}
	/**
	 * @param mes the mes to set
	 */
	public void setMes(String mes) {
		this.mes = mes;
	}

}
