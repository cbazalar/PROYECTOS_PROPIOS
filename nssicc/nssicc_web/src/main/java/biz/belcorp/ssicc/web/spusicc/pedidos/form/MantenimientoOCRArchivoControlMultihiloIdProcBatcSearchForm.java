package biz.belcorp.ssicc.web.spusicc.pedidos.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;


public class MantenimientoOCRArchivoControlMultihiloIdProcBatcSearchForm extends BaseSearchForm implements Serializable{
	
	private static final long serialVersionUID = 621602507708562234L;
	
	
	private String idProcBatc;

	/**
	 * @return the idProcBatc
	 */
	public String getIdProcBatc() {
		return idProcBatc;
	}

	/**
	 * @param idProcBatc the idProcBatc to set
	 * @struts.validator type="required"
	 */
	public void setIdProcBatc(String idProcBatc) {
		this.idProcBatc = idProcBatc;
	}

}
