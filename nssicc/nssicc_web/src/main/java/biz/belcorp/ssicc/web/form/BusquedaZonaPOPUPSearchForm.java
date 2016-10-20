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

public class BusquedaZonaPOPUPSearchForm  extends BaseSearchForm{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3769951371144342051L;
	
	private Integer oidZona;/** Numero secuencial de la zona*/
	private String codigoZona; /** Codigo de Zona*/
	private String descripcionZona;/** Descripcion de la zona*/
	
	
	/**
	 * @return Returns the codigoZona.
	 */
	public String getCodigoZona() {
		return codigoZona;
	}
	/**
	 * @param codigoZona The codigoZona to set.
	 */
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}
	/**
	 * @return Returns the descripcionZona.
	 */
	public String getDescripcionZona() {
		return descripcionZona;
	}
	/**
	 * @param descripcionZona The descripcionZona to set.
	 */
	public void setDescripcionZona(String descripcionZona) {
		this.descripcionZona = descripcionZona;
	}
	/**
	 * @return Returns the oidZona.
	 */
	public Integer getOidZona() {
		return oidZona;
	}
	/**
	 * @param oidZona The oidZona to set.
	 */
	public void setOidZona(Integer oidZona) {
		this.oidZona = oidZona;
	}

}
