package biz.belcorp.ssicc.web.spusicc.web.action;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.spusicc.web.form.InterfazFACRecepcionarDocumentoElectronicoForm;

@ManagedBean
@SessionScoped
public class InterfazFACRecepcionarDocumentoElectronicoAction extends BaseInterfazAbstractAction {

	private static final long serialVersionUID = 1L;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazFACRecepcionarDocumentoElectronicoForm form = new InterfazFACRecepcionarDocumentoElectronicoForm();
		return form;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
            log.debug("Entering 'setViewAttributes' method");
        }		
		this.validarListaArchivosEntrada = false;
	}
}