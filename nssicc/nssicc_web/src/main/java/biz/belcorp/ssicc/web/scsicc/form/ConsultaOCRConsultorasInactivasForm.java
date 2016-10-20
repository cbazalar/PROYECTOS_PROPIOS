package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;


/**
 * The Class ConsultaOCRConsultorasInactivasForm.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 27/08/2014
 */
public class ConsultaOCRConsultorasInactivasForm extends BaseReporteForm implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codigoPais;

	private String periodo;
	
	private Date fechaInicioDate;
	
	private Date fechaFinDate;

	private String fechaInicio;

	private String fechaFin;

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}


	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaInicioDate() {
		return fechaInicioDate;
	}

	public void setFechaInicioDate(Date fechaInicioDate) {
		this.fechaInicioDate = fechaInicioDate;
	}

	public Date getFechaFinDate() {
		return fechaFinDate;
	}

	public void setFechaFinDate(Date fechaFinDate) {
		this.fechaFinDate = fechaFinDate;
	}

}