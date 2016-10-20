package biz.belcorp.ssicc.reportes.web.scsicc.form;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;


public class ReporteINCCuponesElectronicosForm extends BaseReporteForm implements Serializable{
	
	
	private static final long serialVersionUID = -5718482969391515760L;

	private String codigoPais;
	
	private String[] regionList;

	private String[] zonaList;
	
	private String descripcionRegionList;

	private String descripcionZonaList;
	
	private String numeroConcurso;
	
	private String nombreConcurso;

	
	public ReporteINCCuponesElectronicosForm(){
		this.regionList=null;
		this.zonaList=null;
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

	
	public String getNumeroConcurso() {
		return numeroConcurso;
	}

	
	public void setNumeroConcurso(String numeroConcurso) {
		this.numeroConcurso = numeroConcurso;
	}

	
	public String getNombreConcurso() {
		return nombreConcurso;
	}

	public void setNombreConcurso(String nombreConcurso) {
		this.nombreConcurso = nombreConcurso;
	}
}
