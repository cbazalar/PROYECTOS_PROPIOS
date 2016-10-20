package biz.belcorp.ssicc.web.spusicc.pedidos.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;


public class MantenimientoPEDConfiguracionOfertasPorFactorRepeticionGratisForm extends BaseSearchForm  implements Serializable {
			 

	private static final long serialVersionUID = 1L;
	private String factorRepeticion;
	private String precioUnitario;
	private String oidOferta;
	
	private String cuv;
	private String unidades;
	private String codigoPeriodo;
	private String oidRango;
	
	/**
	 * @return the precioUnitario
	 */
	public String getPrecioUnitario() {
		return precioUnitario;
	}
	/**
	 * @param precioUnitario the precioUnitario to set
	 */
	public void setPrecioUnitario(String precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	/**
	 * @return the oidOferta
	 */
	public String getOidOferta() {
		return oidOferta;
	}
	/**
	 * @param oidOferta the oidOferta to set
	 */
	public void setOidOferta(String oidOferta) {
		this.oidOferta = oidOferta;
	}
	/**
	 * @return the cuv
	 */
	public String getCuv() {
		return cuv;
	}
	/**
	 * @param cuv the cuv to set
	 */
	public void setCuv(String cuv) {
		this.cuv = cuv;
	}
	/**
	 * @return the unidades
	 */
	public String getUnidades() {
		return unidades;
	}
	/**
	 * @param unidades the unidades to set
	 */
	public void setUnidades(String unidades) {
		this.unidades = unidades;
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
	 * @return the factorRepeticion
	 */
	public String getFactorRepeticion() {
		return factorRepeticion;
	}
	/**
	 * @param factorRepeticion the factorRepeticion to set
	 */
	public void setFactorRepeticion(String factorRepeticion) {
		this.factorRepeticion = factorRepeticion;
	}
	/**
	 * @return the oidRango
	 */
	public String getOidRango() {
		return oidRango;
	}
	/**
	 * @param oidRango the oidRango to set
	 */
	public void setOidRango(String oidRango) {
		this.oidRango = oidRango;
	}	
}
