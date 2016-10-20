package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * Form del procesamiento del consolidado de archivos de texto de OCS de la
 * Interfaz OCR.
 * 
 * @author <a href="mailto:ghuertas@sigcomt.com">Gonzalo Huertas</a>
 * 
 * @struts.form name = "interfazMAERecibirInformacionForm" extends =
 *              "basePaqueteForm"
 */
public class InterfazMAERecibirInformacionForm extends
		BaseInterfazForm implements Serializable {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigoPeriodo;	


	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo the codigoPeriodo to set
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

}