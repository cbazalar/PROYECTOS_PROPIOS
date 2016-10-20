package biz.belcorp.ssicc.web.spisicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

/**
 * The Class MantenimientoIMPParametroProcesoImpresionForm.
 *
 * @autor: Danny Santa Cruz Rojas
 * @version: 1.0
 */
public class MantenimientoIMPEtiquetasEstatusForm extends BaseEditForm  implements Serializable{

	private static final long serialVersionUID = 1L;
	private String codigoPais;
	private String oid;
	private String oidEstatus;
	private String oidEtiqueta;
	
	/* (non-Javadoc)
	 * @see org.apache.struts.validator.ValidatorForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	public void reset() {
		this.oid = null;
		this.oidEtiqueta = null;
		this.oidEstatus= null;
	}
	
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais the codigoPais to set
	 * 
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the oid
	 */
	public String getOid() {
		return oid;
	}

	/**
	 * @param oid the oid to set
	 * 
	 * 
	 */
	public void setOid(String oid) {
		this.oid = oid;
	}
	
	/**
	 * @return the oidEstatus
	 */
	public String getOidEstatus() {
		return oidEstatus;
	}

	/**
	 * @param oidEstatus the oidEstatus to set
	 * 
	 */
	public void setOidEstatus(String oidEstatus) {
		this.oidEstatus = oidEstatus;
	}
	
	/**
	 * @return the oidEtiqueta
	 */
	public String getOidEtiqueta() {
		return oidEtiqueta;
	}

	/**
	 * @param oidEtiqueta the oidEtiqueta to set
	 * 
	 */
	public void setOidEtiqueta(String oidEtiqueta) {
		this.oidEtiqueta = oidEtiqueta;
	}
	
}
