package biz.belcorp.ssicc.web.sisicc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazSAPFIReprocesoCorporativoForm;

@ManagedBean
@SessionScoped
public class InterfazSAPFIReprocesoCorporativoAction extends
		BaseInterfazAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8254667265974138147L;
	private List sapfiAsientoList;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		// TODO Auto-generated method stub
		InterfazSAPFIReprocesoCorporativoForm form = new InterfazSAPFIReprocesoCorporativoForm();
		return form;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		this.sapfiAsientoList = service.getListaAsientos();

	}

	protected Map<String, Object> prepareParamsBeforeExecute(Map params,
			BaseForm form) throws Exception {

		params = super.prepareParamsBeforeExecute(params, form);

		Map criteria = new HashMap();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
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
		MantenimientoOCRPedidoControlFacturacionService serviceFact = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = serviceFact
				.getControlFacturacionById(criteria);
		InterfazSAPFIReprocesoCorporativoForm f = (InterfazSAPFIReprocesoCorporativoForm) this.formInterfaz;
		f.setFechaProceso(DateUtil.convertDateToString(f.getFechaProcesoD()));
		params.put("codigoPeriodo", controlFacturacion.getCodigoPeriodo());
		params.put("fechaProceso", f.getFechaProceso());
		params.put("fechaFacturacion", f.getFechaProceso());
		params.put("asientoList", f.getAsientoList());

		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		params.put("usuario", usuario);

		return params;

	}

	/**
	 * @return the sapfiAsientoList
	 */
	public List getSapfiAsientoList() {
		return sapfiAsientoList;
	}

	/**
	 * @param sapfiAsientoList
	 *            the sapfiAsientoList to set
	 */
	public void setSapfiAsientoList(List sapfiAsientoList) {
		this.sapfiAsientoList = sapfiAsientoList;
	}

}
