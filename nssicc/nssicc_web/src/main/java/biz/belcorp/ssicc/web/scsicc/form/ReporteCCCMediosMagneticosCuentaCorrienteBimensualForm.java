package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;

import java.text.SimpleDateFormat;
import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
/**
 *
 * @author <a href="mailto:dsantacruz@sigcomt.com">Danny Santa Cruz Rojas</a>
 *
 */

public class ReporteCCCMediosMagneticosCuentaCorrienteBimensualForm extends
BaseReporteForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1536550587474154581L;
	private String fechaInicial;
	private String fechaFinal;
	private Date fechaFinalD;
	private Date fechaInicialD;

	

	public Date getFechaFinalD() {
		return fechaFinalD;
	}

	public void setFechaFinalD(Date fechaFinalD) {
		this.fechaFinalD = fechaFinalD;
	}

	public Date getFechaInicialD() {
		return fechaInicialD;
	}

	public void setFechaInicialD(Date fechaInicialD) {
		this.fechaInicialD = fechaInicialD;
	}

	public void reset() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		this.fechaInicial = sdf.format(new Date(System.currentTimeMillis()));
		this.fechaFinal = sdf.format(new Date(System.currentTimeMillis()));
	}

	/**
	 * @return the fechaInicial
	 */
	public String getFechaInicial() {
		return fechaInicial;
	}
	
	/**
	 * @param fechaInicial
	 *            The fechaInicial to set.
	 * @param fechaInicial
	 *            New value of property fechaInicial.
	 */
	
	public void setFechaInicial(String fechaInicial) {
		this.fechaInicial = fechaInicial;
	}
	

	/**
	 * @return the fechaFinal
	 */
	public String getFechaFinal() {
		return fechaFinal;
	}
	
	/**
	 * @param fechaFinal
	 *            The fechaFinal to set.
	 * @param fechaFinal
	 *            New value of property fechaFinal.
	 */
	
	public void setFechaFinal(String fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	

}
