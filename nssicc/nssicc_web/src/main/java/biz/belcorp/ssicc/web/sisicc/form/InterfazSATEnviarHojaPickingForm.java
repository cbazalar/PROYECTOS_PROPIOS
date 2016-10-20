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
 * <a href="InterfazSATEnviarCronogramaZonasForm.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:mmacias@belcorp.biz">Carolina Macias </a>
 *
 * @struts.form
 *   name = "interfazSATEnviarHojaPickingForm"
 * 	 extends = "baseInterfazForm"	
 */

public class InterfazSATEnviarHojaPickingForm extends BaseInterfazForm implements Serializable {
	private String fechaFacturacion;
	private String codigoLote;
	private String codigoCentro;
	private String codigoLinea;
	private String[] codsublinea;
	//private String[] codigoSublinea;
	private String[] codsublineaTotal;

	public String getCodigoCentro() {
		return codigoCentro;
	}

	public String[] getCodsublineaTotal() {
		return codsublineaTotal;
	}

	public void setCodsublineaTotal(String[] codsublineaTotal) {
		this.codsublineaTotal = codsublineaTotal;
	}

	public void setCodigoCentro(String codigoCentro) {
		this.codigoCentro = codigoCentro;
	}

	public String getCodigoLinea() {
		return codigoLinea;
	}

	public void setCodigoLinea(String codigoLinea) {
		this.codigoLinea = codigoLinea;
	}

	public String[] getCodsublinea() {
		return codsublinea;
	}

	public void setCodsublinea(String[] list) {
		this.codsublinea = list;
	}

	/**
	 * @return the codigoLote
	 */
	public String getCodigoLote() {
		return this.codigoLote;
	}

	/**
	 * @param codigoLote the codigoLote to set
	 */
	public void setCodigoLote(String codigoLote) {
		this.codigoLote = codigoLote;
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
