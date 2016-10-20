package biz.belcorp.ssicc.web.scsicc.hip.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

/**
 * The Class ConsultaHIPRetailForm.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 05/02/2014
 */
public class ConsultaHIPRetailForm extends BaseSearchForm {
	private static final long serialVersionUID = 1L;
	
	private String codConsultora;
	private String nomConsultora;
	private String desRegZonTerri;
	
	private String periodoProceso;
	private String periodoRetail;
	private String totalUnidades;
	private String totalVenta;
	private String estado;
	private String puntos;
	
	private String indicadorBasparampais;
	
	private String unidadesDevolucion;
	private String ventaDevolucion;
	
	private String numeroDocumento;
	
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
	 * @return the periodoProceso
	 */
	public String getPeriodoProceso() {
		return periodoProceso;
	}
	/**
	 * @param periodoProceso the periodoProceso to set
	 */
	public void setPeriodoProceso(String periodoProceso) {
		this.periodoProceso = periodoProceso;
	}
	/**
	 * @return the periodoRetail
	 */
	public String getPeriodoRetail() {
		return periodoRetail;
	}
	/**
	 * @param periodoRetail the periodoRetail to set
	 */
	public void setPeriodoRetail(String periodoRetail) {
		this.periodoRetail = periodoRetail;
	}
	/**
	 * @return the totalUnidades
	 */
	public String getTotalUnidades() {
		return totalUnidades;
	}
	/**
	 * @param totalUnidades the totalUnidades to set
	 */
	public void setTotalUnidades(String totalUnidades) {
		this.totalUnidades = totalUnidades;
	}
	/**
	 * @return the totalVenta
	 */
	public String getTotalVenta() {
		return totalVenta;
	}
	/**
	 * @param totalVenta the totalVenta to set
	 */
	public void setTotalVenta(String totalVenta) {
		this.totalVenta = totalVenta;
	}
	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	/**
	 * @return the puntos
	 */
	public String getPuntos() {
		return puntos;
	}
	/**
	 * @param puntos the puntos to set
	 */
	public void setPuntos(String puntos) {
		this.puntos = puntos;
	}
	/**
	 * @return
	 */
	public String getIndicadorBasparampais() {
		return indicadorBasparampais;
	}
	/**
	 * @param indicadorBasparampais
	 */
	public void setIndicadorBasparampais(String indicadorBasparampais) {
		this.indicadorBasparampais = indicadorBasparampais;
	}
	/**
	 * @return
	 */
	public String getUnidadesDevolucion() {
		return unidadesDevolucion;
	}
	/**
	 * @param unidadesDevolucion
	 */
	public void setUnidadesDevolucion(String unidadesDevolucion) {
		this.unidadesDevolucion = unidadesDevolucion;
	}
	/**
	 * @return
	 */
	public String getVentaDevolucion() {
		return ventaDevolucion;
	}
	/**
	 * @param ventaDevolucion
	 */
	public void setVentaDevolucion(String ventaDevolucion) {
		this.ventaDevolucion = ventaDevolucion;
	}
	
	/**
	 * @return
	 */
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	
	/**
	 * @param numeroDocumento
	 */
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
		
	
}

