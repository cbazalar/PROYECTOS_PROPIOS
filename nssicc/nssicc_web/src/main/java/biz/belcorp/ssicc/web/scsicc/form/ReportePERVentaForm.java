package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ReportePERVentaForm extends BaseReporteForm implements Serializable {
	

	private static final long serialVersionUID = 2250690059546688281L;
	
	private String codigoPais;
	private String codigoPeriodo;
	private String fechaDesde;
	private String fechaHasta;
	private Date fechaDesdeD;
	private Date fechaHastaD;
	
	public ReportePERVentaForm(){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String periodo = sdf.format(new Date(System.currentTimeMillis()));		
		
		if (StringUtils.isEmpty(this.codigoPeriodo))this.codigoPeriodo = periodo;
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



	public String getCodigoPais() {
		return codigoPais;
	}



	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	
	
	
}
