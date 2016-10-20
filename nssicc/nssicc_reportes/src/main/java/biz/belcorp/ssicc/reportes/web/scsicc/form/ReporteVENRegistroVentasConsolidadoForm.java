package biz.belcorp.ssicc.reportes.web.scsicc.form;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ReporteVENRegistroVentasConsolidadoForm extends BaseReporteForm implements Serializable {

	
	private static final long serialVersionUID = -3255424346902556696L;
	

	private String codigoPais;
	private String fechaDesde;
	private String fechaHasta;
	
	private Date fechaInicioD;	
	private Date fechaFinD;
	

	public ReporteVENRegistroVentasConsolidadoForm(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		this.fechaDesde = sdf.format(new Date(System.currentTimeMillis()));
		this.fechaHasta = sdf.format(new Date(System.currentTimeMillis()));
	}
	
	
	public String getCodigoPais() {
		return codigoPais;
	}

	
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
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


	
	

}
