/*
 * Created on 20-dic-2005
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
 * <a href="InterfazBELEnviarFacturasCabeceraForm.java.html"> <i>View Source
 * </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramirez</a>
 * 
 * @struts.form name = "interfazBELEnviarFacturasCabeceraForm" extends =
 *              "baseInterfazForm"
 */

public class InterfazBELEnviarFacturasCabeceraForm extends BaseInterfazForm
        implements Serializable {

    private String codigoMarca;

    private String codigoCanal;

    private String codigoAcceso;

    private String codigoPeriodo;

    private String fechaFacturacion;
    
    private Date fechaFacturacionD;

    
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
     * @param codigoCanal
     *            The codigoCanal to set.
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
     * @param codigoMarca
     *            The codigoMarca to set.
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
     * @param codigoPeriodo
     *            The codigoPeriodo to set.
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
     * @param fechaFacturacion
     *            The fechaFacturacion to set.
     * 
     * @struts.validator type = "required"
     */
    public void setFechaFacturacion(String fechaFacturacion) {
        this.fechaFacturacion = fechaFacturacion;
    }

	public Date getFechaFacturacionD() {
		return fechaFacturacionD;
	}

	public void setFechaFacturacionD(Date fechaFacturacionD) {
		this.fechaFacturacionD = fechaFacturacionD;
	}

    /*
     * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping,
     *      javax.servlet.http.HttpServletRequest)
     */
    
   
}
