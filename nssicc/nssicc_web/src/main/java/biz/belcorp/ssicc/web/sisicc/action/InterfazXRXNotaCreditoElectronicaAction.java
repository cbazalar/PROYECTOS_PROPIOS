package biz.belcorp.ssicc.web.sisicc.action;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

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
import biz.belcorp.ssicc.web.sisicc.form.InterfazXRXNotaCreditoElectronicaForm;

@ManagedBean
@SessionScoped
public class InterfazXRXNotaCreditoElectronicaAction extends
		BaseInterfazAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2567801912532153536L;
	private List xrxTipoRecepcionList;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazXRXNotaCreditoElectronicaForm form = new InterfazXRXNotaCreditoElectronicaForm();
		return form;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		log.debug("Debug. Entry my method");

		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		this.xrxTipoRecepcionList = interfazSiCCService.getTiposRecepcionXRX(
				Constants.XRX_CODIGO_INTERFAZ_XRX3,
				Constants.XRX_CODIGO_INTERFAZ_XRX4);
		InterfazXRXNotaCreditoElectronicaForm interfazXRXForm = (InterfazXRXNotaCreditoElectronicaForm) this.formInterfaz;

		Map criteriaPeriodo = new HashMap();
		criteriaPeriodo.put("codigoPais", pais.getCodigo());
		criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO); // Indica
																		// Campanha
																		// Activa
		criteriaPeriodo.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica
																			// Campanha
																			// activa
																			// q
																			// se
																			// procesa
																			// actualmente

		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = service
				.getControlFacturacionById(criteriaPeriodo);

		if (interfazXRXForm.getCodigoPeriodo() == null) {
			interfazXRXForm.setCodigoPeriodo(controlFacturacion
					.getCodigoPeriodo());
		}

		if (interfazXRXForm.getFechaProceso() == null) {

			interfazXRXForm.setFechaProcesoD(DateUtil
					.convertStringToDate(controlFacturacion.getFechaProceso()));
		}

		if (log.isDebugEnabled()) {
			log.debug("Mi fecha proceso: " + interfazXRXForm.getFechaProceso());
			log.debug("Mi periodo es: " + interfazXRXForm.getCodigoPeriodo());
		}

	
	}


	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params,
			BaseForm form) throws Exception {
		params = super.prepareParamsBeforeExecute(params, form);

		InterfazXRXNotaCreditoElectronicaForm f = (InterfazXRXNotaCreditoElectronicaForm) this.formInterfaz;
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		f.setFechaProceso(DateUtil.convertDateToString(f.getFechaProcesoD()));
		params.put("codigoInterfaz", f.getTipoRecepcion());

		params.put("codigoProcesoBatch", codigoProcesoBatch);
		params.put(
				"fechaProcesoArchivo",
				DateUtil.convertDateToString("yyyyMMdd",
						DateUtil.convertStringToDate(f.getFechaProceso())));
		params.put(
				"fechaProceso",
				DateUtil.convertDateToString("yyyyMMdd",
						DateUtil.convertStringToDate(f.getFechaProceso())));

		String hora = new SimpleDateFormat("HHmmss").format(Calendar
				.getInstance().getTime());
		params.put("fechaProcesoHora", hora);
		params.put("usuario", usuario);

		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		interfazSiCCService.updateInterfazProcesoBatch(params);

		return params;
	}
	
	
	/**
	 * @param val
	 */
	public void showCargarInterfaz(ValueChangeEvent val) {

		log.debug(">>showCargarInterfaz ");
		String codigoInterfaz =  val.getNewValue().toString();
		InterfazXRXNotaCreditoElectronicaForm f = (InterfazXRXNotaCreditoElectronicaForm) this.formInterfaz;
		
		this.parametrosPantalla = new HashMap();
		this.parametrosPantalla.put("codigoInterfaz", codigoInterfaz);
		this.parametrosPantalla.put("tipoRecepcion", codigoInterfaz);
		this.parametrosPantalla.put("codigoProcesoBatch", f.getCodigoProcesoBatch());
		try {
			this.setBeforeViewAtributes();
			this.setViewAtributes();
		}
		catch(Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	

	public List getXrxTipoRecepcionList() {
		return xrxTipoRecepcionList;
	}

	public void setXrxTipoRecepcionList(List xrxTipoRecepcionList) {
		this.xrxTipoRecepcionList = xrxTipoRecepcionList;
	}

}
