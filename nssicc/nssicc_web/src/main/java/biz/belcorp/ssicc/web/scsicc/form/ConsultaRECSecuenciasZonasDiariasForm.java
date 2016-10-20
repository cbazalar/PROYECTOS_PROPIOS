package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

/**
 * @author peextrramirez - Rosalvina Ramirez Guardia
 * 
 *
 */
public class ConsultaRECSecuenciasZonasDiariasForm extends BaseSearchForm implements Serializable{
	
	
	private static final long serialVersionUID = 4296833235714654883L;
	
	private String codigoPais;

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
	

}
