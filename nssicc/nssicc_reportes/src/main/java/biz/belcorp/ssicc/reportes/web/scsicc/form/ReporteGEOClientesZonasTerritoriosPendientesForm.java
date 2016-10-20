package biz.belcorp.ssicc.reportes.web.scsicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * @author <a href="mailto:itocto@belcorp.biz">Marco Agurto</a>
 * 
 */
public class ReporteGEOClientesZonasTerritoriosPendientesForm extends BaseReporteForm 
		implements Serializable{

	private static final long serialVersionUID = 7074641390654574881L;

	private String codigoPais;

	private String fechaDesde;

	private String fechaHasta;

	private String tipoReporte;

	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 *            The codigoPais to set.
	 * 
	 * @struts.validator type = "required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return Returns the fechaDesde.
	 */
	public String getFechaDesde() {
		return fechaDesde;
	}

	/**
	 * @param fechaDesde
	 *            The fechaDesde to set.
	 */
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

}
