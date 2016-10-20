package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ReporteCCCConsolidadoCuentaCorrienteContableForm extends
		BaseReporteForm implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codigoPais;

	private String fechaProcDesde;

	private String fechaProcHasta;

	private Date fechaProcDesdeD;

	private Date fechaProcHastaD;

	private String tipoReporte;

	private String codigoSociedad;

	private String descripcionPais;

	/**
	 * @return
	 */
	public String getDescripcionPais() {
		return descripcionPais;
	}

	/**
	 * @param descripcionPais
	 */
	public void setDescripcionPais(String descripcionPais) {
		this.descripcionPais = descripcionPais;
	}

	/**
	 * @return
	 */
	public String getCodigoSociedad() {
		return codigoSociedad;
	}

	/**
	 * @param codigoSociedad
	 */
	public void setCodigoSociedad(String codigoSociedad) {
		this.codigoSociedad = codigoSociedad;
	}

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
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return Returns the fechaDesde.
	 */
	public String getFechaProcDesde() {
		return fechaProcDesde;
	}

	/**
	 * @param fechaProcDesde
	 *            The fechaProcDesde to set.
	 * @param fechaProcDesde
	 *            New value of property fechaProcDesde.
	 */
	public void setFechaProcDesde(String fechaProcDesde) {
		this.fechaProcDesde = fechaProcDesde;
	}

	/**
	 * @return Returns the fechaHasta.
	 */
	public String getFechaProcHasta() {
		return fechaProcHasta;
	}

	/**
	 * @param fechaHasta
	 *            New value of property fechaProcHasta.
	 *                       value="${defaultDatePattern}"
	 */
	public void setFechaProcHasta(String fechaProcHasta) {
		this.fechaProcHasta = fechaProcHasta;
	}

	/**
	 * @return
	 */
	public String getTipoReporte() {
		return tipoReporte;
	}

	/**
	 * @param tipoReporte
	 */
	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}

	/**
	 * @return the fechaProcDesdeD
	 */
	public Date getFechaProcDesdeD() {
		return fechaProcDesdeD;
	}

	/**
	 * @param fechaProcDesdeD the fechaProcDesdeD to set
	 */
	public void setFechaProcDesdeD(Date fechaProcDesdeD) {
		this.fechaProcDesdeD = fechaProcDesdeD;
	}

	/**
	 * @return the fechaProcHastaD
	 */
	public Date getFechaProcHastaD() {
		return fechaProcHastaD;
	}

	/**
	 * @param fechaProcHastaD the fechaProcHastaD to set
	 */
	public void setFechaProcHastaD(Date fechaProcHastaD) {
		this.fechaProcHastaD = fechaProcHastaD;
	}
}