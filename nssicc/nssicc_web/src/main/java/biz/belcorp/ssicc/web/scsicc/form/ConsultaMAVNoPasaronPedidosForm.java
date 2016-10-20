package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="ConsultaMAVNoPasaronPedidosForm.java.html"> <i>View Source</i> </a>
 * </p>
 * 
  * @author <a href="">Alex Villavicencio</a>
 * 
 * @struts.form name = "consultaMAVNoPasaronPedidosForm"
 */
public class ConsultaMAVNoPasaronPedidosForm extends BaseReporteForm  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String codigoPais;
	
	private String codigoPeriodo;
	
	private String codigoSAP;

	private String[] regionList;

	private String[] zonaList;

	private String listCodigoRegion;

	private String listCodigoZona;

	public void reset() {
		this.regionList = this.zonaList = null;
	}
	/**
	 * 
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}


	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	
	/**
	 * @return Returns the codigoPeriodo.
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	
	
	/**
	 * @return the codigoSAP
	 */
	public String getCodigoSAP() {
		return codigoSAP;
	}

	/**
	 * @param codigoSAP the codigoSAP to set
	 */
	public void setCodigoSAP(String codigoSAP) {
		this.codigoSAP = codigoSAP;
	}
	

	/**
	 * @return Returns the regionList.
	 */
	public String[] getRegionList() {
		return regionList;
	}


	public void setRegionList(String[] regionList) {
		this.regionList = regionList;
	}

	/**
	 * @return Returns the zonaList.
	 */
	public String[] getZonaList() {
		return zonaList;
	}

	public void setZonaList(String[] zonaList) {
		this.zonaList = zonaList;
	}

	public String getListCodigoRegion() {
		return listCodigoRegion;
	}
	/**
	 * @param listCodigoRegion The listCodigoRegion to set.
	 */
	public void setListCodigoRegion(String listCodigoRegion) {
		this.listCodigoRegion = listCodigoRegion;
	}
	/**
	 * @return Returns the listCodigoZona.
	 */
	public String getListCodigoZona() {
		return listCodigoZona;
	}
	/**
	 * @param listCodigoZona The listCodigoZona to set.
	 */
	public void setListCodigoZona(String listCodigoZona) {
		this.listCodigoZona = listCodigoZona;
	}
	
}
