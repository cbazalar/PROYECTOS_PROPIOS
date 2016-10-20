package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * @author <a href="jvelasquez@sigcomt.com">Jorge Velasquez</a>
 * 
 * @struts.form name = "interfazXRXNotaCreditoElectronicaForm" extends =
 *              "baseInterfazPaqueteForm"
 */

public class InterfazXRXNotaCreditoElectronicaForm extends
		BaseInterfazForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1099140974897270230L;

	private String tipoRecepcion;

	private String codigoPeriodo;

	private String numLoteSTO;

	private String codigoBatch;
	
	private String fechaProceso;
	
	private Date fechaProcesoD;

	private String listaArchivos;
	
	/**
	 * @return the codigoBatch
	 */
	public String getCodigoBatch() {
		return codigoBatch;
	}

	/**
	 * @param codigoBatch
	 *            the codigoBatch to set
	 */
	public void setCodigoBatch(String codigoBatch) {
		this.codigoBatch = codigoBatch;
	}

	/**
	 * @return Returns the numLoteSTO.
	 */
	public String getNumLoteSTO() {
		return numLoteSTO;
	}

	/**
	 * @param numLoteSTO
	 *            The numLoteSTO to set.
	 */
	public void setNumLoteSTO(String numLoteSTO) {
		this.numLoteSTO = numLoteSTO;
	}

	public String getTipoRecepcion() {
		return tipoRecepcion;
	}

	/**
	 * @struts.validator type = "required"
	 */
	public void setTipoRecepcion(String tipoRecepcion) {
		this.tipoRecepcion = tipoRecepcion;
	}

	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo
	 *            the codigoPeriodo to set
	 *
	 * @struts.validator type = "required"
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return the fechaProceso
	 */
	public String getFechaProceso() {
		return fechaProceso;
	}

	/**
	 * @param fechaProceso the fechaProceso to set
	 * 
	 * @struts.validator type = "required"
	 * @struts.validator type="date"
	 * @struts.validator-var name="datePatternStrict"
	 *                       value="${defaultDatePattern}"
	 */
	public void setFechaProceso(String fechaProceso) {
		this.fechaProceso = fechaProceso;
	}

	/**
	 * @return
	 */
	public String getListaArchivos() {
		return listaArchivos;
	}

	/**
	 * @param listaArchivos
	 */
	public void setListaArchivos(String listaArchivos) {
		this.listaArchivos = listaArchivos;
	}

	/**
	 * @return the fechaProcesoD
	 */
	public Date getFechaProcesoD() {
		return fechaProcesoD;
	}

	/**
	 * @param fechaProcesoD the fechaProcesoD to set
	 */
	public void setFechaProcesoD(Date fechaProcesoD) {
		this.fechaProcesoD = fechaProcesoD;
	}
	
	
	
}
