package biz.belcorp.ssicc.web.spusicc.zon.form;

import java.io.Serializable;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ReporteZONErroresEliminacionActUniAdmForm extends BaseReporteForm implements Serializable{

	private static final long serialVersionUID = -2833114917018293707L;
	
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
