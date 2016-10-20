package biz.belcorp.ssicc.web.spusicc.mav.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
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
import biz.belcorp.ssicc.service.spusicc.inc.MantenimientoINCConfiguracionConcursoService;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.service.spusicc.mav.MantenimientoMAVConfiguracionService;
import biz.belcorp.ssicc.service.spusicc.men.MantenimientoMENGenericoService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECCodigoVentaOperaService;
import biz.belcorp.ssicc.service.spusicc.zon.ProcesoZONActualizarUnidadesGeograficasService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.scsicc.hip.action.BusquedaProductoSearchAction;
import biz.belcorp.ssicc.web.spusicc.mav.form.MantenimientoMAVConfiguracionCondicionEnvioForm;
import biz.belcorp.ssicc.web.spusicc.mav.form.MantenimientoMAVConfiguracionForm;
import biz.belcorp.ssicc.web.spusicc.mav.form.MantenimientoMAVConfiguracionSearchForm;
import biz.belcorp.ssicc.web.spusicc.mav.form.ReporteMAVConfiguracionForm;

@SessionScoped
@ManagedBean
public class MantenimientoMAVConfiguracionSearchAction extends BaseMantenimientoSearchAbstractAction {
	
	private static final long serialVersionUID = -3561723479961449154L;
	
	private List mavActividadesList;
	private LabelValue [] mavTiposOfertaList = {};
	private LabelValue [] mavTiposOfertaMantList = {};
	private List mavEstadosList;
	private List siccMAVTipoClienteList;
	private List siccMAVOrigenList;
	private List mavConfiguracionesList;
	
	private List mavConsideracionTodosList;
	private List mavRestriccionTodosList;

	private LabelValue [] mavConsideracionList;
	private LabelValue [] mavRestriccionList;

	private String periodoActual;

	private List incUnidadNegocioList;
	private List incNegocioList;
	private List incMarcaProductoList;
	private List recCodigoCatalogoList;
	private List mavCodigoTipoVentaList;

	private List clasificacionesList;
	private List unidadesList;
	private List consultoraList;
	private List regionesList;
	private List codigoEstatusList;
	private List clasificacionesListR;
	private List unidadesListR;
	private List consultoraListR;
	private List regionesListR;
	private List codigoEstatusListR;

	private List mavConfiguracionConsideracionList;
	private List mavConfiguracionRestriccionList;
	
	private List consultorasEditList;	
	private List consultorasList;
	private List consultorasListR;
	private List siccCapacitadoraList;
	private List siccRegionList;
	private LabelValue [] siccZonaList;
	private List siccTipoClienteList;
	private LabelValue [] siccSeccionList;
	private LabelValue [] siccTerritorioList;
	private List SACestadosList;
	private LabelValue [] siccSubTipoClienteList;
	private LabelValue [] siccTipoClasificacionList;
	private LabelValue [] siccClasificacionList;
	private List regionesEditList;
	
	private String idRestriccion;
	private String indicadorTipo;
	private String indicadorCerrarVentana;
	private String codigoIdiomaISO;
	private String correlativo;
	private String indicadorUnidadPopup;
	private String codigoPeriodo;
	
	private DataTableModel dataTableConsideracion;
	private DataTableModel dataTableRestriccion;
	private DataTableModel dataTableDetalleConsideracion;
	private DataTableModel dataTableDetalleRestriccion;
	private DataTableModel dataTableEditRegionZona;
	private DataTableModel dataTableEditConsultora;
	
	private Object beanRegistroConsideracion;
	private Object beanRegistroRestriccion;
	private Object beanRegistroDetalleConsideracion;
	private Object beanRegistroDetalleRestriccion;
	private Object beanRegistroEditRegionZona;
	private Object beanRegistroEditConsultora;
	
	private boolean idConsideracionInsertar;
	private boolean idConsideracionEditar;
	private boolean idConsideracionActualizar;
	private boolean idRestriccionInsertar;
	private boolean idRestriccionEditar;
	private boolean idRestriccionActualizar;
	
	private boolean mostrarCampoUnidades;
	private boolean habilitarRestricciones;

	private boolean esBotonInsertar; //PARA DIFERENCIAR si el Popup es invocado por el boton Insertar o Editar
	private String codigoConsRest;
	private boolean seleccionable; //PARA EL POPUP
	private String nombreVentanaPopup;
	
	private boolean mostrarConsideracionSimple;
	private boolean mostrarConsideracionUnico;
	private boolean mostrarConsideracionDoble;
	private boolean mostrarConsideracionMixto;
	
	private boolean mostrarRestriccionSimple;
	private boolean mostrarRestriccionUnico;
	private boolean mostrarRestriccionDoble;
	private boolean mostrarRestriccionMixto;

	private boolean mostrarThUnicoUnidades;
	private boolean mostrarTdUnicoUnidades;
	private boolean mostrarThUnicoUnidadesRest;
	private boolean mostrarTdUnicoUnidadesRest;
	
	private boolean mostrarThDobleUnidades;
	private boolean mostrarTdDobleUnidades;
	private boolean mostrarThDobleUnidadesRest;
	private boolean mostrarTdDobleUnidadesRest;

	private boolean mostrarThMixtoUnidades;
	private boolean mostrarTdMixtoUnidades;
	private boolean mostrarThMixtoUnidadesRest;
	private boolean mostrarTdMixtoUnidadesRest;
	
	private boolean mostrarTdMixtoMonto1;
	private boolean mostrarTdMixtoMonto2;
	private boolean mostrarTdMixtoMarca1;
	private boolean mostrarTdMixtoMarca2;
	private boolean mostrarTdMixtoNegocio1;
	private boolean mostrarTdMixtoNegocio2;
	private boolean mostrarTdMixtoUnidadNegocio1;
	private boolean mostrarTdMixtoUnidadNegocio2;
	private boolean mostrarTdMixtoCatalogo1;
	private boolean mostrarTdMixtoCatalogo2;
	private boolean mostrarTdMontoFinanciadoDesde;
	private boolean mostrarTdMontoFinanciadoHasta1;
	private boolean mostrarTdMontoFinanciadoHasta2;
	private boolean mostrarTdPeriodoFinanciado1;
	private boolean mostrarTdPeriodoFinanciado2;
	private boolean mostrarThMixtoFormula;
	private boolean mostrarThMixtoVariableVentas;
	private boolean mostrarTdMixtoFormula;
	private boolean mostrarThMixtoNroPeriodosFV;
	private boolean mostrarTdMixtoNroPeriodosFV;
	
	private boolean mostrarTdMixtoPeriodoInicio1;
	private boolean mostrarTdMixtoPeriodoInicio2;
	private boolean mostrarTdMixtoPeriodoFin1;
	private boolean mostrarTdMixtoPeriodoFin2;
	private boolean mostrarThMixtoTipoVenta;
	private boolean mostrarTdMixtoTipoVenta2;
	
	private boolean mostrarTdMixtoMonto1Rest;
	private boolean mostrarTdMixtoMonto2Rest;
	private boolean mostrarTdMixtoMarca1Rest;
	private boolean mostrarTdMixtoMarca2Rest;
	private boolean mostrarTdMixtoNegocio1Rest;
	private boolean mostrarTdMixtoNegocio2Rest;
	private boolean mostrarTdMixtoUnidadNegocio1Rest;
	private boolean mostrarTdMixtoUnidadNegocio2Rest;
	private boolean mostrarTdMixtoCatalogo1Rest;
	private boolean mostrarTdMixtoCatalogo2Rest;
	
	private UploadedFile archivo;	//objeto que se utilizara para el upload del Archivo
	private String directorioTemporal;  //directorio temporal del servidor donde se guardara el archivo
	private String nombreArchivo;	//nombre del archivo a subirse al servidor
	private String extensionArchivo;	//extension del archivo
	
	private String numRegistros;
	private String numRegistrosError;
	
	private String indicadorValido;
	private String numRegistrosValido;
	
	private List mavArchivolist;
	private List mavArchivoRegioneslist;
	private String viewValida;
	private String  attachment = "";
	private String tipoArchivo;
	
	private boolean mostrarBotonValidar;
	private boolean mostrarBotonExecute;
	private boolean mostrarPrimeraGrilla;
	private boolean mostrarSegundaGrilla;
	public boolean mostrarBotonActualizar;
	
	public boolean editableUnidadesIniciado; //Para el Caso de MAV con estado = Iniciado
	
	private static final String POPUP_SACPRODUCTO = "SACPRODUCTO";
	private static final String POPUP_SACPRODUCTO_MANT = "SACPRODUCTOMANT";
	
	private boolean activarComboRestriccion;
	
	@ManagedProperty(value = "#{busquedaProductoSearchAction}")
	private BusquedaProductoSearchAction busquedaProductoSearchAction;
	private boolean mostrarPopupProducto;
	
	@ManagedProperty(value = "#{procesoMAVGenerarEnviosAction}")
	protected ProcesoMAVGenerarEnviosAction procesoMAVGenerarEnviosAction;
	
	@ManagedProperty(value = "#{procesoMAVCargaMasivaAction}")
	protected ProcesoMAVCargaMasivaAction procesoMAVCargaMasivaAction;
	
	@ManagedProperty(value = "#{reporteMAVConfiguracionAction}")
	private ReporteMAVConfiguracionAction reporteConfiguracion;
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setInvocarPopup(java.lang.String)
	 */
	@Override
	protected void setInvocarPopup(String accion) {

		this.mostrarProcesoBatch = false;
		this.mostrarPopupProducto = true;

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
			log.debug("Entering 'setAceptarPopup' method");
		}
		this.mostrarProcesoBatch = true;
		MantenimientoMAVConfiguracionSearchForm form = (MantenimientoMAVConfiguracionSearchForm) this.formBusqueda;

		if (accion.equals(POPUP_SACPRODUCTO)) {
			this.mostrarPopupProducto = false;

			this.busquedaProductoSearchAction.verificarRegistro(event);
			if (this.busquedaProductoSearchAction.isSeleccionoRegistro()) {

				Map prodMap = (Map) this.busquedaProductoSearchAction.getBeanRegistroSeleccionado();

				form.setCodigoProductoBusq(MapUtils.getString(prodMap, "codigoSap"));
				form.setDescripcionProductoBusq(MapUtils.getString(prodMap, "descripcionCorta"));
				this.busquedaProductoSearchAction.setBeanRegistroSeleccionado(null);
			}
			
			this.formBusqueda = form;
		}
		
		if (accion.equals(POPUP_SACPRODUCTO_MANT)) {
			MantenimientoMAVConfiguracionForm f = (MantenimientoMAVConfiguracionForm) this.formMantenimiento;
			this.mostrarPopupProducto = false;

			this.busquedaProductoSearchAction.verificarRegistro(event);
			if (this.busquedaProductoSearchAction.isSeleccionoRegistro()) {

				Map prodMap = (Map) this.busquedaProductoSearchAction.getBeanRegistroSeleccionado();
				//RequestContext.getCurrentInstance().reset("principalForm:panelProducto");
				f.setCodigoProducto(MapUtils.getString(prodMap, "codigoSap"));
				f.setDescripcionProducto(MapUtils.getString(prodMap, "descripcionCorta"));
				this.busquedaProductoSearchAction.setBeanRegistroSeleccionado(null);
			}
			
			this.formMantenimiento = f;
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
		this.mostrarProcesoBatch = true;
		this.mostrarPopupProducto = false;
		this.busquedaProductoSearchAction.setBeanRegistroSeleccionado(null);
	}

	
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}
		
		MantenimientoMAVConfiguracionSearchForm f = (MantenimientoMAVConfiguracionSearchForm) this.formBusqueda;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());			
		
		MantenimientoMAVConfiguracionService service = (MantenimientoMAVConfiguracionService) 
										this.getBean("spusicc.mantenimientoMAVConfiguracionService");
		
		//combo actividades
		this.mavActividadesList = service.getActividades();
		
		//Validar el combo Actividad ya que la descripcion
		//se genera mediante una ejecucion pero en un caso hubo
		//que lo genera vacío y salía un error
		for (int i = 0; i < this.mavActividadesList.size(); i++) {
			Base data = (Base)this.mavActividadesList.get(i);
			if(StringUtils.isBlank(data.getDescripcion())){
				data.setDescripcion(" ");
			}
		}
		
		//combo estados 
		this.mavEstadosList = service.getEstados();
		
		//cargar el periodo de proceso
		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
        criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente  
        
		MantenimientoOCRPedidoControlFacturacionService serviceAux = 
				 (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = serviceAux.getControlFacturacionById(criteria);
		f.setCodigoPeriodoBusq(controlFacturacion.getCodigoPeriodo());
		f.setActividadBusq("");
		f.setTipoOfertaBusq("");
		f.setCodigoProductoBusq("");
		f.setDescripcionProductoBusq("");
		f.setEstadoBusq("");
		f.setCodigoTipoCliente(null);
		f.setIndicadorOrigen(null);
		
		//Lista Tipo Cliente
		List listaTipoCliente = new ArrayList();
		Base base = new Base();
		base.setCodigo("02");
		String proceso = this.getResourceMessage("mantenimientoMAVConfiguracionSearchForm.tipoClienteConsultora");
		base.setDescripcion(proceso);
		listaTipoCliente.add(base);
		base = new Base();
		base.setCodigo("04");
		proceso = this.getResourceMessage("mantenimientoMAVConfiguracionSearchForm.tipoClienteGerente");
		base.setDescripcion(proceso);
		listaTipoCliente.add(base);
		this.siccMAVTipoClienteList = listaTipoCliente;
		
		//Lista Origen
		List listaOrigen = new ArrayList();
		base = new Base();
		base.setCodigo("D");
		proceso = this.getResourceMessage("mantenimientoMAVConfiguracionSearchForm.origenDigitacion");
		base.setDescripcion(proceso);
		listaOrigen.add(base);
		base = new Base();
		base.setCodigo("C");
		proceso = this.getResourceMessage("mantenimientoMAVConfiguracionSearchForm.origenCargaMasiva");
		base.setDescripcion(proceso);
		listaOrigen.add(base);
		this.siccMAVOrigenList = listaOrigen;
		
		this.mavConfiguracionesList = new ArrayList();
		
		this.mostrarBotonModificar = false;
		this.mostrarBotonEliminar = false;
		this.salirGrabarPantallaPadre = true;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.web.framework.action.BaseReporteAbstractAction#getReporteFileName()
	 */
	protected String getReporteFileName() {
		return "reporteMaestroHorizontal";
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.web.framework.action.BaseSubReporteAbstractAction#getSubReporteFileName()
	 */
	protected String getSubReporteFileName() {		
		return "reporteMAVConfiguracionPDF";		
	}

	@Override
	protected List setFindAttributes() throws Exception {
		// TODO Auto-generated method stub

		MantenimientoMAVConfiguracionSearchForm f = (MantenimientoMAVConfiguracionSearchForm) this.formBusqueda;
		MantenimientoMAVConfiguracionService service = (MantenimientoMAVConfiguracionService) 
					this.getBean("spusicc.mantenimientoMAVConfiguracionService");
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		
		/* obteniendo valores */
		Map criteria = BeanUtils.describe(f);
		
		/* Obteniendo el oidTipoOferta */
		if(StringUtils.isNotEmpty(f.getTipoOfertaBusq())) {
			criteria.put("tipoOfertaBusq", f.getTipoOfertaBusq().split("__")[0]);
		}	
		
		//Actualizamos la descripcion del producto
		if(StringUtils.isEmpty(f.getCodigoProductoBusq())) {
			f.setDescripcionProductoBusq("");
		} else {
			String data = ajaxService.getDescripcionInternacionalizableProducto(f.getCodigoProductoBusq());
			
			if (StringUtils.isBlank(data))
				f.setDescripcionProductoBusq("");
			else
				f.setDescripcionProductoBusq(data);
		}
		
		/* obteniendo lista de tipos de oferta */
		LabelValue [] listTiposOferta = ajaxService.getTiposOfertaByActividad(f.getActividadBusq());
		this.mavTiposOfertaList = listTiposOferta;
		
		/* Obteniendo Lista */
		List resultado = service.getConfiguracionesByCriteria(criteria);
		this.mavConfiguracionesList = resultado;

		return resultado;
		
	}
	
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoMAVConfiguracionForm f = new MantenimientoMAVConfiguracionForm();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		limpiar(f);
		
		if(this.editableMantenimiento)
			this.mostrarBotonSave = true;
		else
			this.mostrarBotonSave = false;
		
		this.editableUnidadesIniciado = false;
		
		if (this.accion.equals(this.ACCION_NUEVO)) {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'setAddAttributes ' method");
			}
			
			Map criteria= new HashMap();
			criteria.put("codigoPais", pais.getCodigo());
	        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
	        criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente  
			
			f.setIndicadorEnviaMensaje(Constants.NO);
			f.setIndicadorOrigen("D");
			f.setIndicadorUnidad(Constants.NO);
			this.activarComboRestriccion = true;
			f.setTabSeleccion(Constants.MAV_TAB_CONSIDERACION);
			f.setNumeroUnidades("1");
			f.setPrecio("0");
			f.setIndicadorTipoSeleccionCapacitadora(Constants.INDICADOR_TIPO_SELECCION_CAPACITADORA_REGION);
			
			MantenimientoMAVConfiguracionService service = (MantenimientoMAVConfiguracionService) 
					getBean("spusicc.mantenimientoMAVConfiguracionService");
			MantenimientoINCConfiguracionConcursoService serviceInc = (MantenimientoINCConfiguracionConcursoService) 
					getBean("spusicc.mantenimientoINCConfiguracionConcursoService");
			MantenimientoRECCodigoVentaOperaService serviceREC = (MantenimientoRECCodigoVentaOperaService)  
					getBean("spusicc.mantenimientoRECCodigoVentaOperaService");
			
			//CARGAR TODAS CONSIDERACIONES
			this.mavConsideracionTodosList = service.getConsideracion(criteria);
			//CARGAR TODAS RESTRICCONES
			this.mavRestriccionTodosList = service.getRestriccion(criteria);
					
			//CARGAR CONSIDERACIONES
			this.mavConsideracionList = null;
			//CARGAR RESTRICCONES
			this.mavRestriccionList = null;
			
			//PERIODO ACTIVO
			MantenimientoOCRPedidoControlFacturacionService serviceFact = 
						(MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");        
			PedidoControlFacturacion controlFacturacion = serviceFact.getControlFacturacionById(criteria);
			String periodoActual = controlFacturacion.getCodigoPeriodo();
			this.periodoActual = periodoActual;
			
			//Cargar Unidades de Negocio, Negocios, MarcaProducto
			this.incUnidadNegocioList = serviceInc.getUnidadesNegocio();
			this.incNegocioList = serviceInc.getNegocios();
			this.incMarcaProductoList = serviceInc.getMarcaProductos();
			this.recCodigoCatalogoList = service.getCatalogos();
			this.mavCodigoTipoVentaList = service.getTiposVenta();
					
			this.clasificacionesList = null;
			this.unidadesList = null;
			this.consultoraList = null;
			this.regionesList = null;
			this.codigoEstatusList = null;
			this.clasificacionesListR = null;
			this.unidadesListR = null;
			this.consultoraListR = null;
			this.regionesListR = null;
			this.codigoEstatusListR = null;
					
			this.mavConfiguracionConsideracionList = new ArrayList();
			this.mavConfiguracionRestriccionList = new ArrayList();
			this.dataTableConsideracion = new DataTableModel(this.mavConfiguracionConsideracionList);
			this.dataTableRestriccion = new DataTableModel(this.mavConfiguracionRestriccionList);
			
			/* INI SA PER-SiCC-2013-0440 */
	        GenericoService genericoService = (GenericoService) getBean("genericoService");
			ParametroPais paramPais = new ParametroPais();
			
			paramPais.setCodigoPais(pais.getCodigo());
			paramPais.setCodigoSistema(Constants.MAV_CODIGO_SISTEMA);
			paramPais.setCodigoParametro(Constants.MAV_CODIGO_PARAMETRO_VALIDA_PRECIO);
			paramPais.setIndicadorActivo(Constants.NUMERO_UNO);
			
			List lstParametros = genericoService.getParametrosPais(paramPais);
	        
			if(lstParametros != null && lstParametros.size() > 0){
				ParametroPais pPais = (ParametroPais)lstParametros.get(0);
				String indicadorValidaPrecio = pPais.getValorParametro();
				
				if(indicadorValidaPrecio != null)
					f.setIndicadorValidaPrecio(indicadorValidaPrecio);
				else
					f.setIndicadorValidaPrecio("");
			}else {
				f.setIndicadorValidaPrecio("");
			}
			/* FIN SA PER-SiCC-2013-0440 */
			
			ParametroPais parametroPais = new ParametroPais();
			parametroPais.setCodigoPais(pais.getCodigo());
			parametroPais.setCodigoSistema(Constants.MAV_CODIGO_SISTEMA);
			parametroPais.setCodigoParametro(Constants.ITEM_INDICADOR_CAPACITADORA);
			parametroPais.setIndicadorActivo(Constants.ESTADO_ACTIVO);
			lstParametros = genericoService.getParametrosPais(parametroPais);
			f.setIndicadorCapacitadora(Constants.NO);
			
			ParametroPais parametro = null;
			if(CollectionUtils.size(lstParametros)==1){
				parametro = (ParametroPais) lstParametros.get(0);
				String indicadorCapacitadora = parametro.getValorParametro();
				f.setIndicadorCapacitadora(indicadorCapacitadora);
			}
		} else {
			Map map = (Map) this.beanRegistroSeleccionado;
			
			MantenimientoMAVConfiguracionService service = (MantenimientoMAVConfiguracionService)  
					getBean("spusicc.mantenimientoMAVConfiguracionService");
			MantenimientoINCConfiguracionConcursoService serviceInc = (MantenimientoINCConfiguracionConcursoService) 
					getBean("spusicc.mantenimientoINCConfiguracionConcursoService");
			MantenimientoRECCodigoVentaOperaService serviceREC = (MantenimientoRECCodigoVentaOperaService)  
					getBean("spusicc.mantenimientoRECCodigoVentaOperaService");
			
			Map criteria= new HashMap();
			criteria.put("codigoPais", pais.getCodigo());
			criteria.put("correlativo", map.get("correlativo").toString());
			Map mapConfiguracion = service.getConfiguracion(criteria);
			BeanUtils.copyProperties(f, mapConfiguracion);
			
			/* obteniendo lista de tipos de oferta */
			AjaxService ajaxService = (AjaxService) getBean("ajaxService");
			LabelValue [] listTiposOferta = ajaxService.getTiposOfertaByActividad(f.getOidActividad());
			this.mavTiposOfertaMantList = listTiposOferta;
			
			//actualizando el oid del tipo de oferta
			f.setOidActividadTipoOferta(f.getOidActividadTipoOferta() + "__" + f.getCodigoTipoOferta());
			f.setTabSeleccion(Constants.MAV_TAB_CONSIDERACION);
			
			//Cargar Unidades de Negocio, Negocios, MarcaProducto
			this.incUnidadNegocioList = serviceInc.getUnidadesNegocio();
			this.incNegocioList = serviceInc.getNegocios();
			this.incMarcaProductoList = serviceInc.getMarcaProductos();
			this.recCodigoCatalogoList = service.getCatalogos();
			this.mavCodigoTipoVentaList = service.getTiposVenta();
			
			this.clasificacionesList = null;
			this.unidadesList = null;
			this.consultoraList = null;
			this.regionesList = null;
			this.codigoEstatusList = null;
			this.clasificacionesListR = null;
			this.unidadesListR = null;
			this.consultoraListR = null;
			this.regionesListR = null;
			this.codigoEstatusListR = null;
			
			String indicadorDirigido = "G";
			if(f.getCodigoTipoCliente().equals(Constants.MAE_TIPO_CLIENTE_CONSULTORA)) {
				indicadorDirigido = "C";
			}
			
			//CARGAR TODAS CONSIDERACIONES
			this.mavConsideracionTodosList = service.getConsideracion(criteria);
			//CARGAR TODAS RESTRICCONES
			this.mavRestriccionTodosList = service.getRestriccion(criteria);
			
			String indicadorUnidad = "S".equals(f.getIndicadorUnidad())?"S":"";
			this.activarComboRestriccion = true;
			if("S".equals(indicadorUnidad)) {
				this.mostrarCampoUnidades = false;
				this.activarComboRestriccion = false;
			}
			 
			//CARGAR CONSIDERACIONES
			this.mavConsideracionList = ajaxService.getConsideraciones(indicadorDirigido, indicadorUnidad);
			//CARGAR RESTRICCONES
			this.mavRestriccionList = ajaxService.getRestricciones(indicadorDirigido, indicadorUnidad);
					
			f.setTabSeleccion(Constants.MAV_TAB_CONSIDERACION);
			
			//cargando la lista de consideraciones y restricciones
			criteria.put("correlativoMAV", f.getCorrelativo() );
			criteria.put("tipoConsideracion", Constants.MAV_TIPO_CONSIDERACION );
			List listConsi = service.getRestConsideracion(criteria);
			for(int i=0; i<listConsi.size(); i++) {
				Map mapConsi = (Map)listConsi.get(i);
				
				String indicadorTipo = (String)mapConsi.get("indicadorTipo");
				String codigoConsideracion = mapConsi.get("codigoConsideracion").toString();
				String condicionAux = null;
				String descripcionAux = "";
				
				String condicionFormula = null;
				
				String condicionAuxTipoVenta = null;
				String descripcionAuxTipoVenta ="";
				if(Constants.MAV_TIPO_CONDICIONES_MIXTAS.equals(indicadorTipo)){
					if((Constants.MAV_CONRES_POR_FORMULA == Integer.parseInt(codigoConsideracion)) || 
							(Constants.MAV_CONRES_POR_VARIABLES_VENTA == Integer.parseInt(codigoConsideracion))){
						condicionFormula = mapConsi.get("condicion1").toString();
						descripcionAux = getDesFormula(condicionFormula);
					}
					if((Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_MARCA == Integer.parseInt(codigoConsideracion)) || 
							(Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_MARCA == Integer.parseInt(codigoConsideracion))){
						condicionAux = mapConsi.get("condicion2").toString();
						descripcionAux = getDesMarcaProducto(condicionAux);
						condicionAuxTipoVenta = mapConsi.get("condicion5").toString();
						descripcionAuxTipoVenta = this.getDesTipoVenta(condicionAuxTipoVenta);
					}
					if((Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_NEGOCIO == Integer.parseInt(codigoConsideracion)) || 
							(Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_NEGOCIO == Integer.parseInt(codigoConsideracion))){
						condicionAux = mapConsi.get("condicion2").toString();
						descripcionAux = getDesNegocio(condicionAux);
						condicionAuxTipoVenta = mapConsi.get("condicion5").toString();
						descripcionAuxTipoVenta = this.getDesTipoVenta(condicionAuxTipoVenta);
					
					}
					
					if((Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_UNIDAD == Integer.parseInt(codigoConsideracion)) || 
							(Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_UNIDAD == Integer.parseInt(codigoConsideracion))){
						condicionAux = mapConsi.get("condicion2").toString();
						descripcionAux = getDesUnidadNegocio(condicionAux);
						condicionAuxTipoVenta = mapConsi.get("condicion5").toString();
						descripcionAuxTipoVenta = this.getDesTipoVenta(condicionAuxTipoVenta);
					}
					
					if((Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_CATALOGO == Integer.parseInt(codigoConsideracion)) || 
							(Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_CATALOGO == Integer.parseInt(codigoConsideracion))){
						condicionAux = mapConsi.get("condicion2").toString();
						descripcionAux = getDesCatalogo(condicionAux);
						condicionAuxTipoVenta = mapConsi.get("condicion5").toString();
						descripcionAuxTipoVenta = this.getDesTipoVenta(condicionAuxTipoVenta);
					}
					if((Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO == Integer.parseInt(codigoConsideracion)) || 
							(Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO == Integer.parseInt(codigoConsideracion))){
						condicionAuxTipoVenta = mapConsi.get("condicion4").toString();
						descripcionAuxTipoVenta = this.getDesTipoVenta(condicionAuxTipoVenta);
					}
					
					if(condicionFormula != null) {
						mapConsi.put("condicion",descripcionAux + " , "+ mapConsi.get("condicion2").toString());
						
					}else if(condicionAux != null) {
						mapConsi.put("condicion",mapConsi.get("condicion1").toString() + " , "+ 
												descripcionAux + " , "+
												mapConsi.get("condicion3").toString() + " , "+
												mapConsi.get("condicion4").toString() + " , "+ descripcionAuxTipoVenta);
					}
					else {
						if(mapConsi.get("condicion3")!=null)
							if(StringUtils.isNotEmpty(descripcionAuxTipoVenta))
								mapConsi.put("condicion",mapConsi.get("condicion1").toString() + " , "+ 
									mapConsi.get("condicion2").toString() + " , "+
									mapConsi.get("condicion3").toString() + " , "+ descripcionAuxTipoVenta);
							else
								mapConsi.put("condicion",mapConsi.get("condicion1").toString() + " , "+ 
										mapConsi.get("condicion2").toString() + " , "+
										mapConsi.get("condicion3").toString());
						else
							mapConsi.put("condicion",mapConsi.get("condicion1").toString() + " , "+ 
									mapConsi.get("condicion2").toString() + " , "+ descripcionAuxTipoVenta);
					}
				}
			}
			this.mavConfiguracionConsideracionList = listConsi;
			this.dataTableConsideracion = new DataTableModel(listConsi);
			
			criteria.put("tipoConsideracion", Constants.MAV_TIPO_RESTRICCION );
			List listRest = service.getRestConsideracion(criteria);
			for(int i=0; i<listRest.size(); i++) {
				Map mapRest = (Map)listRest.get(i);
				
				String indicadorTipo = mapRest.get("indicadorTipo").toString();
				String codigoRestriccion = mapRest.get("codigoRestriccion").toString();
				String condicionAux = null;
				String descripcionAux = "";
				String condicionAuxTipoVenta = null;
				String descripcionAuxTipoVenta ="";
				
				if(Constants.MAV_TIPO_CONDICIONES_MIXTAS.equals(indicadorTipo)){
					
					if((Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_MARCA_REST == Integer.parseInt(codigoRestriccion)) || 
							(Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_MARCA_REST == Integer.parseInt(codigoRestriccion))){
						condicionAux = mapRest.get("condicion2").toString();
						descripcionAux = getDesMarcaProducto(condicionAux);
						condicionAuxTipoVenta = mapRest.get("condicion5").toString();
						descripcionAuxTipoVenta = this.getDesTipoVenta(condicionAuxTipoVenta);
					}
					if((Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_NEGOCIO_REST == Integer.parseInt(codigoRestriccion)) || 
							(Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_NEGOCIO_REST == Integer.parseInt(codigoRestriccion))){
						condicionAux = mapRest.get("condicion2").toString();
						descripcionAux = getDesNegocio(condicionAux);
						condicionAuxTipoVenta = mapRest.get("condicion5").toString();
						descripcionAuxTipoVenta = this.getDesTipoVenta(condicionAuxTipoVenta);
					}
					
					if((Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_UNIDAD_REST == Integer.parseInt(codigoRestriccion)) || 
							(Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_UNIDAD_REST == Integer.parseInt(codigoRestriccion))){
						condicionAux = mapRest.get("condicion2").toString();
						descripcionAux = getDesUnidadNegocio(condicionAux);
						condicionAuxTipoVenta = mapRest.get("condicion5").toString();
						descripcionAuxTipoVenta = this.getDesTipoVenta(condicionAuxTipoVenta);
					}
					
					if((Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_CATALOGO_REST == Integer.parseInt(codigoRestriccion)) || 
							(Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_CATALOGO_REST == Integer.parseInt(codigoRestriccion))){
						condicionAux = mapRest.get("condicion2").toString();
						descripcionAux = getDesCatalogo(condicionAux);
						condicionAuxTipoVenta = mapRest.get("condicion5").toString();
						descripcionAuxTipoVenta = this.getDesTipoVenta(condicionAuxTipoVenta);
					}
					
					if((Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_REST == Integer.parseInt(codigoRestriccion)) || 
							(Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_REST == Integer.parseInt(codigoRestriccion))){
						condicionAuxTipoVenta = mapRest.get("condicion4").toString();
						descripcionAuxTipoVenta = this.getDesTipoVenta(condicionAuxTipoVenta);
					}
					if(condicionAux != null) {
						mapRest.put("condicion",mapRest.get("condicion1").toString() + " , "+ 
												descripcionAux + " , "+
												mapRest.get("condicion3").toString() + " , "+
												mapRest.get("condicion4").toString() + " , "+ descripcionAuxTipoVenta);
					}
					else  {
						mapRest.put("condicion",mapRest.get("condicion1").toString() + " , "+ 
								mapRest.get("condicion2").toString() + " , "+
								mapRest.get("condicion3").toString() + " , "+ descripcionAuxTipoVenta);
					}
				}
			}
			this.mavConfiguracionRestriccionList = listRest;
			this.dataTableRestriccion = new DataTableModel(listRest);
			
			/* INI SA PER-SiCC-2013-0440 */
	        GenericoService genericoService = (GenericoService) getBean("genericoService");
			ParametroPais paramPais = new ParametroPais();
			
			paramPais.setCodigoPais(pais.getCodigo());
			paramPais.setCodigoSistema(Constants.MAV_CODIGO_SISTEMA);
			paramPais.setCodigoParametro(Constants.MAV_CODIGO_PARAMETRO_VALIDA_PRECIO);
			paramPais.setIndicadorActivo(Constants.NUMERO_UNO);
			
			List lstParametros = genericoService.getParametrosPais(paramPais);
	        
			if(lstParametros != null && lstParametros.size() > 0){
				ParametroPais pPais = (ParametroPais)lstParametros.get(0);
				String indicadorValidaPrecio = pPais.getValorParametro();
				
				if(indicadorValidaPrecio != null)
					f.setIndicadorValidaPrecio(indicadorValidaPrecio);
				else
					f.setIndicadorValidaPrecio("");
			}else {
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
			if(CollectionUtils.size(lstParametros)==1){
				parametro = (ParametroPais) lstParametros.get(0);
				String indicadorCapacitadora = parametro.getValorParametro();
				f.setIndicadorCapacitadora(indicadorCapacitadora);
			}
			
			actualizarListTiposOferta(f);
			
		}
		
		return f;
	}
	

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return "mantenimientoMAVConfiguracionForm";
	}
	
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoMAVConfiguracionSearchForm form = new MantenimientoMAVConfiguracionSearchForm();
		return form;
	}
	
	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return "mantenimientoMAVConfiguracionList";
	}
	
	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void eliminar(ActionEvent event) {
		Map data = (Map) this.beanRegistroSeleccionado;
		
		if (data != null) {
			try {							
				Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
				MantenimientoMAVConfiguracionService service = (MantenimientoMAVConfiguracionService) 
						this.getBean("spusicc.mantenimientoMAVConfiguracionService");
								
				data.put("codigoUsuario", usuario.getLogin());
				
				service.deleteConfiguracion(data);
				
				//enviamos el mensaje de satisfaccion
				this.addInfo("",
						this.getResourceMessage("mantenimientoMAVConfiguracionSearchForm.deleted"));
				
				this.find(event);
				
			}catch (Exception e) {
				String error = e.getMessage();
				if (StringUtils.isBlank(error)) error = e.getLocalizedMessage();
				
				addError("Error",
						this.getResourceMessage("errors.detail", new Object[] {error}));	
			}
		}
		
		return;
	}
	
	public void loadTiposOferta(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadTiposOferta");
		}
		MantenimientoMAVConfiguracionSearchForm f = (MantenimientoMAVConfiguracionSearchForm)this.formBusqueda;
		String actividad = (String) val.getNewValue();
		if (StringUtils.isNotBlank(actividad)) {
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			this.setMavTiposOfertaList(ajax.getTiposOfertaByActividad( actividad));	 	
		} else {
			this.setMavTiposOfertaList(null);
		}
	}
	
	public void linkEditar(ActionEvent event) {
		try {
			if (!this.verificarRegistroSeleccionado())
				return;
			
			Map data = (Map) this.beanRegistroSeleccionado;
			String codigoEstado = MapUtils.getString(data, "codigoEstado");
			String codigoTipoCliente = MapUtils.getString(data, "codigoTipoCliente");
			String indicadorUniDif = MapUtils.getString(data, "indicadorUniDif");
			
			//Si es Pendiente o Desactivado, no se puede eliminar
			if(!(codigoEstado.equals("2") || codigoEstado.equals("4") || (codigoEstado.equals("3") && "N".equals(indicadorUniDif))) ) {
				mostrarVentanaAlerta("mantenimientoMAVConfiguracionSearchForm.modificar.validar");
				return;
			}
			
			if(codigoEstado.equals("2"))
				edit();
			else {
				if(codigoEstado.equals("3"))
					editIniciado(event);
				else
					if(codigoTipoCliente.equals("4")) { //GERENTE
						this.editUnidadesListaRegionZona(event);
						this.redireccionarPagina("mantenimientoMAVConfiguracionEditUnidadesListaRegionZona");
					} else {
						this.editUnidadesConsultoras(event);
						this.redireccionarPagina("mantenimientoMAVConfiguracionEditUnidadesConsultoras");
					}
			}
			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
			
		}
	}
	
	public void editIniciado(ActionEvent event) {
		consultar(event);
		this.mostrarBotonSave = true;
		this.editableUnidadesIniciado = true;
	}
	
	public String setValidarConfirmar(String accion) {
		Map data = (Map) this.beanRegistroSeleccionado;
		
		// ############### VALIDAR CAMPOS REQUERIDOS #######################
		if (datatableBusqueda == null)
			return this.getResourceMessage("errors.sin.registros");
		if (beanRegistroSeleccionado == null)
			return this.getResourceMessage("errors.select.item");
		
		if (accion.equals("DESACTIVAR")) {
			String estado = MapUtils.getString(data, "codigoEstado");
			
			//Si es Pendiente o Desactivado, no se puede eliminar
			if("1".equals(estado) || "5".equals(estado)) {
				return "Si es Pendiente o Desactivado, no se puede eliminar";
			}
		}

		return null;
	}
	
	public void linkGenerarEnvios(ActionEvent event) {
		try {
			this.procesoMAVGenerarEnviosAction.setPaginaPadre("mantenimientoMAVConfiguracionList");
			this.procesoMAVGenerarEnviosAction.view();
			this.redireccionarPagina("procesoMAVGenerarEnviosForm");
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
			
		}
	}
	
	public void linkCargaMasiva(ActionEvent event) {
		try {
			this.procesoMAVCargaMasivaAction.setPaginaPadre("mantenimientoMAVConfiguracionList");
			this.procesoMAVCargaMasivaAction.view();
			this.redireccionarPagina("procesoMAVCargaMasivaForm");
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
			
		}
	}
	
	public void generarReporte(ActionEvent event) throws Exception {
		Map paramsConfiguracion = (Map) this.beanRegistroSeleccionado;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		if (datatableBusqueda == null)
			this.getRequestContext().execute(
					"PF('dialogSinRegistros_alertDialog').show()");
		else if (beanRegistroSeleccionado == null)
			this.getRequestContext().execute(
					"PF('dialogSinItem_alertDialog').show()");
		else {
			ReporteMAVConfiguracionForm report = new ReporteMAVConfiguracionForm();
			report.setCodigoPais(pais.getCodigo());
			report.setPais(pais);
			report.setUsuario(usuario);
			report.setCorrelativo(paramsConfiguracion.get("correlativo").toString());
			report.setCodigoPeriodoBusq(paramsConfiguracion.get("periodoProceso").toString());

			reporteConfiguracion.setFormatoReporte("PDF");
			reporteConfiguracion.setFormatoExportacion("PDF");
			reporteConfiguracion.setFormReporte(report);
			reporteConfiguracion.getFormReporte().setFormatoExportacion("PDF");
			reporteConfiguracion.executeReporte();

			this.redireccionarPagina("reporteMAVConfiguracionForm");
		}
	}
	
	public void salirReporte(ActionEvent event) throws IOException {
		this.redireccionarPagina("mantenimientoMAVConfiguracionList");
	}
	
	private void limpiar(MantenimientoMAVConfiguracionForm f) {
		f.setCodigoPais(null);
		f.setCorrelativo(null);
		f.setOidActividad(null);
		f.setOidActividadTipoOferta(null);
		f.setOidProducto(null);
		f.setCodigoPeriodo(null);
		f.setCodigoActividad(null);
		f.setCodigoTipoOferta(null);
		f.setCodigoProducto(null);
		f.setDescripcionProducto(null);
		f.setOidTipoCliente(null);
		f.setCodigoTipoCliente(null);
		f.setDescripcionTipoCliente(null);
		f.setCodigoEstado(null);
		f.setIndicadorOrigen(null);
		f.setIndicadorEnviaMensaje(null);
		f.setFormaCobro(null);
		f.setDescripcionFormaCobro(null);
		f.setFormaPago(null);
		f.setDescripcionFormaPago(null);
		f.setTipoDespacho(null);
		f.setDescripcionTipoDespacho(null);
		f.setEnvioSolicitud(null);
		f.setDescripcionEnvioSolicitud(null);
		f.setNumeroUnidades(null);
		f.setPrecio(null);
		f.setIndicadorActivo(null);
		f.setIndicadorUnidad(null);
		
		f.setCodigoConsideracion(null);
		f.setCodigoRestriccion(null);
		f.setCondicion1("");
		f.setCondicion2("");
		f.setCondicionRest1("");
		f.setCondicionRest2("");
		f.setCondicion1S("");
		f.setCondicionRest1S("");
		
		f.setCondicionMonto(null);
		f.setCondicionMonto2(null);
		f.setCondicionPeriodoInicio(null);
		f.setCondicionPeriodoFin(null);
		f.setCondicionUnidades(null);
		f.setCondicionMarca(null);
		f.setCondicionPeriodoFinanciado(null);
		f.setCondicionNegocio(null);
		f.setCondicionUnidadNegocio(null);
		f.setCondicionCatalogo(null);
		f.setCondicionRestMonto(null);
		f.setCondicionRestPeriodoInicio(null);
		f.setCondicionRestPeriodoFin(null);
		f.setCondicionRestUnidades(null);
		f.setCondicionRestMarca(null);
		f.setCondicionRestNegocio(null);
		f.setCondicionRestUnidadNegocio(null);
		f.setCondicionRestCatalogo(null);
		f.setCondicionTipoVenta(null);
		f.setCondicionRestTipoVenta(null);
		
		f.setIndicadorConsideracion(Constants.NRO_UNO);
		f.setIndicadorRestriccion(Constants.NRO_UNO);
		
		f.setConsideracionPedidoSuperaMonto(String.valueOf(Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO));
		f.setConsideracionPedidoSuperaMontoMarca(String.valueOf(Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_MARCA));
		f.setConsideracionPedidoSuperaMontoUnidad(String.valueOf(Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_UNIDAD));
		f.setConsideracionPedidoSuperaMontoNegocio(String.valueOf(Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_NEGOCIO));
		f.setConsideracionPedidoSuperaMontoCatalogo(String.valueOf(Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_CATALOGO));
		f.setConsideracionPedidoNoSuperaMonto(String.valueOf(Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO));
		f.setConsideracionPedidoNoSuperaMontoMarca(String.valueOf(Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_MARCA));
		f.setConsideracionPedidoNoSuperaMontoUnidad(String.valueOf(Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_UNIDAD));
		f.setConsideracionPedidoNoSuperaMontoNegocio(String.valueOf(Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_NEGOCIO));
		f.setConsideracionPedidoNoSuperaMontoCatalogo(String.valueOf(Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_CATALOGO));
		f.setConsideracionInscripcionNuevaDupla(String.valueOf(Constants.MAV_CONRES_INSCRIPCION_NUEVA_DUPLA));
		f.setConsideracionMontoFlexiPago(String.valueOf(Constants.MAV_CONRES_MONTO_FLEXIPAGO));
		f.setConsideracionFormula(String.valueOf(Constants.MAV_CONRES_POR_FORMULA));
		f.setConsideracionVariablesVenta(String.valueOf(Constants.MAV_CONRES_POR_VARIABLES_VENTA));
		
		f.setRestriccionPedidoSuperaMonto(String.valueOf(Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_REST));
		f.setRestriccionPedidoSuperaMontoMarca(String.valueOf(Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_MARCA_REST));
		f.setRestriccionPedidoSuperaMontoUnidad(String.valueOf(Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_UNIDAD_REST));
		f.setRestriccionPedidoSuperaMontoNegocio(String.valueOf(Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_NEGOCIO_REST));
		f.setRestriccionPedidoSuperaMontoCatalogo(String.valueOf(Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_CATALOGO_REST));
		f.setRestriccionPedidoNoSuperaMonto(String.valueOf(Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_REST));
		f.setRestriccionPedidoNoSuperaMontoMarca(String.valueOf(Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_MARCA_REST));
		f.setRestriccionPedidoNoSuperaMontoUnidad(String.valueOf(Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_UNIDAD_REST));
		f.setRestriccionPedidoNoSuperaMontoNegocio(String.valueOf(Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_NEGOCIO_REST));
		f.setRestriccionPedidoNoSuperaMontoCatalogo(String.valueOf(Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_CATALOGO_REST));
		f.setRestriccionInscripcionNuevaDupla(String.valueOf(Constants.MAV_CONRES_INSCRIPCION_NUEVA_DUPLA_REST));
		
		f.setOidTipoClienteAux("");
		f.setOidSubTipoCliente("");
		f.setOidTipoClasificacion("");
		f.setOidClasificacion("");	
		f.setDescripcionRegionList("");
		f.setDescripcionZonaList("");
		f.setDescripcionSeccionList("");
		f.setDescripcionTerritorioList("");
		f.setRegionList("");
		f.setZonaList("");
		f.setSeccionList("");
		f.setTerritorioList("");
		f.setNumeroUnidadesPopup("");
		f.setOidTipoClienteAux2("");
		f.setCodigoCliente(null);
		f.setNombreCliente(null);
		f.setOidPais(null);
		f.setSelectedItemsPopup(null);
		f.setCondicionDobleUnidades("");
		f.setCondicionUnicoUnidades("");
		f.setCondicionSimpleUnidades("");
		f.setCondicionDobleRestUnidades("");
		f.setCondicionUnicoRestUnidades("");
		f.setCondicionSimpleRestUnidades("");
		f.setIndicadorUnidadPopup("");
		f.setCodigoVenta("");
		f.setUnidadesEstimadas("");
		
		/* INI SA PER-SiCC-2013-0432 */
		f.setDescripcionAlmacen("");
		/* FIN SA PER-SiCC-2013-0432 */
		
		/* INI SA PER-SiCC-2013-0440 */
		f.setObservaciones("");
		f.setEstadoList(null);
		f.setDescripcionEstadoList("");
		f.setRegionListMultiple(null);
		f.setZonaListMultiple(null);
		f.setSeccionListMultiple(null);
		f.setTerritorioListMultiple(null);
		f.setTotalUnidadesRegion(null);
		f.setTotalUnidadesZona("");
		/* FIN SA PER-SiCC-2013-0440 */
		
		f.setIndicadorTipoSeleccionCapacitadora(Constants.INDICADOR_TIPO_SELECCION_CAPACITADORA_REGION);
		f.setCondicionFormula(null);
		f.setCondicionNroPeriodosFV(null);
		f.setExisteListaRegionZona(false);
		f.setExisteConsideracionFormula(false);
		
		this.mostrarCampoUnidades = true;
	}
	
	/**
	 * Actualizar lista de Tipos de Oferta
	 * 
	 * @param session
	 * @param f
	 */
	private void actualizarListTiposOferta(MantenimientoMAVConfiguracionForm f) {
		/* obteniendo lista de tipos de oferta */
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		LabelValue [] listTiposOferta = ajaxService.getTiposOfertaByActividad(f.getOidActividad());
		this.mavTiposOfertaMantList = listTiposOferta;
		
		String indicadorDirigido = "G";
		if(f.getCodigoTipoCliente().equals(Constants.MAE_TIPO_CLIENTE_CONSULTORA)) {
			indicadorDirigido = "C";
		}
		
		String indicadorUnidad = "S".equals(f.getIndicadorUnidad())?"S":"";
		
		//CARGAR CONSIDERACIONES
		this.mavConsideracionList = ajaxService.getConsideraciones(indicadorDirigido, indicadorUnidad);
		//CARGAR RESTRICCONES
		this.mavRestriccionList = ajaxService.getRestricciones(indicadorDirigido, indicadorUnidad);
		
		f.setExisteListaRegionZona(false);
		if("G".equals(indicadorDirigido)) {
			List list = this.mavConfiguracionConsideracionList;
			for(int i=0; i<list.size(); i++) {
				Map aux = (Map)list.get(i);
				String indicadorAccion = (String)aux.get("indicadorAccion");
				String codigoConsideracion = (String)aux.get("codigoConsideracion");
				
				if(!Constants.NUMERO_DOS.equals(indicadorAccion) && 
						Constants.MAV_CONRES_LISTA_REGION_ZONA == Integer.parseInt(codigoConsideracion)){
					f.setExisteListaRegionZona(true);
				}
			}
		} 
		
		f.setExisteConsideracionFormula(false);
		if("G".equals(indicadorDirigido)) {
			List list = this.mavConfiguracionConsideracionList;
			for(int i=0; i<list.size(); i++) {
				Map aux = (Map)list.get(i);
				String indicadorAccion = (String)aux.get("indicadorAccion");
				String codigoConsideracion = (String)aux.get("codigoConsideracion");
				
				if(!Constants.NUMERO_DOS.equals(indicadorAccion) && 
						Constants.MAV_CONRES_POR_FORMULA == Integer.parseInt(codigoConsideracion)){
					f.setExisteConsideracionFormula(true);
				}
				if(!Constants.NUMERO_DOS.equals(indicadorAccion) && 
						Constants.MAV_CONRES_POR_VARIABLES_VENTA == Integer.parseInt(codigoConsideracion)){
					f.setExisteConsideracionFormula(true);
				}
			}
		}
	}	
	
	public void loadTiposOfertaMant(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadTiposOfertaMant");
		}
		MantenimientoMAVConfiguracionForm f = (MantenimientoMAVConfiguracionForm)this.formMantenimiento;
		String actividad = (String) val.getNewValue();
		if (StringUtils.isNotBlank(actividad)) {
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			this.setMavTiposOfertaMantList(ajax.getTiposOfertaByActividad(actividad));	 	
		} else {
			this.setMavTiposOfertaMantList(null);
		}
		
		cargarDatosActividad(actividad);
	}
	
	private void cargarDatosActividad(String actividad) {
		MantenimientoMAVConfiguracionForm f = (MantenimientoMAVConfiguracionForm)this.formMantenimiento;
		
		if (StringUtils.isNotBlank(actividad)) {
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			String datosActividad = ajax.getDatosActividad(actividad);	
			
			if (StringUtils.isNotBlank(actividad)) {
				String[] elementos = datosActividad.split("__");
				f.setOidTipoCliente(elementos[1]);
				f.setCodigoTipoCliente(elementos[2]);
				f.setDescripcionTipoCliente(elementos[3]);
				f.setFormaPago(elementos[4]);
				f.setDescripcionFormaPago(elementos[5]);
				f.setFormaCobro(elementos[6]);
				f.setDescripcionFormaCobro(elementos[7]);
				f.setTipoDespacho(elementos[8]);
				f.setDescripcionTipoDespacho(elementos[10]);
				if(elementos.length > 11)
					f.setEnvioSolicitud(elementos[11]);
				if(elementos.length > 12)
					f.setDescripcionEnvioSolicitud(elementos[12]);
				
				if(elementos.length > 13)
					f.setDescripcionAlmacen(elementos[13]);
				
				loadConsideraciones(actividad, elementos[2]);
				loadRestricciones(actividad, elementos[2]);
			}
			else {
				
				f.setOidTipoCliente("");
				f.setCodigoTipoCliente("");
				f.setDescripcionTipoCliente("");
				f.setFormaPago("");
				f.setDescripcionFormaPago("");
				f.setFormaCobro("");
				f.setDescripcionFormaCobro("");
				f.setTipoDespacho("");
				f.setDescripcionTipoDespacho("");
				f.setEnvioSolicitud("");
				f.setDescripcionEnvioSolicitud("");
				f.setDescripcionAlmacen("");
				
			}
		} else {
			loadConsideraciones(actividad, "");
			loadRestricciones(actividad, "");	
		}
			

	 	// Si el tipo de cliente es GERENTE, seteamos la consideracion LISTAS REGION ZONA
		if(f.getCodigoTipoCliente().equals(Constants.MAE_TIPO_CLIENTE_GERENTE)) {
			f.setCodigoConsideracion(Constants.MAV_CONRES_LISTA_REGION_ZONA_STRING);
		}
		else {
			f.setCodigoConsideracion("");
		}
	}
	
	private void loadConsideraciones(String actividad, String tipoCliente) {
		MantenimientoMAVConfiguracionForm f = (MantenimientoMAVConfiguracionForm)this.formMantenimiento;
		String indicadorDirigido;
		
		if (StringUtils.isNotBlank(actividad)) {		
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			
			if(tipoCliente.equals(Constants.MAE_TIPO_CLIENTE_CONSULTORA)) {
				indicadorDirigido = "C";
			} else
				indicadorDirigido = "G";
			
			if(f.getIndicadorUnidad().equals(Constants.SI)) {
				this.setMavConsideracionList(ajax.getConsideraciones(indicadorDirigido, "S"));
		    }
			else
				this.setMavConsideracionList(ajax.getConsideraciones(indicadorDirigido, ""));
		}else{
			this.setMavConsideracionList(null);		
		}
	}                     
	
	private void loadRestricciones(String actividad, String tipoCliente) {
		MantenimientoMAVConfiguracionForm f = (MantenimientoMAVConfiguracionForm)this.formMantenimiento;
		String indicadorDirigido;
		
		if (StringUtils.isNotBlank(actividad)) {		
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			
			if(tipoCliente.equals(Constants.MAE_TIPO_CLIENTE_CONSULTORA)) {
				indicadorDirigido = "C";
			} else
				indicadorDirigido = "G";
			
			this.activarComboRestriccion = true;
			if(f.getIndicadorUnidad().equals(Constants.SI)) {
				this.activarComboRestriccion = false;
				this.setMavRestriccionList(ajax.getRestricciones(indicadorDirigido, "S"));
			}
			else
				this.setMavRestriccionList(ajax.getRestricciones(indicadorDirigido, ""));
		}else{
			this.setMavRestriccionList(null);		
		}
	}   
	
	public void validarUnidadesDiferenciadas() {
		if (log.isDebugEnabled()) {
			log.debug("validarUnidadesDiferenciadas");
		}
		MantenimientoMAVConfiguracionForm f = (MantenimientoMAVConfiguracionForm)this.formMantenimiento;
		String valorIndicadorUnidad = f.getIndicadorUnidad();
		String codigoTipoCliente = f.getCodigoTipoCliente();

		//VALIDAMOS QUE NO HAYA INGRESADO UNA CONSIDERACION O RESTRICCION
		int sizeConsideracionList = obtenerTotalConsideracionesIngresadas(); 
		int sizeRestriccionList = obtenerTotalRestriccionesIngresadas(); 

		if((sizeConsideracionList >= 1) || (sizeRestriccionList >= 1)) {
			this.activarComboRestriccion = true;
			if(valorIndicadorUnidad.equals("S"))  {
				this.activarComboRestriccion = false;
				f.setIndicadorUnidad("N");
			}
			else {
				f.setIndicadorUnidad("S");
			}
			
			String lsMensajeError = this.getResourceMessage("mantenimientoMAVConfiguracionForm.existe.consideracion");
			this.setMensajeAlertaDefault(lsMensajeError);
			String ventana = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventana);
			return;
			
		} else {
			loadConsideraciones(f.getOidActividad(), codigoTipoCliente);
			loadRestricciones(f.getOidActividad(), codigoTipoCliente);

			if(f.getIndicadorUnidad().equals("N")) {
				this.mostrarCampoUnidades = true;
			} else {
				this.mostrarCampoUnidades = false;
			}
			
		}
		
		if(f.getIndicadorUnidad().equals("S")) {
			this.setHabilitarRestricciones(false);
			//consideracionTab.click();
		} else {
			this.setHabilitarRestricciones(true);
		}

	 	// Si el tipo de cliente es GERENTE, seteamos la consideracion LISTAS REGION ZONA
	 	if(codigoTipoCliente.equals(Constants.MAE_TIPO_CLIENTE_GERENTE)) {
	 		f.setCodigoConsideracion(Constants.MAV_CONRES_LISTA_REGION_ZONA_STRING);
		}
		else
		{
			f.setCodigoConsideracion("");
		}
	}
	
	public void jsCondicion(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("jsCondicion");
		}

		MantenimientoMAVConfiguracionForm f = (MantenimientoMAVConfiguracionForm)this.formMantenimiento;
		String codigoConsideracion = (String) val.getNewValue();
	   
		jsCondicionAuxiliar(f, codigoConsideracion);
	}
	
	public void jsCondicionAuxiliar(MantenimientoMAVConfiguracionForm f, String codigoConsideracion) {
		if (log.isDebugEnabled()) {
			log.debug("jsCondicionAuxiliar");
		}

		if(StringUtils.isNotEmpty(codigoConsideracion)) {
			for(int i=0; i<this.mavConsideracionTodosList.size(); i++) {
				Map mapConsideracion = (Map)this.mavConsideracionTodosList.get(i);
				String codigo = mapConsideracion.get("codigoConRes").toString();
				String indicadorTipo = MapUtils.getString(mapConsideracion, "indTipo", "");
				
				if (codigo.equals(codigoConsideracion)) {
					
					if("S".equals(indicadorTipo) || StringUtils.isEmpty(indicadorTipo)){
						limpiarCamposConsideracion(f);
						
						if(f.getIndicadorUnidad().equals("S"))
							mostrarConsideracionSimple = true;
						else	
							mostrarConsideracionSimple = false;				
						
						mostrarConsideracionUnico = false;
						mostrarConsideracionDoble = false;
						mostrarConsideracionMixto = false;
	
					}		
					if("U".equals(indicadorTipo)) {
						limpiarCamposConsideracion(f);
	
						if(f.getIndicadorUnidad().equals("S")){
							mostrarThUnicoUnidades = true;
							mostrarTdUnicoUnidades = true;
						} else {	
							mostrarThUnicoUnidades = false;
							mostrarTdUnicoUnidades = false;
						}	
	
						mostrarConsideracionSimple = false;
						mostrarConsideracionUnico = true;
						mostrarConsideracionDoble = false;
						mostrarConsideracionMixto = false;
						
					}
					if("D".equals(indicadorTipo)) {
						limpiarCamposConsideracion(f);
						
						if(f.getIndicadorUnidad().equals("S")){
							mostrarThDobleUnidades = true;
							mostrarTdDobleUnidades = true;
						} else {
							mostrarThDobleUnidades = false;
							mostrarTdDobleUnidades = false;
						}
						
						mostrarConsideracionSimple = false;
						mostrarConsideracionUnico = false;
						mostrarConsideracionDoble = true;
						mostrarConsideracionMixto = false;
						
					}
					if("M".equals(indicadorTipo)) {
						limpiarCamposConsideracion(f);
						
						if(f.getIndicadorUnidad().equals("S")){
							mostrarThMixtoUnidades = true;
							mostrarTdMixtoUnidades = true;
						} else {	
							mostrarThMixtoUnidades = false;
							mostrarTdMixtoUnidades = false;
						}
						
						mostrarConsideracionSimple = false;
						mostrarConsideracionUnico = false;
						mostrarConsideracionDoble = false;
						mostrarConsideracionMixto = true;
						
						jsConsideracionMixto(f, codigoConsideracion);
					}
					if("L".equals(indicadorTipo) || "E".equals(indicadorTipo)) {
						limpiarCamposConsideracion(f);
						
						mostrarConsideracionSimple = false;
						mostrarConsideracionUnico = false;
						mostrarConsideracionDoble = false;
						mostrarConsideracionMixto = false;
						
					}
									 
				}  
			}	
		}
	}
	
	public void jsRestriccion(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("jsRestriccion");
		}

		MantenimientoMAVConfiguracionForm f = (MantenimientoMAVConfiguracionForm)this.formMantenimiento;
		String codigoRestriccion = (String) val.getNewValue();
	   
		jsRestriccionAuxiliar(f, codigoRestriccion);
	}
	
	public void jsRestriccionAuxiliar(MantenimientoMAVConfiguracionForm f, String codigoRestriccion) {
		if (log.isDebugEnabled()) {
			log.debug("jsRestriccionAuxiliar");
		}

		if(StringUtils.isNotEmpty(codigoRestriccion)) {
			for(int i=0; i<this.mavRestriccionTodosList.size(); i++) {
				Map mapRestriccion = (Map)this.mavRestriccionTodosList.get(i);
				String codigo = mapRestriccion.get("codigoConRes").toString();
				String indicadorTipo = MapUtils.getString(mapRestriccion, "indTipo", "");
				
				if (codigo.equals(codigoRestriccion)) {
					
					if("S".equals(indicadorTipo) || StringUtils.isEmpty(indicadorTipo)){
						limpiarCamposRestriccion(f);
						
						if(f.getIndicadorUnidad().equals("S"))
							mostrarRestriccionSimple = true;
						else	
							mostrarRestriccionSimple = false;				
						
						mostrarRestriccionUnico = false;
						mostrarRestriccionDoble = false;
						mostrarRestriccionMixto = false;
	
					}		
					if("U".equals(indicadorTipo)) {
						limpiarCamposRestriccion(f);
	
						if(f.getIndicadorUnidad().equals("S")){
							mostrarThUnicoUnidadesRest = true;
							mostrarTdUnicoUnidadesRest = true;
						} else {	
							mostrarThUnicoUnidadesRest = false;
							mostrarTdUnicoUnidadesRest = false;
						}	
	
						mostrarRestriccionSimple = false;
						mostrarRestriccionUnico = true;
						mostrarRestriccionDoble = false;
						mostrarRestriccionMixto = false;
						
					}
					if("D".equals(indicadorTipo)) {
						limpiarCamposRestriccion(f);
						
						if(f.getIndicadorUnidad().equals("S")){
							mostrarThDobleUnidadesRest = true;
							mostrarTdDobleUnidadesRest = true;
						} else {
							mostrarThDobleUnidadesRest = false;
							mostrarTdDobleUnidadesRest = false;
						}
						
						mostrarRestriccionSimple = false;
						mostrarRestriccionUnico = false;
						mostrarRestriccionDoble = true;
						mostrarRestriccionMixto = false;
						
					}
					if("M".equals(indicadorTipo)) {
						limpiarCamposRestriccion(f);
						
						if(f.getIndicadorUnidad().equals("S")){
							mostrarThMixtoUnidadesRest = true;
							mostrarTdMixtoUnidadesRest = true;
						} else {	
							mostrarThMixtoUnidadesRest = false;
							mostrarTdMixtoUnidadesRest = false;
						}
						
						mostrarRestriccionSimple = false;
						mostrarRestriccionUnico = false;
						mostrarRestriccionDoble = false;
						mostrarRestriccionMixto = true;
						
						jsRestriccionMixto(f, codigoRestriccion);
					}
					if("L".equals(indicadorTipo) || "E".equals(indicadorTipo)) {
						limpiarCamposRestriccion(f);
						
						mostrarRestriccionSimple = false;
						mostrarRestriccionUnico = false;
						mostrarRestriccionDoble = false;
						mostrarRestriccionMixto = false;
						
					}
									 
				}  
			}	
		}
	}
	
	private void jsConsideracionMixto(MantenimientoMAVConfiguracionForm f, String consideracion) {
		mostrarTdMixtoMonto1 = false;
		mostrarTdMixtoMonto2 = false;
		mostrarTdMixtoMarca1 = false;
		mostrarTdMixtoMarca2 = false;
		mostrarTdMixtoNegocio1 = false;
		mostrarTdMixtoNegocio2 = false;
		mostrarTdMixtoUnidadNegocio1 = false;
		mostrarTdMixtoUnidadNegocio2 = false;
		mostrarTdMixtoCatalogo1 = false;
		mostrarTdMixtoCatalogo2 = false;
		mostrarTdMontoFinanciadoDesde = false;
		mostrarTdMontoFinanciadoHasta1 = false;
		mostrarTdMontoFinanciadoHasta2 = false;
		mostrarTdPeriodoFinanciado1 = false;
		mostrarTdPeriodoFinanciado2 = false;
		mostrarThMixtoFormula = false;
		mostrarThMixtoVariableVentas = false;
		mostrarTdMixtoFormula = false;
		mostrarThMixtoNroPeriodosFV = false;
		mostrarTdMixtoNroPeriodosFV = false;
		
		mostrarTdMixtoPeriodoInicio1 = true;
		mostrarTdMixtoPeriodoInicio2 = true;
		mostrarTdMixtoPeriodoFin1 = true;
		mostrarTdMixtoPeriodoFin2 = true;
		mostrarThMixtoTipoVenta = true;
		mostrarTdMixtoTipoVenta2 = true;

		if((consideracion.equals(f.getConsideracionPedidoSuperaMonto())) || (consideracion.equals(f.getConsideracionPedidoNoSuperaMonto()))) {
			mostrarTdMixtoMonto1 = true;
			mostrarTdMixtoMonto2 = true;
		}
		else if((consideracion.equals(f.getConsideracionPedidoSuperaMontoMarca())) || (consideracion.equals(f.getConsideracionPedidoNoSuperaMontoMarca()))) {
			mostrarTdMixtoMonto1 = true;
			mostrarTdMixtoMonto2 = true;
			mostrarTdMixtoMarca1 = true;
			mostrarTdMixtoMarca2 = true;
		}
		else if((consideracion.equals(f.getConsideracionPedidoSuperaMontoUnidad())) || (consideracion.equals(f.getConsideracionPedidoNoSuperaMontoUnidad()))) {
			mostrarTdMixtoMonto1 = true;
			mostrarTdMixtoMonto2 = true;
			mostrarTdMixtoUnidadNegocio1 = true;
			mostrarTdMixtoUnidadNegocio2 = true;
		}
		else if((consideracion.equals(f.getConsideracionPedidoSuperaMontoNegocio())) || (consideracion.equals(f.getConsideracionPedidoNoSuperaMontoNegocio()))) {
			mostrarTdMixtoMonto1 = true;
			mostrarTdMixtoMonto2 = true;
			mostrarTdMixtoNegocio1 = true;
			mostrarTdMixtoNegocio2 = true;
		}
		else if((consideracion.equals(f.getConsideracionPedidoSuperaMontoCatalogo())) || (consideracion.equals(f.getConsideracionPedidoNoSuperaMontoCatalogo()))) {
			mostrarTdMixtoMonto1 = true;
			mostrarTdMixtoMonto2 = true;
			mostrarTdMixtoCatalogo1 = true;
			mostrarTdMixtoCatalogo2 = true;
		}
		else if(consideracion.equals(f.getConsideracionMontoFlexiPago())) {
			mostrarTdMixtoPeriodoInicio1 = false;
			mostrarTdMixtoPeriodoInicio2 = false;
			mostrarTdMixtoPeriodoFin1 = false;
			mostrarTdMixtoPeriodoFin2 = false;
			mostrarThMixtoTipoVenta = false;
			mostrarTdMixtoTipoVenta2 = false;
			
			mostrarTdMixtoMonto2 = true;
			mostrarTdMontoFinanciadoDesde = true;
			mostrarTdMontoFinanciadoHasta1 = true;
			mostrarTdMontoFinanciadoHasta2 = true;
			mostrarTdPeriodoFinanciado1 = true;
			mostrarTdPeriodoFinanciado2 = true;
		}	
		else if(consideracion.equals(f.getConsideracionInscripcionNuevaDupla())) {
				
		} 
		else if(consideracion.equals(f.getConsideracionFormula())) {
			mostrarTdMixtoPeriodoInicio1 = false;
			mostrarTdMixtoPeriodoInicio2 = false;
			mostrarTdMixtoPeriodoFin1 = false;
			mostrarTdMixtoPeriodoFin2 = false;
			mostrarThMixtoTipoVenta = false;
			mostrarTdMixtoTipoVenta2 = false;
			
			mostrarThMixtoFormula = true;
			mostrarTdMixtoFormula = true;
			mostrarThMixtoNroPeriodosFV = true;
			mostrarTdMixtoNroPeriodosFV = true;
		} 
		else if(consideracion.equals(f.getConsideracionVariablesVenta())) {
			mostrarTdMixtoPeriodoInicio1 = false;
			mostrarTdMixtoPeriodoInicio2 = false;
			mostrarTdMixtoPeriodoFin1 = false;
			mostrarTdMixtoPeriodoFin2 = false;
			mostrarThMixtoTipoVenta = false;
			mostrarTdMixtoTipoVenta2 = false;
			
			mostrarThMixtoVariableVentas = true;
			mostrarTdMixtoFormula = true;
			mostrarThMixtoNroPeriodosFV = true;
			mostrarTdMixtoNroPeriodosFV = true;
		} 
		else {
			mostrarConsideracionMixto = false;
		}
	}
	
	private void jsRestriccionMixto(MantenimientoMAVConfiguracionForm f, String restriccion) {
		mostrarTdMixtoMonto1Rest = false;
		mostrarTdMixtoMonto2Rest = false;
		mostrarTdMixtoMarca1Rest = false;
		mostrarTdMixtoMarca2Rest = false;
		mostrarTdMixtoNegocio1Rest = false;
		mostrarTdMixtoNegocio2Rest = false;
		mostrarTdMixtoUnidadNegocio1Rest = false;
		mostrarTdMixtoUnidadNegocio2Rest = false;
		mostrarTdMixtoCatalogo1Rest = false;
		mostrarTdMixtoCatalogo2Rest = false;

		if((restriccion.equals(f.getRestriccionPedidoSuperaMonto())) || (restriccion.equals(f.getRestriccionPedidoNoSuperaMonto()))) {
			mostrarTdMixtoMonto1Rest = true;
			mostrarTdMixtoMonto2Rest = true;
		}
		else if((restriccion.equals(f.getRestriccionPedidoSuperaMontoMarca())) || (restriccion.equals(f.getRestriccionPedidoNoSuperaMontoMarca()))) {
			mostrarTdMixtoMonto1Rest = true;
			mostrarTdMixtoMonto2Rest = true;
			mostrarTdMixtoMarca1Rest = true;
			mostrarTdMixtoMarca2Rest = true;
		}
		else if((restriccion.equals(f.getRestriccionPedidoSuperaMontoUnidad())) || (restriccion.equals(f.getRestriccionPedidoNoSuperaMontoUnidad()))) {
			mostrarTdMixtoMonto1Rest = true;
			mostrarTdMixtoMonto2Rest = true;
			mostrarTdMixtoUnidadNegocio1Rest = true;
			mostrarTdMixtoUnidadNegocio2Rest = true;
		}
		else if((restriccion.equals(f.getRestriccionPedidoSuperaMontoNegocio())) || (restriccion.equals(f.getRestriccionPedidoNoSuperaMontoNegocio()))) {
			mostrarTdMixtoMonto1Rest = true;
			mostrarTdMixtoMonto2Rest = true;
			mostrarTdMixtoNegocio1Rest = true;
			mostrarTdMixtoNegocio2Rest = true;
		}
		else if((restriccion.equals(f.getRestriccionPedidoSuperaMontoCatalogo())) || (restriccion.equals(f.getRestriccionPedidoNoSuperaMontoCatalogo()))) {
			mostrarTdMixtoMonto1Rest = true;
			mostrarTdMixtoMonto2Rest = true;
			mostrarTdMixtoCatalogo1Rest = true;
			mostrarTdMixtoCatalogo2Rest = true;
		}
		else {
			mostrarRestriccionMixto = false;
		}
	}	
	
	private boolean existeConsideracionIngresado(String consideracion) {
		for(int i=0; i<this.mavConfiguracionConsideracionList.size(); i++) {
			Map mapConsideracion = (Map)this.mavConfiguracionConsideracionList.get(i);

			String indicadorAccion = mapConsideracion.get("indicadorAccion").toString();
			String codigo = mapConsideracion.get("codigoConsideracion").toString();
			
			if(!indicadorAccion.equals("2")) {
				if (codigo.equals(consideracion)) {
					return true;
				}
			}	
		}
		
		return false;
	}
	
	private boolean existeConsideracionListaIngresado() {
		for(int i=0; i<this.mavConfiguracionConsideracionList.size(); i++) {
			Map mapConsideracion = (Map)this.mavConfiguracionConsideracionList.get(i);
			
			String indicadorAccion = mapConsideracion.get("indicadorAccion").toString();
			String indicadorTipo = MapUtils.getString(mapConsideracion , "indicadorTipo" , "");
				
			if(!indicadorAccion.equals("2")) {
				if ("E".equals(indicadorTipo) || "L".equals(indicadorTipo)) {
					return true;
				}
			}	
		}
		
		return false;
	}
	
	private int obtenerTotalConsideracionesIngresadas() {
		int contador = 0;
		
		for(int i=0; i<this.mavConfiguracionConsideracionList.size(); i++) {
			Map mapConsideracion = (Map)this.mavConfiguracionConsideracionList.get(i);

			String indicadorAccion = mapConsideracion.get("indicadorAccion").toString();
			
			if(!indicadorAccion.equals("2")) {
				contador = contador + 1;
			}	
		}
		
		return contador;
	}	
	
	private int obtenerTotalRestriccionesIngresadas() {
		int contador = 0;
		
		for(int i=0; i<this.mavConfiguracionRestriccionList.size(); i++) {
			Map mapConsideracion = (Map)this.mavConfiguracionRestriccionList.get(i);

			String indicadorAccion = mapConsideracion.get("indicadorAccion").toString();
			
			if(!indicadorAccion.equals("2")) {
				contador = contador + 1;
			}	
		}
		
		return contador;
	}
	
	public String getValidarConsRestMAV(String codigoConsideracion, String tipoConsideracion) {
		log.debug("iniico getValidarConsRest " + codigoConsideracion + " tipo " + tipoConsideracion);

		Map map = new HashMap();
		map.put("codigo", codigoConsideracion);

		List listC = null;
		List listR = null;
		String abrevConRes = "";
		if (tipoConsideracion.equals(Constants.MAV_TIPO_CONSIDERACION)) {
			List listConsideracion = this.mavConsideracionTodosList;
			Iterator it = listConsideracion.iterator();
			while (it.hasNext()) {
				Map beanC = (Map) it.next();
				String codigoConRes = beanC.get("codigoConRes").toString();
				
				if(codigoConRes.equals(codigoConsideracion)) {
					abrevConRes = (String)beanC.get("abrevConRes");
					break;
				}
			}
			
		} else {
			List listRestriccion = this.mavRestriccionTodosList;
			Iterator it = listRestriccion.iterator();
			while (it.hasNext()) {
				Map beanC = (Map) it.next();
				String codigoConRes = beanC.get("codigoConRes").toString();
				
				if(codigoConRes.equals(codigoConsideracion)) {
					abrevConRes = (String)beanC.get("abrevConRes");
					break;
				}
			}
			
		}

		log.debug("abrevConRes " + abrevConRes);

		List listConsideracion = this.mavConfiguracionConsideracionList;
		Iterator it = listConsideracion.iterator();
		while (it.hasNext()) {
			Map beanC = (Map) it.next();
			String abrev = (String) beanC.get("abrev");
			String indicadorAccion = (String)beanC.get("indicadorAccion");
			
			if (StringUtils.equals(abrev, abrevConRes) &&
					(indicadorAccion.equals(Constants.NUMERO_CERO) || indicadorAccion.equals(Constants.NUMERO_UNO))) {
				return codigoConsideracion;
			}
		}

		List listRestriccion = this.mavConfiguracionRestriccionList;
		it = listRestriccion.iterator();
		while (it.hasNext()) {
			Map beanR = (Map) it.next();
			String abrev = (String) beanR.get("abrev");
			String indicadorAccion = (String) beanR.get("indicadorAccion");
			
			if (StringUtils.equals(abrev, abrevConRes) &&
					(indicadorAccion.equals(Constants.NUMERO_CERO) || indicadorAccion.equals(Constants.NUMERO_UNO))) {
				return codigoConsideracion;
			}
		}

		return "";
	}
	
	private void mostrarVentanaAlerta(String mensajeResource) {
		String lsMensajeError = this.getResourceMessage(mensajeResource);
		this.setMensajeAlertaDefault(lsMensajeError);
		this.getRequestContext().execute("PF('principalFormAlert_alertDialog').show()");
	}
	
	private String getConsRest(String codigo, String flag){
		  
	  if("0".equals(flag)){//consideracionn
		  for(int i=0; i<this.mavConsideracionTodosList.size(); i++) {
				Map mapConsideracion = (Map)this.mavConsideracionTodosList.get(i);
				String codigoConRes = mapConsideracion.get("codigoConRes").toString();
				String indicadorTipo = MapUtils.getString(mapConsideracion, "indTipo", "");
				
				if (codigoConRes.equals(codigo)) {
					return indicadorTipo;
				}
		  }
		  
		  return "";
	  }
	  
	  if("1".equals(flag)){//restriccion
		  for(int i=0; i<this.mavRestriccionTodosList.size(); i++) {
				Map mapConsideracion = (Map)this.mavRestriccionTodosList.get(i);
				String codigoConRes = mapConsideracion.get("codigoConRes").toString();
				String indicadorTipo = MapUtils.getString(mapConsideracion, "indTipo", "");
				
				if (codigoConRes.equals(codigo)) {
					return indicadorTipo;
				}
		  }
		  
		  return"";
	  }
	  
	  return "";
	}

	private boolean isValidoConsRestr(MantenimientoMAVConfiguracionForm f, String accion, String indicadorTipo){
		if(accion.equals("accionConsideracionInsertar") || accion.equals("accionConsideracionActualizar")){
			if(indicadorTipo.equals("S") || StringUtils.isEmpty(indicadorTipo)){
				
				if(f.getIndicadorUnidad().equals("S") && StringUtils.isEmpty(f.getCondicionSimpleUnidades())){
					mostrarVentanaAlerta("mantenimientoMAVConfiguracionForm.condicionUnidades.requerido");
					//condicionSimpleUnidades.focus();
					return false;
				}
			}
			
			if(indicadorTipo.equals("U")){

				if(StringUtils.isEmpty(f.getCondicion1S())){
					mostrarVentanaAlerta("mantenimientoMAVConfiguracionForm.condicion1S.requerido");
					//condicion1.focus();
					return false;
				}
				if(f.getIndicadorUnidad().equals("S") && StringUtils.isEmpty(f.getCondicionUnicoUnidades())){
					mostrarVentanaAlerta("mantenimientoMAVConfiguracionForm.condicionUnidades.requerido");
					//condicionUnicoUnidades.focus();
					return false;
				}
			}
			
			if(indicadorTipo.equals("D")){
				
				if(StringUtils.isEmpty(f.getCondicion1())){
					mostrarVentanaAlerta("mantenimientoMAVConfiguracionForm.condicion1.requerido");
					//condicion1.focus();
					return false;
				}
				
				if(StringUtils.isEmpty(f.getCondicion2())){
					mostrarVentanaAlerta("mantenimientoMAVConfiguracionForm.condicion2.requerido");
					//condicion2.focus();
					return false;
				}
				
				if(f.getIndicadorUnidad().equals("S") && StringUtils.isEmpty(f.getCondicionDobleUnidades())){
					mostrarVentanaAlerta("mantenimientoMAVConfiguracionForm.condicionUnidades.requerido");
					//condicionDobleUnidades.focus();
					return false;
				}
				
				
				if(Integer.parseInt(f.getCondicion1()) > Integer.parseInt(f.getCondicion2())){
					mostrarVentanaAlerta("mantenimientoMAVConfiguracionForm.condicionRangoEdades");
					//condicion2.focus();
					return false;
				}
			}
			
			if(indicadorTipo.equals("M")){
				String consideracion = f.getCodigoConsideracion();
				
				if(!this.mostrarConsideracionMixto) {
					return true;
				}
				
				if(consideracion.equals(f.getConsideracionMontoFlexiPago())) {
				
					if(StringUtils.isEmpty(f.getCondicionMonto())){
						mostrarVentanaAlerta("mantenimientoMAVConfiguracionForm.montoFinanciadoDesde.requerido");
						//condicionMonto.focus();
						return false;
					}
					
					if(StringUtils.isEmpty(f.getCondicionMonto2())){
						mostrarVentanaAlerta("mantenimientoMAVConfiguracionForm.montoFinanciadoHasta.requerido");
						//condicionMonto2.focus();
						return false;
					}
					
					if(StringUtils.isEmpty(f.getCondicionPeriodoFinanciado())){
						mostrarVentanaAlerta("mantenimientoMAVConfiguracionForm.periodoFinanciada.requerido");
						//condicionPeriodoFinanciado.focus();
						return false;
					}
					
					if(Double.parseDouble(f.getCondicionMonto()) > Double.parseDouble(f.getCondicionMonto2())){
						mostrarVentanaAlerta("mantenimientoMAVConfiguracionForm.rangoMontoFinanciado");
						//condicionMonto.focus();
						return false;
					}
					
					if(f.getCondicionPeriodoFinanciado().compareTo(f.getCodigoPeriodo())>=0) {
						mostrarVentanaAlerta("mantenimientoMAVConfiguracionForm.validarPeriodoFinanciada");
						//condicionPeriodoFinanciado.focus();
						return false;
					}
					
					return true;
				}

				if(!consideracion.equals(f.getConsideracionInscripcionNuevaDupla()) && (!consideracion.equals(f.getConsideracionFormula())) &&
						(!consideracion.equals(f.getConsideracionVariablesVenta()))	) {
					if(StringUtils.isEmpty(f.getCondicionMonto())){
						mostrarVentanaAlerta("mantenimientoMAVConfiguracionForm.condicionMonto.requerido");
						//condicionMonto.focus();
						return false;
					}	
				}
				
				if(consideracion.equals(f.getConsideracionPedidoSuperaMontoMarca()) || consideracion.equals(f.getConsideracionPedidoNoSuperaMontoMarca()) ) {
					if(StringUtils.isEmpty(f.getCondicionMarca())){
						mostrarVentanaAlerta("mantenimientoMAVConfiguracionForm.condicionMarca.requerido");
						//condicionMarca.focus();
						return false;
					}	
				}
				
				if(consideracion.equals(f.getConsideracionPedidoSuperaMontoNegocio()) || consideracion.equals(f.getConsideracionPedidoNoSuperaMontoNegocio()) ) {
					if(StringUtils.isEmpty(f.getCondicionNegocio())){
						mostrarVentanaAlerta("mantenimientoMAVConfiguracionForm.condicionNegocio.requerido");
						//condicionNegocio.focus();
						return false;
					}	
				}
				
				if(consideracion.equals(f.getConsideracionPedidoSuperaMontoUnidad()) || consideracion.equals(f.getConsideracionPedidoNoSuperaMontoUnidad()) ) {
					if(StringUtils.isEmpty(f.getCondicionUnidadNegocio())){
						mostrarVentanaAlerta("mantenimientoMAVConfiguracionForm.condicionUnidadNegocio.requerido");
						//condicionUnidadNegocio.focus();
						return false;
					}	
				}
				
				if(consideracion.equals(f.getConsideracionPedidoSuperaMontoCatalogo()) || consideracion.equals(f.getConsideracionPedidoNoSuperaMontoCatalogo()) ) {
					if(StringUtils.isEmpty(f.getCondicionCatalogo())){
						mostrarVentanaAlerta("mantenimientoMAVConfiguracionForm.condicionCatalogo.requerido");
						//condicionCatalogo.focus();
						return false;
					}	
				}
				
				if(consideracion.equals(f.getConsideracionFormula()) || consideracion.equals(f.getConsideracionVariablesVenta()) ) {

					if(f.getIndicadorUnidad().equals("S")){
						mostrarVentanaAlerta("mantenimientoMAVConfiguracionForm.msg.validaFormula1");
						return false;
					}
					
					if(!f.isExisteListaRegionZona()) {
						mostrarVentanaAlerta("mantenimientoMAVConfiguracionForm.msg.validaFormula2");
						return false;
					}
					
					if(StringUtils.isEmpty(f.getCondicionFormula())){
						if(consideracion.equals(f.getConsideracionFormula()))
							mostrarVentanaAlerta("mantenimientoMAVConfiguracionForm.condicionFormula.requerido");
						else
							mostrarVentanaAlerta("mantenimientoMAVConfiguracionForm.condicionVariableVentas.requerido");
						
						//condicionFormula.focus();
						return false;
					}
					
					if(StringUtils.isEmpty(f.getCondicionNroPeriodosFV())){
						mostrarVentanaAlerta("mantenimientoMAVConfiguracionForm.condicionNroPeriodosFV.requerido");
						//condicionNroPeriodosFV.focus();
						return false;
					}
					
				} else {
					if(StringUtils.isEmpty(f.getCondicionPeriodoInicio())){
						mostrarVentanaAlerta("mantenimientoMAVConfiguracionForm.condicionPeriodoInicio.requerido");
						//condicionPeriodoInicio.focus();
						return false;
					}
					if(StringUtils.isEmpty(f.getCondicionPeriodoFin())){
						mostrarVentanaAlerta("mantenimientoMAVConfiguracionForm.condicionPeriodoFin.requerido");
						//condicionPeriodoFin.focus();
						return false;
					}
					if(f.getCondicionPeriodoInicio().compareTo(f.getCondicionPeriodoFin())>0){
						mostrarVentanaAlerta("mantenimientoMAVConfiguracionForm.condicionRangoPeriodos");
						//condicionPeriodoFin.focus();
						return false;
					}
					if(f.getIndicadorUnidad().equals("S") && StringUtils.isEmpty(f.getCondicionUnidades())){
						mostrarVentanaAlerta("mantenimientoMAVConfiguracionForm.condicionUnidades.requerido");
						//condicionUnidades.focus();
						return false;
					}		
				}	
			}
		
		}else{
			if(indicadorTipo.equals("S") || StringUtils.isEmpty(indicadorTipo)){
				
				if(f.getIndicadorUnidad().equals("S") && StringUtils.isEmpty(f.getCondicionSimpleRestUnidades())){
					mostrarVentanaAlerta("mantenimientoMAVConfiguracionForm.condicionUnidades.requerido");
					//condicionSimpleRestUnidades.focus();
					return false;
				}
			}
			
			if(indicadorTipo.equals("U")){

				if(StringUtils.isEmpty(f.getCondicionRest1S())){
					mostrarVentanaAlerta("mantenimientoMAVConfiguracionForm.condicion1S.requerido");
					//condicionRest1S.focus();
					return false;
				}
				if(f.getIndicadorUnidad().equals("S") && StringUtils.isEmpty(f.getCondicionUnicoRestUnidades())){
					mostrarVentanaAlerta("mantenimientoMAVConfiguracionForm.condicionUnidades.requerido");
					//condicionUnicoRestUnidades.focus();
					return false;
				}
			}
			
			if(indicadorTipo.equals("D")){
				
				if(StringUtils.isEmpty(f.getCondicionRest1())){
					mostrarVentanaAlerta("mantenimientoMAVConfiguracionForm.condicion1.requerido");
					//condicionRest1.focus();
					return false;
				}
				
				if(StringUtils.isEmpty(f.getCondicionRest2())){
					mostrarVentanaAlerta("mantenimientoMAVConfiguracionForm.condicion2.requerido");
					//condicionRest2.focus();
					return false;
				}
				
				if(f.getIndicadorUnidad().equals("S") && StringUtils.isEmpty(f.getCondicionDobleRestUnidades())){
					mostrarVentanaAlerta("mantenimientoMAVConfiguracionForm.condicionUnidades.requerido");
					//condicionDobleRestUnidades.focus();
					return false;
				}
				
				
				if(Integer.parseInt(f.getCondicionRest1()) > Integer.parseInt(f.getCondicionRest2())){
					mostrarVentanaAlerta("mantenimientoMAVConfiguracionForm.condicionRangoEdades");
					//condicionRest2.focus();
					return false;
				}
			}
			
			if(indicadorTipo.equals("M")){
				String restriccion = f.getCodigoRestriccion();
				
				if(!this.mostrarRestriccionMixto) {
					return true;
				}
						
				if(!restriccion.equals(f.getRestriccionInscripcionNuevaDupla())) {
					if(StringUtils.isEmpty(f.getCondicionRestMonto())){
						mostrarVentanaAlerta("mantenimientoMAVConfiguracionForm.condicionMonto.requerido");
						//condicionMonto.focus();
						return false;
					}	
				}
				
				if(restriccion.equals(f.getRestriccionPedidoSuperaMontoMarca()) || restriccion.equals(f.getRestriccionPedidoNoSuperaMontoMarca()) ) {
					if(StringUtils.isEmpty(f.getCondicionRestMarca())){
						mostrarVentanaAlerta("mantenimientoMAVConfiguracionForm.condicionMarca.requerido");
						//condicionRestMarca.focus();
						return false;
					}	
				}
				
				if(restriccion.equals(f.getRestriccionPedidoSuperaMontoNegocio()) || restriccion.equals(f.getRestriccionPedidoNoSuperaMontoNegocio()) ) {
					if(StringUtils.isEmpty(f.getCondicionRestNegocio())){
						mostrarVentanaAlerta("mantenimientoMAVConfiguracionForm.condicionNegocio.requerido");
						//condicionRestNegocio.focus();
						return false;
					}	
				}
				

				if(restriccion.equals(f.getRestriccionPedidoSuperaMontoUnidad()) || restriccion.equals(f.getRestriccionPedidoNoSuperaMontoUnidad()) ) {
					if(StringUtils.isEmpty(f.getCondicionRestUnidadNegocio())){
						mostrarVentanaAlerta("mantenimientoMAVConfiguracionForm.condicionUnidadNegocio.requerido");
						//condicionRestUnidadNegocio.focus();
						return false;
					}	
				}
				
				if(restriccion.equals(f.getRestriccionPedidoSuperaMontoCatalogo()) || restriccion.equals(f.getRestriccionPedidoNoSuperaMontoCatalogo()) ) {
					if(StringUtils.isEmpty(f.getCondicionRestCatalogo())){
						mostrarVentanaAlerta("mantenimientoMAVConfiguracionForm.condicionCatalogo.requerido");
						//condicionRestCatalogo.focus();
						return false;
					}	
				}					
				
				if(StringUtils.isEmpty(f.getCondicionRestPeriodoInicio())){
					mostrarVentanaAlerta("mantenimientoMAVConfiguracionForm.condicionPeriodoInicio.requerido");
					//condicionRestPeriodoInicio.focus();
					return false;
				}
				if(StringUtils.isEmpty(f.getCondicionRestPeriodoFin())){
					mostrarVentanaAlerta("mantenimientoMAVConfiguracionForm.condicionPeriodoFin.requerido");
					//condicionRestPeriodoFin.focus();
					return false;
				}
				if(f.getCondicionRestPeriodoInicio().compareTo(f.getCondicionRestPeriodoFin())>0){
					mostrarVentanaAlerta("mantenimientoMAVConfiguracionForm.condicionRangoPeriodos");
					//condicionRestPeriodoFin.focus();
					return false;
				}
				if(f.getIndicadorUnidad().equals("S") && StringUtils.isEmpty(f.getCondicionRestUnidades())){
					mostrarVentanaAlerta("mantenimientoMAVConfiguracionForm.condicionUnidades.requerido");
					//condicionRestUnidades.focus();
					return false;
				}
						
			}
			
		}
		
		return true;
	}
	
	@Override
	public String setValidarMantenimiento() {
		MantenimientoMAVConfiguracionForm f = (MantenimientoMAVConfiguracionForm) this.formMantenimiento;
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		
		//VALIDAMOS QUE EL CODIGO DE PRODUCTO EXISTA
		String data = ajaxService.getDescripcionInternacionalizableProducto(f.getCodigoProducto());
		
		if (StringUtils.isBlank(data)) {
			f.setDescripcionProducto("");
			return getResourceMessage("mantenimientoDTOMatrizDescuentoForm.msg.producto.no.existe");
		} else {
			f.setDescripcionProducto(data);
		}
		
		//VALIDAMOS QUE INGRESE AL MENOS UNA CONSIDERACION O RESTRICCION
		int sizeConsideracionList = obtenerTotalConsideracionesIngresadas(); 
		int sizeRestriccionList = obtenerTotalRestriccionesIngresadas(); 

		if((sizeConsideracionList == 0) && (sizeRestriccionList == 0)) {
    		return getResourceMessage("mantenimientoMAVConfiguracionForm.consideracion.information");	
    	}
    	
		if(f.getIndicadorUnidad().equals("N")){
    		if(StringUtils.isEmpty(f.getNumeroUnidades())) {
    			return getResourceMessage("mantenimientoMAVConfiguracionForm.condicionUnidades.requerido");	
    		}
    		
    		if(Integer.parseInt(f.getNumeroUnidades()) == 0) {
    			return getResourceMessage("mantenimientoMAVConfiguracionForm.msg.validarCantidadUnidades");	
    		}
    	}

    	//VALIDAMOS QUE LAS CONSIDERACIONES O RESTRICCIONES PERTENEZCAN AL TIPO DE CLIENTE ACTUAL
		boolean hdConsideracionTodos = existeConsideracionIngresado("1"); 
		
		if((sizeConsideracionList>=1 || sizeConsideracionList>=1) && hdConsideracionTodos) {
			String dirigidoAux="";
			for(int i=0; i<this.mavConfiguracionConsideracionList.size(); i++) {
				Map mapConsideracion = (Map)this.mavConfiguracionConsideracionList.get(i);
				
				String indicadorAccion = mapConsideracion.get("indicadorAccion").toString();
				dirigidoAux = mapConsideracion.get("dirigido").toString();
					
				if(!indicadorAccion.equals("2")) {
					break;
				}	
			}
			
			String dirigidoActual = f.getCodigoTipoCliente().equals(Constants.MAE_TIPO_CLIENTE_CONSULTORA)?"C":"G";
			
			if(!dirigidoAux.equals(dirigidoActual)) {
				if(dirigidoAux.equals("C")) {
					return getResourceMessage("mantenimientoMAVConfiguracionForm.msg.validarActividadConsultoras");
				} else {
					return getResourceMessage("mantenimientoMAVConfiguracionForm.msg.validarActividadGerentes");
				}				
			}
		}
		
		//VALIDAMOS EL PRECIO DEL MAV
		if(StringUtils.isNotEmpty(f.getIndicadorValidaPrecio())) {
			if(f.getIndicadorValidaPrecio().equals("1")) {
				String precio = f.getPrecio();
				
				if(StringUtils.isBlank(precio)) {
					precio = "0";
				}
				
				if(Double.parseDouble(precio)==0) {
					return getResourceMessage("mantenimientoMAVConfiguracionForm.msg.validaPrecio1");
				}
			}
		} else {
			return getResourceMessage("mantenimientoMAVConfiguracionForm.msg.validaPrecio2");
		}
		
		//validamos Periodo
		String anho = f.getCodigoPeriodo().substring(0,4); 
		String camp = f.getCodigoPeriodo().substring(4);
		if(Integer.parseInt(camp) >18 || Integer.parseInt(camp) <=0 || Integer.parseInt(anho) <=1900){
			return "Campaña no válida";
		}

		if (this.periodoActual!=null && this.periodoActual.compareTo(f.getCodigoPeriodo())>0) {
			return "Campaña de despacho no puede ser menor a la Campaña Actual";
		}
		
		/*if (!confirm('<fmt:message key="mantenimientoMAVConfiguracionForm.msg.save"/>'))  {
				    return false;
			    }*/
				
		return "";

	}
	
	@Override
	protected boolean setSaveAttributes() throws Exception {
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		MantenimientoMAVConfiguracionService service = (MantenimientoMAVConfiguracionService) 
													getBean("spusicc.mantenimientoMAVConfiguracionService");
		MantenimientoMAVConfiguracionForm f = (MantenimientoMAVConfiguracionForm) this.formMantenimiento;
		
		Map params = BeanUtils.describe(f);
		params.put("codigoUsuario", usuario.getLogin());
		
		String oidActividadTipoOferta = f.getOidActividadTipoOferta();
		StringTokenizer st = new StringTokenizer(oidActividadTipoOferta, "__");
		params.put("oidActividadTipoOferta", st.nextToken());
		params.put("codigoTipoOferta", st.nextToken());
		
		if((f.getPrecio() == null) || (f.getPrecio().equals("")))
			params.put("precio", "0");
		else
			params.put("precio", new BigDecimal(f.getPrecio()));
		params.put("codigoActividad", f.getOidActividad());
		
		//asociamos la lista de consideraciones
		List listConsi = this.mavConfiguracionConsideracionList;
		if(listConsi==null) listConsi = new ArrayList();
		params.put("listConsi", listConsi);
		
		//Validamos consideracion de FlexiPago
		if(listConsi.size() > 0) {
			for(int i=0; i<listConsi.size(); i++) {
				Map mapConsi = (Map)listConsi.get(i);
				
				String codigoConsideracion = mapConsi.get("codigoConsideracion").toString();
				
				if(Constants.MAV_CONRES_MONTO_FLEXIPAGO == Integer.parseInt(codigoConsideracion)) {
					String periodoFlexiPago = mapConsi.get("condicion3").toString();
					
					if(periodoFlexiPago.compareTo(f.getCodigoPeriodo())>=0) {
						throw new Exception(getResourceMessage("mantenimientoMAVConfiguracionForm.validarPeriodoFinanciada"));
					}
						
					//break;	
				}
			}		
		}

		//asociamos la lista de restricciones
		List listRestri = this.mavConfiguracionRestriccionList;
		if(listRestri==null) listRestri = new ArrayList();
		params.put("listRestri", listRestri);
		
		if (this.accion.equals(this.ACCION_NUEVO)) {
			params.put("codigoEstado", "2"); //Completado
		    service.insertConfiguracion(params);//inserta
		    
		    addInfo("",	getResourceMessage("mantenimientoMAVConfiguracionForm.insert"));

		}
		else{
			service.updateConfiguracion(params);//update
			//enviamos el mensaje de satisfaccion
			addInfo("",	getResourceMessage("mantenimientoMAVConfiguracionForm.update"));

		}	

		return true;
	}
	

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	/*public ActionForward regresar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'regresar' method");
		}		
		HttpSession session = request.getSession(true);
		MantenimientoMAVConfiguracionForm f = (MantenimientoMAVConfiguracionForm) form;
		
		session.setAttribute("flagRegreso", Constants.NRO_UNO);
		return mapping.findForward(getSaveForward());
	}*/
	
	/*****INICIO TAB CONSIDERACION *****/
	/**
	 * inserta el Zona de una poliza
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void insertConsideracion(String modo) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'insertConsideracion' method ");
		}

		try {
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			MantenimientoMAVConfiguracionForm f = (MantenimientoMAVConfiguracionForm) this.formMantenimiento;
			MantenimientoMAVConfiguracionService service = (MantenimientoMAVConfiguracionService) getBean("spusicc.mantenimientoMAVConfiguracionService");
			f.setTabSeleccion(Constants.MAV_TAB_CONSIDERACION);
			this.nombreVentanaPopup="";
			
			//VALIDACIONES <INICIO>
			if(StringUtils.isEmpty(f.getCodigoConsideracion())){
				mostrarVentanaAlerta("mantenimientoMENPatronMensajeForm.mensaje.seleccionarConsideracion");
				return;
			}

			String indicadorTipoAux=getConsRest(f.getCodigoConsideracion(),"0");
			
			int sizeConsideracionList = obtenerTotalConsideracionesIngresadas(); 
			int sizeRestriccionList = obtenerTotalRestriccionesIngresadas(); 
			boolean hdConsideracionTodos = existeConsideracionIngresado("1"); 
			boolean hdCRLista = existeConsideracionListaIngresado();
			
			if((sizeConsideracionList>=1 || sizeConsideracionList>=1) && !hdConsideracionTodos) {
				
				String dirigidoAux="";
				for(int i=0; i<this.mavConfiguracionConsideracionList.size(); i++) {
					Map mapConsideracion = (Map)this.mavConfiguracionConsideracionList.get(i);
					
					String indicadorAccion = mapConsideracion.get("indicadorAccion").toString();
					dirigidoAux = mapConsideracion.get("dirigido").toString();
						
					if(!indicadorAccion.equals("2")) {
						break;
					}	
				}
				
				String dirigidoActual = f.getCodigoTipoCliente().equals(Constants.MAE_TIPO_CLIENTE_CONSULTORA)?"C":"G";
				
				if(!dirigidoAux.equals(dirigidoActual)) {
					if(dirigidoAux.equals("C")) {
						mostrarVentanaAlerta("mantenimientoMAVConfiguracionForm.msg.validarActividadConsultoras");
					} else {
						mostrarVentanaAlerta("mantenimientoMAVConfiguracionForm.msg.validarActividadGerentes");
					}	
					return;
				}
			}

			if(f.getCodigoConsideracion().equals(Constants.MAV_CONRES_TODOS) && sizeConsideracionList>=1) {
				mostrarVentanaAlerta("mantenimientoMAVConfiguracionForm.msg.validarTodos");
				return;
			}
			if(hdConsideracionTodos) {
				mostrarVentanaAlerta("mantenimientoMAVConfiguracionForm.msg.validarTodos1");
				return;
			}
			if(indicadorTipoAux.equals("E") && sizeConsideracionList>1) {
				mostrarVentanaAlerta("mantenimientoMAVConfiguracionForm.msg.validarLista");
				return;
			}
			if(f.getIndicadorUnidad().equals("S") && hdCRLista) {
				mostrarVentanaAlerta("mantenimientoMAVConfiguracionForm.msg.validarLista1");
				return;
			}
			
			if(!isValidoConsRestr(f, "accionConsideracionInsertar", indicadorTipoAux)){
				 return;
			 } 
			
			if(indicadorTipoAux.equals("L") || indicadorTipoAux.equals("E")) {
				String validarConsMAV = getValidarConsRestMAV(f.getCodigoConsideracion(), "C");
				if(StringUtils.isNotEmpty(validarConsMAV)) {
					mostrarVentanaAlerta("mantenimientoMAVConfiguracionForm.existe.consideracion.registro");
					return;
				}	
			}
			//VALIDACIONES <FIN>	
			this.esBotonInsertar = true;
			
			if((indicadorTipoAux.equals("L") || indicadorTipoAux.equals("E")) && modo.equals("insertConsideracion")) {
				this.indicadorTipo = "C";
				viewPopup();
			} else {				
					
				List listComboConsideracion= service.getConsideracion(null);
				List list = this.mavConfiguracionConsideracionList;
				if(list==null) list = new ArrayList();
				
				Map condicion = getCondicion(f.getCodigoConsideracion(),listComboConsideracion);
				String tipo = (String)condicion.get("indTipo");
				String dirigido = (String)condicion.get("dirigido");
				
				Map bean= new HashMap();
				bean.put("codigoPais", pais.getCodigo());
				bean.put("correlativoMAV", f.getCorrelativo());
				bean.put("codigoPeriodo", f.getCodigoPeriodo());
				bean.put("codigoConsideracion", f.getCodigoConsideracion());
				bean.put("indicadorTipo",tipo );
				bean.put("dirigido",dirigido );
				bean.put("descripcion",(String)condicion.get("descripcionConRes") );
				bean.put("abrev", getAbrevConRes(f.getCodigoConsideracion(),listComboConsideracion));
				bean.put("indicadorAccion",Constants.NUMERO_CERO);
				bean.put("condicion","" );
				bean.put("numReg","0" );
				
				if(StringUtils.isEmpty(tipo) || Constants.MAV_TIPO_SIN_CONDICION.equals(tipo)){
					bean.put("numeroUnidades",f.getCondicionSimpleUnidades());
					bean.put("numReg","1" );
				}
				if(Constants.MAV_TIPO_UNA_CONDICION.equals(tipo)){
					bean.put("condicion1",f.getCondicion1S());
					bean.put("condicion",f.getCondicion1S());
					bean.put("numeroUnidades",f.getCondicionUnicoUnidades());
					bean.put("numReg","1" );
				}
				if(Constants.MAV_TIPO_UNA_CONDICION_DOBLE.equals(tipo)){
					bean.put("condicion",f.getCondicion1() + " , "+f.getCondicion2() );
					bean.put("condicion1",f.getCondicion1());
					bean.put("condicion2",f.getCondicion2());
					bean.put("numeroUnidades",f.getCondicionDobleUnidades());
					bean.put("numReg","1" );
				}
				if(Constants.MAV_TIPO_CONDICIONES_MIXTAS.equals(tipo)){
					if((Constants.MAV_CONRES_POR_FORMULA == Integer.parseInt(f.getCodigoConsideracion())) || 
							(Constants.MAV_CONRES_POR_VARIABLES_VENTA == Integer.parseInt(f.getCodigoConsideracion())) ){
						bean.put("condicion", getDesFormula(f.getCondicionFormula()) + " , "+f.getCondicionNroPeriodosFV());
						bean.put("condicion1",f.getCondicionFormula());
						bean.put("condicion2",f.getCondicionNroPeriodosFV());
						
					}
					else if(Constants.MAV_CONRES_INSCRIPCION_NUEVA_DUPLA == Integer.parseInt(f.getCodigoConsideracion())){
						bean.put("condicion",f.getCondicionPeriodoInicio() + " , "+f.getCondicionPeriodoFin());
						bean.put("condicion1",f.getCondicionPeriodoInicio());
						bean.put("condicion2",f.getCondicionPeriodoFin());
						
					}
					else if(Constants.MAV_CONRES_MONTO_FLEXIPAGO == Integer.parseInt(f.getCodigoConsideracion())){
						bean.put("condicion",f.getCondicionMonto() + " , "+f.getCondicionMonto2()
																			+ " , "+f.getCondicionPeriodoFinanciado());
						bean.put("condicion1",f.getCondicionMonto());
						bean.put("condicion2",f.getCondicionMonto2());
						bean.put("condicion3",f.getCondicionPeriodoFinanciado());
						
					}
					else {
						String condicionAux = null;
						String descripcionAux = "";
						
						String condicionAuxTipoVenta = f.getCondicionTipoVenta();
						String descripcionAuxTipoVenta = this.getDesTipoVenta(condicionAuxTipoVenta);
						if((Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_MARCA == Integer.parseInt(f.getCodigoConsideracion())) || 
								(Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_MARCA == Integer.parseInt(f.getCodigoConsideracion()))){
							condicionAux = f.getCondicionMarca();
							descripcionAux = getDesMarcaProducto(condicionAux);
						}
						if((Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_NEGOCIO == Integer.parseInt(f.getCodigoConsideracion())) || 
								(Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_NEGOCIO == Integer.parseInt(f.getCodigoConsideracion()))){
							condicionAux = f.getCondicionNegocio();
							descripcionAux = getDesNegocio(condicionAux);
						}
						
						if((Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_UNIDAD == Integer.parseInt(f.getCodigoConsideracion())) || 
								(Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_UNIDAD == Integer.parseInt(f.getCodigoConsideracion()))){
							condicionAux = f.getCondicionUnidadNegocio();
							descripcionAux = getDesUnidadNegocio(condicionAux);
						}
						
						if((Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_CATALOGO == Integer.parseInt(f.getCodigoConsideracion())) || 
								(Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_CATALOGO == Integer.parseInt(f.getCodigoConsideracion()))){
							condicionAux = f.getCondicionCatalogo();
							descripcionAux = getDesCatalogo(condicionAux);
						}
						
						if((Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO == Integer.parseInt(f.getCodigoConsideracion())) || 
								(Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO == Integer.parseInt(f.getCodigoConsideracion()))){
							bean.put("condicion",f.getCondicionMonto() + " , "+f.getCondicionPeriodoInicio() + " , "+
									f.getCondicionPeriodoFin() + " , " + descripcionAuxTipoVenta);
							
							bean.put("condicion1",f.getCondicionMonto());
							bean.put("condicion2",f.getCondicionPeriodoInicio());
							bean.put("condicion3",f.getCondicionPeriodoFin());
							bean.put("condicion4",f.getCondicionTipoVenta());
						} else {
							bean.put("condicion",f.getCondicionMonto() + " , "+ descripcionAux + " , "+f.getCondicionPeriodoInicio() + " , "+
									f.getCondicionPeriodoFin() + " , " + descripcionAuxTipoVenta);
							
							bean.put("condicion1",f.getCondicionMonto());
							bean.put("condicion2",condicionAux);
							bean.put("condicion3",f.getCondicionPeriodoInicio());
							bean.put("condicion4",f.getCondicionPeriodoFin());
							bean.put("condicion5",f.getCondicionTipoVenta());
						}
					}
					
					bean.put("numeroUnidades",f.getCondicionUnidades());
					bean.put("numReg","1" );
				}
				
				if(Constants.MAV_TIPO_LISTA_CONDICION.equals(tipo)
						|| Constants.MAV_TIPO_LISTA_EXTERNA_CONDICION.equals(tipo)){
		
					if(Constants.MAV_CONRES_LISTA_CONSU == Integer.parseInt(f.getCodigoConsideracion())){
						List consultoraList=this.consultoraList;
						log.debug("consultoraList >>>>>>> "+consultoraList.size());
						bean.put("consultoraList", consultoraList);
						bean.put("numReg", "" +consultoraList.size());
						bean.put("condicion","" +consultoraList.size());
						bean.put("condicion1","" +consultoraList.size() );
						bean.put("tipoConsideracionLista",Constants.MAV_TIPO_LISTA_CONDICION );
					}					
					
					if(Constants.MAV_CONRES_LISTA_REGION_ZONA == Integer.parseInt(f.getCodigoConsideracion())){
						List regionesList=this.regionesList;
						log.debug("regionesList >>>>>>> "+regionesList.size());
						bean.put("regionesList", regionesList);
						bean.put("numReg", "" +regionesList.size());
						bean.put("condicion","" +regionesList.size());
						bean.put("condicion1","" +regionesList.size() );
						bean.put("tipoConsideracionLista",Constants.MAV_TIPO_LISTA_CONDICION );
					}
					
					if(Constants.MAV_CONRES_CLASIFICACIONES_CLIENTE == Integer.parseInt(f.getCodigoConsideracion())){
						List listC =this.clasificacionesList;
						log.debug("listClasificaciones >>>>> "+listC.size());
						bean.put("listClasificaciones", listC);
						//actualizar los eliminados 
						Iterator it = listC.iterator();
						int cont =listC.size();
						while(it.hasNext()){
							Map aux = (Map)it.next();
							String indicadorAccion = (String)aux.get("indicadorAccion");
							if(Constants.NUMERO_DOS.equals(indicadorAccion)){
								cont--;
							}												
						}								
						bean.put("numReg", "" +cont);
						bean.put("condicion","" +cont);
						bean.put("condicion1","" +cont);
						bean.put("tipoConsideracionLista",Constants.MAV_TIPO_LISTA_CONDICION );
						if(cont ==0)bean.put("indicadorAccion", Constants.NUMERO_DOS);
					}
		
					if(Constants.MAV_CONRES_UNIDAD_ADM == Integer.parseInt(f.getCodigoConsideracion())){
						List listU =this.unidadesList;
						log.debug("unidadesList >>>>>>> "+listU.size());
						bean.put("listUnidades", listU);
						//actualizar los eliminados 
						Iterator it = listU.iterator();
						int cont =listU.size();
						while(it.hasNext()){
							Map aux = (Map)it.next();
							String indicadorAccion = (String)aux.get("indicadorAccion");
							if(Constants.NUMERO_DOS.equals(indicadorAccion)){
								cont--;
							}												
						}						
						bean.put("numReg", "" +cont);
						bean.put("condicion","" +cont);
						bean.put("condicion1","" +cont);
						bean.put("tipoConsideracionLista",Constants.MAV_TIPO_LISTA_CONDICION );
						if(cont ==0)bean.put("indicadorAccion", Constants.NUMERO_DOS);
					}	
					
					if(Constants.MAV_CONRES_ESTATUS_CLIENTE == Integer.parseInt(f.getCodigoConsideracion())){
						List lista=this.codigoEstatusList;
						log.debug("listEstatus  "+lista.size());
						bean.put("listEstatus", lista);
						//actualizar los eliminados 
						Iterator it = lista.iterator();
						int cont =lista.size();
						while(it.hasNext()){
							Map aux = (Map)it.next();
							String indicadorAccion = (String)aux.get("indicadorAccion");
							if(Constants.NUMERO_DOS.equals(indicadorAccion)){
								cont--;
							}												
						}								
						bean.put("numReg", "" +cont);
						bean.put("condicion","" +cont);
						bean.put("condicion1","" +cont );
						bean.put("tipoConsideracionLista",Constants.MAV_TIPO_LISTA_CONDICION );
						if(cont ==0)bean.put("indicadorAccion", Constants.NUMERO_DOS);
					}
					
				}			
				List listR =this.mavConfiguracionRestriccionList;
				bean.put("codigoRestriccion", f.getCodigoConsideracion());
				if(isValido(bean,list) && isValidoRest(bean,listR)){//es registro valido cuando no se encuntra en la lista o se encuentra como eliminado
					bean.put("codigoRestriccion",null);
					list.add(bean);
					//service.saveTemporalZona(bean);
				}		
				else{
					
				  	f.setSelectedItemsConsideracion(null);
				  	f.setSelectedItemsRestriccion(null);	
				  	
				  	throw new Exception(this.getResourceMessage("mantenimientoMAVConfiguracionForm.existe.consideracion.registro"));
				}
				
				actualizarListTiposOferta(f);
				limpiarCamposConsideracion(f);
				
				f.setCodigoConsideracion("");
				
				f.setSelectedItemsConsideracion(null);
			  	f.setSelectedItemsRestriccion(null);
			  	
				this.mavConfiguracionConsideracionList = list;
				this.dataTableConsideracion = new DataTableModel(list);
			}
		} catch (Exception e) {
			addError("Error", obtieneMensajeErrorException(e));
		}
		
	}
	
	/**
	 * inserta el Zona de una poliza
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void updateConsideracion(ActionEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'updateConsideracion' method");
		}

		try {
			MantenimientoMAVConfiguracionForm f = (MantenimientoMAVConfiguracionForm) this.formMantenimiento;
			f.setTabSeleccion(Constants.MAV_TAB_CONSIDERACION);
	
			List list = this.mavConfiguracionConsideracionList;
			if(list==null) list = new ArrayList();
	
			Map bean = (Map)this.beanRegistroConsideracion;
			String codigoConRes = (String)bean.get("codigoConsideracion");
			String tipo = (String)bean.get("indicadorTipo");
						
			bean.put("codigoPeriodo", f.getCodigoPeriodo());
			//bean.put("indicadorAccion",Constants.NUMERO_UNO);
			bean.put("condicion","" );
			bean.put("numReg","0" );
				
			if(Constants.MAV_TIPO_LISTA_CONDICION.equals(tipo)
					|| Constants.MAV_TIPO_LISTA_EXTERNA_CONDICION.equals(tipo)){
				log.debug(" codigoConRes >>>  "+codigoConRes);				
				
				if(Constants.MAV_CONRES_LISTA_CONSU == Integer.parseInt(codigoConRes)){
					List consultoraList=this.consultoraList;
					log.debug("consultoraList >>>>>>> "+consultoraList.size());
					bean.put("consultoraList", consultoraList);
					bean.put("numReg", "" +consultoraList.size());
					bean.put("condicion","" +consultoraList.size());
					bean.put("condicion1","" +consultoraList.size() );
					bean.put("tipoConsideracionLista",Constants.MAV_TIPO_LISTA_CONDICION );
					bean.put("indicadorModificadoPopup", Constants.NRO_UNO);
				}	
				
				if(Constants.MAV_CONRES_LISTA_REGION_ZONA == Integer.parseInt(codigoConRes)){
					List regionesList=this.regionesList;
					log.debug("regionesList >>>>>>> "+regionesList.size());
					bean.put("regionesList", regionesList);
					bean.put("numReg", "" +regionesList.size());
					bean.put("condicion","" +regionesList.size());
					bean.put("condicion1","" +regionesList.size() );
					bean.put("tipoConsideracionLista",Constants.MAV_TIPO_LISTA_CONDICION );
					bean.put("indicadorModificadoPopup", Constants.NRO_UNO);
				}
				
				if(Constants.MAV_CONRES_CLASIFICACIONES_CLIENTE == Integer.parseInt(codigoConRes)){
					List listC =this.clasificacionesList;
					log.debug("listClasificaciones >>>>>>> "+listC.size());
					bean.put("listClasificaciones", listC);
					//actualizar los eliminados 
					Iterator it = listC.iterator();
					int cont =listC.size();
					while(it.hasNext()){
						Map aux = (Map)it.next();
						String indicadorAccion = (String)aux.get("indicadorAccion");
						if(Constants.NUMERO_DOS.equals(indicadorAccion)){
							aux.put("indicadorAccion",Constants.NUMERO_DOS);
							cont--;
						}												
					}					
					bean.put("numReg", "" +cont);
					bean.put("condicion","" +cont);
					bean.put("condicion1","" +cont );
					bean.put("indicadorModificadoPopup", Constants.NRO_UNO);
					if(cont ==0)bean.put("indicadorAccion", Constants.NUMERO_DOS);
				}
				
				if(Constants.MAV_CONRES_UNIDAD_ADM == Integer.parseInt(codigoConRes)){
					List listU =this.unidadesList;
					log.debug("unidadesList >>>>>>> "+listU.size());
					bean.put("listUnidades", listU);
					//actualizar los eliminados 
					Iterator it = listU.iterator();
					int cont =listU.size();
					while(it.hasNext()){
						Map aux = (Map)it.next();
						String indicadorAccion = (String)aux.get("indicadorAccion");						
						if(Constants.NUMERO_DOS.equals(indicadorAccion)){
							aux.put("indicadorAccion",Constants.NUMERO_DOS);
							cont--;
						}												
					}									
					bean.put("numReg", "" +cont);
					bean.put("condicion","" +cont);
					bean.put("condicion1","" +cont );
					bean.put("indicadorModificadoPopup", Constants.NRO_UNO);
					if(cont ==0)bean.put("indicadorAccion", Constants.NUMERO_DOS);
				}
				
				if(Constants.MAV_CONRES_ESTATUS_CLIENTE == Integer.parseInt(codigoConRes)){
					List lista=this.codigoEstatusList;
					log.debug("listEstatus  "+lista.size());
					bean.put("listEstatus", lista);
					//actualizar los eliminados 
					Iterator it = lista.iterator();
					int cont =lista.size();
					while(it.hasNext()){
						Map aux = (Map)it.next();
						String indicadorAccion = (String)aux.get("indicadorAccion");
						if(Constants.NUMERO_DOS.equals(indicadorAccion)){
							cont--;
						}												
					}					
					bean.put("numReg", "" +cont);
					bean.put("condicion","" +cont);
					bean.put("condicion1","" +cont );
					bean.put("indicadorModificadoPopup", Constants.NRO_UNO);
					if(cont ==0)bean.put("indicadorAccion", Constants.NUMERO_DOS);
				}
			}
			
			actualizarListTiposOferta(f);
			limpiarCamposConsideracion(f);
	
		  	f.setSelectedItemsConsideracion(null);
		  	f.setSelectedItemsRestriccion(null);
		  	log.debug("mavConfiguracionConsideracionList size "+list.size());
			this.mavConfiguracionConsideracionList = list;
		
		} catch (Exception e) {
			addError("Error", obtieneMensajeErrorException(e));
		}
		
	}	
	/**
	 * Retorna la condicon seleecioan pueder consideracion orestricion
	 * @param codigo
	 * @param listCombo
	 * @return
	 */
	private Map getCondicion(String codigo, List listCombo) {
		Iterator it = listCombo.iterator();
		while(it.hasNext()){
			Map map=(Map)it.next();
			String aux=String.valueOf(map.get("codigoConRes"));
			if(codigo.equals(aux)) return map; 
				
		}
		return null;
	}

	/**
	 * El registro es valido si no se encunetra en la lista o ya se encuntra eliminado
	 * @param map
	 * @param list
	 * @return
	 */
	private boolean isValido(Map map, List list) {
		Iterator it = list.iterator();
		//String codigoConsideracion = (String)map.get("codigoConsideracion");
		String abrev = (String)map.get("abrev");
		while(it.hasNext()){
			Map mapAux = (Map)it.next();
			//String codigoConsideracionAux = (String)mapAux.get("codigoConsideracion");
			String abrevAux = (String)mapAux.get("abrev");
			String indicadorAccionAux = (String)mapAux.get("indicadorAccion");
			if(abrev.equals(abrevAux) &&
					(indicadorAccionAux.equals(Constants.NUMERO_CERO) || indicadorAccionAux.equals(Constants.NUMERO_UNO)))
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

	public void deleteConsideracion(ActionEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'deleteConsideracion' method");
		}
		
		try {
			MantenimientoMAVConfiguracionForm f = (MantenimientoMAVConfiguracionForm) this.formMantenimiento;
			
			//VALIDACIONES <INICIO>
			if (this.beanRegistroConsideracion == null) {
    			mostrarVentanaAlerta("errors.select.item");
				return;
			}
			
			Map data = (Map) this.beanRegistroConsideracion;  
			String hdIndicadorTipo = MapUtils.getString(data, "indicadorTipo", "");
			
			if(f.isExisteListaRegionZona()) {
				if(hdIndicadorTipo.equals(Constants.MAV_CONRES_LISTA_REGION_ZONA_STRING) && (f.isExisteConsideracionFormula())) {
					mostrarVentanaAlerta("mantenimientoMAVConfiguracionForm.msg.validaFormula3");
    				return;
				}
			}
			
			//VALIDACIONES <FIN>
			
			data.put("indicadorAccion", Constants.NUMERO_DOS);	
			this.dataTableConsideracion = new DataTableModel(this.mavConfiguracionConsideracionList);
			
			actualizarListTiposOferta(f);
			limpiarCamposConsideracion(f);
			
			f.setCodigoConsideracion("");		
			
		} catch (Exception e) {
			addError("Error", obtieneMensajeErrorException(e));
		}
		
	}

	
	/**
	 * Elimina logicamente el Zona
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void editConsideracion(ActionEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'editConsideracion' method");
		}

		try {
			MantenimientoMAVConfiguracionForm f = (MantenimientoMAVConfiguracionForm) this.formMantenimiento;
			this.nombreVentanaPopup="";
			
			//VALIDACIONES <INICIO>
			if (this.beanRegistroConsideracion == null) {
    			mostrarVentanaAlerta("errors.select.item");
				return;
			}
			
			Map data = (Map) this.beanRegistroConsideracion;
			String hdIndicadorTipo = data.get("indicadorTipo").toString();
			
			if(this.editableMantenimiento) { //EDITAR
				if(StringUtils.isEmpty(hdIndicadorTipo) || hdIndicadorTipo.equals("S")){
	  			  mostrarVentanaAlerta("mantenimientoMENPatronMensajeForm.noeditar.tipo.simple");
	  			  return;
	  		    }
			} else {	//CONSULTA
				if(StringUtils.isEmpty(hdIndicadorTipo) || hdIndicadorTipo.equals("S") || hdIndicadorTipo.equals("U") || 
						hdIndicadorTipo.equals("D") || hdIndicadorTipo.equals("M")){
					mostrarVentanaAlerta("mantenimientoMAVConfiguracionForm.nodetalle.tipo.simple");
	      			return;
	      		}
			}
			//VALIDACIONES <FIN>
			this.esBotonInsertar = false;
			
			if(hdIndicadorTipo.equals("L") || hdIndicadorTipo.equals("E")) {
				this.indicadorTipo = "C";
				editPopup(event);
			} else {
			
				Map bean = (Map) this.beanRegistroConsideracion;
				f.setTabSeleccion(Constants.MAV_TAB_CONSIDERACION);		
				
				if (bean != null) {
					log.debug("row bean "+ bean);
					limpiarCamposConsideracion(f);
																
					List list=(List)this.mavConfiguracionConsideracionList;
					String tipo = (String)bean.get("indicadorTipo");
					log.debug("Tipo "+tipo);
					f.setCodigoConsideracion((String)bean.get("codigoConsideracion"));
					
					//SETEA LOS CAMPOS A MOSTRAR DE ACUERDO A LA CONSIDERACION A EDITARSE
					jsCondicionAuxiliar(f, f.getCodigoConsideracion());
					
					if(StringUtils.isEmpty(tipo) || Constants.MAV_TIPO_SIN_CONDICION.equals(tipo)){
						f.setCondicionSimpleUnidades((String)bean.get("numeroUnidades"));
					}
					if(Constants.MAV_TIPO_UNA_CONDICION.equals(tipo)){
					    f.setCondicion1S((String)bean.get("condicion1"));	
					    f.setCondicionUnicoUnidades((String)bean.get("numeroUnidades"));
					}
					if(Constants.MAV_TIPO_UNA_CONDICION_DOBLE.equals(tipo)){
					    f.setCondicion1((String)bean.get("condicion1"));
					    f.setCondicion2((String)bean.get("condicion2"));
					    f.setCondicionDobleUnidades((String)bean.get("numeroUnidades"));
					}
					if(Constants.MAV_TIPO_CONDICIONES_MIXTAS.equals(tipo)){
						if((Constants.MAV_CONRES_POR_FORMULA == Integer.parseInt(f.getCodigoConsideracion())) || 
								(Constants.MAV_CONRES_POR_VARIABLES_VENTA == Integer.parseInt(f.getCodigoConsideracion())) ){
							f.setCondicionFormula((String)bean.get("condicion1"));
							f.setCondicionNroPeriodosFV((String)bean.get("condicion2"));
							
						} else if(Constants.MAV_CONRES_INSCRIPCION_NUEVA_DUPLA == Integer.parseInt(f.getCodigoConsideracion())){
							f.setCondicionPeriodoInicio((String)bean.get("condicion1"));
							f.setCondicionPeriodoFin((String)bean.get("condicion2"));
							
						} else if(Constants.MAV_CONRES_MONTO_FLEXIPAGO == Integer.parseInt(f.getCodigoConsideracion())){
							f.setCondicionMonto((String)bean.get("condicion1"));
							f.setCondicionMonto2((String)bean.get("condicion2"));
							f.setCondicionPeriodoFinanciado((String)bean.get("condicion3"));
							
						} else {
							f.setCondicionMonto((String)bean.get("condicion1"));
							
							String condicionAux = null;
							if((Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_MARCA == Integer.parseInt(f.getCodigoConsideracion())) || 
									(Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_MARCA == Integer.parseInt(f.getCodigoConsideracion()))){
								f.setCondicionMarca((String)bean.get("condicion2"));
							}
							if((Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_NEGOCIO == Integer.parseInt(f.getCodigoConsideracion())) || 
									(Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_NEGOCIO == Integer.parseInt(f.getCodigoConsideracion()))){
								f.setCondicionNegocio((String)bean.get("condicion2"));
							}
							
							if((Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_UNIDAD == Integer.parseInt(f.getCodigoConsideracion())) || 
									(Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_UNIDAD == Integer.parseInt(f.getCodigoConsideracion()))){
								f.setCondicionUnidadNegocio((String)bean.get("condicion2"));
							}
							
							if((Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_CATALOGO == Integer.parseInt(f.getCodigoConsideracion())) || 
									(Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_CATALOGO == Integer.parseInt(f.getCodigoConsideracion()))){
								f.setCondicionCatalogo((String)bean.get("condicion2"));
							}
							
							if((Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO == Integer.parseInt(f.getCodigoConsideracion())) || 
									(Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO == Integer.parseInt(f.getCodigoConsideracion()))){
								
								f.setCondicionPeriodoInicio((String)bean.get("condicion2"));
								f.setCondicionPeriodoFin((String)bean.get("condicion3"));
								f.setCondicionTipoVenta((String)bean.get("condicion4"));
							} else {
								f.setCondicionPeriodoInicio((String)bean.get("condicion3"));
								f.setCondicionPeriodoFin((String)bean.get("condicion4"));
								f.setCondicionTipoVenta((String)bean.get("condicion5"));
							}
						}
						
						f.setCondicionUnidades((String)bean.get("numeroUnidades"));
					}
					
					f.setIndicadorConsideracion(Constants.NUMERO_CERO);
				  	f.setSelectedItemsConsideracion(null);
				  	f.setSelectedItemsRestriccion(null);
					
				}	
				
				actualizarListTiposOferta(f);
			}	

		} catch (Exception e) {
			addError("Error", obtieneMensajeErrorException(e));
		}
		
	}	
	
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
	public void saveConsideracion(ActionEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'saveConsideracion()' method");
		}		

		try {
			MantenimientoMAVConfiguracionForm f = (MantenimientoMAVConfiguracionForm) this.formMantenimiento;	
	       
			List list=this.mavConfiguracionConsideracionList;	
			Map bean = (Map)this.beanRegistroConsideracion;	
			
			String tipo = MapUtils.getString(bean, "indicadorTipo", "");
			log.debug("Tipo "+tipo);
			
			//VALIDACIONES <INICIO>
			if(!isValidoConsRestr(f, "accionConsideracionActualizar", tipo)){
				return;
			}
			//VALIDACIONES <FIN>
			
			if(StringUtils.isEmpty(tipo) || Constants.MAV_TIPO_SIN_CONDICION.equals(tipo)){
				bean.put("numeroUnidades",f.getCondicionSimpleUnidades());
				bean.put("indicadorModificado", Constants.ESTADO_ACTIVO);
			}
			if(Constants.MAV_TIPO_UNA_CONDICION.equals(tipo)){
			  bean.put("condicion1",f.getCondicion1S());
			  bean.put("condicion",f.getCondicion1S());
			  bean.put("numeroUnidades",f.getCondicionUnicoUnidades());
			  bean.put("indicadorModificado", Constants.ESTADO_ACTIVO);
			}
			if(Constants.MAV_TIPO_UNA_CONDICION_DOBLE.equals(tipo)){
				bean.put("condicion1",f.getCondicion1());
				bean.put("condicion2",f.getCondicion2());
				bean.put("condicion",f.getCondicion1()+" , "+f.getCondicion2());
				bean.put("numeroUnidades",f.getCondicionDobleUnidades());
				bean.put("indicadorModificado", Constants.ESTADO_ACTIVO);
			}
			if(Constants.MAV_TIPO_CONDICIONES_MIXTAS.equals(tipo)){
				if((Constants.MAV_CONRES_POR_FORMULA == Integer.parseInt(f.getCodigoConsideracion())) || 
						(Constants.MAV_CONRES_POR_VARIABLES_VENTA == Integer.parseInt(f.getCodigoConsideracion())) ){
					bean.put("condicion", getDesFormula(f.getCondicionFormula()) + " , "+f.getCondicionNroPeriodosFV());
					bean.put("condicion1",f.getCondicionFormula());
					bean.put("condicion2",f.getCondicionNroPeriodosFV());
					
				} else if(Constants.MAV_CONRES_INSCRIPCION_NUEVA_DUPLA == Integer.parseInt(f.getCodigoConsideracion())){
					bean.put("condicion",f.getCondicionPeriodoInicio() + " , "+f.getCondicionPeriodoFin());
					bean.put("condicion1",f.getCondicionPeriodoInicio());
					bean.put("condicion2",f.getCondicionPeriodoFin());
					
				} else if(Constants.MAV_CONRES_MONTO_FLEXIPAGO == Integer.parseInt(f.getCodigoConsideracion())){
					bean.put("condicion",f.getCondicionMonto() + " , "+f.getCondicionMonto2()
																+ " , "+f.getCondicionPeriodoFinanciado());
					bean.put("condicion1",f.getCondicionMonto());
					bean.put("condicion2",f.getCondicionMonto2());
					bean.put("condicion3",f.getCondicionPeriodoFinanciado());
	
				} else {
					String condicionAux = null;
					String descripcionAux = "";
					
					String condicionAuxTipoVenta = f.getCondicionTipoVenta();
					String descripcionAuxTipoVenta = this.getDesTipoVenta(condicionAuxTipoVenta);
					if((Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_MARCA == Integer.parseInt(f.getCodigoConsideracion())) || 
							(Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_MARCA == Integer.parseInt(f.getCodigoConsideracion()))){
						condicionAux = f.getCondicionMarca();
						descripcionAux = getDesMarcaProducto(condicionAux);
					}
					if((Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_NEGOCIO == Integer.parseInt(f.getCodigoConsideracion())) || 
							(Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_NEGOCIO == Integer.parseInt(f.getCodigoConsideracion()))){
						condicionAux = f.getCondicionNegocio();
						descripcionAux = getDesNegocio(condicionAux);
					}
					
					if((Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_UNIDAD == Integer.parseInt(f.getCodigoConsideracion())) || 
							(Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_UNIDAD == Integer.parseInt(f.getCodigoConsideracion()))){
						condicionAux = f.getCondicionUnidadNegocio();
						descripcionAux = getDesUnidadNegocio(condicionAux);
					}
					
					if((Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_CATALOGO == Integer.parseInt(f.getCodigoConsideracion())) || 
							(Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_CATALOGO == Integer.parseInt(f.getCodigoConsideracion()))){
						condicionAux = f.getCondicionCatalogo();
						descripcionAux = getDesCatalogo(condicionAux);
					}
					
					if((Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO == Integer.parseInt(f.getCodigoConsideracion())) || 
							(Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO == Integer.parseInt(f.getCodigoConsideracion()))){
						bean.put("condicion",f.getCondicionMonto() + " , "+f.getCondicionPeriodoInicio() + " , "+
								f.getCondicionPeriodoFin() + " , " + descripcionAuxTipoVenta);
						bean.put("condicion1",f.getCondicionMonto());
						bean.put("condicion2",f.getCondicionPeriodoInicio());
						bean.put("condicion3",f.getCondicionPeriodoFin());
						bean.put("condicion4",f.getCondicionTipoVenta());
					} else {
						bean.put("condicion",f.getCondicionMonto() + " , "+ descripcionAux + " , "+f.getCondicionPeriodoInicio() + " , "+
								f.getCondicionPeriodoFin() + " , " + descripcionAuxTipoVenta);
						bean.put("condicion1",f.getCondicionMonto());
						bean.put("condicion2",condicionAux);
						bean.put("condicion3",f.getCondicionPeriodoInicio());
						bean.put("condicion4",f.getCondicionPeriodoFin());
						bean.put("condicion5",f.getCondicionTipoVenta());
					}
					
				}
				
				bean.put("numeroUnidades",f.getCondicionUnidades());
				bean.put("indicadorModificado", Constants.ESTADO_ACTIVO);
			}
		    f.setIndicadorConsideracion(Constants.NUMERO_UNO);				
			
		    actualizarListTiposOferta(f);
		    limpiarCamposConsideracion(f);
		    
		    f.setCodigoConsideracion("");
		    
		} catch (Exception e) {
			addError("Error", obtieneMensajeErrorException(e));
		} 
		
	}
	/***FIN TAB CONSIDERACION **********/
	
	/*****INICIO TAB RESTRICCION *****/	
	/**
	 * inserta el Zona de una poliza
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void insertRestriccion(String modo) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'insertRestriccion' method dd");
		}

		try {
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			MantenimientoMAVConfiguracionForm f = (MantenimientoMAVConfiguracionForm) this.formMantenimiento;
			MantenimientoMAVConfiguracionService service = (MantenimientoMAVConfiguracionService) getBean("spusicc.mantenimientoMAVConfiguracionService");
			f.setTabSeleccion(Constants.MAV_TAB_RESTRICCION);
			
			this.nombreVentanaPopup="";
			
			//VALIDACIONES <INICIO>
			if(StringUtils.isEmpty(f.getCodigoRestriccion())){
				mostrarVentanaAlerta("mantenimientoMENPatronMensajeForm.mensaje.seleccionarRestriccion");
				return;
			}

			String indicadorTipoAux=getConsRest(f.getCodigoRestriccion(),"1");
			
			int sizeConsideracionList = obtenerTotalConsideracionesIngresadas(); 
			int sizeRestriccionList = obtenerTotalRestriccionesIngresadas(); 
			boolean hdConsideracionTodos = existeConsideracionIngresado("1"); 
			boolean hdCRLista = existeConsideracionListaIngresado();
			
			if((sizeConsideracionList>=1 || sizeConsideracionList>=1) && !hdConsideracionTodos) {
				
				String dirigidoAux="";
				for(int i=0; i<this.mavConfiguracionConsideracionList.size(); i++) {
					Map mapRestriccion = (Map)this.mavConfiguracionConsideracionList.get(i);
					
					String indicadorAccion = mapRestriccion.get("indicadorAccion").toString();
					dirigidoAux = mapRestriccion.get("dirigido").toString();
						
					if(!indicadorAccion.equals("2")) {
						break;
					}	
				}
				
				String dirigidoActual = f.getCodigoTipoCliente().equals(Constants.MAE_TIPO_CLIENTE_CONSULTORA)?"C":"G";
				
				if(!dirigidoAux.equals(dirigidoActual)) {
					if(dirigidoAux.equals("C")) {
						mostrarVentanaAlerta("mantenimientoMAVConfiguracionForm.msg.validarActividadConsultoras");
					} else {
						mostrarVentanaAlerta("mantenimientoMAVConfiguracionForm.msg.validarActividadGerentes");
					}	
					return;
				}
			}

			if(hdConsideracionTodos) {
				mostrarVentanaAlerta("mantenimientoMAVConfiguracionForm.msg.validarTodos2");
				return;
			}
			if(indicadorTipoAux.equals("E") && sizeRestriccionList>1) {
				mostrarVentanaAlerta("mantenimientoMAVConfiguracionForm.msg.validarLista");
				return;
			}
			if(f.getIndicadorUnidad().equals("S") && hdCRLista) {
				mostrarVentanaAlerta("mantenimientoMAVConfiguracionForm.msg.validarLista1");
				return;
			}
			
			if(!isValidoConsRestr(f, "accionRestriccionInsertar", indicadorTipoAux)){
				 return;
			 } 
			
			if(indicadorTipoAux.equals("L") || indicadorTipoAux.equals("E")) {
				String validarConsMAV = getValidarConsRestMAV(f.getCodigoRestriccion(), "R");
				if(StringUtils.isNotEmpty(validarConsMAV)) {
					mostrarVentanaAlerta("mantenimientoMAVConfiguracionForm.existe.consideracion.registro");
					return;
				}	
			}
			//VALIDACIONES <FIN>

			this.esBotonInsertar = true;
			
			if((indicadorTipoAux.equals("L") || indicadorTipoAux.equals("E")) && modo.equals("insertRestriccion")) {
				this.indicadorTipo = "R";
				viewPopup();
			} else {				
					
				List listComboConsideracion= service.getRestriccion(null);
				List list = this.mavConfiguracionRestriccionList;
				if(list==null) list = new ArrayList();
				
				Map condicion = getCondicion(f.getCodigoRestriccion(),listComboConsideracion);
				String tipo = (String)condicion.get("indTipo");
						
				Map bean= new HashMap();
				bean.put("codigoPais", pais.getCodigo());
				bean.put("correlativoMAV", f.getCorrelativo());
				bean.put("codigoPeriodo", f.getCodigoPeriodo());
				bean.put("codigoRestriccion", f.getCodigoRestriccion());
				bean.put("indicadorTipo",tipo );
				bean.put("descripcion",(String)condicion.get("descripcionConRes") );
				bean.put("abrev", getAbrevConRes(f.getCodigoRestriccion(),listComboConsideracion));
				bean.put("indicadorAccion",Constants.NUMERO_CERO);
				bean.put("condicion","" );
				bean.put("numReg","0" );
				
				if(StringUtils.isEmpty(tipo) || Constants.MAV_TIPO_SIN_CONDICION.equals(tipo)){
					bean.put("numeroUnidades",f.getCondicionSimpleRestUnidades());
					bean.put("numReg","1" );
				}
				if(Constants.MAV_TIPO_UNA_CONDICION.equals(tipo)){
					bean.put("condicion1",f.getCondicionRest1S());
					bean.put("condicion",f.getCondicionRest1S());
					bean.put("numeroUnidades",f.getCondicionUnicoRestUnidades());
					bean.put("numReg","1" );
				}
				if(Constants.MAV_TIPO_UNA_CONDICION_DOBLE.equals(tipo)){
					bean.put("condicion",f.getCondicionRest1() + " , "+f.getCondicionRest2() );
					bean.put("condicion1",f.getCondicionRest1());
					bean.put("condicion2",f.getCondicionRest2());
					bean.put("numeroUnidades",f.getCondicionDobleRestUnidades());
					bean.put("numReg","1" );
				}
				if(Constants.MAV_TIPO_CONDICIONES_MIXTAS.equals(tipo)){
					if(Constants.MAV_CONRES_INSCRIPCION_NUEVA_DUPLA_REST == Integer.parseInt(f.getCodigoRestriccion())){
						bean.put("condicion",f.getCondicionRestPeriodoInicio() + " , "+f.getCondicionRestPeriodoFin());
						bean.put("condicion1",f.getCondicionRestPeriodoInicio());
						bean.put("condicion2",f.getCondicionRestPeriodoFin());
						
					} else {
						String condicionAux = null;
						String descripcionAux = "";
						
						String condicionAuxTipoVenta = f.getCondicionRestTipoVenta();
						String descripcionAuxTipoVenta = this.getDesTipoVenta(condicionAuxTipoVenta);
						if((Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_MARCA_REST == Integer.parseInt(f.getCodigoRestriccion())) || 
								(Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_MARCA_REST == Integer.parseInt(f.getCodigoRestriccion()))){
							condicionAux = f.getCondicionRestMarca();
							descripcionAux = getDesMarcaProducto(condicionAux);
						}
						if((Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_NEGOCIO_REST == Integer.parseInt(f.getCodigoRestriccion())) || 
								(Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_NEGOCIO_REST == Integer.parseInt(f.getCodigoRestriccion()))){
							condicionAux = f.getCondicionRestNegocio();
							descripcionAux = getDesNegocio(condicionAux);
						}
						
						if((Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_UNIDAD_REST == Integer.parseInt(f.getCodigoRestriccion())) || 
								(Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_UNIDAD_REST == Integer.parseInt(f.getCodigoRestriccion()))){
							condicionAux = f.getCondicionRestUnidadNegocio();
							descripcionAux = getDesUnidadNegocio(condicionAux);
						}
						
						if((Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_CATALOGO_REST == Integer.parseInt(f.getCodigoRestriccion())) || 
								(Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_CATALOGO_REST == Integer.parseInt(f.getCodigoRestriccion()))){
							condicionAux = f.getCondicionRestCatalogo();
							descripcionAux = getDesCatalogo(condicionAux);
						}
						
						if((Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_REST == Integer.parseInt(f.getCodigoRestriccion())) || 
								(Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_REST == Integer.parseInt(f.getCodigoRestriccion()))){
							bean.put("condicion",f.getCondicionRestMonto() + " , "+f.getCondicionRestPeriodoInicio() + " , "+
									f.getCondicionRestPeriodoFin()  + " , " + descripcionAuxTipoVenta);
							
							bean.put("condicion1",f.getCondicionRestMonto());
							bean.put("condicion2",f.getCondicionRestPeriodoInicio());
							bean.put("condicion3",f.getCondicionRestPeriodoFin());
							bean.put("condicion4",f.getCondicionRestTipoVenta());
						} else {
							bean.put("condicion",f.getCondicionRestMonto() + " , "+ descripcionAux + " , "+f.getCondicionRestPeriodoInicio() + " , "+
									f.getCondicionRestPeriodoFin()  + " , " + descripcionAuxTipoVenta);
							
							bean.put("condicion1",f.getCondicionRestMonto());
							bean.put("condicion2",condicionAux);
							bean.put("condicion3",f.getCondicionRestPeriodoInicio());
							bean.put("condicion4",f.getCondicionRestPeriodoFin());
							bean.put("condicion5",f.getCondicionRestTipoVenta());
						}
					}
					
					bean.put("numeroUnidades",f.getCondicionRestUnidades());
					bean.put("numReg","1" );
				}	
				if(Constants.MAV_TIPO_LISTA_CONDICION.equals(tipo) ||
						Constants.MAV_TIPO_LISTA_EXTERNA_CONDICION.equals(tipo)){
					log.debug(" f.getCodigoRestriccion() >>>  "+f.getCodigoRestriccion());
		
					if(Constants.MAV_CONRES_LISTA_CONSU_REST == Integer.parseInt(f.getCodigoRestriccion())){
						List consultoraList=this.consultoraListR;
						log.debug("consultoraListR >>>>>>> "+consultoraList.size());
						bean.put("consultoraListR", consultoraList);
						bean.put("numReg", "" +consultoraList.size());
						bean.put("condicion","" +consultoraList.size());
						bean.put("condicion1","" +consultoraList.size() );
						bean.put("tipoConsideracionLista",Constants.MAV_TIPO_LISTA_CONDICION );
					}	
				
					if(Constants.MAV_CONRES_LISTA_REGION_ZONA_REST == Integer.parseInt(f.getCodigoRestriccion())){
						List regionesList=this.regionesListR;
						log.debug("regionesListR >>>>>>> "+regionesList.size());
						bean.put("regionesListR", regionesList);
						bean.put("numReg", "" +regionesList.size());
						bean.put("condicion","" +regionesList.size());
						bean.put("condicion1","" +regionesList.size() );
						bean.put("tipoConsideracionLista",Constants.MAV_TIPO_LISTA_CONDICION );
					}
					
					if(Constants.MAV_CONRES_CLASIFICACIONES_CLIENTE_REST == Integer.parseInt(f.getCodigoRestriccion())){
						List listC =this.clasificacionesListR;
						log.debug("listClasificaciones >>>>>>> "+listC.size());
						bean.put("listClasificacionesR", listC);
						//actualizar los eliminados 
						Iterator it = listC.iterator();
						int cont =listC.size();
						while(it.hasNext()){
							Map aux = (Map)it.next();
							String indicadorAccion = (String)aux.get("indicadorAccion");
							if(Constants.NUMERO_DOS.equals(indicadorAccion)){
								cont--;
							}												
						}							
						bean.put("numReg", "" +cont);
						bean.put("condicion","" +cont);
						bean.put("condicion1","" +cont );
						bean.put("tipoConsideracionLista",Constants.MAV_TIPO_LISTA_CONDICION );
						if(cont ==0)bean.put("indicadorAccion", Constants.NUMERO_DOS);
					}
					
					if(Constants.MAV_CONRES_UNIDAD_ADM_REST == Integer.parseInt(f.getCodigoRestriccion())){
						List listU =this.unidadesListR;
						log.debug("unidadesListR >>>>>>> "+listU.size());
						bean.put("listUnidadesR", listU);
						//actualizar los eliminados 
						Iterator it = listU.iterator();
						int cont =listU.size();
						while(it.hasNext()){
							Map aux = (Map)it.next();
							String indicadorAccion = (String)aux.get("indicadorAccion");
							if(Constants.NUMERO_DOS.equals(indicadorAccion)){
								cont--;
							}												
						}						
						bean.put("numReg", "" +cont);
						bean.put("condicion","" +cont);
						bean.put("condicion1","" +cont );
						bean.put("tipoConsideracionLista",Constants.MAV_TIPO_LISTA_CONDICION );
						if(cont ==0)bean.put("indicadorAccion", Constants.NUMERO_DOS);
					}			
					
					if(Constants.MAV_CONRES_ESTATUS_CLIENTE_REST == Integer.parseInt(f.getCodigoRestriccion())){
						List lista=this.codigoEstatusListR;
						log.debug("listEstatus Rest "+lista.size());				
						bean.put("listEstatusR", lista);
						//actualizar los eliminados 
						Iterator it = lista.iterator();
						int cont =lista.size();
						while(it.hasNext()){
							Map aux = (Map)it.next();
							String indicadorAccion = (String)aux.get("indicadorAccion");
							if(Constants.NUMERO_DOS.equals(indicadorAccion)){
								cont--;
							}												
						}					
						bean.put("numReg", "" +cont);
						bean.put("condicion","" +cont);
						bean.put("condicion1","" +cont );
						bean.put("tipoConsideracionLista",Constants.MAV_TIPO_LISTA_CONDICION );
						if(cont ==0)bean.put("indicadorAccion", Constants.NUMERO_DOS);
					}
				}					
				
				List listC =(List) this.mavConfiguracionConsideracionList;
				bean.put("codigoConsideracion", f.getCodigoRestriccion());
				log.debug("validando listas ");
				if(isValidoRest(bean,list) && isValido(bean,listC)){//es registro valido cuando no se encuntra en la lista o se encuentra como eliminado
					  bean.put("codigoConsideracion",null);
					  list.add(bean);
					//service.saveTemporalZona(bean);
				}		
				else{
	
				  	f.setSelectedItemsConsideracion(null);
				  	f.setSelectedItemsRestriccion(null);
				  	
				  	throw new Exception(this.getResourceMessage("mantenimientoMAVConfiguracionForm.existe.consideracion.registro"));
				}
				
				actualizarListTiposOferta(f);
				limpiarCamposRestriccion(f);
				
				f.setCodigoRestriccion("");
				
			  	f.setSelectedItemsConsideracion(null);
			  	f.setSelectedItemsRestriccion(null);
				this.mavConfiguracionRestriccionList = list;
				this.dataTableRestriccion = new DataTableModel(list);
			}	
			
		} catch (Exception e) {
			addError("Error", obtieneMensajeErrorException(e));
		}

	}
	

	/**
	 * inserta el Zona de una poliza
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void updateRestriccion(ActionEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'updateRestriccion' method dd");
		}
		
		try {

			MantenimientoMAVConfiguracionForm f = (MantenimientoMAVConfiguracionForm) this.formMantenimiento;
			f.setTabSeleccion(Constants.MAV_TAB_RESTRICCION);
			//
			//					
			List list =this.mavConfiguracionRestriccionList;
			if(list==null) list = new ArrayList();
						
			int index = 0; //<OJO>Integer.parseInt(idPopup);
			Map bean = (Map)list.get(index);
			String codigoConRes = (String)bean.get("codigoRestriccion");
			String tipo = (String)bean.get("indicadorTipo");
						
			bean.put("codigoPeriodo", f.getCodigoPeriodo());
			//bean.put("indicadorAccion",Constants.NUMERO_UNO);
			bean.put("condicion","" );
			bean.put("numReg","0" );
				
				
			if(Constants.MAV_TIPO_LISTA_CONDICION.equals(tipo)
					|| Constants.MAV_TIPO_LISTA_EXTERNA_CONDICION.equals(tipo)){
				log.debug(" codigoConRes() >>>  "+codigoConRes);
	
				if(Constants.MAV_CONRES_LISTA_CONSU_REST == Integer.parseInt(codigoConRes)){
					List consultoraList=(List)this.consultoraListR;
					log.debug("consultoraListR >>>>>>> "+consultoraList.size());
					bean.put("consultoraListR", consultoraList);
					bean.put("numReg", "" +consultoraList.size());
					bean.put("condicion","" +consultoraList.size());
					bean.put("condicion1","" +consultoraList.size() );
					bean.put("tipoConsideracionLista",Constants.MAV_TIPO_LISTA_CONDICION );
					bean.put("indicadorModificadoPopup", Constants.NRO_UNO);
				}
				
				if(Constants.MAV_CONRES_LISTA_REGION_ZONA_REST == Integer.parseInt(codigoConRes)){
					List regionesList=this.regionesListR;
					log.debug("regionesListR >>>>>>> "+regionesList.size());
					bean.put("regionesListR", regionesList);
					bean.put("numReg", "" +regionesList.size());
					bean.put("condicion","" +regionesList.size());
					bean.put("condicion1","" +regionesList.size() );
					bean.put("tipoConsideracionLista",Constants.MAV_TIPO_LISTA_CONDICION );
					bean.put("indicadorModificadoPopup", Constants.NRO_UNO);
				}
				
				if(Constants.MAV_CONRES_CLASIFICACIONES_CLIENTE_REST == Integer.parseInt(codigoConRes)){
					List listC =this.clasificacionesListR;
					log.debug("listClasificaciones >>>>>>> "+listC.size());
					bean.put("listClasificacionesR", listC);
					//actualizar los eliminados 
					Iterator it = listC.iterator();
					int cont =listC.size();
					while(it.hasNext()){
						Map aux = (Map)it.next();
						String indicadorAccion = (String)aux.get("indicadorAccion");
						if(Constants.NUMERO_DOS.equals(indicadorAccion)){
							cont--;
						}												
					}					
					bean.put("numReg", "" +cont);
					bean.put("condicion","" +cont);
					bean.put("condicion1","" +cont );
					bean.put("indicadorModificadoPopup", Constants.NRO_UNO);
					if(cont ==0)bean.put("indicadorAccion", Constants.NUMERO_DOS);
				}
				
				if(Constants.MAV_CONRES_UNIDAD_ADM_REST == Integer.parseInt(codigoConRes)){
					List listU =this.unidadesListR;
					log.debug("unidadesListR >>>>>>> "+listU.size());
					bean.put("listUnidadesR", listU);
					//actualizar los eliminados 
					Iterator it = listU.iterator();
					int cont =listU.size();
					while(it.hasNext()){
						Map aux = (Map)it.next();
						String indicadorAccion = (String)aux.get("indicadorAccion");
						if(Constants.NUMERO_DOS.equals(indicadorAccion)){
							cont--;
						}												
					}										
					bean.put("numReg", "" +cont);
					bean.put("condicion","" +cont);
					bean.put("condicion1","" +cont );
					bean.put("indicadorModificadoPopup", Constants.NRO_UNO);
					if(cont ==0)bean.put("indicadorAccion", Constants.NUMERO_DOS);
				}		
				
				if(Constants.MAV_CONRES_ESTATUS_CLIENTE_REST == Integer.parseInt(codigoConRes)){
					List lista=this.codigoEstatusListR;
					log.debug("listEstatus Rest "+lista.size());
					bean.put("listEstatusR", lista);
					//actualizar los eliminados 
					Iterator it = lista.iterator();
					int cont =lista.size();
					while(it.hasNext()){
						Map aux = (Map)it.next();
						String indicadorAccion = (String)aux.get("indicadorAccion");
						if(Constants.NUMERO_DOS.equals(indicadorAccion)){
							cont--;
						}												
					}
					//no contar los eliminados					
					bean.put("numReg", "" +cont);
					bean.put("condicion","" +cont);
					bean.put("condicion1","" +cont);
					bean.put("indicadorModificadoPopup", Constants.NRO_UNO);
					if(cont ==0)bean.put("indicadorAccion", Constants.NUMERO_DOS);
				}
			}					
			
			actualizarListTiposOferta(f);
			limpiarCamposRestriccion(f);
			
		  	f.setSelectedItemsConsideracion(null);
		  	f.setSelectedItemsRestriccion(null);
			this.mavConfiguracionRestriccionList = list;
			
		} catch (Exception e) {
			addError("Error", obtieneMensajeErrorException(e));
		}
	
	}	
	
	/**
	 * @param codigoRestriccion
	 * @param listComboConsideracion
	 * @return
	 */
	private String getAbrevConRes(String codigo,
			List listCombo) {
		Iterator it = listCombo.iterator();
		while(it.hasNext()){
			Map map = (Map)it.next();
			String abrev = (String)map.get("abrevConRes");
			String codigoConRes =  String.valueOf(map.get("codigoConRes"));
			if(codigo.equals(codigoConRes)){
			  return abrev;	
			}
		}
		return "";
	}

	/**
	 * El registro es valido si no se encunetra en la lista o ya se encuntra eliminado
	 * @param map
	 * @param list
	 * @return
	 */
	private boolean isValidoRest(Map map, List list) {
		Iterator it = list.iterator();
		//String codigoRestriccion = (String)map.get("codigoRestriccion");	
		String abrev = (String)map.get("abrev");
		while(it.hasNext()){
			Map mapAux = (Map)it.next();
			//String codigoRestriccionAux = (String)mapAux.get("codigoRestriccion");
			String abrevAux = (String)mapAux.get("abrev");
			String indicadorAccionAux = (String)mapAux.get("indicadorAccion");
			if(abrev.equals(abrevAux) &&
					(indicadorAccionAux.equals(Constants.NUMERO_CERO) || indicadorAccionAux.equals(Constants.NUMERO_UNO)))
					 return false;
		}
		return true;
	}

	/**
	 * Elimina logicamente el Zona
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void deleteRestriccion(ActionEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'deleteRestriccion' method");
		}

		try {
			MantenimientoMAVConfiguracionForm f = (MantenimientoMAVConfiguracionForm) this.formMantenimiento;
			
			//VALIDACIONES <INICIO>
			if (this.beanRegistroRestriccion == null) {
    			mostrarVentanaAlerta("errors.select.item");
				return;
			}
			
			//VALIDACIONES <FIN>
			
			Map data = (Map) this.beanRegistroRestriccion;  
			data.put("indicadorAccion", Constants.NUMERO_DOS);	
			this.dataTableRestriccion = new DataTableModel(this.mavConfiguracionRestriccionList);
			
			actualizarListTiposOferta(f);
			limpiarCamposRestriccion(f);
				
		} catch (Exception e) {
			addError("Error", obtieneMensajeErrorException(e));
		}	
		
	}

	
	/**
	 * Elimina logicamente el Zona
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void editRestriccion(ActionEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'editRestriccion' method");
		}

		try {
			MantenimientoMAVConfiguracionForm f = (MantenimientoMAVConfiguracionForm) this.formMantenimiento;
			this.nombreVentanaPopup="";
			
			this.esBotonInsertar = false;
			
			//VALIDACIONES <INICIO>
			if (this.beanRegistroRestriccion == null) {
    			mostrarVentanaAlerta("errors.select.item");
				return;
			}
			
			Map data = (Map) this.beanRegistroRestriccion;
			String hdIndicadorTipo = data.get("indicadorTipo").toString();
			
			if(this.editableMantenimiento) { //EDITAR
				if(StringUtils.isEmpty(hdIndicadorTipo) || hdIndicadorTipo.equals("S")){
	  			  mostrarVentanaAlerta("mantenimientoMENPatronMensajeForm.noeditar.tipo.simple");
	  			  return;
	  		    }
			} else {	//CONSULTA
				if(hdIndicadorTipo.equals("S") || hdIndicadorTipo.equals("U") || hdIndicadorTipo.equals("D")){
					mostrarVentanaAlerta("mantenimientoMAVConfiguracionForm.nodetalle.tipo.simple");
	      			return;
	      		}
			}
			//VALIDACIONES <FIN>
			
			this.esBotonInsertar = false;
			
			if(hdIndicadorTipo.equals("L") || hdIndicadorTipo.equals("E")) {
				this.indicadorTipo = "R";
				editPopup(event);
			} else {
				Map bean = (Map) this.beanRegistroRestriccion;
				f.setTabSeleccion(Constants.MAV_TAB_CONSIDERACION);	
			
				if (bean != null) {
					log.debug("row bean "+ bean);
					limpiarCamposRestriccion(f);
																
					List list=(List)this.mavConfiguracionRestriccionList;
					String tipo = (String)bean.get("indicadorTipo");
					log.debug("Tipo "+tipo);
					f.setCodigoRestriccion((String)bean.get("codigoRestriccion"));
					
					//SETEA LOS CAMPOS A MOSTRAR DE ACUERDO A LA RESTRICCION A EDITARSE
					jsRestriccionAuxiliar(f, f.getCodigoRestriccion());
							
					if(StringUtils.isEmpty(tipo) || Constants.MAV_TIPO_SIN_CONDICION.equals(tipo)){
						f.setCondicionSimpleRestUnidades((String)bean.get("numeroUnidades"));
					}
					if(Constants.MAV_TIPO_UNA_CONDICION.equals(tipo)){
					  f.setCondicionRest1S((String)bean.get("condicion1"));
					  f.setCondicionUnicoRestUnidades((String)bean.get("numeroUnidades"));
					}
					if(Constants.MAV_TIPO_UNA_CONDICION_DOBLE.equals(tipo)){
						  f.setCondicionRest1((String)bean.get("condicion1"));
						  f.setCondicionRest2((String)bean.get("condicion2"));
						  f.setCondicionDobleRestUnidades((String)bean.get("numeroUnidades"));
					}
					if(Constants.MAV_TIPO_CONDICIONES_MIXTAS.equals(tipo)){
						if(Constants.MAV_CONRES_INSCRIPCION_NUEVA_DUPLA_REST == Integer.parseInt(f.getCodigoRestriccion())){
							f.setCondicionRestPeriodoInicio((String)bean.get("condicion1"));
							f.setCondicionRestPeriodoFin((String)bean.get("condicion2"));
							
						} else {
							f.setCondicionRestMonto((String)bean.get("condicion1"));
							
							String condicionAux = null;
							if((Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_MARCA_REST == Integer.parseInt(f.getCodigoRestriccion())) || 
									(Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_MARCA_REST == Integer.parseInt(f.getCodigoRestriccion()))){
								f.setCondicionRestMarca((String)bean.get("condicion2"));
							}
							if((Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_NEGOCIO_REST == Integer.parseInt(f.getCodigoRestriccion())) || 
									(Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_NEGOCIO_REST == Integer.parseInt(f.getCodigoRestriccion()))){
								f.setCondicionRestNegocio((String)bean.get("condicion2"));
							}
							
							if((Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_UNIDAD_REST == Integer.parseInt(f.getCodigoRestriccion())) || 
									(Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_UNIDAD_REST == Integer.parseInt(f.getCodigoRestriccion()))){
								f.setCondicionRestUnidadNegocio((String)bean.get("condicion2"));
							}
							
							if((Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_CATALOGO_REST == Integer.parseInt(f.getCodigoRestriccion())) || 
									(Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_CATALOGO_REST == Integer.parseInt(f.getCodigoRestriccion()))){
								f.setCondicionRestCatalogo((String)bean.get("condicion2"));
							}
							
							if((Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_REST == Integer.parseInt(f.getCodigoRestriccion())) || 
									(Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_REST == Integer.parseInt(f.getCodigoRestriccion()))){
								
								f.setCondicionRestPeriodoInicio((String)bean.get("condicion2"));
								f.setCondicionRestPeriodoFin((String)bean.get("condicion3"));
								f.setCondicionRestTipoVenta((String)bean.get("condicion4"));
							} else {
								f.setCondicionRestPeriodoInicio((String)bean.get("condicion3"));
								f.setCondicionRestPeriodoFin((String)bean.get("condicion4"));
								f.setCondicionRestTipoVenta((String)bean.get("condicion5"));
							}
						}
						
						f.setCondicionRestUnidades((String)bean.get("numeroUnidades"));
					}
					
					f.setIndicadorRestriccion(Constants.NUMERO_CERO);
					f.setSelectedItemsConsideracion(null);
					f.setSelectedItemsRestriccion(null);
						
				}
				actualizarListTiposOferta(f);
			}
			
		} catch (Exception e) {
			addError("Error", obtieneMensajeErrorException(e));
		}
		
	}	
	
	/**
	 * Actualiza un Descuento d ela poliza
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void saveRestriccion(ActionEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'saveRestriccion' method");
		}		

		try {
			MantenimientoMAVConfiguracionForm f = (MantenimientoMAVConfiguracionForm) this.formMantenimiento;	
		       
			List list=this.mavConfiguracionRestriccionList;	
			Map bean = (Map)this.beanRegistroRestriccion;	
			
			String tipo = MapUtils.getString(bean, "indicadorTipo", "");
			log.debug("Tipo "+tipo);
			
			//VALIDACIONES <INICIO>
			if(!isValidoConsRestr(f, "accionRestriccionActualizar", tipo)){
				return;
			}
			//VALIDACIONES <FIN>
			
			if(StringUtils.isEmpty(tipo) || Constants.MAV_TIPO_SIN_CONDICION.equals(tipo)){
				bean.put("numeroUnidades",f.getCondicionSimpleRestUnidades());
				bean.put("indicadorModificado", Constants.ESTADO_ACTIVO);
			}
			if(Constants.MAV_TIPO_UNA_CONDICION.equals(tipo)){
			  bean.put("condicion1",f.getCondicionRest1S());
			  bean.put("condicion",f.getCondicionRest1S());
			  bean.put("numeroUnidades",f.getCondicionUnicoRestUnidades());
			  bean.put("indicadorModificado", Constants.ESTADO_ACTIVO);		  
			}
			if(Constants.MAV_TIPO_UNA_CONDICION_DOBLE.equals(tipo)){
				bean.put("condicion1",f.getCondicionRest1());
				bean.put("condicion2",f.getCondicionRest2());
				bean.put("condicion",f.getCondicionRest1()+" , "+f.getCondicionRest2());
				bean.put("numeroUnidades",f.getCondicionDobleRestUnidades());
				bean.put("indicadorModificado", Constants.ESTADO_ACTIVO);			
			}
			if(Constants.MAV_TIPO_CONDICIONES_MIXTAS.equals(tipo)){
				if(Constants.MAV_CONRES_INSCRIPCION_NUEVA_DUPLA_REST == Integer.parseInt(f.getCodigoRestriccion())){
					bean.put("condicion",f.getCondicionRestPeriodoInicio() + " , "+f.getCondicionRestPeriodoFin());
					bean.put("condicion1",f.getCondicionRestPeriodoInicio());
					bean.put("condicion2",f.getCondicionRestPeriodoFin());
					
				} else {
					String condicionAux = null;
					String descripcionAux = "";
					String condicionAuxTipoVenta = f.getCondicionRestTipoVenta();
					String descripcionAuxTipoVenta = this.getDesTipoVenta(condicionAuxTipoVenta);
					if((Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_MARCA_REST == Integer.parseInt(f.getCodigoRestriccion())) || 
							(Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_MARCA_REST == Integer.parseInt(f.getCodigoRestriccion()))){
						condicionAux = f.getCondicionRestMarca();
						descripcionAux = getDesMarcaProducto(condicionAux);
					}
					if((Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_NEGOCIO_REST == Integer.parseInt(f.getCodigoRestriccion())) || 
							(Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_NEGOCIO_REST == Integer.parseInt(f.getCodigoRestriccion()))){
						condicionAux = f.getCondicionRestNegocio();
						descripcionAux = getDesNegocio(condicionAux);
					}
					
					if((Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_UNIDAD_REST == Integer.parseInt(f.getCodigoRestriccion())) || 
							(Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_UNIDAD_REST == Integer.parseInt(f.getCodigoRestriccion()))){
						condicionAux = f.getCondicionRestUnidadNegocio();
						descripcionAux = getDesUnidadNegocio(condicionAux);
					}
					
					if((Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_CATALOGO_REST == Integer.parseInt(f.getCodigoRestriccion())) || 
							(Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_CATALOGO_REST == Integer.parseInt(f.getCodigoRestriccion()))){
						condicionAux = f.getCondicionRestCatalogo();
						descripcionAux = getDesCatalogo(condicionAux);
					}
					
					if((Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_REST == Integer.parseInt(f.getCodigoRestriccion())) || 
							(Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_REST == Integer.parseInt(f.getCodigoRestriccion()))){
						bean.put("condicion",f.getCondicionRestMonto() + " , "+f.getCondicionRestPeriodoInicio() + " , "+
								f.getCondicionRestPeriodoFin() + " , " + descripcionAuxTipoVenta);
						
						bean.put("condicion1",f.getCondicionRestMonto());
						bean.put("condicion2",f.getCondicionRestPeriodoInicio());
						bean.put("condicion3",f.getCondicionRestPeriodoFin());
						bean.put("condicion4",f.getCondicionRestTipoVenta());
	
					} else {
						bean.put("condicion",f.getCondicionRestMonto() + " , "+ descripcionAux + " , "+f.getCondicionRestPeriodoInicio() + " , "+
								f.getCondicionRestPeriodoFin() + " , " + descripcionAuxTipoVenta);
	
						bean.put("condicion1",f.getCondicionRestMonto());
						bean.put("condicion2",condicionAux);
						bean.put("condicion3",f.getCondicionRestPeriodoInicio());
						bean.put("condicion4",f.getCondicionRestPeriodoFin());
						bean.put("condicion5",f.getCondicionRestTipoVenta());
					}
				}
				
				bean.put("numeroUnidades",f.getCondicionRestUnidades());
				bean.put("indicadorModificado", Constants.ESTADO_ACTIVO);
			}
			
		    f.setIndicadorRestriccion(Constants.NUMERO_UNO);				
			
		    actualizarListTiposOferta(f);
		    limpiarCamposRestriccion(f);
		    
		    f.setCodigoRestriccion("");

		} catch (Exception e) {
			addError("Error", obtieneMensajeErrorException(e));
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
	public void refreshRest(ActionEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'refreshRest' method");
		}		
		
		try {
	    
			MantenimientoMAVConfiguracionForm f = (MantenimientoMAVConfiguracionForm) this.formMantenimiento;		
			f.setTabSeleccion(Constants.MAV_TAB_RESTRICCION);
		    f.setIndicadorRestriccion(Constants.NUMERO_UNO);
		    
		    actualizarListTiposOferta(f);
		    limpiarCamposRestriccion(f);
			
		    f.setCodigoRestriccion("");
			
		} catch (Exception e) {
			addError("Error", obtieneMensajeErrorException(e));
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
	public void refreshConsi(ActionEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'refreshConsi' method");
		}	
		
		try {
			MantenimientoMAVConfiguracionForm f = (MantenimientoMAVConfiguracionForm) this.formMantenimiento;		
			
			f.setTabSeleccion(Constants.MAV_TAB_CONSIDERACION);
		    f.setIndicadorConsideracion(Constants.NUMERO_UNO);				
			
		    actualizarListTiposOferta(f);
		    limpiarCamposConsideracion(f);
			
			f.setCodigoConsideracion("");
			
			f.setSelectedItemsConsideracion(null);
		  	f.setSelectedItemsRestriccion(null);
		
		} catch (Exception e) {
			addError("Error", obtieneMensajeErrorException(e));
		}
		
	}	
	/***FIN TAB RESTRICCION **********/
	
	private void limpiarCamposConsideracion(MantenimientoMAVConfiguracionForm f) {
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
		f.setCondicionTipoVenta("");
		
		f.setCondicionMonto2("");
		f.setCondicionPeriodoFinanciado("");
	}
	
	private void limpiarCamposRestriccion(MantenimientoMAVConfiguracionForm f) {
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
		f.setCondicionTipoVenta("");
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
	public void viewPopup() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'viewPopup' method");
		}			
		try {
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			MantenimientoMAVConfiguracionForm f = (MantenimientoMAVConfiguracionForm) this.formMantenimiento;		

			String codigoConsRest="";
			String indicadorTipo=this.indicadorTipo;
			String indicadorUnidad=f.getIndicadorUnidad();
			String numeroUnidades=f.getNumeroUnidades();
			
			if(indicadorTipo.equals(Constants.MAV_TIPO_CONSIDERACION)){
				codigoConsRest=f.getCodigoConsideracion();
			} else {
				codigoConsRest=f.getCodigoRestriccion();
			}
			
			this.nombreVentanaPopup="";
			
			this.indicadorCerrarVentana = Constants.NRO_CERO;
			this.codigoIdiomaISO = usuario.getIdioma().getCodigoISO();				
			int codigo =Integer.parseInt(codigoConsRest);
			
			String forward="";
			switch (codigo) {
			case Constants.MAV_CONRES_LISTA_CONSU:
				f.setOidPais(obtenerOidPais(pais.getCodigo()));
				f.setCodigoCliente("");
				f.setNombreCliente("");
				this.consultoraList = new ArrayList();
				
				actualizarTotalConsultoras(f, true);
				forward =Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo)?"viewListaCliente":"viewListaClienteRest";	
				this.nombreVentanaPopup=forward;
				break;
			case Constants.MAV_CONRES_LISTA_REGION_ZONA:
				f.setOidPais(obtenerOidPais(pais.getCodigo()));
				ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
				Map criteriaOperacion = new HashMap();
				criteriaOperacion.put("codigoPais", pais.getCodigo());
				criteriaOperacion.put("indicadorTipoRegion", Constants.SI);
				
				this.siccRegionList = reporteService.getListaGenerico("getRegionesByPais",
								criteriaOperacion);
				this.siccCapacitadoraList = reporteService.getListaGenerico("getRegionesByPais",
								criteriaOperacion);
				this.siccZonaList = null;
				f.setIndicadorUnidadPopup(indicadorUnidad);
				
				if(StringUtils.equals(indicadorUnidad, Constants.NO))
				{
					f.setNumeroUnidadesPopup(numeroUnidades);
					f.setNumeroUnidadesPopupUniDifNO(numeroUnidades);
				}
					
				
				this.regionesList = new ArrayList();
				actualizarTotalUnidades(f, true);
				
				forward =Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo)?"viewListaRegionZona":"viewListaRegionZonaRest";
				this.nombreVentanaPopup=forward;
				break;	
			case Constants.MAV_CONRES_CLASIFICACIONES_CLIENTE:
				this.clasificacionesList = new ArrayList();
				InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
				//cargando en session la lista de concursos habilitados
				this.siccTipoClienteList = interfazSiCCService.getTiposClientesByCodigoISOOID(usuario.getIdioma().getCodigoISO());
	
				f.setOidSubTipoCliente(Constants.OID_SUBTIPO_CLIENTE_DEFAULT );
				f.setOidTipoClienteAux(Constants.OID_TIPO_CLIENTE_DEFAULT);
				f.setOidTipoClienteAux2(Constants.OID_TIPO_CLIENTE_DEFAULT);
				f.setOidTipoClasificacion(null);
				f.setOidClasificacion(null);
				f.setIndicadorUnidadPopup(indicadorUnidad);
	
				recargarTipologiaClientes(f);			
				forward =Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo)?"viewClasificacionesCliente":"viewClasificacionesClienteRest";
				break;
			case Constants.MAV_CONRES_UNIDAD_ADM:
				this.unidadesList = new ArrayList();
				reporteService = (ReporteService) getBean("scsicc.reporteService");
				criteriaOperacion = new HashMap();
				criteriaOperacion.put("codigoPais", pais.getCodigo());
				this.siccRegionList = reporteService.getListaGenerico("getRegionesByPais",
								criteriaOperacion);
				
				this.siccSeccionList = null;
				this.siccZonaList = null;
				this.siccTerritorioList = null;
				this.codigoIdiomaISO = usuario.getIdioma().getCodigoISO();
				
				if(StringUtils.equals(indicadorUnidad, Constants.NO))
				{
					f.setNumeroUnidadesPopup(numeroUnidades);
					f.setNumeroUnidadesPopupUniDifNO(numeroUnidades);
				}
				
				actualizarTotalUnidadesUA(f, true);
				forward =Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo)?"viewUnidadAdministrativa":"viewUnidadAdministrativaRest";
				f.setIndicadorUnidadPopup(indicadorUnidad);
				break;
			case Constants.MAV_CONRES_ESTATUS_CLIENTE:
				this.codigoEstatusList = new ArrayList();
				reporteService = (ReporteService) getBean("scsicc.reporteService");
				criteriaOperacion = new HashMap();
				criteriaOperacion.put("codigoPais", pais.getCodigo());
				this.SACestadosList = reporteService.getListaGenerico("getEstadoSaldoConsultora",
								criteriaOperacion);						
				forward =Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo)?"viewEstatusCliente":"viewEstatusClienteRest";
				break;			
				
			case Constants.MAV_CONRES_LISTA_CONSU_REST:
				f.setOidPais(obtenerOidPais(pais.getCodigo()));
				this.consultoraListR = new ArrayList();
				
				actualizarTotalConsultoras(f, false);
				forward =Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo)?"viewListaCliente":"viewListaClienteRest";
				break;
			case Constants.MAV_CONRES_LISTA_REGION_ZONA_REST:
				f.setOidPais(obtenerOidPais(pais.getCodigo()));
				reporteService = (ReporteService) getBean("scsicc.reporteService");
				criteriaOperacion = new HashMap();
				criteriaOperacion.put("codigoPais", pais.getCodigo());
				criteriaOperacion.put("indicadorTipoRegion", Constants.SI);
				
				this.siccRegionList = reporteService.getListaGenerico("getRegionesByPais",
								criteriaOperacion);
				this.siccCapacitadoraList = reporteService.getListaGenerico("getRegionesByPais",
								criteriaOperacion);
				this.siccZonaList = null;
				f.setIndicadorUnidadPopup(indicadorUnidad);
				this.regionesListR = new ArrayList();
				actualizarTotalUnidades(f, false);
				
				forward =Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo)?"viewListaRegionZona":"viewListaRegionZonaRest";
				break;			
			case Constants.MAV_CONRES_CLASIFICACIONES_CLIENTE_REST:
				this.clasificacionesListR = new ArrayList();
				interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
				//cargando en session la lista de concursos habilitados
				this.siccTipoClienteList = interfazSiCCService.getTiposClientesByCodigoISOOID(usuario.getIdioma().getCodigoISO());
	
				f.setOidSubTipoCliente(Constants.OID_SUBTIPO_CLIENTE_DEFAULT );
				f.setOidTipoClienteAux(Constants.OID_TIPO_CLIENTE_DEFAULT);
				f.setOidTipoClienteAux2(Constants.OID_TIPO_CLIENTE_DEFAULT);
				f.setOidTipoClasificacion(null);
				f.setOidClasificacion(null);
				f.setIndicadorUnidadPopup(indicadorUnidad);
				
				recargarTipologiaClientes(f);					
				forward =Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo)?"viewClasificacionesCliente":"viewClasificacionesClienteRest";
				break;
			case Constants.MAV_CONRES_UNIDAD_ADM_REST:
				this.unidadesListR = new ArrayList();
				reporteService = (ReporteService) getBean("scsicc.reporteService");
				criteriaOperacion = new HashMap();
				criteriaOperacion.put("codigoPais", pais.getCodigo());
				this.siccRegionList = reporteService.getListaGenerico("getRegionesByPais",
								criteriaOperacion);
				
				this.siccSeccionList = null;
				this.siccZonaList = null;
				this.siccTerritorioList = null;
				this.codigoIdiomaISO = usuario.getIdioma().getCodigoISO();			
				forward =Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo)?"viewUnidadAdministrativa":"viewUnidadAdministrativaRest";
				
				if(StringUtils.equals(indicadorUnidad, Constants.NO))
				{
					f.setNumeroUnidadesPopup(numeroUnidades);
					f.setNumeroUnidadesPopupUniDifNO(numeroUnidades);
				}
				
				actualizarTotalUnidadesUA(f, false);
				f.setIndicadorUnidadPopup(indicadorUnidad);
				break;	
			case Constants.MAV_CONRES_ESTATUS_CLIENTE_REST:
				this.codigoEstatusListR = new ArrayList();
				reporteService = (ReporteService) getBean("scsicc.reporteService");
				criteriaOperacion = new HashMap();
				criteriaOperacion.put("codigoPais", pais.getCodigo());
				this.SACestadosList = reporteService.getListaGenerico("getEstadoSaldoConsultora",
								criteriaOperacion);						
				forward =Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo)?"viewEstatusCliente":"viewEstatusClienteRest";
				break;			
			}
			
			if(StringUtils.isEmpty(this.nombreVentanaPopup))
				this.getRequestContext().execute("PF('" + forward + "').show()");
			log.debug("forward ss "+forward);
			
			if(Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo)) {
				this.dataTableDetalleConsideracion = new DataTableModel(new ArrayList());
				this.codigoConsRest = f.getCodigoConsideracion();
			} else {
				this.dataTableDetalleRestriccion = new DataTableModel(new ArrayList());
				this.codigoConsRest = f.getCodigoRestriccion();
			}	
			
		} catch (Exception e) {
			addError("Error", obtieneMensajeErrorException(e));
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
	public void editPopup(ActionEvent event) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'editPopup' method");
		}	
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		MantenimientoMAVConfiguracionForm f = (MantenimientoMAVConfiguracionForm) this.formMantenimiento;	
		MantenimientoMAVConfiguracionService service = (MantenimientoMAVConfiguracionService)
								getBean("spusicc.mantenimientoMAVConfiguracionService");	
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		
		String indicadorTipo=this.indicadorTipo;
		String correlativo=f.getCorrelativo();
		String indicadorUnidad=f.getIndicadorUnidad();
		this.nombreVentanaPopup="";
		
		this.indicadorCerrarVentana = Constants.NRO_CERO;
		this.codigoIdiomaISO = usuario.getIdioma().getCodigoISO();	
		
		String codigoConsRest="";
		if(indicadorTipo.equals(Constants.MAV_TIPO_CONSIDERACION)){
			List listConsi = this.mavConfiguracionConsideracionList;
			Map mapConRes = (Map)this.beanRegistroConsideracion;
			codigoConsRest = (String)mapConRes.get("codigoConsideracion");
		}else{
			List listConsi = this.mavConfiguracionRestriccionList;
			Map mapConRes = (Map)this.beanRegistroRestriccion;
			codigoConsRest = (String)mapConRes.get("codigoRestriccion");
		}

		int codigo =Integer.parseInt(codigoConsRest);
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("correlativoMAV", correlativo);
		criteria.put("codigoConsRest", codigoConsRest);
		criteria.put("indicadorTipo", indicadorTipo);
		List list = (List)service.getDetalleConsRest(criteria);
		Iterator it = list.iterator();
		String forward="";
		List listC = null;
		Map map = null;		
		
		if(f.getIndicadorUnidad().equals("N")) {
			f.setNumeroUnidadesPopup(f.getNumeroUnidades());
		}
		
		switch (codigo) {
		case Constants.MAV_CONRES_LISTA_CONSU:
			listC = (List)this.consultoraList;
			
			if( listC== null){
				listC = new ArrayList();			
				
				while(it.hasNext()){
					map = new HashMap();
					Map bean =(Map) it.next();
					String codigoPais = (String)bean.get("valCondi1");
					String codigoCliente = (String)bean.get("valCondi2");
					String nombreCliente = (String)bean.get("nombreCliente");
					
					map.put("codigoPais", codigoPais);
					map.put("codigoCliente", codigoCliente);
					map.put("nombreCliente", nombreCliente);
					map.put("indicadorAccion",Constants.NRO_UNO);
					map.put("correlativoConsideracion", String.valueOf(bean.get("correlativoConsideracion")));
					map.put("numeroUnidades", String.valueOf(bean.get("numeroUnidades")));
					listC.add(map);
				}	
			}
			f.setOidPais(obtenerOidPais(pais.getCodigo()));
			this.consultoraList = listC;
			
			actualizarTotalConsultoras(f, true);
			
			forward =Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo)?"viewListaCliente":"viewListaClienteRest";
			this.nombreVentanaPopup=forward;
			//this.getRequestContext().execute("PF('" + forward + "').show()");
			break;
		case Constants.MAV_CONRES_LISTA_REGION_ZONA:
			listC = (List)this.regionesList;
			
			Map criteriaOperacion = new HashMap();
			criteriaOperacion.put("codigoPais", pais.getCodigo());
			criteriaOperacion.put("indicadorTipoRegion", Constants.SI);
			this.siccRegionList = reporteService.getListaGenerico("getRegionesByPais",
							criteriaOperacion);
			this.siccCapacitadoraList =
					reporteService.getListaGenerico("getRegionesByPais",
							criteriaOperacion);
			this.siccZonaList = null;
			this.codigoIdiomaISO = usuario.getIdioma().getCodigoISO();

			if( listC== null){
				listC = new ArrayList();			
				
				while(it.hasNext()){
					map = new HashMap();
					Map bean =(Map) it.next();
					String codigoRegion = (String)bean.get("valCondi1");
					String codigoZona = bean.get("valCondi2")!=null?(String)bean.get("valCondi2"):"";
					String codigoCapacitadora = bean.get("valCondi3")!=null?(String)bean.get("valCondi3"):"";
					
					map.put("codigoRegion", codigoRegion);
					map.put("codigoZona", codigoZona);
					map.put("codigoCapacitadora", codigoCapacitadora);
					map.put("descripcionRegion", getDesRegion(codigoRegion));
					map.put("descripcionZona", getDesZona(codigoRegion,codigoZona));
					map.put("descripcionCapacitadora", getDesCapacitadora(codigoCapacitadora));
					map.put("indicadorAccion",Constants.NRO_UNO);
					map.put("correlativoConsideracion", String.valueOf(bean.get("correlativoConsideracion")));
					map.put("numeroUnidades", String.valueOf(bean.get("numeroUnidades")));
					listC.add(map);
				}	
			}
			f.setOidPais(obtenerOidPais(pais.getCodigo()));
			f.setIndicadorUnidadPopup(indicadorUnidad);
			this.regionesList =listC;
			actualizarTotalUnidades(f, true);
			
			forward =Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo)?"viewListaRegionZona":"viewListaRegionZonaRest";
			this.nombreVentanaPopup=forward;
			//this.getRequestContext().execute("PF('" + forward + "').show()");
			break;			
		case Constants.MAV_CONRES_CLASIFICACIONES_CLIENTE:
			listC = (List) this.clasificacionesList;

			InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
			//cargando en session la lista de concursos habilitados
			this.siccTipoClienteList = interfazSiCCService.getTiposClientesByCodigoISOOID(usuario.getIdioma().getCodigoISO());

			f.setOidSubTipoCliente(Constants.OID_SUBTIPO_CLIENTE_DEFAULT );
			f.setOidTipoClienteAux(Constants.OID_TIPO_CLIENTE_DEFAULT);
			f.setOidTipoClienteAux2(Constants.OID_TIPO_CLIENTE_DEFAULT);
			f.setOidTipoClasificacion(null);
			f.setOidClasificacion(null);	
			f.setIndicadorUnidadPopup(indicadorUnidad);

			recargarTipologiaClientes(f);
			if( listC== null){
				listC = new ArrayList();
				
				while(it.hasNext()){
					map = new HashMap();
					Map bean =(Map) it.next();
					String codigoTipoCliente = (String)bean.get("valCondi1");
					String codigoSubTipoCliente = (String)bean.get("valCondi2");
					String codigoTipoClasificacion = (String)bean.get("valCondi3");
					String codigoClasificacion = (String)bean.get("valCondi4");
					String oidTipoCliente = getOidTipoCliente(codigoTipoCliente);
					String oidSubTipoCliente = getOidSubtipoCliente(codigoTipoCliente,codigoSubTipoCliente);
					String oidTipoClasificacion =  getOidTipoClasificacion(codigoTipoCliente,codigoSubTipoCliente,codigoTipoClasificacion);
					String oidClasificacion = getOidClasificacion(codigoTipoCliente,codigoSubTipoCliente,codigoTipoClasificacion,codigoClasificacion);				
					
					map.put("oidTipoCliente", oidTipoCliente);
					map.put("oidSubTipoCliente",oidSubTipoCliente);
					map.put("oidTipoClasificacion",oidTipoClasificacion);
					map.put("oidClasificacion",oidClasificacion );
					map.put("codigoTipoCliente", codigoTipoCliente);
					map.put("codigoSubTipoCliente", codigoSubTipoCliente);
					map.put("codigoTipoClasificacion",codigoTipoClasificacion);
					map.put("codigoClasificacion", codigoClasificacion);			
					map.put("descripcionTipoCliente", getDesTipocliente(oidTipoCliente));
					map.put("descripcionSubTipoCliente", getDesSubtipoCliente(oidTipoCliente,oidSubTipoCliente));
					map.put("descripcionTipoClasificacion", getDesTipoClasificacion(oidTipoCliente,oidSubTipoCliente,oidTipoClasificacion));
					map.put("descripcionClasificacion", getClasificacion(oidTipoCliente,oidSubTipoCliente,oidTipoClasificacion,oidClasificacion));
					map.put("indicadorAccion",Constants.NRO_UNO);
					map.put("correlativoConsideracion", String.valueOf(bean.get("correlativoConsideracion")));
					map.put("numeroUnidades", String.valueOf(bean.get("numeroUnidades")));
					listC.add(map);
				}				
			}
			
			this.clasificacionesList = listC;
			forward =Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo)?"viewClasificacionesCliente":"viewClasificacionesClienteRest";
			this.getRequestContext().execute("PF('" + forward + "').show()");
			break;
		case Constants.MAV_CONRES_UNIDAD_ADM:
			listC = (List) this.unidadesList;

			criteriaOperacion = new HashMap();
			criteriaOperacion.put("codigoPais", pais.getCodigo());
			this.siccRegionList = reporteService.getListaGenerico("getRegionesByPais",
							criteriaOperacion);
			
			this.siccSeccionList = null;
			this.siccZonaList = null;
			this.siccTerritorioList = null;
			this.codigoIdiomaISO = usuario.getIdioma().getCodigoISO();			
			forward =Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo)?"viewUnidadAdministrativa":"viewUnidadAdministrativaRest";
			if( listC== null){
				listC = new ArrayList();			
							
				while(it.hasNext()){
					map = new HashMap();
					Map bean =(Map) it.next();
					String codigoRegion = (String)bean.get("valCondi1");
					String codigoZona = bean.get("valCondi2")!=null?(String)bean.get("valCondi2"):"";
					String codigoSeccion = bean.get("valCondi3")!=null?(String)bean.get("valCondi3"):"";
					String codigoTerritorio = bean.get("valCondi4")!=null?(String)bean.get("valCondi4"):"";
					
					map.put("codigoRegion", codigoRegion);
					map.put("codigoZona", codigoZona);
					map.put("codigoSeccion",codigoSeccion);
					map.put("codigoTerritorio", codigoTerritorio);			
					map.put("descripcionRegion", getDesRegion(codigoRegion));
					map.put("descripcionZona", getDesZona(codigoRegion,codigoZona));
					map.put("descripcionSeccion", getDesSeccion(codigoRegion,codigoZona,codigoSeccion));
					map.put("descripcionTerritorio", getDesTerr(codigoRegion,codigoZona,codigoSeccion,codigoTerritorio));
					map.put("indicadorAccion",Constants.NRO_UNO);
					map.put("correlativoConsideracion", String.valueOf(bean.get("correlativoConsideracion")));
					map.put("numeroUnidades", String.valueOf(bean.get("numeroUnidades")));
					listC.add(map);
				}	
			}						
			this.unidadesList = listC;	
			f.setIndicadorUnidadPopup(indicadorUnidad);
			actualizarTotalUnidadesUA(f, true);
			this.getRequestContext().execute("PF('" + forward + "').show()");
			break;
		case Constants.MAV_CONRES_ESTATUS_CLIENTE:	
			listC = (List) this.codigoEstatusList;
			
			reporteService = (ReporteService) getBean("scsicc.reporteService");
			criteriaOperacion = new HashMap();
			criteriaOperacion.put("codigoPais", pais.getCodigo());
			List listEstado = reporteService.getListaGenerico("getEstadoSaldoConsultora",criteriaOperacion); 
			this.SACestadosList = listEstado;
			if( listC== null){
				listC = new ArrayList();
						
				while(it.hasNext()){
					map = new HashMap();
					Map bean =(Map) it.next();
					map.put("codigoEstado",bean.get("valCondi1"));
					map.put("descripcionEstado",getDesEstado((String)bean.get("valCondi1"),listEstado));
					map.put("indicadorAccion",Constants.NRO_UNO);
					map.put("correlativoConsideracion", String.valueOf(bean.get("correlativoConsideracion")));
					listC.add(map);
				}					
			}
			this.codigoEstatusList = listC;
			forward =Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo)?"viewEstatusCliente":"viewEstatusClienteRest";
			this.getRequestContext().execute("PF('" + forward + "').show()");
			break;
			
		case Constants.MAV_CONRES_LISTA_CONSU_REST:
			listC = this.consultoraListR;
			
			if( listC== null){
				listC = new ArrayList();			
				
				while(it.hasNext()){
					map = new HashMap();
					Map bean =(Map) it.next();
					String codigoPais = (String)bean.get("valCondi1");
					String codigoCliente = (String)bean.get("valCondi2");
					String nombreCliente = (String)bean.get("nombreCliente");
					
					map.put("codigoPais", codigoPais);
					map.put("codigoCliente", codigoCliente);
					map.put("nombreCliente", nombreCliente);
					map.put("indicadorAccion",Constants.NRO_UNO);
					map.put("correlativoConsideracion", String.valueOf(bean.get("correlativoConsideracion")));
					map.put("numeroUnidades", String.valueOf(bean.get("numeroUnidades")));
					listC.add(map);
				}	
			}
			f.setOidPais(obtenerOidPais(pais.getCodigo()));
			this.consultoraListR = listC;
			
			actualizarTotalConsultoras(f, false);
			forward =Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo)?"viewListaCliente":"viewListaClienteRest";
			this.nombreVentanaPopup=forward;
			//this.getRequestContext().execute("PF('" + forward + "').show()");
			break;
		case Constants.MAV_CONRES_LISTA_REGION_ZONA_REST:
			listC = this.regionesListR;
			
			criteriaOperacion = new HashMap();
			criteriaOperacion.put("codigoPais", pais.getCodigo());
			this.siccRegionList = reporteService.getListaGenerico("getRegionesByPais",
							criteriaOperacion);
			this.siccCapacitadoraList = reporteService.getListaGenerico("getRegionesByPais",
							criteriaOperacion);
			this.siccZonaList = null;
			this.codigoIdiomaISO = usuario.getIdioma().getCodigoISO();
			
			if( listC== null){
				listC = new ArrayList();			
				
				while(it.hasNext()){
					map = new HashMap();
					Map bean =(Map) it.next();
					String codigoRegion = (String)bean.get("valCondi1");
					String codigoZona = bean.get("valCondi2")!=null?(String)bean.get("valCondi2"):"";
					String codigoCapacitadora = bean.get("valCondi3")!=null?(String)bean.get("valCondi3"):"";
					
					map.put("codigoRegion", codigoRegion);
					map.put("codigoZona", codigoZona);
					map.put("codigoCapacitadora", codigoCapacitadora);
					map.put("descripcionRegion", getDesRegion(codigoRegion));
					map.put("descripcionZona", getDesZona(codigoRegion,codigoZona));
					map.put("descripcionCapacitadora", getDesCapacitadora(codigoCapacitadora));
					map.put("indicadorAccion",Constants.NRO_UNO);
					map.put("correlativoConsideracion", String.valueOf(bean.get("correlativoConsideracion")));
					map.put("numeroUnidades", String.valueOf(bean.get("numeroUnidades")));
					listC.add(map);
				}	
			}
			f.setOidPais(obtenerOidPais(pais.getCodigo()));
			f.setIndicadorUnidadPopup(indicadorUnidad);
			this.regionesListR = listC;
			actualizarTotalUnidades(f, false);
			
			forward =Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo)?"viewListaRegionZona":"viewListaRegionZonaRest";
			this.getRequestContext().execute("PF('" + forward + "').show()");
			break;			
		case Constants.MAV_CONRES_CLASIFICACIONES_CLIENTE_REST:
			listC = this.clasificacionesListR;
			 interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
			//cargando en session la lista de concursos habilitados
			this.siccTipoClienteList = interfazSiCCService.getTiposClientesByCodigoISOOID(usuario.getIdioma().getCodigoISO());

			f.setOidSubTipoCliente(Constants.OID_SUBTIPO_CLIENTE_DEFAULT );
			f.setOidTipoClienteAux(Constants.OID_TIPO_CLIENTE_DEFAULT);
			f.setOidTipoClienteAux2(Constants.OID_TIPO_CLIENTE_DEFAULT);
			f.setOidTipoClasificacion(null);
			f.setOidClasificacion(null);			
			f.setIndicadorUnidadPopup(indicadorUnidad);

			recargarTipologiaClientes(f);
			if( listC== null){
				listC = new ArrayList();			
			  while(it.hasNext()){
				map = new HashMap();
				Map bean =(Map) it.next();
				String codigoTipoCliente = (String)bean.get("valCondi1");
				String codigoSubTipoCliente = (String)bean.get("valCondi2");
				String codigoTipoClasificacion = (String)bean.get("valCondi3");
				String codigoClasificacion = (String)bean.get("valCondi4");
				String oidTipoCliente = getOidTipoCliente(codigoTipoCliente);
				String oidSubTipoCliente = getOidSubtipoCliente(codigoTipoCliente,codigoSubTipoCliente);
				String oidTipoClasificacion =  getOidTipoClasificacion(codigoTipoCliente,codigoSubTipoCliente,codigoTipoClasificacion);
				String oidClasificacion = getOidClasificacion(codigoTipoCliente,codigoSubTipoCliente,codigoTipoClasificacion,codigoClasificacion);				
				
				map.put("oidTipoCliente", oidTipoCliente);
				map.put("oidSubTipoCliente",oidSubTipoCliente);
				map.put("oidTipoClasificacion",oidTipoClasificacion);
				map.put("oidClasificacion",oidClasificacion );
				map.put("codigoTipoCliente", codigoTipoCliente);
				map.put("codigoSubTipoCliente", codigoSubTipoCliente);
				map.put("codigoTipoClasificacion",codigoTipoClasificacion);
				map.put("codigoClasificacion", codigoClasificacion);			
				map.put("descripcionTipoCliente", getDesTipocliente(oidTipoCliente));
				map.put("descripcionSubTipoCliente", getDesSubtipoCliente(oidTipoCliente,oidSubTipoCliente));
				map.put("descripcionTipoClasificacion", getDesTipoClasificacion(oidTipoCliente,oidSubTipoCliente,oidTipoClasificacion));
				map.put("descripcionClasificacion", getClasificacion(oidTipoCliente,oidSubTipoCliente,oidTipoClasificacion,oidClasificacion));
				map.put("indicadorAccion",Constants.NRO_UNO);
				map.put("correlativoConsideracion", String.valueOf(bean.get("correlativoConsideracion")));
				map.put("numeroUnidades", String.valueOf(bean.get("numeroUnidades")));
				
				listC.add(map);
			  }	
			}
			this.clasificacionesListR = listC;		
			forward =Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo)?"viewClasificacionesCliente":"viewClasificacionesClienteRest";
			this.getRequestContext().execute("PF('" + forward + "').show()");
			break;
		case Constants.MAV_CONRES_UNIDAD_ADM_REST:		
			listC = this.unidadesListR;

			criteriaOperacion = new HashMap();
			criteriaOperacion.put("codigoPais", pais.getCodigo());
			this.siccRegionList = reporteService.getListaGenerico("getRegionesByPais",
							criteriaOperacion);
			
			this.siccSeccionList = null;
			this.siccZonaList = null;
			this.siccTerritorioList = null;
			this.codigoIdiomaISO = usuario.getIdioma().getCodigoISO();			
			forward =Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo)?"viewUnidadAdministrativa":"viewUnidadAdministrativaRest";
			
			if( listC== null){
			 listC = new ArrayList();			
			 while(it.hasNext()){
				map = new HashMap();
				Map bean =(Map) it.next();
				String codigoRegion = (String)bean.get("valCondi1");
				String codigoZona = bean.get("valCondi2")!=null?(String)bean.get("valCondi2"):"";
				String codigoSeccion = bean.get("valCondi3")!=null?(String)bean.get("valCondi3"):"";;
				String codigoTerritorio = bean.get("valCondi4")!=null?(String)bean.get("valCondi4"):"";;
				
				map.put("codigoRegion", codigoRegion);
				map.put("codigoZona", codigoZona);
				map.put("codigoSeccion",codigoSeccion);
				map.put("codigoTerritorio", codigoTerritorio);			
				map.put("descripcionRegion", getDesRegion(codigoRegion));
				map.put("descripcionZona", getDesZona(codigoRegion,codigoZona));
				map.put("descripcionSeccion", getDesSeccion(codigoRegion,codigoZona,codigoSeccion));
				map.put("descripcionTerritorio", getDesTerr(codigoRegion,codigoZona,codigoSeccion,codigoTerritorio));
				map.put("indicadorAccion",Constants.NRO_UNO);
				map.put("correlativoConsideracion", String.valueOf(bean.get("correlativoConsideracion")));
				map.put("numeroUnidades", String.valueOf(bean.get("numeroUnidades")));
				listC.add(map);
			}				
			}
			this.unidadesListR = listC;
			forward =Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo)?"viewUnidadAdministrativa":"viewUnidadAdministrativaRest";
			f.setIndicadorUnidadPopup(indicadorUnidad);
			actualizarTotalUnidadesUA(f, false);
			this.getRequestContext().execute("PF('" + forward + "').show()");
			break;		
		case Constants.MAV_CONRES_ESTATUS_CLIENTE_REST:
			listC = (List) this.codigoEstatusListR;
		
			reporteService = (ReporteService) getBean("scsicc.reporteService");
			criteriaOperacion = new HashMap();
			criteriaOperacion.put("codigoPais", pais.getCodigo());
			listEstado = reporteService.getListaGenerico("getEstadoSaldoConsultora",criteriaOperacion); 
			this.SACestadosList = listEstado;	
			if( listC== null){
				listC = new ArrayList();			
				while(it.hasNext()){
					map = new HashMap();
					Map bean =(Map) it.next();
					map.put("codigoEstado",bean.get("valCondi1"));
					map.put("descripcionEstado",getDesEstado((String)bean.get("valCondi1"),listEstado));
					map.put("indicadorAccion",Constants.NRO_UNO);
					map.put("correlativoConsideracion", String.valueOf(bean.get("correlativoConsideracion")));
					listC.add(map);
				}					
			}
			this.codigoEstatusListR = listC;
					
			forward =Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo)?"viewEstatusCliente":"viewEstatusClienteRest";
			this.getRequestContext().execute("PF('" + forward + "').show()");
			break;			
		}
		
		if(Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo))
			this.dataTableDetalleConsideracion = new DataTableModel(listC);
		else
			this.dataTableDetalleRestriccion = new DataTableModel(listC);
			
		this.codigoConsRest = codigoConsRest;
		log.debug("forward ss "+forward);
			
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
	public void savePopup(ActionEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'savePopup' method");
		}		
		
		try {

			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			//
			MantenimientoMAVConfiguracionForm f = (MantenimientoMAVConfiguracionForm) this.formMantenimiento;
			//MantenimientoMENGenericoService service = (MantenimientoMENGenericoService) getBean("spusicc.mantenimientoMENGenericoService");
			MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
			//
			String codigoConsRest=this.codigoConsRest;
			String indicadorTipo=this.indicadorTipo;
			this.indicadorCerrarVentana = Constants.NRO_UNO;
			//indicadorCerrarVentana
	
			
	        // Asignamos al codigo del periodo el valor por defecto
	        Map crit = new HashMap();
	        crit.put("codigoPais", f.getCodigoPais());
	        crit.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
	        crit.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
	        crit.put("codigoISO", usuario.getIdioma().getCodigoISO());
	        
	        //recuperamos el oid Pais
	        String oidPais = clienteService.getOidPais(crit);
	        //
					
			int codigo =Integer.parseInt(codigoConsRest);
			String forward="";
			List list=null;

			switch (codigo) {
			case Constants.MAV_CONRES_LISTA_CONSU:
				list =this.consultoraList;
				
				if(list != null) {
				for(int i=list.size()-1;i>=0;i--){
					Map m=(Map)list.get(i);
					String indicadorAccion = (String) m.get("indicadorAccion");
					if(Constants.MAV_ESTADO_TMP_INSERTAR.equals(indicadorAccion)){
					  m.put("indicadorAccion",Constants.NRO_CERO);
					}
					if(Constants.MAV_ESTADO_TMP_ELIMINAR.equals(indicadorAccion)){
					  m.put("indicadorAccion",Constants.NUMERO_DOS);	
					}				
				}
				}
				forward =Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo)?"viewListaCliente":"viewListaClienteRest";
				break;
			case Constants.MAV_CONRES_LISTA_REGION_ZONA:
				list =this.regionesList;
				
				if(list != null) {
				for(int i=list.size()-1;i>=0;i--){
					Map m=(Map)list.get(i);
					String indicadorAccion = (String) m.get("indicadorAccion");
					if(Constants.MAV_ESTADO_TMP_INSERTAR.equals(indicadorAccion)){
					  m.put("indicadorAccion",Constants.NRO_CERO);
					}
					if(Constants.MAV_ESTADO_TMP_ELIMINAR.equals(indicadorAccion)){
					  m.put("indicadorAccion",Constants.NUMERO_DOS);	
					}				
				}
				}
				forward =Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo)?"viewListaRegionZona":"viewListaRegionZonaRest";
				break;	
			case Constants.MAV_CONRES_CLASIFICACIONES_CLIENTE:
				list =this.clasificacionesList;
				for(int i=list.size()-1;i>=0;i--){
					Map m=(Map)list.get(i);
					String indicadorAccion = (String) m.get("indicadorAccion");
					if(Constants.MAV_ESTADO_TMP_INSERTAR.equals(indicadorAccion)){
						  m.put("indicadorAccion",Constants.NRO_CERO);
					}
					if(Constants.MAV_ESTADO_TMP_ELIMINAR.equals(indicadorAccion)){
						  m.put("indicadorAccion",Constants.NUMERO_DOS);	
					}	
				}						
				forward =Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo)?"viewClasificacionesCliente":"viewClasificacionesClienteRest";
				break;
			case Constants.MAV_CONRES_UNIDAD_ADM:
				list =this.unidadesList;
				for(int i=list.size()-1;i>=0;i--){
					Map m=(Map)list.get(i);
					String indicadorAccion = (String) m.get("indicadorAccion");
					if(Constants.MAV_ESTADO_TMP_INSERTAR.equals(indicadorAccion)){
						  m.put("indicadorAccion",Constants.NRO_CERO);
					}
					if(Constants.MAV_ESTADO_TMP_ELIMINAR.equals(indicadorAccion)){
						  m.put("indicadorAccion",Constants.NUMERO_DOS);	
					}
				}						
				forward =Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo)?"viewUnidadAdministrativa":"viewUnidadAdministrativaRest";
				break;	
			case Constants.MAV_CONRES_ESTATUS_CLIENTE:
				list =(List)this.codigoEstatusList;
				for(int i=list.size()-1;i>=0;i--){
					Map m=(Map)list.get(i);
					String indicadorAccion = (String) m.get("indicadorAccion");
					if(Constants.MAV_ESTADO_TMP_INSERTAR.equals(indicadorAccion)){
					  m.put("indicadorAccion",Constants.NRO_CERO);
					}
					if(Constants.MAV_ESTADO_TMP_ELIMINAR.equals(indicadorAccion)){
					  m.put("indicadorAccion",Constants.NUMERO_DOS);	
					}				
				}			
				forward =Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo)?"viewEstatusCliente":"viewEstatusClienteRest";
				break;
				
			case Constants.MAV_CONRES_LISTA_CONSU_REST:
				list =(List)this.consultoraListR;
				
				if(list != null) {
				for(int i=list.size()-1;i>=0;i--){
					Map m=(Map)list.get(i);
					String indicadorAccion = (String) m.get("indicadorAccion");
					if(Constants.MAV_ESTADO_TMP_INSERTAR.equals(indicadorAccion)){
					  m.put("indicadorAccion",Constants.NRO_CERO);
					}
					if(Constants.MAV_ESTADO_TMP_ELIMINAR.equals(indicadorAccion)){
					  m.put("indicadorAccion",Constants.NUMERO_DOS);	
					}				
				}
				}
				forward =Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo)?"viewListaCliente":"viewListaClienteRest";
				break;
			case Constants.MAV_CONRES_LISTA_REGION_ZONA_REST:
				list =this.regionesListR;
				
				if(list != null) {
				for(int i=list.size()-1;i>=0;i--){
					Map m=(Map)list.get(i);
					String indicadorAccion = (String) m.get("indicadorAccion");
					if(Constants.MAV_ESTADO_TMP_INSERTAR.equals(indicadorAccion)){
					  m.put("indicadorAccion",Constants.NRO_CERO);
					}
					if(Constants.MAV_ESTADO_TMP_ELIMINAR.equals(indicadorAccion)){
					  m.put("indicadorAccion",Constants.NUMERO_DOS);	
					}				
				}
				}
				forward =Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo)?"viewListaRegionZona":"viewListaRegionZonaRest";
				break;	
			case Constants.MAV_CONRES_CLASIFICACIONES_CLIENTE_REST:
				list =this.clasificacionesListR;
				for(int i=list.size()-1;i>=0;i--){
					Map m=(Map)list.get(i);
					String indicadorAccion = (String) m.get("indicadorAccion");
					if(Constants.MAV_ESTADO_TMP_INSERTAR.equals(indicadorAccion)){
						  m.put("indicadorAccion",Constants.NRO_CERO);
					}
					if(Constants.MAV_ESTADO_TMP_ELIMINAR.equals(indicadorAccion)){
						  m.put("indicadorAccion",Constants.NUMERO_DOS);	
					}
				}				
				forward =Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo)?"viewClasificacionesCliente":"viewClasificacionesClienteRest";
				break;
			case Constants.MAV_CONRES_UNIDAD_ADM_REST:
				list =this.unidadesListR;
				for(int i=list.size()-1;i>=0;i--){
					Map m=(Map)list.get(i);
					String indicadorAccion = (String) m.get("indicadorAccion");
					if(Constants.MAV_ESTADO_TMP_INSERTAR.equals(indicadorAccion)){
						  m.put("indicadorAccion",Constants.NRO_CERO);
					}
					if(Constants.MAV_ESTADO_TMP_ELIMINAR.equals(indicadorAccion)){
						  m.put("indicadorAccion",Constants.NUMERO_DOS);	
					}
				}				
				forward =Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo)?"viewUnidadAdministrativa":"viewUnidadAdministrativaRest";
				break;			
			case Constants.MAV_CONRES_ESTATUS_CLIENTE_REST:
				list =(List)this.codigoEstatusListR;
				for(int i=list.size()-1;i>=0;i--){
					Map m=(Map)list.get(i);
					String indicadorAccion = (String) m.get("indicadorAccion");
					if(Constants.MAV_ESTADO_TMP_INSERTAR.equals(indicadorAccion)){
						  m.put("indicadorAccion",Constants.NRO_CERO);
					}
					if(Constants.MAV_ESTADO_TMP_ELIMINAR.equals(indicadorAccion)){
						  m.put("indicadorAccion",Constants.NUMERO_DOS);	
					}
				}					
				forward =Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo)?"viewEstatusCliente":"viewEstatusClienteRest";
				break;			
			}		
			log.debug("forward "+forward);
			
			if(this.esBotonInsertar)
				if(Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo))
					insertConsideracion("savePopup");
				else
					insertRestriccion("savePopup");
			else
				if(Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo))	
					updateConsideracion(event);
				else
					updateRestriccion(event);
			
			//return mapping.findForward(forward);
			
		} catch (Exception e) {
			addError("Error", obtieneMensajeErrorException(e));
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
	public void insertPopup(ActionEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'insertPopup' method");
		}
		
		try {

			MantenimientoMAVConfiguracionForm f = (MantenimientoMAVConfiguracionForm) this.formMantenimiento;
			String codigoConsRest=this.codigoConsRest;
			String indicadorTipo=this.indicadorTipo;
			String insertar = "S";
			InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
			AjaxService ajaxService = (AjaxService) getBean("ajaxService");
			ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();	
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			this.indicadorCerrarVentana = Constants.NRO_CERO;
			String indicadorTipoSeleccion = f.getIndicadorTipoSeleccionCapacitadora();
			//indicadorCerrarVentana
				
			//VALIDACIONES <INICIO>
			if(Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo) && "S".equals(f.getIndicadorUnidadPopup())) {
				String numeroUnidadesPopup = f.getNumeroUnidadesPopup();
	
		    	if(StringUtils.isEmpty(numeroUnidadesPopup)){
		    		mostrarVentanaAlerta("mantenimientoMAVConfiguracionForm.condicionUnidades.requerido");
		  			return;
				}
		    	if(Integer.parseInt(numeroUnidadesPopup) == 0) {
		    		mostrarVentanaAlerta("mantenimientoMAVConfiguracionForm.msg.validarCantidadUnidades");
		  			return;
				}
			}
			//VALIDACIONES <FIN>
	    	
			int codigo =Integer.parseInt(codigoConsRest);
			log.debug("codigo "+codigo);
			String forward="";
			List list=null;
			switch (codigo) {
	
			case Constants.MAV_CONRES_LISTA_CONSU:
				String numeroUnidadesPopup = f.getNumeroUnidadesPopup();
				
				if(Integer.parseInt(numeroUnidadesPopup) == 0) {
		    		mostrarVentanaAlerta("mantenimientoMAVConfiguracionForm.msg.validarCantidadUnidades");
		  			return;
				}
				
				list =this.consultoraList;
				
				Map map = new HashMap();
				map.put("codigoPais", pais.getCodigo());
				map.put("codigoCliente", f.getCodigoCliente());
				//map.put("indicadorAccion",Constants.NRO_CERO);
				map.put("numeroUnidades", f.getNumeroUnidadesPopup());
				map.put("indicadorAccion",Constants.MAV_ESTADO_TMP_INSERTAR);
				
				boolean existe=false;	
				String oidPais = obtenerOidPais(pais.getCodigo());
				String datosCliente = ajaxService.getExisteCodigoCliente(oidPais, f.getCodigoCliente());
				
				if(datosCliente.length()>0) {
					for(int i=0;i<list.size();i++){
						Map m=(Map)list.get(i);
						String codigoCliente=(String)m.get("codigoCliente");
						String indicadorAccion = (String)m.get("indicadorAccion");
	
						if(StringUtils.equals(codigoCliente,f.getCodigoCliente()) &&
							!(Constants.NUMERO_DOS.equals(indicadorAccion) || Constants.MAV_ESTADO_TMP_ELIMINAR.equals(indicadorAccion)) ){
							this.addError("Error", this.getResourceMessage("mantenimientoMAVConfiguracionCargaClientesForm.error.consultoraDuplicada"));
							existe=true;
							break;
						}
					}
					
				} else {
					this.addError("Error", this.getResourceMessage("mantenimientoMAVConfiguracionCargaClientesForm.error.noExisteConsultora"));
					existe=true;
				}
				
				if(!existe) {
					StringTokenizer st = new StringTokenizer(datosCliente, "|");
					st.nextToken();
					map.put("nombreCliente", st.nextToken());
	
					list.add(map);
					
					f.setCodigoCliente("");
					f.setNombreCliente("");
					f.setNumeroUnidadesPopup("");
					
					this.consultoraList = list;
				}	
				
				actualizarTotalConsultoras(f, true);
				forward =Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo)?"viewListaCliente":"viewListaClienteRest";
				break;
			case Constants.MAV_CONRES_LISTA_REGION_ZONA:
				list =this.regionesList;
				map = new HashMap();
				map.put("numeroUnidades", f.getNumeroUnidadesPopup());
						
				List listUARegionZona = obtenerListaUA(f);
				if (StringUtils.isNotBlank(insertar)) {
					if (listUARegionZona == null || listUARegionZona.size() == 0) {
						if (Constants.SI.equals(insertar)) {
							this.addError("Error", this.getResourceMessage("mantenimientoMAVConfiguracionForm.selecccionarDato"));
						}
					}
				}
			
				if (StringUtils.equals(indicadorTipoSeleccion, Constants.INDICADOR_TIPO_SELECCION_CAPACITADORA_REGION)) {
					for(int j=0;j<listUARegionZona.size();j++) {
						Map mapUA = (Map)listUARegionZona.get(j);
						String codigoRegionAux = (String)mapUA.get("codigoRegion");
						String codigoZonaAux = (String)mapUA.get("codigoZona");
						
						mapUA.put("numeroUnidades", f.getNumeroUnidadesPopup());
						existe=false;
						
						for(int i=0;i<list.size();i++){
							Map m=(Map)list.get(i);
							String codigoRegion=(String)m.get("codigoRegion");
							String codigoZona=(String)m.get("codigoZona");
							
							String indicadorAccion = (String)m.get("indicadorAccion");
							
							if(StringUtils.equals(codigoRegion,codigoRegionAux) &&
							    StringUtils.equals(codigoZona,codigoZonaAux)	&&
							     		  !(Constants.NUMERO_DOS.equals(indicadorAccion) || Constants.MAV_ESTADO_TMP_ELIMINAR.equals(indicadorAccion))){
								
								if(listUARegionZona.size()==1) {
									this.addError("Error", this.getResourceMessage("mantenimientoMAVConfiguracionForm.existe.regionZona"));
								}
								
								existe=true;
								break;
							}
						}
						
						if(!existe) { 
							list.add(mapUA);				
						}
					}	
				}
				else {
					boolean mostrarMensajeExiste = true;
					for(int j=0;j<listUARegionZona.size();j++) {
						Map mapUA = (Map)listUARegionZona.get(j);
						String codigoCapacitadoranAux = (String)mapUA.get("codigoCapacitadora");
						mapUA.put("numeroUnidades", f.getNumeroUnidadesPopup());
						existe=false;
						
						for(int i=0;i<list.size();i++){
							Map m=(Map)list.get(i);
							String codigoCapacitadora=(String)m.get("codigoCapacitadora");
							String indicadorAccion = (String)m.get("indicadorAccion");
							
							if(StringUtils.equals(codigoCapacitadora,codigoCapacitadoranAux) &&
							     		  !(Constants.NUMERO_DOS.equals(indicadorAccion) || Constants.MAV_ESTADO_TMP_ELIMINAR.equals(indicadorAccion))){
							   existe=true;
							   break;
							}
						}
						
						if(!existe) { 
							list.add(mapUA);	
							mostrarMensajeExiste = false;
						}
					}	
					if (mostrarMensajeExiste) {
						this.addError("Error", this.getResourceMessage("mantenimientoMAVConfiguracionForm.existe.capacitadora"));
					}
				}
				
				if("S".equals(f.getIndicadorUnidadPopup()))
					f.setNumeroUnidadesPopup("");
				
				actualizarTotalUnidades(f, true);
				
				forward =Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo)?"viewListaRegionZona":"viewListaRegionZonaRest";
				break;
			case Constants.MAV_CONRES_CLASIFICACIONES_CLIENTE:
				list =(List)this.clasificacionesList;
				map = new HashMap();
				map.put("oidTipoCliente", f.getOidTipoClienteAux());
				map.put("oidSubTipoCliente", f.getOidSubTipoCliente());
				map.put("oidTipoClasificacion", f.getOidTipoClasificacion());
				map.put("oidClasificacion", f.getOidClasificacion());
				map.put("codigoTipoCliente", getCodigoTipocliente(f.getOidTipoClienteAux()));
				map.put("codigoSubTipoCliente", getCodigoSubtipoCliente(f.getOidTipoClienteAux(),f.getOidSubTipoCliente()));
				map.put("codigoTipoClasificacion",getCodigoTipoClasificacion(f.getOidTipoClienteAux(),f.getOidSubTipoCliente(),f.getOidTipoClasificacion()));
				map.put("codigoClasificacion", getCodigoClasificacion(f.getOidTipoClienteAux(),f.getOidSubTipoCliente(),f.getOidTipoClasificacion(),f.getOidClasificacion()));			
				map.put("descripcionTipoCliente", getDesTipocliente(f.getOidTipoClienteAux()));
				map.put("descripcionSubTipoCliente", getDesSubtipoCliente(f.getOidTipoClienteAux(),f.getOidSubTipoCliente()));
				map.put("descripcionTipoClasificacion", getDesTipoClasificacion(f.getOidTipoClienteAux(),f.getOidSubTipoCliente(),f.getOidTipoClasificacion()));
				map.put("descripcionClasificacion", getClasificacion(f.getOidTipoClienteAux(),f.getOidSubTipoCliente(),f.getOidTipoClasificacion(),f.getOidClasificacion()));
				//map.put("indicadorAccion",Constants.NRO_CERO);
				map.put("numeroUnidades", f.getNumeroUnidadesPopup());
				map.put("indicadorAccion",Constants.MAV_ESTADO_TMP_INSERTAR);
				existe=false;	
				for(int i=0;i<list.size();i++){
					Map m=(Map)list.get(i);
					String oidTipoCliente=(String)m.get("oidTipoCliente");
					String oidSubTipoCliente=(String)m.get("oidSubTipoCliente");
					String oidTipoClasificacion=(String)m.get("oidTipoClasificacion");
					String oidClasificacion=(String)m.get("oidClasificacion");
					String indicadorAccion = (String)m.get("indicadorAccion");
					if( StringUtils.equals(oidTipoCliente,f.getOidTipoClienteAux()) &&
							StringUtils.equals(oidSubTipoCliente,f.getOidSubTipoCliente())	&&
							StringUtils.equals(oidTipoClasificacion,f.getOidTipoClasificacion()) &&
							StringUtils.equals(oidClasificacion,f.getOidClasificacion()) &&
							!(Constants.NUMERO_DOS.equals(indicadorAccion) || Constants.MAV_ESTADO_TMP_ELIMINAR.equals(indicadorAccion)) ){
						this.addError("Error", this.getResourceMessage("mantenimientoMAVConfiguracionForm.existe.tipologia"));
						existe=true;
						break;
					}
				}
				
				if(!existe) { 
					list.add(map);				
				}			
				
				this.siccTipoClienteList = interfazSiCCService.getTiposClientesByCodigoISOOID(usuario.getIdioma().getCodigoISO());
				f.setOidSubTipoCliente(Constants.OID_SUBTIPO_CLIENTE_DEFAULT );
				f.setOidTipoClienteAux(Constants.OID_TIPO_CLIENTE_DEFAULT);
				f.setOidTipoClienteAux2(Constants.OID_TIPO_CLIENTE_DEFAULT);
				f.setOidTipoClasificacion(null);
				f.setOidClasificacion(null);
				f.setNumeroUnidadesPopup("");
				//recargarTipologiaClientes(f);	
				forward =Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo)?"viewClasificacionesCliente":"viewClasificacionesClienteRest";
				break;
			case Constants.MAV_CONRES_UNIDAD_ADM:
				list =this.unidadesList;
				List listUA = obtenerListaUA(f);
				if (StringUtils.isNotBlank(insertar)) {
					if (listUA == null || listUA.size() == 0) {
						if (Constants.SI.equals(insertar)) {
							this.addError("Error", this.getResourceMessage("mantenimientoMAVConfiguracionForm.selecccionarDato"));
						}
					}
				}
						
				for(int j=0;j<listUA.size();j++) {
					Map mapUA = (Map)listUA.get(j);
					String codigoRegionAux = (String)mapUA.get("codigoRegion");
					String codigoZonaAux = (String)mapUA.get("codigoZona");
					String codigoSeccionAux = (String)mapUA.get("codigoSeccion");
					String codigoTerritorioAux = (String)mapUA.get("codigoTerritorio");
					mapUA.put("numeroUnidades", f.getNumeroUnidadesPopup());
					existe=false;
					
					for(int i=0;i<list.size();i++){
						Map m=(Map)list.get(i);
						String codigoRegion=(String)m.get("codigoRegion");
						String codigoZona=(String)m.get("codigoZona");
						String codigoSeccion=(String)m.get("codigoSeccion");
						String codigoTerritorio=(String)m.get("codigoTerritorio");
						String indicadorAccion = (String)m.get("indicadorAccion");
						
						if(StringUtils.equals(codigoRegion,codigoRegionAux) &&
						    StringUtils.equals(codigoZona,codigoZonaAux)	&&
						      StringUtils.equals(codigoSeccion,codigoSeccionAux) &&
									StringUtils.equals(codigoTerritorio,codigoTerritorioAux) &&
						     		  !(Constants.NUMERO_DOS.equals(indicadorAccion) || Constants.MAV_ESTADO_TMP_ELIMINAR.equals(indicadorAccion))){
							
							if(listUA.size()==1) {
								this.addError("Error", this.getResourceMessage("mantenimientoMAVConfiguracionForm.existe.unidades"));
							}
							
						   existe=true;
						   break;
						}
					}
					
					if(!existe) { 
						list.add(mapUA);	
					}
				}	
									
				f.setNumeroUnidadesPopup("");
				actualizarTotalUnidadesUA(f, true);
				
				forward =Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo)?"viewUnidadAdministrativa":"viewUnidadAdministrativaRest";
				break;	
			case Constants.MAV_CONRES_ESTATUS_CLIENTE:			
				list =(List)this.codigoEstatusList;
				
				if (f.getEstadoList() == null || f.getEstadoList().length == 0) {
					if (Constants.SI.equals(insertar)) {
						throw new Exception(this.getResourceMessage("mantenimientoMAVConfiguracionForm.selecccionarDato"));
					}
				}
				
				if(f.getEstadoList()[0].equals("Todos")) {
					HashMap criteriaOperacion = new HashMap();
					criteriaOperacion.put("codigoPais", pais.getCodigo());
					List estadoList = reporteService.getListaGenerico("getEstadoSaldoConsultora",
									criteriaOperacion);
					
					for(int j=0;j<estadoList.size();j++) {
						map = new HashMap();
						map.put("indicadorAccion",Constants.MAV_ESTADO_TMP_INSERTAR);
						Base baseEstado = (Base)estadoList.get(j);
						
						String codigoEstadoAux = baseEstado.getCodigo();
						String descripcionEstado = baseEstado.getDescripcion();
						existe=false;	
						
						for(int i=0;i<list.size();i++){
							Map m=(Map)list.get(i);
							String codigoEstado=(String)m.get("codigoEstado");
							String indicadorAccion = (String)m.get("indicadorAccion");
							
							if( StringUtils.equals(codigoEstado,codigoEstadoAux) &&
									!(Constants.NUMERO_DOS.equals(indicadorAccion) || Constants.MAV_ESTADO_TMP_ELIMINAR.equals(indicadorAccion))){
								
								existe=true;
								break;
							}
						}
						
						if(!existe) { 
							map.put("codigoEstado", codigoEstadoAux);
							map.put("descripcionEstado", descripcionEstado);
							list.add(map);				
						}
					}	
					
				}
				else {
					
					for(int j=0;j<f.getEstadoList().length;j++) {
						map = new HashMap();
						map.put("indicadorAccion",Constants.MAV_ESTADO_TMP_INSERTAR);
						
						String codigoEstadoAux = f.getEstadoList()[j].split("__")[0];
						String descripcionEstado = f.getEstadoList()[j].split("__")[1];
						existe=false;	
						
						for(int i=0;i<list.size();i++){
							Map m=(Map)list.get(i);
							String codigoEstado=(String)m.get("codigoEstado");
							String indicadorAccion = (String)m.get("indicadorAccion");
							
							if( StringUtils.equals(codigoEstado,codigoEstadoAux) &&
									!(Constants.NUMERO_DOS.equals(indicadorAccion) || Constants.MAV_ESTADO_TMP_ELIMINAR.equals(indicadorAccion))){
								
								if(f.getEstadoList().length==1) {
									this.addError("Error", this.getResourceMessage("mantenimientoMAVConfiguracionForm.existe.estatus"));
								}
								
								existe=true;
								break;
							}
						}
						
						if(!existe) { 
							map.put("codigoEstado", codigoEstadoAux);
							map.put("descripcionEstado", descripcionEstado);
							list.add(map);				
						}
					}
			
				}
				
				forward =Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo)?"viewEstatusCliente":"viewEstatusClienteRest";
				break;
				
		
			case Constants.MAV_CONRES_LISTA_CONSU_REST:
				list =this.consultoraListR;
				
				map = new HashMap();
				map.put("codigoPais", pais.getCodigo());
				map.put("codigoCliente", f.getCodigoCliente());
				//map.put("indicadorAccion",Constants.NRO_CERO);
				map.put("numeroUnidades", f.getNumeroUnidadesPopup());
				map.put("indicadorAccion",Constants.MAV_ESTADO_TMP_INSERTAR);
				
				oidPais = obtenerOidPais(pais.getCodigo());
				datosCliente = ajaxService.getExisteCodigoCliente(oidPais, f.getCodigoCliente());
				existe=false;
				
				if(datosCliente.length()>0) {
					for(int i=0;i<list.size();i++){
						Map m=(Map)list.get(i);
						String codigoCliente=(String)m.get("codigoCliente");
						String indicadorAccion = (String)m.get("indicadorAccion");
	
						if(StringUtils.equals(codigoCliente,f.getCodigoCliente()) &&
						  !(Constants.NUMERO_DOS.equals(indicadorAccion) || Constants.MAV_ESTADO_TMP_ELIMINAR.equals(indicadorAccion)) ){
							this.addError("Error", this.getResourceMessage("mantenimientoMAVConfiguracionCargaClientesForm.error.consultoraDuplicada"));
							existe=true;
							break;
						}
					}
					
				} else {
					this.addError("Error", this.getResourceMessage("mantenimientoMAVConfiguracionCargaClientesForm.error.noExisteConsultora"));
					existe=true;
				}
				
				if(!existe) { 
					StringTokenizer st = new StringTokenizer(datosCliente, "|");
					st.nextToken();
					map.put("nombreCliente", st.nextToken());
					
					list.add(map);
					
					f.setCodigoCliente("");
					f.setNombreCliente("");
					f.setNumeroUnidadesPopup("");
					
					this.consultoraListR = list;
				}
				
				actualizarTotalConsultoras(f, false);
				forward =Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo)?"viewListaCliente":"viewListaClienteRest";
				break;
			case Constants.MAV_CONRES_LISTA_REGION_ZONA_REST:
				list =this.regionesListR;
				map = new HashMap();
				map.put("numeroUnidades", f.getNumeroUnidadesPopup());
				
				List listUARegion = obtenerListaUA(f);
				if (StringUtils.isNotBlank(insertar)) {
					if (listUARegion == null || listUARegion.size() == 0) {
						if (Constants.SI.equals(insertar)) {
							this.addError("Error", this.getResourceMessage("mantenimientoMAVConfiguracionForm.selecccionarDato"));
						}
					}
				}
				if (StringUtils.equals(indicadorTipoSeleccion, Constants.INDICADOR_TIPO_SELECCION_CAPACITADORA_REGION)) {
					for(int j=0;j<listUARegion.size();j++) {
						Map mapUA = (Map)listUARegion.get(j);
						String codigoRegionAux = (String)mapUA.get("codigoRegion");
						String codigoZonaAux = (String)mapUA.get("codigoZona");
						
						mapUA.put("numeroUnidades", f.getNumeroUnidadesPopup());
						existe=false;
						
						for(int i=0;i<list.size();i++){
							Map m=(Map)list.get(i);
							String codigoRegion=(String)m.get("codigoRegion");
							String codigoZona=(String)m.get("codigoZona");
							
							String indicadorAccion = (String)m.get("indicadorAccion");
							
							if(StringUtils.equals(codigoRegion,codigoRegionAux) &&
							    StringUtils.equals(codigoZona,codigoZonaAux)	&&
							     		  !(Constants.NUMERO_DOS.equals(indicadorAccion) || Constants.MAV_ESTADO_TMP_ELIMINAR.equals(indicadorAccion))){
								
								if(listUARegion.size()==1) {
									this.addError("Error", this.getResourceMessage("mantenimientoMAVConfiguracionForm.existe.regionZona"));
								}
								
								existe=true;
								break;
							}
						}
						
						if(!existe) { 
							list.add(mapUA);				
						}
					}	
				}
				else {
					boolean mostrarMensajeExiste = true;
					for(int j=0;j<listUARegion.size();j++) {
						Map mapUA = (Map)listUARegion.get(j);
						String codigoCapacitadoranAux = (String)mapUA.get("codigoCapacitadora");
						mapUA.put("numeroUnidades", f.getNumeroUnidadesPopup());
						existe=false;
						
						for(int i=0;i<list.size();i++){
							Map m=(Map)list.get(i);
							String codigoCapacitadora=(String)m.get("codigoCapacitadora");
							String indicadorAccion = (String)m.get("indicadorAccion");
							
							if(StringUtils.equals(codigoCapacitadora,codigoCapacitadoranAux) &&
							     		  !(Constants.NUMERO_DOS.equals(indicadorAccion) || Constants.MAV_ESTADO_TMP_ELIMINAR.equals(indicadorAccion))){
							   existe=true;
							   break;
							}
						}
						
						if(!existe) { 
							list.add(mapUA);	
							mostrarMensajeExiste = false;
						}
					}	
					if (mostrarMensajeExiste) {
						this.addError("Error", this.getResourceMessage("mantenimientoMAVConfiguracionForm.existe.capacitadora"));
					}
				}
				f.setNumeroUnidadesPopup("");
				actualizarTotalUnidades(f, false);
				
				forward =Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo)?"viewListaRegionZona":"viewListaRegionZonaRest";
				break;			
			case Constants.MAV_CONRES_CLASIFICACIONES_CLIENTE_REST:
				list =this.clasificacionesListR;
				map = new HashMap();
				map.put("oidTipoCliente", f.getOidTipoClienteAux());
				map.put("oidSubTipoCliente", f.getOidSubTipoCliente());
				map.put("oidTipoClasificacion", f.getOidTipoClasificacion());
				map.put("oidClasificacion", f.getOidClasificacion());
				map.put("codigoTipoCliente", getCodigoTipocliente(f.getOidTipoClienteAux()));
				map.put("codigoSubTipoCliente", getCodigoSubtipoCliente(f.getOidTipoClienteAux(),f.getOidSubTipoCliente()));
				map.put("codigoTipoClasificacion",getCodigoTipoClasificacion(f.getOidTipoClienteAux(),f.getOidSubTipoCliente(),f.getOidTipoClasificacion()));
				map.put("codigoClasificacion", getCodigoClasificacion(f.getOidTipoClienteAux(),f.getOidSubTipoCliente(),f.getOidTipoClasificacion(),f.getOidClasificacion()));			
				map.put("descripcionTipoCliente", getDesTipocliente(f.getOidTipoClienteAux()));
				map.put("descripcionSubTipoCliente", getDesSubtipoCliente(f.getOidTipoClienteAux(),f.getOidSubTipoCliente()));
				map.put("descripcionTipoClasificacion", getDesTipoClasificacion(f.getOidTipoClienteAux(),f.getOidSubTipoCliente(),f.getOidTipoClasificacion()));
				map.put("descripcionClasificacion", getClasificacion(f.getOidTipoClienteAux(),f.getOidSubTipoCliente(),f.getOidTipoClasificacion(),f.getOidClasificacion()));
				//map.put("indicadorAccion",Constants.NRO_CERO);
				map.put("numeroUnidades", f.getNumeroUnidadesPopup());
				map.put("indicadorAccion",Constants.MAV_ESTADO_TMP_INSERTAR);
				existe=false;
				for(int i=0;i<list.size();i++){
					Map m=(Map)list.get(i);
					String oidTipoCliente=(String)m.get("oidTipoCliente");
					String oidSubTipoCliente=(String)m.get("oidSubTipoCliente");
					String oidTipoClasificacion=(String)m.get("oidTipoClasificacion");
					String oidClasificacion=(String)m.get("oidClasificacion");
					String indicadorAccion = (String)m.get("indicadorAccion");
					
					if( StringUtils.equals(oidTipoCliente,f.getOidTipoClienteAux()) &&
							StringUtils.equals(oidSubTipoCliente,f.getOidSubTipoCliente())	&&
							StringUtils.equals(oidTipoClasificacion,f.getOidTipoClasificacion()) &&
							StringUtils.equals(oidClasificacion,f.getOidClasificacion()) &&
							!(Constants.NUMERO_DOS.equals(indicadorAccion) || Constants.MAV_ESTADO_TMP_ELIMINAR.equals(indicadorAccion))){
						this.addError("Error", this.getResourceMessage("mantenimientoMAVConfiguracionForm.existe.tipologia"));
						existe=true;
						break;					
					}
				}
				
				if(!existe) { 
					list.add(map);				
				}				
	
				this.siccTipoClienteList = interfazSiCCService.getTiposClientesByCodigoISOOID(usuario.getIdioma().getCodigoISO());
				f.setOidSubTipoCliente(Constants.OID_SUBTIPO_CLIENTE_DEFAULT );
				f.setOidTipoClienteAux(Constants.OID_TIPO_CLIENTE_DEFAULT);
				f.setOidTipoClienteAux2(Constants.OID_TIPO_CLIENTE_DEFAULT);
				f.setOidTipoClasificacion(null);
				f.setOidClasificacion(null);
				f.setNumeroUnidadesPopup("");
				//recargarTipologiaClientes(f);				
							
				forward =Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo)?"viewClasificacionesCliente":"viewClasificacionesClienteRest";
				break;
			case Constants.MAV_CONRES_UNIDAD_ADM_REST:
				list =this.unidadesListR;
				List listUAR = obtenerListaUA(f);
				
				if (StringUtils.isNotBlank(insertar)) {
					if (listUAR == null || listUAR.size() == 0) {
						if (Constants.SI.equals(insertar)) {
							this.addError("Error", this.getResourceMessage("mantenimientoMAVConfiguracionForm.selecccionarDato"));
						}
					}
				}
				
				for(int j=0;j<listUAR.size();j++) {
					Map mapUA = (Map)listUAR.get(j);
					String codigoRegionAux = (String)mapUA.get("codigoRegion");
					String codigoZonaAux = (String)mapUA.get("codigoZona");
					String codigoSeccionAux = (String)mapUA.get("codigoSeccion");
					String codigoTerritorioAux = (String)mapUA.get("codigoTerritorio");
					existe=false;
					
					for(int i=0;i<list.size();i++){
						Map m=(Map)list.get(i);
						String codigoRegion=(String)m.get("codigoRegion");
						String codigoZona=(String)m.get("codigoZona");
						String codigoSeccion=(String)m.get("codigoSeccion");
						String codigoTerritorio=(String)m.get("codigoTerritorio");
						String indicadorAccion = (String)m.get("indicadorAccion");
						
						if(StringUtils.equals(codigoRegion,codigoRegionAux) &&
						    StringUtils.equals(codigoZona,codigoZonaAux)	&&
						      StringUtils.equals(codigoSeccion,codigoSeccionAux) &&
									StringUtils.equals(codigoTerritorio,codigoTerritorioAux) &&
						     		  !(Constants.NUMERO_DOS.equals(indicadorAccion) || Constants.MAV_ESTADO_TMP_ELIMINAR.equals(indicadorAccion))){
							
							if(listUAR.size()==1) {
								this.addError("Error", this.getResourceMessage("mantenimientoMAVConfiguracionForm.existe.unidades"));
							}
							
						   existe=true;
						   break;
						}
					}
					
					if(!existe) { 
						list.add(mapUA);	
					}
				}	
				
				f.setNumeroUnidadesPopup("");
				actualizarTotalUnidadesUA(f, false);
				forward =Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo)?"viewUnidadAdministrativa":"viewUnidadAdministrativaRest";
				break;	
			case Constants.MAV_CONRES_ESTATUS_CLIENTE_REST:
				list =this.codigoEstatusListR;
				
				/*if (f.getEstadoList() == null || f.getEstadoList().length == 0) {
					if (Constants.SI.equals(insertar)) {
						this.addError("Error", this.getResourceMessage("mantenimientoMAVConfiguracionForm.selecccionarDato"));
						
					}
				}*/
				
				if(f.getEstadoList()[0].equals("Todos")) {
					HashMap criteriaOperacion = new HashMap();
					criteriaOperacion.put("codigoPais", pais.getCodigo());
					List estadoList = reporteService.getListaGenerico("getEstadoSaldoConsultora",
									criteriaOperacion);
					
					for(int j=0;j<estadoList.size();j++) {
						map = new HashMap();
						map.put("indicadorAccion",Constants.MAV_ESTADO_TMP_INSERTAR);
						Base baseEstado = (Base)estadoList.get(j);
						
						String codigoEstadoAux = baseEstado.getCodigo();
						String descripcionEstado = baseEstado.getDescripcion();
						existe=false;	
						
						for(int i=0;i<list.size();i++){
							Map m=(Map)list.get(i);
							String codigoEstado=(String)m.get("codigoEstado");
							String indicadorAccion = (String)m.get("indicadorAccion");
							
							if( StringUtils.equals(codigoEstado,codigoEstadoAux) &&
									!(Constants.NUMERO_DOS.equals(indicadorAccion) || Constants.MAV_ESTADO_TMP_ELIMINAR.equals(indicadorAccion))){
								
								existe=true;
								break;
							}
						}
						
						if(!existe) { 
							map.put("codigoEstado", codigoEstadoAux);
							map.put("descripcionEstado", descripcionEstado);
							list.add(map);				
						}
					}	
					
				}
				else {
					
					for(int j=0;j<f.getEstadoList().length;j++) {
						map = new HashMap();
						map.put("indicadorAccion",Constants.MAV_ESTADO_TMP_INSERTAR);
						
						String codigoEstadoAux = f.getEstadoList()[j].split("__")[0];
						String descripcionEstado = f.getEstadoList()[j].split("__")[1];
						existe=false;	
						
						for(int i=0;i<list.size();i++){
							Map m=(Map)list.get(i);
							String codigoEstado=(String)m.get("codigoEstado");
							String indicadorAccion = (String)m.get("indicadorAccion");
							if( StringUtils.equals(codigoEstado,codigoEstadoAux)
								 &&	!Constants.NUMERO_DOS.equals(indicadorAccion) ){
								
								if(f.getEstadoList().length==1) {
									this.addError("Error", this.getResourceMessage("mantenimientoMAVConfiguracionForm.existe.estatus"));
									
								}	
								
								existe=true;
								break;
							}
						}
						
						if(!existe) { 
							map.put("codigoEstado", codigoEstadoAux);
							map.put("descripcionEstado", descripcionEstado);
							list.add(map);				
						}		
					}
				}
				
				forward =Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo)?"viewEstatusCliente":"viewEstatusClienteRest";
				break;			
			}
			
			if(Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo))
				this.dataTableDetalleConsideracion = new DataTableModel(list);
			else
				this.dataTableDetalleRestriccion = new DataTableModel(list);
			
			log.debug("forward "+forward);
			//return mapping.findForward(forward);
			
		} catch (Exception e) {
			addError("Error", obtieneMensajeErrorException(e));
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
	public void deletePopup(ActionEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'deletePopup' method");
		}	
		
		try {
			MantenimientoMAVConfiguracionForm f = (MantenimientoMAVConfiguracionForm) this.formMantenimiento;
			String codigoConsRest=this.codigoConsRest;
			String indicadorTipo=this.indicadorTipo;

			int codigo =Integer.parseInt(codigoConsRest);
			Map map = null;
			
			//VALIDACIONES <INICIO>
			if(Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo)) {
				if (this.beanRegistroDetalleConsideracion == null) {
	    			mostrarVentanaAlerta("errors.select.item");
					return;
				}
				
				map = (Map) this.beanRegistroDetalleConsideracion;
				
			} else {
				if (this.beanRegistroDetalleRestriccion == null) {
	    			mostrarVentanaAlerta("errors.select.item");
					return;
				}
				
				map = (Map) this.beanRegistroDetalleRestriccion;
			}	
			//VALIDACIONES <FIN>
			
			String forward="";
			List list=null;
			
			switch (codigo) {	
			case Constants.MAV_CONRES_LISTA_CONSU:
				list = this.consultoraList;
				//map.put("indicadorAccion", Constants.NUMERO_DOS);	
				String indicadorAcion = (String)map.get("indicadorAccion");
				map.put("indicadorAccionAnterior",indicadorAcion);//guardo el estado anterior por si ocurre una cancelacion			
				map.put("indicadorAccion",Constants.MAV_ESTADO_TMP_ELIMINAR);				
				
				actualizarTotalConsultoras(f, true);
				forward =Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo)?"viewListaCliente":"viewListaClienteRest";
				break;
			case Constants.MAV_CONRES_LISTA_REGION_ZONA: //<OJO>
				list = this.regionesList;
				/*if(f.getSelectedItemsPopup() != null && f.getSelectedItemsPopup().length > 0)
				{
					String[] ids = f.getSelectedItemsPopup();
					for(int i=0; i<ids.length; i++)
					{
						index = Integer.parseInt(ids[i])-1;
						map = (Map)list.get(index);
						//map.put("indicadorAccion", Constants.NUMERO_DOS);	
						indicadorAcion = (String)map.get("indicadorAccion");
						map.put("indicadorAccionAnterior",indicadorAcion);//guardo el estado anterior por si ocurre una cancelacion			
						map.put("indicadorAccion",Constants.MAV_ESTADO_TMP_ELIMINAR);
					}
				}*/
				
				//map.put("indicadorAccion", Constants.NUMERO_DOS);		
				indicadorAcion = (String)map.get("indicadorAccion");
				map.put("indicadorAccionAnterior",indicadorAcion);//guardo el estado anterior por si ocurre una cancelacion			
				map.put("indicadorAccion",Constants.MAV_ESTADO_TMP_ELIMINAR);
				
				actualizarTotalUnidades(f, true);
				forward =Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo)?"viewListaRegionZona":"viewListaRegionZonaRest";
				break;	
			case Constants.MAV_CONRES_CLASIFICACIONES_CLIENTE:
				list = this.clasificacionesList;
				//map.put("indicadorAccion", Constants.NUMERO_DOS);	
				indicadorAcion = (String)map.get("indicadorAccion");
				map.put("indicadorAccionAnterior",indicadorAcion);//guardo el estado anterior por si ocurre una cancelacion			
				map.put("indicadorAccion",Constants.MAV_ESTADO_TMP_ELIMINAR);
				forward =Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo)?"viewClasificacionesCliente":"viewClasificacionesClienteRest";
				break;
			case Constants.MAV_CONRES_UNIDAD_ADM:
				list = this.unidadesList;
				//map.put("indicadorAccion", Constants.NUMERO_DOS);		
				indicadorAcion = (String)map.get("indicadorAccion");
				map.put("indicadorAccionAnterior",indicadorAcion);//guardo el estado anterior por si ocurre una cancelacion			
				map.put("indicadorAccion",Constants.MAV_ESTADO_TMP_ELIMINAR);
				actualizarTotalUnidadesUA(f, true);
				forward =Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo)?"viewUnidadAdministrativa":"viewUnidadAdministrativaRest";
				break;	
			case Constants.MAV_CONRES_ESTATUS_CLIENTE:
				list = this.codigoEstatusList;
				//map.put("indicadorAccion", Constants.NUMERO_DOS);
				indicadorAcion = (String)map.get("indicadorAccion");
				map.put("indicadorAccionAnterior",indicadorAcion);//guardo el estado anterior por si ocurre una cancelacion
				map.put("indicadorAccion",Constants.MAV_ESTADO_TMP_ELIMINAR);
				forward =Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo)?"viewEstatusCliente":"viewEstatusClienteRest";
				break;
					
			case Constants.MAV_CONRES_LISTA_CONSU_REST:			
				list = this.consultoraListR;
				//map.put("indicadorAccion", Constants.NUMERO_DOS);	
				indicadorAcion = (String)map.get("indicadorAccion");
				map.put("indicadorAccionAnterior",indicadorAcion);//guardo el estado anterior por si ocurre una cancelacion			
				map.put("indicadorAccion",Constants.MAV_ESTADO_TMP_ELIMINAR);	
				
				actualizarTotalConsultoras(f, false);
				forward =Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo)?"viewListaCliente":"viewListaClienteRest";
				break;
			case Constants.MAV_CONRES_LISTA_REGION_ZONA_REST:
				list =this.regionesListR;
				//map.put("indicadorAccion", Constants.NUMERO_DOS);	
				indicadorAcion = (String)map.get("indicadorAccion");
				map.put("indicadorAccionAnterior",indicadorAcion);//guardo el estado anterior por si ocurre una cancelacion			
				map.put("indicadorAccion",Constants.MAV_ESTADO_TMP_ELIMINAR);
				actualizarTotalUnidades(f, false);
				forward =Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo)?"viewListaRegionZona":"viewListaRegionZonaRest";			
			case Constants.MAV_CONRES_CLASIFICACIONES_CLIENTE_REST:
				list = this.clasificacionesListR;
				//map.put("indicadorAccion", Constants.NUMERO_DOS);
				indicadorAcion = (String)map.get("indicadorAccion");
				map.put("indicadorAccionAnterior",indicadorAcion);//guardo el estado anterior por si ocurre una cancelacion			
				map.put("indicadorAccion",Constants.MAV_ESTADO_TMP_ELIMINAR);
				forward =Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo)?"viewClasificacionesCliente":"viewClasificacionesClienteRest";
				break;
			case Constants.MAV_CONRES_UNIDAD_ADM_REST:
				list = (List)this.unidadesListR;
				//map.put("indicadorAccion", Constants.NUMERO_DOS);
				indicadorAcion = (String)map.get("indicadorAccion");
				map.put("indicadorAccionAnterior",indicadorAcion);//guardo el estado anterior por si ocurre una cancelacion			
				map.put("indicadorAccion",Constants.MAV_ESTADO_TMP_ELIMINAR);
				actualizarTotalUnidadesUA(f, false);
				forward =Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo)?"viewUnidadAdministrativa":"viewUnidadAdministrativaRest";
				break;			
			case Constants.MAV_CONRES_ESTATUS_CLIENTE_REST:
				list = this.codigoEstatusListR;
				//map.put("indicadorAccion", Constants.NUMERO_DOS);	
				indicadorAcion = (String)map.get("indicadorAccion");
				map.put("indicadorAccionAnterior",indicadorAcion);//guardo el estado anterior por si ocurre una cancelacion			
				map.put("indicadorAccion",Constants.MAV_ESTADO_TMP_ELIMINAR);
				forward =Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo)?"viewEstatusCliente":"viewEstatusClienteRest";
				break;			
			}
			
			if(Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo))
				this.dataTableDetalleConsideracion = new DataTableModel(list);
			else
				this.dataTableDetalleRestriccion = new DataTableModel(list);
			
			log.debug("forward "+forward);
			//return mapping.findForward(forward);

		} catch (Exception e) {
			addError("Error", obtieneMensajeErrorException(e));
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
	public void cancelarPopup(ActionEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'cancelarPopup' method");
		}	
		
		try {
			MantenimientoMAVConfiguracionForm f = (MantenimientoMAVConfiguracionForm) this.formMantenimiento;
			String codigoConsRest=this.codigoConsRest;
			String indicadorTipo=this.indicadorTipo;	
			this.indicadorCerrarVentana = Constants.NUMERO_DOS;
					
			int codigo =Integer.parseInt(codigoConsRest);
			log.debug("codigo "+codigo + " tipo "+indicadorTipo);
			String forward="";
			//List list=null;
			switch (codigo) {
	
			case Constants.MAV_CONRES_LISTA_CONSU:	
				List list =this.consultoraList;
				if(list != null) {
				for(int i=list.size()-1;i>=0;i--){
					Map m=(Map)list.get(i);
					String indicadorAccion = (String) m.get("indicadorAccion");
					String indicadorAcionAnt = (String)m.get("indicadorAccionAnterior");
					if(Constants.MAV_ESTADO_TMP_INSERTAR.equals(indicadorAccion) || 
							Constants.MAV_ESTADO_TMP_INSERTAR.equals(indicadorAcionAnt)){
					  list.remove(i);
					}
					if(Constants.MAV_ESTADO_TMP_ELIMINAR.equals(indicadorAccion)){
						m.put("indicadorAccion", indicadorAcionAnt);
					}				
				}
				}			
				forward =Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo)?"viewListaCliente":"viewListaClienteRest";
				break;
			case Constants.MAV_CONRES_LISTA_REGION_ZONA:	
				list =this.regionesList;
				if(list != null) {
				for(int i=list.size()-1;i>=0;i--){
					Map m=(Map)list.get(i);
					String indicadorAccion = (String) m.get("indicadorAccion");
					String indicadorAcionAnt = (String)m.get("indicadorAccionAnterior");
					if(Constants.MAV_ESTADO_TMP_INSERTAR.equals(indicadorAccion) || 
							Constants.MAV_ESTADO_TMP_INSERTAR.equals(indicadorAcionAnt)){
					  list.remove(i);
					}
					if(Constants.MAV_ESTADO_TMP_ELIMINAR.equals(indicadorAccion)){
						m.put("indicadorAccion", indicadorAcionAnt);
					}				
				}
				}			
				forward =Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo)?"viewListaRegionZona":"viewListaRegionZonaRest";
				break;	
			case Constants.MAV_CONRES_CLASIFICACIONES_CLIENTE:
				list =this.clasificacionesList;
				if(list != null) {
				for(int i=list.size()-1;i>=0;i--){
					Map m=(Map)list.get(i);
					String indicadorAccion = (String) m.get("indicadorAccion");
					String indicadorAcionAnt = (String)m.get("indicadorAccionAnterior");
					if(Constants.MAV_ESTADO_TMP_INSERTAR.equals(indicadorAccion) || 
							Constants.MAV_ESTADO_TMP_INSERTAR.equals(indicadorAcionAnt)){
					  list.remove(i);
					}
					if(Constants.MAV_ESTADO_TMP_ELIMINAR.equals(indicadorAccion)){
						m.put("indicadorAccion", indicadorAcionAnt);
					}
				}
				}
				forward =Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo)?"viewClasificacionesCliente":"viewClasificacionesClienteRest";
				break;
			case Constants.MAV_CONRES_UNIDAD_ADM:
				list =this.unidadesList;
				if(list != null) {
				for(int i=list.size()-1;i>=0;i--){
					Map m=(Map)list.get(i);
					String indicadorAccion = (String) m.get("indicadorAccion");
					String indicadorAcionAnt = (String)m.get("indicadorAccionAnterior");
					if(Constants.MAV_ESTADO_TMP_INSERTAR.equals(indicadorAccion) || 
							Constants.MAV_ESTADO_TMP_INSERTAR.equals(indicadorAcionAnt)){
					  list.remove(i);
					}
					if(Constants.MAV_ESTADO_TMP_ELIMINAR.equals(indicadorAccion)){
						m.put("indicadorAccion", indicadorAcionAnt);
					}
				}			
				}
				forward =Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo)?"viewUnidadAdministrativa":"viewUnidadAdministrativaRest";
				break;	
			case Constants.MAV_CONRES_ESTATUS_CLIENTE:			
				list =this.codigoEstatusList;
				if(list != null) {
				for(int i=list.size()-1;i>=0;i--){
					Map m=(Map)list.get(i);
					String indicadorAccion = (String) m.get("indicadorAccion");
					String indicadorAcionAnt = (String)m.get("indicadorAccionAnterior");
					if(Constants.MAV_ESTADO_TMP_INSERTAR.equals(indicadorAccion) || 
							Constants.MAV_ESTADO_TMP_INSERTAR.equals(indicadorAcionAnt)){
					  list.remove(i);
					}
					if(Constants.MAV_ESTADO_TMP_ELIMINAR.equals(indicadorAccion)){
						m.put("indicadorAccion", indicadorAcionAnt);
					}				
				}
				}
				forward =Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo)?"viewEstatusCliente":"viewEstatusClienteRest";
				break;
				
		
			case Constants.MAV_CONRES_LISTA_CONSU_REST:
				list =this.consultoraListR;
				if(list != null) {
				for(int i=list.size()-1;i>=0;i--){
					Map m=(Map)list.get(i);
					String indicadorAccion = (String) m.get("indicadorAccion");
					String indicadorAcionAnt = (String)m.get("indicadorAccionAnterior");
					if(Constants.MAV_ESTADO_TMP_INSERTAR.equals(indicadorAccion) || 
							Constants.MAV_ESTADO_TMP_INSERTAR.equals(indicadorAcionAnt)){
					  list.remove(i);
					}
					if(Constants.MAV_ESTADO_TMP_ELIMINAR.equals(indicadorAccion)){
						m.put("indicadorAccion", indicadorAcionAnt);
					}				
				}
				}					
				forward =Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo)?"viewListaCliente":"viewListaClienteRest";
				break;
			case Constants.MAV_CONRES_LISTA_REGION_ZONA_REST:	
				list =this.regionesListR;
				if(list != null) {
				for(int i=list.size()-1;i>=0;i--){
					Map m=(Map)list.get(i);
					String indicadorAccion = (String) m.get("indicadorAccion");
					String indicadorAcionAnt = (String)m.get("indicadorAccionAnterior");
					if(Constants.MAV_ESTADO_TMP_INSERTAR.equals(indicadorAccion) || 
							Constants.MAV_ESTADO_TMP_INSERTAR.equals(indicadorAcionAnt)){
					  list.remove(i);
					}
					if(Constants.MAV_ESTADO_TMP_ELIMINAR.equals(indicadorAccion)){
						m.put("indicadorAccion", indicadorAcionAnt);
					}				
				}
				}			
				forward =Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo)?"viewListaRegionZona":"viewListaRegionZonaRest";
				break;	
			case Constants.MAV_CONRES_CLASIFICACIONES_CLIENTE_REST:
				list =this.clasificacionesListR;
				if(list != null) {
				for(int i=list.size()-1;i>=0;i--){
					Map m=(Map)list.get(i);
					String indicadorAccion = (String) m.get("indicadorAccion");
					String indicadorAcionAnt = (String)m.get("indicadorAccionAnterior");
					if(Constants.MAV_ESTADO_TMP_INSERTAR.equals(indicadorAccion) || 
							Constants.MAV_ESTADO_TMP_INSERTAR.equals(indicadorAcionAnt)){
					  list.remove(i);
					}
					if(Constants.MAV_ESTADO_TMP_ELIMINAR.equals(indicadorAccion)){
						m.put("indicadorAccion", indicadorAcionAnt);
					}
				}						
				}						
				forward =Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo)?"viewClasificacionesCliente":"viewClasificacionesClienteRest";
				break;
			case Constants.MAV_CONRES_UNIDAD_ADM_REST:
				list =this.unidadesListR;
				if(list != null) {
				for(int i=list.size()-1;i>=0;i--){
					Map m=(Map)list.get(i);
					String indicadorAccion = (String) m.get("indicadorAccion");
					String indicadorAcionAnt = (String)m.get("indicadorAccionAnterior");
					if(Constants.MAV_ESTADO_TMP_INSERTAR.equals(indicadorAccion) || 
							Constants.MAV_ESTADO_TMP_INSERTAR.equals(indicadorAcionAnt)){
					  list.remove(i);
					}
					if(Constants.MAV_ESTADO_TMP_ELIMINAR.equals(indicadorAccion)){
						m.put("indicadorAccion", indicadorAcionAnt);
					}
				}
				}
				forward =Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo)?"viewUnidadAdministrativa":"viewUnidadAdministrativaRest";
				break;		
			case Constants.MAV_CONRES_ESTATUS_CLIENTE_REST:
				list =this.codigoEstatusListR;
				if(list != null) {
				for(int i=list.size()-1;i>=0;i--){
					Map m=(Map)list.get(i);
					String indicadorAccion = (String) m.get("indicadorAccion");
					String indicadorAcionAnt = (String)m.get("indicadorAccionAnterior");
					if(Constants.MAV_ESTADO_TMP_INSERTAR.equals(indicadorAccion) || 
							Constants.MAV_ESTADO_TMP_INSERTAR.equals(indicadorAcionAnt)){
					  list.remove(i);
					}
					if(Constants.MAV_ESTADO_TMP_ELIMINAR.equals(indicadorAccion)){
						m.put("indicadorAccion", indicadorAcionAnt);
					}
				}	
				}
				forward =Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo)?"viewEstatusCliente":"viewEstatusClienteRest";
				break;			
			}
			log.debug("forward xx "+forward);
			//return mapping.findForward(forward);
			
		} catch (Exception e) {
			addError("Error", obtieneMensajeErrorException(e));
		}
		
	}
	
	private void recargarTipologiaClientes(MantenimientoMAVConfiguracionForm f) {
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		log.debug("recargarTipologiaClientes ");
		
		ArrayList temp = new ArrayList();
		temp = new ArrayList();
		temp.add(f.getOidTipoClienteAux());
		this.siccSubTipoClienteList = aSvc.getSubTiposClientesPorPaisTipoClientesOID(usuario.getIdioma().getCodigoISO(), temp);

		temp = new ArrayList();
		temp.add(f.getOidSubTipoCliente());
		LabelValue[] listTiposClasificiones = aSvc.getTiposClasificacionesByCriteriaMultipleOID(usuario.getIdioma().getCodigoISO(),
									Constants.OID_TIPO_CLIENTE_DEFAULT, temp);
		this.siccTipoClasificacionList = listTiposClasificiones;
		
		/*f.setOidTipoClasificacion(listTiposClasificiones[0].getValue());
		
		ArrayList temp2 = new ArrayList();
		temp2 = new ArrayList();
		temp2.add(f.getOidTipoClasificacion());
		this.siccClasificacionList = aSvc.getClasificacionesByCriteriaMultipleOID(usuario.getIdioma().getCodigoISO(), 
										Constants.OID_TIPO_CLIENTE_DEFAULT, temp, temp2);*/
		
	}

	/**
	 * Obtiene la descripcion del tipo cliente
	 * @param request 
	 * @param oidTipoCliente
	 * @return
	 */
	private String getOidTipoCliente(String codigoTipoCliente) {
		String descp=getDesTipoclienteByCodigo(codigoTipoCliente);
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		List list = (List)interfazSiCCService.getTiposClientesByCodigoISOOID(usuario.getIdioma().getCodigoISO());
		Iterator it = list.iterator();
		while(it.hasNext()){
			BaseOID base=(BaseOID)it.next();
			if(StringUtils.equalsIgnoreCase(descp,base.getDescripcion()))
				return ""+base.getOid();
		}		
		return "";	
	}	
	
	/**
	 * obtiene la descripcion del 
	 * @param request 
	 * @param oidTipoCliente
	 * @param oidSubTipoCliente
	 * @return
	 */
	private String getOidSubtipoCliente(String codigoTipoCliente,
			String codigoSubTipoCliente) {
		//Usuario usuario = getUsuario(request);
		MantenimientoMENGenericoService service = (MantenimientoMENGenericoService) getBean("spusicc.mantenimientoMENGenericoService");	
		Map criteria = new HashMap();
		criteria.put("codigoTipoCliente",codigoTipoCliente);
		criteria.put("codigoSubTipoCliente",codigoSubTipoCliente);
		List list = (List)service.getSubTiposClientes(criteria);
		Map map= (Map)list.get(0);
		String oid = String.valueOf(map.get("oidSubTipoCliente"));
		return oid;	
		
	}
	
	/**
	 * Obtien la descripcion del tipo de clasificacion
	 * @param request 
	 * @param oidTipoCliente
	 * @param oidSubTipoCliente
	 * @param oidTipoClasificacion
	 * @return
	 */
	private String getOidTipoClasificacion(String codigoTipoCliente,
			String codigoSubTipoCliente, String codigoTipoClasificacion) {
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		MantenimientoMENGenericoService service = (MantenimientoMENGenericoService) getBean("spusicc.mantenimientoMENGenericoService");
		Map criteria = new HashMap();
		criteria.put("codigoTipoCliente",codigoTipoCliente);
		criteria.put("codigoSubTipoCliente",codigoSubTipoCliente);
		criteria.put("codigoTipoClasificacion",codigoTipoClasificacion);
		criteria.put("codigoISO", usuario.getIdioma().getCodigoISO());
		if(StringUtils.isNotEmpty(codigoTipoClasificacion)){
			List list = (List)service.getTiposClasificaciones(criteria);
			Map map= (Map)list.get(0);
			String oid =String.valueOf(map.get("oidTipoClasificacion"));
			return oid;
		}
		return "";		
	}
	

	/**
	 * Obtine la descripcion de clasificacion
	 * @param request 
	 * @param oidTipoCliente
	 * @param oidSubTipoCliente
	 * @param oidTipoClasificacion
	 * @param oidClasificacion
	 * @return
	 */
	private String getOidClasificacion(String codigoTipoCliente,
			String codigoSubTipoCliente, String codigoTipoClasificacion,
			String codigoClasificacion) {

		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		MantenimientoMENGenericoService service = (MantenimientoMENGenericoService) getBean("spusicc.mantenimientoMENGenericoService");
		Map criteria = new HashMap();
		criteria.put("codigoTipoCliente",codigoTipoCliente);
		criteria.put("codigoSubTipoCliente",codigoSubTipoCliente);
		criteria.put("codigoTipoClasificacion",codigoTipoClasificacion);
		criteria.put("codigoClasificacion", codigoClasificacion);
		criteria.put("codigoISO", usuario.getIdioma().getCodigoISO());

		if(StringUtils.isNotEmpty(codigoTipoClasificacion) && StringUtils.isNotEmpty(codigoClasificacion)){
			List list = (List)service.getClasificaciones(criteria);
			Map map= (Map)list.get(0);
			String oid = String.valueOf(map.get("oidClasificacion"));
			return oid;
		}
		return "";		
		
	}

	/**
	 * Obtiene la descripcion del tipo cliente
	 * @param request 
	 * @param oidTipoCliente
	 * @return
	 */
	private String getDesTipocliente(String oidTipoCliente) {
		log.debug("getDesTipocliente oidTipoCliente "+ oidTipoCliente);
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		List list = (List)interfazSiCCService.getTiposClientesByCodigoISOOID(usuario.getIdioma().getCodigoISO());
		Iterator it = list.iterator();
		while(it.hasNext()){
			BaseOID base=(BaseOID)it.next();
			if(oidTipoCliente.equals("" +base.getOid()))
				return base.getDescripcion();
		}		
		return "";	
	}

	/**
	 * Obtiene la descripcion del tipo cliente
	 * @param request 
	 * @param oidTipoCliente
	 * @return
	 */
	private String getDesTipoclienteByCodigo(String codigoTipoCliente) {
		log.debug("getDesTipoclienteByCodigo codigoTipoCliente "+ codigoTipoCliente);
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		List list = (List)interfazSiCCService.getTiposClientesByCodigoISO(usuario.getIdioma().getCodigoISO());
		Iterator it = list.iterator();
		while(it.hasNext()){
			Base base=(Base)it.next();
			if(codigoTipoCliente.equals(base.getCodigo()))
				return base.getDescripcion();
		}		
		return "";	
	}
	
	/**
	 * obtiene la descripcion del 
	 * @param request 
	 * @param oidTipoCliente
	 * @param oidSubTipoCliente
	 * @return
	 */
	private String getDesSubtipoCliente(String oidTipoCliente, String oidSubTipoCliente) {
		//Usuario usuario = getUsuario(request);
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Map criteria = new HashMap();
		criteria.put("oidTipoCliente",oidTipoCliente);
		criteria.put("oidSubTipoCliente",oidSubTipoCliente);
		List list = (List)interfazSiCCService.getSubTiposClientesByCriteria(criteria);
		Base base = (Base)list.get(0);
		return base.getDescripcion();	
		
	}
	
	/**
	 * Obtien la descripcion del tipo de clasificacion
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
		criteria.put("oidTipoCliente",oidTipoCliente);
		criteria.put("oidSubTipoCliente",oidSubTipoCliente);
		criteria.put("oidTipoClasificacion",oidTipoClasificacion);
		criteria.put("codigoISO", usuario.getIdioma().getCodigoISO());
		Base base=null;
		if(StringUtils.isNotEmpty(oidTipoClasificacion)){
			List list = (List)interfazSiCCService.getTiposClasificacionesByCriteria(criteria);
			base = (Base)list.get(0);
			return base.getDescripcion();
		}
		return "";		
	}
	

	/**
	 * Obtine la descripcion de clasificacion
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
		criteria.put("oidTipoCliente",oidTipoCliente);
		criteria.put("oidSubTipoCliente",oidSubTipoCliente);
		criteria.put("oidTipoClasificacion",oidTipoClasificacion);
		criteria.put("oidClasificacion", oidClasificacion);
		criteria.put("codigoISO", usuario.getIdioma().getCodigoISO());
		Base base=null;
		if(StringUtils.isNotEmpty(oidTipoClasificacion) && StringUtils.isNotEmpty(oidClasificacion)){
			List list = (List)interfazSiCCService.getClasificacionesByCriteria(criteria);
			base = (Base)list.get(0);
			return base.getDescripcion();
		}
		return "";		
		
	}

	/**
	 * devuelve la descripcion del territorio
	 * @param request
	 * @param codigoRegion
	 * @param codigoZona
	 * @param codigoSeccion
	 * @param codigoTerritorio
	 * @return
	 */
	private String getDesTerr(String codigoRegion,
			String codigoZona, String codigoSeccion, String codigoTerritorio) {
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		MantenimientoMENGenericoService service = (MantenimientoMENGenericoService) getBean("spusicc.mantenimientoMENGenericoService");
		Map criteria = new HashMap();
		criteria.put("codigoRegion",codigoRegion);
		criteria.put("codigoZona",codigoZona);
		criteria.put("codigoSeccion",codigoSeccion);
		criteria.put("codigoTerritorio", codigoTerritorio);
		criteria.put("codigoISO", usuario.getIdioma().getCodigoISO());
		if(StringUtils.isNotEmpty(codigoRegion) && StringUtils.isNotEmpty(codigoZona)
			&& StringUtils.isNotEmpty(codigoSeccion) && StringUtils.isNotEmpty(codigoTerritorio)){
			List list = (List)service.getTerritorio(criteria);
			Map map= (Map)list.get(0);
			String descripcion = String.valueOf(map.get("descripcion"));
			return descripcion;
		}
		return "";		
	}

	/**
	 * devulve la descripcin de la seccion
	 * @param request
	 * @param codigoRegion
	 * @param codigoZona
	 * @param codigoSeccion
	 * @return
	 */
	private String getDesSeccion(String codigoRegion, String codigoZona, String codigoSeccion) {
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		MantenimientoMENGenericoService service = (MantenimientoMENGenericoService) getBean("spusicc.mantenimientoMENGenericoService");
		Map criteria = new HashMap();
		criteria.put("codigoRegion",codigoRegion);
		criteria.put("codigoZona",codigoZona);
		criteria.put("codigoSeccion",codigoSeccion);
		criteria.put("codigoISO", usuario.getIdioma().getCodigoISO());
		if(StringUtils.isNotEmpty(codigoRegion) && StringUtils.isNotEmpty(codigoZona)
			&& StringUtils.isNotEmpty(codigoSeccion)){
			List list = (List)service.getSeccion(criteria);
			Map map= (Map)list.get(0);
			String descripcion = String.valueOf(map.get("descripcion"));
			return descripcion;
		}
		return "";		
	}

	/**
	 * devuleve la descripcion d ela zona
	 * @param request
	 * @param codigoRegion
	 * @param codigoZona
	 * @return
	 */
	private String getDesZona(String codigoRegion, String codigoZona) {
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		MantenimientoMENGenericoService service = (MantenimientoMENGenericoService) getBean("spusicc.mantenimientoMENGenericoService");
		Map criteria = new HashMap();
		criteria.put("codigoRegion",codigoRegion);
		criteria.put("codigoZona",codigoZona);
		criteria.put("codigoISO", usuario.getIdioma().getCodigoISO());
		if(StringUtils.isNotEmpty(codigoRegion) && StringUtils.isNotEmpty(codigoZona)){
			List list = (List)service.getZona(criteria);
			Map map= (Map)list.get(0);
			String descripcion = String.valueOf(map.get("descripcion"));
			return descripcion;
		}
		return "";		
	}

	/**
	 * devuleve la decripcion de la region
	 * @param request
	 * @param codigoRegion
	 * @return
	 */
	private String getDesRegion(String codigoRegion) {
		List listRegion=this.siccRegionList;
		Iterator it = listRegion.iterator();
		while(it.hasNext()){
			Base base = (Base)it.next();
			if(StringUtils.equals(codigoRegion, base.getCodigo()))
				return base.getDescripcion();
		}		
		return "";		
	}	
	
	
	/**
	 * devuelve la decripcion de la capacitadora
	 * @param request
	 * @param codigoCapacitadora
	 * @return
	 */
	private String getDesCapacitadora(String codigoCapacitadora) {
		List listRegion=this.siccCapacitadoraList;
		Iterator it = listRegion.iterator();
		while(it.hasNext()){
			Base base = (Base)it.next();
			if(StringUtils.equals(codigoCapacitadora, base.getCodigo()))
				return base.getDescripcion();
		}		
		return "";		
	}	

	/**
	 * Obtiene la descripcion del tipo cliente
	 * @param request 
	 * @param oidTipoCliente
	 * @return
	 */
	private String getCodigoTipocliente(String oidTipoCliente) {
		log.debug("getCodigoTipocliente oidTipoCliente "+ oidTipoCliente);
		String descp=getDesTipocliente(oidTipoCliente);
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		List list = (List)interfazSiCCService.getTiposClientesByCodigoISO(usuario.getIdioma().getCodigoISO());
		Iterator it = list.iterator();
		while(it.hasNext()){
			Base base=(Base)it.next();
			if(StringUtils.equalsIgnoreCase(descp,base.getDescripcion()))
				return base.getCodigo();
		}		
		return "";	
	}	
	
	/**
	 * obtiene la descripcion del 
	 * @param request 
	 * @param oidTipoCliente
	 * @param oidSubTipoCliente
	 * @return
	 */
	private String getCodigoSubtipoCliente(String oidTipoCliente, String oidSubTipoCliente) {
		//Usuario usuario = getUsuario(request);
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Map criteria = new HashMap();
		criteria.put("oidTipoCliente",oidTipoCliente);
		criteria.put("oidSubTipoCliente",oidSubTipoCliente);
		List list = (List)interfazSiCCService.getSubTiposClientesByCriteria(criteria);
		Base base = (Base)list.get(0);
		return base.getCodigo();	
		
	}
	
	/**
	 * Obtien la descripcion del tipo de clasificacion
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
		criteria.put("oidTipoCliente",oidTipoCliente);
		criteria.put("oidSubTipoCliente",oidSubTipoCliente);
		criteria.put("oidTipoClasificacion",oidTipoClasificacion);
		criteria.put("codigoISO", usuario.getIdioma().getCodigoISO());
		Base base=null;
		if(StringUtils.isNotEmpty(oidTipoClasificacion)){
			List list = (List)interfazSiCCService.getTiposClasificacionesByCriteria(criteria);
			base = (Base)list.get(0);
			return base.getCodigo();
		}
		return "";		
	}
	

	/**
	 * Obtine la descripcion de clasificacion
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
		criteria.put("oidTipoCliente",oidTipoCliente);
		criteria.put("oidSubTipoCliente",oidSubTipoCliente);
		criteria.put("oidTipoClasificacion",oidTipoClasificacion);
		criteria.put("oidClasificacion", oidClasificacion);
		criteria.put("codigoISO", usuario.getIdioma().getCodigoISO());
		Base base=null;
		if(StringUtils.isNotEmpty(oidTipoClasificacion) && StringUtils.isNotEmpty(oidClasificacion)){
			List list = (List)interfazSiCCService.getClasificacionesByCriteria(criteria);
			base = (Base)list.get(0);
			return base.getCodigo();
		}
		return "";		
		
	}

	private String obtenerOidPais(String codigoPais) {
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) 
														getBean("spusicc.mantenimientoMAEClienteService");
		
		Map crit = new HashMap();
        crit.put("codigoPais", codigoPais);
        
		String oidPais = clienteService.getOidPais(crit);	
		
		return oidPais;
	}

	/**
	 * devuelve la decripcion de la unidad de negocio
	 * @param request
	 * @param codigoRegion
	 * @return
	 */
	private String getDesUnidadNegocio(String codigoUnidadNegocio) {
		List listAux=this.incUnidadNegocioList;
		Iterator it = listAux.iterator();
		while(it.hasNext()){
			Base base = (Base)it.next();
			if(StringUtils.equals(codigoUnidadNegocio, base.getCodigo()))
				return base.getDescripcion();
		}		
		return "";		
	}
	
	/**
	 * devuelve la decripcion de negocio
	 * @param request
	 * @param codigoRegion
	 * @return
	 */
	private String getDesNegocio(String codigoNegocio) {
		List listAux=this.incNegocioList;
		Iterator it = listAux.iterator();
		while(it.hasNext()){
			Base base = (Base)it.next();
			if(StringUtils.equals(codigoNegocio, base.getCodigo()))
				return base.getDescripcion();
		}		
		return "";		
	}
	
	/**
	 * devuelve la decripcion de la marca producto
	 * @param request
	 * @param codigoRegion
	 * @return
	 */
	private String getDesMarcaProducto(String codigoMarcaProducto) {
		List listAux=this.incMarcaProductoList;
		Iterator it = listAux.iterator();
		while(it.hasNext()){
			Base base = (Base)it.next();
			if(StringUtils.equals(codigoMarcaProducto, base.getCodigo()))
				return base.getDescripcion();
		}		
		return "";		
	}
	
	/**
	 * devuelve la decripcion de catalogo
	 * @param request
	 * @param codigoRegion
	 * @return
	 */
	private String getDesCatalogo(String codigoCatalogo) {
		List listAux=this.recCodigoCatalogoList;
		Iterator it = listAux.iterator();
		while(it.hasNext()){
			Base base = (Base)it.next();
			if(StringUtils.equals(codigoCatalogo, base.getCodigo()))
				return base.getDescripcion();
		}		
		return "";		
	}
	
	/**
	 * Devuelve la decripcion de estado
	 * @param codigoEstado
	 * @param listEstado
	 * @return
	 */
	private String getDesEstado(String codigoEstado, List listEstado) {
		Iterator it = listEstado.iterator();
		while (it.hasNext()){
			Base base = (Base)it.next();
			String codigo = base.getCodigo();
			if(StringUtils.equals(codigo, codigoEstado)){
				return base.getDescripcion();
			}
		}
		return "";
	}
	
	/**
	 * Devuelve la decripcion de tipo de vebta
	 * @param request
	 * @param tipoVenta
	 * @return
	 */
	private String getDesTipoVenta(String tipoVenta) {
		List listAux=this.mavCodigoTipoVentaList;
		Iterator it = listAux.iterator();
		while(it.hasNext()){
			Base base = (Base)it.next();
			if(StringUtils.equals(tipoVenta, base.getCodigo()))
				return base.getDescripcion();
		}		
		return "";		
	}

	/**
	 * @param request
	 * @param f
	 * @return
	 */
	private List obtenerListaUA(MantenimientoMAVConfiguracionForm f) {
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		List listaUA = new ArrayList();
		String codigoPais = f.getCodigoPais();
		
		Map mapRegion = null;
		Map mapZona = null;
		Map mapSeccion = null;
		Map mapTerritorio = null;
		
		/* En caso sea Capacitadora */
		String indicadorTipoSeleccionCapacitadora = f.getIndicadorTipoSeleccionCapacitadora();
		if (StringUtils.equals(indicadorTipoSeleccionCapacitadora, Constants.INDICADOR_TIPO_SELECCION_CAPACITADORA_CAPACITADORA)) {
			String[] listaCapacitadora = f.getCapacitadoraListMultiple();
			if (listaCapacitadora == null)
				return listaUA;
			if(listaCapacitadora.length==1 && listaCapacitadora[0].equals("Todos")) {
				List regiones = this.siccCapacitadoraList;
				
				for(int i=0; i<regiones.size(); i++) {
					Base baseRegion = (Base)regiones.get(i);
					mapRegion = obtenerMapUACapacitadora(baseRegion.getCodigo(),"","","",
											 baseRegion.getDescripcion(),"","","",f.getNumeroUnidadesPopup());
					listaUA.add(mapRegion);
				}
				return listaUA;
			}
			
			for(int i=0; i<listaCapacitadora.length; i++) {
				mapRegion = obtenerMapUACapacitadora(listaCapacitadora[i],"","","",
						getDesCapacitadora(listaCapacitadora[i]),"","","",f.getNumeroUnidadesPopup());
				listaUA.add(mapRegion);
			}
			return listaUA;
			
		}
		
		//REGIONES CON ZONAS<TODOS>
		String[] listaRegion = f.getRegionListMultiple();
		if (listaRegion == null)
			return listaUA;
		if(f.getRegionListMultiple().length==1 && f.getRegionListMultiple()[0].equals("Todos")) {
			List regiones = this.siccRegionList;
			
			for(int i=0; i<regiones.size(); i++) {
				Base baseRegion = (Base)regiones.get(i);
				mapRegion = obtenerMapUA(baseRegion.getCodigo(),"","","",
										 baseRegion.getDescripcion(),"","","",f.getNumeroUnidadesPopup());
				listaUA.add(mapRegion);
			}
			
		} else {
			for(int i=0; i<f.getRegionListMultiple().length; i++) {
				ArrayList aRegionListMultiple = new ArrayList();
				for(int j=0;j<f.getRegionListMultiple().length;j++) {
					aRegionListMultiple.add(f.getRegionListMultiple()[j]);
				}

				boolean encontradoRegion = false;
				
				if(f.getZonaListMultiple() != null && f.getZonaListMultiple().length>0) {
					LabelValue[] zonas = ajaxService.getZonasMultipleByPaisMarcaCanalRegion( codigoPais, 
							Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, 
							new String[]{f.getRegionListMultiple()[i]}, "U");

					String codigoZonaPrimero = f.getZonaListMultiple()[0];
					if(f.getZonaListMultiple().length==1 && StringUtils.isBlank(codigoZonaPrimero)) {
						for(int j=0; j<zonas.length; j++) {
							mapZona = obtenerMapUA(f.getRegionListMultiple()[i],zonas[j].getValue(),"","",
													getDesRegion(f.getRegionListMultiple()[i]) ,
													zonas[j].getLabel(),"","",f.getNumeroUnidadesPopup());
							encontradoRegion = true;
							listaUA.add(mapZona);
						}
					} else {
						
						for(int j=0; j<f.getZonaListMultiple().length;j++) {
							boolean perteneceRegion = false;
							for(int k=0;k<zonas.length;k++) {
								if(f.getZonaListMultiple()[j].equals(zonas[k].getValue())) { 
									perteneceRegion=true;
									encontradoRegion = true;
									break;
								}
							}
							if(!perteneceRegion) {
								continue;
							}
								
							ArrayList aZonaList = new ArrayList();
							aZonaList.add(f.getZonaListMultiple()[j]);
							boolean encontradoZona = false;
							
							if(f.getSeccionListMultiple() != null && f.getSeccionListMultiple().length>0) {
								LabelValue[] secciones = ajaxService.getSeccionMultiple2ByPaisMarcaCanalRegionZona( codigoPais, 
										Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, 
										aRegionListMultiple, aZonaList, "U");
								
								if(f.getSeccionListMultiple().length==1 && f.getSeccionListMultiple()[0].equals("Todos")) {
									for(int k=0; k<secciones.length; k++) {
										mapSeccion = obtenerMapUA(f.getRegionListMultiple()[i],f.getZonaListMultiple()[j],
												secciones[k].getValue().split("__")[1],"",
												getDesRegion(f.getRegionListMultiple()[i]),
												getDesZona(f.getRegionListMultiple()[i], f.getZonaListMultiple()[j]),
												secciones[k].getLabel(),"",f.getNumeroUnidadesPopup());
										encontradoZona = true;
										listaUA.add(mapSeccion);
									}
								}  else {
									for(int k=0; k<f.getSeccionListMultiple().length;k++) {
										boolean perteneceZona = false;
										for(int l=0;l<secciones.length;l++) {
											if(f.getSeccionListMultiple()[k].equals(secciones[l].getValue())) { 
												perteneceZona=true;
												encontradoZona = true;
												break;
											}
										}
										if(!perteneceZona) {
											continue;
										}
										
										ArrayList aSeccionList = new ArrayList();
										aSeccionList.add(f.getSeccionListMultiple()[k]);
										String codigoSeccion = f.getSeccionListMultiple()[k].split("__")[1];
										boolean encontradoSeccion = false;
										
										if(f.getTerritorioListMultiple()!=null && f.getTerritorioListMultiple().length>0) {
											LabelValue[] territorios = ajaxService.getTerritoriosMultiple2ByPaisMarcaCanalRegionZonaSeccion( 
													codigoPais,Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, 
													aRegionListMultiple, aZonaList, aSeccionList, "U");
											
											if(f.getTerritorioListMultiple().length==1 && f.getTerritorioListMultiple()[0].equals("Todos")) {
												for(int l=0; l<territorios.length; l++) {
													mapTerritorio = obtenerMapUA(f.getRegionListMultiple()[i],
															f.getZonaListMultiple()[j],codigoSeccion,territorios[l].getValue(),
															getDesRegion(f.getRegionListMultiple()[i]),
															getDesZona(f.getRegionListMultiple()[i], f.getZonaListMultiple()[j]),
															getDesSeccion(f.getRegionListMultiple()[i], f.getZonaListMultiple()[j],
																	codigoSeccion),territorios[l].getLabel(),f.getNumeroUnidadesPopup());
													encontradoSeccion = true;
													listaUA.add(mapTerritorio);
												}
											}  else {
												for(int l=0; l<f.getTerritorioListMultiple().length;l++) {
													boolean perteneceSeccion = false;
													for(int m=0;m<territorios.length;m++) {
														if(f.getTerritorioListMultiple()[l].equals(territorios[m].getValue())) { 
															perteneceSeccion=true;
															encontradoSeccion = true;
															break;
														}
													}
													if(!perteneceSeccion) {
														continue;
													}
													
													mapTerritorio = obtenerMapUA(f.getRegionListMultiple()[i],
															f.getZonaListMultiple()[j],codigoSeccion,
															f.getTerritorioListMultiple()[l],
															getDesRegion(f.getRegionListMultiple()[i]),
															getDesZona(f.getRegionListMultiple()[i], f.getZonaListMultiple()[j]),
															getDesSeccion(f.getRegionListMultiple()[i], f.getZonaListMultiple()[j],
																	codigoSeccion),
															getDesTerr(f.getRegionListMultiple()[i], f.getZonaListMultiple()[j],
																	codigoSeccion, f.getTerritorioListMultiple()[l]), f.getNumeroUnidadesPopup());
													listaUA.add(mapTerritorio);
												}
												
											}
										}
										
										if(!encontradoSeccion) {
											mapSeccion = obtenerMapUA(f.getRegionListMultiple()[i],f.getZonaListMultiple()[j],
													codigoSeccion,"",
													getDesRegion(f.getRegionListMultiple()[i]),
													getDesZona(f.getRegionListMultiple()[i], f.getZonaListMultiple()[j]),
													getDesSeccion(f.getRegionListMultiple()[i], f.getZonaListMultiple()[j],
															codigoSeccion),"",f.getNumeroUnidadesPopup());
											listaUA.add(mapSeccion);
										}
										
									}
								}
							}
							
							if(!encontradoZona) {
								mapZona = obtenerMapUA(f.getRegionListMultiple()[i],f.getZonaListMultiple()[j],"","",
												getDesRegion(f.getRegionListMultiple()[i]),
												getDesZona(f.getRegionListMultiple()[i], f.getZonaListMultiple()[j]),
												"","",f.getNumeroUnidadesPopup());
								listaUA.add(mapZona);
							}

						}
					}
				}
				
				if(!encontradoRegion) {
					mapRegion = obtenerMapUA(f.getRegionListMultiple()[i],"","","",
							getDesRegion(f.getRegionListMultiple()[i]),"","","",f.getNumeroUnidadesPopup());
					listaUA.add(mapRegion);
				}

			}
		}	
		
		return listaUA;
	}

	private Map obtenerMapUA(String codigoRegion, String codigoZona, String codigoSeccion, String codigoTerritorio,
							String descripcionRegion, String descripcionZona, String descripcionSeccion, 
							String descripcionTerritorio, String numeroUnidades) {
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
		map.put("descripcionSeccion",descripcionSeccion );
		map.put("descripcionTerritorio", descripcionTerritorio);
		map.put("numeroUnidades", numeroUnidades);
		map.put("indicadorAccion",Constants.MAV_ESTADO_TMP_INSERTAR);
		
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
	private Map obtenerMapUACapacitadora(String codigoCapacitadora, String codigoZona, String codigoSeccion, String codigoTerritorio,
			String descripcionCapacitadora, String descripcionZona, String descripcionSeccion, 
			String descripcionTerritorio, String numeroUnidades) {
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
		map.put("descripcionSeccion",descripcionSeccion );
		map.put("descripcionTerritorio", descripcionTerritorio);
		map.put("numeroUnidades", numeroUnidades);
		map.put("indicadorAccion",Constants.MAV_ESTADO_TMP_INSERTAR);
		
		return map;
	}	

	private void actualizarTotalUnidades(MantenimientoMAVConfiguracionForm f, boolean esConsideracion) {
		List list = null;
		int totalUnidadesRegion = 0;
		int totalUnidadesZona = 0;
		int totalUnidadesCapacitadora = 0;
		
		int totalRegiones = 0;
		int totalZonas = 0;
		int totalCapacitadoras = 0;
		
		if(esConsideracion)
			list = this.regionesList;
		else
			list = this.regionesListR;

		if(list!=null){
			for(int i=0;i<list.size();i++){
				Map m=(Map)list.get(i);
				String codigoRegion = MapUtils.getString(m, "codigoRegion");
				String codigoZona = MapUtils.getString(m, "codigoZona");
				String codigoCapacitadora = MapUtils.getString(m, "codigoCapacitadora");
				String StrNro = MapUtils.getString(m, "numeroUnidades", "0");
				
				int numeroUnidades = 0;
				
				try
				{
					numeroUnidades = Integer.parseInt(StrNro);
				}catch(Exception ex)
				{}				
				
				String indicadorAccion = (String)m.get("indicadorAccion");
	
				if(!Constants.NUMERO_DOS.equals(indicadorAccion) && !Constants.MAV_ESTADO_TMP_ELIMINAR.equals(indicadorAccion) ) {
					if(StringUtils.isNotBlank(codigoCapacitadora)) {
						totalUnidadesCapacitadora += numeroUnidades;
						totalCapacitadoras++;
					}
					else {
						if(StringUtils.isEmpty(codigoZona))
							totalUnidadesRegion = totalUnidadesRegion + numeroUnidades;
						else
							totalUnidadesZona = totalUnidadesZona + numeroUnidades;
					}
					
					if(StringUtils.isNotBlank(codigoRegion) && StringUtils.isBlank(codigoZona))
					{
						totalRegiones++;
					}
					
					if(StringUtils.isNotBlank(codigoRegion) && StringUtils.isNotBlank(codigoZona))
					{
						totalZonas++;
					}					
				}
			}
		}
		
		f.setTotalUnidadesRegion(String.valueOf(totalUnidadesRegion));
		f.setTotalUnidadesZona(String.valueOf(totalUnidadesZona));
		f.setTotalUnidadesCapacitadora(String.valueOf(totalUnidadesCapacitadora));
		
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
	public void refreshPopup(ActionEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'refreshPopup' method");
		}	
		
		try {

			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			MantenimientoMAVConfiguracionForm f = (MantenimientoMAVConfiguracionForm) this.formMantenimiento;		
			MantenimientoMAVConfiguracionService service = (MantenimientoMAVConfiguracionService)
									getBean("spusicc.mantenimientoMAVConfiguracionService");	
			
			String codigoConsRest=f.getCodigoConsideracion();
			String indicadorTipo=this.indicadorTipo;
			
			this.indicadorCerrarVentana = Constants.NRO_CERO;
			this.codigoIdiomaISO = usuario.getIdioma().getCodigoISO();	
			
			int codigo =Integer.parseInt(codigoConsRest);
			String forward="";
			List listC = null;
			
			switch (codigo) {
			case Constants.MAV_CONRES_LISTA_CONSU:
				listC = this.consultoraList;
				
				actualizarTotalConsultoras(f, true);
				forward =Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo)?"viewListaCliente":"viewListaClienteRest";
				break;
				
			case Constants.MAV_CONRES_LISTA_REGION_ZONA:
				listC = this.regionesList;
				if (Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo))
					actualizarTotalUnidades(f, true);
				else
					actualizarTotalUnidades(f, false);
				forward =Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo)?"viewListaRegionZona":"viewListaRegionZonaRest";
				break;	
				
			case Constants.MAV_CONRES_CLASIFICACIONES_CLIENTE:
				listC =this.clasificacionesList;
				forward =Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo)?"viewClasificacionesCliente":"viewClasificacionesClienteRest";
				break;
				
			case Constants.MAV_CONRES_UNIDAD_ADM:
				listC = this.unidadesList;
				forward =Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo)?"viewUnidadAdministrativa":"viewUnidadAdministrativaRest";
				break;
				
			case Constants.MAV_CONRES_ESTATUS_CLIENTE:	
				listC = (List) this.codigoEstatusList;
				forward =Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo)?"viewEstatusCliente":"viewEstatusClienteRest";
				break;
				
			case Constants.MAV_CONRES_LISTA_CONSU_REST:
				listC = this.consultoraListR;
				
				actualizarTotalConsultoras(f, true);
				forward =Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo)?"viewListaCliente":"viewListaClienteRest";
				break;
				
			case Constants.MAV_CONRES_LISTA_REGION_ZONA_REST:
				listC = this.regionesListR;
				if (Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo))
					actualizarTotalUnidades(f, true);
				else
					actualizarTotalUnidades(f, false);
				forward =Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo)?"viewListaRegionZona":"viewListaRegionZonaRest";
				break;			
				
			case Constants.MAV_CONRES_CLASIFICACIONES_CLIENTE_REST:
				listC = this.clasificacionesListR;	
				forward =Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo)?"viewClasificacionesCliente":"viewClasificacionesClienteRest";
				break;
				
			case Constants.MAV_CONRES_UNIDAD_ADM_REST:		
				listC = this.unidadesListR;
				forward =Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo)?"viewUnidadAdministrativa":"viewUnidadAdministrativaRest";
				break;		
				
			case Constants.MAV_CONRES_ESTATUS_CLIENTE_REST:
				listC = this.codigoEstatusListR;
				forward =Constants.MAV_TIPO_CONSIDERACION.equals(indicadorTipo)?"viewEstatusCliente":"viewEstatusClienteRest";
				break;			
			}
			log.debug("forward ss "+forward);
			//return mapping.findForward(forward);
		
		} catch (Exception e) {
			addError("Error", obtieneMensajeErrorException(e));
		}
		
	}
	
	/**
	 * Actualiza Totales en Lista Region Zona
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public void totalesRegionZona(ActionEvent event) {
		try {
			String forward = "viewListaRegionZona";
			String indicadorUnidadPopup = this.indicadorUnidadPopup;
			MantenimientoMAVConfiguracionForm f = (MantenimientoMAVConfiguracionForm) this.formMantenimiento;
			if (StringUtils.isNotBlank(indicadorUnidadPopup)) {
				f.setIndicadorUnidadPopup(indicadorUnidadPopup);
			}
			
			this.actualizarTotalUnidades(f, true);
			//return mapping.findForward(forward);
		
		} catch (Exception e) {
			addError("Error", obtieneMensajeErrorException(e));
		}	
	
	}
	
	
	/**
	 * Actualiza Totales en Lista Region Zona Restriccion
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public void totalesRegionZonaRes(ActionEvent event) {
		try {
			String forward = "viewListaRegionZonaRest";
			String indicadorUnidadPopup = this.indicadorUnidadPopup;
			MantenimientoMAVConfiguracionForm f = (MantenimientoMAVConfiguracionForm) this.formMantenimiento;
			if (StringUtils.isNotBlank(indicadorUnidadPopup)) {
				f.setIndicadorUnidadPopup(indicadorUnidadPopup);
			}
			
			this.actualizarTotalUnidades(f, false);
			//return mapping.findForward(forward);
		
		} catch (Exception e) {
			addError("Error", obtieneMensajeErrorException(e));
		}
		
	}
	
	
	/**
	 * Actualiza Totales en Lista Cliente
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public void totalesConsultoras(ActionEvent event) {
		try {
			String forward = "viewListaCliente";
			MantenimientoMAVConfiguracionForm f = (MantenimientoMAVConfiguracionForm) this.formMantenimiento;
			this.actualizarTotalConsultoras(f, true);
			//return mapping.findForward(forward);
			
		} catch (Exception e) {
			addError("Error", obtieneMensajeErrorException(e));
		}	
	
	}
	
	
	/**
	 * Actualiza Totales en Lista Cliente Restriccion
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public void totalesConsultorasRes(ActionEvent event) {
		try {
			String forward = "viewListaClienteRest";
			MantenimientoMAVConfiguracionForm f = (MantenimientoMAVConfiguracionForm) this.formMantenimiento;
			this.actualizarTotalConsultoras(f, false);
			//return mapping.findForward(forward);
		
		} catch (Exception e) {
			addError("Error", obtieneMensajeErrorException(e));
		}	
				
	}
	
	
	/**
	 * Carga la pantalla de eicion de unidades de Regiones, zonas y capacitadoras
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public void editUnidadesListaRegionZona(ActionEvent event) {
		try {
			String forward = "viewEditUnidadesListaRegionZona";
			MantenimientoMAVConfiguracionForm f = new MantenimientoMAVConfiguracionForm();
			this.formMantenimiento = f;
			GenericoService genericoService = (GenericoService) getBean("genericoService");
			MantenimientoMAVConfiguracionService service = (MantenimientoMAVConfiguracionService)getBean("spusicc.mantenimientoMAVConfiguracionService");
			ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
			
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			
			//Cargamos los valores de las listas.
			ParametroPais parametroPais = new ParametroPais();
			parametroPais.setCodigoPais(pais.getCodigo());
			parametroPais.setCodigoSistema(Constants.MAV_CODIGO_SISTEMA);
			parametroPais.setCodigoParametro(Constants.ITEM_INDICADOR_CAPACITADORA);
			parametroPais.setIndicadorActivo(Constants.ESTADO_ACTIVO);
			List lstParametros = genericoService.getParametrosPais(parametroPais);
			f.setCodigoPais(pais.getCodigo());
			f.setIndicadorCapacitadora(Constants.NO);
			
			ParametroPais parametro = null;
			if(CollectionUtils.size(lstParametros)==1){
				parametro = (ParametroPais) lstParametros.get(0);
				String indicadorCapacitadora = parametro.getValorParametro();
				f.setIndicadorCapacitadora(indicadorCapacitadora);
			}		
			
			List list = this.mavConfiguracionesList;
			Map criteria = new HashMap();
			Map map = null;
			
			Map data = (Map) this.beanRegistroSeleccionado;
			String correlativo = MapUtils.getString(data, "correlativo");
			
			criteria.put("codigoPais", pais.getCodigo());
			criteria.put("correlativoMAV", correlativo);
			criteria.put("tipoConsideracion", Constants.MAV_TIPO_CONSIDERACION);
			criteria.put("indicadorTipo", Constants.MAV_TIPO_LISTA_EXTERNA_CONDICION);
			criteria.put("abreviatura", Constants.MAV_ABREVIATURA_LISTA_REGION_ZONA);
			
			List listConsi = service.getRestConsideracion(criteria);
			List listC = new ArrayList();
			
			if(listConsi != null && listConsi.size() > 0)
			{
				//Subimos a session las listas
				Map criteriaOperacion = new HashMap();
				criteriaOperacion.put("codigoPais", pais.getCodigo());
				criteriaOperacion.put("indicadorTipoRegion", Constants.SI);
				this.siccRegionList = reporteService.getListaGenerico("getRegionesByPais", criteriaOperacion);
				this.siccCapacitadoraList = reporteService.getListaGenerico("getRegionesByPais", criteriaOperacion);
				//
				
				//Obtenemos el detalle de la consideracion
				Map mapConRes = (Map)listConsi.get(0);
				String codigoConsRest = (String)mapConRes.get("codigoConsideracion");
				
				criteria.put("correlativoMAV", correlativo);
				
				criteria.put("codigoConsRest", codigoConsRest);
				criteria.put("indicadorTipo", Constants.MAV_TIPO_CONSIDERACION);
				
				List listDetalle = (List)service.getDetalleConsRestEnvio(criteria);
				MantenimientoMAVConfiguracionCondicionEnvioForm listaValores[] = new MantenimientoMAVConfiguracionCondicionEnvioForm[listDetalle.size()];
				
				for(int i=0; i<listDetalle.size(); i++)
				{
					Map bean =(Map) listDetalle.get(i);
					
					String codigoRegion = MapUtils.getString(bean, "valCondi1", "");
					String codigoZona = MapUtils.getString(bean, "valCondi2", "");
					String codigoCapacitadora = MapUtils.getString(bean, "valCondi3", "");
					
					bean.put("codigoRegion", codigoRegion);
					bean.put("codigoZona", codigoZona);
					bean.put("codigoCapacitadora", codigoCapacitadora);
					bean.put("indicadorAccion",Constants.NRO_UNO);				
					bean.put("descripcionRegion", getDesRegion(codigoRegion));
					bean.put("descripcionZona", getDesZona(codigoRegion,codigoZona));
					bean.put("descripcionCapacitadora", getDesCapacitadora(codigoCapacitadora));
					
					MantenimientoMAVConfiguracionCondicionEnvioForm registroForm = new MantenimientoMAVConfiguracionCondicionEnvioForm();
					BeanUtils.copyProperties(registroForm, bean);
					
					listaValores[i] = registroForm;
					listC.add(bean);
				}
				
				this.mostrarBotonActualizar = false;
				for(int i=0; i<listC.size(); i++)
				{
					Map bean = (Map)listC.get(i);
					String indicadorEnvio = (String)bean.get("indicadorEnvio");
					
					if(indicadorEnvio.equals("P") || indicadorEnvio.equals("D") ) {
						this.mostrarBotonActualizar = true;
						break;
					}
				}
				
				f.setMantenimientoMAVConfiguracionCondicionEnvioForm(listaValores);
				this.regionesList = listC;
			}
			
			this.actualizarTotalUnidades(f, true);
			this.mostrarBotonSave = false;
			
			this.correlativo = MapUtils.getString(data, "correlativo");
			this.codigoPeriodo = MapUtils.getString(data, "periodoProceso");
			
			//return mapping.findForward(forward);		
			
		} catch (Exception e) {
			addError("Error", obtieneMensajeErrorException(e));
		}
		
	}
	
	/**
	 * Graba las unidades de Regiones, zonas y capacitadoras
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public void updateUnidadesListaRegionZona(ActionEvent event) {
		try {
			String forward = "viewEditUnidadesListaRegionZona";
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			
			MantenimientoMAVConfiguracionForm f = (MantenimientoMAVConfiguracionForm) this.formMantenimiento;
			MantenimientoMAVConfiguracionService service = (MantenimientoMAVConfiguracionService)getBean("spusicc.mantenimientoMAVConfiguracionService");
			
			//Grabamos los valores de las listas.
			//MantenimientoMAVConfiguracionCondicionEnvioForm []unidades = f.getMantenimientoMAVConfiguracionCondicionEnvioForm();
			List regionesList = this.regionesList;
			List listaUnidades = new ArrayList();
			List listC = new ArrayList();

			for(int i=0; i<regionesList.size(); i++)
			{
				Map bean = (Map)regionesList.get(i);
				String indicadorEnvio = (String)bean.get("indicadorEnvio");
				String numeroUnidades = MapUtils.getString(bean, "numeroUnidades", "");
				
				if(indicadorEnvio.equals("P")) {
					if(StringUtils.isEmpty(numeroUnidades)) {
						this.setMensajeAlertaDefault("Por favor ingrese el valor de las unidades.");
						this.getRequestContext().execute("PF('principalFormAlert_alertDialog').show()");
						return;
					}
					
					if(Integer.parseInt(numeroUnidades)==0) {
						this.setMensajeAlertaDefault("El valor de las unidades tiene que ser mayor que cero.");
						this.getRequestContext().execute("PF('principalFormAlert_alertDialog').show()");
						return;
					}
				}
			}
			
			for(int i=0; i<regionesList.size(); i++)
			{
				Map bean = (Map)regionesList.get(i);
				String indicadorEnvio = (String)bean.get("indicadorEnvio");
				String indicadorActivo = MapUtils.getString(bean, "indicadorActivo", "");
				
				System.out.println("indicadorActivo :" + indicadorActivo);
				if(StringUtils.equals(indicadorEnvio, Constants.MAV_CODIGO_INDICADOR_ENVIO_P)) {
					if(StringUtils.equals(indicadorActivo, "true")) {
						bean.put("indicadorEnvio", Constants.MAV_CODIGO_INDICADOR_ENVIO_D);
						bean.put("indicadorActivo", "");
					}
				}
				
				if(StringUtils.equals(indicadorEnvio, Constants.MAV_CODIGO_INDICADOR_ENVIO_D)) {
					if(StringUtils.equals(indicadorActivo, "true")) {
						bean.put("indicadorEnvio", Constants.MAV_CODIGO_INDICADOR_ENVIO_P);
						bean.put("indicadorActivo", "");
					}
				}
				
			}
			
			service.updateConfiguracionUnidadesListaRegionZona(this.regionesList, usuario);
			//f.setMantenimientoMAVConfiguracionCondicionEnvioForm(unidades);
			
			//this.regionesList = listC;
			this.actualizarTotalUnidades(f, true);
			
			this.addInfo("", this.getResourceMessage("mantenimientoMAVConfiguracionForm.updateUnidades"));
			
			//return mapping.findForward(forward);		
			
		} catch (Exception e) {
			addError("Error", obtieneMensajeErrorException(e));
		}
		
	}
	
	
	/**
	 * Insetamos nuevas regiones/zona a la consideracion LISTA REGION/ZONA de los MAV tipo GERENTE
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void viewRegionZona(ActionEvent event) {

		try {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'viewRegionZona' method");
			}			
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			MantenimientoMAVConfiguracionForm f = (MantenimientoMAVConfiguracionForm) this.formMantenimiento;;		
			this.indicadorCerrarVentana = Constants.NRO_CERO;
			this.codigoIdiomaISO = usuario.getIdioma().getCodigoISO();				
			
			String correlativo = this.correlativo;
			String codigoPeriodo = this.codigoPeriodo;
			
			f.setOidPais(obtenerOidPais(pais.getCodigo()));
			ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
			Map criteriaOperacion = new HashMap();
			criteriaOperacion.put("codigoPais", pais.getCodigo());
			criteriaOperacion.put("indicadorTipoRegion", Constants.SI);
			
			this.siccRegionList = reporteService.getListaGenerico("getRegionesByPais",
							criteriaOperacion);
			this.siccCapacitadoraList = reporteService.getListaGenerico("getRegionesByPais",
							criteriaOperacion);
			this.siccZonaList = null;
			
			f.setIndicadorTipoSeleccionCapacitadora(Constants.INDICADOR_TIPO_SELECCION_CAPACITADORA_REGION);
			f.setNumeroUnidadesPopup("");
			f.setCorrelativo(correlativo);
			f.setCodigoPeriodo(codigoPeriodo);
			
			this.regionesEditList = new ArrayList();
			this.dataTableEditRegionZona = new DataTableModel(this.regionesEditList);
			actualizarTotalUnidades2(f);
			
			this.getRequestContext().execute("PF('viewEditListaRegionZona').show()");
			
			//return mapping.findForward("viewEditListaRegionZona");
			
		} catch (Exception e) {
			addError("Error", obtieneMensajeErrorException(e));
		}
		
	}

	/**
	 * Insertamos un detalle de la lista de pantalla Region/Zona Editable
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void insertRegionZona(ActionEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'insertRegionZona' method");
		}			
		
		try {
			MantenimientoMAVConfiguracionForm f = (MantenimientoMAVConfiguracionForm) this.formMantenimiento;
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			
			String insertar = "S";
			this.indicadorCerrarVentana = Constants.NRO_CERO;
			String indicadorTipoSeleccion = f.getIndicadorTipoSeleccionCapacitadora();
			int codigo =Constants.MAV_CONRES_LISTA_REGION_ZONA;
			
			List list=this.regionesEditList;
			List list2=this.regionesList;
			Map	map = new HashMap();
			map.put("numeroUnidades", f.getNumeroUnidadesPopup());
			
			List listUARegionZona = obtenerListaUA(f);
			if (StringUtils.isNotBlank(insertar)) {
				if (listUARegionZona == null || listUARegionZona.size() == 0) {
					if (Constants.SI.equals(insertar)) {
						throw new Exception(this.getResourceMessage("mantenimientoMAVConfiguracionForm.selecccionarDato"));
					}
				}
			}
		
			boolean existe = true;
			
			if (StringUtils.equals(indicadorTipoSeleccion, Constants.INDICADOR_TIPO_SELECCION_CAPACITADORA_REGION)) {
				for(int j=0;j<listUARegionZona.size();j++) {
					Map mapUA = (Map)listUARegionZona.get(j);
					String codigoRegionAux = (String)mapUA.get("codigoRegion");
					String codigoZonaAux = (String)mapUA.get("codigoZona");
					
					mapUA.put("numeroUnidades", f.getNumeroUnidadesPopup());
					existe=false;
					
					for(int i=0;i<list2.size();i++){
						Map m=(Map)list2.get(i);
						String codigoRegion=(String)m.get("codigoRegion");
						String codigoZona=(String)m.get("codigoZona");
						
						if(StringUtils.equals(codigoRegion,codigoRegionAux) &&
						    StringUtils.equals(codigoZona,codigoZonaAux)){
							
							if(listUARegionZona.size()==1) {
								addError("Error", this.getResourceMessage("mantenimientoMAVConfiguracionForm.existe.regionZona"));
								
							}
							
							existe=true;
							break;
						}
					}
					
					if(!existe) {
						for(int i=0;i<list.size();i++){
							Map m=(Map)list.get(i);
							String codigoRegion=(String)m.get("codigoRegion");
							String codigoZona=(String)m.get("codigoZona");
							
							if(StringUtils.equals(codigoRegion,codigoRegionAux) &&
							    StringUtils.equals(codigoZona,codigoZonaAux)){
								
								if(listUARegionZona.size()==1) {
									addError("Error", this.getResourceMessage("mantenimientoMAVConfiguracionForm.existe.regionZona"));
								}
								
								existe=true;
								break;
							}
						}
					}
					
					if(!existe) { 
						mapUA.put("codigoPais", f.getCodigoPais());
						mapUA.put("correlativoMAV", f.getCorrelativo());
						mapUA.put("codigoPeriodo", f.getCodigoPeriodo());
						mapUA.put("codigoConsideracion", f.getCodigoConsideracion());
						mapUA.put("indicadorConsideracion",Constants.MAV_TIPO_CONSIDERACION);
						mapUA.put("codigoConsRest", Constants.MAV_CONRES_LISTA_REGION_ZONA);
						mapUA.put("login", usuario.getLogin());
	
						list.add(mapUA);				
					}
				}	
			}
			else {
				boolean mostrarMensajeExiste = true;
				for(int j=0;j<listUARegionZona.size();j++) {
					Map mapUA = (Map)listUARegionZona.get(j);
					String codigoCapacitadoranAux = (String)mapUA.get("codigoCapacitadora");
					mapUA.put("numeroUnidades", f.getNumeroUnidadesPopup());
					existe=false;
					
					for(int i=0;i<list2.size();i++){
						Map m=(Map)list2.get(i);
						String codigoCapacitadora=(String)m.get("codigoCapacitadora");
						
						if(StringUtils.equals(codigoCapacitadora,codigoCapacitadoranAux)){
						   existe=true;
						   break;
						}
					}
					
					if(!existe) {
						for(int i=0;i<list.size();i++){
							Map m=(Map)list.get(i);
							String codigoCapacitadora=(String)m.get("codigoCapacitadora");
							
							if(StringUtils.equals(codigoCapacitadora,codigoCapacitadoranAux)){
							   existe=true;
							   break;
							}
						}
					}
					
					if(!existe) { 
						mapUA.put("codigoPais", f.getCodigoPais());
						mapUA.put("correlativoMAV", f.getCorrelativo());
						mapUA.put("codigoPeriodo", f.getCodigoPeriodo());
						mapUA.put("codigoConsideracion", f.getCodigoConsideracion());
						mapUA.put("indicadorConsideracion",Constants.MAV_TIPO_CONSIDERACION);
						mapUA.put("codigoConsRest", Constants.MAV_CONRES_LISTA_REGION_ZONA);
						mapUA.put("login", usuario.getLogin());
	
						list.add(mapUA);	
						mostrarMensajeExiste = false;
					}
				}	
				if (mostrarMensajeExiste) {
					addError("Error", this.getResourceMessage("mantenimientoMAVConfiguracionForm.existe.capacitadora"));

				}
			}
			this.dataTableEditRegionZona = new DataTableModel(this.regionesEditList);
			f.setNumeroUnidadesPopup("");
			actualizarTotalUnidades2(f);
			
			//return mapping.findForward("viewEditListaRegionZona");
			
		} catch (Exception e) {
			addError("Error", obtieneMensajeErrorException(e));
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
	public void deleteRegionZona(ActionEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'deleteRegionZona' method");
		}			
		
		try {
			MantenimientoMAVConfiguracionForm f = (MantenimientoMAVConfiguracionForm) this.formMantenimiento;;
			String codigoConsRest=f.getCodigoConsideracion();
			String indicadorTipo=this.indicadorTipo;
			
			if (this.beanRegistroEditRegionZona == null) {
    			mostrarVentanaAlerta("errors.select.item");
				return;
			}
			
			Map map = (Map) this.beanRegistroEditRegionZona;
			map.put("indicadorAccion",Constants.MAV_ESTADO_TMP_ELIMINAR);
			
			List list = this.regionesEditList;
			/*if(f.getSelectedItemsPopup() != null && f.getSelectedItemsPopup().length > 0)
			{
				String[] ids = f.getSelectedItemsPopup();
				for(int i=0; i<ids.length; i++)
				{
					index = Integer.parseInt(ids[i])-1;
					map = (Map)list.get(index);
					map.put("indicadorAccion",Constants.MAV_ESTADO_TMP_ELIMINAR);
				}
			}*/
			
			Iterator it = list.iterator();
			while(it.hasNext()) {
				map = (Map)it.next();
				if(Constants.MAV_ESTADO_TMP_ELIMINAR.equals(map.get("indicadorAccion"))) {
					it.remove();
				}
			}
			
			this.dataTableEditRegionZona = new DataTableModel(this.regionesEditList);
			actualizarTotalUnidades2(f);
			//f.setSelectedItemsPopup(null);
			
		} catch (Exception e) {
			addError("Error", obtieneMensajeErrorException(e));
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
	public void saveRegionZona(ActionEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'saveRegionZona' method");
		}	
		
		try {
			MantenimientoMAVConfiguracionService service = (MantenimientoMAVConfiguracionService)getBean("spusicc.mantenimientoMAVConfiguracionService");
			//this.indicadorCerrarVentana = Constants.NRO_UNO;
			
			List list =this.regionesEditList;
			service.insertListaRegionZona(list);
			
			//return mapping.findForward("viewEditListaRegionZona");
			editUnidadesListaRegionZona(event);
			
		} catch (Exception e) {
			addError("Error", obtieneMensajeErrorException(e));
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
	public void cancelarRegionZona(ActionEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'cancelarRegionZona' method");
		}	
		
		try {
			this.indicadorCerrarVentana = Constants.NUMERO_DOS;
			
			//return mapping.findForward("viewEditListaRegionZona");
		} catch (Exception e) {
			addError("Error", obtieneMensajeErrorException(e));
		}	
	}

	/**
	 * @param request
	 * @param f
	 */
	private void actualizarTotalUnidades2( MantenimientoMAVConfiguracionForm f) {
		List list = null;
		int totalUnidadesRegion = 0;
		int totalUnidadesZona = 0;
		int totalUnidadesCapacitadora = 0;
		
		int totalRegiones = 0;
		int totalZonas = 0;
		int totalCapacitadoras = 0;
		
		list =this.regionesEditList;

		if(list!=null){
			for(int i=0;i<list.size();i++){
				Map m=(Map)list.get(i);
				String codigoRegion = MapUtils.getString(m, "codigoRegion");
				String codigoZona = MapUtils.getString(m, "codigoZona");
				String codigoCapacitadora = MapUtils.getString(m, "codigoCapacitadora");
				String StrNro = MapUtils.getString(m, "numeroUnidades", "0");
				
				int numeroUnidades = 0;
				
				try
				{
					numeroUnidades = Integer.parseInt(StrNro);
				}catch(Exception ex)
				{}				
				
				String indicadorAccion = (String)m.get("indicadorAccion");
	
				if(!Constants.NUMERO_DOS.equals(indicadorAccion) && !Constants.MAV_ESTADO_TMP_ELIMINAR.equals(indicadorAccion) ) {
					if(StringUtils.isNotBlank(codigoCapacitadora)) {
						totalUnidadesCapacitadora += numeroUnidades;
						totalCapacitadoras++;
					}
					else {
						if(StringUtils.isEmpty(codigoZona))
							totalUnidadesRegion = totalUnidadesRegion + numeroUnidades;
						else
							totalUnidadesZona = totalUnidadesZona + numeroUnidades;
					}
					
					if(StringUtils.isNotBlank(codigoRegion) && StringUtils.isBlank(codigoZona))
					{
						totalRegiones++;
					}
					
					if(StringUtils.isNotBlank(codigoRegion) && StringUtils.isNotBlank(codigoZona))
					{
						totalZonas++;
					}					
				}
			}
		}
		
		f.setTotalUnidadesRegion(String.valueOf(totalUnidadesRegion));
		f.setTotalUnidadesZona(String.valueOf(totalUnidadesZona));
		f.setTotalUnidadesCapacitadora(String.valueOf(totalUnidadesCapacitadora));
		
		f.setTotalRegiones(String.valueOf(totalRegiones));
		f.setTotalZonas(String.valueOf(totalZonas));
		f.setTotalCapacitadoras(String.valueOf(totalCapacitadoras));
	}
	
	
	/**
	 * Actualizar el total de Consultoras
	 * @param request
	 * @param f
	 * @param esConsideracion
	 */
	private void actualizarTotalConsultoras(MantenimientoMAVConfiguracionForm f, boolean esConsideracion) {
		List list = null;
		int totalConsultoras = 0;
		int totalUnidadesConsultoras = 0;
		
		if(esConsideracion)
			list =this.consultoraList;
		else
			list =this.consultoraListR;

		if(list!=null){
			for(int i=0;i<list.size();i++){
				Map m=(Map)list.get(i);
				String StrNro = MapUtils.getString(m, "numeroUnidades", "0");
				
				int numeroUnidades = 0;
				
				try
				{
					numeroUnidades = Integer.parseInt(StrNro);
				}catch(Exception ex)
				{}				
				
				String indicadorAccion = (String)m.get("indicadorAccion");
				if(!Constants.NUMERO_DOS.equals(indicadorAccion) && !Constants.MAV_ESTADO_TMP_ELIMINAR.equals(indicadorAccion) ) {
					totalUnidadesConsultoras += numeroUnidades;
					totalConsultoras ++;
				}
				
			}
		}
		
		f.setTotalConsultoras(String.valueOf(totalConsultoras));
		f.setTotalUnidadesConsultoras(String.valueOf(totalUnidadesConsultoras));
	}

	/**
	 * Carga la pantalla de edicion de unidades de Consultoras
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public void editUnidadesConsultoras(ActionEvent event) {
		try {
			String forward = "viewEditUnidadesConsultoras";
			MantenimientoMAVConfiguracionForm f = new MantenimientoMAVConfiguracionForm();
			this.formMantenimiento = f;
			GenericoService genericoService = (GenericoService) getBean("genericoService");
			MantenimientoMAVConfiguracionService service = (MantenimientoMAVConfiguracionService)getBean("spusicc.mantenimientoMAVConfiguracionService");
			
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			f.setCodigoPais(pais.getCodigo());
			
			List list = this.mavConfiguracionesList;
			Map criteria = new HashMap();
			Map data = (Map) this.beanRegistroSeleccionado;
			String correlativo = MapUtils.getString(data, "correlativo");
			
			criteria.put("codigoPais", pais.getCodigo());
			criteria.put("correlativoMAV", correlativo);
	
			criteria.put("tipoConsideracion", Constants.MAV_TIPO_CONSIDERACION);
			criteria.put("indicadorTipo", Constants.MAV_TIPO_LISTA_EXTERNA_CONDICION);
			criteria.put("abreviatura", Constants.MAV_ABREVIATURA_LISTA_CONSULTORAS);
			
			List listConsi = service.getRestConsideracion(criteria);
			List listC = new ArrayList();
			
			if(listConsi != null && listConsi.size() > 0)
			{
				//Obtenemos el detalle de la consideracion
				Map mapConRes = (Map)listConsi.get(0);
				String codigoConsRest = (String)mapConRes.get("codigoConsideracion");
				
				criteria.put("correlativoMAV", correlativo);
				
				criteria.put("codigoConsRest", codigoConsRest);
				criteria.put("indicadorTipo", Constants.MAV_TIPO_CONSIDERACION);
				
				List listDetalle = (List)service.getDetalleClientesConsRestEnvio(criteria);
				MantenimientoMAVConfiguracionCondicionEnvioForm listaValores[] = new MantenimientoMAVConfiguracionCondicionEnvioForm[listDetalle.size()];
				
				for(int i=0; i<listDetalle.size(); i++)
				{
					Map bean =(Map) listDetalle.get(i);
					
					String codigoPais = MapUtils.getString(bean, "valCondi1", "");
					String codigoCliente = MapUtils.getString(bean, "valCondi2", "");
					
					bean.put("codigoPais", codigoPais);
					bean.put("codigoCliente", codigoCliente);
					bean.put("indicadorAccion",Constants.NRO_UNO);				
					
					MantenimientoMAVConfiguracionCondicionEnvioForm registroForm = new MantenimientoMAVConfiguracionCondicionEnvioForm();
					BeanUtils.copyProperties(registroForm, bean);
					
					listaValores[i] = registroForm;
					listC.add(bean);
				}
				
				this.mostrarBotonActualizar = false;
				for(int i=0; i<listC.size(); i++)
				{
					Map bean = (Map)listC.get(i);
					String indicadorEnvio = (String)bean.get("indicadorEnvio");
					
					if(indicadorEnvio.equals("P") || indicadorEnvio.equals("D") ) {
						this.mostrarBotonActualizar = true;
						break;
					}
				}
				
				f.setMantenimientoMAVConfiguracionCondicionEnvioForm(listaValores);
				this.consultorasList = listC;
			}
			
			this.actualizarTotalUnidadesClientes(f, true);
			this.mostrarBotonSave = false;
			
			this.correlativo = MapUtils.getString(data, "correlativo");
			this.codigoPeriodo = MapUtils.getString(data, "periodoProceso");
			
			//return mapping.findForward(forward);		
		
		} catch (Exception e) {
			addError("Error", obtieneMensajeErrorException(e));
		}
		
	}

	private void actualizarTotalUnidadesClientes(MantenimientoMAVConfiguracionForm f, boolean esConsideracion) {
		List list = null;
		int totalUnidadesConsultoras = 0;
		int totalConsultoras = 0;
		
		if(esConsideracion)
			list =this.consultorasList;
		else
			list =this.consultorasListR;

		if(list!=null){
			for(int i=0;i<list.size();i++){
				Map m=(Map)list.get(i);
				String codigoRegion = MapUtils.getString(m, "codigoRegion");
				String codigoZona = MapUtils.getString(m, "codigoZona");
				String codigoCapacitadora = MapUtils.getString(m, "codigoCapacitadora");
				String StrNro = MapUtils.getString(m, "numeroUnidades", "0");
				
				int numeroUnidades = 0;
				
				try
				{
					numeroUnidades = Integer.parseInt(StrNro);
				}catch(Exception ex)
				{}				
				
				String indicadorAccion = (String)m.get("indicadorAccion");
	
				if(!Constants.NUMERO_DOS.equals(indicadorAccion) && !Constants.MAV_ESTADO_TMP_ELIMINAR.equals(indicadorAccion) ) {
					totalUnidadesConsultoras = totalUnidadesConsultoras + numeroUnidades;
					totalConsultoras++;			
				}
			}
		}
		
		f.setTotalUnidadesConsultoras(String.valueOf(totalUnidadesConsultoras));
		f.setTotalConsultoras(String.valueOf(totalConsultoras));
	}
	
	/**
	 * Graba las unidades de Consultoras
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public void updateUnidadesConsultoras(ActionEvent event) {
		try {
			String forward = "viewEditUnidadesConsultoras";
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			
			MantenimientoMAVConfiguracionForm f = (MantenimientoMAVConfiguracionForm) this.formMantenimiento;
			MantenimientoMAVConfiguracionService service = (MantenimientoMAVConfiguracionService)getBean("spusicc.mantenimientoMAVConfiguracionService");
			
			//Grabamos los valores de las listas.
			MantenimientoMAVConfiguracionCondicionEnvioForm []unidades = f.getMantenimientoMAVConfiguracionCondicionEnvioForm();
			List consultorasList = this.consultorasList;
			List listaUnidades = new ArrayList();
			List listC = new ArrayList();
			
			for(int i=0; i<consultorasList.size(); i++)
			{
				Map bean = (Map)consultorasList.get(i);
				String indicadorEnvio = (String)bean.get("indicadorEnvio");
				String numeroUnidades = MapUtils.getString(bean, "numeroUnidades", "");
				
				if(indicadorEnvio.equals("P")) {
					if(StringUtils.isEmpty(numeroUnidades)) {
						this.setMensajeAlertaDefault("Por favor ingrese el valor de las unidades.");
						this.getRequestContext().execute("PF('principalFormAlert_alertDialog').show()");
						return;
					}
					
					if(Integer.parseInt(numeroUnidades)==0) {
						this.setMensajeAlertaDefault("El valor de las unidades tiene que ser mayor que cero.");
						this.getRequestContext().execute("PF('principalFormAlert_alertDialog').show()");
						return;
					}
				}
			}
			
			for(int i=0; i<consultorasList.size(); i++)
			{
				Map bean = (Map)consultorasList.get(i);
				String indicadorEnvio = (String)bean.get("indicadorEnvio");
				String indicadorActivo = MapUtils.getString(bean, "indicadorActivo", "");
				
				System.out.println("indicadorActivo :" + indicadorActivo);
				if(StringUtils.equals(indicadorEnvio, Constants.MAV_CODIGO_INDICADOR_ENVIO_P)) {
					if(StringUtils.equals(indicadorActivo, "true")) {
						bean.put("indicadorEnvio", Constants.MAV_CODIGO_INDICADOR_ENVIO_D);
						bean.put("indicadorActivo", "");
					}
				}
				
				if(StringUtils.equals(indicadorEnvio, Constants.MAV_CODIGO_INDICADOR_ENVIO_D)) {
					if(StringUtils.equals(indicadorActivo, "true")) {
						bean.put("indicadorEnvio", Constants.MAV_CODIGO_INDICADOR_ENVIO_P);
						bean.put("indicadorActivo", "");
					}
				}
				
			}
					
			service.updateConfiguracionUnidadesListaRegionZona(this.consultorasList, usuario);
			//f.setMantenimientoMAVConfiguracionCondicionEnvioForm(unidades);
			
			//this.consultorasList = listC;
			this.actualizarTotalUnidadesClientes(f, true);
			
			this.addInfo("",this.getResourceMessage("mantenimientoMAVConfiguracionForm.updateConsultoras"));
			
			//return mapping.findForward(forward);		

		} catch (Exception e) {
			addError("Error", obtieneMensajeErrorException(e));
		}	
		
	}
	
	/**
	 * Insetamos nuevas consultoras a la consideracion LISTA CLIENTES de los MAV tipo CONSULTORA
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void viewConsultora(ActionEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'viewConsultora' method");
		}	
		
		try {
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			MantenimientoMAVConfiguracionForm f = (MantenimientoMAVConfiguracionForm) this.formMantenimiento;;		
			this.indicadorCerrarVentana = Constants.NRO_CERO;
			this.codigoIdiomaISO = usuario.getIdioma().getCodigoISO();
			
			f.setCorrelativo(this.correlativo);
			f.setOidPais(obtenerOidPais(pais.getCodigo()));
			f.setCodigoPeriodo(this.codigoPeriodo);
			
			this.consultorasEditList = new ArrayList();
			actualizarTotalConsultoras2(f);
			
			this.dataTableEditConsultora = new DataTableModel(this.consultorasEditList);
			this.getRequestContext().execute("PF('viewEditListaConsultora').show()");
				
			//return mapping.findForward("viewEditListaConsultora");
			
		} catch (Exception e) {
			addError("Error", obtieneMensajeErrorException(e));
		}
		
	}

	/**
	 * Insertamos un detalle de la lista de pantalla Consultora Editable
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void insertConsultora(ActionEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'insertConsultora' method");
		}			
		
		try {
			MantenimientoMAVConfiguracionForm f = (MantenimientoMAVConfiguracionForm) this.formMantenimiento;
			AjaxService ajaxService = (AjaxService) getBean("ajaxService");
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			
			if(StringUtils.isEmpty(f.getCodigoCliente())){
				mostrarVentanaAlerta("mantenimientoMAVConfiguracionForm.codigoCliente.requerido");
				return;
			}
			
			if(StringUtils.isEmpty(f.getNumeroUnidadesPopup())){
				mostrarVentanaAlerta("mantenimientoMAVConfiguracionForm.cantidadDespachar.requerido");
				return;
			}
	    	
	    	if(Integer.parseInt(f.getNumeroUnidadesPopup()) == 0) {
	    		mostrarVentanaAlerta("mantenimientoMAVConfiguracionForm.msg.validarCantidadDespachar");
				return;
			}
			
			String insertar = "S";
			this.indicadorCerrarVentana = Constants.NRO_CERO;
			String indicadorTipoSeleccion = f.getIndicadorTipoSeleccionCapacitadora();
			int codigo =Constants.MAV_CONRES_LISTA_CONSU;
			
			List list =this.consultorasEditList;
			List list2 =this.consultorasList;
			
			Map map = new HashMap();
			map.put("codigoPais", pais.getCodigo());
			map.put("codigoCliente", f.getCodigoCliente());
			//map.put("indicadorAccion",Constants.NRO_CERO);
			map.put("numeroUnidades", f.getNumeroUnidadesPopup());
			map.put("indicadorAccion",Constants.MAV_ESTADO_TMP_INSERTAR);
			
			boolean existe=false;	
			String oidPais = obtenerOidPais(pais.getCodigo());
			String datosCliente = ajaxService.getExisteCodigoCliente(oidPais, f.getCodigoCliente());
			
			if(datosCliente.length()>0) {
				for(int i=0;i<list2.size();i++){
					Map m=(Map)list2.get(i);
					String codigoCliente=(String)m.get("codigoCliente");
					
					if(StringUtils.equals(codigoCliente,f.getCodigoCliente())){
					   existe=true;
					   break;
					}
				}
				
				if(existe) {
					addError("Error", this.getResourceMessage("mantenimientoMAVConfiguracionCargaClientesForm.error.consultoraListaInicial"));
				}
				
				if(!existe) {
					for(int i=0;i<list.size();i++){
						Map m=(Map)list.get(i);
						String codigoCliente=(String)m.get("codigoCliente");
						String indicadorAccion = (String)m.get("indicadorAccion");
		
						if(StringUtils.equals(codigoCliente,f.getCodigoCliente()) &&
							!(Constants.NUMERO_DOS.equals(indicadorAccion) || Constants.MAV_ESTADO_TMP_ELIMINAR.equals(indicadorAccion)) ){
							addError("Error", this.getResourceMessage("mantenimientoMAVConfiguracionCargaClientesForm.error.consultoraDuplicada"));
							
							existe=true;
							break;
						}
					}
				}	
				
			} else {
				addError("Error", this.getResourceMessage("mantenimientoMAVConfiguracionCargaClientesForm.error.noExisteConsultora"));
				existe=true;
			}
			
			if(!existe) {
				StringTokenizer st = new StringTokenizer(datosCliente, "|");
				st.nextToken();
				map.put("nombreCliente", st.nextToken());
				
				map.put("correlativoMAV", f.getCorrelativo());
				map.put("codigoPeriodo", f.getCodigoPeriodo());
				map.put("codigoConsideracion", f.getCodigoConsideracion());
				map.put("indicadorConsideracion",Constants.MAV_TIPO_CONSIDERACION);
				map.put("codigoConsRest", Constants.MAV_CONRES_LISTA_CONSU);
				map.put("login", usuario.getLogin());
	
				list.add(map);
				
				f.setCodigoCliente("");
				f.setNombreCliente("");
				f.setNumeroUnidadesPopup("");
				
				this.consultorasEditList = list;
			}	
	
			this.dataTableEditConsultora = new DataTableModel(this.consultorasEditList);
			f.setNumeroUnidadesPopup("");
			actualizarTotalConsultoras2(f);
			
			//return mapping.findForward("viewEditListaConsultora");
			
		} catch (Exception e) {
			addError("Error", obtieneMensajeErrorException(e));
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
	public void deleteConsultora(ActionEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'deleteConsultora' method");
		}			
		
		try {
			MantenimientoMAVConfiguracionForm f = (MantenimientoMAVConfiguracionForm) this.formMantenimiento;;	
			String codigoConsRest=f.getCodigoConsideracion();
			String indicadorTipo=this.indicadorTipo;
			
			if (this.beanRegistroEditConsultora == null) {
    			mostrarVentanaAlerta("errors.select.item");
				return;
			}
			
			Map map = (Map) this.beanRegistroEditRegionZona;
			map.put("indicadorAccion",Constants.MAV_ESTADO_TMP_ELIMINAR);
			
			//case Constants.MAV_CONRES_LISTA_REGION_ZONA:
			List list = this.consultorasEditList;
			/*if(f.getSelectedItemsPopup() != null && f.getSelectedItemsPopup().length > 0)
			{
				String[] ids = f.getSelectedItemsPopup();
				for(int i=0; i<ids.length; i++)
				{
					index = Integer.parseInt(ids[i])-1;
					map = (Map)list.get(index);
					map.put("indicadorAccion",Constants.MAV_ESTADO_TMP_ELIMINAR);
				}
			}*/
			
			Iterator it = list.iterator();
			while(it.hasNext()) {
				map = (Map)it.next();
				if(Constants.MAV_ESTADO_TMP_ELIMINAR.equals(map.get("indicadorAccion"))) {
					it.remove();
				}
			}
			
			this.dataTableEditConsultora = new DataTableModel(this.consultorasEditList);
			actualizarTotalConsultoras2(f);
			//f.setSelectedItemsPopup(null);
			
			//return mapping.findForward("viewEditListaConsultora");

		} catch (Exception e) {
			addError("Error", obtieneMensajeErrorException(e));
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
	public void saveConsultora(ActionEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'saveConsultora' method");
		}			
		
		try {
			MantenimientoMAVConfiguracionService service = (MantenimientoMAVConfiguracionService)getBean("spusicc.mantenimientoMAVConfiguracionService");
			//this.indicadorCerrarVentana = Constants.NRO_UNO;
			
			List list =this.consultorasEditList;
			service.insertListaConsultora(list);
			
			//return mapping.findForward("viewEditListaConsultora");
			editUnidadesConsultoras(event);
			
		} catch (Exception e) {
			addError("Error", obtieneMensajeErrorException(e));
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
	public void cancelarConsultora(ActionEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'cancelarConsultora' method");
		}			
		this.indicadorCerrarVentana = Constants.NUMERO_DOS;
		
		//return mapping.findForward("viewEditListaConsultora");
	}
	
	/**
	 * @param request
	 * @param f
	 */
	private void actualizarTotalConsultoras2(MantenimientoMAVConfiguracionForm f) {
		List list = null;
		int totalUnidadesConsultoras = 0;		
		int totalConsultoras = 0;

		list =this.consultorasEditList;

		if(list!=null){
			for(int i=0;i<list.size();i++){
				Map m=(Map)list.get(i);
				String StrNro = MapUtils.getString(m, "numeroUnidades", "0");
				
				int numeroUnidades = 0;
				
				try
				{
					numeroUnidades = Integer.parseInt(StrNro);
				}catch(Exception ex)
				{}				
				
				String indicadorAccion = (String)m.get("indicadorAccion");
	
				if(!Constants.NUMERO_DOS.equals(indicadorAccion) && !Constants.MAV_ESTADO_TMP_ELIMINAR.equals(indicadorAccion) ) {
					totalUnidadesConsultoras = totalUnidadesConsultoras + numeroUnidades;
					totalConsultoras++;			
				}
			}
		}
		
		f.setTotalUnidadesConsultoras(String.valueOf(totalUnidadesConsultoras));
		f.setTotalConsultoras(String.valueOf(totalConsultoras));

	}
	
	private void actualizarTotalUnidadesUA(MantenimientoMAVConfiguracionForm f, boolean esConsideracion) {
		List list = null;
		int totalUnidadesRegion = 0;
		int totalUnidadesZona = 0;
		
		int totalRegiones = 0;
		int totalZonas = 0;
		
		if(esConsideracion)
			list =this.unidadesList;
		else
			list =this.unidadesListR;

		if(list!=null){
			for(int i=0;i<list.size();i++){
				Map m=(Map)list.get(i);
				String codigoRegion = MapUtils.getString(m, "codigoRegion");
				String codigoZona = MapUtils.getString(m, "codigoZona");
				String StrNro = MapUtils.getString(m, "numeroUnidades", "0");
				
				int numeroUnidades = 0;
				
				try
				{
					numeroUnidades = Integer.parseInt(StrNro);
				}catch(Exception ex)
				{}				
				
				String indicadorAccion = (String)m.get("indicadorAccion");
	
				if(!Constants.NUMERO_DOS.equals(indicadorAccion) && !Constants.MAV_ESTADO_TMP_ELIMINAR.equals(indicadorAccion) ) {
					if(StringUtils.isEmpty(codigoZona))
						totalUnidadesRegion = totalUnidadesRegion + numeroUnidades;
					else
						totalUnidadesZona = totalUnidadesZona + numeroUnidades;
					
					if(StringUtils.isNotBlank(codigoRegion) && StringUtils.isBlank(codigoZona))
					{
						totalRegiones++;
					}
					
					if(StringUtils.isNotBlank(codigoRegion) && StringUtils.isNotBlank(codigoZona))
					{
						totalZonas++;
					}					
				}
			}
		}
		
		f.setTotalUnidadesRegion(String.valueOf(totalUnidadesRegion));
		f.setTotalUnidadesZona(String.valueOf(totalUnidadesZona));
		
		f.setTotalRegiones(String.valueOf(totalRegiones));
		f.setTotalZonas(String.valueOf(totalZonas));
	}
	
	private String getDesFormula(String codigoFormula) {
		String descripcion = "";
		
		if("IN".equals(codigoFormula))
			descripcion = "Ingresos";
		if("AF".equals(codigoFormula))
			descripcion = "Activas Finales";
		if("RE".equals(codigoFormula))
			descripcion = "Reingresos";
		
		return descripcion;		
	}

	public void loadSubTiposClientes(ValueChangeEvent val) {
		MantenimientoMAVConfiguracionForm f = (MantenimientoMAVConfiguracionForm) this.formMantenimiento;
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		String codCliente = (String) val.getNewValue();
		ArrayList values = new ArrayList<String>(Arrays.asList(codCliente));
		this.siccSubTipoClienteList = ajaxService.getSubTiposClientesPorPaisTipoClientesOID(usuario.getIdioma()
						.getCodigoISO(), values);
		this.siccTipoClasificacionList = null;
		this.siccClasificacionList = null;
	}

	public void loadTiposClasificaciones(ValueChangeEvent val) {
		MantenimientoMAVConfiguracionForm f = (MantenimientoMAVConfiguracionForm) this.formMantenimiento;
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		String codSubTipoCliente = (String) val.getNewValue();
		ArrayList values = new ArrayList<String>(Arrays.asList(codSubTipoCliente));
		this.siccTipoClasificacionList = ajaxService.getTiposClasificacionesByCriteriaMultipleOID(usuario
						.getIdioma().getCodigoISO(), f.getOidTipoCliente(),
						values);
		this.siccClasificacionList = null;
	}

	public void loadClasificaciones(ValueChangeEvent val) {
		MantenimientoMAVConfiguracionForm f = (MantenimientoMAVConfiguracionForm) this.formMantenimiento;
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		String valor = (String) val.getNewValue();
		ArrayList codSubTipoCliente = new ArrayList<String>(Arrays.asList(f.getOidSubTipoCliente()));
		ArrayList codTipoClasificacion = new ArrayList<String>(
				Arrays.asList(valor));
		this.siccClasificacionList = ajaxService.getClasificacionesByCriteriaMultipleOID(usuario.getIdioma()
						.getCodigoISO(), f.getOidTipoCliente(),
						codSubTipoCliente, codTipoClasificacion);
	}
	
	public void loadZonas(ValueChangeEvent val) {
		MantenimientoMAVConfiguracionForm f = (MantenimientoMAVConfiguracionForm) this.formMantenimiento;
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		String[] regiones = (String [])val.getNewValue();
		if(regiones!=null && regiones.length > 0 ){		
			if(regiones[0].equals("Todos")) {
				this.siccZonaList= null;
			} else {
				LabelValue[] lbZonaList = ajaxService.getZonasMultipleByPaisMarcaCanalRegion(
						pais.getCodigo(), "T", "VD", regiones, "T");
				this.siccZonaList = lbZonaList; //adiccionarLabelInicio(lbZonaList, "R", "REGIONES");
			}
		
		} else {
			this.siccZonaList= null;
		}

		f.setZonaListMultiple(null);
		f.setSeccionListMultiple(null);
		f.setTerritorioListMultiple(null);

		this.siccSeccionList = null;
		this.siccTerritorioList = null;

	}

	public void loadSeccion(ValueChangeEvent val) {
		MantenimientoMAVConfiguracionForm f = (MantenimientoMAVConfiguracionForm) this.formMantenimiento;
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		String[] zonas = (String [])val.getNewValue();
		if(zonas!=null && zonas.length > 0 ){		
			if(StringUtils.isEmpty(zonas[0]) || zonas[0].equals("Todos")) {
				this.siccSeccionList= null;
			} else {
				LabelValue[] lbSeccionList = ajaxService.getSeccionMultiple2ByPaisMarcaCanalRegionZona(pais.getCodigo(),
											"T", "VD", new ArrayList<String>(Arrays.asList(f.getRegionListMultiple())),
											new ArrayList<String>(Arrays.asList(zonas)), "U");
				lbSeccionList = adiccionarLabelInicio(lbSeccionList, "Todos", "Todos");
				this.siccSeccionList = lbSeccionList; //adiccionarLabelInicio(lbSeccionList, "", "");
			}
			
		} else {
			siccSeccionList = null;
		}

		f.setSeccionListMultiple(null);
		f.setTerritorioListMultiple(null);
		
		this.siccTerritorioList = null;
	}

	public void loadTerritorios(ValueChangeEvent val) {
		MantenimientoMAVConfiguracionForm f = (MantenimientoMAVConfiguracionForm) this.formMantenimiento;
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		String[] secciones = (String [])val.getNewValue();
		if(secciones!=null && secciones.length > 0 ){		
			if(secciones[0].equals("Todos")) {
				this.siccTerritorioList= null;
			} else {
				LabelValue[] lbTerritorioList = ajaxService.getTerritoriosMultiple2ByPaisMarcaCanalRegionZonaSeccion(pais.getCodigo(),
											"T", "VD", 
											new ArrayList<String>(Arrays.asList(f.getRegionListMultiple())),
											new ArrayList<String>(Arrays.asList(f.getZonaListMultiple())),
											new ArrayList<String>(Arrays.asList(secciones)), "U");
				lbTerritorioList = adiccionarLabelInicio(lbTerritorioList, "Todos", "Todos");
				this.siccTerritorioList = lbTerritorioList; //adiccionarLabelInicio(lbTerritorioList, "", "");
			}
			
		} else {
			siccTerritorioList = null;
		}

		f.setTerritorioListMultiple(null);
	}
	
	private LabelValue[] adiccionarLabelInicio(LabelValue[] lista, String value, String label) {
		LabelValue[] listaAux = new LabelValue[lista.length+1];
		LabelValue labelAux = new LabelValue(label, value);
		listaAux[0] = labelAux;
		
		for(int i=0; i<lista.length; i++) {
			listaAux[i+1] = lista[i];
		}
		
		return listaAux;
	}
	
	public void deshabilitarRegionZona(ValueChangeEvent val) {
	}
	
	/******************************************************************************************************************
	 * METODOS PARA LA CARGA DE ARCHIVO DEL POPUP DE REGION/ZONA
	 ******************************************************************************************************************/
	public void viewArchivoRegionZona(ActionEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'insertPopup' method");
		}
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		ProcesoZONActualizarUnidadesGeograficasService serviceUnidad = (ProcesoZONActualizarUnidadesGeograficasService) 
				getBean("spusicc.procesoZONActualizarUnidadesGeograficasService");
		
		this.setDirectorioTemporal(serviceUnidad.obtenerPathUpload(pais.getCodigo()));
		
		//limpiando el flag de validacion de archivo
		this.viewValida = "";
		this.mavArchivoRegioneslist = null;
		limpiarArchivoRegionZona();
		
		this.getRequestContext().execute("PF('viewArchivoRegionZona').show()");
			
	}
	
	/**
	 * Carga el archivo excel que viene del request e inserta su informacion en tabla
	 * 
	 * 
	 */
	public void cargarArchivoRegionZona(FileUploadEvent event)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'cargarArchivoRegionZona' method");
		}

		
		Usuario usuario= this.mPantallaPrincipalBean.getCurrentUser();
		this.setAttachment(event.getFile().getFileName());
		
		// Cargamos el archivo de la maquina del cliente al servidor
		UploadedFile archi = event.getFile();
		uploadArchivo(archi);
		
		// Obtenemos la extension del archivo
		String extensionArchivo = obtenerExtensionArchivo(this.getNombreArchivo());
		this.setExtensionArchivo(extensionArchivo);
		
		limpiarArchivoRegionZona();
		this.mostrarBotonValidar = true;
	}
	
	/**
	 * Carga el archivo excel que viene del request e inserta su informacion en tabla
	 * 
	 * 
	 */
	public void validarArchivoRegionZona(ActionEvent event)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'validarArchivoRegionZona' method");
		}
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario= this.mPantallaPrincipalBean.getCurrentUser();
		MantenimientoMAVConfiguracionForm f = (MantenimientoMAVConfiguracionForm) this.formMantenimiento;
				
		//obtenemos el periodo desde la fecha de proceso 
		InterfazSiCCService serviceSiCC = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		
		// obtenemos el servicio 
		MantenimientoMAVConfiguracionService service = (MantenimientoMAVConfiguracionService)  
				getBean("spusicc.mantenimientoMAVConfiguracionService");
		Map criteria = new HashMap();
		criteria.put("directorioTemporal",this.getDirectorioTemporal());
		criteria.put("nombreArchivo",this.getNombreArchivo());
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoUsuario", usuario.getLogin());
		String oidPais= clienteService.getOidPais(criteria);
		criteria.put("oidPais",oidPais );
		criteria.put("indicadorUnidadPopup",f.getIndicadorUnidad() );
		
		//validamos el archivo excel y en criteria mandamos que estructura es sin period o con periodo
		boolean isValido = true; //service.validarArchivoExcel(criteria);
		//cargamos toda los registros del excel con la sgte informacion y simultaneamente cargara la 
		//informcion en la taba de puntaje
		//num fila codconsultora mensaje error num errores , este campo sera llenado y acualizado en el ultimo registro
		List listaArchivo =null;
				
		if(isValido){
			//Seteamos los codigos de clientes que ya han sido ingresados a la Lista de Clientes
			Map mapRegiones = new HashMap();
			Map mapCapacitadoras = new HashMap();
			List regionesList = null;
			if(Constants.MAV_TIPO_CONSIDERACION.equals(this.indicadorTipo)) {
				regionesList = this.regionesList;
			} else {
				regionesList = this.regionesListR;
			}
			for(int i=0;i<regionesList.size();i++){
				Map m=(Map)regionesList.get(i);
				String codigoRegion=(String)m.get("codigoRegion");
				String codigoZona=(String)m.get("codigoZona");
				String codigoCapacitadora=(String)m.get("codigoCapacitadora");
				String indicadorAccion = (String)m.get("indicadorAccion");
				
				if(!Constants.NUMERO_DOS.equals(indicadorAccion) || !Constants.MAV_ESTADO_TMP_ELIMINAR.equals(indicadorAccion)){
					if (StringUtils.isBlank(codigoCapacitadora)) {
						if(StringUtils.isEmpty(codigoZona)) {
							mapRegiones.put(codigoRegion, "");
						} else
							mapRegiones.put(codigoRegion + "-" + codigoZona, "");
					}
					else {
						mapCapacitadoras.put(codigoCapacitadora, "");
					}
				}
				
			}
			criteria.put("indicadorCapacitadora", f.getIndicadorCapacitadora());
			criteria.put("mapRegiones", mapRegiones);
			criteria.put("mapCapacitadoras", mapCapacitadoras);
			criteria.put("numeroUnidadesPopupUniDifNO", f.getNumeroUnidades());
			
		
			List listaResultado = service.cargarRegionesArchivoExcel(criteria);
			listaArchivo = (List)listaResultado.get(0);
			
			this.setNumRegistros(String.valueOf(listaArchivo.size()));
			Map map =(Map) listaArchivo.get(listaArchivo.size()-1);
			this.setNumRegistrosError(String.valueOf(map.get("numErrores")));
			this.setNumRegistrosValido(String.valueOf(listaArchivo.size() - Integer.parseInt(this.getNumRegistrosError())));
			
			//es valido si por lo menos hay un registro por cargar , es decir numero errors < num registros
			if(Integer.parseInt(this.getNumRegistrosError())< Integer.parseInt(this.getNumRegistros())) {
				this.setIndicadorValido(Constants.NUMERO_UNO);
				this.mostrarBotonExecute = true;
			} else
				this.setIndicadorValido(Constants.NUMERO_CERO);		
		
			borrarFichero(this.getDirectorioTemporal(), this.getNombreArchivo());
			this.viewValida = Constants.NUMERO_UNO;//flag para mostrar el resultado de la validacion
			this.mavArchivoRegioneslist = listaArchivo;

			this.mostrarPrimeraGrilla = true;
			this.mostrarSegundaGrilla = true;
			
		}else{
			listaArchivo = new ArrayList();
			this.setIndicadorValido(Constants.NUMERO_CERO);
			this.setNumRegistrosError(Constants.NUMERO_UNO);
			this.setNumRegistros(Constants.NUMERO_UNO);
			this.setNumRegistrosValido(Constants.NUMERO_CERO);
			
			this.viewValida = Constants.NUMERO_UNO;//flag para mostrar el resultado de la validacion
			//mandamos infomacion del archivo
			Map mapFila = new HashMap();
			mapFila.put("numeroFila", Constants.NUMERO_UNO);
			mapFila.put("mensajeError", this.getResourceMessage("mantenimientoMAVConfiguracionCargaRegionesForm.error.noFormatoExcel"));				
			listaArchivo.add(mapFila);		
			this.mavArchivoRegioneslist = listaArchivo;

		}
		
	}
	
	public void saveArchivoRegionZona(ActionEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'saveArchivoRegionZona' method ");
		}

		try {
				
			MantenimientoMAVConfiguracionForm f = (MantenimientoMAVConfiguracionForm) this.formMantenimiento;	
	       
			//ActionMessages messages = new ActionMessages();
			List list = this.mavArchivoRegioneslist;
			String forward = "";
			String indicadorUnidadPopup = f.getIndicadorUnidadPopup();
			if(Constants.MAV_TIPO_CONSIDERACION.equals(this.getIndicadorTipo())) {
				List regionesList = this.regionesList;
				
				Iterator it = list.iterator();
				while(it.hasNext()) {
					Map mapBean = (Map)it.next();
					String mensajeError = (String)mapBean.get("mensajeError");
					
					if(mensajeError != null){
						it.remove();
					} else {
						String codigoRegion = (String)mapBean.get("codigoRegion");
						String codigoZona = (String)mapBean.get("codigoZona");
						String codigoCapacitadora = (String) mapBean.get("codigoCapacitadora");
						
						mapBean.put("descripcionRegion", null);
						mapBean.put("descripcionZona", null);
						mapBean.put("descripcionCapacitadora", null);
						
						if (StringUtils.isNotBlank(codigoRegion))
							mapBean.put("descripcionRegion", getDesRegion(codigoRegion));
						
						if (StringUtils.isNotBlank(codigoZona) && StringUtils.isNotBlank(codigoRegion))
							mapBean.put("descripcionZona", getDesZona(codigoRegion,codigoZona));
						
						if (StringUtils.isNotBlank(codigoCapacitadora))
							mapBean.put("descripcionCapacitadora", getDesRegion(codigoCapacitadora));
					}
				}
				
				if(regionesList == null) regionesList = new ArrayList();
				
				regionesList.addAll(list);
				this.regionesList = regionesList;
				this.dataTableDetalleConsideracion = new DataTableModel(this.regionesList);
				
				this.actualizarTotalUnidades(f, true);

			} else {
				List regionesList = this.regionesListR;
				
				Iterator it = list.iterator();
				while(it.hasNext()) {
					Map mapBean = (Map)it.next();
					String mensajeError = (String)mapBean.get("mensajeError");
					
					if(mensajeError != null){
						it.remove();
					} else {
						String codigoRegion = (String)mapBean.get("codigoRegion");
						String codigoZona = (String)mapBean.get("codigoZona");
						String codigoCapacitadora = (String) mapBean.get("codigoCapacitadora");
						
						mapBean.put("descripcionRegion", null);
						mapBean.put("descripcionZona", null);
						mapBean.put("descripcionCapacitadora", null);
						
						if (StringUtils.isNotBlank(codigoRegion))
							mapBean.put("descripcionRegion", getDesRegion(codigoRegion));
						
						if (StringUtils.isNotBlank(codigoZona) && StringUtils.isNotBlank(codigoRegion))
							mapBean.put("descripcionZona", getDesZona(codigoRegion,codigoZona));
						
						if (StringUtils.isNotBlank(codigoCapacitadora))
							mapBean.put("descripcionCapacitadora", getDesRegion(codigoCapacitadora));
					}
				}
				
				if(regionesList == null) regionesList = new ArrayList();
				
				regionesList.addAll(list);
				this.regionesListR = regionesList;
				this.dataTableDetalleRestriccion = new DataTableModel(this.regionesListR);
				forward = "viewPadreRest";
				
				this.actualizarTotalUnidades(f, false);
			}

			//return mapping.findForward(forward);
			
		} catch (Exception e) {
			addError("Error", obtieneMensajeErrorException(e));
		}	
		
	}
	
	public void cancelarArchivoRegionZona(ActionEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'cancelarArchivoRegionZona' method");
		}	
		
	}
	
	/**
	 * carga el archivo
	 * 
	 */
	private void uploadArchivo(UploadedFile archivo) throws Exception {
		this.setNombreArchivo(archivo.getFileName());
		log.debug("Nombre Archivo Upload: " + this.getNombreArchivo());
		// leyemos el stream de entrada
		InputStream is = archivo.getInputstream();
		// abrimos el stream de escritura, ubicacion al cual se grabara el
		// archivo del cliente
		FileOutputStream os = new FileOutputStream(new File(this.getDirectorioTemporal(), this.getNombreArchivo()));
		// grabamos cada 1024 bytes
		int bytesRead = 0;
		byte[] buffer = new byte[1024];
		while ((bytesRead = is.read(buffer, 0, 1024)) != -1) {
			os.write(buffer, 0, bytesRead);
		}
		os.close();
		this.setArchivo(null);
		String mensaje = "Se cargo archivo al Servidor con éxito";
		addInfo("Mensaje", mensaje);
	}
	
	/**
	 * obtiene la extension del archivo
	 * 
	 */
	private String obtenerExtensionArchivo(String nombreArchivo)
			throws Exception {
		return nombreArchivo.substring(nombreArchivo.length() - 3);
	}

	/**
	 * elimina el fichero del temporal
	 * 
	 */
	private void borrarFichero(String path, String nombreArchivo) {
		try {
			File file = new File(path, nombreArchivo);
			file.delete();
			log.debug("Se elimino el archivo");
		}	
		catch(Exception ex) {
			log.debug("No se pudo eliminar el archivo "+ex.getMessage());
		}
	}
	
	public void limpiarArchivoRegionZona() {
		this.mavArchivoRegioneslist =null;
		this.attachment = "";		
		this.mostrarPrimeraGrilla = false;
		this.mostrarSegundaGrilla = false;
		this.mostrarBotonExecute = false;
		this.mostrarBotonValidar = false;
	}
	
	/******************************************************************************************************************
	 * METODOS PARA LA CARGA DE ARCHIVO DEL POPUP DE LISTA CLIENTES
	 ******************************************************************************************************************/
	public void viewArchivoListaClientes(String tipoArchivo) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'viewArchivoListaClientes' method");
		}
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		ProcesoZONActualizarUnidadesGeograficasService serviceUnidad = (ProcesoZONActualizarUnidadesGeograficasService) 
				getBean("spusicc.procesoZONActualizarUnidadesGeograficasService");
		
		this.setDirectorioTemporal(serviceUnidad.obtenerPathUpload(pais.getCodigo()));
		
		//limpiando el flag de validacion de archivo
		this.viewValida = "";
		this.tipoArchivo = tipoArchivo;
		limpiarArchivoListaClientes();
		
		this.getRequestContext().execute("PF('viewArchivoListaClientes').show()");
	}
	
	/**
	 * Carga el archivo excel que viene del request e inserta su informacion en tabla
	 * 
	 * 
	 */
	public void cargarArchivoListaClientes(FileUploadEvent event)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'cargarArchivoListaClientes' method");
		}
		
		Usuario usuario= this.mPantallaPrincipalBean.getCurrentUser();
		this.setAttachment(event.getFile().getFileName());
		
		// Cargamos el archivo de la maquina del cliente al servidor
		UploadedFile archi = event.getFile();
		uploadArchivo(archi);
		
		// Obtenemos la extension del archivo
		String extensionArchivo = obtenerExtensionArchivo(this.getNombreArchivo());
		this.setExtensionArchivo(extensionArchivo);
		
		limpiarArchivoListaClientes();
		this.mostrarBotonValidar = true;
	}
	
	/**
	 * Carga el archivo excel que viene del request e inserta su informacion en tabla
	 * 
	 * 
	 */
	public void validarArchivoListaClientes(ActionEvent event)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'validarArchivoListaClientes' method");
		}
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario= this.mPantallaPrincipalBean.getCurrentUser();
		MantenimientoMAVConfiguracionForm f = (MantenimientoMAVConfiguracionForm) this.formMantenimiento;
				
		//obtenemos el periodo desde la fecha de proceso 
		InterfazSiCCService serviceSiCC = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		
		// obtenemos el servicio 
		MantenimientoMAVConfiguracionService service = (MantenimientoMAVConfiguracionService)  
				getBean("spusicc.mantenimientoMAVConfiguracionService");
		Map criteria = new HashMap();
		criteria.put("directorioTemporal",this.getDirectorioTemporal());
		criteria.put("nombreArchivo",this.getNombreArchivo());
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("usuario",usuario);
		String oidPais= clienteService.getOidPais(criteria);
		criteria.put("oidPais",oidPais );
		
		//validamos el archivo excel y en criteria mandamos que estructura es sin period o con periodo
		boolean isValido = true; //service.validarArchivoExcel(criteria);
		//cargamos toda los registros del excel con la sgte informacion y simultaneamente cargara la 
		//informcion en la taba de puntaje
		//num fila codconsultora mensaje error num errores , este campo sera llenado y acualizado en el ultimo registro
		List listaArchivo =null;
				
		if(isValido){
			//Seteamos los codigos de clientes que ya han sido ingresados a la Lista de Clientes
			Map mapClientes = new HashMap();
			List consultoraList = null;

			if(Constants.MAV_TIPO_CONSIDERACION.equals(this.indicadorTipo)) {
				consultoraList = this.consultoraList;
			} else {
				consultoraList = this.consultoraListR;
			}
			for(int i=0;i<consultoraList.size();i++){
				Map m=(Map)consultoraList.get(i);
				String codigoCliente=(String)m.get("codigoCliente");
				String indicadorAccion = (String)m.get("indicadorAccion");

				if(!Constants.NUMERO_DOS.equals(indicadorAccion) || !Constants.MAV_ESTADO_TMP_ELIMINAR.equals(indicadorAccion)){
					mapClientes.put(codigoCliente, "");
				}
				
			}
			
			criteria.put("mapClientes", mapClientes);
			List listaResultado = null;
			if(this.getTipoArchivo().equals("CSV"))
			{
				int numeroregistroCsv = service.cargarArchivoCsv(criteria);
				this.setNumRegistros(Integer.toString(numeroregistroCsv));
				
				//Obtengo todos los resultados
				List resultados = service.executeValidarExternaConsultora(usuario.getLogin());
		    	//Obtengo la cantidad de errores
				Integer totalErrores = service.getErroresTempoExterConsul(usuario.getLogin());
				//int erroresFila= Integer.parseInt(totalErrores);
		    	int totalValidos = Integer.parseInt(this.getNumRegistros()) - totalErrores;
		    	this.setNumRegistrosError(String.valueOf(totalErrores));
		    	this.setNumRegistrosValido(String.valueOf(totalValidos));
		    	//es valido si por lo menos hay un registro por cargar , es decir numero errors < num registros
				if(Integer.parseInt(this.getNumRegistrosError())< Integer.parseInt(this.getNumRegistros())) {
					this.setIndicadorValido(Constants.NUMERO_UNO);
					this.mostrarBotonExecute = true;
				} else
					this.setIndicadorValido(Constants.NUMERO_CERO);		
			
				borrarFichero(this.getDirectorioTemporal(), this.getNombreArchivo());
				this.viewValida = Constants.NUMERO_UNO;//flag para mostrar el resultado de la validacion
				this.mavArchivolist = resultados;

				this.mostrarPrimeraGrilla = true;
				this.mostrarSegundaGrilla = true;
		    	
			}
			else
			{
				listaResultado = service.cargarArchivoExcel(criteria);
				listaArchivo = (List)listaResultado.get(0);
				this.setNumRegistros(String.valueOf(listaArchivo.size()));
				Map map =(Map) listaArchivo.get(listaArchivo.size()-1);
				this.setNumRegistrosError(String.valueOf(map.get("numErrores")));
				this.setNumRegistrosValido(String.valueOf(listaArchivo.size() - Integer.parseInt(this.getNumRegistrosError())));
				
				//es valido si por lo menos hay un registro por cargar , es decir numero errors < num registros
				if(Integer.parseInt(this.getNumRegistrosError())< Integer.parseInt(this.getNumRegistros())){
					this.setIndicadorValido(Constants.NUMERO_UNO);
					this.mostrarBotonExecute = true;
				} else
					this.setIndicadorValido(Constants.NUMERO_CERO);		
			
				borrarFichero(this.getDirectorioTemporal(), this.getNombreArchivo());
				this.viewValida = Constants.NUMERO_UNO;//flag para mostrar el resultado de la validacion
				this.mavArchivolist = listaArchivo;

				this.mostrarPrimeraGrilla = true;
				this.mostrarSegundaGrilla = true;
			}			
			
		}else{
			listaArchivo = new ArrayList();
			this.setIndicadorValido(Constants.NUMERO_CERO);
			this.setNumRegistrosError(Constants.NUMERO_UNO);
			this.setNumRegistros(Constants.NUMERO_UNO);
			this.setNumRegistrosValido(Constants.NUMERO_CERO);
			
			this.viewValida = Constants.NUMERO_UNO;//flag para mostrar el resultado de la validacion
			//mandamos infomacion del archivo
			Map mapFila = new HashMap();
			mapFila.put("numeroFila", Constants.NUMERO_UNO);
			mapFila.put("codigoCliente", "");	
			mapFila.put("mensajeError", this.getResourceMessage("mantenimientoMAVConfiguracionCargaRegionesForm.error.noFormatoExcel"));				
			listaArchivo.add(mapFila);		
			this.mavArchivolist = listaArchivo;

		}
		
	}
	
	public void saveArchivoListaClientes(ActionEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'saveArchivoListaClientes' method ");
		}

		try {
				
			MantenimientoMAVConfiguracionForm f = (MantenimientoMAVConfiguracionForm) this.formMantenimiento;	
	       
			//ActionMessages messages = new ActionMessages();
			List list = this.mavArchivolist;
			String forward = "";

			if(Constants.MAV_TIPO_CONSIDERACION.equals(this.getIndicadorTipo())) {
				List consultoraList = this.consultoraList;
				
				Iterator it = list.iterator();
				while(it.hasNext()) {
					Map mapBean = (Map)it.next();
					String mensajeError = (String)mapBean.get("mensajeError");
					
					if(mensajeError != null){
						it.remove();
					}
				}
				
				if(consultoraList == null) consultoraList = new ArrayList();
				
				
				consultoraList.addAll(list);
				this.consultoraList = consultoraList;
				this.dataTableDetalleConsideracion = new DataTableModel(this.consultoraList);
				
				this.actualizarTotalConsultoras(f, true);
				
			} else {
				List consultoraList = this.consultoraListR;
				
				Iterator it = list.iterator();
				while(it.hasNext()) {
					Map mapBean = (Map)it.next();
					String mensajeError = (String)mapBean.get("mensajeError");
					
					if(mensajeError != null){
						it.remove();
					}
				}
				
				if(consultoraList == null) consultoraList = new ArrayList();
				
				consultoraList.addAll(list);
				this.consultoraListR = consultoraList;
				this.dataTableDetalleConsideracion = new DataTableModel(this.consultoraListR);
				
				this.actualizarTotalConsultoras(f, false);
			}

			//return mapping.findForward(forward);
			
		} catch (Exception e) {
			addError("Error", obtieneMensajeErrorException(e));
		}	
		
	}
	
	public void cancelarArchivoListaClientes(ActionEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'cancelarArchivoListaClientes' method");
		}	
		
	}
	
	public void limpiarArchivoListaClientes() {
		this.mavArchivolist =null;
		this.attachment = "";		
		this.mostrarPrimeraGrilla = false;
		this.mostrarSegundaGrilla = false;
		this.mostrarBotonExecute = false;
		this.mostrarBotonValidar = false;
	}
	
	public void validaCodigoCliente() {
		MantenimientoMAVConfiguracionForm f = (MantenimientoMAVConfiguracionForm) this.formMantenimiento;
		
		String regex = "";
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		if (f.getCodigoCliente()!=null && f.getCodigoCliente().length() > 0) {
			regex = ajaxService.getExisteCodigoCliente(f.getOidPais(),	f.getCodigoCliente());
			if (regex.length() > 0) {
				String[] x = regex.split("\\|");

				f.setNombreCliente(x[1]);
			} else {
				mostrarVentanaAlerta("mantenimientoMAVConfiguracionForm.consultora.not.existe");
			}
		} 
	}	
	
	public void openPopupBusquedaClientes(ActionEvent e){
		if(log.isDebugEnabled()){
			log.debug("openPopupBusquedaClientes");
		}
		//this.getRequestContext().execute("PF('viewBusquedaClientes').show()");
	}
	
	public void validarCodigoProducto() {
		MantenimientoMAVConfiguracionForm f = (MantenimientoMAVConfiguracionForm) this.formMantenimiento;
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		try {
			String data = ajax.getDescripcionInternacionalizableProducto(f.getCodigoProducto());
			
			if (StringUtils.isBlank(data))
				throw new Exception(this.getResourceMessage("mantenimientoDTOMatrizDescuentoForm.msg.producto.no.existe"));
			RequestContext.getCurrentInstance().reset("principalForm:panelProducto");
			f.setDescripcionProducto(data);
		} catch (Exception e) {

			f.setDescripcionProducto(null);
			addError("Mensaje", obtieneMensajeErrorException(e));
		}

	}
	
	public void validarCodigoProductoBusqueda() {
		MantenimientoMAVConfiguracionSearchForm f = (MantenimientoMAVConfiguracionSearchForm) this.formBusqueda;
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		try {
			String data = ajax.getDescripcionInternacionalizableProducto(f.getCodigoProductoBusq());
			
			if (StringUtils.isBlank(data))
				throw new Exception(this.getResourceMessage("mantenimientoDTOMatrizDescuentoForm.msg.producto.no.existe"));
			f.setDescripcionProductoBusq(data);
		} catch (Exception e) {

			f.setDescripcionProductoBusq(null);
			addError("Mensaje", obtieneMensajeErrorException(e));
		}

	}
	
	/**
	 * @return the mavActividadesList
	 */
	public List getMavActividadesList() {
		return mavActividadesList;
	}

	/**
	 * @param mavActividadesList the mavActividadesList to set
	 */
	public void setMavActividadesList(List mavActividadesList) {
		this.mavActividadesList = mavActividadesList;
	}

	/**
	 * @return the mavTiposOfertaList
	 */
	public LabelValue[] getMavTiposOfertaList() {
		return mavTiposOfertaList;
	}

	/**
	 * @param mavTiposOfertaList the mavTiposOfertaList to set
	 */
	public void setMavTiposOfertaList(LabelValue[] mavTiposOfertaList) {
		this.mavTiposOfertaList = mavTiposOfertaList;
	}

	/**
	 * @return the mavEstadosList
	 */
	public List getMavEstadosList() {
		return mavEstadosList;
	}

	/**
	 * @param mavEstadosList the mavEstadosList to set
	 */
	public void setMavEstadosList(List mavEstadosList) {
		this.mavEstadosList = mavEstadosList;
	}

	/**
	 * @return the siccMAVTipoClienteList
	 */
	public List getSiccMAVTipoClienteList() {
		return siccMAVTipoClienteList;
	}

	/**
	 * @param siccMAVTipoClienteList the siccMAVTipoClienteList to set
	 */
	public void setSiccMAVTipoClienteList(List siccMAVTipoClienteList) {
		this.siccMAVTipoClienteList = siccMAVTipoClienteList;
	}

	/**
	 * @return the siccMAVOrigenList
	 */
	public List getSiccMAVOrigenList() {
		return siccMAVOrigenList;
	}

	/**
	 * @param siccMAVOrigenList the siccMAVOrigenList to set
	 */
	public void setSiccMAVOrigenList(List siccMAVOrigenList) {
		this.siccMAVOrigenList = siccMAVOrigenList;
	}

	/**
	 * @return the mavConfiguracionesList
	 */
	public List getMavConfiguracionesList() {
		return mavConfiguracionesList;
	}

	/**
	 * @param mavConfiguracionesList the mavConfiguracionesList to set
	 */
	public void setMavConfiguracionesList(List mavConfiguracionesList) {
		this.mavConfiguracionesList = mavConfiguracionesList;
	}

	/**
	 * @return the busquedaProductoSearchAction
	 */
	public BusquedaProductoSearchAction getBusquedaProductoSearchAction() {
		return busquedaProductoSearchAction;
	}

	/**
	 * @param busquedaProductoSearchAction the busquedaProductoSearchAction to set
	 */
	public void setBusquedaProductoSearchAction(
			BusquedaProductoSearchAction busquedaProductoSearchAction) {
		this.busquedaProductoSearchAction = busquedaProductoSearchAction;
	}

	/**
	 * @return the mostrarPopupProducto
	 */
	public boolean isMostrarPopupProducto() {
		return mostrarPopupProducto;
	}

	/**
	 * @param mostrarPopupProducto the mostrarPopupProducto to set
	 */
	public void setMostrarPopupProducto(boolean mostrarPopupProducto) {
		this.mostrarPopupProducto = mostrarPopupProducto;
	}

	/**
	 * @return the procesoMAVGenerarEnviosAction
	 */
	public ProcesoMAVGenerarEnviosAction getProcesoMAVGenerarEnviosAction() {
		return procesoMAVGenerarEnviosAction;
	}

	/**
	 * @param procesoMAVGenerarEnviosAction the procesoMAVGenerarEnviosAction to set
	 */
	public void setProcesoMAVGenerarEnviosAction(
			ProcesoMAVGenerarEnviosAction procesoMAVGenerarEnviosAction) {
		this.procesoMAVGenerarEnviosAction = procesoMAVGenerarEnviosAction;
	}

	/**
	 * @return the procesoMAVCargaMasivaAction
	 */
	public ProcesoMAVCargaMasivaAction getProcesoMAVCargaMasivaAction() {
		return procesoMAVCargaMasivaAction;
	}

	/**
	 * @param procesoMAVCargaMasivaAction the procesoMAVCargaMasivaAction to set
	 */
	public void setProcesoMAVCargaMasivaAction(
			ProcesoMAVCargaMasivaAction procesoMAVCargaMasivaAction) {
		this.procesoMAVCargaMasivaAction = procesoMAVCargaMasivaAction;
	}

	/**
	 * @return the mavConsideracionTodosList
	 */
	public List getMavConsideracionTodosList() {
		return mavConsideracionTodosList;
	}

	/**
	 * @param mavConsideracionTodosList the mavConsideracionTodosList to set
	 */
	public void setMavConsideracionTodosList(List mavConsideracionTodosList) {
		this.mavConsideracionTodosList = mavConsideracionTodosList;
	}

	/**
	 * @return the mavRestriccionTodosList
	 */
	public List getMavRestriccionTodosList() {
		return mavRestriccionTodosList;
	}

	/**
	 * @param mavRestriccionTodosList the mavRestriccionTodosList to set
	 */
	public void setMavRestriccionTodosList(List mavRestriccionTodosList) {
		this.mavRestriccionTodosList = mavRestriccionTodosList;
	}

	/**
	 * @return the periodoActual
	 */
	public String getPeriodoActual() {
		return periodoActual;
	}

	/**
	 * @param periodoActual the periodoActual to set
	 */
	public void setPeriodoActual(String periodoActual) {
		this.periodoActual = periodoActual;
	}

	/**
	 * @return the incUnidadNegocioList
	 */
	public List getIncUnidadNegocioList() {
		return incUnidadNegocioList;
	}

	/**
	 * @param incUnidadNegocioList the incUnidadNegocioList to set
	 */
	public void setIncUnidadNegocioList(List incUnidadNegocioList) {
		this.incUnidadNegocioList = incUnidadNegocioList;
	}

	/**
	 * @return the incNegocioList
	 */
	public List getIncNegocioList() {
		return incNegocioList;
	}

	/**
	 * @param incNegocioList the incNegocioList to set
	 */
	public void setIncNegocioList(List incNegocioList) {
		this.incNegocioList = incNegocioList;
	}

	/**
	 * @return the incMarcaProductoList
	 */
	public List getIncMarcaProductoList() {
		return incMarcaProductoList;
	}

	/**
	 * @param incMarcaProductoList the incMarcaProductoList to set
	 */
	public void setIncMarcaProductoList(List incMarcaProductoList) {
		this.incMarcaProductoList = incMarcaProductoList;
	}

	/**
	 * @return the recCodigoCatalogoList
	 */
	public List getRecCodigoCatalogoList() {
		return recCodigoCatalogoList;
	}

	/**
	 * @param recCodigoCatalogoList the recCodigoCatalogoList to set
	 */
	public void setRecCodigoCatalogoList(List recCodigoCatalogoList) {
		this.recCodigoCatalogoList = recCodigoCatalogoList;
	}

	/**
	 * @return the mavCodigoTipoVentaList
	 */
	public List getMavCodigoTipoVentaList() {
		return mavCodigoTipoVentaList;
	}

	/**
	 * @param mavCodigoTipoVentaList the mavCodigoTipoVentaList to set
	 */
	public void setMavCodigoTipoVentaList(List mavCodigoTipoVentaList) {
		this.mavCodigoTipoVentaList = mavCodigoTipoVentaList;
	}

	/**
	 * @return the clasificacionesList
	 */
	public List getClasificacionesList() {
		return clasificacionesList;
	}

	/**
	 * @param clasificacionesList the clasificacionesList to set
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
	 * @param unidadesList the unidadesList to set
	 */
	public void setUnidadesList(List unidadesList) {
		this.unidadesList = unidadesList;
	}

	/**
	 * @return the consultoraList
	 */
	public List getConsultoraList() {
		return consultoraList;
	}

	/**
	 * @param consultoraList the consultoraList to set
	 */
	public void setConsultoraList(List consultoraList) {
		this.consultoraList = consultoraList;
	}

	/**
	 * @return the regionesList
	 */
	public List getRegionesList() {
		return regionesList;
	}

	/**
	 * @param regionesList the regionesList to set
	 */
	public void setRegionesList(List regionesList) {
		this.regionesList = regionesList;
	}

	/**
	 * @return the codigoEstatusList
	 */
	public List getCodigoEstatusList() {
		return codigoEstatusList;
	}

	/**
	 * @param codigoEstatusList the codigoEstatusList to set
	 */
	public void setCodigoEstatusList(List codigoEstatusList) {
		this.codigoEstatusList = codigoEstatusList;
	}

	/**
	 * @return the clasificacionesListR
	 */
	public List getClasificacionesListR() {
		return clasificacionesListR;
	}

	/**
	 * @param clasificacionesListR the clasificacionesListR to set
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
	 * @param unidadesListR the unidadesListR to set
	 */
	public void setUnidadesListR(List unidadesListR) {
		this.unidadesListR = unidadesListR;
	}

	/**
	 * @return the consultoraListR
	 */
	public List getConsultoraListR() {
		return consultoraListR;
	}

	/**
	 * @param consultoraListR the consultoraListR to set
	 */
	public void setConsultoraListR(List consultoraListR) {
		this.consultoraListR = consultoraListR;
	}

	/**
	 * @return the regionesListR
	 */
	public List getRegionesListR() {
		return regionesListR;
	}

	/**
	 * @param regionesListR the regionesListR to set
	 */
	public void setRegionesListR(List regionesListR) {
		this.regionesListR = regionesListR;
	}

	/**
	 * @return the codigoEstatusListR
	 */
	public List getCodigoEstatusListR() {
		return codigoEstatusListR;
	}

	/**
	 * @param codigoEstatusListR the codigoEstatusListR to set
	 */
	public void setCodigoEstatusListR(List codigoEstatusListR) {
		this.codigoEstatusListR = codigoEstatusListR;
	}

	/**
	 * @return the mavConfiguracionConsideracionList
	 */
	public List getMavConfiguracionConsideracionList() {
		return mavConfiguracionConsideracionList;
	}

	/**
	 * @param mavConfiguracionConsideracionList the mavConfiguracionConsideracionList to set
	 */
	public void setMavConfiguracionConsideracionList(
			List mavConfiguracionConsideracionList) {
		this.mavConfiguracionConsideracionList = mavConfiguracionConsideracionList;
	}

	/**
	 * @return the mavConfiguracionRestriccionList
	 */
	public List getMavConfiguracionRestriccionList() {
		return mavConfiguracionRestriccionList;
	}

	/**
	 * @param mavConfiguracionRestriccionList the mavConfiguracionRestriccionList to set
	 */
	public void setMavConfiguracionRestriccionList(
			List mavConfiguracionRestriccionList) {
		this.mavConfiguracionRestriccionList = mavConfiguracionRestriccionList;
	}

	/**
	 * @return the mavConsideracionList
	 */
	public LabelValue[] getMavConsideracionList() {
		return mavConsideracionList;
	}

	/**
	 * @param mavConsideracionList the mavConsideracionList to set
	 */
	public void setMavConsideracionList(LabelValue[] mavConsideracionList) {
		this.mavConsideracionList = mavConsideracionList;
	}

	/**
	 * @return the mavRestriccionList
	 */
	public LabelValue[] getMavRestriccionList() {
		return mavRestriccionList;
	}

	/**
	 * @param mavRestriccionList the mavRestriccionList to set
	 */
	public void setMavRestriccionList(LabelValue[] mavRestriccionList) {
		this.mavRestriccionList = mavRestriccionList;
	}

	/**
	 * @return the consultorasEditList
	 */
	public List getConsultorasEditList() {
		return consultorasEditList;
	}

	/**
	 * @param consultorasEditList the consultorasEditList to set
	 */
	public void setConsultorasEditList(List consultorasEditList) {
		this.consultorasEditList = consultorasEditList;
	}

	/**
	 * @return the consultorasList
	 */
	public List getConsultorasList() {
		return consultorasList;
	}

	/**
	 * @param consultorasList the consultorasList to set
	 */
	public void setConsultorasList(List consultorasList) {
		this.consultorasList = consultorasList;
	}

	/**
	 * @return the consultorasListR
	 */
	public List getConsultorasListR() {
		return consultorasListR;
	}

	/**
	 * @param consultorasListR the consultorasListR to set
	 */
	public void setConsultorasListR(List consultorasListR) {
		this.consultorasListR = consultorasListR;
	}

	/**
	 * @return the siccCapacitadoraList
	 */
	public List getSiccCapacitadoraList() {
		return siccCapacitadoraList;
	}

	/**
	 * @param siccCapacitadoraList the siccCapacitadoraList to set
	 */
	public void setSiccCapacitadoraList(List siccCapacitadoraList) {
		this.siccCapacitadoraList = siccCapacitadoraList;
	}

	/**
	 * @return the siccRegionList
	 */
	public List getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList the siccRegionList to set
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
	 * @param siccTipoClienteList the siccTipoClienteList to set
	 */
	public void setSiccTipoClienteList(List siccTipoClienteList) {
		this.siccTipoClienteList = siccTipoClienteList;
	}

	/**
	 * @return the sACestadosList
	 */
	public List getSACestadosList() {
		return SACestadosList;
	}

	/**
	 * @param sACestadosList the sACestadosList to set
	 */
	public void setSACestadosList(List sACestadosList) {
		SACestadosList = sACestadosList;
	}

	/**
	 * @return the siccSubTipoClienteList
	 */
	public LabelValue[] getSiccSubTipoClienteList() {
		return siccSubTipoClienteList;
	}

	/**
	 * @param siccSubTipoClienteList the siccSubTipoClienteList to set
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
	 * @param siccTipoClasificacionList the siccTipoClasificacionList to set
	 */
	public void setSiccTipoClasificacionList(LabelValue[] siccTipoClasificacionList) {
		this.siccTipoClasificacionList = siccTipoClasificacionList;
	}

	/**
	 * @return the siccClasificacionList
	 */
	public LabelValue[] getSiccClasificacionList() {
		return siccClasificacionList;
	}

	/**
	 * @param siccClasificacionList the siccClasificacionList to set
	 */
	public void setSiccClasificacionList(LabelValue[] siccClasificacionList) {
		this.siccClasificacionList = siccClasificacionList;
	}

	/**
	 * @return the regionesEditList
	 */
	public List getRegionesEditList() {
		return regionesEditList;
	}

	/**
	 * @param regionesEditList the regionesEditList to set
	 */
	public void setRegionesEditList(List regionesEditList) {
		this.regionesEditList = regionesEditList;
	}
	
	/**
	 * @return the idRestriccion
	 */
	public String getIdRestriccion() {
		return idRestriccion;
	}

	/**
	 * @param idRestriccion the idRestriccion to set
	 */
	public void setIdRestriccion(String idRestriccion) {
		this.idRestriccion = idRestriccion;
	}

	/**
	 * @return the indicadorTipo
	 */
	public String getIndicadorTipo() {
		return indicadorTipo;
	}

	/**
	 * @param indicadorTipo the indicadorTipo to set
	 */
	public void setIndicadorTipo(String indicadorTipo) {
		this.indicadorTipo = indicadorTipo;
	}


	/**
	 * @return the indicadorCerrarVentana
	 */
	public String getIndicadorCerrarVentana() {
		return indicadorCerrarVentana;
	}

	/**
	 * @param indicadorCerrarVentana the indicadorCerrarVentana to set
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
	 * @param codigoIdiomaISO the codigoIdiomaISO to set
	 */
	public void setCodigoIdiomaISO(String codigoIdiomaISO) {
		this.codigoIdiomaISO = codigoIdiomaISO;
	}

	/**
	 * @return the correlativo
	 */
	public String getCorrelativo() {
		return correlativo;
	}

	/**
	 * @param correlativo the correlativo to set
	 */
	public void setCorrelativo(String correlativo) {
		this.correlativo = correlativo;
	}

	/**
	 * @return the indicadorUnidadPopup
	 */
	public String getIndicadorUnidadPopup() {
		return indicadorUnidadPopup;
	}

	/**
	 * @param indicadorUnidadPopup the indicadorUnidadPopup to set
	 */
	public void setIndicadorUnidadPopup(String indicadorUnidadPopup) {
		this.indicadorUnidadPopup = indicadorUnidadPopup;
	}

	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo the codigoPeriodo to set
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return the reporteConfiguracion
	 */
	public ReporteMAVConfiguracionAction getReporteConfiguracion() {
		return reporteConfiguracion;
	}

	/**
	 * @param reporteConfiguracion the reporteConfiguracion to set
	 */
	public void setReporteConfiguracion(
			ReporteMAVConfiguracionAction reporteConfiguracion) {
		this.reporteConfiguracion = reporteConfiguracion;
	}

	/**
	 * @return the mavTiposOfertaMantList
	 */
	public LabelValue[] getMavTiposOfertaMantList() {
		return mavTiposOfertaMantList;
	}

	/**
	 * @param mavTiposOfertaMantList the mavTiposOfertaMantList to set
	 */
	public void setMavTiposOfertaMantList(LabelValue[] mavTiposOfertaMantList) {
		this.mavTiposOfertaMantList = mavTiposOfertaMantList;
	}

	/**
	 * @return the dataTableConsideracion
	 */
	public DataTableModel getDataTableConsideracion() {
		return dataTableConsideracion;
	}

	/**
	 * @param dataTableConsideracion the dataTableConsideracion to set
	 */
	public void setDataTableConsideracion(DataTableModel dataTableConsideracion) {
		this.dataTableConsideracion = dataTableConsideracion;
	}

	/**
	 * @return the beanRegistroConsideracion
	 */
	public Object getBeanRegistroConsideracion() {
		return beanRegistroConsideracion;
	}

	/**
	 * @param beanRegistroConsideracion the beanRegistroConsideracion to set
	 */
	public void setBeanRegistroConsideracion(Object beanRegistroConsideracion) {
		this.beanRegistroConsideracion = beanRegistroConsideracion;
	}

	/**
	 * @return the dataTableRestriccion
	 */
	public DataTableModel getDataTableRestriccion() {
		return dataTableRestriccion;
	}

	/**
	 * @param dataTableRestriccion the dataTableRestriccion to set
	 */
	public void setDataTableRestriccion(DataTableModel dataTableRestriccion) {
		this.dataTableRestriccion = dataTableRestriccion;
	}

	/**
	 * @return the beanRegistroRestriccion
	 */
	public Object getBeanRegistroRestriccion() {
		return beanRegistroRestriccion;
	}

	/**
	 * @param beanRegistroRestriccion the beanRegistroRestriccion to set
	 */
	public void setBeanRegistroRestriccion(Object beanRegistroRestriccion) {
		this.beanRegistroRestriccion = beanRegistroRestriccion;
	}

	/**
	 * @return the idConsideracionInsertar
	 */
	public boolean isIdConsideracionInsertar() {
		return idConsideracionInsertar;
	}

	/**
	 * @param idConsideracionInsertar the idConsideracionInsertar to set
	 */
	public void setIdConsideracionInsertar(boolean idConsideracionInsertar) {
		this.idConsideracionInsertar = idConsideracionInsertar;
	}

	/**
	 * @return the idConsideracionEditar
	 */
	public boolean isIdConsideracionEditar() {
		return idConsideracionEditar;
	}

	/**
	 * @param idConsideracionEditar the idConsideracionEditar to set
	 */
	public void setIdConsideracionEditar(boolean idConsideracionEditar) {
		this.idConsideracionEditar = idConsideracionEditar;
	}

	/**
	 * @return the idConsideracionActualizar
	 */
	public boolean isIdConsideracionActualizar() {
		return idConsideracionActualizar;
	}

	/**
	 * @param idConsideracionActualizar the idConsideracionActualizar to set
	 */
	public void setIdConsideracionActualizar(boolean idConsideracionActualizar) {
		this.idConsideracionActualizar = idConsideracionActualizar;
	}

	/**
	 * @return the idRestriccionInsertar
	 */
	public boolean isIdRestriccionInsertar() {
		return idRestriccionInsertar;
	}

	/**
	 * @param idRestriccionInsertar the idRestriccionInsertar to set
	 */
	public void setIdRestriccionInsertar(boolean idRestriccionInsertar) {
		this.idRestriccionInsertar = idRestriccionInsertar;
	}

	/**
	 * @return the idRestriccionEditar
	 */
	public boolean isIdRestriccionEditar() {
		return idRestriccionEditar;
	}

	/**
	 * @param idRestriccionEditar the idRestriccionEditar to set
	 */
	public void setIdRestriccionEditar(boolean idRestriccionEditar) {
		this.idRestriccionEditar = idRestriccionEditar;
	}

	/**
	 * @return the idRestriccionActualizar
	 */
	public boolean isIdRestriccionActualizar() {
		return idRestriccionActualizar;
	}

	/**
	 * @param idRestriccionActualizar the idRestriccionActualizar to set
	 */
	public void setIdRestriccionActualizar(boolean idRestriccionActualizar) {
		this.idRestriccionActualizar = idRestriccionActualizar;
	}

	/**
	 * @return the mostrarConsideracionSimple
	 */
	public boolean isMostrarConsideracionSimple() {
		return mostrarConsideracionSimple;
	}

	/**
	 * @param mostrarConsideracionSimple the mostrarConsideracionSimple to set
	 */
	public void setMostrarConsideracionSimple(boolean mostrarConsideracionSimple) {
		this.mostrarConsideracionSimple = mostrarConsideracionSimple;
	}

	/**
	 * @return the mostrarConsideracionUnico
	 */
	public boolean isMostrarConsideracionUnico() {
		return mostrarConsideracionUnico;
	}

	/**
	 * @param mostrarConsideracionUnico the mostrarConsideracionUnico to set
	 */
	public void setMostrarConsideracionUnico(boolean mostrarConsideracionUnico) {
		this.mostrarConsideracionUnico = mostrarConsideracionUnico;
	}

	/**
	 * @return the mostrarConsideracionDoble
	 */
	public boolean isMostrarConsideracionDoble() {
		return mostrarConsideracionDoble;
	}

	/**
	 * @param mostrarConsideracionDoble the mostrarConsideracionDoble to set
	 */
	public void setMostrarConsideracionDoble(boolean mostrarConsideracionDoble) {
		this.mostrarConsideracionDoble = mostrarConsideracionDoble;
	}

	/**
	 * @return the mostrarConsideracionMixto
	 */
	public boolean isMostrarConsideracionMixto() {
		return mostrarConsideracionMixto;
	}

	/**
	 * @param mostrarConsideracionMixto the mostrarConsideracionMixto to set
	 */
	public void setMostrarConsideracionMixto(boolean mostrarConsideracionMixto) {
		this.mostrarConsideracionMixto = mostrarConsideracionMixto;
	}

	/**
	 * @return the mostrarThUnicoUnidades
	 */
	public boolean isMostrarThUnicoUnidades() {
		return mostrarThUnicoUnidades;
	}

	/**
	 * @param mostrarThUnicoUnidades the mostrarThUnicoUnidades to set
	 */
	public void setMostrarThUnicoUnidades(boolean mostrarThUnicoUnidades) {
		this.mostrarThUnicoUnidades = mostrarThUnicoUnidades;
	}

	/**
	 * @return the mostrarTdUnicoUnidades
	 */
	public boolean isMostrarTdUnicoUnidades() {
		return mostrarTdUnicoUnidades;
	}

	/**
	 * @param mostrarTdUnicoUnidades the mostrarTdUnicoUnidades to set
	 */
	public void setMostrarTdUnicoUnidades(boolean mostrarTdUnicoUnidades) {
		this.mostrarTdUnicoUnidades = mostrarTdUnicoUnidades;
	}

	/**
	 * @return the mostrarThDobleUnidades
	 */
	public boolean isMostrarThDobleUnidades() {
		return mostrarThDobleUnidades;
	}

	/**
	 * @param mostrarThDobleUnidades the mostrarThDobleUnidades to set
	 */
	public void setMostrarThDobleUnidades(boolean mostrarThDobleUnidades) {
		this.mostrarThDobleUnidades = mostrarThDobleUnidades;
	}

	/**
	 * @return the mostrarTdDobleUnidades
	 */
	public boolean isMostrarTdDobleUnidades() {
		return mostrarTdDobleUnidades;
	}

	/**
	 * @param mostrarTdDobleUnidades the mostrarTdDobleUnidades to set
	 */
	public void setMostrarTdDobleUnidades(boolean mostrarTdDobleUnidades) {
		this.mostrarTdDobleUnidades = mostrarTdDobleUnidades;
	}

	/**
	 * @return the mostrarThMixtoUnidades
	 */
	public boolean isMostrarThMixtoUnidades() {
		return mostrarThMixtoUnidades;
	}

	/**
	 * @param mostrarThMixtoUnidades the mostrarThMixtoUnidades to set
	 */
	public void setMostrarThMixtoUnidades(boolean mostrarThMixtoUnidades) {
		this.mostrarThMixtoUnidades = mostrarThMixtoUnidades;
	}

	/**
	 * @return the mostrarTdMixtoUnidades
	 */
	public boolean isMostrarTdMixtoUnidades() {
		return mostrarTdMixtoUnidades;
	}

	/**
	 * @param mostrarTdMixtoUnidades the mostrarTdMixtoUnidades to set
	 */
	public void setMostrarTdMixtoUnidades(boolean mostrarTdMixtoUnidades) {
		this.mostrarTdMixtoUnidades = mostrarTdMixtoUnidades;
	}

	/**
	 * @return the mostrarTdMixtoMonto1
	 */
	public boolean isMostrarTdMixtoMonto1() {
		return mostrarTdMixtoMonto1;
	}

	/**
	 * @param mostrarTdMixtoMonto1 the mostrarTdMixtoMonto1 to set
	 */
	public void setMostrarTdMixtoMonto1(boolean mostrarTdMixtoMonto1) {
		this.mostrarTdMixtoMonto1 = mostrarTdMixtoMonto1;
	}

	/**
	 * @return the mostrarTdMixtoMonto2
	 */
	public boolean isMostrarTdMixtoMonto2() {
		return mostrarTdMixtoMonto2;
	}

	/**
	 * @param mostrarTdMixtoMonto2 the mostrarTdMixtoMonto2 to set
	 */
	public void setMostrarTdMixtoMonto2(boolean mostrarTdMixtoMonto2) {
		this.mostrarTdMixtoMonto2 = mostrarTdMixtoMonto2;
	}

	/**
	 * @return the mostrarTdMixtoMarca1
	 */
	public boolean isMostrarTdMixtoMarca1() {
		return mostrarTdMixtoMarca1;
	}

	/**
	 * @param mostrarTdMixtoMarca1 the mostrarTdMixtoMarca1 to set
	 */
	public void setMostrarTdMixtoMarca1(boolean mostrarTdMixtoMarca1) {
		this.mostrarTdMixtoMarca1 = mostrarTdMixtoMarca1;
	}

	/**
	 * @return the mostrarTdMixtoMarca2
	 */
	public boolean isMostrarTdMixtoMarca2() {
		return mostrarTdMixtoMarca2;
	}

	/**
	 * @param mostrarTdMixtoMarca2 the mostrarTdMixtoMarca2 to set
	 */
	public void setMostrarTdMixtoMarca2(boolean mostrarTdMixtoMarca2) {
		this.mostrarTdMixtoMarca2 = mostrarTdMixtoMarca2;
	}

	/**
	 * @return the mostrarTdMixtoNegocio1
	 */
	public boolean isMostrarTdMixtoNegocio1() {
		return mostrarTdMixtoNegocio1;
	}

	/**
	 * @param mostrarTdMixtoNegocio1 the mostrarTdMixtoNegocio1 to set
	 */
	public void setMostrarTdMixtoNegocio1(boolean mostrarTdMixtoNegocio1) {
		this.mostrarTdMixtoNegocio1 = mostrarTdMixtoNegocio1;
	}

	/**
	 * @return the mostrarTdMixtoNegocio2
	 */
	public boolean isMostrarTdMixtoNegocio2() {
		return mostrarTdMixtoNegocio2;
	}

	/**
	 * @param mostrarTdMixtoNegocio2 the mostrarTdMixtoNegocio2 to set
	 */
	public void setMostrarTdMixtoNegocio2(boolean mostrarTdMixtoNegocio2) {
		this.mostrarTdMixtoNegocio2 = mostrarTdMixtoNegocio2;
	}

	/**
	 * @return the mostrarTdMixtoUnidadNegocio1
	 */
	public boolean isMostrarTdMixtoUnidadNegocio1() {
		return mostrarTdMixtoUnidadNegocio1;
	}

	/**
	 * @param mostrarTdMixtoUnidadNegocio1 the mostrarTdMixtoUnidadNegocio1 to set
	 */
	public void setMostrarTdMixtoUnidadNegocio1(boolean mostrarTdMixtoUnidadNegocio1) {
		this.mostrarTdMixtoUnidadNegocio1 = mostrarTdMixtoUnidadNegocio1;
	}

	/**
	 * @return the mostrarTdMixtoUnidadNegocio2
	 */
	public boolean isMostrarTdMixtoUnidadNegocio2() {
		return mostrarTdMixtoUnidadNegocio2;
	}

	/**
	 * @param mostrarTdMixtoUnidadNegocio2 the mostrarTdMixtoUnidadNegocio2 to set
	 */
	public void setMostrarTdMixtoUnidadNegocio2(boolean mostrarTdMixtoUnidadNegocio2) {
		this.mostrarTdMixtoUnidadNegocio2 = mostrarTdMixtoUnidadNegocio2;
	}

	/**
	 * @return the mostrarTdMixtoCatalogo1
	 */
	public boolean isMostrarTdMixtoCatalogo1() {
		return mostrarTdMixtoCatalogo1;
	}

	/**
	 * @param mostrarTdMixtoCatalogo1 the mostrarTdMixtoCatalogo1 to set
	 */
	public void setMostrarTdMixtoCatalogo1(boolean mostrarTdMixtoCatalogo1) {
		this.mostrarTdMixtoCatalogo1 = mostrarTdMixtoCatalogo1;
	}

	/**
	 * @return the mostrarTdMixtoCatalogo2
	 */
	public boolean isMostrarTdMixtoCatalogo2() {
		return mostrarTdMixtoCatalogo2;
	}

	/**
	 * @param mostrarTdMixtoCatalogo2 the mostrarTdMixtoCatalogo2 to set
	 */
	public void setMostrarTdMixtoCatalogo2(boolean mostrarTdMixtoCatalogo2) {
		this.mostrarTdMixtoCatalogo2 = mostrarTdMixtoCatalogo2;
	}

	/**
	 * @return the mostrarTdMontoFinanciadoDesde
	 */
	public boolean isMostrarTdMontoFinanciadoDesde() {
		return mostrarTdMontoFinanciadoDesde;
	}

	/**
	 * @param mostrarTdMontoFinanciadoDesde the mostrarTdMontoFinanciadoDesde to set
	 */
	public void setMostrarTdMontoFinanciadoDesde(
			boolean mostrarTdMontoFinanciadoDesde) {
		this.mostrarTdMontoFinanciadoDesde = mostrarTdMontoFinanciadoDesde;
	}

	/**
	 * @return the mostrarTdMontoFinanciadoHasta1
	 */
	public boolean isMostrarTdMontoFinanciadoHasta1() {
		return mostrarTdMontoFinanciadoHasta1;
	}

	/**
	 * @param mostrarTdMontoFinanciadoHasta1 the mostrarTdMontoFinanciadoHasta1 to set
	 */
	public void setMostrarTdMontoFinanciadoHasta1(
			boolean mostrarTdMontoFinanciadoHasta1) {
		this.mostrarTdMontoFinanciadoHasta1 = mostrarTdMontoFinanciadoHasta1;
	}

	/**
	 * @return the mostrarTdMontoFinanciadoHasta2
	 */
	public boolean isMostrarTdMontoFinanciadoHasta2() {
		return mostrarTdMontoFinanciadoHasta2;
	}

	/**
	 * @param mostrarTdMontoFinanciadoHasta2 the mostrarTdMontoFinanciadoHasta2 to set
	 */
	public void setMostrarTdMontoFinanciadoHasta2(
			boolean mostrarTdMontoFinanciadoHasta2) {
		this.mostrarTdMontoFinanciadoHasta2 = mostrarTdMontoFinanciadoHasta2;
	}

	/**
	 * @return the mostrarTdPeriodoFinanciado1
	 */
	public boolean isMostrarTdPeriodoFinanciado1() {
		return mostrarTdPeriodoFinanciado1;
	}

	/**
	 * @param mostrarTdPeriodoFinanciado1 the mostrarTdPeriodoFinanciado1 to set
	 */
	public void setMostrarTdPeriodoFinanciado1(boolean mostrarTdPeriodoFinanciado1) {
		this.mostrarTdPeriodoFinanciado1 = mostrarTdPeriodoFinanciado1;
	}

	/**
	 * @return the mostrarTdPeriodoFinanciado2
	 */
	public boolean isMostrarTdPeriodoFinanciado2() {
		return mostrarTdPeriodoFinanciado2;
	}

	/**
	 * @param mostrarTdPeriodoFinanciado2 the mostrarTdPeriodoFinanciado2 to set
	 */
	public void setMostrarTdPeriodoFinanciado2(boolean mostrarTdPeriodoFinanciado2) {
		this.mostrarTdPeriodoFinanciado2 = mostrarTdPeriodoFinanciado2;
	}

	/**
	 * @return the mostrarThMixtoFormula
	 */
	public boolean isMostrarThMixtoFormula() {
		return mostrarThMixtoFormula;
	}

	/**
	 * @param mostrarThMixtoFormula the mostrarThMixtoFormula to set
	 */
	public void setMostrarThMixtoFormula(boolean mostrarThMixtoFormula) {
		this.mostrarThMixtoFormula = mostrarThMixtoFormula;
	}

	/**
	 * @return the mostrarThMixtoVariableVentas
	 */
	public boolean isMostrarThMixtoVariableVentas() {
		return mostrarThMixtoVariableVentas;
	}

	/**
	 * @param mostrarThMixtoVariableVentas the mostrarThMixtoVariableVentas to set
	 */
	public void setMostrarThMixtoVariableVentas(boolean mostrarThMixtoVariableVentas) {
		this.mostrarThMixtoVariableVentas = mostrarThMixtoVariableVentas;
	}

	/**
	 * @return the mostrarTdMixtoFormula
	 */
	public boolean isMostrarTdMixtoFormula() {
		return mostrarTdMixtoFormula;
	}

	/**
	 * @param mostrarTdMixtoFormula the mostrarTdMixtoFormula to set
	 */
	public void setMostrarTdMixtoFormula(boolean mostrarTdMixtoFormula) {
		this.mostrarTdMixtoFormula = mostrarTdMixtoFormula;
	}

	/**
	 * @return the mostrarThMixtoNroPeriodosFV
	 */
	public boolean isMostrarThMixtoNroPeriodosFV() {
		return mostrarThMixtoNroPeriodosFV;
	}

	/**
	 * @param mostrarThMixtoNroPeriodosFV the mostrarThMixtoNroPeriodosFV to set
	 */
	public void setMostrarThMixtoNroPeriodosFV(boolean mostrarThMixtoNroPeriodosFV) {
		this.mostrarThMixtoNroPeriodosFV = mostrarThMixtoNroPeriodosFV;
	}

	/**
	 * @return the mostrarTdMixtoNroPeriodosFV
	 */
	public boolean isMostrarTdMixtoNroPeriodosFV() {
		return mostrarTdMixtoNroPeriodosFV;
	}

	/**
	 * @param mostrarTdMixtoNroPeriodosFV the mostrarTdMixtoNroPeriodosFV to set
	 */
	public void setMostrarTdMixtoNroPeriodosFV(boolean mostrarTdMixtoNroPeriodosFV) {
		this.mostrarTdMixtoNroPeriodosFV = mostrarTdMixtoNroPeriodosFV;
	}

	/**
	 * @return the mostrarTdMixtoPeriodoInicio1
	 */
	public boolean isMostrarTdMixtoPeriodoInicio1() {
		return mostrarTdMixtoPeriodoInicio1;
	}

	/**
	 * @param mostrarTdMixtoPeriodoInicio1 the mostrarTdMixtoPeriodoInicio1 to set
	 */
	public void setMostrarTdMixtoPeriodoInicio1(boolean mostrarTdMixtoPeriodoInicio1) {
		this.mostrarTdMixtoPeriodoInicio1 = mostrarTdMixtoPeriodoInicio1;
	}

	/**
	 * @return the mostrarTdMixtoPeriodoInicio2
	 */
	public boolean isMostrarTdMixtoPeriodoInicio2() {
		return mostrarTdMixtoPeriodoInicio2;
	}

	/**
	 * @param mostrarTdMixtoPeriodoInicio2 the mostrarTdMixtoPeriodoInicio2 to set
	 */
	public void setMostrarTdMixtoPeriodoInicio2(boolean mostrarTdMixtoPeriodoInicio2) {
		this.mostrarTdMixtoPeriodoInicio2 = mostrarTdMixtoPeriodoInicio2;
	}

	/**
	 * @return the mostrarTdMixtoPeriodoFin1
	 */
	public boolean isMostrarTdMixtoPeriodoFin1() {
		return mostrarTdMixtoPeriodoFin1;
	}

	/**
	 * @param mostrarTdMixtoPeriodoFin1 the mostrarTdMixtoPeriodoFin1 to set
	 */
	public void setMostrarTdMixtoPeriodoFin1(boolean mostrarTdMixtoPeriodoFin1) {
		this.mostrarTdMixtoPeriodoFin1 = mostrarTdMixtoPeriodoFin1;
	}

	/**
	 * @return the mostrarTdMixtoPeriodoFin2
	 */
	public boolean isMostrarTdMixtoPeriodoFin2() {
		return mostrarTdMixtoPeriodoFin2;
	}

	/**
	 * @param mostrarTdMixtoPeriodoFin2 the mostrarTdMixtoPeriodoFin2 to set
	 */
	public void setMostrarTdMixtoPeriodoFin2(boolean mostrarTdMixtoPeriodoFin2) {
		this.mostrarTdMixtoPeriodoFin2 = mostrarTdMixtoPeriodoFin2;
	}

	/**
	 * @return the mostrarThMixtoTipoVenta
	 */
	public boolean isMostrarThMixtoTipoVenta() {
		return mostrarThMixtoTipoVenta;
	}

	/**
	 * @param mostrarThMixtoTipoVenta the mostrarThMixtoTipoVenta to set
	 */
	public void setMostrarThMixtoTipoVenta(boolean mostrarThMixtoTipoVenta) {
		this.mostrarThMixtoTipoVenta = mostrarThMixtoTipoVenta;
	}

	/**
	 * @return the mostrarTdMixtoTipoVenta2
	 */
	public boolean isMostrarTdMixtoTipoVenta2() {
		return mostrarTdMixtoTipoVenta2;
	}

	/**
	 * @param mostrarTdMixtoTipoVenta2 the mostrarTdMixtoTipoVenta2 to set
	 */
	public void setMostrarTdMixtoTipoVenta2(boolean mostrarTdMixtoTipoVenta2) {
		this.mostrarTdMixtoTipoVenta2 = mostrarTdMixtoTipoVenta2;
	}

	/**
	 * @return the mostrarCampoUnidades
	 */
	public boolean isMostrarCampoUnidades() {
		return mostrarCampoUnidades;
	}

	/**
	 * @param mostrarCampoUnidades the mostrarCampoUnidades to set
	 */
	public void setMostrarCampoUnidades(boolean mostrarCampoUnidades) {
		this.mostrarCampoUnidades = mostrarCampoUnidades;
	}

	/**
	 * @return the habilitarRestricciones
	 */
	public boolean isHabilitarRestricciones() {
		return habilitarRestricciones;
	}

	/**
	 * @param habilitarRestricciones the habilitarRestricciones to set
	 */
	public void setHabilitarRestricciones(boolean habilitarRestricciones) {
		this.habilitarRestricciones = habilitarRestricciones;
	}

	/**
	 * @return the mostrarRestriccionSimple
	 */
	public boolean isMostrarRestriccionSimple() {
		return mostrarRestriccionSimple;
	}

	/**
	 * @param mostrarRestriccionSimple the mostrarRestriccionSimple to set
	 */
	public void setMostrarRestriccionSimple(boolean mostrarRestriccionSimple) {
		this.mostrarRestriccionSimple = mostrarRestriccionSimple;
	}

	/**
	 * @return the mostrarRestriccionUnico
	 */
	public boolean isMostrarRestriccionUnico() {
		return mostrarRestriccionUnico;
	}

	/**
	 * @param mostrarRestriccionUnico the mostrarRestriccionUnico to set
	 */
	public void setMostrarRestriccionUnico(boolean mostrarRestriccionUnico) {
		this.mostrarRestriccionUnico = mostrarRestriccionUnico;
	}

	/**
	 * @return the mostrarRestriccionDoble
	 */
	public boolean isMostrarRestriccionDoble() {
		return mostrarRestriccionDoble;
	}

	/**
	 * @param mostrarRestriccionDoble the mostrarRestriccionDoble to set
	 */
	public void setMostrarRestriccionDoble(boolean mostrarRestriccionDoble) {
		this.mostrarRestriccionDoble = mostrarRestriccionDoble;
	}

	/**
	 * @return the mostrarRestriccionMixto
	 */
	public boolean isMostrarRestriccionMixto() {
		return mostrarRestriccionMixto;
	}

	/**
	 * @param mostrarRestriccionMixto the mostrarRestriccionMixto to set
	 */
	public void setMostrarRestriccionMixto(boolean mostrarRestriccionMixto) {
		this.mostrarRestriccionMixto = mostrarRestriccionMixto;
	}

	/**
	 * @return the mostrarThUnicoUnidadesRest
	 */
	public boolean isMostrarThUnicoUnidadesRest() {
		return mostrarThUnicoUnidadesRest;
	}

	/**
	 * @param mostrarThUnicoUnidadesRest the mostrarThUnicoUnidadesRest to set
	 */
	public void setMostrarThUnicoUnidadesRest(boolean mostrarThUnicoUnidadesRest) {
		this.mostrarThUnicoUnidadesRest = mostrarThUnicoUnidadesRest;
	}

	/**
	 * @return the mostrarTdUnicoUnidadesRest
	 */
	public boolean isMostrarTdUnicoUnidadesRest() {
		return mostrarTdUnicoUnidadesRest;
	}

	/**
	 * @param mostrarTdUnicoUnidadesRest the mostrarTdUnicoUnidadesRest to set
	 */
	public void setMostrarTdUnicoUnidadesRest(boolean mostrarTdUnicoUnidadesRest) {
		this.mostrarTdUnicoUnidadesRest = mostrarTdUnicoUnidadesRest;
	}

	/**
	 * @return the mostrarThDobleUnidadesRest
	 */
	public boolean isMostrarThDobleUnidadesRest() {
		return mostrarThDobleUnidadesRest;
	}

	/**
	 * @param mostrarThDobleUnidadesRest the mostrarThDobleUnidadesRest to set
	 */
	public void setMostrarThDobleUnidadesRest(boolean mostrarThDobleUnidadesRest) {
		this.mostrarThDobleUnidadesRest = mostrarThDobleUnidadesRest;
	}

	/**
	 * @return the mostrarThMixtoUnidadesRest
	 */
	public boolean isMostrarThMixtoUnidadesRest() {
		return mostrarThMixtoUnidadesRest;
	}

	/**
	 * @param mostrarThMixtoUnidadesRest the mostrarThMixtoUnidadesRest to set
	 */
	public void setMostrarThMixtoUnidadesRest(boolean mostrarThMixtoUnidadesRest) {
		this.mostrarThMixtoUnidadesRest = mostrarThMixtoUnidadesRest;
	}

	/**
	 * @return the mostrarTdDobleUnidadesRest
	 */
	public boolean isMostrarTdDobleUnidadesRest() {
		return mostrarTdDobleUnidadesRest;
	}

	/**
	 * @param mostrarTdDobleUnidadesRest the mostrarTdDobleUnidadesRest to set
	 */
	public void setMostrarTdDobleUnidadesRest(boolean mostrarTdDobleUnidadesRest) {
		this.mostrarTdDobleUnidadesRest = mostrarTdDobleUnidadesRest;
	}

	/**
	 * @return the mostrarTdMixtoUnidadesRest
	 */
	public boolean isMostrarTdMixtoUnidadesRest() {
		return mostrarTdMixtoUnidadesRest;
	}

	/**
	 * @param mostrarTdMixtoUnidadesRest the mostrarTdMixtoUnidadesRest to set
	 */
	public void setMostrarTdMixtoUnidadesRest(boolean mostrarTdMixtoUnidadesRest) {
		this.mostrarTdMixtoUnidadesRest = mostrarTdMixtoUnidadesRest;
	}

	/**
	 * @return the mostrarTdMixtoMonto1Rest
	 */
	public boolean isMostrarTdMixtoMonto1Rest() {
		return mostrarTdMixtoMonto1Rest;
	}

	/**
	 * @param mostrarTdMixtoMonto1Rest the mostrarTdMixtoMonto1Rest to set
	 */
	public void setMostrarTdMixtoMonto1Rest(boolean mostrarTdMixtoMonto1Rest) {
		this.mostrarTdMixtoMonto1Rest = mostrarTdMixtoMonto1Rest;
	}

	/**
	 * @return the mostrarTdMixtoMonto2Rest
	 */
	public boolean isMostrarTdMixtoMonto2Rest() {
		return mostrarTdMixtoMonto2Rest;
	}

	/**
	 * @param mostrarTdMixtoMonto2Rest the mostrarTdMixtoMonto2Rest to set
	 */
	public void setMostrarTdMixtoMonto2Rest(boolean mostrarTdMixtoMonto2Rest) {
		this.mostrarTdMixtoMonto2Rest = mostrarTdMixtoMonto2Rest;
	}

	/**
	 * @return the mostrarTdMixtoMarca1Rest
	 */
	public boolean isMostrarTdMixtoMarca1Rest() {
		return mostrarTdMixtoMarca1Rest;
	}

	/**
	 * @param mostrarTdMixtoMarca1Rest the mostrarTdMixtoMarca1Rest to set
	 */
	public void setMostrarTdMixtoMarca1Rest(boolean mostrarTdMixtoMarca1Rest) {
		this.mostrarTdMixtoMarca1Rest = mostrarTdMixtoMarca1Rest;
	}

	/**
	 * @return the mostrarTdMixtoMarca2Rest
	 */
	public boolean isMostrarTdMixtoMarca2Rest() {
		return mostrarTdMixtoMarca2Rest;
	}

	/**
	 * @param mostrarTdMixtoMarca2Rest the mostrarTdMixtoMarca2Rest to set
	 */
	public void setMostrarTdMixtoMarca2Rest(boolean mostrarTdMixtoMarca2Rest) {
		this.mostrarTdMixtoMarca2Rest = mostrarTdMixtoMarca2Rest;
	}

	/**
	 * @return the mostrarTdMixtoNegocio1Rest
	 */
	public boolean isMostrarTdMixtoNegocio1Rest() {
		return mostrarTdMixtoNegocio1Rest;
	}

	/**
	 * @param mostrarTdMixtoNegocio1Rest the mostrarTdMixtoNegocio1Rest to set
	 */
	public void setMostrarTdMixtoNegocio1Rest(boolean mostrarTdMixtoNegocio1Rest) {
		this.mostrarTdMixtoNegocio1Rest = mostrarTdMixtoNegocio1Rest;
	}

	/**
	 * @return the mostrarTdMixtoNegocio2Rest
	 */
	public boolean isMostrarTdMixtoNegocio2Rest() {
		return mostrarTdMixtoNegocio2Rest;
	}

	/**
	 * @param mostrarTdMixtoNegocio2Rest the mostrarTdMixtoNegocio2Rest to set
	 */
	public void setMostrarTdMixtoNegocio2Rest(boolean mostrarTdMixtoNegocio2Rest) {
		this.mostrarTdMixtoNegocio2Rest = mostrarTdMixtoNegocio2Rest;
	}

	/**
	 * @return the mostrarTdMixtoUnidadNegocio1Rest
	 */
	public boolean isMostrarTdMixtoUnidadNegocio1Rest() {
		return mostrarTdMixtoUnidadNegocio1Rest;
	}

	/**
	 * @param mostrarTdMixtoUnidadNegocio1Rest the mostrarTdMixtoUnidadNegocio1Rest to set
	 */
	public void setMostrarTdMixtoUnidadNegocio1Rest(
			boolean mostrarTdMixtoUnidadNegocio1Rest) {
		this.mostrarTdMixtoUnidadNegocio1Rest = mostrarTdMixtoUnidadNegocio1Rest;
	}

	/**
	 * @return the mostrarTdMixtoUnidadNegocio2Rest
	 */
	public boolean isMostrarTdMixtoUnidadNegocio2Rest() {
		return mostrarTdMixtoUnidadNegocio2Rest;
	}

	/**
	 * @param mostrarTdMixtoUnidadNegocio2Rest the mostrarTdMixtoUnidadNegocio2Rest to set
	 */
	public void setMostrarTdMixtoUnidadNegocio2Rest(
			boolean mostrarTdMixtoUnidadNegocio2Rest) {
		this.mostrarTdMixtoUnidadNegocio2Rest = mostrarTdMixtoUnidadNegocio2Rest;
	}

	/**
	 * @return the mostrarTdMixtoCatalogo1Rest
	 */
	public boolean isMostrarTdMixtoCatalogo1Rest() {
		return mostrarTdMixtoCatalogo1Rest;
	}

	/**
	 * @param mostrarTdMixtoCatalogo1Rest the mostrarTdMixtoCatalogo1Rest to set
	 */
	public void setMostrarTdMixtoCatalogo1Rest(boolean mostrarTdMixtoCatalogo1Rest) {
		this.mostrarTdMixtoCatalogo1Rest = mostrarTdMixtoCatalogo1Rest;
	}

	/**
	 * @return the mostrarTdMixtoCatalogo2Rest
	 */
	public boolean isMostrarTdMixtoCatalogo2Rest() {
		return mostrarTdMixtoCatalogo2Rest;
	}

	/**
	 * @param mostrarTdMixtoCatalogo2Rest the mostrarTdMixtoCatalogo2Rest to set
	 */
	public void setMostrarTdMixtoCatalogo2Rest(boolean mostrarTdMixtoCatalogo2Rest) {
		this.mostrarTdMixtoCatalogo2Rest = mostrarTdMixtoCatalogo2Rest;
	}

	/**
	 * @return the esBotonInsertar
	 */
	public boolean isEsBotonInsertar() {
		return esBotonInsertar;
	}

	/**
	 * @param esBotonInsertar the esBotonInsertar to set
	 */
	public void setEsBotonInsertar(boolean esBotonInsertar) {
		this.esBotonInsertar = esBotonInsertar;
	}

	/**
	 * @return the beanRegistroDetalleConsideracion
	 */
	public Object getBeanRegistroDetalleConsideracion() {
		return beanRegistroDetalleConsideracion;
	}

	/**
	 * @param beanRegistroDetalleConsideracion the beanRegistroDetalleConsideracion to set
	 */
	public void setBeanRegistroDetalleConsideracion(
			Object beanRegistroDetalleConsideracion) {
		this.beanRegistroDetalleConsideracion = beanRegistroDetalleConsideracion;
	}

	/**
	 * @return the dataTableDetalleConsideracion
	 */
	public DataTableModel getDataTableDetalleConsideracion() {
		return dataTableDetalleConsideracion;
	}

	/**
	 * @param dataTableDetalleConsideracion the dataTableDetalleConsideracion to set
	 */
	public void setDataTableDetalleConsideracion(
			DataTableModel dataTableDetalleConsideracion) {
		this.dataTableDetalleConsideracion = dataTableDetalleConsideracion;
	}

	/**
	 * @return the codigoConsRest
	 */
	public String getCodigoConsRest() {
		return codigoConsRest;
	}

	/**
	 * @param codigoConsRest the codigoConsRest to set
	 */
	public void setCodigoConsRest(String codigoConsRest) {
		this.codigoConsRest = codigoConsRest;
	}

	/**
	 * @return the siccZonaList
	 */
	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	/**
	 * @param siccZonaList the siccZonaList to set
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
	 * @param siccSeccionList the siccSeccionList to set
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
	 * @param siccTerritorioList the siccTerritorioList to set
	 */
	public void setSiccTerritorioList(LabelValue[] siccTerritorioList) {
		this.siccTerritorioList = siccTerritorioList;
	}

	/**
	 * @return the seleccionable
	 */
	public boolean isSeleccionable() {
		return seleccionable;
	}

	/**
	 * @param seleccionable the seleccionable to set
	 */
	public void setSeleccionable(boolean seleccionable) {
		this.seleccionable = seleccionable;
	}

	/**
	 * @return the nombreVentanaPopup
	 */
	public String getNombreVentanaPopup() {
		return nombreVentanaPopup;
	}

	/**
	 * @param nombreVentanaPopup the nombreVentanaPopup to set
	 */
	public void setNombreVentanaPopup(String nombreVentanaPopup) {
		this.nombreVentanaPopup = nombreVentanaPopup;
	}

	/**
	 * @return the archivo
	 */
	public UploadedFile getArchivo() {
		return archivo;
	}

	/**
	 * @param archivo the archivo to set
	 */
	public void setArchivo(UploadedFile archivo) {
		this.archivo = archivo;
	}

	/**
	 * @return the directorioTemporal
	 */
	public String getDirectorioTemporal() {
		return directorioTemporal;
	}

	/**
	 * @param directorioTemporal the directorioTemporal to set
	 */
	public void setDirectorioTemporal(String directorioTemporal) {
		this.directorioTemporal = directorioTemporal;
	}

	/**
	 * @return the nombreArchivo
	 */
	public String getNombreArchivo() {
		return nombreArchivo;
	}

	/**
	 * @param nombreArchivo the nombreArchivo to set
	 */
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	/**
	 * @return the extensionArchivo
	 */
	public String getExtensionArchivo() {
		return extensionArchivo;
	}

	/**
	 * @param extensionArchivo the extensionArchivo to set
	 */
	public void setExtensionArchivo(String extensionArchivo) {
		this.extensionArchivo = extensionArchivo;
	}

	/**
	 * @return the numRegistros
	 */
	public String getNumRegistros() {
		return numRegistros;
	}

	/**
	 * @param numRegistros the numRegistros to set
	 */
	public void setNumRegistros(String numRegistros) {
		this.numRegistros = numRegistros;
	}

	/**
	 * @return the numRegistrosError
	 */
	public String getNumRegistrosError() {
		return numRegistrosError;
	}

	/**
	 * @param numRegistrosError the numRegistrosError to set
	 */
	public void setNumRegistrosError(String numRegistrosError) {
		this.numRegistrosError = numRegistrosError;
	}

	/**
	 * @return the indicadorValido
	 */
	public String getIndicadorValido() {
		return indicadorValido;
	}

	/**
	 * @param indicadorValido the indicadorValido to set
	 */
	public void setIndicadorValido(String indicadorValido) {
		this.indicadorValido = indicadorValido;
	}

	/**
	 * @return the numRegistrosValido
	 */
	public String getNumRegistrosValido() {
		return numRegistrosValido;
	}

	/**
	 * @param numRegistrosValido the numRegistrosValido to set
	 */
	public void setNumRegistrosValido(String numRegistrosValido) {
		this.numRegistrosValido = numRegistrosValido;
	}

	/**
	 * @return the mavArchivoRegioneslist
	 */
	public List getMavArchivoRegioneslist() {
		return mavArchivoRegioneslist;
	}

	/**
	 * @param mavArchivoRegioneslist the mavArchivoRegioneslist to set
	 */
	public void setMavArchivoRegioneslist(List mavArchivoRegioneslist) {
		this.mavArchivoRegioneslist = mavArchivoRegioneslist;
	}

	/**
	 * @return the viewValida
	 */
	public String getViewValida() {
		return viewValida;
	}

	/**
	 * @param viewValida the viewValida to set
	 */
	public void setViewValida(String viewValida) {
		this.viewValida = viewValida;
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
	 * @return the mostrarBotonValidar
	 */
	public boolean isMostrarBotonValidar() {
		return mostrarBotonValidar;
	}

	/**
	 * @param mostrarBotonValidar the mostrarBotonValidar to set
	 */
	public void setMostrarBotonValidar(boolean mostrarBotonValidar) {
		this.mostrarBotonValidar = mostrarBotonValidar;
	}

	/**
	 * @return the mostrarBotonExecute
	 */
	public boolean isMostrarBotonExecute() {
		return mostrarBotonExecute;
	}

	/**
	 * @param mostrarBotonExecute the mostrarBotonExecute to set
	 */
	public void setMostrarBotonExecute(boolean mostrarBotonExecute) {
		this.mostrarBotonExecute = mostrarBotonExecute;
	}

	/**
	 * @return the mostrarPrimeraGrilla
	 */
	public boolean isMostrarPrimeraGrilla() {
		return mostrarPrimeraGrilla;
	}

	/**
	 * @param mostrarPrimeraGrilla the mostrarPrimeraGrilla to set
	 */
	public void setMostrarPrimeraGrilla(boolean mostrarPrimeraGrilla) {
		this.mostrarPrimeraGrilla = mostrarPrimeraGrilla;
	}

	/**
	 * @return the mostrarSegundaGrilla
	 */
	public boolean isMostrarSegundaGrilla() {
		return mostrarSegundaGrilla;
	}

	/**
	 * @param mostrarSegundaGrilla the mostrarSegundaGrilla to set
	 */
	public void setMostrarSegundaGrilla(boolean mostrarSegundaGrilla) {
		this.mostrarSegundaGrilla = mostrarSegundaGrilla;
	}

	/**
	 * @return the beanRegistroDetalleRestriccion
	 */
	public Object getBeanRegistroDetalleRestriccion() {
		return beanRegistroDetalleRestriccion;
	}

	/**
	 * @param beanRegistroDetalleRestriccion the beanRegistroDetalleRestriccion to set
	 */
	public void setBeanRegistroDetalleRestriccion(
			Object beanRegistroDetalleRestriccion) {
		this.beanRegistroDetalleRestriccion = beanRegistroDetalleRestriccion;
	}

	/**
	 * @return the dataTableDetalleRestriccion
	 */
	public DataTableModel getDataTableDetalleRestriccion() {
		return dataTableDetalleRestriccion;
	}

	/**
	 * @param dataTableDetalleRestriccion the dataTableDetalleRestriccion to set
	 */
	public void setDataTableDetalleRestriccion(
			DataTableModel dataTableDetalleRestriccion) {
		this.dataTableDetalleRestriccion = dataTableDetalleRestriccion;
	}

	/**
	 * @return the mavArchivolist
	 */
	public List getMavArchivolist() {
		return mavArchivolist;
	}

	/**
	 * @param mavArchivolist the mavArchivolist to set
	 */
	public void setMavArchivolist(List mavArchivolist) {
		this.mavArchivolist = mavArchivolist;
	}

	/**
	 * @return the tipoArchivo
	 */
	public String getTipoArchivo() {
		return tipoArchivo;
	}

	/**
	 * @param tipoArchivo the tipoArchivo to set
	 */
	public void setTipoArchivo(String tipoArchivo) {
		this.tipoArchivo = tipoArchivo;
	}

	/**
	 * @return the beanRegistroEditRegionZona
	 */
	public Object getBeanRegistroEditRegionZona() {
		return beanRegistroEditRegionZona;
	}

	/**
	 * @param beanRegistroEditRegionZona the beanRegistroEditRegionZona to set
	 */
	public void setBeanRegistroEditRegionZona(Object beanRegistroEditRegionZona) {
		this.beanRegistroEditRegionZona = beanRegistroEditRegionZona;
	}

	/**
	 * @return the dataTableEditRegionZona
	 */
	public DataTableModel getDataTableEditRegionZona() {
		return dataTableEditRegionZona;
	}

	/**
	 * @param dataTableEditRegionZona the dataTableEditRegionZona to set
	 */
	public void setDataTableEditRegionZona(DataTableModel dataTableEditRegionZona) {
		this.dataTableEditRegionZona = dataTableEditRegionZona;
	}

	/**
	 * @return the mostrarBotonActualizar
	 */
	public boolean isMostrarBotonActualizar() {
		return mostrarBotonActualizar;
	}

	/**
	 * @param mostrarBotonActualizar the mostrarBotonActualizar to set
	 */
	public void setMostrarBotonActualizar(boolean mostrarBotonActualizar) {
		this.mostrarBotonActualizar = mostrarBotonActualizar;
	}

	/**
	 * @return the dataTableEditConsultora
	 */
	public DataTableModel getDataTableEditConsultora() {
		return dataTableEditConsultora;
	}

	/**
	 * @param dataTableEditConsultora the dataTableEditConsultora to set
	 */
	public void setDataTableEditConsultora(DataTableModel dataTableEditConsultora) {
		this.dataTableEditConsultora = dataTableEditConsultora;
	}

	/**
	 * @return the beanRegistroEditConsultora
	 */
	public Object getBeanRegistroEditConsultora() {
		return beanRegistroEditConsultora;
	}

	/**
	 * @param beanRegistroEditConsultora the beanRegistroEditConsultora to set
	 */
	public void setBeanRegistroEditConsultora(Object beanRegistroEditConsultora) {
		this.beanRegistroEditConsultora = beanRegistroEditConsultora;
	}

	/**
	 * @return the editableUnidadesIniciado
	 */
	public boolean isEditableUnidadesIniciado() {
		return editableUnidadesIniciado;
	}

	/**
	 * @param editableUnidadesIniciado the editableUnidadesIniciado to set
	 */
	public void setEditableUnidadesIniciado(boolean editableUnidadesIniciado) {
		this.editableUnidadesIniciado = editableUnidadesIniciado;
	}

	/**
	 * @return the activarComboRestriccion
	 */
	public boolean isActivarComboRestriccion() {
		return activarComboRestriccion;
	}

	/**
	 * @param activarComboRestriccion the activarComboRestriccion to set
	 */
	public void setActivarComboRestriccion(boolean activarComboRestriccion) {
		this.activarComboRestriccion = activarComboRestriccion;
	}
	
	
	
}
