package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * Form de la recepci�n de archivos OCR para la Interfaz EVI.
 * 
 * @author <a href="mailto:lshimokawa@belcorp.biz">Lennon Shimokawa</a>
 * 
 * @struts.form name = "interfazEVIRecepcionarArchivosOCRForm" extends =
 *              "baseInterfazPaqueteForm"
 */
public class InterfazEVIRecepcionarArchivosOCRForm extends
		BaseInterfazForm implements Serializable {
	private String periodo;

	private String periodoCruce;

	private boolean cruceCampanya;

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public String getPeriodoCruce() {
		return periodoCruce;
	}

	public void setPeriodoCruce(String periodoCruce) {
		this.periodoCruce = periodoCruce;
	}

	public boolean isCruceCampanya() {
		return cruceCampanya;
	}

	public void setCruceCampanya(boolean cruceCampanya) {
		this.cruceCampanya = cruceCampanya;
	}
}