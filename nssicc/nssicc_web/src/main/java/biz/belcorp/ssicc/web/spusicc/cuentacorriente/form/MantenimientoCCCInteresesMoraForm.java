package biz.belcorp.ssicc.web.spusicc.cuentacorriente.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoCCCInteresesMoraForm extends BaseSearchForm
		implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	private String codigoPais;
	private int diasCadencia;
	private int diasCorte;
	private double montoMinimo;
	private double tasaImpuesto;
	private double importeFijo;
	private double tasaInteres;
	private double saldoPermitido;
	private int numeroDiasBloqueo;
	
							
	/**
	 * @return the diasCadencia
	 */
	public int getDiasCadencia() {
		return diasCadencia;
	}

	/**
	 * @param diasCadencia the diasCadencia to set
	 */
	public void setDiasCadencia(int diasCadencia) {
		this.diasCadencia = diasCadencia;
	}

	/**
	 * @return the diasCorte
	 */
	public int getDiasCorte() {
		return diasCorte;
	}

	/**
	 * @param diasCorte the diasCorte to set
	 */
	public void setDiasCorte(int diasCorte) {
		this.diasCorte = diasCorte;
	}

	/**
	 * @return the montoMinimo
	 */
	public double getMontoMinimo() {
		return montoMinimo;
	}

	/**
	 * @param montoMinimo the montoMinimo to set
	 */
	public void setMontoMinimo(double montoMinimo) {
		this.montoMinimo = montoMinimo;
	}

	/**
	 * @return the tasaImpuesto
	 */
	public double getTasaImpuesto() {
		return tasaImpuesto;
	}

	/**
	 * @param tasaImpuesto the tasaImpuesto to set
	 */
	public void setTasaImpuesto(double tasaImpuesto) {
		this.tasaImpuesto = tasaImpuesto;
	}

	/**
	 * @return the importeFijo
	 */
	public double getImporteFijo() {
		return importeFijo;
	}

	/**
	 * @param importeFijo the importeFijo to set
	 */
	public void setImporteFijo(double importeFijo) {
		this.importeFijo = importeFijo;
	}

	/**
	 * @return the tasaInteres
	 */
	public double getTasaInteres() {
		return tasaInteres;
	}

	/**
	 * @param tasaInteres the tasaInteres to set
	 */
	public void setTasaInteres(double tasaInteres) {
		this.tasaInteres = tasaInteres;
	}

	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @return the saldoPermitido
	 */
	public double getSaldoPermitido() {
		return saldoPermitido;
	}

	/**
	 * @param saldoPermitido the saldoPermitido to set
	 */
	public void setSaldoPermitido(double saldoPermitido) {
		this.saldoPermitido = saldoPermitido;
	}

	/**
	 * @return the numeroDiasBloqueo
	 */
	public int getNumeroDiasBloqueo() {
		return numeroDiasBloqueo;
	}

	/**
	 * @param numeroDiasBloqueo the numeroDiasBloqueo to set
	 */
	public void setNumeroDiasBloqueo(int numeroDiasBloqueo) {
		this.numeroDiasBloqueo = numeroDiasBloqueo;
	}

	/**
	 * @param codigoPais the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}		
}