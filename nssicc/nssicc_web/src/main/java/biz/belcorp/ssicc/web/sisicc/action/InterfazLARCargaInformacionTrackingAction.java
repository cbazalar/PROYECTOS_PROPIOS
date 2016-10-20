package biz.belcorp.ssicc.web.sisicc.action;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazLARCargaInformacionTrackingForm;

@ManagedBean
@SessionScoped
public class InterfazLARCargaInformacionTrackingAction extends BaseInterfazAbstractAction
{
	private static final long serialVersionUID = 479271363744801664L;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazLARCargaInformacionTrackingForm formInterfaz = new InterfazLARCargaInformacionTrackingForm();
		return formInterfaz;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		
		
	}
	
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params,BaseForm form) throws Exception 
	{
		Map queryParams = super.prepareParamsBeforeExecute(params, form);
		Map criteriaPeriodo = new HashMap();
		criteriaPeriodo.put("codigoPais", this.mPantallaPrincipalBean.getCurrentCountry().getCodigo());

		// Indica Campanha Activa
		criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO);
		criteriaPeriodo.put("indicadorActiva", Constants.ESTADO_ACTIVO);
		
		MantenimientoOCRPedidoControlFacturacionService controlFactService = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = controlFactService.getControlFacturacionById(criteriaPeriodo);
		
		String fechaProceso = controlFacturacion.getFechaProceso();
		String codigoPais = this.mPantallaPrincipalBean.getCurrentCountry().getCodigo();
		String codigoUsuario = this.mPantallaPrincipalBean.getCurrentUser().getLogin();
		queryParams.put("fechaProceso", fechaProceso);
		queryParams.put("codigoPais", codigoPais);
		queryParams.put("codigoUsuario", codigoUsuario);

		return queryParams;		
	}

}
