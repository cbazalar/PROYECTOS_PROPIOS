package biz.belcorp.ssicc.web.scsicc.hip.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
public class ConsultaHIPCuentaCorrientesForm extends BaseSearchForm {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 7760246722591586299L;
	
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
	
	private String indicadorBasparampais;
	
	private Double dsaldoVencido;
	private Double dsaldoPorVencer;
	private Double dsaldoTotal;
	private Double dpagosPosteriores;
	private Double dsaldoCupon3;
	private Double dpagoHoy;
	private Double dsaldoPagar;
	
	/**
	 * @return Returns the codConsultora.
	 */
	public String getCodConsultora() {
		return codConsultora;
	}
	/**
	 * @param codConsultora The codConsultora to set.
	 */
	public void setCodConsultora(String codConsultora) {
		this.codConsultora = codConsultora;
	}
	/**
	 * @return Returns the desRegZonTerri.
	 */
	public String getDesRegZonTerri() {
		return desRegZonTerri;
	}
	/**
	 * @param desRegZonTerri The desRegZonTerri to set.
	 */
	public void setDesRegZonTerri(String desRegZonTerri) {
		this.desRegZonTerri = desRegZonTerri;
	}
	/**
	 * @return Returns the nomConsultora.
	 */
	public String getNomConsultora() {
		return nomConsultora;
	}
	/**
	 * @param nomConsultora The nomConsultora to set.
	 */
	public void setNomConsultora(String nomConsultora) {
		this.nomConsultora = nomConsultora;
	}
	/**
	 * @return Returns the pagosPosteriores.
	 */
	public String getPagosPosteriores() {
		return pagosPosteriores;
	}
	/**
	 * @param pagosPosteriores The pagosPosteriores to set.
	 */
	public void setPagosPosteriores(String pagosPosteriores) {
		this.pagosPosteriores = pagosPosteriores;
	}
	/**
	 * @return Returns the saldoPagar.
	 */
	public String getSaldoPagar() {
		return saldoPagar;
	}
	/**
	 * @param saldoPagar The saldoPagar to set.
	 */
	public void setSaldoPagar(String saldoPagar) {
		this.saldoPagar = saldoPagar;
	}
	/**
	 * @return Returns the saldoUnico.
	 */
	public String getSaldoUnico() {
		return saldoUnico;
	}
	/**
	 * @param saldoUnico The saldoUnico to set.
	 */
	public void setSaldoUnico(String saldoUnico) {
		this.saldoUnico = saldoUnico;
	}
	/**
	 * @return Returns the mostrarLinks.
	 */
	public boolean isMostrarLinks() {
		return mostrarLinks;
	}
	/**
	 * @param mostrarLinks The mostrarLinks to set.
	 */
	public void setMostrarLinks(boolean mostrarLinks) {
		this.mostrarLinks = mostrarLinks;
	}
	/**
	 * @return the bloqueo
	 */
	public String getBloqueo() {
		return bloqueo;
	}
	/**
	 * @param bloqueo the bloqueo to set
	 */
	public void setBloqueo(String bloqueo) {
		this.bloqueo = bloqueo;
	}
	/**
	 * @return the saldoVencido
	 */
	public String getSaldoVencido() {
		return saldoVencido;
	}
	/**
	 * @param saldoVencido the saldoVencido to set
	 */
	public void setSaldoVencido(String saldoVencido) {
		this.saldoVencido = saldoVencido;
	}
	/**
	 * @return the saldoPorVencer
	 */
	public String getSaldoPorVencer() {
		return saldoPorVencer;
	}
	/**
	 * @param saldoPorVencer the saldoPorVencer to set
	 */
	public void setSaldoPorVencer(String saldoPorVencer) {
		this.saldoPorVencer = saldoPorVencer;
	}

	/**
	 * @return the saldoCupon3
	 */
	public String getSaldoCupon3() {
		return saldoCupon3;
	}
	/**
	 * @param saldoCupon3 the saldoCupon3 to set
	 */
	public void setSaldoCupon3(String saldoCupon3) {
		this.saldoCupon3 = saldoCupon3;
	}
	/**
	 * @return
	 */
	public String getMontoMinimo() {
		return montoMinimo;
	}
	/**
	 * @param montoMinimo
	 */
	public void setMontoMinimo(String montoMinimo) {
		this.montoMinimo = montoMinimo;
	}
	/**
	 * @return
	 */
	public String getMontoMinimoSiguiente() {
		return montoMinimoSiguiente;
	}
	/**
	 * @param montoMinimoSiguiente
	 */
	public void setMontoMinimoSiguiente(String montoMinimoSiguiente) {
		this.montoMinimoSiguiente = montoMinimoSiguiente;
	}
	/**
	 * @return
	 */
	public String getAplicaPeriodo() {
		return aplicaPeriodo;
	}
	/**
	 * @param aplicaPeriodo
	 */
	public void setAplicaPeriodo(String aplicaPeriodo) {
		this.aplicaPeriodo = aplicaPeriodo;
	}
	/**
	 * @return
	 */
	public String getAplicaPeriodoSiguiente() {
		return aplicaPeriodoSiguiente;
	}
	/**
	 * @param aplicaPeriodoSiguiente
	 */
	public void setAplicaPeriodoSiguiente(String aplicaPeriodoSiguiente) {
		this.aplicaPeriodoSiguiente = aplicaPeriodoSiguiente;
	}
	/**
	 * @return
	 */
	public String getPeriodo() {
		return periodo;
	}
	/**
	 * @param periodo
	 */
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	/**
	 * @return
	 */
	public String getPeriodoSiguiente() {
		return periodoSiguiente;
	}
	/**
	 * @param periodoSiguiente
	 */
	public void setPeriodoSiguiente(String periodoSiguiente) {
		this.periodoSiguiente = periodoSiguiente;
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
	 * @return the dsaldoVencido
	 */
	public Double getDsaldoVencido() {
		return dsaldoVencido;
	}
	/**
	 * @param dsaldoVencido the dsaldoVencido to set
	 */
	public void setDsaldoVencido(Double dsaldoVencido) {
		this.dsaldoVencido = dsaldoVencido;
	}
	/**
	 * @return the dsaldoPorVencer
	 */
	public Double getDsaldoPorVencer() {
		return dsaldoPorVencer;
	}
	/**
	 * @param dsaldoPorVencer the dsaldoPorVencer to set
	 */
	public void setDsaldoPorVencer(Double dsaldoPorVencer) {
		this.dsaldoPorVencer = dsaldoPorVencer;
	}
	/**
	 * @return the dsaldoTotal
	 */
	public Double getDsaldoTotal() {
		return dsaldoTotal;
	}
	/**
	 * @param dsaldoTotal the dsaldoTotal to set
	 */
	public void setDsaldoTotal(Double dsaldoTotal) {
		this.dsaldoTotal = dsaldoTotal;
	}
	/**
	 * @return the dpagosPosteriores
	 */
	public Double getDpagosPosteriores() {
		return dpagosPosteriores;
	}
	/**
	 * @param dpagosPosteriores the dpagosPosteriores to set
	 */
	public void setDpagosPosteriores(Double dpagosPosteriores) {
		this.dpagosPosteriores = dpagosPosteriores;
	}
	/**
	 * @return the dsaldoCupon3
	 */
	public Double getDsaldoCupon3() {
		return dsaldoCupon3;
	}
	/**
	 * @param dsaldoCupon3 the dsaldoCupon3 to set
	 */
	public void setDsaldoCupon3(Double dsaldoCupon3) {
		this.dsaldoCupon3 = dsaldoCupon3;
	}
	/**
	 * @return the dpagoHoy
	 */
	public Double getDpagoHoy() {
		return dpagoHoy;
	}
	/**
	 * @param dpagoHoy the dpagoHoy to set
	 */
	public void setDpagoHoy(Double dpagoHoy) {
		this.dpagoHoy = dpagoHoy;
	}
	/**
	 * @return the dsaldoPagar
	 */
	public Double getDsaldoPagar() {
		return dsaldoPagar;
	}
	/**
	 * @param dsaldoPagar the dsaldoPagar to set
	 */
	public void setDsaldoPagar(Double dsaldoPagar) {
		this.dsaldoPagar = dsaldoPagar;
	}
		
}

