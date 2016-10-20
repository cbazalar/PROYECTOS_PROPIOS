package biz.belcorp.ssicc.web.scsicc.hip.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;


public class ConsultaHIPCuentaCorrientesHistoricaForm extends BaseSearchForm {

	private static final long serialVersionUID = -5331293966005538494L;
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
		
}

