/*
 * Created on 26/09/2006 09:42:55 AM
 * biz.belcorp.ssicc.sisicc.web.form.InterfazREUEnviarMaestrosForm
 */
package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazREUEnviarMaestrosForm.java.html"> <i>View Source </i>
 * </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ram�rez </a>�
 * 
 * @struts.form name = "interfazREUEnviarMaestrosForm" extends =
 *              "baseInterfazForm"
 */

public class InterfazREUEnviarMaestrosForm extends BaseInterfazForm
        implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 918007468059631848L;

	private String codigoMarca;

    private String codigoCanal;

    private String periodoDesde;

    private String periodoHasta;

    private String codigoActividad;

    private String codigoTipoCliente;
    
    private String codigoAcceso;
    
    private String fechaFacturacion;
    
    private Date fechaFacturacionD;
   
    private String codigoPeriodo;
   
    /**
     * @return Returns the codigoActividad.
     */
    public String getCodigoActividad() {
        return codigoActividad;
    }

    /**
     * @param codigoActividad
     *            The codigoActividad to set.
     * @struts.validator type = "required"
     */
    public void setCodigoActividad(String codigoActividad) {
        this.codigoActividad = codigoActividad;
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
     * @return Returns the periodoDesde.
     */
    public String getPeriodoDesde() {
        return periodoDesde;
    }

    /**
     * @param periodoDesde
     *            The periodoDesde to set.
     * 
     * @struts.validator type = "required"
	 * @struts.validator type="mask"
	 * @struts.validator-var name="mask" value="${campaign}"
     */
    public void setPeriodoDesde(String periodoDesde) {
        this.periodoDesde = periodoDesde;
    }

    /**
     * @return Returns the periodoHasta.
     */
    public String getPeriodoHasta() {
        return periodoHasta;
    }

    /**
     * @param periodoHasta
     *            The periodoHasta to set.
     * 
     * @struts.validator type = "required"
	 * @struts.validator type="mask"
	 * @struts.validator-var name="mask" value="${campaign}"
     */
    public void setPeriodoHasta(String periodoHasta) {
        this.periodoHasta = periodoHasta;
    }

	/**
	 * @return Returns the codigoAcceso.
	 */
	public String getCodigoAcceso() {
		return codigoAcceso;
	}

	/**
	 * @param codigoAcceso The codigoAcceso to set.
     * @struts.validator type = "required"
     */
	public void setCodigoAcceso(String codigoAcceso) {
		this.codigoAcceso = codigoAcceso;
	}

	/**
	 * @return Returns the codigoPeriodo.
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo The codigoPeriodo to set.
     * @struts.validator type = "required"
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return Returns the codigoTipoCliente.
	 */
	public String getCodigoTipoCliente() {
		return codigoTipoCliente;
	}

	/**
	 * @param codigoTipoCliente The codigoTipoCliente to set.
     * @struts.validator type = "required"
	 */
	public void setCodigoTipoCliente(String codigoTipoCliente) {
		this.codigoTipoCliente = codigoTipoCliente;
	}

	/**
	 * @return Returns the fechaFacturacion.
	 */
	public String getFechaFacturacion() {
		return fechaFacturacion;
	}

	/**
	 * @param fechaFacturacion The fechaFacturacion to set.
     * @struts.validator type = "required"
	 */
	public void setFechaFacturacion(String fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
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

	/**
     * Default Constructor
     */
    public InterfazREUEnviarMaestrosForm() {
        this.codigoActividad = Constants.CODIGO_ACTIVIDAD_DEFAULT;
        this.codigoMarca = Constants.CODIGO_MARCA_DEFAULT;
        this.codigoCanal = Constants.CODIGO_CANAL_DEFAULT;
        this.codigoAcceso = Constants.CODIGO_ACCESO_DEFAULT;
        this.codigoTipoCliente = Constants.CODIGO_TIPO_CLIENTE_DEFAULT;            
        this.fechaFacturacionD = new Date();
        
    }

    public ActionErrors validate(ActionMapping mapping,
            HttpServletRequest request) {

        ActionErrors errors = new ActionErrors();

        if ((getPeriodoDesde() != null && getPeriodoDesde().length() > 1)
                && (getPeriodoHasta() != null && getPeriodoHasta().length() > 1)) {
            Long desde = new Long(getPeriodoDesde());
            Long hasta = new Long(getPeriodoHasta());

            if (desde.longValue() > hasta.longValue()) {
                errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                        "interfazSiCC.error.periodo.Desde.Mayor"));
            }
        }
        return errors;
    }
	
}
