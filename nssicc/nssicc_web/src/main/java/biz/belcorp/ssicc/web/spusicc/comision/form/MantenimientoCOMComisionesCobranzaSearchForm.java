package biz.belcorp.ssicc.web.spusicc.comision.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoCOMComisionesCobranzaSearchForm extends BaseSearchForm implements Serializable {
	
	private static final long serialVersionUID = 169680144045874377L;

	private String codigoPais;
	
	private String codigoComision;
		
	private String descripcionComision;

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
	 * @return the codigoComision
	 */
	public String getCodigoComision() {
		return codigoComision;
	}

	/**
	 * @param codigoComision the codigoComision to set
	 */
	public void setCodigoComision(String codigoComision) {
		this.codigoComision = codigoComision;
	}

	/**
	 * @return the descripcionComision
	 */
	public String getDescripcionComision() {
		return descripcionComision;
	}

	/**
	 * @param descripcionComision the descripcionComision to set
	 */
	public void setDescripcionComision(String descripcionComision) {
		this.descripcionComision = descripcionComision;
	}
	

}
