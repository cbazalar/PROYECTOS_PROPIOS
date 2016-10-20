package biz.belcorp.ssicc.web.edu.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

public class ProcesoEDUCierreFacturacionDiarioForm extends BaseProcesoForm implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private String codigoPais;
	private String codigoEmpresa;
	private String codigoPeriodo;
	private String fechaFacturacion;
	private Date fechaFacturacionDate;
	private String indicadorEnvioCronograma;
	
	
	/*
	 *  (non-Javadoc)
	 * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	/*public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.codigoPais="";
		this.codigoEmpresa="";
		this.codigoPeriodo = null;
		this.fechaFacturacion = null;
	}*/

	/**
	 * @return Returns the codigoEmpresa.
	 */
	public String getCodigoEmpresa() {
		return codigoEmpresa;
	}

	/**
	 * @param codigoEmpresa The codigoEmpresa to set.
	 * @struts.validator type = "required" 
	 */
	public void setCodigoEmpresa(String codigoEmpresa) {
		this.codigoEmpresa = codigoEmpresa;
	}

	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais The codigoPais to set.
	 * @struts.validator type = "required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
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
	 * @return Returns the indicadorEnvioCronograma.
	 */
	public String getIndicadorEnvioCronograma() {
		return indicadorEnvioCronograma;
	}

	/**
	 * @param indicadorEnvioCronograma The indicadorEnvioCronograma to set.
	 */
	public void setIndicadorEnvioCronograma(String indicadorEnvioCronograma) {
		this.indicadorEnvioCronograma = indicadorEnvioCronograma;
	}

	public Date getFechaFacturacionDate() {
		return fechaFacturacionDate;
	}

	public void setFechaFacturacionDate(Date fechaFacturacionDate) {
		this.fechaFacturacionDate = fechaFacturacionDate;
	}
}
