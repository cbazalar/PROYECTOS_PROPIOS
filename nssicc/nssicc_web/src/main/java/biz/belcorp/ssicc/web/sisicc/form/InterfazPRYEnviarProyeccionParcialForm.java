package biz.belcorp.ssicc.web.sisicc.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * The Class InterfazPRYEnviarProyeccionParcialForm.
 *
 */
public class InterfazPRYEnviarProyeccionParcialForm extends BaseInterfazForm {

	private static final long serialVersionUID = 1L;
	private String codigoMarca;
	private String codigoCanal;
	private String codigoPeriodo;
	private String fechaFacturacion;


	
	public String getCodigoCanal() {
		return codigoCanal;
	}

	/**
	 * @param codigoCanal 
	 */
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}

	public String getCodigoMarca() {
		return codigoMarca;
	}

	/**
	 * @param codigoMarca 
	 */
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}

	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	public String getFechaFacturacion() {
		return fechaFacturacion;
	}

	/**
	 * @param fechaFacturacion 
	 */
	public void setFechaFacturacion(String fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}
}