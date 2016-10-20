package biz.belcorp.ssicc.web.spusicc.cobranzas.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoCOBSeccionesNoCriticasForm extends BaseEditForm {
	
	
	private static final long serialVersionUID = -4158422148684956047L;
	
	private String codEtapaDeuda;
	private String codigoRegion;
	private String codigoZona;
	private String codigoSeccion;
	private int indicaActivo;
	private String observacion;
	
	
	/**
	 * @return the codEtapaDeuda
	 */
	public String getCodEtapaDeuda() {
		return codEtapaDeuda;
	}
	/**
	 * @param codEtapaDeuda the codEtapaDeuda to set
	 */
	public void setCodEtapaDeuda(String codEtapaDeuda) {
		this.codEtapaDeuda = codEtapaDeuda;
	}
	/**
	 * @return the codigoRegion
	 */
	public String getCodigoRegion() {
		return codigoRegion;
	}
	/**
	 * @param codigoRegion the codigoRegion to set
	 */
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}
	/**
	 * @return the codigoZona
	 */
	public String getCodigoZona() {
		return codigoZona;
	}
	/**
	 * @param codigoZona the codigoZona to set
	 */
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}
	/**
	 * @return the codigoSeccion
	 */
	public String getCodigoSeccion() {
		return codigoSeccion;
	}
	/**
	 * @param codigoSeccion the codigoSeccion to set
	 */
	public void setCodigoSeccion(String codigoSeccion) {
		this.codigoSeccion = codigoSeccion;
	}
	/**
	 * @return the indicaActivo
	 */
	public int getIndicaActivo() {
		return indicaActivo;
	}
	/**
	 * @param indicaActivo the indicaActivo to set
	 */
	public void setIndicaActivo(int indicaActivo) {
		this.indicaActivo = indicaActivo;
	}
	/**
	 * @return the observacion
	 */
	public String getObservacion() {
		return observacion;
	}
	/**
	 * @param observacion the observacion to set
	 */
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	
	

}
