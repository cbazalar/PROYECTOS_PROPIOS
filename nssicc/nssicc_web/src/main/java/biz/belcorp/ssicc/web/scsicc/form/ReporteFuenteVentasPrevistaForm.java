package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;


public class ReporteFuenteVentasPrevistaForm extends BaseReporteForm implements Serializable{
	
	
	private static final long serialVersionUID = 8497650785706397607L;
	
	private String codigoPais;

	private String periodoInicio;
	
	private String periodoFin;
	
	private String tipoReporte;


	public String getCodigoPais() {
		return codigoPais;
	}

	
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	
	public String getPeriodoInicio() {
		return periodoInicio;
	}

	
	public void setPeriodoInicio(String periodoInicio) {
		this.periodoInicio = periodoInicio;
	}
	
	
	public String getPeriodoFin() {
		return periodoFin;
	}

	
	public void setPeriodoFin(String periodoFin) {
		this.periodoFin = periodoFin;
	}


	/**
	 * @return the tipoReporte
	 */
	public String getTipoReporte() {
		return tipoReporte;
	}


	/**
	 * @param tipoReporte the tipoReporte to set
	 */
	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}
	
	
}
