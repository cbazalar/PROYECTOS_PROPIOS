package biz.belcorp.ssicc.web.scsicc.form;

import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * The Class ReporteSACFaltanteNoAnunciadoForm.
 * 
 * @autor: Belcorp
 * @version: 1.0 21/05/2014
 */
public class ReporteSACFaltanteNoAnunciadoForm extends BaseReporteForm {

	private static final long serialVersionUID = 1L;

	private String codigoPais;

	private String codigoPeriodo;

	private Date fecFactIni;

	private Date fecFactFin;

	private String tipoConsulta;

	private String incluyeMAV;

	private String[] unidadNegocio;

	private String[] codigoRegion;

	private String[] codigoZona;

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
	 * @return Returns the incluyeMAV.
	 */
	public String getIncluyeMAV() {
		return incluyeMAV;
	}

	/**
	 * @param incluyeMAV
	 *            The incluyeMAV to set.
	 */
	public void setIncluyeMAV(String incluyeMAV) {
		this.incluyeMAV = incluyeMAV;
	}

	/**
	 * @return Returns the tipoConsulta.
	 */
	public String getTipoConsulta() {
		return tipoConsulta;
	}

	/**
	 * @param tipoConsulta
	 *            The tipoConsulta to set.
	 */
	public void setTipoConsulta(String tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}

	/**
	 * @return Returns the unidadNegocio.
	 */
	public String[] getUnidadNegocio() {
		return unidadNegocio;
	}

	/**
	 * @param unidadNegocio
	 *            The unidadNegocio to set.
	 */
	public void setUnidadNegocio(String[] unidadNegocio) {
		this.unidadNegocio = unidadNegocio;
	}

	/**
	 * @return Returns the codigoRegion.
	 */
	public String[] getCodigoRegion() {
		return codigoRegion;
	}

	/**
	 * @param codigoRegion
	 *            The codigoRegion to set.
	 */
	public void setCodigoRegion(String[] codigoRegion) {
		this.codigoRegion = codigoRegion;
	}

	/**
	 * @return Returns the codigoZona.
	 */
	public String[] getCodigoZona() {
		return codigoZona;
	}

	/**
	 * @param codigoZona
	 *            The codigoZona to set.
	 */
	public void setCodigoZona(String[] codigoZona) {
		this.codigoZona = codigoZona;
	}

	/**
	 * @return the fecFactIni
	 */
	public Date getFecFactIni() {
		return fecFactIni;
	}

	/**
	 * @param fecFactIni the fecFactIni to set
	 */
	public void setFecFactIni(Date fecFactIni) {
		this.fecFactIni = fecFactIni;
	}

	/**
	 * @return the fecFactFin
	 */
	public Date getFecFactFin() {
		return fecFactFin;
	}

	/**
	 * @param fecFactFin the fecFactFin to set
	 */
	public void setFecFactFin(Date fecFactFin) {
		this.fecFactFin = fecFactFin;
	}
}
