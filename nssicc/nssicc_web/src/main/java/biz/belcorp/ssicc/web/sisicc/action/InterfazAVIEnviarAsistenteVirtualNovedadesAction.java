package biz.belcorp.ssicc.web.sisicc.action;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazAVIEnviarAsistenteVirtualNovedadesForm;

/**

  */
@ManagedBean
@SessionScoped
public class InterfazAVIEnviarAsistenteVirtualNovedadesAction extends
		BaseInterfazAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4308621267567096192L;
	private boolean opcionPeriodo;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction
	 * #devuelveFormInterfaz()
	 */
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazAVIEnviarAsistenteVirtualNovedadesForm interfazDATEnviarAdministracionFlujosForm = new InterfazAVIEnviarAsistenteVirtualNovedadesForm();
		return interfazDATEnviarAdministracionFlujosForm;
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
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		HashMap criteria = new HashMap();
		InterfazAVIEnviarAsistenteVirtualNovedadesForm interfazForm = (InterfazAVIEnviarAsistenteVirtualNovedadesForm) this.formInterfaz;
		criteria.put("codigoPais", usuario.getPais().getCodigo());
		// por defecto MARCA TODAS
		// por defecto CANAL VENTA DIRECTA

		interfazForm.setCodigoPeriodo(interfazSiCCService
				.getPeriodoDefaultByPaisCanal(pais.getCodigo(),
						Constants.CODIGO_CANAL_DEFAULT));
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		criteria.put("codigoPeriodo", interfazForm.getCodigoPeriodo());
		criteria.put("campanias", Constants.PERIODO_SIGUIENTE);
		String desPeriodoSiguiente = reporteService.getOidString(
				"getDesPeriodoByCodigoPeriodoX", criteria);
		interfazForm.setCodigoNuevoPeriodo(desPeriodoSiguiente);
		setOpcionPeriodo(true);

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
		params.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		params.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
		params.put("enviarNovedades", Constants.YES);
		return params;
	}

	/**
	 * Metodo para validar nuevo periodo
	 * 
	 * @param val
	 */
	public void loadPeriodo(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadPeriodo");
		}
		String opcion = (String) val.getNewValue().toString();
	
		if (opcion == "true") {
			setOpcionPeriodo(false);
		}else{
			setOpcionPeriodo(true);
		}
	}

	/**
	 * @return the opcionPeriodo
	 */
	public boolean isOpcionPeriodo() {
		return opcionPeriodo;
	}

	/**
	 * @param opcionPeriodo
	 *            the opcionPeriodo to set
	 */
	public void setOpcionPeriodo(boolean opcionPeriodo) {
		this.opcionPeriodo = opcionPeriodo;
	}


	
	

}
