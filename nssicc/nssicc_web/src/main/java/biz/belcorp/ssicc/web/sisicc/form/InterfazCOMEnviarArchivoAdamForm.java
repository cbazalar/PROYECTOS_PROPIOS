/*
 * Created on 30-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="InterfazCOMEnviarArchivoAdamForm.java.html"> <i>View Source </i>
 * </a>
 * </p>
 * 
 * @author <a href="mailto:majimenez@belcorp.biz">Marco Antonio Agurto Jimenez</a>
 * 
 * @struts.form name = "interfazCOMEnviarArchivoAdamForm" extends =
 *              "baseInterfazForm"
 */

public class InterfazCOMEnviarArchivoAdamForm extends BaseInterfazForm
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String codigoPeriodoProceso1;

	private String codigoPeriodoProceso2;

	private String codigoComision;

	private String fechaProcesoInicio;

	private String fechaProcesoFin;
	
	private Date fechaProcesoInicioD;
	
	private Date fechaProcesoFinD;
	
	private String indicador;

	/**
	 * @return Returns the codigoComision.
	 */
	public String getCodigoComision() {
		return codigoComision;
	}

	/**
	 * @param codigoComision
	 *            The codigoComision to set.
	 * @struts.validator type = "required"
	 */
	public void setCodigoComision(String codigoComision) {
		this.codigoComision = codigoComision;
	}

	/**
	 * @return Returns the codigoPeriodoProceso1.
	 */
	public String getCodigoPeriodoProceso1() {
		return codigoPeriodoProceso1;
	}

	/**
	 * @param codigoPeriodoProceso1
	 *            The codigoPeriodoProceso1 to set.
	 * @struts.validator type="mask"
	 * @struts.validator-var name="mask" value="${campaign}"
	 */
	public void setCodigoPeriodoProceso1(String codigoPeriodoProceso1) {
		this.codigoPeriodoProceso1 = codigoPeriodoProceso1;
	}

	/**
	 * @return Returns the codigoPeriodoProceso2.
	 */
	public String getCodigoPeriodoProceso2() {
		return codigoPeriodoProceso2;
	}

	/**
	 * @param codigoPeriodoProceso2
	 *            The codigoPeriodoProceso2 to set.
	 * @struts.validator type="mask"
	 * @struts.validator-var name="mask" value="${campaign}"
	 */
	public void setCodigoPeriodoProceso2(String codigoPeriodoProceso2) {
		this.codigoPeriodoProceso2 = codigoPeriodoProceso2;
	}

	/**
	 * @return Returns the fechaProcesoFin.
	 */
	public String getFechaProcesoFin() {
		return fechaProcesoFin;
	}

	/**
	 * @param fechaProcesoFin
	 *            The fechaProcesoFin to set.
	 * @struts.validator type="date"
	 * @struts.validator-var name="datePatternStrict"
	 *                       value="${defaultDatePattern}"
	 */
	public void setFechaProcesoFin(String fechaProcesoFin) {
		this.fechaProcesoFin = fechaProcesoFin;
	}

	/**
	 * @return Returns the fechaProcesoInicio.
	 */
	public String getFechaProcesoInicio() {
		return fechaProcesoInicio;
	}

	/**
	 * @param fechaProcesoInicio
	 *            The fechaProcesoInicio to set.
	 * @struts.validator type="date"
	 * @struts.validator-var name="datePatternStrict"
	 *                       value="${defaultDatePattern}"
	 */
	public void setFechaProcesoInicio(String fechaProcesoInicio) {
		this.fechaProcesoInicio = fechaProcesoInicio;
	}

	/**
	 * @return Returns the indicador.
	 */
	public String getIndicador() {
		return indicador;
	}

	/**
	 * @param indicador The indicador to set.
	 */
	public void setIndicador(String indicador) {
		this.indicador = indicador;
	}

	public Date getFechaProcesoInicioD() {
		return fechaProcesoInicioD;
	}

	public void setFechaProcesoInicioD(Date fechaProcesoInicioD) {
		this.fechaProcesoInicioD = fechaProcesoInicioD;
	}

	public Date getFechaProcesoFinD() {
		return fechaProcesoFinD;
	}

	public void setFechaProcesoFinD(Date fechaProcesoFinD) {
		this.fechaProcesoFinD = fechaProcesoFinD;
	}
	
	
	

}
