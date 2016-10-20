package biz.belcorp.ssicc.web.spusicc.reclamos.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;
import org.primefaces.event.FileUploadEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.sisicc.model.Cliente;
import biz.belcorp.ssicc.dao.spusicc.reclamos.model.ExcepcionesValidaciones;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.exception.InvalidDescriptionException;
import biz.belcorp.ssicc.service.exception.InvalidIdentifierException;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoExcepcionesValidacionesService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECIngresoAtencionesService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEjecucionValidacionesService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.action.ReporteSTOExcepcionesValidacionesAction;
import biz.belcorp.ssicc.web.scsicc.hip.action.BusquedaConsultoraPOPUPSearchAction;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.ConsultaOCRSolicitudesCreditoForm;
import biz.belcorp.ssicc.web.spusicc.reclamos.form.MantenimientoExcepcionesValidacionesForm;
import biz.belcorp.ssicc.web.spusicc.reclamos.form.MantenimientoExcepcionesValidacionesSearchForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MantenimientoExcepcionesValidacionesSearchAction extends
		BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = 6974155976333471413L;

	private List stoTipoDocumentoExcepcionList = new ArrayList();
	private List siccRegionList = new ArrayList();
	private List siccZonaList = new ArrayList();
	private List stoExcepcionesList = new ArrayList();
	private List stoValidacionesExcepcionByDocumento = new ArrayList();
	private List stoExcepcionClientesList = new ArrayList();
	private String indExito;
	private String numeroErrados;
	private boolean indicadorRegionesBool;
	private boolean mostrarGrilla;
	private boolean estadoFecha;
	private String lengthCodigoConsultora;

	private List columnasSeleccionadas = new ArrayList();

	@ManagedProperty(value = "#{busquedaConsultoraPOPUPSearchAction}")
	private BusquedaConsultoraPOPUPSearchAction popupBusquedaConsultora;

	@ManagedProperty(value = "#{reporteSTOExcepcionesValidacionesAction}")
	private ReporteSTOExcepcionesValidacionesAction reporte;

	private boolean mostrarPopupConsultora = false;
	private static final String POPUP_CONSULTORA = "SCONSULTORA";

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#getSalirForward()
	 */
	@Override
	protected String getSalirForward() {
		return "mantenimientoExcepcionesValidacionesList";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoExcepcionesValidacionesForm";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoExcepcionesValidacionesSearchForm searchForm = new MantenimientoExcepcionesValidacionesSearchForm();
		return searchForm;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'MantenimientoExcepcionesValidacionesSearchAction - search' method");
		}
		

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoExcepcionesValidacionesSearchForm searchForm = (MantenimientoExcepcionesValidacionesSearchForm) this.formBusqueda;
		if(this.formMantenimiento != null){
			MantenimientoExcepcionesValidacionesForm editForm = (MantenimientoExcepcionesValidacionesForm) this.formMantenimiento;

			searchForm.setTipoDocumento(StringUtils.isNotBlank(editForm.getTipoDocumento()) ? editForm.getTipoDocumento() : searchForm.getTipoDocumento());
			searchForm.setValidaciones(StringUtils.isNotBlank(editForm.getValidaciones()) ? editForm.getValidaciones() : searchForm.getValidaciones());
			searchForm.setCodigoPeriodo(StringUtils.isNotBlank(editForm.getCodigoPeriodo()) ? editForm.getCodigoPeriodo() : searchForm.getCodigoPeriodo());
			
			if(!StringUtils.isBlank(editForm.getCodigoConsultora())){
				searchForm.setCodigoConsultora(editForm.getCodigoConsultora());
			}
			//searchForm.setValidaciones("");
			
		}
		
//		Para mostrar un item vacio
		List l = new ArrayList();
		Base base = new Base();
		base.setCodigo("");
		base.setDescripcion("");
		l.add(base);
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		LabelValue[] lv = ajax.getValidacionesExcepcionByDocumento(pais.getCodigo(), searchForm.getTipoDocumento());
		for (int i = 0; i < lv.length; i++) {
			Base cargaLista = new Base();
			cargaLista.setCodigo(lv[i].getValue());
			cargaLista.setDescripcion(lv[i].getLabel());
			l.add(cargaLista);
		};
		this.stoValidacionesExcepcionByDocumento = l;
		
		searchForm.setFecha("");
		if (searchForm.getFechaDate() != null)
			searchForm.setFecha(DateUtil.convertDateToString(searchForm
					.getFechaDate()));

		Map criteriaSearch = new HashMap();
		criteriaSearch.put("codigoPais", pais.getCodigo());

		if (StringUtils.isNotBlank(searchForm.getValidaciones())) {
			criteriaSearch.put("validacion", searchForm.getValidaciones());
		}

		if (searchForm.getRegionList() != null
				&& searchForm.getRegionList().length > 0) {
			boolean todos = false;
			for (int i = 0; i < searchForm.getRegionList().length; i++) {
				if (searchForm.getRegionList()[i].equals("Todos")) {
					todos = true;
				}
			}
			if (!todos) {
				criteriaSearch.put("codigoRegion", searchForm.getRegionList());
			}
		} else {
			criteriaSearch.put("codigoRegion", new String[0]);
		}

		if (searchForm.getZonaList() != null
				&& searchForm.getZonaList().length > 0) {
			boolean todos = false;
			for (int i = 0; i < searchForm.getZonaList().length; i++) {
				if (searchForm.getZonaList()[i].equals("Todos")) {
					todos = true;
				}
			}
			if (!todos) {
				criteriaSearch.put("codigoZona", searchForm.getZonaList());
			}
		} else {
			criteriaSearch.put("codigoZona", new String[0]);
		}
		searchForm.setZonaList(null);

		if (StringUtils.isNotBlank(searchForm.getCodigoPeriodo())) {
			criteriaSearch.put("codigoPeriodo", searchForm.getCodigoPeriodo());
		}

		if (StringUtils.isNotBlank(searchForm.getCodigoConsultora())) {
			criteriaSearch.put("codigoConsultora",
					searchForm.getCodigoConsultora());
		}

		if (StringUtils.isNotBlank(searchForm.getFecha())) {
			criteriaSearch.put("fecha", searchForm.getFecha());
		}
		criteriaSearch.put("tipoDocumento", searchForm.getTipoDocumento());
		MantenimientoExcepcionesValidacionesService serviceMant = (MantenimientoExcepcionesValidacionesService) getBean("spusicc.mantenimientoExcepcionesValidacionesService");
		this.stoExcepcionesList = serviceMant
				.getExcepcionesValidaciones(criteriaSearch);

		return this.stoExcepcionesList;
	}	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#verificarRegistroSeleccionado()
	 */
	@Override
	protected boolean verificarRegistroSeleccionado() {
		
		boolean verificar= true;
		if(StringUtils.equals(this.accion, "ELIMINAR")){
			
			try {
				if (this.columnasSeleccionadas == null || this.columnasSeleccionadas.size()==0)
					verificar = false;
			}	
			catch (Exception e) {		
				verificar = false;
			}
			if (!verificar) 
				this.addWarn("Warning: ",this.getResourceMessage("errors.select.items"));
			else {
//				this.mPantallaPrincipalBean.setCriteriosBusqueda(this.formBusqueda);			
//				this.mPantallaPrincipalBean.setManageBeanPadre(this);
			}		
			this.seleccionoRegistro = verificar;
			
		}
		
		return verificar;

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
			log.debug("Entering 'delete' method");
		}

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		String[] params = new String[this.columnasSeleccionadas.size()]; 
				
		for (int i = 0; i < this.columnasSeleccionadas.size(); i++) {
			ExcepcionesValidaciones registroSeleccionado = (ExcepcionesValidaciones) this.columnasSeleccionadas.get(i);
			params[i]=	Long.toString(registroSeleccionado.getCodigo());
			
		}
		
		Map criteriaSearch = new HashMap();
		criteriaSearch.put("codigoPais", pais.getCodigo());
		criteriaSearch.put("codigo", params);

		MantenimientoExcepcionesValidacionesService serviceMant = (MantenimientoExcepcionesValidacionesService) getBean("spusicc.mantenimientoExcepcionesValidacionesService");
		List registrosList = (List) serviceMant
				.getExcepcionesValidaciones(criteriaSearch);

		Map criteria = new HashMap();

		for (int i = 0; i < registrosList.size(); i++) {
			ExcepcionesValidaciones excepcionesValidaciones = (ExcepcionesValidaciones) registrosList
					.get(i);
			excepcionesValidaciones
					.setFechaModificacion(excepcionesValidaciones
							.getFechaCreacion());
			excepcionesValidaciones
					.setUsuarioModificacion(excepcionesValidaciones
							.getUsuarioCreacion());
			excepcionesValidaciones.setIndicador("9");

			criteria.put("codigo", excepcionesValidaciones.getCodigo());
			serviceMant.removeExcepcionesValidacione(criteria);

			serviceMant.insertExcepcionesValidacionesHistorico(
					excepcionesValidaciones, usuario);
		}
		

		return true;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#devuelveMensajeKeyEliminarOK()
	 */
	@Override
	protected String devuelveMensajeKeyEliminarOK() {
		return "mantenimientoExcepcionesValidacionesSearchForm.delete.success";
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
			log.debug("Entering 'save' method");
		}

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		MantenimientoExcepcionesValidacionesForm editForm = (MantenimientoExcepcionesValidacionesForm) this.formMantenimiento;
		editForm.setCodigoPais(pais.getCodigo());
		if (editForm.getFechaDate() != null)
			editForm.setFecha(DateUtil.convertDateToString(editForm
					.getFechaDate()));

		String codigoPais = editForm.getCodigoPais();
		String tipoDocumento = editForm.getTipoDocumento();
		String validaciones = editForm.getValidaciones();

		if (editForm.getRegionList() != null) {
			if (editForm.getRegionList().length == 1) {
				if (editForm.getRegionList()[0].equals(""))
					editForm.setRegionList(null);
			}
		}
		String[] regionList = editForm.getRegionList();

		if (editForm.getZonaList() != null) {
			if (editForm.getZonaList().length == 1) {
				if (editForm.getZonaList()[0].equals(""))
					editForm.setZonaList(null);
			}
		}
		String[] zonaList = editForm.getZonaList();

		String codigoPeriodo = editForm.getCodigoPeriodo();
		String codigoConsultora = editForm.getCodigoConsultora();

		// String[] clienteList =
		// request.getSession().getAttribute(Constants.STO_EXCEPCION_CLIENTES_LIST);
		List listaClientes = this.stoExcepcionClientesList;
		log.debug("stoExcepcionClientesList : " + listaClientes.size());

		editForm.setClienteList(null);

		int size = 0;
		if (listaClientes != null) {
			size = listaClientes.size();
		}

		Long longitudPais = pais.getLongitudCodigoCliente();
		Map map = new HashMap();
		String codCliente = "";
		String[] clienteList = new String[size];

		for (int i = 0; i < size; i++) {
			map = (Map) listaClientes.get(i);
			codCliente = (String) map.get("codigoCliente");
			clienteList[i] = codCliente;
		}

		editForm.setClienteList(clienteList);

		String codigosErradosFile = editForm.getCodigosErradosFile();
		String fecha = editForm.getFecha();
		int errados = editForm.getErrados();

		boolean existeRegistro = false;

		try {
			if (errados == 0) {

				boolean isNew = editForm.isNewRecord();
				// Creamos la instancia del servicio y le asignamos
				// el usuario que va a realizar las operaciones
				MantenimientoExcepcionesValidacionesService serviceMant = (MantenimientoExcepcionesValidacionesService) getBean("spusicc.mantenimientoExcepcionesValidacionesService");

				if (isNew) {
					Map criteriaObtenerTipoDoc = new HashMap();
					criteriaObtenerTipoDoc.put("codigoPais", codigoPais);
					criteriaObtenerTipoDoc.put("codigoValidacion",
							editForm.getValidaciones());
					ProcesoSTOService procesoSTOService = (ProcesoSTOService) getBean("spusicc.procesoSTOService");
					String codigoTipoDocumento = procesoSTOService
							.getTipoDocumentoByValidacion(criteriaObtenerTipoDoc);

					if ((listaClientes == null || (listaClientes != null && listaClientes
							.size() < 1))
							&& (zonaList == null || (zonaList != null && zonaList.length == 1))
							&& (regionList == null || (regionList != null && regionList.length == 1))) {
						Map criteriaVerificacion = new HashMap();
						criteriaVerificacion.put("codigoPais",
								editForm.getCodigoPais());
						criteriaVerificacion.put("tipoDocumento",
								codigoTipoDocumento);
						criteriaVerificacion.put("validacion",
								editForm.getValidaciones());
						criteriaVerificacion.put("codigoRegion", regionList[0]);
						criteriaVerificacion.put("codigoZona", zonaList[0]);
						criteriaVerificacion.put("codigoPeriodo",
								editForm.getCodigoPeriodo());
						criteriaVerificacion.put("codigoConsultora",
								editForm.getCodigoConsultora());
						criteriaVerificacion.put("fecha", editForm.getFecha());
						List listaExistentes = serviceMant
								.getExcepcionesValidacionesExist(criteriaVerificacion);
						if (listaExistentes != null
								&& listaExistentes.size() > 0) {
							// this.indExito = "3";
							this.getResourceMessage("mantenimientoExcepcionesValidacionesForm.error.existe");
						}
					}

					ExcepcionesValidaciones excepcionesValidaciones = new ExcepcionesValidaciones();
					excepcionesValidaciones = getParametrosExcepcionesValidaciones(editForm);
					excepcionesValidaciones
							.setTipoDocumento(codigoTipoDocumento);

					if (zonaList != null && zonaList.length > 0) {
						List listRegionesZonas = new ArrayList();
						Map criteriaRegion = new HashMap();
						criteriaRegion.put("codigoPais",
								editForm.getCodigoPais());

						for (int i = 0; i < zonaList.length; i++) {
							criteriaRegion.put("codigoZona", zonaList[i]);
							InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
							List listaRegion = service.getLista(
									"getRegionByZona", criteriaRegion);
							excepcionesValidaciones.setCodigoZona(zonaList[i]);

							Base base = new Base();
							if (listaRegion != null && listaRegion.size() > 0) {
								base = (Base) listaRegion.get(0);
								listRegionesZonas = this.addRegionToList(
										listRegionesZonas, base.getCodigo());
								excepcionesValidaciones.setCodigoRegion(base
										.getCodigo());
							}

							if (clienteList != null && clienteList.length > 0) {

								for (int k = 0; k < clienteList.length; k++) {
									excepcionesValidaciones
											.setCodigoCliente(clienteList[k]);
									if (!this
											.existeExcepcionesValidaciones(excepcionesValidaciones)) {
										serviceMant
												.insertExcepcionesValidaciones(
														excepcionesValidaciones,
														usuario);
									} else {
										existeRegistro = true;
									}
								}

							} else {
								excepcionesValidaciones
										.setCodigoCliente(editForm
												.getCodigoConsultora());
								if (!this
										.existeExcepcionesValidaciones(excepcionesValidaciones)) {
									serviceMant.insertExcepcionesValidaciones(
											excepcionesValidaciones, usuario);
								} else {
									existeRegistro = true;
								}
							}
						}

						if (regionList != null && regionList.length > 0) {
							boolean existe = false;
							for (int i = 0; i < regionList.length; i++) {
								int k = 0;
								String region = null;
								existe = false;
								while (k < listRegionesZonas.size()) {
									region = (String) listRegionesZonas.get(k);
									if (region.equals(regionList[i])) {
										existe = true;
									}
									k++;
								}

								if (!existe) {
									excepcionesValidaciones
											.setCodigoRegion(regionList[i]);
									excepcionesValidaciones.setCodigoZona(null);
									if (clienteList != null
											&& clienteList.length > 0) {
										for (int j = 0; j < clienteList.length; j++) {
											excepcionesValidaciones
													.setCodigoCliente(clienteList[j]);
											if (!this
													.existeExcepcionesValidaciones(excepcionesValidaciones)) {
												serviceMant
														.insertExcepcionesValidaciones(
																excepcionesValidaciones,
																usuario);
											} else {
												existeRegistro = true;
											}
										}
									} else {
										excepcionesValidaciones
												.setCodigoCliente(editForm
														.getCodigoConsultora());
										if (!this
												.existeExcepcionesValidaciones(excepcionesValidaciones)) {
											serviceMant
													.insertExcepcionesValidaciones(
															excepcionesValidaciones,
															usuario);
										} else {
											existeRegistro = true;
										}
									}
								}
							}
						}

					} else {
						excepcionesValidaciones.setCodigoZona(null);
						if (regionList != null && regionList.length > 0) {
							for (int i = 0; i < regionList.length; i++) {
								excepcionesValidaciones
										.setCodigoRegion(regionList[i]);
								if (clienteList != null
										&& clienteList.length > 0) {
									if (clienteList != null
											&& clienteList.length > 0) {
										for (int j = 0; j < clienteList.length; j++) {
											excepcionesValidaciones
													.setCodigoCliente(clienteList[j]);
											if (!this
													.existeExcepcionesValidaciones(excepcionesValidaciones)) {
												serviceMant
														.insertExcepcionesValidaciones(
																excepcionesValidaciones,
																usuario);
											} else {
												existeRegistro = true;
											}
										}
									}
								} else {
									excepcionesValidaciones
											.setCodigoCliente(editForm
													.getCodigoConsultora());
									if (!this
											.existeExcepcionesValidaciones(excepcionesValidaciones)) {
										serviceMant
												.insertExcepcionesValidaciones(
														excepcionesValidaciones,
														usuario);
									} else {
										existeRegistro = true;
									}
								}
							}
						} else {
							if (clienteList != null && clienteList.length > 0) {
								for (int j = 0; j < clienteList.length; j++) {
									excepcionesValidaciones
											.setCodigoCliente(clienteList[j]);
									if (!this
											.existeExcepcionesValidaciones(excepcionesValidaciones)) {
										serviceMant
												.insertExcepcionesValidaciones(
														excepcionesValidaciones,
														usuario);
									} else {
										existeRegistro = true;
									}
								}
							} else {
								excepcionesValidaciones
										.setCodigoCliente(editForm
												.getCodigoConsultora());
								if (!this
										.existeExcepcionesValidaciones(excepcionesValidaciones)) {
									serviceMant.insertExcepcionesValidaciones(
											excepcionesValidaciones, usuario);
								} else {
									existeRegistro = true;
								}
							}
						}
					}

					if (existeRegistro) {
						// this.indExito = "2";
						this.addWarn(
								"",
								this.getResourceMessage("mantenimientoExcepcionesValidacionesForm.mensaje.existe"));
						return false;
					} else {
						// this.indExito = "1";
						// this.getResourceMessage("mantenimientoExcepcionesValidacionesForm.exito");
						// this.stoExcepcionClientesList = new ArrayList();
					}
				}
			} else {
				// this.indExito = "0";
				this.numeroErrados = codigosErradosFile;
				this.getResourceMessage("errors.detail",
						new Object[] { this.numeroErrados });
			}
		} catch (InvalidIdentifierException iie) {
			String codigo = iie.getIdentifier().toString();
			throw new Exception(this.getResourceMessage("errors.invalid.id",
					new Object[] { codigo }));

		} catch (InvalidDescriptionException ide) {
			String descripcion = ide.getDescription();
			throw new Exception(this.getResourceMessage(
					"errors.invalid.description", new Object[] { descripcion }));
		}
		return true;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'edit' method");
		}
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		this.estadoFecha = true;
		Map criteria = new HashMap();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		MantenimientoExcepcionesValidacionesForm f = new MantenimientoExcepcionesValidacionesForm();

		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoUsuario", usuario.getLogin());

		ProcesoSTOEjecucionValidacionesService procesoSTOEjecucionValidacionesService = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");

		f.setTipoDocumento(null);
		f.setValidaciones(null);

		this.stoTipoDocumentoExcepcionList = procesoSTOEjecucionValidacionesService
				.getTiposDocumentosExcepcionSTO(criteria);
		this.stoValidacionesExcepcionByDocumento = new ArrayList();

		f.setRegionList(new String[0]);
		f.setZonaList(new String[0]);
		this.siccRegionList = reporteService.getListaGenerico(
				"getRegionesByPais", criteria);
		this.siccZonaList = new ArrayList();
		this.stoExcepcionClientesList = new ArrayList();
		
		this.lengthCodigoConsultora =  ajax.getLongitudCodCliente(pais.getCodigo());		        


		f.setErrados(0);
		f.setClienteList(null);
		f.setCodigoPeriodo(null);
		f.setCodigoConsultora(null);
		f.setClienteFile(null);
		f.setFecha(null);
		f.setViewRegiones("0");
		f.setIndicadorClientes("0");
		f.setCodigoPais(pais.getCodigo());
		f.setTipoDocumento("");
		this.indicadorRegionesBool = false;
		this.mostrarGrilla = false;

		return f;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarBotonConsultar = false;
		this.mostrarBotonModificar = false;
		this.mostrarListaBusqueda = false;
		this.mostrarBotonEliminar = false;
		
		ProcesoSTOEjecucionValidacionesService procesoSTOEjecucionValidacionesService = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Map criteria = new HashMap();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoUsuario", usuario.getLogin());
		this.stoTipoDocumentoExcepcionList = procesoSTOEjecucionValidacionesService
				.getTiposDocumentosExcepcionSTO(criteria);
		this.siccRegionList = reporteService.getListaGenerico(
				"getRegionesByPais", criteria);
		this.mostrarPaginacionDatatableSpinner = false;
		this.nroPaginacionDatatable = 25;
		
//		Para mostrar un item vacio
		Base base = new Base();
		base.setCodigo("");
		base.setDescripcion("");
		this.stoValidacionesExcepcionByDocumento.add(base);

		
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setInvocarPopup(java.lang.String)
	 */
	@Override
	protected void setInvocarPopup(String accion) {
		this.mostrarProcesoBatch = false;

		if (accion.equals(this.POPUP_CONSULTORA)) {
			this.mostrarPopupConsultora = true;
		} else {
			if (accion.equals("SCONSULTORAS")) {
				this.mostrarPopupConsultora = true;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setAceptarPopup(javax.faces.event.ActionEvent, java.lang.String)
	 */
	@Override
	protected void setAceptarPopup(ActionEvent event, String accion) {
		if (log.isDebugEnabled()) {
			log.debug("Finish 'setAceptarPopup' method");
		}
		if (accion.equals(this.POPUP_CONSULTORA)) {
			this.popupBusquedaConsultora.verificarRegistro(event);
			if (this.popupBusquedaConsultora.isSeleccionoRegistro()) {
				Cliente clienteMap = (Cliente) this.popupBusquedaConsultora
						.getBeanRegistroSeleccionado();
				MantenimientoExcepcionesValidacionesForm f = (MantenimientoExcepcionesValidacionesForm) this.formMantenimiento;
				MantenimientoExcepcionesValidacionesSearchForm fs = (MantenimientoExcepcionesValidacionesSearchForm) this.formBusqueda;

				f.setCodigoConsultora(clienteMap.getCodigo());
				fs.setCodigoConsultora(clienteMap.getCodigo());
				this.popupBusquedaConsultora.setBeanRegistroSeleccionado(null);
			}
		} else {
			this.popupBusquedaConsultora.verificarRegistro(event);
			if (this.popupBusquedaConsultora.isSeleccionoRegistro()) {
				Cliente clienteMap = (Cliente) this.popupBusquedaConsultora
						.getBeanRegistroSeleccionado();
				MantenimientoExcepcionesValidacionesSearchForm fs = (MantenimientoExcepcionesValidacionesSearchForm) this.formBusqueda;

				fs.setCodigoConsultora(clienteMap.getCodigo());
				this.popupBusquedaConsultora.setBeanRegistroSeleccionado(null);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setSalirPopup()
	 */
	@Override
	protected void setSalirPopup() {
		this.mostrarPopupConsultora = false;
		this.popupBusquedaConsultora.setBeanRegistroSeleccionado(null);
	}

	
	/**
	 * Ceros izquierda.
	 * 
	 * @param query
	 *            the query
	 * @return the list
	 */
	public void cerosIzquierda() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'cerosIzquierda' method");
		}

		MantenimientoExcepcionesValidacionesForm f = (MantenimientoExcepcionesValidacionesForm) this.formMantenimiento;

		if (StringUtils.isNotBlank(f.getCodigoConsultora())) {
			if (f.getCodigoConsultora().length() <= Integer.parseInt(this.getLengthCodigoConsultora()))
				f.setCodigoConsultora(StringUtils.leftPad(f.getCodigoConsultora(),Integer.parseInt(this.getLengthCodigoConsultora()),
						"0"));
			else {
				int tamanio = f.getCodigoConsultora().length() - Integer.parseInt(this.getLengthCodigoConsultora());
				String codFinal = f.getCodigoConsultora().substring(tamanio);
				f.setCodigoConsultora(codFinal);
			}
		}
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setValidarMantenimiento()
	 */
	@Override
	public String setValidarMantenimiento() {
		String mensaje = null;
		MantenimientoExcepcionesValidacionesForm f = (MantenimientoExcepcionesValidacionesForm) this.formMantenimiento;
		int salirRegion = 0, salirZona = 0;
		
		
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		
		
		
		if (f.getTipoDocumento().equalsIgnoreCase("OCC")) {
			if (f.getRegionList().length > 0) {
				for (String region : f.getRegionList()) {
					if (region.equalsIgnoreCase(""))
						salirRegion = 1;
				}
			} else
				salirRegion = 1;

			if (f.getZonaList().length > 0) {
				for (String zona : f.getZonaList()) {
					if (zona.equalsIgnoreCase(""))
						salirZona = 1;
				}
			} else
				salirZona = 1;

			log.debug("salirZona "+salirZona);
			log.debug("f.getIndicadorClientes() "+f.getIndicadorClientes());
			log.debug("salirRegion "+salirRegion);
			log.debug("f.getCodigoConsultora() "+f.getCodigoConsultora());
			log.debug("f.getClienteFile() "+f.getClienteFile());
			
			
			if (salirZona == 1
					&& f.getIndicadorClientes().equals("0")
					&& salirRegion == 1
					&& (f.getCodigoConsultora() == null || f
							.getCodigoConsultora().equals(""))
					&& f.getClienteFile() == null) {
				mensaje = this
						.getResourceMessage("mantenimientoExcepcionesValidacionesForm.error.tipoOCC");
				return mensaje;
			}
		}
		
		if(!StringUtils.isBlank(f.getCodigoConsultora())){
			String [] arregloValidarConsultora =  ajax.getConsultoraByCriteria(f.getCodigoPais(), f.getCodigoConsultora());
			if(arregloValidarConsultora == null){
				return this.getResourceMessage("mantenimientoExcepcionesValidacionesForm.error.consultora1");
			}
		}
		
		
		if (f.getClienteFile() != null) {
			if (f.getIndicadorClientes().equals("0")) {
				mensaje = this
						.getResourceMessage("mantenimientoExcepcionesValidacionesForm.error.listaConsultora");
				return mensaje;
			} else {
				if (f.getErrados() > 0) {
					mensaje = this
							.getResourceMessage("mantenimientoExcepcionesValidacionesForm.error.listaConsultoraErrados");
					return mensaje;
				}

			}
		}

		if (f.getIndicadorClientes().equals("1")) {
			if (f.getErrados() > 0) {
				mensaje = this
						.getResourceMessage("mantenimientoExcepcionesValidacionesForm.error.listaConsultoraErrados");
				return mensaje;
			}
		}
		
		

		return mensaje;
	}
	
	/**
	 * Caargando los tipos de documentos
	 * 
	 * @param event
	 */
	public void loadTipoDocumento(ValueChangeEvent event) {
		try {
			this.stoValidacionesExcepcionByDocumento = new ArrayList();

			String valor = (String) event.getNewValue();
			MantenimientoExcepcionesValidacionesSearchForm f = (MantenimientoExcepcionesValidacionesSearchForm) this.formBusqueda;
			f.setTipoDocumento(valor);
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			List l = new ArrayList();

			if (f.getTipoDocumento() != null) {
				LabelValue[] lv = ajax.getValidacionesExcepcionByDocumento(
						pais.getCodigo(), f.getTipoDocumento());
				for (int i = 0; i < lv.length; i++) {
					Base base = new Base();
					base.setCodigo(lv[i].getValue());
					base.setDescripcion(lv[i].getLabel());
					l.add(base);
				}
				;
				this.stoValidacionesExcepcionByDocumento = l;

				if (f.getTipoDocumento().equalsIgnoreCase("OCC")) {
					f.setCodigoPeriodo(ajax
							.getPeriodoFechaAndCampanyaActivaCad().split(",")[0]);
				} else {
					f.setCodigoPeriodo(null);
				}
			} else {
				this.stoValidacionesExcepcionByDocumento = null;
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));

		}

	}

	/**
	 * Carga el tipo de documento
	 * 
	 * @param event
	 */
	public void loadTipoDocumentoForm(ValueChangeEvent event) {
		try {
			
			String valor = (String) event.getNewValue();
			MantenimientoExcepcionesValidacionesForm f = (MantenimientoExcepcionesValidacionesForm) this.formMantenimiento;
			f.setTipoDocumento(valor);
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			List l = new ArrayList();
                 
			
			
			if (StringUtils.isNotBlank(f.getTipoDocumento())) {
				LabelValue[] lv = ajax.getValidacionesExcepcionByDocumento(
						pais.getCodigo(), f.getTipoDocumento());
				for (int i = 0; i < lv.length; i++) {
					Base base = new Base();
					base.setCodigo(lv[i].getValue());
					base.setDescripcion(lv[i].getLabel());
					l.add(base);
				}
				;

				this.stoValidacionesExcepcionByDocumento = l;

				if (f.getTipoDocumento().equalsIgnoreCase("OCC")) {
					estadoFecha=false;
					f.setCodigoPeriodo(ajax
							.getPeriodoFechaAndCampanyaActivaCad().split(",")[0]);
				} else {
					estadoFecha=true;
					f.setCodigoPeriodo(null);
				}
			} else {
				estadoFecha=true;
				this.stoValidacionesExcepcionByDocumento = null;
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));

		}

	}

	/**
	 * Carga las zonas respecto a la region
	 * 
	 * @param event
	 */
	public void loadZonas(ValueChangeEvent event) {
		try {
			String[] regiones = (String[]) event.getNewValue();
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			List l = new ArrayList();

			if (regiones.length > 0) {
				LabelValue[] lv = ajax.getZonasMultipleByPaisMarcaCanalRegion(
						pais.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,
						Constants.CODIGO_CANAL_DEFAULT, regiones,
						Constants.FORMATEAR_TODOS);
				for (int i = 1; i < lv.length; i++) {
					Base base = new Base();
					base.setCodigo(lv[i].getValue());
					base.setDescripcion(lv[i].getLabel());
					l.add(base);
				}
				;

				this.siccZonaList = l;
			} else
				this.siccZonaList = null;
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * @param excepcionesValidaciones
	 * @return
	 */
	private boolean existeExcepcionesValidaciones(
			ExcepcionesValidaciones excepcionesValidaciones) {
		boolean exist = false;
		Map criteriaVerificacion = new HashMap();
		criteriaVerificacion.put("codigoPais",
				excepcionesValidaciones.getCodigoPais());
		criteriaVerificacion.put("tipoDocumento",
				excepcionesValidaciones.getTipoDocumento());
		criteriaVerificacion.put("validacion",
				excepcionesValidaciones.getValidaciones());
		criteriaVerificacion.put("codigoRegion",
				excepcionesValidaciones.getCodigoRegion());
		criteriaVerificacion.put("codigoZona",
				excepcionesValidaciones.getCodigoZona());
		criteriaVerificacion.put("codigoPeriodo",
				excepcionesValidaciones.getCodigoPeriodo());
		criteriaVerificacion.put("codigoConsultora",
				excepcionesValidaciones.getCodigoCliente());

		if (excepcionesValidaciones.getFecha() != null)
			criteriaVerificacion.put("fecha", DateUtil
					.convertDateToString(new Date(excepcionesValidaciones
							.getFecha().getTime())));
		else
			criteriaVerificacion.put("fecha", "");

		MantenimientoExcepcionesValidacionesService serviceMant = (MantenimientoExcepcionesValidacionesService) getBean("spusicc.mantenimientoExcepcionesValidacionesService");
		List listaExistentes = serviceMant
				.getExcepcionesValidacionesExist(criteriaVerificacion);
		if (listaExistentes != null && listaExistentes.size() > 0) {
			exist = true;
		}
		return exist;
	}

	/**
	 * @param editForm
	 * @return
	 */
	public ExcepcionesValidaciones getParametrosExcepcionesValidaciones(
			MantenimientoExcepcionesValidacionesForm editForm) {
		ExcepcionesValidaciones excepcionesValidaciones = new ExcepcionesValidaciones();
		excepcionesValidaciones.setCodigoPais(editForm.getCodigoPais());
		excepcionesValidaciones.setTipoDocumento(editForm.getTipoDocumento());
		// excepcionesValidaciones.setCodigoOrigen(codigoOrigen);
		excepcionesValidaciones.setCodigoPeriodo(editForm.getCodigoPeriodo());
		if (editForm.getFecha() != null) {
			try {
				DateFormat formatter;
				formatter = new SimpleDateFormat("dd/MM/yyyy");
				Date date = (Date) formatter.parse(editForm.getFecha());
				java.sql.Timestamp timeStampDate = new Timestamp(date.getTime());
				excepcionesValidaciones.setFecha(timeStampDate);
			} catch (ParseException e) {
				System.out.println("Exception :" + e);
			}
		}
		excepcionesValidaciones.setIndicador("1");
		excepcionesValidaciones.setValidaciones(editForm.getValidaciones());
		return excepcionesValidaciones;
	}

	/**
	 * @param listRegiones
	 * @param region
	 * @return
	 */
	private List addRegionToList(List listRegiones, String region) {
		List lista = listRegiones;
		boolean pertenece = false;
		int k = 0;
		String auxRegion = null;
		while (k < listRegiones.size()) {
			auxRegion = (String) listRegiones.get(k);
			if (auxRegion.equals(region)) {
				pertenece = true;
			}
			k++;
		}

		if (!pertenece) {
			lista.add(region);
		}
		return lista;
	}

	/**
	 * @param event
	 */
	public void visualizarRegionesZonas(ValueChangeEvent event) {
		MantenimientoExcepcionesValidacionesForm f = (MantenimientoExcepcionesValidacionesForm) this.formMantenimiento;

		Boolean valor = (Boolean) event.getNewValue();
		if (valor) {
			this.indicadorRegionesBool = false;
		} else {
			String [] limpiar = new String[0];
			f.setRegionList(limpiar);
			f.setZonaList(limpiar);
			this.indicadorRegionesBool = true;
		}
	}

	/**
	 * @param event
	 */
	public void limpiarArchivo(ActionEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'limpiar' method");
		}

		MantenimientoExcepcionesValidacionesForm f = (MantenimientoExcepcionesValidacionesForm) this.formMantenimiento;

		f.setCodigoConsultora("");
		f.setIndicadorClientes("0");
		f.setCorrectos(0);
		f.setClienteFile(null);
		this.mostrarGrilla = false;
		this.stoExcepcionClientesList = new ArrayList();
	}

	/**
	 * @param event
	 * @throws IOException
	 */
	public void loadfile(FileUploadEvent event) throws IOException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'load Clientes from file' method");
		}
		
		this.stoExcepcionClientesList = new ArrayList();
		
		MantenimientoExcepcionesValidacionesForm f = (MantenimientoExcepcionesValidacionesForm) this.formMantenimiento;
		
		if(StringUtils.isBlank(f.getTipoDocumento())){
			this.addError("Error : ", "'Tipo Documento' es un campo requerido.");
			return;
		}
		if(StringUtils.isBlank(f.getValidaciones())){
			this.addError("Error : ", "'Validacion' es un campo requerido.");
			return;
		}
		
		f.setClienteFile(null);
		MantenimientoRECIngresoAtencionesService service = (MantenimientoRECIngresoAtencionesService) getBean("spusicc.mantenimientoRECIngresoAtencionesService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		List listaClientes = new ArrayList();
		f.setClienteFile(event.getFile());
		Map criteria = new HashMap();
		InputStream is = event.getFile().getInputstream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		Long longitudPais = pais.getLongitudCodigoCliente();
		String linea = "";
		String codigoConsultora = "";
		int cont = 0;
		int numRegistros = 0;
		f.setErrados(0);

		while (true) {
			linea = br.readLine();
			if (linea == null)
				break;

			codigoConsultora = StringUtils.leftPad(linea.trim(),
					longitudPais.intValue(), '0');
			criteria.put("codigoConsultora", codigoConsultora);
			LabelValue bean = new LabelValue(codigoConsultora,
					service.getCodigoConsultora(criteria));
			listaClientes.add(bean);

			if (bean.getValue() == null)
				cont++;

			numRegistros++;
		}

		// Se inserta en la tabla temporal
		String oidCarga = service.getOidCargaCliente();
		criteria.put("oid", oidCarga);
		service.insertaClienteFile(listaClientes, criteria);

		// Se obtiene la lista de la tabla temporal
		List list = new ArrayList();
		list = service.getCargaClienteList(criteria);

		f.setCodigosErradosFile("");

		if (cont != 0) {
			f.setCodigosErradosFile("Existe(n) " + cont
					+ " codigo(s) errado(s)");
			f.setErrados(cont);
		}
		int correctos = numRegistros - cont;
		f.setCorrectos(correctos);

		criteria.put("numRegistros", numRegistros);
		List list2 = new ArrayList();
		list2 = service.getResumenCargaClienteList(criteria);

		this.stoExcepcionClientesList = list;

		f.setIndicadorClientes("1");
		f.setCodigoConsultora(null);
		event = null;
		this.mostrarGrilla = true;
	}

	/**
	 * @param event
	 */
	public void ejecutarReporte(ActionEvent event) {
		if (this.log.isDebugEnabled()) {
			this.log.debug("Entering 'ejecutarReporte' method - MantenimientoExcepcionesValidacionesSearchAction");
		}

		MantenimientoExcepcionesValidacionesSearchForm searchForm = (MantenimientoExcepcionesValidacionesSearchForm) this.formBusqueda;
		searchForm.setFormatoExportacion("XLS");
		log.debug("Formato exportacion: " + searchForm.getFormatoExportacion());

		this.reporte.setFormatoReporte("XLS");
		this.reporte.setFormatoExportacion("XLS");
		this.reporte.getFormReporte().setFormatoExportacion("XLS");
		this.reporte.setTipoDocumento(searchForm.getTipoDocumento());
		this.reporte.setValidaciones(searchForm.getValidaciones());
		this.reporte.setCodigoConsultora(searchForm.getCodigoConsultora());
		this.reporte.setFecha(searchForm.getFecha());
		this.reporte.setCodigoPeriodo(searchForm.getCodigoPeriodo());
		this.reporte.setearValores();
		this.reporte.executeReporte();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#devuelveMensajeKeySaveOK()
	 */
	@Override
	protected String devuelveMensajeKeySaveOK() {
		MantenimientoExcepcionesValidacionesForm f = (MantenimientoExcepcionesValidacionesForm) this.formMantenimiento;
		boolean newRecord = f.isNewRecord();
		if (newRecord)
			return "mantenimientoExcepcionesValidacionesForm.exito";
		else
			return "";
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setValidarFind()
	 */
	@Override
	public String setValidarFind() {
		if(log.isDebugEnabled()){
			log.debug("setValidarFind...");
		}
		
		String mensaje= "";
		MantenimientoExcepcionesValidacionesSearchForm searchForm = (MantenimientoExcepcionesValidacionesSearchForm) this.formBusqueda;
		
		if(StringUtils.isBlank(searchForm.getTipoDocumento())){
			mensaje = "'Tipo Documento' es un campo requerido.";
		}
		
		return mensaje;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setSaveFindBeforeAttributes()
	 */
	@Override
	public void setSaveFindBeforeAttributes() throws Exception {
		MantenimientoExcepcionesValidacionesForm editForm = (MantenimientoExcepcionesValidacionesForm) this.formMantenimiento;
		MantenimientoExcepcionesValidacionesSearchForm searchForm = (MantenimientoExcepcionesValidacionesSearchForm) this.formBusqueda;
		
		searchForm.setTipoDocumento(editForm.getTipoDocumento());
		searchForm.setValidaciones(editForm.getValidaciones());
		searchForm.setCodigoPeriodo(editForm.getCodigoPeriodo());
		searchForm.setCodigoConsultora(editForm.getCodigoConsultora());
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setSalirFindBeforeAttributes()
	 */
	@Override
	public void setSalirFindBeforeAttributes() throws Exception {
		MantenimientoExcepcionesValidacionesSearchForm searchForm = (MantenimientoExcepcionesValidacionesSearchForm) this.formBusqueda;
		MantenimientoExcepcionesValidacionesForm editForm = (MantenimientoExcepcionesValidacionesForm) this.formMantenimiento;
		
		searchForm.setTipoDocumento("");
		searchForm.setValidaciones("");
		searchForm.setCodigoPeriodo("");
		searchForm.setCodigoConsultora("");
		searchForm.setFechaDate(null);
		
		editForm.setTipoDocumento("");
		editForm.setValidaciones("");
		editForm.setCodigoPeriodo("");
		editForm.setCodigoConsultora("");
		editForm.setFechaDate(null);
	}

	/**
	 * @return
	 */
	public List getStoTipoDocumentoExcepcionList() {
		return stoTipoDocumentoExcepcionList;
	}

	/**
	 * @param stoTipoDocumentoExcepcionList
	 */
	public void setStoTipoDocumentoExcepcionList(
			List stoTipoDocumentoExcepcionList) {
		this.stoTipoDocumentoExcepcionList = stoTipoDocumentoExcepcionList;
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
	public List getStoExcepcionesList() {
		return stoExcepcionesList;
	}

	/**
	 * @param stoExcepcionesList
	 */
	public void setStoExcepcionesList(List stoExcepcionesList) {
		this.stoExcepcionesList = stoExcepcionesList;
	}

	/**
	 * @return
	 */
	public List getStoValidacionesExcepcionByDocumento() {
		return stoValidacionesExcepcionByDocumento;
	}

	/**
	 * @param stoValidacionesExcepcionByDocumento
	 */
	public void setStoValidacionesExcepcionByDocumento(
			List stoValidacionesExcepcionByDocumento) {
		this.stoValidacionesExcepcionByDocumento = stoValidacionesExcepcionByDocumento;
	}

	/**
	 * @return
	 */
	public List getStoExcepcionClientesList() {
		return stoExcepcionClientesList;
	}

	/**
	 * @param stoExcepcionClientesList
	 */
	public void setStoExcepcionClientesList(List stoExcepcionClientesList) {
		this.stoExcepcionClientesList = stoExcepcionClientesList;
	}

	/**
	 * @return
	 */
	public String getIndExito() {
		return indExito;
	}

	/**
	 * @param indExito
	 */
	public void setIndExito(String indExito) {
		this.indExito = indExito;
	}

	/**
	 * @return
	 */
	public String getNumeroErrados() {
		return numeroErrados;
	}

	/**
	 * @param numeroErrados
	 */
	public void setNumeroErrados(String numeroErrados) {
		this.numeroErrados = numeroErrados;
	}

	/**
	 * @return
	 */
	public boolean isIndicadorRegionesBool() {
		return indicadorRegionesBool;
	}

	/**
	 * @param indicadorRegionesBool
	 */
	public void setIndicadorRegionesBool(boolean indicadorRegionesBool) {
		this.indicadorRegionesBool = indicadorRegionesBool;
	}

	/**
	 * @return
	 */
	public boolean isMostrarGrilla() {
		return mostrarGrilla;
	}

	/**
	 * @param mostrarGrilla
	 */
	public void setMostrarGrilla(boolean mostrarGrilla) {
		this.mostrarGrilla = mostrarGrilla;
	}

	/**
	 * @return
	 */
	public BusquedaConsultoraPOPUPSearchAction getPopupBusquedaConsultora() {
		return popupBusquedaConsultora;
	}

	/**
	 * @param popupBusquedaConsultora
	 */
	public void setPopupBusquedaConsultora(
			BusquedaConsultoraPOPUPSearchAction popupBusquedaConsultora) {
		this.popupBusquedaConsultora = popupBusquedaConsultora;
	}

	/**
	 * @return
	 */
	public boolean isMostrarPopupConsultora() {
		return mostrarPopupConsultora;
	}

	/**
	 * @param mostrarPopupConsultora
	 */
	public void setMostrarPopupConsultora(boolean mostrarPopupConsultora) {
		this.mostrarPopupConsultora = mostrarPopupConsultora;
	}

	/**
	 * @return
	 */
	public static String getPopupConsultora() {
		return POPUP_CONSULTORA;
	}

	/**
	 * @return
	 */
	public ReporteSTOExcepcionesValidacionesAction getReporte() {
		return reporte;
	}

	/**
	 * @param reporte
	 */
	public void setReporte(ReporteSTOExcepcionesValidacionesAction reporte) {
		this.reporte = reporte;
	}

	/**
	 * @return the columnasSeleccionadas
	 */
	public List getColumnasSeleccionadas() {
		return columnasSeleccionadas;
	}

	/**
	 * @param columnasSeleccionadas the columnasSeleccionadas to set
	 */
	public void setColumnasSeleccionadas(List columnasSeleccionadas) {
		this.columnasSeleccionadas = columnasSeleccionadas;
	}

	/**
	 * @return the estadoFecha
	 */
	public boolean isEstadoFecha() {
		return estadoFecha;
	}

	/**
	 * @param estadoFecha the estadoFecha to set
	 */
	public void setEstadoFecha(boolean estadoFecha) {
		this.estadoFecha = estadoFecha;
	}

	/**
	 * @return the lengthCodigoConsultora
	 */
	public String getLengthCodigoConsultora() {
		return lengthCodigoConsultora;
	}

	/**
	 * @param lengthCodigoConsultora the lengthCodigoConsultora to set
	 */
	public void setLengthCodigoConsultora(String lengthCodigoConsultora) {
		this.lengthCodigoConsultora = lengthCodigoConsultora;
	}
	
	
}