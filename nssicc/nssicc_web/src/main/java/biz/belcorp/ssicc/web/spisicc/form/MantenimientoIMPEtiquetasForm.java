package biz.belcorp.ssicc.web.spisicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

/**
 * The Class MantenimientoIMPParametroProcesoImpresionForm.
 *
 * @autor: Danny Santa Cruz Rojas
 * @version: 1.0
 */
public class MantenimientoIMPEtiquetasForm extends BaseEditForm  implements Serializable{

	private static final long serialVersionUID = 1L;
	private String codigoPais;
	private String oid;
	private String valorEtiqueta;
	private String indicadorEstado;
	
	/* (non-Javadoc)
	 * @see org.apache.struts.validator.ValidatorForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	public void reset() {
		this.oid = null;
		this.valorEtiqueta = null;
		this.indicadorEstado= null;
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
	 * @return the valorEtiqueta
	 */
	public String getValorEtiqueta() {
		return valorEtiqueta;
	}

	/**
	 * @param valorEtiqueta the valorEtiqueta to set
	 * 
	 */
	public void setValorEtiqueta(String valorEtiqueta) {
		this.valorEtiqueta = valorEtiqueta;
	}

	/**
	 * @return the indicadorEstado
	 */
	public String getIndicadorEstado() {
		return indicadorEstado;
	}

	/**
	 * @param indicadorEstado the indicadorEstado to set
	 * 
	 */
	public void setIndicadorEstado(String indicadorEstado) {
		this.indicadorEstado = indicadorEstado;
	}
	
}
