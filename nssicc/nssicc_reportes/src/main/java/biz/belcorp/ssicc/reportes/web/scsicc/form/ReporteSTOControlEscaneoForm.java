package biz.belcorp.ssicc.reportes.web.scsicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * 
 * @author RRG
 * 
 */

public class ReporteSTOControlEscaneoForm extends BaseReporteForm	implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8128315555629957192L;

	private String codigoPais;	
	private String codigoPeriodo;	
	private String codigoRegion;
	private String[] zonaList;
	private String codigoTipoOrden;
	private String codigoCompaniaTransporte;
	private String codigoCentroAcopio;
	private String tipoReporte;
		
	/**	 
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return this.codigoPais;
	}

	/**
	 * @return Returns the codigoPeriodo.
	 */	
	public String getCodigoPeriodo() {
		return this.codigoPeriodo;
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
	 * @struts.validator type="required"
	 * @struts.validator type="mask"
	 * @struts.validator-var name="mask" value="${campaign}"
	 * @param codigoPeriodo
	 *            The codigoPeriodo to set.	 
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	
	/**
	 * @return the codigoRegion
	 */
	public String getCodigoRegion() {
		return codigoRegion;
	}

	/**
	 * @param codigoRegion the codigoRegion to set
	 */
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}

	/**
	 * @return the codigoTipoOrden
	 */
	public String getCodigoTipoOrden() {
		return codigoTipoOrden;
	}

	/**
	 * @param codigoTipoOrden the codigoTipoOrden to set
	 */
	public void setCodigoTipoOrden(String codigoTipoOrden) {
		this.codigoTipoOrden = codigoTipoOrden;
	}

	/**
	 * @return the codigoCompaniaTransporte
	 */
	public String getCodigoCompaniaTransporte() {
		return codigoCompaniaTransporte;
	}

	/**
	 * @param codigoCompaniaTransporte the codigoCompaniaTransporte to set
	 */
	public void setCodigoCompaniaTransporte(String codigoCompaniaTransporte) {
		this.codigoCompaniaTransporte = codigoCompaniaTransporte;
	}

	/**
	 * @return the codigoCentroAcopio
	 */
	public String getCodigoCentroAcopio() {
		return codigoCentroAcopio;
	}

	/**
	 * @param codigoCentroAcopio the codigoCentroAcopio to set
	 */
	public void setCodigoCentroAcopio(String codigoCentroAcopio) {
		this.codigoCentroAcopio = codigoCentroAcopio;
	}

	/**
	 * @return the tipoReporte
	 */
	public String getTipoReporte() {
		return tipoReporte;
	}

	/**
	 * @param tipoReporte the tipoReporte to set
	 */
	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}
	
	/**
	 * @return the zonaList
	 */
	public String[] getZonaList() {
		return zonaList;
	}

	/**
	 * @param zonaList the zonaList to set
	 */
	public void setZonaList(String[] zonaList) {
		this.zonaList = zonaList;
	}


	

	/* (non-Javadoc)
	 * @see org.apache.struts.validator.ValidatorForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
//	public void reset(ActionMapping mapping, HttpServletRequest request) {		
//		codigoRegion="";
//		codigoTipoOrden="";
//		codigoCompaniaTransporte="";
//		codigoCentroAcopio="";
//		tipoReporte="";
//		this.zonaList = new String[0];
//		HttpSession session = request.getSession(true);
//		session.setAttribute(Constants.SICC_ZONA_LIST, new ArrayList());
//	}
}
