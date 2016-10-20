package biz.belcorp.ssicc.web.spusicc.inc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoINCGarantiasPremiosForm extends BaseSearchForm implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String codigoEntidad;
	private String codigoInterfaz;
	private String codigoSAP;
	private String numDias;
	private String indicadorRegistro;
		
	/**
	 * @return the numDias
	 */
	public String getNumDias() {
		return numDias;
	}
	/**
	 * @param numDias the numDias to set
	 */
	public void setNumDias(String numDias) {
		this.numDias = numDias;
	}
	/**
	 * @return the indicadorRegistro
	 */
	public String getIndicadorRegistro() {
		return indicadorRegistro;
	}
	/**
	 * @param indicadorRegistro the indicadorRegistro to set
	 */
	public void setIndicadorRegistro(String indicadorRegistro) {
		this.indicadorRegistro = indicadorRegistro;
	}
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
	 * @return the codigoEntidad
	 */
	public String getCodigoEntidad() {
		return codigoEntidad;
	}
	/**
	 * @param codigoEntidad the codigoEntidad to set
	 */
	public void setCodigoEntidad(String codigoEntidad) {
		this.codigoEntidad = codigoEntidad;
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
	 * @return the codigoSAP
	 */
	public String getCodigoSAP() {
		return codigoSAP;
	}
	/**
	 * @param codigoSAP the codigoSAP to set
	 */
	public void setCodigoSAP(String codigoSAP) {
		this.codigoSAP = codigoSAP;
	}
}