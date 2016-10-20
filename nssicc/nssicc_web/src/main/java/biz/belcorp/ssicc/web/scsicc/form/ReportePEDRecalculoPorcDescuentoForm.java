package biz.belcorp.ssicc.web.scsicc.form;

import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ReportePEDRecalculoPorcDescuentoForm extends BaseReporteForm{
	
	private static final long serialVersionUID = -4761918516654225364L;
	
	private String codigoPais;
	private String porcentajeDescuento;
	private String dsctoPlano;
	private String nroDocumento;
	private String nroFactura;
	private String condicionDsctoPlano;
	private String fechaFactura;
	private Date fechaFacturaDate;
	
	
	
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
	 * @return the porcentajeDescuento
	 */
	public String getPorcentajeDescuento() {
		return porcentajeDescuento;
	}
	/**
	 * @param porcentajeDescuento the porcentajeDescuento to set
	 */
	public void setPorcentajeDescuento(String porcentajeDescuento) {
		this.porcentajeDescuento = porcentajeDescuento;
	}
	/**
	 * @return the dsctoPlano
	 */
	public String getDsctoPlano() {
		return dsctoPlano;
	}
	/**
	 * @param dsctoPlano the dsctoPlano to set
	 */
	public void setDsctoPlano(String dsctoPlano) {
		this.dsctoPlano = dsctoPlano;
	}
	/**
	 * @return the nroDocumento
	 */
	public String getNroDocumento() {
		return nroDocumento;
	}
	/**
	 * @param nroDocumento the nroDocumento to set
	 */
	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}
	/**
	 * @return the nroFactura
	 */
	public String getNroFactura() {
		return nroFactura;
	}
	/**
	 * @param nroFactura the nroFactura to set
	 */
	public void setNroFactura(String nroFactura) {
		this.nroFactura = nroFactura;
	}
	/**
	 * @return the condicionDsctoPlano
	 */
	public String getCondicionDsctoPlano() {
		return condicionDsctoPlano;
	}
	/**
	 * @param condicionDsctoPlano the condicionDsctoPlano to set
	 */
	public void setCondicionDsctoPlano(String condicionDsctoPlano) {
		this.condicionDsctoPlano = condicionDsctoPlano;
	}
	/**
	 * @return the fechaFactura
	 */
	public String getFechaFactura() {
		return fechaFactura;
	}
	/**
	 * @param fechaFactura the fechaFactura to set
	 */
	public void setFechaFactura(String fechaFactura) {
		this.fechaFactura = fechaFactura;
	}
	/**
	 * @return the fechaFacturaDate
	 */
	public Date getFechaFacturaDate() {
		return fechaFacturaDate;
	}
	/**
	 * @param fechaFacturaDate the fechaFacturaDate to set
	 */
	public void setFechaFacturaDate(Date fechaFacturaDate) {
		this.fechaFacturaDate = fechaFacturaDate;
	}
	
	
	
}
