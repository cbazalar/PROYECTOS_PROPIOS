package biz.belcorp.ssicc.reportes.web.scsicc.form;


import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;



/**
 * @author <a href="mailto:jflorencio@belcorp.biz">JFA</a>
 * 
 * @struts.form name = "reporteCCCRecuperacionFamiliaProtegidaForm"
 */
public class ReporteCCCRecuperacionFamiliaProtegidaForm extends BaseReporteForm implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String codigoPais;
			
	private String tipoReporte;
	
	private String codigoCampanaInicial;

	private String codigoCampanaFinal;
	
    private String fechaDesde;
    
    private Date fechaDesdeD;
	
	private String fechaHasta;
	
	private Date fechaHastaD;
	
	
	
	

	public Date getFechaDesdeD() {
		return fechaDesdeD;
	}


	public void setFechaDesdeD(Date fechaDesdeD) {
		this.fechaDesdeD = fechaDesdeD;
	}


	public Date getFechaHastaD() {
		return fechaHastaD;
	}


	public void setFechaHastaD(Date fechaHastaD) {
		this.fechaHastaD = fechaHastaD;
	}


	public void reset() {					
	}

	
	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 *            The codigoPais to set.
	 * @struts.validator type = "required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

								
	/**
	 * @return the tipoReporte
	 * 
	 */
	public String getTipoReporte() {
		return tipoReporte;
	}


	/**
	 * @param tipoReporte the tipoReporte to set
	 * @struts.validator type = "required"
	 */
	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}
	
	/**
	 * @return Returns the codigoCampanaInicial.
	 */
	public String getCodigoCampanaInicial() {
		return codigoCampanaInicial;
	}

	/**
	 * @struts.validator type="mask"
	 * @struts.validator-var name="mask" value="${campaign}"
	 * @param codigoCampanaInicial The codigoCampanaInicial to set.
	 * 
	 */
	public void setCodigoCampanaInicial(String codigoCampanaInicial) {
		this.codigoCampanaInicial = codigoCampanaInicial;
	}

	/**
	 * @return Returns the codigoCampanaFinal.
	 */
	public String getCodigoCampanaFinal() {
		return codigoCampanaFinal;
	}

	/**
	 * @struts.validator type="mask"
	 * @struts.validator-var name="mask" value="${campaign}"
	 * @param codigoCampanaFinal The codigoCampanaFinal to set.
	 */
	public void setCodigoCampanaFinal(String codigoCampanaFinal) {
		this.codigoCampanaFinal = codigoCampanaFinal;
	}


	/**
	 * @return the fechaDesde
	 */
	public String getFechaDesde() {
		return fechaDesde;
	}


	/**
	 * @param fechaDesde the fechaDesde to set
	 */
	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}


	/**
	 * @return the fechaHasta
	 */
	public String getFechaHasta() {
		return fechaHasta;
	}


	/**
	 * @param fechaHasta the fechaHasta to set
	 */
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}


}
