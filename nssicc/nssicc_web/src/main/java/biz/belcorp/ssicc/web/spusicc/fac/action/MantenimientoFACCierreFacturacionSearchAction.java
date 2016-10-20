package biz.belcorp.ssicc.web.spusicc.fac.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.framework.BaseMailService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.MailParams;
import biz.belcorp.ssicc.service.spusicc.fac.MantenimientoFACGenericoService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.fac.form.MantenimientoFACCierreFacturacionForm;
import biz.belcorp.ssicc.web.spusicc.fac.form.MantenimientoFACCierreFacturacionSearchForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MantenimientoFACCierreFacturacionSearchAction extends
		BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = -7410707438658770587L;

	private List facMantCierreList;
	private List facRegionList;
	private LabelValue[] facZonaList;
	private List facCierreZonaList;
	private List facCierreRegionList;
	private String indHabilitar;
	private String valorUno = Constants.NRO_UNO;
	private String ventana;
	private String campaniaActual;

	private boolean consultar;

	private DataTableModel detalleCierreZonaTableModel;
	private Object[] beanRegistroDetalleCierreZona;
	private DataTableModel detalleCierreRegionTableModel;
	private Object[] beanRegistroDetalleCierreRegion;
	
	private String indicadorCierreCampanha;

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#getSalirForward()
	 */
	@Override
	protected String getSalirForward() {
		this.consultar = false;
		this.mostrarBotonSave = true;
		
		return "mantenimientoFACCierreFacturacionList";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoFACCierreFacturacionForm";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoFACCierreFacturacionSearchForm searchForm = new MantenimientoFACCierreFacturacionSearchForm();
		return searchForm;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#edit()
	 */
//	@Override
//	public void edit() {
//		try {
//			Map sistemabusqueda = (Map) this.beanRegistroSeleccionado;
//			String estadoCierre = sistemabusqueda.get("estadoCierre").toString();
//			if (!estadoCierre.equals("E")) {
//				this.addError(
//						"ERROR ",
//						this.getResourceMessage("mantenimientoFACCierreFacturacionForm.cierre.estado.no.pendiente.modificar"));
//			}else{
//				this.setObtenerRegistroAttributes();
//				this.redireccionarPagina(this.getPaginaMantenimiento());
//			}	
//		} catch (Exception e) {
//			this.addError("Error : ", this.obtieneMensajeErrorException(e));
//		}
//		
//	}
//	
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction
	 * #delete(javax.faces.event.ActionEvent)
	 */
	@Override
	public void delete(ActionEvent actionEvent) {
		try {
			Map sistemabusqueda = (Map) this.beanRegistroSeleccionado;
			String estadoCierre = sistemabusqueda.get("estadoCierre").toString();
			if (!estadoCierre.equals("E")) {
				if (estadoCierre.equals("A")){
					this.addError(
							"ERROR ",
							this.getResourceMessage("mantenimientoFACCierreFacturacionForm.cierre.estado.no.pendiente.aprobado"));
				return;
				}
					
				else{
					this.addError(
							"ERROR ",
							this.getResourceMessage("mantenimientoFACCierreFacturacionForm.cierre.estado.no.pendiente"));
				return;
				}
				
			}else{
				this.setDeleteAttributes();
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
		
		return;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		MantenimientoFACCierreFacturacionSearchForm f = (MantenimientoFACCierreFacturacionSearchForm) this.formBusqueda;
		MantenimientoFACGenericoService service = (MantenimientoFACGenericoService) this
				.getBean("spusicc.mantenimientoFACGenericoService");

		/* obteniendo valores */
		if(f.getFechaCierreDate()!=null)
			f.setFechaCierre(DateUtil.convertDateToString("dd/MM/yyyy", f.getFechaCierreDate()));
		Map criteria = BeanUtils.describe(f);
		
		/* Obteniendo Lista */
		List resultado = service.getConsolidadoCierreFacturacion(criteria);
		this.facMantCierreList = resultado;
		return resultado;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setSaveAttributes' method");
		}
		Map sistemabusqueda = (Map) this.beanRegistroSeleccionado;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		// String id =sistemabusqueda.get("id").toString();
		// log.debug("row id "+id);

		MantenimientoFACGenericoService service = (MantenimientoFACGenericoService) this
				.getBean("spusicc.mantenimientoFACGenericoService");
		List list = this.facMantCierreList;
		Map map = sistemabusqueda;
		map.put("codigoPais", pais.getCodigo());
		map.put("login", usuario.getLogin());

		String cantidadRegion = String.valueOf(map.get("cantidadRegion"));
		// String cantidadZona=(String)map.get("cantidadZona");
		String cantidadCampanha = String.valueOf(map.get("cantidadCampanha"));

		if (Constants.NRO_UNO.equals(cantidadCampanha)) {
			// eliminadno
			service.deleteCierreFacturacion(map);
			return true;
		} else {
			// es cero indicador campanha
			// se valida la campanha
			Map criteria = new HashMap();
			criteria.put("campanhaProceso", map.get("campanhaProceso"));
			// se obtine flag de cierre campanha
			criteria.put("tipoCierre", "C");
			List listCierreCampanha = service.getCierreFacturacion(criteria);
			if (listCierreCampanha.size() > 0)
				throw new Exception(
						this.getResourceMessage("mantenimientoFACCierreFacturacionForm.no.eliminar.region"));
		}

		if (Constants.NRO_CERO.equals(cantidadRegion)) {
			Map criteria = new HashMap();
			criteria.put("campanhaProceso", map.get("campanhaProceso"));
			criteria.put("fechaCierre", map.get("fechaCierre"));
			criteria.put("tipoCierre", "R");
			Integer validar = service
					.getExisteCierreFacturacionRegion(criteria);
			if (validar.intValue() > 0)
				throw new Exception(
						this.getResourceMessage("mantenimientoFACCierreFacturacionForm.no.eliminar.zona.programada"));

		}
		// eliminadno
		service.deleteCierreFacturacion(map);
		this.listaBusqueda = this.setFindAttributes();
		this.datatableBusqueda = new DataTableModel(this.listaBusqueda);
		return true;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setSaveAttributes()
	 */
	@Override
	protected boolean setSaveAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setSaveAttributes' method");
		}
		MantenimientoFACGenericoService service = (MantenimientoFACGenericoService) getBean("spusicc.mantenimientoFACGenericoService");
		MantenimientoFACCierreFacturacionForm f = (MantenimientoFACCierreFacturacionForm) this.formMantenimiento;
		Map sistemabusqueda = (Map) this.beanRegistroSeleccionado;
		f.setIndicadorRegion(Constants.NRO_UNO);
		f.setIndicadorZona(Constants.NRO_UNO);
		f.setFechaCierre(DateUtil.convertDateToString(f.getFechaCierreDate()));

		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Map map = BeanUtils.describe(f);
		map.put("login", usuario.getLogin());
		if (f.isNewRecord()) {
			// flagCierreCampanha
			// se verificaq ue campanha y fecha distinta
			List list = service.getCierreFacturacion(map);
			if (list.size() > 0) {
				throw new Exception(
						this.getResourceMessage("mantenimientoFACCierreFacturacionForm.campanha.fecha.distinto"));
			}

			// se verificara que haya dado en la pestanaha
			List listCierreRegion = this.facCierreRegionList;
			List listCierreZona = this.facCierreZonaList;

			if (listCierreRegion == null)
				listCierreRegion = new ArrayList();
			if (listCierreZona == null)
				listCierreZona = new ArrayList();
			List listRegionPorcerrar = getRegionesPorCerrar(map);
			
			List listZonaAbiertas = service.getZonasAbiertas(map);
			List listRegionAbiertas = service.getRegionesAbiertas(map);
			
			if ("true".equals(f.getFlagCierreCampanha())) {
				// listRegionPorcerrar= getRegionesPorCerrar(map);
				if (listRegionAbiertas.size() > 0)
					throw new Exception(this.getResourceMessage("mantenimientoFACCierreFacturacionForm.region.abierta"));
				
				if (listRegionPorcerrar.size() > 0)
					throw new Exception(
							this.getResourceMessage("mantenimientoFACCierreFacturacionForm.falta.region.programar"));
			}
			
			if (listZonaAbiertas.size() > 0){
				throw new Exception(this.getResourceMessage("mantenimientoFACCierreFacturacionForm.zona.abierta"));
			}
			
			int numEliminadasZona = getNumEliminadas(listCierreZona);
			// int numEliminadasRegion=getNumEliminadas(listCierreRegion);;

			if ((listCierreRegion.size() == 0 && listCierreZona.size() == 0)
					|| (listCierreZona.size() != 0 && listCierreZona.size() == numEliminadasZona)) {

				if ("true".equals(f.getFlagCierreCampanha())) {
					if (listRegionPorcerrar.size() > 0)// no se ha programado
														// todas, aun hay
														// regiones x cerrar
						throw new Exception(
								this.getResourceMessage("mantenimientoFACCierreFacturacionForm.programar.zona.region"));

				} else {
					throw new Exception(
							this.getResourceMessage("mantenimientoFACCierreFacturacionForm.programar.zona.region"));

				}
			}

			log.debug("listCierreRegion " + listCierreRegion.size()
					+ " listCierreZona " + listCierreZona.size());
			map.put("listCierreZona", listCierreZona);
			map.put("listCierreRegion", listCierreRegion);

			service.saveCierreFacturacion(map);// este inserta tb en la tabla de
												// parametros
			return true;

		} else {
			map.put("indicadorCierreCampanha", this.indicadorCierreCampanha);
			// actualizacion
			List listRegionPorcerrar = getRegionesPorCerrar(map);
			
			List listZonaAbiertas = service.getZonasAbiertas(map);
			List listRegionAbiertas = service.getRegionesAbiertas(map);
			
			if ("true".equals(f.getFlagCierreCampanha())) {
				// List listRegionPorcerrar= getRegionesPorCerrar(map);
				if (listRegionAbiertas.size() > 0)
					throw new Exception(this.getResourceMessage("mantenimientoFACCierreFacturacionForm.region.abierta"));
				
				if (listRegionPorcerrar.size() > 0)
					throw new Exception(
							this.getResourceMessage("mantenimientoFACCierreFacturacionForm.falta.region.programar"));
			}

			// se verificara que haya dado en la pestanaha de vigencias
			List listCierreRegion = this.facCierreRegionList;
			List listCierreZona = this.facCierreZonaList;

			if (listCierreRegion == null)
				listCierreRegion = new ArrayList();
			if (listCierreZona == null)
				listCierreZona = new ArrayList();

			if (listCierreRegion.size() == 0 && listCierreZona.size() == 0) {

				if ("true".equals(f.getFlagCierreCampanha())) {
					if (listRegionPorcerrar.size() > 0)
						throw new Exception(
								this.getResourceMessage("mantenimientoFACCierreFacturacionForm.programar.zona.region"));
				} else {
					throw new Exception(
							this.getResourceMessage("mantenimientoFACCierreFacturacionForm.programar.zona.region"));
				}
			}
			
			if (listZonaAbiertas.size() > 0){
				throw new Exception(this.getResourceMessage("mantenimientoFACCierreFacturacionForm.zona.abierta"));
			}

			log.debug("listCierreRegion " + listCierreRegion.size()
					+ " listCierreZona " + listCierreZona.size());
			map.put("listCierreZona", listCierreZona);
			map.put("listCierreRegion", listCierreRegion);
			service.updateCierreFacturacion(map);// este inserta tb en la tabla
													// de parametros
		}

		List resultado = service.getConsolidadoCierreFacturacion(map);
		this.facMantCierreList = resultado;
		return true;

	}

	/**
	 * retorna el numero de eliminadas
	 */
	private int getNumEliminadas(List listCierre) {
		Iterator it = listCierre.iterator();
		int cont = 0;
		while (it.hasNext()) {
			Map map = (Map) it.next();
			String indicadorAccion = (String) map.get("indicadorAccion");
			if (Constants.NUMERO_DOS.equals(indicadorAccion))
				cont++;
		}
		return cont;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setObtenerRegistroAttributes()
	 */
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		Map sistemabusqueda = (Map) this.beanRegistroSeleccionado;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoFACCierreFacturacionForm f = new MantenimientoFACCierreFacturacionForm();
		MantenimientoFACGenericoService service = (MantenimientoFACGenericoService) getBean("spusicc.mantenimientoFACGenericoService");

		f.setIndicadorZona(Constants.NRO_UNO);
		f.setIndicadorRegion(Constants.NRO_UNO); 
		f.setTabSeleccion(Constants.FAC_TAB_CIERRE_ZONA);

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

		MantenimientoOCRPedidoControlFacturacionService serviceMantPedidoCtrlFact = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = serviceMantPedidoCtrlFact
				.getControlFacturacionById(criteria);

		f.setCampanhaProceso(controlFacturacion.getCodigoPeriodo());
		this.campaniaActual = controlFacturacion.getCodigoPeriodo();
		String fecha = controlFacturacion.getFechaProceso();
		f.setFechaCierre(fecha);
		f.setFechaProcesoActual(fecha);
		f.setFechaCierreDate(DateUtil.convertStringToDate(fecha));
		f.setFechaProcesoActualDate(DateUtil.convertStringToDate(fecha));
		f.setCodigoPais(pais.getCodigo());
		this.beanRegistroDetalleCierreRegion = null;
		this.beanRegistroDetalleCierreZona = null;
		this.facCierreRegionList = new ArrayList();
		this.facCierreZonaList = new ArrayList();
		this.detalleCierreZonaTableModel = new DataTableModel();
		this.detalleCierreRegionTableModel = new DataTableModel();
		Map map = new HashMap();
		map.put("campanhaProceso", f.getCampanhaProceso());
		this.facRegionList = getRegionesPorCerrar(map);
		this.indHabilitar = Constants.NRO_UNO;
		f.setIndicadorEdit(null);
		// borrando temporales
		service.deleteTemporales();

		if (!this.accion.equals(this.ACCION_NUEVO)) {
			//Valida Previas			
			String estadoCierre = sistemabusqueda.get("estadoCierre").toString();
			
			if(!this.accion.equals(this.ACCION_CONSULTAR)){
				if (!estadoCierre.equals("E")) {
					throw new Exception(this.getResourceMessage("mantenimientoFACCierreFacturacionForm.cierre.estado.no.pendiente.modificar"));
					//this.addError("ERROR ",);
				}
			}
			
			
			// String id =sistemabusqueda.get("id").toString();
			String codigoPais = pais.getCodigo();
			if (codigoPais != null) {
				List list = this.facMantCierreList;
				Map bean = sistemabusqueda;
				BeanUtils.copyProperties(f, bean);
				// CARGAMOS COMBOS
				// Map map = new HashMap();
				map.put("campanhaProceso", f.getCampanhaProceso());
				this.facRegionList = getRegionesPorCerrar(map);
				f.setNewRecord(false);
				String fechaCierre = sistemabusqueda.get("fechaCierre")
						.toString();
				f.setFechaCierreDate(DateUtil.convertStringToDate(fechaCierre));
				f.setFechaCierre(fechaCierre);

				// borrando temporales
				service.deleteTemporales();
				List listCierreRegion = service
						.getCierreFacturacionRegion(bean);// lista que ha sido
															// prograamas
				List listCierreZona = service.getCierreFacturacionZona(bean);// lista
																				// de
																				// zonas
																				// que
																				// han
																				// sido
																				// programadas

				// se obtine flag de cierre campanha
				bean.put("tipoCierre", "C");
				List listCierreCampanha = service.getCierreFacturacion(bean);
				if (listCierreCampanha.size() > 0) {
					f.setFlagCierreCampanha("true");
					sistemabusqueda.put("indicadorCierreCampanha",
							Constants.NRO_UNO);
					this.indicadorCierreCampanha= Constants.NRO_UNO;
					// session.setAttribute("indicadorCierreCampanha",
					// Constants.NRO_UNO);
				} else {
					f.setFlagCierreCampanha("false");
					sistemabusqueda.put("indicadorCierreCampanha",
							Constants.NRO_CERO);
					this.indicadorCierreCampanha= Constants.NRO_CERO;
					// session.setAttribute("indicadorCierreCampanha",
					// Constants.NRO_CERO);
				}

				bean.put("tipoCierre", null);// volvemos a us esatdo inicial
				this.facCierreRegionList = listCierreRegion;
				this.facCierreZonaList = listCierreZona;
				this.detalleCierreRegionTableModel = new DataTableModel(
						this.facCierreRegionList);
				this.detalleCierreZonaTableModel = new DataTableModel(
						this.facCierreZonaList);

				f.setIndicadorRegion(Constants.NRO_UNO);
				f.setIndicadorZona(Constants.NRO_UNO);
				f.setNewRecord(false);
			}
		}
		if (this.accion.equals(this.ACCION_CONSULTAR)) {
			this.consultar = true;
			f.setEditable(false);
			this.mostrarBotonSave = false;
			// List list =this.facMantCierreList;
			//
			// Map bean =sistemabusqueda;
			// BeanUtils.copyProperties(f, bean);
			// //CARGAMOS COMBOS
			// // Map map = new HashMap();
			// map.put("campanhaProceso",f.getCampanhaProceso());
			// this.facRegionList=getRegionesPorCerrar(map);
			// f.setNewRecord(false);
			// String fechaCierre=sistemabusqueda.get("fechaCierre").toString();
			// f.setFechaCierreDate(DateUtil.convertStringToDate(fechaCierre));
			//
			// //borrando temporales
			// service.deleteTemporales();
			// List listCierreRegion =
			// service.getCierreFacturacionRegion(bean);//lista que ha sido
			// prograamas
			// List listCierreZona =
			// service.getCierreFacturacionZona(bean);//lista de zonas que han
			// sido programadas
			//
			// //se obtine flag de cierre campanha
			// bean.put("tipoCierre", "C");
			// List listCierreCampanha = service.getCierreFacturacion(bean);
			// if (listCierreCampanha.size()>0){
			// f.setFlagCierreCampanha(Constants.NRO_UNO);
			// sistemabusqueda.put("indicadorCierreCampanha",
			// Constants.NRO_UNO);
			// //session.setAttribute("indicadorCierreCampanha",
			// Constants.NRO_UNO);
			// }
			// else{
			// f.setFlagCierreCampanha(Constants.NRO_CERO);
			// sistemabusqueda.put("indicadorCierreCampanha",
			// Constants.NRO_CERO);
			// //session.setAttribute("indicadorCierreCampanha",
			// Constants.NRO_CERO);
			// }
			//
			// bean.put("tipoCierre", null);//volvemos a us esatdo inicial
			// this.facCierreRegionList=listCierreRegion;
			// this.facCierreZonaList=listCierreZona;
			// this.detalleCierreRegionTableModel= new
			// DataTableModel(this.facCierreRegionList);
			// this.detalleCierreZonaTableModel= new
			// DataTableModel(this.facCierreZonaList);
			//
			// f.setIndicadorRegion(Constants.NRO_UNO);
			// f.setIndicadorZona(Constants.NRO_UNO);
		}

		return f;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		MantenimientoFACCierreFacturacionSearchForm f = (MantenimientoFACCierreFacturacionSearchForm) this.formBusqueda;

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());
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

		MantenimientoOCRPedidoControlFacturacionService serviceMantPedidoCtrlFact = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = serviceMantPedidoCtrlFact
				.getControlFacturacionById(criteria);

		f.setCampanhaProceso(controlFacturacion.getCodigoPeriodo());
		this.consultar = false;
		this.log.debug("Todo Ok: Redireccionando");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#devuelveMensajeKeySaveOK()
	 */
	@Override
	protected String devuelveMensajeKeySaveOK() {
		MantenimientoFACCierreFacturacionForm sistemaForm = (MantenimientoFACCierreFacturacionForm) this.formMantenimiento;
		boolean isNew = sistemaForm.isNewRecord();
		if (isNew) {
			return "mantenimientoFACCierreFacturacion.created";
		} else {
			return "mantenimientoFACCierreFacturacion.updated";
		}
	}

	private List getRegionesPorCerrar(Map map) {
		MantenimientoFACGenericoService service = (MantenimientoFACGenericoService) getBean("spusicc.mantenimientoFACGenericoService");
		List resultado = service.getRegionesPorCerrar(map);// codigo descripcion
		return resultado;
	}

	/**
	 * @param val
	 */
	public void showZonasxRegion(ValueChangeEvent val) {
		log.debug(">>showZonasxRegion ");
		log.debug("val: " + val.getNewValue().toString());
		try {
			MantenimientoFACCierreFacturacionForm f = (MantenimientoFACCierreFacturacionForm) this.formMantenimiento;
			String[] regiones = (String[]) val.getNewValue();
			if (!val.equals(null) && regiones.length > 0) {
				AjaxService ajax = (AjaxService) getBean("ajaxService");
				LabelValue[] result = ajax.getZonasMultipleByCerrar(
						f.getCodigoPais(), f.getCampanhaProceso(), regiones,
						"U", f.getIndicadorEdit());
				this.setFacZonaList(result);
				f.setZonas(null);
			} else {
				this.facZonaList = null;
				f.setZonas(null);
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	public void insertZona(ActionEvent actionEvent) {
		try {
			MantenimientoFACCierreFacturacionForm f = (MantenimientoFACCierreFacturacionForm) this.formMantenimiento;
			// Valida el ingreso de region y zona
			if (f.getRegions() == null || f.getZonas() == null) {
				this.setMensajeAlertaDefault(this
						.getResourceMessage("mantenimientoFACCierreFacturacionForm.region.zona.requerido"));
				String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
				this.getRequestContext().execute(ventanaConfirmar);
				return;
			}

			MantenimientoFACGenericoService service = (MantenimientoFACGenericoService) getBean("spusicc.mantenimientoFACGenericoService");
			f.setIndicadorZona(Constants.NRO_UNO);
			f.setIndicadorRegion(Constants.NRO_UNO);
			f.setTabSeleccion(Constants.FAC_TAB_CIERRE_ZONA);

			List list = this.facCierreZonaList;
			if (list == null)
				list = new ArrayList();

			String[] zonas = f.getZonas();

			for (int i = 0; i < zonas.length; i++) {
				if (StringUtils.isNotEmpty(zonas[i])) {
					Map bean = new HashMap();
					bean.put("campanhaProceso", f.getCampanhaProceso());
					bean.put("codigoZona", zonas[i]);
					bean.put("indicadorAccion", Constants.NUMERO_CERO);
					if (isValido(bean, list)) {// es registro valido cuando no
												// se encuntra en la lista o se
												// encuentra como eliminado
						bean.put("codigoRegion",
								service.getCodigoRegion(zonas[i]));
						bean.put("descripcionZona",
								service.getDescripcionZona(zonas[i]));
						list.add(bean);
						service.saveTemporalZona(bean);
						this.facZonaList = new LabelValue[] {};
						// this.facRegionList = new ArrayList();
						// Map map = new HashMap();
						// map.put("campanhaProceso",f.getCampanhaProceso());
						// this.facRegionList= getRegionesPorCerrar(map);
					} else {
						f.setRegions(null);
						f.setZonas(null);
						this.addError(
								"ERROR: ",
								this.getResourceMessage("mantenimientoFACCierreFacturacionForm.existe.zona.registro"));
						throw new Exception(
								this.getResourceMessage("mantenimientoFACCierreFacturacionForm.existe.zona.registro"));

					}
				} else {
					// se anhade toda la lista de todas las zonas de las
					// regiones que se han selccionado
					String[] regions = f.getRegions();
					Map criteria = new HashMap();
					criteria.put("codigoPais", f.getCodigoPais());
					criteria.put("campanhaProceso", f.getCampanhaProceso());
					criteria.put("codigoRegion", regions);
					List listZona = service.getZonasPorCerrar(criteria);
					Iterator it = listZona.iterator();
					while (it.hasNext()) {
						Map aux = (Map) it.next();
						String codigoZona = (String) aux.get("value");
						Map bean = new HashMap();
						bean.put("campanhaProceso", f.getCampanhaProceso());
						bean.put("codigoRegion",
								service.getCodigoRegion(codigoZona));
						bean.put("descripcionZona",
								service.getDescripcionZona(codigoZona));
						bean.put("indicadorAccion", Constants.NUMERO_CERO);
						list.add(bean);
						service.saveTemporalZona(bean);
						this.facZonaList = new LabelValue[] {};
						// this.facRegionList = new ArrayList();
						// Map map = new HashMap();
						// map.put("campanhaProceso",f.getCampanhaProceso());

					}
					break;
				}
			}

			// detalleCierreZonaTableModel
			f.setSelectedItemsRegion(null);
			f.setSelectedItemsZona(null);
			f.setRegions(null);
			f.setZonas(null);
			this.facCierreZonaList = list;
			this.detalleCierreZonaTableModel = new DataTableModel(
					this.facCierreZonaList);
			this.facZonaList = null;
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * Elimina logicamente el Zona
	 */
	public void deleteZona(ActionEvent actionEvent) {
		try {
			MantenimientoFACGenericoService service = (MantenimientoFACGenericoService) getBean("spusicc.mantenimientoFACGenericoService");
			MantenimientoFACCierreFacturacionForm f = (MantenimientoFACCierreFacturacionForm) this.formMantenimiento;
			f.setIndicadorZona(Constants.NRO_UNO);
			f.setIndicadorRegion(Constants.NRO_UNO);
			f.setTabSeleccion(Constants.FAC_TAB_CIERRE_ZONA);
			if (this.beanRegistroDetalleCierreZona != null) {

				List list = this.facCierreZonaList;
				List listR = this.facCierreRegionList;

				// validamos si se puede elimnar
				// es decir si no s eha programado cieeere en region
				boolean isValidoEliminar = true;
				for (int i = 0; i < this.beanRegistroDetalleCierreZona.length; i++) {
					Map bean = (Map) this.beanRegistroDetalleCierreZona[i];
					if (!validoEliminarZona(bean, listR)) {
						this.addError(
								"Error : ",
								this.getResourceMessage("mantenimientoFACCierreFacturacionForm.no.eliminar.zona"));
						isValidoEliminar = false;
						break;
					}
				}

				if (isValidoEliminar) {
					for (int i = 0; i < this.beanRegistroDetalleCierreZona.length; i++) {
						Map bean = (Map) this.beanRegistroDetalleCierreZona[i];
						bean.put("indicadorAccion", Constants.NUMERO_DOS);
						service.deleteTemporalZona(bean);
						list.remove(bean);
					}
				}

				this.facCierreZonaList = list;
				this.detalleCierreZonaTableModel = new DataTableModel(
						this.facCierreZonaList);
				this.facZonaList = null;
				this.beanRegistroDetalleCierreZona = null;
				f.setRegions(null);
				f.setZonas(null);
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * Validar Eliminar Zona
	 */
	private boolean validoEliminarZona(Map bean, List listRegion) {
		MantenimientoFACGenericoService service = (MantenimientoFACGenericoService) getBean("spusicc.mantenimientoFACGenericoService");
		String codigoRegion = (String) bean.get("codigoRegion");
		// se obtine flag de cierre campanha
		Map criteria = new HashMap();
		criteria.put("campanhaProceso", bean.get("campanhaProceso"));
		criteria.put("codigoRegion", codigoRegion);
		criteria.put("tipoCierre", "R");
		List listCierreRegion = service.getCierreFacturacion(criteria);
		if (listCierreRegion.size() > 0)
			return false;

		// puede ser que se tenga en la lista como programada
		if (listRegion != null) {
			Iterator it = listRegion.iterator();
			while (it.hasNext()) {
				Map m = (Map) it.next();
				String region = (String) m.get("codigoRegion");
				String indicadorAccion = (String) m.get("indicadorAccion");
				if (StringUtils.equals(codigoRegion, region)
						&& !Constants.NUMERO_DOS.equals(indicadorAccion)) {
					return false;
				}

			}
		}
		return true;
	}

	/**
	 * @param map
	 * @param list
	 * @return
	 */
	private boolean isValido(Map map, List list) {
		Iterator it = list.iterator();
		String codigoZona = (String) map.get("codigoZona");
		while (it.hasNext()) {
			Map mapAux = (Map) it.next();
			String codigoZonaAux = (String) mapAux.get("codigoZona");
			String indicadorAccionAux = (String) mapAux.get("indicadorAccion");
			if (codigoZona.equals(codigoZonaAux)
					&& (indicadorAccionAux.equals(Constants.NUMERO_CERO) || indicadorAccionAux
							.equals(Constants.NUMERO_UNO)))
				return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setValidarConfirmar(java.lang.String)
	 */
	public String setValidarConfirmar(String accion) {
		if (accion.equals("ELIMINAR_ZONA")) {
			if (this.beanRegistroDetalleCierreZona == null
					|| this.beanRegistroDetalleCierreZona.length == 0)
				return this.getResourceMessage("errors.select.item");
		}
		if (accion.equals("ELIMINAR_REGION")) {
			if (this.beanRegistroDetalleCierreRegion == null
					|| this.beanRegistroDetalleCierreRegion.length == 0)
				return this.getResourceMessage("errors.select.item");
		}
		if (accion.equals("APROBAR") || accion.equals("DESAPROBAR")
				|| accion.equals("DELETECIERRE")) {
			if (this.beanRegistroSeleccionado == null)
				return this.getResourceMessage("errors.select.item");
			else {
				Map sistemabusqueda = (Map) this.beanRegistroSeleccionado;
				String estadoCierre = sistemabusqueda.get("estadoCierre")
						.toString();
				String cantidad = sistemabusqueda.get("cantidadCampanha")
						.toString();
				if (accion.equals("APROBAR") && estadoCierre.equals("A")) {
					this.ventana = this
							.getResourceMessage("mantenimientoFACCierreFacturacionForm.cierre.aprobado.volver.enviar");
					return null;
				}
				if (accion.equals("APROBAR") && estadoCierre.equals("E")) {
					this.ventana = this
							.getResourceMessage("mantenimientoFACCierreFacturacionForm.cierre.aprobar");
					return null;
				}

				if (accion.equals("APROBAR") && !estadoCierre.equals("E"))
					return this
							.getResourceMessage("mantenimientoFACCierreFacturacionForm.cierre.estado.no.pendiente");
				if (accion.equals("DESAPROBAR") && !estadoCierre.equals("A"))
					return this
							.getResourceMessage("mantenimientoFACCierreFacturacionForm.cierre.estado.no.aprobado");
				if (accion.equals("DELETECIERRE") && !estadoCierre.equals("E"))
					return this
							.getResourceMessage("mantenimientoFACCierreFacturacionForm.cierre.estado.no.pendiente");
				if (accion.equals("DELETECIERRE") && !cantidad.equals("1"))
					return this
							.getResourceMessage("mantenimientoFACCierreFacturacionForm.cierre.campanha.requerido");

				return null;

			}
		}
		return null;
	}

	public String setValidarMantenimiento() {
		MantenimientoFACCierreFacturacionForm f = (MantenimientoFACCierreFacturacionForm) this.formMantenimiento;
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		if(f.isNewRecord()){
			int actual = Integer.parseInt(this.campaniaActual);
			int periodo = Integer.parseInt(f.getCampanhaProceso());
			if (periodo < actual)
				return this.getResourceMessage("mantenimientoFACCierreFacturacionForm.no.campanha.menor.requerido");
			
			String fechaCierre = DateUtil.convertDateToString(f.getFechaCierreDate());
			f.setFechaCierre(fechaCierre);
			int resultado = DateUtil.compareDates(f.getFechaProcesoActual(),f.getFechaCierre(), "dd/MM/yyyy");
			if (resultado == 1)
				return this.getResourceMessage("errors.compare.campaigns.periodo.fechaCierre.a")+ f.getFechaProcesoActual();
		}
		
		String fInicio = ajax.getFechaInicioPeriodoByPaisMarcaCanal(
				f.getCodigoPais(), "T", "VD", f.getCampanhaProceso());
		String fFin = ajax.getFechaFinalPeriodoByPaisMarcaCanal(
				f.getCodigoPais(), "T", "VD", f.getCampanhaProceso());
		int valor1 = DateUtil.compareDates(f.getFechaCierre(), fFin,
				"dd/MM/yyyy");
		int valor2 = DateUtil.compareDates(fInicio, f.getFechaCierre(),
				"dd/MM/yyyy");
		if (valor1 == 1 || valor2 == 1)
			return this
					.getResourceMessage("errors.compare.campaigns.periodo.fechaCierre")
					+ fInicio + " - " + fFin;

		if("false".equals(f.getFlagCierreCampanha())) {
			if (this.facCierreZonaList.size() <=0 && this.facCierreRegionList.size() <= 0)
				return this
						.getResourceMessage("mantenimientoFACCierreFacturacionForm.region.zona.requerido");
		}
		
		return "";
	}

	/**
	 * @param actionEvent
	 * @throws Exception
	 */
	public void aprobarCierre(ActionEvent actionEvent) {
		try {
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

			if (this.beanRegistroSeleccionado != null) {
				MantenimientoFACGenericoService service = (MantenimientoFACGenericoService) this
						.getBean("spusicc.mantenimientoFACGenericoService");
				Map map = (Map) this.beanRegistroSeleccionado;
				map.put("codigoPais", pais.getCodigo());
				String tmp = (String) map.get("estadoCierre");

				// validacion que no haya fechas anteriores sin aprobacion
				// para campanha
				if (map.get("estadoCierre") != null
						&& tmp.trim().compareTo("A") != 0) {
					List listSinAprobadar = service.getAprobadasAnteriores(map);
					if (listSinAprobadar.size() > 0) {
						String msj = this
								.getResourceMessage("mantenimientoFACCierreFacturacionForm.no.eliminar.aprobado.anterior");
						throw new Exception(msj);

					}
				}

				// VALIDAMOS FECHA DE CIERRE
				Map criteria = new HashMap();
				criteria.put("codigoPais", pais.getCodigo());
				criteria.put("estadoCampanha", Constants.NUMERO_CERO); // Indica
																		// Campanha
																		// Activa
				criteria.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica
																			// Campanha
																			// activa
																			// q
																			// se
																			// procesa
																			// actualmente

				MantenimientoOCRPedidoControlFacturacionService serviceMantPedidoCtrlFact = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
				PedidoControlFacturacion controlFacturacion = serviceMantPedidoCtrlFact
						.getControlFacturacionById(criteria);
				String fechaProceso = controlFacturacion.getFechaProceso();
				String fechaCierre = (String) map.get("fechaCierre");

				Date dFechaProceso = DateUtil.convertStringToDate(fechaProceso);
				Date dFechaCierre = DateUtil.convertStringToDate(fechaCierre);
				if (dFechaCierre.compareTo(dFechaProceso) < 0) {
					String msjFechas = this
							.getResourceMessage("mantenimientoFACCierreFacturacionForm.error.validacionFechaCierre");
					throw new Exception(msjFechas);
				}

				// Realizamos APROBACION
				map.put("login", usuario.getLogin());
				map.put("estadoRegistro", Constants.ACTIVO);
				if (map.get("estadoCierre") != null) {
					if (tmp.trim().compareTo("A") != 0) {
						map.put("estadoCierre", "A");
						service.updateConsolidadoCierreFacturacion(map);
					}
				}
				this.setFindAttributes();
				sendMail(map, Constants.NRO_UNO, pais);// aprobacion

				String enviarCorreo = (String) map.get("enviarCorreo");
				// enviamos el mensaje de satisfaccion
				if (Constants.SI.equals(enviarCorreo)) {
					if (map.get("estadoCierre") != null
							&& tmp.trim().compareTo("A") != 0) {
						this.addInfo(
								"INFO:",
								this.getResourceMessage("mantenimientoFACCierreFacturacionSearchForm.cabecera.aproved.correo"));
					} else {
						this.addInfo(
								"INFO:",
								this.getResourceMessage("mantenimientoFACCierreFacturacionSearchForm.envio.correo"));
					}
				} else {
					if (map.get("estadoCierre") != null
							&& tmp.trim().compareTo("A") != 0) {
						this.addInfo(
								"INFO:",
								this.getResourceMessage("mantenimientoFACCierreFacturacionSearchForm.cabecera.aproved"));
					} else {
						this.addInfo(
								"INFO:",
								this.getResourceMessage("mantenimientoFACCierreFacturacionSearchForm.envio.no.correo"));
					}
				}
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}

	private void sendMail(Map map, String tipo, Pais pais) {
		ReporteService reporteService = (ReporteService) this
				.getBean("scsicc.reporteService");
		MantenimientoFACGenericoService service = (MantenimientoFACGenericoService) this
				.getBean("spusicc.mantenimientoFACGenericoService");

		String campanhaproceso = (String) map.get("campanhaProceso");
		String fechaCierre = (String) map.get("fechaCierre");
		String codPais = (String) map.get("codigoPais");
		String login = (String) map.get("login");
		String descripcion = pais.getDescripcion();

		Map criteria = new HashMap();
		criteria.put("codigoPais", codPais);
		criteria.put("login", login);
		criteria.put("paisDescripcion", descripcion);
		if (tipo.equals(Constants.NRO_UNO))
			criteria.put("nombreReporte", "procesoFACAprobarCierre"); // nombre
																		// del
																		// proceso
																		// sirve
																		// para
																		// buscar
																		// en la
																		// tabla
																		// generica
																		// de
																		// envios
																		// de
																		// correo
		else
			criteria.put("nombreReporte", "procesoFACDesaprobarCierre"); // nombre
																			// del
																			// proceso
																			// sirve
																			// para
																			// buscar
																			// en
																			// la
																			// tabla
																			// generica
																			// de
																			// envios
																			// de
																			// correo

		Map paramReporte = reporteService.getParametrosReporte(criteria);
		
		String ambienteAplicacion = this.mPantallaPrincipalBean.getCurrentCountry().getAmbienteAplicacion();
		
		if (paramReporte != null) {
			paramReporte.put("correosDestinos",
					(String) paramReporte.get("correoDefault"));
			String subject = (String) paramReporte.get("subject");

			if (tipo.equals(Constants.NRO_UNO))
				subject = ambienteAplicacion.toUpperCase()+ " - " + subject + " - "
						+ StringUtils.replace(fechaCierre, "/", "-") + " - "
						+ pais.getDescripcion() + " - " + campanhaproceso;
			else
				subject = ambienteAplicacion.toUpperCase() + " - " + "Cancelación Cierres" + " - "
						+ StringUtils.replace(fechaCierre, "/", "-") + " - "
						+ pais.getDescripcion() + " - " + campanhaproceso;

			paramReporte.put("subject", subject);
			criteria.put("campanhaProceso", campanhaproceso);
			criteria.put("campanha", campanhaproceso.substring(0, 4) + "-"
					+ campanhaproceso.substring(4));
			criteria.put("fechaCierre", fechaCierre);
			criteria.put("tipoCierre", "R");
			List regionList = service.getCierreFacturacion(criteria);
			criteria.put("tipoCierre", "Z");
			List zonaList = service.getCierreFacturacion(criteria);
			criteria.put("tipoCierre", "C");
			List campList = service.getCierreFacturacion(criteria);
			String enviarCorreo = (String) paramReporte.get("enviarCorreo");
			log.debug("enviar correo " + enviarCorreo);
			map.put("enviarCorreo", enviarCorreo);
			if (Constants.SI.equals(enviarCorreo)) {
				MailParams mailParams = new MailParams();
				criteria.put("regionList", regionList);
				if (regionList.size() > 0)
					criteria.put("indicadorCierreRegion", Constants.NRO_UNO);
				else
					criteria.put("indicadorCierreRegion", Constants.NRO_CERO);
				// zonas
				criteria.put("zonaList", zonaList);
				if (zonaList.size() > 0)
					criteria.put("indicadorCierreZona", Constants.NRO_UNO);
				else
					criteria.put("indicadorCierreZona", Constants.NRO_CERO);
				// campanha
				if (campList.size() > 0)
					criteria.put("indicadorCierreCampanha", Constants.NRO_UNO);
				else
					criteria.put("indicadorCierreCampanha", Constants.NRO_CERO);

				paramReporte.put("parameterMap", criteria);

				String bodyTxt = (String) paramReporte.get("bodyTxt");
				String bodyHtml = (String) paramReporte.get("bodyHtml");
				mailParams.setQueryParams(paramReporte);

				BaseMailService mailService = (BaseMailService) this
						.getBean(this.getMailService());
				criteria.put("bodyTxt", bodyTxt);
				criteria.put("bodyHtml", bodyHtml);
				mailService.enviarMail(mailParams);
			}
		}
	}

	/**
	 * cambia de estado desparobado
	 */
	public void desaprobarCierre(ActionEvent actionEvent) {
		try {
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

			if (this.beanRegistroSeleccionado != null) {
				MantenimientoFACGenericoService service = (MantenimientoFACGenericoService) this
						.getBean("spusicc.mantenimientoFACGenericoService");

				Map map = (Map) this.beanRegistroSeleccionado;
				map.put("codigoPais", pais.getCodigo());
				// validacion que no haya fechas anteriores sin aprobacion
				// para campanha
				List listSinCerrar = service.getCanceladasPosterior(map);
				if (listSinCerrar.size() > 0) {
					throw new Exception(
							this.getResourceMessage("mantenimientoFACCierreFacturacionForm.no.eliminar.aprobado.posterior"));

				}
				map.put("login", usuario.getLogin());
				map.put("estadoCierre", "E");
				map.put("estadoRegistro", Constants.ACTIVO);
				service.updateConsolidadoCierreFacturacion(map);

				this.setFindAttributes();
				sendMail(map, Constants.NRO_CERO, pais);// DESaprobacion

				String enviarCorreo = (String) map.get("enviarCorreo");
				// enviamos el mensaje de satisfaccion
				if (Constants.SI.equals(enviarCorreo)) {
					this.addInfo(
							"INFO: ",
							this.getResourceMessage("mantenimientoFACCierreFacturacionSearchForm.cabecera.cancel.correo"));
				} else {
					// enviamos el mensaje de satisfaccion
					this.addInfo(
							"INFO: ",
							this.getResourceMessage("mantenimientoFACCierreFacturacionSearchForm.cabecera.cancel"));
				}
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * Borra el cierre de campanha
	 */
	public void deleteCierreCampania(ActionEvent actionEvent) {
		try {
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

			if (this.beanRegistroSeleccionado != null) {
				MantenimientoFACGenericoService service = (MantenimientoFACGenericoService) this
						.getBean("spusicc.mantenimientoFACGenericoService");
				Map map = (Map) this.beanRegistroSeleccionado;
				map.put("codigoPais", pais.getCodigo());
				map.put("login", usuario.getLogin());
				map.put("estadoRegistro", Constants.ESTADO_INACTIVO);
				map.put("tipoCierre", "C");
				service.updateConsolidadoCierreFacturacion(map);
				// enviamos el mensaje de satisfaccion
				this.addInfo(
						"INFO: ",
						this.getResourceMessage("mantenimientoFACCierreFacturacionSearchForm.cierre.campanha.deleted"));
				this.setFindAttributes();
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * Devuelve Service a trabajar para el envio de correo del reporte Dicho
	 * metodo debe ser sobreescrito para que devuelva el Service correspondiente
	 * al reporte en ejecución
	 */
	public String getMailService() {
		String service = "sisicc.mailUtil";// "sisicc.baseMailAbstractService";
		return service;
	}

	/***** INICIO TAB Region *****/

	/**
	 * inserta el region de una poliza
	 */
	public void insertRegion(ActionEvent actionEvent) {
		try {
			MantenimientoFACCierreFacturacionForm f = (MantenimientoFACCierreFacturacionForm) this.formMantenimiento;
			// Valida el ingreso de region y zona
			if (f.getSoloRegions() == null) {
				this.setMensajeAlertaDefault(this
						.getResourceMessage("mantenimientoMENEscaleraGananciaForm.region.zona2.requerido"));
				String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
				this.getRequestContext().execute(ventanaConfirmar);
				return;
			}

			MantenimientoFACGenericoService service = (MantenimientoFACGenericoService) getBean("spusicc.mantenimientoFACGenericoService");
			f.setIndicadorZona(Constants.NRO_UNO);
			f.setIndicadorRegion(Constants.NRO_UNO);
			f.setTabSeleccion(Constants.FAC_TAB_CIERRE_REGION);

			Map map = BeanUtils.describe(f);
			// AjaxService ajaxService = (AjaxService) getBean("ajaxService");

			List list = this.facCierreRegionList;
			if (list == null)
				list = new ArrayList();

			String[] regions = f.getSoloRegions();

			// validar que ya se programo todas sus zonas
			Map criteria = new HashMap();
			criteria.put("campanhaProceso", f.getCampanhaProceso());
			criteria.put("codigoRegion", regions);

			List listZonaPorCerrar = service.getZonasPorCerrar(criteria);
			if (listZonaPorCerrar.size() > 0)
				throw new Exception(
						this.getResourceMessage("mantenimientoFACCierreFacturacionForm.falta.zona.programar"));

			for (int i = 0; i < regions.length; i++) {
				Map bean = new HashMap();
				bean.put("campanhaProceso", f.getCampanhaProceso());
				bean.put("codigoRegion", regions[i]);
				bean.put("indicadorAccion", Constants.NUMERO_CERO);
				if (isValidoRegion(bean, list)) {// es registro valido cuando no
													// se encuntra en la lista o
													// se encuentra como
													// eliminado
					bean.put("descripcionRegion",
							service.getDescripcionRegion(regions[i]));
					list.add(bean);
					service.saveTemporalRegion(bean);
				} else {
					this.addError(
							"ERROR: ",
							this.getResourceMessage("mantenimientoFACCierreFacturacionForm.existe.region.registro"));
					throw new Exception(
							this.getResourceMessage("mantenimientoFACCierreFacturacionForm.existe.region.registro"));
				}
			}
			this.facCierreRegionList = list;
			this.facRegionList = getRegionesPorCerrar(map);
			this.detalleCierreRegionTableModel = new DataTableModel(
					this.facCierreRegionList);
			f.setSoloRegions(null);
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * El registro es valido si no se encunetra en la lista o ya se encuntra
	 * eliminado
	 */
	private boolean isValidoRegion(Map map, List list) {
		Iterator it = list.iterator();

		String codigoRegion = (String) map.get("codigoRegion");

		while (it.hasNext()) {
			Map mapAux = (Map) it.next();
			String codigoRegionAux = (String) mapAux.get("codigoRegion");
			String indicadorAccionAux = (String) mapAux.get("indicadorAccion");

			if (codigoRegion.equals(codigoRegionAux)
					&& (indicadorAccionAux.equals(Constants.NUMERO_CERO) || indicadorAccionAux
							.equals(Constants.NUMERO_UNO)))
				return false;
		}
		return true;
	}

	/**
	 * Elimina logicamente region
	 */
	public void deleteRegion(ActionEvent actionEvent) {
		try {
			MantenimientoFACCierreFacturacionForm f = (MantenimientoFACCierreFacturacionForm) this.formMantenimiento;
			MantenimientoFACGenericoService service = (MantenimientoFACGenericoService) getBean("spusicc.mantenimientoFACGenericoService");

			Map map = BeanUtils.describe(f);
			f.setIndicadorZona(Constants.NRO_UNO);
			f.setIndicadorRegion(Constants.NRO_UNO);
			f.setTabSeleccion(Constants.FAC_TAB_CIERRE_REGION);

			if (this.beanRegistroDetalleCierreRegion != null) {

				List list = this.facCierreRegionList;
				boolean isValidoEliminar = true;
				for (int j = 0; j < this.beanRegistroDetalleCierreRegion.length; j++) {
					Map bean = (Map) this.beanRegistroDetalleCierreRegion[j];
					if (!validoEliminarRegion(bean)) {
						this.addError(
								"ERROR: ",
								this.getResourceMessage("mantenimientoFACCierreFacturacionForm.no.eliminar.region"));
						isValidoEliminar = false;
						break;
					}
				}

				if (isValidoEliminar) {
					for (int i = 0; i < this.beanRegistroDetalleCierreRegion.length; i++) {
						Map bean = (Map) this.beanRegistroDetalleCierreRegion[i];
						bean.put("indicadorAccion", Constants.NUMERO_DOS);
						service.deleteTemporalRegion(bean);
						list.remove(bean);
					}
				}

				this.facCierreRegionList = list;
				this.detalleCierreRegionTableModel = new DataTableModel(
						this.facCierreRegionList);
				f.setSoloRegions(null);

			}
			// actualizamos las regiones
			map.put("flagEliminarRegion", Constants.NRO_UNO);
			this.facRegionList = getRegionesPorCerrar(map);
			map.put("flagEliminarRegion", null);
			this.beanRegistroDetalleCierreRegion = null;
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * Verifica que no haya habido programacion de cierre de campanha
	 * 
	 * @param bean
	 */
	private boolean validoEliminarRegion(Map bean) {
		MantenimientoFACGenericoService service = (MantenimientoFACGenericoService) getBean("spusicc.mantenimientoFACGenericoService");
		// se obtine flag de cierre campanha
		bean.put("tipoCierre", "C");
		List listCierreCampanha = service.getCierreFacturacion(bean);
		if (listCierreCampanha.size() > 0)
			return false;
		return true;
	}

	/**
	 * @return the facMantCierreList
	 */
	public List getFacMantCierreList() {
		return facMantCierreList;
	}

	/**
	 * @param facMantCierreList
	 *            the facMantCierreList to set
	 */
	public void setFacMantCierreList(List facMantCierreList) {
		this.facMantCierreList = facMantCierreList;
	}

	/**
	 * @return the facRegionList
	 */
	public List getFacRegionList() {
		return facRegionList;
	}

	/**
	 * @param facRegionList
	 *            the facRegionList to set
	 */
	public void setFacRegionList(List facRegionList) {
		this.facRegionList = facRegionList;
	}

	/**
	 * @return the facZonaList
	 */
	public LabelValue[] getFacZonaList() {
		return facZonaList;
	}

	/**
	 * @param facZonaList
	 *            the facZonaList to set
	 */
	public void setFacZonaList(LabelValue[] facZonaList) {
		this.facZonaList = facZonaList;
	}

	/**
	 * @return the indHabilitar
	 */
	public String getIndHabilitar() {
		return indHabilitar;
	}

	/**
	 * @param indHabilitar
	 *            the indHabilitar to set
	 */
	public void setIndHabilitar(String indHabilitar) {
		this.indHabilitar = indHabilitar;
	}

	/**
	 * @return the valorUno
	 */
	public String getValorUno() {
		return valorUno;
	}

	/**
	 * @param valorUno
	 *            the valorUno to set
	 */
	public void setValorUno(String valorUno) {
		this.valorUno = valorUno;
	}

	/**
	 * @return the facCierreZonaList
	 */
	public List getFacCierreZonaList() {
		return facCierreZonaList;
	}

	/**
	 * @param facCierreZonaList
	 *            the facCierreZonaList to set
	 */
	public void setFacCierreZonaList(List facCierreZonaList) {
		this.facCierreZonaList = facCierreZonaList;
	}

	/**
	 * @return the facCierreRegionList
	 */
	public List getFacCierreRegionList() {
		return facCierreRegionList;
	}

	/**
	 * @param facCierreRegionList
	 *            the facCierreRegionList to set
	 */
	public void setFacCierreRegionList(List facCierreRegionList) {
		this.facCierreRegionList = facCierreRegionList;
	}

	/**
	 * @return the consultar
	 */
	public boolean isConsultar() {
		return consultar;
	}

	/**
	 * @param consultar
	 *            the consultar to set
	 */
	public void setConsultar(boolean consultar) {
		this.consultar = consultar;
	}

	/**
	 * @return the detalleCierreZonaTableModel
	 */
	public DataTableModel getDetalleCierreZonaTableModel() {
		return detalleCierreZonaTableModel;
	}

	/**
	 * @param detalleCierreZonaTableModel
	 *            the detalleCierreZonaTableModel to set
	 */
	public void setDetalleCierreZonaTableModel(
			DataTableModel detalleCierreZonaTableModel) {
		this.detalleCierreZonaTableModel = detalleCierreZonaTableModel;
	}

	/**
	 * @return the beanRegistroDetalleCierreZona
	 */
	public Object[] getBeanRegistroDetalleCierreZona() {
		return beanRegistroDetalleCierreZona;
	}

	/**
	 * @param beanRegistroDetalleCierreZona
	 *            the beanRegistroDetalleCierreZona to set
	 */
	public void setBeanRegistroDetalleCierreZona(
			Object[] beanRegistroDetalleCierreZona) {
		this.beanRegistroDetalleCierreZona = beanRegistroDetalleCierreZona;
	}

	/**
	 * @return the detalleCierreRegionTableModel
	 */
	public DataTableModel getDetalleCierreRegionTableModel() {
		return detalleCierreRegionTableModel;
	}

	/**
	 * @param detalleCierreRegionTableModel
	 *            the detalleCierreRegionTableModel to set
	 */
	public void setDetalleCierreRegionTableModel(
			DataTableModel detalleCierreRegionTableModel) {
		this.detalleCierreRegionTableModel = detalleCierreRegionTableModel;
	}

	/**
	 * @return the beanRegistroDetalleCierreRegion
	 */
	public Object[] getBeanRegistroDetalleCierreRegion() {
		return beanRegistroDetalleCierreRegion;
	}

	/**
	 * @param beanRegistroDetalleCierreRegion
	 *            the beanRegistroDetalleCierreRegion to set
	 */
	public void setBeanRegistroDetalleCierreRegion(
			Object[] beanRegistroDetalleCierreRegion) {
		this.beanRegistroDetalleCierreRegion = beanRegistroDetalleCierreRegion;
	}

	public String getVentana() {
		return ventana;
	}

	public void setVentana(String ventana) {
		this.ventana = ventana;
	}

	public String getCampaniaActual() {
		return campaniaActual;
	}

	public void setCampaniaActual(String campaniaActual) {
		this.campaniaActual = campaniaActual;
	}

}
