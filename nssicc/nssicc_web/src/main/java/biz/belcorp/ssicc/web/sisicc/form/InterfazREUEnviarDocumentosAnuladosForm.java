/*
 * Created on 26/09/2006 05:49:36 PM
 * biz.belcorp.ssicc.sisicc.web.form.InterfazREUEnviarDocumentosAnuladosForm
 */
package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazREUEnviarDocumentosAnuladosForm.java.html"> <i>View Source
 * </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ram�rez </a>
 * 
 * @struts.form name = "interfazREUEnviarDocumentosAnuladosForm" extends =
 *              "baseInterfazForm"
 */
public class InterfazREUEnviarDocumentosAnuladosForm extends BaseInterfazForm
        implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String codigoMarca;

    private String codigoCanal;

    private String codigoAcceso = "";

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

    /**
     * @param codigoAcceso
     *            The codigoAcceso to set.
     * 
     */
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
     * @param codigocanal
     *            The codigocanal to set.
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
}
