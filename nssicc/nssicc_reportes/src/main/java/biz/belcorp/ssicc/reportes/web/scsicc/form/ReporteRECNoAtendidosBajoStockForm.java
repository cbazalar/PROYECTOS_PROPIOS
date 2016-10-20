package biz.belcorp.ssicc.reportes.web.scsicc.form;


import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * 
 * @author <a href="mailto:majimenez@belcorp.biz">Marco Agurto</a>
 * 
 * @struts.form name="reporteRECNoAtendidosBajoStockForm" extends="baseReporteForm"
 */
public class ReporteRECNoAtendidosBajoStockForm extends BaseReporteForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String[] marcaList;

	private String codigoPais;

	private String codigoPeriodoInicial;

	private String codigoPeriodoFinal;
	
	private String tipoPeriodo;

	private String[] operacionList;

	private String[] tipoOperacionList;

	private String[] regionList;

	private String[] zonaList;

	private String[] territorioList;

	private String[] unidadNegocioList;

	private String[] negocioList;

	private String tipoReporte;
	
	private String codigoConsultora;
	
	private String descripcionConsultora;

	/**
	 * @return Returns the codigoConsultora.
	 */
	public String getCodigoConsultora() {
		return codigoConsultora;
	}

	/**
	 * @param codigoConsultora The codigoConsultora to set.
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
	 * @param descripcionConsultora The descripcionConsultora to set.
	 */
	public void setDescripcionConsultora(String descripcionConsultora) {
		this.descripcionConsultora = descripcionConsultora;
	}

	public void reset() {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
//		String periodo = sdf.format(new Date(System.currentTimeMillis()));
//		this.codigoPeriodoInicial = (String) request.getSession().getAttribute(
//				"periodoActual");
//		if (StringUtils.isEmpty(this.codigoPeriodoInicial))
//			this.codigoPeriodoInicial = periodo;
//		this.codigoPeriodoFinal = (String) request.getSession().getAttribute(
//				"periodoActual");
//		if (StringUtils.isEmpty(this.codigoPeriodoFinal))
//			this.codigoPeriodoFinal = periodo;

	}
	
	/**
	 * 
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @struts.validator type="required"
	 * @param codigoPais
	 *            The codigoPais to set.
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return Returns the negocioList.
	 */
	public String[] getNegocioList() {
		return negocioList;
	}

	/**
	 * @param negocioList
	 *            The negocioList to set.
	 */
	public void setNegocioList(String[] negocioList) {
		this.negocioList = negocioList;
	}

	/**
	 * @return Returns the regionList.
	 */
	public String[] getRegionList() {
		return regionList;
	}

	/**
	 * @param regionList
	 *            The regionList to set.
	 */
	public void setRegionList(String[] regionList) {
		this.regionList = regionList;
	}

	/**
	 * @return Returns the territorioList.
	 */
	public String[] getTerritorioList() {
		return territorioList;
	}

	/**
	 * @param territorioList
	 *            The territorioList to set.
	 */
	public void setTerritorioList(String[] territorioList) {
		this.territorioList = territorioList;
	}

	/**
	 * @return Returns the tipoOperacionList.
	 */
	public String[] getTipoOperacionList() {
		return tipoOperacionList;
	}

	/**
	 * @struts.validator type="required"
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
	 * @return Returns the unidadNegocioList.
	 */
	public String[] getUnidadNegocioList() {
		return unidadNegocioList;
	}

	/**
	 * @param unidadNegocioList
	 *            The unidadNegocioList to set.
	 */
	public void setUnidadNegocioList(String[] unidadNegocioList) {
		this.unidadNegocioList = unidadNegocioList;
	}

	/**
	 * @return Returns the zonaList.
	 */
	public String[] getZonaList() {
		return zonaList;
	}

	/**
	 * @param zonaList
	 *            The zonaList to set.
	 */
	public void setZonaList(String[] zonaList) {
		this.zonaList = zonaList;
	}

	/**
	 * @return Returns the marcaList.
	 */
	public String[] getMarcaList() {
		return marcaList;
	}

	/**
	 * @param marcaList The marcaList to set.
	 */
	public void setMarcaList(String[] marcaList) {
		this.marcaList = marcaList;
	}

	/**
	 * @return Returns the codigoPeriodoFinal.
	 */
	public String getCodigoPeriodoFinal() {
		return codigoPeriodoFinal;
	}

	/**
	 * @struts.validator type="required"
	 * @struts.validator type="mask"
	 * @struts.validator-var name="mask" value="${campaign}"
	 * @param codigoPeriodoFinal The codigoPeriodoFinal to set.
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
	 * @struts.validator type="required"
	 * @struts.validator type="mask"
	 * @struts.validator-var name="mask" value="${campaign}"
	 * @param codigoPeriodoInicial The codigoPeriodoInicial to set.
	 * 
	 */
	public void setCodigoPeriodoInicial(String codigoPeriodoInicial) {
		this.codigoPeriodoInicial = codigoPeriodoInicial;
	}

	/**
	 * @return Returns the tipoPeriodo.
	 */
	public String getTipoPeriodo() {
		return tipoPeriodo;
	}

	/**
	 * @param tipoPeriodo The tipoPeriodo to set.
	 */
	public void setTipoPeriodo(String tipoPeriodo) {
		this.tipoPeriodo = tipoPeriodo;
	}

	/**
	 * @return Returns the operacionList.
	 */
	public String[] getOperacionList() {
		return operacionList;
	}

	/**
	 * @param operacionList The operacionList to set.
	 * @struts.validator type="required"
	 */
	public void setOperacionList(String[] operacionList) {
		this.operacionList = operacionList;
	}

}
