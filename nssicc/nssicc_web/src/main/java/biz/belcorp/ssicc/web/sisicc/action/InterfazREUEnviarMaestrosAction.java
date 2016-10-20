package biz.belcorp.ssicc.web.sisicc.action;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Historico;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.HistoricoService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazExecutionResult;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.spusicc.let.MantenimientoLETLideresService;
import biz.belcorp.ssicc.service.spusicc.men.ProcesoMENCargaMasivaInformacionMensajesService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazREUEnviarMaestrosForm;

/**

  */
@ManagedBean
@SessionScoped
public class InterfazREUEnviarMaestrosAction extends BaseInterfazAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5096390441719054391L;
	private List siccMarcaList;
	private List siccCanalList;
	private List siccAccesoTodosList;
	private List siccActividadList;
	private List siccTipoClienteList;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction
	 * #devuelveFormInterfaz()
	 */
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazREUEnviarMaestrosForm interfazREUEnviarMaestrosForm = new InterfazREUEnviarMaestrosForm();
		return interfazREUEnviarMaestrosForm;
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
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		InterfazREUEnviarMaestrosForm f = (InterfazREUEnviarMaestrosForm) this.formInterfaz;

		/* obteniendo codigo de periodo actual */
		Map criteria = new HashMap();
		f.setCodigoPais(pais.getCodigo());
		criteria.put("codigoPais", f.getCodigoPais());
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
		f.setPeriodoDesde(controlFacturacion.getCodigoPeriodo());
		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());

		String periodoDesde = f.getPeriodoDesde();
		if (StringUtils.isNotBlank(periodoDesde)) {
			// Obteniendo oid Pais y Codigo Periodo+2
			MantenimientoLETLideresService letService = (MantenimientoLETLideresService) getBean("spusicc.mantenimientoLETLideresService");
			ProcesoMENCargaMasivaInformacionMensajesService service = (ProcesoMENCargaMasivaInformacionMensajesService) getBean("spusicc.procesoMENCargaMasivaInformacionMensajesService");
			Map criteriaOid = new HashMap();
			criteriaOid.put("codigoPais", f.getCodigoPais());
			criteriaOid.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
			criteriaOid.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);

			Map params = new HashMap();
			params.put("codigoPais", f.getCodigoPais());
			params.put("codigoPeriodo", periodoDesde);
			params.put("numeroCampanna", 3);
			params.put("oidPais",
					letService.getOidPaisByCodigoPaisLET(criteriaOid));
			String periodoHasta = service.getDevuelveCodigoCampanha(params);
			f.setPeriodoHasta(String.valueOf(periodoHasta));
		}

		// Carga de los combos Marca, Canal Actividad, Acceso y Periodo
		InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		this.siccMarcaList = svc.getMarcas();
		this.siccCanalList = svc.getCanalesByCodigoISO(usuario.getIdioma()
				.getCodigoISO());
		this.siccAccesoTodosList = getAccesoList(Constants.CODIGO_CANAL_DEFAULT);
		this.siccTipoClienteList = svc.getTiposClientesByCodigoISO(usuario
				.getIdioma().getCodigoISO());

		String marcaDefault = Constants.CODIGO_MARCA_DEFAULT;
		String canalDefault = Constants.CODIGO_CANAL_DEFAULT;
		this.siccActividadList = getActividadByPaisByMarcaByCanal(
				pais.getCodigo(), marcaDefault, canalDefault);

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
		InterfazREUEnviarMaestrosForm f = (InterfazREUEnviarMaestrosForm) this.formInterfaz;
		f.setFechaFacturacion(DateUtil.convertDateToString(f
				.getFechaFacturacionD()));
		params = super.prepareParamsBeforeExecute(params, form);
		HistoricoService historicoService = (HistoricoService) getBean("sisicc.historicoService");
		List historicos = historicoService
				.getUltimoHistoricoByCodInterfaz(params);
		if (historicos != null) {
			if (historicos.size() == 1)
				params.put("fechaInicioUltimoProceso",
						((Historico) historicos.get(0)).getFechaInicioProceso());
		} else {
			params.put("fechaInicioUltimoProceso", null);
		}
		return params;

	}

	public String setValidarInterfaz() {
		InterfazREUEnviarMaestrosForm form = (InterfazREUEnviarMaestrosForm) this.formInterfaz;
		int codperini = Integer.parseInt(form.getPeriodoDesde());
		int codperfin = Integer.parseInt(form.getPeriodoHasta());
		if (codperfin < codperini) {
			String mensaje = "El Periodo Hasta debe ser mayor o igual al Periodo Desde";
			return mensaje;
		}

		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		String periodo = form.getCodigoPeriodo();

		String fechaDesde = ajaxService.getFechaInicioPeriodoByPaisMarcaCanal(
				this.getmPantallaPrincipalBean().getCurrentCountry()
						.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,
				Constants.CODIGO_CANAL_DEFAULT, periodo);
		String fechaHasta = ajaxService.getFechaFinalPeriodoByPaisMarcaCanal(
				this.getmPantallaPrincipalBean().getCurrentCountry()
						.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,
				Constants.CODIGO_CANAL_DEFAULT, periodo);
		try {
			if (form.getFechaFacturacionD().before(
					DateUtil.convertStringToDate(fechaDesde))
					|| form.getFechaFacturacionD().after(
							DateUtil.convertStringToDate(fechaHasta))) {
				String mensaje = this
						.getResourceMessage("interfazREUEnviarMaestrosForm.error.rango.fechaFacturacion")
						+ " (" +fechaDesde + " - " +fechaHasta+ ")";
				return mensaje;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;
	}

	/*
	 * Método para cargar Acceso
	 */
	public void loadAcceso(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadAcceso");
		}
		InterfazREUEnviarMaestrosForm form = (InterfazREUEnviarMaestrosForm) this.formInterfaz;

		String valor = (String) val.getNewValue();

		this.siccAccesoTodosList = getAccesoList(valor);
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		this.siccActividadList = getActividadByPaisByMarcaByCanal(
				pais.getCodigo(), form.getCodigoMarca(), valor);

	}

	/*
	 * Método para cargar Actividad
	 */
	public void loadActividad(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadActividad");
		}
		InterfazREUEnviarMaestrosForm form = (InterfazREUEnviarMaestrosForm) this.formInterfaz;
		String marca = (String) val.getNewValue();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		setSiccActividadList(getActividadByPaisByMarcaByCanal(pais.getCodigo(),
				marca, form.getCodigoCanal()));

	}

	/* (non-Javadoc)
     * @see biz.belcorp.ssicc.sisicc.web.framework.action.BaseInterfazAbstractAction#afterExecuteInterfaz(java.util.Map, biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazExecutionResult)
     */
    protected void afterExecuteInterfaz(Map params, InterfazExecutionResult interfazExecutionResult) throws Exception{
    	super.afterExecuteInterfaz(params, interfazExecutionResult);

        if(log.isDebugEnabled()) {
            log.debug("Dentro del metodo 'afterExecuteInterfaz'");
        }
        // Validamos el resultado de la ejecucion
        if(interfazExecutionResult.ejecucionCompletada()) {
            InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
            svc.updateInterfazREUIndicadorTransferenciaClientes();
        }
    }
    
	
	/**
	 * @return the siccMarcaList
	 */
	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	/**
	 * @param siccMarcaList
	 *            the siccMarcaList to set
	 */
	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
	}

	/**
	 * @return the siccCanalList
	 */
	public List getSiccCanalList() {
		return siccCanalList;
	}

	/**
	 * @param siccCanalList
	 *            the siccCanalList to set
	 */
	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
	}

	/**
	 * @return the siccAccesoTodosList
	 */
	public List getSiccAccesoTodosList() {
		return siccAccesoTodosList;
	}

	/**
	 * @param siccAccesoTodosList
	 *            the siccAccesoTodosList to set
	 */
	public void setSiccAccesoTodosList(List siccAccesoTodosList) {
		this.siccAccesoTodosList = siccAccesoTodosList;
	}

	/**
	 * @return the siccActividadList
	 */
	public List getSiccActividadList() {
		return siccActividadList;
	}

	/**
	 * @param siccActividadList
	 *            the siccActividadList to set
	 */
	public void setSiccActividadList(List siccActividadList) {
		this.siccActividadList = siccActividadList;
	}

	/**
	 * @return the siccTipoClienteList
	 */
	public List getSiccTipoClienteList() {
		return siccTipoClienteList;
	}

	/**
	 * @param siccTipoClienteList
	 *            the siccTipoClienteList to set
	 */
	public void setSiccTipoClienteList(List siccTipoClienteList) {
		this.siccTipoClienteList = siccTipoClienteList;
	}

}
