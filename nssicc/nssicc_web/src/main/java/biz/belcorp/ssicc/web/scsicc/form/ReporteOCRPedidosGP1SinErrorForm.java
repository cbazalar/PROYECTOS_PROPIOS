/*
 * 
 */
package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

// TODO: Auto-generated Javadoc
/**
 * Clase que controla los request/response de los jsp llevando los datos al ActionController relacionado
 * 
 * <p>
 * <a href="ReporteOCRPedidosGP1SinErrorForm.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:cdiaz@csigcomt.com"> Carlos Diaz Valverde </a>
 * 
 */

public class ReporteOCRPedidosGP1SinErrorForm extends BaseReporteForm  implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The codigo pais. */
	private String codigoPais;
	
	/** The fecha inicial date. */
	private Date fechaInicialDate;
	
	/** The fecha final date. */
	private Date fechaFinalDate;
	
	/** The fecha inicial. */
	private String fechaInicial;
	
	/** The fecha final. */
	private String fechaFinal;
	
	/**
	 * Returns the codigoPais.
	 *
	 * @return the codigo pais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	
	/**
	 * The codigoPais to set.
	 *
	 * @param codigoPais the new codigo pais
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	
	/**
	 * Returns the fechaFinal.
	 *
	 * @return the fecha final
	 */
	public String getFechaFinal() {
		return fechaFinal;
	}

	/**
	 * The fechaFinal to set.
	 *
	 * @param fechaFinal the new fecha final
	 */
	public void setFechaFinal(String fechaFinal) {
		this.fechaFinal = fechaFinal;
	}
	
	/**
	 * Returns the fechaInicial.
	 *
	 * @return the fecha inicial
	 */
	public String getFechaInicial() {
		return fechaInicial;
	}
	
	/**
	 * The fechaInicial to set.
	 *
	 * @param fechaInicial the new fecha inicial
	 */
	public void setFechaInicial(String fechaInicial) {
		this.fechaInicial = fechaInicial;
	}
	
	/**
	 * Gets the fecha inicial date.
	 *
	 * @return the fecha inicial date
	 */
	public Date getFechaInicialDate() {
		return fechaInicialDate;
	}
	
	/**
	 * Sets the fecha inicial date.
	 *
	 * @param fechaInicialDate the new fecha inicial date
	 */
	public void setFechaInicialDate(Date fechaInicialDate) {
		this.fechaInicialDate = fechaInicialDate;
	}
	
	/**
	 * Gets the fecha final date.
	 *
	 * @return the fecha final date
	 */
	public Date getFechaFinalDate() {
		return fechaFinalDate;
	}
	
	/**
	 * Sets the fecha final date.
	 *
	 * @param fechaFinalDate the new fecha final date
	 */
	public void setFechaFinalDate(Date fechaFinalDate) {
		this.fechaFinalDate = fechaFinalDate;
	}
}
