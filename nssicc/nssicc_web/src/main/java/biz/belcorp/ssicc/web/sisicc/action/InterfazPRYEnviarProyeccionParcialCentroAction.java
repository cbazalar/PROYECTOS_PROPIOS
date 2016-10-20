package biz.belcorp.ssicc.web.sisicc.action;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazPRYEnviarProyeccionParcialCentroForm;


/**
 * The Class InterfazPRYEnviarProyeccionParcialCentroAction.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 28/11/2014
 */
@SuppressWarnings("rawtypes")
@ManagedBean
@SessionScoped
public class InterfazPRYEnviarProyeccionParcialCentroAction extends BaseInterfazAbstractAction {

	private static final long serialVersionUID = -4268755588245834241L;

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#devuelveFormInterfaz()
	 */
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazPRYEnviarProyeccionParcialCentroForm interfazPRYEnviar = new InterfazPRYEnviarProyeccionParcialCentroForm();
		return interfazPRYEnviar;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}

		InterfazPRYEnviarProyeccionParcialCentroForm f = (InterfazPRYEnviarProyeccionParcialCentroForm) this.formInterfaz;
		PedidoControlFacturacion controlFacturacion = null;
		MantenimientoOCRPedidoControlFacturacionService serviceCF = 
        	(MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Map criteriaPeriodo = new HashMap();

        criteriaPeriodo.put("codigoPais", pais.getCodigo());
        criteriaPeriodo.put("estadoCampanha", Constants.DAT_PARAM_STA_CAMP_CERO);
        criteriaPeriodo.put("indicadorActiva", Constants.DAT_PARAM_IND_CAMP_ACT_UNO);

        controlFacturacion = serviceCF.getControlFacturacionById(criteriaPeriodo);
        f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		f.setFechaFacturacion(controlFacturacion.getFechaProceso());
		f.setFechaFacturacionDate(DateUtil.stringToCalender(f.getFechaFacturacion(), "dd/MM/yyyy").getTime());		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#prepareParamsBeforeExecute(java.util.Map, biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception {

		InterfazPRYEnviarProyeccionParcialCentroForm f = (InterfazPRYEnviarProyeccionParcialCentroForm) this.formInterfaz;
		f.setFechaFacturacion(DateUtil.convertDateToString(f.getFechaFacturacionDate()));
		params =  super.prepareParamsBeforeExecute(params, form);
		return params;
	}
	
}