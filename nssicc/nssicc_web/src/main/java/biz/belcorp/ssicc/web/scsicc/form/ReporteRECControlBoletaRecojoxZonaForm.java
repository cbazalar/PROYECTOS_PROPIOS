package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ReporteRECControlBoletaRecojoxZonaForm extends BaseReporteForm implements Serializable{
	

	private static final long serialVersionUID = -4509145145650815339L;

	private String codigoPais;

	private String[] regionList;

	private String[] resultadoList;	
	
	private String[] zonaList;
	
	private String[] recojoList;	
	
	private String fechaInicial;	
	private String fechaFinal;
	private Date fechaInicialD;
	private Date fechaFinalD;
	
	
	public ReporteRECControlBoletaRecojoxZonaForm(){
		 	
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	        this.fechaInicial = sdf.format(new Date(System.currentTimeMillis()));
	        this.fechaFinal = sdf.format(new Date(System.currentTimeMillis()));
	        this.fechaInicialD=new Date(System.currentTimeMillis());
	        this.fechaFinalD=new Date(System.currentTimeMillis());	        
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
	
	public String[] getRecojoList() {
		return recojoList;
	}
	
	public void setRecojoList(String[] recojoList) {
		this.recojoList = recojoList;
	}
	
	public String[] getResultadoList() {
		return resultadoList;
	}
	
	public void setResultadoList(String[] resultadoList) {
		this.resultadoList = resultadoList;
	}
	
	public String getFechaFinal() {
		return fechaFinal;
	}
	
	public void setFechaFinal(String fechaFinal) {
		this.fechaFinal = fechaFinal;
	}
	
	public String getFechaInicial() {
		return fechaInicial;
	}
	
	public void setFechaInicial(String fechaInicial) {
		this.fechaInicial = fechaInicial;
	}
	
	public String[] getZonaList() {
		return zonaList;
	}
	
	public void setZonaList(String[] zonaList) {
		this.zonaList = zonaList;
	}

	public Date getFechaInicialD() {
		return fechaInicialD;
	}

	public void setFechaInicialD(Date fechaInicialD) {
		this.fechaInicialD = fechaInicialD;
	}

	public Date getFechaFinalD() {
		return fechaFinalD;
	}

	public void setFechaFinalD(Date fechaFinalD) {
		this.fechaFinalD = fechaFinalD;
	}	
	
	
}
