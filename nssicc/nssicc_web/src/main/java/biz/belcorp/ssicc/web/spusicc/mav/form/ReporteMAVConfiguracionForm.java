package biz.belcorp.ssicc.web.spusicc.mav.form;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;


/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 * 
 */
public class ReporteMAVConfiguracionForm extends BaseReporteForm{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String descPais;
	private String correlativo;
	private String codigoPeriodoBusq;
	
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
	 * @return the correlativo
	 */
	public String getCorrelativo() {
		return correlativo;
	}
	/**
	 * @param correlativo the correlativo to set
	 */
	public void setCorrelativo(String correlativo) {
		this.correlativo = correlativo;
	}
	/**
	 * @return the descPais
	 */
	public String getDescPais() {
		return descPais;
	}
	/**
	 * @param descPais the descPais to set
	 */
	public void setDescPais(String descPais) {
		this.descPais = descPais;
	}
	/**
	 * @return the codigoPeriodoBusq
	 */
	public String getCodigoPeriodoBusq() {
		return codigoPeriodoBusq;
	}
	/**
	 * @param codigoPeriodoBusq the codigoPeriodoBusq to set
	 */
	public void setCodigoPeriodoBusq(String codigoPeriodoBusq) {
		this.codigoPeriodoBusq = codigoPeriodoBusq;
	}

}
