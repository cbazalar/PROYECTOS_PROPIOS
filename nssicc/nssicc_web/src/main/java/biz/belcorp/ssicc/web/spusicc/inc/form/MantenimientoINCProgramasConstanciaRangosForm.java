package biz.belcorp.ssicc.web.spusicc.inc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoINCProgramasConstanciaRangosForm extends BaseEditForm implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String codigoProgramaConstancia;
	private String rangoFinal;
	private String puntosAbono;
	
	//agregue
	private String factorMultiplicador;

	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais the codigoPais to set
	 * 
	 * @struts.validator type="required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the codigoProgramaConstancia
	 */
	public String getCodigoProgramaConstancia() {
		return codigoProgramaConstancia;
	}

	/**
	 * @param codigoProgramaConstancia the codigoProgramaConstancia to set
	 * 
	 * @struts.validator type="required"
	 */
	public void setCodigoProgramaConstancia(String codigoProgramaConstancia) {
		this.codigoProgramaConstancia = codigoProgramaConstancia;
	}

	/**
	 * @return the rangoFinal
	 */
	public String getRangoFinal() {
		return rangoFinal;
	}

	/**
	 * @param rangoFinal the rangoFinal to set
	 * 
	 */
	public void setRangoFinal(String rangoFinal) {
		this.rangoFinal = rangoFinal;
	}

	/**
	 * @return the puntosAbono
	 */
	public String getPuntosAbono() {
		return puntosAbono;
	}

	/**
	 * @param puntosAbono the puntosAbono to set
	 * 
	 */
	public void setPuntosAbono(String puntosAbono) {
		this.puntosAbono = puntosAbono;
	}

	public String getFactorMultiplicador() {
		return factorMultiplicador;
	}

	public void setFactorMultiplicador(String factorMultiplicador) {
		this.factorMultiplicador = factorMultiplicador;
	}	
}