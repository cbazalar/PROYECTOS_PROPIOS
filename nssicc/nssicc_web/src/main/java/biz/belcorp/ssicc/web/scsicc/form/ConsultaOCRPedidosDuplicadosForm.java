package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;


/**
 * The Class ConsultaOCRPedidosDuplicadosForm.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 03/09/2014
 */
public class ConsultaOCRPedidosDuplicadosForm extends BaseReporteForm implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String codigoPais ;	
	private String periodo;
	private String fechaFact;
	private Date fechaFactDate;
	
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


	public String getCodigoPais() {
		return codigoPais;
	}


	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public Date getFechaFactDate() {
		return fechaFactDate;
	}

	public void setFechaFactDate(Date fechaFactDate) {
		this.fechaFactDate = fechaFactDate;
	}


	
}