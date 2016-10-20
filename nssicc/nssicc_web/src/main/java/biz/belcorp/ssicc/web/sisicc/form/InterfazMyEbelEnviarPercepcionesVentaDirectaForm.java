/*
 * Created on 02-nov-2005
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
 * <a href="InterfazMyEbelEnviarPercepcionesVentaDirectaForm.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:dtoledo@belcorp.biz">David Toledo</a>
 *  
 * @struts.form
 *   name = "interfazMyEbelEnviarPercepcionesVentaDirectaForm"
 * 	 extends = "baseInterfazForm"	
 */
public class InterfazMyEbelEnviarPercepcionesVentaDirectaForm extends BaseInterfazForm implements Serializable{
	
    private String[] codigoInterfacesEmpaquetadas;
    
    private String codigoIdioma;
    
    private String fechaDesde;
    
    private String fechaHasta;
       		
    
	public String getCodigoIdioma() {
        return codigoIdioma;
    }

    public void setCodigoIdioma(String codigoIdioma) {
        this.codigoIdioma = codigoIdioma;
    }



	/**
	 * @return Returns the codigoInterfacesEmpaquetadas.
	 */
	public String[] getCodigoInterfacesEmpaquetadas() {
		return codigoInterfacesEmpaquetadas;
	}

	/**
	 * @param codigoInterfacesEmpaquetadas The codigoInterfacesEmpaquetadas to set.
	 */
	public void setCodigoInterfacesEmpaquetadas(String[] codigoInterfacesEmpaquetadas) {
		this.codigoInterfacesEmpaquetadas = codigoInterfacesEmpaquetadas;
	}

	public String getFechaDesde() {
		return fechaDesde;
	}

	/**
	 * @param fechaDesde The fechaDesde to set.
	 * @param fechaDesde
	 *            New value of property fechaDesde.
	 * @struts.validator type="required"
	 * @struts.validator type="date"
	 * @struts.validator-var name="datePatternStrict"
	 *                       value="${defaultDatePattern}" 
	 */

	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public String getFechaHasta() {
		return fechaHasta;
	}

	/** 
	 * * @param fechaHasta
	 *            New value of property fechaHasta.
	 * @struts.validator type="required"
	 * @struts.validator type="date"
	 * @struts.validator-var name="datePatternStrict"
	 *                       value="${defaultDatePattern}"
	*/		
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
    /* 
	 * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	public void reset(ActionMapping arg0, HttpServletRequest arg1) {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");		
		this.fechaDesde = sdf.format(new Date(System.currentTimeMillis()));
		this.fechaHasta = sdf.format(new Date(System.currentTimeMillis()));
        
	}
}
