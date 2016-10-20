package biz.belcorp.ssicc.web.scsicc.form;


import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;


public class ReportePEDEstadisticasWebForm extends BaseReporteForm{
	
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	
	private String codigoPeriodo;
	
	private String tipoReporte;
	
	
	/* (non-Javadoc)
	 * @see org.apache.struts.validator.ValidatorForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	public void reset() {
		this.codigoPeriodo = null;
		this.tipoReporte = null;
	}
	
	/**
	 * @return
	 */
	public String getCodigoPais() {
		return codigoPais;
	}


	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}
	

	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	
	/**
	 * @return
	 */
	public String getTipoReporte() {
		return tipoReporte;
	}
	

	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}	
}