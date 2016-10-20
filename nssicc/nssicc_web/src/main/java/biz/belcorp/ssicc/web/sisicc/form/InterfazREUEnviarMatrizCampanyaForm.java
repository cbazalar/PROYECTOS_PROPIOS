/*
 * Created on 21-dic-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="InterfazREUEnviarMatrizCampanyaForm.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cmarius@belcorp.biz">Carla Marius</a>
 * 
 * @struts.form
 *   name = "interfazREUEnviarMatrizCampanyaForm"
 * 	 extends = "baseInterfazForm"	
 */

public class InterfazREUEnviarMatrizCampanyaForm extends BaseInterfazForm implements Serializable {
	private String codigoMarca;
	private String codigoCanal;
	private String codigoAcceso;
	private String periodoDesde;
	private String periodoHasta;
		
    /**
	 * @return Returns the codigoAcceso.
	 */
	public String getCodigoAcceso() {
		return codigoAcceso;
	}

	/**
	 * @param codigoAcceso The codigoAcceso to set.
	 */
	public void setCodigoAcceso(String codigoAcceso) {
		this.codigoAcceso = codigoAcceso;
	}

	/**
	 * @return Returns the codigoCanal.
	 */
	public String getCodigoCanal() {
		return codigoCanal;
	}

	/**
	 * @param codigoCanal The codigoCanal to set.
	 */
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}

	/**
	 * @return Returns the codigoMarca.
	 */
	public String getCodigoMarca() {
		return codigoMarca;
	}

	/**
	 * @param codigoMarca The codigoMarca to set.
	 */
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}

	/**
	 * @return Returns the periodoDesde.
	 */
	public String getPeriodoDesde() {
		return periodoDesde;
	}

	/**
	 * @param periodoDesde The periodoDesde to set.
	 * @struts.validator type = "required"
	 * @struts.validator type="mask"
	 * @struts.validator-var name="mask" value="${campaign}"
	 */
	public void setPeriodoDesde(String periodoDesde) {
		this.periodoDesde = periodoDesde;
	}

	/**
	 * @return Returns the periodoHasta.
	 */
	public String getPeriodoHasta() {
		return periodoHasta;
	}

	/**
	 * @param periodoHasta The periodoHasta to set.
	 * @struts.validator type = "required"
	 * @struts.validator type="mask"
	 * @struts.validator-var name="mask" value="${campaign}"
	 */
	public void setPeriodoHasta(String periodoHasta) {
		this.periodoHasta = periodoHasta;
	}

	
}
