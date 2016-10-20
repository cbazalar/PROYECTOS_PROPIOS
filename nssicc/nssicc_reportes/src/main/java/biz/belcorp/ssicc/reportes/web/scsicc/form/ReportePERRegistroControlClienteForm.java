package biz.belcorp.ssicc.reportes.web.scsicc.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;




/**
 * @author <a href="mailto:dtoledo@belcorp.biz">David Toledo</a>
 * 
 */
public class ReportePERRegistroControlClienteForm extends BaseReporteForm implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String descripcionPais;

	private String codigoPais;

	private String fechaDesde;

	private String fechaHasta;
	
	private Date fechaDesdeDt;

	private Date fechaHastaDt;

	public Date getFechaDesdeDt() {
		return fechaDesdeDt;
	}

	public void setFechaDesdeDt(Date fechaDesdeDt) {
		this.fechaDesdeDt = fechaDesdeDt;
	}

	public Date getFechaHastaDt() {
		return fechaHastaDt;
	}

	public void setFechaHastaDt(Date fechaHastaDt) {
		this.fechaHastaDt = fechaHastaDt;
	}

	private String tipoReporte;
	
	private String tipoDocumento;
	
	private String numeroDocumento;
	
	
	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 *            The codigoPais to set.
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
	 * @param fechaDesde
	 *            New value of property fechaDesde.
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
	 * *
	 * 
	 * @param fechaHasta
	 *            New value of property fechaHasta.
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
	
	public String getDescripcionPais() {
		return descripcionPais;
	}

	public void setDescripcionPais(String descripcionPais) {
		this.descripcionPais = descripcionPais;
	}

	/**
	 * @return Returns the numeroDocumento.
	 */
	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	/**
	 * @param numeroDocumento The numeroDocumento to set.
	 */
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	/**
	 * @return Returns the tipoDocumento.
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}

	/**
	 * @param tipoDocumento The tipoDocumento to set.
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	


	
}
