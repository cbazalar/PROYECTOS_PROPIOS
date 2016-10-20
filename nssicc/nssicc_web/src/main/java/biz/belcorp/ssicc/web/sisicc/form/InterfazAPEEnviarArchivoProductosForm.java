package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazAPEEnviarArchivoProductosForm.java.html"> <i>View
 * Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:nlopez@belcorp.biz">Nicol�s L�pez</a>
 * 
 * @struts.form name = "interfazAPEEnviarArchivoProductosForm" extends =
 *              "baseInterfazForm"
 */

public class InterfazAPEEnviarArchivoProductosForm extends BaseInterfazForm implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String codigoCD;
	
	private String codigoMarca;
	
	private String codigoCanal;
	
	private String codigoPeriodo;
	
	private String oidLineaArmado;
	
    /**
     * @return oidLineaArmado
     */
    public String getOidLineaArmado() {
		return oidLineaArmado;
	}

	/**
	 * @param oidLineaArmado
	 */
	public void setOidLineaArmado(String oidLineaArmado) {
		this.oidLineaArmado = oidLineaArmado;
	}

	/**
     * Default constructor
     */
    public InterfazAPEEnviarArchivoProductosForm() {
        super();
        this.codigoMarca = Constants.CODIGO_MARCA_DEFAULT;
        this.codigoCanal = Constants.CODIGO_CANAL_DEFAULT;
    }
	
	/**
	 * @return codigoMarca
	 */
	public String getCodigoMarca() {
		return codigoMarca;
	}

    /**
     * @struts.validator type = "required"
     */
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}

	/**
	 * @return codigoCanal
	 */
	public String getCodigoCanal() {
		return codigoCanal;
	}

    /**
     * @struts.validator type = "required"
     */
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}

	/**
	 * @return codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

    /**
     * @struts.validator type = "required"
     * @struts.validator type="mask"
     * @struts.validator-var name="mask" value="${campaign}"
     */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return codigoCD
	 */
	public String getCodigoCD() {
		return codigoCD;
	}

    /**
     * @struts.validator type = "required"
     */
	public void setCodigoCD(String codigoCD) {
		this.codigoCD = codigoCD;
	}
	
}
