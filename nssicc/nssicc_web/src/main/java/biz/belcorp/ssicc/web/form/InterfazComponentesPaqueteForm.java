/**
 * 
 */
package biz.belcorp.ssicc.web.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

/**
 * @author Sigcomt
 *
 */
public class InterfazComponentesPaqueteForm extends BaseEditForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4958920041214401288L;
	
	private String codigoPais;
	private String codigoSistema;
	private String codigoInterfaz;
	private String descripcion;
	private String interfacesNoAsignadas;
	private String ordenEjecucion;
	private String ordenMultihilo;
	private String nivelMultihilo;
	private String indicadorControl;
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
	 * @return the codigoSistema
	 */
	public String getCodigoSistema() {
		return codigoSistema;
	}
	/**
	 * @param codigoSistema the codigoSistema to set
	 */
	public void setCodigoSistema(String codigoSistema) {
		this.codigoSistema = codigoSistema;
	}
	/**
	 * @return the codigoInterfaz
	 */
	public String getCodigoInterfaz() {
		return codigoInterfaz;
	}
	/**
	 * @param codigoInterfaz the codigoInterfaz to set
	 */
	public void setCodigoInterfaz(String codigoInterfaz) {
		this.codigoInterfaz = codigoInterfaz;
	}
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
	 * @return the interfacesNoAsignadas
	 */
	public String getInterfacesNoAsignadas() {
		return interfacesNoAsignadas;
	}
	/**
	 * @param interfacesNoAsignadas the interfacesNoAsignadas to set
	 */
	public void setInterfacesNoAsignadas(String interfacesNoAsignadas) {
		this.interfacesNoAsignadas = interfacesNoAsignadas;
	}
	/**
	 * @return the ordenEjecucion
	 */
	public String getOrdenEjecucion() {
		return ordenEjecucion;
	}
	/**
	 * @param ordenEjecucion the ordenEjecucion to set
	 */
	public void setOrdenEjecucion(String ordenEjecucion) {
		this.ordenEjecucion = ordenEjecucion;
	}
	/**
	 * @return the ordenMultihilo
	 */
	public String getOrdenMultihilo() {
		return ordenMultihilo;
	}
	/**
	 * @param ordenMultihilo the ordenMultihilo to set
	 */
	public void setOrdenMultihilo(String ordenMultihilo) {
		this.ordenMultihilo = ordenMultihilo;
	}
	/**
	 * @return the nivelMultihilo
	 */
	public String getNivelMultihilo() {
		return nivelMultihilo;
	}
	/**
	 * @param nivelMultihilo the nivelMultihilo to set
	 */
	public void setNivelMultihilo(String nivelMultihilo) {
		this.nivelMultihilo = nivelMultihilo;
	}
	/**
	 * @return the indicadorControl
	 */
	public String getIndicadorControl() {
		return indicadorControl;
	}
	/**
	 * @param indicadorControl the indicadorControl to set
	 */
	public void setIndicadorControl(String indicadorControl) {
		this.indicadorControl = indicadorControl;
	}

}
