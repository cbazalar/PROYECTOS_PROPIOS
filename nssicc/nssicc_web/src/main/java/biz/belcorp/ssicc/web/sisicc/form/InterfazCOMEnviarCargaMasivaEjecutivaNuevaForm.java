/*
 * Created on 26/09/2006 11:32:52 AM
 * biz.belcorp.ssicc.sisicc.web.form.InterfazMYEEnviarMovimientosCuentaCorrienteForm
 */
package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazCOMEnviarCargaMasivaEjecutivaNuevaForm.java.html"> <i>View
 * Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jcairampoma@belcorp.biz">EFO </a>
 * 
 * @struts.form name = "interfazCOMEnviarCargaMasivaEjecutivaNuevaForm" extends =
 *              "baseInterfazForm"
 */

public class InterfazCOMEnviarCargaMasivaEjecutivaNuevaForm extends BaseInterfazForm implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codigoMarca;

	private String codigoCanal;

	private String anioInicial;
	
	private String codigoTramo;
	
	private String tipoComisionista;

	private String descripcionMarca;
	
	private String descripcionCanal;	
	
	
	public InterfazCOMEnviarCargaMasivaEjecutivaNuevaForm() {
		this.codigoMarca = Constants.CODIGO_MARCA_DEFAULT;
		this.codigoCanal = Constants.CODIGO_CANAL_DEFAULT;		
		this.tipoComisionista = Constants.CODIGO_TIPO_COMISIONISTA_DEFAULT;
	}

	/**
	 * @return the codigoMarca
	 */
	public String getCodigoMarca() {
		return codigoMarca;
	}

	/**
	 * @struts.validator type = "required"
	 * @param codigoMarca the codigoMarca to set
	 */
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}

	/**
	 * @return the codigoCanal
	 */
	public String getCodigoCanal() {
		return codigoCanal;
	}

	/**
	 * @struts.validator type = "required"
	 * @param codigoCanal the codigoCanal to set
	 */
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}

	public String getAnioInicial() {
		return anioInicial;
	}
	/**
	 * @struts.validator type = "required"
	 * @param anioInicial the anioInicial to set
	 */	
	public void setAnioInicial(String anioInicial) {
		this.anioInicial = anioInicial;
	}

	public String getCodigoTramo() {
		return codigoTramo;
	}
	/**
	 * @struts.validator type = "required"
	 * @param codigoTramo the codigoTramo to set
	 */
	public void setCodigoTramo(String codigoTramo) {
		this.codigoTramo = codigoTramo;
	}

	public String getTipoComisionista() {
		return tipoComisionista;
	}

	/**
	 * @struts.validator type = "required"
	 * @param tipoComisionista the tipoComisionista to set
	 */	
	public void setTipoComisionista(String tipoComisionista) {
		this.tipoComisionista = tipoComisionista;
	}

	public String getDescripcionMarca() {
		return descripcionMarca;
	}

	public void setDescripcionMarca(String descripcionMarca) {
		this.descripcionMarca = descripcionMarca;
	}

	public String getDescripcionCanal() {
		return descripcionCanal;
	}

	public void setDescripcionCanal(String descripcionCanal) {
		this.descripcionCanal = descripcionCanal;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	
}
