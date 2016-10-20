package biz.belcorp.ssicc.web.spusicc.fac.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.primefaces.context.RequestContext;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.sisicc.model.BaseOID;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.fac.MantenimientoFACGenericoService;
import biz.belcorp.ssicc.service.spusicc.inc.MantenimientoINCConfiguracionConcursoService;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.service.spusicc.mav.MantenimientoMAVConfiguracionService;
import biz.belcorp.ssicc.service.spusicc.men.MantenimientoMENGenericoService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECCodigoVentaOperaService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.fac.form.MantenimientoFACDeshabilitarZonasEnvioBoletasPremioForm;
import biz.belcorp.ssicc.web.spusicc.mav.form.MantenimientoMAVConfiguracionCondicionEnvioForm;
import biz.belcorp.ssicc.web.spusicc.mav.form.MantenimientoMAVConfiguracionForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MantenimientoFACDeshabilitarZonasEnvioBoletasPremioAction extends
		BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = 6013163031967501046L;
	private List siccRegionList;
	private List siccCapacitadoraList;
	private LabelValue[] siccZonaList;
	private List regionesList;
	private List regionesListR;
	private String idPopup;
	private String indicadorCerrarVentana;
	private String codigoIdiomaISO;
	private Object[] beanMantenimientoFACDeshabilitarZonasEnvioBoletasPremio;
	private List consultoraList;
	private List clasificacionesList;
	private List unidadesList;
	private List siccTipoClienteList;
	private List codigoEstatusList;
	private List consultoraListR;
	private List clasificacionesListR;
	private List unidadesListR;
	private List codigoEstatusListR;
	private String insertar;
	private String codConsRest;
	private String indicadorTipo;
	private Boolean mostrarPaneles;
	private String indicadorAccion;;

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#getSalirForward()
	 */
	@Override
	protected String getSalirForward() {
		return "mantenimientoFACDeshabilitarZonasEnvioBoletasPremioList";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		return null;
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
	 * BaseMantenimientoSearchAbstractAction
	 * #setValidarConfirmar(java.lang.String)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setValidarConfirmar(java.lang.String)
	 */
	@Override
	public String setValidarConfirmar(String accion) {
		RequestContext context = RequestContext.getCurrentInstance();

		context.addCallbackParam("retornoMensaje", "");
		return "";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setSaveAttributes()
	 */
	@Override
	protected boolean setSaveAttributes() throws Exception {

		MantenimientoFACDeshabilitarZonasEnvioBoletasPremioForm f = (MantenimientoFACDeshabilitarZonasEnvioBoletasPremioForm) this.formBusqueda;
		MantenimientoFACGenericoService service = (MantenimientoFACGenericoService) getBean("spusicc.mantenimientoFACGenericoService");

		List regionesList = this.regionesList;

		service.deleteZonasDeshabilitadasParaEnvio();
		try {
			for (int i = 0; i < regionesList.size(); i++) {
				Map criteriaInsert = (Map) regionesList.get(i);
				criteriaInsert.put("codigoPais", f.getCodigoPais());
				criteriaInsert.put("codigoMarca",
						Constants.CODIGO_MARCA_DEFAULT);
				criteriaInsert.put("codigoCanal",
						Constants.CODIGO_CANAL_DEFAULT);

				if (StringUtils.isNotBlank(MapUtils.getString(criteriaInsert,
						"codigoZona"))) {
					service.insertZonasDeshabilitadasParaEnvio(criteriaInsert);
				}

			}
			return true;
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setObtenerRegistroAttributes()
	 */
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		return null;
	}

	public void loadZonas(ValueChangeEvent val) {
		log.debug(">>loadZonas");
		try {
			MantenimientoFACDeshabilitarZonasEnvioBoletasPremioForm search = (MantenimientoFACDeshabilitarZonasEnvioBoletasPremioForm) this.formBusqueda;
			String[] valor = (String[]) val.getNewValue();
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			if (valor.length > 0) {
				LabelValue[] result = ajax.getZonasMultipleByPaisMarcaCanalRegion(this.mPantallaPrincipalBean.getCurrentCountry().getCodigo(), "T", "VD", valor, "");
				result[0]= new LabelValue("Todos","T");
				
				this.setSiccZonaList(result);
			} else {
				this.siccZonaList = null;
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}		
	}

	/**
	 * 
	 */
	public void limpiara() {
		this.mostrarBotonBuscar = false;
		this.mostrarBotonConsultar = false;
		this.mostrarBotonModificar = false;
		this.mostrarBotonNuevo = false;
		this.mostrarBotonEliminar = false;
		this.insertar = "S";
		this.codConsRest = "30";
		this.indicadorTipo = "C";
		this.mostrarPaneles = true;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		limpiara();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoFACDeshabilitarZonasEnvioBoletasPremioForm f = (MantenimientoFACDeshabilitarZonasEnvioBoletasPremioForm) this.formBusqueda;
		MantenimientoFACGenericoService serviceGen = (MantenimientoFACGenericoService) getBean("spusicc.mantenimientoFACGenericoService");

//		String codigoConsRest = this.getParametrosPantalla().get("codConsRest");
		String indicadorTipo = this.getParametrosPantalla()
				.get("indicadorTipo");
		String indicadorUnidad = this.getParametrosPantalla().get(
				"indicadorUnidad");
		String numeroUnidades = this.getParametrosPantalla().get(
				"numeroUnidades");
//		String indicadorAccion = this.getParametrosPantalla().get(
//				"indicadorAccion");
		this.indicadorAccion = "";
		this.idPopup = "";
		this.indicadorCerrarVentana = Constants.NRO_CERO;
		this.codigoIdiomaISO = usuario.getIdioma().getCodigoISO();

		int codigo = Integer.parseInt("30");

		String forward = "";

		f.setCodigoPais(pais.getCodigo());
		f.setOidPais(obtenerOidPais(pais.getCodigo()));
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");

		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		criteriaOperacion.put("indicadorTipoRegion", Constants.SI);

		siccRegionList = reporteService.getListaGenerico("getRegionesByPais",
				criteriaOperacion);
		siccCapacitadoraList = reporteService.getListaGenerico(
				"getRegionesByPais", criteriaOperacion);
		siccZonaList = null;

		f.setIndicadorUnidadPopup(indicadorUnidad);

		if (StringUtils.equals(indicadorUnidad, Constants.NO)) {
			f.setNumeroUnidadesPopup(numeroUnidades);
			f.setNumeroUnidadesPopupUniDifNO(numeroUnidades);
		}

		regionesList = serviceGen.getZonasDeshabilitadas();
		actualizarTotalUnidades(true);

		forward = Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo) ? "viewListaRegionZona"
				: "viewListaRegionZonaRest";

		log.debug("forward ss " + forward);
		// return mapping.findForward(forward);

	}

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void deletePopup(ActionEvent evt) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'deletePopup' method");
		}
		try {
			List hola = this.regionesList;
			MantenimientoFACDeshabilitarZonasEnvioBoletasPremioForm f = (MantenimientoFACDeshabilitarZonasEnvioBoletasPremioForm) this.formBusqueda;

			String codigoConsRest = this.codConsRest;
			String indicadorTipo = this.indicadorTipo;

			// HashMap<String, Object> sistemabusqueda = (HashMap<String,
			// Object>)this.beanRegistroSeleccionado;

			List listaDelete = new ArrayList();
			int tamanio = beanMantenimientoFACDeshabilitarZonasEnvioBoletasPremio.length;
			for (int i = 0; i < tamanio; i++) {
				HashMap<String, Object> form = (HashMap<String, Object>) beanMantenimientoFACDeshabilitarZonasEnvioBoletasPremio[i];
				listaDelete.add(form);
			}

			// String id = (String) request.getParameter("id");

			// log.debug("delete popup id >>>>: " + id);
			this.indicadorCerrarVentana = Constants.NRO_CERO;

			// int index = Integer.parseInt(id) - 1;
			int codigo = Integer.parseInt(codigoConsRest);
			String forward = "";
			// List list=null;

			List list = this.regionesList;
			// Map map = (Map) list.get(index);
			// String indicadorAcion = (String) map.get("indicadorAccion");

			if (tamanio > 0) {
				// String[] ids = f.getSelectedItemsPopup();

				for (int i = 0; i < tamanio; i++) {
					list.remove(listaDelete.get(i));

					/*
					 * index = Integer.parseInt(ids[i])-1;
					 * map.put("indicadorAccionAnterior", indicadorAcion);//guardo
					 * el estado anterior por si ocurre una cancelacion
					 * map.put("indicadorAccion",
					 * Constants.MAV_ESTADO_TMP_ELIMINAR);
					 */
				}

				this.regionesList = list;
			}

			actualizarTotalUnidades(true);
			forward = Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo) ? "viewListaRegionZona"
					: "viewListaRegionZonaRest";

			log.debug("forward " + forward);
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	/*
	 * public ActionForward savePopup(ActionMapping mapping, ActionForm
	 * form,HttpServletRequest request, HttpServletResponse response) throws
	 * Exception { if (log.isDebugEnabled()) {
	 * log.debug("Entering 'savePopup' method"); }
	 * 
	 * HttpSession session = request.getSession(true); Pais pais =
	 * getPais(session); Usuario usuario = getUsuario(session);
	 * 
	 * MantenimientoMAVConfiguracionForm f = (MantenimientoMAVConfiguracionForm)
	 * form; //MantenimientoMENGenericoService service =
	 * (MantenimientoMENGenericoService)
	 * getBean("spusicc.mantenimientoMENGenericoService");
	 * MantenimientoMAEClienteService clienteService =
	 * (MantenimientoMAEClienteService)
	 * getBean("spusicc.mantenimientoMAEClienteService");
	 * 
	 * String codigoConsRest=(String)request.getParameter("codConsRest"); String
	 * indicadorTipo=(String)request.getParameter("indicadorTipo"); String
	 * idPopup = (String)session.getAttribute("idPopup");
	 * session.setAttribute("indicadorCerrarVentana", Constants.NRO_UNO);
	 * //indicadorCerrarVentana
	 * 
	 * // Asignamos al codigo del periodo el valor por defecto Map crit = new
	 * HashMap(); crit.put("codigoPais", f.getCodigoPais());
	 * crit.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
	 * crit.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
	 * crit.put("codigoISO", usuario.getIdioma().getCodigoISO());
	 * 
	 * //recuperamos el oid Pais String oidPais =
	 * clienteService.getOidPais(crit);
	 * 
	 * int codigo =Integer.parseInt(codigoConsRest); String forward=""; List
	 * list=null; session.setAttribute("idPopup",idPopup);
	 * log.debug("idPopup xxxxx "+idPopup);
	 * 
	 * switch (codigo) { case Constants.MAV_CONRES_LISTA_CONSU: list
	 * =(List)session.getAttribute("consultoraList");
	 * 
	 * if(list != null) { for(int i=list.size()-1;i>=0;i--){ Map
	 * m=(Map)list.get(i); String indicadorAccion = (String)
	 * m.get("indicadorAccion");
	 * if(Constants.MAV_ESTADO_TMP_INSERTAR.equals(indicadorAccion)){
	 * m.put("indicadorAccion",Constants.NRO_CERO); }
	 * if(Constants.MAV_ESTADO_TMP_ELIMINAR.equals(indicadorAccion)){
	 * m.put("indicadorAccion",Constants.NUMERO_DOS); } } } forward
	 * =Constants.MAV_TIPO_CONSIDERACION
	 * .equals(indicadorTipo)?"viewListaCliente":"viewListaClienteRest"; break;
	 * case Constants.MAV_CONRES_LISTA_REGION_ZONA: list
	 * =(List)session.getAttribute("regionesList");
	 * 
	 * if(list != null) { for(int i=list.size()-1;i>=0;i--){ Map
	 * m=(Map)list.get(i); String indicadorAccion = (String)
	 * m.get("indicadorAccion");
	 * if(Constants.MAV_ESTADO_TMP_INSERTAR.equals(indicadorAccion)){
	 * m.put("indicadorAccion",Constants.NRO_CERO); }
	 * if(Constants.MAV_ESTADO_TMP_ELIMINAR.equals(indicadorAccion)){
	 * m.put("indicadorAccion",Constants.NUMERO_DOS); } } } forward
	 * =Constants.MAV_TIPO_CONSIDERACION
	 * .equals(indicadorTipo)?"viewListaRegionZona":"viewListaRegionZonaRest";
	 * break; case Constants.MAV_CONRES_CLASIFICACIONES_CLIENTE: list
	 * =(List)session.getAttribute("clasificacionesList"); for(int
	 * i=list.size()-1;i>=0;i--){ Map m=(Map)list.get(i); String indicadorAccion
	 * = (String) m.get("indicadorAccion");
	 * if(Constants.MAV_ESTADO_TMP_INSERTAR.equals(indicadorAccion)){
	 * m.put("indicadorAccion",Constants.NRO_CERO); }
	 * if(Constants.MAV_ESTADO_TMP_ELIMINAR.equals(indicadorAccion)){
	 * m.put("indicadorAccion",Constants.NUMERO_DOS); } } forward
	 * =Constants.MAV_TIPO_CONSIDERACION
	 * .equals(indicadorTipo)?"viewClasificacionesCliente"
	 * :"viewClasificacionesClienteRest"; break; case
	 * Constants.MAV_CONRES_UNIDAD_ADM: list
	 * =(List)session.getAttribute("unidadesList"); for(int
	 * i=list.size()-1;i>=0;i--){ Map m=(Map)list.get(i); String indicadorAccion
	 * = (String) m.get("indicadorAccion");
	 * if(Constants.MAV_ESTADO_TMP_INSERTAR.equals(indicadorAccion)){
	 * m.put("indicadorAccion",Constants.NRO_CERO); }
	 * if(Constants.MAV_ESTADO_TMP_ELIMINAR.equals(indicadorAccion)){
	 * m.put("indicadorAccion",Constants.NUMERO_DOS); } } forward
	 * =Constants.MAV_TIPO_CONSIDERACION
	 * .equals(indicadorTipo)?"viewUnidadAdministrativa"
	 * :"viewUnidadAdministrativaRest"; break; case
	 * Constants.MAV_CONRES_ESTATUS_CLIENTE: list
	 * =(List)session.getAttribute("codigoEstatusList"); for(int
	 * i=list.size()-1;i>=0;i--){ Map m=(Map)list.get(i); String indicadorAccion
	 * = (String) m.get("indicadorAccion");
	 * if(Constants.MAV_ESTADO_TMP_INSERTAR.equals(indicadorAccion)){
	 * m.put("indicadorAccion",Constants.NRO_CERO); }
	 * if(Constants.MAV_ESTADO_TMP_ELIMINAR.equals(indicadorAccion)){
	 * m.put("indicadorAccion",Constants.NUMERO_DOS); } } forward
	 * =Constants.MAV_TIPO_CONSIDERACION
	 * .equals(indicadorTipo)?"viewEstatusCliente":"viewEstatusClienteRest";
	 * break;
	 * 
	 * case Constants.MAV_CONRES_LISTA_CONSU_REST: list
	 * =(List)session.getAttribute("consultoraListR");
	 * 
	 * if(list != null) { for(int i=list.size()-1;i>=0;i--){ Map
	 * m=(Map)list.get(i); String indicadorAccion = (String)
	 * m.get("indicadorAccion");
	 * if(Constants.MAV_ESTADO_TMP_INSERTAR.equals(indicadorAccion)){
	 * m.put("indicadorAccion",Constants.NRO_CERO); }
	 * if(Constants.MAV_ESTADO_TMP_ELIMINAR.equals(indicadorAccion)){
	 * m.put("indicadorAccion",Constants.NUMERO_DOS); } } } forward
	 * =Constants.MAV_TIPO_CONSIDERACION
	 * .equals(indicadorTipo)?"viewListaCliente":"viewListaClienteRest"; break;
	 * case Constants.MAV_CONRES_LISTA_REGION_ZONA_REST: list
	 * =(List)session.getAttribute("regionesListR");
	 * 
	 * if(list != null) { for(int i=list.size()-1;i>=0;i--){ Map
	 * m=(Map)list.get(i); String indicadorAccion = (String)
	 * m.get("indicadorAccion");
	 * if(Constants.MAV_ESTADO_TMP_INSERTAR.equals(indicadorAccion)){
	 * m.put("indicadorAccion",Constants.NRO_CERO); }
	 * if(Constants.MAV_ESTADO_TMP_ELIMINAR.equals(indicadorAccion)){
	 * m.put("indicadorAccion",Constants.NUMERO_DOS); } } } forward
	 * =Constants.MAV_TIPO_CONSIDERACION
	 * .equals(indicadorTipo)?"viewListaRegionZona":"viewListaRegionZonaRest";
	 * break; case Constants.MAV_CONRES_CLASIFICACIONES_CLIENTE_REST: list
	 * =(List)session.getAttribute("clasificacionesListR"); for(int
	 * i=list.size()-1;i>=0;i--){ Map m=(Map)list.get(i); String indicadorAccion
	 * = (String) m.get("indicadorAccion");
	 * if(Constants.MAV_ESTADO_TMP_INSERTAR.equals(indicadorAccion)){
	 * m.put("indicadorAccion",Constants.NRO_CERO); }
	 * if(Constants.MAV_ESTADO_TMP_ELIMINAR.equals(indicadorAccion)){
	 * m.put("indicadorAccion",Constants.NUMERO_DOS); } } forward
	 * =Constants.MAV_TIPO_CONSIDERACION
	 * .equals(indicadorTipo)?"viewClasificacionesCliente"
	 * :"viewClasificacionesClienteRest"; break; case
	 * Constants.MAV_CONRES_UNIDAD_ADM_REST: list
	 * =(List)session.getAttribute("unidadesListR"); for(int
	 * i=list.size()-1;i>=0;i--){ Map m=(Map)list.get(i); String indicadorAccion
	 * = (String) m.get("indicadorAccion");
	 * if(Constants.MAV_ESTADO_TMP_INSERTAR.equals(indicadorAccion)){
	 * m.put("indicadorAccion",Constants.NRO_CERO); }
	 * if(Constants.MAV_ESTADO_TMP_ELIMINAR.equals(indicadorAccion)){
	 * m.put("indicadorAccion",Constants.NUMERO_DOS); } } forward
	 * =Constants.MAV_TIPO_CONSIDERACION
	 * .equals(indicadorTipo)?"viewUnidadAdministrativa"
	 * :"viewUnidadAdministrativaRest"; break; case
	 * Constants.MAV_CONRES_ESTATUS_CLIENTE_REST: list
	 * =(List)session.getAttribute("codigoEstatusListR"); for(int
	 * i=list.size()-1;i>=0;i--){ Map m=(Map)list.get(i); String indicadorAccion
	 * = (String) m.get("indicadorAccion");
	 * if(Constants.MAV_ESTADO_TMP_INSERTAR.equals(indicadorAccion)){
	 * m.put("indicadorAccion",Constants.NRO_CERO); }
	 * if(Constants.MAV_ESTADO_TMP_ELIMINAR.equals(indicadorAccion)){
	 * m.put("indicadorAccion",Constants.NUMERO_DOS); } } forward
	 * =Constants.MAV_TIPO_CONSIDERACION
	 * .equals(indicadorTipo)?"viewEstatusCliente":"viewEstatusClienteRest";
	 * break; } log.debug("forward "+forward); return
	 * mapping.findForward(forward); }
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.action.BaseMantenimientoAbstractAction#
	 * setEditAttributes(javax.servlet.http.HttpServletRequest,
	 * biz.belcorp.ssicc.web.form.BaseMantenimientoForm)
	 */
	// protected void setEditAttributes(HttpServletRequest request,
	// BaseMantenimientoForm form) throws Exception {
	// HttpSession session = request.getSession(true);
	// Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
	// MantenimientoMAVConfiguracionForm f =
	// (MantenimientoMAVConfiguracionForm)formBusqueda;
	// String id = request.getParameter("id");
	//
	// log.debug("row id " + id);
	// String mensaje= null;
	// if (id != null) {
	// try {
	// List list = (List) session
	// .getAttribute(Constants.MAV_CONFIGURACIONES_LIST);
	// Map map = (Map) list.get(Integer.parseInt(id) - 1);
	// log.debug("map " + map);
	//
	// Map criteria = new HashMap();
	// criteria.put("codigoPais", map.get("codigoPais").toString());
	// criteria.put("correlativo", map.get("correlativo").toString());
	//
	// obtenerRegistro(request, f, criteria);
	//
	// log.debug("enviando para editar");
	// } catch (Exception e) {
	// String error = e.getMessage();
	// if (StringUtils.isBlank(error))
	// error = e.getLocalizedMessage();
	//
	// mensaje = this.getResourceMessage("errors.detail");
	// this.addError("Error : ", mensaje);
	// }
	//
	// }
	//
	// }

	/**
	 * Obtiene los datos de un registro de Configuracion MAV
	 * 
	 * @param request
	 * @param f
	 * @param criteria
	 * @throws Exception
	 */
	private void obtenerRegistro(HttpServletRequest request,
			MantenimientoMAVConfiguracionForm f, Map criteria) throws Exception {
		MantenimientoMAVConfiguracionService service = (MantenimientoMAVConfiguracionService) getBean("spusicc.mantenimientoMAVConfiguracionService");
		MantenimientoINCConfiguracionConcursoService serviceInc = (MantenimientoINCConfiguracionConcursoService) getBean("spusicc.mantenimientoINCConfiguracionConcursoService");
		MantenimientoRECCodigoVentaOperaService serviceREC = (MantenimientoRECCodigoVentaOperaService) getBean("spusicc.mantenimientoRECCodigoVentaOperaService");

		HttpSession session = request.getSession(true);

		Map mapConfiguracion = service.getConfiguracion(criteria);
		BeanUtils.copyProperties(f, mapConfiguracion);

		/* obteniendo lista de tipos de oferta */
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		LabelValue[] listTiposOferta = ajaxService.getTiposOfertaByActividad(f
				.getOidActividad());
		session.setAttribute(Constants.MAV_TIPOS_OFERTA_LIST, listTiposOferta);

		// actualizando el oid del tipo de oferta
		f.setOidActividadTipoOferta(f.getOidActividadTipoOferta() + "__"
				+ f.getCodigoTipoOferta());
		f.setTabSeleccion(Constants.MAV_TAB_CONSIDERACION);

		// Cargar Unidades de Negocio, Negocios, MarcaProducto
		session.setAttribute(Constants.INC_UNIDAD_NEGOCIO_LIST,
				serviceInc.getUnidadesNegocio());
		session.setAttribute(Constants.INC_NEGOCIO_LIST,
				serviceInc.getNegocios());
		session.setAttribute(Constants.INC_MARCA_PRODUCTO_LIST,
				serviceInc.getMarcaProductos());
		session.setAttribute(Constants.REC_CODIGO_CATALOGO_LIST,
				service.getCatalogos());

		session.removeAttribute("clasificacionesList");
		session.removeAttribute("unidadesList");
		session.removeAttribute("consultoraList");
		session.removeAttribute("regionesList");
		session.removeAttribute("codigoEstatusList");
		session.removeAttribute("clasificacionesListR");
		session.removeAttribute("unidadesListR");
		session.removeAttribute("consultoraListR");
		session.removeAttribute("regionesListR");
		session.removeAttribute("codigoEstatusListR");

		String indicadorDirigido = "G";
		if (f.getCodigoTipoCliente().equals(
				Constants.MAE_TIPO_CLIENTE_CONSULTORA)) {
			indicadorDirigido = "C";
		}

		// CARGAR TODAS CONSIDERACIONES
		session.setAttribute("mavConsideracionTodosList",
				service.getConsideracion(criteria));
		// CARGAR TODAS RESTRICCONES
		session.setAttribute("mavRestriccionTodosList",
				service.getRestriccion(criteria));

		String indicadorUnidad = "S".equals(f.getIndicadorUnidad()) ? "S" : "";

		// CARGAR CONSIDERACIONES
		session.setAttribute(Constants.MAV_CONSIDERACION_LIST, ajaxService
				.getConsideraciones(indicadorDirigido, indicadorUnidad));
		// CARGAR RESTRICCONES
		session.setAttribute(Constants.MAV_RESTRICCION_LIST, ajaxService
				.getRestricciones(indicadorDirigido, indicadorUnidad));

		f.setTabSeleccion(Constants.MAV_TAB_CONSIDERACION);

		// cargando la lista de consideraciones y restricciones
		criteria.put("correlativoMAV", f.getCorrelativo());
		criteria.put("tipoConsideracion", Constants.MAV_TIPO_CONSIDERACION);
		List listConsi = service.getRestConsideracion(criteria);
		for (int i = 0; i < listConsi.size(); i++) {
			Map mapConsi = (Map) listConsi.get(i);

			String indicadorTipo = (String) mapConsi.get("indicadorTipo");
			String codigoConsideracion = mapConsi.get("codigoConsideracion")
					.toString();
			String condicionAux = null;
			String descripcionAux = "";

			if (Constants.MAV_TIPO_CONDICIONES_MIXTAS.equals(indicadorTipo)) {
				if ((Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_MARCA == Integer
						.parseInt(codigoConsideracion))
						|| (Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_MARCA == Integer
								.parseInt(codigoConsideracion))) {
					condicionAux = mapConsi.get("condicion2").toString();
					descripcionAux = getDesMarcaProducto(request, condicionAux);
				}
				if ((Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_NEGOCIO == Integer
						.parseInt(codigoConsideracion))
						|| (Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_NEGOCIO == Integer
								.parseInt(codigoConsideracion))) {
					condicionAux = mapConsi.get("condicion2").toString();
					descripcionAux = getDesNegocio(request, condicionAux);
				}

				if ((Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_UNIDAD == Integer
						.parseInt(codigoConsideracion))
						|| (Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_UNIDAD == Integer
								.parseInt(codigoConsideracion))) {
					condicionAux = mapConsi.get("condicion2").toString();
					descripcionAux = getDesUnidadNegocio(request, condicionAux);
				}

				if ((Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_CATALOGO == Integer
						.parseInt(codigoConsideracion))
						|| (Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_CATALOGO == Integer
								.parseInt(codigoConsideracion))) {
					condicionAux = mapConsi.get("condicion2").toString();
					descripcionAux = getDesCatalogo(request, condicionAux);
				}

				if (condicionAux != null) {
					mapConsi.put("condicion", mapConsi.get("condicion1")
							.toString()
							+ " , "
							+ descripcionAux
							+ " , "
							+ mapConsi.get("condicion3").toString()
							+ " , "
							+ mapConsi.get("condicion4").toString());
				}
			}
		}
		session.setAttribute("mavConfiguracionConsideracionList", listConsi);

		criteria.put("tipoConsideracion", Constants.MAV_TIPO_RESTRICCION);
		List listRest = service.getRestConsideracion(criteria);
		for (int i = 0; i < listRest.size(); i++) {
			Map mapRest = (Map) listRest.get(i);

			String indicadorTipo = mapRest.get("indicadorTipo").toString();
			String codigoRestriccion = mapRest.get("codigoRestriccion")
					.toString();
			String condicionAux = null;
			String descripcionAux = "";

			if (Constants.MAV_TIPO_CONDICIONES_MIXTAS.equals(indicadorTipo)) {
				if ((Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_MARCA_REST == Integer
						.parseInt(codigoRestriccion))
						|| (Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_MARCA_REST == Integer
								.parseInt(codigoRestriccion))) {
					condicionAux = mapRest.get("condicion2").toString();
					descripcionAux = getDesMarcaProducto(request, condicionAux);
				}
				if ((Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_NEGOCIO_REST == Integer
						.parseInt(codigoRestriccion))
						|| (Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_NEGOCIO_REST == Integer
								.parseInt(codigoRestriccion))) {
					condicionAux = mapRest.get("condicion2").toString();
					descripcionAux = getDesNegocio(request, condicionAux);
				}

				if ((Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_UNIDAD_REST == Integer
						.parseInt(codigoRestriccion))
						|| (Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_UNIDAD_REST == Integer
								.parseInt(codigoRestriccion))) {
					condicionAux = mapRest.get("condicion2").toString();
					descripcionAux = getDesUnidadNegocio(request, condicionAux);
				}

				if ((Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_CATALOGO_REST == Integer
						.parseInt(codigoRestriccion))
						|| (Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_CATALOGO_REST == Integer
								.parseInt(codigoRestriccion))) {
					condicionAux = mapRest.get("condicion2").toString();
					descripcionAux = getDesCatalogo(request, condicionAux);
				}

				if (condicionAux != null) {
					mapRest.put("condicion", mapRest.get("condicion1")
							.toString()
							+ " , "
							+ descripcionAux
							+ " , "
							+ mapRest.get("condicion3").toString()
							+ " , "
							+ mapRest.get("condicion4").toString());
				}
			}
		}
		session.setAttribute("mavConfiguracionRestriccionList", listRest);

		/* INI SA PER-SiCC-2013-0440 */
		GenericoService genericoService = (GenericoService) getBean("genericoService");
		ParametroPais paramPais = new ParametroPais();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		paramPais.setCodigoPais(pais.getCodigo());
		paramPais.setCodigoSistema(Constants.MAV_CODIGO_SISTEMA);
		paramPais
				.setCodigoParametro(Constants.MAV_CODIGO_PARAMETRO_VALIDA_PRECIO);
		paramPais.setIndicadorActivo(Constants.NUMERO_UNO);

		List lstParametros = genericoService.getParametrosPais(paramPais);

		if (lstParametros != null && lstParametros.size() > 0) {
			ParametroPais pPais = (ParametroPais) lstParametros.get(0);
			String indicadorValidaPrecio = pPais.getValorParametro();

			if (indicadorValidaPrecio != null)
				f.setIndicadorValidaPrecio(indicadorValidaPrecio);
			else
				f.setIndicadorValidaPrecio("");
		} else {
			f.setIndicadorValidaPrecio("");
		}
		/* FIN SA PER-SiCC-2013-0440 */

		f.setIndicadorTipoSeleccionCapacitadora(Constants.INDICADOR_TIPO_SELECCION_CAPACITADORA_REGION);
		ParametroPais parametroPais = new ParametroPais();
		parametroPais.setCodigoPais(pais.getCodigo());
		parametroPais.setCodigoSistema(Constants.MAV_CODIGO_SISTEMA);
		parametroPais.setCodigoParametro(Constants.ITEM_INDICADOR_CAPACITADORA);
		parametroPais.setIndicadorActivo(Constants.ESTADO_ACTIVO);
		lstParametros = genericoService.getParametrosPais(parametroPais);
		f.setIndicadorCapacitadora(Constants.NO);

		ParametroPais parametro = null;
		if (CollectionUtils.size(lstParametros) == 1) {
			parametro = (ParametroPais) lstParametros.get(0);
			String indicadorCapacitadora = parametro.getValorParametro();
			f.setIndicadorCapacitadora(indicadorCapacitadora);
		}
	}

	/**
	 * Metodo para mostrar Paneles
	 * 
	 * @param val
	 */
	public void mostrarPanel(ValueChangeEvent val) {
		try {
			String valor = (String) val.getNewValue();
			MantenimientoFACDeshabilitarZonasEnvioBoletasPremioForm f = (MantenimientoFACDeshabilitarZonasEnvioBoletasPremioForm) this.formBusqueda;
			if (valor.equals("R")) {
				this.mostrarPaneles = true;
			} else {
				f.setIndicadorCapacitadora("S");
				this.mostrarPaneles = false;
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}		
	}

	/**
	 * Actualizar lista de Tipos de Oferta
	 * 
	 * @param session
	 * @param f
	 */
	private void actualizarListTiposOferta(HttpSession session,
			MantenimientoFACDeshabilitarZonasEnvioBoletasPremioForm f) {
		/* obteniendo lista de tipos de oferta */
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		LabelValue[] listTiposOferta = ajaxService.getTiposOfertaByActividad(f
				.getOidActividad());
		session.setAttribute(Constants.MAV_TIPOS_OFERTA_LIST, listTiposOferta);

		String indicadorDirigido = "G";
		if (f.getCodigoTipoCliente().equals(
				Constants.MAE_TIPO_CLIENTE_CONSULTORA)) {
			indicadorDirigido = "C";
		}

		String indicadorUnidad = "S".equals(f.getIndicadorUnidad()) ? "S" : "";

		// CARGAR CONSIDERACIONES
		session.setAttribute(Constants.MAV_CONSIDERACION_LIST, ajaxService
				.getConsideraciones(indicadorDirigido, indicadorUnidad));
		// CARGAR RESTRICCONES
		session.setAttribute(Constants.MAV_RESTRICCION_LIST, ajaxService
				.getRestricciones(indicadorDirigido, indicadorUnidad));
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward regresar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'regresar' method");
		}
		HttpSession session = request.getSession(true);
		MantenimientoMAVConfiguracionForm f = (MantenimientoMAVConfiguracionForm) this.formMantenimiento;

		session.setAttribute("flagRegreso", Constants.NRO_UNO);
		return mapping.findForward(getSaveForward());
	}

	/*
	 * protected String getSaveForward() { return "view"; }
	 */

	/***** INICIO TAB CONSIDERACION *****/
	/**
	 * inserta el Zona de una poliza
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	// public ActionForward insertConsideracion(ActionMapping mapping,
	// ActionForm form, HttpServletRequest request,
	// HttpServletResponse response) throws Exception {
	// if (log.isDebugEnabled()) {
	// log.debug("Entering 'insertConsideracion' method ");
	// }
	// HttpSession session = request.getSession(true);
	// Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
	// MantenimientoFACDeshabilitarZonasEnvioBoletasPremioForm f =
	// (MantenimientoFACDeshabilitarZonasEnvioBoletasPremioForm)this.formBusqueda;
	// MantenimientoMAVConfiguracionService service =
	// (MantenimientoMAVConfiguracionService)
	// getBean("spusicc.mantenimientoMAVConfiguracionService");
	// f.setTabSeleccion(Constants.MAV_TAB_CONSIDERACION);
	// ActionMessages messages = new ActionMessages();
	//
	// session.setAttribute("idPopup", "");
	// List listComboConsideracion = service.getConsideracion(null);
	// List list = (List) session
	// .getAttribute("mavConfiguracionConsideracionList");
	// if (list == null)
	// list = new ArrayList();
	//
	// Map condicion = getCondicion(f.getCodigoConsideracion(),
	// listComboConsideracion);
	// String tipo = (String) condicion.get("indTipo");
	// String dirigido = (String) condicion.get("dirigido");
	//
	// Map bean = new HashMap();
	// bean.put("codigoPais", pais.getCodigo());
	// bean.put("correlativoMAV", f.getCorrelativo());
	// bean.put("codigoPeriodo", f.getCodigoPeriodo());
	// bean.put("codigoConsideracion", f.getCodigoConsideracion());
	// bean.put("indicadorTipo", tipo);
	// bean.put("dirigido", dirigido);
	// bean.put("descripcion", (String) condicion.get("descripcionConRes"));
	// bean.put(
	// "abrev",
	// getAbrevConRes(f.getCodigoConsideracion(),
	// listComboConsideracion));
	// bean.put("indicadorAccion", Constants.NUMERO_CERO);
	// bean.put("condicion", "");
	// bean.put("numReg", "0");
	//
	// if (StringUtils.isEmpty(tipo)
	// || Constants.MAV_TIPO_SIN_CONDICION.equals(tipo)) {
	// bean.put("numeroUnidades", f.getCondicionSimpleUnidades());
	// bean.put("numReg", "1");
	// }
	// if (Constants.MAV_TIPO_UNA_CONDICION.equals(tipo)) {
	// bean.put("condicion1", f.getCondicion1S());
	// bean.put("condicion", f.getCondicion1S());
	// bean.put("numeroUnidades", f.getCondicionUnicoUnidades());
	// bean.put("numReg", "1");
	// }
	// if (Constants.MAV_TIPO_UNA_CONDICION_DOBLE.equals(tipo)) {
	// bean.put("condicion", f.getCondicion1() + " , " + f.getCondicion2());
	// bean.put("condicion1", f.getCondicion1());
	// bean.put("condicion2", f.getCondicion2());
	// bean.put("numeroUnidades", f.getCondicionDobleUnidades());
	// bean.put("numReg", "1");
	// }
	// if (Constants.MAV_TIPO_CONDICIONES_MIXTAS.equals(tipo)) {
	// if (Constants.MAV_CONRES_INSCRIPCION_NUEVA_DUPLA == Integer
	// .parseInt(f.getCodigoConsideracion())) {
	// bean.put(
	// "condicion",
	// f.getCondicionPeriodoInicio() + " , "
	// + f.getCondicionPeriodoFin());
	// bean.put("condicion1", f.getCondicionPeriodoInicio());
	// bean.put("condicion2", f.getCondicionPeriodoFin());
	//
	// } else {
	// String condicionAux = null;
	// String descripcionAux = "";
	// if ((Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_MARCA == Integer
	// .parseInt(f.getCodigoConsideracion()))
	// || (Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_MARCA == Integer
	// .parseInt(f.getCodigoConsideracion()))) {
	// condicionAux = f.getCondicionMarca();
	// descripcionAux = getDesMarcaProducto(request, condicionAux);
	// }
	// if ((Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_NEGOCIO == Integer
	// .parseInt(f.getCodigoConsideracion()))
	// || (Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_NEGOCIO == Integer
	// .parseInt(f.getCodigoConsideracion()))) {
	// condicionAux = f.getCondicionNegocio();
	// descripcionAux = getDesNegocio(request, condicionAux);
	// }
	//
	// if ((Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_UNIDAD == Integer
	// .parseInt(f.getCodigoConsideracion()))
	// || (Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_UNIDAD == Integer
	// .parseInt(f.getCodigoConsideracion()))) {
	// condicionAux = f.getCondicionUnidadNegocio();
	// descripcionAux = getDesUnidadNegocio(request, condicionAux);
	// }
	//
	// if ((Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_CATALOGO == Integer
	// .parseInt(f.getCodigoConsideracion()))
	// || (Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_CATALOGO == Integer
	// .parseInt(f.getCodigoConsideracion()))) {
	// condicionAux = f.getCondicionCatalogo();
	// descripcionAux = getDesCatalogo(request, condicionAux);
	// }
	//
	// if ((Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO == Integer
	// .parseInt(f.getCodigoConsideracion()))
	// || (Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO == Integer
	// .parseInt(f.getCodigoConsideracion()))) {
	// bean.put(
	// "condicion",
	// f.getCondicionMonto() + " , "
	// + f.getCondicionPeriodoInicio() + " , "
	// + f.getCondicionPeriodoFin());
	//
	// bean.put("condicion1", f.getCondicionMonto());
	// bean.put("condicion2", f.getCondicionPeriodoInicio());
	// bean.put("condicion3", f.getCondicionPeriodoFin());
	// } else {
	// bean.put("condicion",
	// f.getCondicionMonto() + " , " + descripcionAux
	// + " , " + f.getCondicionPeriodoInicio()
	// + " , " + f.getCondicionPeriodoFin());
	//
	// bean.put("condicion1", f.getCondicionMonto());
	// bean.put("condicion2", condicionAux);
	// bean.put("condicion3", f.getCondicionPeriodoInicio());
	// bean.put("condicion4", f.getCondicionPeriodoFin());
	// }
	// }
	//
	// bean.put("numeroUnidades", f.getCondicionUnidades());
	// bean.put("numReg", "1");
	// }
	//
	// if (Constants.MAV_TIPO_LISTA_CONDICION.equals(tipo)
	// || Constants.MAV_TIPO_LISTA_EXTERNA_CONDICION.equals(tipo)) {
	//
	// if (Constants.MAV_CONRES_LISTA_CONSU == Integer.parseInt(f
	// .getCodigoConsideracion())) {
	// List consultoraList = (List) session
	// .getAttribute("consultoraList");
	// log.debug("consultoraList >>>>>>> " + consultoraList.size());
	// bean.put("consultoraList", consultoraList);
	// bean.put("numReg", "" + consultoraList.size());
	// bean.put("condicion", "" + consultoraList.size());
	// bean.put("condicion1", "" + consultoraList.size());
	// bean.put("tipoConsideracionLista",
	// Constants.MAV_TIPO_LISTA_CONDICION);
	// }
	//
	// if (Constants.MAV_CONRES_LISTA_REGION_ZONA == Integer.parseInt(f
	// .getCodigoConsideracion())) {
	// List regionesList = (List) session.getAttribute("regionesList");
	// log.debug("regionesList >>>>>>> " + regionesList.size());
	// bean.put("regionesList", regionesList);
	// bean.put("numReg", "" + regionesList.size());
	// bean.put("condicion", "" + regionesList.size());
	// bean.put("condicion1", "" + regionesList.size());
	// bean.put("tipoConsideracionLista",
	// Constants.MAV_TIPO_LISTA_CONDICION);
	// }
	//
	// if (Constants.MAV_CONRES_CLASIFICACIONES_CLIENTE == Integer
	// .parseInt(f.getCodigoConsideracion())) {
	// List listC = (List) session.getAttribute("clasificacionesList");
	// log.debug("listClasificaciones >>>>> " + listC.size());
	// bean.put("listClasificaciones", listC);
	// // actualizar los eliminados
	// Iterator it = listC.iterator();
	// int cont = listC.size();
	// while (it.hasNext()) {
	// Map aux = (Map) it.next();
	// String indicadorAccion = (String) aux
	// .get("indicadorAccion");
	// if (Constants.NUMERO_DOS.equals(indicadorAccion)) {
	// cont--;
	// }
	// }
	// bean.put("numReg", "" + cont);
	// bean.put("condicion", "" + cont);
	// bean.put("condicion1", "" + cont);
	// bean.put("tipoConsideracionLista",
	// Constants.MAV_TIPO_LISTA_CONDICION);
	// if (cont == 0)
	// bean.put("indicadorAccion", Constants.NUMERO_DOS);
	// }
	//
	// if (Constants.MAV_CONRES_UNIDAD_ADM == Integer.parseInt(f
	// .getCodigoConsideracion())) {
	// List listU = (List) session.getAttribute("unidadesList");
	// log.debug("unidadesList >>>>>>> " + listU.size());
	// bean.put("listUnidades", listU);
	// // actualizar los eliminados
	// Iterator it = listU.iterator();
	// int cont = listU.size();
	// while (it.hasNext()) {
	// Map aux = (Map) it.next();
	// String indicadorAccion = (String) aux
	// .get("indicadorAccion");
	// if (Constants.NUMERO_DOS.equals(indicadorAccion)) {
	// cont--;
	// }
	// }
	// bean.put("numReg", "" + cont);
	// bean.put("condicion", "" + cont);
	// bean.put("condicion1", "" + cont);
	// bean.put("tipoConsideracionLista",
	// Constants.MAV_TIPO_LISTA_CONDICION);
	// if (cont == 0)
	// bean.put("indicadorAccion", Constants.NUMERO_DOS);
	// }
	//
	// if (Constants.MAV_CONRES_ESTATUS_CLIENTE == Integer.parseInt(f
	// .getCodigoConsideracion())) {
	// List lista = (List) session.getAttribute("codigoEstatusList");
	// log.debug("listEstatus  " + lista.size());
	// bean.put("listEstatus", lista);
	// // actualizar los eliminados
	// Iterator it = lista.iterator();
	// int cont = lista.size();
	// while (it.hasNext()) {
	// Map aux = (Map) it.next();
	// String indicadorAccion = (String) aux
	// .get("indicadorAccion");
	// if (Constants.NUMERO_DOS.equals(indicadorAccion)) {
	// cont--;
	// }
	// }
	// bean.put("numReg", "" + cont);
	// bean.put("condicion", "" + cont);
	// bean.put("condicion1", "" + cont);
	// bean.put("tipoConsideracionLista",
	// Constants.MAV_TIPO_LISTA_CONDICION);
	// if (cont == 0)
	// bean.put("indicadorAccion", Constants.NUMERO_DOS);
	// }
	//
	// }
	// List listR = (List) session
	// .getAttribute("mavConfiguracionRestriccionList");
	// bean.put("codigoRestriccion", f.getCodigoConsideracion());
	// if (isValido(bean, list) && isValidoRest(bean, listR)) {// es registro
	// // valido cuando
	// // no se
	// // encuntra en
	// // la lista o se
	// // encuentra
	// // como
	// // eliminado
	// bean.put("codigoRestriccion", null);
	// list.add(bean);
	// // service.saveTemporalZona(bean);
	// } else {
	// String mensaje = null;
	// mensaje =
	// this.getResourceMessage("mantenimientoMAVConfiguracionForm.existe.consideracion.registro");
	// this.addError("Error : ", mensaje);
	// f.setSelectedItemsConsideracion(null);
	// f.setSelectedItemsRestriccion(null);
	// return mapping.findForward(getManteForward());
	// }
	//
	// actualizarListTiposOferta(session, f);
	// limpiarCamposConsideracion(f);
	//
	// f.setCodigoConsideracion("");
	//
	// f.setSelectedItemsConsideracion(null);
	// f.setSelectedItemsRestriccion(null);
	// session.setAttribute("mavConfiguracionConsideracionList", list);
	//
	// return mapping.findForward(getManteForward());
	// }

	/**
	 * inserta el Zona de una poliza
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	// public ActionForward updateConsideracion(ActionMapping mapping,
	// ActionForm form, HttpServletRequest request,
	// HttpServletResponse response) throws Exception {
	// if (log.isDebugEnabled()) {
	// log.debug("Entering 'updateConsideracion' method");
	// }
	// HttpSession session = request.getSession(true);
	// MantenimientoFACDeshabilitarZonasEnvioBoletasPremioForm f =
	// (MantenimientoFACDeshabilitarZonasEnvioBoletasPremioForm)
	// this.formBusqueda;
	// f.setTabSeleccion(Constants.MAV_TAB_CONSIDERACION);
	//
	// List list = (List) session
	// .getAttribute("mavConfiguracionConsideracionList");
	// if (list == null)
	// list = new ArrayList();
	//
	// String idPopup = (String) session.getAttribute("idPopup");
	// int index = Integer.parseInt(idPopup);
	// Map bean = (Map) list.get(index);
	// String codigoConRes = (String) bean.get("codigoConsideracion");
	// String tipo = (String) bean.get("indicadorTipo");
	//
	// bean.put("codigoPeriodo", f.getCodigoPeriodo());
	// // bean.put("indicadorAccion",Constants.NUMERO_UNO);
	// bean.put("condicion", "");
	// bean.put("numReg", "0");
	//
	// if (Constants.MAV_TIPO_LISTA_CONDICION.equals(tipo)
	// || Constants.MAV_TIPO_LISTA_EXTERNA_CONDICION.equals(tipo)) {
	// log.debug(" codigoConRes >>>  " + codigoConRes);
	//
	// if (Constants.MAV_CONRES_LISTA_CONSU == Integer
	// .parseInt(codigoConRes)) {
	// List consultoraList = (List) session
	// .getAttribute("consultoraList");
	// log.debug("consultoraList >>>>>>> " + consultoraList.size());
	// bean.put("consultoraList", consultoraList);
	// bean.put("numReg", "" + consultoraList.size());
	// bean.put("condicion", "" + consultoraList.size());
	// bean.put("condicion1", "" + consultoraList.size());
	// bean.put("tipoConsideracionLista",
	// Constants.MAV_TIPO_LISTA_CONDICION);
	// bean.put("indicadorModificadoPopup", Constants.NRO_UNO);
	// }
	//
	// if (Constants.MAV_CONRES_LISTA_REGION_ZONA == Integer
	// .parseInt(codigoConRes)) {
	// List regionesList = (List) session.getAttribute("regionesList");
	// log.debug("regionesList >>>>>>> " + regionesList.size());
	// bean.put("regionesList", regionesList);
	// bean.put("numReg", "" + regionesList.size());
	// bean.put("condicion", "" + regionesList.size());
	// bean.put("condicion1", "" + regionesList.size());
	// bean.put("tipoConsideracionLista",
	// Constants.MAV_TIPO_LISTA_CONDICION);
	// bean.put("indicadorModificadoPopup", Constants.NRO_UNO);
	// }
	//
	// if (Constants.MAV_CONRES_CLASIFICACIONES_CLIENTE == Integer
	// .parseInt(codigoConRes)) {
	// List listC = (List) session.getAttribute("clasificacionesList");
	// log.debug("listClasificaciones >>>>>>> " + listC.size());
	// bean.put("listClasificaciones", listC);
	// // actualizar los eliminados
	// Iterator it = listC.iterator();
	// int cont = listC.size();
	// while (it.hasNext()) {
	// Map aux = (Map) it.next();
	// String indicadorAccion = (String) aux
	// .get("indicadorAccion");
	// if (Constants.NUMERO_DOS.equals(indicadorAccion)) {
	// aux.put("indicadorAccion", Constants.NUMERO_DOS);
	// cont--;
	// }
	// }
	// bean.put("numReg", "" + cont);
	// bean.put("condicion", "" + cont);
	// bean.put("condicion1", "" + cont);
	// bean.put("indicadorModificadoPopup", Constants.NRO_UNO);
	// if (cont == 0)
	// bean.put("indicadorAccion", Constants.NUMERO_DOS);
	// }
	//
	// if (Constants.MAV_CONRES_UNIDAD_ADM == Integer
	// .parseInt(codigoConRes)) {
	// List listU = (List) session.getAttribute("unidadesList");
	// log.debug("unidadesList >>>>>>> " + listU.size());
	// bean.put("listUnidades", listU);
	// // actualizar los eliminados
	// Iterator it = listU.iterator();
	// int cont = listU.size();
	// while (it.hasNext()) {
	// Map aux = (Map) it.next();
	// String indicadorAccion = (String) aux
	// .get("indicadorAccion");
	// if (Constants.NUMERO_DOS.equals(indicadorAccion)) {
	// aux.put("indicadorAccion", Constants.NUMERO_DOS);
	// cont--;
	// }
	// }
	// bean.put("numReg", "" + cont);
	// bean.put("condicion", "" + cont);
	// bean.put("condicion1", "" + cont);
	// bean.put("indicadorModificadoPopup", Constants.NRO_UNO);
	// if (cont == 0)
	// bean.put("indicadorAccion", Constants.NUMERO_DOS);
	// }
	//
	// if (Constants.MAV_CONRES_ESTATUS_CLIENTE == Integer
	// .parseInt(codigoConRes)) {
	// List lista = (List) session.getAttribute("codigoEstatusList");
	// log.debug("listEstatus  " + lista.size());
	// bean.put("listEstatus", lista);
	// // actualizar los eliminados
	// Iterator it = lista.iterator();
	// int cont = lista.size();
	// while (it.hasNext()) {
	// Map aux = (Map) it.next();
	// String indicadorAccion = (String) aux
	// .get("indicadorAccion");
	// if (Constants.NUMERO_DOS.equals(indicadorAccion)) {
	// cont--;
	// }
	// }
	// bean.put("numReg", "" + cont);
	// bean.put("condicion", "" + cont);
	// bean.put("condicion1", "" + cont);
	// bean.put("indicadorModificadoPopup", Constants.NRO_UNO);
	// if (cont == 0)
	// bean.put("indicadorAccion", Constants.NUMERO_DOS);
	// }
	// }
	//
	// actualizarListTiposOferta(session, f);
	// limpiarCamposConsideracion(f);
	//
	// f.setSelectedItemsConsideracion(null);
	// f.setSelectedItemsRestriccion(null);
	// log.debug("mavConfiguracionConsideracionList size " + list.size());
	// session.setAttribute("mavConfiguracionConsideracionList", list);
	// return mapping.findForward(getManteForward());
	// }

	/**
	 * Retorna la condicon seleecioan pueder consideracion orestricion
	 * 
	 * @param codigo
	 * @param listCombo
	 * @return
	 */
	private Map getCondicion(String codigo, List listCombo) {
		Iterator it = listCombo.iterator();
		while (it.hasNext()) {
			Map map = (Map) it.next();
			String aux = String.valueOf(map.get("codigoConRes"));
			if (codigo.equals(aux))
				return map;

		}
		return null;
	}

	/**
	 * El registro es valido si no se encunetra en la lista o ya se encuntra
	 * eliminado
	 * 
	 * @param map
	 * @param list
	 * @return
	 */
	private boolean isValido(Map map, List list) {
		Iterator it = list.iterator();
		// String codigoConsideracion = (String)map.get("codigoConsideracion");
		String abrev = (String) map.get("abrev");
		while (it.hasNext()) {
			Map mapAux = (Map) it.next();
			// String codigoConsideracionAux =
			// (String)mapAux.get("codigoConsideracion");
			String abrevAux = (String) mapAux.get("abrev");
			String indicadorAccionAux = (String) mapAux.get("indicadorAccion");
			if (abrev.equals(abrevAux)
					&& (indicadorAccionAux.equals(Constants.NUMERO_CERO) || indicadorAccionAux
							.equals(Constants.NUMERO_UNO)))
				return false;
		}
		return true;
	}

	/**
	 * Elimina una consideracion
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	// public ActionForward deleteConsideracion(ActionMapping mapping,
	// ActionForm form, HttpServletRequest request,
	// HttpServletResponse response) throws Exception {
	// if (log.isDebugEnabled()) {
	// log.debug("Entering 'deleteConsideracion' method");
	// }
	// HttpSession session = request.getSession(true);
	// ActionMessages messages = new ActionMessages();
	// String id = request.getParameter("id");
	// MantenimientoFACDeshabilitarZonasEnvioBoletasPremioForm f =
	// (MantenimientoFACDeshabilitarZonasEnvioBoletasPremioForm)
	// this.formBusqueda;
	// f.setTabSeleccion(Constants.MAV_TAB_CONSIDERACION);
	// log.debug("row id " + id);
	//
	// if (id != null) {
	// try {
	// List list = (List) session
	// .getAttribute("mavConfiguracionConsideracionList");
	// Map bean = (Map) list.get(Integer.parseInt(id) - 1);
	// bean.put("indicadorAccion", Constants.NUMERO_DOS);
	// } catch (Exception e) {
	// e.printStackTrace();
	// String error = e.getMessage();
	// if (StringUtils.isBlank(error))
	// error = e.getLocalizedMessage();
	// messages.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(
	// "errors.detail", error));
	// saveErrors(request, messages);
	// }
	// }
	//
	// actualizarListTiposOferta(session, f);
	// limpiarCamposConsideracion(f);
	//
	// f.setCodigoConsideracion("");
	//
	// return mapping.findForward(getManteForward());
	//
	// }

	/**
	 * Elimina logicamente el Zona
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	// public ActionForward editConsideracion(ActionMapping mapping,
	// ActionForm form, HttpServletRequest request,
	// HttpServletResponse response) throws Exception {
	// if (log.isDebugEnabled()) {
	// log.debug("Entering 'editConsideracion' method");
	// }
	// HttpSession session = request.getSession(true);
	// ActionMessages messages = new ActionMessages();
	// String id = request.getParameter("id");
	// MantenimientoFACDeshabilitarZonasEnvioBoletasPremioForm f =
	// (MantenimientoFACDeshabilitarZonasEnvioBoletasPremioForm)
	// this.formBusqueda;
	// f.setTabSeleccion(Constants.MAV_TAB_CONSIDERACION);
	// log.debug("row id " + id);
	//
	// if (id != null) {
	// limpiarCamposConsideracion(f);
	//
	// try {
	// List list = (List) session
	// .getAttribute("mavConfiguracionConsideracionList");
	// Map bean = (Map) list.get(Integer.parseInt(id) - 1);
	// String tipo = (String) bean.get("indicadorTipo");
	// log.debug("Tipo " + tipo);
	// f.setCodigoConsideracion((String) bean
	// .get("codigoConsideracion"));
	//
	// if (StringUtils.isEmpty(tipo)
	// || Constants.MAV_TIPO_SIN_CONDICION.equals(tipo)) {
	// f.setCondicionSimpleUnidades((String) bean
	// .get("numeroUnidades"));
	// }
	// if (Constants.MAV_TIPO_UNA_CONDICION.equals(tipo)) {
	// f.setCondicion1S((String) bean.get("condicion1"));
	// f.setCondicionUnicoUnidades((String) bean
	// .get("numeroUnidades"));
	// }
	// if (Constants.MAV_TIPO_UNA_CONDICION_DOBLE.equals(tipo)) {
	// f.setCondicion1((String) bean.get("condicion1"));
	// f.setCondicion2((String) bean.get("condicion2"));
	// f.setCondicionDobleUnidades((String) bean
	// .get("numeroUnidades"));
	// }
	// if (Constants.MAV_TIPO_CONDICIONES_MIXTAS.equals(tipo)) {
	// if (Constants.MAV_CONRES_INSCRIPCION_NUEVA_DUPLA == Integer
	// .parseInt(f.getCodigoConsideracion())) {
	// f.setCondicionPeriodoInicio((String) bean
	// .get("condicion1"));
	// f.setCondicionPeriodoFin((String) bean
	// .get("condicion2"));
	//
	// } else {
	// f.setCondicionMonto((String) bean.get("condicion1"));
	//
	// String condicionAux = null;
	// if ((Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_MARCA == Integer
	// .parseInt(f.getCodigoConsideracion()))
	// || (Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_MARCA == Integer
	// .parseInt(f.getCodigoConsideracion()))) {
	// f.setCondicionMarca((String) bean.get("condicion2"));
	// }
	// if ((Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_NEGOCIO == Integer
	// .parseInt(f.getCodigoConsideracion()))
	// || (Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_NEGOCIO == Integer
	// .parseInt(f.getCodigoConsideracion()))) {
	// f.setCondicionNegocio((String) bean
	// .get("condicion2"));
	// }
	//
	// if ((Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_UNIDAD == Integer
	// .parseInt(f.getCodigoConsideracion()))
	// || (Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_UNIDAD == Integer
	// .parseInt(f.getCodigoConsideracion()))) {
	// f.setCondicionUnidadNegocio((String) bean
	// .get("condicion2"));
	// }
	//
	// if ((Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_CATALOGO == Integer
	// .parseInt(f.getCodigoConsideracion()))
	// || (Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_CATALOGO == Integer
	// .parseInt(f.getCodigoConsideracion()))) {
	// f.setCondicionCatalogo((String) bean
	// .get("condicion2"));
	// }
	//
	// if ((Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO == Integer
	// .parseInt(f.getCodigoConsideracion()))
	// || (Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO == Integer
	// .parseInt(f.getCodigoConsideracion()))) {
	//
	// f.setCondicionPeriodoInicio((String) bean
	// .get("condicion2"));
	// f.setCondicionPeriodoFin((String) bean
	// .get("condicion3"));
	// } else {
	// f.setCondicionPeriodoInicio((String) bean
	// .get("condicion3"));
	// f.setCondicionPeriodoFin((String) bean
	// .get("condicion4"));
	// }
	// }
	//
	// f.setCondicionUnidades((String) bean.get("numeroUnidades"));
	// }
	//
	// f.setIndicadorConsideracion(Constants.NUMERO_CERO);
	// f.setSelectedItemsConsideracion(null);
	// f.setSelectedItemsRestriccion(null);
	// session.setAttribute("idConsideracion", id);
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// String error = e.getMessage();
	// if (StringUtils.isBlank(error))
	// error = e.getLocalizedMessage();
	// messages.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(
	// "errors.detail", error));
	// saveErrors(request, messages);
	// }
	// }
	//
	// actualizarListTiposOferta(session, f);
	//
	// return mapping.findForward(getManteForward());
	//
	// }

	/**
	 * Actualiza una Consideracion
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	// public ActionForward saveConsideracion(ActionMapping mapping,
	// ActionForm form, HttpServletRequest request,
	// HttpServletResponse response) throws Exception {
	// if (log.isDebugEnabled()) {
	// log.debug("Entering 'saveConsideracion()' method");
	// }
	// HttpSession session = request.getSession(true);
	// // ActionMessages messages = new ActionMessages();
	// MantenimientoFACDeshabilitarZonasEnvioBoletasPremioForm f =
	// (MantenimientoFACDeshabilitarZonasEnvioBoletasPremioForm)
	// this.formBusqueda;
	// // Map criteria = BeanUtils.describe(f);
	//
	// // ActionMessages messages = new ActionMessages();
	// List list = (List) session
	// .getAttribute("mavConfiguracionConsideracionList");
	// String id = (String) session.getAttribute("idConsideracion");
	// Map bean = (Map) list.get(Integer.parseInt(id) - 1);
	//
	// String tipo = (String) bean.get("indicadorTipo");
	// log.debug("Tipo " + tipo);
	//
	// if (StringUtils.isEmpty(tipo)
	// || Constants.MAV_TIPO_SIN_CONDICION.equals(tipo)) {
	// bean.put("numeroUnidades", f.getCondicionSimpleUnidades());
	// bean.put("indicadorModificado", Constants.ESTADO_ACTIVO);
	// }
	// if (Constants.MAV_TIPO_UNA_CONDICION.equals(tipo)) {
	// bean.put("condicion1", f.getCondicion1S());
	// bean.put("condicion", f.getCondicion1S());
	// bean.put("numeroUnidades", f.getCondicionUnicoUnidades());
	// bean.put("indicadorModificado", Constants.ESTADO_ACTIVO);
	// }
	// if (Constants.MAV_TIPO_UNA_CONDICION_DOBLE.equals(tipo)) {
	// bean.put("condicion1", f.getCondicion1());
	// bean.put("condicion2", f.getCondicion2());
	// bean.put("condicion", f.getCondicion1() + " , " + f.getCondicion2());
	// bean.put("numeroUnidades", f.getCondicionDobleUnidades());
	// bean.put("indicadorModificado", Constants.ESTADO_ACTIVO);
	// }
	// if (Constants.MAV_TIPO_CONDICIONES_MIXTAS.equals(tipo)) {
	// if (Constants.MAV_CONRES_INSCRIPCION_NUEVA_DUPLA == Integer
	// .parseInt(f.getCodigoConsideracion())) {
	// bean.put(
	// "condicion",
	// f.getCondicionPeriodoInicio() + " , "
	// + f.getCondicionPeriodoFin());
	// bean.put("condicion1", f.getCondicionPeriodoInicio());
	// bean.put("condicion2", f.getCondicionPeriodoFin());
	//
	// } else {
	// String condicionAux = null;
	// String descripcionAux = "";
	// if ((Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_MARCA == Integer
	// .parseInt(f.getCodigoConsideracion()))
	// || (Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_MARCA == Integer
	// .parseInt(f.getCodigoConsideracion()))) {
	// condicionAux = f.getCondicionMarca();
	// descripcionAux = getDesMarcaProducto(request, condicionAux);
	// }
	// if ((Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_NEGOCIO == Integer
	// .parseInt(f.getCodigoConsideracion()))
	// || (Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_NEGOCIO == Integer
	// .parseInt(f.getCodigoConsideracion()))) {
	// condicionAux = f.getCondicionNegocio();
	// descripcionAux = getDesNegocio(request, condicionAux);
	// }
	//
	// if ((Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_UNIDAD == Integer
	// .parseInt(f.getCodigoConsideracion()))
	// || (Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_UNIDAD == Integer
	// .parseInt(f.getCodigoConsideracion()))) {
	// condicionAux = f.getCondicionUnidadNegocio();
	// descripcionAux = getDesUnidadNegocio(request, condicionAux);
	// }
	//
	// if ((Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_CATALOGO == Integer
	// .parseInt(f.getCodigoConsideracion()))
	// || (Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_CATALOGO == Integer
	// .parseInt(f.getCodigoConsideracion()))) {
	// condicionAux = f.getCondicionCatalogo();
	// descripcionAux = getDesCatalogo(request, condicionAux);
	// }
	//
	// if ((Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO == Integer
	// .parseInt(f.getCodigoConsideracion()))
	// || (Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO == Integer
	// .parseInt(f.getCodigoConsideracion()))) {
	// bean.put(
	// "condicion",
	// f.getCondicionMonto() + " , "
	// + f.getCondicionPeriodoInicio() + " , "
	// + f.getCondicionPeriodoFin());
	//
	// bean.put("condicion1", f.getCondicionMonto());
	// bean.put("condicion2", f.getCondicionPeriodoInicio());
	// bean.put("condicion3", f.getCondicionPeriodoFin());
	// } else {
	// bean.put("condicion",
	// f.getCondicionMonto() + " , " + descripcionAux
	// + " , " + f.getCondicionPeriodoInicio()
	// + " , " + f.getCondicionPeriodoFin());
	//
	// bean.put("condicion1", f.getCondicionMonto());
	// bean.put("condicion2", condicionAux);
	// bean.put("condicion3", f.getCondicionPeriodoInicio());
	// bean.put("condicion4", f.getCondicionPeriodoFin());
	// }
	//
	// }
	//
	// bean.put("numeroUnidades", f.getCondicionUnidades());
	// bean.put("indicadorModificado", Constants.ESTADO_ACTIVO);
	// }
	// f.setIndicadorConsideracion(Constants.NUMERO_UNO);
	//
	// actualizarListTiposOferta(session, f);
	// limpiarCamposConsideracion(f);
	//
	// f.setCodigoConsideracion("");
	//
	// return mapping.findForward(getManteForward());
	// }

	/*** FIN TAB CONSIDERACION **********/

	/***** INICIO TAB RESTRICCION *****/
	/**
	 * inserta el Zona de una poliza
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	// public ActionForward insertRestriccion(ActionMapping mapping,
	// ActionForm form, HttpServletRequest request,
	// HttpServletResponse response) throws Exception {
	// if (log.isDebugEnabled()) {
	// log.debug("Entering 'insertRestriccion' method dd");
	// }
	// HttpSession session = request.getSession(true);
	// Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
	// MantenimientoFACDeshabilitarZonasEnvioBoletasPremioForm f =
	// (MantenimientoFACDeshabilitarZonasEnvioBoletasPremioForm)
	// this.formBusqueda;
	// MantenimientoMAVConfiguracionService service =
	// (MantenimientoMAVConfiguracionService)
	// getBean("spusicc.mantenimientoMAVConfiguracionService");
	// f.setTabSeleccion(Constants.MAV_TAB_RESTRICCION);
	// ActionMessages messages = new ActionMessages();
	// String mensaje = null;
	//
	// session.setAttribute("idPopup", "");
	// List listComboConsideracion = service.getRestriccion(null);
	// List list = (List) session
	// .getAttribute("mavConfiguracionRestriccionList");
	// if (list == null)
	// list = new ArrayList();
	//
	// Map condicion = getCondicion(f.getCodigoRestriccion(),
	// listComboConsideracion);
	// String tipo = (String) condicion.get("indTipo");
	//
	// Map bean = new HashMap();
	// bean.put("codigoPais", pais.getCodigo());
	// bean.put("correlativoMAV", f.getCorrelativo());
	// bean.put("codigoPeriodo", f.getCodigoPeriodo());
	// bean.put("codigoRestriccion", f.getCodigoRestriccion());
	// bean.put("indicadorTipo", tipo);
	// bean.put("descripcion", (String) condicion.get("descripcionConRes"));
	// bean.put(
	// "abrev",
	// getAbrevConRes(f.getCodigoRestriccion(), listComboConsideracion));
	// bean.put("indicadorAccion", Constants.NUMERO_CERO);
	// bean.put("condicion", "");
	// bean.put("numReg", "0");
	//
	// if (StringUtils.isEmpty(tipo)
	// || Constants.MAV_TIPO_SIN_CONDICION.equals(tipo)) {
	// bean.put("numeroUnidades", f.getCondicionSimpleRestUnidades());
	// bean.put("numReg", "1");
	// }
	// if (Constants.MAV_TIPO_UNA_CONDICION.equals(tipo)) {
	// bean.put("condicion1", f.getCondicionRest1S());
	// bean.put("condicion", f.getCondicionRest1S());
	// bean.put("numeroUnidades", f.getCondicionUnicoRestUnidades());
	// bean.put("numReg", "1");
	// }
	// if (Constants.MAV_TIPO_UNA_CONDICION_DOBLE.equals(tipo)) {
	// bean.put("condicion",
	// f.getCondicionRest1() + " , " + f.getCondicionRest2());
	// bean.put("condicion1", f.getCondicionRest1());
	// bean.put("condicion2", f.getCondicionRest2());
	// bean.put("numeroUnidades", f.getCondicionDobleRestUnidades());
	// bean.put("numReg", "1");
	// }
	// if (Constants.MAV_TIPO_CONDICIONES_MIXTAS.equals(tipo)) {
	// if (Constants.MAV_CONRES_INSCRIPCION_NUEVA_DUPLA_REST == Integer
	// .parseInt(f.getCodigoRestriccion())) {
	// bean.put("condicion", f.getCondicionRestPeriodoInicio() + " , "
	// + f.getCondicionRestPeriodoFin());
	// bean.put("condicion1", f.getCondicionRestPeriodoInicio());
	// bean.put("condicion2", f.getCondicionRestPeriodoFin());
	//
	// } else {
	// String condicionAux = null;
	// String descripcionAux = "";
	// if ((Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_MARCA_REST == Integer
	// .parseInt(f.getCodigoRestriccion()))
	// || (Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_MARCA_REST == Integer
	// .parseInt(f.getCodigoRestriccion()))) {
	// condicionAux = f.getCondicionRestMarca();
	// descripcionAux = getDesMarcaProducto(request, condicionAux);
	// }
	// if ((Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_NEGOCIO_REST == Integer
	// .parseInt(f.getCodigoRestriccion()))
	// || (Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_NEGOCIO_REST == Integer
	// .parseInt(f.getCodigoRestriccion()))) {
	// condicionAux = f.getCondicionRestNegocio();
	// descripcionAux = getDesNegocio(request, condicionAux);
	// }
	//
	// if ((Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_UNIDAD_REST == Integer
	// .parseInt(f.getCodigoRestriccion()))
	// || (Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_UNIDAD_REST == Integer
	// .parseInt(f.getCodigoRestriccion()))) {
	// condicionAux = f.getCondicionRestUnidadNegocio();
	// descripcionAux = getDesUnidadNegocio(request, condicionAux);
	// }
	//
	// if ((Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_CATALOGO_REST == Integer
	// .parseInt(f.getCodigoRestriccion()))
	// || (Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_CATALOGO_REST == Integer
	// .parseInt(f.getCodigoRestriccion()))) {
	// condicionAux = f.getCondicionRestCatalogo();
	// descripcionAux = getDesCatalogo(request, condicionAux);
	// }
	//
	// if ((Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_REST == Integer
	// .parseInt(f.getCodigoRestriccion()))
	// || (Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_REST == Integer
	// .parseInt(f.getCodigoRestriccion()))) {
	// bean.put(
	// "condicion",
	// f.getCondicionRestMonto() + " , "
	// + f.getCondicionRestPeriodoInicio() + " , "
	// + f.getCondicionRestPeriodoFin());
	//
	// bean.put("condicion1", f.getCondicionRestMonto());
	// bean.put("condicion2", f.getCondicionRestPeriodoInicio());
	// bean.put("condicion3", f.getCondicionRestPeriodoFin());
	// } else {
	// bean.put("condicion",
	// f.getCondicionRestMonto() + " , " + descripcionAux
	// + " , " + f.getCondicionRestPeriodoInicio()
	// + " , " + f.getCondicionRestPeriodoFin());
	//
	// bean.put("condicion1", f.getCondicionRestMonto());
	// bean.put("condicion2", condicionAux);
	// bean.put("condicion3", f.getCondicionRestPeriodoInicio());
	// bean.put("condicion4", f.getCondicionRestPeriodoFin());
	// }
	// }
	//
	// bean.put("numeroUnidades", f.getCondicionRestUnidades());
	// bean.put("numReg", "1");
	// }
	// if (Constants.MAV_TIPO_LISTA_CONDICION.equals(tipo)
	// || Constants.MAV_TIPO_LISTA_EXTERNA_CONDICION.equals(tipo)) {
	// log.debug(" f.getCodigoRestriccion() >>>  "
	// + f.getCodigoRestriccion());
	//
	// if (Constants.MAV_CONRES_LISTA_CONSU_REST == Integer.parseInt(f
	// .getCodigoRestriccion())) {
	// List consultoraList = (List) session
	// .getAttribute("consultoraListR");
	// log.debug("consultoraListR >>>>>>> " + consultoraList.size());
	// bean.put("consultoraListR", consultoraList);
	// bean.put("numReg", "" + consultoraList.size());
	// bean.put("condicion", "" + consultoraList.size());
	// bean.put("condicion1", "" + consultoraList.size());
	// bean.put("tipoConsideracionLista",
	// Constants.MAV_TIPO_LISTA_CONDICION);
	// }
	//
	// if (Constants.MAV_CONRES_LISTA_REGION_ZONA_REST == Integer
	// .parseInt(f.getCodigoRestriccion())) {
	// List regionesList = (List) session
	// .getAttribute("regionesListR");
	// log.debug("regionesListR >>>>>>> " + regionesList.size());
	// bean.put("regionesListR", regionesList);
	// bean.put("numReg", "" + regionesList.size());
	// bean.put("condicion", "" + regionesList.size());
	// bean.put("condicion1", "" + regionesList.size());
	// bean.put("tipoConsideracionLista",
	// Constants.MAV_TIPO_LISTA_CONDICION);
	// }
	//
	// if (Constants.MAV_CONRES_CLASIFICACIONES_CLIENTE_REST == Integer
	// .parseInt(f.getCodigoRestriccion())) {
	// List listC = (List) session
	// .getAttribute("clasificacionesListR");
	// log.debug("listClasificaciones >>>>>>> " + listC.size());
	// bean.put("listClasificacionesR", listC);
	// // actualizar los eliminados
	// Iterator it = listC.iterator();
	// int cont = listC.size();
	// while (it.hasNext()) {
	// Map aux = (Map) it.next();
	// String indicadorAccion = (String) aux
	// .get("indicadorAccion");
	// if (Constants.NUMERO_DOS.equals(indicadorAccion)) {
	// cont--;
	// }
	// }
	// bean.put("numReg", "" + cont);
	// bean.put("condicion", "" + cont);
	// bean.put("condicion1", "" + cont);
	// bean.put("tipoConsideracionLista",
	// Constants.MAV_TIPO_LISTA_CONDICION);
	// if (cont == 0)
	// bean.put("indicadorAccion", Constants.NUMERO_DOS);
	// }
	//
	// if (Constants.MAV_CONRES_UNIDAD_ADM_REST == Integer.parseInt(f
	// .getCodigoRestriccion())) {
	// List listU = (List) session.getAttribute("unidadesListR");
	// log.debug("unidadesListR >>>>>>> " + listU.size());
	// bean.put("listUnidadesR", listU);
	// // actualizar los eliminados
	// Iterator it = listU.iterator();
	// int cont = listU.size();
	// while (it.hasNext()) {
	// Map aux = (Map) it.next();
	// String indicadorAccion = (String) aux
	// .get("indicadorAccion");
	// if (Constants.NUMERO_DOS.equals(indicadorAccion)) {
	// cont--;
	// }
	// }
	// bean.put("numReg", "" + cont);
	// bean.put("condicion", "" + cont);
	// bean.put("condicion1", "" + cont);
	// bean.put("tipoConsideracionLista",
	// Constants.MAV_TIPO_LISTA_CONDICION);
	// if (cont == 0)
	// bean.put("indicadorAccion", Constants.NUMERO_DOS);
	// }
	//
	// if (Constants.MAV_CONRES_ESTATUS_CLIENTE_REST == Integer.parseInt(f
	// .getCodigoRestriccion())) {
	// List lista = (List) session.getAttribute("codigoEstatusListR");
	// log.debug("listEstatus Rest " + lista.size());
	// bean.put("listEstatusR", lista);
	// // actualizar los eliminados
	// Iterator it = lista.iterator();
	// int cont = lista.size();
	// while (it.hasNext()) {
	// Map aux = (Map) it.next();
	// String indicadorAccion = (String) aux
	// .get("indicadorAccion");
	// if (Constants.NUMERO_DOS.equals(indicadorAccion)) {
	// cont--;
	// }
	// }
	// bean.put("numReg", "" + cont);
	// bean.put("condicion", "" + cont);
	// bean.put("condicion1", "" + cont);
	// bean.put("tipoConsideracionLista",
	// Constants.MAV_TIPO_LISTA_CONDICION);
	// if (cont == 0)
	// bean.put("indicadorAccion", Constants.NUMERO_DOS);
	// }
	// }
	//
	// List listC = (List) session
	// .getAttribute("mavConfiguracionConsideracionList");
	// bean.put("codigoConsideracion", f.getCodigoRestriccion());
	// log.debug("validando listas ");
	// if (isValidoRest(bean, list) && isValido(bean, listC)) {// es registro
	// // valido cuando
	// // no se
	// // encuntra en
	// // la lista o se
	// // encuentra
	// // como
	// // eliminado
	// bean.put("codigoConsideracion", null);
	// list.add(bean);
	// // service.saveTemporalZona(bean);
	// } else {
	// mensaje =
	// this.getResourceMessage("mantenimientoMAVConfiguracionForm.existe.consideracion.registro");
	// this.addError("Error : ", mensaje);
	// f.setSelectedItemsConsideracion(null);
	// f.setSelectedItemsRestriccion(null);
	// return mapping.findForward(getManteForward());
	// }
	//
	// actualizarListTiposOferta(session, f);
	// limpiarCamposRestriccion(f);
	//
	// f.setCodigoRestriccion("");
	//
	// f.setSelectedItemsConsideracion(null);
	// f.setSelectedItemsRestriccion(null);
	// session.setAttribute("mavConfiguracionRestriccionList", list);
	// return mapping.findForward(getManteForward());
	// }

	/**
	 * inserta el Zona de una poliza
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	// public ActionForward updateRestriccion(ActionMapping mapping,
	// ActionForm form, HttpServletRequest request,
	// HttpServletResponse response) throws Exception {
	// if (log.isDebugEnabled()) {
	// log.debug("Entering 'updateRestriccion' method dd");
	// }
	// HttpSession session = request.getSession(true);
	// MantenimientoFACDeshabilitarZonasEnvioBoletasPremioForm f =
	// (MantenimientoFACDeshabilitarZonasEnvioBoletasPremioForm)
	// this.formBusqueda;
	// f.setTabSeleccion(Constants.MAV_TAB_RESTRICCION);
	// //
	// //
	// List list = (List) session
	// .getAttribute("mavConfiguracionRestriccionList");
	// if (list == null)
	// list = new ArrayList();
	//
	// String idPopup = (String) session.getAttribute("idPopup");
	// int index = Integer.parseInt(idPopup);
	// Map bean = (Map) list.get(index);
	// String codigoConRes = (String) bean.get("codigoRestriccion");
	// String tipo = (String) bean.get("indicadorTipo");
	//
	// bean.put("codigoPeriodo", f.getCodigoPeriodo());
	// // bean.put("indicadorAccion",Constants.NUMERO_UNO);
	// bean.put("condicion", "");
	// bean.put("numReg", "0");
	//
	// if (Constants.MAV_TIPO_LISTA_CONDICION.equals(tipo)
	// || Constants.MAV_TIPO_LISTA_EXTERNA_CONDICION.equals(tipo)) {
	// log.debug(" codigoConRes() >>>  " + codigoConRes);
	//
	// if (Constants.MAV_CONRES_LISTA_CONSU_REST == Integer
	// .parseInt(codigoConRes)) {
	// List consultoraList = (List) session
	// .getAttribute("consultoraListR");
	// log.debug("consultoraListR >>>>>>> " + consultoraList.size());
	// bean.put("consultoraListR", consultoraList);
	// bean.put("numReg", "" + consultoraList.size());
	// bean.put("condicion", "" + consultoraList.size());
	// bean.put("condicion1", "" + consultoraList.size());
	// bean.put("tipoConsideracionLista",
	// Constants.MAV_TIPO_LISTA_CONDICION);
	// bean.put("indicadorModificadoPopup", Constants.NRO_UNO);
	// }
	//
	// if (Constants.MAV_CONRES_LISTA_REGION_ZONA_REST == Integer
	// .parseInt(codigoConRes)) {
	// List regionesList = (List) session
	// .getAttribute("regionesListR");
	// log.debug("regionesListR >>>>>>> " + regionesList.size());
	// bean.put("regionesListR", regionesList);
	// bean.put("numReg", "" + regionesList.size());
	// bean.put("condicion", "" + regionesList.size());
	// bean.put("condicion1", "" + regionesList.size());
	// bean.put("tipoConsideracionLista",
	// Constants.MAV_TIPO_LISTA_CONDICION);
	// bean.put("indicadorModificadoPopup", Constants.NRO_UNO);
	// }
	//
	// if (Constants.MAV_CONRES_CLASIFICACIONES_CLIENTE_REST == Integer
	// .parseInt(codigoConRes)) {
	// List listC = (List) session
	// .getAttribute("clasificacionesListR");
	// log.debug("listClasificaciones >>>>>>> " + listC.size());
	// bean.put("listClasificacionesR", listC);
	// // actualizar los eliminados
	// Iterator it = listC.iterator();
	// int cont = listC.size();
	// while (it.hasNext()) {
	// Map aux = (Map) it.next();
	// String indicadorAccion = (String) aux
	// .get("indicadorAccion");
	// if (Constants.NUMERO_DOS.equals(indicadorAccion)) {
	// cont--;
	// }
	// }
	// bean.put("numReg", "" + cont);
	// bean.put("condicion", "" + cont);
	// bean.put("condicion1", "" + cont);
	// bean.put("indicadorModificadoPopup", Constants.NRO_UNO);
	// if (cont == 0)
	// bean.put("indicadorAccion", Constants.NUMERO_DOS);
	// }
	//
	// if (Constants.MAV_CONRES_UNIDAD_ADM_REST == Integer
	// .parseInt(codigoConRes)) {
	// List listU = (List) session.getAttribute("unidadesListR");
	// log.debug("unidadesListR >>>>>>> " + listU.size());
	// bean.put("listUnidadesR", listU);
	// // actualizar los eliminados
	// Iterator it = listU.iterator();
	// int cont = listU.size();
	// while (it.hasNext()) {
	// Map aux = (Map) it.next();
	// String indicadorAccion = (String) aux
	// .get("indicadorAccion");
	// if (Constants.NUMERO_DOS.equals(indicadorAccion)) {
	// cont--;
	// }
	// }
	// bean.put("numReg", "" + cont);
	// bean.put("condicion", "" + cont);
	// bean.put("condicion1", "" + cont);
	// bean.put("indicadorModificadoPopup", Constants.NRO_UNO);
	// if (cont == 0)
	// bean.put("indicadorAccion", Constants.NUMERO_DOS);
	// }
	//
	// if (Constants.MAV_CONRES_ESTATUS_CLIENTE_REST == Integer
	// .parseInt(codigoConRes)) {
	// List lista = (List) session.getAttribute("codigoEstatusListR");
	// log.debug("listEstatus Rest " + lista.size());
	// bean.put("listEstatusR", lista);
	// // actualizar los eliminados
	// Iterator it = lista.iterator();
	// int cont = lista.size();
	// while (it.hasNext()) {
	// Map aux = (Map) it.next();
	// String indicadorAccion = (String) aux
	// .get("indicadorAccion");
	// if (Constants.NUMERO_DOS.equals(indicadorAccion)) {
	// cont--;
	// }
	// }
	// // no contar los eliminados
	// bean.put("numReg", "" + cont);
	// bean.put("condicion", "" + cont);
	// bean.put("condicion1", "" + cont);
	// bean.put("indicadorModificadoPopup", Constants.NRO_UNO);
	// if (cont == 0)
	// bean.put("indicadorAccion", Constants.NUMERO_DOS);
	// }
	// }
	//
	// actualizarListTiposOferta(session, f);
	// limpiarCamposRestriccion(f);
	//
	// f.setSelectedItemsConsideracion(null);
	// f.setSelectedItemsRestriccion(null);
	// session.setAttribute("mavConfiguracionRestriccionList", list);
	// return mapping.findForward(getManteForward());
	// }

	/**
	 * @param codigoRestriccion
	 * @param listComboConsideracion
	 * @return
	 */
	private String getAbrevConRes(String codigo, List listCombo) {
		Iterator it = listCombo.iterator();
		while (it.hasNext()) {
			Map map = (Map) it.next();
			String abrev = (String) map.get("abrevConRes");
			String codigoConRes = String.valueOf(map.get("codigoConRes"));
			if (codigo.equals(codigoConRes)) {
				return abrev;
			}
		}
		return "";
	}

	/**
	 * El registro es valido si no se encunetra en la lista o ya se encuntra
	 * eliminado
	 * 
	 * @param map
	 * @param list
	 * @return
	 */
	private boolean isValidoRest(Map map, List list) {
		Iterator it = list.iterator();
		// String codigoRestriccion = (String)map.get("codigoRestriccion");
		String abrev = (String) map.get("abrev");
		while (it.hasNext()) {
			Map mapAux = (Map) it.next();
			// String codigoRestriccionAux =
			// (String)mapAux.get("codigoRestriccion");
			String abrevAux = (String) mapAux.get("abrev");
			String indicadorAccionAux = (String) mapAux.get("indicadorAccion");
			if (abrev.equals(abrevAux)
					&& (indicadorAccionAux.equals(Constants.NUMERO_CERO) || indicadorAccionAux
							.equals(Constants.NUMERO_UNO)))
				return false;
		}
		return true;
	}

	/**
	 * Elimina logicamente el Zona
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	// public ActionForward deleteRestriccion(ActionMapping mapping,
	// ActionForm form, HttpServletRequest request,
	// HttpServletResponse response) throws Exception {
	// if (log.isDebugEnabled()) {
	// log.debug("Entering 'deleteRestriccion' method");
	// }
	// HttpSession session = request.getSession(true);
	// ActionMessages messages = new ActionMessages();
	// // MantenimientoMAVConfiguracionService service =
	// // (MantenimientoMAVConfiguracionService)
	// // getBean("spusicc.mantenimientoMAVConfiguracionService");
	// String id = request.getParameter("id");
	// MantenimientoFACDeshabilitarZonasEnvioBoletasPremioForm f =
	// (MantenimientoFACDeshabilitarZonasEnvioBoletasPremioForm) form;
	// f.setTabSeleccion(Constants.MAV_TAB_RESTRICCION);
	// log.debug("row id " + id);
	// if (id != null) {
	// try {
	// List list = (List) session
	// .getAttribute("mavConfiguracionRestriccionList");
	// Map bean = (Map) list.get(Integer.parseInt(id) - 1);
	// bean.put("indicadorAccion", Constants.NUMERO_DOS);
	// } catch (Exception e) {
	// e.printStackTrace();
	// String error = e.getMessage();
	// if (StringUtils.isBlank(error))
	// error = e.getLocalizedMessage();
	// messages.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(
	// "errors.detail", error));
	// saveErrors(request, messages);
	// }
	// }
	// actualizarListTiposOferta(session, f);
	// limpiarCamposRestriccion(f);
	//
	// return mapping.findForward(getManteForward());
	//
	// }

	/**
	 * Elimina logicamente el Zona
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	// public ActionForward editRestriccion(ActionMapping mapping,
	// ActionForm form, HttpServletRequest request,
	// HttpServletResponse response) throws Exception {
	// if (log.isDebugEnabled()) {
	// log.debug("Entering 'editRestriccion' method");
	// }
	// HttpSession session = request.getSession(true);
	// ActionMessages messages = new ActionMessages();
	// // MantenimientoMAVConfiguracionService service =
	// // (MantenimientoMAVConfiguracionService)
	// // getBean("spusicc.mantenimientoMAVConfiguracionService");
	// String id = request.getParameter("id");
	// MantenimientoFACDeshabilitarZonasEnvioBoletasPremioForm f =
	// (MantenimientoFACDeshabilitarZonasEnvioBoletasPremioForm)
	// this.formBusqueda;
	// f.setTabSeleccion(Constants.MAV_TAB_RESTRICCION);
	// log.debug("row id " + id);
	// if (id != null) {
	// limpiarCamposRestriccion(f);
	//
	// try {
	// List list = (List) session
	// .getAttribute("mavConfiguracionRestriccionList");
	// Map bean = (Map) list.get(Integer.parseInt(id) - 1);
	// String tipo = (String) bean.get("indicadorTipo");
	// log.debug("Tipo " + tipo);
	// f.setCodigoRestriccion((String) bean.get("codigoRestriccion"));
	//
	// if (StringUtils.isEmpty(tipo)
	// || Constants.MAV_TIPO_SIN_CONDICION.equals(tipo)) {
	// f.setCondicionSimpleRestUnidades((String) bean
	// .get("numeroUnidades"));
	// }
	// if (Constants.MAV_TIPO_UNA_CONDICION.equals(tipo)) {
	// f.setCondicionRest1S((String) bean.get("condicion1"));
	// f.setCondicionUnicoRestUnidades((String) bean
	// .get("numeroUnidades"));
	// }
	// if (Constants.MAV_TIPO_UNA_CONDICION_DOBLE.equals(tipo)) {
	// f.setCondicionRest1((String) bean.get("condicion1"));
	// f.setCondicionRest2((String) bean.get("condicion2"));
	// f.setCondicionDobleRestUnidades((String) bean
	// .get("numeroUnidades"));
	// }
	// if (Constants.MAV_TIPO_CONDICIONES_MIXTAS.equals(tipo)) {
	// if (Constants.MAV_CONRES_INSCRIPCION_NUEVA_DUPLA_REST == Integer
	// .parseInt(f.getCodigoRestriccion())) {
	// f.setCondicionRestPeriodoInicio((String) bean
	// .get("condicion1"));
	// f.setCondicionRestPeriodoFin((String) bean
	// .get("condicion2"));
	//
	// } else {
	// f.setCondicionRestMonto((String) bean.get("condicion1"));
	//
	// String condicionAux = null;
	// if ((Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_MARCA_REST == Integer
	// .parseInt(f.getCodigoRestriccion()))
	// || (Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_MARCA_REST == Integer
	// .parseInt(f.getCodigoRestriccion()))) {
	// f.setCondicionRestMarca((String) bean
	// .get("condicion2"));
	// }
	// if ((Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_NEGOCIO_REST == Integer
	// .parseInt(f.getCodigoRestriccion()))
	// || (Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_NEGOCIO_REST == Integer
	// .parseInt(f.getCodigoRestriccion()))) {
	// f.setCondicionRestNegocio((String) bean
	// .get("condicion2"));
	// }
	//
	// if ((Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_UNIDAD_REST == Integer
	// .parseInt(f.getCodigoRestriccion()))
	// || (Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_UNIDAD_REST == Integer
	// .parseInt(f.getCodigoRestriccion()))) {
	// f.setCondicionRestUnidadNegocio((String) bean
	// .get("condicion2"));
	// }
	//
	// if ((Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_CATALOGO_REST == Integer
	// .parseInt(f.getCodigoRestriccion()))
	// || (Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_CATALOGO_REST == Integer
	// .parseInt(f.getCodigoRestriccion()))) {
	// f.setCondicionRestCatalogo((String) bean
	// .get("condicion2"));
	// }
	//
	// if ((Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_REST == Integer
	// .parseInt(f.getCodigoRestriccion()))
	// || (Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_REST == Integer
	// .parseInt(f.getCodigoRestriccion()))) {
	//
	// f.setCondicionRestPeriodoInicio((String) bean
	// .get("condicion2"));
	// f.setCondicionRestPeriodoFin((String) bean
	// .get("condicion3"));
	// } else {
	// f.setCondicionRestPeriodoInicio((String) bean
	// .get("condicion3"));
	// f.setCondicionRestPeriodoFin((String) bean
	// .get("condicion4"));
	// }
	// }
	//
	// f.setCondicionRestUnidades((String) bean
	// .get("numeroUnidades"));
	// }
	//
	// f.setIndicadorRestriccion(Constants.NUMERO_CERO);
	// f.setSelectedItemsConsideracion(null);
	// f.setSelectedItemsRestriccion(null);
	// session.setAttribute("idRestriccion", id);
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// String error = e.getMessage();
	// if (StringUtils.isBlank(error))
	// error = e.getLocalizedMessage();
	// String mensaje = this.getResourceMessage("errors.detail");
	// this.addError("Error  : ", mensaje);
	// }
	// }
	// actualizarListTiposOferta(session, f);
	//
	// return mapping.findForward(getManteForward());
	//
	// }

	/**
	 * Actualiza un Descuento d ela poliza
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	// public ActionForward saveRestriccion(ActionMapping mapping,
	// ActionForm form, HttpServletRequest request,
	// HttpServletResponse response) throws Exception {
	// if (log.isDebugEnabled()) {
	// log.debug("Entering 'saveRestriccion' method");
	// }
	// HttpSession session = request.getSession(true);
	// MantenimientoFACDeshabilitarZonasEnvioBoletasPremioForm f =
	// (MantenimientoFACDeshabilitarZonasEnvioBoletasPremioForm) form;
	//
	// List list = (List) session
	// .getAttribute("mavConfiguracionRestriccionList");
	// String id = (String) session.getAttribute("idRestriccion");
	// Map bean = (Map) list.get(Integer.parseInt(id) - 1);
	//
	// String tipo = (String) bean.get("indicadorTipo");
	// log.debug("Tipo " + tipo);
	//
	// if (StringUtils.isEmpty(tipo)
	// || Constants.MAV_TIPO_SIN_CONDICION.equals(tipo)) {
	// bean.put("numeroUnidades", f.getCondicionSimpleRestUnidades());
	// bean.put("indicadorModificado", Constants.ESTADO_ACTIVO);
	// }
	// if (Constants.MAV_TIPO_UNA_CONDICION.equals(tipo)) {
	// bean.put("condicion1", f.getCondicionRest1S());
	// bean.put("condicion", f.getCondicionRest1S());
	// bean.put("numeroUnidades", f.getCondicionUnicoRestUnidades());
	// bean.put("indicadorModificado", Constants.ESTADO_ACTIVO);
	// }
	// if (Constants.MAV_TIPO_UNA_CONDICION_DOBLE.equals(tipo)) {
	// bean.put("condicion1", f.getCondicionRest1());
	// bean.put("condicion2", f.getCondicionRest2());
	// bean.put("condicion",
	// f.getCondicionRest1() + " , " + f.getCondicionRest2());
	// bean.put("numeroUnidades", f.getCondicionDobleRestUnidades());
	// bean.put("indicadorModificado", Constants.ESTADO_ACTIVO);
	// }
	// if (Constants.MAV_TIPO_CONDICIONES_MIXTAS.equals(tipo)) {
	// if (Constants.MAV_CONRES_INSCRIPCION_NUEVA_DUPLA_REST == Integer
	// .parseInt(f.getCodigoRestriccion())) {
	// bean.put("condicion", f.getCondicionRestPeriodoInicio() + " , "
	// + f.getCondicionRestPeriodoFin());
	// bean.put("condicion1", f.getCondicionRestPeriodoInicio());
	// bean.put("condicion2", f.getCondicionRestPeriodoFin());
	//
	// } else {
	// String condicionAux = null;
	// String descripcionAux = "";
	// if ((Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_MARCA_REST == Integer
	// .parseInt(f.getCodigoRestriccion()))
	// || (Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_MARCA_REST == Integer
	// .parseInt(f.getCodigoRestriccion()))) {
	// condicionAux = f.getCondicionRestMarca();
	// descripcionAux = getDesMarcaProducto(request, condicionAux);
	// }
	// if ((Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_NEGOCIO_REST == Integer
	// .parseInt(f.getCodigoRestriccion()))
	// || (Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_NEGOCIO_REST == Integer
	// .parseInt(f.getCodigoRestriccion()))) {
	// condicionAux = f.getCondicionRestNegocio();
	// descripcionAux = getDesNegocio(request, condicionAux);
	// }
	//
	// if ((Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_UNIDAD_REST == Integer
	// .parseInt(f.getCodigoRestriccion()))
	// || (Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_UNIDAD_REST == Integer
	// .parseInt(f.getCodigoRestriccion()))) {
	// condicionAux = f.getCondicionRestUnidadNegocio();
	// descripcionAux = getDesUnidadNegocio(request, condicionAux);
	// }
	//
	// if ((Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_CATALOGO_REST == Integer
	// .parseInt(f.getCodigoRestriccion()))
	// || (Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_CATALOGO_REST == Integer
	// .parseInt(f.getCodigoRestriccion()))) {
	// condicionAux = f.getCondicionRestCatalogo();
	// descripcionAux = getDesCatalogo(request, condicionAux);
	// }
	//
	// if ((Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_REST == Integer
	// .parseInt(f.getCodigoRestriccion()))
	// || (Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_REST == Integer
	// .parseInt(f.getCodigoRestriccion()))) {
	// bean.put(
	// "condicion",
	// f.getCondicionRestMonto() + " , "
	// + f.getCondicionRestPeriodoInicio() + " , "
	// + f.getCondicionRestPeriodoFin());
	//
	// bean.put("condicion1", f.getCondicionRestMonto());
	// bean.put("condicion2", f.getCondicionRestPeriodoInicio());
	// bean.put("condicion3", f.getCondicionRestPeriodoFin());
	// } else {
	// bean.put("condicion",
	// f.getCondicionRestMonto() + " , " + descripcionAux
	// + " , " + f.getCondicionRestPeriodoInicio()
	// + " , " + f.getCondicionRestPeriodoFin());
	//
	// bean.put("condicion1", f.getCondicionRestMonto());
	// bean.put("condicion2", condicionAux);
	// bean.put("condicion3", f.getCondicionRestPeriodoInicio());
	// bean.put("condicion4", f.getCondicionRestPeriodoFin());
	// }
	// }
	//
	// bean.put("numeroUnidades", f.getCondicionRestUnidades());
	// bean.put("indicadorModificado", Constants.ESTADO_ACTIVO);
	// }
	//
	// f.setIndicadorRestriccion(Constants.NUMERO_UNO);
	//
	// actualizarListTiposOferta(session, f);
	// limpiarCamposRestriccion(f);
	//
	// f.setCodigoRestriccion("");
	//
	// return mapping.findForward(getManteForward());
	// }

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	// public ActionForward refreshRest(ActionMapping mapping, ActionForm form,
	// HttpServletRequest request, HttpServletResponse response)
	// throws Exception {
	// if (log.isDebugEnabled()) {
	// log.debug("Entering 'refreshRest' method");
	// }
	// HttpSession session = request.getSession(true);
	// MantenimientoFACDeshabilitarZonasEnvioBoletasPremioForm f =
	// (MantenimientoFACDeshabilitarZonasEnvioBoletasPremioForm) form;
	// f.setTabSeleccion(Constants.MAV_TAB_RESTRICCION);
	// f.setIndicadorRestriccion(Constants.NUMERO_UNO);
	//
	// actualizarListTiposOferta(session, f);
	// limpiarCamposRestriccion(f);
	//
	// f.setCodigoRestriccion("");
	//
	// return mapping.findForward(getManteForward());
	// }

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	// public ActionForward refreshConsi(ActionMapping mapping, ActionForm form,
	// HttpServletRequest request, HttpServletResponse response)
	// throws Exception {
	// if (log.isDebugEnabled()) {
	// log.debug("Entering 'refreshConsi' method");
	// }
	// HttpSession session = request.getSession(true);
	// MantenimientoFACDeshabilitarZonasEnvioBoletasPremioForm f =
	// (MantenimientoFACDeshabilitarZonasEnvioBoletasPremioForm) form;
	// f.setTabSeleccion(Constants.MAV_TAB_CONSIDERACION);
	// f.setIndicadorConsideracion(Constants.NUMERO_UNO);
	//
	// actualizarListTiposOferta(session, f);
	// limpiarCamposConsideracion(f);
	//
	// f.setCodigoConsideracion("");
	//
	// f.setSelectedItemsConsideracion(null);
	// f.setSelectedItemsRestriccion(null);
	//
	// return mapping.findForward(getManteForward());
	// }

	/*** FIN TAB RESTRICCION **********/

	private void limpiarCamposConsideracion(
			MantenimientoFACDeshabilitarZonasEnvioBoletasPremioForm f) {
		f.setCondicion1("");
		f.setCondicion2("");
		f.setCondicion1S("");

		f.setCondicionSimpleUnidades("");
		f.setCondicionUnicoUnidades("");
		f.setCondicionDobleUnidades("");

		f.setCondicionMonto("");
		f.setCondicionPeriodoInicio("");
		f.setCondicionPeriodoFin("");
		f.setCondicionUnidades("");
		f.setCondicionMarca("");
		f.setCondicionNegocio("");
		f.setCondicionUnidadNegocio("");
		f.setCondicionCatalogo("");
	}

	private void limpiarCamposRestriccion(
			MantenimientoFACDeshabilitarZonasEnvioBoletasPremioForm f) {
		f.setCondicionRest1("");
		f.setCondicionRest2("");
		f.setCondicionRest1S("");

		f.setCondicionSimpleRestUnidades("");
		f.setCondicionUnicoRestUnidades("");
		f.setCondicionDobleRestUnidades("");

		f.setCondicionRestMonto("");
		f.setCondicionRestPeriodoInicio("");
		f.setCondicionRestPeriodoFin("");
		f.setCondicionRestUnidades("");
		f.setCondicionRestMarca("");
		f.setCondicionRestNegocio("");
		f.setCondicionRestUnidadNegocio("");
		f.setCondicionRestCatalogo("");
	}

	/********* CARGA DE POPUPS ***************/
	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	// public ActionForward viewPopup(ActionMapping mapping, ActionForm form,
	// HttpServletRequest request, HttpServletResponse response)
	// throws Exception {
	// if (log.isDebugEnabled()) {
	// log.debug("Entering 'viewPopup' method");
	// }
	//
	// Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
	// Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
	// MantenimientoFACDeshabilitarZonasEnvioBoletasPremioForm f =
	// (MantenimientoFACDeshabilitarZonasEnvioBoletasPremioForm)
	// this.formBusqueda;
	// String codigoConsRest = (String) request.getParameter("codConsRest");
	// String indicadorTipo = (String) request.getParameter("indicadorTipo");
	// String indicadorUnidad = (String) request
	// .getParameter("indicadorUnidad");
	// String numeroUnidades = (String) request.getParameter("numeroUnidades");
	//
	// session.setAttribute("idPopup", "");
	// session.setAttribute("indicadorCerrarVentana", Constants.NRO_CERO);
	// session.setAttribute("codigoIdiomaISO", usuario.getIdioma()
	// .getCodigoISO());
	// int codigo = Integer.parseInt(codigoConsRest);
	//
	// String forward = "";
	// switch (codigo) {
	// case Constants.MAV_CONRES_LISTA_CONSU:
	// f.setOidPais(obtenerOidPais(pais.getCodigo()));
	// session.setAttribute("consultoraList", new ArrayList());
	// forward = Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo) ?
	// "viewListaCliente"
	// : "viewListaClienteRest";
	// break;
	// case Constants.MAV_CONRES_LISTA_REGION_ZONA:
	// f.setOidPais(obtenerOidPais(pais.getCodigo()));
	// ReporteService reporteService = (ReporteService)
	// getBean("scsicc.reporteService");
	// Map criteriaOperacion = new HashMap();
	// criteriaOperacion.put("codigoPais", getCodigoPais(request));
	// criteriaOperacion.put("indicadorTipoRegion", Constants.SI);
	//
	// request.getSession().setAttribute(
	// Constants.SICC_REGION_LIST,
	// reporteService.getListaGenerico("getRegionesByPais",
	// criteriaOperacion));
	// request.getSession().setAttribute(
	// Constants.SICC_CAPACITADORA_LIST,
	// reporteService.getListaGenerico("getRegionesByPais",
	// criteriaOperacion));
	// request.getSession().setAttribute(Constants.SICC_ZONA_LIST,
	// new ArrayList());
	// f.setIndicadorUnidadPopup(indicadorUnidad);
	//
	// if (StringUtils.equals(indicadorUnidad, Constants.NO)) {
	// f.setNumeroUnidadesPopup(numeroUnidades);
	// f.setNumeroUnidadesPopupUniDifNO(numeroUnidades);
	// }
	//
	// session.setAttribute("regionesList", new ArrayList());
	// actualizarTotalUnidades(request, f, true);
	//
	// forward = Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo) ?
	// "viewListaRegionZona"
	// : "viewListaRegionZonaRest";
	// break;
	// case Constants.MAV_CONRES_CLASIFICACIONES_CLIENTE:
	// session.setAttribute("clasificacionesList", new ArrayList());
	// InterfazSiCCService interfazSiCCService = (InterfazSiCCService)
	// getBean("sisicc.interfazSiCCService");
	// // cargando en session la lista de concursos habilitados
	// request.getSession().setAttribute(
	// Constants.SICC_TIPO_CLIENTE_LIST,
	// interfazSiCCService.getTiposClientesByCodigoISOOID(usuario
	// .getIdioma().getCodigoISO()));
	//
	// f.setOidSubTipoCliente(Constants.OID_SUBTIPO_CLIENTE_DEFAULT);
	// f.setOidTipoClienteAux(Constants.OID_TIPO_CLIENTE_DEFAULT);
	// f.setOidTipoClienteAux2(Constants.OID_TIPO_CLIENTE_DEFAULT);
	// f.setOidTipoClasificacion(null);
	// f.setOidClasificacion(null);
	// f.setIndicadorUnidadPopup(indicadorUnidad);
	//
	// recargarTipologiaClientes(request, f);
	// forward = Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo) ?
	// "viewClasificacionesCliente"
	// : "viewClasificacionesClienteRest";
	// break;
	// case Constants.MAV_CONRES_UNIDAD_ADM:
	// session.setAttribute("unidadesList", new ArrayList());
	// reporteService = (ReporteService) getBean("scsicc.reporteService");
	// criteriaOperacion = new HashMap();
	// criteriaOperacion.put("codigoPais", getCodigoPais(request));
	// request.getSession().setAttribute(
	// Constants.SICC_REGION_LIST,
	// reporteService.getListaGenerico("getRegionesByPais",
	// criteriaOperacion));
	//
	// request.getSession().setAttribute(Constants.SICC_SECCION_LIST,
	// new ArrayList());
	// request.getSession().setAttribute(Constants.SICC_ZONA_LIST,
	// new ArrayList());
	// request.getSession().setAttribute(Constants.SICC_TERRITORIO_LIST,
	// new ArrayList());
	// request.getSession()
	// .setAttribute(
	// "codigoIdiomaISO",
	// getUsuario(request.getSession()).getIdioma()
	// .getCodigoISO());
	// forward = Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo) ?
	// "viewUnidadAdministrativa"
	// : "viewUnidadAdministrativaRest";
	// f.setIndicadorUnidadPopup(indicadorUnidad);
	// break;
	// case Constants.MAV_CONRES_ESTATUS_CLIENTE:
	// session.setAttribute("codigoEstatusList", new ArrayList());
	// reporteService = (ReporteService) getBean("scsicc.reporteService");
	// criteriaOperacion = new HashMap();
	// criteriaOperacion.put("codigoPais", getCodigoPais(request));
	// request.getSession().setAttribute(
	// Constants.SICC_ESTADO_LIST,
	// reporteService.getListaGenerico("getEstadoSaldoConsultora",
	// criteriaOperacion));
	// forward = Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo) ?
	// "viewEstatusCliente"
	// : "viewEstatusClienteRest";
	// break;
	//
	// case Constants.MAV_CONRES_LISTA_CONSU_REST:
	// f.setOidPais(obtenerOidPais(pais.getCodigo()));
	// session.setAttribute("consultoraListR", new ArrayList());
	// forward = Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo) ?
	// "viewListaCliente"
	// : "viewListaClienteRest";
	// break;
	// case Constants.MAV_CONRES_LISTA_REGION_ZONA_REST:
	// f.setOidPais(obtenerOidPais(pais.getCodigo()));
	// reporteService = (ReporteService) getBean("scsicc.reporteService");
	// criteriaOperacion = new HashMap();
	// criteriaOperacion.put("codigoPais", getCodigoPais(request));
	// criteriaOperacion.put("indicadorTipoRegion", Constants.SI);
	//
	// request.getSession().setAttribute(
	// Constants.SICC_REGION_LIST,
	// reporteService.getListaGenerico("getRegionesByPais",
	// criteriaOperacion));
	// request.getSession().setAttribute(
	// Constants.SICC_CAPACITADORA_LIST,
	// reporteService.getListaGenerico("getRegionesByPais",
	// criteriaOperacion));
	// request.getSession().setAttribute(Constants.SICC_ZONA_LIST,
	// new ArrayList());
	// f.setIndicadorUnidadPopup(indicadorUnidad);
	// session.setAttribute("regionesListR", new ArrayList());
	// actualizarTotalUnidades(request, f, false);
	//
	// forward = Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo) ?
	// "viewListaRegionZona"
	// : "viewListaRegionZonaRest";
	// break;
	// case Constants.MAV_CONRES_CLASIFICACIONES_CLIENTE_REST:
	// session.setAttribute("clasificacionesListR", new ArrayList());
	// interfazSiCCService = (InterfazSiCCService)
	// getBean("sisicc.interfazSiCCService");
	// // cargando en session la lista de concursos habilitados
	// request.getSession().setAttribute(
	// Constants.SICC_TIPO_CLIENTE_LIST,
	// interfazSiCCService.getTiposClientesByCodigoISOOID(usuario
	// .getIdioma().getCodigoISO()));
	//
	// f.setOidSubTipoCliente(Constants.OID_SUBTIPO_CLIENTE_DEFAULT);
	// f.setOidTipoClienteAux(Constants.OID_TIPO_CLIENTE_DEFAULT);
	// f.setOidTipoClienteAux2(Constants.OID_TIPO_CLIENTE_DEFAULT);
	// f.setOidTipoClasificacion(null);
	// f.setOidClasificacion(null);
	// f.setIndicadorUnidadPopup(indicadorUnidad);
	//
	// recargarTipologiaClientes(request, f);
	// forward = Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo) ?
	// "viewClasificacionesCliente"
	// : "viewClasificacionesClienteRest";
	// break;
	// case Constants.MAV_CONRES_UNIDAD_ADM_REST:
	// session.setAttribute("unidadesListR", new ArrayList());
	// reporteService = (ReporteService) getBean("scsicc.reporteService");
	// criteriaOperacion = new HashMap();
	// criteriaOperacion.put("codigoPais", getCodigoPais(request));
	// request.getSession().setAttribute(
	// Constants.SICC_REGION_LIST,
	// reporteService.getListaGenerico("getRegionesByPais",
	// criteriaOperacion));
	//
	// request.getSession().setAttribute(Constants.SICC_SECCION_LIST,
	// new ArrayList());
	// request.getSession().setAttribute(Constants.SICC_ZONA_LIST,
	// new ArrayList());
	// request.getSession().setAttribute(Constants.SICC_TERRITORIO_LIST,
	// new ArrayList());
	// request.getSession()
	// .setAttribute(
	// "codigoIdiomaISO",
	// getUsuario(request.getSession()).getIdioma()
	// .getCodigoISO());
	// forward = Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo) ?
	// "viewUnidadAdministrativa"
	// : "viewUnidadAdministrativaRest";
	// f.setIndicadorUnidadPopup(indicadorUnidad);
	// break;
	// case Constants.MAV_CONRES_ESTATUS_CLIENTE_REST:
	// session.setAttribute("codigoEstatusListR", new ArrayList());
	// reporteService = (ReporteService) getBean("scsicc.reporteService");
	// criteriaOperacion = new HashMap();
	// criteriaOperacion.put("codigoPais", getCodigoPais(request));
	// request.getSession().setAttribute(
	// Constants.SICC_ESTADO_LIST,
	// reporteService.getListaGenerico("getEstadoSaldoConsultora",
	// criteriaOperacion));
	// forward = Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo) ?
	// "viewEstatusCliente"
	// : "viewEstatusClienteRest";
	// break;
	// }
	// log.debug("forward ss " + forward);
	// return mapping.findForward(forward);
	// }

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	// public ActionForward editPopup(ActionMapping mapping, ActionForm form,
	// HttpServletRequest request, HttpServletResponse response)
	// throws Exception {
	// if (log.isDebugEnabled()) {
	// log.debug("Entering 'editPopup' method");
	// }
	// Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
	// Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
	// MantenimientoFACDeshabilitarZonasEnvioBoletasPremioForm f =
	// (MantenimientoFACDeshabilitarZonasEnvioBoletasPremioForm)
	// this.formBusqueda;
	// MantenimientoMAVConfiguracionService service =
	// (MantenimientoMAVConfiguracionService)
	// getBean("spusicc.mantenimientoMAVConfiguracionService");
	// ReporteService reporteService = (ReporteService)
	// getBean("scsicc.reporteService");
	//
	// String id = (String) request.getParameter("id");
	// String indicadorTipo = (String) request.getParameter("indicadorTipo");
	// String correlativo = (String) request.getParameter("correlativo");
	// String indicadorUnidad = (String) request
	// .getParameter("indicadorUnidad");
	//
	// session.setAttribute("indicadorCerrarVentana", Constants.NRO_CERO);
	// session.setAttribute("codigoIdiomaISO", usuario.getIdioma()
	// .getCodigoISO());
	//
	// int index = Integer.parseInt(id) - 1;
	// String codigoConsRest = "";
	// if (indicadorTipo.equals(Constants.MAV_TIPO_CONSIDERACION)) {
	// List listConsi = (List) session
	// .getAttribute("mavConfiguracionConsideracionList");
	// Map mapConRes = (Map) listConsi.get(index);
	// codigoConsRest = (String) mapConRes.get("codigoConsideracion");
	// } else {
	// List listConsi = (List) session
	// .getAttribute("mavConfiguracionRestriccionList");
	// Map mapConRes = (Map) listConsi.get(index);
	// codigoConsRest = (String) mapConRes.get("codigoRestriccion");
	// }
	// session.setAttribute("idPopup", "" + index);
	// log.debug("idPopup " + index);
	// int codigo = Integer.parseInt(codigoConsRest);
	// Map criteria = new HashMap();
	// criteria.put("codigoPais", pais.getCodigo());
	// criteria.put("correlativoMAV", correlativo);
	// criteria.put("codigoConsRest", codigoConsRest);
	// criteria.put("indicadorTipo", indicadorTipo);
	// List list = (List) service.getDetalleConsRest(criteria);
	// Iterator it = list.iterator();
	// String forward = "";
	// List listC = null;
	// Map map = null;
	// switch (codigo) {
	// case Constants.MAV_CONRES_LISTA_CONSU:
	// listC = (List) session.getAttribute("consultoraList");
	//
	// if (listC == null) {
	// listC = new ArrayList();
	//
	// while (it.hasNext()) {
	// map = new HashMap();
	// Map bean = (Map) it.next();
	// String codigoPais = (String) bean.get("valCondi1");
	// String codigoCliente = (String) bean.get("valCondi2");
	// String nombreCliente = (String) bean.get("nombreCliente");
	//
	// map.put("codigoPais", codigoPais);
	// map.put("codigoCliente", codigoCliente);
	// map.put("nombreCliente", nombreCliente);
	// map.put("indicadorAccion", Constants.NRO_UNO);
	// map.put("correlativoConsideracion", String.valueOf(bean
	// .get("correlativoConsideracion")));
	// map.put("numeroUnidades",
	// String.valueOf(bean.get("numeroUnidades")));
	// listC.add(map);
	// }
	// }
	// f.setOidPais(obtenerOidPais(pais.getCodigo()));
	// session.setAttribute("consultoraList", listC);
	//
	// forward = Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo) ?
	// "viewListaCliente"
	// : "viewListaClienteRest";
	// break;
	// case Constants.MAV_CONRES_LISTA_REGION_ZONA:
	// listC = (List) session.getAttribute("regionesList");
	//
	// Map criteriaOperacion = new HashMap();
	// criteriaOperacion.put("codigoPais", getCodigoPais(request));
	// criteriaOperacion.put("indicadorTipoRegion", Constants.SI);
	// request.getSession().setAttribute(
	// Constants.SICC_REGION_LIST,
	// reporteService.getListaGenerico("getRegionesByPais",
	// criteriaOperacion));
	// request.getSession().setAttribute(
	// Constants.SICC_CAPACITADORA_LIST,
	// reporteService.getListaGenerico("getRegionesByPais",
	// criteriaOperacion));
	// request.getSession().setAttribute(Constants.SICC_ZONA_LIST,
	// new ArrayList());
	// request.getSession()
	// .setAttribute(
	// "codigoIdiomaISO",
	// getUsuario(request.getSession()).getIdioma()
	// .getCodigoISO());
	//
	// if (listC == null) {
	// listC = new ArrayList();
	//
	// while (it.hasNext()) {
	// map = new HashMap();
	// Map bean = (Map) it.next();
	// String codigoRegion = (String) bean.get("valCondi1");
	// String codigoZona = bean.get("valCondi2") != null ? (String) bean
	// .get("valCondi2") : "";
	// String codigoCapacitadora = bean.get("valCondi3") != null ? (String) bean
	// .get("valCondi3") : "";
	//
	// map.put("codigoRegion", codigoRegion);
	// map.put("codigoZona", codigoZona);
	// map.put("codigoCapacitadora", codigoCapacitadora);
	// map.put("descripcionRegion",
	// getDesRegion(request, codigoRegion));
	// map.put("descripcionZona",
	// getDesZona(request, codigoRegion, codigoZona));
	// map.put("descripcionCapacitadora",
	// getDesCapacitadora(request, codigoCapacitadora));
	// map.put("indicadorAccion", Constants.NRO_UNO);
	// map.put("correlativoConsideracion", String.valueOf(bean
	// .get("correlativoConsideracion")));
	// map.put("numeroUnidades",
	// String.valueOf(bean.get("numeroUnidades")));
	// listC.add(map);
	// }
	// }
	// f.setOidPais(obtenerOidPais(pais.getCodigo()));
	// f.setIndicadorUnidadPopup(indicadorUnidad);
	// session.setAttribute("regionesList", listC);
	// actualizarTotalUnidades(request, f, true);
	//
	// forward = Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo) ?
	// "viewListaRegionZona"
	// : "viewListaRegionZonaRest";
	// break;
	// case Constants.MAV_CONRES_CLASIFICACIONES_CLIENTE:
	// listC = (List) session.getAttribute("clasificacionesList");
	//
	// InterfazSiCCService interfazSiCCService = (InterfazSiCCService)
	// getBean("sisicc.interfazSiCCService");
	// // cargando en session la lista de concursos habilitados
	// request.getSession().setAttribute(
	// Constants.SICC_TIPO_CLIENTE_LIST,
	// interfazSiCCService.getTiposClientesByCodigoISOOID(usuario
	// .getIdioma().getCodigoISO()));
	//
	// f.setOidSubTipoCliente(Constants.OID_SUBTIPO_CLIENTE_DEFAULT);
	// f.setOidTipoClienteAux(Constants.OID_TIPO_CLIENTE_DEFAULT);
	// f.setOidTipoClienteAux2(Constants.OID_TIPO_CLIENTE_DEFAULT);
	// f.setOidTipoClasificacion(null);
	// f.setOidClasificacion(null);
	// f.setIndicadorUnidadPopup(indicadorUnidad);
	//
	// recargarTipologiaClientes(request, f);
	// if (listC == null) {
	// listC = new ArrayList();
	//
	// while (it.hasNext()) {
	// map = new HashMap();
	// Map bean = (Map) it.next();
	// String codigoTipoCliente = (String) bean.get("valCondi1");
	// String codigoSubTipoCliente = (String) bean
	// .get("valCondi2");
	// String codigoTipoClasificacion = (String) bean
	// .get("valCondi3");
	// String codigoClasificacion = (String) bean.get("valCondi4");
	// String oidTipoCliente = getOidTipoCliente(request,
	// codigoTipoCliente);
	// String oidSubTipoCliente = getOidSubtipoCliente(request,
	// codigoTipoCliente, codigoSubTipoCliente);
	// String oidTipoClasificacion = getOidTipoClasificacion(
	// request, codigoTipoCliente, codigoSubTipoCliente,
	// codigoTipoClasificacion);
	// String oidClasificacion = getOidClasificacion(request,
	// codigoTipoCliente, codigoSubTipoCliente,
	// codigoTipoClasificacion, codigoClasificacion);
	//
	// map.put("oidTipoCliente", oidTipoCliente);
	// map.put("oidSubTipoCliente", oidSubTipoCliente);
	// map.put("oidTipoClasificacion", oidTipoClasificacion);
	// map.put("oidClasificacion", oidClasificacion);
	// map.put("codigoTipoCliente", codigoTipoCliente);
	// map.put("codigoSubTipoCliente", codigoSubTipoCliente);
	// map.put("codigoTipoClasificacion", codigoTipoClasificacion);
	// map.put("codigoClasificacion", codigoClasificacion);
	// map.put("descripcionTipoCliente",
	// getDesTipocliente(request, oidTipoCliente));
	// map.put("descripcionSubTipoCliente",
	// getDesSubtipoCliente(request, oidTipoCliente,
	// oidSubTipoCliente));
	// map.put("descripcionTipoClasificacion",
	// getDesTipoClasificacion(request, oidTipoCliente,
	// oidSubTipoCliente, oidTipoClasificacion));
	// map.put("descripcionClasificacion",
	// getClasificacion(request, oidTipoCliente,
	// oidSubTipoCliente, oidTipoClasificacion,
	// oidClasificacion));
	// map.put("indicadorAccion", Constants.NRO_UNO);
	// map.put("correlativoConsideracion", String.valueOf(bean
	// .get("correlativoConsideracion")));
	// map.put("numeroUnidades",
	// String.valueOf(bean.get("numeroUnidades")));
	// listC.add(map);
	// }
	// }
	//
	// session.setAttribute("clasificacionesList", listC);
	// forward = Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo) ?
	// "viewClasificacionesCliente"
	// : "viewClasificacionesClienteRest";
	// break;
	// case Constants.MAV_CONRES_UNIDAD_ADM:
	// listC = (List) session.getAttribute("unidadesList");
	//
	// criteriaOperacion = new HashMap();
	// criteriaOperacion.put("codigoPais", getCodigoPais(request));
	// request.getSession().setAttribute(
	// Constants.SICC_REGION_LIST,
	// reporteService.getListaGenerico("getRegionesByPais",
	// criteriaOperacion));
	//
	// request.getSession().setAttribute(Constants.SICC_SECCION_LIST,
	// new ArrayList());
	// request.getSession().setAttribute(Constants.SICC_ZONA_LIST,
	// new ArrayList());
	// request.getSession().setAttribute(Constants.SICC_TERRITORIO_LIST,
	// new ArrayList());
	// request.getSession()
	// .setAttribute(
	// "codigoIdiomaISO",
	// getUsuario(request.getSession()).getIdioma()
	// .getCodigoISO());
	// forward = Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo) ?
	// "viewUnidadAdministrativa"
	// : "viewUnidadAdministrativaRest";
	// if (listC == null) {
	// listC = new ArrayList();
	//
	// while (it.hasNext()) {
	// map = new HashMap();
	// Map bean = (Map) it.next();
	// String codigoRegion = (String) bean.get("valCondi1");
	// String codigoZona = bean.get("valCondi2") != null ? (String) bean
	// .get("valCondi2") : "";
	// String codigoSeccion = bean.get("valCondi3") != null ? (String) bean
	// .get("valCondi3") : "";
	// String codigoTerritorio = bean.get("valCondi4") != null ? (String) bean
	// .get("valCondi4") : "";
	//
	// map.put("codigoRegion", codigoRegion);
	// map.put("codigoZona", codigoZona);
	// map.put("codigoSeccion", codigoSeccion);
	// map.put("codigoTerritorio", codigoTerritorio);
	// map.put("descripcionRegion",
	// getDesRegion(request, codigoRegion));
	// map.put("descripcionZona",
	// getDesZona(request, codigoRegion, codigoZona));
	// map.put("descripcionSeccion",
	// getDesSeccion(request, codigoRegion, codigoZona,
	// codigoSeccion));
	// map.put("descripcionTerritorio",
	// getDesTerr(request, codigoRegion, codigoZona,
	// codigoSeccion, codigoTerritorio));
	// map.put("indicadorAccion", Constants.NRO_UNO);
	// map.put("correlativoConsideracion", String.valueOf(bean
	// .get("correlativoConsideracion")));
	// map.put("numeroUnidades",
	// String.valueOf(bean.get("numeroUnidades")));
	// listC.add(map);
	// }
	// }
	// session.setAttribute("unidadesList", listC);
	// f.setIndicadorUnidadPopup(indicadorUnidad);
	// break;
	// case Constants.MAV_CONRES_ESTATUS_CLIENTE:
	// listC = (List) session.getAttribute("codigoEstatusList");
	//
	// reporteService = (ReporteService) getBean("scsicc.reporteService");
	// criteriaOperacion = new HashMap();
	// criteriaOperacion.put("codigoPais", getCodigoPais(request));
	// List listEstado = reporteService.getListaGenerico(
	// "getEstadoSaldoConsultora", criteriaOperacion);
	// request.getSession().setAttribute(Constants.SICC_ESTADO_LIST,
	// listEstado);
	// if (listC == null) {
	// listC = new ArrayList();
	//
	// while (it.hasNext()) {
	// map = new HashMap();
	// Map bean = (Map) it.next();
	// map.put("codigoEstado", bean.get("valCondi1"));
	// map.put("descripcionEstado",
	// getDesEstado((String) bean.get("valCondi1"),
	// listEstado));
	// map.put("indicadorAccion", Constants.NRO_UNO);
	// map.put("correlativoConsideracion", String.valueOf(bean
	// .get("correlativoConsideracion")));
	// listC.add(map);
	// }
	// }
	// session.setAttribute("codigoEstatusList", listC);
	// forward = Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo) ?
	// "viewEstatusCliente"
	// : "viewEstatusClienteRest";
	// break;
	//
	// case Constants.MAV_CONRES_LISTA_CONSU_REST:
	// listC = (List) session.getAttribute("consultoraListR");
	//
	// if (listC == null) {
	// listC = new ArrayList();
	//
	// while (it.hasNext()) {
	// map = new HashMap();
	// Map bean = (Map) it.next();
	// String codigoPais = (String) bean.get("valCondi1");
	// String codigoCliente = (String) bean.get("valCondi2");
	// String nombreCliente = (String) bean.get("nombreCliente");
	//
	// map.put("codigoPais", codigoPais);
	// map.put("codigoCliente", codigoCliente);
	// map.put("nombreCliente", nombreCliente);
	// map.put("indicadorAccion", Constants.NRO_UNO);
	// map.put("correlativoConsideracion", String.valueOf(bean
	// .get("correlativoConsideracion")));
	// map.put("numeroUnidades",
	// String.valueOf(bean.get("numeroUnidades")));
	// listC.add(map);
	// }
	// }
	// f.setOidPais(obtenerOidPais(pais.getCodigo()));
	// session.setAttribute("consultoraListR", listC);
	//
	// forward = Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo) ?
	// "viewListaCliente"
	// : "viewListaClienteRest";
	// break;
	// case Constants.MAV_CONRES_LISTA_REGION_ZONA_REST:
	// listC = (List) session.getAttribute("regionesListR");
	//
	// criteriaOperacion = new HashMap();
	// criteriaOperacion.put("codigoPais", getCodigoPais(request));
	// request.getSession().setAttribute(
	// Constants.SICC_REGION_LIST,
	// reporteService.getListaGenerico("getRegionesByPais",
	// criteriaOperacion));
	// request.getSession().setAttribute(
	// Constants.SICC_CAPACITADORA_LIST,
	// reporteService.getListaGenerico("getRegionesByPais",
	// criteriaOperacion));
	// request.getSession().setAttribute(Constants.SICC_ZONA_LIST,
	// new ArrayList());
	// request.getSession()
	// .setAttribute(
	// "codigoIdiomaISO",
	// getUsuario(request.getSession()).getIdioma()
	// .getCodigoISO());
	//
	// if (listC == null) {
	// listC = new ArrayList();
	//
	// while (it.hasNext()) {
	// map = new HashMap();
	// Map bean = (Map) it.next();
	// String codigoRegion = (String) bean.get("valCondi1");
	// String codigoZona = bean.get("valCondi2") != null ? (String) bean
	// .get("valCondi2") : "";
	// String codigoCapacitadora = bean.get("valCondi3") != null ? (String) bean
	// .get("valCondi3") : "";
	//
	// map.put("codigoRegion", codigoRegion);
	// map.put("codigoZona", codigoZona);
	// map.put("codigoCapacitadora", codigoCapacitadora);
	// map.put("descripcionRegion",
	// getDesRegion(request, codigoRegion));
	// map.put("descripcionZona",
	// getDesZona(request, codigoRegion, codigoZona));
	// map.put("descripcionCapacitadora",
	// getDesCapacitadora(request, codigoCapacitadora));
	// map.put("indicadorAccion", Constants.NRO_UNO);
	// map.put("correlativoConsideracion", String.valueOf(bean
	// .get("correlativoConsideracion")));
	// map.put("numeroUnidades",
	// String.valueOf(bean.get("numeroUnidades")));
	// listC.add(map);
	// }
	// }
	// f.setOidPais(obtenerOidPais(pais.getCodigo()));
	// f.setIndicadorUnidadPopup(indicadorUnidad);
	// session.setAttribute("regionesListR", listC);
	// actualizarTotalUnidades(request, f, false);
	//
	// forward = Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo) ?
	// "viewListaRegionZona"
	// : "viewListaRegionZonaRest";
	// break;
	// case Constants.MAV_CONRES_CLASIFICACIONES_CLIENTE_REST:
	// listC = (List) session.getAttribute("clasificacionesListR");
	// interfazSiCCService = (InterfazSiCCService)
	// getBean("sisicc.interfazSiCCService");
	// // cargando en session la lista de concursos habilitados
	// request.getSession().setAttribute(
	// Constants.SICC_TIPO_CLIENTE_LIST,
	// interfazSiCCService.getTiposClientesByCodigoISOOID(usuario
	// .getIdioma().getCodigoISO()));
	//
	// f.setOidSubTipoCliente(Constants.OID_SUBTIPO_CLIENTE_DEFAULT);
	// f.setOidTipoClienteAux(Constants.OID_TIPO_CLIENTE_DEFAULT);
	// f.setOidTipoClienteAux2(Constants.OID_TIPO_CLIENTE_DEFAULT);
	// f.setOidTipoClasificacion(null);
	// f.setOidClasificacion(null);
	// f.setIndicadorUnidadPopup(indicadorUnidad);
	//
	// recargarTipologiaClientes(request, f);
	// if (listC == null) {
	// listC = new ArrayList();
	// while (it.hasNext()) {
	// map = new HashMap();
	// Map bean = (Map) it.next();
	// String codigoTipoCliente = (String) bean.get("valCondi1");
	// String codigoSubTipoCliente = (String) bean
	// .get("valCondi2");
	// String codigoTipoClasificacion = (String) bean
	// .get("valCondi3");
	// String codigoClasificacion = (String) bean.get("valCondi4");
	// String oidTipoCliente = getOidTipoCliente(request,
	// codigoTipoCliente);
	// String oidSubTipoCliente = getOidSubtipoCliente(request,
	// codigoTipoCliente, codigoSubTipoCliente);
	// String oidTipoClasificacion = getOidTipoClasificacion(
	// request, codigoTipoCliente, codigoSubTipoCliente,
	// codigoTipoClasificacion);
	// String oidClasificacion = getOidClasificacion(request,
	// codigoTipoCliente, codigoSubTipoCliente,
	// codigoTipoClasificacion, codigoClasificacion);
	//
	// map.put("oidTipoCliente", oidTipoCliente);
	// map.put("oidSubTipoCliente", oidSubTipoCliente);
	// map.put("oidTipoClasificacion", oidTipoClasificacion);
	// map.put("oidClasificacion", oidClasificacion);
	// map.put("codigoTipoCliente", codigoTipoCliente);
	// map.put("codigoSubTipoCliente", codigoSubTipoCliente);
	// map.put("codigoTipoClasificacion", codigoTipoClasificacion);
	// map.put("codigoClasificacion", codigoClasificacion);
	// map.put("descripcionTipoCliente",
	// getDesTipocliente(request, oidTipoCliente));
	// map.put("descripcionSubTipoCliente",
	// getDesSubtipoCliente(request, oidTipoCliente,
	// oidSubTipoCliente));
	// map.put("descripcionTipoClasificacion",
	// getDesTipoClasificacion(request, oidTipoCliente,
	// oidSubTipoCliente, oidTipoClasificacion));
	// map.put("descripcionClasificacion",
	// getClasificacion(request, oidTipoCliente,
	// oidSubTipoCliente, oidTipoClasificacion,
	// oidClasificacion));
	// map.put("indicadorAccion", Constants.NRO_UNO);
	// map.put("correlativoConsideracion", String.valueOf(bean
	// .get("correlativoConsideracion")));
	// map.put("numeroUnidades",
	// String.valueOf(bean.get("numeroUnidades")));
	//
	// listC.add(map);
	// }
	// }
	// session.setAttribute("clasificacionesListR", listC);
	// forward = Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo) ?
	// "viewClasificacionesCliente"
	// : "viewClasificacionesClienteRest";
	// break;
	// case Constants.MAV_CONRES_UNIDAD_ADM_REST:
	// listC = (List) session.getAttribute("unidadesListR");
	//
	// criteriaOperacion = new HashMap();
	// criteriaOperacion.put("codigoPais", getCodigoPais(request));
	// request.getSession().setAttribute(
	// Constants.SICC_REGION_LIST,
	// reporteService.getListaGenerico("getRegionesByPais",
	// criteriaOperacion));
	//
	// request.getSession().setAttribute(Constants.SICC_SECCION_LIST,
	// new ArrayList());
	// request.getSession().setAttribute(Constants.SICC_ZONA_LIST,
	// new ArrayList());
	// request.getSession().setAttribute(Constants.SICC_TERRITORIO_LIST,
	// new ArrayList());
	// request.getSession()
	// .setAttribute(
	// "codigoIdiomaISO",
	// getUsuario(request.getSession()).getIdioma()
	// .getCodigoISO());
	// forward = Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo) ?
	// "viewUnidadAdministrativa"
	// : "viewUnidadAdministrativaRest";
	//
	// if (listC == null) {
	// listC = new ArrayList();
	// while (it.hasNext()) {
	// map = new HashMap();
	// Map bean = (Map) it.next();
	// String codigoRegion = (String) bean.get("valCondi1");
	// String codigoZona = bean.get("valCondi2") != null ? (String) bean
	// .get("valCondi2") : "";
	// String codigoSeccion = bean.get("valCondi3") != null ? (String) bean
	// .get("valCondi3") : "";
	// ;
	// String codigoTerritorio = bean.get("valCondi4") != null ? (String) bean
	// .get("valCondi4") : "";
	// ;
	//
	// map.put("codigoRegion", codigoRegion);
	// map.put("codigoZona", codigoZona);
	// map.put("codigoSeccion", codigoSeccion);
	// map.put("codigoTerritorio", codigoTerritorio);
	// map.put("descripcionRegion",
	// getDesRegion(request, codigoRegion));
	// map.put("descripcionZona",
	// getDesZona(request, codigoRegion, codigoZona));
	// map.put("descripcionSeccion",
	// getDesSeccion(request, codigoRegion, codigoZona,
	// codigoSeccion));
	// map.put("descripcionTerritorio",
	// getDesTerr(request, codigoRegion, codigoZona,
	// codigoSeccion, codigoTerritorio));
	// map.put("indicadorAccion", Constants.NRO_UNO);
	// map.put("correlativoConsideracion", String.valueOf(bean
	// .get("correlativoConsideracion")));
	// map.put("numeroUnidades",
	// String.valueOf(bean.get("numeroUnidades")));
	// listC.add(map);
	// }
	// }
	// session.setAttribute("unidadesListR", listC);
	// forward = Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo) ?
	// "viewUnidadAdministrativa"
	// : "viewUnidadAdministrativaRest";
	// f.setIndicadorUnidadPopup(indicadorUnidad);
	// break;
	// case Constants.MAV_CONRES_ESTATUS_CLIENTE_REST:
	// listC = (List) session.getAttribute("codigoEstatusListR");
	//
	// reporteService = (ReporteService) getBean("scsicc.reporteService");
	// criteriaOperacion = new HashMap();
	// criteriaOperacion.put("codigoPais", getCodigoPais(request));
	// listEstado = reporteService.getListaGenerico(
	// "getEstadoSaldoConsultora", criteriaOperacion);
	// request.getSession().setAttribute(Constants.SICC_ESTADO_LIST,
	// listEstado);
	// if (listC == null) {
	// listC = new ArrayList();
	// while (it.hasNext()) {
	// map = new HashMap();
	// Map bean = (Map) it.next();
	// map.put("codigoEstado", bean.get("valCondi1"));
	// map.put("descripcionEstado",
	// getDesEstado((String) bean.get("valCondi1"),
	// listEstado));
	// map.put("indicadorAccion", Constants.NRO_UNO);
	// map.put("correlativoConsideracion", String.valueOf(bean
	// .get("correlativoConsideracion")));
	// listC.add(map);
	// }
	// }
	// session.setAttribute("codigoEstatusListR", listC);
	//
	// forward = Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo) ?
	// "viewEstatusCliente"
	// : "viewEstatusClienteRest";
	// break;
	// }
	// log.debug("forward ss " + forward);
	// return mapping.findForward(forward);
	// }

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void insertPopup(ActionEvent evt) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'insertPopup' method");
		}
		try {
			String mensaje = null;
			MantenimientoFACDeshabilitarZonasEnvioBoletasPremioForm f = (MantenimientoFACDeshabilitarZonasEnvioBoletasPremioForm) this.formBusqueda;
			String codigoConsRest = this.codConsRest;
			String indicadorTipo = this.indicadorTipo;
			String insertar = this.insertar;
			InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
			AjaxService ajaxService = (AjaxService) getBean("ajaxService");
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			this.indicadorCerrarVentana = Constants.NRO_CERO;
			f.setIndicadorTipoSeleccionCapacitadora("R");
			String indicadorTipoSeleccion = f
					.getIndicadorTipoSeleccionCapacitadora();
			// indicadorCerrarVentana

			int codigo = Integer.parseInt(codigoConsRest);
			log.debug("codigo " + codigo);
			String forward = "";
			// List list=null;
			switch (codigo) {

			case Constants.MAV_CONRES_LISTA_CONSU:
				List list = consultoraList;

				Map map = new HashMap();
				map.put("codigoPais", pais.getCodigo());
				map.put("codigoCliente", f.getCodigoCliente());
				// map.put("indicadorAccion",Constants.NRO_CERO);
				map.put("numeroUnidades", f.getNumeroUnidadesPopup());
				map.put("indicadorAccion", Constants.MAV_ESTADO_TMP_INSERTAR);

				boolean existe = false;
				String oidPais = obtenerOidPais(pais.getCodigo());
				String datosCliente = ajaxService.getExisteCodigoCliente(oidPais,
						f.getCodigoCliente());
				ActionMessages messages = new ActionMessages();
				if (datosCliente.length() > 0) {
					for (int i = 0; i < list.size(); i++) {
						Map m = (Map) list.get(i);
						String codigoCliente = (String) m.get("codigoCliente");
						String indicadorAccion = (String) m.get("indicadorAccion");

						if (StringUtils.equals(codigoCliente, f.getCodigoCliente())
								&& !(Constants.NUMERO_DOS.equals(indicadorAccion) || Constants.MAV_ESTADO_TMP_ELIMINAR
										.equals(indicadorAccion))) {

							mensaje = this
									.getResourceMessage("mantenimientoMAVConfiguracionCargaClientesForm.error.consultoraDuplicada");
							this.addError("Error : ", mensaje);
							existe = true;
							break;
						}
					}

				} else {
					mensaje = this
							.getResourceMessage("mantenimientoMAVConfiguracionCargaClientesForm.error.noExisteConsultora");
					this.addError("Error : ", mensaje);
					existe = true;
				}

				if (!existe) {
					StringTokenizer st = new StringTokenizer(datosCliente, "|");
					st.nextToken();
					map.put("nombreCliente", st.nextToken());

					list.add(map);

					f.setCodigoCliente("");
					f.setNombreCliente("");
					f.setNumeroUnidadesPopup("");

					consultoraList = list;
				}

				forward = Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo) ? "viewListaCliente"
						: "viewListaClienteRest";
				break;
			case Constants.MAV_CONRES_LISTA_REGION_ZONA:
				list = regionesList;
				map = new HashMap();
				map.put("numeroUnidades", f.getNumeroUnidadesPopup());

				List listUARegionZona = obtenerListaUA();
				if (StringUtils.isNotBlank(insertar)) {
					if (listUARegionZona == null || listUARegionZona.size() == 0) {
						if (Constants.SI.equals(insertar)) {
							mensaje = this
									.getResourceMessage("mantenimientoMAVConfiguracionForm.selecccionarDato");
							this.addError("Error : ", mensaje);
						}
					}
				}

				if (StringUtils.equals(indicadorTipoSeleccion,
						Constants.INDICADOR_TIPO_SELECCION_CAPACITADORA_REGION)) {
					for (int j = 0; j < listUARegionZona.size(); j++) {
						Map mapUA = (Map) listUARegionZona.get(j);
						String codigoRegionAux = (String) mapUA.get("codigoRegion");
						String codigoZonaAux = (String) mapUA.get("codigoZona");

						mapUA.put("numeroUnidades", f.getNumeroUnidadesPopup());
						existe = false;

						for (int i = 0; i < list.size(); i++) {
							Map m = (Map) list.get(i);
							String codigoRegion = (String) m.get("codigoRegion");
							String codigoZona = (String) m.get("codigoZona");

							String indicadorAccion = (String) m
									.get("indicadorAccion");

							if (StringUtils.equals(codigoRegion, codigoRegionAux)
									&& StringUtils
											.equals(codigoZona, codigoZonaAux)
									&& !(Constants.NUMERO_DOS
											.equals(indicadorAccion) || Constants.MAV_ESTADO_TMP_ELIMINAR
											.equals(indicadorAccion))) {

								if (listUARegionZona.size() == 1) {
									mensaje = this
											.getResourceMessage("mantenimientoMAVConfiguracionForm.existe.regionZona");
									this.addError("Error : ", mensaje);
								}

								existe = true;
								break;
							}
						}

						if (!existe) {
							list.add(mapUA);
							regionesList = list;
						}
					}
				} else {
					boolean mostrarMensajeExiste = true;
					for (int j = 0; j < listUARegionZona.size(); j++) {
						Map mapUA = (Map) listUARegionZona.get(j);
						String codigoCapacitadoranAux = (String) mapUA
								.get("codigoCapacitadora");
						mapUA.put("numeroUnidades", f.getNumeroUnidadesPopup());
						existe = false;

						for (int i = 0; i < list.size(); i++) {
							Map m = (Map) list.get(i);
							String codigoCapacitadora = (String) m
									.get("codigoCapacitadora");
							String indicadorAccion = (String) m
									.get("indicadorAccion");

							if (StringUtils.equals(codigoCapacitadora,
									codigoCapacitadoranAux)
									&& !(Constants.NUMERO_DOS
											.equals(indicadorAccion) || Constants.MAV_ESTADO_TMP_ELIMINAR
											.equals(indicadorAccion))) {
								existe = true;
								break;
							}
						}

						if (!existe) {
							list.add(mapUA);
							regionesList = list;
							mostrarMensajeExiste = false;
						}
					}
					if (mostrarMensajeExiste) {
						mensaje = this
								.getResourceMessage("mantenimientoMAVConfiguracionForm.existe.capacitadora");
						this.addError("Error : ", mensaje);
					}
				}
				f.setNumeroUnidadesPopup("");
				actualizarTotalUnidades(true);

				forward = Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo) ? "viewListaRegionZona"
						: "viewListaRegionZonaRest";
				break;
			case Constants.MAV_CONRES_CLASIFICACIONES_CLIENTE:
				list = clasificacionesList;
				map = new HashMap();
				map.put("oidTipoCliente", f.getOidTipoClienteAux());
				map.put("oidSubTipoCliente", f.getOidSubTipoCliente());
				map.put("oidTipoClasificacion", f.getOidTipoClasificacion());
				map.put("oidClasificacion", f.getOidClasificacion());
				map.put("codigoTipoCliente",
						getCodigoTipocliente(f.getOidTipoClienteAux()));
				map.put("codigoSubTipoCliente",
						getCodigoSubtipoCliente(f.getOidTipoClienteAux(),
								f.getOidSubTipoCliente()));
				map.put("codigoTipoClasificacion",
						getCodigoTipoClasificacion(f.getOidTipoClienteAux(),
								f.getOidSubTipoCliente(),
								f.getOidTipoClasificacion()));
				map.put("codigoClasificacion",
						getCodigoClasificacion(f.getOidTipoClienteAux(),
								f.getOidSubTipoCliente(),
								f.getOidTipoClasificacion(),
								f.getOidClasificacion()));
				map.put("descripcionTipoCliente",
						getDesTipocliente(f.getOidTipoClienteAux()));
				map.put("descripcionSubTipoCliente",
						getDesSubtipoCliente(f.getOidTipoClienteAux(),
								f.getOidSubTipoCliente()));
				map.put("descripcionTipoClasificacion",
						getDesTipoClasificacion(f.getOidTipoClienteAux(),
								f.getOidSubTipoCliente(),
								f.getOidTipoClasificacion()));
				map.put("descripcionClasificacion",
						getClasificacion(f.getOidTipoClienteAux(),
								f.getOidSubTipoCliente(),
								f.getOidTipoClasificacion(),
								f.getOidClasificacion()));
				// map.put("indicadorAccion",Constants.NRO_CERO);
				map.put("numeroUnidades", f.getNumeroUnidadesPopup());
				map.put("indicadorAccion", Constants.MAV_ESTADO_TMP_INSERTAR);
				existe = false;
				for (int i = 0; i < list.size(); i++) {
					Map m = (Map) list.get(i);
					String oidTipoCliente = (String) m.get("oidTipoCliente");
					String oidSubTipoCliente = (String) m.get("oidSubTipoCliente");
					String oidTipoClasificacion = (String) m
							.get("oidTipoClasificacion");
					String oidClasificacion = (String) m.get("oidClasificacion");
					String indicadorAccion = (String) m.get("indicadorAccion");
					if (StringUtils
							.equals(oidTipoCliente, f.getOidTipoClienteAux())
							&& StringUtils.equals(oidSubTipoCliente,
									f.getOidSubTipoCliente())
							&& StringUtils.equals(oidTipoClasificacion,
									f.getOidTipoClasificacion())
							&& StringUtils.equals(oidClasificacion,
									f.getOidClasificacion())
							&& !(Constants.NUMERO_DOS.equals(indicadorAccion) || Constants.MAV_ESTADO_TMP_ELIMINAR
									.equals(indicadorAccion))) {

						mensaje = this
								.getResourceMessage("mantenimientoMAVConfiguracionForm.existe.tipologia");
						this.addError("Error : ", mensaje);
						existe = true;
						break;
					}
				}

				if (!existe) {
					list.add(map);
					regionesList = list;
				}

				this.siccTipoClienteList = interfazSiCCService
						.getTiposClientesByCodigoISOOID(usuario.getIdioma()
								.getCodigoISO());
				f.setOidSubTipoCliente(Constants.OID_SUBTIPO_CLIENTE_DEFAULT);
				f.setOidTipoClienteAux(Constants.OID_TIPO_CLIENTE_DEFAULT);
				f.setOidTipoClienteAux2(Constants.OID_TIPO_CLIENTE_DEFAULT);
				f.setOidTipoClasificacion(null);
				f.setOidClasificacion(null);
				f.setNumeroUnidadesPopup("");
				recargarTipologiaClientes();

				forward = Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo) ? "viewClasificacionesCliente"
						: "viewClasificacionesClienteRest";
				break;
			case Constants.MAV_CONRES_UNIDAD_ADM:
				list = this.unidadesList;
				List listUA = obtenerListaUA();
				if (StringUtils.isNotBlank(insertar)) {
					if (listUA == null || listUA.size() == 0) {
						if (Constants.SI.equals(insertar)) {
							mensaje = this
									.getResourceMessage("mantenimientoMAVConfiguracionForm.selecccionarDato");
							this.addError("Error : ", mensaje);
						}
					}
				}

				for (int j = 0; j < listUA.size(); j++) {
					Map mapUA = (Map) listUA.get(j);
					String codigoRegionAux = (String) mapUA.get("codigoRegion");
					String codigoZonaAux = (String) mapUA.get("codigoZona");
					String codigoSeccionAux = (String) mapUA.get("codigoSeccion");
					String codigoTerritorioAux = (String) mapUA
							.get("codigoTerritorio");
					mapUA.put("numeroUnidades", f.getNumeroUnidadesPopup());
					existe = false;

					for (int i = 0; i < list.size(); i++) {
						Map m = (Map) list.get(i);
						String codigoRegion = (String) m.get("codigoRegion");
						String codigoZona = (String) m.get("codigoZona");
						String codigoSeccion = (String) m.get("codigoSeccion");
						String codigoTerritorio = (String) m
								.get("codigoTerritorio");
						String indicadorAccion = (String) m.get("indicadorAccion");

						if (StringUtils.equals(codigoRegion, codigoRegionAux)
								&& StringUtils.equals(codigoZona, codigoZonaAux)
								&& StringUtils.equals(codigoSeccion,
										codigoSeccionAux)
								&& StringUtils.equals(codigoTerritorio,
										codigoTerritorioAux)
								&& !(Constants.NUMERO_DOS.equals(indicadorAccion) || Constants.MAV_ESTADO_TMP_ELIMINAR
										.equals(indicadorAccion))) {

							if (listUA.size() == 1) {
								mensaje = this
										.getResourceMessage("mantenimientoMAVConfiguracionForm.existe.unidades");
								this.addError("Error : ", mensaje);
							}

							existe = true;
							break;
						}
					}

					if (!existe) {
						list.add(mapUA);
						regionesList = list;
					}
				}

				f.setNumeroUnidadesPopup("");

				forward = Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo) ? "viewUnidadAdministrativa"
						: "viewUnidadAdministrativaRest";
				break;
			case Constants.MAV_CONRES_ESTATUS_CLIENTE:
				list = this.codigoEstatusList;
				map = new HashMap();
				map.put("codigoEstado", f.getEstadoList());
				map.put("descripcionEstado", f.getDescripcionEstadoList());
				map.put("indicadorAccion", Constants.MAV_ESTADO_TMP_INSERTAR);
				// map.put("indicadorAccion",Constants.NRO_CERO);

				existe = false;
				for (int i = 0; i < list.size(); i++) {
					Map m = (Map) list.get(i);
					String codigoEstado = (String) m.get("codigoEstado");
					String indicadorAccion = (String) m.get("indicadorAccion");
					if (StringUtils.equals(codigoEstado, f.getEstadoList())
							&& !(Constants.NUMERO_DOS.equals(indicadorAccion) || Constants.MAV_ESTADO_TMP_ELIMINAR
									.equals(indicadorAccion))) {
						mensaje = this
								.getResourceMessage("mantenimientoMAVConfiguracionForm.existe.estatus");
						this.addError("Error : ", mensaje);
						existe = true;
						break;
					}
				}

				if (!existe) {
					list.add(map);
					regionesList = list;
				}
				forward = Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo) ? "viewEstatusCliente"
						: "viewEstatusClienteRest";
				break;

			case Constants.MAV_CONRES_LISTA_CONSU_REST:
				list = this.consultoraListR;

				map = new HashMap();
				map.put("codigoPais", pais.getCodigo());
				map.put("codigoCliente", f.getCodigoCliente());
				// map.put("indicadorAccion",Constants.NRO_CERO);
				map.put("numeroUnidades", f.getNumeroUnidadesPopup());
				map.put("indicadorAccion", Constants.MAV_ESTADO_TMP_INSERTAR);

				oidPais = obtenerOidPais(pais.getCodigo());
				datosCliente = ajaxService.getExisteCodigoCliente(oidPais,
						f.getCodigoCliente());
				messages = new ActionMessages();
				existe = false;

				if (datosCliente.length() > 0) {
					for (int i = 0; i < list.size(); i++) {
						Map m = (Map) list.get(i);
						String codigoCliente = (String) m.get("codigoCliente");
						String indicadorAccion = (String) m.get("indicadorAccion");

						if (StringUtils.equals(codigoCliente, f.getCodigoCliente())
								&& !(Constants.NUMERO_DOS.equals(indicadorAccion) || Constants.MAV_ESTADO_TMP_ELIMINAR
										.equals(indicadorAccion))) {

							mensaje = this
									.getResourceMessage("mantenimientoMAVConfiguracionCargaClientesForm.error.consultoraDuplicada");
							this.addError("Error : ", mensaje);
							existe = true;
							break;
						}
					}

				} else {
					mensaje = this
							.getResourceMessage("mantenimientoMAVConfiguracionCargaClientesForm.error.noExisteConsultora");
					this.addError("Error : ", mensaje);
					existe = true;
				}

				if (!existe) {
					StringTokenizer st = new StringTokenizer(datosCliente, "|");
					st.nextToken();
					map.put("nombreCliente", st.nextToken());

					list.add(map);

					f.setCodigoCliente("");
					f.setNombreCliente("");
					f.setNumeroUnidadesPopup("");

					regionesList = list;
				}

				forward = Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo) ? "viewListaCliente"
						: "viewListaClienteRest";
				break;
			case Constants.MAV_CONRES_LISTA_REGION_ZONA_REST:
				list = this.regionesListR;
				map = new HashMap();
				map.put("numeroUnidades", f.getNumeroUnidadesPopup());

				List listUARegion = obtenerListaUA();
				if (StringUtils.isNotBlank(insertar)) {
					if (listUARegion == null || listUARegion.size() == 0) {
						if (Constants.SI.equals(insertar)) {
							mensaje = this
									.getResourceMessage("mantenimientoMAVConfiguracionForm.selecccionarDato");
							this.addError("Error : ", mensaje);
						}
					}
				}
				if (StringUtils.equals(indicadorTipoSeleccion,
						Constants.INDICADOR_TIPO_SELECCION_CAPACITADORA_REGION)) {
					for (int j = 0; j < listUARegion.size(); j++) {
						Map mapUA = (Map) listUARegion.get(j);
						String codigoRegionAux = (String) mapUA.get("codigoRegion");
						String codigoZonaAux = (String) mapUA.get("codigoZona");

						mapUA.put("numeroUnidades", f.getNumeroUnidadesPopup());
						existe = false;

						for (int i = 0; i < list.size(); i++) {
							Map m = (Map) list.get(i);
							String codigoRegion = (String) m.get("codigoRegion");
							String codigoZona = (String) m.get("codigoZona");

							String indicadorAccion = (String) m
									.get("indicadorAccion");

							if (StringUtils.equals(codigoRegion, codigoRegionAux)
									&& StringUtils
											.equals(codigoZona, codigoZonaAux)
									&& !(Constants.NUMERO_DOS
											.equals(indicadorAccion) || Constants.MAV_ESTADO_TMP_ELIMINAR
											.equals(indicadorAccion))) {

								if (listUARegion.size() == 1) {
									mensaje = this
											.getResourceMessage("mantenimientoMAVConfiguracionForm.existe.regionZona");
									this.addError("Error : ", mensaje);
								}

								existe = true;
								break;
							}
						}

						if (!existe) {
							list.add(mapUA);
							regionesList = list;
						}
					}
				} else {
					boolean mostrarMensajeExiste = true;
					for (int j = 0; j < listUARegion.size(); j++) {
						Map mapUA = (Map) listUARegion.get(j);
						String codigoCapacitadoranAux = (String) mapUA
								.get("codigoCapacitadora");
						mapUA.put("numeroUnidades", f.getNumeroUnidadesPopup());
						existe = false;

						for (int i = 0; i < list.size(); i++) {
							Map m = (Map) list.get(i);
							String codigoCapacitadora = (String) m
									.get("codigoCapacitadora");
							String indicadorAccion = (String) m
									.get("indicadorAccion");

							if (StringUtils.equals(codigoCapacitadora,
									codigoCapacitadoranAux)
									&& !(Constants.NUMERO_DOS
											.equals(indicadorAccion) || Constants.MAV_ESTADO_TMP_ELIMINAR
											.equals(indicadorAccion))) {
								existe = true;
								break;
							}
						}

						if (!existe) {
							list.add(mapUA);
							regionesList = list;
							mostrarMensajeExiste = false;
						}
					}
					if (mostrarMensajeExiste) {
						mensaje = this
								.getResourceMessage("mantenimientoMAVConfiguracionForm.existe.capacitadora");
						this.addError("Error : ", mensaje);
					}
				}
				f.setNumeroUnidadesPopup("");
				actualizarTotalUnidades(false);

				forward = Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo) ? "viewListaRegionZona"
						: "viewListaRegionZonaRest";
				break;
			case Constants.MAV_CONRES_CLASIFICACIONES_CLIENTE_REST:
				list = this.clasificacionesListR;
				map = new HashMap();
				map.put("oidTipoCliente", f.getOidTipoClienteAux());
				map.put("oidSubTipoCliente", f.getOidSubTipoCliente());
				map.put("oidTipoClasificacion", f.getOidTipoClasificacion());
				map.put("oidClasificacion", f.getOidClasificacion());
				map.put("codigoTipoCliente",
						getCodigoTipocliente(f.getOidTipoClienteAux()));
				map.put("codigoSubTipoCliente",
						getCodigoSubtipoCliente(f.getOidTipoClienteAux(),
								f.getOidSubTipoCliente()));
				map.put("codigoTipoClasificacion",
						getCodigoTipoClasificacion(f.getOidTipoClienteAux(),
								f.getOidSubTipoCliente(),
								f.getOidTipoClasificacion()));
				map.put("codigoClasificacion",
						getCodigoClasificacion(f.getOidTipoClienteAux(),
								f.getOidSubTipoCliente(),
								f.getOidTipoClasificacion(),
								f.getOidClasificacion()));
				map.put("descripcionTipoCliente",
						getDesTipocliente(f.getOidTipoClienteAux()));
				map.put("descripcionSubTipoCliente",
						getDesSubtipoCliente(f.getOidTipoClienteAux(),
								f.getOidSubTipoCliente()));
				map.put("descripcionTipoClasificacion",
						getDesTipoClasificacion(f.getOidTipoClienteAux(),
								f.getOidSubTipoCliente(),
								f.getOidTipoClasificacion()));
				map.put("descripcionClasificacion",
						getClasificacion(f.getOidTipoClienteAux(),
								f.getOidSubTipoCliente(),
								f.getOidTipoClasificacion(),
								f.getOidClasificacion()));
				// map.put("indicadorAccion",Constants.NRO_CERO);
				map.put("numeroUnidades", f.getNumeroUnidadesPopup());
				map.put("indicadorAccion", Constants.MAV_ESTADO_TMP_INSERTAR);
				existe = false;
				for (int i = 0; i < list.size(); i++) {
					Map m = (Map) list.get(i);
					String oidTipoCliente = (String) m.get("oidTipoCliente");
					String oidSubTipoCliente = (String) m.get("oidSubTipoCliente");
					String oidTipoClasificacion = (String) m
							.get("oidTipoClasificacion");
					String oidClasificacion = (String) m.get("oidClasificacion");
					String indicadorAccion = (String) m.get("indicadorAccion");
					if (StringUtils
							.equals(oidTipoCliente, f.getOidTipoClienteAux())
							&& StringUtils.equals(oidSubTipoCliente,
									f.getOidSubTipoCliente())
							&& StringUtils.equals(oidTipoClasificacion,
									f.getOidTipoClasificacion())
							&& StringUtils.equals(oidClasificacion,
									f.getOidClasificacion())
							&& !(Constants.NUMERO_DOS.equals(indicadorAccion) || Constants.MAV_ESTADO_TMP_ELIMINAR
									.equals(indicadorAccion))) {
						mensaje = this
								.getResourceMessage("mantenimientoMAVConfiguracionForm.existe.tipologia");
						this.addError("Error :", mensaje);
						existe = true;
						break;
					}
				}

				if (!existe) {
					list.add(map);
					regionesList = list;
				}

				this.siccTipoClienteList = interfazSiCCService
						.getTiposClientesByCodigoISOOID(usuario.getIdioma()
								.getCodigoISO());
				f.setOidSubTipoCliente(Constants.OID_SUBTIPO_CLIENTE_DEFAULT);
				f.setOidTipoClienteAux(Constants.OID_TIPO_CLIENTE_DEFAULT);
				f.setOidTipoClienteAux2(Constants.OID_TIPO_CLIENTE_DEFAULT);
				f.setOidTipoClasificacion(null);
				f.setOidClasificacion(null);
				f.setNumeroUnidadesPopup("");
				recargarTipologiaClientes();

				forward = Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo) ? "viewClasificacionesCliente"
						: "viewClasificacionesClienteRest";
				break;
			case Constants.MAV_CONRES_UNIDAD_ADM_REST:
				list = this.unidadesListR;
				List listUAR = obtenerListaUA();

				if (StringUtils.isNotBlank(insertar)) {
					if (listUAR == null || listUAR.size() == 0) {
						if (Constants.SI.equals(insertar)) {
							mensaje = this
									.getResourceMessage("mantenimientoMAVConfiguracionForm.selecccionarDato");
							this.addError("Error :", mensaje);
						}
					}
				}

				for (int j = 0; j < listUAR.size(); j++) {
					Map mapUA = (Map) listUAR.get(j);
					String codigoRegionAux = (String) mapUA.get("codigoRegion");
					String codigoZonaAux = (String) mapUA.get("codigoZona");
					String codigoSeccionAux = (String) mapUA.get("codigoSeccion");
					String codigoTerritorioAux = (String) mapUA
							.get("codigoTerritorio");
					existe = false;

					for (int i = 0; i < list.size(); i++) {
						Map m = (Map) list.get(i);
						String codigoRegion = (String) m.get("codigoRegion");
						String codigoZona = (String) m.get("codigoZona");
						String codigoSeccion = (String) m.get("codigoSeccion");
						String codigoTerritorio = (String) m
								.get("codigoTerritorio");
						String indicadorAccion = (String) m.get("indicadorAccion");

						if (StringUtils.equals(codigoRegion, codigoRegionAux)
								&& StringUtils.equals(codigoZona, codigoZonaAux)
								&& StringUtils.equals(codigoSeccion,
										codigoSeccionAux)
								&& StringUtils.equals(codigoTerritorio,
										codigoTerritorioAux)
								&& !(Constants.NUMERO_DOS.equals(indicadorAccion) || Constants.MAV_ESTADO_TMP_ELIMINAR
										.equals(indicadorAccion))) {

							if (listUAR.size() == 1) {
								mensaje = this
										.getResourceMessage("mantenimientoMAVConfiguracionForm.existe.unidades");
								this.addError("Error :", mensaje);
							}

							existe = true;
							break;
						}
					}

					if (!existe) {
						list.add(mapUA);
						regionesList = list;
					}
				}

				f.setNumeroUnidadesPopup("");
				forward = Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo) ? "viewUnidadAdministrativa"
						: "viewUnidadAdministrativaRest";
				break;
			case Constants.MAV_CONRES_ESTATUS_CLIENTE_REST:
				list = this.codigoEstatusListR;
				map = new HashMap();
				map.put("codigoEstado", f.getEstadoList());
				map.put("descripcionEstado", f.getDescripcionEstadoList());
				// map.put("indicadorAccion",Constants.NRO_CERO);
				map.put("indicadorAccion", Constants.MAV_ESTADO_TMP_INSERTAR);
				existe = false;
				for (int i = 0; i < list.size(); i++) {
					Map m = (Map) list.get(i);
					String codigoEstado = (String) m.get("codigoEstado");
					String indicadorAccion = (String) m.get("indicadorAccion");
					if (StringUtils.equals(codigoEstado, f.getEstadoList())
							&& !Constants.NUMERO_DOS.equals(indicadorAccion)) {
						mensaje = this
								.getResourceMessage("mantenimientoMAVConfiguracionForm.existe.estatus");
						this.addError("Error :", mensaje);
						existe = true;
						break;
					}
				}

				if (!existe) {
					list.add(map);
					regionesList = list;
				}
				forward = Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo) ? "viewEstatusCliente"
						: "viewEstatusClienteRest";
				break;
			}
			log.debug("forward " + forward);
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
		
	}

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cancelarPopup(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'cancelarPopup' method");
		}
		HttpSession session = request.getSession(true);
		String codigoConsRest = (String) request.getParameter("codConsRest");
		String indicadorTipo = (String) request.getParameter("indicadorTipo");
		session.setAttribute("indicadorCerrarVentana", Constants.NUMERO_DOS);
		// indicadorCerrarVentana

		int codigo = Integer.parseInt(codigoConsRest);
		log.debug("codigo " + codigo + " tipo " + indicadorTipo);
		String forward = "";
		// List list=null;
		switch (codigo) {

		case Constants.MAV_CONRES_LISTA_CONSU:
			List list = (List) session.getAttribute("consultoraList");
			if (list != null) {
				for (int i = list.size() - 1; i >= 0; i--) {
					Map m = (Map) list.get(i);
					String indicadorAccion = (String) m.get("indicadorAccion");
					String indicadorAcionAnt = (String) m
							.get("indicadorAccionAnterior");
					if (Constants.MAV_ESTADO_TMP_INSERTAR
							.equals(indicadorAccion)
							|| Constants.MAV_ESTADO_TMP_INSERTAR
									.equals(indicadorAcionAnt)) {
						list.remove(i);
					}
					if (Constants.MAV_ESTADO_TMP_ELIMINAR
							.equals(indicadorAccion)) {
						m.put("indicadorAccion", indicadorAcionAnt);
					}
				}
			}
			forward = Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo) ? "viewListaCliente"
					: "viewListaClienteRest";
			break;
		case Constants.MAV_CONRES_LISTA_REGION_ZONA:
			list = (List) session.getAttribute("regionesList");
			if (list != null) {
				for (int i = list.size() - 1; i >= 0; i--) {
					Map m = (Map) list.get(i);
					String indicadorAccion = (String) m.get("indicadorAccion");
					String indicadorAcionAnt = (String) m
							.get("indicadorAccionAnterior");
					if (Constants.MAV_ESTADO_TMP_INSERTAR
							.equals(indicadorAccion)
							|| Constants.MAV_ESTADO_TMP_INSERTAR
									.equals(indicadorAcionAnt)) {
						list.remove(i);
					}
					if (Constants.MAV_ESTADO_TMP_ELIMINAR
							.equals(indicadorAccion)) {
						m.put("indicadorAccion", indicadorAcionAnt);
					}
				}
			}
			forward = Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo) ? "viewListaRegionZona"
					: "viewListaRegionZonaRest";
			break;
		case Constants.MAV_CONRES_CLASIFICACIONES_CLIENTE:
			list = (List) session.getAttribute("clasificacionesList");
			if (list != null) {
				for (int i = list.size() - 1; i >= 0; i--) {
					Map m = (Map) list.get(i);
					String indicadorAccion = (String) m.get("indicadorAccion");
					String indicadorAcionAnt = (String) m
							.get("indicadorAccionAnterior");
					if (Constants.MAV_ESTADO_TMP_INSERTAR
							.equals(indicadorAccion)
							|| Constants.MAV_ESTADO_TMP_INSERTAR
									.equals(indicadorAcionAnt)) {
						list.remove(i);
					}
					if (Constants.MAV_ESTADO_TMP_ELIMINAR
							.equals(indicadorAccion)) {
						m.put("indicadorAccion", indicadorAcionAnt);
					}
				}
			}
			forward = Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo) ? "viewClasificacionesCliente"
					: "viewClasificacionesClienteRest";
			break;
		case Constants.MAV_CONRES_UNIDAD_ADM:
			list = (List) session.getAttribute("unidadesList");
			if (list != null) {
				for (int i = list.size() - 1; i >= 0; i--) {
					Map m = (Map) list.get(i);
					String indicadorAccion = (String) m.get("indicadorAccion");
					String indicadorAcionAnt = (String) m
							.get("indicadorAccionAnterior");
					if (Constants.MAV_ESTADO_TMP_INSERTAR
							.equals(indicadorAccion)
							|| Constants.MAV_ESTADO_TMP_INSERTAR
									.equals(indicadorAcionAnt)) {
						list.remove(i);
					}
					if (Constants.MAV_ESTADO_TMP_ELIMINAR
							.equals(indicadorAccion)) {
						m.put("indicadorAccion", indicadorAcionAnt);
					}
				}
			}
			forward = Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo) ? "viewUnidadAdministrativa"
					: "viewUnidadAdministrativaRest";
			break;
		case Constants.MAV_CONRES_ESTATUS_CLIENTE:
			list = (List) session.getAttribute("codigoEstatusList");
			if (list != null) {
				for (int i = list.size() - 1; i >= 0; i--) {
					Map m = (Map) list.get(i);
					String indicadorAccion = (String) m.get("indicadorAccion");
					String indicadorAcionAnt = (String) m
							.get("indicadorAccionAnterior");
					if (Constants.MAV_ESTADO_TMP_INSERTAR
							.equals(indicadorAccion)
							|| Constants.MAV_ESTADO_TMP_INSERTAR
									.equals(indicadorAcionAnt)) {
						list.remove(i);
					}
					if (Constants.MAV_ESTADO_TMP_ELIMINAR
							.equals(indicadorAccion)) {
						m.put("indicadorAccion", indicadorAcionAnt);
					}
				}
			}
			forward = Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo) ? "viewEstatusCliente"
					: "viewEstatusClienteRest";
			break;

		case Constants.MAV_CONRES_LISTA_CONSU_REST:
			list = (List) session.getAttribute("consultoraListR");
			if (list != null) {
				for (int i = list.size() - 1; i >= 0; i--) {
					Map m = (Map) list.get(i);
					String indicadorAccion = (String) m.get("indicadorAccion");
					String indicadorAcionAnt = (String) m
							.get("indicadorAccionAnterior");
					if (Constants.MAV_ESTADO_TMP_INSERTAR
							.equals(indicadorAccion)
							|| Constants.MAV_ESTADO_TMP_INSERTAR
									.equals(indicadorAcionAnt)) {
						list.remove(i);
					}
					if (Constants.MAV_ESTADO_TMP_ELIMINAR
							.equals(indicadorAccion)) {
						m.put("indicadorAccion", indicadorAcionAnt);
					}
				}
			}
			forward = Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo) ? "viewListaCliente"
					: "viewListaClienteRest";
			break;
		case Constants.MAV_CONRES_LISTA_REGION_ZONA_REST:
			list = (List) session.getAttribute("regionesListR");
			if (list != null) {
				for (int i = list.size() - 1; i >= 0; i--) {
					Map m = (Map) list.get(i);
					String indicadorAccion = (String) m.get("indicadorAccion");
					String indicadorAcionAnt = (String) m
							.get("indicadorAccionAnterior");
					if (Constants.MAV_ESTADO_TMP_INSERTAR
							.equals(indicadorAccion)
							|| Constants.MAV_ESTADO_TMP_INSERTAR
									.equals(indicadorAcionAnt)) {
						list.remove(i);
					}
					if (Constants.MAV_ESTADO_TMP_ELIMINAR
							.equals(indicadorAccion)) {
						m.put("indicadorAccion", indicadorAcionAnt);
					}
				}
			}
			forward = Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo) ? "viewListaRegionZona"
					: "viewListaRegionZonaRest";
			break;
		case Constants.MAV_CONRES_CLASIFICACIONES_CLIENTE_REST:
			list = (List) session.getAttribute("clasificacionesListR");
			if (list != null) {
				for (int i = list.size() - 1; i >= 0; i--) {
					Map m = (Map) list.get(i);
					String indicadorAccion = (String) m.get("indicadorAccion");
					String indicadorAcionAnt = (String) m
							.get("indicadorAccionAnterior");
					if (Constants.MAV_ESTADO_TMP_INSERTAR
							.equals(indicadorAccion)
							|| Constants.MAV_ESTADO_TMP_INSERTAR
									.equals(indicadorAcionAnt)) {
						list.remove(i);
					}
					if (Constants.MAV_ESTADO_TMP_ELIMINAR
							.equals(indicadorAccion)) {
						m.put("indicadorAccion", indicadorAcionAnt);
					}
				}
			}
			forward = Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo) ? "viewClasificacionesCliente"
					: "viewClasificacionesClienteRest";
			break;
		case Constants.MAV_CONRES_UNIDAD_ADM_REST:
			list = (List) session.getAttribute("unidadesListR");
			if (list != null) {
				for (int i = list.size() - 1; i >= 0; i--) {
					Map m = (Map) list.get(i);
					String indicadorAccion = (String) m.get("indicadorAccion");
					String indicadorAcionAnt = (String) m
							.get("indicadorAccionAnterior");
					if (Constants.MAV_ESTADO_TMP_INSERTAR
							.equals(indicadorAccion)
							|| Constants.MAV_ESTADO_TMP_INSERTAR
									.equals(indicadorAcionAnt)) {
						list.remove(i);
					}
					if (Constants.MAV_ESTADO_TMP_ELIMINAR
							.equals(indicadorAccion)) {
						m.put("indicadorAccion", indicadorAcionAnt);
					}
				}
			}
			forward = Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo) ? "viewUnidadAdministrativa"
					: "viewUnidadAdministrativaRest";
			break;
		case Constants.MAV_CONRES_ESTATUS_CLIENTE_REST:
			list = (List) session.getAttribute("codigoEstatusListR");
			if (list != null) {
				for (int i = list.size() - 1; i >= 0; i--) {
					Map m = (Map) list.get(i);
					String indicadorAccion = (String) m.get("indicadorAccion");
					String indicadorAcionAnt = (String) m
							.get("indicadorAccionAnterior");
					if (Constants.MAV_ESTADO_TMP_INSERTAR
							.equals(indicadorAccion)
							|| Constants.MAV_ESTADO_TMP_INSERTAR
									.equals(indicadorAcionAnt)) {
						list.remove(i);
					}
					if (Constants.MAV_ESTADO_TMP_ELIMINAR
							.equals(indicadorAccion)) {
						m.put("indicadorAccion", indicadorAcionAnt);
					}
				}
			}
			forward = Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo) ? "viewEstatusCliente"
					: "viewEstatusClienteRest";
			break;
		}
		log.debug("forward xx " + forward);
		return mapping.findForward(forward);
	}

	private LabelValue[] siccSubTipoClienteList;
	private LabelValue[] siccTipoClasificacionList;
	private LabelValue[] siccClasificacionList;

	private void recargarTipologiaClientes() {
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		log.debug("recargarTipologiaClientes ");
		MantenimientoMAVConfiguracionForm f = (MantenimientoMAVConfiguracionForm) this.formMantenimiento;

		ArrayList temp = new ArrayList();
		temp = new ArrayList();
		temp.add(f.getOidTipoClienteAux());
		siccSubTipoClienteList = aSvc
				.getSubTiposClientesPorPaisTipoClientesOID(usuario.getIdioma()
						.getCodigoISO(), temp);

		temp = new ArrayList();
		temp.add(f.getOidSubTipoCliente());
		LabelValue[] listTiposClasificiones = aSvc
				.getTiposClasificacionesByCriteriaMultipleOID(usuario
						.getIdioma().getCodigoISO(),
						Constants.OID_TIPO_CLIENTE_DEFAULT, temp);
		siccTipoClasificacionList = listTiposClasificiones;

		if (f.getOidTipoClasificacion() == null)
			f.setOidTipoClasificacion(listTiposClasificiones[0].getValue());

		ArrayList temp2 = new ArrayList();
		temp2 = new ArrayList();
		temp2.add(f.getOidTipoClasificacion());
		siccClasificacionList = aSvc.getClasificacionesByCriteriaMultipleOID(
				usuario.getIdioma().getCodigoISO(),
				Constants.OID_TIPO_CLIENTE_DEFAULT, temp, temp2);

	}

	/**
	 * Obtiene la descripcion del tipo cliente
	 * 
	 * @param request
	 * @param oidTipoCliente
	 * @return
	 */
	private String getOidTipoCliente(HttpServletRequest request,
			String codigoTipoCliente) {
		String descp = getDesTipoclienteByCodigo(request, codigoTipoCliente);
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		List list = (List) interfazSiCCService
				.getTiposClientesByCodigoISOOID(usuario.getIdioma()
						.getCodigoISO());
		Iterator it = list.iterator();
		while (it.hasNext()) {
			BaseOID base = (BaseOID) it.next();
			if (StringUtils.equalsIgnoreCase(descp, base.getDescripcion()))
				return "" + base.getOid();
		}
		return "";
	}

	/**
	 * obtiene la descripcion del
	 * 
	 * @param request
	 * @param oidTipoCliente
	 * @param oidSubTipoCliente
	 * @return
	 */
	private String getOidSubtipoCliente(HttpServletRequest request,
			String codigoTipoCliente, String codigoSubTipoCliente) {
		// Usuario usuario = getUsuario(request);
		MantenimientoMENGenericoService service = (MantenimientoMENGenericoService) getBean("spusicc.mantenimientoMENGenericoService");
		Map criteria = new HashMap();
		criteria.put("codigoTipoCliente", codigoTipoCliente);
		criteria.put("codigoSubTipoCliente", codigoSubTipoCliente);
		List list = (List) service.getSubTiposClientes(criteria);
		Map map = (Map) list.get(0);
		String oid = String.valueOf(map.get("oidSubTipoCliente"));
		return oid;

	}

	/**
	 * Obtien la descripcion del tipo de clasificacion
	 * 
	 * @param request
	 * @param oidTipoCliente
	 * @param oidSubTipoCliente
	 * @param oidTipoClasificacion
	 * @return
	 */
	private String getOidTipoClasificacion(HttpServletRequest request,
			String codigoTipoCliente, String codigoSubTipoCliente,
			String codigoTipoClasificacion) {
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		MantenimientoMENGenericoService service = (MantenimientoMENGenericoService) getBean("spusicc.mantenimientoMENGenericoService");
		Map criteria = new HashMap();
		criteria.put("codigoTipoCliente", codigoTipoCliente);
		criteria.put("codigoSubTipoCliente", codigoSubTipoCliente);
		criteria.put("codigoTipoClasificacion", codigoTipoClasificacion);
		criteria.put("codigoISO", usuario.getIdioma().getCodigoISO());
		if (StringUtils.isNotEmpty(codigoTipoClasificacion)) {
			List list = (List) service.getTiposClasificaciones(criteria);
			Map map = (Map) list.get(0);
			String oid = String.valueOf(map.get("oidTipoClasificacion"));
			return oid;
		}
		return "";
	}

	/**
	 * Obtine la descripcion de clasificacion
	 * 
	 * @param request
	 * @param oidTipoCliente
	 * @param oidSubTipoCliente
	 * @param oidTipoClasificacion
	 * @param oidClasificacion
	 * @return
	 */
	private String getOidClasificacion(HttpServletRequest request,
			String codigoTipoCliente, String codigoSubTipoCliente,
			String codigoTipoClasificacion, String codigoClasificacion) {

		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		MantenimientoMENGenericoService service = (MantenimientoMENGenericoService) getBean("spusicc.mantenimientoMENGenericoService");
		Map criteria = new HashMap();
		criteria.put("codigoTipoCliente", codigoTipoCliente);
		criteria.put("codigoSubTipoCliente", codigoSubTipoCliente);
		criteria.put("codigoTipoClasificacion", codigoTipoClasificacion);
		criteria.put("codigoClasificacion", codigoClasificacion);
		criteria.put("codigoISO", usuario.getIdioma().getCodigoISO());

		if (StringUtils.isNotEmpty(codigoTipoClasificacion)
				&& StringUtils.isNotEmpty(codigoClasificacion)) {
			List list = (List) service.getClasificaciones(criteria);
			Map map = (Map) list.get(0);
			String oid = String.valueOf(map.get("oidClasificacion"));
			return oid;
		}
		return "";

	}

	/**
	 * Obtiene la descripcion del tipo cliente
	 * 
	 * @param request
	 * @param oidTipoCliente
	 * @return
	 */
	private String getDesTipocliente(String oidTipoCliente) {
		log.debug("getDesTipocliente oidTipoCliente " + oidTipoCliente);
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		List list = (List) interfazSiCCService
				.getTiposClientesByCodigoISOOID(usuario.getIdioma()
						.getCodigoISO());
		Iterator it = list.iterator();
		while (it.hasNext()) {
			BaseOID base = (BaseOID) it.next();
			if (oidTipoCliente.equals("" + base.getOid()))
				return base.getDescripcion();
		}
		return "";
	}

	/**
	 * Obtiene la descripcion del tipo cliente
	 * 
	 * @param request
	 * @param oidTipoCliente
	 * @return
	 */
	private String getDesTipoclienteByCodigo(HttpServletRequest request,
			String codigoTipoCliente) {
		log.debug("getDesTipoclienteByCodigo codigoTipoCliente "
				+ codigoTipoCliente);
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		List list = (List) interfazSiCCService
				.getTiposClientesByCodigoISO(usuario.getIdioma().getCodigoISO());
		Iterator it = list.iterator();
		while (it.hasNext()) {
			Base base = (Base) it.next();
			if (codigoTipoCliente.equals(base.getCodigo()))
				return base.getDescripcion();
		}
		return "";
	}

	/**
	 * obtiene la descripcion del
	 * 
	 * @param request
	 * @param oidTipoCliente
	 * @param oidSubTipoCliente
	 * @return
	 */
	private String getDesSubtipoCliente(String oidTipoCliente,
			String oidSubTipoCliente) {
		// Usuario usuario = getUsuario(request);
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Map criteria = new HashMap();
		criteria.put("oidTipoCliente", oidTipoCliente);
		criteria.put("oidSubTipoCliente", oidSubTipoCliente);
		List list = (List) interfazSiCCService
				.getSubTiposClientesByCriteria(criteria);
		Base base = (Base) list.get(0);
		return base.getDescripcion();

	}

	/**
	 * Obtien la descripcion del tipo de clasificacion
	 * 
	 * @param request
	 * @param oidTipoCliente
	 * @param oidSubTipoCliente
	 * @param oidTipoClasificacion
	 * @return
	 */
	private String getDesTipoClasificacion(String oidTipoCliente,
			String oidSubTipoCliente, String oidTipoClasificacion) {
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Map criteria = new HashMap();
		criteria.put("oidTipoCliente", oidTipoCliente);
		criteria.put("oidSubTipoCliente", oidSubTipoCliente);
		criteria.put("oidTipoClasificacion", oidTipoClasificacion);
		criteria.put("codigoISO", usuario.getIdioma().getCodigoISO());
		Base base = null;
		if (StringUtils.isNotEmpty(oidTipoClasificacion)) {
			List list = (List) interfazSiCCService
					.getTiposClasificacionesByCriteria(criteria);
			base = (Base) list.get(0);
			return base.getDescripcion();
		}
		return "";
	}

	/**
	 * Obtine la descripcion de clasificacion
	 * 
	 * @param request
	 * @param oidTipoCliente
	 * @param oidSubTipoCliente
	 * @param oidTipoClasificacion
	 * @param oidClasificacion
	 * @return
	 */
	private String getClasificacion(String oidTipoCliente,
			String oidSubTipoCliente, String oidTipoClasificacion,
			String oidClasificacion) {

		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Map criteria = new HashMap();
		criteria.put("oidTipoCliente", oidTipoCliente);
		criteria.put("oidSubTipoCliente", oidSubTipoCliente);
		criteria.put("oidTipoClasificacion", oidTipoClasificacion);
		criteria.put("oidClasificacion", oidClasificacion);
		criteria.put("codigoISO", usuario.getIdioma().getCodigoISO());
		Base base = null;
		if (StringUtils.isNotEmpty(oidTipoClasificacion)
				&& StringUtils.isNotEmpty(oidClasificacion)) {
			List list = (List) interfazSiCCService
					.getClasificacionesByCriteria(criteria);
			base = (Base) list.get(0);
			return base.getDescripcion();
		}
		return "";

	}

	/**
	 * devuelve la descripcion del territorio
	 * 
	 * @param request
	 * @param codigoRegion
	 * @param codigoZona
	 * @param codigoSeccion
	 * @param codigoTerritorio
	 * @return
	 */
	private String getDesTerr(String codigoRegion, String codigoZona,
			String codigoSeccion, String codigoTerritorio) {
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		MantenimientoMENGenericoService service = (MantenimientoMENGenericoService) getBean("spusicc.mantenimientoMENGenericoService");
		Map criteria = new HashMap();
		criteria.put("codigoRegion", codigoRegion);
		criteria.put("codigoZona", codigoZona);
		criteria.put("codigoSeccion", codigoSeccion);
		criteria.put("codigoTerritorio", codigoTerritorio);
		criteria.put("codigoISO", usuario.getIdioma().getCodigoISO());
		if (StringUtils.isNotEmpty(codigoRegion)
				&& StringUtils.isNotEmpty(codigoZona)
				&& StringUtils.isNotEmpty(codigoSeccion)
				&& StringUtils.isNotEmpty(codigoTerritorio)) {
			List list = (List) service.getTerritorio(criteria);
			Map map = (Map) list.get(0);
			String descripcion = String.valueOf(map.get("descripcion"));
			return descripcion;
		}
		return "";
	}

	/**
	 * devulve la descripcin de la seccion
	 * 
	 * @param request
	 * @param codigoRegion
	 * @param codigoZona
	 * @param codigoSeccion
	 * @return
	 */
	private String getDesSeccion(String codigoRegion, String codigoZona,
			String codigoSeccion) {
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		MantenimientoMENGenericoService service = (MantenimientoMENGenericoService) getBean("spusicc.mantenimientoMENGenericoService");
		Map criteria = new HashMap();
		criteria.put("codigoRegion", codigoRegion);
		criteria.put("codigoZona", codigoZona);
		criteria.put("codigoSeccion", codigoSeccion);
		criteria.put("codigoISO", usuario.getIdioma().getCodigoISO());
		if (StringUtils.isNotEmpty(codigoRegion)
				&& StringUtils.isNotEmpty(codigoZona)
				&& StringUtils.isNotEmpty(codigoSeccion)) {
			List list = (List) service.getSeccion(criteria);
			Map map = (Map) list.get(0);
			String descripcion = String.valueOf(map.get("descripcion"));
			return descripcion;
		}
		return "";
	}

	/**
	 * devuleve la descripcion d ela zona
	 * 
	 * @param request
	 * @param codigoRegion
	 * @param codigoZona
	 * @return
	 */
	private String getDesZona(String codigoRegion, String codigoZona) {
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		MantenimientoMENGenericoService service = (MantenimientoMENGenericoService) getBean("spusicc.mantenimientoMENGenericoService");
		Map criteria = new HashMap();
		criteria.put("codigoRegion", codigoRegion);
		criteria.put("codigoZona", codigoZona);
		criteria.put("codigoISO", usuario.getIdioma().getCodigoISO());
		if (StringUtils.isNotEmpty(codigoRegion)
				&& StringUtils.isNotEmpty(codigoZona)) {
			List list = (List) service.getZona(criteria);
			Map map = (Map) list.get(0);
			String descripcion = String.valueOf(map.get("descripcion"));
			return descripcion;
		}
		return "";
	}

	/**
	 * devuleve la decripcion de la region
	 * 
	 * @param request
	 * @param codigoRegion
	 * @return
	 */
	private String getDesRegion(String codigoRegion) {
		List listRegion = this.siccRegionList;
		Iterator it = listRegion.iterator();
		while (it.hasNext()) {
			Base base = (Base) it.next();
			if (StringUtils.equals(codigoRegion, base.getCodigo()))
				return base.getDescripcion();
		}
		return "";
	}

	/**
	 * devuelve la decripcion de la capacitadora
	 * 
	 * @param request
	 * @param codigoCapacitadora
	 * @return
	 */
	private String getDesCapacitadora(String codigoCapacitadora) {
		List listRegion = this.siccCapacitadoraList;
		Iterator it = listRegion.iterator();
		while (it.hasNext()) {
			Base base = (Base) it.next();
			if (StringUtils.equals(codigoCapacitadora, base.getCodigo()))
				return base.getDescripcion();
		}
		return "";
	}

	/**
	 * Obtiene la descripcion del tipo cliente
	 * 
	 * @param request
	 * @param oidTipoCliente
	 * @return
	 */
	private String getCodigoTipocliente(String oidTipoCliente) {
		log.debug("getCodigoTipocliente oidTipoCliente " + oidTipoCliente);
		String descp = getDesTipocliente(oidTipoCliente);
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		List list = (List) interfazSiCCService
				.getTiposClientesByCodigoISO(usuario.getIdioma().getCodigoISO());
		Iterator it = list.iterator();
		while (it.hasNext()) {
			Base base = (Base) it.next();
			if (StringUtils.equalsIgnoreCase(descp, base.getDescripcion()))
				return base.getCodigo();
		}
		return "";
	}

	/**
	 * obtiene la descripcion del
	 * 
	 * @param request
	 * @param oidTipoCliente
	 * @param oidSubTipoCliente
	 * @return
	 */
	private String getCodigoSubtipoCliente(String oidTipoCliente,
			String oidSubTipoCliente) {
		// Usuario usuario = getUsuario(request);
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Map criteria = new HashMap();
		criteria.put("oidTipoCliente", oidTipoCliente);
		criteria.put("oidSubTipoCliente", oidSubTipoCliente);
		List list = (List) interfazSiCCService
				.getSubTiposClientesByCriteria(criteria);
		Base base = (Base) list.get(0);
		return base.getCodigo();

	}

	/**
	 * Obtien la descripcion del tipo de clasificacion
	 * 
	 * @param request
	 * @param oidTipoCliente
	 * @param oidSubTipoCliente
	 * @param oidTipoClasificacion
	 * @return
	 */
	private String getCodigoTipoClasificacion(String oidTipoCliente,
			String oidSubTipoCliente, String oidTipoClasificacion) {
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Map criteria = new HashMap();
		criteria.put("oidTipoCliente", oidTipoCliente);
		criteria.put("oidSubTipoCliente", oidSubTipoCliente);
		criteria.put("oidTipoClasificacion", oidTipoClasificacion);
		criteria.put("codigoISO", usuario.getIdioma().getCodigoISO());
		Base base = null;
		if (StringUtils.isNotEmpty(oidTipoClasificacion)) {
			List list = (List) interfazSiCCService
					.getTiposClasificacionesByCriteria(criteria);
			base = (Base) list.get(0);
			return base.getCodigo();
		}
		return "";
	}

	/**
	 * Obtine la descripcion de clasificacion
	 * 
	 * @param request
	 * @param oidTipoCliente
	 * @param oidSubTipoCliente
	 * @param oidTipoClasificacion
	 * @param oidClasificacion
	 * @return
	 */
	private String getCodigoClasificacion(String oidTipoCliente,
			String oidSubTipoCliente, String oidTipoClasificacion,
			String oidClasificacion) {

		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Map criteria = new HashMap();
		criteria.put("oidTipoCliente", oidTipoCliente);
		criteria.put("oidSubTipoCliente", oidSubTipoCliente);
		criteria.put("oidTipoClasificacion", oidTipoClasificacion);
		criteria.put("oidClasificacion", oidClasificacion);
		criteria.put("codigoISO", usuario.getIdioma().getCodigoISO());
		Base base = null;
		if (StringUtils.isNotEmpty(oidTipoClasificacion)
				&& StringUtils.isNotEmpty(oidClasificacion)) {
			List list = (List) interfazSiCCService
					.getClasificacionesByCriteria(criteria);
			base = (Base) list.get(0);
			return base.getCodigo();
		}
		return "";

	}

	private String obtenerOidPais(String codigoPais) {
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");

		Map crit = new HashMap();
		crit.put("codigoPais", codigoPais);

		String oidPais = clienteService.getOidPais(crit);

		return oidPais;
	}

	/**
	 * devuelve la decripcion de la unidad de negocio
	 * 
	 * @param request
	 * @param codigoRegion
	 * @return
	 */
	private String getDesUnidadNegocio(HttpServletRequest request,
			String codigoUnidadNegocio) {
		List listAux = (List) request.getSession().getAttribute(
				Constants.INC_UNIDAD_NEGOCIO_LIST);
		Iterator it = listAux.iterator();
		while (it.hasNext()) {
			Base base = (Base) it.next();
			if (StringUtils.equals(codigoUnidadNegocio, base.getCodigo()))
				return base.getDescripcion();
		}
		return "";
	}

	/**
	 * devuelve la decripcion de negocio
	 * 
	 * @param request
	 * @param codigoRegion
	 * @return
	 */
	private String getDesNegocio(HttpServletRequest request,
			String codigoNegocio) {
		List listAux = (List) request.getSession().getAttribute(
				Constants.INC_NEGOCIO_LIST);
		Iterator it = listAux.iterator();
		while (it.hasNext()) {
			Base base = (Base) it.next();
			if (StringUtils.equals(codigoNegocio, base.getCodigo()))
				return base.getDescripcion();
		}
		return "";
	}

	/**
	 * devuelve la decripcion de la marca producto
	 * 
	 * @param request
	 * @param codigoRegion
	 * @return
	 */
	private String getDesMarcaProducto(HttpServletRequest request,
			String codigoMarcaProducto) {
		List listAux = (List) request.getSession().getAttribute(
				Constants.INC_MARCA_PRODUCTO_LIST);
		Iterator it = listAux.iterator();
		while (it.hasNext()) {
			Base base = (Base) it.next();
			if (StringUtils.equals(codigoMarcaProducto, base.getCodigo()))
				return base.getDescripcion();
		}
		return "";
	}

	/**
	 * devuelve la decripcion de catalogo
	 * 
	 * @param request
	 * @param codigoRegion
	 * @return
	 */
	private String getDesCatalogo(HttpServletRequest request,
			String codigoCatalogo) {
		List listAux = (List) request.getSession().getAttribute(
				Constants.REC_CODIGO_CATALOGO_LIST);
		Iterator it = listAux.iterator();
		while (it.hasNext()) {
			Base base = (Base) it.next();
			if (StringUtils.equals(codigoCatalogo, base.getCodigo()))
				return base.getDescripcion();
		}
		return "";
	}

	private String getDesEstado(String codigoEstado, List listEstado) {
		Iterator it = listEstado.iterator();
		while (it.hasNext()) {
			Base base = (Base) it.next();
			String codigo = base.getCodigo();
			if (StringUtils.equals(codigo, codigoEstado)) {
				return base.getDescripcion();
			}
		}
		return "";
	}

	/**
	 * @param request
	 * @param f
	 * @return
	 */
	private List obtenerListaUA() {
		MantenimientoFACDeshabilitarZonasEnvioBoletasPremioForm f = (MantenimientoFACDeshabilitarZonasEnvioBoletasPremioForm) this.formBusqueda;
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		List listaUA = new ArrayList();
		String codigoPais = f.getCodigoPais();

		Map mapRegion = null;
		Map mapZona = null;
		Map mapSeccion = null;
		Map mapTerritorio = null;

		/* En caso sea Capacitadora */
		String indicadorTipoSeleccionCapacitadora = f
				.getIndicadorTipoSeleccionCapacitadora();
		if (StringUtils.equals(indicadorTipoSeleccionCapacitadora,
				Constants.INDICADOR_TIPO_SELECCION_CAPACITADORA_CAPACITADORA)) {
			String[] listaCapacitadora = f.getCapacitadoraListMultiple();
			if (listaCapacitadora == null)
				return listaUA;
			if (listaCapacitadora.length == 1
					&& listaCapacitadora[0].equals("Todos")) {
				List regiones = siccCapacitadoraList;

				for (int i = 0; i < regiones.size(); i++) {
					Base baseRegion = (Base) regiones.get(i);
					mapRegion = obtenerMapUACapacitadora(
							baseRegion.getCodigo(), "", "", "",
							baseRegion.getDescripcion(), "", "", "",
							f.getNumeroUnidadesPopup());
					listaUA.add(mapRegion);
				}
				return listaUA;
			}

			for (int i = 0; i < listaCapacitadora.length; i++) {
				mapRegion = obtenerMapUACapacitadora(listaCapacitadora[i], "",
						"", "", getDesCapacitadora(listaCapacitadora[i]), "",
						"", "", f.getNumeroUnidadesPopup());
				listaUA.add(mapRegion);
			}
			return listaUA;

		}

		// REGIONES CON ZONAS<TODOS>
		String[] listaRegion = f.getRegionListMultiple();
		if (listaRegion == null)
			return listaUA;
		if (f.getRegionListMultiple().length == 1
				&& f.getRegionListMultiple()[0].equals("Todos")) {
			List regiones = this.siccRegionList;

			for (int i = 0; i < regiones.size(); i++) {
				Base baseRegion = (Base) regiones.get(i);
				mapRegion = obtenerMapUA(baseRegion.getCodigo(), "", "", "",
						baseRegion.getDescripcion(), "", "", "",
						f.getNumeroUnidadesPopup());
				listaUA.add(mapRegion);
			}

		} else {
			for (int i = 0; i < f.getRegionListMultiple().length; i++) {
				ArrayList aRegionListMultiple = new ArrayList();
				for (int j = 0; j < f.getRegionListMultiple().length; j++) {
					aRegionListMultiple.add(f.getRegionListMultiple()[j]);
				}

				boolean encontradoRegion = false;

				if (f.getZonaListMultiple() != null) {
					LabelValue[] zonas = ajaxService
							.getZonasMultipleByPaisMarcaCanalRegion(
									codigoPais,
									Constants.CODIGO_MARCA_DEFAULT,
									Constants.CODIGO_CANAL_DEFAULT,
									new String[] { f.getRegionListMultiple()[i] },
									"U");

					String codigoZonaPrimero = f.getZonaListMultiple()[0];
					if (f.getZonaListMultiple().length == 1
							&& StringUtils.equals(codigoZonaPrimero, "T")) {
						for (int j = 0; j < zonas.length; j++) {
							mapZona = obtenerMapUA(
									f.getRegionListMultiple()[i],
									zonas[j].getValue(), "", "",
									getDesRegion(f.getRegionListMultiple()[i]),
									zonas[j].getLabel(), "", "",
									f.getNumeroUnidadesPopup());
							encontradoRegion = true;
							listaUA.add(mapZona);
						}
					} else {

						for (int j = 0; j < f.getZonaListMultiple().length; j++) {
							boolean perteneceRegion = false;
							for (int k = 0; k < zonas.length; k++) {
								if (f.getZonaListMultiple()[j].equals(zonas[k]
										.getValue())) {
									perteneceRegion = true;
									encontradoRegion = true;
									break;
								}
							}
							if (!perteneceRegion) {
								continue;
							}

							ArrayList aZonaList = new ArrayList();
							aZonaList.add(f.getZonaListMultiple()[j]);
							boolean encontradoZona = false;

							if (f.getSeccionListMultiple() != null) {
								LabelValue[] secciones = ajaxService
										.getSeccionMultiple2ByPaisMarcaCanalRegionZona(
												codigoPais,
												Constants.CODIGO_MARCA_DEFAULT,
												Constants.CODIGO_CANAL_DEFAULT,
												aRegionListMultiple, aZonaList,
												"U");

								if (f.getSeccionListMultiple().length == 1
										&& f.getSeccionListMultiple()[0]
												.equals("Todos")) {
									for (int k = 0; k < secciones.length; k++) {
										mapSeccion = obtenerMapUA(
												f.getRegionListMultiple()[i],
												f.getZonaListMultiple()[j],
												secciones[k].getValue().split(
														"__")[1],
												"",
												getDesRegion(f
														.getRegionListMultiple()[i]),
												getDesZona(
														f.getRegionListMultiple()[i],
														f.getZonaListMultiple()[j]),
												secciones[k].getLabel(), "",
												f.getNumeroUnidadesPopup());
										encontradoZona = true;
										listaUA.add(mapSeccion);
									}
								} else {
									for (int k = 0; k < f
											.getSeccionListMultiple().length; k++) {
										boolean perteneceZona = false;
										for (int l = 0; l < secciones.length; l++) {
											if (f.getSeccionListMultiple()[k]
													.equals(secciones[l]
															.getValue())) {
												perteneceZona = true;
												encontradoZona = true;
												break;
											}
										}
										if (!perteneceZona) {
											continue;
										}

										ArrayList aSeccionList = new ArrayList();
										aSeccionList.add(f
												.getSeccionListMultiple()[k]);
										String codigoSeccion = f
												.getSeccionListMultiple()[k]
												.split("__")[1];
										boolean encontradoSeccion = false;

										if (f.getTerritorioListMultiple() != null) {
											LabelValue[] territorios = ajaxService
													.getTerritoriosMultiple2ByPaisMarcaCanalRegionZonaSeccion(
															codigoPais,
															Constants.CODIGO_MARCA_DEFAULT,
															Constants.CODIGO_CANAL_DEFAULT,
															aRegionListMultiple,
															aZonaList,
															aSeccionList, "U");

											if (f.getTerritorioListMultiple().length == 1
													&& f.getTerritorioListMultiple()[0]
															.equals("Todos")) {
												for (int l = 0; l < territorios.length; l++) {
													mapTerritorio = obtenerMapUA(
															f.getRegionListMultiple()[i],
															f.getZonaListMultiple()[j],
															codigoSeccion,
															territorios[l]
																	.getValue(),
															getDesRegion(f
																	.getRegionListMultiple()[i]),
															getDesZona(
																	f.getRegionListMultiple()[i],
																	f.getZonaListMultiple()[j]),
															getDesSeccion(
																	f.getRegionListMultiple()[i],
																	f.getZonaListMultiple()[j],
																	codigoSeccion),
															territorios[l]
																	.getLabel(),
															f.getNumeroUnidadesPopup());
													encontradoSeccion = true;
													listaUA.add(mapTerritorio);
												}
											} else {
												for (int l = 0; l < f
														.getTerritorioListMultiple().length; l++) {
													boolean perteneceSeccion = false;
													for (int m = 0; m < territorios.length; m++) {
														if (f.getTerritorioListMultiple()[l]
																.equals(territorios[m]
																		.getValue())) {
															perteneceSeccion = true;
															encontradoSeccion = true;
															break;
														}
													}
													if (!perteneceSeccion) {
														continue;
													}

													mapTerritorio = obtenerMapUA(
															f.getRegionListMultiple()[i],
															f.getZonaListMultiple()[j],
															codigoSeccion,
															f.getTerritorioListMultiple()[l],
															getDesRegion(f
																	.getRegionListMultiple()[i]),
															getDesZona(
																	f.getRegionListMultiple()[i],
																	f.getZonaListMultiple()[j]),
															getDesSeccion(
																	f.getRegionListMultiple()[i],
																	f.getZonaListMultiple()[j],
																	codigoSeccion),
															getDesTerr(
																	f.getRegionListMultiple()[i],
																	f.getZonaListMultiple()[j],
																	codigoSeccion,
																	f.getTerritorioListMultiple()[l]),
															f.getNumeroUnidadesPopup());
													listaUA.add(mapTerritorio);
												}

											}
										}

										if (!encontradoSeccion) {
											mapSeccion = obtenerMapUA(
													f.getRegionListMultiple()[i],
													f.getZonaListMultiple()[j],
													codigoSeccion,
													"",
													getDesRegion(f
															.getRegionListMultiple()[i]),
													getDesZona(
															f.getRegionListMultiple()[i],
															f.getZonaListMultiple()[j]),
													getDesSeccion(
															f.getRegionListMultiple()[i],
															f.getZonaListMultiple()[j],
															codigoSeccion), "",
													f.getNumeroUnidadesPopup());
											listaUA.add(mapSeccion);
										}

									}
								}
							}

							if (!encontradoZona) {
								mapZona = obtenerMapUA(
										f.getRegionListMultiple()[i],
										f.getZonaListMultiple()[j],
										"",
										"",
										getDesRegion(f.getRegionListMultiple()[i]),
										getDesZona(
												f.getRegionListMultiple()[i],
												f.getZonaListMultiple()[j]),
										"", "", f.getNumeroUnidadesPopup());
								listaUA.add(mapZona);
							}
						}
					}
				}

				if (!encontradoRegion) {
					mapRegion = obtenerMapUA(f.getRegionListMultiple()[i], "",
							"", "", getDesRegion(f.getRegionListMultiple()[i]),
							"", "", "", f.getNumeroUnidadesPopup());
					listaUA.add(mapRegion);
				}

			}
		}

		return listaUA;
	}

	private Map obtenerMapUA(String codigoRegion, String codigoZona,
			String codigoSeccion, String codigoTerritorio,
			String descripcionRegion, String descripcionZona,
			String descripcionSeccion, String descripcionTerritorio,
			String numeroUnidades) {
		Map map = new HashMap();
		String descripcionZonaTmp = descripcionZona;
		if (StringUtils.equals("Todos", descripcionZona)) {
			descripcionZonaTmp = "";
		}

		map.put("codigoCapacitadora", null);
		map.put("codigoRegion", codigoRegion);
		map.put("codigoZona", codigoZona);
		map.put("codigoSeccion", codigoSeccion);
		map.put("codigoTerritorio", codigoTerritorio);
		map.put("descripcionRegion", descripcionRegion);
		map.put("descripcionCapacitadora", null);
		map.put("descripcionZona", descripcionZonaTmp);
		map.put("descripcionSeccion", descripcionSeccion);
		map.put("descripcionTerritorio", descripcionTerritorio);
		map.put("numeroUnidades", numeroUnidades);
		map.put("indicadorAccion", Constants.MAV_ESTADO_TMP_INSERTAR);

		return map;
	}

	/**
	 * @param codigoCapacitadora
	 * @param codigoZona
	 * @param codigoSeccion
	 * @param codigoTerritorio
	 * @param descripcionRegion
	 * @param descripcionZona
	 * @param descripcionSeccion
	 * @param descripcionTerritorio
	 * @param numeroUnidades
	 * @return
	 */
	private Map obtenerMapUACapacitadora(String codigoCapacitadora,
			String codigoZona, String codigoSeccion, String codigoTerritorio,
			String descripcionCapacitadora, String descripcionZona,
			String descripcionSeccion, String descripcionTerritorio,
			String numeroUnidades) {
		Map map = new HashMap();
		String descripcionZonaTmp = descripcionZona;
		if (StringUtils.equals("Todos", descripcionZona)) {
			descripcionZonaTmp = "";
		}
		map.put("codigoCapacitadora", codigoCapacitadora);
		map.put("codigoRegion", null);
		map.put("codigoZona", codigoZona);
		map.put("codigoSeccion", codigoSeccion);
		map.put("codigoTerritorio", codigoTerritorio);
		map.put("descripcionRegion", null);
		map.put("descripcionCapacitadora", descripcionCapacitadora);
		map.put("descripcionZona", descripcionZona);
		map.put("descripcionSeccion", descripcionSeccion);
		map.put("descripcionTerritorio", descripcionTerritorio);
		map.put("numeroUnidades", numeroUnidades);
		map.put("indicadorAccion", Constants.MAV_ESTADO_TMP_INSERTAR);

		return map;
	}

	private void actualizarTotalUnidades(boolean esConsideracion) {
		List list = null;
		int totalUnidadesRegion = 0;
		int totalUnidadesZona = 0;
		int totalUnidadesCapacitadora = 0;

		int totalRegiones = 0;
		int totalZonas = 0;
		int totalCapacitadoras = 0;
		MantenimientoFACDeshabilitarZonasEnvioBoletasPremioForm f = (MantenimientoFACDeshabilitarZonasEnvioBoletasPremioForm) this.formBusqueda;
		if (esConsideracion)
			list = this.regionesList;
		else
			list = this.regionesListR;

		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				Map m = (Map) list.get(i);
				String codigoRegion = MapUtils.getString(m, "codigoRegion");
				String codigoZona = MapUtils.getString(m, "codigoZona");
				String codigoCapacitadora = MapUtils.getString(m,
						"codigoCapacitadora");
				String StrNro = MapUtils.getString(m, "numeroUnidades", "0");

				int numeroUnidades = 0;

				try {
					numeroUnidades = Integer.parseInt(StrNro);
				} catch (Exception ex) {
				}

				String indicadorAccion = (String) m.get("indicadorAccion");

				if (!Constants.NUMERO_DOS.equals(indicadorAccion)
						&& !Constants.MAV_ESTADO_TMP_ELIMINAR
								.equals(indicadorAccion)) {
					if (StringUtils.isNotBlank(codigoCapacitadora)) {
						totalUnidadesCapacitadora += numeroUnidades;
						totalCapacitadoras++;
					} else {
						if (StringUtils.isEmpty(codigoZona))
							totalUnidadesRegion = totalUnidadesRegion
									+ numeroUnidades;
						else
							totalUnidadesZona = totalUnidadesZona
									+ numeroUnidades;
					}

					if (StringUtils.isNotBlank(codigoRegion)
							&& StringUtils.isBlank(codigoZona)) {
						totalRegiones++;
					}

					if (StringUtils.isNotBlank(codigoRegion)
							&& StringUtils.isNotBlank(codigoZona)) {
						totalZonas++;
					}
				}
			}
		}

		f.setTotalUnidadesRegion(String.valueOf(totalUnidadesRegion));
		f.setTotalUnidadesZona(String.valueOf(totalUnidadesZona));
		f.setTotalUnidadesCapacitadora(String
				.valueOf(totalUnidadesCapacitadora));

		f.setTotalRegiones(String.valueOf(totalRegiones));
		f.setTotalZonas(String.valueOf(totalZonas));
		f.setTotalCapacitadoras(String.valueOf(totalCapacitadoras));
	}

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward refreshPopup(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'refreshPopup' method");
		}
		HttpSession session = request.getSession(true);
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		MantenimientoFACDeshabilitarZonasEnvioBoletasPremioForm f = (MantenimientoFACDeshabilitarZonasEnvioBoletasPremioForm) this.formBusqueda;
		MantenimientoMAVConfiguracionService service = (MantenimientoMAVConfiguracionService) getBean("spusicc.mantenimientoMAVConfiguracionService");

		String codigoConsRest = (String) request.getParameter("codConsRest");
		String indicadorTipo = (String) request.getParameter("indicadorTipo");

		session.setAttribute("indicadorCerrarVentana", Constants.NRO_CERO);
		session.setAttribute("codigoIdiomaISO", usuario.getIdioma()
				.getCodigoISO());

		int codigo = Integer.parseInt(codigoConsRest);
		String forward = "";
		List listC = null;

		switch (codigo) {
		case Constants.MAV_CONRES_LISTA_CONSU:
			listC = (List) session.getAttribute("consultoraList");
			session.setAttribute("consultoraList", listC);
			forward = Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo) ? "viewListaCliente"
					: "viewListaClienteRest";
			break;

		case Constants.MAV_CONRES_LISTA_REGION_ZONA:
			listC = (List) session.getAttribute("regionesList");
			if (Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo))
				actualizarTotalUnidades(true);
			else
				actualizarTotalUnidades(false);
			forward = Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo) ? "viewListaRegionZona"
					: "viewListaRegionZonaRest";
			break;

		case Constants.MAV_CONRES_CLASIFICACIONES_CLIENTE:
			listC = (List) session.getAttribute("clasificacionesList");
			session.setAttribute("clasificacionesList", listC);
			forward = Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo) ? "viewClasificacionesCliente"
					: "viewClasificacionesClienteRest";
			break;

		case Constants.MAV_CONRES_UNIDAD_ADM:
			listC = (List) session.getAttribute("unidadesList");
			session.setAttribute("unidadesList", listC);
			forward = Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo) ? "viewUnidadAdministrativa"
					: "viewUnidadAdministrativaRest";
			break;

		case Constants.MAV_CONRES_ESTATUS_CLIENTE:
			listC = (List) session.getAttribute("codigoEstatusList");
			session.setAttribute("codigoEstatusList", listC);
			forward = Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo) ? "viewEstatusCliente"
					: "viewEstatusClienteRest";
			break;

		case Constants.MAV_CONRES_LISTA_CONSU_REST:
			listC = (List) session.getAttribute("consultoraListR");
			session.setAttribute("consultoraListR", listC);
			forward = Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo) ? "viewListaCliente"
					: "viewListaClienteRest";
			break;

		case Constants.MAV_CONRES_LISTA_REGION_ZONA_REST:
			listC = (List) session.getAttribute("regionesListR");
			session.setAttribute("regionesListR", listC);
			if (Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo))
				actualizarTotalUnidades(true);
			else
				actualizarTotalUnidades(false);
			forward = Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo) ? "viewListaRegionZona"
					: "viewListaRegionZonaRest";
			break;

		case Constants.MAV_CONRES_CLASIFICACIONES_CLIENTE_REST:
			listC = (List) session.getAttribute("clasificacionesListR");
			session.setAttribute("clasificacionesListR", listC);
			forward = Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo) ? "viewClasificacionesCliente"
					: "viewClasificacionesClienteRest";
			break;

		case Constants.MAV_CONRES_UNIDAD_ADM_REST:
			listC = (List) session.getAttribute("unidadesListR");
			session.setAttribute("unidadesListR", listC);
			forward = Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo) ? "viewUnidadAdministrativa"
					: "viewUnidadAdministrativaRest";
			break;

		case Constants.MAV_CONRES_ESTATUS_CLIENTE_REST:
			listC = (List) session.getAttribute("codigoEstatusListR");
			session.setAttribute("codigoEstatusListR", listC);
			forward = Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo) ? "viewEstatusCliente"
					: "viewEstatusClienteRest";
			break;
		}
		log.debug("forward ss " + forward);
		return mapping.findForward(forward);
	}

	/**
	 * Actualiza Totales en Lista Region Zona
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward totalesRegionZona(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		String forward = "viewListaRegionZona";
		HttpSession session = request.getSession(true);
		String indicadorUnidadPopup = (String) session
				.getAttribute("indicadorUnidadPopup");
		MantenimientoFACDeshabilitarZonasEnvioBoletasPremioForm f = (MantenimientoFACDeshabilitarZonasEnvioBoletasPremioForm) this.formBusqueda;
		if (StringUtils.isNotBlank(indicadorUnidadPopup)) {
			f.setIndicadorUnidadPopup(indicadorUnidadPopup);
		}

		this.actualizarTotalUnidades(true);
		return mapping.findForward(forward);

	}

	/**
	 * Actualiza Totales en Lista Region Zona Restriccion
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward totalesRegionZonaRes(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String forward = "viewListaRegionZonaRest";
		HttpSession session = request.getSession(true);
		String indicadorUnidadPopup = (String) session
				.getAttribute("indicadorUnidadPopup");
		MantenimientoFACDeshabilitarZonasEnvioBoletasPremioForm f = (MantenimientoFACDeshabilitarZonasEnvioBoletasPremioForm) this.formBusqueda;
		if (StringUtils.isNotBlank(indicadorUnidadPopup)) {
			f.setIndicadorUnidadPopup(indicadorUnidadPopup);
		}

		this.actualizarTotalUnidades(false);
		return mapping.findForward(forward);

	}

	/**
	 * Carga la pantalla de eicion de unidades de Regiones, zonas y
	 * capacitadoras
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward editUnidadesListaRegionZona(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String forward = "viewEditUnidadesListaRegionZona";
		HttpSession session = request.getSession(true);
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoFACDeshabilitarZonasEnvioBoletasPremioForm f = (MantenimientoFACDeshabilitarZonasEnvioBoletasPremioForm) this.formBusqueda;
		GenericoService genericoService = (GenericoService) getBean("genericoService");
		MantenimientoMAVConfiguracionService service = (MantenimientoMAVConfiguracionService) getBean("spusicc.mantenimientoMAVConfiguracionService");
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");

		String id = request.getParameter("id");

		// Cargamos los valores de las listas.
		ParametroPais parametroPais = new ParametroPais();
		parametroPais.setCodigoPais(pais.getCodigo());
		parametroPais.setCodigoSistema(Constants.MAV_CODIGO_SISTEMA);
		parametroPais.setCodigoParametro(Constants.ITEM_INDICADOR_CAPACITADORA);
		parametroPais.setIndicadorActivo(Constants.ESTADO_ACTIVO);
		List lstParametros = genericoService.getParametrosPais(parametroPais);
		f.setIndicadorCapacitadora(Constants.NO);

		ParametroPais parametro = null;
		if (CollectionUtils.size(lstParametros) == 1) {
			parametro = (ParametroPais) lstParametros.get(0);
			String indicadorCapacitadora = parametro.getValorParametro();
			f.setIndicadorCapacitadora(indicadorCapacitadora);
		}

		List list = (List) session
				.getAttribute(Constants.MAV_CONFIGURACIONES_LIST);
		Map map = (Map) list.get(Integer.parseInt(id) - 1);

		log.debug("map " + map);

		Map criteria = new HashMap();
		criteria.put("codigoPais", map.get("codigoPais").toString());
		criteria.put("correlativoMAV", map.get("correlativo").toString());
		criteria.put("tipoConsideracion", Constants.MAV_TIPO_CONSIDERACION);
		criteria.put("indicadorTipo",
				Constants.MAV_TIPO_LISTA_EXTERNA_CONDICION);
		criteria.put("abreviatura", Constants.MAV_ABREVIATURA_LISTA_REGION_ZONA);

		List listConsi = service.getRestConsideracion(criteria);
		List listC = new ArrayList();

		if (listConsi != null && listConsi.size() > 0) {
			// Subimos a session las listas
			Map criteriaOperacion = new HashMap();
			criteriaOperacion.put("codigoPais", pais.getCodigo());
			criteriaOperacion.put("indicadorTipoRegion", Constants.SI);
			request.getSession().setAttribute(
					Constants.SICC_REGION_LIST,
					reporteService.getListaGenerico("getRegionesByPais",
							criteriaOperacion));
			request.getSession().setAttribute(
					Constants.SICC_CAPACITADORA_LIST,
					reporteService.getListaGenerico("getRegionesByPais",
							criteriaOperacion));
			//

			// Obtenemos el detalle de la consideracion
			Map mapConRes = (Map) listConsi.get(0);
			String codigoConsRest = (String) mapConRes
					.get("codigoConsideracion");

			int codigo = Integer.parseInt(codigoConsRest);
			criteria.put("correlativoMAV", map.get("correlativo").toString());
			criteria.put("codigoConsRest", codigoConsRest);
			criteria.put("indicadorTipo", Constants.MAV_TIPO_CONSIDERACION);

			List listDetalle = (List) service.getDetalleConsRestEnvio(criteria);
			MantenimientoMAVConfiguracionCondicionEnvioForm listaValores[] = new MantenimientoMAVConfiguracionCondicionEnvioForm[listDetalle
					.size()];

			for (int i = 0; i < listDetalle.size(); i++) {
				Map bean = (Map) listDetalle.get(i);

				String codigoRegion = MapUtils.getString(bean, "valCondi1", "");
				String codigoZona = MapUtils.getString(bean, "valCondi2", "");
				String codigoCapacitadora = MapUtils.getString(bean,
						"valCondi3", "");

				bean.put("codigoRegion", codigoRegion);
				bean.put("codigoZona", codigoZona);
				bean.put("codigoCapacitadora", codigoCapacitadora);
				bean.put("indicadorAccion", Constants.NRO_UNO);
				bean.put("descripcionRegion", getDesRegion(codigoRegion));
				bean.put("descripcionZona",
						getDesZona(codigoRegion, codigoZona));
				bean.put("descripcionCapacitadora",
						getDesCapacitadora(codigoCapacitadora));

				MantenimientoMAVConfiguracionCondicionEnvioForm registroForm = new MantenimientoMAVConfiguracionCondicionEnvioForm();
				BeanUtils.copyProperties(registroForm, bean);

				listaValores[i] = registroForm;
				listC.add(bean);
			}

			// f.setMantenimientoMAVConfiguracionCondicionEnvioForm(listaValores);
			session.setAttribute("regionesList", listC);
		}

		this.actualizarTotalUnidades(true);

		return mapping.findForward(forward);
	}

	/**
	 * Graba las unidades de Regiones, zonas y capacitadoras
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward updateUnidadesListaRegionZona(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String forward = "viewEditUnidadesListaRegionZona";
		HttpSession session = request.getSession(true);
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		ActionMessages messages = new ActionMessages();

		MantenimientoFACDeshabilitarZonasEnvioBoletasPremioForm f = (MantenimientoFACDeshabilitarZonasEnvioBoletasPremioForm) this.formBusqueda;
		MantenimientoMAVConfiguracionService service = (MantenimientoMAVConfiguracionService) getBean("spusicc.mantenimientoMAVConfiguracionService");

		// Grabamos los valores de las listas.
		// MantenimientoMAVConfiguracionCondicionEnvioForm []unidades =
		// f.getMantenimientoMAVConfiguracionCondicionEnvioForm();
		List regionesList = (List) session.getAttribute("regionesList");
		List listaUnidades = new ArrayList();
		/*
		 * for(int i=0; i<unidades.length; i++) {
		 * if(StringUtils.equals(unidades[i].getIndicadorEnvio(),
		 * Constants.MAV_CODIGO_INDICADOR_ENVIO_P)) {
		 * listaUnidades.add(BeanUtils.describe(unidades[i]));
		 * 
		 * Map r = (Map)regionesList.get(i); r.put("numeroUnidades",
		 * unidades[i].getNumeroUnidades());
		 * if(unidades[i].getIndicadorActivo()!= null){
		 * unidades[i].setIndicadorEnvio(unidades[i].getIndicadorActivo()); } }
		 * }
		 */

		service.updateConfiguracionUnidadesListaRegionZona(listaUnidades,
				usuario);
		// f.setMantenimientoMAVConfiguracionCondicionEnvioForm(unidades);
		this.actualizarTotalUnidades(true);
		String mensaje = this
				.getResourceMessage("mantenimientoMAVConfiguracionForm.updateUnidades");
		this.addError("Error : ", mensaje);

		return mapping.findForward(forward);
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
	 * @return the siccCapacitadoraList
	 */
	public List getSiccCapacitadoraList() {
		return siccCapacitadoraList;
	}

	/**
	 * @param siccCapacitadoraList
	 *            the siccCapacitadoraList to set
	 */
	public void setSiccCapacitadoraList(List siccCapacitadoraList) {
		this.siccCapacitadoraList = siccCapacitadoraList;
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
	 * @return the regionesList
	 */
	public List getRegionesList() {
		return regionesList;
	}

	/**
	 * @param regionesList
	 *            the regionesList to set
	 */
	public void setRegionesList(List regionesList) {
		this.regionesList = regionesList;
	}

	/**
	 * @return the regionesListR
	 */
	public List getRegionesListR() {
		return regionesListR;
	}

	/**
	 * @param regionesListR
	 *            the regionesListR to set
	 */
	public void setRegionesListR(List regionesListR) {
		this.regionesListR = regionesListR;
	}

	/**
	 * @return the idPopup
	 */
	public String getIdPopup() {
		return idPopup;
	}

	/**
	 * @param idPopup
	 *            the idPopup to set
	 */
	public void setIdPopup(String idPopup) {
		this.idPopup = idPopup;
	}

	/**
	 * @return the indicadorCerrarVentana
	 */
	public String getIndicadorCerrarVentana() {
		return indicadorCerrarVentana;
	}

	/**
	 * @param indicadorCerrarVentana
	 *            the indicadorCerrarVentana to set
	 */
	public void setIndicadorCerrarVentana(String indicadorCerrarVentana) {
		this.indicadorCerrarVentana = indicadorCerrarVentana;
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
	 * @return the beanMantenimientoFACDeshabilitarZonasEnvioBoletasPremio
	 */
	public Object[] getBeanMantenimientoFACDeshabilitarZonasEnvioBoletasPremio() {
		return beanMantenimientoFACDeshabilitarZonasEnvioBoletasPremio;
	}

	/**
	 * @param beanMantenimientoFACDeshabilitarZonasEnvioBoletasPremio
	 *            the beanMantenimientoFACDeshabilitarZonasEnvioBoletasPremio to
	 *            set
	 */
	public void setBeanMantenimientoFACDeshabilitarZonasEnvioBoletasPremio(
			Object[] beanMantenimientoFACDeshabilitarZonasEnvioBoletasPremio) {
		this.beanMantenimientoFACDeshabilitarZonasEnvioBoletasPremio = beanMantenimientoFACDeshabilitarZonasEnvioBoletasPremio;
	}

	/**
	 * @return the consultoraList
	 */
	public List getConsultoraList() {
		return consultoraList;
	}

	/**
	 * @param consultoraList
	 *            the consultoraList to set
	 */
	public void setConsultoraList(List consultoraList) {
		this.consultoraList = consultoraList;
	}

	/**
	 * @return the clasificacionesList
	 */
	public List getClasificacionesList() {
		return clasificacionesList;
	}

	/**
	 * @param clasificacionesList
	 *            the clasificacionesList to set
	 */
	public void setClasificacionesList(List clasificacionesList) {
		this.clasificacionesList = clasificacionesList;
	}

	/**
	 * @return the unidadesList
	 */
	public List getUnidadesList() {
		return unidadesList;
	}

	/**
	 * @param unidadesList
	 *            the unidadesList to set
	 */
	public void setUnidadesList(List unidadesList) {
		this.unidadesList = unidadesList;
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
	 * @return the codigoEstatusList
	 */
	public List getCodigoEstatusList() {
		return codigoEstatusList;
	}

	/**
	 * @param codigoEstatusList
	 *            the codigoEstatusList to set
	 */
	public void setCodigoEstatusList(List codigoEstatusList) {
		this.codigoEstatusList = codigoEstatusList;
	}

	/**
	 * @return the consultoraListR
	 */
	public List getConsultoraListR() {
		return consultoraListR;
	}

	/**
	 * @param consultoraListR
	 *            the consultoraListR to set
	 */
	public void setConsultoraListR(List consultoraListR) {
		this.consultoraListR = consultoraListR;
	}

	/**
	 * @return the clasificacionesListR
	 */
	public List getClasificacionesListR() {
		return clasificacionesListR;
	}

	/**
	 * @param clasificacionesListR
	 *            the clasificacionesListR to set
	 */
	public void setClasificacionesListR(List clasificacionesListR) {
		this.clasificacionesListR = clasificacionesListR;
	}

	/**
	 * @return the unidadesListR
	 */
	public List getUnidadesListR() {
		return unidadesListR;
	}

	/**
	 * @param unidadesListR
	 *            the unidadesListR to set
	 */
	public void setUnidadesListR(List unidadesListR) {
		this.unidadesListR = unidadesListR;
	}

	/**
	 * @return the codigoEstatusListR
	 */
	public List getCodigoEstatusListR() {
		return codigoEstatusListR;
	}

	/**
	 * @param codigoEstatusListR
	 *            the codigoEstatusListR to set
	 */
	public void setCodigoEstatusListR(List codigoEstatusListR) {
		this.codigoEstatusListR = codigoEstatusListR;
	}

	/**
	 * @return the insertar
	 */
	public String getInsertar() {
		return insertar;
	}

	/**
	 * @param insertar
	 *            the insertar to set
	 */
	public void setInsertar(String insertar) {
		this.insertar = insertar;
	}

	/**
	 * @return the codConsRest
	 */
	public String getCodConsRest() {
		return codConsRest;
	}

	/**
	 * @param codConsRest
	 *            the codConsRest to set
	 */
	public void setCodConsRest(String codConsRest) {
		this.codConsRest = codConsRest;
	}

	/**
	 * @return the indicadorTipo
	 */
	public String getIndicadorTipo() {
		return indicadorTipo;
	}

	/**
	 * @param indicadorTipo
	 *            the indicadorTipo to set
	 */
	public void setIndicadorTipo(String indicadorTipo) {
		this.indicadorTipo = indicadorTipo;
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
	 * @return the mostrarPaneles
	 */
	public Boolean getMostrarPaneles() {
		return mostrarPaneles;
	}

	/**
	 * @param mostrarPaneles
	 *            the mostrarPaneles to set
	 */
	public void setMostrarPaneles(Boolean mostrarPaneles) {
		this.mostrarPaneles = mostrarPaneles;
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoFACDeshabilitarZonasEnvioBoletasPremioForm form = new MantenimientoFACDeshabilitarZonasEnvioBoletasPremioForm();
		return form;
	}

	/**
	 * @return the indicadorAccion
	 */
	public String getIndicadorAccion() {
		return indicadorAccion;
	}

	/**
	 * @param indicadorAccion
	 *            the indicadorAccion to set
	 */
	public void setIndicadorAccion(String indicadorAccion) {
		this.indicadorAccion = indicadorAccion;
	}
}