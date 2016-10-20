/**
 * 
 */
package biz.belcorp.ssicc.web.scsicc.form;

import java.text.SimpleDateFormat;
import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ReporteCCCDetalladoProvisionIncobrableForm extends BaseReporteForm {

	private static final long serialVersionUID = 1L;

	private String codigoPais;
	private String fechaInicial;
	private Date   dfechaInicial;
	private String fechaFinal;
	private Date   dfechaFinal;
	private String tipoReporte;
	private boolean mostrarBotonExcel;
	
	public void reset() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String fecha = sdf.format(new Date(System.currentTimeMillis()));
				
		this.fechaInicial = fecha;
		this.fechaFinal = fecha;
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
	 * @return the mostrarBotonExcel
	 */
	public boolean isMostrarBotonExcel() {
		return mostrarBotonExcel;
	}


	/**
	 * @param mostrarBotonExcel the mostrarBotonExcel to set
	 */
	public void setMostrarBotonExcel(boolean mostrarBotonExcel) {
		this.mostrarBotonExcel = mostrarBotonExcel;
	}



	/**
	 * @return the fechaInicial
	 */
	public String getFechaInicial() {
		return fechaInicial;
	}
	/**
	 * @param fechaInicial the fechaInicial to set
	 */
	public void setFechaInicial(String fechaInicial) {
		this.fechaInicial = fechaInicial;
	}
	
	/**
	 * @return the fechaFinal
	 */
	public String getFechaFinal() {
		return fechaFinal;
	}
	
	/**
	 * @param fechaFinal the fechaFinal to set
	 */
	public void setFechaFinal(String fechaFinal) {
		this.fechaFinal = fechaFinal;
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


	public Date getDfechaInicial() {
		return dfechaInicial;
	}


	public void setDfechaInicial(Date dfechaInicial) {
		this.dfechaInicial = dfechaInicial;
	}


	public Date getDfechaFinal() {
		return dfechaFinal;
	}


	public void setDfechaFinal(Date dfechaFinal) {
		this.dfechaFinal = dfechaFinal;
	}	
}
