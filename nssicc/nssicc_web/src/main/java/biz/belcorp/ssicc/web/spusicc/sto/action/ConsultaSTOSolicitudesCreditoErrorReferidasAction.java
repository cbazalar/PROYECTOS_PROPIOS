package biz.belcorp.ssicc.web.spusicc.sto.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.sto.ConsultaSTOSolicitudesCreditoErrorReferidasService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.sto.form.ConsultaSTOSolicitudesCreditoErrorReferidasForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ConsultaSTOSolicitudesCreditoErrorReferidasAction extends
		BaseProcesoAbstractAction {

	private static final long serialVersionUID = 1L;

	private List stoSCErrorReferidasList = new ArrayList();
	private List siccZonaList = new ArrayList();
	private List siccRegionList = new ArrayList();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction
	 * #devuelveFormProceso()
	 */
	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ConsultaSTOSolicitudesCreditoErrorReferidasForm form = new ConsultaSTOSolicitudesCreditoErrorReferidasForm();
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
		if (log.isDebugEnabled()) {
			log.debug("executeProcess");
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction
	 * #setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("setFindAttributes");
		}
		ConsultaSTOSolicitudesCreditoErrorReferidasForm form = (ConsultaSTOSolicitudesCreditoErrorReferidasForm) this.formProceso;
		Map criteriaSearch = new HashMap();

		criteriaSearch.put("codigoPeriodo", form.getCodigoPeriodo());
		criteriaSearch.put("fechaSolicitud", form.getFechaSolicitud());

		ConsultaSTOSolicitudesCreditoErrorReferidasService service = (ConsultaSTOSolicitudesCreditoErrorReferidasService) getBean("spusicc.consultaSTOSolicitudesCreditoErrorReferidasService");

		if (form.getRegionList() != null) {
			if (form.getRegionList().length == 1) {
				if (form.getRegionList()[0] == null
						|| form.getRegionList()[0].compareToIgnoreCase("") == 0) {
					form.setRegionList(null);
				}
			}
		}

		if (form.getZonaList() != null) {
			if (form.getZonaList().length == 1) {
				if (form.getZonaList()[0] == null
						|| form.getZonaList()[0].compareToIgnoreCase("") == 0) {
					form.setZonaList(null);
				}
			}
		}

		criteriaSearch.put(
				"regionList",
				(form.getRegionList() == null) ? new ArrayList() : Arrays
						.asList(form.getRegionList()));
		criteriaSearch.put(
				"zonaList",
				(form.getZonaList() == null) ? new ArrayList() : Arrays
						.asList(form.getZonaList()));

		List l = service
				.getSolicitudesCreditoErrorReferidosList(criteriaSearch);
		this.setStoSCErrorReferidasList(l);

		form.setZonaList(new String[1]);

		return l;

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
			log.debug("setViewAtributes");
		}
		this.mostrarBotonBuscar = true;
		this.mostrarBotonExecute = false;
		this.mostrarListaBusqueda = true;
		this.mostrarCabeceraFija = true;
		this.mostrarProcesoBatch = false;

		ConsultaSTOSolicitudesCreditoErrorReferidasForm form = (ConsultaSTOSolicitudesCreditoErrorReferidasForm) this.formProceso;
		Map criteria = new HashMap();
		criteria.put("codigoPais", this.getmPantallaPrincipalBean()
				.getCurrentCountry().getCodigo());
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

		// Setea la campña activa
		//form.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		form.setFechaSolicitud("");

		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");

		this.setSiccRegionList(reporteService.getListaGenerico(
				"getRegionesByPais", criteria));
	}

	/**
	 * @param val
	 */
	public void showZonasxRegion(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("showZonasxRegion:ValueChangeEvent");
		}
		try {
			log.debug(val.getNewValue().toString());
			if (StringUtils.isNotEmpty(val.getNewValue().toString())
					|| StringUtils.isNotBlank(val.getNewValue().toString())) {
				String[] regionListado = (String[]) val.getNewValue();
				log.debug(regionListado.length);
				

				for (String ar : regionListado) {
					if(ar.equals("")){
						this.siccZonaList = new ArrayList();
						return;
					}
				}
				
				
				AjaxService ajax = (AjaxService) getBean("ajaxService");
				if (regionListado.length > 0) {
					
					
					this.siccZonaList = Arrays.asList(ajax
							.getZonasMultipleByPaisMarcaCanalRegion(this
									.getmPantallaPrincipalBean()
									.getCurrentCountry().getCodigo(), "T",
									"VD", regionListado, ""));
					
					
				
				
					for (int i = 0; i < siccZonaList.size(); i++) {
						LabelValue objLbl = (LabelValue) this.siccZonaList.get(i);
						if(objLbl.getLabel().equals("Todos")){
	
							this.siccZonaList.get(i);
							break;
						}
						
					}
				
				} else {
					siccZonaList = new ArrayList();
				}
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * @return
	 */
	public List getStoSCErrorReferidasList() {
		return stoSCErrorReferidasList;
	}

	/**
	 * @param stoSCErrorReferidasList
	 */
	public void setStoSCErrorReferidasList(List stoSCErrorReferidasList) {
		this.stoSCErrorReferidasList = stoSCErrorReferidasList;
	}

	/**
	 * @return
	 */
	public List getSiccZonaList() {
		return siccZonaList;
	}

	/**
	 * @param siccZonaList
	 */
	public void setSiccZonaList(List siccZonaList) {
		this.siccZonaList = siccZonaList;
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

}