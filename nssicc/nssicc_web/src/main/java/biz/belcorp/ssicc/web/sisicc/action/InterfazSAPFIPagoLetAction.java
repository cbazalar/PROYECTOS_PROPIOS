package biz.belcorp.ssicc.web.sisicc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.spusicc.lec.ProcesoLECCargaDatosExcelService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazSAPFIPagoLetForm;

@ManagedBean
@SessionScoped
public class InterfazSAPFIPagoLetAction extends BaseInterfazAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4135224032226404786L;
	private List lecTipoPagoList;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		// TODO Auto-generated method stub
		InterfazSAPFIPagoLetForm form = new InterfazSAPFIPagoLetForm();
		return form;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}

		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		// Carga de los combos
		ProcesoLECCargaDatosExcelService servicePEJ = (ProcesoLECCargaDatosExcelService) getBean("spusicc.procesoLECCargaDatosExcelService");

		InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		this.lecTipoPagoList = svc.getListaTipoPagoLec();

		InterfazSAPFIPagoLetForm f = (InterfazSAPFIPagoLetForm) this.formInterfaz;
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		f.setCodigoPeriodo(service.getPeriodoDefaultByPaisCanal(
				pais.getCodigo(),
				Constants.CODIGO_CANAL_DEFAULT));

		

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
		criteria.put("estado", Constants.ESTADO_ACTIVO);
		criteria.put("campana", f.getCodigoPeriodo());
		MantenimientoOCRPedidoControlFacturacionService servicePedido = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = servicePedido
				.getControlFacturacionById(criteria);
		List programas = servicePEJ.getPrograma(criteria);
		if (programas != null && programas.size() > 0) {
			Base tmp = (Base) programas.get(0);
			f.setCodigoPrograma(tmp.getCodigo());
			f.setPrograma(tmp.getDescripcion());
		}
		// request.getSession().setAttribute(Constants.LEC_PROGRAMA_LIST,
		// servicePEJ.getPrograma(criteria));
		// Carga el periodo actual
		f.setFechaProcesoD(DateUtil.convertStringToDate(controlFacturacion.getFechaProceso()));



	}

	protected Map<String, Object> prepareParamsBeforeExecute(Map params,
			BaseForm form) throws Exception {

		params = super.prepareParamsBeforeExecute(params, form);
		
		return params;
	}

	/**
	 * @return the lecTipoPagoList
	 */
	public List getLecTipoPagoList() {
		return lecTipoPagoList;
	}

	/**
	 * @param lecTipoPagoList
	 *            the lecTipoPagoList to set
	 */
	public void setLecTipoPagoList(List lecTipoPagoList) {
		this.lecTipoPagoList = lecTipoPagoList;
	}

}
