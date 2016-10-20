package biz.belcorp.ssicc.web.spisicc.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

public class CopyOfProcesoCompaginacionForm extends BaseProcesoForm implements Serializable{

private static final long serialVersionUID = 1L;
	
	
	private String codigoMarca;

	private String codigoCanal;
		
	private String codigoPeriodo;
	
	private String fechaFacturacion;
	
	public void reset() {
		this.codigoMarca = Constants.CODIGO_MARCA_DEFAULT;
		this.codigoCanal = Constants.CODIGO_CANAL_DEFAULT;
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
	 * @return the codigoMarca
	 */
	public String getCodigoMarca() {
		return codigoMarca;
	}


	/**
	 * @param codigoMarca the codigoMarca to set
	 * @struts.validator type = "required"
	 */
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}


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


		


}
