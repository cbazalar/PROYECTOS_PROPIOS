/*
 * Created on 24-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="InterfazRETEnviarFacturasCompleVentaDirectaForm.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar</a>
 * 
 * @struts.form
 *   name = "interfazRETEnviarFacturasCompleVentaDirectaForm"
 * 	 extends = "baseInterfazForm"	
 */

public class InterfazRETEnviarFacturasCompleVentaDirectaForm extends BaseInterfazForm implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoMarca;
	private String codigoCanal;
	private String codigoAcceso;
	private String codigoPeriodo;
	private String fechaFacturacion;
	private Date fechaFacturacionDate;
	
	public Date getFechaFacturacionDate() {
		return fechaFacturacionDate;
	}


	public void setFechaFacturacionDate(Date fechaFacturacionDate) {
		this.fechaFacturacionDate = fechaFacturacionDate;
	}


	/**
	 * @return Returns the codigoAcceso.
	 */
	public String getCodigoAcceso() {
		return codigoAcceso;
	}

    
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
	 * @param codigocanal The codigocanal to set.
	 * 
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
	 * 
	 * @struts.validator type = "required"
	 */
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}
	/**
	 * @return Returns the codigoPeriodo.
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}
	/**
	 * @param codigoPeriodo The codigoPeriodo to set.
	 * 
	 * @struts.validator type = "required"
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	/**
	 * @return Returns the fechaFacturacion.
	 */
	public String getFechaFacturacion() {
		return fechaFacturacion;
	}
	/**
	 * @param fechaFacturacion The fechaFacturacion to set.
	 * 
	 * @struts.validator type = "required"
	 */
	public void setFechaFacturacion(String fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}	
	
	/* 
	 * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	public void reset() {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
		
		this.codigoMarca = Constants.CODIGO_MARCA_DEFAULT;
		this.codigoCanal = Constants.CODIGO_CANAL_DEFAULT;
		this.codigoAcceso = Constants.CODIGO_ACCESO_DEFAULT;
		this.codigoPeriodo = sdf.format(new Date(System.currentTimeMillis()));
		Date fechaActual = new Date(System.currentTimeMillis());
        
        
         Calendar fec = new GregorianCalendar();
         fec.add(Calendar.DATE, -0);
         fechaActual = fec.getTime();

        
        this.fechaFacturacion = sdf1.format(fechaActual);
        this.fechaFacturacionDate = fechaActual;
	}
}
