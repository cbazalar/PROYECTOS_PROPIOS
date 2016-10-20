package biz.belcorp.ssicc.web.scsicc.form;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * The Class ReporteCOBDetalladoCobranza31DiasForm.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 17/03/2014
 */
public class ReporteCOBDetalladoCobranza31DiasForm extends BaseReporteForm {
	
	private static final long serialVersionUID = 1L;

	private String codigoPais;
	
	private String codigoSociedad;

    private String codigoPeriodoInicial;
	
	private String codigoPeriodoFinal;
					
	private String[] regionList;

	private String[] zonaList;
	
	private String descripcionRegionList;

	private String descripcionZonaList;
	
	private boolean mostrarBotonExcel;
				
//	public void reset(ActionMapping mapping, HttpServletRequest request) {
//		
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
//		String periodo = sdf.format(new Date(System.currentTimeMillis()));
//		 
//		this.codigoPeriodoInicial = (String) request.getSession().getAttribute(
//				"periodoActual");
//		if (StringUtils.isEmpty(this.codigoPeriodoInicial))
//			this.codigoPeriodoInicial = periodo;
//		
//		this.codigoPeriodoFinal = (String) request.getSession().getAttribute(
//		"periodoActual");
//		if (StringUtils.isEmpty(this.codigoPeriodoFinal))
//			this.codigoPeriodoFinal = periodo;
//				
//	}

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
	 * 
	 * @return Returns the codigoSociedad.
	 */
	public String getCodigoSociedad() {
		return codigoSociedad;
	}

	/**
	 * @struts.validator type="required"
	 * @param codigoSociedad
	 *            The CodigoSociedad to set.
	 */
	public void setCodigoSociedad(String codigoSociedad) {
		this.codigoSociedad = codigoSociedad;
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
	 * 
	 */
	public void setCodigoPeriodoFinal(String codigoPeriodoFinal) {
		this.codigoPeriodoFinal = codigoPeriodoFinal;
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
	 * 
	 */
	public void setRegionList(String[] regionList) {
		this.regionList = regionList;
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
	 * 
	 */
	public void setZonaList(String[] zonaList) {
		this.zonaList = zonaList;
	}

		
	/**
	 * @return Returns the descripcionRegionList.
	 */
	public String getDescripcionRegionList() {
		return descripcionRegionList;
	}
	/**
	 * @param descripcionRegionList The descripcionRegionList to set.
	 */
	public void setDescripcionRegionList(String descripcionRegionList) {
		String temp = StringUtils.replace(descripcionRegionList, "&&","\n" );
		this.descripcionRegionList = temp;
	}
	
	
	/**
	 * @return Returns the descripcionZonaList.
	 */
	public String getDescripcionZonaList() {
		return descripcionZonaList;
	}
	
	/**
	 * @param descripcionZonaList The descripcionZonaList to set.
	 */
	public void setDescripcionZonaList(String descripcionZonaList) {
		String temp = StringUtils.replace(descripcionZonaList, "&&","\n" );
		this.descripcionZonaList = temp;
	}

	/**
	 * @return the mostrarBotonExcel
	 */
	public boolean isMostrarBotonExcel() {
		return mostrarBotonExcel;
	}

	/**
	 * @param mostrarBotonExcel the mostrarBotonExcel to set
	 */
	public void setMostrarBotonExcel(boolean mostrarBotonExcel) {
		this.mostrarBotonExcel = mostrarBotonExcel;
	}
	
	
	
}
