package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * @author Jose Pulido
 * @company Sigcomt
 * 
 */
public class ReporteCCCConsolidadoTransaccionesBancariasForm extends
		BaseReporteForm implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codigoPais;

	private String tipoVista;

	private String tipoOrigenLote;

	private String codigoBanco;

	private String fechaPagoDesde;

	private String fechaPagoHasta;

	private Date fechaPagoDesdeD;

	private Date fechaPagoHastaD;

	/**
	 * @return
	 */
	public String getCodigoBanco() {
		return codigoBanco;
	}

	/**
	 * @param codigoBanco
	 */
	public void setCodigoBanco(String codigoBanco) {
		this.codigoBanco = codigoBanco;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm#getCodigoPais()
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm#setCodigoPais
	 * (java.lang.String)
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return
	 */
	public String getTipoVista() {
		return tipoVista;
	}

	/**
	 * @param tipoVista
	 */
	public void setTipoVista(String tipoVista) {
		this.tipoVista = tipoVista;
	}

	/**
	 * @return
	 */
	public String getTipoOrigenLote() {
		return tipoOrigenLote;
	}

	public void setTipoOrigenLote(String tipoOrigenLote) {
		this.tipoOrigenLote = tipoOrigenLote;
	}

	/**
	 * @return
	 */
	public String getFechaPagoDesde() {
		return fechaPagoDesde;
	}

	/**
	 * @param fechaPagoDesde
	 */
	public void setFechaPagoDesde(String fechaPagoDesde) {
		this.fechaPagoDesde = fechaPagoDesde;
	}

	/**
	 * @return
	 */
	public String getFechaPagoHasta() {
		return fechaPagoHasta;
	}

	/**
	 * @param fechaPagoHasta
	 */
	public void setFechaPagoHasta(String fechaPagoHasta) {
		this.fechaPagoHasta = fechaPagoHasta;
	}

	/**
	 * @return the fechaPagoDesdeD
	 */
	public Date getFechaPagoDesdeD() {
		return fechaPagoDesdeD;
	}

	/**
	 * @param fechaPagoDesdeD
	 *            the fechaPagoDesdeD to set
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
	 * @param fechaPagoHastaD
	 *            the fechaPagoHastaD to set
	 */
	public void setFechaPagoHastaD(Date fechaPagoHastaD) {
		this.fechaPagoHastaD = fechaPagoHastaD;
	}
}