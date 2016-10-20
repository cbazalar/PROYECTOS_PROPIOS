package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;


/**
 * The Class ReporteOCRMasVeinteUnidadesForm.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 26/08/2014
 */
public class ReporteOCRMasVeinteUnidadesForm extends BaseReporteForm implements	Serializable {

	private static final long serialVersionUID = 1L;

	private String codigoPais;

	private String resumen;

	private String codigoPeriodo;

	private String fechaFact;

	private String numeroUnidades;
	
	private String tipoConsulta;

	public String getFechaFact() {
		return fechaFact;
	}

	public void setFechaFact(String fechaFact) {
		this.fechaFact = fechaFact;
	}

	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	public String getResumen() {
		return resumen;
	}

	public void setResumen(String resumen) {
		this.resumen = resumen;
	}

	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public String getNumeroUnidades() {
		return numeroUnidades;
	}

	public void setNumeroUnidades(String numeroUnidades) {
		this.numeroUnidades = numeroUnidades;
	}

	public String getTipoConsulta() {
		return tipoConsulta;
	}

	public void setTipoConsulta(String tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}

}