package biz.belcorp.ssicc.web.spusicc.pedidos.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoSTOSecuenciaValidacionForm  extends BaseEditForm{
	
	private static final long serialVersionUID = 8070352066958183028L;
	
	private String codigoValidacion;
	private String numeroSecuencia;



	/**
	 * @return the codigoValidacion
	 */
	public String getCodigoValidacion() {
		return codigoValidacion;
	}




	/**
	 * @param codigoValidacion the codigoValidacion to set
	 * @struts.validator type = "required"
	 */
	public void setCodigoValidacion(String codigoValidacion) {
		this.codigoValidacion = codigoValidacion;
	}




	/**
	 * @return the numeroSecuencia
	 */
	public String getNumeroSecuencia() {
		return numeroSecuencia;
	}




	/**
	 * @param numeroSecuencia the numeroSecuencia to set
	 * @struts.validator type = "required" 
	 */
	public void setNumeroSecuencia(String numeroSecuencia) {
		this.numeroSecuencia = numeroSecuencia;
	}
}
