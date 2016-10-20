package biz.belcorp.ssicc.web.scsicc.form;



import java.text.SimpleDateFormat;
import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;



public class ReporteGEOClientesZonasTerritoriosErradosForm extends BaseReporteForm {
	private String codigoPais;

	private String fechaDesde;
	
	private Date fechaDesdeD;

	private String fechaHasta;
	
	private Date FechaHastaD;

	private String tipoReporte;

	
	
	public Date getFechaDesdeD() {
		return fechaDesdeD;
	}


	public void setFechaDesdeD(Date fechaDesdeD) {
		this.fechaDesdeD = fechaDesdeD;
	}


	public Date getFechaHastaD() {
		return FechaHastaD;
	}


	public void setFechaHastaD(Date fechaHastaD) {
		FechaHastaD = fechaHastaD;
	}


	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}


	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public String getFechaDesde() {
		return fechaDesde;
	}


	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	/**
	 * @return Returns the fechaHasta.
	 */
	public String getFechaHasta() {
		return fechaHasta;
	}

	/**
	 * @param fechaHasta
	 *            The fechaHasta to set.
	 */
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public String getTipoReporte() {
		return tipoReporte;
	}

	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}


	public void reset() {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		this.fechaDesde = sdf.format(new Date(System.currentTimeMillis()));
		this.fechaHasta = sdf.format(new Date(System.currentTimeMillis()));

	}
}
