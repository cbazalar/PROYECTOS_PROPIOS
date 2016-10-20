package biz.belcorp.ssicc.web.sisicc.action;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.sisicc.InterfazDATService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazSGREnviarInformacionPolizaForm;

/**

  */
@ManagedBean
@SessionScoped
public class InterfazSGREnviarInformacionPolizaAction extends
		BaseInterfazAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6689677978184687072L;
	private boolean opcionPeriodoFecha;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction
	 * #devuelveFormInterfaz()
	 */
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazSGREnviarInformacionPolizaForm interfazDATEnviarAdministracionFlujosForm = new InterfazSGREnviarInformacionPolizaForm();
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
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		// Carga de los combos de Marca, Canal, Accesos y Subaccesos

		InterfazSGREnviarInformacionPolizaForm interfazDATForm = (InterfazSGREnviarInformacionPolizaForm) this.formInterfaz;

		MantenimientoOCRPedidoControlFacturacionService serviceCF = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = null;

		Map criteriaPeriodo = new HashMap();

		criteriaPeriodo.put("codigoPais", pais.getCodigo());
		criteriaPeriodo
				.put("estadoCampanha", Constants.DAT_PARAM_STA_CAMP_CERO);
		criteriaPeriodo.put("indicadorActiva",
				Constants.DAT_PARAM_IND_CAMP_ACT_UNO);

		controlFacturacion = serviceCF
				.getControlFacturacionById(criteriaPeriodo);



		interfazDATForm.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		interfazDATForm.setFechaFacturacionD(DateUtil.convertStringToDate(controlFacturacion
				.getFechaProceso()));


		setOpcionPeriodoFecha(true);

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
		InterfazSGREnviarInformacionPolizaForm f = (InterfazSGREnviarInformacionPolizaForm) this.formInterfaz;
		f.setFechaFacturacion(DateUtil.convertDateToString(f
				.getFechaFacturacionD()));
		params = super.prepareParamsBeforeExecute(params, form);
		return params;
	}
	
	/* (non-Javadoc)
     * @see biz.belcorp.ssicc.sisicc.web.framework.action.BaseInterfazAbstractAction#beforeExecuteInterfaz(java.util.Map)
     */
    protected void beforeExecuteInterfaz(Map params) {
    	
    	super.beforeExecuteInterfaz(params);
    	log.debug("Entering 'before' method");
    	InterfazDATService svc = (InterfazDATService) getBean("sisicc.interfazDATService");
    	
    	svc.executeCargarTablaInterfaz(params);
    	
    }

	/**
	 * Metodo para validar nuevo periodo
	 * 
	 * @param val
	 */
	public void loadPeriodoFecha(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadPeriodoFecha");
		}
		String opcion = (String) val.getNewValue().toString();

		if (opcion == "true") {
			setOpcionPeriodoFecha(false);
		} else {
			setOpcionPeriodoFecha(true);
		}
	}

	/**
	 * @return the opcionPeriodoFecha
	 */
	public boolean isOpcionPeriodoFecha() {
		return opcionPeriodoFecha;
	}

	/**
	 * @param opcionPeriodoFecha
	 *            the opcionPeriodoFecha to set
	 */
	public void setOpcionPeriodoFecha(boolean opcionPeriodoFecha) {
		this.opcionPeriodoFecha = opcionPeriodoFecha;
	}

}
