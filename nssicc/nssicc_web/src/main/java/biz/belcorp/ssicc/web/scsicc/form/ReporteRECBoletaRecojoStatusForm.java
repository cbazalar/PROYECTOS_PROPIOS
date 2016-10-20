package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ReporteRECBoletaRecojoStatusForm extends BaseReporteForm implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String codigoPais;

	private String codigoPeriodoInicio;
	
	private String codigoPeriodoFin;

	private String[] regionList;

	private String[] zonaList;	
	
	private String[] gestionList;
	
	private String[] resultadoList;
	
	private String fechaProceso;
	private Date fechaProcesoD;
	
	
	public ReporteRECBoletaRecojoStatusForm(){
		   SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	       this.fechaProceso = sdf.format(new Date(System.currentTimeMillis()));
	       this.fechaProcesoD=new Date(System.currentTimeMillis());
	}
	
	public String getCodigoPais() {
		return codigoPais;
	}

	
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
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

	
	public String getFechaProceso() {
		return fechaProceso;
	}
	
	public void setFechaProceso(String fechaProceso) {
		this.fechaProceso = fechaProceso;
	}
	
	public String[] getGestionList() {
		return gestionList;
	}
	
	public void setGestionList(String[] gestionList) {
		this.gestionList = gestionList;
	}
	
	public String[] getResultadoList() {
		return resultadoList;
	}
	
	public void setResultadoList(String[] resultadoList) {
		this.resultadoList = resultadoList;
	}
	
	public String getCodigoPeriodoInicio() {
		return codigoPeriodoInicio;
	}
	
	public String getCodigoPeriodoFin() {
		return codigoPeriodoFin;
	}
	
	public void setCodigoPeriodoInicio(String codigoPeriodoInicio) {
		this.codigoPeriodoInicio = codigoPeriodoInicio;
	}
	
	public void setCodigoPeriodoFin(String codigoPeriodoFin) {
		this.codigoPeriodoFin = codigoPeriodoFin;
	}

	public Date getFechaProcesoD() {
		return fechaProcesoD;
	}

	public void setFechaProcesoD(Date fechaProcesoD) {
		this.fechaProcesoD = fechaProcesoD;
	}	
}
