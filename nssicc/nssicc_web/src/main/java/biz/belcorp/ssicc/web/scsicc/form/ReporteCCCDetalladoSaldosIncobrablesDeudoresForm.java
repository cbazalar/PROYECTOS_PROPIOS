package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * @author Jose Pulido
 * @company Sigcomt
 *
 */
public class ReporteCCCDetalladoSaldosIncobrablesDeudoresForm extends BaseReporteForm implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codigoPais;
		
	private String codigoPeriodoHasta;
    						
	private String importeDesde;
	
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
	public String getCodigoPeriodoHasta() {
		return codigoPeriodoHasta;
	}

	/**
	 * @param codigoPeriodoHasta
	 */
	public void setCodigoPeriodoHasta(String codigoPeriodoHasta) {
		this.codigoPeriodoHasta = codigoPeriodoHasta;
	}

	/**
	 * @return
	 */
	public String getImporteDesde() {
		return importeDesde;
	}

	/**
	 * @param importeDesde
	 */
	public void setImporteDesde(String importeDesde) {
		this.importeDesde = importeDesde;
	}
}