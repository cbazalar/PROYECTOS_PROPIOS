package biz.belcorp.ssicc.web.spncd.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

public class ProcesoCUPGenerarCCCForm extends BaseProcesoForm implements
		Serializable {

	private static final long serialVersionUID = 1L;
	private String codigoPrograma;
	private String periodo;
	private Date fechaFactD;
	private String fechaFact;

	public String getFechaFact() {
		return fechaFact;
	}

	/**
	 * @param fechaFact
	 *            The fechaFac to set.
	 * @param fechaFact
	 */

	public void setFechaFact(String fechaFact) {
		this.fechaFact = fechaFact;
	}

	public String getPeriodo() {
		return periodo;
	}

	/**
	 */

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	// public void reset(ActionMapping mapping, HttpServletRequest request) {
	// // TODO Auto-generated method stub
	// SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	// this.fechaFact = sdf.format(new Date(System.currentTimeMillis()));
	// }
	public String getCodigoPrograma() {
		return codigoPrograma;
	}

	public void setCodigoPrograma(String codigoPrograma) {
		this.codigoPrograma = codigoPrograma;
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