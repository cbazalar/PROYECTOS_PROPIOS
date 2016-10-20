package biz.belcorp.ssicc.web.spusicc.sto.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class ConsultaSTOSeguimientoPedidosForm extends BaseSearchForm implements Serializable{

	private static final long serialVersionUID = -5712184003759517441L;
	
	private String codigoPais;
	private String codigoPeriodo;
	private String codigoConsultora;
	private String fechaEntrega;
		
	private String fechaEntrega2;
	private String fechaEntrega3;
	
	private String showCodigoConsultora;
	
	public ConsultaSTOSeguimientoPedidosForm(){
		this.showCodigoConsultora = "N";
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
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}
	/**
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	/**
	 * @return the codigoConsultora
	 */
	public String getCodigoConsultora() {
		return codigoConsultora;
	}
	/**
	 */
	public void setCodigoConsultora(String codigoConsultora) {
		this.codigoConsultora = codigoConsultora;
	}
	/**
	 * @return the fechaEntrega
	 */
	public String getFechaEntrega() {
		return fechaEntrega;
	}
	/**
	 * @param fechaEntrega the fechaEntrega to set
	 */
	public void setFechaEntrega(String fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
	/**
	 * @return the showCodigoConsultora
	 */
	public String getShowCodigoConsultora() {
		return showCodigoConsultora;
	}
	/**
	 * @param showCodigoConsultora the showCodigoConsultora to set
	 */
	public void setShowCodigoConsultora(String showCodigoConsultora) {
		this.showCodigoConsultora = showCodigoConsultora;
	}

	/**
	 * @return the fechaEntrega2
	 */
	public String getFechaEntrega2() {
		return fechaEntrega2;
	}

	/**
	 * @param fechaEntrega2 the fechaEntrega2 to set
	 */
	public void setFechaEntrega2(String fechaEntrega2) {
		this.fechaEntrega2 = fechaEntrega2;
	}

	/**
	 * @return the fechaEntrega3
	 */
	public String getFechaEntrega3() {
		return fechaEntrega3;
	}

	/**
	 * @param fechaEntrega3 the fechaEntrega3 to set
	 */
	public void setFechaEntrega3(String fechaEntrega3) {
		this.fechaEntrega3 = fechaEntrega3;
	}
}