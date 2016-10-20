/*
 * Created on 26/09/2006 11:32:52 AM
 * biz.belcorp.ssicc.sisicc.web.form.InterfazMYEEnviarMovimientosCuentaCorrienteForm
 */
package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazSGREnviarInformacionPolizaForm.java.html"> <i>View
 * Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli </a>
 * 
 * @struts.form name = "interfazSGREnviarInformacionPolizaForm" extends =
 *              "baseInterfazForm"
 */
public class InterfazSGREnviarInformacionPolizaForm extends
        BaseInterfazForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String codigoMarca; 
	
    private String codigoCanal;

    private String codigoAcceso;

	private String codigoPeriodo;
	
	private String fechaFacturacion;
	
	private Date fechaFacturacionD;
	
	private String habilitadorHidden;
	
   	/**
   	 * @return
   	 */
   	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo
	 * @struts.validator type = "required"
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return
	 * 
	 */
	public String getFechaFacturacion() {
		return fechaFacturacion;
	}

	/**
	 * @param fechaFacturacion
	 * @struts.validator type = "required"
	 */
	public void setFechaFacturacion(String fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}

	/**
	 * @return
	 */
	public String getCodigoAcceso() {
        return codigoAcceso;
    }

    /**
     * @param codigoAcceso
     */
    public void setCodigoAcceso(String codigoAcceso) {
        this.codigoAcceso = codigoAcceso;
    }

    /**
     * @return
     */
    public String getCodigoCanal() {
        return codigoCanal;
    }

    /**
     * @param codigoCanal
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
	 */
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.sisicc.web.framework.form.BaseInterfazForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
     */
    public void reset(ActionMapping arg0, HttpServletRequest arg1) {
        this.codigoMarca = Constants.CODIGO_MARCA_DEFAULT;
        this.codigoCanal = Constants.CODIGO_CANAL_DEFAULT;
        this.codigoAcceso = Constants.CODIGO_ACCESO_DEFAULT;	
        setNumeroLote("");
        setHabilitadorHidden("");
	}

	/**
	 * @return the habilitadorHidden
	 */
	public String getHabilitadorHidden() {
		return habilitadorHidden;
	}

	/**
	 * @param habilitadorHidden the habilitadorHidden to set
	 */
	public void setHabilitadorHidden(String habilitadorHidden) {
		this.habilitadorHidden = habilitadorHidden;
	}

	/**
	 * @return the fechaFacturacionD
	 */
	public Date getFechaFacturacionD() {
		return fechaFacturacionD;
	}

	/**
	 * @param fechaFacturacionD the fechaFacturacionD to set
	 */
	public void setFechaFacturacionD(Date fechaFacturacionD) {
		this.fechaFacturacionD = fechaFacturacionD;
	}
	
	
}
