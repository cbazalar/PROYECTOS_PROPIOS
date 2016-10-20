package biz.belcorp.ssicc.web.spncd.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoCUPLogrosForm extends BaseEditForm implements
		Serializable {

	private static final long serialVersionUID = 1L;

	private String codigoPais;
	private String codigoConsultora;
	private String descripcionConsultora;
	private String codigoTipoLogro;
	private String codigoMedioCaptura;
	private String monto;
	private String codigoPeriodoInicio;
	private String codigoPeriodoFin;
	private String descripcionLarga;
	private String indicadorEdit;
	private String codigoOrigen;
	private String periodoInicioConsultora;
	private String periodoFinConsultora;

	private String codigoPeriodoInicioBD;
	private String codigoPeriodoFinBD;

	private String indicadorNuevas;
	private String indicadorActividadConsultora;

	/**
	 * @return codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the codigoConsultora
	 */
	public String getCodigoConsultora() {
		return codigoConsultora;
	}

	/**
	 * @param codigoConsultora
	 *            the codigoConsultora to set
	 */
	public void setCodigoConsultora(String codigoConsultora) {
		this.codigoConsultora = codigoConsultora;
	}

	/**
	 * @return the descripcionConsultora
	 */
	public String getDescripcionConsultora() {
		return descripcionConsultora;
	}

	/**
	 * @param descripcionConsultora
	 *            the descripcionConsultora to set
	 */
	public void setDescripcionConsultora(String descripcionConsultora) {
		this.descripcionConsultora = descripcionConsultora;
	}

	/**
	 * @return the codigoTipoLogro
	 */
	public String getCodigoTipoLogro() {
		return codigoTipoLogro;
	}

	/**
	 * @param codigoTipoLogro
	 *            the codigoTipoLogro to set
	 */
	public void setCodigoTipoLogro(String codigoTipoLogro) {
		this.codigoTipoLogro = codigoTipoLogro;
	}

	/**
	 * @return the codigoMedioCaptura
	 */
	public String getCodigoMedioCaptura() {
		return codigoMedioCaptura;
	}

	/**
	 * @param codigoMedioCaptura
	 *            the codigoMedioCaptura to set
	 */
	public void setCodigoMedioCaptura(String codigoMedioCaptura) {
		this.codigoMedioCaptura = codigoMedioCaptura;
	}

	/**
	 * @return the monto
	 */
	public String getMonto() {
		return monto;
	}

	/**
	 * @param monto
	 *            the monto to set
	 */
	public void setMonto(String monto) {
		this.monto = monto;
	}

	/**
	 * @return the codigoPeriodoInicio
	 */
	public String getCodigoPeriodoInicio() {
		return codigoPeriodoInicio;
	}

	/**
	 * @param codigoPeriodoInicio
	 *            the codigoPeriodoInicio to set
	 */
	public void setCodigoPeriodoInicio(String codigoPeriodoInicio) {
		this.codigoPeriodoInicio = codigoPeriodoInicio;
	}

	/**
	 * @return the codigoPeriodoFin�
	 */
	public String getCodigoPeriodoFin() {
		return codigoPeriodoFin;
	}

	/**
	 * @param codigoPeriodoFin
	 *            the codigoPeriodoFin to set
	 */
	public void setCodigoPeriodoFin(String codigoPeriodoFin) {
		this.codigoPeriodoFin = codigoPeriodoFin;
	}

	/**
	 * @return the descripcionLarga
	 */
	public String getDescripcionLarga() {
		return descripcionLarga;
	}

	/**
	 * @param descripcionLarga
	 *            the descripcionLarga to set
	 */
	public void setDescripcionLarga(String descripcionLarga) {
		this.descripcionLarga = descripcionLarga;
	}

	/**
	 * @return the indicadorEdit
	 */
	public String getIndicadorEdit() {
		return indicadorEdit;
	}

	/**
	 * @param indicadorEdit
	 *            the indicadorEdit to set
	 */
	public void setIndicadorEdit(String indicadorEdit) {
		this.indicadorEdit = indicadorEdit;
	}

	/**
	 * @return the codigoOrigen
	 */
	public String getCodigoOrigen() {
		return codigoOrigen;
	}

	/**
	 * @param codigoOrigen
	 *            the codigoOrigen to set
	 */
	public void setCodigoOrigen(String codigoOrigen) {
		this.codigoOrigen = codigoOrigen;
	}

	/**
	 * @return the periodoInicioConsultora
	 */
	public String getPeriodoInicioConsultora() {
		return periodoInicioConsultora;
	}

	/**
	 * @param periodoInicioConsultora
	 *            the periodoInicioConsultora to set
	 */
	public void setPeriodoInicioConsultora(String periodoInicioConsultora) {
		this.periodoInicioConsultora = periodoInicioConsultora;
	}

	/**
	 * @return the periodoFinConsultora
	 */
	public String getPeriodoFinConsultora() {
		return periodoFinConsultora;
	}

	/**
	 * @param periodoFinConsultora
	 *            the periodoFinConsultora to set
	 */
	public void setPeriodoFinConsultora(String periodoFinConsultora) {
		this.periodoFinConsultora = periodoFinConsultora;
	}

	/**
	 * @return the codigoPeriodoInicioBD
	 */
	public String getCodigoPeriodoInicioBD() {
		return codigoPeriodoInicioBD;
	}

	/**
	 * @param codigoPeriodoInicioBD
	 *            the codigoPeriodoInicioBD to set
	 */
	public void setCodigoPeriodoInicioBD(String codigoPeriodoInicioBD) {
		this.codigoPeriodoInicioBD = codigoPeriodoInicioBD;
	}

	/**
	 * @return the codigoPeriodoFinBD
	 */
	public String getCodigoPeriodoFinBD() {
		return codigoPeriodoFinBD;
	}

	/**
	 * @param codigoPeriodoFinBD
	 *            the codigoPeriodoFinBD to set
	 */
	public void setCodigoPeriodoFinBD(String codigoPeriodoFinBD) {
		this.codigoPeriodoFinBD = codigoPeriodoFinBD;
	}

	/**
	 * @return the indicadorNuevas
	 */
	public String getIndicadorNuevas() {
		return indicadorNuevas;
	}

	/**
	 * @param indicadorNuevas
	 *            the indicadorNuevas to set
	 */
	public void setIndicadorNuevas(String indicadorNuevas) {
		this.indicadorNuevas = indicadorNuevas;
	}

	/**
	 * @return the indicadorActividadConsultora
	 */
	public String getIndicadorActividadConsultora() {
		return indicadorActividadConsultora;
	}

	/**
	 * @param indicadorActividadConsultora
	 *            the indicadorActividadConsultora to set
	 */
	public void setIndicadorActividadConsultora(
			String indicadorActividadConsultora) {
		this.indicadorActividadConsultora = indicadorActividadConsultora;
	}
}