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
 * <a href="InterfazDATEnviarDatamartSacForm.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jjrios@csigcomt.com">Jesse James Rios Franco</a>
 * 
 * @struts.form name = "interfazDATEnviarDatamartSacForm" extends = "baseInterfazForm"
 */

public class InterfazDATEnviarDatamartSacForm extends BaseInterfazForm implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String codigoMarca; 
	
    private String codigoCanal;

    private String codigoAcceso;

	private String codigoPeriodo;
	
	private String fechaFacturacion;
	private Date fechaFacturacionDate;
	
	
	private String fechaInicio;
	private String fechaFin;

	private String valIndiImpu ;
	private String tipoDocumentoDetalle;
	private String tipoDocumentoCabecera;
	private String tipoDocumentoOCCabecera;
	
	
	/**
     * Default Constructor
     */
	public InterfazDATEnviarDatamartSacForm(){
		super();
        this.codigoMarca = Constants.CODIGO_MARCA_DEFAULT;
        this.codigoCanal = Constants.CODIGO_CANAL_DEFAULT;
        this.codigoAcceso = Constants.CODIGO_ACCESO_DEFAULT;
	}
	
   	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	


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
	
	public void reset(ActionMapping arg0, HttpServletRequest arg1) {
	
	}

	/**
	 * @return the fechaInicio
	 */
	public String getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * @param fechaInicio the fechaInicio to set
	 */
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * @return the fechaFin
	 */
	public String getFechaFin() {
		return fechaFin;
	}

	/**
	 * @param fechaFin the fechaFin to set
	 */
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	/**
	 * @return the valIndiImpu
	 */
	public String getValIndiImpu() {
		return valIndiImpu;
	}

	/**
	 * @param valIndiImpu the valIndiImpu to set
	 */
	public void setValIndiImpu(String valIndiImpu) {
		this.valIndiImpu = valIndiImpu;
	}

	/**
	 * @return the tipoDocumentoDetalle
	 */
	public String getTipoDocumentoDetalle() {
		return tipoDocumentoDetalle;
	}

	/**
	 * @param tipoDocumentoDetalle the tipoDocumentoDetalle to set
	 */
	public void setTipoDocumentoDetalle(String tipoDocumentoDetalle) {
		this.tipoDocumentoDetalle = tipoDocumentoDetalle;
	}

	/**
	 * @return the tipoDocumentoCabecera
	 */
	public String getTipoDocumentoCabecera() {
		return tipoDocumentoCabecera;
	}

	/**
	 * @param tipoDocumentoCabecera the tipoDocumentoCabecera to set
	 */
	public void setTipoDocumentoCabecera(String tipoDocumentoCabecera) {
		this.tipoDocumentoCabecera = tipoDocumentoCabecera;
	}

	/**
	 * @return the tipoDocumentoOCCabecera
	 */
	public String getTipoDocumentoOCCabecera() {
		return tipoDocumentoOCCabecera;
	}

	/**
	 * @param tipoDocumentoOCCabecera the tipoDocumentoOCCabecera to set
	 */
	public void setTipoDocumentoOCCabecera(String tipoDocumentoOCCabecera) {
		this.tipoDocumentoOCCabecera = tipoDocumentoOCCabecera;
	}

	/**
	 * @return the fechaFacturacion
	 */
	public String getFechaFacturacion() {
		return fechaFacturacion;
	}

	/**
	 * @param fechaFacturacion the fechaFacturacion to set
	 */
	public void setFechaFacturacion(String fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
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

	
	
	
}