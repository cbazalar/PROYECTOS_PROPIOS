package biz.belcorp.ssicc.web.spncd.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

public class ProcesoCUPDespachoProductoForm extends BaseProcesoForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7834662815405609710L;
	private String codigoPais ;
	private String codigoPrograma;	
	private String periodo;
	private String fechaFact;
	private Date fechaFactD;
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	/**
	 * @return the codigoPrograma
	 */
	public String getCodigoPrograma() {
		return codigoPrograma;
	}
	/**
	 * @param codigoPrograma the codigoPrograma to set
	 */
	public void setCodigoPrograma(String codigoPrograma) {
		this.codigoPrograma = codigoPrograma;
	}
	/**
	 * @return the periodo
	 */
	public String getPeriodo() {
		return periodo;
	}
	/**
	 * @param periodo the periodo to set
	 */
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	/**
	 * @return the fechaFact
	 */
	public String getFechaFact() {
		return fechaFact;
	}
	/**
	 * @param fechaFact the fechaFact to set
	 */
	public void setFechaFact(String fechaFact) {
		this.fechaFact = fechaFact;
	}
	/**
	 * @return the fechaFactD
	 */
	public Date getFechaFactD() {
		return fechaFactD;
	}
	/**
	 * @param fechaFactD the fechaFactD to set
	 */
	public void setFechaFactD(Date fechaFactD) {
		this.fechaFactD = fechaFactD;
	}

	
	

}
