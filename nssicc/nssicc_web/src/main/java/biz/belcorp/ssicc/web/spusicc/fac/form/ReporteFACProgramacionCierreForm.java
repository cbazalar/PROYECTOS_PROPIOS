package biz.belcorp.ssicc.web.spusicc.fac.form;

import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * @author <a href="mailto:aoviedo@sigcomt.com">Aurelio Oviedo</a>
 */

public class ReporteFACProgramacionCierreForm extends BaseReporteForm{
	
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private Date fechaFacDesde;
	private Date fechaFacHasta;
	private String codigoPeriodoInicio;
	private String codigoPeriodoFin;
	private String tipoCierre;
	private String estado;
	
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	/**
	 * @return the fechaFacDesde
	 */
	public Date getFechaFacDesde() {
		return fechaFacDesde;
	}
	/**
	 * @param fechaFacDesde the fechaFacDesde to set
	 */
	public void setFechaFacDesde(Date fechaFacDesde) {
		this.fechaFacDesde = fechaFacDesde;
	}
	/**
	 * @return the fechaFacHasta
	 */
	public Date getFechaFacHasta() {
		return fechaFacHasta;
	}
	/**
	 * @param fechaFacHasta the fechaFacHasta to set
	 */
	public void setFechaFacHasta(Date fechaFacHasta) {
		this.fechaFacHasta = fechaFacHasta;
	}
	/**
	 * @return the codigoPeriodoInicio
	 */
	public String getCodigoPeriodoInicio() {
		return codigoPeriodoInicio;
	}
	/**
	 * @param codigoPeriodoInicio the codigoPeriodoInicio to set
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
	 */
	public void setCodigoPeriodoFin(String codigoPeriodoFin) {
		this.codigoPeriodoFin = codigoPeriodoFin;
	}
	/**
	 * @return the tipoCierre
	 */
	public String getTipoCierre() {
		return tipoCierre;
	}
	/**
	 * @param tipoCierre the tipoCierre to set
	 */
	public void setTipoCierre(String tipoCierre) {
		this.tipoCierre = tipoCierre;
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
}