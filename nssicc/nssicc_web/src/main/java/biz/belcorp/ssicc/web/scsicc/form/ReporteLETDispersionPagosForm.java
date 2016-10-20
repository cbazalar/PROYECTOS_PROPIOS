package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ReporteLETDispersionPagosForm  extends BaseReporteForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3009475545473957090L;

	private String codigoPais;

	private String codigoPeriodo;
	
	private String codigoRegion;
	
	private String estadoPago;
	
	private String codigoPrograma;
	private String descPrograma;
	
	private String fechaPagos;
	
	private Date fechaPagosD;
	
	private String[] estadoPagoList;
	private String[] regionList;
	private String[] zonaList;
	private String codigoPeriodoFinal;
    private String fechaPagosFinal;
	private Date fechaPagosFinalD;

		

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
	 * @return Returns the codigoPeriodo.
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo
	 *            The codigoPeriodo to set.
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return the codigoRegion
	 */
	public String getCodigoRegion() {
		return codigoRegion;
	}

	/**
	 * @param codigoRegion the codigoRegion to set
	 */
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}

	/**
	 * @return the estadoPago
	 */
	public String getEstadoPago() {
		return estadoPago;
	}

	/**
	 * @param estadoPago the estadoPago to set
	 */
	public void setEstadoPago(String estadoPago) {
		this.estadoPago = estadoPago;
	}

	
	/**
	 * @return the codigoPrograma
	 */
	public String getCodigoPrograma() {
		return codigoPrograma;
	}

	/**
	 * @param codigoPrograma the codigoPrograma to set
	 */
	public void setCodigoPrograma(String codigoPrograma) {
		this.codigoPrograma = codigoPrograma;
	}

	/**
	 * @return the descPrograma
	 */
	public String getDescPrograma() {
		return descPrograma;
	}

	/**
	 * @param descPrograma the descPrograma to set
	 */
	public void setDescPrograma(String descPrograma) {
		this.descPrograma = descPrograma;
	}
	/**
	 * @return the fechaPagos
	 */
	public String getFechaPagos() {
		return fechaPagos;
	}

	public void setFechaPagos(String fechaPagos) {
		this.fechaPagos = fechaPagos;
	}

	public Date getFechaPagosD() {
		return fechaPagosD;
	}

	public void setFechaPagosD(Date fechaPagosD) {
		this.fechaPagosD = fechaPagosD;
	}

	/**
	 * @return the regionList
	 */
	public String[] getRegionList() {
		return regionList;
	}

	/**
	 * @param regionList the regionList to set
	 */
	public void setRegionList(String[] regionList) {
		this.regionList = regionList;
	}

	/**
	 * @return the zonaList
	 */
	public String[] getZonaList() {
		return zonaList;
	}

	/**
	 * @param zonaList the zonaList to set
	 */
	public void setZonaList(String[] zonaList) {
		this.zonaList = zonaList;
	}

	/**
	 * @return the codigoPeriodoFinal
	 */
	public String getCodigoPeriodoFinal() {
		return codigoPeriodoFinal;
	}

	/**
	 * @param codigoPeriodoFinal the codigoPeriodoFinal to set
	 */
	public void setCodigoPeriodoFinal(String codigoPeriodoFinal) {
		this.codigoPeriodoFinal = codigoPeriodoFinal;
	}

	/**
	 * @return the fechaPagosFinal
	 */
	public String getFechaPagosFinal() {
		return fechaPagosFinal;
	}

	/**
	 * @param fechaPagosFinal the fechaPagosFinal to set
	 */
	public void setFechaPagosFinal(String fechaPagosFinal) {
		this.fechaPagosFinal = fechaPagosFinal;
	}

	/**
	 * @return the fechaPagosFinalD
	 */
	public Date getFechaPagosFinalD() {
		return fechaPagosFinalD;
	}

	/**
	 * @param fechaPagosFinalD the fechaPagosFinalD to set
	 */
	public void setFechaPagosFinalD(Date fechaPagosFinalD) {
		this.fechaPagosFinalD = fechaPagosFinalD;
	}

	/**
	 * @return the estadoPagoList
	 */
	public String[] getEstadoPagoList() {
		return estadoPagoList;
	}

	/**
	 * @param estadoPagoList the estadoPagoList to set
	 */
	public void setEstadoPagoList(String[] estadoPagoList) {
		this.estadoPagoList = estadoPagoList;
	}
	
	
	
}
