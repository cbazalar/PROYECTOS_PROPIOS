package biz.belcorp.ssicc.web.spusicc.pedidos.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoPEDUnidadesAdministrativasChequeoForm extends BaseSearchForm{
	
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String codigoPeriodo;
	private String codigoTipoChequeo;
	private String codigoRegion;
	private String codigoZona;
	private String codigoSeccion;
	private String codigoTerritorio;

	/**
	 * @return
	 */
	public String getCodigoRegion() {
		return codigoRegion;
	}

	/**
	 * @param codigoRegion	 
	 */
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}

	public String getCodigoPais() {
		return codigoPais;
	}
	
	/**
	 * @param codigoPais
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	
	/**
	 * @return
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}
	
	/**
	 * @param codigoPeriodo
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	
	/**
	 * @return
	 */
	public String getCodigoTipoChequeo() {
		return codigoTipoChequeo;
	}
	/**
	 * @param codigoTipoChequeo
	 */
	public void setCodigoTipoChequeo(String codigoTipoChequeo) {
		this.codigoTipoChequeo = codigoTipoChequeo;
	}
	
	/**
	 * @return
	 */
	public String getCodigoZona() {
		return codigoZona;
	}
	
	/**
	 * @param codigoZona
	 */
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}
	
	/**
	 * @return
	 */
	public String getCodigoSeccion() {
		return codigoSeccion;
	}
	
	/**
	 * @param codigoSeccion
	 */
	public void setCodigoSeccion(String codigoSeccion) {
		this.codigoSeccion = codigoSeccion;
	}
	
	/**
	 * @return
	 */
	public String getCodigoTerritorio() {
		return codigoTerritorio;
	}
	
	/**
	 * @param codigoTerritorio
	 */
	public void setCodigoTerritorio(String codigoTerritorio) {
		this.codigoTerritorio = codigoTerritorio;
	}
}