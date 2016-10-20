package biz.belcorp.ssicc.web.spusicc.mae.form;

import java.io.Serializable;

import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

public class ProcesoMAECalificacionEstatusCargaInicialForm extends
		BaseProcesoForm implements Serializable {

	private static final long serialVersionUID = 1L;

	private boolean cargaRealizada;

	

	/**
	 * @return Returns the cargaRealizada.
	 */
	public boolean isCargaRealizada() {
		return cargaRealizada;
	}

	/**
	 * @param cargaRealizada
	 *            The cargaRealizada to set.
	 */
	public void setCargaRealizada(boolean cargaRealizada) {
		this.cargaRealizada = cargaRealizada;
	}

}