package biz.belcorp.ssicc.web.spusicc.sto.action;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.service.spusicc.mav.ProcesoMAVAsignacionReemplazoGerenteService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECIngresoAtencionesService;
import biz.belcorp.ssicc.service.spusicc.sto.MantenimientoSTOFacturacionAdicionalService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.sto.form.MantenimientoSTOFacturacionAdicionalForm;
import biz.belcorp.ssicc.web.spusicc.sto.form.MantenimientoSTOFacturacionAdicionalSearchForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MantenimientoSTOFacturacionAdicionalSearchAction extends
		BaseMantenimientoSearchAbstractAction {

	private List siccRegionList;
	private List siccTipoClienteList;
	private LabelValue[] siccSubTipoClienteList;
	private LabelValue[] siccTipoClasificacionList;
	private LabelValue[] siccClasificacionList;
	private LabelValue[] siccZonaList;
	private String codigoIdiomaISO;

	private List stoFacturaAdicionalList;
	private List stoLevantamientoErroresClientesList;
	private List stoResumenClientesList;

	private List stoLevantamientoErroresClientesList2;
	private List stoResumenClientesList2;

	private String indicadorGraboFacturaAdicional;

	private Boolean mostrarGrilla;
	private Boolean mostrarGrilla2;

	private boolean mantenimiento = false;
	private String attachment = "";
	private String attachment2 = "";
	private String oidSubTipoCliente;
	private String oidSubTipoClasificacion;
	private String oidClasificacion;
	private String codigoZona;
	private String oid;
	private DataTableModel dmSTOFacturacionAdicional;

	private Object[] objectosMultiplesSeleccionados;
	/**
	 * 
	 */
	private static final long serialVersionUID = -8656911016721100018L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#getSalirForward()
	 */
	@Override
	protected String getSalirForward() {
		return "mantenimientoSTOFacturacionAdicionalSearchForm";

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoSTOFacturacionAdicionalForm";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoSTOFacturacionAdicionalSearchForm search = new MantenimientoSTOFacturacionAdicionalSearchForm();
		return search;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {

		log.debug("MantenimientoSTOFacturacionAdicionalSearchAction - setFindAttributes");

		MantenimientoSTOFacturacionAdicionalSearchForm f = (MantenimientoSTOFacturacionAdicionalSearchForm) this.formBusqueda;

		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		MantenimientoSTOFacturacionAdicionalService service = (MantenimientoSTOFacturacionAdicionalService) getBean("spusicc.mantenimientoSTOFacturacionAdicionalService");
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		ProcesoMAVAsignacionReemplazoGerenteService service2 = (ProcesoMAVAsignacionReemplazoGerenteService) getBean("spusicc.procesoMAVAsignacionReemplazoGerenteService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		int aux = validarCodigoConsultora();
		if (aux == 1) {

			String mensaje = "Codigo de Cliente incorrecto, por favor ingrese un codigo valido para hacer la busqueda";
			this.addError("Error : ", mensaje);
			return null;
		}
		Map criteria = new HashMap();

		criteria.put("codigoPais", pais.getCodigo());
		String oidPais = clienteService.getOidPais(criteria);
		criteria.put("oidPais", oidPais);
		criteria.put("codigoIso", usuario.getIdioma().getCodigoISO());
		criteria.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		criteria.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
		criteria.put("codigoPeriodo", f.getCodigoPeriodo());
		criteria.put("codigoCliente", f.getCodigoCliente());

		if (StringUtils.isNotBlank(f.getCodigoCliente())) {
			boolean existeCliente = service2.existeCodigoCliente(criteria);
			if (!existeCliente) {
				criteria.put("oidCliente", "-1");
			} else {
				criteria.put("oidCliente", reporteService.getOidString(
						"getOidClienteByCodigoCliente", criteria));
			}
		} else
			criteria.put("oidCliente", null);

		if (StringUtils.isNotBlank(f.getCodigoPeriodo()))
			criteria.put("oidPeriodo", reporteService.getOidString(
					"getOidPeriodoByCodigoPeriodo", criteria));
		else
			criteria.put("oidPeriodo", null);

		if (StringUtils.isNotBlank(f.getOidTipoCliente()))
			criteria.put("oidTipoCliente",
					Integer.valueOf(f.getOidTipoCliente()));
		else
			criteria.put("oidTipoCliente", null);

		if (StringUtils.isNotBlank(f.getOidSubTipoCliente())) {
			criteria.put("oidSubTipoCliente",
					Integer.valueOf(f.getOidSubTipoCliente()));
			this.oidSubTipoCliente = criteria.get("oidSubTipoCliente")
					.toString();
			// request.setAttribute("oidSubTipoCliente",
			// criteria.get("oidSubTipoCliente"));
		} else {
			criteria.put("oidSubTipoCliente", null);
			this.oidSubTipoCliente = null;
			// request.setAttribute("oidSubTipoCliente", null);
		}

		if (StringUtils.isNotBlank(f.getOidTipoClasificacion())) {
			criteria.put("oidSubTipoClasificacion",
					Integer.valueOf(f.getOidTipoClasificacion()));
			this.oidSubTipoClasificacion = criteria.get(
					"oidSubTipoClasificacion").toString();
			// request.setAttribute("oidSubTipoClasificacion",
			// criteria.get("oidSubTipoClasificacion"));
		} else {
			criteria.put("oidSubTipoClasificacion", null);
			this.oidSubTipoClasificacion = null;
			// request.setAttribute("oidSubTipoClasificacion",null);
		}

		if (StringUtils.isNotBlank(f.getOidClasificacion())) {
			criteria.put("oidClasificacion",
					Integer.valueOf(f.getOidClasificacion()));
			this.oidClasificacion = criteria.get("oidClasificacion").toString();
			// request.setAttribute("oidClasificacion",
			// criteria.get("oidClasificacion"));
		} else {
			criteria.put("oidClasificacion", null);
			this.oidClasificacion = null;
			// request.setAttribute("oidClasificacion", null);
		}

		if (StringUtils.isNotBlank(f.getCodigoRegion())) {
			criteria.put("codigoRegion", f.getCodigoRegion());
			criteria.put("oidRegion",
					reporteService.getOidRegionByPaisMarcaCanal(criteria));
		} else
			criteria.put("oidRegion", null);

		if (StringUtils.isNotBlank(f.getCodigoZona())
				&& StringUtils.isNotBlank(f.getCodigoRegion())) {
			criteria.put("codigoZona", f.getCodigoZona());
			criteria.put("oidZona",
					reporteService.getOidZonaByPaisMarcaCanalRegion(criteria));
			this.codigoZona = criteria.get("codigoZona").toString();
			// request.setAttribute("codigoZona",
			// criteria.get("codigoZona"));
		} else {
			criteria.put("oidZona", null);
			this.codigoZona = null;
			// request.setAttribute("codigoZona", null);
		}

		/*-------------------------*/

		// List listaClientes =
		// (List)request.getSession().getAttribute(Constants.STO_LEVANTAMIENTO_ERRORES_CLIENTES_LIST);
		// // grilla del archivo
		List listaClientes = stoLevantamientoErroresClientesList; // grilla
																	// del
																	// archivo
		// SI cargo consultoras por el archivo
		if (listaClientes != null && StringUtils.isBlank(f.getCodigoCliente())) {
			criteria.put("oidClienteCarga", this.oid);
		} else {
			criteria.put("oidClienteCarga", null);
		}
		/*-------------------------*/

		List lista = new ArrayList();

		criteria.put("validaPromedio", f.getValidaPromedio());
		criteria.put("validaMontoMinimo", f.getValidaMontoMinimo());
		lista = service.getFacturaAdicionalList(criteria);
		this.stoFacturaAdicionalList = new ArrayList();

		this.stoFacturaAdicionalList = lista;
		this.dmSTOFacturacionAdicional = new DataTableModel(
				this.stoFacturaAdicionalList);

		return lista;

	}

	/**
	 * Elimina Multiple
	 * 
	 * @param evt
	 */
	public void eliminar(ActionEvent evt) {
		log.debug("MantenimientoSTOFacturacionAdicionalSearchAction - setDeleteAttributes");
		try {
			MantenimientoSTOFacturacionAdicionalService service = (MantenimientoSTOFacturacionAdicionalService) getBean("spusicc.mantenimientoSTOFacturacionAdicionalService");
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

			Map parametros = new HashMap();

			int tamanio = this.objectosMultiplesSeleccionados.length;
			String id = "";
			String[] arregloDeCodigos = new String[tamanio];
			BigDecimal oidfact = null;
			for (int i = 0; i < tamanio; i++) {
				HashMap objBeanSelect = (HashMap) this.objectosMultiplesSeleccionados[i];
				id = (String) objBeanSelect.get("codigoCliente");
				oidfact = (BigDecimal) objBeanSelect.get("oidFacturaAdicional");
				arregloDeCodigos[i] = oidfact.toString() + "|" + id;
			}

			parametros.put("idSeleccionados", arregloDeCodigos);
			parametros.put("usuario", usuario.getLogin());
			parametros.put("codPais", pais.getCodigo());
			service.deleteFacturaAdicional(parametros);
			this.stoFacturaAdicionalList = new ArrayList();
			this.stoFacturaAdicionalList = this.setFindAttributes();
			this.dmSTOFacturacionAdicional = new DataTableModel(
					this.stoFacturaAdicionalList);
			this.addInfo("Info : ",
					this.getResourceMessage("estructuraArchivo.deleted"));

		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {

		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setSaveAttributes()
	 */
	@Override
	protected boolean setSaveAttributes() throws Exception {

		log.debug("MantenimientoSTOFacturacionAdicionalAction - setAddAttributes");

		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		boolean isArchivo = false;

		MantenimientoSTOFacturacionAdicionalForm f = (MantenimientoSTOFacturacionAdicionalForm) this.formMantenimiento;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		MantenimientoSTOFacturacionAdicionalService service = (MantenimientoSTOFacturacionAdicionalService) getBean("spusicc.mantenimientoSTOFacturacionAdicionalService");
		MantenimientoRECIngresoAtencionesService service2 = (MantenimientoRECIngresoAtencionesService) getBean("spusicc.mantenimientoRECIngresoAtencionesService");
		Map criteria = new HashMap();

		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoPeriodo", f.getCodigoPeriodo());
		criteria.put("codigoCliente", f.getCodigoCliente());
		criteria.put("codigoIso", usuario.getIdioma().getCodigoISO());
		criteria.put("usuario", usuario.getLogin());
		criteria.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		criteria.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);

		if (StringUtils.isNotBlank(f.getCodigoPeriodo()))
			criteria.put("oidPeriodo", reporteService.getOidString(
					"getOidPeriodoByCodigoPeriodo", criteria));
		else
			criteria.put("oidPeriodo", null);

		if (StringUtils.isNotBlank(f.getCodigoCliente())) {
			try {
				criteria.put("oidCliente", reporteService.getOidString(
						"getOidClienteByCodigoCliente", criteria));
			} catch (Exception ex) {
				throw new Exception(String.format(
						"Consultora con codigo {0} no ha sido ubicada.",
						f.getCodigoCliente()));
			}
		} else
			criteria.put("oidCliente", null);

		if (StringUtils.isNotBlank(f.getOidTipoCliente()))
			criteria.put("oidTipoCliente", f.getOidTipoCliente());
		else
			criteria.put("oidTipoCliente", null);

		if (StringUtils.isNotBlank(f.getOidSubTipoCliente())) {
			criteria.put("oidSubTipoCliente", f.getOidSubTipoCliente());
			this.oidSubTipoCliente = criteria.get("oidSubTipoCliente")
					.toString();
			// request.setAttribute("oidSubTipoCliente",
			// criteria.get("oidSubTipoCliente"));
		} else {
			criteria.put("oidSubTipoCliente", null);
			this.oidSubTipoCliente = null;
			// request.setAttribute("oidSubTipoCliente", null);
		}

		if (StringUtils.isNotBlank(f.getOidTipoClasificacion())) {
			criteria.put("oidSubTipoClasificacion", f.getOidTipoClasificacion());
			this.oidSubTipoClasificacion = criteria.get(
					"oidSubTipoClasificacion").toString();
			// request.setAttribute("oidSubTipoClasificacion",
			// criteria.get("oidSubTipoClasificacion"));
		} else {
			criteria.put("oidSubTipoClasificacion", null);
			this.oidSubTipoClasificacion = null;
			// request.setAttribute("oidSubTipoClasificacion",null);
		}

		if (StringUtils.isNotBlank(f.getOidClasificacion())) {
			criteria.put("oidClasificacion", f.getOidClasificacion());
			this.oidClasificacion = criteria.get("oidClasificacion").toString();
			// request.setAttribute("oidClasificacion",
			// criteria.get("oidClasificacion"));
		} else {
			criteria.put("oidClasificacion", null);
			this.oidClasificacion = null;
			// request.setAttribute("oidClasificacion", null);
		}

		if (StringUtils.isNotBlank(f.getCodigoRegion())) {
			criteria.put("codigoRegion", f.getCodigoRegion());
			criteria.put("oidRegion",
					reporteService.getOidRegionByPaisMarcaCanal(criteria));
		} else
			criteria.put("oidRegion", null);

		if (StringUtils.isNotBlank(f.getCodigoZona())) {
			criteria.put("codigoZona", f.getCodigoZona());
			criteria.put("oidZona",
					reporteService.getOidZonaByPaisMarcaCanalRegion(criteria));
			this.codigoZona = criteria.get("codigoZona").toString();
			// request.setAttribute("codigoZona", criteria.get("codigoZona"));
		} else {
			criteria.put("oidZona", null);
			this.codigoZona = null;
			// request.setAttribute("codigoZona", null);
		}

		criteria.put("fechaCreacion", Calendar.getInstance().getTime());
		criteria.put("observaciones", f.getObservaciones());

		/*-------------------------*/
		String[] arrlistaClientes = new String[0];
		List clienteList = new ArrayList(); // result
		Long longitudPais = pais.getLongitudCodigoCliente();
		String codigoCliente = f.getCodigoCliente();

		// List listaClientes =
		// (List)request.getSession().getAttribute(Constants.STO_LEVANTAMIENTO_ERRORES_CLIENTES_LIST);
		// // grilla del archivo
		List listaClientes = stoLevantamientoErroresClientesList2; // grilla del
																	// archivo

		Map param = new HashMap();
		Map map = new HashMap();

		// SI cargo consultoras por el archivo
		if (listaClientes != null) {
			isArchivo = true;
			for (int i = 0; i < listaClientes.size(); i++) {
				map = (Map) listaClientes.get(i);
				codigoCliente = (String) map.get("codigoCliente");
				param.put("codigoCliente", codigoCliente);

				try {
					clienteList.add(reporteService.getOidString(
							"getOidClienteByCodigoCliente", param));
				} catch (Exception e) {
					log.warn(String.format(
							"Consultora con codigo {0} no ha sido ubicada.",
							codigoCliente));
				}
			}
		}
		criteria.put("clienteList", (clienteList == null) ? new ArrayList()
				: clienteList);
		/*-------------------------*/

		criteria.put("validaPromedio", f.getValidaPromedio());
		criteria.put("validaMontoMinimo", f.getValidaMontoMinimo());
		service.insertFacturaAdicional(criteria);
		this.stoFacturaAdicionalList = new ArrayList();

		this.stoFacturaAdicionalList = this.setFindAttributes();
		this.dmSTOFacturacionAdicional = new DataTableModel(
				this.stoFacturaAdicionalList);

		this.indicadorGraboFacturaAdicional = Constants.NUMERO_UNO;

		// this.getResourceMessage("proceso.ok");

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setObtenerRegistroAttributes()
	 */
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		this.mantenimiento = true;

		log.debug("MantenimientoSTOFacturacionAdicionalAction - setViewAttributes");

		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		MantenimientoSTOFacturacionAdicionalForm f = new MantenimientoSTOFacturacionAdicionalForm();
		limpiarForm(f);
		cleanFormEdit(f);

		Map criteriaOperacion = new HashMap();
		String codigoPais = pais.getCodigo();
		criteriaOperacion.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		criteriaOperacion.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);

		this.siccRegionList = reporteService.getListaGenerico(
				"getRegionesByPais", criteriaOperacion);
		this.siccTipoClienteList = interfazSiCCService
				.getTiposClientesByCodigoISOOID(usuario.getIdioma()
						.getCodigoISO());

		this.siccSubTipoClienteList = null;
		this.siccTipoClasificacionList = null;
		this.siccClasificacionList = null;
		this.siccZonaList = null;
		this.codigoIdiomaISO = usuario.getIdioma().getCodigoISO();
		this.setIndicadorGraboFacturaAdicional(Constants.NUMERO_CERO);

		f.setCodigosErradosFile("");

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

		this.mantenimiento = false;
		log.info("MantenimientoSTOFacturacionAdicionalSearchAction - setViewAttributes");

		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		this.mostrarBotonEliminar = false;
		this.mostrarBotonConsultar = false;
		this.mostrarBotonModificar = false;
		this.mostrarListaBusqueda = false;
		MantenimientoSTOFacturacionAdicionalSearchForm search = (MantenimientoSTOFacturacionAdicionalSearchForm) this.formBusqueda;
		limpiar(search);
		cleanForm(search);

		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		criteriaOperacion.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);

		this.siccRegionList = reporteService.getListaGenerico(
				"getRegionesByPais", criteriaOperacion);
		this.siccTipoClienteList = interfazSiCCService
				.getTiposClientesByCodigoISOOID(usuario.getIdioma()
						.getCodigoISO());
		search.setCodigoPais(pais.getCodigo());
		this.codigoIdiomaISO = usuario.getIdioma().getCodigoISO();

	}

	/**
	 * Limpia los campos del formulario BaseSearch
	 * 
	 * @param f
	 */
	private void cleanForm(MantenimientoSTOFacturacionAdicionalSearchForm f) {

		f.setOidTipoCliente("");
		f.setOidSubTipoCliente("");
		f.setOidTipoClasificacion("");
		f.setOidClasificacion("");
		f.setCodigoRegion("");
		f.setCodigoZona("");
		f.setCodigoCliente("");
		f.setCodigoPeriodo("");

	}

	/**
	 * Limpia los campos del formulario BaseEdit
	 * 
	 * @param f
	 */
	/**
	 * @param f
	 */
	private void cleanFormEdit(MantenimientoSTOFacturacionAdicionalForm f) {
		f.setOidTipoCliente("");
		f.setOidSubTipoCliente("");
		f.setOidTipoClasificacion("");
		f.setOidClasificacion("");
		f.setCodigoRegion("");
		f.setCodigoZona("");
		f.setCodigoCliente("");
		f.setObservaciones("");
		f.setNombreCliente("");
		f.setCodigoPeriodo("");

	}

	/**
	 * @throws Exception
	 */
	private void loadArchivoBusqueda() {
		try {

			if (log.isDebugEnabled()) {
				log.debug("Entering 'load Clientes from file' method");
			}
			MantenimientoSTOFacturacionAdicionalSearchForm search = (MantenimientoSTOFacturacionAdicionalSearchForm) this.formBusqueda;

			MantenimientoRECIngresoAtencionesService service = (MantenimientoRECIngresoAtencionesService) getBean("spusicc.mantenimientoRECIngresoAtencionesService");
			GenericoService genericoService = (GenericoService) getBean("genericoService");

			List listaClientes = new ArrayList();
			String indValidaCodConsultoraDocIdentidad = null;
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

			ParametroPais parametroPais = new ParametroPais();
			parametroPais.setCodigoPais(pais.getCodigo());
			parametroPais.setCodigoSistema("STO");
			parametroPais
					.setNombreParametro("indValidaCodConsultoraDocIdentidad");

			List lstParametros = genericoService
					.getParametrosPais(parametroPais);

			if (lstParametros != null && lstParametros.size() > 0) {
				ParametroPais ps = (ParametroPais) lstParametros.get(0);
				indValidaCodConsultoraDocIdentidad = ps.getValorParametro();
			}

			UploadedFile archivo = search.getClienteFile();

			Map criteria = new HashMap();
			InputStream is = archivo.getInputstream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			Long longitudPais = pais.getLongitudCodigoCliente();
			String linea = "";
			String codigoConsultora = "";
			int cont = 0;
			int numRegistros = 0;

			while (true) {
				linea = br.readLine();
				if (linea == null)
					break;

				codigoConsultora = StringUtils.leftPad(linea.trim(),
						longitudPais.intValue(), '0');
				criteria.put("codigoConsultora", codigoConsultora);
				LabelValue bean = new LabelValue(codigoConsultora,
						service.getCodigoConsultora(criteria));

				if (bean.getValue() == null
						&& StringUtils.equals(
								indValidaCodConsultoraDocIdentidad,
								Constants.SI)) {
					criteria.put("documentoIdentidad", codigoConsultora);
					String codigoConsultoraPorDocIden = service
							.getCodigoConsultoraPorDocumentoIdentidad(criteria);

					if (codigoConsultoraPorDocIden == null) {
						bean = new LabelValue(
								codigoConsultora,
								service.getCodigoConsultoraPorDocumentoIdentidad(criteria));
					} else {
						bean = new LabelValue(
								codigoConsultoraPorDocIden,
								service.getCodigoConsultoraPorDocumentoIdentidad(criteria));
					}
				}

				listaClientes.add(bean);

				if (bean.getValue() == null)
					cont++;

				numRegistros++;
			}

			// Se inserta en la tabla temporal
			oid = service.getOidCargaCliente();
			criteria.put("oid", oid);
			service.insertaClienteFile(listaClientes, criteria);

			// Se obtiene la lista de la tabla temporal
			List list = new ArrayList();
			list = service.getCargaClienteList(criteria);

			search.setCodigosErradosFile("");
			if (cont != 0)
				search.setCodigosErradosFile("Existe(n) " + cont
						+ " codigo(s) errado(s)");

			criteria.put("numRegistros", numRegistros);
			List list2 = new ArrayList();
			list2 = service.getResumenCargaClienteList(criteria);

			this.stoLevantamientoErroresClientesList = list;
			if (stoLevantamientoErroresClientesList != null) {
				this.setMostrarGrilla(true);
			}
			
			this.stoResumenClientesList = list2; 
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private void loadArchivo() {
		try {

			if (log.isDebugEnabled()) {
				log.debug("Entering 'load Clientes from file' method");
			}
			MantenimientoSTOFacturacionAdicionalForm search = (MantenimientoSTOFacturacionAdicionalForm) this.formMantenimiento;

			MantenimientoRECIngresoAtencionesService service = (MantenimientoRECIngresoAtencionesService) getBean("spusicc.mantenimientoRECIngresoAtencionesService");
			GenericoService genericoService = (GenericoService) getBean("genericoService");

			List listaClientes = new ArrayList();
			String indValidaCodConsultoraDocIdentidad = null;
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

			ParametroPais parametroPais = new ParametroPais();
			parametroPais.setCodigoPais(pais.getCodigo());
			parametroPais.setCodigoSistema("STO");
			parametroPais
					.setNombreParametro("indValidaCodConsultoraDocIdentidad");

			List lstParametros = genericoService
					.getParametrosPais(parametroPais);

			if (lstParametros != null && lstParametros.size() > 0) {
				ParametroPais ps = (ParametroPais) lstParametros.get(0);
				indValidaCodConsultoraDocIdentidad = ps.getValorParametro();
			}

			UploadedFile archivo = search.getClienteFile();

			Map criteria = new HashMap();
			InputStream is = archivo.getInputstream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			Long longitudPais = pais.getLongitudCodigoCliente();
			String linea = "";
			String codigoConsultora = "";
			int cont = 0;
			int numRegistros = 0;

			while (true) {
				linea = br.readLine();
				if (linea == null)
					break;

				codigoConsultora = StringUtils.leftPad(linea.trim(),
						longitudPais.intValue(), '0');
				criteria.put("codigoConsultora", codigoConsultora);
				LabelValue bean = new LabelValue(codigoConsultora,
						service.getCodigoConsultora(criteria));

				if (bean.getValue() == null
						&& StringUtils.equals(
								indValidaCodConsultoraDocIdentidad,
								Constants.SI)) {
					criteria.put("documentoIdentidad", codigoConsultora);
					String codigoConsultoraPorDocIden = service
							.getCodigoConsultoraPorDocumentoIdentidad(criteria);

					if (codigoConsultoraPorDocIden == null) {
						bean = new LabelValue(
								codigoConsultora,
								service.getCodigoConsultoraPorDocumentoIdentidad(criteria));
					} else {
						bean = new LabelValue(
								codigoConsultoraPorDocIden,
								service.getCodigoConsultoraPorDocumentoIdentidad(criteria));
					}
				}

				listaClientes.add(bean);

				if (bean.getValue() == null)
					cont++;

				numRegistros++;
			}

			// Se inserta en la tabla temporal
			oid = service.getOidCargaCliente();
			criteria.put("oid", oid);
			service.insertaClienteFile(listaClientes, criteria);

			// Se obtiene la lista de la tabla temporal
			List list = new ArrayList();
			list = service.getCargaClienteList(criteria);

			search.setCodigosErradosFile("");
			if (cont != 0)
				search.setCodigosErradosFile("Existe(n) " + cont
						+ " codigo(s) errado(s)");

			criteria.put("numRegistros", numRegistros);
			List list2 = new ArrayList();
			list2 = service.getResumenCargaClienteList(criteria);

			this.stoLevantamientoErroresClientesList2 = list;
			if (stoLevantamientoErroresClientesList2 != null) {
				this.setMostrarGrilla2(true);
			}
			this.stoResumenClientesList2 = list2;

		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));

		}

	}

	/**
	 * @param event
	 * @throws Exception
	 */
	public void handleFileUploadBusqueda(FileUploadEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("handleFileUpload");
		}
		try {
			MantenimientoSTOFacturacionAdicionalSearchForm search = (MantenimientoSTOFacturacionAdicionalSearchForm) this.formBusqueda;
			if (event != null) {
				// f.setArchivo(event.getFile());
				search.setClienteFile(event.getFile());
				this.setAttachment2(event.getFile().getFileName());
				this.loadArchivoBusqueda();
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));

		}

	}

	/**
	 * @param event
	 * @throws Exception
	 */
	public void handleFileUpload(FileUploadEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("handleFileUpload");
		}
		try {
			MantenimientoSTOFacturacionAdicionalForm search = (MantenimientoSTOFacturacionAdicionalForm) this.formMantenimiento;
			if (event != null) {
				// f.setArchivo(event.getFile());
				search.setClienteFile(event.getFile());
				this.setAttachment(event.getFile().getFileName());
				this.loadArchivo();
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setValidarConfirmar(java.lang.String)
	 */
	public String setValidarConfirmar(String accion) {

		if (accion.equals("ELIMINAR")) {
			if (objectosMultiplesSeleccionados == null
					|| objectosMultiplesSeleccionados.length == 0)
				return this.getResourceMessage("errors.select.item");
		}
		return null;
	}

	/**
	 * Limpia el archivo de Clientes
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void limpiarForm(MantenimientoSTOFacturacionAdicionalForm edit)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'limpiar' method");
		}

		edit.setCodigoCliente("");
		edit.setCodigosErrados("");
		edit.setCodigosErradosFile("");
		this.stoResumenClientesList2 = null;
		this.stoLevantamientoErroresClientesList2 = null;
		this.mostrarGrilla2 = false;
		this.attachment2 = null;
	}
	
	/**
	 * Limpiar el archivo de la pantalla form
	 * 
	 * @param event
	 */
	public void limpiarFormFile(ActionEvent event){
		try {

			if (log.isDebugEnabled()) {
				log.debug("Entering 'limpiar' method");
			}
			
			MantenimientoSTOFacturacionAdicionalForm edit = (MantenimientoSTOFacturacionAdicionalForm )this.formMantenimiento; 
			edit.setCodigoCliente("");
			edit.setCodigosErrados("");
			edit.setCodigosErradosFile("");
			this.stoResumenClientesList2 = null;
			this.stoLevantamientoErroresClientesList2 = null;
			this.mostrarGrilla2 = false;
			this.attachment2 = null;

		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
		
	}

	
	/**
	 * Limpia el archivo de Clientes
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void limpiar(MantenimientoSTOFacturacionAdicionalSearchForm search)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'limpiar' method");
		}
		search.setCodigoCliente("");
		search.setCodigosErradosFile("");
		this.oid = "";
		this.stoResumenClientesList = null;
		this.stoLevantamientoErroresClientesList = null;
		this.mostrarGrilla = false;
		this.mantenimiento = false;

	}
	
	/**
	 * Limpiar el archivo de la pantalla searchForm
	 * 
	 * @param event
	 */
	public void limpiarFile(ActionEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'limpiar' method");
		}
		
		try {
			MantenimientoSTOFacturacionAdicionalSearchForm search = (MantenimientoSTOFacturacionAdicionalSearchForm) this.formBusqueda;
			search.setCodigoCliente("");
			search.setCodigosErradosFile("");
			this.oid = "";
			this.stoResumenClientesList = null;
			this.stoLevantamientoErroresClientesList = null;
			this.mostrarGrilla = false;
			this.mantenimiento = false;
			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * Carga la Lista de subTiposClientes
	 * */
	public void loadSubTiposClientes(ValueChangeEvent val) {
		MantenimientoSTOFacturacionAdicionalSearchForm f = (MantenimientoSTOFacturacionAdicionalSearchForm) this.formBusqueda;
		if (val.getNewValue() == null) {
			f.setOidSubTipoCliente(null);
			f.setOidTipoClasificacion(null);
			f.setOidClasificacion(null);
			this.siccSubTipoClienteList = null;
			this.siccTipoClasificacionList = null;
			this.siccClasificacionList = null;
			return;
		}
		try {
			String stipos = (String) val.getNewValue();
			ArrayList tipos = new ArrayList();
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			if (!stipos.equals(null)) {
				tipos.add(stipos);
				AjaxService ajax = (AjaxService) getBean("ajaxService");
				this.siccSubTipoClienteList = ajax
						.getSubTiposClientesPorPaisTipoClientesOID(usuario
								.getIdioma().getCodigoISO(), tipos);

			} else {
				this.siccSubTipoClienteList = null;
			}
			this.siccTipoClasificacionList = null;
			this.siccClasificacionList = null;
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * Carga la Lista de subTiposClientes
	 * */
	public void loadSubTiposClientesMantenimiento(ValueChangeEvent val) {
		MantenimientoSTOFacturacionAdicionalForm f = (MantenimientoSTOFacturacionAdicionalForm) this.formMantenimiento;
		if (val.getNewValue() == null) {
			f.setOidSubTipoCliente(null);
			f.setOidTipoClasificacion(null);
			f.setOidClasificacion(null);
			this.siccSubTipoClienteList = null;
			this.siccTipoClasificacionList = null;
			this.siccClasificacionList = null;
			return;
		}
		try {
			String stipos = (String) val.getNewValue();
			ArrayList tipos = new ArrayList();
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			if (!stipos.equals(null)) {
				tipos.add(stipos);
				AjaxService ajax = (AjaxService) getBean("ajaxService");
				this.siccSubTipoClienteList = ajax
						.getSubTiposClientesPorPaisTipoClientesOID(usuario
								.getIdioma().getCodigoISO(), tipos);

			} else {
				this.siccSubTipoClienteList = null;
			}
			this.siccTipoClasificacionList = null;
			this.siccClasificacionList = null;
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * Carga tipos de clasificaciones
	 * 
	 * @param val
	 */
	public void loadTiposClasificaciones(ValueChangeEvent val) {
		MantenimientoSTOFacturacionAdicionalSearchForm f = (MantenimientoSTOFacturacionAdicionalSearchForm) this.formBusqueda;

		if (val.getNewValue() == null) {
			f.setOidTipoClasificacion(null);
			f.setOidClasificacion(null);
			this.siccTipoClasificacionList = null;
			this.siccClasificacionList = null;
			return;
		}
		try {
			MantenimientoSTOFacturacionAdicionalSearchForm search = (MantenimientoSTOFacturacionAdicionalSearchForm) this.formBusqueda;
			String stipos = (String) val.getNewValue();
			ArrayList listSubtipo = new ArrayList();

			if (!stipos.equals(null)) {
				listSubtipo.add(stipos);
				AjaxService ajax = (AjaxService) getBean("ajaxService");
				if (this.mantenimiento) {
					this.siccTipoClasificacionList = ajax
							.getTiposClasificacionesByCriteriaMultipleOID(
									this.codigoIdiomaISO,
									f.getOidTipoCliente(), listSubtipo);
				} else {
					this.siccTipoClasificacionList = ajax
							.getTiposClasificacionesByCriteriaMultipleOID(
									this.codigoIdiomaISO,
									search.getOidTipoCliente(), listSubtipo);
				}
			} else {
				this.siccTipoClasificacionList = null;
			}

			this.siccClasificacionList = null;
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * Carga tipos de clasificaciones
	 * 
	 * @param val
	 */
	public void loadTiposClasificacionesMantenimiento(ValueChangeEvent val) {
		MantenimientoSTOFacturacionAdicionalForm f = (MantenimientoSTOFacturacionAdicionalForm) this.formMantenimiento;

		if (val.getNewValue() == null) {
			f.setOidTipoClasificacion(null);
			f.setOidClasificacion(null);
			this.siccTipoClasificacionList = null;
			this.siccClasificacionList = null;
			return;
		}
		try {
			MantenimientoSTOFacturacionAdicionalForm search = (MantenimientoSTOFacturacionAdicionalForm) this.formMantenimiento;
			String stipos = (String) val.getNewValue();
			ArrayList listSubtipo = new ArrayList();

			if (!stipos.equals(null)) {
				listSubtipo.add(stipos);
				AjaxService ajax = (AjaxService) getBean("ajaxService");
				if (this.mantenimiento) {
					this.siccTipoClasificacionList = ajax
							.getTiposClasificacionesByCriteriaMultipleOID(
									this.codigoIdiomaISO,
									f.getOidTipoCliente(), listSubtipo);
				} else {
					this.siccTipoClasificacionList = ajax
							.getTiposClasificacionesByCriteriaMultipleOID(
									this.codigoIdiomaISO,
									search.getOidTipoCliente(), listSubtipo);
				}
			} else {
				this.siccTipoClasificacionList = null;
			}

			this.siccClasificacionList = null;
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * Carga Clasificaciones
	 * 
	 * @param val
	 */
	public void loadClasificaciones(ValueChangeEvent val) {
		if (val.getNewValue() == null) {
			this.siccClasificacionList = null;
			return;
		}
		try {
			MantenimientoSTOFacturacionAdicionalSearchForm search = (MantenimientoSTOFacturacionAdicionalSearchForm) this.formBusqueda;
			ArrayList listSubtipo = new ArrayList();
			listSubtipo.add(search.getOidSubTipoCliente());

			String tipoClasificacion = (String) val.getNewValue();
			ArrayList listTipoClasificacion = new ArrayList();
			listTipoClasificacion.add(tipoClasificacion);
			AjaxService ajax = (AjaxService) getBean("ajaxService");

			if (!tipoClasificacion.equals(null) || val.getNewValue().equals("")) {
				if (this.mantenimiento) {
					ArrayList listSubtipoForm = new ArrayList();
					listSubtipoForm.add(search.getOidSubTipoCliente());
					this.siccClasificacionList = ajax
							.getClasificacionesByCriteriaMultipleOID(
									this.codigoIdiomaISO,
									search.getOidTipoCliente(),
									listSubtipoForm, listTipoClasificacion);
				} else {

					this.siccClasificacionList = ajax
							.getClasificacionesByCriteriaMultipleOID(
									this.codigoIdiomaISO,
									search.getOidTipoCliente(), listSubtipo,
									listTipoClasificacion);
				}
			} else {
				this.siccClasificacionList = null;
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * Carga Clasificaciones
	 * 
	 * @param val
	 */
	public void loadClasificacionesMantenimiento(ValueChangeEvent val) {
		if (val.getNewValue() == null) {
			this.siccClasificacionList = null;
			return;
		}
		try {
			MantenimientoSTOFacturacionAdicionalForm f = (MantenimientoSTOFacturacionAdicionalForm) this.formMantenimiento;
			ArrayList listSubtipo = new ArrayList();
			listSubtipo.add(f.getOidSubTipoCliente());

			String tipoClasificacion = (String) val.getNewValue();
			ArrayList listTipoClasificacion = new ArrayList();
			listTipoClasificacion.add(tipoClasificacion);
			AjaxService ajax = (AjaxService) getBean("ajaxService");

			if (!tipoClasificacion.equals(null) || val.getNewValue().equals("")) {
				if (this.mantenimiento) {
					ArrayList listSubtipoForm = new ArrayList();
					listSubtipoForm.add(f.getOidSubTipoCliente());
					this.siccClasificacionList = ajax
							.getClasificacionesByCriteriaMultipleOID(
									this.codigoIdiomaISO,
									f.getOidTipoCliente(), listSubtipoForm,
									listTipoClasificacion);
				} else {

					this.siccClasificacionList = ajax
							.getClasificacionesByCriteriaMultipleOID(
									this.codigoIdiomaISO,
									f.getOidTipoCliente(), listSubtipo,
									listTipoClasificacion);
				}
			} else {
				this.siccClasificacionList = null;
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	public void loadZonasBusqueda(ValueChangeEvent val) {
		log.debug(">>loadZonas");
		MantenimientoSTOFacturacionAdicionalSearchForm fsearch = (MantenimientoSTOFacturacionAdicionalSearchForm) this.formBusqueda;
		if (val.getNewValue() == null) {
			fsearch.setCodigoZona(null);
			this.siccZonaList = null;
			return;

		}

		try {
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

			String valor = (String) val.getNewValue();
			String[] regiones = { valor };
			AjaxService ajax = (AjaxService) getBean("ajaxService");

			if (regiones.length > 0) {
				if (this.mantenimiento) {
					this.siccZonaList = ajax
							.getZonasMultipleByPaisMarcaCanalRegion(
									pais.getCodigo(), "T", "VD", regiones, "");
				} else {
					this.siccZonaList = ajax
							.getZonasMultipleByPaisMarcaCanalRegion(
									pais.getCodigo(), "T", "VD", regiones, "");
				}
			} else {
				this.siccZonaList = null;
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	public void loadZonasMantenimiento(ValueChangeEvent val) {
		log.debug(">>loadZonas");
		MantenimientoSTOFacturacionAdicionalForm f = (MantenimientoSTOFacturacionAdicionalForm) this.formMantenimiento;
		if (val.getNewValue() == null) {
			f.setCodigoZona(null);
			this.siccZonaList = null;
		}

		try {
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

			String valor = (String) val.getNewValue();
			String[] regiones = { valor };
			AjaxService ajax = (AjaxService) getBean("ajaxService");

			if (regiones.length > 0) {
				if (this.mantenimiento) {
					this.siccZonaList = ajax
							.getZonasMultipleByPaisMarcaCanalRegion(
									pais.getCodigo(), "T", "VD", regiones, "");
				} else {
					this.siccZonaList = ajax
							.getZonasMultipleByPaisMarcaCanalRegion(
									pais.getCodigo(), "T", "VD", regiones, "");
				}
			} else {
				this.siccZonaList = null;
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	public int validarCodigoConsultora() {

		try {
			MantenimientoSTOFacturacionAdicionalSearchForm search = (MantenimientoSTOFacturacionAdicionalSearchForm) this.formBusqueda;

			String codigoCliente = "";

			codigoCliente = search.getCodigoCliente();
			if (StringUtils.isEmpty(codigoCliente)) {
				return 0;
			} else {
				AjaxService ajax = (AjaxService) getBean("ajaxService");
				String codigocliente = ajax.getNombreCliente(codigoCliente);

				if (codigocliente != null) {
					return 0;
				} else
					return 1;

			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
			return 1;
		}

	}

	public void validarCodigoConsultoraForm() {
		try {
			MantenimientoSTOFacturacionAdicionalForm f = (MantenimientoSTOFacturacionAdicionalForm) this.formMantenimiento;
			String codigoCliente = "";
			String mensaje = "";

			codigoCliente = f.getCodigoCliente();
			if (StringUtils.isEmpty(codigoCliente)) {

			} else {
				AjaxService ajax = (AjaxService) getBean("ajaxService");
				String codCliente = ajax.getNombreCliente(codigoCliente);
				f.setNombreCliente(codCliente);
				if (codCliente == null) {
					mensaje = "Codigo de Cliente incorrecto, por favor ingrese un codigo valido para hacer la busqueda";
					this.addError("Error : ", mensaje);
					f.setCodigoCliente(null);
				}
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * @return the siccRegionList
	 */
	public List getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList
	 *            the siccRegionList to set
	 */
	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
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

	/**
	 * @return the siccSubTipoClienteList
	 */
	public LabelValue[] getSiccSubTipoClienteList() {
		return siccSubTipoClienteList;
	}

	/**
	 * @param siccSubTipoClienteList
	 *            the siccSubTipoClienteList to set
	 */
	public void setSiccSubTipoClienteList(LabelValue[] siccSubTipoClienteList) {
		this.siccSubTipoClienteList = siccSubTipoClienteList;
	}

	/**
	 * @return the siccTipoClasificacionList
	 */
	public LabelValue[] getSiccTipoClasificacionList() {
		return siccTipoClasificacionList;
	}

	/**
	 * @param siccTipoClasificacionList
	 *            the siccTipoClasificacionList to set
	 */
	public void setSiccTipoClasificacionList(
			LabelValue[] siccTipoClasificacionList) {
		this.siccTipoClasificacionList = siccTipoClasificacionList;
	}

	/**
	 * @return the siccClasificacionList
	 */
	public LabelValue[] getSiccClasificacionList() {
		return siccClasificacionList;
	}

	/**
	 * @param siccClasificacionList
	 *            the siccClasificacionList to set
	 */
	public void setSiccClasificacionList(LabelValue[] siccClasificacionList) {
		this.siccClasificacionList = siccClasificacionList;
	}

	/**
	 * @return the siccZonaList
	 */
	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	/**
	 * @param siccZonaList
	 *            the siccZonaList to set
	 */
	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	/**
	 * @return the codigoIdiomaISO
	 */
	public String getCodigoIdiomaISO() {
		return codigoIdiomaISO;
	}

	/**
	 * @param codigoIdiomaISO
	 *            the codigoIdiomaISO to set
	 */
	public void setCodigoIdiomaISO(String codigoIdiomaISO) {
		this.codigoIdiomaISO = codigoIdiomaISO;
	}

	/**
	 * @return the stoFacturaAdicionalList
	 */
	public List getStoFacturaAdicionalList() {
		return stoFacturaAdicionalList;
	}

	/**
	 * @param stoFacturaAdicionalList
	 *            the stoFacturaAdicionalList to set
	 */
	public void setStoFacturaAdicionalList(List stoFacturaAdicionalList) {
		this.stoFacturaAdicionalList = stoFacturaAdicionalList;
	}

	/**
	 * @return the stoLevantamientoErroresClientesList
	 */
	public List getStoLevantamientoErroresClientesList() {
		return stoLevantamientoErroresClientesList;
	}

	/**
	 * @param stoLevantamientoErroresClientesList
	 *            the stoLevantamientoErroresClientesList to set
	 */
	public void setStoLevantamientoErroresClientesList(
			List stoLevantamientoErroresClientesList) {
		this.stoLevantamientoErroresClientesList = stoLevantamientoErroresClientesList;
	}

	/**
	 * @return the stoResumenClientesList
	 */
	public List getStoResumenClientesList() {
		return stoResumenClientesList;
	}

	/**
	 * @param stoResumenClientesList
	 *            the stoResumenClientesList to set
	 */
	public void setStoResumenClientesList(List stoResumenClientesList) {
		this.stoResumenClientesList = stoResumenClientesList;
	}

	/**
	 * @return
	 */
	public String getIndicadorGraboFacturaAdicional() {
		return indicadorGraboFacturaAdicional;
	}

	/**
	 * @param indicadorGraboFacturaAdicional
	 */
	public void setIndicadorGraboFacturaAdicional(
			String indicadorGraboFacturaAdicional) {
		this.indicadorGraboFacturaAdicional = indicadorGraboFacturaAdicional;
	}

	/**
	 * @return
	 */
	public boolean isMantenimiento() {
		return mantenimiento;
	}

	/**
	 * @param mantenimiento
	 */
	public void setMantenimiento(boolean mantenimiento) {
		this.mantenimiento = mantenimiento;
	}

	/**
	 * @return
	 */
	public Boolean getMostrarGrilla() {
		return mostrarGrilla;
	}

	/**
	 * @param mostrarGrilla
	 */
	public void setMostrarGrilla(Boolean mostrarGrilla) {
		this.mostrarGrilla = mostrarGrilla;
	}

	/**
	 * @return the attachment
	 */
	public String getAttachment() {
		return attachment;
	}

	/**
	 * @param attachment
	 *            the attachment to set
	 */
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	/**
	 * @return
	 */
	public String getOidSubTipoCliente() {
		return oidSubTipoCliente;
	}

	/**
	 * @param oidSubTipoCliente
	 */
	public void setOidSubTipoCliente(String oidSubTipoCliente) {
		this.oidSubTipoCliente = oidSubTipoCliente;
	}

	/**
	 * @return
	 */
	public String getOidSubTipoClasificacion() {
		return oidSubTipoClasificacion;
	}

	/**
	 * @param oidSubTipoClasificacion
	 */
	public void setOidSubTipoClasificacion(String oidSubTipoClasificacion) {
		this.oidSubTipoClasificacion = oidSubTipoClasificacion;
	}

	/**
	 * @return
	 */
	public String getOidClasificacion() {
		return oidClasificacion;
	}

	/**
	 * @param oidClasificacion
	 */
	public void setOidClasificacion(String oidClasificacion) {
		this.oidClasificacion = oidClasificacion;
	}

	/**
	 * @return the codigoZona
	 */
	public String getCodigoZona() {
		return codigoZona;
	}

	/**
	 * @param codigoZona
	 *            the codigoZona to set
	 */
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}

	/**
	 * @return the oid
	 */
	public String getOid() {
		return oid;
	}

	/**
	 * @param oid
	 *            the oid to set
	 */
	public void setOid(String oid) {
		this.oid = oid;
	}

	/**
	 * @return the stoLevantamientoErroresClientesList2
	 */
	public List getStoLevantamientoErroresClientesList2() {
		return stoLevantamientoErroresClientesList2;
	}

	/**
	 * @param stoLevantamientoErroresClientesList2
	 *            the stoLevantamientoErroresClientesList2 to set
	 */
	public void setStoLevantamientoErroresClientesList2(
			List stoLevantamientoErroresClientesList2) {
		this.stoLevantamientoErroresClientesList2 = stoLevantamientoErroresClientesList2;
	}

	/**
	 * @return the stoResumenClientesList2
	 */
	public List getStoResumenClientesList2() {
		return stoResumenClientesList2;
	}

	/**
	 * @param stoResumenClientesList2
	 *            the stoResumenClientesList2 to set
	 */
	public void setStoResumenClientesList2(List stoResumenClientesList2) {
		this.stoResumenClientesList2 = stoResumenClientesList2;
	}

	/**
	 * @return the mostrarGrilla2
	 */
	public Boolean getMostrarGrilla2() {
		return mostrarGrilla2;
	}

	/**
	 * @param mostrarGrilla2
	 *            the mostrarGrilla2 to set
	 */
	public void setMostrarGrilla2(Boolean mostrarGrilla2) {
		this.mostrarGrilla2 = mostrarGrilla2;
	}

	/**
	 * @return the attachment2
	 */
	public String getAttachment2() {
		return attachment2;
	}

	/**
	 * @param attachment2
	 *            the attachment2 to set
	 */
	public void setAttachment2(String attachment2) {
		this.attachment2 = attachment2;
	}

	/**
	 * @return the objectosMultiplesSeleccionados
	 */
	public Object[] getObjectosMultiplesSeleccionados() {
		return objectosMultiplesSeleccionados;
	}

	/**
	 * @param objectosMultiplesSeleccionados
	 *            the objectosMultiplesSeleccionados to set
	 */
	public void setObjectosMultiplesSeleccionados(
			Object[] objectosMultiplesSeleccionados) {
		this.objectosMultiplesSeleccionados = objectosMultiplesSeleccionados;
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
		boolean flag = false;
		MantenimientoSTOFacturacionAdicionalForm f = (MantenimientoSTOFacturacionAdicionalForm) this.formMantenimiento;
		if (this.stoLevantamientoErroresClientesList2 != null
				&& this.stoLevantamientoErroresClientesList2.size() > 0) {
			flag = true;
		}
		if (StringUtils.isBlank(f.getOidTipoCliente()) && !flag
				&& StringUtils.isBlank(f.getCodigoRegion())
				&& StringUtils.isBlank(f.getCodigoCliente())) {
			mensaje = this
					.getResourceMessage("mantenimientoSTOFacturacionAdicionalForm.valida.campos");
		}
		if (StringUtils.isNotBlank(f.getCodigosErradosFile())) {
			mensaje = "Antes de Guardar por favor corrija el/los Codigo(s) del Cliente";
		}
		return mensaje;
	}

	/**
	 * @return the dmSTOFacturacionAdicional
	 */
	public DataTableModel getDmSTOFacturacionAdicional() {
		return dmSTOFacturacionAdicional;
	}

	/**
	 * @param dmSTOFacturacionAdicional
	 *            the dmSTOFacturacionAdicional to set
	 */
	public void setDmSTOFacturacionAdicional(
			DataTableModel dmSTOFacturacionAdicional) {
		this.dmSTOFacturacionAdicional = dmSTOFacturacionAdicional;
	}

}