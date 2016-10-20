package biz.belcorp.ssicc.web.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="BusquedaZonaPOPUPSearchForm.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:jonunez@belcorp.biz">Jose Nunez</a>
 *
 */

public class BusquedaRegionPOPUPSearchForm  extends BaseSearchForm{

	
	private String codigoRegion;
	private String descripcionRegion;
	

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
	 * @return the descripcionRegion
	 */
	public String getDescripcionRegion() {
		return descripcionRegion;
	}

	/**
	 * @param descripcionRegion the descripcionRegion to set
	 */
	public void setDescripcionRegion(String descripcionRegion) {
		this.descripcionRegion = descripcionRegion;
	}		

}
