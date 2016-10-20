package biz.belcorp.ssicc.web.scsicc.hip.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

/**
 * @author <a href="mailto:sguerra@sigcomt.com">Sebastian Guerra</a>
 *                      
 * @struts.form name = "consultaHIPCuentaCorrientesCampanhaForm" extends="baseReporteForm"
 */
public class ConsultaHIPCuentaCorrientesCampanhaForm extends BaseSearchForm {
	private static final long serialVersionUID = 1L;
	
	private String codConsultora;
	private String nomConsultora;
	private String desRegZonTerri;
	
	private String saldoUnico;
	private String saldoPagar;
	private String pagosPosteriores;
	
	private boolean mostrarLinks;
	
	private String bloqueo;
	private String saldoVencido;
	private String saldoPorVencer;
	
	private String saldoCupon3;
	
	private String montoMinimo;
	private String montoMinimoSiguiente;
	private String aplicaPeriodo;
	private String aplicaPeriodoSiguiente;
	
	private String periodo;
	private String periodoSiguiente;
	
	private String saldoTotal;
	private String pagoHoy;
	
	private String pagoTotalUltimo;
	private String pagoTotalPenultimo;
	private String pagoMinimoPenultimo;
	
	private String estatusFlx;
	private String fechaActivacionFlx;
	
	//inicio PER-SiCC-2014-0999
	private String estadoFlexipago;
	private String fechaActivacionFlexipago;
	private String campanaActivacionFlexipago;
	private String fechaCancelacionFlexipago;
	private String motivoCancelacionFlexipago;
	private String fechaComunicacionFlexipago;
	private String campanaComunicacionFlexipago;
	//fin PER-SiCC-2014-0999
	
	private String indicadorBasparampais;
	
	private Double dmontoMinimo;
	private Double dpagoMinimoPenultimo;
	private Double dpagoTotalPenultimo;
	private Double dmontoMinimoSiguiente;
	private Double dpagoTotalUltimo;
	
	/**
	 * @return the codConsultora
	 */
	public String getCodConsultora() {
		return codConsultora;
	}
	/**
	 * @return the nomConsultora
	 */
	public String getNomConsultora() {
		return nomConsultora;
	}
	/**
	 * @return the desRegZonTerri
	 */
	public String getDesRegZonTerri() {
		return desRegZonTerri;
	}
	/**
	 * @return the saldoUnico
	 */
	public String getSaldoUnico() {
		return saldoUnico;
	}
	/**
	 * @return the saldoPagar
	 */
	public String getSaldoPagar() {
		return saldoPagar;
	}
	/**
	 * @return the pagosPosteriores
	 */
	public String getPagosPosteriores() {
		return pagosPosteriores;
	}
	/**
	 * @return the mostrarLinks
	 */
	public boolean isMostrarLinks() {
		return mostrarLinks;
	}
	/**
	 * @return the bloqueo
	 */
	public String getBloqueo() {
		return bloqueo;
	}
	/**
	 * @return the saldoVencido
	 */
	public String getSaldoVencido() {
		return saldoVencido;
	}
	/**
	 * @return the saldoPorVencer
	 */
	public String getSaldoPorVencer() {
		return saldoPorVencer;
	}
	/**
	 * @return the saldoCupon3
	 */
	public String getSaldoCupon3() {
		return saldoCupon3;
	}
	/**
	 * @return the montoMinimo
	 */
	public String getMontoMinimo() {
		return montoMinimo;
	}
	/**
	 * @return the montoMinimoSiguiente
	 */
	public String getMontoMinimoSiguiente() {
		return montoMinimoSiguiente;
	}
	/**
	 * @return the aplicaPeriodo
	 */
	public String getAplicaPeriodo() {
		return aplicaPeriodo;
	}
	/**
	 * @return the aplicaPeriodoSiguiente
	 */
	public String getAplicaPeriodoSiguiente() {
		return aplicaPeriodoSiguiente;
	}
	/**
	 * @return the periodo
	 */
	public String getPeriodo() {
		return periodo;
	}
	/**
	 * @return the periodoSiguiente
	 */
	public String getPeriodoSiguiente() {
		return periodoSiguiente;
	}
	/**
	 * @return the saldoTotal
	 */
	public String getSaldoTotal() {
		return saldoTotal;
	}
	/**
	 * @return the pagoHoy
	 */
	public String getPagoHoy() {
		return pagoHoy;
	}
	/**
	 * @param codConsultora the codConsultora to set
	 */
	public void setCodConsultora(String codConsultora) {
		this.codConsultora = codConsultora;
	}
	/**
	 * @param nomConsultora the nomConsultora to set
	 */
	public void setNomConsultora(String nomConsultora) {
		this.nomConsultora = nomConsultora;
	}
	/**
	 * @param desRegZonTerri the desRegZonTerri to set
	 */
	public void setDesRegZonTerri(String desRegZonTerri) {
		this.desRegZonTerri = desRegZonTerri;
	}
	/**
	 * @param saldoUnico the saldoUnico to set
	 */
	public void setSaldoUnico(String saldoUnico) {
		this.saldoUnico = saldoUnico;
	}
	/**
	 * @param saldoPagar the saldoPagar to set
	 */
	public void setSaldoPagar(String saldoPagar) {
		this.saldoPagar = saldoPagar;
	}
	/**
	 * @param pagosPosteriores the pagosPosteriores to set
	 */
	public void setPagosPosteriores(String pagosPosteriores) {
		this.pagosPosteriores = pagosPosteriores;
	}
	/**
	 * @param mostrarLinks the mostrarLinks to set
	 */
	public void setMostrarLinks(boolean mostrarLinks) {
		this.mostrarLinks = mostrarLinks;
	}
	/**
	 * @param bloqueo the bloqueo to set
	 */
	public void setBloqueo(String bloqueo) {
		this.bloqueo = bloqueo;
	}
	/**
	 * @param saldoVencido the saldoVencido to set
	 */
	public void setSaldoVencido(String saldoVencido) {
		this.saldoVencido = saldoVencido;
	}
	/**
	 * @param saldoPorVencer the saldoPorVencer to set
	 */
	public void setSaldoPorVencer(String saldoPorVencer) {
		this.saldoPorVencer = saldoPorVencer;
	}
	/**
	 * @param saldoCupon3 the saldoCupon3 to set
	 */
	public void setSaldoCupon3(String saldoCupon3) {
		this.saldoCupon3 = saldoCupon3;
	}
	/**
	 * @param montoMinimo the montoMinimo to set
	 */
	public void setMontoMinimo(String montoMinimo) {
		this.montoMinimo = montoMinimo;
	}
	/**
	 * @param montoMinimoSiguiente the montoMinimoSiguiente to set
	 */
	public void setMontoMinimoSiguiente(String montoMinimoSiguiente) {
		this.montoMinimoSiguiente = montoMinimoSiguiente;
	}
	/**
	 * @param aplicaPeriodo the aplicaPeriodo to set
	 */
	public void setAplicaPeriodo(String aplicaPeriodo) {
		this.aplicaPeriodo = aplicaPeriodo;
	}
	/**
	 * @param aplicaPeriodoSiguiente the aplicaPeriodoSiguiente to set
	 */
	public void setAplicaPeriodoSiguiente(String aplicaPeriodoSiguiente) {
		this.aplicaPeriodoSiguiente = aplicaPeriodoSiguiente;
	}
	/**
	 * @param periodo the periodo to set
	 */
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	/**
	 * @param periodoSiguiente the periodoSiguiente to set
	 */
	public void setPeriodoSiguiente(String periodoSiguiente) {
		this.periodoSiguiente = periodoSiguiente;
	}
	/**
	 * @param saldoTotal the saldoTotal to set
	 */
	public void setSaldoTotal(String saldoTotal) {
		this.saldoTotal = saldoTotal;
	}
	/**
	 * @param pagoHoy the pagoHoy to set
	 */
	public void setPagoHoy(String pagoHoy) {
		this.pagoHoy = pagoHoy;
	}
	/**
	 * @return the pagoTotalUltimo
	 */
	public String getPagoTotalUltimo() {
		return pagoTotalUltimo;
	}
	/**
	 * @return the pagoTotalPenultimo
	 */
	public String getPagoTotalPenultimo() {
		return pagoTotalPenultimo;
	}
	/**
	 * @param pagoTotalUltimo the pagoTotalUltimo to set
	 */
	public void setPagoTotalUltimo(String pagoTotalUltimo) {
		this.pagoTotalUltimo = pagoTotalUltimo;
	}
	/**
	 * @param pagoTotalPenultimo the pagoTotalPenultimo to set
	 */
	public void setPagoTotalPenultimo(String pagoTotalPenultimo) {
		this.pagoTotalPenultimo = pagoTotalPenultimo;
	}
	/**
	 * @return the pagoMinimoPenultimo
	 */
	public String getPagoMinimoPenultimo() {
		return pagoMinimoPenultimo;
	}
	/**
	 * @param pagoMinimoPenultimo the pagoMinimoPenultimo to set
	 */
	public void setPagoMinimoPenultimo(String pagoMinimoPenultimo) {
		this.pagoMinimoPenultimo = pagoMinimoPenultimo;
	}
	/**
	 * @return the estatusFlx
	 */
	public String getEstatusFlx() {
		return estatusFlx;
	}
	/**
	 * @param estatusFlx the estatusFlx to set
	 */
	public void setEstatusFlx(String estatusFlx) {
		this.estatusFlx = estatusFlx;
	}
	/**
	 * @return the fechaActivacionFlx
	 */
	public String getFechaActivacionFlx() {
		return fechaActivacionFlx;
	}
	/**
	 * @param fechaActivacionFlx the fechaActivacionFlx to set
	 */
	public void setFechaActivacionFlx(String fechaActivacionFlx) {
		this.fechaActivacionFlx = fechaActivacionFlx;
	}
	/**
	 * @return the estadoFlexipago
	 */
	public String getEstadoFlexipago() {
		return estadoFlexipago;
	}
	/**
	 * @param estadoFlexipago the estadoFlexipago to set
	 */
	public void setEstadoFlexipago(String estadoFlexipago) {
		this.estadoFlexipago = estadoFlexipago;
	}
	/**
	 * @return the fechaActivacionFlexipago
	 */
	public String getFechaActivacionFlexipago() {
		return fechaActivacionFlexipago;
	}
	/**
	 * @param fechaActivacionFlexipago the fechaActivacionFlexipago to set
	 */
	public void setFechaActivacionFlexipago(String fechaActivacionFlexipago) {
		this.fechaActivacionFlexipago = fechaActivacionFlexipago;
	}
	/**
	 * @return the campanaActivacionFlexipago
	 */
	public String getCampanaActivacionFlexipago() {
		return campanaActivacionFlexipago;
	}
	/**
	 * @param campanaActivacionFlexipago the campanaActivacionFlexipago to set
	 */
	public void setCampanaActivacionFlexipago(String campanaActivacionFlexipago) {
		this.campanaActivacionFlexipago = campanaActivacionFlexipago;
	}
	/**
	 * @return the fechaCancelacionFlexipago
	 */
	public String getFechaCancelacionFlexipago() {
		return fechaCancelacionFlexipago;
	}
	/**
	 * @param fechaCancelacionFlexipago the fechaCancelacionFlexipago to set
	 */
	public void setFechaCancelacionFlexipago(String fechaCancelacionFlexipago) {
		this.fechaCancelacionFlexipago = fechaCancelacionFlexipago;
	}
	/**
	 * @return the motivoCancelacionFlexipago
	 */
	public String getMotivoCancelacionFlexipago() {
		return motivoCancelacionFlexipago;
	}
	/**
	 * @param motivoCancelacionFlexipago the motivoCancelacionFlexipago to set
	 */
	public void setMotivoCancelacionFlexipago(String motivoCancelacionFlexipago) {
		this.motivoCancelacionFlexipago = motivoCancelacionFlexipago;
	}
	/**
	 * @return the fechaComunicacionFlexipago
	 */
	public String getFechaComunicacionFlexipago() {
		return fechaComunicacionFlexipago;
	}
	/**
	 * @param fechaComunicacionFlexipago the fechaComunicacionFlexipago to set
	 */
	public void setFechaComunicacionFlexipago(String fechaComunicacionFlexipago) {
		this.fechaComunicacionFlexipago = fechaComunicacionFlexipago;
	}
	/**
	 * @return the campanaComunicacionFlexipago
	 */
	public String getCampanaComunicacionFlexipago() {
		return campanaComunicacionFlexipago;
	}
	/**
	 * @param campanaComunicacionFlexipago the campanaComunicacionFlexipago to set
	 */
	public void setCampanaComunicacionFlexipago(String campanaComunicacionFlexipago) {
		this.campanaComunicacionFlexipago = campanaComunicacionFlexipago;
	}
	/**
	 * @return the indicadorBasparampais
	 */
	public String getIndicadorBasparampais() {
		return indicadorBasparampais;
	}
	/**
	 * @param indicadorBasparampais the indicadorBasparampais to set
	 */
	public void setIndicadorBasparampais(String indicadorBasparampais) {
		this.indicadorBasparampais = indicadorBasparampais;
	}
	/**
	 * @return the dmontoMinimo
	 */
	public Double getDmontoMinimo() {
		return dmontoMinimo;
	}
	/**
	 * @param dmontoMinimo the dmontoMinimo to set
	 */
	public void setDmontoMinimo(Double dmontoMinimo) {
		this.dmontoMinimo = dmontoMinimo;
	}
	/**
	 * @return the dpagoMinimoPenultimo
	 */
	public Double getDpagoMinimoPenultimo() {
		return dpagoMinimoPenultimo;
	}
	/**
	 * @param dpagoMinimoPenultimo the dpagoMinimoPenultimo to set
	 */
	public void setDpagoMinimoPenultimo(Double dpagoMinimoPenultimo) {
		this.dpagoMinimoPenultimo = dpagoMinimoPenultimo;
	}
	/**
	 * @return the dpagoTotalPenultimo
	 */
	public Double getDpagoTotalPenultimo() {
		return dpagoTotalPenultimo;
	}
	/**
	 * @param dpagoTotalPenultimo the dpagoTotalPenultimo to set
	 */
	public void setDpagoTotalPenultimo(Double dpagoTotalPenultimo) {
		this.dpagoTotalPenultimo = dpagoTotalPenultimo;
	}
	/**
	 * @return the dmontoMinimoSiguiente
	 */
	public Double getDmontoMinimoSiguiente() {
		return dmontoMinimoSiguiente;
	}
	/**
	 * @param dmontoMinimoSiguiente the dmontoMinimoSiguiente to set
	 */
	public void setDmontoMinimoSiguiente(Double dmontoMinimoSiguiente) {
		this.dmontoMinimoSiguiente = dmontoMinimoSiguiente;
	}
	/**
	 * @return the dpagoTotalUltimo
	 */
	public Double getDpagoTotalUltimo() {
		return dpagoTotalUltimo;
	}
	/**
	 * @param dpagoTotalUltimo the dpagoTotalUltimo to set
	 */
	public void setDpagoTotalUltimo(Double dpagoTotalUltimo) {
		this.dpagoTotalUltimo = dpagoTotalUltimo;
	}
}
