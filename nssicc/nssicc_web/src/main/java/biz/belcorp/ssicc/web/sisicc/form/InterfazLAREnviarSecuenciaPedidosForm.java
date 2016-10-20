/*
 * Created on 25/01/2007 11:20:31 AM
 * biz.belcorp.ssicc.sisicc.web.form.InterfazLAREnviarSecuenciaPedidosForm
 */
package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazLAREnviarSecuenciaPedidosForm.java.html"> <i>View Source
 * </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ram�rez </a>
 * 
 * @struts.form name = "interfazLAREnviarSecuenciaPedidosForm" extends =
 *              "baseInterfazForm"
 */
public class InterfazLAREnviarSecuenciaPedidosForm extends BaseInterfazForm
        implements Serializable {

    private String codigoMarca;

    private String codigoCanal;

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
     * Default constructor
     */
    public InterfazLAREnviarSecuenciaPedidosForm() {
        super();
        this.codigoMarca = Constants.CODIGO_MARCA_DEFAULT;
        this.codigoCanal = Constants.CODIGO_CANAL_DEFAULT;
        fechaFacturacion = DateFormatUtils.format(new Date(), Constants.DEFAULT_DATE_FORMAT);
        fechaFacturacionDate = new Date();
    }

    public String getCodigoCanal() {
        return codigoCanal;
    }

    /**
     * @struts.validator type = "required"
     */
    public void setCodigoCanal(String codigoCanal) {
        this.codigoCanal = codigoCanal;
    }

    public String getCodigoMarca() {
        return codigoMarca;
    }

    /**
     * @struts.validator type = "required"
     */
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

}
