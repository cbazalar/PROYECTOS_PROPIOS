package biz.belcorp.ssicc.web.sisicc.action;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazPOLEnviarInformacionPortalForm;

@ManagedBean
@SessionScoped
public class InterfazPOLEnviarInformacionPortalAction extends
		BaseInterfazAbstractAction {

	private static final long serialVersionUID = 1L;
	private String alturaFija;
	private Boolean bloqueoFechas;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazPOLEnviarInformacionPortalForm form = new InterfazPOLEnviarInformacionPortalForm();
		return form;
	}

	public void indicadorFechasChange(ValueChangeEvent val) {
		String valor = val.getNewValue().toString();

		log.debug("indicadorComisionEscalonadaChange: valor = " + valor);

		if (StringUtils.equalsIgnoreCase(valor, "true")) {
			this.bloqueoFechas = true;
		} else {
			this.bloqueoFechas = false;

		}
	}

	public void inicializando() {
		this.alturaFija = "";
		this.bloqueoFechas = false;

	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}
		this.inicializando();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		InterfazPOLEnviarInformacionPortalForm interfazPOLForm = (InterfazPOLEnviarInformacionPortalForm) this.formInterfaz;
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		interfazPOLForm.setCodigoPeriodo(service.getPeriodoDefaultByPaisCanal(
				pais.getCodigo(), Constants.CODIGO_CANAL_DEFAULT));

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

		interfazPOLForm.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		interfazPOLForm.setFechaFacturacion(controlFacturacion
				.getFechaProceso());

		this.alturaFija = "S";

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
		InterfazPOLEnviarInformacionPortalForm interfazPOLForm = (InterfazPOLEnviarInformacionPortalForm) this.formInterfaz;
		interfazPOLForm.setFechaFacturacion(DateUtil
				.convertDateToString(interfazPOLForm.getFechaFacturacionD()));
		params = super.prepareParamsBeforeExecute(params, form);
		return params;
	}

	/**
	 * @return the alturaFija
	 */
	public String getAlturaFija() {
		return alturaFija;
	}

	/**
	 * @param alturaFija
	 *            the alturaFija to set
	 */
	public void setAlturaFija(String alturaFija) {
		this.alturaFija = alturaFija;
	}

	/**
	 * @return the bloqueoFechas
	 */
	public Boolean getBloqueoFechas() {
		return bloqueoFechas;
	}

	/**
	 * @param bloqueoFechas
	 *            the bloqueoFechas to set
	 */
	public void setBloqueoFechas(Boolean bloqueoFechas) {
		this.bloqueoFechas = bloqueoFechas;
	}

}