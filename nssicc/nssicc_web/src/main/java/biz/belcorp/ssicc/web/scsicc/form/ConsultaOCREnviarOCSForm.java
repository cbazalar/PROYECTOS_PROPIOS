package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;


/**
 * The Class ConsultaOCREnviarOCSForm.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 25/08/2014
 */
public class ConsultaOCREnviarOCSForm extends BaseReporteForm implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String codigoPais ;
	private String resumen ;
	private String periodo ;
	private String fechaFact ;
	
	public ConsultaOCREnviarOCSForm() {
		super();
	}
    

	public String getFechaFact() {
		return fechaFact;
	}

   
	public void setFechaFact(String fechaFact) {
		this.fechaFact = fechaFact;
	}


	public String getPeriodo() {
		return periodo;
	}


	public void setPeriodo(String periodo) {
		this.periodo = periodo;
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
	
}