package biz.belcorp.ssicc.web.spusicc.sto.form;

import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoSTOOrdenCompraCabeceraForm extends BaseMantenimientoSTOGestionForm{
	
	private static final long serialVersionUID = -7766218846481670568L;
	
	private String codigoPais;
	private String codPeriodo;
	private String codCliente;
	private String fechaSolicitud;
	private Date fechaSolicitudDate;
	private Integer numCliente;
	private String tipoSolicitud;
	private String codSubAcceso;
	private String codAcceso;
	private String tipoDespacho;
	private String estadoProceso;
	private String numLote;
	private String codTipoDocumento;
	private String numDocumento;
	private String codMotivoRechazo;
	private String codRegion;
	private String codZona;
	private String numSecuencia;
	private String detalle;
	private String montoPedido;
	private Integer unidades;
	private String codigoVenta;
	private String []selectedItems; 
	private String montoMaximo;
	private String montoMinimo;
	private boolean nuevo=false;
	private String oidPais;
	private String unidadesMaximas;
	private String desMensajeError;
	private String oidZona;
	private String indicadorModificarConsultora;
	private String codClienteBD;
	private String indicadorGuardar;
	private String codigoZonaArribo;

	/* INI JR PER-SiCC-2012-0444 */
	private String montoFlexipago;
	private String utilizaFlexipago;
	private String aceptaFlexipago;
	private String muestraFlexipago;
	/* FIN JR PER-SiCC-2012-0444 */
	
	private boolean indicadorPROL;
	
	private String indicadorValidaDocIdentidad;
	private String longitudNumeroDocIdentidad;
	private String codClienteAux;
	private String oidIdioma;
	
	private String indViewMotiGest;
	private String oidMotiGes;
	private String valObseGestion;
	private boolean mostrarMotivoObservacion;
	
	private String resultadoDataCredi;
	private String estadoDatacredi;
	private boolean mostrarValidaDataCredi;

	
	public String getCodAcceso() {
		return codAcceso;
	}
	
	public void setCodAcceso(String codAcceso) {
		this.codAcceso = codAcceso;
	}
	
	public String getCodCliente() {
		return codCliente;
	}
	
	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
	}
	
	public String getCodMotivoRechazo() {
		return codMotivoRechazo;
	}
	
	public void setCodMotivoRechazo(String codMotivoRechazo) {
		this.codMotivoRechazo = codMotivoRechazo;
	}
	
	public String getCodPeriodo() {
		return codPeriodo;
	}
	
	public void setCodPeriodo(String codPeriodo) {
		this.codPeriodo = codPeriodo;
	}
	
	public String getCodRegion() {
		return codRegion;
	}
	
	public void setCodRegion(String codRegion) {
		this.codRegion = codRegion;
	}
	
	public String getCodSubAcceso() {
		return codSubAcceso;
	}
	
	public void setCodSubAcceso(String codSubAcceso) {
		this.codSubAcceso = codSubAcceso;
	}
	
	public String getCodTipoDocumento() {
		return codTipoDocumento;
	}
	
	public void setCodTipoDocumento(String codTipoDocumento) {
		this.codTipoDocumento = codTipoDocumento;
	}
	
	public String getCodZona() {
		return codZona;
	}
	
	public void setCodZona(String codZona) {
		this.codZona = codZona;
	}
	
	public String getEstadoProceso() {
		return estadoProceso;
	}
	
	public void setEstadoProceso(String estadoProceso) {
		this.estadoProceso = estadoProceso;
	}
	
	public String getFechaSolicitud() {
		return fechaSolicitud;
	}
	
	public void setFechaSolicitud(String fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}
	
	public Integer getNumCliente() {
		return numCliente;
	}
	
	public void setNumCliente(Integer numCliente) {
		this.numCliente = numCliente;
	}
	
	public String getNumDocumento() {
		return numDocumento;
	}
	
	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}
	
	public String getNumLote() {
		return numLote;
	}
	
	public void setNumLote(String numLote) {
		this.numLote = numLote;
	}
	
	public String getTipoDespacho() {
		return tipoDespacho;
	}
	
	public void setTipoDespacho(String tipoDespacho) {
		this.tipoDespacho = tipoDespacho;
	}
	
	public String getTipoSolicitud() {
		return tipoSolicitud;
	}
	
	public void setTipoSolicitud(String tipoSolicitud) {
		this.tipoSolicitud = tipoSolicitud;
	}
	
	public String getCodigoPais() {
		return codigoPais;
	}
	
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	
	public String getNumSecuencia() {
		return numSecuencia;
	}
	
	public void setNumSecuencia(String numSecuencia) {
		this.numSecuencia = numSecuencia;
	}
	
	public String getDetalle() {
		return detalle;
	}
	
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	
	public String getCodigoVenta() {
		return codigoVenta;
	}
	
	public void setCodigoVenta(String codigoVenta) {
		this.codigoVenta = codigoVenta;
	}
	
	public String getMontoPedido() {
		return montoPedido;
	}
	
	public void setMontoPedido(String montoPedido) {
		this.montoPedido = montoPedido;
	}
	
	public Integer getUnidades() {
		return unidades;
	}
	
	public void setUnidades(Integer unidades) {
		this.unidades = unidades;
	}
	
	public String[] getSelectedItems() {
		return selectedItems;
	}
	
	public void setSelectedItems(String[] selectedItems) {
		this.selectedItems = selectedItems;
	}
	
	public String getMontoMaximo() {
		return montoMaximo;
	}
	
	public void setMontoMaximo(String montoMaximo) {
		this.montoMaximo = montoMaximo;
	}
	
	public String getMontoMinimo() {
		return montoMinimo;
	}
	
	public void setMontoMinimo(String montoMinimo) {
		this.montoMinimo = montoMinimo;
	}
	
	public boolean isNuevo() {
		return nuevo;
	}
	
	public void setNuevo(boolean nuevo) {
		this.nuevo = nuevo;
	}
	public String getOidPais() {
		return oidPais;
	}
	public void setOidPais(String oidPais) {
		this.oidPais = oidPais;
	}
	
	public String getUnidadesMaximas() {
		return unidadesMaximas;
	}
	
	public void setUnidadesMaximas(String unidadesMaximas) {
		this.unidadesMaximas = unidadesMaximas;
	}
	
	public String getDesMensajeError() {
		return desMensajeError;
	}
	public void setDesMensajeError(String desMensajeError) {
		this.desMensajeError = desMensajeError;
	}
	public String getOidZona() {
		return oidZona;
	}
	public void setOidZona(String oidZona) {
		this.oidZona = oidZona;
	}
	
	public String getIndicadorModificarConsultora() {
		return indicadorModificarConsultora;
	}
	
	public void setIndicadorModificarConsultora(String indicadorModificarConsultora) {
		this.indicadorModificarConsultora = indicadorModificarConsultora;
	}
	
	public String getCodClienteBD() {
		return codClienteBD;
	}
	
	public void setCodClienteBD(String codClienteBD) {
		this.codClienteBD = codClienteBD;
	}
	
	public String getIndicadorGuardar() {
		return indicadorGuardar;
	}
	
	public void setIndicadorGuardar(String indicadorGuardar) {
		this.indicadorGuardar = indicadorGuardar;
	}
	
	public String getCodigoZonaArribo() {
		return codigoZonaArribo;
	}
	
	public void setCodigoZonaArribo(String codigoZonaArribo) {
		this.codigoZonaArribo = codigoZonaArribo;
	}

	/* INI JR PER-SiCC-2012-0444 */
	
	public String getMontoFlexipago() {
		return montoFlexipago;
	}
	
	public void setMontoFlexipago(String montoFlexipago) {
		this.montoFlexipago = montoFlexipago;
	}
	
	public String getUtilizaFlexipago() {
		return utilizaFlexipago;
	}
	
	public void setUtilizaFlexipago(String utilizaFlexipago) {
		this.utilizaFlexipago = utilizaFlexipago;
	}
	
	public String getAceptaFlexipago() {
		return aceptaFlexipago;
	}
	
	public void setAceptaFlexipago(String aceptaFlexipago) {
		this.aceptaFlexipago = aceptaFlexipago;
	}

	public String getMuestraFlexipago() {
		return muestraFlexipago;
	}
	
	public void setMuestraFlexipago(String muestraFlexipago) {
		this.muestraFlexipago = muestraFlexipago;
	}
	/* FIN JR PER-SiCC-2012-0444 */
	
	public boolean isIndicadorPROL() {
		return indicadorPROL;
	}
	
	public void setIndicadorPROL(boolean indicadorPROL) {
		this.indicadorPROL = indicadorPROL;
	}
	
	public String getLongitudNumeroDocIdentidad() {
		return longitudNumeroDocIdentidad;
	}
	
	public void setLongitudNumeroDocIdentidad(String longitudNumeroDocIdentidad) {
		this.longitudNumeroDocIdentidad = longitudNumeroDocIdentidad;
	}
	
	public String getCodClienteAux() {
		return codClienteAux;
	}
	
	public void setCodClienteAux(String codClienteAux) {
		this.codClienteAux = codClienteAux;
	}
	
	public String getIndicadorValidaDocIdentidad() {
		return indicadorValidaDocIdentidad;
	}
	
	public void setIndicadorValidaDocIdentidad(String indicadorValidaDocIdentidad) {
		this.indicadorValidaDocIdentidad = indicadorValidaDocIdentidad;
	}
	
	public String getOidIdioma() {
		return oidIdioma;
	}
	
	public void setOidIdioma(String oidIdioma) {
		this.oidIdioma = oidIdioma;
	}
	
	public String getIndViewMotiGest() {
		return indViewMotiGest;
	}
	
	public void setIndViewMotiGest(String indViewMotiGest) {
		this.indViewMotiGest = indViewMotiGest;
	}
	
	public String getOidMotiGes() {
		return oidMotiGes;
	}
	
	public void setOidMotiGes(String oidMotiGes) {
		this.oidMotiGes = oidMotiGes;
	}
	
	public String getValObseGestion() {
		return valObseGestion;
	}
	
	public void setValObseGestion(String valObseGestion) {
		this.valObseGestion = valObseGestion;
	}
	
	public boolean isMostrarMotivoObservacion() {
		return mostrarMotivoObservacion;
	}
	
	public void setMostrarMotivoObservacion(boolean mostrarMotivoObservacion) {
		this.mostrarMotivoObservacion = mostrarMotivoObservacion;
	}

	public Date getFechaSolicitudDate() {
		return fechaSolicitudDate;
	}

	public void setFechaSolicitudDate(Date fechaSolicitudDate) {
		this.fechaSolicitudDate = fechaSolicitudDate;
	}

	/**
	 * @return the resultadoDataCredi
	 */
	public String getResultadoDataCredi() {
		return resultadoDataCredi;
	}

	/**
	 * @param resultadoDataCredi the resultadoDataCredi to set
	 */
	public void setResultadoDataCredi(String resultadoDataCredi) {
		this.resultadoDataCredi = resultadoDataCredi;
	}

	/**
	 * @return the estadoDatacredi
	 */
	public String getEstadoDatacredi() {
		return estadoDatacredi;
	}

	/**
	 * @param estadoDatacredi the estadoDatacredi to set
	 */
	public void setEstadoDatacredi(String estadoDatacredi) {
		this.estadoDatacredi = estadoDatacredi;
	}

	/**
	 * @return the mostrarValidaDataCredi
	 */
	public boolean isMostrarValidaDataCredi() {
		return mostrarValidaDataCredi;
	}

	/**
	 * @param mostrarValidaDataCredi the mostrarValidaDataCredi to set
	 */
	public void setMostrarValidaDataCredi(boolean mostrarValidaDataCredi) {
		this.mostrarValidaDataCredi = mostrarValidaDataCredi;
	}
	
	
	
	
}
