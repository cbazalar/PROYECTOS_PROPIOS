/*
 * Created on 11/04/2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="InterfazSATEnviarAFPConsultoraForm.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:mmacias@co.belcorp.biz">Facturacion Macias</a>
 *
 * @struts.form
 *   name = "interfazSATEnviarAFPConsultoraForm"
 * 	 extends = "baseInterfazForm"	
 */

public class InterfazSATEnviarAFPConsultoraForm extends BaseInterfazForm implements Serializable {
	private String fechaFacturacion;
	private String numeroLoteFacturado;
	

	/**
	 * @return the numeroLoteFacturado
	 */
	public String getNumeroLoteFacturado() {
		return this.numeroLoteFacturado;
	}

	/**
	 * @param numeroLoteFacturado the numeroLoteFacturado to set
	 */
	public void setNumeroLoteFacturado(String numeroLoteFacturado) {
		this.numeroLoteFacturado = numeroLoteFacturado;
	}

	/**
	 * @return the fechaFacturacion
	 */
	public String getFechaFacturacion() {
		return this.fechaFacturacion;
	}

	/**
	 * @param fechaFacturacion the fechaFacturacion to set
	 */
	public void setFechaFacturacion(String fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		this.fechaFacturacion = sdf.format(new Date(System.currentTimeMillis()));
	}
	
}
