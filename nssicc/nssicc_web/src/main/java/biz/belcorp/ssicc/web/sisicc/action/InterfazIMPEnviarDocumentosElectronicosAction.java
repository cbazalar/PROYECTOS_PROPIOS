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
import biz.belcorp.ssicc.web.sisicc.form.InterfazIMPEnviarDocumentosElectronicosForm;

@ManagedBean
@SessionScoped
public class InterfazIMPEnviarDocumentosElectronicosAction extends
		BaseInterfazAbstractAction {

	private static final long serialVersionUID = 423545158267358001L;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		// TODO Auto-generated method stub
		InterfazIMPEnviarDocumentosElectronicosForm form = new InterfazIMPEnviarDocumentosElectronicosForm();
		return form;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (this.log.isDebugEnabled()) {
			this.log.debug("Entering 'setViewAttributes' method");
		}

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("estadoCampanha", Constants.NUMERO_CERO); // Indica
																// Campanha
																// Activa
		criteria.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica
																	// Campanha
																	// activa q
																	// se
																	// procesa
																	// actualmente

		InterfazIMPEnviarDocumentosElectronicosForm f = (InterfazIMPEnviarDocumentosElectronicosForm) this.formInterfaz;
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) this
				.getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = service
				.getControlFacturacionById(criteria);

		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		f.setFechaFacturacion(controlFacturacion.getFechaProceso());
		f.setFechaFacturacionD(DateUtil.convertStringToDate(f.getFechaFacturacion()));

	}
	
	@Override
    protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception {
		InterfazIMPEnviarDocumentosElectronicosForm f = (InterfazIMPEnviarDocumentosElectronicosForm) this.formInterfaz;
		f.setFechaFacturacion(DateUtil.convertDateToString(f.getFechaFacturacionD()));
		params = super.prepareParamsBeforeExecute(params, form);						
		return params;
    }

}
