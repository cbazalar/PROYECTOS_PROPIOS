package biz.belcorp.ssicc.web.spusicc.mae.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoMAEBloqueoDesbloqueoClienteForm extends BaseEditForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7733653449119417802L;
	protected String codigoPais;
	protected String codigoCliente;
	protected String nombreCliente;
	protected String tipoBloqueo;
	protected String motivoBloqueo;
	protected String observacionesBloqueo;	
	protected Long oidCliente;
	protected String observacionesDesbloqueo;
	protected Long oidBloqueo;
	protected String fechaDesbloqueo;
	protected String usuarioDesbloqueo;
	
	protected String indicadorSolicitudCredito;
	
	protected String codigoArea;
	protected String descripcionArea;
	protected String indicadorDesbloqueo;
	protected String desIndicadorDesbloqueo;
	
	protected boolean bloqueoForm = false;
	
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}


	/**
	 * @param codigoPais the codigoPais to set
	 * 
	 * @struts.validator type="required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}


	/**
	 * @return the codigoCliente
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}


	/**
	 * @param codigoClienteBuscar the codigoCliente to set
	 * 
	 * @struts.validator type="required"
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}


	/**
	 * @return the nombreCliente
	 */
	public String getNombreCliente() {
		return nombreCliente;
	}


	/**
	 * @param nombreClienteBuscar the nombreCliente to set
	 */
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	/**
	 * @return the tipoBloqueo
	 */
	public String getTipoBloqueo() {
		return tipoBloqueo;
	}


	/**
	 * @param tipoBloqueo the tipoBloqueo to set
	 * 
	 * @struts.validator type="required"
	 */
	public void setTipoBloqueo(String tipoBloqueo) {
		this.tipoBloqueo = tipoBloqueo;
	}


	/**
	 * @return the motivoBloqueo
	 */
	public String getMotivoBloqueo() {
		return motivoBloqueo;
	}


	/**
	 * @param motivoBloqueo the motivoBloqueo to set
	 * 
	 * @struts.validator type="required"
	 */
	public void setMotivoBloqueo(String motivoBloqueo) {
		this.motivoBloqueo = motivoBloqueo;
	}


	/**
	 * @return the observacionesBloqueo
	 */
	public String getObservacionesBloqueo() {
		return observacionesBloqueo;
	}


	/**
	 * @param observacionesBloqueo the observacionesBloqueo to set
	 * 
	 * @struts.validator type="required"
	 */
	public void setObservacionesBloqueo(String observacionesBloqueo) {
		this.observacionesBloqueo = observacionesBloqueo;
	}
	

	/**
	 * @return the bloqueoForm
	 */
	public boolean getBloqueoForm() {
		return bloqueoForm;
	}


	/**
	 * @param bloqueoForm the bloqueoForm to set
	 */
	public void setBloqueoForm(boolean bloqueoForm) {
		this.bloqueoForm = bloqueoForm;
	}


	/**
	 * @return the oidCliente
	 */
	public Long getOidCliente() {
		return oidCliente;
	}


	/**
	 * @param oidCliente the oidCliente to set
	 * 
	 * @struts.validator type="required"
	 */
	public void setOidCliente(Long oidCliente) {
		this.oidCliente = oidCliente;
	}


	/**
	 * @return the observacionesDesbloqueo
	 */
	public String getObservacionesDesbloqueo() {
		return observacionesDesbloqueo;
	}


	/**
	 * @param observacionesDesbloqueo the observacionesDesbloqueo to set
	 * 
	 * @struts.validator type="required"
	 */
	public void setObservacionesDesbloqueo(String observacionesDesbloqueo) {
		this.observacionesDesbloqueo = observacionesDesbloqueo;
	}


	/**
	 * @return the oidBloqueo
	 */
	public Long getOidBloqueo() {
		return oidBloqueo;
	}


	/**
	 * @param oidBloqueo the oidBloqueo to set
	 */
	public void setOidBloqueo(Long oidBloqueo) {
		this.oidBloqueo = oidBloqueo;
	}


	/**
	 * @return the fechaDesbloqueo
	 */
	public String getFechaDesbloqueo() {
		return fechaDesbloqueo;
	}


	/**
	 * @param fechaDesbloqueo the fechaDesbloqueo to set
	 */
	public void setFechaDesbloqueo(String fechaDesbloqueo) {
		this.fechaDesbloqueo = fechaDesbloqueo;
	}


	/**
	 * @return the usuarioDesbloqueo
	 */
	public String getUsuarioDesbloqueo() {
		return usuarioDesbloqueo;
	}


	/**
	 * @param usuarioDesbloqueo the usuarioDesbloqueo to set
	 */
	public void setUsuarioDesbloqueo(String usuarioDesbloqueo) {
		this.usuarioDesbloqueo = usuarioDesbloqueo;
	}


	/**
	 * @return the indicadorSolicitudCredito
	 */
	public String getIndicadorSolicitudCredito() {
		return indicadorSolicitudCredito;
	}


	/**
	 * @param indicadorSolicitudCredito the indicadorSolicitudCredito to set
	 */
	public void setIndicadorSolicitudCredito(String indicadorSolicitudCredito) {
		this.indicadorSolicitudCredito = indicadorSolicitudCredito;
	}


	/**
	 * @return the codigoArea
	 */
	public String getCodigoArea() {
		return codigoArea;
	}


	/**
	 * @param codigoArea the codigoArea to set
	 */
	public void setCodigoArea(String codigoArea) {
		this.codigoArea = codigoArea;
	}


	/**
	 * @return the descripcionArea
	 */
	public String getDescripcionArea() {
		return descripcionArea;
	}


	/**
	 * @param descripcionArea the descripcionArea to set
	 */
	public void setDescripcionArea(String descripcionArea) {
		this.descripcionArea = descripcionArea;
	}


	/**
	 * @return the indicadorDesbloqueo
	 */
	public String getIndicadorDesbloqueo() {
		return indicadorDesbloqueo;
	}


	/**
	 * @param indicadorDesbloqueo the indicadorDesbloqueo to set
	 */
	public void setIndicadorDesbloqueo(String indicadorDesbloqueo) {
		this.indicadorDesbloqueo = indicadorDesbloqueo;
	}


	/**
	 * @return the desIndicadorDesbloqueo
	 */
	public String getDesIndicadorDesbloqueo() {
		return desIndicadorDesbloqueo;
	}


	/**
	 * @param desIndicadorDesbloqueo the desIndicadorDesbloqueo to set
	 */
	public void setDesIndicadorDesbloqueo(String desIndicadorDesbloqueo) {
		this.desIndicadorDesbloqueo = desIndicadorDesbloqueo;
	}
}
