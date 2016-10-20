package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ReporteSTOContratoEjecutivaForm extends BaseReporteForm  implements Serializable  
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String codigoPeriodo;	
	private String fechaDesde;
	private String fechaHasta;	
	private Date fechaDesdeD;
	private Date fechaHastaD;
	private String[] regionList;
	private String[] zonaList;
	
	private String descripcionRegionList;
	private String descripcionZonaList;	
	private String codigoCliente;
	private String numDocuIdentidad;
	private String tipoDocumento;
	
	
	public String getCodigoPais() {
		return codigoPais;
	}
	
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}
	
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	
	public String getFechaDesde() {
		return fechaDesde;
	}
	
	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	
	public String getFechaHasta() {
		return fechaHasta;
	}
	
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	
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
	
	public String[] getRegionList() {
		return regionList;
	}
	
	public void setRegionList(String[] regionList) {
		this.regionList = regionList;
	}
	
	public String[] getZonaList() {
		return zonaList;
	}
	
	public void setZonaList(String[] zonaList) {
		this.zonaList = zonaList;
	}
	public String getDescripcionRegionList() {
		return descripcionRegionList;
	}
	
	public void setDescripcionRegionList(String descripcionRegionList) {
		String temp = StringUtils.replace(descripcionRegionList, "&&","\n" );
		this.descripcionRegionList = temp;
	}
	
	public String getDescripcionZonaList() {
		return descripcionZonaList;
	}
	
	public void setDescripcionZonaList(String descripcionZonaList) {
		String temp = StringUtils.replace(descripcionZonaList, "&&","\n" );
		this.descripcionZonaList = temp;
	}
	
	public String getCodigoCliente() {
		return codigoCliente;
	}
	
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
	public String getNumDocuIdentidad() {
		return numDocuIdentidad;
	}
	
	public void setNumDocuIdentidad(String numDocuIdentidad) {
		this.numDocuIdentidad = numDocuIdentidad;
	}
	
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
}
