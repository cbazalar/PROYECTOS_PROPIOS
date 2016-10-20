package biz.belcorp.ssicc.web.spusicc.men.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.LabelValueCUV;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.sisicc.model.BaseOID;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.service.spusicc.men.MantenimientoMENGenericoService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.zon.ProcesoZONActualizarUnidadesGeograficasService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.men.form.MantenimientoMENPatronMensajeForm;
import biz.belcorp.ssicc.web.spusicc.men.form.MantenimientoMENPatronMensajeSearchForm;

@SessionScoped
@ManagedBean
public class MantenimientoMENPatronMensajeSearchAction extends
		BaseMantenimientoSearchAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8176801984296107772L;
	private static final String CODIGO_PATRON_FLYERS = "fl";
	
	private String codigoPais;
	private String codigoConsRestr;
	private String path;
	private String pathRutaImgJava;
	private String pathRutaImgOracle;
	private String indexPatronMensaje;
	private String campanhaActual;
	private Usuario usuario;
	private AjaxService ajax;
	private MantenimientoMENGenericoService service;
	private Object beanSeleccionadoMensajePatron;
	private List msgMensajeDocumentoList;
	private List msgPatronMensajeList;
	private List msgMensajePatronList;
	private List msgSeccionList;
	private List msgModuloList;
	private List codigoVentaList;
	private List codigoCuvFaltanteList;
	private List codigoPremioList;
	private List codigoVentaReemplazoList;
	private List consultoraList;
	private List buzonLoteList;
	private List codigoEstatusList;
	private List clasificacionesList;
	private List unidadesList;
	private List SACestadosList;
	private List siccTipoClienteList;
	private List siccRegionList;
	private LabelValue[] siccZonaList = {};
	private LabelValue[] siccSeccionList = {};
	private LabelValue[] siccTerritorioList = {};
	private List unidadesListR;
	private List clasificacionesListR;
	private List consultoraListR;
	private List buzonLoteListR;
	private List codigoEstatusListR;
	private List codigoPremioListR;
	private List codigoVentaListR;
	private List codigoVentaReemplazoListR;
	private LabelValue[] siccClasificacionList = {};
	private LabelValue[] siccTipoClasificacionList = {};
	private LabelValue[] siccSubTipoClienteList = {};
	private LabelValue[] msgConsideracionList = {};
	private LabelValue[] msgRestriccionList = {};
	private List msgPatronConsideracionList;
	private DataTableModel dataTablePatronConsideracion;
	private List msgPatronRestriccionList;
	private DataTableModel dataTableModelMensaje;
	private DataTableModel comDetalleClasificacion;
	private DataTableModel comDetalleUnidad;
	private DataTableModel comDetalleCodigoVenta;
	private DataTableModel comDetalleCodigoCuvFaltante;
	private DataTableModel comDetalleEstatus;
	private DataTableModel comDetalleListaConsultora;
	private Object beanRegistroPatronConsideracion;
	private Object beanRegistroClasificacion;
	private Object beanRegistroUnidad;
	private Object beanRegistroCodigoVenta;
	private Object beanRegistroCodigoCuvFaltante;
	private Object beanRegistroEstatus;
	private Object beanRegistroListaConsultora;
	// CAMPOS PARA HABILITAR Y DESHABILITAR
	private boolean bloqueBuscarDisabled;
	private boolean bloqueNuevoMensaje;
	private boolean botonBuscar;
	private boolean botonNuevoMensaje;
	private boolean botonGrabarMensaje;
	private boolean botonLimpiar;
	private boolean codigoMensajeDisabled;
	private boolean indicadorManualBoolean;
	private boolean flagEditar;
	private boolean botonEdit;
	private boolean descripcionMensajeBoolean;
	private boolean indicadorActivoBoolean;
	private boolean descripcionPatronBoolean;
	private boolean consResBoolean;
	private boolean montoCatalogoBoolean;
	private String camposRequeridos;
	private int cantidadListaCodigoVenta;
	private final static String CODIGO_MODULO_MENSAJE = "6";
	private String nombreImagenFondo = "";
	

	@ManagedProperty(value = "#{mantenimientoMENPatronMensajeConsultaAction}")
	private MantenimientoMENPatronMensajeConsultaAction consultaAction;
	@ManagedProperty(value = "#{mantenimientoMENPatronMensajeOrdenaAction}")
	private MantenimientoMENPatronMensajeOrdenaAction ordenaAction;
	@ManagedProperty(value = "#{mantenimientoMENPatronMensajeReplicaAction}")
	private MantenimientoMENPatronMensajeReplicaAction replicaAction;
	
	private String attachment = "";
	
	private List msgMensajeDocumentoFlyersList;
	private List msgMensajeBandejaFlyersList;
	private String campanhaProcesoFlyers;
	private String codigoDocumentoFlyers;
	private String descripcionPatronFlyers;
	private String codigoBandejaFlyers;
	private boolean esRegistroNuevoFlyers = true;
	private String oidPatronFlyers;

	

	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return "mantenimientoMENPatronMensajeList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return "mantenimientoMENPatronMensajeForm";
	}

	public void salirpagina(ActionEvent event) throws IOException {
		this.redireccionarPagina("mantenimientoMENPatronMensajeList");
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoMENPatronMensajeSearchForm form = new MantenimientoMENPatronMensajeSearchForm();
		return form;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoMENPatronMensajeSearchForm f = (MantenimientoMENPatronMensajeSearchForm) this.formBusqueda;
		this.msgPatronMensajeList = findList(f);
		return this.msgPatronMensajeList;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		boolean bEliminar = true;
		MantenimientoMENPatronMensajeSearchForm f = (MantenimientoMENPatronMensajeSearchForm) this.formBusqueda;
		Map map = (Map) this.beanRegistroSeleccionado;
		int campanhaPatron = Integer.parseInt(map.get("campanhaProceso")
				.toString());
		int campanhaActual = Integer.parseInt(f.getCampanhaActual());
		if (campanhaPatron <= campanhaActual) {
			addError(
					"Error",
					this.getResourceMessage("mantenimientoMENPatronMensajeForm.campanhaProceso.information"));
			bEliminar = false;
		} else {
			map.put("login", usuario.getLogin());
			map.put("codigoPais", codigoPais);
			service.deletePatronMensaje(map);
		}
		return bEliminar;

	}
	
	/**
	 * Realiza las validaciones para luego ingresar a la pantalla de Crear Flyers
	 * @param actionEvent
	 */
	public void ingresarFlyers(ActionEvent actionEvent) {
		if (log.isWarnEnabled()) {
			log.warn("Entering 'ingresarFlyers' method");
		}
		try {	
			MantenimientoMENPatronMensajeSearchForm f = (MantenimientoMENPatronMensajeSearchForm) this.formBusqueda;
			String codigoPeriodo = f.getCampanhaProceso();
			if (StringUtils.isBlank(codigoPeriodo)) {
				this.addWarn("Mensaje", this.getResourceMessage("mantenimientoMENPatronMensajeForm.campanhaProceso.errorFlyers"));
				return;
			}
			this.obtenerFormularioFlyersNuevo(codigoPeriodo);
			this.getRequestContext().execute("PF('dialogFlyers').show()");
			return ;
		}
		catch(Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
		
		if (log.isWarnEnabled()) {
			log.warn("Finish 'ingresarFlyers' method");
		}
	}
	
	/**
	 * Obtiene datos para Crear Nuevo Flyers
	 * @param codigoPeriodo
	 */
	private void obtenerFormularioFlyersNuevo(String codigoPeriodo) {
		this.campanhaProcesoFlyers = codigoPeriodo;
		this.codigoDocumentoFlyers = "";
		this.codigoBandejaFlyers = "";
		this.descripcionPatronFlyers = "";
		this.oidPatronFlyers = "";
		this.esRegistroNuevoFlyers = true; 
		
				
		this.msgMensajeBandejaFlyersList = new ArrayList();
		this.msgMensajeDocumentoFlyersList = new ArrayList();
		
		Map hmap = new HashMap();
		hmap.put("codigoPais", codigoPais);
		hmap.put("abreviatura", CODIGO_PATRON_FLYERS);
		this.msgMensajeDocumentoFlyersList = service.getDocumentosMensaje(hmap);
		this.msgMensajeBandejaFlyersList = service.getListaBandejaFlyers(hmap);
		
	}

	/**
	 * @param codigoPeriodo
	 */
	private void obtenerFormularioFlyersEdit(String oidPatron, String codigoPeriodo) {
		this.campanhaProcesoFlyers = codigoPeriodo;
		this.codigoDocumentoFlyers = "";
		this.codigoBandejaFlyers = "";
		this.descripcionPatronFlyers = "";
		this.oidPatronFlyers = "";
		this.esRegistroNuevoFlyers = true; 
		
		// OBTENIENDO OID pAIS
		// Asignamos al codigo del periodo el valor por defecto
		Map criteria = new HashMap();
		criteria.put("codigoPais", this.mPantallaPrincipalBean.getCurrentCountry().getCodigo());
		criteria.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		criteria.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
		criteria.put("codigoISO", usuario.getIdioma().getCodigoISO());

		// recuperamos el oid Pais
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		String oidPais = clienteService.getOidPais(criteria);
		
		criteria.put("oidPais", oidPais);
		criteria.put("campanhaProceso", codigoPeriodo);
		criteria.put("oidPatron", oidPatron);
		criteria.put("codigoPatron", CODIGO_PATRON_FLYERS);
		List lista = service.getPatronMensaje(criteria);
		
		if (lista != null && lista.size() > 0) {
			Map beanFlyers = (Map)lista.get(0);
			BigDecimal bcodigoDocumentoFlyers = (BigDecimal)beanFlyers.get("codigoDocumento");
			this.codigoDocumentoFlyers = bcodigoDocumentoFlyers.toString().trim();
			this.codigoBandejaFlyers = (String)beanFlyers.get("nombreBandejaFlyers");
			this.descripcionPatronFlyers = (String)beanFlyers.get("descripcionPatron");
			BigDecimal boidPatronFlyers = (BigDecimal)beanFlyers.get("oidPatron"); 
			this.oidPatronFlyers = boidPatronFlyers.toString().trim();
			this.esRegistroNuevoFlyers = false; 
		}
		
		this.msgMensajeBandejaFlyersList = new ArrayList();
		this.msgMensajeDocumentoFlyersList = new ArrayList();
		
		Map hmap = new HashMap();
		hmap.put("codigoPais", codigoPais);
		hmap.put("abreviatura", CODIGO_PATRON_FLYERS);
		this.msgMensajeDocumentoFlyersList = service.getDocumentosMensaje(hmap);
		this.msgMensajeBandejaFlyersList = service.getListaBandejaFlyers(hmap);
		
	}
	
	
	/**
	 * Graba Flyers
	 * @param actionEvent
	 */
	public void grabarFlyers(ActionEvent actionEvent) {
		if (log.isWarnEnabled()) {
			log.warn("Entering 'grabarFlyers' method");
		}
		try {	
			/* Validaciones Previas */
			String mensajeValidacion = "";
			if (StringUtils.isBlank(this.codigoDocumentoFlyers)) 
				mensajeValidacion = this.getResourceMessage("mantenimientoMENPatronMensajeForm.flyers.codigoDocumento.vacio");
			if (StringUtils.isBlank(mensajeValidacion))
				if (StringUtils.isBlank(this.codigoBandejaFlyers)) 
					mensajeValidacion = this.getResourceMessage("mantenimientoMENPatronMensajeForm.flyers.codigoBandeja.vacio");
			if (StringUtils.isBlank(mensajeValidacion))
				if (StringUtils.isBlank(this.descripcionPatronFlyers)) 
					mensajeValidacion = this.getResourceMessage("mantenimientoMENPatronMensajeForm.flyers.descripcionPatron.vacio");
			
			if (StringUtils.isBlank(mensajeValidacion)) {
				Map beanValida = new HashMap();
				beanValida.put("codigoPais", this.codigoPais);
				beanValida.put("campanhaProceso", this.campanhaProcesoFlyers);
				beanValida.put("codigoDocumento", this.codigoDocumentoFlyers);
				beanValida.put("codigoBandeja", this.codigoBandejaFlyers);
				if (!this.esRegistroNuevoFlyers)
					beanValida.put("notOidPatron", this.oidPatronFlyers);
				List listFlyers = service.getListaFlyers(beanValida);
				if (listFlyers != null && listFlyers.size() > 0) {
					mensajeValidacion = this.getResourceMessage("mantenimientoMENPatronMensajeForm.flyers.registoExiste.error");
				}
			}
			if (StringUtils.isNotBlank(mensajeValidacion)) {
				this.setMensajeAlertaDefault(mensajeValidacion);
				RequestContext.getCurrentInstance().update("principalFormAlert:textoMensajeAlerta");
				String ventana = "PF('principalFormAlert_alertDialog').show()";
				this.getRequestContext().execute(ventana);
				return;
			}
			
			/* Grabando Flyers */
			Map criteria = new HashMap();
			criteria.put("codigoPais", this.codigoPais);
			criteria.put("campanhaProceso", this.campanhaProcesoFlyers);
			String oidPeriodoCorpo = service.getOidPeriodoCorpo(criteria);
			criteria.put("oidPeriodoCorpo", oidPeriodoCorpo);
			criteria.put("oidPatron", this.oidPatronFlyers);
			criteria.put("codigoDocumento", this.codigoDocumentoFlyers);
			criteria.put("codigoBandeja", this.codigoBandejaFlyers);
			criteria.put("descripcionPatron", this.descripcionPatronFlyers);
			criteria.put("login", this.mPantallaPrincipalBean.getCurrentUser().getLogin());
			service.executaSavePatronFlyersMensaje(criteria);
			this.find(actionEvent);
			
			/* Mostrando mensaje de GRabacion OK */
			String mensajeSave = this.getResourceMessage("mantenimientoMENPatronMensajeForm.flyers.save.ok");
			this.setMensajeAlertaDefaultAction(mensajeSave);
			RequestContext.getCurrentInstance().update("principalFormAlertAction:textoMensajeAlerta");
			String ventana = "PF('principalFormAlertAction_alertDialogAction').show()";
			this.getRequestContext().execute(ventana);
		}
		catch(Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
		
		if (log.isWarnEnabled()) {
			log.warn("Finish 'grabarFlyers' method");
		}
	}
	
	
	/**
	 * Sale del popup de Flyers
	 * @param actionEvent
	 */
	public void salirFlyers(ActionEvent actionEvent) {
		if (log.isWarnEnabled()) {
			log.warn("Entering 'salirFlyers' method");
		}
		try {	
			this.getRequestContext().execute("PF('dialogFlyers').hide()");
		}
		catch(Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
		
		if (log.isWarnEnabled()) {
			log.warn("Finish 'salirFlyers' method");
		}
	}
	

	@Override
	protected boolean setSaveAttributes() throws Exception {
		// TODO Auto-generated method stub
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setSaveAttributes' method");
		}
		boolean bGrabar = true;
		MantenimientoMENPatronMensajeForm f = (MantenimientoMENPatronMensajeForm) this.formMantenimiento;
		if (StringUtils.isBlank(f.getDescripcionPatron()))
			throw new Exception(
					this.getResourceMessage("mantenimientoMENPatronMensaje.descripcionPatron.requerido"));
		if (msgMensajePatronList.size() == 0 || msgMensajePatronList == null)
			throw new Exception(
					this.getResourceMessage("mantenimientoMENPatronMensaje.mesaje.patron.requerido"));

		f.setCodigoPais(codigoPais);
		try {
			Map map = BeanUtils.describe(f);
			map.put("login", usuario.getLogin());
			List listMensajePatron = this.msgMensajePatronList;
			map.put("listMensajePatron", listMensajePatron);
			
			if (this.accion.equals(this.ACCION_NUEVO)) {
				service.savePatronMensaje(map);

			} else {
				log.debug("save or update");
				service.savePatronMensaje(map);// X el indicador de accion
												// sabremos
												// que es inser o update
			}

			Map criteria = new HashMap();
			criteria.put("campanhaProceso", f.getCampanhaProceso());
			criteria.put("codigoDocumento", f.getCodigoDocumento());
			List list = service.getPatronMensaje(criteria);
			log.debug("lista patron " + list.size());
		} catch (Exception e) {
			// TODO: handle exception
			bGrabar = false;
			addError("Error", obtieneMensajeErrorException(e));
		}

		// session.setAttribute(Constants.MEN_PATRON_LIST,list);
		return bGrabar;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoAbstractAction
	 * #devuelveMensajeKeySaveOK()
	 */
	@Override
	protected String devuelveMensajeKeySaveOK() {
		MantenimientoMENPatronMensajeForm form = (MantenimientoMENPatronMensajeForm) this.formMantenimiento;
		boolean isNew = form.isNewRecord();
		if (isNew) {
			return "mantenimientoMENPatronMensaje.created";
		} else {
			return "mantenimientoMENPatronMensaje.updated";
		}
	}

	/**
	 * retrona la lista de mensajes
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void saveMensaje(ActionEvent event) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'saveMensaje' method");
		}

		try {
			MantenimientoMENPatronMensajeForm f = (MantenimientoMENPatronMensajeForm) this.formMantenimiento;
			// VARIFICAMOS SI CODIGO DE MENSAJE ES UNICO SOLO SI NO ESTY EN
			// EDICION
			// DE MENSAJE del patron
			if (!f.isFlagEditCabecera()) {// si es nuevo
				int cont = service.getExisteCodigoMensaje(f.getCodigoMensaje(),
						f.getCampanhaProceso());// se iba a validar por campanha
												// pero ya no
				if (cont > 0) {
					throw new Exception(
							this.getResourceMessage("mantenimientoMENPatronMensajeForm.existe.codigo.mensaje"));

				}
			}

			List listMensajePatron = this.msgMensajePatronList;
			if (listMensajePatron == null)
				listMensajePatron = new ArrayList();
			log.debug("listMensajePatron size " + listMensajePatron.size());

			List listConsi = this.msgPatronConsideracionList;
			if (listConsi == null)
				listConsi = new ArrayList();

			List listRestri = this.msgPatronRestriccionList;
			if (listRestri == null)
				listRestri = new ArrayList();
			if (StringUtils.isEmpty(f.getIdEdicion())) {
				Map map = BeanUtils.describe(f);
				map.put("login", usuario.getLogin());
				// map.put("abrevSeccion", getAbrevSeccion(map));
				map.put("descripcionSeccion", getAbrevSeccion(map));
				map.put("listConsi", listConsi);
				map.put("listRestri", listRestri);
				map.put("indicadorAccion", Constants.NRO_CERO);
				map.put("nombreImagenFondo", this.nombreImagenFondo);
				boolean existe = false;
				Iterator it = listMensajePatron.iterator();
				while (it.hasNext()) {
					Map aux = (Map) it.next();
					String indicadorAccion = (String) aux
							.get("indicadorAccion");
					String codigoMensaje = (String) aux.get("codigoMensaje");
					if (!Constants.NUMERO_DOS.equals(indicadorAccion)
							&& codigoMensaje.equals(f.getCodigoMensaje())) {
						throw new Exception(
								this.getResourceMessage("mantenimientoMENPatronMensajeForm.existe.codigo.mensaje"));
					}
				}

				if (!existe) {
					// SE DEBE BUSCAR X Seccion Y COLOCARLO AL FINAL DE SU
					// Seccion
					String codSeccion = f.getCodigoSeccion();
					// listMensajePatron.add(map);
					int index = -1;
					for (int i = listMensajePatron.size() - 1; i >= 0; i--) {
						Map bean = (Map) listMensajePatron.get(i);
						String codigoSecc = String.valueOf(bean
								.get("codigoSeccion"));
						if (codigoSecc.equals(codSeccion)) {// encontramo el
															// final
															// del modulo
							index = i;
							break;
						}
					}

					if (index == -1 || index == listMensajePatron.size() - 1) {
						listMensajePatron.add(map);
					} else {
						listMensajePatron.add(index + 1, map);
					}

				}
			} else {
				int index = Integer.parseInt(f.getIdEdicion());// posee el index
																// del
																// arreglo
				log.debug("index " + index);
				Map map = (Map) listMensajePatron.get(index);
				map.put("login", usuario.getLogin());
				map.put("codigoPais", f.getCodigoPais());
				map.put("descripcionMensaje", f.getDescripcionMensaje());
				map.put("descripcionPatron", f.getDescripcionPatron());
				map.put("codigoModulo", f.getCodigoModulo());
				map.put("textoMensaje", f.getTextoMensaje());
				map.put("textoHtml", f.getTextoHtml());
				map.put("tipoMensaje", f.getTipoMensaje());
				map.put("indicadorActivo", f.getIndicadorActivo());
				// map.put("abrevSeccion", getAbrevSeccion(map));
				map.put("listConsi", listConsi);
				map.put("listRestri", listRestri);
				// map.put("indicadorAccion", Constants.NRO_UNO);
				map.put("estadoRegistro", Constants.NRO_UNO);
				map.put("indicadorParaActualizar", Constants.NRO_UNO);
				map.put("oidPeriodoCorpo", f.getOidPeriodoCorpo());
				
				map.put("nombreImagenFondo", this.nombreImagenFondo);
				// listMensajePatron.add(index,map);
				log.debug("oidPeriodoCorpo "
						+ map.get("oidPeriodoCorpo").getClass());
			}

			f.setTipoMensaje(Constants.NUMERO_DOS);
			f.setIndicadorManual(Constants.NRO_CERO);
			indicadorManualBoolean = false;
			f.setIndicadorActivo(Constants.NUMERO_UNO);
			f.setCodigoModulo("");
			f.setCodigoMensaje("");
			f.setCodigoSeccion("");
			f.setCorrelativo("");
			f.setDescripcionMensaje("");
			f.setTextoMensaje("");
			f.setTextoHtml("");
			f.setCodigoConsideracion(null);
			f.setCodigoRestriccion(null);
			f.setFlagSeccionMensaje(false);
			f.setFlagSeccionPatron(true);
			f.setSelectedItemsConsideracion(null);
			f.setSelectedItemsRestriccion(null);
			f.setCondicion1("");
			f.setCondicion2("");
			f.setCondicionRest1("");
			f.setCondicionRest2("");
			f.setCondicion1S("");
			f.setCondicionRest1S("");
			f.setIndicadorConsideracion(Constants.NRO_UNO);
			f.setIndicadorRestriccion(Constants.NRO_UNO);
			bloqueNuevoMensaje = true;
			botonGrabarMensaje = false;
			botonNuevoMensaje = true;
			consResBoolean = true;
			descripcionMensajeBoolean = true;
			indicadorActivoBoolean = true;
			this.msgPatronConsideracionList = new ArrayList();
			this.dataTablePatronConsideracion = new DataTableModel(
					new ArrayList());
			this.msgPatronRestriccionList = new ArrayList();
			log.debug("oid patron " + f.getOidPatron() + " PERIODO "
					+ f.getCampanhaProceso() + " documento "
					+ f.getCodigoDocumento());
		} catch (Exception e) {
			// TODO: handle exception
			addError("Error", obtieneMensajeErrorException(e));
		}

	}

	/**
	 * @param map
	 * @return
	 */
	private String getAbrevSeccion(Map map) {
		return service.getAbrevSeccion(map);
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		// TODO Auto-generated method stub
		this.mostrarMantenimientoEnPopup = false;
		this.nombreVentanaPopupMantenimiento =  this.nombreVentanaPopupMantenimientoDefault;
		this.msgMensajeBandejaFlyersList = new ArrayList();
		this.msgMensajeDocumentoFlyersList = new ArrayList();
		this.esRegistroNuevoFlyers = true; 
		
		MantenimientoMENPatronMensajeForm f = new MantenimientoMENPatronMensajeForm();
		AjaxService ajax = (AjaxService) getBean("ajaxService");

		this.path = getRequest().getContextPath() + "/resources";
		this.pathRutaImgJava = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/images/");
		                       
		log.debug("ingresando setObtenerRegistroAttributes");
		log.debug("path: " + this.path);
		log.debug("pathRutaImgJava: " + this.pathRutaImgJava);
		log.debug("pathRutaImgOracle: " + this.pathRutaImgOracle);
		this.nombreImagenFondo = "";
		
		if (this.accion.equals(this.ACCION_NUEVO)) {
			this.mostrarBotonSave = false;
			Map criteria = new HashMap();
			criteria.put("codigoPais", codigoPais);

			// Indica Campanha Activa
			criteria.put("estadoCampanha", Constants.NUMERO_CERO);

			// Indica Campanha activa
			// q se procesa actualmente
			criteria.put("indicadorActiva", Constants.ESTADO_ACTIVO);

			MantenimientoOCRPedidoControlFacturacionService serviceMantPedidoCtrlFact = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
			PedidoControlFacturacion controlFacturacion = serviceMantPedidoCtrlFact
					.getControlFacturacionById(criteria);

			f.setCampanhaProceso(controlFacturacion.getCodigoPeriodo());
			f.setCampanhaActual(controlFacturacion.getCodigoPeriodo());
			f.setIndicadorActivo(Constants.NUMERO_UNO);
			Map hmap = new HashMap();
			hmap.put("codigoPais", codigoPais);
			this.msgMensajeDocumentoList = service.getDocumentosMensaje(hmap);
			// cargamos la seccion
			this.msgSeccionList = service.getSeccionPatron(hmap);
			// cargamos el modulo
			this.msgModuloList = service.getModulos(hmap);
			// CARGAR CONSIDERACIONES
			this.msgConsideracionList = ajax.getConsideracionMensajes(
					codigoPais, "F");
			// CARGAR RESTRICCONES
			// this.msgRestriccionList = service.getRestriccion(hmap);

			this.msgPatronConsideracionList = new ArrayList();
			this.msgPatronRestriccionList = new ArrayList();
			this.msgMensajePatronList = new ArrayList();
			this.dataTableModelMensaje = new DataTableModel(
					msgMensajePatronList);
			f.setFlagSeccionMensaje(false);

			// CAMPOS POR DEFECTO
			bloqueNuevoMensaje = true;
			botonBuscar = true;
			botonLimpiar = false;
			codigoMensajeDisabled = true;
			descripcionMensajeBoolean = true;
			descripcionPatronBoolean = false;
			indicadorActivoBoolean = true;
			consResBoolean = true;
			bloqueBuscarDisabled = false;
			botonNuevoMensaje = false;
			botonGrabarMensaje = false;
			dataTablePatronConsideracion = new DataTableModel();

			f.setTipoMensaje(Constants.NUMERO_DOS);
			setCodigoConsRestr("C");
			log.debug("fin add yy");
			// service.deleteTemporales();
		} else {
			Map data = (Map) this.beanRegistroSeleccionado;
			// CARGAMOS COMBOS
			f.setTabSeleccion(Constants.MEN_TAB_CONSIDERACION);
			f.setNewRecord(false);
			f.setCodigoPais(codigoPais);
			f.setCampanhaProceso((String) data.get("campanhaProceso"));
			f.setCodigoDocumento(String.valueOf(data.get("codigoDocumento")));
			f.setDescripcionPatron((String) data.get("descripcionPatron"));

			int campanhaPatron = Integer.parseInt(data.get("campanhaProceso")
					.toString());
			int campanhaActual = Integer.parseInt(this.campanhaActual);
			if (campanhaPatron < campanhaActual)
				throw new Exception(
						this.getResourceMessage("mantenimientoMENPatronMensajeForm.update.information"));

			Map hmap = new HashMap();
			hmap.put("codigoPais", codigoPais);
			msgMensajeDocumentoList = service.getDocumentosMensaje(hmap);
			// cargamos la seccion
			msgSeccionList = service.getSeccionPatron(hmap);
			// cargamos el modulo
			msgModuloList = service.getModulos(hmap);

			Map map = BeanUtils.describe(f);
			String oidPeriodoCorpo = service.getOidPeriodoCorpo(map);

			List list1 = service.getMensajePatron(map);
			msgMensajePatronList = list1;
			if (list1.size() > 0) {
				Map aux = (Map) list1.get(0);
				f.setOidPatron(String.valueOf(aux.get("oidPatron")));
				f.setOidMensaje((String) aux.get("oidMensaje"));
				
				/* Trayendo las imagenes a la Ruta Temporal Oracle */
				log.debug("Ini - Trayendo imagens de Ruta Oracle");
				
				for (int i=0; i < this.msgMensajePatronList.size(); i++) {
					Map bean = (Map) this.msgMensajePatronList.get(i);
					bean.put("codigoPais", this.mPantallaPrincipalBean.getCurrentCountry().getCodigo());
					
					log.debug("Ini - graba imagen a Ruta Oracle");
					try {
						service.leerMensajeImagen(bean);
					}
					catch(Exception e) {
						e.printStackTrace();
					}
					log.debug("Fin - graba imagen a Ruta Oracle");
					
					String textoMensaje = (String) bean.get("textoMensaje");
					log.debug("textoMensaje: "+ textoMensaje);
					
					/* Verificando si hay imagenes en el mensaje */
					String mensajeTemp = textoMensaje;
					String valorImagen = "";
					while(true) {
						int pos = StringUtils.indexOf(mensajeTemp, "<img>");
						if (pos >= 0) {
							mensajeTemp = StringUtils.substring(mensajeTemp, pos + 5);
							
							int posFin = StringUtils.indexOf(mensajeTemp, "</img>");
							valorImagen = StringUtils.substring(mensajeTemp, 0,posFin);
							mensajeTemp = StringUtils.substring(mensajeTemp, posFin);
							
							log.debug("valorImagen: "+ valorImagen);
							log.debug("mensajeTemp: "+ mensajeTemp);
							try {
								this.uploadArchivoImagenOracleJava(valorImagen);
							}
							catch(Exception e) {
								e.printStackTrace();
							}
						}
						else {
							break;
						}
					}
					
					/* Verificando si hay imagen de Fondo */
					String nombreImagenFondo = (String) bean.get("nombreImagenFondo");
					if (StringUtils.isNotBlank(nombreImagenFondo)) {
						try {
							this.uploadArchivoImagenOracleJava(nombreImagenFondo);
						}
						catch(Exception e) {
							e.printStackTrace();
						}
					}
					
			     }
				log.debug("Fin - Trayendo imagens de Ruta Oracle");
				
			} else {
				f.setOidPatron("");
				f.setOidMensaje("");
			}

			f.setOidPeriodoCorpo(oidPeriodoCorpo);
			f.setTipoMensaje(Constants.NUMERO_DOS);
			f.setIndicadorActivo(Constants.NUMERO_UNO);

			// this.obtenerRegistro(data);

			f.setTabSeleccion(Constants.MEN_TAB_CONSIDERACION);
			f.setFlagEditCabecera(false);
			f.setFlagConsulta(false);

			// obteniendo campanha Actual

			Map criteria = new HashMap();
			criteria.put("codigoPais", codigoPais);
			criteria.put("estadoCampanha", Constants.NUMERO_CERO); // Indica
																	// Campanha
																	// Activa
			criteria.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica
																		// Campanha
																		// activa
																		// q se
																		// procesa
																		// actualmente

			MantenimientoOCRPedidoControlFacturacionService serviceMantPedidoCtrlFact = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
			PedidoControlFacturacion controlFacturacion = serviceMantPedidoCtrlFact
					.getControlFacturacionById(criteria);
			f.setCampanhaActual(controlFacturacion.getCodigoPeriodo());
			this.msgConsideracionList = ajax.getConsideracionMensajes(
					codigoPais, "F");
			bloqueBuscarDisabled = true;
			flagEditar = true;
			botonLimpiar = true;
			botonNuevoMensaje = true;
			bloqueNuevoMensaje = true;
			codigoMensajeDisabled = true;
			descripcionMensajeBoolean = true;
			msgPatronConsideracionList = new ArrayList();
			// descripcionPatronBoolean = false;
			indicadorActivoBoolean = true;
			consResBoolean = true;
			mostrarBotonSave = true;
			setCodigoConsRestr("C");
			this.dataTablePatronConsideracion = new DataTableModel(msgPatronConsideracionList);
			this.dataTableModelMensaje = new DataTableModel(msgMensajePatronList);
			
			String codigoPatron = (String)data.get("codigoPatron");
			String prefijoPatron = StringUtils.substring(codigoPatron, 0, 2);
			if (prefijoPatron.equals(CODIGO_PATRON_FLYERS)) {
				String campanhaProceso = (String)data.get("campanhaProceso");
				BigDecimal boidPatron = (BigDecimal)data.get("oidPatron");
				String oidPatron = boidPatron.toString();
				this.obtenerFormularioFlyersEdit(oidPatron, campanhaProceso);
				this.mostrarMantenimientoEnPopup = true;
				this.nombreVentanaPopupMantenimiento = "dialogFlyers";
				this.esRegistroNuevoFlyers = false; 
				RequestContext.getCurrentInstance().update("panelDialogFlyers");
			}
		}
		return f;

	}
	
	

	/**
	 * @param event
	 * @throws Exception
	 */
	public void editMensajePatron(ActionEvent event) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'editMensajePatron' method");
		}
		MantenimientoMENPatronMensajeForm f = (MantenimientoMENPatronMensajeForm) this.formMantenimiento;
		MantenimientoMENGenericoService service = (MantenimientoMENGenericoService) getBean("spusicc.mantenimientoMENGenericoService");

		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		String index = externalContext.getRequestParameterMap().get("index");

		/*
		 * Agregado para reinicializar las
		 * listas**************************************************************
		 */

		// session.removeAttribute("codigoPremioList");
		// session.removeAttribute("codigoVentaList");
		// session.removeAttribute("codigoVentaReemplazoList");
		// session.removeAttribute("codigoEstatusList");
		// session.removeAttribute("clasificacionesList");
		// session.removeAttribute("unidadesList");
		// session.removeAttribute("consultoraList");
		// session.removeAttribute("codigoPremioListR");
		// session.removeAttribute("codigoVentaListR");
		// session.removeAttribute("codigoVentaReemplazoListR");
		// session.removeAttribute("codigoEstatusListR");
		// session.removeAttribute("clasificacionesListR");
		// session.removeAttribute("unidadesListR");
		// session.removeAttribute("consultoraListR");
		// session.removeAttribute("numLote");
		// session.removeAttribute("numLoteR");
		// session.removeAttribute("buzonLoteList");
		// session.removeAttribute("buzonLoteListR");

		/* ************************************************************** */
		List list = this.msgMensajePatronList;
		// int index = Integer.parseInt(request.getParameter("id"));

		Map map = (Map) list.get(Integer.parseInt(index));
		String indEditable = String.valueOf(map.get("indicadorEditable"));
		String indicadorAccion = String.valueOf(map.get("indicadorAccion"));
		indEditable = (Constants.NRO_CERO.equals(indicadorAccion) ? Constants.NRO_UNO
				: indEditable);
		log.debug("indicadorEditable " + indEditable + " indicadorAccion >>> "
				+ indicadorAccion);

		if (indEditable.equals("0")) {
			consResBoolean = true;
			descripcionMensajeBoolean = true;
			indicadorActivoBoolean = true;
		} else {
			consResBoolean = false;
			descripcionMensajeBoolean = false;
			indicadorActivoBoolean = false;
		}
		f.setTipoMensaje(String.valueOf(map.get("tipoMensaje")));
		if (Constants.NRO_CERO.equals(indicadorAccion))
			f.setCodigoModulo(String.valueOf(map.get("codigoModulo")));
		else
			f.setCodigoModulo(String.valueOf(map.get("oidModulo")));
		f.setCodigoMensaje(String.valueOf(map.get("codigoMensaje")));
		f.setCodigoSeccion(String.valueOf(map.get("codigoSeccion")));
		f.setCorrelativo(String.valueOf(map.get("correlativo")));
		f.setDescripcionMensaje(String.valueOf(map.get("descripcionMensaje")));
		f.setTextoMensaje(String.valueOf(map.get("textoMensaje")));
		f.setTextoHtml((String) map.get("textoHtml"));

		// cargando la lista de consideraciones y restricciones

		List listConsi = (List) map.get("listConsi");
		List listRest = (List) map.get("listRestri");

		if (listConsi == null || listConsi.size() == 0) {
			map.put("tipoConsideracion", Constants.MEN_TIPO_CONSIDERACION);
			listConsi = service.getRestConsideracion(map);
		}
		this.msgPatronConsideracionList = listConsi;
		this.dataTablePatronConsideracion = new DataTableModel(
				indAccionEliminar(msgPatronConsideracionList));

		if (listRest == null || listRest.size() == 0) {
			map.put("tipoConsideracion", Constants.MEN_TIPO_RESTRICCION);
			listRest = service.getRestConsideracion(map);
		}
		this.msgPatronRestriccionList = listRest;

		// nueva validacion
		if (f.getCampanhaProceso().compareTo(f.getCampanhaActual()) < 0) {
			indEditable = Constants.NUMERO_CERO;
			f.setFlagConsulta(true);
			// session.setAttribute("flagConsultaSession",Constants.NUMERO_UNO);
		} else {
			f.setFlagConsulta(false);
			// session.setAttribute("flagConsultaSession",Constants.NUMERO_CERO);
		}

		f.setIdEdicion("" + index);
		f.setFlagSeccionPatron(false);
		// f.setSelectedItem("");
		// f.setSelectedItems(null);
		f.setSelectedItemsConsideracion(null);
		f.setSelectedItemsRestriccion(null);
		// f.setModified(false);
		f.setFlagEditCabecera(true);
		f.setFlagSeccionMensaje(Constants.NUMERO_UNO.equals(indEditable) ? true
				: false);
		f.setTabSeleccion(Constants.MEN_TAB_CONSIDERACION);

		// validar campos
		bloqueNuevoMensaje = true;
		botonLimpiar = false;
		botonNuevoMensaje = true;
		
		this.botonGrabarMensaje = true;
		String indicadorEditable = String.valueOf(map.get("indicadorEditable"));
		if (Constants.NRO_CERO.equals(indicadorEditable))
			this.botonGrabarMensaje = false;

		if (f.getTipoMensaje().equals("2"))
			this.msgConsideracionList = ajax.getConsideracionMensajes(
					codigoPais, "F");
		else
			this.msgConsideracionList = ajax.getConsideracionMensajes(
					codigoPais, "V");

		// para validar si codigo mensaje esta en edit o no
		f.setFlagEditCabecera(true);

		Map hmap = new HashMap();
		hmap.put("codigoDocumento", f.getCodigoDocumento());
		this.msgSeccionList = service.getSeccionPatron(hmap);

		// inicializar variables de Lista Consultoras
		consultoraList = new ArrayList();
		buzonLoteList = new ArrayList(); 

		/* Validando tema de Imagen de Fondo */
		this.nombreImagenFondo = "";
		f.setNombreArchivo("");
		String nombreImagenFondo = (String) map.get("nombreImagenFondo");
		if (StringUtils.isNotBlank(nombreImagenFondo) && nombreImagenFondo != "null") {
			this.nombreImagenFondo = nombreImagenFondo;
			f.setNombreArchivo(nombreImagenFondo);
		}

	}

	private void obtenerRegistro(Map bean) throws Exception {

		// CARGAMOS COMBOS
		MantenimientoMENPatronMensajeForm f = new MantenimientoMENPatronMensajeForm();
		f.setTabSeleccion(Constants.MEN_TAB_CONSIDERACION);

		f.setCodigoPais(codigoPais);
		f.setCampanhaProceso((String) bean.get("campanhaProceso"));
		f.setCodigoDocumento(String.valueOf(bean.get("codigoDocumento")));
		f.setDescripcionPatron((String) bean.get("descripcionPatron"));

		Map hmap = new HashMap();
		hmap.put("codigoPais", codigoPais);
		msgMensajeDocumentoList = service.getDocumentosMensaje(hmap);
		// cargamos la seccion
		msgSeccionList = service.getSeccionPatron(hmap);
		// cargamos el modulo
		msgModuloList = service.getModulos(hmap);
		// CARGAR CONSIDERACIONES
		// msgConsideracionList =service.getConsideracion(hmap);
		// CARGAR RESTRICCONES
		// msgRestriccionList = service.getRestriccion(hmap);

		// session.setAttribute("msgPatronConsideracionList", new ArrayList());
		// session.setAttribute("msgPatronRestriccionList", new ArrayList());

		// borrando temporales
		// session.removeAttribute(Constants.MEN_MENSAJE_PATRON_LIST);
		Map map = BeanUtils.describe(f);
		String oidPeriodoCorpo = service.getOidPeriodoCorpo(map);

		List list1 = service.getMensajePatron(map);
		msgMensajePatronList = list1;
		if (list1.size() > 0) {
			Map aux = (Map) list1.get(0);
			f.setOidPatron(String.valueOf(aux.get("oidPatron")));
			f.setOidMensaje((String) aux.get("oidMensaje"));
		} else {
			f.setOidPatron("");
			f.setOidMensaje("");
		}

		f.setOidPeriodoCorpo(oidPeriodoCorpo);
		f.setFlagSeccionPatron(true);
		f.setFlagSeccionMensaje(false);
		// f.setSelectedItem("");
		// f.setSelectedItems(null);
		f.setSelectedItemsConsideracion(null);
		f.setSelectedItemsRestriccion(null);

	}

	public void jsCorrelativo(ValueChangeEvent val) {
		String codigoSeccion = val.getNewValue().toString();
		MantenimientoMENPatronMensajeForm f = (MantenimientoMENPatronMensajeForm) this.formMantenimiento;
		if (f.getCodigoModulo() == null)
			f.setCodigoModulo("");
		String correlativo = ajax.getCorrelativoDocumentoSecccion(
				f.getCodigoModulo(), codigoSeccion, f.getOidPatron(),
				msgMensajePatronList);
		f.setCorrelativo(correlativo);
	}

	public void cambiarListConsideracion(ValueChangeEvent val) {
		String valor = val.getNewValue().toString();
		if (valor.equals("1"))
			valor = "V";
		else
			valor = "F";

		this.msgConsideracionList = ajax.getConsideracionMensajes(codigoPais,
				valor);

	}

	public void generarCodigoMensaje(ValueChangeEvent val) {
		String codigoModulo = val.getNewValue().toString();
		MantenimientoMENPatronMensajeForm f = (MantenimientoMENPatronMensajeForm) this.formMantenimiento;
		String codigoMensaje = "";
		codigoMensaje = ajax.getCodigoMensajeByModulo(codigoModulo,
				f.getOidPeriodoCorpo(), f.getCampanhaProceso(),
				msgMensajePatronList);
		f.setCodigoMensaje(codigoMensaje);

	}
	
	/**
	 * @param codigoModulo
	 */
	public void generarCodigoMensaje(String codigoModulo) {
		MantenimientoMENPatronMensajeForm f = (MantenimientoMENPatronMensajeForm) this.formMantenimiento;
		String codigoMensaje = "";
		codigoMensaje = ajax.getCodigoMensajeByModulo(codigoModulo,
				f.getOidPeriodoCorpo(), f.getCampanhaProceso(),
				msgMensajePatronList);
		f.setCodigoMensaje(codigoMensaje);

	}
	

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		mostrarBotonSalir = false;
		usuario = this.mPantallaPrincipalBean.getCurrentUser();
		ajax = (AjaxService) getBean("ajaxService");
		this.mostrarBotonConsultar = false;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		codigoPais = pais.getCodigo();
		FacesContext.getCurrentInstance().getExternalContext().getRealPath("")
				.replaceAll("\\\\", "/");
		MantenimientoMENPatronMensajeSearchForm f = (MantenimientoMENPatronMensajeSearchForm) this.formBusqueda;
		service = (MantenimientoMENGenericoService) getBean("spusicc.mantenimientoMENGenericoService");

		Map hmap = new HashMap();
		hmap.put("codigoPais", codigoPais);
		this.msgMensajeDocumentoList = service.getDocumentosMensaje(hmap);

		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
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
		f.setCampanhaActual(f.getCampanhaProceso());
		campanhaActual = f.getCampanhaProceso();
		log.debug("campanha actual " + f.getCampanhaActual());
		salirGrabarPantallaPadre = true;
		// carga de grilla inicial
		listaBusqueda = findList(f);
		datatableBusqueda = new DataTableModel(listaBusqueda);
			
		//Obtenes valor del parametro para RUTA TEMPORAL EN ORACLE PARA LAS IMAGENES
		GenericoService genericoService = (GenericoService)this.getBeanService("genericoService");	
		ParametroPais parametroPais1 = new ParametroPais();
		parametroPais1.setCodigoPais(this.mPantallaPrincipalBean.getCountryCode());
		parametroPais1.setCodigoSistema(Constants.MSG_CODIGO_SISTEMA);
		parametroPais1.setNombreParametro(Constants.MSG_NOMBRE_PARAMETRO_RUTA_IMAGENES);
		List lstParametros1 = genericoService.getParametrosPais(parametroPais1);
		
		this.pathRutaImgOracle = "";
		if(lstParametros1 != null && lstParametros1.size() > 0){			
			ParametroPais ps = (ParametroPais)lstParametros1.get(0);
			this.pathRutaImgOracle = ps.getValorParametro();
		}
		return;
	}

	/**
	 * @param f
	 * @return
	 * @throws Exception
	 */
	public List findList(MantenimientoMENPatronMensajeSearchForm f)
			throws Exception {
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");

		// Obtenemos los datos del usuario Logueado

		// enviando en session los parametros de mensaje
		Map map = BeanUtils.describe(f);

		// OBTENIENDO OID pAIS
		// Asignamos al codigo del periodo el valor por defecto
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		criteria.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
		criteria.put("codigoISO", usuario.getIdioma().getCodigoISO());

		// recuperamos el oid Pais
		String oidPais = clienteService.getOidPais(criteria);
		// recuperamos el oidPeriodo
		if (StringUtils.isNotEmpty(f.getCampanhaProceso())) {
			criteria.put("codigoPeriodo", f.getCampanhaProceso());
			String oidPeriodo = reporteService.getOidString(
					"getOidPeriodoByCodigoPeriodo", criteria);
			map.put("oidPeriodo", oidPeriodo);
		}

		map.put("oidPais", oidPais);
		List list = service.getPatronMensaje(map);
		log.debug("lista patron " + list.size());
		this.msgPatronMensajeList = list;

		return list;
	}

	public void ordenarPatron(ActionEvent event) throws Exception {
		Map data = (Map) this.beanRegistroSeleccionado;
		int campanhaPatron = Integer.parseInt(data.get("campanhaProceso")
				.toString());
		int campanhaActual = Integer.parseInt(this.campanhaActual);
		if (campanhaPatron < campanhaActual) {
			addError(
					"Error",
					this.getResourceMessage("mantenimientoMENPatronMensaje.validation.information")
							+ " " + this.campanhaActual);
		} else {
			if (datatableBusqueda == null)
				this.getRequestContext().execute(
						"PF('dialogSinRegistros_alertDialog').show()");
			else if (beanRegistroSeleccionado == null)
				this.getRequestContext().execute(
						"PF('dialogSinItem_alertDialog').show()");
			else {
				ordenaAction.setData(data);
				ordenaAction.setViewAtributes();
			}
		}
	}

	public void consultarPatron(ActionEvent event) throws Exception {
		Map data = (Map) this.beanRegistroSeleccionado;
		if (datatableBusqueda == null)
			this.getRequestContext().execute(
					"PF('dialogSinRegistros_alertDialog').show()");
		else if (beanRegistroSeleccionado == null)
			this.getRequestContext().execute(
					"PF('dialogSinItem_alertDialog').show()");
		else {
			consultaAction.setData(data);
			consultaAction.setViewAtributes();
		}
	}

	public void findMensajePatron(ActionEvent event) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'findMensajePatron' method >>>");
		}
		MantenimientoMENPatronMensajeForm f = (MantenimientoMENPatronMensajeForm) this.formMantenimiento;
		f.setCodigoPais(codigoPais);
		Map map = BeanUtils.describe(f);
		String oidPeriodoCorpo = service.getOidPeriodoCorpo(map);

		List list = service.getMensajePatron(map);
		msgMensajePatronList = list;
		dataTableModelMensaje = new DataTableModel(msgMensajePatronList);
		if (list.size() > 0) {
			Map bean = (Map) list.get(0);
			f.setOidPatron(String.valueOf(bean.get("oidPatron")));
			f.setDescripcionPatron((String) bean.get("descripcionPatron"));
		} else {
			f.setOidPatron("");
			f.setDescripcionPatron("");
		}

		f.setOidPeriodoCorpo(oidPeriodoCorpo);
		f.setFlagSeccionPatron(true);
		f.setSelectedItemsConsideracion(null);
		f.setSelectedItemsRestriccion(null);

		// seteando la campanha actual
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
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

		f.setCampanhaActual(controlFacturacion.getCodigoPeriodo());

		// CAMPOS PARA HABILITAR Y DESHABILITAR
		bloqueBuscarDisabled = true;
		botonBuscar = false;
		botonLimpiar = true;
		botonNuevoMensaje = true;
		this.mostrarBotonSave = true;

		// nueva validacion
		if (f.getCampanhaProceso().compareTo(f.getCampanhaActual()) < 0) {
			// indEditable = Constants.NUMERO_UNO;
			f.setFlagConsulta(true);
			// session.setAttribute("flagConsultaSession",Constants.NUMERO_UNO);
		} else {
			f.setFlagConsulta(false);
			// session.setAttribute("flagConsultaSession",Constants.NUMERO_CERO);
		}
	}

	public void clearMensajePatron(ActionEvent event) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'clearMensajePatron' method");
		}
		MantenimientoMENPatronMensajeForm f = (MantenimientoMENPatronMensajeForm) this.formMantenimiento;
		f.setNewRecord(true);
		f.setCampanhaProceso(null);
		f.setCodigoDocumento(null);
		f.setDescripcionPatron(null);
		this.msgMensajePatronList.clear();

		// CAMPOS HABILITAR Y DESHABILITAR
		botonBuscar = true;
		bloqueBuscarDisabled = false;
		botonNuevoMensaje = false;

	}

	public void deleteMensajePatron(ActionEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'deleteMensajePatron ' method");
		}
		MantenimientoMENPatronMensajeForm f = (MantenimientoMENPatronMensajeForm) this.formMantenimiento;

		List list = this.msgMensajePatronList;
		Map map = (Map) list.get(Integer.parseInt(indexPatronMensaje));
		map.put("indicadorAccion", Constants.NUMERO_DOS);
		map.put("tipoMensaje", String.valueOf(map.get("tipoMensaje")));
		map.put("codigoModulo", String.valueOf(map.get("oidModulo")));
		map.put("tipoMensaje", String.valueOf(map.get("tipoMensaje")));
		map.put("login", usuario.getLogin());
		map.put("codigoPais", codigoPais);
		map.put("oidPeriodoCorpo", f.getOidPeriodoCorpo());
		map.put("indicadorActivo", Constants.NRO_CERO);
		map.put("estadoRegistro", Constants.ESTADO_INACTIVO);

		List lista = new ArrayList();
		for (int i = 0; i < msgMensajePatronList.size(); i++) {
			Map map2 = (Map) msgMensajePatronList.get(i);
			String ind = map2.get("indicadorAccion").toString();
			if (!ind.equals(Constants.NRO_DOS))
				lista.add(map2);
		}
		// msgMensajePatronList = lista;
		dataTableModelMensaje = new DataTableModel(lista);

	}

	public String setValidarConfirmar(String accion) {
		MantenimientoMENPatronMensajeForm f = (MantenimientoMENPatronMensajeForm) this.formMantenimiento;
		if (accion.equals("LIMPIAR")) {
			//
		} else if (accion.split(";")[0].equals("ELIMINAR")) {

			indexPatronMensaje = accion.split(";")[1];
		} else if (accion.equals("GUARDAR_MENSAJE")) {

		} else if (accion.equals("SALIR")) {

		} else if (accion.equals("CLASIFICACIONES")) {
			if (clasificacionesList.size() == 0)
				return "No hay elementos guardados";
		} else if (accion.equals("UNIDADES")) {
			if (unidadesList.size() == 0)
				return "No hay elementos guardados";
		} else if (accion.equals("ELIMINARCONSIDERACION")) {
			if (beanRegistroPatronConsideracion == null)
				return this.getResourceMessage("errors.select.item");
		} else if (accion.equals("ESTATUS")) {
			if (codigoEstatusList.size() == 0)
				return "No hay elementos guardados";
		}
		return null;
	}

	/**
	 * @param event
	 */
	public void validarSaveMensaje(ActionEvent event) {
		MantenimientoMENPatronMensajeForm f = (MantenimientoMENPatronMensajeForm) this.formMantenimiento;
		log.debug("ini validarSaveMensaje");
		camposRequeridos = "SI";
		if (StringUtils.isBlank(f.getDescripcionPatron())) {
			addError("Error", 	this.getResourceMessage("mantenimientoMENPatronMensaje.descripcionPatron.requerido"));
			return;
		}
		else if (StringUtils.isBlank(f.getCodigoModulo())) {
			addError("Error",this.getResourceMessage("mantenimientoMENPatronMensajeForm.codigoModulo.requerido"));
			return;
		}
		else if (StringUtils.isBlank(f.getCodigoMensaje())) {
			addError("Error", 	this.getResourceMessage("mantenimientoMENPatronMensajeForm.codigoMensaje.requerido"));
			return;
		}
		else if (StringUtils.isBlank(f.getCodigoSeccion())) {
			addError("Error", this.getResourceMessage("mantenimientoMENPatronMensajeForm.codigoSeccion.requerido"));
			return;
		}
		else if (StringUtils.isBlank(f.getDescripcionMensaje())) {
			addError("Error", this.getResourceMessage("mantenimientoMENPatronMensajeForm.descripcionMensaje.requerido"));
			return;
		}
		else if (msgPatronConsideracionList.size() == 0
				&& msgPatronRestriccionList.size() == 0) {
			addError("Error", this.getResourceMessage("mantenimientoMENPatronMensajeForm.consideracion.information"));
			return;
		}
		else if (StringUtils.isBlank(f.getTextoHtml())) {
			if (StringUtils.isBlank(f.getTextoMensaje())) 
				if (StringUtils.isBlank(nombreImagenFondo)) {
					addError("Error",	this.getResourceMessage("mantenimientoMENPatronMensajeForm.textoMensaje.requerido"));
					return;
				}
		}
		else
			camposRequeridos = "NO";
		try {
			camposRequeridos = "NO";
			if (StringUtils.isBlank(f.getTextoHtml())) {
				if (StringUtils.isBlank(f.getTextoMensaje())) 
					if (StringUtils.isNotBlank(nombreImagenFondo)) {
						f.setTextoHtml(" ");
						f.setTextoMensaje(" ");
					}
			}
			String descripcionMensaje = f.getDescripcionMensaje();
			descripcionMensaje = StringUtils.substring(descripcionMensaje, 0, 80);
			f.setDescripcionMensaje(descripcionMensaje);
			
			String mensajeDato = borrarDato(f.getTextoMensaje());
			
			/* Verificando si hay imagenes en el mensaje */
			String mensajeTemp = mensajeDato;
			log.debug("ini obteniendo imagenes mensajeTemp: " + mensajeTemp);
			String valorImagen = "";
			while(true) {
				int pos = StringUtils.indexOf(mensajeTemp, "<img>");
				log.debug("pos: " + pos);
				if (pos >= 0) {
					mensajeTemp = StringUtils.substring(mensajeTemp, pos + 5);
					int posFin = StringUtils.indexOf(mensajeTemp, "</img>");
					valorImagen = StringUtils.substring(mensajeTemp, 0,posFin);
					mensajeTemp = StringUtils.substring(mensajeTemp, posFin);
					
					log.debug("mensajeTemp: " + mensajeTemp);
					log.debug("valorImagen: " + valorImagen);
					this.uploadArchivoImagenJavaOracle(valorImagen);
					
				}
				else {
					break;
				}
			}
			if (StringUtils.isNotBlank(this.nombreImagenFondo)) {
				this.uploadArchivoImagenJavaOracle(this.nombreImagenFondo);
			}
			log.debug("fin obteniendo imagenes");
			
			String data = ajax.getValidarConsideracion(mensajeDato,
					f.getTipoMensaje(), null, null);
			if (StringUtils.isNotBlank(data)) {
				String cad0 = data.split("_")[0];
				String cad1 = data.split("_")[1];
				if (cad0.equals("-1")) {
					camposRequeridos = "SI";
					throw new Exception(
							this.getResourceMessage("mantenimientoMENPatronMensajeForm.xml.information")
									+ " " + cad1);
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			addError("Error", obtieneMensajeErrorException(e));
		}
		log.debug("fin validarSaveMensaje");

	}

	public String borrarDato(String dato) {
		dato = dato.replace("</dato01>", "");
		dato = dato.replace("</dato02>", "");
		dato = dato.replace("</dato03>", "");
		dato = dato.replace("</dato04>", "");
		dato = dato.replace("</dato05>", "");
		dato = dato.replace("</dato06>", "");
		dato = dato.replace("</dato07>", "");
		dato = dato.replace("</dato08>", "");
		dato = dato.replace("</dato09>", "");
		dato = dato.replace("</dato10>", "");
		dato = dato.replace("</dato11>", "");
		dato = dato.replace("</dato12>", "");
		dato = dato.replace("</dato13>", "");
		dato = dato.replace("</dato14>", "");
		dato = dato.replace("</dato15>", "");
		dato = dato.replace("</dato16>", "");
		dato = dato.replace("</dato17>", "");
		dato = dato.replace("</dato18>", "");
		dato = dato.replace("</dato19>", "");
		dato = dato.replace("</dato20>", "");
		dato = dato.replace("<dato01>", "<DATO01>");
		dato = dato.replace("<dato02>", "<DATO02>");
		dato = dato.replace("<dato03>", "<DATO03>");
		dato = dato.replace("<dato04>", "<DATO04>");
		dato = dato.replace("<dato05>", "<DATO05>");
		dato = dato.replace("<dato06>", "<DATO06>");
		dato = dato.replace("<dato07>", "<DATO07>");
		dato = dato.replace("<dato08>", "<DATO08>");
		dato = dato.replace("<dato09>", "<DATO09>");
		dato = dato.replace("<dato10>", "<DATO10>");
		dato = dato.replace("<dato11>", "<DATO11>");
		dato = dato.replace("<dato12>", "<DATO12>");
		dato = dato.replace("<dato13>", "<DATO13>");
		dato = dato.replace("<dato14>", "<DATO14>");
		dato = dato.replace("<dato15>", "<DATO15>");
		dato = dato.replace("<dato16>", "<DATO16>");
		dato = dato.replace("<dato17>", "<DATO17>");
		dato = dato.replace("<dato18>", "<DATO18>");
		dato = dato.replace("<dato19>", "<DATO19>");
		dato = dato.replace("<dato20>", "<DATO20>");
		return dato;
	}

	public void validarBotones(ActionEvent event) {
		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		String accion = externalContext.getRequestParameterMap().get(
				"parametroAccion");
		MantenimientoMENPatronMensajeForm f = (MantenimientoMENPatronMensajeForm) this.formMantenimiento;

		if (accion.equals("NUEVO_MENSAJE")) {
			botonGrabarMensaje = true;
			botonNuevoMensaje = false;
			bloqueNuevoMensaje = false;
			botonLimpiar = false;
			indicadorActivoBoolean = false;
			descripcionMensajeBoolean = false;
			consResBoolean = false;
			f.setFlagSeccionMensaje(true);
			f.setDescripcionMensaje(null);
			f.setTipoMensaje(Constants.NUMERO_DOS);
			f.setCodigoSeccion(null);
			f.setCodigoModulo(null);
			f.setCodigoModulo(CODIGO_MODULO_MENSAJE);
			f.setCodigoMensaje(null);
			f.setIndicadorActivo("1");
			f.setTextoHtml(null);
			f.setIdEdicion("");
			f.setTextoMensaje(null);
			f.setCorrelativo(null);
			f.setFlagEditCabecera(false);
			msgPatronRestriccionList = new ArrayList();
			msgPatronConsideracionList = new ArrayList();
			dataTablePatronConsideracion = new DataTableModel(
					msgPatronConsideracionList);
			this.msgConsideracionList = ajax.getConsideracionMensajes(
					codigoPais, "F");
			this.codigoConsRestr = "C";
			consultoraList = new ArrayList();
			buzonLoteList = new ArrayList();
			Map hmap = new HashMap();
			hmap.put("codigoDocumento", f.getCodigoDocumento());
			this.msgSeccionList = service.getSeccionPatron(hmap);
			
			this.generarCodigoMensaje(f.getCodigoModulo());
			f.setNombreArchivo("");
			this.nombreImagenFondo = "";
		}
	}

	public void habilitarCodigoMensaje(ValueChangeEvent val) {
		String valor = val.getNewValue().toString();
		if (valor.equals("true"))
			codigoMensajeDisabled = false;
		else
			codigoMensajeDisabled = true;
	}

	public void replicarPatron(ActionEvent event) throws Exception {
		Map data = (Map) this.beanRegistroSeleccionado;
		if (datatableBusqueda == null)
			this.getRequestContext().execute(
					"PF('dialogSinRegistros_alertDialog').show()");
		else if (beanRegistroSeleccionado == null)
			this.getRequestContext().execute(
					"PF('dialogSinItem_alertDialog').show()");
		else {
			replicaAction.setData(data);
			replicaAction.setViewAtributes();
		}
	}

	public void upload(FileUploadEvent event) throws IOException, Exception {
		if (log.isDebugEnabled()) {
			log.debug(" metodo uploadSeccionFile");
		}
		MantenimientoMENPatronMensajeForm f = (MantenimientoMENPatronMensajeForm) this.formMantenimiento;
		f.setArchivo(event.getFile());
		f.setNombreArchivo(f.getArchivo().getFileName());
		this.getRequestContext()
				.execute(
						"PF('confirmDialogSalirUpload_confirmationDialogConfirmar').show()");

	}

	public void insertarArchivo(ActionEvent event) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug(" metodo uploadSeccionFile");
		}
		MantenimientoMENPatronMensajeForm f = (MantenimientoMENPatronMensajeForm) this.formMantenimiento;
		String flagArchivo = "";
		List list = new ArrayList();
		// String flagArchivo = (String)
		// request.getParameter("flagArchivo");
		if (flagArchivo.equals(Constants.NRO_CERO)) {

			list = this.buzonLoteList;
			if (list != null) {
				for (int i = list.size() - 1; i >= 0; i--) {
					Map m = (Map) list.get(i);
					String indicadorAccion = (String) m.get("indicadorAccion");
					if (Constants.MEN_ESTADO_TMP_INSERTAR
							.equals(indicadorAccion)) {
						m.put("indicadorAccion", Constants.NRO_CERO);
					}
					if (Constants.MEN_ESTADO_TMP_ELIMINAR
							.equals(indicadorAccion)) {
						m.put("indicadorAccion", Constants.NUMERO_DOS);
					}
				}
			}

		} else {

			ProcesoZONActualizarUnidadesGeograficasService serviceUnidad = (ProcesoZONActualizarUnidadesGeograficasService) getBean("spusicc.procesoZONActualizarUnidadesGeograficasService");
			f.setDirectorioTemporal(serviceUnidad.obtenerPathUpload(codigoPais));
			this.uploadArchivo(f.getDirectorioTemporal(), f.getNombreArchivo(),
					f.getArchivo().getInputstream());
			Map criteria = new HashMap();
			// Asignamos al codigo del periodo el valor por defecto
			Map crit = new HashMap();
			crit.put("codigoPais", f.getCodigoPais());
			crit.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
			crit.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
			crit.put("codigoISO", usuario.getIdioma().getCodigoISO());

			// recuperamos el oid Pais
			MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");

			String oidPais = clienteService.getOidPais(crit);
			String indicadorTipo = codigoConsRestr;
			// String oidPais = String.valueOf(pais.getOidPais());
			criteria.put("directorioTemporal", f.getDirectorioTemporal());
			criteria.put("nombreArchivo", f.getNombreArchivo());
			criteria.put("oidPais", oidPais);
			criteria.put("indicadorTipo", indicadorTipo);
			criteria.put("oidPeriodoCorpo", "");// vacio
												// pantalla
												// popup
												// no
												// existe
												// campo
			criteria.put("oidModulo", "");// vacio pantalla
											// popup no
											// existe campo
			criteria.put("login", usuario.getLogin());
			boolean isValido = service.validarArchivoExcel(criteria);
			if (isValido) {
				service.executeCargaArchivoExcel(criteria);
				String mensajeResultado = (String) criteria
						.get("mensajeResultado");
				if (StringUtils.isNotEmpty(mensajeResultado)) {
					log.debug("mensajeResultado " + mensajeResultado);

				} else {
					List consultoraList = (List) criteria.get("consultoraList");
					this.consultoraList = consultoraList;
				}

			}
			borrarFichero(f.getDirectorioTemporal(), f.getNombreArchivo());
		}
		for (int i = 0; i < msgPatronConsideracionList.size(); i++) {
			Map data = (Map) msgPatronConsideracionList.get(i);
			if (data.get("codigoConsideracion").equals(
					f.getCodigoConsideracion())) {
				data.put("consultoraList", this.consultoraList);
				data.put("numReg", this.consultoraList.size());
				data.put("buzonLoteList", buzonLoteList);
				data.put("indicadorModificadoPopup", Constants.NUMERO_UNO);
			}
		}

		if (!botonEdit)
			insertConsideracion();

		this.getRequestContext().execute("PF('viewListaConsultora').hide()");
	}

	/**
	 * Metodo que guarda el archivo en el servidor
	 * 
	 * @param fileName
	 * @param in
	 * @throws Exception
	 */
	protected final void uploadArchivo(String directorio, String fileName,
			InputStream in) throws Exception {

		// Escribe el contenido de un archivo de entrada a un FileOutputStream
		// de salida
		OutputStream out = new FileOutputStream(new File(directorio + "/"	+ fileName));

		int read = 0;
		byte[] bytes = new byte[1024];

		while ((read = in.read(bytes)) != -1) {
			out.write(bytes, 0, read);
		}

		in.close();
		out.flush();
		out.close();
	}

	public void insertConsideracion(ActionEvent actionEvent) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'insertConsideracion' method ");
		}

		insertConsideracion();

	}

	public void insertConsideracion() throws Exception {
		MantenimientoMENPatronMensajeForm f = (MantenimientoMENPatronMensajeForm) this.formMantenimiento;
		botonEdit = false;
		// VARIFICAMOS SI CODIGO DE MENSAJE ES UNICO SOLO SI NO ESTY EN
		// EDICION DE MENSAJE del patron
		try {
			if (!f.isFlagEditCabecera()) {// si es nuevo o esta en edicion el
											// codigo mensaje
				int cont = service.getExisteCodigoMensaje(f.getCodigoMensaje(),
						f.getCampanhaProceso());
				if (cont > 0) {
					throw new Exception(
							this.getResourceMessage("mantenimientoMENPatronMensajeForm.existe.codigo.mensaje"));
				}
			}

			// Map map = BeanUtils.describe(f);
			// AjaxService ajaxService = (AjaxService) getBean("ajaxService");
			Map map = new HashMap();
			String valor = "";
			if (f.getTipoMensaje().equals("1"))
				valor = "V";
			else
				valor = "F";

			map.put("tipoMensaje", valor);
			// LISTA EL COMBO DE CONSIDERACION QUE DEPENDE DEL TIPO DE VARIABLE
			List listComboConsideracion = service.getConsideracion(map);
			List list = this.msgPatronConsideracionList;
			if (list == null)
				list = new ArrayList();

			Map condicion = getCondicion(f.getCodigoConsideracion(),
					listComboConsideracion);
			String tipo = (String) condicion.get("indTipo");

			Map bean = new HashMap();
			bean.put("codigoPais", codigoPais);
			bean.put("codigoMensaje", f.getCodigoMensaje());
			bean.put("campanhaProceso", f.getCampanhaProceso());
			bean.put("codigoConsideracion", f.getCodigoConsideracion());
			bean.put("indicadorTipo", tipo);
			bean.put("descripcion", (String) condicion.get("descripcionConRes"));
			bean.put(
					"abrev",
					getAbrevConRes(f.getCodigoConsideracion(),
							listComboConsideracion));
			bean.put("oidPeriodoCorpo", f.getOidPeriodoCorpo());
			bean.put("oidModulo", f.getCodigoModulo());
			bean.put("indicadorAccion", Constants.NUMERO_CERO);
			bean.put("condicion", "");
			bean.put("numReg", "0");
			if (Constants.MEN_TIPO_UNA_CONDICION.equals(tipo)) {
				bean.put("condicion1", f.getCondicion1S());
				bean.put("condicion", f.getCondicion1S());
				bean.put("numReg", "1");
			}
			if (Constants.MEN_TIPO_UNA_CONDICION_DOBLE.equals(tipo)) {
				if (StringUtils.isBlank(f.getCondicion1()))
					throw new Exception("Condición 1 es un campo requerido");
				if (StringUtils.isBlank(f.getCondicion2()))
					throw new Exception("Condición 2 es un campo requerido");

				bean.put("condicion",
						f.getCondicion1() + " , " + f.getCondicion2());
				bean.put("condicion1", f.getCondicion1());
				bean.put("condicion2", f.getCondicion2());
				bean.put("numReg", "1");
			}

			if (Constants.MEN_TIPO_LISTA_CONDICION.equals(tipo)
					|| Constants.MEN_TIPO_LISTA_EXTERNA_CONDICION.equals(tipo)) {
				if (Constants.MEN_CONRES_CODIGO_VENTA == Integer.parseInt(f
						.getCodigoConsideracion())) {
					List listVenta = this.codigoVentaList;
					// List temp = new ArrayList();
					// for (int i = 0; i < listVenta.size(); i++) {
					// Map dato = (Map) listVenta.get(i);
					// dato.remove("descripcion");
					// dato.remove("indAccion");
					// if (StringUtils.isNotBlank((String)
					// dato.get("codigoVenta")))
					// temp.add(dato);
					// }

					log.debug("listVentas " + listVenta.size());
					bean.put("listVenta", listVenta);
					bean.put("numReg", "" + listVenta.size());
					bean.put("condicion", "" + listVenta.size());
					bean.put("condicion1", "" + listVenta.size());
					bean.put("tipoConsideracionLista",
							Constants.MEN_TIPO_LISTA_CONDICION);
				}
				
				if (Constants.MEN_CONRES_CUV_FALTANTE == Integer.parseInt(f
						.getCodigoConsideracion())) {
					List listVenta = this.codigoCuvFaltanteList;

					log.debug("listCUVFaltante " + listVenta.size());
					bean.put("listCUVFaltante", listVenta);
					bean.put("numReg", "" + listVenta.size());
					bean.put("condicion", "" + listVenta.size());
					bean.put("condicion1", "" + listVenta.size());
					bean.put("tipoConsideracionLista", Constants.MEN_TIPO_LISTA_CONDICION);
				}

				if (Constants.MEN_CONRES_CODIGO_PREMIO == Integer.parseInt(f
						.getCodigoConsideracion())) {
					List listPremio = this.codigoPremioList;
					log.debug("listPremio >>>>>>> " + listPremio.size());
					bean.put("listPremio", listPremio);
					bean.put("numReg", "" + listPremio.size());
					bean.put("condicion", "" + listPremio.size());
					bean.put("condicion1", "" + listPremio.size());
					bean.put("tipoConsideracionLista",
							Constants.MEN_TIPO_LISTA_CONDICION);
				}

				if (Constants.MEN_CONRES_CUV_REEMPLAZO == Integer.parseInt(f
						.getCodigoConsideracion())) {
					List lista = this.codigoVentaList;
					List listaReemplazo = this.codigoVentaReemplazoList;
					log.debug("listVenta >>>>>>> " + lista.size());
					bean.put("listVenta", lista);
					bean.put("listVentaReemplazo", listaReemplazo);
					bean.put("numReg", "" + lista.size());
					bean.put("condicion", "" + lista.size());
					bean.put("condicion1", "" + lista.size());
					bean.put("tipoConsideracionLista",
							Constants.MEN_TIPO_LISTA_CONDICION);
				}

				if (Constants.MEN_CONRES_LISTA_CONSU == Integer.parseInt(f
						.getCodigoConsideracion())) {
					List consultoraList = this.consultoraList;
					List buzonLoteList = this.buzonLoteList;
					log.debug("consultoraList >>>>>>> " + consultoraList.size());
					bean.put("consultoraList", consultoraList);
					bean.put("buzonLoteList", buzonLoteList);
					bean.put("numReg", "" + consultoraList.size());
					bean.put("condicion", "" + consultoraList.size());
					bean.put("condicion1", "" + consultoraList.size());
					bean.put("tipoConsideracionLista",
							Constants.MEN_TIPO_LISTA_CONDICION);
				}

				if (Constants.MEN_CONRES_ESTATUS_CLIENTE == Integer.parseInt(f
						.getCodigoConsideracion())) {
					List lista = this.codigoEstatusList;
					for (int i = lista.size() - 1; i >= 0; i--) {
						Map m = (Map) lista.get(i);
						String indicadorAccion = (String) m
								.get("indicadorAccion");
						if (Constants.MEN_ESTADO_TMP_INSERTAR
								.equals(indicadorAccion)) {
							m.put("indicadorAccion", Constants.NRO_CERO);
						}
						if (Constants.MEN_ESTADO_TMP_ELIMINAR
								.equals(indicadorAccion)) {
							m.put("indicadorAccion", Constants.NUMERO_DOS);
						}
					}
					log.debug("listEstatus  " + lista.size());
					bean.put("listEstatus", lista);
					// actualizar los eliminados
					Iterator it = lista.iterator();
					int cont = lista.size();
					while (it.hasNext()) {
						Map aux = (Map) it.next();
						String indicadorAccion = (String) aux
								.get("indicadorAccion");
						if (Constants.NUMERO_DOS.equals(indicadorAccion)) {
							cont--;
						}
					}
					bean.put("numReg", "" + cont);
					bean.put("condicion", "" + cont);
					bean.put("condicion1", "" + cont);
					bean.put("tipoConsideracionLista",
							Constants.MEN_TIPO_LISTA_CONDICION);
					if (cont == 0)
						bean.put("indicadorAccion", Constants.NUMERO_DOS);
				}

				if (Constants.MEN_CONRES_CLASIFICACIONES_CLIENTE == Integer
						.parseInt(f.getCodigoConsideracion())) {
					List listC = this.clasificacionesList;
					for (int i = listC.size() - 1; i >= 0; i--) {
						Map m = (Map) listC.get(i);
						String indicadorAccion = (String) m
								.get("indicadorAccion");
						if (Constants.MEN_ESTADO_TMP_INSERTAR
								.equals(indicadorAccion)) {
							m.put("indicadorAccion", Constants.NRO_CERO);
						}
						if (Constants.MEN_ESTADO_TMP_ELIMINAR
								.equals(indicadorAccion)) {
							m.put("indicadorAccion", Constants.NUMERO_DOS);
						}
					}
					log.debug("listClasificaciones >>>>> " + listC.size());
					bean.put("listClasificaciones", listC);
					// actualizar los eliminados
				}

				if (Constants.MEN_CONRES_UNIDAD_ADM == Integer.parseInt(f
						.getCodigoConsideracion())) {
					List listU = this.unidadesList;
					log.debug("unidadesList >>>>>>> " + listU.size());
					bean.put("listUnidades", listU);
					// actualizar los eliminados

					for (int i = listU.size() - 1; i >= 0; i--) {
						Map m = (Map) listU.get(i);
						String indicadorAccion = (String) m
								.get("indicadorAccion");
						if (Constants.MEN_ESTADO_TMP_INSERTAR
								.equals(indicadorAccion)) {
							m.put("indicadorAccion", Constants.NRO_CERO);
						}
						if (Constants.MEN_ESTADO_TMP_ELIMINAR
								.equals(indicadorAccion)) {
							m.put("indicadorAccion", Constants.NUMERO_DOS);
						}
					}

					Iterator it = listU.iterator();
					int cont = listU.size();
					while (it.hasNext()) {
						Map aux = (Map) it.next();
						String indicadorAccion = (String) aux
								.get("indicadorAccion");
						if (Constants.NUMERO_DOS.equals(indicadorAccion)) {
							cont--;
						}
					}
					bean.put("numReg", "" + cont);
					bean.put("condicion", "" + cont);
					bean.put("condicion1", "" + cont);
					bean.put("tipoConsideracionLista",
							Constants.MEN_TIPO_LISTA_CONDICION);
					if (cont == 0)
						bean.put("indicadorAccion", Constants.NUMERO_DOS);
				}
			}
			List listR = this.msgPatronRestriccionList;
			bean.put("codigoRestriccion", f.getCodigoConsideracion());

			if (isValido(bean, list) && isValidoRest(bean, listR)) {// es
				// registro
				// valido cuando no se encuntra en la lista o se encuentra como
				// eliminado
				bean.put("codigoRestriccion", null);
				list.add(bean);
				// service.saveTemporalZona(bean);
			} else {
				throw new Exception(
						this.getResourceMessage("mantenimientoMENPatronMensajeForm.existe.consideracion.registro"));

			}

			f.setCondicion1("");
			f.setCondicion2("");
			f.setCondicion1S("");

			/* INI SA PER-SiCC-2012-0998 */
			f.setCodigoConsideracion("");
			/* FIN SA PER-SiCC-2012-0998 */

			f.setSelectedItemsConsideracion(null);
			f.setSelectedItemsRestriccion(null);
			this.msgPatronConsideracionList = list;
			this.dataTablePatronConsideracion = new DataTableModel(
					indAccionEliminar(msgPatronConsideracionList));
		} catch (Exception e) {
			// TODO: handle exception
			addError("Error", this.obtieneMensajeErrorException(e));
		}

	}

	public void tipoConsideracionRestriccion(TabChangeEvent event)
			throws Exception {
		String valorTab = event.getTab().getId();
		if (valorTab.equalsIgnoreCase("tabConsideracion"))
			this.codigoConsRestr = "C";
		else
			this.codigoConsRestr = "R";
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
	public void viewPopup(ActionEvent event) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'viewPopup' method");
		}
		MantenimientoMENPatronMensajeForm f = (MantenimientoMENPatronMensajeForm) this.formMantenimiento;
		botonEdit = false;
		try {

			if (StringUtils.isBlank(f.getDescripcionPatron()))
				throw new Exception(
						this.getResourceMessage("mantenimientoMENPatronMensaje.descripcionPatron.requerido"));
			if (StringUtils.isBlank(f.getCodigoModulo()))
				throw new Exception(
						this.getResourceMessage("mantenimientoMENPatronMensajeForm.codigoModulo.requerido"));
			if (StringUtils.isBlank(f.getCodigoMensaje()))
				throw new Exception(
						this.getResourceMessage("mantenimientoMENPatronMensajeForm.codigoMensaje.requerido"));
			if (StringUtils.isBlank(f.getCodigoSeccion()))
				throw new Exception(
						this.getResourceMessage("mantenimientoMENPatronMensajeForm.codigoSeccion.requerido"));
			if (StringUtils.isBlank(f.getDescripcionMensaje()))
				throw new Exception(
						this.getResourceMessage("mantenimientoMENPatronMensajeForm.descripcionMensaje.requerido"));
			/*
			if (StringUtils.isBlank(f.getTextoHtml())) {
				if (StringUtils.isBlank(f.getTextoMensaje()))
					throw new Exception(
							this.getResourceMessage("mantenimientoMENPatronMensajeForm.textoMensaje.requerido"));
			}
			*/
			Map map = new HashMap();
			if (f.getCodigoConsideracion() == null
					&& codigoConsRestr.equals("C"))
				throw new Exception(
						this.getResourceMessage("mantenimientoMENPatronMensajeForm.mensaje.seleccionarConsideracion"));
			if (f.getCodigoRestriccion() == null && codigoConsRestr.equals("R"))
				throw new Exception(
						this.getResourceMessage("mantenimientoMENPatronMensajeForm.mensaje.seleccionarRestriccion"));

			if (codigoConsRestr.equals("C"))
				map.put("codigo", f.getCodigoConsideracion());
			else
				map.put("codigo", f.getCodigoRestriccion());

			List consideracion = service.getConsideracion(map);
			Map data = (Map) consideracion.get(0);
			map.put("abrev", data.get("abrevConRes").toString());
			// VALIDACION SI SE REPITE UNA MISMA CONSIDERACION
			if (isValido(map, msgPatronConsideracionList)
					&& isValidoRest(map, msgPatronRestriccionList)) {// es

			} else
				throw new Exception(
						this.getResourceMessage("mantenimientoMENPatronMensajeForm.existe.consideracion.registro"));

			String codigoConsRest = data.get("codigoConRes").toString();// codConsRest
			String indicadorTipo = codigoConsRestr;
			String periodo = f.getCampanhaProceso();
			f.setCampanhaProceso(periodo);

			int codigo = Integer.parseInt(codigoConsRest);
			String ventana = "";
			switch (codigo) {
			case Constants.MEN_CONRES_CODIGO_PREMIO:
				this.codigoPremioList = new ArrayList();
				// mantenimientoMENPatronMensajeCodigoPremioPopup
				ventana = Constants.MEN_TIPO_CONSIDERACION
						.equals(indicadorTipo) ? "viewCodigoPremio"
						: "viewCodigoPremioRest";
				this.getRequestContext()
						.execute("PF('" + ventana + "').show()");
				break;
			case Constants.MEN_CONRES_CODIGO_VENTA:
				this.codigoVentaList = new ArrayList();
				comDetalleCodigoVenta = new DataTableModel(codigoVentaList);
				List list = new ArrayList();
				// mantenimientoMENPatronMensajeCodigoVentaPopup
				ventana = Constants.MEN_TIPO_CONSIDERACION
						.equals(indicadorTipo) ? "viewCodigoVenta"
						: "viewCodigoVentaRest";
				this.getRequestContext()
						.execute("PF('" + ventana + "').show()");

				Map dato = new HashMap();
				// dato.put("index", "0");
				dato.put("codigoVenta", "");
				dato.put("descripcion", "");
				dato.put("indAccion", "0");// 0:no hace nada 1:Inserta 2:Editar
				list.add(dato);
				this.codigoVentaList = list;
				comDetalleCodigoVenta = new DataTableModel(this.codigoVentaList);
				break;
			case Constants.MEN_CONRES_CUV_FALTANTE:
				this.codigoCuvFaltanteList = new ArrayList();
				this.comDetalleCodigoCuvFaltante = new DataTableModel(this.codigoCuvFaltanteList);
				List listCuv = new ArrayList();
				// mantenimientoMENPatronMensajeCodigoVentaPopup
				ventana = Constants.MEN_TIPO_CONSIDERACION.equals(indicadorTipo) ? "viewCodigoCuvFaltante"
						: "viewCodigoCuvFaltanteRest";
				this.getRequestContext().execute("PF('" + ventana + "').show()");

				Map datoCuv = new HashMap();
				// dato.put("index", "0");
				datoCuv.put("codigoCuvFaltante", "");
				datoCuv.put("descripcion", "");
				datoCuv.put("indAccion", "0");// 0:no hace nada 1:Inserta 2:Editar
				listCuv.add(datoCuv);
				this.codigoCuvFaltanteList = listCuv;
				this.comDetalleCodigoCuvFaltante = new DataTableModel(this.codigoCuvFaltanteList);
				break;
			case Constants.MEN_CONRES_CUV_REEMPLAZO:
				this.codigoVentaList = new ArrayList();
				this.codigoVentaReemplazoList = new ArrayList();
				// mantenimientoMENPatronMensajeCuvPopup
				ventana = Constants.MEN_TIPO_CONSIDERACION
						.equals(indicadorTipo) ? "viewCuvReemplazo"
						: "viewCuvReemplazoRest";
				this.getRequestContext()
						.execute("PF('" + ventana + "').show()");
				break;
			case Constants.MEN_CONRES_ESTATUS_CLIENTE:
				this.codigoEstatusList = new ArrayList();
				comDetalleEstatus = new DataTableModel(this.codigoEstatusList);
				// mantenimientoMENPatronMensajeEstatusPopup
				ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
				Map criteriaOperacion = new HashMap();
				criteriaOperacion.put("codigoPais", codigoPais);
				this.SACestadosList = reporteService.getListaGenerico(
						"getEstadoSaldoConsultora", criteriaOperacion);
				ventana = Constants.MEN_TIPO_CONSIDERACION
						.equals(indicadorTipo) ? "viewEstatusCliente"
						: "viewEstatusClienteRest";
				this.getRequestContext()
						.execute("PF('" + ventana + "').show()");
				break;
			case Constants.MEN_CONRES_LISTA_CONSU:
				this.consultoraList = new ArrayList();
				this.buzonLoteList = new ArrayList();
				comDetalleListaConsultora = new DataTableModel(
						this.buzonLoteList);
				// mantenimientoMENPatronMensajeListaPopup
				// session.setAttribute("numRegistrosConsultora", "");
				ventana = Constants.MEN_TIPO_CONSIDERACION
						.equals(indicadorTipo) ? "viewListaConsultora"
						: "viewListaConsultoraRest";
				this.getRequestContext()
						.execute("PF('" + ventana + "').show()");
				break;
			case Constants.MEN_CONRES_CLASIFICACIONES_CLIENTE:
				this.clasificacionesList = new ArrayList();
				this.comDetalleClasificacion = new DataTableModel(
						this.clasificacionesList);
				// mantenimientoMENPatronMensajeClasificacionesPopup
				InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
				// cargando en session la lista de concursos habilitados
				this.siccTipoClienteList = interfazSiCCService
						.getTiposClientesByCodigoISOOID(usuario.getIdioma()
								.getCodigoISO());

				f.setOidSubTipoCliente(Constants.OID_SUBTIPO_CLIENTE_DEFAULT);
				f.setOidTipoCliente(Constants.OID_TIPO_CLIENTE_DEFAULT);
				f.setOidTipoClasificacion(null);
				f.setOidClasificacion(null);

				recargarTipologiaClientes(f);
				ventana = Constants.MEN_TIPO_CONSIDERACION
						.equals(indicadorTipo) ? "viewClasificacionesCliente"
						: "viewClasificacionesClienteRest";
				this.getRequestContext()
						.execute("PF('" + ventana + "').show()");
				break;
			case Constants.MEN_CONRES_UNIDAD_ADM:
				this.unidadesList = new ArrayList();
				this.comDetalleUnidad = new DataTableModel(this.unidadesList);
				// mantenimientoMENPatronMensajeUnidadPopup
				reporteService = (ReporteService) getBean("scsicc.reporteService");
				criteriaOperacion = new HashMap();
				criteriaOperacion.put("codigoPais", codigoPais);
				this.siccRegionList = reporteService.getListaGenerico(
						"getRegionesByPais", criteriaOperacion);
				ventana = Constants.MEN_TIPO_CONSIDERACION
						.equals(indicadorTipo) ? "viewUnidadAdministrativa"
						: "viewUnidadAdministrativaRest";
				this.getRequestContext()
						.execute("PF('" + ventana + "').show()");
				break;

			case Constants.MEN_CONRES_CODIGO_PREMIO_REST:
				this.codigoPremioListR = new ArrayList();
				ventana = Constants.MEN_TIPO_CONSIDERACION
						.equals(indicadorTipo) ? "viewCodigoPremio"
						: "viewCodigoPremioRest";
				this.getRequestContext()
						.execute("PF('" + ventana + "').show()");
				break;
			case Constants.MEN_CONRES_CODIGO_VENTA_REST:
				// SE OBTIENE LA LISTA DE CODIGO VENTAS
				this.codigoVentaListR = new ArrayList();
				ventana = Constants.MEN_TIPO_CONSIDERACION
						.equals(indicadorTipo) ? "viewCodigoVenta"
						: "viewCodigoVentaRest";
				this.getRequestContext()
						.execute("PF('" + ventana + "').show()");
				break;
			case Constants.MEN_CONRES_CUV_REEMPLAZO_REST:
				this.codigoVentaListR = new ArrayList();
				this.codigoVentaReemplazoListR = new ArrayList();
				ventana = Constants.MEN_TIPO_CONSIDERACION
						.equals(indicadorTipo) ? "viewCuvReemplazo"
						: "viewCuvReemplazoRest";
				this.getRequestContext()
						.execute("PF('" + ventana + "').show()");
				break;
			case Constants.MEN_CONRES_ESTATUS_CLIENTE_REST:
				this.codigoEstatusListR = new ArrayList();
				reporteService = (ReporteService) getBean("scsicc.reporteService");
				criteriaOperacion = new HashMap();
				criteriaOperacion.put("codigoPais", codigoPais);
				SACestadosList = reporteService.getListaGenerico(
						"getEstadoSaldoConsultora", criteriaOperacion);
				ventana = Constants.MEN_TIPO_CONSIDERACION
						.equals(indicadorTipo) ? "viewEstatusCliente"
						: "viewEstatusClienteRest";
				this.getRequestContext()
						.execute("PF('" + ventana + "').show()");
				break;
			case Constants.MEN_CONRES_LISTA_CONSU_REST:
				this.consultoraListR = new ArrayList();
				this.buzonLoteListR = new ArrayList();
				// session.setAttribute("numRegistrosConsultoraR", "");
				ventana = Constants.MEN_TIPO_CONSIDERACION
						.equals(indicadorTipo) ? "viewListaConsultora"
						: "viewListaConsultoraRest";
				this.getRequestContext()
						.execute("PF('" + ventana + "').show()");
				break;
			case Constants.MEN_CONRES_CLASIFICACIONES_CLIENTE_REST:
				this.clasificacionesListR = new ArrayList();
				interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
				// cargando en session la lista de concursos habilitados
				siccTipoClienteList = interfazSiCCService
						.getTiposClientesByCodigoISOOID(usuario.getIdioma()
								.getCodigoISO());

				f.setOidSubTipoCliente(Constants.OID_SUBTIPO_CLIENTE_DEFAULT);
				f.setOidTipoCliente(Constants.OID_TIPO_CLIENTE_DEFAULT);
				f.setOidTipoClasificacion(null);
				f.setOidClasificacion(null);

				recargarTipologiaClientes(f);
				ventana = Constants.MEN_TIPO_CONSIDERACION
						.equals(indicadorTipo) ? "viewClasificacionesCliente"
						: "viewClasificacionesClienteRest";
				this.getRequestContext()
						.execute("PF('" + ventana + "').show()");
				break;
			case Constants.MEN_CONRES_UNIDAD_ADM_REST:
				this.unidadesListR = new ArrayList();
				reporteService = (ReporteService) getBean("scsicc.reporteService");
				criteriaOperacion = new HashMap();
				criteriaOperacion.put("codigoPais", codigoPais);
				this.siccRegionList = reporteService.getListaGenerico(
						"getRegionesByPais", criteriaOperacion);

				// request.getSession()
				// .setAttribute(
				// "codigoIdiomaISO",
				// getUsuario(request.getSession()).getIdioma()
				// .getCodigoISO());
				ventana = Constants.MEN_TIPO_CONSIDERACION
						.equals(indicadorTipo) ? "viewUnidadAdministrativa"
						: "viewUnidadAdministrativaRest";
				this.getRequestContext()
						.execute("PF('" + ventana + "').show()");
				break;
			default:
				insertConsideracion();
			}
		} catch (Exception e) {
			this.addError("Error", this.obtieneMensajeErrorException(e));
		}

	}

	public void editPopup(ActionEvent event) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'editPopup' method");
		}
		try {
			MantenimientoMENPatronMensajeForm f = (MantenimientoMENPatronMensajeForm) this.formMantenimiento;
			botonEdit = true;
			Map map = (Map) this.beanRegistroPatronConsideracion;
			if (beanRegistroPatronConsideracion == null)
				throw new Exception(
						this.getResourceMessage("errors.select.item"));

			if (map.get("indicadorTipo").toString().equals("S"))
				throw new Exception(
						this.getResourceMessage("mantenimientoMENPatronMensajeForm.noeditar.tipo.simple"));

			f.setCodigoConsideracion(map.get("codigoConsideracion").toString());
			String indicadorTipo = codigoConsRestr;
			String codigoMensaje = f.getCodigoMensaje();
			// String oidMensaje=(String)request.getParameter("oidMensaje");
			String periodo = f.getCampanhaProceso();
			f.setCampanhaProceso(periodo);

			String codigoConsRest = "";
			String oidMensaje = "";
			String oidModulo = "";
			String ventana = "";
			if (indicadorTipo.equals(Constants.MEN_TIPO_CONSIDERACION)) {
				List listConsi = this.msgPatronConsideracionList;
				codigoConsRest = (String) map.get("codigoConsideracion");
				oidMensaje = (String) map.get("oidMensaje");
				oidModulo = String.valueOf(map.get("oidModulo"));
			} else {
				List listConsi = this.msgPatronRestriccionList;
				// Map mapConRes = (Map)listConsi.get(index);
				// codigoConsRest = (String)mapConRes.get("codigoRestriccion");
				// oidMensaje =(String)mapConRes.get("oidMensaje");
				oidModulo = String.valueOf(map.get("oidModulo"));
			}
			int codigo = Integer.parseInt(codigoConsRest);
			Map criteria = new HashMap();
			criteria.put("codigoConsRest", codigoConsRest);
			criteria.put("codigoMensaje", codigoMensaje);
			criteria.put("oidMensaje", oidMensaje);
			criteria.put("indicadorTipo", indicadorTipo);
			criteria.put("campanhaProceso", f.getCampanhaProceso());
			List list = (List) service.getDetalleConsRest(criteria);
			Iterator it = list.iterator();
			String forward = "";
			List listC = null;
			// Map map = null;
			switch (codigo) {
			case Constants.MEN_CONRES_CODIGO_PREMIO:
				listC = this.codigoPremioList;
				if (listC == null) {
					listC = new ArrayList();

					while (it.hasNext()) {
						map = new HashMap();
						Map bean = (Map) it.next();
						map.put("codigoPremio", bean.get("valCondi1"));
						map.put("indicadorAccion", Constants.NRO_UNO);
						map.put("correlativoConsideracion", String.valueOf(bean
								.get("correlativoConsideracion")));
						listC.add(map);
					}
				}
				this.codigoPremioList = listC;
				ventana = Constants.MEN_TIPO_CONSIDERACION
						.equals(indicadorTipo) ? "viewCodigoPremio"
						: "viewCodigoPremioRest";
				this.getRequestContext()
						.execute("PF('" + ventana + "').show()");

				break;
			case Constants.MEN_CONRES_CODIGO_VENTA:			
				listC = (List) map.get("listVenta");
				if (listC == null) {
					listC = new ArrayList();

					while (it.hasNext()) {
						map = new HashMap();
						Map bean = (Map) it.next();
						String cod = (String) bean.get("valCondi1");
						map.put("codigoVenta", bean.get("valCondi1"));
						if (StringUtils.isBlank(cod))
							map.put("descripcion", "");
						else {
							LabelValueCUV dato = ajax.getCodigoVentaPrecio(cod,	f.getCampanhaProceso());
							map.put("descripcion", dato.getValue());
						}
						map.put("indicadorAccion", Constants.NRO_UNO);
						map.put("correlativoConsideracion", String.valueOf(bean.get("correlativoConsideracion")));
						listC.add(map);
					}
				}
				this.codigoVentaList = listC;
				Map map2 = (Map) codigoVentaList
						.get(codigoVentaList.size() - 1);
				if (StringUtils.isNotBlank((String) map2.get("codigoVenta"))) {
					Map map3 = new HashMap();
					map3.put("codigoVenta", "");
					codigoVentaList.add(map3);
				}
				generarIndex(codigoVentaList);
				comDetalleCodigoVenta = new DataTableModel(codigoVentaList);
				ventana = Constants.MEN_TIPO_CONSIDERACION
						.equals(indicadorTipo) ? "viewCodigoVenta"
						: "viewCodigoVentaRest";				
				this.getRequestContext()
						.execute("PF('" + ventana + "').show()");
				break;
			case Constants.MEN_CONRES_CUV_FALTANTE:
				// SE OBTIENE LA LISTA DE CODIGO VENTAS
				listC = (List) map.get("listCUVFaltante");
				if (listC == null) {
					listC = new ArrayList();

					while (it.hasNext()) {
						map = new HashMap();
						Map bean = (Map) it.next();
						String cod = (String) bean.get("valCondi1");
						map.put("codigoCuvFaltante", bean.get("valCondi1"));
						if (StringUtils.isBlank(cod))
							map.put("descripcion", "");
						else {
							LabelValueCUV dato = ajax.getCodigoVentaPrecio(cod,
									f.getCampanhaProceso());
							map.put("descripcion", dato.getValue());
						}
						map.put("indicadorAccion", Constants.NRO_UNO);
						map.put("correlativoConsideracion", String.valueOf(bean
								.get("correlativoConsideracion")));
						listC.add(map);
					}
				}
				this.codigoCuvFaltanteList = listC;
				Map map2Cuv = (Map) this.codigoCuvFaltanteList.get(codigoCuvFaltanteList.size() - 1);
				if (StringUtils.isNotBlank((String) map2Cuv.get("codigoCuvFaltante"))) {
					Map map3 = new HashMap();
					map3.put("codigoCuvFaltante", "");
					this.codigoCuvFaltanteList.add(map3);
				}
				generarIndex(this.codigoCuvFaltanteList);
				this.comDetalleCodigoCuvFaltante = new DataTableModel(this.codigoCuvFaltanteList);
				ventana = Constants.MEN_TIPO_CONSIDERACION.equals(indicadorTipo) ? "viewCodigoCuvFaltante"
						: "viewCodigoCuvFaltanteRest";
				this.getRequestContext().execute("PF('" + ventana + "').show()");
				break;
			case Constants.MEN_CONRES_CUV_REEMPLAZO:
				listC = this.codigoVentaList;
				;
				if (listC == null)
					listC = new ArrayList();

				List listCR = this.codigoVentaReemplazoList;
				if (listCR == null) {
					listCR = new ArrayList();

					while (it.hasNext()) {
						map = new HashMap();
						Map mapCr = new HashMap();
						Map bean = (Map) it.next();
						map.put("codigoVenta", bean.get("valCondi1"));
						map.put("indicadorAccion", Constants.NRO_UNO);
						map.put("correlativoConsideracion", String.valueOf(bean
								.get("correlativoConsideracion")));
						mapCr.put("codigoVenta", bean.get("valCondi2"));
						mapCr.put("indicadorAccion", Constants.NRO_UNO);
						mapCr.put("correlativoConsideracion", String
								.valueOf(bean.get("correlativoConsideracion")));
						listC.add(map);
						listCR.add(mapCr);
					}
				}
				this.codigoVentaList = listC;
				this.codigoVentaReemplazoList = listCR;
				ventana = Constants.MEN_TIPO_CONSIDERACION
						.equals(indicadorTipo) ? "viewCuvReemplazo"
						: "viewCuvReemplazoRest";

				this.getRequestContext()
						.execute("PF('" + ventana + "').show()");
				break;
			case Constants.MEN_CONRES_ESTATUS_CLIENTE:
				listC = (List) map.get("listEstatus");

				ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
				Map criteriaOperacion = new HashMap();
				criteriaOperacion.put("codigoPais", codigoPais);
				List listEstado = reporteService.getListaGenerico(
						"getEstadoSaldoConsultora", criteriaOperacion);
				SACestadosList = listEstado;
				if (listC == null) {
					listC = new ArrayList();

					while (it.hasNext()) {
						map = new HashMap();
						Map bean = (Map) it.next();
						map.put("codigoEstado", bean.get("valCondi1"));
						map.put("descripcionEstado",
								getDesEstado((String) bean.get("valCondi1"),
										listEstado));
						map.put("indicadorAccion", Constants.NRO_UNO);
						map.put("correlativoConsideracion", String.valueOf(bean
								.get("correlativoConsideracion")));
						listC.add(map);
					}
				}
				this.codigoEstatusList = listC;
				comDetalleEstatus = new DataTableModel(this.codigoEstatusList);
				ventana = Constants.MEN_TIPO_CONSIDERACION
						.equals(indicadorTipo) ? "viewEstatusCliente"
						: "viewEstatusClienteRest";
				this.getRequestContext()
						.execute("PF('" + ventana + "').show()");
				break;
			case Constants.MEN_CONRES_LISTA_CONSU:
				Map crit = new HashMap();
				this.consultoraList = (List) map.get("consultoraList");
				crit.put("oidMensaje", oidMensaje);
				crit.put("oidModulo", oidModulo);// relamnest es el oid
				crit.put("oidPeriodoCorpo", f.getOidPeriodoCorpo());
				crit.put("codigoConsRest", codigoConsRest);
				list = this.consultoraList;
				// session.removeAttribute("buzonLoteList");

				List buzonList = this.buzonLoteList;
				if (buzonList == null || buzonList.size() == 0) {
					buzonList = new ArrayList();
					if (list == null || list.size() == 0) {
						consultoraList = new ArrayList();

					} else {
						Map buz = new HashMap();
						buz.put("numLote", "");
						buz.put("numRegistro", list.size());
						buz.put("indicadorAccion", Constants.NRO_CERO);
						buzonList.add(buz);
					}
					List buzonLoteList = service.getLoteBuzon(crit);
					if (buzonLoteList.size() > 0)
						buzonList.addAll(buzonLoteList);
				} else {
					if (list.size() > 0) {
						Map buz = new HashMap();
						buz.put("numLote", "");
						buz.put("numRegistro", list.size());
						buz.put("indicadorAccion", Constants.NRO_CERO);
						// buscamos el lote vacio en posicion 0
						Map aux = (Map) buzonList.get(0);
						String numLote = (String) aux.get("numLote");
						if (StringUtils.isNotEmpty(numLote)) {
							buzonList.add(0, buz);
						}
					}
				}
				this.buzonLoteList = buzonList;
				this.comDetalleListaConsultora = new DataTableModel(
						indAccionEliminar(buzonLoteList));

				// int num =service.getRegistrosBuzon(crit) + (list!=null
				// ?list.size():0);
				// String numLote = service.getLoteBuzon(crit);
				// session.setAttribute("numRegistrosConsultora", num );
				// session.setAttribute("numLote", numLote);
				consultoraList = new ArrayList();
				ventana = Constants.MEN_TIPO_CONSIDERACION
						.equals(indicadorTipo) ? "viewListaConsultora"
						: "viewListaConsultoraRest";
				this.getRequestContext()
						.execute("PF('" + ventana + "').show()");

				break;
			case Constants.MEN_CONRES_CLASIFICACIONES_CLIENTE:
				listC = (List) map.get("listClasificaciones");
				InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
				// cargando en session la lista de concursos habilitados
				this.siccTipoClienteList = interfazSiCCService
						.getTiposClientesByCodigoISOOID(usuario.getIdioma()
								.getCodigoISO());

				f.setOidSubTipoCliente(Constants.OID_SUBTIPO_CLIENTE_DEFAULT);
				f.setOidTipoCliente(Constants.OID_TIPO_CLIENTE_DEFAULT);
				f.setOidTipoClasificacion(null);
				f.setOidClasificacion(null);
				recargarTipologiaClientes(f);
				if (listC == null) {
					listC = new ArrayList();

					while (it.hasNext()) {
						map = new HashMap();
						Map bean = (Map) it.next();
						String codigoTipoCliente = (String) bean
								.get("valCondi1");
						String codigoSubTipoCliente = (String) bean
								.get("valCondi2");
						String codigoTipoClasificacion = (String) bean
								.get("valCondi3");
						String codigoClasificacion = (String) bean
								.get("valCondi4");
						String oidTipoCliente = getOidTipoCliente(codigoTipoCliente);
						String oidSubTipoCliente = getOidSubtipoCliente(
								codigoTipoCliente, codigoSubTipoCliente);
						String oidTipoClasificacion = getOidTipoClasificacion(
								codigoTipoCliente, codigoSubTipoCliente,
								codigoTipoClasificacion);
						String oidClasificacion = getOidClasificacion(
								codigoTipoCliente, codigoSubTipoCliente,
								codigoTipoClasificacion, codigoClasificacion);

						map.put("oidTipoCliente", oidTipoCliente);
						map.put("oidSubTipoCliente", oidSubTipoCliente);
						map.put("oidTipoClasificacion", oidTipoClasificacion);
						map.put("oidClasificacion", oidClasificacion);
						map.put("codigoTipoCliente", codigoTipoCliente);
						map.put("codigoSubTipoCliente", codigoSubTipoCliente);
						map.put("codigoTipoClasificacion",
								codigoTipoClasificacion);
						map.put("codigoClasificacion", codigoClasificacion);
						map.put("descripcionTipoCliente",
								getDesTipocliente(oidTipoCliente));
						map.put("descripcionSubTipoCliente",
								getDesSubtipoCliente(oidTipoCliente,
										oidSubTipoCliente));
						map.put("descripcionTipoClasificacion",
								getDesTipoClasificacion(oidTipoCliente,
										oidSubTipoCliente, oidTipoClasificacion));
						map.put("descripcionClasificacion",
								getClasificacion(oidTipoCliente,
										oidSubTipoCliente,
										oidTipoClasificacion, oidClasificacion));
						map.put("indicadorAccion", Constants.NRO_UNO);
						map.put("correlativoConsideracion", String.valueOf(bean
								.get("correlativoConsideracion")));
						listC.add(map);
					}
				}
				this.clasificacionesList = listC;
				this.comDetalleClasificacion = new DataTableModel(listC);
				ventana = Constants.MEN_TIPO_CONSIDERACION
						.equals(indicadorTipo) ? "viewClasificacionesCliente"
						: "viewClasificacionesClienteRest";
				this.getRequestContext()
						.execute("PF('" + ventana + "').show()");

				break;
			case Constants.MEN_CONRES_UNIDAD_ADM:
				listC = (List) map.get("listUnidades");

				reporteService = (ReporteService) getBean("scsicc.reporteService");
				criteriaOperacion = new HashMap();
				criteriaOperacion.put("codigoPais", codigoPais);
				siccRegionList = reporteService.getListaGenerico(
						"getRegionesByPais", criteriaOperacion);

				ventana = Constants.MEN_TIPO_CONSIDERACION
						.equals(indicadorTipo) ? "viewUnidadAdministrativa"
						: "viewUnidadAdministrativaRest";
				this.getRequestContext()
						.execute("PF('" + ventana + "').show()");
				if (listC == null || listC.size() == 0) {
					listC = new ArrayList();
					List listMensajePatron = this.msgMensajePatronList;
					for (int i = 0; i < listMensajePatron.size(); i++) {
						Map aux1 = (Map) listMensajePatron.get(i);
						String codigoMensajeAux = (String) aux1
								.get("codigoMensaje");
						if (codigoMensaje.trim().compareTo(
								codigoMensajeAux.trim()) == 0) {
							List listConsi1 = (List) aux1.get("listConsi");
							if (listConsi1 != null && listConsi1.size() > 0) {
								for (int j = 0; j < listConsi1.size(); j++) {
									Map bean = (Map) listConsi1.get(j);
									listC = (List) bean.get("listUnidades");
								}
							}
							break;
						}
					}
					if (listC == null || listC.size() == 0) {
						listC = new ArrayList();
						while (it.hasNext()) {
							map = new HashMap();
							Map bean = (Map) it.next();
							String codigoRegion = (String) bean
									.get("valCondi1");
							String codigoZona = (String) bean.get("valCondi2");
							String codigoSeccion = (String) bean
									.get("valCondi3");
							String codigoTerritorio = (String) bean
									.get("valCondi4");

							map.put("codigoRegion", codigoRegion);
							map.put("codigoZona", codigoZona);
							map.put("codigoSeccion", codigoSeccion);
							map.put("codigoTerritorio", codigoTerritorio);
							map.put("descripcionRegion",
									getDesRegion(codigoRegion));
							map.put("descripcionZona",
									getDesZona(codigoRegion, codigoZona));
							map.put("descripcionSeccion",
									getDesSeccion(codigoRegion, codigoZona,
											codigoSeccion));
							map.put("descripcionTerritorio",
									getDesTerr(codigoRegion, codigoZona,
											codigoSeccion, codigoTerritorio));
							map.put("indicadorAccion", Constants.NRO_UNO);
							map.put("correlativoConsideracion", String
									.valueOf(bean
											.get("correlativoConsideracion")));
							listC.add(map);
						}
					}
				}
				generarIndex(listC);
				this.unidadesList = listC;
				this.comDetalleUnidad = new DataTableModel(this.unidadesList);
				break;

			case Constants.MEN_CONRES_CODIGO_PREMIO_REST:
				listC = this.codigoPremioListR;
				if (listC == null) {
					listC = new ArrayList();

					while (it.hasNext()) {
						map = new HashMap();
						Map bean = (Map) it.next();
						map.put("codigoPremio", bean.get("valCondi1"));
						map.put("indicadorAccion", Constants.NRO_UNO);
						map.put("correlativoConsideracion", String.valueOf(bean
								.get("correlativoConsideracion")));
						listC.add(map);
					}
				}
				this.codigoPremioListR = listC;
				ventana = Constants.MEN_TIPO_CONSIDERACION
						.equals(indicadorTipo) ? "viewCodigoPremio"
						: "viewCodigoPremioRest";
				this.getRequestContext()
						.execute("PF('" + ventana + "').show()");
				break;
			case Constants.MEN_CONRES_CODIGO_VENTA_REST:
				// SE OBTIENE LA LISTA DE CODIGO VENTAS
				listC = this.codigoVentaListR;
				if (listC == null) {
					listC = new ArrayList();

					while (it.hasNext()) {
						map = new HashMap();
						Map bean = (Map) it.next();
						map.put("codigoVenta", bean.get("valCondi1"));
						map.put("indicadorAccion", Constants.NRO_UNO);
						map.put("correlativoConsideracion", String.valueOf(bean
								.get("correlativoConsideracion")));
						listC.add(map);
					}
				}
				this.codigoVentaListR = listC;
				ventana = Constants.MEN_TIPO_CONSIDERACION
						.equals(indicadorTipo) ? "viewCodigoVenta"
						: "viewCodigoVentaRest";
				this.getRequestContext()
						.execute("PF('" + ventana + "').show()");
				break;
			case Constants.MEN_CONRES_CUV_REEMPLAZO_REST:
				listC = this.codigoVentaListR;
				listCR = this.codigoVentaReemplazoListR;
				if (listCR == null) {
					listCR = new ArrayList();
					listC = new ArrayList();
					while (it.hasNext()) {
						map = new HashMap();
						Map mapCr = new HashMap();
						Map bean = (Map) it.next();
						map.put("codigoVenta", bean.get("valCondi1"));
						map.put("indicadorAccion", Constants.NRO_UNO);
						mapCr.put("codigoVenta", bean.get("valCondi2"));
						mapCr.put("indicadorAccion", Constants.NRO_UNO);
						map.put("correlativoConsideracion", String.valueOf(bean
								.get("correlativoConsideracion")));
						mapCr.put("correlativoConsideracion", String
								.valueOf(bean.get("correlativoConsideracion")));
						listC.add(map);
						listCR.add(mapCr);
					}
				}
				this.codigoVentaListR = listC;
				this.codigoVentaReemplazoListR = listCR;
				ventana = Constants.MEN_TIPO_CONSIDERACION
						.equals(indicadorTipo) ? "viewCuvReemplazo"
						: "viewCuvReemplazoRest";
				this.getRequestContext()
						.execute("PF('" + ventana + "').show()");
				break;
			case Constants.MEN_CONRES_ESTATUS_CLIENTE_REST:
				listC = this.codigoEstatusListR;

				reporteService = (ReporteService) getBean("scsicc.reporteService");
				criteriaOperacion = new HashMap();
				criteriaOperacion.put("codigoPais", codigoPais);
				listEstado = reporteService.getListaGenerico(
						"getEstadoSaldoConsultora", criteriaOperacion);
				this.SACestadosList = listEstado;
				if (listC == null) {
					listC = new ArrayList();
					while (it.hasNext()) {
						map = new HashMap();
						Map bean = (Map) it.next();
						map.put("codigoEstado", bean.get("valCondi1"));
						map.put("descripcionEstado",
								getDesEstado((String) bean.get("valCondi1"),
										listEstado));
						map.put("indicadorAccion", Constants.NRO_UNO);
						map.put("correlativoConsideracion", String.valueOf(bean
								.get("correlativoConsideracion")));
						listC.add(map);
					}
				}
				this.codigoEstatusListR = listC;

				ventana = Constants.MEN_TIPO_CONSIDERACION
						.equals(indicadorTipo) ? "viewEstatusCliente"
						: "viewEstatusClienteRest";
				this.getRequestContext()
						.execute("PF('" + ventana + "').show()");
				break;
			case Constants.MEN_CONRES_LISTA_CONSU_REST:
				crit = new HashMap();
				crit.put("oidMensaje", oidMensaje);
				crit.put("oidModulo", oidModulo);// relamnest es el oid
				crit.put("oidPeriodoCorpo", f.getOidPeriodoCorpo());
				crit.put("codigoConsRest", codigoConsRest);
				list = this.consultoraListR;

				buzonList = this.buzonLoteListR;
				if (buzonList == null || buzonList.size() == 0) {
					buzonList = new ArrayList();
					if (list == null || list.size() == 0) {
						this.consultoraListR = new ArrayList();

					} else {
						Map buz = new HashMap();
						buz.put("numLote", "");
						buz.put("numRegistro", list.size());
						buz.put("indicadorAccion", Constants.NRO_CERO);
						buzonList.add(buz);
					}
					List buzonLoteList = service.getLoteBuzon(crit);
					if (buzonLoteList.size() > 0)
						buzonList.addAll(buzonLoteList);
				} else {
					if (list.size() > 0) {
						Map buz = new HashMap();
						buz.put("numLote", "");
						buz.put("numRegistro", list.size());
						buz.put("indicadorAccion", Constants.NRO_CERO);
						// buscamos el lote vacio en posicion 0
						Map aux = (Map) buzonList.get(0);
						String numLote = (String) aux.get("numLote");
						if (StringUtils.isNotEmpty(numLote)) {
							buzonList.add(0, buz);
						}
					}
				}
				this.buzonLoteListR = buzonList;
				// num =service.getRegistrosBuzon(crit) + (list!=null
				// ?list.size():0);
				// numLote = service.getLoteBuzon(crit);
				// session.setAttribute("numRegistrosConsultoraR", num );
				// session.setAttribute("numLoteR", numLote);
				ventana = Constants.MEN_TIPO_CONSIDERACION
						.equals(indicadorTipo) ? "viewListaConsultora"
						: "viewListaConsultoraRest";
				this.getRequestContext()
						.execute("PF('" + ventana + "').show()");
				break;
			case Constants.MEN_CONRES_CLASIFICACIONES_CLIENTE_REST:
				listC = this.clasificacionesListR;
				interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
				// cargando en session la lista de concursos habilitados
				this.siccTipoClienteList = interfazSiCCService
						.getTiposClientesByCodigoISOOID(usuario.getIdioma()
								.getCodigoISO());

				f.setOidSubTipoCliente(Constants.OID_SUBTIPO_CLIENTE_DEFAULT);
				f.setOidTipoCliente(Constants.OID_TIPO_CLIENTE_DEFAULT);
				f.setOidTipoClasificacion(null);
				f.setOidClasificacion(null);
				recargarTipologiaClientes(f);
				if (listC == null) {
					listC = new ArrayList();
					while (it.hasNext()) {
						map = new HashMap();
						Map bean = (Map) it.next();
						String codigoTipoCliente = (String) bean
								.get("valCondi1");
						String codigoSubTipoCliente = (String) bean
								.get("valCondi2");
						String codigoTipoClasificacion = (String) bean
								.get("valCondi3");
						String codigoClasificacion = (String) bean
								.get("valCondi4");
						String oidTipoCliente = getOidTipoCliente(codigoTipoCliente);
						String oidSubTipoCliente = getOidSubtipoCliente(
								codigoTipoCliente, codigoSubTipoCliente);
						String oidTipoClasificacion = getOidTipoClasificacion(
								codigoTipoCliente, codigoSubTipoCliente,
								codigoTipoClasificacion);
						String oidClasificacion = getOidClasificacion(
								codigoTipoCliente, codigoSubTipoCliente,
								codigoTipoClasificacion, codigoClasificacion);

						map.put("oidTipoCliente", oidTipoCliente);
						map.put("oidSubTipoCliente", oidSubTipoCliente);
						map.put("oidTipoClasificacion", oidTipoClasificacion);
						map.put("oidClasificacion", oidClasificacion);
						map.put("codigoTipoCliente", codigoTipoCliente);
						map.put("codigoSubTipoCliente", codigoSubTipoCliente);
						map.put("codigoTipoClasificacion",
								codigoTipoClasificacion);
						map.put("codigoClasificacion", codigoClasificacion);
						map.put("descripcionTipoCliente",
								getDesTipocliente(oidTipoCliente));
						map.put("descripcionSubTipoCliente",
								getDesSubtipoCliente(oidTipoCliente,
										oidSubTipoCliente));
						map.put("descripcionTipoClasificacion",
								getDesTipoClasificacion(oidTipoCliente,
										oidSubTipoCliente, oidTipoClasificacion));
						map.put("descripcionClasificacion",
								getClasificacion(oidTipoCliente,
										oidSubTipoCliente,
										oidTipoClasificacion, oidClasificacion));
						map.put("indicadorAccion", Constants.NRO_UNO);
						map.put("correlativoConsideracion", String.valueOf(bean
								.get("correlativoConsideracion")));
						listC.add(map);
					}
				}
				this.clasificacionesListR = listC;
				ventana = Constants.MEN_TIPO_CONSIDERACION
						.equals(indicadorTipo) ? "viewClasificacionesCliente"
						: "viewClasificacionesClienteRest";
				this.getRequestContext()
						.execute("PF('" + ventana + "').show()");
				break;
			case Constants.MEN_CONRES_UNIDAD_ADM_REST:
				listC = this.unidadesListR;
				reporteService = (ReporteService) getBean("scsicc.reporteService");
				criteriaOperacion = new HashMap();
				criteriaOperacion.put("codigoPais", codigoPais);
				this.siccRegionList = reporteService.getListaGenerico(
						"getRegionesByPais", criteriaOperacion);

				if (listC == null) {
					listC = new ArrayList();
					while (it.hasNext()) {
						map = new HashMap();
						Map bean = (Map) it.next();
						String codigoRegion = (String) bean.get("valCondi1");
						String codigoZona = (String) bean.get("valCondi2");
						String codigoSeccion = (String) bean.get("valCondi3");
						String codigoTerritorio = (String) bean
								.get("valCondi4");

						map.put("codigoRegion", codigoRegion);
						map.put("codigoZona", codigoZona);
						map.put("codigoSeccion", codigoSeccion);
						map.put("codigoTerritorio", codigoTerritorio);
						map.put("descripcionRegion", getDesRegion(codigoRegion));
						map.put("descripcionZona",
								getDesZona(codigoRegion, codigoZona));
						map.put("descripcionSeccion",
								getDesSeccion(codigoRegion, codigoZona,
										codigoSeccion));
						map.put("descripcionTerritorio",
								getDesTerr(codigoRegion, codigoZona,
										codigoSeccion, codigoTerritorio));
						map.put("indicadorAccion", Constants.NRO_UNO);
						map.put("correlativoConsideracion", String.valueOf(bean
								.get("correlativoConsideracion")));
						listC.add(map);
					}
				}
				unidadesListR = listC;
				ventana = Constants.MEN_TIPO_CONSIDERACION
						.equals(indicadorTipo) ? "viewUnidadAdministrativa"
						: "viewUnidadAdministrativaRest";
				this.getRequestContext()
						.execute("PF('" + ventana + "').show()");

				break;
			}

		} catch (Exception e) {
			// TODO: handle exception
			addError("Error", this.obtieneMensajeErrorException(e));
		}

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

	private String getDesZona(String codigoRegion, String codigoZona) {
		Map criteria = new HashMap();
		try {
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
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "";
	}

	private String getDesSeccion(String codigoRegion, String codigoZona,
			String codigoSeccion) {
		Map criteria = new HashMap();
		try {
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
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "";
	}

	private String getDesTerr(String codigoRegion, String codigoZona,
			String codigoSeccion, String codigoTerritorio) {
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

	private String getOidTipoCliente(String codigoTipoCliente) {
		String descp = getDesTipoclienteByCodigo(codigoTipoCliente);
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
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

	private String getOidSubtipoCliente(String codigoTipoCliente,
			String codigoSubTipoCliente) {
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

	private String getOidTipoClasificacion(String codigoTipoCliente,
			String codigoSubTipoCliente, String codigoTipoClasificacion) {
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

	private String getOidClasificacion(String codigoTipoCliente,
			String codigoSubTipoCliente, String codigoTipoClasificacion,
			String codigoClasificacion) {

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

	private String getDesTipoclienteByCodigo(String codigoTipoCliente) {
		log.debug("getDesTipoclienteByCodigo codigoTipoCliente "
				+ codigoTipoCliente);
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
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

	private void recargarTipologiaClientes(MantenimientoMENPatronMensajeForm f) {
		log.debug("recargarTipologiaClientes ");

		ArrayList temp = new ArrayList();
		temp = new ArrayList();
		temp.add(f.getOidTipoCliente());
		this.siccSubTipoClienteList = ajax
				.getSubTiposClientesPorPaisTipoClientesOID(usuario.getIdioma()
						.getCodigoISO(), temp);

		temp = new ArrayList();
		temp.add(f.getOidSubTipoCliente());
		LabelValue[] listTiposClasificiones = ajax
				.getTiposClasificacionesByCriteriaMultipleOID(usuario
						.getIdioma().getCodigoISO(),
						Constants.OID_TIPO_CLIENTE_DEFAULT, temp);
		this.siccTipoClasificacionList = listTiposClasificiones;

		if (f.getOidTipoClasificacion() == null)
			f.setOidTipoClasificacion(listTiposClasificiones[0].getValue());

		ArrayList temp2 = new ArrayList();
		temp2 = new ArrayList();
		temp2.add(f.getOidTipoClasificacion());
		this.siccClasificacionList = ajax
				.getClasificacionesByCriteriaMultipleOID(usuario.getIdioma()
						.getCodigoISO(), Constants.OID_TIPO_CLIENTE_DEFAULT,
						temp, temp2);

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

	public void deleteConsideracion(ActionEvent event) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'deleteConsideracion' method");
		}

		MantenimientoMENPatronMensajeForm f = (MantenimientoMENPatronMensajeForm) this.formMantenimiento;
		Map map = (Map) this.beanRegistroPatronConsideracion;
		map.put("indicadorAccion", Constants.NUMERO_DOS);
		botonEdit = false;
		try {
			this.dataTablePatronConsideracion = new DataTableModel(
					indAccionEliminar(msgPatronConsideracionList));
		} catch (Exception e) {
			addError("Error", this.obtieneMensajeErrorException(e));
		}

	}

	// muestra la lista que no sea accion 2 (Eliminado)
	public List indAccionEliminar(List getList) {
		List lista = new ArrayList();
		for (int i = 0; i < getList.size(); i++) {
			Map map2 = (Map) getList.get(i);
			String ind = map2.get("indicadorAccion").toString();
			if (!ind.equals(Constants.NRO_DOS))
				lista.add(map2);
		}
		return lista;
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

	public void loadSubTiposClientes(ValueChangeEvent val) {
		MantenimientoMENPatronMensajeForm f = (MantenimientoMENPatronMensajeForm) this.formMantenimiento;
		String codCliente = (String) val.getNewValue();
		ArrayList values = new ArrayList<String>(Arrays.asList(codCliente));
		this.siccSubTipoClienteList = ajax
				.getSubTiposClientesPorPaisTipoClientesOID(usuario.getIdioma()
						.getCodigoISO(), values);
		this.siccTipoClasificacionList = null;
		this.siccClasificacionList = null;
	}

	public void loadTiposClasificaciones(ValueChangeEvent val) {
		MantenimientoMENPatronMensajeForm f = (MantenimientoMENPatronMensajeForm) this.formMantenimiento;
		String codSubTipoCliente = (String) val.getNewValue();
		ArrayList values = new ArrayList<String>(
				Arrays.asList(codSubTipoCliente));
		this.siccTipoClasificacionList = ajax
				.getTiposClasificacionesByCriteriaMultipleOID(usuario
						.getIdioma().getCodigoISO(), f.getOidTipoCliente(),
						values);
		this.siccClasificacionList = null;
	}

	public void loadClasificaciones(ValueChangeEvent val) {
		MantenimientoMENPatronMensajeForm f = (MantenimientoMENPatronMensajeForm) this.formMantenimiento;
		String valor = (String) val.getNewValue();
		ArrayList codSubTipoCliente = new ArrayList<String>(Arrays.asList(f
				.getOidSubTipoCliente()));
		ArrayList codTipoClasificacion = new ArrayList<String>(
				Arrays.asList(valor));
		this.siccClasificacionList = ajax
				.getClasificacionesByCriteriaMultipleOID(usuario.getIdioma()
						.getCodigoISO(), f.getOidTipoCliente(),
						codSubTipoCliente, codTipoClasificacion);
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
	public void savePopup(ActionEvent event) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'savePopup' method");
		}
		//
		MantenimientoMENPatronMensajeForm f = (MantenimientoMENPatronMensajeForm) this.formMantenimiento;
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		String ventana = "";
		//
		String codigoConsRest = "";
		if (codigoConsRestr.equals("C"))
			codigoConsRest = f.getCodigoConsideracion();
		else
			codigoConsRest = f.getCodigoRestriccion();

		String indicadorTipo = codigoConsRestr;
		// indicadorCerrarVentana

		// Asignamos al codigo del periodo el valor por defecto
		Map crit = new HashMap();
		crit.put("codigoPais", codigoPais);
		crit.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		crit.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
		crit.put("codigoISO", usuario.getIdioma().getCodigoISO());

		// recuperamos el oid Pais
		String oidPais = clienteService.getOidPais(crit);
		//

		int codigo = Integer.parseInt(codigoConsRest);
		List list = null;
		switch (codigo) {
		case Constants.MEN_CONRES_CODIGO_PREMIO:
			list = new ArrayList();
			String[] codigoPremio;
			// String[] codigoPremio = request
			// .getParameterValues("codigoPremioItems");
			// for (int i = 0; i < codigoPremio.length; i++) {
			// if (StringUtils.isNotEmpty(codigoPremio[i])) {
			// Map map = new HashMap();
			// map.put("codigoPremio", codigoPremio[i]);
			// list.add(map);
			// }
			// }
			this.codigoPremioList = list;
			ventana = Constants.MEN_TIPO_CONSIDERACION.equals(indicadorTipo) ? "viewCodigoPremio"
					: "viewCodigoPremioRest";
			this.getRequestContext().execute("PF('" + ventana + "').show()");
			break;
		case Constants.MEN_CONRES_CODIGO_VENTA:
			list = new ArrayList();
			for (int i = 0; i < codigoVentaList.size(); i++) {
				Map data = (Map) codigoVentaList.get(i);
				String codigoVenta = (String) data.get("codigo");
				if (StringUtils.isNotEmpty(codigoVenta)) {
					Map map = new HashMap();
					map.put("codigoVenta", codigoVenta);
					list.add(map);
				}
			}
			this.codigoVentaList = list;
			break;
		case Constants.MEN_CONRES_CUV_FALTANTE:
			list = new ArrayList();
			for (int i = 0; i < codigoCuvFaltanteList.size(); i++) {
				Map data = (Map) codigoCuvFaltanteList.get(i);
				String codigoVenta = (String) data.get("codigo");
				if (StringUtils.isNotEmpty(codigoVenta)) {
					Map map = new HashMap();
					map.put("codigoCuvFaltante", codigoVenta);
					list.add(map);
				}
			}
			this.codigoCuvFaltanteList = list;
			break;
		case Constants.MEN_CONRES_CUV_REEMPLAZO:
			list = new ArrayList();
			for (int i = 0; i < codigoVentaList.size(); i++) {
				Map data = (Map) codigoVentaList.get(i);
				if (StringUtils.isNotEmpty(data.get("codigoVentas").toString())) {
					Map map = new HashMap();
					map.put("codigoVenta", data.get("codigoVentas").toString());
					list.add(map);
				}
			}
			this.codigoVentaList = list;

			list = new ArrayList();

			for (int i = 0; i < codigoVentaList.size(); i++) {
				Map data = (Map) codigoVentaList.get(i);
				if (StringUtils.isNotEmpty(data.get("codigoVentas").toString())) {
					Map map = new HashMap();
					map.put("codigoVenta", data.get("codigoVentas").toString());
					list.add(map);
				}
			}
			this.codigoVentaReemplazoList = list;
			ventana = Constants.MEN_TIPO_CONSIDERACION.equals(indicadorTipo) ? "viewCuvReemplazo"
					: "viewCuvReemplazoRest";
			this.getRequestContext().execute("PF('" + ventana + "').show()");
			break;
		case Constants.MEN_CONRES_ESTATUS_CLIENTE:

			list = this.codigoEstatusList;
			for (int i = list.size() - 1; i >= 0; i--) {
				Map m = (Map) list.get(i);
				String indicadorAccion = (String) m.get("indicadorAccion");
				if (Constants.MEN_ESTADO_TMP_INSERTAR.equals(indicadorAccion)) {
					m.put("indicadorAccion", Constants.NRO_CERO);
				}
				if (Constants.MEN_ESTADO_TMP_ELIMINAR.equals(indicadorAccion)) {
					m.put("indicadorAccion", Constants.NUMERO_DOS);
				}
			}
			for (int i = 0; i < msgPatronConsideracionList.size(); i++) {
				Map map = (Map) msgPatronConsideracionList.get(i);
				String conRes = map.get("codigoConsideracion").toString();
				if (conRes.equals(codigoConsRest)) {
					map.put("indicadorModificadoPopup", "1");
					map.put("listEstatus", list);
				}
			}
			break;
		case Constants.MEN_CONRES_LISTA_CONSU:
			String flagArchivo = "";
			// String flagArchivo = (String)
			// request.getParameter("flagArchivo");
			if (flagArchivo.equals(Constants.NRO_CERO)) {

				list = this.buzonLoteList;
				if (list != null) {
					for (int i = list.size() - 1; i >= 0; i--) {
						Map m = (Map) list.get(i);
						String indicadorAccion = (String) m
								.get("indicadorAccion");
						if (Constants.MEN_ESTADO_TMP_INSERTAR
								.equals(indicadorAccion)) {
							m.put("indicadorAccion", Constants.NRO_CERO);
						}
						if (Constants.MEN_ESTADO_TMP_ELIMINAR
								.equals(indicadorAccion)) {
							m.put("indicadorAccion", Constants.NUMERO_DOS);
						}
					}
				}

			} else {

				ProcesoZONActualizarUnidadesGeograficasService serviceUnidad = (ProcesoZONActualizarUnidadesGeograficasService) getBean("spusicc.procesoZONActualizarUnidadesGeograficasService");
				f.setDirectorioTemporal(serviceUnidad
						.obtenerPathUpload(codigoPais));
				uploadArchivo(f);
				Map criteria = new HashMap();
				// String oidPais = String.valueOf(pais.getOidPais());
				criteria.put("directorioTemporal", f.getDirectorioTemporal());
				criteria.put("nombreArchivo", f.getNombreArchivo());
				criteria.put("oidPais", oidPais);
				criteria.put("indicadorTipo", indicadorTipo);
				criteria.put("oidPeriodoCorpo", f.getOidPeriodoCorpo());// vacio
																		// pantalla
																		// popup
																		// no
																		// existe
																		// campo
				criteria.put("oidModulo", f.getCodigoModulo());// vacio pantalla
																// popup no
																// existe campo
				criteria.put("login", usuario.getLogin());
				boolean isValido = service.validarArchivoExcel(criteria);
				if (isValido) {
					service.executeCargaArchivoExcel(criteria);
					String mensajeResultado = (String) criteria
							.get("mensajeResultado");
					if (StringUtils.isNotEmpty(mensajeResultado)) {
						log.debug("mensajeResultado " + mensajeResultado);
					} else {
						List consultoraList = (List) criteria
								.get("consultoraList");
						this.consultoraList = consultoraList;
					}

				}
				borrarFichero(f.getDirectorioTemporal(), f.getNombreArchivo());
			}
			ventana = Constants.MEN_TIPO_CONSIDERACION.equals(indicadorTipo) ? "viewListaConsultora"
					: "viewListaConsultoraRest";
			this.getRequestContext().execute("PF('" + ventana + "').show()");
			break;
		case Constants.MEN_CONRES_CLASIFICACIONES_CLIENTE:
			list = this.clasificacionesList;
			for (int i = list.size() - 1; i >= 0; i--) {
				Map m = (Map) list.get(i);
				String indicadorAccion = (String) m.get("indicadorAccion");
				if (Constants.MEN_ESTADO_TMP_INSERTAR.equals(indicadorAccion)) {
					m.put("indicadorAccion", Constants.NRO_CERO);
				}
				if (Constants.MEN_ESTADO_TMP_ELIMINAR.equals(indicadorAccion)) {
					m.put("indicadorAccion", Constants.NUMERO_DOS);
				}
			}
			for (int i = 0; i < msgPatronConsideracionList.size(); i++) {
				Map map = (Map) msgPatronConsideracionList.get(i);
				String conRes = map.get("codigoConsideracion").toString();
				if (conRes.equals(codigoConsRest)) {
					map.put("indicadorModificadoPopup", "1");
					map.put("listClasificaciones", list);
				}
			}
			break;
		case Constants.MEN_CONRES_UNIDAD_ADM:
			list = this.unidadesList;
			for (int i = list.size() - 1; i >= 0; i--) {
				Map m = (Map) list.get(i);
				String indicadorAccion = (String) m.get("indicadorAccion");
				if (Constants.MEN_ESTADO_TMP_INSERTAR.equals(indicadorAccion)) {
					m.put("indicadorAccion", Constants.NRO_CERO);
				}
				if (Constants.MEN_ESTADO_TMP_ELIMINAR.equals(indicadorAccion)) {
					m.put("indicadorAccion", Constants.NUMERO_DOS);
				}
			}
			for (int i = 0; i < msgPatronConsideracionList.size(); i++) {
				Map map = (Map) msgPatronConsideracionList.get(i);
				String conRes = map.get("codigoConsideracion").toString();
				if (conRes.equals(codigoConsRest)) {
					map.put("indicadorModificadoPopup", "1");
					map.put("listUnidades", list);
				}
			}
			break;

		case Constants.MEN_CONRES_CODIGO_PREMIO_REST:
			list = new ArrayList();
			// for (int i = 0; i < codigoPremioListR.size(); i++) {
			// if (StringUtils.isNotEmpty(codigoPremioR[i])) {
			// Map map = new HashMap();
			// map.put("codigoPremio", codigoPremioR[i]);
			// list.add(map);
			// }
			// }
			codigoPremioListR = list;
			ventana = Constants.MEN_TIPO_CONSIDERACION.equals(indicadorTipo) ? "viewCodigoPremio"
					: "viewCodigoPremioRest";
			this.getRequestContext().execute("PF('" + ventana + "').show()");
			break;
		case Constants.MEN_CONRES_CODIGO_VENTA_REST:
			List listR = new ArrayList();
			// String[] codigoVentasR = request
			// .getParameterValues("codigoVentaFicticioItemsR");
			// for (int i = 0; i < codigoVentasR.length; i++) {
			// if (StringUtils.isNotEmpty(codigoVentasR[i])) {
			// Map map = new HashMap();
			// map.put("codigoVenta", codigoVentasR[i]);
			// listR.add(map);
			// }
			// }
			codigoVentaListR = listR;
			ventana = Constants.MEN_TIPO_CONSIDERACION.equals(indicadorTipo) ? "viewCodigoVenta"
					: "viewCodigoVentaRest";
			this.getRequestContext().execute("PF('" + ventana + "').show()");
			break;
		case Constants.MEN_CONRES_CUV_REEMPLAZO_REST:
			list = new ArrayList();
			// codigoVentas = request.getParameterValues("codigoVentaItemsR");
			// for (int i = 0; i < codigoVentas.length; i++) {
			// if (StringUtils.isNotEmpty(codigoVentas[i])) {
			// Map map = new HashMap();
			// map.put("codigoVenta", codigoVentas[i]);
			// list.add(map);
			// }
			// }
			this.codigoVentaListR = list;

			list = new ArrayList();
			// codigoVentas = request
			// .getParameterValues("codigoVentaReemplazoItemsR");
			// for (int i = 0; i < codigoVentas.length; i++) {
			// if (StringUtils.isNotEmpty(codigoVentas[i])) {
			// Map map = new HashMap();
			// map.put("codigoVenta", codigoVentas[i]);
			// list.add(map);
			// }
			// }
			this.codigoVentaReemplazoListR = list;
			ventana = Constants.MEN_TIPO_CONSIDERACION.equals(indicadorTipo) ? "viewCuvReemplazo"
					: "viewCuvReemplazoRest";
			this.getRequestContext().execute("PF('" + ventana + "').show()");
			break;
		case Constants.MEN_CONRES_ESTATUS_CLIENTE_REST:
			list = this.codigoEstatusListR;
			for (int i = list.size() - 1; i >= 0; i--) {
				Map m = (Map) list.get(i);
				String indicadorAccion = (String) m.get("indicadorAccion");
				if (Constants.MEN_ESTADO_TMP_INSERTAR.equals(indicadorAccion)) {
					m.put("indicadorAccion", Constants.NRO_CERO);
				}
				if (Constants.MEN_ESTADO_TMP_ELIMINAR.equals(indicadorAccion)) {
					m.put("indicadorAccion", Constants.NUMERO_DOS);
				}
			}
			ventana = Constants.MEN_TIPO_CONSIDERACION.equals(indicadorTipo) ? "viewEstatusCliente"
					: "viewEstatusClienteRest";
			this.getRequestContext().execute("PF('" + ventana + "').show()");
			break;
		case Constants.MEN_CONRES_LISTA_CONSU_REST:
			// flagArchivo = (String) request.getParameter("flagArchivoR");
			flagArchivo = "";
			if (flagArchivo.equals(Constants.NRO_CERO)) {
				list = this.buzonLoteListR;
				if (list != null) {
					for (int i = list.size() - 1; i >= 0; i--) {
						Map m = (Map) list.get(i);
						String indicadorAccion = (String) m
								.get("indicadorAccion");
						if (Constants.MEN_ESTADO_TMP_INSERTAR
								.equals(indicadorAccion)) {
							m.put("indicadorAccion", Constants.NRO_CERO);
						}
						if (Constants.MEN_ESTADO_TMP_ELIMINAR
								.equals(indicadorAccion)) {
							m.put("indicadorAccion", Constants.NUMERO_DOS);
						}
					}
				}

			} else {
				ProcesoZONActualizarUnidadesGeograficasService serviceUnidad = (ProcesoZONActualizarUnidadesGeograficasService) getBean("spusicc.procesoZONActualizarUnidadesGeograficasService");
				f.setDirectorioTemporal(serviceUnidad
						.obtenerPathUpload(codigoPais));
				uploadArchivo(f);
				// oidPais = String.valueOf(pais.getOidPais());
				Map criteria = new HashMap();
				criteria.put("directorioTemporal", f.getDirectorioTemporal());
				criteria.put("nombreArchivo", f.getNombreArchivo());
				criteria.put("oidPais", oidPais);
				criteria.put("indicadorTipo", indicadorTipo);
				criteria.put("oidPeriodoCorpo", f.getOidPeriodoCorpo());// vacio
																		// pantalla
																		// popup
																		// no
																		// existe
																		// campo
				criteria.put("oidModulo", f.getCodigoModulo());// vacio pantalla
																// popup no
																// existe campo
				criteria.put("login", usuario.getLogin());

				boolean isValido = service.validarArchivoExcel(criteria);
				if (isValido) {
					service.executeCargaArchivoExcel(criteria);
					String mensajeResultado = (String) criteria
							.get("mensajeResultado");
					if (StringUtils.isNotEmpty(mensajeResultado)) {
						// ActionMessages messages = new ActionMessages();
						// messages.add(
						// ActionErrors.GLOBAL_MESSAGE,
						// new ActionMessage(
						// "mantenimientoMENPatronMensajeForm.existe.estatus"));
					} else {
						List consultoraList = (List) criteria
								.get("consultoraListR");
						this.consultoraListR = consultoraList;
					}

				}
				borrarFichero(f.getDirectorioTemporal(), f.getNombreArchivo());
			}
			ventana = Constants.MEN_TIPO_CONSIDERACION.equals(indicadorTipo) ? "viewListaConsultora"
					: "viewListaConsutoraRest";
			this.getRequestContext().execute("PF('" + ventana + "').show()");
			break;
		case Constants.MEN_CONRES_CLASIFICACIONES_CLIENTE_REST:
			list = this.clasificacionesListR;
			for (int i = list.size() - 1; i >= 0; i--) {
				Map m = (Map) list.get(i);
				String indicadorAccion = (String) m.get("indicadorAccion");
				if (Constants.MEN_ESTADO_TMP_INSERTAR.equals(indicadorAccion)) {
					m.put("indicadorAccion", Constants.NRO_CERO);
				}
				if (Constants.MEN_ESTADO_TMP_ELIMINAR.equals(indicadorAccion)) {
					m.put("indicadorAccion", Constants.NUMERO_DOS);
				}
			}
			ventana = Constants.MEN_TIPO_CONSIDERACION.equals(indicadorTipo) ? "viewClasificacionesCliente"
					: "viewClasificacionesClienteRest";
			this.getRequestContext().execute("PF('" + ventana + "').show()");
			break;
		case Constants.MEN_CONRES_UNIDAD_ADM_REST:
			list = this.unidadesListR;
			for (int i = list.size() - 1; i >= 0; i--) {
				Map m = (Map) list.get(i);
				String indicadorAccion = (String) m.get("indicadorAccion");
				if (Constants.MEN_ESTADO_TMP_INSERTAR.equals(indicadorAccion)) {
					m.put("indicadorAccion", Constants.NRO_CERO);
				}
				if (Constants.MEN_ESTADO_TMP_ELIMINAR.equals(indicadorAccion)) {
					m.put("indicadorAccion", Constants.NUMERO_DOS);
				}
			}
			ventana = Constants.MEN_TIPO_CONSIDERACION.equals(indicadorTipo) ? "viewUnidadAdministrativa"
					: "viewUnidadAdministrativaRest";
			this.getRequestContext().execute("PF('" + ventana + "').show()");
			break;
		}
		log.debug("forward " + ventana);
	}

	public void insertPopup(ActionEvent event) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'insertPopup' method");
		}

		try {
			MantenimientoMENPatronMensajeForm f = (MantenimientoMENPatronMensajeForm) this.formMantenimiento;
			Map result = new HashMap();

			if (codigoConsRestr.equals("C"))
				result.put("codigo", f.getCodigoConsideracion());
			else
				result.put("codigo", f.getCodigoRestriccion());

			List consideracion = service.getConsideracion(result);
			Map data = (Map) consideracion.get(0);
			String codigoConsRest = data.get("codigoConRes").toString();// codConsRest
			String indicadorTipo = codigoConsRestr;
			InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
			// indicadorCerrarVentana

			int codigo = Integer.parseInt(codigoConsRest);
			log.debug("codigo " + codigo);
			String forward = "";
			// List list=null;
			switch (codigo) {

			case Constants.MEN_CONRES_ESTATUS_CLIENTE:
				List list = this.codigoEstatusList;
				Map map = new HashMap();
				f.setDescripcionEstadoList(f.getEstadoList().split(";")[0]);
				f.setEstadoList(f.getEstadoList().split(";")[1]);
				map.put("codigoEstado", f.getEstadoList());
				map.put("descripcionEstado", f.getDescripcionEstadoList());
				map.put("indicadorAccion", Constants.MEN_ESTADO_TMP_INSERTAR);
				// map.put("indicadorAccion",Constants.NRO_CERO);

				boolean existe = false;
				for (int i = 0; i < list.size(); i++) {
					Map m = (Map) list.get(i);
					String codigoEstado = (String) m.get("codigoEstado");
					String indicadorAccion = (String) m.get("indicadorAccion");
					if (StringUtils.equals(codigoEstado, f.getEstadoList())
							&& (!Constants.NUMERO_DOS.equals(indicadorAccion) || !Constants.MEN_ESTADO_TMP_ELIMINAR
									.equals(indicadorAccion))) {
						throw new Exception(
								this.getResourceMessage("mantenimientoMENPatronMensajeForm.existe.estatus"));
					}
				}

				if (!existe) {
					list.add(map);
				}
				generarIndex(list);
				this.codigoEstatusList = list;
				this.comDetalleEstatus = new DataTableModel(
						this.codigoEstatusList);
				break;
			case Constants.MEN_CONRES_LISTA_CONSU:

				forward = Constants.MEN_TIPO_CONSIDERACION
						.equals(indicadorTipo) ? "viewListaConsultora"
						: "viewListaConsultoraRest";
				break;
			case Constants.MEN_CONRES_CLASIFICACIONES_CLIENTE:
				list = this.clasificacionesList;
				map = new HashMap();
				map.put("oidTipoCliente", f.getOidTipoCliente());
				map.put("oidSubTipoCliente", f.getOidSubTipoCliente());
				map.put("oidTipoClasificacion", f.getOidTipoClasificacion());
				map.put("oidClasificacion", f.getOidClasificacion());
				map.put("codigoTipoCliente",
						getCodigoTipocliente(f.getOidTipoCliente()));
				map.put("codigoSubTipoCliente",
						getCodigoSubtipoCliente(f.getOidTipoCliente(),
								f.getOidSubTipoCliente()));
				map.put("codigoTipoClasificacion",
						getCodigoTipoClasificacion(f.getOidTipoCliente(),
								f.getOidSubTipoCliente(),
								f.getOidTipoClasificacion()));
				map.put("codigoClasificacion",
						getCodigoClasificacion(f.getOidTipoCliente(),
								f.getOidSubTipoCliente(),
								f.getOidTipoClasificacion(),
								f.getOidClasificacion()));
				map.put("descripcionTipoCliente",
						getDesTipocliente(f.getOidTipoCliente()));
				map.put("descripcionSubTipoCliente",
						getDesSubtipoCliente(f.getOidTipoCliente(),
								f.getOidSubTipoCliente()));
				map.put("descripcionTipoClasificacion",
						getDesTipoClasificacion(f.getOidTipoCliente(),
								f.getOidSubTipoCliente(),
								f.getOidTipoClasificacion()));
				map.put("descripcionClasificacion",
						getClasificacion(f.getOidTipoCliente(),
								f.getOidSubTipoCliente(),
								f.getOidTipoClasificacion(),
								f.getOidClasificacion()));
				// map.put("indicadorAccion",Constants.NRO_CERO);
				map.put("indicadorAccion", Constants.MEN_ESTADO_TMP_INSERTAR);
				existe = false;
				for (int i = 0; i < list.size(); i++) {
					Map m = (Map) list.get(i);
					String oidTipoCliente = (String) m.get("oidTipoCliente");
					String oidSubTipoCliente = (String) m
							.get("oidSubTipoCliente");
					String oidTipoClasificacion = (String) m
							.get("oidTipoClasificacion");
					String oidClasificacion = (String) m
							.get("oidClasificacion");
					String indicadorAccion = (String) m.get("indicadorAccion");
					if (StringUtils.equals(oidTipoCliente,
							f.getOidTipoCliente())
							&& StringUtils.equals(oidSubTipoCliente,
									f.getOidSubTipoCliente())
							&& StringUtils.equals(oidTipoClasificacion,
									f.getOidTipoClasificacion())
							&& StringUtils.equals(oidClasificacion,
									f.getOidClasificacion())
							&& (!Constants.NUMERO_DOS.equals(indicadorAccion) || !Constants.MEN_ESTADO_TMP_ELIMINAR
									.equals(indicadorAccion))) {
						throw new Exception(
								this.getResourceMessage("mantenimientoMENPatronMensajeForm.existe.tipologia"));

					}
				}

				if (!existe) {
					list.add(map);
				}

				generarIndex(list);

				siccTipoClienteList = interfazSiCCService
						.getTiposClientesByCodigoISOOID(usuario.getIdioma()
								.getCodigoISO());
				f.setOidSubTipoCliente(Constants.OID_SUBTIPO_CLIENTE_DEFAULT);
				f.setOidTipoCliente(Constants.OID_TIPO_CLIENTE_DEFAULT);
				f.setOidTipoClasificacion(null);
				f.setOidClasificacion(null);
				recargarTipologiaClientes(f);
				this.clasificacionesList = list;
				this.comDetalleClasificacion = new DataTableModel(
						this.clasificacionesList);

				break;
			case Constants.MEN_CONRES_UNIDAD_ADM:
				list = this.unidadesList;
				map = new HashMap();
				if (f.getRegionList() == null)
					f.setRegionList(" : ");
				if (f.getZonaList() == null)
					f.setZonaList(" : ");
				if (f.getSeccionList() == null)
					f.setSeccionList(" : ");
				if (f.getTerritorioList() == null)
					f.setTerritorioList("");

				f.setDescripcionRegionList(f.getRegionList().split(":")[0]);
				f.setDescripcionZonaList(f.getZonaList().split(":")[0]);
				f.setDescripcionSeccionList(f.getSeccionList().split(":")[0]);
				f.setRegionList(f.getRegionList().split(":")[1]);
				if (f.getDescripcionZonaList().equals("Todos"))
					f.setZonaList("");
				else
					f.setZonaList(f.getZonaList().split(":")[1]);
				f.setSeccionList(f.getSeccionList().split(":")[1]);

				map.put("codigoRegion", f.getRegionList());
				map.put("codigoZona", f.getZonaList());
				map.put("codigoSeccion", f.getSeccionList());
				map.put("codigoTerritorio", f.getTerritorioList());
				map.put("descripcionRegion", f.getDescripcionRegionList());
				map.put("descripcionZona", f.getDescripcionZonaList());
				map.put("descripcionSeccion", f.getDescripcionSeccionList());
				map.put("descripcionTerritorio", f.getTerritorioList());
				// map.put("indicadorAccion",Constants.NRO_CERO);
				map.put("indicadorAccion", Constants.MEN_ESTADO_TMP_INSERTAR);
				existe = false;
				for (int i = 0; i < list.size(); i++) {
					Map m = (Map) list.get(i);
					String codigoRegion = (String) m.get("codigoRegion");
					String codigoZona = (String) m.get("codigoZona");
					String codigoSeccion = (String) m.get("codigoSeccion");
					String codigoTerritorio = (String) m
							.get("codigoTerritorio");
					String indicadorAccion = (String) m.get("indicadorAccion");
					if (StringUtils.equals(codigoRegion, f.getRegionList())
							&& StringUtils.equals(codigoZona, f.getZonaList())
							&& StringUtils.equals(codigoSeccion,
									f.getSeccionList())
							&& StringUtils.equals(codigoTerritorio,
									f.getTerritorioList())
							&& (!Constants.NUMERO_DOS.equals(indicadorAccion) || !Constants.MEN_ESTADO_TMP_ELIMINAR
									.equals(indicadorAccion))) {

						throw new Exception(
								this.getResourceMessage("mantenimientoMENPatronMensajeForm.existe.unidades"));

					}
				}

				if (!existe) {
					list.add(map);
					f.setRegionList(null);
					f.setZonaList(null);
					f.setSeccionList(null);
					f.setTerritorioList(null);
					f.setDescripcionRegionList(null);
					f.setDescripcionZonaList(null);
					f.setDescripcionSeccionList(null);
					f.setDescripcionTerritorioList(null);
				}

				generarIndex(list);

				this.unidadesList = list;
				this.comDetalleUnidad = new DataTableModel(this.unidadesList);
				break;

			case Constants.MEN_CONRES_ESTATUS_CLIENTE_REST:
				list = this.codigoEstatusListR;
				map = new HashMap();
				map.put("codigoEstado", f.getEstadoList());
				map.put("descripcionEstado", f.getDescripcionEstadoList());
				// map.put("indicadorAccion",Constants.NRO_CERO);
				map.put("indicadorAccion", Constants.MEN_ESTADO_TMP_INSERTAR);
				existe = false;
				for (int i = 0; i < list.size(); i++) {
					Map m = (Map) list.get(i);
					String codigoEstado = (String) m.get("codigoEstado");
					String indicadorAccion = (String) m.get("indicadorAccion");
					if (StringUtils.equals(codigoEstado, f.getEstadoList())
							&& !Constants.NUMERO_DOS.equals(indicadorAccion)) {
						throw new Exception(
								this.getResourceMessage("mantenimientoMENPatronMensajeForm.existe.estatus"));

					}
				}

				if (!existe) {
					list.add(map);
				}

				break;
			case Constants.MEN_CONRES_LISTA_CONSU_REST:
				forward = Constants.MEN_TIPO_CONSIDERACION
						.equals(indicadorTipo) ? "viewListaConsultora"
						: "viewListaConsultoraRest";
				break;
			case Constants.MEN_CONRES_CLASIFICACIONES_CLIENTE_REST:
				list = this.clasificacionesListR;
				map = new HashMap();
				map.put("oidTipoCliente", f.getOidTipoCliente());
				map.put("oidSubTipoCliente", f.getOidSubTipoCliente());
				map.put("oidTipoClasificacion", f.getOidTipoClasificacion());
				map.put("oidClasificacion", f.getOidClasificacion());
				map.put("codigoTipoCliente",
						getCodigoTipocliente(f.getOidTipoCliente()));
				map.put("codigoSubTipoCliente",
						getCodigoSubtipoCliente(f.getOidTipoCliente(),
								f.getOidSubTipoCliente()));
				map.put("codigoTipoClasificacion",
						getCodigoTipoClasificacion(f.getOidTipoCliente(),
								f.getOidSubTipoCliente(),
								f.getOidTipoClasificacion()));
				map.put("codigoClasificacion",
						getCodigoClasificacion(f.getOidTipoCliente(),
								f.getOidSubTipoCliente(),
								f.getOidTipoClasificacion(),
								f.getOidClasificacion()));
				map.put("descripcionTipoCliente",
						getDesTipocliente(f.getOidTipoCliente()));
				map.put("descripcionSubTipoCliente",
						getDesSubtipoCliente(f.getOidTipoCliente(),
								f.getOidSubTipoCliente()));
				map.put("descripcionTipoClasificacion",
						getDesTipoClasificacion(f.getOidTipoCliente(),
								f.getOidSubTipoCliente(),
								f.getOidTipoClasificacion()));
				map.put("descripcionClasificacion",
						getClasificacion(f.getOidTipoCliente(),
								f.getOidSubTipoCliente(),
								f.getOidTipoClasificacion(),
								f.getOidClasificacion()));
				// map.put("indicadorAccion",Constants.NRO_CERO);
				map.put("indicadorAccion", Constants.MEN_ESTADO_TMP_INSERTAR);
				existe = false;
				for (int i = 0; i < list.size(); i++) {
					Map m = (Map) list.get(i);
					String oidTipoCliente = (String) m.get("oidTipoCliente");
					String oidSubTipoCliente = (String) m
							.get("oidSubTipoCliente");
					String oidTipoClasificacion = (String) m
							.get("oidTipoClasificacion");
					String oidClasificacion = (String) m
							.get("oidClasificacion");
					String indicadorAccion = (String) m.get("indicadorAccion");
					if (StringUtils.equals(oidTipoCliente,
							f.getOidTipoCliente())
							&& StringUtils.equals(oidSubTipoCliente,
									f.getOidSubTipoCliente())
							&& StringUtils.equals(oidTipoClasificacion,
									f.getOidTipoClasificacion())
							&& StringUtils.equals(oidClasificacion,
									f.getOidClasificacion())
							&& !Constants.NUMERO_DOS.equals(indicadorAccion)) {
						throw new Exception(
								this.getResourceMessage("mantenimientoMENPatronMensajeForm.existe.tipologia"));
					}
				}

				if (!existe) {
					list.add(map);
				}
				siccTipoClienteList = interfazSiCCService
						.getTiposClientesByCodigoISOOID(usuario.getIdioma()
								.getCodigoISO());
				f.setOidSubTipoCliente(Constants.OID_SUBTIPO_CLIENTE_DEFAULT);
				f.setOidTipoCliente(Constants.OID_TIPO_CLIENTE_DEFAULT);
				f.setOidTipoClasificacion(null);
				f.setOidClasificacion(null);
				recargarTipologiaClientes(f);

				forward = Constants.MEN_TIPO_CONSIDERACION
						.equals(indicadorTipo) ? "viewClasificacionesCliente"
						: "viewClasificacionesClienteRest";
				break;
			case Constants.MEN_CONRES_UNIDAD_ADM_REST:
				list = this.unidadesListR;
				map = new HashMap();
				map.put("codigoRegion", f.getRegionList());
				map.put("codigoZona", f.getZonaList());
				map.put("codigoSeccion", f.getSeccionList());
				map.put("codigoTerritorio", f.getTerritorioList());
				map.put("descripcionRegion", f.getDescripcionRegionList());
				map.put("descripcionZona", f.getDescripcionZonaList());
				map.put("descripcionSeccion", f.getDescripcionSeccionList());
				map.put("descripcionTerritorio",
						f.getDescripcionTerritorioList());
				// map.put("indicadorAccion",Constants.NRO_CERO);
				map.put("indicadorAccion", Constants.MEN_ESTADO_TMP_INSERTAR);
				existe = false;
				for (int i = 0; i < list.size(); i++) {
					Map m = (Map) list.get(i);
					String codigoRegion = (String) m.get("codigoRegion");
					String codigoZona = (String) m.get("codigoZona");
					String codigoSeccion = (String) m.get("codigoSeccion");
					String codigoTerritorio = (String) m
							.get("codigoTerritorio");
					String indicadorAccion = (String) m.get("indicadorAccion");
					if (StringUtils.equals(codigoRegion, f.getRegionList())
							&& StringUtils.equals(codigoZona, f.getZonaList())
							&& StringUtils.equals(codigoSeccion,
									f.getSeccionList())
							&& StringUtils.equals(codigoTerritorio,
									f.getTerritorioList())
							&& !Constants.NUMERO_DOS.equals(indicadorAccion)) {
						throw new Exception(
								this.getResourceMessage("mantenimientoMENPatronMensajeForm.existe.unidades"));
					}
				}

				if (!existe) {
					list.add(map);
				}
				forward = Constants.MEN_TIPO_CONSIDERACION
						.equals(indicadorTipo) ? "viewUnidadAdministrativa"
						: "viewUnidadAdministrativaRest";
				break;
			}
		} catch (Exception e) {
			addError("Error", this.obtieneMensajeErrorException(e));
		}

	}

	public void generarIndex(List list) {
		for (int i = 0; i < list.size(); i++) {
			Map m = (Map) list.get(i);
			m.put("index", "" + i + "");
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
	public void deletePopup(ActionEvent event) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'deletePopup' method");
		}
		MantenimientoMENPatronMensajeForm f = (MantenimientoMENPatronMensajeForm) this.formMantenimiento;

		// String codigoConsRest=(String)request.getParameter("codConsRest");
		String codigoConsRest = "";
		if (codigoConsRestr.equals("C"))
			codigoConsRest = f.getCodigoConsideracion();
		else
			codigoConsRest = f.getCodigoRestriccion();

		String indicadorTipo = codigoConsRestr;
		Map map = new HashMap();
		// indicadorCerrarVentana
		int codigo = Integer.parseInt(codigoConsRest);
		// List list=null;
		try {

			switch (codigo) {
			case Constants.MEN_CONRES_ESTATUS_CLIENTE:
				if (beanRegistroEstatus == null)
					throw new Exception(
							this.getResourceMessage("msg.errors.sin.registros"));
				List list = this.codigoEstatusList;
				map = (Map) this.beanRegistroEstatus;
				// map.put("indicadorAccion", Constants.NUMERO_DOS);
				String indicadorAcion = (String) map.get("indicadorAccion");
				map.put("indicadorAccionAnterior", indicadorAcion);// guardo el
																	// estado
																	// anterior
																	// por
																	// si ocurre
																	// una
																	// cancelacion
				map.put("indicadorAccion", Constants.MEN_ESTADO_TMP_ELIMINAR);
				List listaEstatus = new ArrayList();
				for (int i = 0; i < list.size(); i++) {
					Map map2 = (Map) list.get(i);
					String ind = map2.get("indicadorAccion").toString();
					if (!ind.equals(Constants.MEN_ESTADO_TMP_ELIMINAR))
						listaEstatus.add(map2);
				}
				this.comDetalleEstatus = new DataTableModel(listaEstatus);
				break;
			case Constants.MEN_CONRES_LISTA_CONSU:
				list = this.buzonLoteList;
				ExternalContext externalContext = FacesContext
						.getCurrentInstance().getExternalContext();
				String index = externalContext.getRequestParameterMap().get(
						"parametroAccion");
				map = (Map) list.get(Integer.parseInt(index));
				// map.put("indicadorAccion", Constants.NUMERO_DOS);
				indicadorAcion = (String) map.get("indicadorAccion");
				map.put("indicadorAccionAnterior", indicadorAcion);// guardo el
																	// estado
																	// anterior
																	// por
																	// si ocurre
																	// una
																	// cancelacion
				map.put("indicadorAccion", Constants.NUMERO_DOS);
				this.buzonLoteList = list;
				for (int i = 0; i < msgPatronConsideracionList.size(); i++) {
					Map data = (Map) msgPatronConsideracionList.get(i);
					if (data.get("codigoConsideracion").equals(codigoConsRest)) {
						data.put("consultoraList", new ArrayList());
					}
				}
				this.comDetalleListaConsultora = new DataTableModel(
						indAccionEliminar(buzonLoteList));
				break;
			case Constants.MEN_CONRES_CLASIFICACIONES_CLIENTE:
				list = this.clasificacionesList;
				if (beanRegistroClasificacion == null)
					throw new Exception(
							this.getResourceMessage("msg.errors.sin.registros"));
				map = (Map) this.beanRegistroClasificacion;
				// map.put("indicadorAccion", Constants.NUMERO_DOS);
				indicadorAcion = (String) map.get("indicadorAccion");
				map.put("indicadorAccionAnterior", indicadorAcion);// guardo el
																	// estado
																	// anterior
																	// por
																	// si ocurre
																	// una
																	// cancelacion
				map.put("indicadorAccion", Constants.MEN_ESTADO_TMP_ELIMINAR);
				// eliminarIndex(list, map);
				List listaCliente = new ArrayList();
				for (int i = 0; i < list.size(); i++) {
					Map map2 = (Map) list.get(i);
					String ind = map2.get("indicadorAccion").toString();
					if (!ind.equals(Constants.MEN_ESTADO_TMP_ELIMINAR))
						listaCliente.add(map2);
				}

				// this.clasificacionesList = list;
				this.comDetalleClasificacion = new DataTableModel(listaCliente);
				break;
			case Constants.MEN_CONRES_UNIDAD_ADM:
				if (beanRegistroUnidad == null)
					throw new Exception(
							this.getResourceMessage("msg.errors.sin.registros"));
				list = this.unidadesList;
				map = (Map) this.beanRegistroUnidad;
				// map.put("indicadorAccion", Constants.NUMERO_DOS);
				indicadorAcion = (String) map.get("indicadorAccion");
				map.put("indicadorAccionAnterior", indicadorAcion);// guardo el
																	// estado
																	// anterior
																	// por
																	// si ocurre
																	// una
																	// cancelacion
				map.put("indicadorAccion", Constants.MEN_ESTADO_TMP_ELIMINAR);

				List listaUnidad = new ArrayList();
				for (int i = 0; i < list.size(); i++) {
					Map map2 = (Map) list.get(i);
					String ind = map2.get("indicadorAccion").toString();
					if (!ind.equals(Constants.MEN_ESTADO_TMP_ELIMINAR))
						listaUnidad.add(map2);
				}

				this.comDetalleUnidad = new DataTableModel(listaUnidad);

				break;

			case Constants.MEN_CONRES_ESTATUS_CLIENTE_REST:
				list = this.codigoEstatusListR;
				map = (Map) list.get(0);
				// map.put("indicadorAccion", Constants.NUMERO_DOS);
				indicadorAcion = (String) map.get("indicadorAccion");
				map.put("indicadorAccionAnterior", indicadorAcion);// guardo el
																	// estado
																	// anterior
																	// por
																	// si ocurre
																	// una
																	// cancelacion
				map.put("indicadorAccion", Constants.MEN_ESTADO_TMP_ELIMINAR);
				// forward =
				// Constants.MEN_TIPO_CONSIDERACION.equals(indicadorTipo)
				// ? "viewEstatusCliente"
				// : "viewEstatusClienteRest";
				break;
			case Constants.MEN_CONRES_LISTA_CONSU_REST:
				list = this.buzonLoteListR;
				map = (Map) list.get(0);
				// map.put("indicadorAccion", Constants.NUMERO_DOS);
				indicadorAcion = (String) map.get("indicadorAccion");
				map.put("indicadorAccionAnterior", indicadorAcion);// guardo el
																	// estado
																	// anterior
																	// por
																	// si ocurre
																	// una
																	// cancelacion
				map.put("indicadorAccion", Constants.MEN_ESTADO_TMP_ELIMINAR);
				// forward =
				// Constants.MEN_TIPO_CONSIDERACION.equals(indicadorTipo)
				// ? "viewListaConsu"
				// : "viewListaConsuRest";
				break;
			case Constants.MEN_CONRES_CLASIFICACIONES_CLIENTE_REST:
				list = this.clasificacionesListR;
				map = (Map) list.get(0);
				// map.put("indicadorAccion", Constants.NUMERO_DOS);
				indicadorAcion = (String) map.get("indicadorAccion");
				map.put("indicadorAccionAnterior", indicadorAcion);// guardo el
																	// estado
																	// anterior
																	// por
																	// si ocurre
																	// una
																	// cancelacion
				map.put("indicadorAccion", Constants.MEN_ESTADO_TMP_ELIMINAR);
				// forward =
				// Constants.MEN_TIPO_CONSIDERACION.equals(indicadorTipo)
				// ? "viewClasificacionesCliente"
				// : "viewClasificacionesClienteRest";
				break;
			case Constants.MEN_CONRES_UNIDAD_ADM_REST:
				list = this.unidadesListR;
				map = (Map) list.get(0);
				// map.put("indicadorAccion", Constants.NUMERO_DOS);
				indicadorAcion = (String) map.get("indicadorAccion");
				map.put("indicadorAccionAnterior", indicadorAcion);// guardo el
																	// estado
																	// anterior
																	// por
																	// si ocurre
																	// una
																	// cancelacion
				map.put("indicadorAccion", Constants.MEN_ESTADO_TMP_ELIMINAR);
				// forward =
				// Constants.MEN_TIPO_CONSIDERACION.equals(indicadorTipo)
				// ? "viewUnidadAdministrativa"
				// : "viewUnidadAdministrativaRest";
				break;
			}
		} catch (Exception e) {
			// TODO: handle exception
			addError("Error", obtieneMensajeErrorException(e));
		}
	}

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

	private String getDesTipoClasificacion(String oidTipoCliente,
			String oidSubTipoCliente, String oidTipoClasificacion) {
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

	private String getClasificacion(String oidTipoCliente,
			String oidSubTipoCliente, String oidTipoClasificacion,
			String oidClasificacion) {

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

	private String getCodigoTipocliente(String oidTipoCliente) {
		log.debug("getCodigoTipocliente oidTipoCliente " + oidTipoCliente);
		String descp = getDesTipocliente(oidTipoCliente);
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
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

	private String getDesTipocliente(String oidTipoCliente) {
		log.debug("getDesTipocliente oidTipoCliente " + oidTipoCliente);
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
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

	private String getCodigoClasificacion(String oidTipoCliente,
			String oidSubTipoCliente, String oidTipoClasificacion,
			String oidClasificacion) {

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

	private String getCodigoTipoClasificacion(String oidTipoCliente,
			String oidSubTipoCliente, String oidTipoClasificacion) {
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

	private void uploadArchivo(MantenimientoMENPatronMensajeForm f)
			throws Exception {

		// recuperamos el fichero
		UploadedFile archivo = f.getArchivo();
		f.setNombreArchivo(archivo.getFileName());
		log.debug("Archivo Upload: " + f.getArchivo());
		log.debug("Nombre Archivo Upload: " + f.getNombreArchivo());
		// leyemos el stream de entrada
		InputStream is = archivo.getInputstream();
		// abrimos el stream de escritura, ubicacion al cual se grabara el
		// archivo del cliente
		FileOutputStream os = new FileOutputStream(new File(
				f.getDirectorioTemporal(), f.getNombreArchivo()));
		// grabamos cada 1024 bytes
		int bytesRead = 0;
		byte[] buffer = new byte[1024];
		while ((bytesRead = is.read(buffer, 0, 1024)) != -1) {
			os.write(buffer, 0, bytesRead);
		}
		os.close();
		f.setArchivo(null);
	}

	public void loadZonas(ValueChangeEvent val) {
		MantenimientoMENPatronMensajeForm f = (MantenimientoMENPatronMensajeForm) this.formMantenimiento;
		String[] codRegion = null;
		if (val.getNewValue() != null) {
			String valor = val.getNewValue().toString();
			codRegion = new String[1];
			codRegion[0] = valor.split(":")[1];
		}

		this.siccZonaList = ajax.getZonasMultipleByPaisMarcaCanalRegion(
				codigoPais, "T", "VD", codRegion, "U");

		f.setZonaList(null);
		f.setSeccionList(null);
		f.setTerritorioList(null);

		siccSeccionList = null;
		siccTerritorioList = null;

	}

	public void loadSeccion(ValueChangeEvent val) {
		MantenimientoMENPatronMensajeForm f = (MantenimientoMENPatronMensajeForm) this.formMantenimiento;
		if (val.getNewValue() != null) {
			String valor = val.getNewValue().toString();
			String[] codZona = new String[1];
			codZona[0] = valor.split(":")[1];
			this.siccSeccionList = ajax
					.getSeccionMultipleByPaisMarcaCanalRegionZona(codigoPais,
							"T", "VD",
							new String[] { f.getRegionList().split(":")[1] },
							codZona, "U");
		} else {
			f.setSeccionList(null);
			f.setTerritorioList(null);
			siccSeccionList = null;
			siccTerritorioList = null;
		}

	}

	public void loadTerritorios(ValueChangeEvent val) {
		MantenimientoMENPatronMensajeForm f = (MantenimientoMENPatronMensajeForm) this.formMantenimiento;
		if (val.getNewValue() != null) {
			String valor = val.getNewValue().toString();
			String[] codSeccion = new String[1];
			codSeccion[0] = valor.split(":")[1];
			this.siccTerritorioList = ajax
					.getTerritoriosMultipleByPaisMarcaCanalRegionZonaSeccion(
							codigoPais,
							"T",
							"VD",
							new ArrayList<String>(Arrays.asList(f
									.getRegionList().split(":")[1])),
							new ArrayList<String>(Arrays.asList(f.getZonaList()
									.split(":")[1])), new ArrayList<String>(
									Arrays.asList(codSeccion)), "U");
		} else {
			f.setTerritorioList(null);
			siccTerritorioList = null;
		}

	}

	public void validaCodigo(String codVenta, String index) {
		if (StringUtils.isBlank(codVenta)) return;
		int tamanno = StringUtils.length(codVenta);
		if (tamanno < 5) return;
		
		MantenimientoMENPatronMensajeForm f = (MantenimientoMENPatronMensajeForm) this.formMantenimiento;
		LabelValueCUV dato = ajax.getCodigoVentaPrecio(codVenta,	f.getCampanhaProceso());
		int indexN = Integer.parseInt(index);
		Map data = new HashMap();
		List addList = this.codigoVentaList;
		boolean flag = existeCodVenta(codVenta, addList);
		boolean editar = editar(addList, index);
		if (dato == null) { 
			data = cogerData(codVenta, addList);
			data.put("codigoVenta", "");
			data.put("descripcion", "");

			addError(
					"Error",
					this.getResourceMessage("mantenimientoMENPatronMensajeForm.mensaje.codVtaNotExiste"));
		} else if (flag) {
			data = (Map) addList.get(indexN);
			data.put("codigoVenta", "");
			addError(
					"Error",
					this.getResourceMessage("mantenimientoMENPatronMensajeForm.mensaje.codVtaExiste.grilla"));
		} else if (editar) {
			data = (Map) addList.get(Integer.parseInt(index));
			data.put("descripcion", dato.getValue());
		} else {

			data = cogerData(codVenta, addList);
			data.put("descripcion", dato.getValue());
			data.put("indAccion", "1");

			Map map = new HashMap();
			map.put("codigoVenta", "");
			addList.add(map);

		}
		codigoVentaList = addList;
		this.comDetalleCodigoVenta = new DataTableModel(addList);
	}
	
	public void validaCuvFaltante(String codVenta, String index) {
		MantenimientoMENPatronMensajeForm f = (MantenimientoMENPatronMensajeForm) this.formMantenimiento;
		LabelValueCUV dato = ajax.getCodigoVentaPrecio(codVenta,f.getCampanhaProceso());
		int indexN = Integer.parseInt(index);
		Map data = new HashMap();
		List addList = this.codigoCuvFaltanteList;
		boolean flag = existeCodVenta(codVenta, addList);
		boolean editar = editar(addList, index);
		if (dato == null) {
			data = cogerDataCuvFaltante(codVenta, addList);
			data.put("codigoCuvFaltante", "");
			data.put("descripcion", "");
			addError("Error", this.getResourceMessage("mantenimientoMENPatronMensajeForm.mensaje.codVtaNotExiste"));
		} else if (flag) {
			data = (Map) addList.get(indexN);
			data.put("codigoCuvFaltante", "");
			addError("Error", this.getResourceMessage("mantenimientoMENPatronMensajeForm.mensaje.codVtaExiste.grilla"));
		} else if (editar) {
			data = (Map) addList.get(Integer.parseInt(index));
			data.put("descripcion", dato.getValue());
		} else {
			data = cogerDataCuvFaltante(codVenta, addList);
			data.put("descripcion", dato.getValue());
			data.put("indAccion", "1");

			Map map = new HashMap();
			map.put("codigoCuvFaltante", "");
			addList.add(map);
		}
		codigoCuvFaltanteList = addList;
		this.comDetalleCodigoCuvFaltante= new DataTableModel(addList);
	}

	public void eliminarCodigoVenta(ActionEvent event) {
		Map data = (Map) this.beanRegistroCodigoVenta;
		try {
			if (beanRegistroCodigoVenta == null)
				throw new Exception(
						this.getResourceMessage("mantenimientoINCPremiosElectivosForm.mensaje.no.seleccion"));

			if (StringUtils.isBlank(data.get("codigoVenta").toString()))
				throw new Exception(
						this.getResourceMessage("mantenimientoINCPremiosElectivosForm.mensaje.no.seleccion"));
			else {
				codigoVentaList.remove(data);
				generarIndex(codigoVentaList);
			}

		} catch (Exception e) {
			// TODO: handle exception
			addError("Error", obtieneMensajeErrorException(e));
		}
	}
	
	public void eliminarCodigoCuvFaltante(ActionEvent event) {
		Map data = (Map) this.beanRegistroCodigoCuvFaltante;
		try {
			if (beanRegistroCodigoCuvFaltante == null)
				throw new Exception(this.getResourceMessage("mantenimientoINCPremiosElectivosForm.mensaje.no.seleccion"));

			if (StringUtils.isBlank(data.get("codigoCuvFaltante").toString()))
				throw new Exception(this.getResourceMessage("mantenimientoINCPremiosElectivosForm.mensaje.no.seleccion"));
			else {
				this.codigoCuvFaltanteList.remove(data);
				generarIndex(this.codigoCuvFaltanteList);
			}
		} catch (Exception e) {			
			this.addError("Error", this.obtieneMensajeErrorException(e));
		}
	}


	public Map cogerData(String codVenta, List list) {
		Map data = new HashMap();
		for (int i = 0; i < list.size(); i++) {
			Map map = (Map) list.get(i);
			if (map.get("codigoVenta").toString().equals(codVenta)) {
				data = map;
			}
		}
		return data;
	}
	
	public Map cogerDataCuvFaltante(String codVenta, List list) {
		Map data = new HashMap();
		for (int i = 0; i < list.size(); i++) {
			Map map = (Map) list.get(i);
			if (map.get("codigoCuvFaltante").toString().equals(codVenta)) {
				data = map;
			}
		}
		return data;
	}

	public boolean editar(List list, String index) {
		boolean flag = false;
		Map map = (Map) list.get(Integer.parseInt(index));
		try {
			if (map.get("indAccion").toString().equals("1"))
				flag = true;
		} catch (Exception e) {
			// TODO: handle exception
		}

		return flag;
	}

	public boolean existeCodVenta(String codVenta, List list) {
		boolean flag = false;
		try {
			int k = 0;
			for (int i = 0; i < list.size(); i++) {
				Map map = (Map) list.get(i);
				if (map.get("codigo").toString().equals(codVenta)) {
					k++;
				}
			}
			if (k > 1)
				flag = true;
		} catch (Exception e) {
			// TODO: handle exception
		}

		return flag;
	}

	/**
	 * elimina el fichero
	 * 
	 * @param path
	 * @param nombreArchivo
	 */
	private void borrarFichero(String path, String nombreArchivo) {
		try {
			File file = new File(path, nombreArchivo);
			file.delete();
			log.debug("Se elimino el archivo");
		} catch (Exception ex) {
			log.debug("No se pudo eliminar el archivo " + ex.getMessage());
		}
	}

	public void ocultarMontoCatalogo(ValueChangeEvent val) {
		if (val.getNewValue() != null) {
			String valor = val.getNewValue().toString();
			if (valor.equals("2011"))
				montoCatalogoBoolean = true;
			else
				montoCatalogoBoolean = false;
		} else
			montoCatalogoBoolean = false;
	}

	public void guardarLista(ActionEvent event) {
		this.getRequestContext()
				.execute(
						"PF('confirmDialogSalirUpload2_confirmationDialogConfirmar').show()");
	}

	public void insertarLista(ActionEvent event) {
		List lista = new ArrayList();
		lista = this.buzonLoteList;
		if (lista != null) {
			for (int i = lista.size() - 1; i >= 0; i--) {
				Map m = (Map) lista.get(i);
				String indicadorAccion = (String) m.get("indicadorAccion");
				if (Constants.MEN_ESTADO_TMP_INSERTAR.equals(indicadorAccion)) {
					m.put("indicadorAccion", Constants.NRO_CERO);
				}
				if (Constants.MEN_ESTADO_TMP_ELIMINAR.equals(indicadorAccion)) {
					m.put("indicadorAccion", Constants.NUMERO_DOS);
				}
			}
		}

		MantenimientoMENPatronMensajeForm f = (MantenimientoMENPatronMensajeForm) this.formMantenimiento;

		Map map = new HashMap();
		String valor = "";
		if (f.getTipoMensaje().equals("1"))
			valor = "V";
		else
			valor = "F";

		map.put("tipoMensaje", valor);
		// LISTA EL COMBO DE CONSIDERACION QUE DEPENDE DEL TIPO DE VARIABLE
		List listComboConsideracion = service.getConsideracion(map);
		List list = this.msgPatronConsideracionList;
		if (list == null)
			list = new ArrayList();

		Map condicion = getCondicion(f.getCodigoConsideracion(),
				listComboConsideracion);
		String tipo = (String) condicion.get("indTipo");

		Map bean = new HashMap();
		bean.put("codigoPais", codigoPais);
		bean.put("codigoMensaje", f.getCodigoMensaje());
		bean.put("campanhaProceso", f.getCampanhaProceso());
		bean.put("codigoConsideracion", f.getCodigoConsideracion());
		bean.put("indicadorTipo", tipo);
		bean.put("descripcion", (String) condicion.get("descripcionConRes"));
		bean.put(
				"abrev",
				getAbrevConRes(f.getCodigoConsideracion(),
						listComboConsideracion));
		bean.put("oidPeriodoCorpo", f.getOidPeriodoCorpo());
		bean.put("oidModulo", f.getCodigoModulo());
		bean.put("indicadorAccion", Constants.NUMERO_CERO);
		bean.put("condicion", "");
		bean.put("numReg", "0");

		if (Constants.MEN_CONRES_LISTA_CONSU == Integer.parseInt(f
				.getCodigoConsideracion())) {
			List consultoraList = this.consultoraList;
			List buzonLoteList = this.buzonLoteList;
			log.debug("consultoraList >>>>>>> " + consultoraList.size());
			bean.put("consultoraList", consultoraList);
			bean.put("buzonLoteList", buzonLoteList);
			bean.put("numReg", "" + consultoraList.size());
			bean.put("condicion", "" + consultoraList.size());
			bean.put("condicion1", "" + consultoraList.size());
			bean.put("tipoConsideracionLista",
					Constants.MEN_TIPO_LISTA_CONDICION);
		}

		bean.put("codigoRestriccion", f.getCodigoConsideracion());
		list.add(bean);
		f.setCodigoConsideracion("");
		this.msgPatronConsideracionList = list;
		this.dataTablePatronConsideracion = new DataTableModel(
				indAccionEliminar(msgPatronConsideracionList));
	}
	
	
	
	/**
	 * @param event
	 */
	public void handleFileUpload(FileUploadEvent event) {
		if(log.isDebugEnabled()){
			log.debug("handleFileUpload");
		}
		try {
			MantenimientoMENPatronMensajeForm f = (MantenimientoMENPatronMensajeForm) this.formMantenimiento;
			if(event != null){
				f.setArchivo(event.getFile());
				f.setNombreArchivo(event.getFile().getFileName());
				this.setAttachment(event.getFile().getFileName());
			}
			this.uploadArchivoImagenJava();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @param event
	 */
	public void handleFileUploadImagenFondo(FileUploadEvent event) {
		if(log.isDebugEnabled()){
			log.debug("handleFileUploadImagenFondo");
		}
		this.nombreImagenFondo = event.getFile().getFileName();
		this.handleFileUpload(event);
		this.nombreImagenFondo = event.getFile().getFileName();
	}
	
	/**
	 * carga el archivo al temporal Java
	 * @param form
	 * @throws Exception
	 */
	private void uploadArchivoImagenJava() throws IOException {
		if(log.isDebugEnabled()){
			log.debug("uploadArchivo");			
		}
		MantenimientoMENPatronMensajeForm f = (MantenimientoMENPatronMensajeForm) this.formMantenimiento;
		
		// leyemos el stream de entrada y pasando a la Ruta Temporal en Oracle
		InputStream is = f.getArchivo().getInputstream();
		// archivo del cliente
		FileOutputStream os = new FileOutputStream(new File(this.pathRutaImgJava, 	f.getNombreArchivo()));
		// grabamos cada 1024 bytes
		int bytesRead = 0;
		byte[] buffer = new byte[1024];
		while ((bytesRead = is.read(buffer, 0, 1024)) != -1) {
			os.write(buffer, 0, bytesRead);
		}
		os.close();		
		f.setArchivo(null);		
	}
	
	/**
	 * carga el archivo al temporal Oracle
	 * @param nombreArchivo
	 * @throws IOException
	 */
	private void uploadArchivoImagenJavaOracle(String nombreArchivo) throws IOException {
		if(log.isDebugEnabled()){
			log.debug("uploadArchivo");			
		}
		
		// leyemos el stream de entrada y pasando a la Ruta Temporal en Oracle
		File file = new File(this.pathRutaImgJava, nombreArchivo);
		FileInputStream is = new FileInputStream(file);
		
		// archivo temporal Oracle
		FileOutputStream os = new FileOutputStream(new File(this.pathRutaImgOracle, nombreArchivo));
		// grabamos cada 1024 bytes
		int bytesRead = 0;
		byte[] buffer = new byte[1024];
		while ((bytesRead = is.read(buffer, 0, 1024)) != -1) {
			os.write(buffer, 0, bytesRead);
		}
		os.close();	
		is.close();
	}
	
	/**
	 * carga el archivo al temporal Oracle
	 * @param nombreArchivo
	 * @throws IOException
	 */
	private void uploadArchivoImagenOracleJava(String nombreArchivo) throws IOException {
		if(log.isDebugEnabled()){
			log.debug("ini uploadArchivoImagenOracleJava");			
		}
		
		// leyemos el stream de entrada 
		File file = new File(this.pathRutaImgOracle, nombreArchivo);
		FileInputStream is = new FileInputStream(file);
		log.debug("file Origen: " + file.toString());		
		
		// archivo temporal Java
		FileOutputStream os = new FileOutputStream(new File(this.pathRutaImgJava, nombreArchivo));
		log.debug("file Destino: " + this.pathRutaImgJava + nombreArchivo);	
		
		// grabamos cada 1024 bytes
		int bytesRead = 0;
		byte[] buffer = new byte[1024];
		while ((bytesRead = is.read(buffer, 0, 1024)) != -1) {
			os.write(buffer, 0, bytesRead);
		}
		os.close();	
		is.close();
		log.debug("fin uploadArchivoImagenOracleJava");	
	}
	
	
	/**
	 * @param actionEvent
	 */
	public void eliminarImagenFondo(ActionEvent actionEvent) {
		MantenimientoMENPatronMensajeForm f = (MantenimientoMENPatronMensajeForm) this.formMantenimiento;
		f.setNombreArchivo("");
		this.nombreImagenFondo = "";
	}
	
	
	
	
	/* GET - SET */

	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 *            the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the service
	 */
	public MantenimientoMENGenericoService getService() {
		return service;
	}

	/**
	 * @param service
	 *            the service to set
	 */
	public void setService(MantenimientoMENGenericoService service) {
		this.service = service;
	}

	/**
	 * @return the msgMensajeDocumentoList
	 */
	public List getMsgMensajeDocumentoList() {
		return msgMensajeDocumentoList;
	}

	/**
	 * @param msgMensajeDocumentoList
	 *            the msgMensajeDocumentoList to set
	 */
	public void setMsgMensajeDocumentoList(List msgMensajeDocumentoList) {
		this.msgMensajeDocumentoList = msgMensajeDocumentoList;
	}

	/**
	 * @return the msgPatronMensajeList
	 */
	public List getMsgPatronMensajeList() {
		return msgPatronMensajeList;
	}

	/**
	 * @param msgPatronMensajeList
	 *            the msgPatronMensajeList to set
	 */
	public void setMsgPatronMensajeList(List msgPatronMensajeList) {
		this.msgPatronMensajeList = msgPatronMensajeList;
	}

	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario
	 *            the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the ordenaAction
	 */
	public MantenimientoMENPatronMensajeOrdenaAction getOrdenaAction() {
		return ordenaAction;
	}

	/**
	 * @param ordenaAction
	 *            the ordenaAction to set
	 */
	public void setOrdenaAction(
			MantenimientoMENPatronMensajeOrdenaAction ordenaAction) {
		this.ordenaAction = ordenaAction;
	}

	/**
	 * @return the msgSeccionList
	 */
	public List getMsgSeccionList() {
		return msgSeccionList;
	}

	/**
	 * @param msgSeccionList
	 *            the msgSeccionList to set
	 */
	public void setMsgSeccionList(List msgSeccionList) {
		this.msgSeccionList = msgSeccionList;
	}

	/**
	 * @return the msgModuloList
	 */
	public List getMsgModuloList() {
		return msgModuloList;
	}

	/**
	 * @param msgModuloList
	 *            the msgModuloList to set
	 */
	public void setMsgModuloList(List msgModuloList) {
		this.msgModuloList = msgModuloList;
	}

	/**
	 * @return the msgConsideracionList
	 */
	public LabelValue[] getMsgConsideracionList() {
		return msgConsideracionList;
	}

	/**
	 * @param msgConsideracionList
	 *            the msgConsideracionList to set
	 */
	public void setMsgConsideracionList(LabelValue[] msgConsideracionList) {
		this.msgConsideracionList = msgConsideracionList;
	}

	/**
	 * @return the msgRestriccionList
	 */
	public LabelValue[] getMsgRestriccionList() {
		return msgRestriccionList;
	}

	/**
	 * @param msgRestriccionList
	 *            the msgRestriccionList to set
	 */
	public void setMsgRestriccionList(LabelValue[] msgRestriccionList) {
		this.msgRestriccionList = msgRestriccionList;
	}

	/**
	 * @return the msgPatronConsideracionList
	 */
	public List getMsgPatronConsideracionList() {
		return msgPatronConsideracionList;
	}

	/**
	 * @param msgPatronConsideracionList
	 *            the msgPatronConsideracionList to set
	 */
	public void setMsgPatronConsideracionList(List msgPatronConsideracionList) {
		this.msgPatronConsideracionList = msgPatronConsideracionList;
	}

	/**
	 * @return the msgPatronRestriccionList
	 */
	public List getMsgPatronRestriccionList() {
		return msgPatronRestriccionList;
	}

	/**
	 * @param msgPatronRestriccionList
	 *            the msgPatronRestriccionList to set
	 */
	public void setMsgPatronRestriccionList(List msgPatronRestriccionList) {
		this.msgPatronRestriccionList = msgPatronRestriccionList;
	}

	/**
	 * @return the dataTableModelMensaje
	 */
	public DataTableModel getDataTableModelMensaje() {
		return dataTableModelMensaje;
	}

	/**
	 * @param dataTableModelMensaje
	 *            the dataTableModelMensaje to set
	 */
	public void setDataTableModelMensaje(DataTableModel dataTableModelMensaje) {
		this.dataTableModelMensaje = dataTableModelMensaje;
	}

	/**
	 * @return the msgMensajePatronList
	 */
	public List getMsgMensajePatronList() {
		return msgMensajePatronList;
	}

	/**
	 * @param msgMensajePatronList
	 *            the msgMensajePatronList to set
	 */
	public void setMsgMensajePatronList(List msgMensajePatronList) {
		this.msgMensajePatronList = msgMensajePatronList;
	}

	/**
	 * @return the replicaAction
	 */
	public MantenimientoMENPatronMensajeReplicaAction getReplicaAction() {
		return replicaAction;
	}

	/**
	 * @param replicaAction
	 *            the replicaAction to set
	 */
	public void setReplicaAction(
			MantenimientoMENPatronMensajeReplicaAction replicaAction) {
		this.replicaAction = replicaAction;
	}

	/**
	 * @return the bloqueBuscarDisabled
	 */
	public boolean isBloqueBuscarDisabled() {
		return bloqueBuscarDisabled;
	}

	/**
	 * @param bloqueBuscarDisabled
	 *            the bloqueBuscarDisabled to set
	 */
	public void setBloqueBuscarDisabled(boolean bloqueBuscarDisabled) {
		this.bloqueBuscarDisabled = bloqueBuscarDisabled;
	}

	/**
	 * @return the bloqueNuevoMensaje
	 */
	public boolean isBloqueNuevoMensaje() {
		return bloqueNuevoMensaje;
	}

	/**
	 * @param bloqueNuevoMensaje
	 *            the bloqueNuevoMensaje to set
	 */
	public void setBloqueNuevoMensaje(boolean bloqueNuevoMensaje) {
		this.bloqueNuevoMensaje = bloqueNuevoMensaje;
	}

	/**
	 * @return the botonBuscar
	 */
	public boolean isBotonBuscar() {
		return botonBuscar;
	}

	/**
	 * @param botonBuscar
	 *            the botonBuscar to set
	 */
	public void setBotonBuscar(boolean botonBuscar) {
		this.botonBuscar = botonBuscar;
	}

	/**
	 * @return the botonNuevoMensaje
	 */
	public boolean isBotonNuevoMensaje() {
		return botonNuevoMensaje;
	}

	/**
	 * @param botonNuevoMensaje
	 *            the botonNuevoMensaje to set
	 */
	public void setBotonNuevoMensaje(boolean botonNuevoMensaje) {
		this.botonNuevoMensaje = botonNuevoMensaje;
	}

	/**
	 * @return the botonGrabarMensaje
	 */
	public boolean isBotonGrabarMensaje() {
		return botonGrabarMensaje;
	}

	/**
	 * @param botonGrabarMensaje
	 *            the botonGrabarMensaje to set
	 */
	public void setBotonGrabarMensaje(boolean botonGrabarMensaje) {
		this.botonGrabarMensaje = botonGrabarMensaje;
	}

	/**
	 * @return the botonLimpiar
	 */
	public boolean isBotonLimpiar() {
		return botonLimpiar;
	}

	/**
	 * @param botonLimpiar
	 *            the botonLimpiar to set
	 */
	public void setBotonLimpiar(boolean botonLimpiar) {
		this.botonLimpiar = botonLimpiar;
	}

	/**
	 * @return the codigoMensajeDisabled
	 */
	public boolean isCodigoMensajeDisabled() {
		return codigoMensajeDisabled;
	}

	/**
	 * @param codigoMensajeDisabled
	 *            the codigoMensajeDisabled to set
	 */
	public void setCodigoMensajeDisabled(boolean codigoMensajeDisabled) {
		this.codigoMensajeDisabled = codigoMensajeDisabled;
	}

	/**
	 * @return the ajax
	 */
	public AjaxService getAjax() {
		return ajax;
	}

	/**
	 * @param ajax
	 *            the ajax to set
	 */
	public void setAjax(AjaxService ajax) {
		this.ajax = ajax;
	}

	/**
	 * @return the beanSeleccionadoMensajePatron
	 */
	public Object getBeanSeleccionadoMensajePatron() {
		return beanSeleccionadoMensajePatron;
	}

	/**
	 * @param beanSeleccionadoMensajePatron
	 *            the beanSeleccionadoMensajePatron to set
	 */
	public void setBeanSeleccionadoMensajePatron(
			Object beanSeleccionadoMensajePatron) {
		this.beanSeleccionadoMensajePatron = beanSeleccionadoMensajePatron;
	}

	/**
	 * @return the indicadorManualBoolean
	 */
	public boolean isIndicadorManualBoolean() {
		return indicadorManualBoolean;
	}

	/**
	 * @param indicadorManualBoolean
	 *            the indicadorManualBoolean to set
	 */
	public void setIndicadorManualBoolean(boolean indicadorManualBoolean) {
		this.indicadorManualBoolean = indicadorManualBoolean;
	}

	/**
	 * @return the dataTablePatronConsideracion
	 */
	public DataTableModel getDataTablePatronConsideracion() {
		return dataTablePatronConsideracion;
	}

	/**
	 * @param dataTablePatronConsideracion
	 *            the dataTablePatronConsideracion to set
	 */
	public void setDataTablePatronConsideracion(
			DataTableModel dataTablePatronConsideracion) {
		this.dataTablePatronConsideracion = dataTablePatronConsideracion;
	}

	/**
	 * @return the consultaAction
	 */
	public MantenimientoMENPatronMensajeConsultaAction getConsultaAction() {
		return consultaAction;
	}

	/**
	 * @param consultaAction
	 *            the consultaAction to set
	 */
	public void setConsultaAction(
			MantenimientoMENPatronMensajeConsultaAction consultaAction) {
		this.consultaAction = consultaAction;
	}

	/**
	 * @return the camposRequeridos
	 */
	public String getCamposRequeridos() {
		return camposRequeridos;
	}

	/**
	 * @param camposRequeridos
	 *            the camposRequeridos to set
	 */
	public void setCamposRequeridos(String camposRequeridos) {
		this.camposRequeridos = camposRequeridos;
	}

	/**
	 * @return the codigoVentaList
	 */
	public List getCodigoVentaList() {
		return codigoVentaList;
	}

	/**
	 * @param codigoVentaList
	 *            the codigoVentaList to set
	 */
	public void setCodigoVentaList(List codigoVentaList) {
		this.codigoVentaList = codigoVentaList;
	}

	/**
	 * @return the codigoPremioList
	 */
	public List getCodigoPremioList() {
		return codigoPremioList;
	}

	/**
	 * @param codigoPremioList
	 *            the codigoPremioList to set
	 */
	public void setCodigoPremioList(List codigoPremioList) {
		this.codigoPremioList = codigoPremioList;
	}

	/**
	 * @return the codigoVentaReemplazoList
	 */
	public List getCodigoVentaReemplazoList() {
		return codigoVentaReemplazoList;
	}

	/**
	 * @param codigoVentaReemplazoList
	 *            the codigoVentaReemplazoList to set
	 */
	public void setCodigoVentaReemplazoList(List codigoVentaReemplazoList) {
		this.codigoVentaReemplazoList = codigoVentaReemplazoList;
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
	 * @return the buzonLoteList
	 */
	public List getBuzonLoteList() {
		return buzonLoteList;
	}

	/**
	 * @param buzonLoteList
	 *            the buzonLoteList to set
	 */
	public void setBuzonLoteList(List buzonLoteList) {
		this.buzonLoteList = buzonLoteList;
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
	 * @return the sACestadosList
	 */
	public List getSACestadosList() {
		return SACestadosList;
	}

	/**
	 * @param sACestadosList
	 *            the sACestadosList to set
	 */
	public void setSACestadosList(List sACestadosList) {
		SACestadosList = sACestadosList;
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
	 * @return the siccSeccionList
	 */
	public LabelValue[] getSiccSeccionList() {
		return siccSeccionList;
	}

	/**
	 * @param siccSeccionList
	 *            the siccSeccionList to set
	 */
	public void setSiccSeccionList(LabelValue[] siccSeccionList) {
		this.siccSeccionList = siccSeccionList;
	}

	/**
	 * @return the siccTerritorioList
	 */
	public LabelValue[] getSiccTerritorioList() {
		return siccTerritorioList;
	}

	/**
	 * @param siccTerritorioList
	 *            the siccTerritorioList to set
	 */
	public void setSiccTerritorioList(LabelValue[] siccTerritorioList) {
		this.siccTerritorioList = siccTerritorioList;
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
	 * @return the buzonLoteListR
	 */
	public List getBuzonLoteListR() {
		return buzonLoteListR;
	}

	/**
	 * @param buzonLoteListR
	 *            the buzonLoteListR to set
	 */
	public void setBuzonLoteListR(List buzonLoteListR) {
		this.buzonLoteListR = buzonLoteListR;
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
	 * @return the codigoPremioListR
	 */
	public List getCodigoPremioListR() {
		return codigoPremioListR;
	}

	/**
	 * @param codigoPremioListR
	 *            the codigoPremioListR to set
	 */
	public void setCodigoPremioListR(List codigoPremioListR) {
		this.codigoPremioListR = codigoPremioListR;
	}

	/**
	 * @return the codigoVentaListR
	 */
	public List getCodigoVentaListR() {
		return codigoVentaListR;
	}

	/**
	 * @param codigoVentaListR
	 *            the codigoVentaListR to set
	 */
	public void setCodigoVentaListR(List codigoVentaListR) {
		this.codigoVentaListR = codigoVentaListR;
	}

	/**
	 * @return the codigoVentaReemplazoListR
	 */
	public List getCodigoVentaReemplazoListR() {
		return codigoVentaReemplazoListR;
	}

	/**
	 * @param codigoVentaReemplazoListR
	 *            the codigoVentaReemplazoListR to set
	 */
	public void setCodigoVentaReemplazoListR(List codigoVentaReemplazoListR) {
		this.codigoVentaReemplazoListR = codigoVentaReemplazoListR;
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
	 * @return the comDetalleClasificacion
	 */
	public DataTableModel getComDetalleClasificacion() {
		return comDetalleClasificacion;
	}

	/**
	 * @param comDetalleClasificacion
	 *            the comDetalleClasificacion to set
	 */
	public void setComDetalleClasificacion(
			DataTableModel comDetalleClasificacion) {
		this.comDetalleClasificacion = comDetalleClasificacion;
	}

	/**
	 * @return the beanRegistroClasificacion
	 */
	public Object getBeanRegistroClasificacion() {
		return beanRegistroClasificacion;
	}

	/**
	 * @param beanRegistroClasificacion
	 *            the beanRegistroClasificacion to set
	 */
	public void setBeanRegistroClasificacion(Object beanRegistroClasificacion) {
		this.beanRegistroClasificacion = beanRegistroClasificacion;
	}

	/**
	 * @return the comDetalleUnidad
	 */
	public DataTableModel getComDetalleUnidad() {
		return comDetalleUnidad;
	}

	/**
	 * @param comDetalleUnidad
	 *            the comDetalleUnidad to set
	 */
	public void setComDetalleUnidad(DataTableModel comDetalleUnidad) {
		this.comDetalleUnidad = comDetalleUnidad;
	}

	/**
	 * @return the beanRegistroUnidad
	 */
	public Object getBeanRegistroUnidad() {
		return beanRegistroUnidad;
	}

	/**
	 * @param beanRegistroUnidad
	 *            the beanRegistroUnidad to set
	 */
	public void setBeanRegistroUnidad(Object beanRegistroUnidad) {
		this.beanRegistroUnidad = beanRegistroUnidad;
	}

	/**
	 * @return the comDetalleCodigoVenta
	 */
	public DataTableModel getComDetalleCodigoVenta() {
		return comDetalleCodigoVenta;
	}

	/**
	 * @param comDetalleCodigoVenta
	 *            the comDetalleCodigoVenta to set
	 */
	public void setComDetalleCodigoVenta(DataTableModel comDetalleCodigoVenta) {
		this.comDetalleCodigoVenta = comDetalleCodigoVenta;
	}

	/**
	 * @return the beanRegistroCodigoVenta
	 */
	public Object getBeanRegistroCodigoVenta() {
		return beanRegistroCodigoVenta;
	}

	/**
	 * @param beanRegistroCodigoVenta
	 *            the beanRegistroCodigoVenta to set
	 */
	public void setBeanRegistroCodigoVenta(Object beanRegistroCodigoVenta) {
		this.beanRegistroCodigoVenta = beanRegistroCodigoVenta;
	}

	/**
	 * @return the cantidadListaCodigoVenta
	 */
	public int getCantidadListaCodigoVenta() {
		return cantidadListaCodigoVenta;
	}

	/**
	 * @param cantidadListaCodigoVenta
	 *            the cantidadListaCodigoVenta to set
	 */
	public void setCantidadListaCodigoVenta(int cantidadListaCodigoVenta) {
		this.cantidadListaCodigoVenta = cantidadListaCodigoVenta;
	}

	/**
	 * @return the comDetalleEstatus
	 */
	public DataTableModel getComDetalleEstatus() {
		return comDetalleEstatus;
	}

	/**
	 * @param comDetalleEstatus
	 *            the comDetalleEstatus to set
	 */
	public void setComDetalleEstatus(DataTableModel comDetalleEstatus) {
		this.comDetalleEstatus = comDetalleEstatus;
	}

	/**
	 * @return the beanRegistroEstatus
	 */
	public Object getBeanRegistroEstatus() {
		return beanRegistroEstatus;
	}

	/**
	 * @param beanRegistroEstatus
	 *            the beanRegistroEstatus to set
	 */
	public void setBeanRegistroEstatus(Object beanRegistroEstatus) {
		this.beanRegistroEstatus = beanRegistroEstatus;
	}

	/**
	 * @return the beanRegistroPatronConsideracion
	 */
	public Object getBeanRegistroPatronConsideracion() {
		return beanRegistroPatronConsideracion;
	}

	/**
	 * @param beanRegistroPatronConsideracion
	 *            the beanRegistroPatronConsideracion to set
	 */
	public void setBeanRegistroPatronConsideracion(
			Object beanRegistroPatronConsideracion) {
		this.beanRegistroPatronConsideracion = beanRegistroPatronConsideracion;
	}

	/**
	 * @return the flagEditar
	 */
	public boolean isFlagEditar() {
		return flagEditar;
	}

	/**
	 * @param flagEditar
	 *            the flagEditar to set
	 */
	public void setFlagEditar(boolean flagEditar) {
		this.flagEditar = flagEditar;
	}

	/**
	 * @return the codigoConsRestr
	 */
	public String getCodigoConsRestr() {
		return codigoConsRestr;
	}

	/**
	 * @param codigoConsRestr
	 *            the codigoConsRestr to set
	 */
	public void setCodigoConsRestr(String codigoConsRestr) {
		this.codigoConsRestr = codigoConsRestr;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path
	 *            the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @return the botonEdit
	 */
	public boolean isBotonEdit() {
		return botonEdit;
	}

	/**
	 * @param botonEdit
	 *            the botonEdit to set
	 */
	public void setBotonEdit(boolean botonEdit) {
		this.botonEdit = botonEdit;
	}

	/**
	 * @return the descripcionMensajeBoolean
	 */
	public boolean isDescripcionMensajeBoolean() {
		return descripcionMensajeBoolean;
	}

	/**
	 * @param descripcionMensajeBoolean
	 *            the descripcionMensajeBoolean to set
	 */
	public void setDescripcionMensajeBoolean(boolean descripcionMensajeBoolean) {
		this.descripcionMensajeBoolean = descripcionMensajeBoolean;
	}

	/**
	 * @return the indicadorActivoBoolean
	 */
	public boolean isIndicadorActivoBoolean() {
		return indicadorActivoBoolean;
	}

	/**
	 * @param indicadorActivoBoolean
	 *            the indicadorActivoBoolean to set
	 */
	public void setIndicadorActivoBoolean(boolean indicadorActivoBoolean) {
		this.indicadorActivoBoolean = indicadorActivoBoolean;
	}

	/**
	 * @return the descripcionPatronBoolean
	 */
	public boolean isDescripcionPatronBoolean() {
		return descripcionPatronBoolean;
	}

	/**
	 * @param descripcionPatronBoolean
	 *            the descripcionPatronBoolean to set
	 */
	public void setDescripcionPatronBoolean(boolean descripcionPatronBoolean) {
		this.descripcionPatronBoolean = descripcionPatronBoolean;
	}

	/**
	 * @return the consResBoolean
	 */
	public boolean isConsResBoolean() {
		return consResBoolean;
	}

	/**
	 * @param consResBoolean
	 *            the consResBoolean to set
	 */
	public void setConsResBoolean(boolean consResBoolean) {
		this.consResBoolean = consResBoolean;
	}

	/**
	 * @return the indexPatronMensaje
	 */
	public String getIndexPatronMensaje() {
		return indexPatronMensaje;
	}

	/**
	 * @param indexPatronMensaje
	 *            the indexPatronMensaje to set
	 */
	public void setIndexPatronMensaje(String indexPatronMensaje) {
		this.indexPatronMensaje = indexPatronMensaje;
	}

	/**
	 * @return the comDetalleListaConsultora
	 */
	public DataTableModel getComDetalleListaConsultora() {
		return comDetalleListaConsultora;
	}

	/**
	 * @param comDetalleListaConsultora
	 *            the comDetalleListaConsultora to set
	 */
	public void setComDetalleListaConsultora(
			DataTableModel comDetalleListaConsultora) {
		this.comDetalleListaConsultora = comDetalleListaConsultora;
	}

	/**
	 * @return the beanRegistroListaConsultora
	 */
	public Object getBeanRegistroListaConsultora() {
		return beanRegistroListaConsultora;
	}

	/**
	 * @param beanRegistroListaConsultora
	 *            the beanRegistroListaConsultora to set
	 */
	public void setBeanRegistroListaConsultora(
			Object beanRegistroListaConsultora) {
		this.beanRegistroListaConsultora = beanRegistroListaConsultora;
	}

	/**
	 * @return the montoCatalogoBoolean
	 */
	public boolean isMontoCatalogoBoolean() {
		return montoCatalogoBoolean;
	}

	/**
	 * @param montoCatalogoBoolean
	 *            the montoCatalogoBoolean to set
	 */
	public void setMontoCatalogoBoolean(boolean montoCatalogoBoolean) {
		this.montoCatalogoBoolean = montoCatalogoBoolean;
	}

	public String getCampanhaActual() {
		return campanhaActual;
	}

	public void setCampanhaActual(String campanhaActual) {
		this.campanhaActual = campanhaActual;
	}

	/**
	 * @return the codigoCuvFaltanteList
	 */
	public List getCodigoCuvFaltanteList() {
		return codigoCuvFaltanteList;
	}

	/**
	 * @param codigoCuvFaltanteList the codigoCuvFaltanteList to set
	 */
	public void setCodigoCuvFaltanteList(List codigoCuvFaltanteList) {
		this.codigoCuvFaltanteList = codigoCuvFaltanteList;
	}

	/**
	 * @return the comDetalleCodigoCuvFaltante
	 */
	public DataTableModel getComDetalleCodigoCuvFaltante() {
		return comDetalleCodigoCuvFaltante;
	}

	/**
	 * @param comDetalleCodigoCuvFaltante the comDetalleCodigoCuvFaltante to set
	 */
	public void setComDetalleCodigoCuvFaltante(
			DataTableModel comDetalleCodigoCuvFaltante) {
		this.comDetalleCodigoCuvFaltante = comDetalleCodigoCuvFaltante;
	}

	/**
	 * @return the beanRegistroCodigoCuvFaltante
	 */
	public Object getBeanRegistroCodigoCuvFaltante() {
		return beanRegistroCodigoCuvFaltante;
	}

	/**
	 * @param beanRegistroCodigoCuvFaltante the beanRegistroCodigoCuvFaltante to set
	 */
	public void setBeanRegistroCodigoCuvFaltante(
			Object beanRegistroCodigoCuvFaltante) {
		this.beanRegistroCodigoCuvFaltante = beanRegistroCodigoCuvFaltante;
	}

	/**
	 * @return the attachment
	 */
	public String getAttachment() {
		return attachment;
	}

	/**
	 * @param attachment the attachment to set
	 */
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	

	/**
	 * @return the pathRutaImgJava
	 */
	public String getPathRutaImgJava() {
		return pathRutaImgJava;
	}

	/**
	 * @param pathRutaImgJava the pathRutaImgJava to set
	 */
	public void setPathRutaImgJava(String pathRutaImgJava) {
		this.pathRutaImgJava = pathRutaImgJava;
	}

	/**
	 * @return the pathRutaImgOracle
	 */
	public String getPathRutaImgOracle() {
		return pathRutaImgOracle;
	}

	/**
	 * @param pathRutaImgOracle the pathRutaImgOracle to set
	 */
	public void setPathRutaImgOracle(String pathRutaImgOracle) {
		this.pathRutaImgOracle = pathRutaImgOracle;
	}

	/**
	 * @return the nombreImagenFondo
	 */
	public String getNombreImagenFondo() {
		return nombreImagenFondo;
	}

	/**
	 * @param nombreImagenFondo the nombreImagenFondo to set
	 */
	public void setNombreImagenFondo(String nombreImagenFondo) {
		this.nombreImagenFondo = nombreImagenFondo;
	}

	

	/**
	 * @return the msgMensajeDocumentoFlyersList
	 */
	public List getMsgMensajeDocumentoFlyersList() {
		return msgMensajeDocumentoFlyersList;
	}

	/**
	 * @param msgMensajeDocumentoFlyersList the msgMensajeDocumentoFlyersList to set
	 */
	public void setMsgMensajeDocumentoFlyersList(List msgMensajeDocumentoFlyersList) {
		this.msgMensajeDocumentoFlyersList = msgMensajeDocumentoFlyersList;
	}

	/**
	 * @return the msgMensajeBandejaFlyersList
	 */
	public List getMsgMensajeBandejaFlyersList() {
		return msgMensajeBandejaFlyersList;
	}

	/**
	 * @param msgMensajeBandejaFlyersList the msgMensajeBandejaFlyersList to set
	 */
	public void setMsgMensajeBandejaFlyersList(List msgMensajeBandejaFlyersList) {
		this.msgMensajeBandejaFlyersList = msgMensajeBandejaFlyersList;
	}

	/**
	 * @return the campanhaProcesoFlyers
	 */
	public String getCampanhaProcesoFlyers() {
		return campanhaProcesoFlyers;
	}

	/**
	 * @param campanhaProcesoFlyers the campanhaProcesoFlyers to set
	 */
	public void setCampanhaProcesoFlyers(String campanhaProcesoFlyers) {
		this.campanhaProcesoFlyers = campanhaProcesoFlyers;
	}

	/**
	 * @return the codigoDocumentoFlyers
	 */
	public String getCodigoDocumentoFlyers() {
		return codigoDocumentoFlyers;
	}

	/**
	 * @param codigoDocumentoFlyers the codigoDocumentoFlyers to set
	 */
	public void setCodigoDocumentoFlyers(String codigoDocumentoFlyers) {
		this.codigoDocumentoFlyers = codigoDocumentoFlyers;
	}

	/**
	 * @return the descripcionPatronFlyers
	 */
	public String getDescripcionPatronFlyers() {
		return descripcionPatronFlyers;
	}

	/**
	 * @param descripcionPatronFlyers the descripcionPatronFlyers to set
	 */
	public void setDescripcionPatronFlyers(String descripcionPatronFlyers) {
		this.descripcionPatronFlyers = descripcionPatronFlyers;
	}

	/**
	 * @return the codigoBandejaFlyers
	 */
	public String getCodigoBandejaFlyers() {
		return codigoBandejaFlyers;
	}

	/**
	 * @param codigoBandejaFlyers the codigoBandejaFlyers to set
	 */
	public void setCodigoBandejaFlyers(String codigoBandejaFlyers) {
		this.codigoBandejaFlyers = codigoBandejaFlyers;
	}

	/**
	 * @return the esRegistroNuevoFlyers
	 */
	public boolean isEsRegistroNuevoFlyers() {
		return esRegistroNuevoFlyers;
	}

	/**
	 * @param esRegistroNuevoFlyers the esRegistroNuevoFlyers to set
	 */
	public void setEsRegistroNuevoFlyers(boolean esRegistroNuevoFlyers) {
		this.esRegistroNuevoFlyers = esRegistroNuevoFlyers;
	}
	
	
}
