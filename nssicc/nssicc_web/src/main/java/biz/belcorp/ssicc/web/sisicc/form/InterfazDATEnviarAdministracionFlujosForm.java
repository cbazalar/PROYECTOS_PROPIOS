/*
 * Created on 26/09/2006 11:32:52 AM
 * biz.belcorp.ssicc.sisicc.web.form.InterfazMYEEnviarMovimientosCuentaCorrienteForm
 */
package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazDATEnviarAdministracionFlujosForm.java.html"> <i>View
 * Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jcairampoma@belcorp.biz">Jose Cairampoma </a>
 */
public class InterfazDATEnviarAdministracionFlujosForm extends
        BaseInterfazForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7990664002399349745L;

	private String codigoMarca; 
	
    private String codigoCanal;

    private String codigoAcceso;

	private String codigoPeriodo;
	
	private String fechaFacturacion;
	
	private Date fechaFacturacionDate;
	
	private String codigoConexionExterna;
	
	private String validarFechaFacturacion;

   	/**
	 * @return the codigoCanal
	 */
	public String getCodigoCanal() {
		return codigoCanal;
	}

	/**
	 * @param codigoCanal the codigoCanal to set
	 * @struts.validator type = "required"
	 */
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}

	/**
	 * @return the codigoAcceso
	 */
	public String getCodigoAcceso() {
		return codigoAcceso;
	}

	/**
	 * @param codigoAcceso the codigoAcceso to set
	 * @struts.validator type = "required"
	 */
	public void setCodigoAcceso(String codigoAcceso) {
		this.codigoAcceso = codigoAcceso;
	}

	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo the codigoPeriodo to set
	 * @struts.validator type = "required"
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return the fechaFacturacion
	 */
	public String getFechaFacturacion() {
		return fechaFacturacion;
	}

	/**
	 * @param fechaFacturacion the fechaFacturacion to set
	 * @struts.validator type = "required"
	 */
	public void setFechaFacturacion(String fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}

	/**
	 * @return Returns the codigoMarca.
	 */
	public String getCodigoMarca() {
		return codigoMarca;
	}

	/**
	 * @param codigoMarca The codigoMarca to set.
	 * @struts.validator type = "required"
	 */
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}
    
    /**
     * Default Constructor
     */
    public InterfazDATEnviarAdministracionFlujosForm() {
        super();
        this.codigoMarca = Constants.CODIGO_MARCA_DEFAULT;
        this.codigoCanal = Constants.CODIGO_CANAL_DEFAULT;
        this.codigoAcceso = Constants.CODIGO_ACCESO_DEFAULT;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		this.fechaFacturacion = sdf.format(new Date(System.currentTimeMillis()));
		this.fechaFacturacionDate = new Date(System.currentTimeMillis());
		this.validarFechaFacturacion = Constants.SI;
    }

	/**
	 * @return the fechaFacturacionDate
	 */
	public Date getFechaFacturacionDate() {
		return fechaFacturacionDate;
	}

	/**
	 * @param fechaFacturacionDate the fechaFacturacionDate to set
	 */
	public void setFechaFacturacionDate(Date fechaFacturacionDate) {
		this.fechaFacturacionDate = fechaFacturacionDate;
	}

	/**
	 * @return the codigoConexionExterna
	 */
	public String getCodigoConexionExterna() {
		return codigoConexionExterna;
	}

	/**
	 * @param codigoConexionExterna the codigoConexionExterna to set
	 */
	public void setCodigoConexionExterna(String codigoConexionExterna) {
		this.codigoConexionExterna = codigoConexionExterna;
	}

	/**
	 * @return the validarFechaFacturacion
	 */
	public String getValidarFechaFacturacion() {
		return validarFechaFacturacion;
	}

	/**
	 * @param validarFechaFacturacion the validarFechaFacturacion to set
	 */
	public void setValidarFechaFacturacion(String validarFechaFacturacion) {
		this.validarFechaFacturacion = validarFechaFacturacion;
	}
    
    
    
    
}
