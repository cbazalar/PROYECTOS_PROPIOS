/*
 * Created on 24-nov-2005
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
 * <a href="InterfazLLIEnviarVentaRealDiariaAcumuladaForm.java.html"> <i>View
 * Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:lmendoza@belcorp.biz">Luis Mendoza Veran</a>
 * 
 * @struts.form name = "interfazLLIEnviarVentaRealDiariaAcumuladaForm" extends =
 *              "baseInterfazForm"
 */

public class InterfazLLIEnviarVentaRealDiariaAcumuladaForm extends
		BaseInterfazForm implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoPeriodo;
	private String codigoMarca;
	private String codigoCanal;
	private String fechaFacturacionInicial;
	private String fechaFacturacionFinal;
	private Date fechaFacturacionInicialDate;
	private Date fechaFacturacionFinalDate;


	public Date getFechaFacturacionInicialDate() {
		return fechaFacturacionInicialDate;
	}

	public void setFechaFacturacionInicialDate(Date fechaFacturacionInicialDate) {
		this.fechaFacturacionInicialDate = fechaFacturacionInicialDate;
	}

	public Date getFechaFacturacionFinalDate() {
		return fechaFacturacionFinalDate;
	}

	public void setFechaFacturacionFinalDate(Date fechaFacturacionFinalDate) {
		this.fechaFacturacionFinalDate = fechaFacturacionFinalDate;
	}

	/**
	 * @return Returns the codigoPeriodo.
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo
	 *            The codigoPeriodo to set.
	 * 
	 * @struts.validator type = "required"
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return Returns the codigoCanal.
	 */
	public String getCodigoCanal() {
		return codigoCanal;
	}

	/**
	 * @param codigoCanal The codigoCanal to set.
	 * @struts.validator type = "required"
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
	 * @struts.validator type = "required"
	 */
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}

	/**
	 * @return Returns the fechaFacturacionFinal.
	 */
	public String getFechaFacturacionFinal() {
		return fechaFacturacionFinal;
	}

	/**
	 * @param fechaFacturacionFinal The fechaFacturacionFinal to set.
	 * @struts.validator type = "required"
	 */
	public void setFechaFacturacionFinal(String fechaFacturacionFinal) {
		this.fechaFacturacionFinal = fechaFacturacionFinal;
	}

	/**
	 * @return Returns the fechaFacturacionInicial.
	 */
	public String getFechaFacturacionInicial() {
		return fechaFacturacionInicial;
	}

	/**
	 * @param fechaFacturacionInicial The fechaFacturacionInicial to set.
	 * @struts.validator type = "required"
	 */
	public void setFechaFacturacionInicial(String fechaFacturacionInicial) {
		this.fechaFacturacionInicial = fechaFacturacionInicial;
	}
}
