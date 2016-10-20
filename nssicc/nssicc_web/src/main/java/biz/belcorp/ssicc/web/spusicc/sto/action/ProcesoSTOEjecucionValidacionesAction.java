package biz.belcorp.ssicc.web.spusicc.sto.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.spusicc.sto.model.AccionTipoDocumento;
import biz.belcorp.ssicc.dao.spusicc.sto.model.TipoDocumentoDigitado;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEjecucionValidacionesService;
import biz.belcorp.ssicc.service.spusicc.sto.framework.ProcesoSTOExecutionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.sto.form.ProcesoSTOEjecucionValidacionesForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ProcesoSTOEjecucionValidacionesAction extends
		BaseProcesoAbstractAction {
	private List stoTipoDocumentoList;
	private LabelValue[] stoDetalleDocumentoList;
	private boolean primerPaso = true;

	/**
	 * @return
	 */
	public boolean isPrimerPaso() {
		return primerPaso;
	}

	/**
	 * @param primerPaso
	 */
	public void setPrimerPaso(boolean primerPaso) {
		this.primerPaso = primerPaso;
	}

	/**
	 * @return
	 */
	public List getStoTipoDocumentoList() {
		return stoTipoDocumentoList;
	}

	/**
	 * @param stoTipoDocumentoList
	 */
	public void setStoTipoDocumentoList(List stoTipoDocumentoList) {
		this.stoTipoDocumentoList = stoTipoDocumentoList;
	}

	/**
	 * @return
	 */
	public LabelValue[] getStoDetalleDocumentoList() {
		return stoDetalleDocumentoList;
	}

	/**
	 * @param stoDetalleDocumentoList
	 */
	public void setStoDetalleDocumentoList(LabelValue[] stoDetalleDocumentoList) {
		this.stoDetalleDocumentoList = stoDetalleDocumentoList;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 3430646839850023181L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction
	 * #devuelveFormProceso()
	 */
	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoSTOEjecucionValidacionesForm form = new ProcesoSTOEjecucionValidacionesForm();
		return form;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction
	 * #executeProcess(java.util.Map)
	 */
	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		AccionTipoDocumento accionTipoDocumento = (AccionTipoDocumento) params
				.get("accionTipoDocumento");
		ProcesoSTOExecutionService service = (ProcesoSTOExecutionService) getBean("spusicc.procesoSTOExecutionService");
		this.primerPaso = false;
		service.execute(accionTipoDocumento, params, null);
		return params;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction
	 * #afterExecuteProcess
	 * (biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm)
	 */
	@Override
	public void afterExecuteProcess(BaseProcesoForm form) throws Exception {

		super.afterExecuteProcess(form);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarBotonBuscar = false;
		ProcesoSTOEjecucionValidacionesForm f = (ProcesoSTOEjecucionValidacionesForm) this.formProceso;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		log.debug("Executing action : setViewAttributes.");
		Map criteria = new HashMap();
		criteria.put("codigoPais", this.mPantallaPrincipalBean
				.getCurrentCountry().getCodigo());
		criteria.put("codigoUsuario", this.mPantallaPrincipalBean
				.getCurrentUser().getLogin());
		String codigoProceso = f.getCodigoProcesoBatch();
		String codigoSistema = f.getCodigoSistema();

		f.setCodigoProcesoBatch(codigoProceso);
		f.setCodigoSistema(codigoSistema);
		this.primerPaso = false;
		ProcesoSTOEjecucionValidacionesService procesoSTOEjecucionValidacionesService = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");

		this.stoTipoDocumentoList = procesoSTOEjecucionValidacionesService
				.getTiposDocumentosSTO(criteria);

		Map criteriaPeriodo = new HashMap();
		criteriaPeriodo.put("codigoPais", mPantallaPrincipalBean
				.getCurrentCountry().getCodigo());
		criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO);
		criteriaPeriodo.put("indicadorActiva", Constants.ESTADO_ACTIVO);

		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = service
				.getControlFacturacionById(criteriaPeriodo);

		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		f.setFechaProceso(controlFacturacion.getFechaProceso());
		//f.setFechaProcesoD(DateUtil.convertStringToDate(f.getFechaProceso()));
		f.setCodigoPais(pais.getCodigo());

	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#prepareParamsBeforeExecute(java.util.Map, biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm)
	 */
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params,
			BaseForm form) throws Exception {

		if (log.isDebugEnabled()) {
			log.debug("Exectuting action : prepareParamsBeforeExecute.");
		}

		ProcesoSTOEjecucionValidacionesService procesoSTOEjecucionValidacionesService = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
		ProcesoSTOEjecucionValidacionesForm f = (ProcesoSTOEjecucionValidacionesForm) formProceso;

		String codigoTipoDocumento = f.getTipoDocumento();
		TipoDocumentoDigitado tipoDocumentoDigitado = procesoSTOEjecucionValidacionesService
				.getTipoDocumentoDigitado(codigoTipoDocumento);

		// Setea el proceso Batch Actual a ejecutarse
		String codigoProcesoBatch = tipoDocumentoDigitado.getCodigoProcesoBatch();
		this.codigoProcesoBatch = codigoProcesoBatch;
		f.setCodigoProcesoBatch(this.codigoProcesoBatch);
		
		//Ajuste para el caso que no termina el proceso batch de Validaciones STO
		if(StringUtils.isNotEmpty(this.codigoProcesoBatch))
			this.esProcesoBatch = true;
		
		params = super.prepareParamsBeforeExecute(params, f);
		params.put("codigoProcesoBatch", this.codigoProcesoBatch);
		params.put("usuario", mPantallaPrincipalBean.getCurrentUser());

		if (codigoTipoDocumento.equalsIgnoreCase("OCC")) {
			params.put("codigoPeriodo", f.getCodigoPeriodo());
		} else {
			params.put("codigoPeriodo", "");
		}

		AccionTipoDocumento accionTipoDocumento = new AccionTipoDocumento(
				this.mPantallaPrincipalBean.getCurrentCountry().getCodigo(),
				codigoTipoDocumento, Constants.STO_ACCI_VALI_MASI);
		params.put("accionTipoDocumento", accionTipoDocumento);
		return params;

	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction#setObtenerConsultaProcesoBatch(java.util.Map)
	 */
	protected  Map<String, Object> setActualizarDatos(Map<String, Object> criteria) {
		ProcesoSTOEjecucionValidacionesForm f = (ProcesoSTOEjecucionValidacionesForm) formProceso;
		f.setCodigoProcesoBatch(this.codigoProcesoBatch);
		criteria.put("codigoProcesoBatch", this.codigoProcesoBatch);
		return criteria;
	}
	
	
	/**
	 * Cargar documentos
	 * @param valueChange
	 */
	private void loadDocumentos(String valor){
		try {
			AjaxService ajaxService = (AjaxService) getBean("ajaxService");
			ProcesoSTOEjecucionValidacionesForm f = (ProcesoSTOEjecucionValidacionesForm) formProceso;
			if(valor!=null && !f.getCodigoPais().equals("")){
				LabelValue data[]=ajaxService.getDocumentos(f.getCodigoPais(), f.getTipoDocumento());
				if(data != null){
					if(data.length>0 ){
						if(valor.equals( "OCC")){
							this.primerPaso=true;
						}
					}
				}else{
					this.primerPaso=false;
				}
			}	
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}	
	}

	/**
	 * @param val
	 */
	public void cargarDocumentos(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("Load Docs");
		}
		this.primerPaso = false;
		if(val == null){
			return;
		}
		
		try {
			String tipoDoc = (String) val.getNewValue();
			ProcesoSTOEjecucionValidacionesForm f = (ProcesoSTOEjecucionValidacionesForm) formProceso;
			f.setTipoDocumento(tipoDoc);
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			this.stoDetalleDocumentoList = null;
			this.stoDetalleDocumentoList = aSvc.getDocumentos(
					mPantallaPrincipalBean.getCurrentCountry().getCodigo(),
					tipoDoc);
			loadDocumentos(tipoDoc);
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}
}
