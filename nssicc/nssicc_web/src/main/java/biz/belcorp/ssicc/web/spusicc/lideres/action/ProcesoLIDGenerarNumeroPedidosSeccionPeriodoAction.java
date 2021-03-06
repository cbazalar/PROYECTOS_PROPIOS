package biz.belcorp.ssicc.web.spusicc.lideres.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.lideres.ProcesoLIDGenerarNumeroPedidosSeccionPeriodoService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.lideres.form.ProcesoLIDGenerarNumeroPedidosSeccionPeriodoForm;

@SessionScoped
@ManagedBean
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ProcesoLIDGenerarNumeroPedidosSeccionPeriodoAction extends
		BaseProcesoAbstractAction {
	
	private List siccMarcaList;
	private List siccRegionList;
	
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
		ProcesoLIDGenerarNumeroPedidosSeccionPeriodoForm form = new ProcesoLIDGenerarNumeroPedidosSeccionPeriodoForm();
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
			ProcesoLIDGenerarNumeroPedidosSeccionPeriodoService procesoLIDGenerarNumeroPedidosSeccionPeriodoService = (ProcesoLIDGenerarNumeroPedidosSeccionPeriodoService) getBean("spusicc.procesoLIDGenerarNumeroPedidosSeccionPeriodoService");
			procesoLIDGenerarNumeroPedidosSeccionPeriodoService
					.executeGenerarNumeroPedidosSeccionPeriodo(params);
		}
		return params;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		log.debug("Executin action : view.");
		ProcesoLIDGenerarNumeroPedidosSeccionPeriodoForm f = (ProcesoLIDGenerarNumeroPedidosSeccionPeriodoForm) formProceso;

		Map criteria = new HashMap();
		criteria.put("codigoPais", mPantallaPrincipalBean.getCurrentCountry()
				.getCodigo());
		criteria.put("estadoCampanha", Constants.NUMERO_CERO); 
		criteria.put("indicadorActiva", Constants.ESTADO_ACTIVO); 
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", mPantallaPrincipalBean
				.getCurrentCountry().getCodigo());

		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");

		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = service
				.getControlFacturacionById(criteria);
		// Carga de los combos Marca, PeriodoProceso
		this.siccMarcaList=reporteService.getMarcas();
		this.siccRegionList=reporteService.getListaGenerico("getRegionesByPais",
				criteriaOperacion);
		f.setMarcaList(reporteService.getMarcas());
		f.setRegionList(reporteService.getListaGenerico("getRegionesByPais",
				criteriaOperacion));
		f.setPeriodoProceso(controlFacturacion.getCodigoPeriodo());
		f.setCodigoIdiomaISO(this.mPantallaPrincipalBean.getCurrentUser()
				.getIdioma().getCodigoISO());
		f.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
	}
}