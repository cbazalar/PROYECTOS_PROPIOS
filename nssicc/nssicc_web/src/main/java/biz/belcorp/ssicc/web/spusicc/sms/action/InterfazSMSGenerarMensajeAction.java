package biz.belcorp.ssicc.web.spusicc.sms.action;

import java.util.HashMap;
import java.util.List;
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
import biz.belcorp.ssicc.web.spusicc.sms.form.InterfazSMSGenerarMensajeForm;

@ManagedBean
@SessionScoped
public class InterfazSMSGenerarMensajeAction  extends BaseInterfazAbstractAction{

	private static final long serialVersionUID = -1515064982421602260L;
	
	private List interfazSMSGenerarMensajeList;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#devuelveFormInterfaz()
	 */
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {		
		InterfazSMSGenerarMensajeForm objForm = new InterfazSMSGenerarMensajeForm();
		return objForm;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}
		
		InterfazSMSGenerarMensajeForm objForm =  (InterfazSMSGenerarMensajeForm)this.formInterfaz;
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		objForm.setCodigoPais(pais.getCodigo()); 
		
		Map criteriaPeriodo = new HashMap();
		criteriaPeriodo.put("codigoPais", pais.getCodigo());
		criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO); // Indica Campanha Activa
		criteriaPeriodo.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente

		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		
		PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteriaPeriodo);

		objForm.setPeriodo(controlFacturacion.getCodigoPeriodo());
		objForm.setFechaFacturacionD(DateUtil.convertStringToDate(controlFacturacion.getFechaProceso()));
		//objForm.setFechaFacturacion(controlFacturacion.getFechaProceso());
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#prepareParamsBeforeExecute(java.util.Map, biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm)
	 */
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#prepareParamsBeforeExecute(java.util.Map, biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm)
	 */
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params,
			BaseForm form) throws Exception {
		InterfazSMSGenerarMensajeForm objForm =  (InterfazSMSGenerarMensajeForm)this.formInterfaz;
		String fechaFac = DateUtil.convertDateToString(objForm.getFechaFacturacionD());
		objForm.setCodigoPeriodo(objForm.getPeriodo());
		objForm.setFechaFacturacion(fechaFac);
		params =  super.prepareParamsBeforeExecute(params, form);
		
		return params;

	}

	/**
	 * @return the interfazSMSGenerarMensajeList
	 */
	public List getInterfazSMSGenerarMensajeList() {
		return interfazSMSGenerarMensajeList;
	}

	/**
	 * @param interfazSMSGenerarMensajeList the interfazSMSGenerarMensajeList to set
	 */
	public void setInterfazSMSGenerarMensajeList(List interfazSMSGenerarMensajeList) {
		this.interfazSMSGenerarMensajeList = interfazSMSGenerarMensajeList;
	}	
}
