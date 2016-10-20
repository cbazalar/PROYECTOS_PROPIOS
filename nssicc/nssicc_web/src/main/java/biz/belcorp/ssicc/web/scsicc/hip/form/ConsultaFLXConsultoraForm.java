package biz.belcorp.ssicc.web.scsicc.hip.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

/**
 * @author <a href="mailto:jaltamirano@sigcomt.com">Juan José Altamirano Huamán</a>
 * 
 */

public class ConsultaFLXConsultoraForm extends BaseSearchForm {

	private static final long serialVersionUID = -1872340793507259738L;

	private String codigoPais;
	private String codConsultora;
	private String nomConsultora;
	private String desRegZonTerri;
	private String primerPeriodo;
	private String ultimoPeriodo;
	private String lineaCredito;
	private String fechaActivacion;
	private String periodoActivacion;	
	private String fechaComunicacion;
	private String periodoComunicacion;
	private String calificacion;
	private String periodoActual;	
	private String estadoContrato;
	
	private String estadoConsultora;
	private String numeroContrato;
	private String motivoRechazo;
	private String fechaRechazo;
	private String pagoMinimoSiguientePedido;
	private String pagoTotalSiguientePedido;
	
	private String mail;
	private String celular;
	private String flagHabilitado;
	private String codigoCliente;
	private String nombreConsultora;
	private String pedidoBase;	private String lineaCred;
	private String campanyaComunicacion;
	private String codigoCampanyaFacturacion;
	private String flagEstatus;
	private String codigoMotivo;
	private String ultimaModificacionActivar;
	private String fechaNacimiento;
	private String direccion;
	
	private String flagActivo;
	private String flagCancelado;
	
	private String periodo;
	private String periodoSiguiente;
	
	private String montoMinimo;
	private String montoMinimoSiguiente;
	
	protected boolean newRecord = true;
	
	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}
	/**
	 * @param mail the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}
	/**
	 * @return the celular
	 */
	public String getCelular() {
		return celular;
	}
	/**
	 * @param celular the celular to set
	 */
	public void setCelular(String celular) {
		this.celular = celular;
	}
	/**
	 * @return the flagHabilitado
	 */
	public String getFlagHabilitado() {
		return flagHabilitado;
	}
	/**
	 * @param flagHabilitado the flagHabilitado to set
	 */
	public void setFlagHabilitado(String flagHabilitado) {
		this.flagHabilitado = flagHabilitado;
	}
	/**
	 * @return the codigoCliente
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}
	/**
	 * @param codigoCliente the codigoCliente to set
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
	/**
	 * @return the nombreConsultora
	 */
	public String getNombreConsultora() {
		return nombreConsultora;
	}
	/**
	 * @param nombreConsultora the nombreConsultora to set
	 */
	public void setNombreConsultora(String nombreConsultora) {
		this.nombreConsultora = nombreConsultora;
	}
	/**
	 * @return the pedidoBase
	 */
	public String getPedidoBase() {
		return pedidoBase;
	}
	/**
	 * @param pedidoBase the pedidoBase to set
	 */
	public void setPedidoBase(String pedidoBase) {
		this.pedidoBase = pedidoBase;
	}
	/**
	 * @return the lineaCred
	 */
	public String getLineaCred() {
		return lineaCred;
	}
	/**
	 * @param lineaCred the lineaCred to set
	 */
	public void setLineaCred(String lineaCred) {
		this.lineaCred = lineaCred;
	}
	/**
	 * @return the campanyaComunicacion
	 */
	public String getCampanyaComunicacion() {
		return campanyaComunicacion;
	}
	/**
	 * @param campanyaComunicacion the campanyaComunicacion to set
	 */
	public void setCampanyaComunicacion(String campanyaComunicacion) {
		this.campanyaComunicacion = campanyaComunicacion;
	}
	/**
	 * @return the codigoCampanyaFacturacion
	 */
	public String getCodigoCampanyaFacturacion() {
		return codigoCampanyaFacturacion;
	}
	/**
	 * @param codigoCampanyaFacturacion the codigoCampanyaFacturacion to set
	 */
	public void setCodigoCampanyaFacturacion(String codigoCampanyaFacturacion) {
		this.codigoCampanyaFacturacion = codigoCampanyaFacturacion;
	}
	/**
	 * @return the flagEstatus
	 */
	public String getFlagEstatus() {
		return flagEstatus;
	}
	/**
	 * @param flagEstatus the flagEstatus to set
	 */
	public void setFlagEstatus(String flagEstatus) {
		this.flagEstatus = flagEstatus;
	}
	/**
	 * @return the codigoMotivo
	 */
	public String getCodigoMotivo() {
		return codigoMotivo;
	}
	/**
	 * @param codigoMotivo the codigoMotivo to set
	 */
	public void setCodigoMotivo(String codigoMotivo) {
		this.codigoMotivo = codigoMotivo;
	}
	/**
	 * @return the ultimaModificacionActivar
	 */
	public String getUltimaModificacionActivar() {
		return ultimaModificacionActivar;
	}
	/**
	 * @param ultimaModificacionActivar the ultimaModificacionActivar to set
	 */
	public void setUltimaModificacionActivar(String ultimaModificacionActivar) {
		this.ultimaModificacionActivar = ultimaModificacionActivar;
	}
	
	
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais the codigoPais to set
	 * @struts.validator type = "required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	/**
	 * @return the codConsultora
	 */
	public String getCodConsultora() {
		return codConsultora;
	}
	/**
	 * @param codConsultora the codConsultora to set
	 */
	public void setCodConsultora(String codConsultora) {
		this.codConsultora = codConsultora;
	}
	/**
	 * @return the nomConsultora
	 */
	public String getNomConsultora() {
		return nomConsultora;
	}
	/**
	 * @param nomConsultora the nomConsultora to set
	 */
	public void setNomConsultora(String nomConsultora) {
		this.nomConsultora = nomConsultora;
	}
	/**
	 * @return the desRegZonTerri
	 */
	public String getDesRegZonTerri() {
		return desRegZonTerri;
	}
	/**
	 * @param desRegZonTerri the desRegZonTerri to set
	 */
	public void setDesRegZonTerri(String desRegZonTerri) {
		this.desRegZonTerri = desRegZonTerri;
	}
	/**
	 * @return the primerPeriodo
	 */
	public String getPrimerPeriodo() {
		return primerPeriodo;
	}
	/**
	 * @param primerPeriodo the primerPeriodo to set
	 */
	public void setPrimerPeriodo(String primerPeriodo) {
		this.primerPeriodo = primerPeriodo;
	}
	/**
	 * @return the ultimoPeriodo
	 */
	public String getUltimoPeriodo() {
		return ultimoPeriodo;
	}
	/**
	 * @param ultimoPeriodo the ultimoPeriodo to set
	 */
	public void setUltimoPeriodo(String ultimoPeriodo) {
		this.ultimoPeriodo = ultimoPeriodo;
	}
	/**
	 * @return the lineaCredito
	 */
	public String getLineaCredito() {
		return lineaCredito;
	}
	/**
	 * @param lineaCredito the lineaCredito to set
	 */
	public void setLineaCredito(String lineaCredito) {
		this.lineaCredito = lineaCredito;
	}
	/**
	 * @return the fechaActivacion
	 */
	public String getFechaActivacion() {
		return fechaActivacion;
	}
	/**
	 * @param fechaActivacion the fechaActivacion to set
	 */
	public void setFechaActivacion(String fechaActivacion) {
		this.fechaActivacion = fechaActivacion;
	}
	/**
	 * @return the periodoActivacion
	 */
	public String getPeriodoActivacion() {
		return periodoActivacion;
	}
	/**
	 * @param periodoActivacion the periodoActivacion to set
	 */
	public void setPeriodoActivacion(String periodoActivacion) {
		this.periodoActivacion = periodoActivacion;
	}
	/**
	 * @return the fechaComunicacion
	 */
	public String getFechaComunicacion() {
		return fechaComunicacion;
	}
	/**
	 * @param fechaComunicacion the fechaComunicacion to set
	 */
	public void setFechaComunicacion(String fechaComunicacion) {
		this.fechaComunicacion = fechaComunicacion;
	}
	/**
	 * @return the periodoComunicacion
	 */
	public String getPeriodoComunicacion() {
		return periodoComunicacion;
	}
	/**
	 * @param periodoComunicacion the periodoComunicacion to set
	 */
	public void setPeriodoComunicacion(String periodoComunicacion) {
		this.periodoComunicacion = periodoComunicacion;
	}
	/**
	 * @return the calificacion
	 */
	public String getCalificacion() {
		return calificacion;
	}
	/**
	 * @param calificacion the calificacion to set
	 */
	public void setCalificacion(String calificacion) {
		this.calificacion = calificacion;
	}
	/**
	 * @return the periodoActual
	 */
	public String getPeriodoActual() {
		return periodoActual;
	}
	/**
	 * @param periodoActual the periodoActual to set
	 */
	public void setPeriodoActual(String periodoActual) {
		this.periodoActual = periodoActual;
	}
	/**
	 * @return the estadoContrato
	 */
	public String getEstadoContrato() {
		return estadoContrato;
	}
	/**
	 * @param estadoContrato the estadoContrato to set
	 */
	public void setEstadoContrato(String estadoContrato) {
		this.estadoContrato = estadoContrato;
	}
	/**
	 * @return the estadoConsultora
	 */
	public String getEstadoConsultora() {
		return estadoConsultora;
	}
	/**
	 * @param estadoConsultora the estadoConsultora to set
	 */
	public void setEstadoConsultora(String estadoConsultora) {
		this.estadoConsultora = estadoConsultora;
	}
	/**
	 * @return the numeroContrato
	 */
	public String getNumeroContrato() {
		return numeroContrato;
	}
	/**
	 * @param numeroContrato the numeroContrato to set
	 */
	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}
	/**
	 * @return the motivoRechazo
	 */
	public String getMotivoRechazo() {
		return motivoRechazo;
	}
	/**
	 * @param motivoRechazo the motivoRechazo to set
	 */
	public void setMotivoRechazo(String motivoRechazo) {
		this.motivoRechazo = motivoRechazo;
	}
	/**
	 * @return the fechaRechazo
	 */
	public String getFechaRechazo() {
		return fechaRechazo;
	}
	/**
	 * @param fechaRechazo the fechaRechazo to set
	 */
	public void setFechaRechazo(String fechaRechazo) {
		this.fechaRechazo = fechaRechazo;
	}
	/**
	 * @return the pagoMinimoSiguientePedido
	 */
	public String getPagoMinimoSiguientePedido() {
		return pagoMinimoSiguientePedido;
	}
	/**
	 * @param pagoMinimoSiguientePedido the pagoMinimoSiguientePedido to set
	 */
	public void setPagoMinimoSiguientePedido(String pagoMinimoSiguientePedido) {
		this.pagoMinimoSiguientePedido = pagoMinimoSiguientePedido;
	}
	/**
	 * @return the pagoTotalSiguientePedido
	 */
	public String getPagoTotalSiguientePedido() {
		return pagoTotalSiguientePedido;
	}
	/**
	 * @param pagoTotalSiguientePedido the pagoTotalSiguientePedido to set
	 */
	public void setPagoTotalSiguientePedido(String pagoTotalSiguientePedido) {
		this.pagoTotalSiguientePedido = pagoTotalSiguientePedido;
	}
	/**
	 * @return the fechaNacimiento
	 */
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	/**
	 * @param fechaNacimiento the fechaNacimiento to set
	 */
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	/**
	 * @return the flagActivo
	 */
	public String getFlagActivo() {
		return flagActivo;
	}
	/**
	 * @param flagActivo the flagActivo to set
	 */
	public void setFlagActivo(String flagActivo) {
		this.flagActivo = flagActivo;
	}
	/**
	 * @return the flagCancelado
	 */
	public String getFlagCancelado() {
		return flagCancelado;
	}
	/**
	 * @param flagCancelado the flagCancelado to set
	 */
	public void setFlagCancelado(String flagCancelado) {
		this.flagCancelado = flagCancelado;
	}
	/**
	 * @return the periodo
	 */
	public String getPeriodo() {
		return periodo;
	}
	/**
	 * @param periodo the periodo to set
	 */
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	/**
	 * @return the periodoSiguiente
	 */
	public String getPeriodoSiguiente() {
		return periodoSiguiente;
	}
	/**
	 * @param periodoSiguiente the periodoSiguiente to set
	 */
	public void setPeriodoSiguiente(String periodoSiguiente) {
		this.periodoSiguiente = periodoSiguiente;
	}
	/**
	 * @return the montoMinimo
	 */
	public String getMontoMinimo() {
		return montoMinimo;
	}
	/**
	 * @param montoMinimo the montoMinimo to set
	 */
	public void setMontoMinimo(String montoMinimo) {
		this.montoMinimo = montoMinimo;
	}
	/**
	 * @return the montoMinimoSiguiente
	 */
	public String getMontoMinimoSiguiente() {
		return montoMinimoSiguiente;
	}
	/**
	 * @param montoMinimoSiguiente the montoMinimoSiguiente to set
	 */
	public void setMontoMinimoSiguiente(String montoMinimoSiguiente) {
		this.montoMinimoSiguiente = montoMinimoSiguiente;
	}
	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}
	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	/**
	 * @return the newRecord
	 */
	public boolean isNewRecord() {
		return newRecord;
	}
	/**
	 * @param newRecord the newRecord to set
	 */
	public void setNewRecord(boolean newRecord) {
		this.newRecord = newRecord;
	}
	
}
