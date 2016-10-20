package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;

import java.text.SimpleDateFormat;
import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
/**
 *
 * @author <a href="mailto:dsantacruz@sigcomt.com">Danny Santa Cruz Rojas</a>
 *
 */

public class ReporteZONTerritorioUnidadGeograficaForm extends
BaseReporteForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2344142813971000471L;
	/**
	 * 
	 */

	private String codigoPais;

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


	

}
