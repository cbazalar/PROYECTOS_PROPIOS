package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ReporteMAVPersonalVentasForm extends BaseReporteForm	implements Serializable{
	
	
	private static final long serialVersionUID = 7759686324684955762L;
	
	

	private String codigoPeriodo;

	private String codigoPais;

	private String[] codigoActividad;
	
	private String descripcionActividad;

	private String fechaReporte;
	
	private Date fechaReporteD;

	
	
	public String[] getCodigoActividad() {
		return codigoActividad;
	}
	
	public void setCodigoActividad(String[] codigoActividad) {
		this.codigoActividad = codigoActividad;
	}

	public String getFechaReporte() {
		return fechaReporte;
	}
	
	public void setFechaReporte(String fechaReporte) {
		this.fechaReporte = fechaReporte;
	}

	
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

	public String getDescripcionActividad() {
		return descripcionActividad;
	}

	
	public void setDescripcionActividad(String descripcionActividad) {
		String temp = StringUtils.replace(descripcionActividad, "&&","\n" );
		this.descripcionActividad = temp;
	}

	public Date getFechaReporteD() {
		return fechaReporteD;
	}

	public void setFechaReporteD(Date fechaReporteD) {
		this.fechaReporteD = fechaReporteD;
	}

}
