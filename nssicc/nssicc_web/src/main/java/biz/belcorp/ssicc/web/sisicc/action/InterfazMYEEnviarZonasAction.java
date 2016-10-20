/*
 * Created on 26/09/2006 11:32:06 AM
 * biz.belcorp.ssicc.sisicc.web.action.InterfazMYEEnviarMovimientosCuentaCorrienteAction
 */
package biz.belcorp.ssicc.web.sisicc.action;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazMYEEnviarZonasForm;


@ManagedBean
@SessionScoped
public class InterfazMYEEnviarZonasAction extends BaseInterfazAbstractAction {


	private static final long serialVersionUID = -3203189676172552747L;
	
	private List siccMarcaList;
	private List siccCanalList;

	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#devuelveFormInterfaz()
	 */
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		return new InterfazMYEEnviarZonasForm();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
	        log.debug("Entering 'setViewAttributes' method");
	    }
		 Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		 InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
	    
		 InterfazMYEEnviarZonasForm form = (InterfazMYEEnviarZonasForm)this.formInterfaz;
		 
	        // Cargamos las listas Canales y Marcas
		 siccMarcaList = svc.getMarcas();
		 siccCanalList = svc.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());
	   
		 form.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
	     form.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
	}

	/**
	 * @return the siccMarcaList
	 */
	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	/**
	 * @param siccMarcaList the siccMarcaList to set
	 */
	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
	}

	/**
	 * @return the siccCanalList
	 */
	public List getSiccCanalList() {
		return siccCanalList;
	}

	/**
	 * @param siccCanalList the siccCanalList to set
	 */
	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
	}

	
	

	
    

}
