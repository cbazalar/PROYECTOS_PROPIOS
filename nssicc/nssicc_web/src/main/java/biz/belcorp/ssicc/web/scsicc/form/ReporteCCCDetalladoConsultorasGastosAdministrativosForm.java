/**
 * 
 */
package biz.belcorp.ssicc.web.scsicc.form;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;


/**
 * 
 * @author <a href="jflorencio@belcorp.biz">Jorge Florencio</a>
 * 
 * @struts.form name="reporteCCCDetalladoConsultorasGastosAdministrativosForm"
 *              extends="baseReporteForm"
 */

public class ReporteCCCDetalladoConsultorasGastosAdministrativosForm extends BaseReporteForm {

	private static final long serialVersionUID = 1L;

	private String codigoPais;

	private String fechaInicio;
	
	private Date fechaInicioD;
	
	private String fechaFin;
	
	private Date fechaFinD;

	private String[] regionList;
	
	private String[] zonaList;

	private String[] seccionList;
	
	private String descripcionRegionList;
	
	private String descripcionZonaList;
	
	private String descripcionSeccionList;
	
	private String tipoReporte;
	

	
	public String getTipoReporte() {
		return tipoReporte;
	}

	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}

	public Date getFechaInicioD() {
		return fechaInicioD;
	}

	public void setFechaInicioD(Date fechaInicioD) {
		this.fechaInicioD = fechaInicioD;
	}

	public Date getFechaFinD() {
		return fechaFinD;
	}

	public void setFechaFinD(Date fechaFinD) {
		this.fechaFinD = fechaFinD;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		
		this.fechaInicio="";
		this.fechaFin="";
	}
	
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}


	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the fechaInicio
	 */
	public String getFechaInicio() {
		return fechaInicio;
	}


	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * @return the fechaFin
	 */
	public String getFechaFin() {
		return fechaFin;
	}


	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
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
	 * @return the seccionList
	 */
	public String[] getSeccionList() {
		return seccionList;
	}

	/**
	 * @param seccionList the seccionList to set
	 */
	public void setSeccionList(String[] seccionList) {
		this.seccionList = seccionList;
	}

	/**
	 * @return the descripcionRegionList
	 */
	public String getDescripcionRegionList() {
		return descripcionRegionList;
	}

	/**
	 * @param descripcionRegionList the descripcionRegionList to set
	 */
	public void setDescripcionRegionList(String descripcionRegionList) {
		this.descripcionRegionList = descripcionRegionList;
	}

	/**
	 * @return the descripcionZonaList
	 */
	public String getDescripcionZonaList() {
		return descripcionZonaList;
	}

	/**
	 * @param descripcionZonaList the descripcionZonaList to set
	 */
	public void setDescripcionZonaList(String descripcionZonaList) {
		this.descripcionZonaList = descripcionZonaList;
	}

	/**
	 * @return the descripcionSeccionList
	 */
	public String getDescripcionSeccionList() {
		return descripcionSeccionList;
	}

	/**
	 * @param descripcionSeccionList the descripcionSeccionList to set
	 */
	public void setDescripcionSeccionList(String descripcionSeccionList) {
		this.descripcionSeccionList = descripcionSeccionList;
	}	
	
	
}
