package biz.belcorp.ssicc.web.sisicc.action;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazIVREnviarCompletoIVRForm;

/**

  */
@ManagedBean
@SessionScoped
public class InterfazIVREnviarCompletoIVRAction extends
		BaseInterfazAbstractAction {


	/**
	 * 
	 */
	private static final long serialVersionUID = -906155047019018075L;
	private List siccMarcaList;
	private List siccCanalList;
	private List siccAccesoList;
	private List siccAccesoTodosList;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction
	 * #devuelveFormInterfaz()
	 */
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazIVREnviarCompletoIVRForm form = new InterfazIVREnviarCompletoIVRForm();
		return form;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		// Carga de los combos de Marca, Canal, Accesos y Subaccesos
		InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		this.siccMarcaList = svc.getMarcas();
		this.siccCanalList = svc.getCanalesByCodigoISO(usuario.getIdioma()
				.getCodigoISO());

		InterfazIVREnviarCompletoIVRForm form = (InterfazIVREnviarCompletoIVRForm) this.formInterfaz;
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		form.setCodigoPeriodo(service.getPeriodoDefaultByPaisCanal(
				pais.getCodigo(), Constants.CODIGO_CANAL_DEFAULT));

		// ################### LISTA ACCESO ################

		this.siccAccesoTodosList = getAccesoList(Constants.CODIGO_CANAL_DEFAULT);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction
	 * #prepareParamsBeforeExecute(java.util.Map,
	 * biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm)
	 */
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params,
			BaseForm form) throws Exception {
		// TODO Auto-generated method stub
		params = super.prepareParamsBeforeExecute(params, form);
		params.put("indProceso", Constants.IVR_ENVIAR_COMPLETO);
		return params;
	}

	/*
	 * Método para cargar Acceso
	 */
	public void loadAcceso(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadAcceso");
		}
		InterfazIVREnviarCompletoIVRForm form = (InterfazIVREnviarCompletoIVRForm) this.formInterfaz;

		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		String valor = (String) val.getNewValue();
		if (valor == null) {
			this.siccAccesoTodosList = svc
					.getAccesosTodosByCanalByCodigoISO(usuario.getIdioma()
							.getCodigoISO());
			form.setCodigoAcceso(null);

		} else {

			this.siccAccesoTodosList = getAccesoList(valor);
		}

	}

	
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#beforeExecuteInterfaz(java.util.Map)
     */
    protected void beforeExecuteInterfaz(Map params) {
    	
    	super.beforeExecuteInterfaz(params);
    	 InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
    	 svc.deleteTablaControlIVR();
    	
    }
	
	/**
	 * @return the siccMarcaList
	 */
	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	/**
	 * @param siccMarcaList
	 *            the siccMarcaList to set
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
	 * @param siccCanalList
	 *            the siccCanalList to set
	 */
	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
	}

	/**
	 * @return the siccAccesoList
	 */
	public List getSiccAccesoList() {
		return siccAccesoList;
	}

	/**
	 * @param siccAccesoList
	 *            the siccAccesoList to set
	 */
	public void setSiccAccesoList(List siccAccesoList) {
		this.siccAccesoList = siccAccesoList;
	}

	/**
	 * @return the siccAccesoTodosList
	 */
	public List getSiccAccesoTodosList() {
		return siccAccesoTodosList;
	}

	/**
	 * @param siccAccesoTodosList
	 *            the siccAccesoTodosList to set
	 */
	public void setSiccAccesoTodosList(List siccAccesoTodosList) {
		this.siccAccesoTodosList = siccAccesoTodosList;
	}

}

