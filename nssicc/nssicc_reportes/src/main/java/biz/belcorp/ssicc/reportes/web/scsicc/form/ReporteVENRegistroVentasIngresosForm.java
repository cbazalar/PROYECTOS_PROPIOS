package biz.belcorp.ssicc.reportes.web.scsicc.form;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli</a>
 * 
 */
public class ReporteVENRegistroVentasIngresosForm extends BaseReporteForm  {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6677241887498281347L;
	private String codigoPais;
	private String codigoPeriodoInformar;
	private String codigoPeriodoEnviar;
	private String accion; 

	/**
	 * @return the accion
	 */
	public String getAccion() {
		return accion;
	}

	/**
	 * @param accion the accion to set
	 */
	public void setAccion(String accion) {
		this.accion = accion;
	}

	/**
	 * @return the codigoPeriodoInformar
	 */
	public String getCodigoPeriodoInformar() {
		return codigoPeriodoInformar;
	}

	/**
	 * @param codigoPeriodoInformar the codigoPeriodoInformar to set
	 */
	public void setCodigoPeriodoInformar(String codigoPeriodoInformar) {
		this.codigoPeriodoInformar = codigoPeriodoInformar;
	}

	/**
	 * @return the codigoPeriodoEnviar
	 */
	public String getCodigoPeriodoEnviar() {
		return codigoPeriodoEnviar;
	}

	/**
	 * @param codigoPeriodoEnviar the codigoPeriodoEnviar to set
	 */
	public void setCodigoPeriodoEnviar(String codigoPeriodoEnviar) {
		this.codigoPeriodoEnviar = codigoPeriodoEnviar;
	}

	

	/**
	 * @return
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 * @struts.validator type="required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}


}