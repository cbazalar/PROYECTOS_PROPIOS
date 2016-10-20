package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;


// TODO: Auto-generated Javadoc
/**
 * The Class ReporteSACActualizacionDireccionesForm.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 17/09/2014
 */
public class ReporteSACActualizacionDireccionesForm extends BaseReporteForm implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The codigo pais. */
	private String codigoPais;

	/** The codigo periodo. */
	private String codigoPeriodo;

	/** The fecha fact. */
	private String fechaFact;
	
	/** The fecha fact date. */
	private Date fechaFactDate;

	public String getFechaFact() {
		return fechaFact;
	}

	/**
	 * @param fechaFact            	
	 */
	public void setFechaFact(String fechaFact) {
		this.fechaFact = fechaFact;
	}

	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**	 	
	 * @param codigoPeriodo	 
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	public String getCodigoPais() {
		return codigoPais;
	}
	
	/**
	 * @param codigoPais The codigoPais to set.
	 */
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