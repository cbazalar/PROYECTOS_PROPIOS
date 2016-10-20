package biz.belcorp.ssicc.web.scsicc.form;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;


/**
 * 
 * @author <a href="mailto:jjrios@csigcomt.com">Jesse James Rios Franco</a>
 * 
 * @struts.form name="reporteLIDResumenZonaForm" extends="baseReporteForm"
 */
public class ReporteLIDResumenZonaForm extends BaseReporteForm{

	private static final long serialVersionUID = 1L;
	
	private String codigoPais;

	private String codigoPeriodo;
	
	private String codigoMarca;	
	
	private String codigoCanal;
	
	private String[] regionList;

	private String[] zonaList;	

	/**
	 * @return
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 * @struts.validator type="required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo
	 * @struts.validator type="required"
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return
	 */
	public String getCodigoMarca() {
		return codigoMarca;
	}

	/**
	 * @param codigoMarca
	 * @struts.validator type="required"
	 */
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}

	/**
	 * @return
	 */
	public String getCodigoCanal() {
		return codigoCanal;
	}

	/**
	 * @param codigoCanal
	 * @struts.validator type="required"
	 */
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}

	/**
	 * @return
	 */
	public String[] getRegionList() {
		return regionList;
	}

	/**
	 * @param regionList
	 */
	public void setRegionList(String[] regionList) {
		this.regionList = regionList;
	}

	/**
	 * @return
	 */
	public String[] getZonaList() {
		return zonaList;
	}

	/**
	 * @param zonaList
	 */
	public void setZonaList(String[] zonaList) {
		this.zonaList = zonaList;
	}
}