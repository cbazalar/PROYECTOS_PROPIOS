package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * @author <a href="mailto:jperez@sigcomt.com">Juan Carlos Prez</a>
 * 
 * @struts.form name = "consultaCOMComisionGerenteZonaEscalonadaSearchForm"
 */
public class ConsultaCOMComisionGerenteZonaEscalonadaSearchForm extends BaseReporteForm implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3545201168624732190L;

	private String codigoPais;
	
	private String[] codigoComision;
		
	private String codigoPeriodoIni;
	
	private String codigoPeriodoFin;
	
	private String codigoRegion;	
	

	/**
	 * @return Returns the codigoComision.
	 */
	public String[] getCodigoComision() {
		return codigoComision;
	}

	/**
	 * @param codigoComision The codigoComision to set.
	 * @struts.validator type = "required"
	 */
	public void setCodigoComision(String[] codigoComision) {
		this.codigoComision = codigoComision;
	}

	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais The codigoPais to set.
	 * @struts.validator type = "required" 
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}



	/**
	 * @return Returns the codigoPeriodoIni.
	 */
	public String getCodigoPeriodoIni() {
		return codigoPeriodoIni;
	}


	/**
	 * @param codigoPeriodoFin The codigoPeriodofin to set.
	 * @struts.validator type = "required" 
	 */
	public void setCodigoPeriodoFin(String codigoPeriodoFin) {
		this.codigoPeriodoFin = codigoPeriodoFin;
	}
	
	/**
	 * @return Returns the codigoPeriodoFin.
	 */
	public String getCodigoPeriodoFin() {
		return codigoPeriodoFin;
	}


	/**
	 * @param codigoPeriodoIni The codigoPeriodoIni to set.
	 * @struts.validator type = "required" 
	 */
	public void setCodigoPeriodoIni(String codigoPeriodoIni) {
		this.codigoPeriodoIni = codigoPeriodoIni;
	}

	/**
	 * @return Returns the codigoRegion.
	 */
	public String getCodigoRegion() {
		return codigoRegion;
	}

	/**
	 * @param codigoRegion The codigoRegion to set.
	 */
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}
}
