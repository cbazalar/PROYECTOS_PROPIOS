package biz.belcorp.ssicc.web.scsicc.hip.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ConsultaHIPSegurosForm.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:ivan.tocto@gmail.com">Ivan Tocto Jaimes</a>
 */

public class ConsultaHIPSegurosForm extends BaseSearchForm {

	private static final long serialVersionUID = 1L;
	
	private String codConsultora;
	private String nomConsultora;
	private String desRegZonTerri;
	
	private String aseguradora;
	private String numeroPoliza;
	private String indicadorOrigen;
	private String periodoActivacion;
	private String fechaActivacion;
	private String estadoPoliza;
	
	private String periodoCancelacion;
	private String fechaCancelacion;
	private String motivoCancelacion;
	
	private String fechaInicioCobertura;
	private String fechaFinCobertura;
	
	private String documentoIdentidad;
	
	private String indicadorBasparampais;
	
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
	 * @return the aseguradora
	 */
	public String getAseguradora() {
		return aseguradora;
	}
	/**
	 * @param aseguradora the aseguradora to set
	 */
	public void setAseguradora(String aseguradora) {
		this.aseguradora = aseguradora;
	}
	/**
	 * @return the numeroPoliza
	 */
	public String getNumeroPoliza() {
		return numeroPoliza;
	}
	/**
	 * @param numeroPoliza the numeroPoliza to set
	 */
	public void setNumeroPoliza(String numeroPoliza) {
		this.numeroPoliza = numeroPoliza;
	}
	/**
	 * @return the indicadorOrigen
	 */
	public String getIndicadorOrigen() {
		return indicadorOrigen;
	}
	/**
	 * @param indicadorOrigen the indicadorOrigen to set
	 */
	public void setIndicadorOrigen(String indicadorOrigen) {
		this.indicadorOrigen = indicadorOrigen;
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
	 * @return the estadoPoliza
	 */
	public String getEstadoPoliza() {
		return estadoPoliza;
	}
	/**
	 * @param estadoPoliza the estadoPoliza to set
	 */
	public void setEstadoPoliza(String estadoPoliza) {
		this.estadoPoliza = estadoPoliza;
	}
	/**
	 * @return the periodoCancelacion
	 */
	public String getPeriodoCancelacion() {
		return periodoCancelacion;
	}
	/**
	 * @param periodoCancelacion the periodoCancelacion to set
	 */
	public void setPeriodoCancelacion(String periodoCancelacion) {
		this.periodoCancelacion = periodoCancelacion;
	}
	/**
	 * @return the fechaCancelacion
	 */
	public String getFechaCancelacion() {
		return fechaCancelacion;
	}
	/**
	 * @param fechaCancelacion the fechaCancelacion to set
	 */
	public void setFechaCancelacion(String fechaCancelacion) {
		this.fechaCancelacion = fechaCancelacion;
	}
	/**
	 * @return the motivoCancelacion
	 */
	public String getMotivoCancelacion() {
		return motivoCancelacion;
	}
	/**
	 * @param motivoCancelacion the motivoCancelacion to set
	 */
	public void setMotivoCancelacion(String motivoCancelacion) {
		this.motivoCancelacion = motivoCancelacion;
	}
	/**
	 * @return the fechaInicioCobertura
	 */
	public String getFechaInicioCobertura() {
		return fechaInicioCobertura;
	}
	/**
	 * @param fechaInicioCobertura the fechaInicioCobertura to set
	 */
	public void setFechaInicioCobertura(String fechaInicioCobertura) {
		this.fechaInicioCobertura = fechaInicioCobertura;
	}
	/**
	 * @return the fechaFinCobertura
	 */
	public String getFechaFinCobertura() {
		return fechaFinCobertura;
	}
	/**
	 * @param fechaFinCobertura the fechaFinCobertura to set
	 */
	public void setFechaFinCobertura(String fechaFinCobertura) {
		this.fechaFinCobertura = fechaFinCobertura;
	}
	public String getDocumentoIdentidad() {
		return documentoIdentidad;
	}
	public void setDocumentoIdentidad(String documentoIdentidad) {
		this.documentoIdentidad = documentoIdentidad;
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
}
