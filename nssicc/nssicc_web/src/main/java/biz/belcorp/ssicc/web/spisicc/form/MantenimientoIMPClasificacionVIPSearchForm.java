package biz.belcorp.ssicc.web.spisicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

/**
 * The Class MantenimientoIMPEtiquetasClasificacionSearchForm.
 * @autor: Danny Santa Cruz Rojas
 */
public class MantenimientoIMPClasificacionVIPSearchForm extends BaseSearchForm implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String oidTipoClasificacion;
	private String oidClasificacion;
	private String valorLAR;
	private String numeroOCS;
	
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	
	/**
	 * @return the oidTipoClasificacion
	 */
	public String getOidTipoClasificacion() {
		return oidTipoClasificacion;
	}
	/**
	 * @param oidTipoClasificacion the oidTipoClasificacion to set
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
	 */
	public void setOidClasificacion(String oidClasificacion) {
		this.oidClasificacion = oidClasificacion;
	}
	
	/**
	 * @return the valorLAR
	 */
	public String getValorLAR() {
		return valorLAR;
	}

	/**
	 * @param valorLAR the valorLAR to set
	 * 
	 *  
	 */
	public void setValorLAR(String valorLAR) {
		this.valorLAR = valorLAR;
	}
	
	/**
	 * @return the numeroOCS
	 */
	public String getNumeroOCS() {
		return numeroOCS;
	}

	/**
	 * @param numeroOCS the numeroOCS to set
	 * 
	 *  
	 */
	public void setNumeroOCS(String numeroOCS) {
		this.numeroOCS = numeroOCS;
	} 
}
