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
 * <a href="InterfazSATEnviarVolumenesValorizacionPorSeccionDiaForm.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:mmacias@co.belcorp.biz">Facturacion Macias</a>
 *
 * @struts.form
 *   name = "interfazSATEnviarVolumenesValorizacionPorSeccionDiaForm"
 * 	 extends = "baseInterfazForm"	
 */

public class InterfazSATEnviarVolumenesValorizacionPorSeccionDiaForm extends BaseInterfazForm implements Serializable {
	private String fechaFacturacion;
	private String codigoLote;
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

	/**
	 * @return the numeroLoteFact
	 */
	public String getCodigoLote() {
		return this.codigoLote;
	}

	/**
	 * @param numeroLoteFact the numeroLoteFact to set
	 */
	public void setCodigoLote(String numeroLoteFact) {
		this.codigoLote = numeroLoteFact;
	}
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		this.fechaFacturacion = sdf.format(new Date(System.currentTimeMillis()));
	}
}
