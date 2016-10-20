package biz.belcorp.ssicc.web.scsicc.form;

import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * The Class ReporteSACFacturacionResumenForm.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 15/09/2014
 */
public class ReporteSACFacturacionResumenForm extends BaseReporteForm {
	
	private static final long serialVersionUID = 1L;

	private String codigoPais;

	private String codigoPeriodo;
	
	private String fechaProceso;
	
	private Date fechaProcesoDate;
	
	private String []regionList;
	
	private String vistaReporte;
	
	private String almacen;
	
	private String origen;
	
	private String reporte;

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

	public String getFechaProceso() {
		return fechaProceso;
	}

	public void setFechaProceso(String fechaProceso) {
		this.fechaProceso = fechaProceso;
	}

	public Date getFechaProcesoDate() {
		return fechaProcesoDate;
	}

	public void setFechaProcesoDate(Date fechaProcesoDate) {
		this.fechaProcesoDate = fechaProcesoDate;
	}

	public String[] getRegionList() {
		return regionList;
	}

	public void setRegionList(String[] regionList) {
		this.regionList = regionList;
	}

	public String getVistaReporte() {
		return vistaReporte;
	}

	public void setVistaReporte(String vistaReporte) {
		this.vistaReporte = vistaReporte;
	}

	public String getAlmacen() {
		return almacen;
	}

	public void setAlmacen(String almacen) {
		this.almacen = almacen;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getReporte() {
		return reporte;
	}

	public void setReporte(String reporte) {
		this.reporte = reporte;
	}
		

	
}
