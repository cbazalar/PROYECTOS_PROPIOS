package biz.belcorp.ssicc.web.scsicc.form;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;


/**
 * 
 * @author <a href="mailto:majimenez@belcorp.biz">Marco Agurto</a>
 * 
 */
public class ReporteRECListadoReclamosDetalleForm extends BaseReporteForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private String codigoPais;

	private String codigoPeriodoInicial;

	private String codigoPeriodoFinal;	

	private String[] operacionList;

	private String[] tipoOperacionList;
	
	private String[] motivoReclamoList;
	
	private String codigoConsultora;

	private String descripcionConsultora;
	
	private String codigoSap;

	private String descripcionCorta;

	private String descripcionOperacion;

	private String descripcionTipoOperacionList;	

	private String descripcionMotivoReclamoList;

	private String tipoReporte;
	
	private String tipoMovimiento;

	private String accion;
	/**
	 * @return Returns the tipoMovimiento.
	 */
	public String getTipoMovimiento() {
		return tipoMovimiento;
	}

	/**
	 * @param tipoMovimiento The tipoMovimiento to set.
	 */
	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}


	/**
	 * 
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 *            The codigoPais to set.
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return Returns the tipoOperacionList.
	 */
	public String[] getTipoOperacionList() {
		return tipoOperacionList;
	}

	/**
	 * @param tipoOperacionList
	 *            The tipoOperacionList to set.
	 */
	public void setTipoOperacionList(String[] tipoOperacionList) {
		this.tipoOperacionList = tipoOperacionList;
	}

	/**
	 * @return Returns the tipoReporte.
	 */
	public String getTipoReporte() {
		return tipoReporte;
	}

	/**
	 * @param tipoReporte
	 *            The tipoReporte to set.
	 */
	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}
	/**
	 * @return Returns the codigoPeriodoFinal.
	 */
	public String getCodigoPeriodoFinal() {
		return codigoPeriodoFinal;
	}

	/**
	 * @param codigoPeriodoFinal
	 *            The codigoPeriodoFinal to set.
	 */
	public void setCodigoPeriodoFinal(String codigoPeriodoFinal) {
		this.codigoPeriodoFinal = codigoPeriodoFinal;
	}

	/**
	 * @return Returns the codigoPeriodoInicial.
	 */
	public String getCodigoPeriodoInicial() {
		return codigoPeriodoInicial;
	}

	/**
	 * @param codigoPeriodoInicial
	 *            The codigoPeriodoInicial to set.
	 * 
	 */
	public void setCodigoPeriodoInicial(String codigoPeriodoInicial) {
		this.codigoPeriodoInicial = codigoPeriodoInicial;
	}

	/**
	 * @return Returns the codigoConsultora.
	 */
	public String getCodigoConsultora() {
		return codigoConsultora;
	}

	/**
	 * @param codigoConsultora
	 *            The codigoConsultora to set.
	 */
	public void setCodigoConsultora(String codigoConsultora) {
		this.codigoConsultora = codigoConsultora;
	}

	/**
	 * @return Returns the descripcionConsultora.
	 */
	public String getDescripcionConsultora() {
		return descripcionConsultora;
	}

	/**
	 * @param descripcionConsultora
	 *            The descripcionConsultora to set.
	 */
	public void setDescripcionConsultora(String descripcionConsultora) {
		this.descripcionConsultora = descripcionConsultora;
	}
	/**
	 * @return Returns the descripcionOperacion.
	 */
	public String getDescripcionOperacion() {
		return descripcionOperacion;
	}

	/**
	 * @param descripcionOperacion
	 *            The descripcionOperacion to set.
	 */
	public void setDescripcionOperacion(String descripcionOperacion) {
		String temp = StringUtils.replace(descripcionOperacion, "&&","\n" );
		this.descripcionOperacion = temp;
	}

		/**
	 * @return Returns the descripcionTipoOperacionList.
	 */
	public String getDescripcionTipoOperacionList() {
		return descripcionTipoOperacionList;
	}

	/**
	 * @param descripcionTipoOperacionList
	 *            The descripcionTipoOperacionList to set.
	 */
	public void setDescripcionTipoOperacionList(
			String descripcionTipoOperacionList) {
		this.descripcionTipoOperacionList = descripcionTipoOperacionList;
	}

	
	/**
	 * @return Returns the codigoSap.
	 */
	public String getCodigoSap() {
		return codigoSap;
	}

	/**
	 * @param codigoSap The codigoSap to set.
	 */
	public void setCodigoSap(String codigoSap) {
		this.codigoSap = codigoSap;
	}

	/**
	 * @return Returns the descripcionCorta.
	 */
	public String getDescripcionCorta() {
		return descripcionCorta;
	}

	/**
	 * @param descripcionCorta The descripcionCorta to set.
	 */
	public void setDescripcionCorta(String descripcionCorta) {
		this.descripcionCorta = descripcionCorta;
	}

	/**
	 * @return Returns the descripcionMotivoReclamoList.
	 */
	public String getDescripcionMotivoReclamoList() {
		return descripcionMotivoReclamoList;
	}

	/**
	 * @param descripcionMotivoReclamoList The descripcionMotivoReclamoList to set.
	 */
	public void setDescripcionMotivoReclamoList(String descripcionMotivoReclamoList) {
		String temp = StringUtils.replace(descripcionMotivoReclamoList, "&&","\n" );
		this.descripcionMotivoReclamoList = temp;
	}

	/**
	 * @return Returns the motivoReclamoList.
	 */
	public String[] getMotivoReclamoList() {
		return motivoReclamoList;
	}

	/**
	 * @param motivoReclamoList The motivoReclamoList to set.
	 */
	public void setMotivoReclamoList(String[] motivoReclamoList) {
		this.motivoReclamoList = motivoReclamoList;
	}

	/**
	 * @return Returns the operacionList.
	 */
	public String[] getOperacionList() {
		return operacionList;
	}

	/**
	 * @param operacionList The operacionList to set.
	 */
	public void setOperacionList(String[] operacionList) {
		this.operacionList = operacionList;
	}

	/**
	 * @return the accion
	 */
	public String getAccion() {
		return accion;
	}

	/**
	 * @param accion the accion to set
	 */
	public void setAccion(String accion) {
		this.accion = accion;
	}

	
}
