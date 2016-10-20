package biz.belcorp.ssicc.reportes.web.scsicc.form;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;


public class ReporteCCCCuadreSAPFIForm extends BaseReporteForm implements Serializable{
	
	
	private static final long serialVersionUID = -5120079151219254527L;
	
	private String codigoPais;
	private String fechaProcDesde;
	private String fechaProcHasta;
	
	private Date fechaProcDesdeD;
	private Date fechaProcHastaD;

	
	public ReporteCCCCuadreSAPFIForm(){
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		this.fechaProcDesde = sdf.format(new Date(System.currentTimeMillis()));
		this.fechaProcHasta = sdf.format(new Date(System.currentTimeMillis()));
		this.fechaProcDesdeD = new Date(System.currentTimeMillis());
		this.fechaProcHastaD = new Date(System.currentTimeMillis());
	}
	
	public String getCodigoPais() {
		return codigoPais;
	}

	
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	
	public String getFechaProcDesde() {
		return fechaProcDesde;
	}

	
	public void setFechaProcDesde(String fechaProcDesde) {
		this.fechaProcDesde = fechaProcDesde;
	}

	
	public String getFechaProcHasta() {
		return fechaProcHasta;
	}

	
	public void setFechaProcHasta(String fechaProcHasta) {
		this.fechaProcHasta = fechaProcHasta;
	}

	public Date getFechaProcDesdeD() {
		return fechaProcDesdeD;
	}

	public void setFechaProcDesdeD(Date fechaProcDesdeD) {
		this.fechaProcDesdeD = fechaProcDesdeD;
	}

	public Date getFechaProcHastaD() {
		return fechaProcHastaD;
	}

	public void setFechaProcHastaD(Date fechaProcHastaD) {
		this.fechaProcHastaD = fechaProcHastaD;
	}

	
}
