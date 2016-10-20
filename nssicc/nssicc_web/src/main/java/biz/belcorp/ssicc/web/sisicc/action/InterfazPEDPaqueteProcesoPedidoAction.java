package biz.belcorp.ssicc.web.sisicc.action;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazPEDPaqueteProcesoPedidoForm;

@SessionScoped
@ManagedBean
public class InterfazPEDPaqueteProcesoPedidoAction extends
		BaseInterfazAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1446776295684297513L;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		// TODO Auto-generated method stub
		InterfazPEDPaqueteProcesoPedidoForm form = new InterfazPEDPaqueteProcesoPedidoForm();
		return form;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
	}

	protected Map<String, Object> prepareParamsBeforeExecute(Map params,
			BaseForm form) throws Exception {
		params = super.prepareParamsBeforeExecute(params, form);
		
		InterfazPEDPaqueteProcesoPedidoForm f = (InterfazPEDPaqueteProcesoPedidoForm) this.formInterfaz;
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		Map criteriaPeriodo = new HashMap();
		criteriaPeriodo.put("codigoPais", pais.getCodigo());
		criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO); // Indica Campanha Activa
		criteriaPeriodo.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente

		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		
		PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteriaPeriodo);
		
		params.put("codigoUsuario", usuario.getLogin());
		params.put("pais",pais);
		params.put("codigoPais", pais.getCodigo());
		params.put("usuario", usuario);
		params.put("codigoPeriodo", controlFacturacion.getCodigoPeriodo());
		params.put("fechaFacturacion", controlFacturacion.getFechaProceso());
					
		return params;
	}

}
