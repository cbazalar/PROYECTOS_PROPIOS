package biz.belcorp.ssicc.reportes.web.scsicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * 
 * @author RRG
 * 
 */

public class ReportePEDBonificacionesForm extends BaseReporteForm implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2078571948685457786L;

	private String codigoAnhoMes;
	private String codigoPais;
	private String tipoDocumento;

	public ReportePEDBonificacionesForm() {
		codigoAnhoMes = null;
		tipoDocumento = null;
	}

	/**
	 * @return the codigoAnhoMes
	 */
	public String getCodigoAnhoMes() {
		return codigoAnhoMes;
	}

	/**
	 * @param codigoAnhoMes the codigoAnhoMes to set
	 */
	public void setCodigoAnhoMes(String codigoAnhoMes) {
		this.codigoAnhoMes = codigoAnhoMes;
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
	 * @return the tipoDocumento
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}

	/**
	 * @param tipoDocumento the tipoDocumento to set
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	
	
}
