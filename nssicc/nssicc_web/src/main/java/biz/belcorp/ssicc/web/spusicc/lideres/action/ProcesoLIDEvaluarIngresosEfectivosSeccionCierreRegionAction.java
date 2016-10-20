package biz.belcorp.ssicc.web.spusicc.lideres.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.lideres.ProcesoLIDEvaluarIngresosEfectivosSeccionCierreRegionService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.lideres.form.ProcesoLIDEvaluarIngresosEfectivosSeccionCierreRegionForm;

@SessionScoped
@ManagedBean
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ProcesoLIDEvaluarIngresosEfectivosSeccionCierreRegionAction extends
		BaseProcesoAbstractAction {

	private List siccMarcaList;
	private List siccRegionList;
	private List lidTipoEvaluacionList;

	/**
	 * @return
	 */
	public List getLidTipoEvaluacionList() {
		return lidTipoEvaluacionList;
	}

	/**
	 * @param lidTipoEvaluacionList
	 */
	public void setLidTipoEvaluacionList(List lidTipoEvaluacionList) {
		this.lidTipoEvaluacionList = lidTipoEvaluacionList;
	}

	/**
	 * @return
	 */
	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	/**
	 * @param siccMarcaList
	 */
	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
	}

	/**
	 * @return
	 */
	public List getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList
	 */
	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	private static final long serialVersionUID = 8285439889938061938L;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction#devuelveFormProceso()
	 */
	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoLIDEvaluarIngresosEfectivosSeccionCierreRegionForm form = new ProcesoLIDEvaluarIngresosEfectivosSeccionCierreRegionForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction#executeProcess(java.util.Map)
	 */
	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		log.debug("Exectuting action : executeProcess.");
		if (params != null) {
			ProcesoLIDEvaluarIngresosEfectivosSeccionCierreRegionService procesoLIDEvaluarIngresosEfectivosSeccionCierreRegionService = (ProcesoLIDEvaluarIngresosEfectivosSeccionCierreRegionService) getBean("spusicc.procesoLIDEvaluarIngresosEfectivosSeccionCierreRegionService");
			procesoLIDEvaluarIngresosEfectivosSeccionCierreRegionService
					.executeEvaluarIngresosEfectivosSeccionCierreRegion(params);
		}
		return params;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		log.debug("Executin action : view.");
		ProcesoLIDEvaluarIngresosEfectivosSeccionCierreRegionForm f = (ProcesoLIDEvaluarIngresosEfectivosSeccionCierreRegionForm) formProceso;

		Map criteria = new HashMap();
		criteria.put("codigoPais", this.mPantallaPrincipalBean.getCurrentCountry()
				.getCodigo());
		criteria.put("estadoCampanha", Constants.NUMERO_CERO); // Indica
																// Campanha
																// Activa
		criteria.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica
																	// Campanha
																	// activa q
																	// se
																	// procesa
																	// actualmente

		ProcesoLIDEvaluarIngresosEfectivosSeccionCierreRegionService procesoService = (ProcesoLIDEvaluarIngresosEfectivosSeccionCierreRegionService) getBean("spusicc.procesoLIDEvaluarIngresosEfectivosSeccionCierreRegionService");

		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = service
				.getControlFacturacionById(criteria);

		// Carga de los combos Marca
		this.siccMarcaList = reporteService.getMarcas();

		// Carga del combo Regiones
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", mPantallaPrincipalBean
				.getCurrentCountry().getCodigo());
		this.siccRegionList = reporteService.getListaGenerico("getRegionesByPais",
				criteriaOperacion);

		// Carga del combo tipo Evaluacion
		this.lidTipoEvaluacionList = procesoService.getTiposEvaluacion(null);

		
		// Carga PeriodoProceso
		f.setPeriodoProceso(controlFacturacion.getCodigoPeriodo());
		f.setFechaProceso(controlFacturacion.getFechaProceso());
		f.setCodigoIdiomaISO(this.mPantallaPrincipalBean.getCurrentUser()
				.getIdioma().getCodigoISO());
		f.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		f.setTipoEvaluacion(Constants.LID_TIPO_EVALUACION_DEFAULT);

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

		ProcesoLIDEvaluarIngresosEfectivosSeccionCierreRegionService procesoLIDEvaluarIngresosEfectivosSeccionCierreRegionService = (ProcesoLIDEvaluarIngresosEfectivosSeccionCierreRegionService) getBean("spusicc.procesoLIDEvaluarIngresosEfectivosSeccionCierreRegionService");

		boolean esRegionProcesada = procesoLIDEvaluarIngresosEfectivosSeccionCierreRegionService
				.verificaRegionProcesada(params);

		if (!esRegionProcesada) {
			throw new Exception(
					this.getResourceMessage("procesoLIDEvaluarIngresosEfectivosSeccionCierreRegionForm.msg.validaRegionProcesada"));
		}
		return params;
	}
}
