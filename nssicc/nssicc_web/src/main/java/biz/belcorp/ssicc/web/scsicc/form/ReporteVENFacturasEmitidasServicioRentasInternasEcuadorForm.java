package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ReporteVENFacturasEmitidasServicioRentasInternasEcuadorForm extends BaseReporteForm implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codigoPais;
	private String codigoSociedad;
	private String tipoDocumento;
	private String tipoReporte;
	private String codigoPeriodo;
	private String fechaFacturacion;
	private String fechaEmision;
	private Date fechaFacturacionD;
	private Date fechaEmisionD;
	
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
	 * @return the codigoSociedad
	 */
	public String getCodigoSociedad() {
		return codigoSociedad;
	}
	/**
	 * @param codigoSociedad the codigoSociedad to set
	 */
	public void setCodigoSociedad(String codigoSociedad) {
		this.codigoSociedad = codigoSociedad;
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
	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}
	/**
	 * @param codigoPeriodo the codigoPeriodo to set
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	/**
	 * @return the fechaFacturacion
	 */
	public String getFechaFacturacion() {
		return fechaFacturacion;
	}
	/**
	 * @param fechaFacturacion the fechaFacturacion to set
	 */
	public void setFechaFacturacion(String fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}
	/**
	 * @return the fechaEmision
	 */
	public String getFechaEmision() {
		return fechaEmision;
	}
	/**
	 * @param fechaEmision the fechaEmision to set
	 */
	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	/**
	 * @return the fechaFacturacionD
	 */
	public Date getFechaFacturacionD() {
		return fechaFacturacionD;
	}
	/**
	 * @param fechaFacturacionD the fechaFacturacionD to set
	 */
	public void setFechaFacturacionD(Date fechaFacturacionD) {
		this.fechaFacturacionD = fechaFacturacionD;
	}
	/**
	 * @return the fechaEmisionD
	 */
	public Date getFechaEmisionD() {
		return fechaEmisionD;
	}
	/**
	 * @param fechaEmisionD the fechaEmisionD to set
	 */
	public void setFechaEmisionD(Date fechaEmisionD) {
		this.fechaEmisionD = fechaEmisionD;
	}
}