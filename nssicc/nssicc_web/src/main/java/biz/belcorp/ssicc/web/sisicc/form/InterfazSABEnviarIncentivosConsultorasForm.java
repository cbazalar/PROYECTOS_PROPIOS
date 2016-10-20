/*
 * Created on 30-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="InterfazSABEnviarIncentivoConsultorasForm.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 * @struts.form
 *   name = "interfazSABEnviarIncentivosConsultorasForm"
 * 	 extends = "baseInterfazForm"	
 */

public class InterfazSABEnviarIncentivosConsultorasForm extends BaseInterfazForm implements Serializable{
	
	private static final long serialVersionUID = 4718685997997077080L;
	
	private String codigoMarca;
	private String codigoCanal;
	private String codigoAcceso="";
	private String codigoPeriodo;
	private String fechaFacturacion;
    private String codigoSociedad;
    

	public String getCodigoAcceso() {
		return codigoAcceso;
	}
	
	public void setCodigoAcceso(String codigoAcceso) {
		this.codigoAcceso = codigoAcceso;
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
	
	public String getFechaFacturacion() {
		return fechaFacturacion;
	}
	
	public void setFechaFacturacion(String fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}    
    
	public String getcodigoSociedad() {
        return codigoSociedad;
    }
    public void setcodigoSociedad(String codigoSociedad) {
        this.codigoSociedad = codigoSociedad;
    }
   
	
}
