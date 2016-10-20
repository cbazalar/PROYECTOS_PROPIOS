package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * @author Jose Pulido
 * @company Sigcomt
 * 
 */
public class ReporteCOBComisionAbogadosForm extends BaseReporteForm implements
		Serializable {

	private static final long serialVersionUID = 1L;

	private String fechaInicial;
	private String fechaFinal;
	private Date fechaInicialD;
	private Date fechaFinalD;

	/**
	 * Gets the fecha inicial.
	 * 
	 * @return the fecha inicial
	 */
	public String getFechaInicial() {
		return fechaInicial;
	}

	/**
	 * Sets the fecha inicial.
	 * 
	 * @param fechaInicial
	 *            the new fecha inicial
	 */
	public void setFechaInicial(String fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	/**
	 * Gets the fecha final.
	 * 
	 * @return the fecha final
	 */
	public String getFechaFinal() {
		return fechaFinal;
	}

	/**
	 * Sets the fecha final.
	 * 
	 * @param fechaFinal
	 *            the new fecha final
	 */
	public void setFechaFinal(String fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	/**
	 * @return the fechaInicialD
	 */
	public Date getFechaInicialD() {
		return fechaInicialD;
	}

	/**
	 * @param fechaInicialD
	 *            the fechaInicialD to set
	 */
	public void setFechaInicialD(Date fechaInicialD) {
		this.fechaInicialD = fechaInicialD;
	}

	/**
	 * @return the fechaFinalD
	 */
	public Date getFechaFinalD() {
		return fechaFinalD;
	}

	/**
	 * @param fechaFinalD
	 *            the fechaFinalD to set
	 */
	public void setFechaFinalD(Date fechaFinalD) {
		this.fechaFinalD = fechaFinalD;
	}
}