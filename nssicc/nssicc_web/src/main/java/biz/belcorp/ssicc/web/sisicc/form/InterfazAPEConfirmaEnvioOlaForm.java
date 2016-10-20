package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazAPEConfirmaEnvioOlaForm.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:nlopez@csigcomt.com">Nicol�s L�pez</a>
 * 
 * @struts.form name = "interfazAPEConfirmaEnvioOlaForm" extends =
 *              "baseInterfazForm"
 */
public class InterfazAPEConfirmaEnvioOlaForm extends BaseInterfazForm implements
		Serializable {

	private String numeroOla;
	private String codigoCD;
	private String descripcionCentro;

	/**
	 * @return numeroOla
	 */
	public String getNumeroOla() {
		return numeroOla;
	}

	/**
	 * @param numeroOla
	 *            the numeroOla to set
	 * @struts.validator type="required"
	 */
	public void setNumeroOla(String numeroOla) {
		this.numeroOla = numeroOla;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.web.framework.form.BaseInterfazForm#reset(org
	 * .apache.struts.action.ActionMapping,
	 * javax.servlet.http.HttpServletRequest)
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.numeroOla = "";
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

}
