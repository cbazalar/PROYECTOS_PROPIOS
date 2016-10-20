package biz.belcorp.ssicc.web.spusicc.let.form;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ReporteLETLideresForm extends BaseReporteForm{
	
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String codigoPrograma;
	private String codigoPeriodoInicio;
	private String codigoPeriodoFin;
	private String tipoEstado;
	
	private String descripcionPrograma;	
	
	/**
	 * @return
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
	 * @return the codigoPrograma
	 */
	public String getCodigoPrograma() {
		return codigoPrograma;
	}

	/**
	 * @param codigoPrograma the codigoPrograma to set
	 * 
	 */
	public void setCodigoPrograma(String codigoPrograma) {
		this.codigoPrograma = codigoPrograma;
	}

	/**
	 * @return the codigoPeriodoInicio
	 */
	public String getCodigoPeriodoInicio() {
		return codigoPeriodoInicio;
	}

	/**
	 * @param codigoPeriodoInicio the codigoPeriodoInicio to set
	 * 
	 */
	public void setCodigoPeriodoInicio(String codigoPeriodoInicio) {
		this.codigoPeriodoInicio = codigoPeriodoInicio;
	}

	/**
	 * @return the codigoPeriodoFin
	 */
	public String getCodigoPeriodoFin() {
		return codigoPeriodoFin;
	}

	/**
	 * @param codigoPeriodoFin the codigoPeriodoFin to set
	 * 
	 */
	public void setCodigoPeriodoFin(String codigoPeriodoFin) {
		this.codigoPeriodoFin = codigoPeriodoFin;
	}

	/**
	 * @return the tipoEstado
	 */
	public String getTipoEstado() {
		return tipoEstado;
	}

	/**
	 * @param tipoEstado the tipoEstado to set
	 * 
	 */
	public void setTipoEstado(String tipoEstado) {
		this.tipoEstado = tipoEstado;
	}

	/**
	 * @return the descripcionPrograma
	 */
	public String getDescripcionPrograma() {
		return descripcionPrograma;
	}

	/**
	 * @param descripcionPrograma the descripcionPrograma to set
	 */
	public void setDescripcionPrograma(String descripcionPrograma) {
		this.descripcionPrograma = descripcionPrograma;
	}
}