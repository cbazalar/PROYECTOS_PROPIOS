package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * 
 * @author RRG
 * 
 */
public class ReporteCOSCierreCostosForm extends BaseReporteForm	implements Serializable{

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -9016171332416064486L;
	private String codigoAnhoMes;
	private String codigoReporte;
	private String codigoPais;
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
	 * @return the codigoReporte
	 */
	public String getCodigoReporte() {
		return codigoReporte;
	}
	/**
	 * @param codigoReporte the codigoReporte to set
	 */
	public void setCodigoReporte(String codigoReporte) {
		this.codigoReporte = codigoReporte;
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
			
	
	
}

