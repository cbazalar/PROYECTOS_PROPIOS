package biz.belcorp.ssicc.web.scsicc.form;

import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

// TODO: Auto-generated Javadoc
/**
 * The Class ReporteSACFaltanteNoAnunciadoNegocioForm.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 22/05/2014
 */
public class ReporteSACFaltanteNoAnunciadoNegocioForm  extends BaseReporteForm{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The codigo pais. */
	private String codigoPais;
	
	/** The codigo periodo. */
	private String codigoPeriodo;
	
	/** The fec fact ini. */
	private Date fecFactIni;
	
	/** The fec fact fin. */
	private Date fecFactFin;
	
	private String fecFactIniS;
	
	/** The fec fact fin. */
	private String fecFactFinS;
	
	/** The tipo consulta. */
	private String tipoConsulta;
	
	/** The incluye mav. */
	private String incluyeMAV;
	
	/** The negocio. */
	private String[] negocio;
	
	/** The codigo region. */
	private String[] codigoRegion;
	
	/** The codigo zona. */
	private String[] codigoZona;

	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais The codigoPais to set.
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
	 * @param codigoPeriodo The codigoPeriodo to set.
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
	 * @param incluyeMAV The incluyeMAV to set.
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
	 * @param tipoConsulta The tipoConsulta to set.
	 */
	public void setTipoConsulta(String tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}
	/**
	 * @return Returns the unidadNegocio.
	 */
	public String[] getNegocio() {
		return negocio;
	}
	/**
	 * @param unidadNegocio The unidadNegocio to set.
	 */
	public void setNegocio(String[] negocio) {
		this.negocio = negocio;
	}
	/**
	 * @return Returns the codigoRegion.
	 */
	public String[] getCodigoRegion() {
		return codigoRegion;
	}
	/**
	 * @param codigoRegion The codigoRegion to set.
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
	 * @param codigoZona The codigoZona to set.
	 */
	public void setCodigoZona(String[] codigoZona) {
		this.codigoZona = codigoZona;
	}
	public Date getFecFactIni() {
		return fecFactIni;
	}
	public void setFecFactIni(Date fecFactIni) {
		this.fecFactIni = fecFactIni;
	}
	public Date getFecFactFin() {
		return fecFactFin;
	}
	public void setFecFactFin(Date fecFactFin) {
		this.fecFactFin = fecFactFin;
	}
	/**
	 * @return the fecFactIniS
	 */
	public String getFecFactIniS() {
		return fecFactIniS;
	}
	/**
	 * @param fecFactIniS the fecFactIniS to set
	 */
	public void setFecFactIniS(String fecFactIniS) {
		this.fecFactIniS = fecFactIniS;
	}
	/**
	 * @return the fecFactFinS
	 */
	public String getFecFactFinS() {
		return fecFactFinS;
	}
	/**
	 * @param fecFactFinS the fecFactFinS to set
	 */
	public void setFecFactFinS(String fecFactFinS) {
		this.fecFactFinS = fecFactFinS;
	}
			
}
