
package biz.belcorp.ssicc.web.sisicc.action;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazPEREnviarRecaudosBancariosForm;


@ManagedBean
@SessionScoped
public class InterfazPEREnviarRecaudosBancariosAction extends BaseInterfazAbstractAction {

 
	private static final long serialVersionUID = -3203189676172552747L;

	private List siccTipoOrigenDatosList;
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#devuelveFormInterfaz()
	 */
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		return new InterfazPEREnviarRecaudosBancariosForm();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
	        log.debug("Entering 'setViewAttributes' method");
	    }
		//Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		// Carga del combo de Tipo de Origen de Datos
		siccTipoOrigenDatosList = interfazSiCCService.getTipoOrigenDatosRecaudosBancarios();
	
	}

	/**
	 * @return the siccTipoOrigenDatosList
	 */
	public List getSiccTipoOrigenDatosList() {
		return siccTipoOrigenDatosList;
	}

	/**
	 * @param siccTipoOrigenDatosList the siccTipoOrigenDatosList to set
	 */
	public void setSiccTipoOrigenDatosList(List siccTipoOrigenDatosList) {
		this.siccTipoOrigenDatosList = siccTipoOrigenDatosList;
	}

	
	
	

	
    

}
