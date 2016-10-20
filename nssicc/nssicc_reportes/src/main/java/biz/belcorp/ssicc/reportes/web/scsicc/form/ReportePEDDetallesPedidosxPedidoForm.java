package biz.belcorp.ssicc.reportes.web.scsicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ReportePEDDetallesPedidosxPedidoForm extends BaseReporteForm implements Serializable {
	
	private static final long serialVersionUID = -6753122575068698219L;
	
	private String codigoPais;
	private String nroBoleta;
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
	 * @return the nroBoleta
	 */
	public String getNroBoleta() {
		return nroBoleta;
	}
	/**
	 * @param nroBoleta the nroBoleta to set
	 */
	public void setNroBoleta(String nroBoleta) {
		this.nroBoleta = nroBoleta;
	}
	
	
	

}
