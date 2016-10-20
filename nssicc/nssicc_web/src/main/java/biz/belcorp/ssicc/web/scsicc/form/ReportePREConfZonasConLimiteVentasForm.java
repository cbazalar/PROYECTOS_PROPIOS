package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * @author <a href="mailto:etovar@csigcomt.com">Eder Tovar</a>
 * 
 */

public class ReportePREConfZonasConLimiteVentasForm extends BaseReporteForm	implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	
	private String periodoIni;
	
	private String periodoFin;
	
	private String codigoVenta;
	
	private String codigoSAP;
	
	private String[] tipoOfertaList;
	
	private String tipoOferta;

	/**
	 * @return the tipoOferta
	 */
	public String getTipoOferta() {
		return tipoOferta;
	}

	/**
	 * @param tipoOferta the tipoOferta to set
	 */
	public void setTipoOferta(String tipoOferta) {
		this.tipoOferta = tipoOferta;
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
	 * @return the codigoVenta
	 */
	public String getCodigoVenta() {
		return codigoVenta;
	}

	/**
	 * @param codigoVenta the codigoVenta to set
	 */
	public void setCodigoVenta(String codigoVenta) {
		this.codigoVenta = codigoVenta;
	}

	/**
	 * @return the codigoSAP
	 */
	public String getCodigoSAP() {
		return codigoSAP;
	}

	/**
	 * @param codigoSAP the codigoSAP to set
	 */
	public void setCodigoSAP(String codigoSAP) {
		this.codigoSAP = codigoSAP;
	}

	/**
	 * @return the serialVersionUID
	 */
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	/**
	 * @return the tipoOfertaList
	 */
	public String[] getTipoOfertaList() {
		return tipoOfertaList;
	}

	/**
	 * @param tipoOfertaList the tipoOfertaList to set
	 */
	public void setTipoOfertaList(String[] tipoOfertaList) {
		this.tipoOfertaList = tipoOfertaList;
	}

	/**
	 * @return the periodoIni
	 */
	public String getPeriodoIni() {
		return periodoIni;
	}

	/**
	 * @param periodoIni the periodoIni to set
	 */
	public void setPeriodoIni(String periodoIni) {
		this.periodoIni = periodoIni;
	}

	/**
	 * @return the periodoFin
	 */
	public String getPeriodoFin() {
		return periodoFin;
	}

	/**
	 * @param periodoFin the periodoFin to set
	 */
	public void setPeriodoFin(String periodoFin) {
		this.periodoFin = periodoFin;
	}
	
	
	
}