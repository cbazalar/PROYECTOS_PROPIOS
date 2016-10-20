package biz.belcorp.ssicc.web.spusicc.pedidos.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoPEDConfiguracionOfertasPorConcursosGratisForm extends BaseEditForm implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3831101147349951537L;
	private String rangoInferior;
	private String rangoSuperior;
	private String precioUnitario;
	private String oidOferta;
	
	private String cuv;
	private String unidades;
	private String codigoPeriodo;
	private String oidRango;
	/**
	 * @return the rangoInferior
	 */
	public String getRangoInferior() {
		return rangoInferior;
	}
	/**
	 * @param rangoInferior the rangoInferior to set
	 */
	public void setRangoInferior(String rangoInferior) {
		this.rangoInferior = rangoInferior;
	}
	/**
	 * @return the rangoSuperior
	 */
	public String getRangoSuperior() {
		return rangoSuperior;
	}
	/**
	 * @param rangoSuperior the rangoSuperior to set
	 */
	public void setRangoSuperior(String rangoSuperior) {
		this.rangoSuperior = rangoSuperior;
	}
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
	
	

}
