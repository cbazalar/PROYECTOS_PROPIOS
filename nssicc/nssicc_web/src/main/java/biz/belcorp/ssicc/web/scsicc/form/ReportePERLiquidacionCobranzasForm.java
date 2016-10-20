package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * 
 * @author RRG
 * 
 */
public class ReportePERLiquidacionCobranzasForm extends BaseReporteForm
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5949009570991092287L;

	private String codigoPais;

	private String codigoSociedad;

	private String descripcionSociedad;

	private String codigoBanco;

	private String descripcionBanco;

	private String fechaPagoDesde;

	private String fechaPagoHasta;

	private String fechaProcDesde;

	private String fechaProcHasta;
	
	private Date fechaPagoDesdeD;

	private Date fechaPagoHastaD;

	private Date fechaProcDesdeD;

	private Date fechaProcHastaD;

	/**
	 * Valores por default
	 */
	public ReportePERLiquidacionCobranzasForm(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		this.fechaPagoDesde = sdf.format(new Date(System.currentTimeMillis()));
		this.fechaPagoHasta = sdf.format(new Date(System.currentTimeMillis()));
		this.fechaProcDesde = sdf.format(new Date(System.currentTimeMillis()));
		this.fechaProcHasta = sdf.format(new Date(System.currentTimeMillis()));
		this.fechaPagoDesdeD = new Date();
		this.fechaPagoHastaD = new Date();
		this.fechaProcDesdeD = new Date();
		this.fechaProcHastaD = new Date();
	}

	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 *            the codigoPais to set
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
	 * @param codigoSociedad
	 *            the codigoSociedad to set
	 */
	public void setCodigoSociedad(String codigoSociedad) {
		this.codigoSociedad = codigoSociedad;
	}

	/**
	 * @return the descripcionSociedad
	 */
	public String getDescripcionSociedad() {
		return descripcionSociedad;
	}

	/**
	 * @param descripcionSociedad
	 *            the descripcionSociedad to set
	 */
	public void setDescripcionSociedad(String descripcionSociedad) {
		this.descripcionSociedad = descripcionSociedad;
	}

	/**
	 * @return the codigoBanco
	 */
	public String getCodigoBanco() {
		return codigoBanco;
	}

	/**
	 * @param codigoBanco
	 *            the codigoBanco to set
	 */
	public void setCodigoBanco(String codigoBanco) {
		this.codigoBanco = codigoBanco;
	}

	/**
	 * @return the descripcionBanco
	 */
	public String getDescripcionBanco() {
		return descripcionBanco;
	}

	/**
	 * @param descripcionBanco
	 *            the descripcionBanco to set
	 */
	public void setDescripcionBanco(String descripcionBanco) {
		this.descripcionBanco = descripcionBanco;
	}

	/**
	 * @return the fechaPagoDesde
	 */
	public String getFechaPagoDesde() {
		return fechaPagoDesde;
	}

	/**
	 * @param fechaPagoDesde
	 *            the fechaPagoDesde to set
	 */
	public void setFechaPagoDesde(String fechaPagoDesde) {
		this.fechaPagoDesde = fechaPagoDesde;
	}

	/**
	 * @return the fechaPagoHasta
	 */
	public String getFechaPagoHasta() {
		return fechaPagoHasta;
	}

	/**
	 * @param fechaPagoHasta
	 *            the fechaPagoHasta to set
	 */
	public void setFechaPagoHasta(String fechaPagoHasta) {
		this.fechaPagoHasta = fechaPagoHasta;
	}

	/**
	 * @return the fechaProcDesde
	 */
	public String getFechaProcDesde() {
		return fechaProcDesde;
	}

	/**
	 * @param fechaProcDesde
	 *            the fechaProcDesde to set
	 */
	public void setFechaProcDesde(String fechaProcDesde) {
		this.fechaProcDesde = fechaProcDesde;
	}

	/**
	 * @return the fechaProcHasta
	 */
	public String getFechaProcHasta() {
		return fechaProcHasta;
	}

	/**
	 * @param fechaProcHasta
	 *            the fechaProcHasta to set
	 */
	public void setFechaProcHasta(String fechaProcHasta) {
		this.fechaProcHasta = fechaProcHasta;
	}

	/**
	 * @return the fechaPagoDesdeD
	 */
	public Date getFechaPagoDesdeD() {
		return fechaPagoDesdeD;
	}

	/**
	 * @param fechaPagoDesdeD the fechaPagoDesdeD to set
	 */
	public void setFechaPagoDesdeD(Date fechaPagoDesdeD) {
		this.fechaPagoDesdeD = fechaPagoDesdeD;
	}

	/**
	 * @return the fechaPagoHastaD
	 */
	public Date getFechaPagoHastaD() {
		return fechaPagoHastaD;
	}

	/**
	 * @param fechaPagoHastaD the fechaPagoHastaD to set
	 */
	public void setFechaPagoHastaD(Date fechaPagoHastaD) {
		this.fechaPagoHastaD = fechaPagoHastaD;
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
