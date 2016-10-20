package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;


/**
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>

 */
public class ReporteRECCuadreSAPForm extends BaseReporteForm implements Serializable {
	private static final long serialVersionUID = 1L;

	private String codigoPais;

	/**
	 * 
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @struts.validator type="required"
	 * @param codigoPais
	 *            The codigoPais to set.
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

}
