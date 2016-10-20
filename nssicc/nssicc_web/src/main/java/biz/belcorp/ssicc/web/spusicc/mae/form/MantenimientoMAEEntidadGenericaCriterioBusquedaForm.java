package biz.belcorp.ssicc.web.spusicc.mae.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoMAEEntidadGenericaCriterioBusquedaForm extends BaseEditForm implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String oidCriterioBus;
	private String atributo1;
	private String atributo2;
	private String estado;
	private String codigoPais;
	private String nombreEntidad;
	/**
	 * @return the oidCriterioBus
	 */
	public String getOidCriterioBus() {
		return oidCriterioBus;
	}
	/**
	 * @param oidCriterioBus the oidCriterioBus to set
	 */
	public void setOidCriterioBus(String oidCriterioBus) {
		this.oidCriterioBus = oidCriterioBus;
	}
	/**
	 * @return the atributo1
	 */
	public String getAtributo1() {
		return atributo1;
	}
	/**
	 * @param atributo1 the atributo1 to set
	 * 
	 * @struts.validator type="required"
	 */
	public void setAtributo1(String atributo1) {
		this.atributo1 = atributo1;
	}
	/**
	 * @return the atributo2
	 */
	public String getAtributo2() {
		return atributo2;
	}
	/**
	 * @param atributo2 the atributo2 to set
	 * 
	 * @struts.validator type="required"
	 */
	public void setAtributo2(String atributo2) {
		this.atributo2 = atributo2;
	}
	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}
	/**
	 * @param estado the estado to set
	 * 
	 * @struts.validator type="required"
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais the codigoPais to set
	 * @struts.validator type="required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	/**
	 * @return the nombreEntidad
	 */
	public String getNombreEntidad() {
		return nombreEntidad;
	}
	/**
	 * @param nombreEntidad the nombreEntidad to set
	 */
	public void setNombreEntidad(String nombreEntidad) {
		this.nombreEntidad = nombreEntidad;
	}
}