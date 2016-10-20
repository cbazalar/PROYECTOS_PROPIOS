package biz.belcorp.ssicc.web.spusicc.zon.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.context.RequestContext;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Idioma;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.LabelValueCDR;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.spusicc.zon.model.HistoricoDirectorioVenta;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.ClienteUAGenerarService;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.IdiomaService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.zon.MantenimientoZONDirectorioService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.scsicc.hip.action.BusquedaHIPClienteSearchAction;
import biz.belcorp.ssicc.web.scsicc.hip.form.BusquedaHIPClienteSearchForm;
import biz.belcorp.ssicc.web.spusicc.zon.form.MantenimientoZONCargosForm;
import biz.belcorp.ssicc.web.spusicc.zon.form.MantenimientoZONDirectorioVentasSearchForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"rawtypes", "unchecked"})
public class MantenimientoZONDirectorioVentasSearchAction extends BaseMantenimientoSearchAbstractAction
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8597204078236356068L;
	
	private String zonMantOidIdioma;
	private List zonTipoCargoList;
	private LabelValue[] siccRegionList;
	private String longitudCampoClientes;
	private List zonPerfilList;
	private List zonRolList;
	private List zonNovedadesList;
	private List listaDirectorioVentas;
	private boolean flagFuturas;
	private LabelValueCDR[] siccZonaList;
	private String rutaPagina;
	
	//popup cliente
	private boolean mostrarPopUpCliente = false;
	private static final String POPUP_CLIENTES = "SCLIENTES";
	private static final String POPUP_ASIGNAR = "SCLIENTESASIGNAR";
	private static final String POPUP_LICENCIAS = "SCLIENTESLICENCIAS";
	
	@ManagedProperty(value="#{mantenimientoZONCargosAction}")
	private MantenimientoZONCargosAction mantenimientoZONCargosAction;
	
	@ManagedProperty(value="#{busquedaHIPClienteSearchAction}")
	private BusquedaHIPClienteSearchAction busquedaHIPClienteSearchAction;
	
	// utiliza para mostrar/ocultar popup
	private Boolean flag;
	
	//pantalla asignar cargos
	private List zonMantAsignarTipoCargoList;
	private LabelValue[] zonMantAsignarRegionList = {};
	private LabelValueCDR[] zonMantAsignarZonaList = {};
	private String zonMantAsignarTipoCargoCerrar;
	private MantenimientoZONCargosForm mantenimientoZONCargosForm;
	
	// flag para ocultar/mostrar select multiple de region y zona
	private Boolean flagOcultarMultiple;

	// flag ocultar/mostrar zonas
	private Boolean flagOcultarZonas;
	
	//pantalla mantener licencias
	private List zonMantTipoLicenciaList;
	private LabelValue[] zonCargosList = {};
	private Map idRegistroLicencia;
	private String diasLicencia;
	private String fecFacturacion;
	private String grabarLicencias;
	private String grabarAsignar;

	@Override
	protected String getSalirForward() {
		return "mantenimientoZONDirectorioVentasSearchForm";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoZONDirectorioVentasSearchForm searchForm = new MantenimientoZONDirectorioVentasSearchForm();
		return searchForm;
	}

	@Override
	protected List setFindAttributes() throws Exception 
	{
		AjaxService ajaxService = (AjaxService)	this.getBean("ajaxService");
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		MantenimientoZONDirectorioVentasSearchForm f = (MantenimientoZONDirectorioVentasSearchForm) this.formBusqueda;
		MantenimientoZONDirectorioService service = (MantenimientoZONDirectorioService)	this.getBean("spusicc.mantenimientoZonDirectorioService");
		f.setFechaIngresoDesde(DateUtil.convertDateToString(f.getFechaIngresoDesdeDate()));
		f.setFechaIngresoHasta(DateUtil.convertDateToString(f.getFechaIngresoHastaDate()));
		
		Map criteria = BeanUtils.describe(f);
		criteria.put("codigoConexionExterna", pais.getCodigoConexionExterna());
		List listDir = service.getDirectorioVentasList(criteria);
		
		this.listaDirectorioVentas =  listDir;
		
		f.setCodigoZona(StringUtils.isNotBlank(f.getCodigoZona())?f.getCodigoZona():null);
		f.setSelectedItems(new String[0]);
		
		this.siccRegionList = ajaxService.getRegionesAllDirectorioMantenimientoZON(pais.getCodigo(), pais.getCodigoConexionExterna(), Constants.NUMERO_CERO, "ASD");		
		
		if(f.getCodigoZona() != null){
			String[] region = new String[1];
			
			for (int i = 0; i < region.length; i++) {
				region[i] = f.getCodigoRegion();
			}
			
			this.siccZonaList = ajaxService.getZonasAllDirectorioActivas(pais.getCodigo(), pais.getCodigoConexionExterna(), region, "0");
		}
		
		Map criteriaCargo = new HashMap();
		criteriaCargo.put("estado", Constants.ESTADO_ACTIVO);
		if (Constants.NUMERO_CERO.equals(f.getIndicadorFutura()))
			criteriaCargo.put("indCargoNoFuturo", f.getIndicadorFutura());
		else
			criteriaCargo.put("indCargoFuturo", f.getIndicadorFutura());
		
		List resultado = service.getTipoCargo(criteriaCargo);
		this.zonTipoCargoList = resultado;
		return listDir;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setViewAtributes() throws Exception 
	{
		MantenimientoZONDirectorioVentasSearchForm f = (MantenimientoZONDirectorioVentasSearchForm) this.formBusqueda;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		f.setCodigoPais(pais.getCodigo());
		ConvertUtils.register(new DateConverter(null), Date.class);
		
		MantenimientoZONDirectorioService service = (MantenimientoZONDirectorioService)	this.getBean("spusicc.mantenimientoZonDirectorioService");
		AjaxService ajaxService = (AjaxService)	this.getBean("ajaxService");	
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		
		Map criteria = new HashMap();
		criteria.put("estado", Constants.ESTADO_ACTIVO);
		criteria.put("indCargoNoFuturo", Constants.ESTADO_ACTIVO);
		List resultado = service.getTipoCargo(criteria);
        
        //Recuperamos el idioma
  		IdiomaService idiomaService = (IdiomaService) getBean("idiomaService");
  		String s = idiomaService.getLocale(usuario).getLanguage();
  		Idioma idioma = idiomaService.getIdiomaByCodigoISO(s);
        
        Map parameterMap = new HashMap();
		parameterMap.put("codigoIdiomaIso", idioma.getCodigoSiCC());
		parameterMap.put("codigoIdioma", idioma.getCodigoISO());
        
        String oidIdiomaIso = reporteService.getOidString("getOidIdiomaByCodigoIdiomaIso", parameterMap);
		f.setOidIdioma(oidIdiomaIso);
		f.setIndicadorFutura(Constants.NUMERO_CERO);
		
		this.zonMantOidIdioma = f.getOidIdioma();
		this.zonTipoCargoList = resultado;
		this.siccRegionList = ajaxService.getRegionesAllDirectorioMantenimientoZON(pais.getCodigo(), pais.getCodigoConexionExterna(), Constants.NUMERO_CERO, "ASD");
				
		// Longitud del campo consultora
		ClienteUAGenerarService clienteService2 = (ClienteUAGenerarService) getBean("sisicc.clienteUAGenerarService");
		this.longitudCampoClientes = clienteService2.getTamanhoNumeroCliente(pais.getCodigo()).toString();
		
		Map params = new HashMap();
		params.put("indicadorActivo", Constants.UNO);
		List perfiles = service.getPerfilesByCriteria(params);
		List roles = service.getRolesByCriteria(params);
		List novedades = service.getOperaciones(params);
		
		this.zonPerfilList = perfiles;
		this.zonRolList = roles;
		this.zonNovedadesList = novedades;	
		
		this.mostrarBotonNuevo = false;
		this.mostrarBotonModificar = false;
		this.mostrarBotonEliminar = false;
		this.mostrarBotonConsultar = false;
		
		this.flag = false;
	}

	public void loadZonas(ValueChangeEvent event)
	{
		String valor = (String) event.getNewValue();
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		if(valor != null)
		{
			String[] codigoRegiones = new String[1];
			codigoRegiones[0] = valor;
			this.siccZonaList = ajax.getZonasAllDirectorioActivas(pais.getCodigo(), pais.getCodigoConexionExterna(), codigoRegiones, "0");
		}else
			this.siccZonaList = null;
	}
	
	
	public void loadCargosByFlagFuturas(ValueChangeEvent event)
	{
		boolean valor = (Boolean) event.getNewValue();
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		List lista = new ArrayList();
		LabelValue[] aux = new LabelValue[]{};
		
		if(valor)
			 aux = ajax.getCargosByFlagFuturas("1");
		else
			aux = ajax.getCargosByFlagFuturas("0");
		
		 for (LabelValue labelValue : aux) {
			 Map aux1 = new HashMap();
			 aux1.put("descripcion", labelValue.getLabel());
			 aux1.put("codigoCargo", labelValue.getValue());
			 lista.add(aux1);
		}		 
		 this.zonTipoCargoList = lista;		
	}
	
		
	@Override
	protected void setAceptarPopup(ActionEvent event, String accion) 
	{
		if (log.isDebugEnabled()) {
			log.debug("Finish 'PopupHipCliente' method");
		}
		
		if (accion.equals(this.POPUP_CLIENTES)) 
		{
			this.busquedaHIPClienteSearchAction.verificarRegistro(event);
			if (this.busquedaHIPClienteSearchAction.isSeleccionoRegistro()) 
			{
				Map clienteHipMap = (Map) this.busquedaHIPClienteSearchAction.getBeanRegistroSeleccionado();
				MantenimientoZONDirectorioVentasSearchForm f = (MantenimientoZONDirectorioVentasSearchForm) this.formBusqueda;			
				
				f.setCodigoClienteBuscar((String)clienteHipMap.get("codigoCliente") );
				String apellido1 = clienteHipMap.get("apellido1") == null ? ""
						: (String) clienteHipMap.get("apellido1");
				String apellido2 = clienteHipMap.get("apellido2") == null ? ""
						: (String) clienteHipMap.get("apellido2");
				String nombre1 = clienteHipMap.get("nombre1") == null ? ""
						: (String) clienteHipMap.get("nombre1");
				String nombre2 = clienteHipMap.get("nombre2") == null ? ""
						: (String) clienteHipMap.get("nombre2");
				
				f.setNombreCliente(apellido1+" "+apellido2+" "+nombre1+" "+nombre2);
				this.busquedaHIPClienteSearchAction.setBeanRegistroSeleccionado(null);
			}
		}
		
		if (accion.equals(this.POPUP_ASIGNAR)) 
		{
			this.busquedaHIPClienteSearchAction.verificarRegistro(event);
			if (this.busquedaHIPClienteSearchAction.isSeleccionoRegistro()) 
			{
				Map clienteHipMap = (Map) this.busquedaHIPClienteSearchAction.getBeanRegistroSeleccionado();
				MantenimientoZONCargosForm f = (MantenimientoZONCargosForm) this.mantenimientoZONCargosForm;
				String indicador= (String)clienteHipMap.get("indActi");
				
				if (indicador.equals("SI")) 
				{
					f.setCodigoClienteBuscar((String) clienteHipMap.get("codigoCliente"));
					f.setNombreCliente((String) clienteHipMap.get("apellido1") + " " + (String) clienteHipMap.get("apellido2")
							+ " " + (String) clienteHipMap.get("nombre1") + " " + (String) clienteHipMap.get("nombre2"));
					f.setDocCliente((String) clienteHipMap.get("numeroDocumento"));
				} else 
				{
					f.setCodigoClienteBuscar((String) clienteHipMap.get("codigoCliente"));
					f.setNombreCliente("No se encuentra");
					f.setDocCliente("No se encuentra");
				}
						
				this.busquedaHIPClienteSearchAction.setBeanRegistroSeleccionado(null);
			}			
		}
		
		if (accion.equals(this.POPUP_LICENCIAS)) 
		{
			this.busquedaHIPClienteSearchAction.verificarRegistro(event);
			if (this.busquedaHIPClienteSearchAction.isSeleccionoRegistro()) 
			{
				Map clienteHipMap = (Map) this.busquedaHIPClienteSearchAction.getBeanRegistroSeleccionado();
				MantenimientoZONCargosForm f = (MantenimientoZONCargosForm) this.mantenimientoZONCargosForm;
				String valor = (String) clienteHipMap.get("codigoCliente");
				AjaxService ajax = (AjaxService) getBean("ajaxService");
				Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
				String ua = "";
				
				String[] resultado = ajax.obtenerDatosConsultoraReemplazo(pais.getCodigo(), pais.getCodigoConexionExterna(), valor);
				if(resultado[0].equals("1"))
				{
					f.setNombresCompletosConsultoraReemplazo("No se encuentra");
					f.setNroDocumentoIdentidadReemplazo("No se encuentra");
					this.zonCargosList = null;
					f.setListaUnidadesAdministrativas(null);
					f.setCodigoClienteBuscar(valor);
				}else
				{
					f.setCodigoClienteBuscar(valor);
					for (int i = 0; i < resultado.length; i++) 
					{	
						f.setNombresCompletosConsultoraReemplazo(resultado[i].split("_")[0]);
						f.setNroDocumentoIdentidadReemplazo(resultado[i].split("_")[1]);
						ua = ua + resultado[i].split("_")[3];
						f.setListaUnidadesAdministrativas(ua);
						
						this.zonCargosList = ajax.getDataCargo(pais.getCodigo(), pais.getCodigoConexionExterna(), valor);
					}					
				}
			}			
		}
	}
	
	@Override
	protected void setSalirPopup()
	{
		this.mostrarPopUpCliente = false;
		this.busquedaHIPClienteSearchAction.setBeanRegistroSeleccionado(null);
	}
	
	
	@Override
	protected void setInvocarPopup(String accion){
		this.mostrarProcesoBatch = false;

		if (accion.equals(this.POPUP_CLIENTES)) {
			this.mostrarPopUpCliente = true;
			BusquedaHIPClienteSearchForm searchForm = (BusquedaHIPClienteSearchForm)this.busquedaHIPClienteSearchAction.getFormBusqueda();
			MantenimientoZONDirectorioVentasSearchForm f = (MantenimientoZONDirectorioVentasSearchForm) this.formBusqueda;
			searchForm.setOidIdioma(f.getOidIdioma());
			this.busquedaHIPClienteSearchAction.setFormBusqueda(searchForm);
			try {
				this.busquedaHIPClienteSearchAction.cargarVista();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (accion.equals(this.POPUP_ASIGNAR)) {
			this.mostrarPopUpCliente = true;
			BusquedaHIPClienteSearchForm searchForm = (BusquedaHIPClienteSearchForm)this.busquedaHIPClienteSearchAction.getFormBusqueda();
			MantenimientoZONDirectorioVentasSearchForm f = (MantenimientoZONDirectorioVentasSearchForm) this.formBusqueda;
			searchForm.setOidIdioma(f.getOidIdioma());
			this.busquedaHIPClienteSearchAction.setFormBusqueda(searchForm);
			try {
				this.busquedaHIPClienteSearchAction.cargarVista();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (accion.equals(this.POPUP_LICENCIAS)) {
			this.mostrarPopUpCliente = true;
			BusquedaHIPClienteSearchForm searchForm = (BusquedaHIPClienteSearchForm)this.busquedaHIPClienteSearchAction.getFormBusqueda();
			MantenimientoZONDirectorioVentasSearchForm f = (MantenimientoZONDirectorioVentasSearchForm) this.formBusqueda;
			searchForm.setOidIdioma(f.getOidIdioma());
			this.busquedaHIPClienteSearchAction.setFormBusqueda(searchForm);
			try {
				this.busquedaHIPClienteSearchAction.cargarVista();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void searchClienteOnEnter()
	{
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoZONDirectorioVentasSearchForm f = (MantenimientoZONDirectorioVentasSearchForm) this.formBusqueda;
		String valor = f.getCodigoClienteBuscar();
		
		if(StringUtils.isNotBlank(valor))
		{
			String[] resultado;
			resultado = ajax.getConsultoraByCriteria2(pais.getCodigo(), valor, pais.getCodigoConexionExterna());
			if(resultado == null)
			{
				f.setNombreCliente(null);
				f.setCodigoClienteBuscar(null);
				this.addWarn("Error: ", "El cliente no existe.");
			}
			else
				f.setNombreCliente(resultado[1]);
		}else
		{
			f.setNombreCliente(null);
			f.setCodigoClienteBuscar(null);
			this.addWarn("Error: ", "Debe ingresar un codigo de consultora.");
		}
	}
	
	
	public void viewAjax(ActionEvent actionEvent) 
	{
		try {
			// parametro de los botones que abren el popup
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			String accion = externalContext.getRequestParameterMap().get("parametroAccion");

			if (accion.equalsIgnoreCase("AsignarCargos")) {
				this.flag = true;
				mantenimientoZONCargosAction
						.setBeanRegistroSeleccionado(this.beanRegistroSeleccionado);
				mantenimientoZONCargosAction.viewAjax(actionEvent);
				return;
			}

			if (accion.equalsIgnoreCase("MantenerCargos")) {
				this.flag = true;
				mantenimientoZONCargosAction.viewAjax(actionEvent);
				return;
			}

			if (this.beanRegistroSeleccionado == null) {
				this.flag = false;
				this.setMensajeAlertaDefault(this.getResourceMessage("mantenimientoZONCargosForm.message.error.selected.item"));
				String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
				this.getRequestContext().execute(ventanaConfirmar);
			} else {
				if (accion.equalsIgnoreCase("CambiarCargos")) {
					Map map = (Map) this.beanRegistroSeleccionado;
					String indicadorCargoFuturo = map.get("indicadorCargoFuturo").toString();
					String codigoEstadoCargo = map.get("codigoEstadoCargo").toString();

					if (indicadorCargoFuturo.equalsIgnoreCase("F")) {
						this.flag = false;
						this.setMensajeAlertaDefault("Cliente No valido para Cambio de Cargo");
						String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
						this.getRequestContext().execute(ventanaConfirmar);
						return;
					}

					if (!codigoEstadoCargo.trim().equalsIgnoreCase("A")) {
						this.flag = false;
						this.setMensajeAlertaDefault(this.getResourceMessage("mantenimientoZONCargosForm.message.error.cambio.cargo.registro.inactivo"));
						String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
						this.getRequestContext().execute(ventanaConfirmar);
						return;
					}

					this.flag = true;
					mantenimientoZONCargosAction.setBeanRegistroSeleccionado(this.beanRegistroSeleccionado);
					mantenimientoZONCargosAction.viewAjax(actionEvent);
					return;
				}

				if (accion.equalsIgnoreCase("rotarCargos")) {
					Map map = (Map) this.beanRegistroSeleccionado;
					String codigoEstadoCargo = map.get("codigoEstadoCargo").toString();
					String codigoCargo = map.get("codigoCargo").toString();

					// Solo las Gerentes con estado activo pueden rotar
					if (!((codigoCargo.equalsIgnoreCase("GR") || codigoCargo.equalsIgnoreCase("GZ")) && codigoEstadoCargo
							.equalsIgnoreCase("A"))) {
						this.flag = false;
						this.setMensajeAlertaDefault(this.getResourceMessage("mantenimientoZONCargosForm.cargo.no.titular.inactivo"));
						String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
						this.getRequestContext().execute(ventanaConfirmar);
						return;
					}

					this.flag = true;
					mantenimientoZONCargosAction.setBeanRegistroSeleccionado(this.beanRegistroSeleccionado);
					mantenimientoZONCargosAction.viewAjax(actionEvent);
					return;
				}				

				if (accion.equalsIgnoreCase("retirarPersonal")) {
					// Validamos si esta activa y si es titular
					Map map = (Map) this.beanRegistroSeleccionado;

					String codigoEstadoCargo = map.get("codigoEstadoCargo").toString();

					if (!codigoEstadoCargo.trim().equals("A") && !codigoEstadoCargo.trim().equals("IT")) {
						this.flag = false;
						this.setMensajeAlertaDefault(this.getResourceMessage("mantenimientoZONCargosForm.message.error.retiro.registro.inactivo"));
						String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
						this.getRequestContext().execute(ventanaConfirmar);
						return;
					}

					this.flag = true;
					mantenimientoZONCargosAction.setBeanRegistroSeleccionado(this.beanRegistroSeleccionado);
					mantenimientoZONCargosAction.viewAjax(actionEvent);
					return;
				}
			}
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	
	/**
	 * Inicializa popup Asignar Cargos
	 */
	public void vistaAsignarCargo(ActionEvent event)
	{
		log.debug("Entering my method 'vistaAsignarCargo'");
		this.zonMantAsignarRegionList = null;
		this.zonMantAsignarZonaList = null;	
		this.mostrarBotonSave = false;
				
		try {
			GenericoService genericoService = (GenericoService)getBean("genericoService");
			MantenimientoZONDirectorioService service = (MantenimientoZONDirectorioService)	this.getBean("spusicc.mantenimientoZonDirectorioService");
			MantenimientoOCRPedidoControlFacturacionService serviceFacturacion = (MantenimientoOCRPedidoControlFacturacionService) 
					getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
			
			MantenimientoZONCargosForm f = new MantenimientoZONCargosForm();			
			f.setSelectedItems(new String[0]);
			limpiarCamposCargo(f);		
			f.setIndicadorRegistrar("0");
			f.setIndicadorEditar("0");
			f.setIndicadorEliminar("0");
			f.setIndicadorAdmin(Constants.NUMERO_CERO);
			
			BeanUtils.copyProperties(f, new MantenimientoZONCargosForm());
			Map map = new HashMap();
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			//Obteniendo el parametro recibido
			Map registroSeleccionado = (Map) this.beanRegistroSeleccionado;
			
			String oidVentDetal = null;
			String operacion = null;
			String cargo =null;
			String cliente =null;
			String fechaRegistro =null;
			String campanaProceso = null;
			
			String estadoCargo = null;
			String tipoCargo = null;
			String indicadorEstado = null;
			
			
			if(registroSeleccionado != null)
			{	
				oidVentDetal = registroSeleccionado.get("oidDirecVentDetal") == null? null:registroSeleccionado.get("oidDirecVentDetal").toString();
				operacion = registroSeleccionado.get("codigoOperacion") == null?null:registroSeleccionado.get("codigoOperacion").toString();
				cargo = registroSeleccionado.get("codigoCargo") == null?null: registroSeleccionado.get("codigoCargo").toString();
				cliente = registroSeleccionado.get("codigoCliente") == null?null:  registroSeleccionado.get("codigoCliente").toString();
				fechaRegistro = registroSeleccionado.get("fechaIngreso") == null?null: registroSeleccionado.get("fechaIngreso").toString();
				campanaProceso = registroSeleccionado.get("campana") == null?null: registroSeleccionado.get("campana").toString();
				f.setCorrelativoCabecera(registroSeleccionado.get("correlativoCabecera") ==null?null: registroSeleccionado.get("correlativoCabecera").toString());
				
				estadoCargo = registroSeleccionado.get("codigoEstadoCargo") ==null?null: registroSeleccionado.get("codigoEstadoCargo").toString();		//(A=Activo, I=Inactivo)
				tipoCargo = registroSeleccionado.get("valorTitular") == null? null: registroSeleccionado.get("valorTitular").toString();			//(1=Titular, 0=No Titular)
				indicadorEstado = registroSeleccionado.get("estadoRegistro") == null? null:registroSeleccionado.get("estadoRegistro").toString();	//(A=Aprobado, G=Grabado, R=Rechazado)
							
				map.put("oidVentDetal", oidVentDetal);
				map.put("codigoOperacion", operacion);
				map.put("codigoCargo", cargo);
				map.put("codigoCliente", cliente);
				map.put("fechaRegistro", fechaRegistro);
				map.put("codigoPais", pais.getCodigo());
				map.put("campanaProceso", campanaProceso);
				
				map.put("estadoCargo", estadoCargo);
				map.put("tipoCargo", tipoCargo);
				map.put("indicadorEstado", indicadorEstado);
				
				List lst = service.getMaeCliente(map);
			
				if(lst.size()==1){
					Map result = (Map)lst.get(0);
					f.setCodigoClienteBuscar(cliente);
					f.setDocCliente(MapUtils.getString(result, "numDocumentoIdentidad"));
					f.setNombreCliente(MapUtils.getString(result, "nombreCliente"));
				}
			
			}
			
			///////////////////////////////////////////////////////////
			Map criteria = new HashMap();
			criteria.put("estado", Constants.ESTADO_ACTIVO);
			criteria.put("indCargoNoFuturo", Constants.ESTADO_ACTIVO);
			
			List resultado = service.getTipoCargo(criteria);
			this.zonMantAsignarTipoCargoList = resultado;
						
			f.setOidIdioma(this.zonMantOidIdioma);
			f.setCodigoPais(pais.getCodigo());
			
			//Capturar campaña Proceso
			Map criteriaPeriodo = new HashMap();
			criteriaPeriodo.put("codigoPais", pais.getCodigo());
			criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO); // Indica Campanha Activa
			criteriaPeriodo.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente
			
			if (StringUtils.equals(pais.getCodigoConexionExterna(),Constants.CONEXION_EXTERNA_FOX)) {
				Map mapControl = service.getControlFacturacionByCriteriaFOX(criteriaPeriodo);
				f.setCampanyaProceso(MapUtils.getString(mapControl, "codigoPeriodo"));
				f.setFechaIngreso(MapUtils.getString(mapControl, "fechaProceso"));
				f.setFechaIngresoDate(DateUtil.convertStringToDate(f.getFechaIngreso()));
			}else{
				PedidoControlFacturacion controlFacturacion = serviceFacturacion.getControlFacturacionById(criteriaPeriodo);
				f.setCampanyaProceso(controlFacturacion.getCodigoPeriodo());
				f.setFechaIngreso(controlFacturacion.getFechaProceso());
				f.setFechaIngresoDate(DateUtil.convertStringToDate(f.getFechaIngreso()));
			}
		
			f.setFechaAsignacion(DateUtil.convertDateToString(new Date()));
			
			//Obtener CampañaFacturacion y CampañaVenta a partir de la fechaIngreso obtenida.
			criteriaPeriodo.put("fecha", f.getFechaIngreso());
			criteriaPeriodo.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
			criteriaPeriodo.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
			criteriaPeriodo.put("codigoConexionExterna", pais.getCodigoConexionExterna());
			String campanyaRelacionada = genericoService.getPeriodoByFecha(criteriaPeriodo);
			
			f.setCampanyaFacturacion(campanyaRelacionada);
			
			criteriaPeriodo.put("codigoPeriodo", campanyaRelacionada);
			criteriaPeriodo.put("numeroPeriodo", 1);
			String campanyaRelacionadaSgte  = genericoService.getPeriodoNSiguiente(criteriaPeriodo);
			
			f.setCampanyaVenta(campanyaRelacionadaSgte);
			
			// Longitud del campo consultora
			ClienteUAGenerarService clienteService2 = (ClienteUAGenerarService) getBean("sisicc.clienteUAGenerarService");
			this.longitudCampoClientes = clienteService2.getTamanhoNumeroCliente(pais.getCodigo()).toString();
		
			// parametros para iniciar pantalla
			this.flagFuturas = false;
			this.flagOcultarMultiple = false;
			this.flagOcultarZonas = false;
			f.setRolDesc("No Existe");
			f.setPerfilDesc("No Existe");
			
			this.mantenimientoZONCargosForm = f;
			
//			this.redireccionarPagina("mantenimientoZONAsignarCargoForm");
			String ventanaPopup = "mantenimientoZONAsignarCargoForm.xhtml";
			RequestContext context = RequestContext.getCurrentInstance(); 
			context.addCallbackParam("ventanaPopup", ventanaPopup);
			this.mostrarBotonSalir = false;
			
		} catch (Exception e) {
			this.listaBusqueda = null;
			this.flagFuturas = false;
			this.flagOcultarMultiple = false;
			this.flagOcultarZonas = false;
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	
	/**
	 * Guardar de popup Asignar Cargo
	 * @param event
	 */
	public void guardarAsignarCargo(ActionEvent event) 
	{
		log.debug("Entering my method 'guardarAsignarCargo'");
		try {
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			MantenimientoZONCargosForm f = (MantenimientoZONCargosForm) this.mantenimientoZONCargosForm;
			MantenimientoZONDirectorioService service = (MantenimientoZONDirectorioService) this
					.getBean("spusicc.mantenimientoZonDirectorioService");
			AjaxService ajax = (AjaxService) getBean("ajaxService");

			f.setFechaIngreso(DateUtil.convertDateToString(f
					.getFechaIngresoDate()));
			f.setFechaIngresoHasta(DateUtil.convertDateToString(f
					.getFechaIngresoHastaDate()));

			if (!this.flagOcultarMultiple) {
				f.setCodigoRegion(new String[] { f.getCodigoRegionUnico() });
				if (!this.flagOcultarZonas)
					f.setCodigoZona(new String[] { f.getCodigoZonaUnico() });
			}

			String oidClieGerente = "";
			String codClieGerente = "";

			Map criteria = new HashMap();
			criteria.put("codigoPais", f.getCodigoPais());
			criteria.put("codigoConsultora", f.getCodigoClienteBuscar());
			criteria.put("codigoConexionExterna",
					pais.getCodigoConexionExterna());
			criteria.put("codigoCargo", f.getCodigoCargoAsignarCambiar());

			// Validar si el cliente esta activo.(Para ORA y FOX)
			String acti = "";
			acti = ajax
					.obtenerDatosConsultoraAsignarCargo(f.getCodigoPais(),
							pais.getCodigoConexionExterna(),
							f.getCodigoClienteBuscar());
			// 1 indica que la cosnulta retorno null
			if (acti.equals("1")) {
				this.addError("Error: ", this.getResourceMessage("mantenimientoZONCargosForm.message.error.cliente.isInactiva"));
				this.grabarAsignar = "N";
				return;
			}

			Map result = new HashMap();
			result = service.getTipoCargoFuturas(criteria);
			if (result != null) {
				String codRolTitu = (String) result.get("codigoRol");
				// Validacion de Cargo Titular
				if ((StringUtils.equals(codRolTitu,
						Constants.ZON_MANT_GERENTE_REGION) || StringUtils
						.equals(codRolTitu, Constants.ZON_MANT_GERENTE_ZONA))
						&& !StringUtils.equals(pais.getCodigoConexionExterna(),
								Constants.CONEXION_EXTERNA_FOX)) {
					// Validacion de subtipo
					if (StringUtils.equals(codRolTitu,
							Constants.ZON_MANT_GERENTE_REGION)) {
						criteria.put("codigoSubTipoCliente",
								Constants.CODIGO_SUBTIPO_CLIENTE_GERENTE_REGION);
						criteria.put("codigoConexionExterna",
								pais.getCodigoConexionExterna());

						oidClieGerente = service
								.getObtenerGerentexTipo(criteria);

						if (oidClieGerente == null) {
							this.addError("Error: ", this.getResourceMessage("mantenimientoZONCargosForm.message.error.cliente.no.corresponde.gr"));
							this.grabarAsignar = "N";
							return;
						}

					} else if (StringUtils.equals(codRolTitu,
							Constants.ZON_MANT_GERENTE_ZONA)) {
						criteria.put("codigoSubTipoCliente",
								Constants.CODIGO_SUBTIPO_CLIENTE_GERENTE_ZONA);
						criteria.put("codigoConexionExterna",
								pais.getCodigoConexionExterna());

						oidClieGerente = service
								.getObtenerGerentexTipo(criteria);

						if (oidClieGerente == null) {
							this.addError(
									"Error: ",
									this.getResourceMessage("mantenimientoZONCargosForm.message.error.cliente.no.corresponde.gz"));
							this.grabarAsignar = "N";
							return;
						}
					}

					/* Validacion para FOX */
				} else if ((StringUtils.equals(codRolTitu,
						Constants.ZON_MANT_GERENTE_REGION) || StringUtils
						.equals(codRolTitu, Constants.ZON_MANT_GERENTE_ZONA))
						&& StringUtils.equals(pais.getCodigoConexionExterna(),
								Constants.CONEXION_EXTERNA_FOX)) {
					// Validacion de subtipo
					if (StringUtils.equals(codRolTitu,
							Constants.ZON_MANT_GERENTE_REGION)) {
						criteria.put("codigoSubTipoCliente",
								Constants.CODIGO_SUBTIPO_CLIENTE_GERENTE_REGION);
						criteria.put("codigoConexionExterna",
								pais.getCodigoConexionExterna());

						codClieGerente = service
								.getObtenerGerentexTipo(criteria);

						if (codClieGerente == null) {
							this.addError(
									"Error: ",
									this.getResourceMessage("mantenimientoZONCargosForm.message.error.cliente.no.corresponde.gr"));
							this.grabarAsignar = "N";
							return;
						}

					} else if (StringUtils.equals(codRolTitu,
							Constants.ZON_MANT_GERENTE_ZONA)) {
						criteria.put("codigoSubTipoCliente",
								Constants.CODIGO_SUBTIPO_CLIENTE_GERENTE_ZONA);
						criteria.put("codigoConexionExterna",
								pais.getCodigoConexionExterna());

						codClieGerente = service
								.getObtenerGerentexTipo(criteria);

						if (codClieGerente == null) {
							this.addError(
									"Error: ",
									this.getResourceMessage("mantenimientoZONCargosForm.message.error.cliente.no.corresponde.gz"));
							this.grabarAsignar = "N";
							return;
						}
					}
				}

			}

			// Validar que en el directorio no exista ninguna con estado ACTIVA
			// o INACTIVA Temporal para la misma UA
			Map params = new HashMap();
			params.put("codigoCargo", f.getCodigoCargoAsignarCambiar());

			List tiposCargo = service.getTipoCargo(params);

			if (tiposCargo != null && tiposCargo.size() > 0) {
				Map tipoCargo = (Map) tiposCargo.get(0);
				String tipoUA = MapUtils.getString(tipoCargo,
						"tipoUnidadAdministrativa", "");
				String cantidadUA = MapUtils.getString(tipoCargo,
						"cantidadUnidadAdministrativa", "");

				Map criteriaVerificarInactiva = new HashMap();
				criteriaVerificarInactiva.put("codigoPais", f.getCodigoPais());
				criteriaVerificarInactiva.put("codigoClienteBuscar",
						f.getCodigoClienteBuscar());
				criteriaVerificarInactiva.put("codigoConexionExterna",
						pais.getCodigoConexionExterna());
				criteriaVerificarInactiva.put("codigoCargo",
						f.getCodigoCargoAsignarCambiar());
				criteriaVerificarInactiva.put("codigoEstadoRegistro",
						Constants.ESTADO_INACTIVO_TEMPORAL);

				if (StringUtils.equals(tipoUA, Constants.ZON_TIPO_UA_ZONA)
						&& StringUtils.equals(cantidadUA,
								Constants.ZON_CANTIDAD_UA_VARIOS)) {
					params.put("codigoRegionArray", f.getCodigoRegion());
					params.put("codigoZonaArray", f.getCodigoZona());

					criteriaVerificarInactiva.put("codigoRegion",
							f.getCodigoRegion()[0]);
					criteriaVerificarInactiva.put("codigoZona",
							f.getCodigoZona()[0]);
					List listDir = service
							.getDirectorioVentasList(criteriaVerificarInactiva);
					if (listDir != null && listDir.size() > 0) {
						this.addError(
								"Error: ",
								this.getResourceMessage("mantenimientoZONCargosForm.message.error.cliente.estadoInactivaTemporal"));
						this.grabarAsignar = "N";
						return;
					}
				}

				if (StringUtils.equals(tipoUA, Constants.ZON_TIPO_UA_ZONA)
						&& StringUtils.equals(cantidadUA,
								Constants.ZON_CANTIDAD_UA_UNICO)) {
					params.put("codigoRegion", f.getCodigoRegion()[0]);
					params.put("codigoZona", f.getCodigoZona()[0]);

					criteriaVerificarInactiva.put("codigoRegion",
							f.getCodigoRegion()[0]);
					criteriaVerificarInactiva.put("codigoZona",
							f.getCodigoZona()[0]);
					List listDir = service
							.getDirectorioVentasList(criteriaVerificarInactiva);
					if (listDir != null && listDir.size() > 0) {
						this.addError(
								"Error: ",
								this.getResourceMessage("mantenimientoZONCargosForm.message.error.cliente.estadoInactivaTemporal"));
						this.grabarAsignar = "N";
						return;
					}
				}

				if (StringUtils.equals(tipoUA, Constants.ZON_TIPO_UA_REGION)
						&& StringUtils.equals(cantidadUA,
								Constants.ZON_CANTIDAD_UA_VARIOS)) {
					params.put("codigoRegionArray", f.getCodigoRegion());
					for (int i = 0; i < f.getCodigoRegion().length; i++) {
						criteriaVerificarInactiva.put("codigoRegion",
								f.getCodigoRegion()[i]);
						List listDir = service
								.getDirectorioVentasList(criteriaVerificarInactiva);
						if (listDir != null && listDir.size() > 0) {
							this.addError(
									"Error: ",
									this.getResourceMessage("mantenimientoZONCargosForm.message.error.cliente.estadoInactivaTemporal"));
							this.grabarAsignar = "N";
							return;
						}
					}
				}

				if (StringUtils.equals(tipoUA, Constants.ZON_TIPO_UA_REGION)
						&& StringUtils.equals(cantidadUA,
								Constants.ZON_CANTIDAD_UA_UNICO)) {
					params.put("codigoRegion", f.getCodigoRegion()[0]);

					criteriaVerificarInactiva.put("codigoRegion",
							f.getCodigoRegion()[0]);
					List listDir = service
							.getDirectorioVentasList(criteriaVerificarInactiva);
					if (listDir != null && listDir.size() > 0) {
						this.addError(
								"Error: ",
								this.getResourceMessage("mantenimientoZONCargosForm.message.error.cliente.estadoInactivaTemporal"));
						this.grabarAsignar = "N";
						return;
					}
				}
			}

			params.put(
					"codigoSubGerencia",
					StringUtils.equals(pais.getCodigoConexionExterna(),
							Constants.CONEXION_EXTERNA_FOX) ? Constants.ZON_CODIGO_SUBGERENCIA_DEFAULT
							: f.getSubGerencia());
			params.put("estatusCargo", Constants.ZON_MANT_ESTADO_CARGO_ACTIVA);
			params.put("codigoPais", pais.getCodigo());
			params.put("codigoConexionExterna", pais.getCodigoConexionExterna());

			List listaActivas = service.getDirectorioVentasDetalle(params);

			if (listaActivas != null && listaActivas.size() > 0) {
				this.addError(
						"Error: ",
						this.getResourceMessage("mantenimientoZONCargosForm.message.error.cargo.ya.existe"));
				this.grabarAsignar = "N";
				return;
			}

			criteria.put("oidCliente", oidClieGerente);
			criteria.put("codigoPais", pais.getCodigo());
			criteria.put("codigoTipoCargo", f.getCodigoCargoAsignarCambiar());
			criteria.put(
					"fechaIngreso",
					DateUtil.convertDateToString("dd/MM/yyyy",
							DateUtil.convertStringToDate(f.getFechaIngreso())));

			if (StringUtils.isNotBlank(f.getFechaIngresoHasta()))
				criteria.put("fechaIngresoHasta", DateUtil.convertDateToString(
						"dd/MM/yyyy",
						DateUtil.convertStringToDate(f.getFechaIngresoHasta())));

			if (StringUtils.isNotBlank(f.getFechaVenta()))
				criteria.put("fechaVenta", DateUtil.convertDateToString(
						"dd/MM/yyyy",
						DateUtil.convertStringToDate(f.getFechaVenta())));
			if (StringUtils.isNotBlank(f.getFechaFacturacion()))
				criteria.put("fechaFacturacion", DateUtil.convertDateToString(
						"dd/MM/yyyy",
						DateUtil.convertStringToDate(f.getFechaFacturacion())));

			// criteria.put("campanyaProceso", f.getCampanyaProceso());
			String fechaCam = ajax.getPeriodoByFecha(pais.getCodigo(),
					pais.getCodigoConexionExterna(), f.getFechaIngreso());
			if (fechaCam != null) {
				if (!fechaCam.equals(f.getCampanyaProceso())) {
					criteria.put("campanyaProceso", fechaCam);
				} else
					criteria.put("campanyaProceso", f.getCampanyaProceso());
			} else {
				// criteria.put("campanyaProceso", f.getCampanyaProceso());
				this.addError(
						"Error: ",
						this.getResourceMessage("mantenimientoZONCargosForm.message.error.fecha.sincampania"));
				this.grabarAsignar = "N";
				return;
			}
			criteria.put("usuarioLogin", usuario.getLogin());
			criteria.put("codigoPais", pais.getCodigo());
			criteria.put("nombresCompletosConsultora",
					f.getNombresCompletosConsultora());
			criteria.put("tipoOperacion",
					Constants.ZON_MANT_CODIGO_OPERACION_IN);
			criteria.put("codigoEstadoCargo",
					Constants.ZON_MANT_ESTADO_CARGO_ACTIVA);
			criteria.put(
					"codigoSubGerencia",
					StringUtils.equals(pais.getCodigoConexionExterna(),
							Constants.CONEXION_EXTERNA_FOX) ? Constants.ZON_CODIGO_SUBGERENCIA_DEFAULT
							: f.getSubGerencia());
			criteria.put("codigoRegion", f.getCodigoRegion());
			criteria.put("codigoZona", f.getCodigoZona());
			criteria.put("estadoRegistro", Constants.ACTIVO);
			criteria.put("envioCorreo", Constants.SI);
			criteria.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
			criteria.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
			criteria.put("codigoEnvio", Constants.MAV_CODIGO_INDICADOR_ENVIO_P);

			limpiarZonas(f.getCodigoCargoAsignarCambiar(), criteria);

			// Inserta
			service.insertDirectorioVenta(criteria);

			// Registramos en el historico SOLAMENTE PARA PAISES SSICC/ORA
			if (!StringUtils.equals(pais.getCodigoConexionExterna(),
					Constants.CONEXION_EXTERNA_FOX)) {
				HistoricoDirectorioVenta historico = new HistoricoDirectorioVenta();
				historico.setCodigoPais(f.getCodigoPais());
				historico.setCodigoCliente(f.getCodigoClienteBuscar());
				service.insertHistoricoDirectorioVenta(historico, usuario);
			}
		
			this.zonMantAsignarTipoCargoCerrar = Constants.NUMERO_UNO;
			
			this.listaBusqueda = this.setFindAttributes();
			this.datatableBusqueda = new DataTableModel(this.listaBusqueda);
			this.grabarAsignar = "S";
			
//			this.setMensajeAlertaDefaultAction(this.getResourceMessage("mantenimientoZONCargosForm.datos.insert"));
//			RequestContext.getCurrentInstance().update("principalFormAlertAction:textoMensajeAlerta");
//			String ventana = "PF('principalFormAlertActionAsignar_alertDialogAction').show()";
//			this.getRequestContext().execute(ventana);
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
			this.grabarAsignar = "N";
		}
	}
	
	@Override
	public String setValidarConfirmar(String accion) 
	{
		String mensaje = null;
		MantenimientoZONCargosForm f = (MantenimientoZONCargosForm) this.mantenimientoZONCargosForm;	
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry(); 
		
		if(accion.equals("AsignarCargos"))
		{			
			f.setFechaIngreso(DateUtil.convertDateToString(f.getFechaIngresoDate()));
			f.setFechaIngresoHasta(DateUtil.convertDateToString(f.getFechaIngresoHastaDate()));
			
			if(StringUtils.isBlank(f.getCodigoCargoAsignarCambiar()))
			{
				mensaje = "'Cargo' es un campo requerido.";
				System.out.println("mensaje: "+mensaje);
				return mensaje;
			}
			
			if(this.flagOcultarMultiple) {	
				if (f.getCodigoRegion() == null || f.getCodigoRegion().length == 0) {
					mensaje = "'Región' es un campo requerido.";
					System.out.println("mensaje: " + mensaje);
					return mensaje;
					}
				
				if(!this.flagOcultarZonas){
					for (String zona : f.getCodigoZona()) {
						if (StringUtils.isBlank(zona)) {
							mensaje = "'Zona' es un campo requerido.";
							System.out.println("mensaje: "+mensaje);
							return mensaje;							
						}
					}					
				}				
			} else {
				if (StringUtils.isBlank(f.getCodigoRegionUnico())) {
					mensaje = "'Región' es un campo requerido.";
					System.out.println("mensaje: " + mensaje);
					return mensaje;
				}
				
				if(!this.flagOcultarZonas){
					if(StringUtils.isBlank(f.getCodigoZonaUnico())){
						mensaje = "'Zona' es un campo requerido.";
						System.out.println("mensaje: "+mensaje);
						return mensaje;						
					}					
				}			
			}
						
			if(StringUtils.isBlank(f.getCodigoClienteBuscar()))
			{
				f.setNombreCliente(null);
				f.setDocCliente(null);
				mensaje = "'Consultora' es un campo requerido.";
				System.out.println("mensaje: "+mensaje);
				return mensaje;
			}
			
			if(StringUtils.isBlank(f.getFechaIngreso()))
			{
				mensaje = "'Fecha Ingreso' es un campo requerido.";
				return mensaje;						
			}
			
			String resultado = ajax.obtenerDatosConsultoraAsignarCargo(pais.getCodigo(), pais.getCodigoConexionExterna(), f.getCodigoClienteBuscar());
			
			if(resultado.compareTo("1") == 0)
	    	{
				mensaje = "Código de Consultora no existe o su estado es INACTIVO.";
				System.out.println("mensaje: "+mensaje);
	        	return mensaje;            	
	        }			
			
			if (f.getFechaIngresoHastaDate() != null && f.getFechaIngresoDate() != null) 
			{			
				if (f.getFechaIngresoHastaDate().before(f.getFechaIngresoDate())) {
					mensaje = "La Fecha de Ingreso Hasta debe ser mayor o igual a la Fecha Ingreso Desde.";
					System.out.println("mensaje: "+mensaje);
					return mensaje;
				}
			}
			
			if(StringUtils.isNotBlank(f.getCampanyaFacturacion()) && 
					f.getCampanyaFacturacion().equalsIgnoreCase("Fecha sin campaña."))
			{
				mensaje = "Fecha sin campaña.";
				System.out.println("mensaje: "+mensaje);
				return mensaje;
	    	}
			
			//Inicio : Valida Fecha Ingreso (Desde - Hasta)			
			if(this.flagOcultarZonas)
			{ 
				f.setCodigoZona(new String[]{""});
				f.setCodigoZonaUnico("");
			}
			
			mensaje = getValidarFechaIngreso(f.getCodigoCargoAsignarCambiar(), f.getFechaIngreso(), 
					f.getCodigoRegionUnico(), f.getCodigoZonaUnico());
			if(StringUtils.isNotBlank(mensaje))
				return mensaje;				
			//Fin : Valida Fecha Ingreso (Desde - Hasta)
			
			//Inicio : Valida Cruce Fecha Gerente (Desde - Hasta)						
			 if(StringUtils.isNotBlank(f.getFechaIngreso()) && StringUtils.isNotBlank(f.getFechaIngresoHasta()))
			 {
				 if(this.flagOcultarZonas)
				{
					f.setCodigoZona(new String[]{""});
					f.setCodigoZonaUnico("");
				}
				 
				mensaje = getValidarCruceFechaGeren(f.getFechaIngreso(),  f.getFechaIngresoHasta(), f.getCodigoRegionUnico(), 
						 f.getCodigoZonaUnico(), f.getCodigoCargoAsignarCambiar(), "A");				
				 
				 if(StringUtils.isNotBlank(mensaje))
					 return mensaje;
			  }
			 // Fin : Valida Cruce Fecha Gerente (Desde - Hasta)		
		}	
		
		if(accion.equals("mantenerLicencias"))
		{
			f.setFechaSalida(DateUtil.convertDateToString(f.getFechaSalidaDate()));
			f.setFechaRegreso(DateUtil.convertDateToString(f.getFechaRegresoDate()));
			
			if(StringUtils.isBlank(f.getFechaRegreso()))
			{
				mensaje = "'Fecha Regreso' es un campo requerido.";
				System.out.println("mensaje: "+mensaje);
				return mensaje;				
			}
			
			if(StringUtils.isBlank(f.getCodigoTipoLicencia()))
			{
				mensaje = "'Tipo de Licencia' es un campo requerido.";
				System.out.println("mensaje: "+mensaje);
				return mensaje;
			}
			
			if(StringUtils.isBlank(f.getFechaSalida()))
			{
				mensaje = "'Fecha Salida' es un campo requerido.";
				System.out.println("mensaje: "+mensaje);
				return mensaje;
			}
			
			if(DateUtil.compareDates(f.getFechaSalida(), f.getFechaRegreso(), "dd/MM/yyyy") > 0){
				mensaje = "La Fecha Regreso debe ser mayor a la Fecha Salida.";
				System.out.println("mensaje: "+mensaje);
				return mensaje;
			}
			
			int resultadoFechas1 = DateUtil.compareDates(f.getFechaSalida(), this.fecFacturacion,"dd/MM/yyyy");
        	if(resultadoFechas1 == 1){
        		mensaje = "La Fecha de Salida no puede ser mayor a "+ this.fecFacturacion;
				System.out.println("mensaje: "+mensaje);
				return mensaje;
        	}
        	
        	int resultFechSaIng = DateUtil.compareDates(f.getFechaIngreso(), f.getFechaSalida(),"dd/MM/yyyy");
        	if(resultFechSaIng == 1){
        		mensaje = "La Fecha de Salida no puede ser menor a la de Ingreso.";
				System.out.println("mensaje: "+mensaje);
				return mensaje;
        	}	
        	
        	if (StringUtils.isNotBlank(f.getCampanyaFacturacion()) && f.getCampanyaFacturacion().equalsIgnoreCase("Fecha sin campaña.")) 
			{
				mensaje = "Fecha sin campaña.";
				System.out.println("mensaje: " + mensaje);
				return mensaje;
			}	
        	
        	if (StringUtils.isNotBlank(f.getCampanyaFacturacion2()) && f.getCampanyaFacturacion2().equalsIgnoreCase("Fecha sin campaña.")) 
			{
				mensaje = "Fecha sin campaña.";
				System.out.println("mensaje: " + mensaje);
				return mensaje;
			}
        	
        	if(StringUtils.isNotBlank(f.getCodigoClienteBuscar()))
        	{
        		String[] resultado = ajax.obtenerDatosConsultoraReemplazo(pais.getCodigo(), pais.getCodigoConexionExterna(), f.getCodigoClienteBuscar());
				if(resultado[0].equals("1"))
				{
					f.setNombresCompletosConsultoraReemplazo("No se encuentra");
					f.setNroDocumentoIdentidadReemplazo("No se encuentra");
					this.zonCargosList = null;
					f.setListaUnidadesAdministrativas(null);
					mensaje = "Código de Consultora no existe o su estado es INACTIVO";
					return mensaje;
				}
        	}
		}
		
		return mensaje;	
	}
	
	/**
	 * Valida Fecha Ingreso
	 * @return
	 */
	private String getValidarFechaIngreso(String codigoCargo, String fecha, String region, String zona)
	{
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry(); 
		String mensaje = null;
		String resultado1 = ajax.getValidarFechaIngreso(codigoCargo, pais.getCodigo(), fecha, region, zona, pais.getCodigoConexionExterna());
		
		if (!resultado1.equals("-1")) {
			if (resultado1.equals("0")) {
				mensaje = "Fecha fuera de rango permitido";
				return mensaje;
			} else if (resultado1.equals("2")) {
				mensaje = "Fecha de campaña cerrada";
				return mensaje;
			} else if (resultado1.equals("3")) {
				mensaje = "No hay campaña vigente";
				return mensaje;
			}
		}
		
		return mensaje;
	}
	
	
	/**
	 * Valida Cruce Fecha Gerente
	 * @return
	 */
	private String getValidarCruceFechaGeren(String fechaIngresoInicio, String fechaIngresoFin, String codigoRegion, 
			String codigoZona, String tipoCargo, String tipoOperacion)
	{
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry(); 
		String mensaje = null;
		
		String resultado2 = ajax.getValidarCruceFechaGeren(pais.getCodigo(), fechaIngresoInicio, fechaIngresoFin, codigoRegion, 
							codigoZona, tipoCargo, tipoOperacion, pais.getCodigoConexionExterna());
		
		if (!resultado2.equals("-1")) 
		 {
			mensaje = "Hay cruces de fechas con la consultora "+resultado2;
			return mensaje;
		  }
		
		return mensaje;
	}
	
	
	/**
	* Metodo que carga las regiones en el popup Asignar Cargos
	*/
	public void loadRegionxTitularAsignar(ValueChangeEvent event) 
	{
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		String valorResultado = null;
		String valorResultado1 = null;
		MantenimientoZONCargosForm f = (MantenimientoZONCargosForm) this.mantenimientoZONCargosForm;		
		String valor = (String)event.getNewValue();
		
		if (valor != null) 
		{
			valorResultado = ajax.getRolPerfil(valor);
			String rol = valorResultado.split("\\|")[0];
			String perfil = valorResultado.split("\\|")[1];
			f.setRolDesc(rol);
			f.setPerfilDesc(perfil);

			valorResultado1 = ajax.getVerificarCargoTitular(valor);
			String indicadorTitular = valorResultado1.split("\\|")[0];
			String tipoUA = valorResultado1.split("\\|")[1];

			// Limpia los valores de las fechas calculadas ya que se
			// cambiara de cargo y se recargara los combos de regio y zon
			f.setCampanyaFacturacion(null);
			f.setCampanyaVenta(null);
			f.setIndicadorTitularNoTitular(indicadorTitular);

			if (!indicadorTitular.equals("1")) {
				this.flagOcultarMultiple = false;
				loadRegionAsignar(indicadorTitular, valor);
			} else {
				// Envia '1' indica que No titular y cantidad de unidades
				// administrativas a cargo es mayor 1
				this.flagOcultarMultiple = true;
				loadRegionAsignar(indicadorTitular, valor);
			}

			if (tipoUA.equalsIgnoreCase("R")) {
				// ocultar lo puse yo
				this.flagOcultarZonas = true;
			} else {
				// mostrar lo puse yo
				this.flagOcultarZonas = false;
			}
		} else {
			this.zonMantAsignarRegionList = null;
			this.zonMantAsignarZonaList = null;
			this.flagOcultarMultiple = false;
		}
	}
	
	
	private void loadRegionAsignar(String indicadorTitular, String cargo)
	{
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		this.zonMantAsignarRegionList = ajax.getRegionesAllDirectorioMantenimientoZON(pais.getCodigo(), pais.getCodigoConexionExterna(), indicadorTitular, cargo);	
	}
	
	
	/**
	 * metodo que carga las zonas en el popup Asignar Cambios
	 * @param event
	 */
	public void loadZonasAsignar(ValueChangeEvent event)
	{
		String[] valores = null;  
		
		try{
			String valor = (String)event.getNewValue();
			valores = new String[]{valor};
		}catch(Exception e)
		{
			String[] valor = (String[])event.getNewValue();
			valores = new String[valor.length];
			for (int i = 0; i < valor.length; i++) {
				valores[i] = valor[i];				
			}
		}
		finally{
			AjaxService ajax = (AjaxService)getBean("ajaxService");
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			MantenimientoZONCargosForm f = (MantenimientoZONCargosForm) this.mantenimientoZONCargosForm;
			
			String valorResultado1 = ajax.getVerificarCargoTitular(f.getCodigoCargoAsignarCambiar());
			String indicadorTitular = valorResultado1.split("\\|")[0];
			
			if(valores != null && valores.length > 0)
			{
				this.zonMantAsignarZonaList = ajax.getZonasAllDirectorioAsignarCargo(pais.getCodigo(), pais.getCodigoConexionExterna(), 
						indicadorTitular, valores, "0", f.getCodigoCargoAsignarCambiar());					
			}
			else
				this.zonMantAsignarZonaList = null;			
		}
	}
	
	
	private void limpiarZonas(String codigoCargo, Map criteria)
	{
		//Limpiamos las zonas que vienen con NO_DATA
		//verificamos que Tipo de UA sea R y limpiamos
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		
		String valor = ajax.getVerificarCargoTitular(codigoCargo);
		String []valores = StringUtils.split(valor, "|");
		if(StringUtils.equals(valores[1], "R"))
		{
			//Se verifica posteriormente este valor para setear el tipo de UA para las plantillas de correo
			criteria.put("codigoZona", null);
		}
		//			
	}
	
	
	public void seteaCampanias(ActionEvent event) 
	{
		try {		
			MantenimientoZONCargosForm f = (MantenimientoZONCargosForm) this.mantenimientoZONCargosForm;
	
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			String accion = externalContext.getRequestParameterMap().get("parametroAccion");
					
			if (accion.equalsIgnoreCase("AsignarCargos")) 
			{
				f.setFechaIngreso(DateUtil.convertDateToString(f.getFechaIngresoDate()));
	
				if (StringUtils.isNotBlank(f.getFechaIngreso())) 
					obtenerDatosCampanias(f.getFechaIngreso());								
				else
					this.addWarn("", "Seleccione fecha de ingreso.");
			}
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	
	private void obtenerDatosCampanias(String fecha)
	{
		MantenimientoZONCargosForm f = (MantenimientoZONCargosForm) this.mantenimientoZONCargosForm;
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		String codigoPeriodo = ajax.getPeriodoByFecha(pais.getCodigo(), pais.getCodigoConexionExterna(), fecha);
		String periodoCampania = ajax.getPeriodoNSiguiente(pais.getCodigo(), codigoPeriodo, "1");
	
		if(StringUtils.isNotBlank(codigoPeriodo))
			f.setCampanyaFacturacion(codigoPeriodo);
		else
			f.setCampanyaFacturacion("Fecha sin campaña.");
		
		if(StringUtils.isNotBlank(periodoCampania))
			f.setCampanyaVenta(periodoCampania);
		else
			f.setCampanyaVenta("Fecha sin campaña.");		
	}
	
	public void metodoKeyPressSeteaFocoCodigoConsultora()
	{
		MantenimientoZONCargosForm f = (MantenimientoZONCargosForm) this.mantenimientoZONCargosForm;
		if(StringUtils.isNotBlank(f.getCodigoClienteBuscar()))
		{
			f.setCodigoClienteBuscar(StringUtils.leftPad(f.getCodigoClienteBuscar(), Integer.parseInt(this.longitudCampoClientes), "0"));
			seteaFocoCodigoConsultora(f.getCodigoClienteBuscar());			
		}		
	}
	
	public void metodoKeyUpSeteaFocoCodigoConsultora()
	{
		MantenimientoZONCargosForm f = (MantenimientoZONCargosForm) this.mantenimientoZONCargosForm;
		if(StringUtils.isNotBlank(f.getCodigoClienteBuscar()))
		{
			if(f.getCodigoClienteBuscar().length() == Integer.parseInt(this.longitudCampoClientes))
				seteaFocoCodigoConsultora(f.getCodigoClienteBuscar());
			else
			{
				f.setNombreCliente("");
				f.setDocCliente("");
			}
		}
	}
	
	public void seteaFocoCodigoConsultora(String valor)
	{
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoZONCargosForm f = (MantenimientoZONCargosForm) this.mantenimientoZONCargosForm;
		String resultado = null;
		
		if(valor != null)
			 resultado = ajax.obtenerDatosConsultoraAsignarCargo(pais.getCodigo(), pais.getCodigoConexionExterna(), valor);
		
		if(resultado.compareTo("1") == 0)
		{
			f.setNombreCliente("No se encuentra");
			f.setDocCliente("No se encuentra");
		}else
		{
			f.setNombreCliente(resultado.split("_")[0]);
			f.setNombresCompletosConsultora(resultado.split("_")[0]);
			f.setDocCliente(resultado.split("_")[1]);			
		}		
	}
	
		
	public void loadCargosByFlagFuturasAsignar()
	{
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		List lista = new ArrayList();
		LabelValue[] aux = new LabelValue[]{};
		
		if(this.flagFuturas)
			aux = ajax.getCargosByFlagFuturas("1");
		else
			aux = ajax.getCargosByFlagFuturas("0");
		
		for (LabelValue labelValue : aux) {
			 Map aux1 = new HashMap();
			 aux1.put("descripcion", labelValue.getLabel());
			 aux1.put("codigoCargo", labelValue.getValue());
			 lista.add(aux1);
		}
		
		this.zonMantAsignarTipoCargoList = lista;		
	}
	
	
	private void limpiarCamposCargo(MantenimientoZONCargosForm f)
	{
		f.setCodigoCargo("");
		f.setDescripcionCargo("");
		f.setCodigoTipoUniAdmi("");
		f.setCantUniAdmi("");
		f.setCodigoTitular("");
		f.setPosicion("");
		f.setIndicadorAdmin("");
		f.setCodigoCargoBase("");
	}
	
	
	/**
	* inicializa popup Licencia Cargo
	*/
	public void vistaLicenciaCargo(ActionEvent event) 
	{
		log.debug("Entering my method 'vistaLicenciaCargo'");
		try {
			this.mostrarBotonSave = false;
			this.diasLicencia = null;	
			this.zonCargosList = null;
			this.zonMantTipoLicenciaList = new ArrayList();
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			MantenimientoZONCargosForm f = new MantenimientoZONCargosForm();
			String ventanaPopup = "";
			RequestContext context = RequestContext.getCurrentInstance(); 
			
			if (this.beanRegistroSeleccionado == null) {
				this.setMensajeAlertaDefault(this.getResourceMessage("mantenimientoZONCargosForm.message.error.selected.item"));
				String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
				this.getRequestContext().execute(ventanaConfirmar);
				context.addCallbackParam("ventanaPopup", ventanaPopup);
				return;
			} else {
				// Validamos si es titular y si no tiene licencia vigente y
				// no este reemplaznado a otra
				Map map = (Map) this.beanRegistroSeleccionado;
				String codigoEstadoCargo = map.get("codigoEstadoCargo").toString();
				String valorTitular = map.get("valorTitular").toString();
				String estadoLicenciaVigente = map.get("estadoLicenciaVigente").toString();
				String estadoReemplazoPorLicencia = map.get("estadoReemplazoPorLicencia").toString();
				String cantidadUnidadAdministrativa = map.get("cantidadUnidadAdministrativa").toString();
				String indicadorCargoFuturo = map.get("indicadorCargoFuturo").toString();

				if (indicadorCargoFuturo.equals("F")) {
					this.flag = false;
					this.setMensajeAlertaDefault("Cliente No valido para Licencia");
					String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
					this.getRequestContext().execute(ventanaConfirmar);
					context.addCallbackParam("ventanaPopup", ventanaPopup);
					return;
				}

				if (!(codigoEstadoCargo.trim().equals("A") && cantidadUnidadAdministrativa.equals("U")
						&& estadoLicenciaVigente.trim().equals("0") && estadoReemplazoPorLicencia.trim().equals("0"))) {
					this.flag = false;
					this.setMensajeAlertaDefault(this.getResourceMessage("mantenimientoZONCargosForm.no.apta.licencia"));
					String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
					this.getRequestContext().execute(ventanaConfirmar);
					context.addCallbackParam("ventanaPopup", ventanaPopup);
					return;
				}	
			}
			
			
			MantenimientoZONDirectorioService service = (MantenimientoZONDirectorioService) this.getBean("spusicc.mantenimientoZonDirectorioService");
			AjaxService ajaxService = (AjaxService) getBean("ajaxService");
			BeanUtils.copyProperties(f, new MantenimientoZONCargosForm());

			Map registroSeleccionado = (Map) this.beanRegistroSeleccionado;

			if (registroSeleccionado != null) {
				this.zonMantTipoLicenciaList = service.getTipoLicenciaList();
				Map criteria = new HashMap();

				// Obtener el objeto del detalle de Directorio de Venta

				criteria.put("numDetalle", registroSeleccionado.get("oidDirecVentDetal"));
				criteria.put("tipoOperacion", registroSeleccionado.get("codigoOperacion"));
				criteria.put("codigoCargo", registroSeleccionado.get("codigoCargo"));
				criteria.put("codigoCliente", registroSeleccionado.get("codigoCliente"));
				criteria.put("fechaRegistro",registroSeleccionado.get("fechaIngreso"));
				criteria.put("campanaProceso", registroSeleccionado.get("campana"));
				criteria.put("codigoRegion", registroSeleccionado.get("codigoRegion"));
				criteria.put("codigoZona", registroSeleccionado.get("codigoZona"));
				criteria.put("correlativoCabecera", registroSeleccionado.get("correlativoCabecera"));
				criteria.put("codigoPais", pais.getCodigo());
				criteria.put("codigoConexionExterna", pais.getCodigoConexionExterna());

				// Ponemos a session el id, para luego usarlo en la pantalla de grabado
				this.idRegistroLicencia = criteria;

				Map resultado = service.obtenerDirectorioVentaDetalle(criteria);

				String[] varCodigoRegion = new String[1];
				varCodigoRegion[0] = (String) resultado.get("codigoRegion");

				String[] varCodigoZona = new String[1];
				varCodigoZona[0] = (String) resultado.get("codigoZona");

				f.setCodigoConsultora((String) resultado.get("codigoCliente"));
				f.setCodigoCargoLicencia((String) resultado.get("codigoCargo"));
				f.setCodigoRegionLic((String) resultado.get("codigoRegion"));
				f.setCodigoZonaLic((String) resultado.get("codigoZona"));
				f.setCodigoSubGerencia((String) resultado.get("codigoSubGerencia"));
				f.setCampanyaProceso(registroSeleccionado.get("campana").toString());
				f.setOidIdioma(this.zonMantOidIdioma);
				f.setCodigoPais(pais.getCodigo());
				f.setCorrelativoCabecera(registroSeleccionado.get("correlativoCabecera").toString());
				f.setFechaIngreso(String.valueOf(resultado.get("fechaRegistro")));

				// Setear el nombre Cliente y documento de Identidad
				String nombreCliente = ajaxService.obtenerDatosConsultora(
						pais.getCodigo(), pais.getCodigoConexionExterna(), f.getCodigoConsultora());
				if (nombreCliente.equals("1")) {
					f.setNombreCliente("No se encuentra");
					f.setDocCliente("No se encuentra");
				} else {
					f.setNombreCliente(nombreCliente.split("_")[0]);
					f.setNombresCompletosConsultora(nombreCliente.split("_")[0]);
					f.setDocCliente(nombreCliente.split("_")[1]);
				}

				// Setear el Rol y Perfil para el Cargo Actual
				String rolPerfil = ajaxService.getRolPerfil((String) criteria.get("codigoCargo")).trim();
				String[] arrayRP = StringUtils.split(rolPerfil, "|");
				f.setRolDesc(arrayRP[0]);
				f.setPerfilDesc(arrayRP[1]);

				String desZona = MapUtils.getString(resultado, "unidadAdministrativa", "");
				String desRegio = MapUtils.getString(resultado, "descripcionRegion", "");

				if (StringUtils.isNotBlank(desZona))
					f.setCodigoUnidadAdministrativa(String.format("%s %s - %s %s", varCodigoRegion[0], desRegio,
							varCodigoZona[0], StringUtils.remove(desZona, varCodigoZona[0])));
				else
					f.setCodigoUnidadAdministrativa(String.format("%s %s", varCodigoRegion[0], desRegio));

				// Fecha de Facturacion y/o Proceso
				Map criteriaPeriodo = new HashMap();
				criteriaPeriodo.put("codigoPais", pais.getCodigo());
				criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO); // Indica Campanha Activa
				criteriaPeriodo.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente

				if (StringUtils.equals(pais.getCodigoConexionExterna(),
						Constants.CONEXION_EXTERNA_FOX)) {
					Map mapControl = service.getControlFacturacionByCriteriaFOX(criteriaPeriodo);
					f.setFechaSalida(MapUtils.getString(mapControl, "fechaProceso"));
					f.setFechaSalidaDate(DateUtil.convertStringToDate(f.getFechaSalida()));
					this.fecFacturacion = MapUtils.getString(mapControl, "fechaProceso");
				} else {
					MantenimientoOCRPedidoControlFacturacionService serviceFacturacion = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
					PedidoControlFacturacion controlFacturacion = serviceFacturacion
							.getControlFacturacionById(criteriaPeriodo);
					f.setFechaSalida(controlFacturacion.getFechaProceso());
					f.setFechaSalidaDate(DateUtil.convertStringToDate(f.getFechaSalida()));
					this.fecFacturacion = controlFacturacion.getFechaProceso();
				}
				// Longitud del campo consultora
				ClienteUAGenerarService clienteService2 = (ClienteUAGenerarService) getBean("sisicc.clienteUAGenerarService");
				this.longitudCampoClientes = clienteService2.getTamanhoNumeroCliente(pais.getCodigo()).toString();
				
				this.mantenimientoZONCargosForm = f;

//				this.redireccionarPagina("mantenimientoZONRegistrarLicenciasCargoForm");
				ventanaPopup = "mantenimientoZONRegistrarLicenciasCargoForm.xhtml";
				context.addCallbackParam("ventanaPopup", ventanaPopup);
				this.mostrarBotonSalir = false;
			}
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	/**
	 * Guarda los cambios en el popup Licencia Cargo
	 * @param event
	 */
	public void guardarLicenciaCargo(ActionEvent event)
	{
		log.debug("Entering my method 'guardarLicenciaCargo'");
		try {
			MantenimientoZONCargosForm f = (MantenimientoZONCargosForm) this.mantenimientoZONCargosForm;
			MantenimientoZONDirectorioService service = (MantenimientoZONDirectorioService)	this.getBean("spusicc.mantenimientoZonDirectorioService");
			AjaxService ajax = (AjaxService)getBean("ajaxService");
			
			Map criteria = new HashMap(); 

			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			
			/*Directorio Ventas Cabecera y Directorio Ventas Detalle 
			con código de operación LI  y estatus IT con el código del cliente y el 
			codigo del cliente  que lo esta reemplazando */
			criteria.put("codigoPais", pais.getCodigo());
			criteria.put("codigoConsultora", f.getCodigoConsultora());
			criteria.put("codigoTipoCargo", StringUtils.trim(f.getCodigoCargoLicencia().substring(0, 3)));
			criteria.put("fechaIngreso",DateUtil.convertDateToString("dd/MM/yyyy", DateUtil.convertStringToDate(f.getFechaSalida())));
			String fechaCam = ajax.getPeriodoByFecha(pais.getCodigo(), pais.getCodigoConexionExterna(), f.getFechaSalida());
			if(fechaCam !=null){
				if(!fechaCam.equals(f.getCampanyaProceso())){
					criteria.put("campanyaProceso", fechaCam);
				}else
					criteria.put("campanyaProceso", f.getCampanyaProceso());
			}else{
				this.addError("Error: ", this.getResourceMessage("mantenimientoZONCargosForm.message.error.fecha.sincampania"));	
				this.grabarLicencias = "N";
				return;	
			}
			
			criteria.put("usuarioLogin",usuario.getLogin());
			criteria.put("nombresCompletosConsultora", f.getNombresCompletosConsultora());
			criteria.put("tipoOperacion", Constants.ZON_MANT_CODIGO_OPERACION_LI);
			criteria.put("codigoEstadoCargo", Constants.ZON_MANT_ESTADO_CARGO_INACTIVA_TEMPORAL);
			criteria.put("codigoSubGerencia", f.getCodigoSubGerencia());
			criteria.put("correlativoCabecera", f.getCorrelativoCabecera());
			criteria.put("codigoConexionExterna", pais.getCodigoConexionExterna());
			
			//El primer insert no envia el correo
			criteria.put("envioCorreo", Constants.NO);
			//
			criteria.put("estadoRegistro", Constants.ACTIVO);
			criteria.put("nombresCompletosConsultoraReemplazo", f.getNombresCompletosConsultoraReemplazo());
			
			criteria.put("tipoLicencia", f.getCodigoTipoLicencia());
			criteria.put("codigoConsultoraReemplazante", f.getCodigoClienteBuscar());
			criteria.put("codigoEnvio", Constants.MAV_CODIGO_INDICADOR_ENVIO_P);
			
			String codigoConsultoraReemplazante = (String)criteria.get("codigoConsultoraReemplazante");

			String codigoTipoCargoReemplazo = "";
			if(StringUtils.isNotBlank(f.getCodigoCargoLicencia()))
				codigoTipoCargoReemplazo = StringUtils.trim(StringUtils.substring(f.getCodigoCargoLicencia(), 0,3));

			criteria.put("codigoTipoCargoReemplazo", codigoTipoCargoReemplazo);
			
			if(StringUtils.isNotBlank(f.getCodigoClienteBuscar())){
				//Validar si el cliente esta activo.(Para ORA y FOX)
				String[] acti = ajax.obtenerDatosConsultoraReemplazo(pais.getCodigo(), pais.getCodigoConexionExterna(), f.getCodigoClienteBuscar());
				if(acti[0].equals("1")){
					this.addError("Error: ", this.getResourceMessage("mantenimientoZONCargosForm.licencia.message.error.cliente.esInactiva"));					
					this.grabarLicencias = "N";
					return;
				}	
			}
			
			//Validar Tipo Subtipo
			if (StringUtils.isNotEmpty(codigoConsultoraReemplazante) && (StringUtils.equals(codigoTipoCargoReemplazo, Constants.ZON_MANT_GERENTE_REGION) || 
					StringUtils.equals(codigoTipoCargoReemplazo, Constants.ZON_MANT_GERENTE_ZONA)) && 
						!StringUtils.equals(pais.getCodigoConexionExterna(), Constants.CONEXION_EXTERNA_FOX)){
			
				ReporteService reporteService = (ReporteService)getBean("scsicc.reporteService");
				Map criteriaOidClienteReemp = new HashMap();
				criteriaOidClienteReemp.put("codigoCliente", codigoConsultoraReemplazante);	
					
				String oidClieGerente = reporteService.getOidString("getOidClienteByCodigoCliente", criteriaOidClienteReemp);
				criteria.put("oidCliente", oidClieGerente);
			}
			
			//Validamos que la fecha de salida ingresada sea menor o igual a la fecha de facturación
			String fecFacturacion = this.fecFacturacion;
			if(DateUtil.compareDates(f.getFechaSalida(), fecFacturacion,"dd/MM/yyyy") > 0){
				this.addError("Error: ", this.getResourceMessage("mantenimientoZONCargosForm.licencia.message.error.fechaSalida.esmayor"));					
				this.grabarLicencias = "N";
				return;	
			}
			
			criteria.put("fechaSalida",DateUtil.convertDateToString("dd/MM/yyyy", DateUtil.convertStringToDate(f.getFechaSalida())));
			criteria.put("fechaRegreso", DateUtil.convertDateToString("dd/MM/yyyy", DateUtil.convertStringToDate(f.getFechaRegreso())));
			criteria.put("fechaRegresoCorreo", DateUtil.convertDateToString("dd/MM/yyyy", DateUtil.convertStringToDate(f.getFechaRegreso())));

			String codigoRegion = f.getCodigoRegionLic();
			String codigoZona = f.getCodigoZonaLic();
			log.info(">>codigoRegion: "+codigoRegion);
			log.info(">>codigoZona: "+codigoZona);
			
			if(StringUtils.isNotBlank(codigoRegion) && StringUtils.isBlank(codigoZona)){
				criteria.put("codigoRegion", codigoRegion);
				criteria.put("codigoRegionReemplazante", "");
			}
			
			if(StringUtils.isNotBlank(codigoRegion) && StringUtils.isNotBlank(codigoZona)){
				criteria.put("codigoRegion", codigoRegion);
				criteria.put("codigoZona", codigoZona);
				criteria.put("codigoRegionReemplazante", "");
				criteria.put("codigoZonaReemplazante", "");
			}
			
			//Tomamos el id de la session
			Map idRegistroLicencia = this.idRegistroLicencia;
			criteria.put("idRegistroLicencia", idRegistroLicencia);
			
			criteria.put("codigoCorrrelativoCabeceraReemplazo", f.getCodigoCorrrelativoCabeceraReemplazos());
			service.insertDirectorioVentaLicencia(criteria);			
			this.idRegistroLicencia = null;
			
			this.listaBusqueda = this.setFindAttributes();
			this.datatableBusqueda = new DataTableModel(this.listaBusqueda);
			this.grabarLicencias = "S";

//			this.setMensajeAlertaDefaultAction(this.getResourceMessage("mantenimientoZONCargosForm.datos.insert"));
//			RequestContext.getCurrentInstance().update("principalFormAlertAction:textoMensajeAlerta");
//			String ventana = "PF('principalFormAlertActionLicencia_alertDialogAction').show()";
//			this.getRequestContext().execute(ventana);			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
			this.grabarLicencias = "N";
		}		
	}
	
	public void metodoKeyPressSeteaFocoCodigoConsultoraLicencias()
	{
		MantenimientoZONCargosForm f = (MantenimientoZONCargosForm) this.mantenimientoZONCargosForm;
		if(StringUtils.isNotBlank(f.getCodigoClienteBuscar()))
		{
			f.setCodigoClienteBuscar(StringUtils.leftPad(f.getCodigoClienteBuscar(), Integer.parseInt(this.longitudCampoClientes), "0"));
			seteaFocoCodigoConsultoraLicencias(f.getCodigoClienteBuscar());			
		}		
	}
	
	public void metodoKeyUpSeteaFocoCodigoConsultoraLicencias()
	{
		MantenimientoZONCargosForm f = (MantenimientoZONCargosForm) this.mantenimientoZONCargosForm;
		if(StringUtils.isNotBlank(f.getCodigoClienteBuscar()))
		{
			if(f.getCodigoClienteBuscar().length() == Integer.parseInt(this.longitudCampoClientes))
				seteaFocoCodigoConsultoraLicencias(f.getCodigoClienteBuscar());
		}
	}
	
	public void seteaFocoCodigoConsultoraLicencias(String valor)
	{
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoZONCargosForm f = (MantenimientoZONCargosForm) this.mantenimientoZONCargosForm;
		String ua = "";
		
		if(valor != null){
			if(valor.equalsIgnoreCase(f.getCodigoConsultora()))
			{
				f.setNombresCompletosConsultoraReemplazo("No se encuentra");
				f.setNroDocumentoIdentidadReemplazo("No se encuentra");
				this.zonCargosList = null;
				this.addError("Error: ", "La cliente de reemplazo no puede ser la misma.");				
			}else
			{
				String[] resultado = ajax.obtenerDatosConsultoraReemplazo(pais.getCodigo(), pais.getCodigoConexionExterna(), valor);
				if(resultado[0].equals("1"))
				{
					f.setNombresCompletosConsultoraReemplazo("No se encuentra");
					f.setNroDocumentoIdentidadReemplazo("No se encuentra");
					this.zonCargosList = null;
					f.setListaUnidadesAdministrativas(null);
				}else
				{
					for (int i = 0; i < resultado.length; i++) 
					{	
						f.setNombresCompletosConsultoraReemplazo(resultado[i].split("_")[0]);
						f.setNroDocumentoIdentidadReemplazo(resultado[i].split("_")[1]);
						ua = ua + resultado[i].split("_")[3];
						f.setListaUnidadesAdministrativas(ua);
						
						this.zonCargosList = ajax.getDataCargo(pais.getCodigo(), pais.getCodigoConexionExterna(), valor);
					}					
				}				
			}
		}
	}
	
	/**
	 * Metodo que calcula la diferencia entre fecha de salida y fecha de regreso.
	 * Se ejecuta al cambiar de valor el campo Fecha de Regreso
	 */
	public void numeroDias() 
	{
		try {
			
			MantenimientoZONCargosForm f = (MantenimientoZONCargosForm) this.mantenimientoZONCargosForm;
			Date valor = f.getFechaRegresoDate();
			f.setFechaRegreso(DateUtil.convertDateToString(f.getFechaRegresoDate()));
			f.setFechaSalida(DateUtil.convertDateToString(f.getFechaSalidaDate()));
			
			if(f.getFechaRegresoDate() != null && f.getFechaSalidaDate() != null)
			{
				int resultadoFechas = DateUtil.compareDates(f.getFechaSalida() ,f.getFechaRegreso(),"dd/MM/yyyy");
				
				if(resultadoFechas == 1)
					this.addWarn("", "La Fecha Regreso debe ser mayor a la Fecha Salida");
				else{
					Long diferencia = valor.getTime() - f.getFechaSalidaDate().getTime();
					Long dias = diferencia / (1000 * 60 * 60 * 24) + 1;		
					this.diasLicencia = dias.toString();
				}
			}else
				this.diasLicencia = null;		
		} catch (Exception e) {
			this.diasLicencia = null;
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
		
	/**
	 * Metodo que calcula la diferencia entre fecha de salida y fecha de regreso.
	 * Se ejecuta al cambiar de valor el campo Fecha de Salida
	 */
	public void cambiarSalidaNumeroDias() 
	{
		try {
			MantenimientoZONCargosForm f = (MantenimientoZONCargosForm) this.mantenimientoZONCargosForm;
			Date valor = f.getFechaSalidaDate();
			f.setFechaRegreso(DateUtil.convertDateToString(f.getFechaRegresoDate()));
			f.setFechaSalida(DateUtil.convertDateToString(f.getFechaSalidaDate()));
			
			if(f.getFechaRegresoDate() != null && f.getFechaSalidaDate() != null)
			{
				int resultadoFechas = DateUtil.compareDates(f.getFechaSalida() ,f.getFechaRegreso(),"dd/MM/yyyy");
				
				if(resultadoFechas == 1)
					this.addWarn("", "La Fecha Regreso debe ser mayor a la Fecha Salida");
				else{
					Long diferencia = f.getFechaRegresoDate().getTime() - valor.getTime();
					Long dias = diferencia / (1000 * 60 * 60 * 24) + 1;		
					this.diasLicencia = dias.toString();
				}
			}else
				this.diasLicencia = null;		
		} catch (Exception e) {
			this.diasLicencia = null;
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	/**
	 * Fecha Facturacion y Fecha Venta segun Fecha Salida
	 * @param event
	 */
	public void seteaCampaniasFS(ActionEvent event)
	{
		try {		
			AjaxService ajax = (AjaxService)getBean("ajaxService");
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			MantenimientoZONCargosForm f = (MantenimientoZONCargosForm) this.mantenimientoZONCargosForm;
			f.setFechaSalida(DateUtil.convertDateToString(f.getFechaSalidaDate()));
			
			if(StringUtils.isNotBlank(f.getFechaSalida()))
			{
				String numPer = "1";
				String periodoObtenido = ajax.getPeriodoByFecha(pais.getCodigo(), pais.getCodigoConexionExterna(), f.getFechaSalida());
				String periodoSiguiente = ajax.getPeriodoNSiguiente(pais.getCodigo(), periodoObtenido, numPer);
				
				if(StringUtils.isBlank(periodoObtenido))
					f.setCampanyaFacturacion("Fecha sin campaña.");
				else
					f.setCampanyaFacturacion(periodoObtenido);
				
				if(StringUtils.isBlank(periodoSiguiente))
					f.setCampanyaVenta("Fecha sin campaña.");
				else
					f.setCampanyaVenta(periodoSiguiente);					
			}else
				this.addWarn("", "Seleccione fecha de Salida.");
		
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	public void seteaCampaniasFR(ActionEvent event)
	{
		try {			
			AjaxService ajax = (AjaxService)getBean("ajaxService");
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			MantenimientoZONCargosForm f = (MantenimientoZONCargosForm) this.mantenimientoZONCargosForm;
			f.setFechaRegreso(DateUtil.convertDateToString(f.getFechaRegresoDate()));
			
			if(StringUtils.isNotBlank(f.getFechaRegreso()))
			{
				String numPer = "1";
				String periodoObtenido = ajax.getPeriodoByFecha(pais.getCodigo(), pais.getCodigoConexionExterna(), f.getFechaRegreso());
				String periodoSiguiente = ajax.getPeriodoNSiguiente(pais.getCodigo(), periodoObtenido, numPer);
				
				if(StringUtils.isBlank(periodoObtenido))
					f.setCampanyaFacturacion2("Fecha sin campaña.");
				else
					f.setCampanyaFacturacion2(periodoObtenido);
				
				if(StringUtils.isBlank(periodoSiguiente))
					f.setCampanyaVenta2("Fecha sin campaña.");
				else
					f.setCampanyaVenta2(periodoSiguiente);
			}else
				this.addWarn("", "Seleccione fecha de Regreso.");
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	public String getZonMantOidIdioma() {
		return zonMantOidIdioma;
	}

	public void setZonMantOidIdioma(String zonMantOidIdioma) {
		this.zonMantOidIdioma = zonMantOidIdioma;
	}

	public List getZonTipoCargoList() {
		return zonTipoCargoList;
	}

	public void setZonTipoCargoList(List zonTipoCargoList) {
		this.zonTipoCargoList = zonTipoCargoList;
	}

	public LabelValue[] getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(LabelValue[] siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	public String getLongitudCampoClientes() {
		return longitudCampoClientes;
	}

	public void setLongitudCampoClientes(String longitudCampoClientes) {
		this.longitudCampoClientes = longitudCampoClientes;
	}

	public List getZonPerfilList() {
		return zonPerfilList;
	}

	public void setZonPerfilList(List zonPerfilList) {
		this.zonPerfilList = zonPerfilList;
	}

	public List getZonRolList() {
		return zonRolList;
	}

	public void setZonRolList(List zonRolList) {
		this.zonRolList = zonRolList;
	}

	public List getZonNovedadesList() {
		return zonNovedadesList;
	}

	public void setZonNovedadesList(List zonNovedadesList) {
		this.zonNovedadesList = zonNovedadesList;
	}

	public List getListaDirectorioVentas() {
		return listaDirectorioVentas;
	}

	public void setListaDirectorioVentas(List listaDirectorioVentas) {
		this.listaDirectorioVentas = listaDirectorioVentas;
	}

	public boolean isFlagFuturas() {
		return flagFuturas;
	}

	public void setFlagFuturas(boolean flagFuturas) {
		this.flagFuturas = flagFuturas;
	}

	public LabelValueCDR[] getSiccZonaList() {
		return siccZonaList;
	}

	public void setSiccZonaList(LabelValueCDR[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	public boolean isMostrarPopUpCliente() {
		return mostrarPopUpCliente;
	}

	public void setMostrarPopUpCliente(boolean mostrarPopUpCliente) {
		this.mostrarPopUpCliente = mostrarPopUpCliente;
	}

	public static String getPopupClientes() {
		return POPUP_CLIENTES;
	}

	public String getRutaPagina() {
		return rutaPagina;
	}

	public void setRutaPagina(String rutaPagina) {
		this.rutaPagina = rutaPagina;
	}

	public MantenimientoZONCargosAction getMantenimientoZONCargosAction() {
		return mantenimientoZONCargosAction;
	}

	public void setMantenimientoZONCargosAction(
			MantenimientoZONCargosAction mantenimientoZONCargosAction) {
		this.mantenimientoZONCargosAction = mantenimientoZONCargosAction;
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public List getZonMantAsignarTipoCargoList() {
		return zonMantAsignarTipoCargoList;
	}

	public void setZonMantAsignarTipoCargoList(List zonMantAsignarTipoCargoList) {
		this.zonMantAsignarTipoCargoList = zonMantAsignarTipoCargoList;
	}

	public LabelValue[] getZonMantAsignarRegionList() {
		return zonMantAsignarRegionList;
	}

	public void setZonMantAsignarRegionList(LabelValue[] zonMantAsignarRegionList) {
		this.zonMantAsignarRegionList = zonMantAsignarRegionList;
	}

	public LabelValueCDR[] getZonMantAsignarZonaList() {
		return zonMantAsignarZonaList;
	}

	public void setZonMantAsignarZonaList(LabelValueCDR[] zonMantAsignarZonaList) {
		this.zonMantAsignarZonaList = zonMantAsignarZonaList;
	}

	public String getZonMantAsignarTipoCargoCerrar() {
		return zonMantAsignarTipoCargoCerrar;
	}

	public void setZonMantAsignarTipoCargoCerrar(
			String zonMantAsignarTipoCargoCerrar) {
		this.zonMantAsignarTipoCargoCerrar = zonMantAsignarTipoCargoCerrar;
	}

	public MantenimientoZONCargosForm getMantenimientoZONCargosForm() {
		return mantenimientoZONCargosForm;
	}

	public void setMantenimientoZONCargosForm(
			MantenimientoZONCargosForm mantenimientoZONCargosForm) {
		this.mantenimientoZONCargosForm = mantenimientoZONCargosForm;
	}

	public Boolean getFlagOcultarMultiple() {
		return flagOcultarMultiple;
	}

	public void setFlagOcultarMultiple(Boolean flagOcultarMultiple) {
		this.flagOcultarMultiple = flagOcultarMultiple;
	}

	public Boolean getFlagOcultarZonas() {
		return flagOcultarZonas;
	}

	public void setFlagOcultarZonas(Boolean flagOcultarZonas) {
		this.flagOcultarZonas = flagOcultarZonas;
	}

	public BusquedaHIPClienteSearchAction getBusquedaHIPClienteSearchAction() {
		return busquedaHIPClienteSearchAction;
	}

	public void setBusquedaHIPClienteSearchAction(
			BusquedaHIPClienteSearchAction busquedaHIPClienteSearchAction) {
		this.busquedaHIPClienteSearchAction = busquedaHIPClienteSearchAction;
	}

	public static String getPopupAsignar() {
		return POPUP_ASIGNAR;
	}

	public List getZonMantTipoLicenciaList() {
		return zonMantTipoLicenciaList;
	}

	public void setZonMantTipoLicenciaList(List zonMantTipoLicenciaList) {
		this.zonMantTipoLicenciaList = zonMantTipoLicenciaList;
	}

	public LabelValue[] getZonCargosList() {
		return zonCargosList;
	}

	public void setZonCargosList(LabelValue[] zonCargosList) {
		this.zonCargosList = zonCargosList;
	}

	public Map getIdRegistroLicencia() {
		return idRegistroLicencia;
	}

	public void setIdRegistroLicencia(Map idRegistroLicencia) {
		this.idRegistroLicencia = idRegistroLicencia;
	}

	public String getDiasLicencia() {
		return diasLicencia;
	}

	public void setDiasLicencia(String diasLicencia) {
		this.diasLicencia = diasLicencia;
	}

	public String getFecFacturacion() {
		return fecFacturacion;
	}

	public void setFecFacturacion(String fecFacturacion) {
		this.fecFacturacion = fecFacturacion;
	}

	public static String getPopupLicencias() {
		return POPUP_LICENCIAS;
	}

	/**
	 * @return the grabarLicencias
	 */
	public String getGrabarLicencias() {
		return grabarLicencias;
	}

	/**
	 * @param grabarLicencias the grabarLicencias to set
	 */
	public void setGrabarLicencias(String grabarLicencias) {
		this.grabarLicencias = grabarLicencias;
	}

	/**
	 * @return the grabarAsignar
	 */
	public String getGrabarAsignar() {
		return grabarAsignar;
	}

	/**
	 * @param grabarAsignar the grabarAsignar to set
	 */
	public void setGrabarAsignar(String grabarAsignar) {
		this.grabarAsignar = grabarAsignar;
	}

}
