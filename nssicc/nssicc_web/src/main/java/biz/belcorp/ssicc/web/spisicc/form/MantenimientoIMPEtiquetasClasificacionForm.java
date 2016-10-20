package biz.belcorp.ssicc.web.spisicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

/**
 * The Class MantenimientoIMPEtiquetasClasificacionForm.
 *
 * @autor: Danny Santa Cruz Rojas
 * @version: 1.0
 */
public class MantenimientoIMPEtiquetasClasificacionForm extends BaseEditForm  implements Serializable{

	private static final long serialVersionUID = 1L;
	private String codigoPais;
	private String oid;
	private String oidTipoClasificacion;
	private String oidClasificacion;
	private String oidEtiqueta;
	
	/* (non-Javadoc)
	 * @see org.apache.struts.validator.ValidatorForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	public void reset() {
		this.oid = null;
		this.oidTipoClasificacion= null;
		this.oidClasificacion= null;
		this.oidEtiqueta = null;
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
	 * @return the oidTipoClasificacion
	 */
	public String getOidTipoClasificacion() {
		return oidTipoClasificacion;
	}

	/**
	 * @param oidTipoClasificacion the oidTipoClasificacion to set
	 * 
	 */
	public void setOidTipoClasificacion(String oidTipoClasificacion) {
		this.oidTipoClasificacion = oidTipoClasificacion;
	}
	
	/**
	 * @return the oidClasificacion
	 */
	public String getOidClasificacion() {
		return oidClasificacion;
	}

	/**
	 * @param oidClasificacion the oidClasificacion to set
	 * 
	 */
	public void setOidClasificacion(String oidClasificacion) {
		this.oidClasificacion = oidClasificacion;
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
