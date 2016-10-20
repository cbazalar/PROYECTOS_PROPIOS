/**
 * 
 */
package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * TODO Include class description here.
 * @author Danny Amaro 
 * <p>
 * <a href="InterfazLARGenerarLARFacturacionElectronicaForm.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * 
 * @struts.form name = "interfazLARGenerarLARFacturacionElectronicaForm" extends = "baseInterfazForm"
 */

public class InterfazLARGenerarLARFacturacionElectronicaForm extends BaseInterfazForm
		implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8619771398737715904L;
	private String codigoPeriodo;
	private String fecha;
	private Date fechaD;
	private String[] tipoDocumento;
	private String[] regionList;
	private String[] zonaList;
	private String desde;
	private String hasta;
		
	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo the codigoPeriodo to set
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	
	/**
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the tipoDocumento
	 */
	public String[] getTipoDocumento() {
		return tipoDocumento;
	}

	/**
	 * @param tipoDocumento the tipoDocumento to set
	 */
	public void setTipoDocumento(String[] tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	/**
	 * @return the regionList
	 */
	public String[] getRegionList() {
		return regionList;
	}

	/**
	 * @param regionList the regionList to set
	 */
	public void setRegionList(String[] regionList) {
		this.regionList = regionList;
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

	/**
	 * @return the desde
	 */
	public String getDesde() {
		return desde;
	}

	/**
	 * @param desde the desde to set
	 */
	public void setDesde(String desde) {
		this.desde = desde;
	}

	/**
	 * @return the hasta
	 */
	public String getHasta() {
		return hasta;
	}

	/**
	 * @param hasta the hasta to set
	 */
	public void setHasta(String hasta) {
		this.hasta = hasta;
	}

	/**
	 * @return the fechaD
	 */
	public Date getFechaD() {
		return fechaD;
	}

	/**
	 * @param fechaD the fechaD to set
	 */
	public void setFechaD(Date fechaD) {
		this.fechaD = fechaD;
	}
	
	

	

}
