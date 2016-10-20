package biz.belcorp.ssicc.web.sisicc.action;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.sisicc.InterfazIVRService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazIVREnviarNovedadIVRCorporativoForm;

@ManagedBean
@SessionScoped
public class InterfazIVREnviarNovedadIVRCorporativoAction extends BaseInterfazAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8897700575633968298L;
	private List siccMarcaList;
	private List siccCanalList;
	private List siccAccesoList;
	private List siccAccesoListTodos;
	private List siccSubAccesoList;
	private List siccAccesoTodosList;
	
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		// TODO Auto-generated method stub
		InterfazIVREnviarNovedadIVRCorporativoForm form=new InterfazIVREnviarNovedadIVRCorporativoForm();
		return form;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		if (log.isDebugEnabled()) {
		    log.debug("Entering 'setViewAttributes' method");
		}
		InterfazIVREnviarNovedadIVRCorporativoForm form=(InterfazIVREnviarNovedadIVRCorporativoForm) this.formInterfaz;
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		form.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		form.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
		form.setCodigoAcceso(Constants.CODIGO_ACCESO_DEFAULT);

		// Carga de los combos de Marca, Canal, Accesos y Subaccesos
		InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		this.siccMarcaList=svc.getMarcas();
		this.siccCanalList=svc.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());
		this.siccAccesoList=svc.getAccesosByCanalByCodigoISO(usuario.getIdioma().getCodigoISO());
		this.siccAccesoListTodos=svc.getAccesosTodosByCanalByCodigoISO(usuario.getIdioma().getCodigoISO());
		this.siccSubAccesoList=svc.getSubaccesosByCodigoISO(usuario.getIdioma().getCodigoISO());
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		form.setCodigoPeriodo(service.getPeriodoDefaultByPaisCanal(usuario.getPais().getCodigo(),Constants.CODIGO_CANAL_DEFAULT));
		
		this.siccAccesoTodosList = getAccesoList(Constants.CODIGO_CANAL_DEFAULT);

	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#prepareParamsBeforeExecute(java.util.Map, biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm)
	 */
	protected Map<String, Object> prepareParamsBeforeExecute (Map params, BaseForm form)
	throws Exception{
		    			
		params =  super.prepareParamsBeforeExecute(params, form);
		params.put("indProceso", Constants.IVR_ENVIAR_NOVEDAD);		
		return params;		
	}
	
	/* (non-Javadoc)
     * @see biz.belcorp.ssicc.sisicc.web.framework.action.BaseInterfazAbstractAction#beforeExecuteInterfaz(java.util.Map)
     */
    protected void beforeExecuteInterfaz(Map params) {
    	
    	super.beforeExecuteInterfaz(params);
    	 InterfazIVRService svc = (InterfazIVRService) getBean("sisicc.interfazIVRService");
    	 svc.deleteTablaControlIVRCorporativo();
    	
    }
	
	/*
	 * Método para cargar Acceso
	 */
	public void loadAcceso(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadAcceso");
		}
		InterfazIVREnviarNovedadIVRCorporativoForm interfazDATForm = (InterfazIVREnviarNovedadIVRCorporativoForm) this.formInterfaz;

		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		String valor = (String) val.getNewValue();
		if (valor == null) {
			this.siccAccesoTodosList = svc.getAccesosTodosByCanalByCodigoISO(usuario.getIdioma().getCodigoISO());
			interfazDATForm.setCodigoAcceso(null);

		} else {

			this.siccAccesoTodosList = getAccesoList(valor);
		}

	}

	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
	}

	public List getSiccCanalList() {
		return siccCanalList;
	}

	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
	}

	public List getSiccAccesoList() {
		return siccAccesoList;
	}

	public void setSiccAccesoList(List siccAccesoList) {
		this.siccAccesoList = siccAccesoList;
	}

	public List getSiccAccesoListTodos() {
		return siccAccesoListTodos;
	}

	public void setSiccAccesoListTodos(List siccAccesoListTodos) {
		this.siccAccesoListTodos = siccAccesoListTodos;
	}

	public List getSiccSubAccesoList() {
		return siccSubAccesoList;
	}

	public void setSiccSubAccesoList(List siccSubAccesoList) {
		this.siccSubAccesoList = siccSubAccesoList;
	}

	public List getSiccAccesoTodosList() {
		return siccAccesoTodosList;
	}

	public void setSiccAccesoTodosList(List siccAccesoTodosList) {
		this.siccAccesoTodosList = siccAccesoTodosList;
	}
	
	

}
