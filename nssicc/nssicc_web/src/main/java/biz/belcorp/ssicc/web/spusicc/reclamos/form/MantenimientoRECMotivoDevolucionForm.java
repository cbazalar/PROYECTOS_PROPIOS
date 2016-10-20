package biz.belcorp.ssicc.web.spusicc.reclamos.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoRECMotivoDevolucionForm extends BaseEditForm{

	private static final long serialVersionUID = -1240406638676681525L;
	
	private String oidMotiDevo; 
	private String codMotiDevo;
	private String paisOidPais;
	private String indRegMod;
	private String tipMotDev;
	private String indicador;
	private String descripcionMotivo;
	private String codigoPais;
	private String indicadorCalidad;
	private String flagIndicadorCal;
	private String indicadorTipoDevolucion;
	private String codigoOperacionAnulacion;
	private String valorRecuperaDCRS;
	private String valorRecuperaPremios;
	  
	  
	
	public String getCodigoPais() {
		return codigoPais;
	}
	
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	
	public String getIndicador() {
		return indicador;
	}
	
	public void setIndicador(String indicador) {
		this.indicador = indicador;
	}
	
	public String getDescripcionMotivo() {
		return descripcionMotivo;
	}
	
	public void setDescripcionMotivo(String descripcionMotivo) {
		this.descripcionMotivo = descripcionMotivo;
	}
	
	public String getOidMotiDevo() {
		return oidMotiDevo;
	}
	
	public void setOidMotiDevo(String oidMotiDevo) {
		this.oidMotiDevo = oidMotiDevo;
	}

	public String getCodMotiDevo() {
		return codMotiDevo;
	}
	
	public void setCodMotiDevo(String codMotiDevo) {
		this.codMotiDevo = codMotiDevo;
	}
	
	public String getPaisOidPais() {
		return paisOidPais;
	}
	
	public void setPaisOidPais(String paisOidPais) {
		this.paisOidPais = paisOidPais;
	}

	public String getTipMotDev() {
		return tipMotDev;
	}

	public void setTipMotDev(String tipMotDev) {
		this.tipMotDev = tipMotDev;
	}
	
	public String getIndRegMod() {
		return indRegMod;
	}
	
	public void setIndRegMod(String indRegMod) {
		this.indRegMod = indRegMod;
	}
	
	public String getIndicadorCalidad() {
		return indicadorCalidad;
	}
	
	public void setIndicadorCalidad(String indicadorCalidad) {
		this.indicadorCalidad = indicadorCalidad;
	}
	
	public String getFlagIndicadorCal() {
		return flagIndicadorCal;
	}
	
	public void setFlagIndicadorCal(String flagIndicadorCal) {
		this.flagIndicadorCal = flagIndicadorCal;
	}

	/**
	 * @return the indicadorTipoDevolucion
	 */
	public String getIndicadorTipoDevolucion() {
		return indicadorTipoDevolucion;
	}

	/**
	 * @param indicadorTipoDevolucion the indicadorTipoDevolucion to set
	 */
	public void setIndicadorTipoDevolucion(String indicadorTipoDevolucion) {
		this.indicadorTipoDevolucion = indicadorTipoDevolucion;
	}

	/**
	 * @return the codigoOperacionAnulacion
	 */
	public String getCodigoOperacionAnulacion() {
		return codigoOperacionAnulacion;
	}

	/**
	 * @param codigoOperacionAnulacion the codigoOperacionAnulacion to set
	 */
	public void setCodigoOperacionAnulacion(String codigoOperacionAnulacion) {
		this.codigoOperacionAnulacion = codigoOperacionAnulacion;
	}

	/**
	 * @return the valorRecuperaDCRS
	 */
	public String getValorRecuperaDCRS() {
		return valorRecuperaDCRS;
	}

	/**
	 * @param valorRecuperaDCRS the valorRecuperaDCRS to set
	 */
	public void setValorRecuperaDCRS(String valorRecuperaDCRS) {
		this.valorRecuperaDCRS = valorRecuperaDCRS;
	}

	/**
	 * @return the valorRecuperaPremios
	 */
	public String getValorRecuperaPremios() {
		return valorRecuperaPremios;
	}

	/**
	 * @param valorRecuperaPremios the valorRecuperaPremios to set
	 */
	public void setValorRecuperaPremios(String valorRecuperaPremios) {
		this.valorRecuperaPremios = valorRecuperaPremios;
	} 
	
}
