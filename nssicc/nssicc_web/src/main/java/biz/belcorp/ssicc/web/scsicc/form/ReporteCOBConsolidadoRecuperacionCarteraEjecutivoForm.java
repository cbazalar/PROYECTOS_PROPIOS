package biz.belcorp.ssicc.web.scsicc.form;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;


/**
 * The Class ReporteCOBConsolidadoRecuperacionCarteraEjecutivoForm.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 17/11/2014
 */
public class ReporteCOBConsolidadoRecuperacionCarteraEjecutivoForm extends BaseReporteForm {

	private static final long serialVersionUID = 1L;

	private String codigoPais;

	private String codigoPeriodo;
		
	private String codigoSociedad;
	
	private String codigoEtapaDeuda;
	
	private String[] regionList;

	private String[] zonaList;
	
	private String[] cobradorList;	
	
	private String cobrador;	
	
	private String descripcionRegionList;

	private String descripcionZonaList;

	private String descripcionCobradorList;
			
	private String tipoVista;
	
	private String codigoUsuarioLogin;
	
	private String descripcionUsuarioLogin;
	

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
	 * @return Returns the codigoEtapaDeuda.
	 */
	public String getCodigoEtapaDeuda() {
		return codigoEtapaDeuda;
	}

	/**
	 * @param codigoEtapaDeuda
	 *            The codigoEtapaDeuda to set.
	 * @struts.validator type = "required"
	 */
	public void setCodigoEtapaDeuda(String codigoEtapaDeuda) {
		this.codigoEtapaDeuda = codigoEtapaDeuda;
	}
	
	/**
	 * @return Returns the codigoPeriodo.
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @struts.validator type="required"
	 * @struts.validator type="mask"
	 * @struts.validator-var name="mask" value="${campaign}"
	 * @param codigoPeriodo The codigoPeriodo to set.
	 * 
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
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
	 * @return Returns the cobradorList.
	 */
	public String[] getCobradorList() {
		return cobradorList;
	}
	
	/**
	 * @param cobradorList
	 *            The cobradorList to set.
	 * 
	 */
	public void setCobradorList(String[] cobradorList) {
		this.cobradorList = cobradorList;
	}

	
	/**
	 * @return the cobrador
	 */
	public String getCobrador() {
		return cobrador;
	}

	/**
	 * @param cobrador the cobrador to set
	 */
	public void setCobrador(String cobrador) {
		this.cobrador = cobrador;
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
	 * @return Returns the descripcionCobradorList.
	 */
	public String getDescripcionCobradorList() {
		return descripcionCobradorList;
	}
	/**
	 * @param descripcionCobradorList The descripcionCobradorList to set.
	 */
	public void setDescripcionCobradorList(String descripcionCobradorList) {
		String temp = StringUtils.replace(descripcionCobradorList, "&&","\n" );
		this.descripcionCobradorList = temp;
	}	
	
	/**
	 * 
	 * @return Returns the tipoVista.
	 */
	public String getTipoVista() {
		return tipoVista;
	}

	/**	 
	 * @param tipoVista
	 *            The tipoVista to set.
	 * @struts.validator type="required"
	 */
	public void setTipoVista(String tipoVista) {
		this.tipoVista = tipoVista;
	}

	/**
	 * @return the codigoUsuarioLogin
	 */
	public String getCodigoUsuarioLogin() {
		return codigoUsuarioLogin;
	}

	/**
	 * @param codigoUsuarioLogin the codigoUsuarioLogin to set
	 * @struts.validator type="required"
	 */
	public void setCodigoUsuarioLogin(String codigoUsuarioLogin) {
		this.codigoUsuarioLogin = codigoUsuarioLogin;
	}

	/**
	 * @return the descripcionUsuarioLogin
	 */
	public String getDescripcionUsuarioLogin() {
		return descripcionUsuarioLogin;
	}

	/**
	 * @param descripcionUsuarioLogin the descripcionUsuarioLogion to set
	 * @struts.validator type="required"
	 */
	public void setDescripcionUsuarioLogin(String descripcionUsuarioLogin) {
		this.descripcionUsuarioLogin = descripcionUsuarioLogin;
	}

	
	
}
