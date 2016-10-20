/*
 * Created on 26/09/2006 11:32:52 AM
 * biz.belcorp.ssicc.sisicc.web.form.InterfazMYEEnviarMovimientosCuentaCorrienteForm
 */
package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazCOMEnviarFicheroPagoComisionEjecutivaForm.java.html">
 * <i>View Source </i> </a>
 * </p>
 * 
 * @author <a>efernandezo</a>
 * 
 * @struts.form name = "interfazCOMEnviarFicheroPagoComisionEjecutivaForm"
 *              extends = "baseInterfazForm"
 */

public class InterfazCOMEnviarFicheroPagoComisionEjecutivaForm extends
		BaseInterfazForm implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codigoPeriodo;

	private String tipoComisionista;

	private String codigoComision;

	private String comisionIngresos;

	/**
	 * @return
	 */
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	/**
	 * @return
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @struts.validator type = "required"
	 * @param codigoPeriodo
	 *            the codigoPeriodo to set
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return
	 */
	public String getTipoComisionista() {
		return tipoComisionista;
	}

	/**
	 * @struts.validator type = "required"
	 * @param tipoComisionista
	 *            the tipoComisionista to set
	 */
	public void setTipoComisionista(String tipoComisionista) {
		this.tipoComisionista = tipoComisionista;
	}

	/**
	 * @return
	 */
	public String getCodigoComision() {
		return codigoComision;
	}

	/**
	 * @struts.validator type = "required"
	 * @param codigoComision
	 *            the codigoComision to set
	 */
	public void setCodigoComision(String codigoComision) {
		this.codigoComision = codigoComision;
	}

	/**
	 * @return Returns the comisionIngresos.
	 */
	public String getComisionIngresos() {
		return comisionIngresos;
	}

	/**
	 * @param comisionIngresos
	 *            The comisionIngresos to set.
	 */
	public void setComisionIngresos(String comisionIngresos) {
		this.comisionIngresos = comisionIngresos;
	}

}
