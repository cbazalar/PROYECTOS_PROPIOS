/*
 * Created on 11-jul-2006
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
 * <a href="interfazSATRecepcionarEstimadoProductos.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:mmacias@co.belcorp.biz">Carolina Macias</a>
 * 
 * @struts.form name = "interfazSATRecepcionarEstimadoProductosForm" extends = "BaseInterfazPaqueteForm"
 */
public class InterfazSATRecepcionarEstimadoProductosForm extends BaseInterfazForm 
	implements Serializable {
    
	private String fechaGenerar;
	private String codigoMarca;
	private String codigoCanal;
	private String codigoPeriodo;
	private String linea;
	private String conM;
	private String codigoCentro;
	private String codigoLinea;
	private String mensaje;
	private String cruceCampanya;
	
	public String getCodigoLinea() {
		return codigoLinea;
	}

	public void setCodigoLinea(String codigoLinea) {
		this.codigoLinea = codigoLinea;
	}

	public String getCodigoCentro() {
		return codigoCentro;
	}

	public void setCodigoCentro(String codigoCentro) {
		this.codigoCentro = codigoCentro;
	}

	public String getLinea() {
		return linea;
	}

	public void setLinea(String linea) {
		this.linea = linea;
	}

	public String getCodigoCanal() {
		return codigoCanal;
	}

	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}

	public String getCodigoMarca() {
		return codigoMarca;
	}

	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}

	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return Returns the fechaGenerar.
	 */
	public String getFechaGenerar() {
		return fechaGenerar;
	}

	/**
	 * @param fechaGenerar The fechaGenerar to set.
     * @struts.validator type="required"
     * @struts.validator type="date"
     * @struts.validator-var name="datePatternStrict"
     *                       value="${defaultDatePattern}"
	 */
	public void setFechaGenerar(String fechaGenerar) {
		this.fechaGenerar = fechaGenerar;
	}



	public String getCruceCampanya() {
		return cruceCampanya;
	}

	public void setCruceCampanya(String cruceCampanya) {
		this.cruceCampanya = cruceCampanya;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		this.fechaGenerar = sdf.format(new Date(System.currentTimeMillis()));
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getConM() {
		return conM;
	}

	public void setConM(String sinM) {
		this.conM = sinM;
	}
}
