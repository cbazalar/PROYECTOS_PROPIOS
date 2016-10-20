package biz.belcorp.ssicc.web.scsicc.form;


import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;


/**
 * @author <a href="mailto:aoviedo@sigcomt.com">Aurelio Oviedo</a>
 * 
 */
public class ReporteRECExcepcionValidacionesForm extends BaseReporteForm{

	private static final long serialVersionUID = 1L;

	private String codigoPais;
	private String tipoDocumento;
	private String validacion;
	private String[] regionList;
	private String[] zonaList;
	private String codigoPeriodo;
	private String consultora;
	private Date fecha;

	
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
	 * @return the validacion
	 */
	public String getValidacion() {
		return validacion;
	}

	/**
	 * @param validacion the validacion to set
	 */
	public void setValidacion(String validacion) {
		this.validacion = validacion;
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
	 * @return the consultora
	 */
	public String getConsultora() {
		return consultora;
	}

	/**
	 * @param consultora the consultora to set
	 */
	public void setConsultora(String consultora) {
		this.consultora = consultora;
	}

	/**
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	

	
}
