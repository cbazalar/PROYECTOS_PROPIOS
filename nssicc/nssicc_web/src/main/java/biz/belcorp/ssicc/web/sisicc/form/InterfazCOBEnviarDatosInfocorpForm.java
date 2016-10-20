package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

public class InterfazCOBEnviarDatosInfocorpForm extends BaseInterfazForm implements Serializable{

	private static final long serialVersionUID = 2274119894600136077L;
	private String codigoPais;
	private String campanyaInicial;
	private String importe;
	private String diasVencimiento;
	
	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais The codigoPais to set.
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return Returns the campanyaInicial.
	 */
	public String getCampanyaInicial() {
		return campanyaInicial;
	}

	/**
	 * @param campanyaInicial The campanyaInicial to set.
	 */
	public void setCampanyaInicial(String campanyaInicial) {
		this.campanyaInicial = campanyaInicial;
	}

	/**
	 * @return Returns the diasVencimientoas.
	 */
	public String getDiasVencimiento() {
		return diasVencimiento;
	}

	/**
	 * @param dias The diasVencimiento to set.
	 */
	public void setDiasVencimiento(String diasVencimiento) {
		this.diasVencimiento = diasVencimiento;
	}

	/**
	 * @return Returns the importe.
	 */
	public String getImporte() {
		return importe;
	}

	/**
	 * @param importe The importe to set.
	 */
	public void setImporte(String importe) {
		this.importe = importe;
	}	
}