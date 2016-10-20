package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazAPEEnviarArchivoAnaquelesForm.java.html"> <i>View
 * Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:nlopez@csigcomt.com">Nicol�s L�pez</a>
 * 
 * @struts.form name = "interfazAPEEnviarArchivoAnaquelesForm" extends =
 *              "baseInterfazForm"
 */

public class InterfazAPEEnviarArchivoAnaquelesForm extends BaseInterfazForm implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String codigoCD;
	
	private String descripcionCentro;
	
	private String codigoMapaCD;
	
	private String codigoMarca;
	
	private String codigoCanal;
	
	private String codigoPeriodo;
	
	private String codigoVersion;
	
	private String oidLineaArmado;
	
	private String codigoLineaArmado;
	
	private String descripcionLineaArmado;
	
	private String viewEdit;
	
	/**
	 * @return descripcionCentro
	 */
	public String getDescripcionCentro() {
		return descripcionCentro;
	}

	/**
	 * @param descripcionCentro
	 */
	public void setDescripcionCentro(String descripcionCentro) {
		this.descripcionCentro = descripcionCentro;
	}

	/**
	 * @return descripcionLineaArmado
	 */
	public String getDescripcionLineaArmado() {
		return descripcionLineaArmado;
	}

	/**
	 * @param descripcionLineaArmado
	 */
	public void setDescripcionLineaArmado(String descripcionLineaArmado) {
		this.descripcionLineaArmado = descripcionLineaArmado;
	}

	/**
     * Default constructor
     */
    public InterfazAPEEnviarArchivoAnaquelesForm() {
        super();
        this.codigoMarca = Constants.CODIGO_MARCA_DEFAULT;
        this.codigoCanal = Constants.CODIGO_CANAL_DEFAULT;
    }
    
    /**
     * @return viewEdit
     */
    public String getViewEdit() {
		return viewEdit;
	}

	/**
	 * @param viewEdit
	 */
	public void setViewEdit(String viewEdit) {
		this.viewEdit = viewEdit;
	}

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
     * @return codigoVersion
     */
    public String getCodigoVersion() {
		return codigoVersion;
	}

    /**
     * @struts.validator type = "required"
     */
	public void setCodigoVersion(String codigoVersion) {
		this.codigoVersion = codigoVersion;
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
	 * @param codigoCD
	 */
	public void setCodigoCD(String codigoCD) {
		this.codigoCD = codigoCD;
	}
	
	/**
	 * @return codigoMapaCD
	 */
	public String getCodigoMapaCD() {
		return codigoMapaCD;
	}

    /**
     * @struts.validator type = "required"
     */
	public void setCodigoMapaCD(String codigoMapaCD) {
		this.codigoMapaCD = codigoMapaCD;
	}
	
	/**
	 * @return codigoLineaArmado
	 */
	public String getCodigoLineaArmado() {
		return codigoLineaArmado;
	}

	/**
	 * @param codigoLineaArmado
	 */
	public void setCodigoLineaArmado(String codigoLineaArmado) {
		this.codigoLineaArmado = codigoLineaArmado;
	}
	
}
