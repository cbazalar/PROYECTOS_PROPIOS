package biz.belcorp.ssicc.web.spusicc.sto.action;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
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

import org.apache.commons.lang.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.bas.model.BASParametroPais;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.spusicc.sto.model.AccionTipoDocumento;
import biz.belcorp.ssicc.dao.spusicc.sto.model.GestionDocumento;
import biz.belcorp.ssicc.dao.spusicc.sto.model.GestionDocumentoNssicc;
import biz.belcorp.ssicc.dao.spusicc.sto.model.HistoricoTipoDocumento;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.ClienteUAGenerarService;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.bas.MantenimientoBASParametroPaisService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECIngresoAtencionesService;
import biz.belcorp.ssicc.service.spusicc.sto.MantenimientoSTOBloqueoControlService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEjecucionValidacionesService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOHistoricoService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOLevantamientoErroresValidacionService;
import biz.belcorp.ssicc.service.spusicc.sto.framework.ProcesoSTOExecutionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.sto.form.ProcesoSTOLevantamientoErroresValidacionForm;


@ManagedBean
@SessionScoped
public class ProcesoSTOLevantamientoErroresValidacionAction extends BaseProcesoAbstractAction{

	private static final long serialVersionUID = 2570108724981716281L;
	
	private List siccRegionList;
	private LabelValue[] siccZonaList;
	private LabelValue[] stoValidacionesByDocumento;
	private LabelValue[] stoOrigenesList;
	private LabelValue[] stoZonaArriboList;
	private LabelValue[] stoAccionesGestionList;
	private LabelValue[] stoMotivosRechazoList;
	private List stoTipoDocumentoList;
	private List stoLevantamientoErroresCLientesList;
	private List stoLevantamientoErroresValidacionList;
	private List stoResumenClientesList;
	private List nuevaLista;
	private DataTableModel nuevaListaModel;
	private Object[] registroSeleccionadoObject;
	
	private String stoTipoDocumentoCabecera="1";
	private String stoTipoDocumentoDetalle="0";
	private String stoTipoDocumentoTodos=Constants.STO_TIPO_DOCUMENTO_TODOS;
	private String stoTipoGestionableSi="1";
	private String stoTipoGestionableNo="0";
	private String stoTipoGestionableTodos=Constants.STO_TIPO_GESTIONABLE_TODOS;
	private String stoTipoAprobadoSi="1";
	private String stoTipoAprobadoNo="0";
	private String stoTipoAprobadoTodos=Constants.STO_TIPO_APROBADO_TODOS;
	private Integer longitudCampoClientes;
	private String codigoPais;
	private String parametroRechazo;
	
	
	private String attachment = "";
	private boolean mostrarFecha;
	private boolean mostrarMotivoRechazo;
	private boolean mostrarAccion;
	private boolean isPopupModificar;
	
	//Mostar popup OCR
	private String urlOCR;
	private String paisOCR;
	private String marcaOCR;
	private String tipoDocumentoSTO;
	private boolean mostrarSeleccion;
	
	//OCC
	@ManagedProperty(value="#{mantenimientoSTOOrdenCompraCabeceraAction}")	
	private MantenimientoSTOOrdenCompraCabeceraAction mantSTOOrdenCompraCabecera;
	//OCD
	@ManagedProperty(value="#{mantenimientoSTOOrdenCompraDetalleAction}")	
	private MantenimientoSTOOrdenCompraDetalleAction mantSTOOrdenCompraDetalle;
	//DCYZ
	@ManagedProperty(value="#{mantenimientoSTODuplaCyzoneAction}")	
	private MantenimientoSTODuplaCyzoneAction mantSTODuplaCyzone;
	//SAD
	@ManagedProperty(value="#{mantenimientoSTOActualizacionDatosAction}")	
	private MantenimientoSTOActualizacionDatosAction mantSTOActualizacionDatos;
	//CUP
	@ManagedProperty(value="#{mantenimientoSTOCuponPagoAction}")	
	private MantenimientoSTOCuponPagoAction mantSTOCuponPago;
	//FAS
	@ManagedProperty(value="#{mantenimientoSTOFamiliaSeguraAction}")	
	private MantenimientoSTOFamiliaSeguraAction mantSTOFamiliaSegura;
	//CIF
	@ManagedProperty(value="#{mantenimientoSTOCartaInvitacionFlexipagoAction}")	
	private MantenimientoSTOCartaInvitacionFlexipagoAction mantSTOCartaInvitacionFlexipago;
	//SIM
	@ManagedProperty(value="#{mantenimientoSTOIngresoMetasAction}")	
	private MantenimientoSTOIngresoMetasAction mantSTOIngresoMetas;
	//OT
	@ManagedProperty(value="#{mantenimientoSTOOrdenTransporteAction}")	
	private MantenimientoSTOOrdenTransporteAction mantSTOOrdenTransporte;
	//CED
	@ManagedProperty(value="#{mantenimientoSTOContratoEjecutivaAction}")	
	private MantenimientoSTOContratoEjecutivaAction mantSTOContratoEjecutiva;
	//SCC
	@ManagedProperty(value="#{mantenimientoSTOSolicitudCreditoAction}")	
	private MantenimientoSTOSolicitudCreditoAction mantSTOSolicitudCredito;
	//SPVC
	@ManagedProperty(value="#{mantenimientoSTOSolicitudPostVentaCabeceraAction}")	
	private MantenimientoSTOSolicitudPostVentaCabeceraAction mantSTOSolicitudPostVentaCabecera;
	//SPVD
	@ManagedProperty(value="#{mantenimientoSTOSolicitudPostVentaDetalleAction}")	
	private MantenimientoSTOSolicitudPostVentaDetalleAction mantSTOSolicitudPostVentaDetalle;

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoSTOLevantamientoErroresValidacionForm procesoForm =new ProcesoSTOLevantamientoErroresValidacionForm();		
		return procesoForm;
	}
	
	//Redirecciona a la pag. respectiva dependiendo el tipo de Documento
	public void ejecutarModificar(ActionEvent event){
		try {
			if(!this.isPopupModificar){
				if(this.registroSeleccionadoObject!=null){
					if(this.registroSeleccionadoObject.length==1){					
						this.beanRegistroSeleccionado=this.registroSeleccionadoObject[0];					
						
					}
					else {
						this.setMensajeAlertaDefault(this.getResourceMessage("errors.select.item"));
						String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
						this.getRequestContext().execute(ventanaConfirmar);
						this.getRequestContext().addCallbackParam("ventanaPopup", "");
						return;
					}				
				}
				else {
					this.setMensajeAlertaDefault(this.getResourceMessage("errors.select.item"));
					String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
					this.getRequestContext().execute(ventanaConfirmar);
					this.getRequestContext().addCallbackParam("ventanaPopup", "");
					return;			
				}
			}
			
			ProcesoSTOLevantamientoErroresValidacionForm f = (ProcesoSTOLevantamientoErroresValidacionForm)this.formProceso;
			GestionDocumento gestion = (GestionDocumento) this.beanRegistroSeleccionado;
			
			String ventanaPopup = "";
			if(StringUtils.isNotBlank(f.getTipoDocumento())){
				if(StringUtils.equals(f.getTipoDocumento(), "OCC") && StringUtils.equals(gestion.getDocumento(), "OCC")){
					this.mantSTOOrdenCompraCabecera.setRegistroSeleccionado(this.beanRegistroSeleccionado);
					this.mantSTOOrdenCompraCabecera.inicializarValores();					
					ventanaPopup = "mantenimientoSTOOrdenCompraCabeceraForm.xhtml";
					this.getRequestContext().addCallbackParam("ventanaPopup", ventanaPopup);							
				}
				if(StringUtils.equals(f.getTipoDocumento(), "OCC") && StringUtils.equals(gestion.getDocumento(), "OCD")){
					this.mantSTOOrdenCompraDetalle.setRegistroSeleccionado(this.beanRegistroSeleccionado);
					this.mantSTOOrdenCompraDetalle.inicializarValores();					
					ventanaPopup = "mantenimientoSTOOrdenCompraDetalleForm.xhtml";
					this.getRequestContext().addCallbackParam("ventanaPopup", ventanaPopup);					
				}
				if(StringUtils.equals(f.getTipoDocumento(), "DCYZ")){					
					this.mantSTODuplaCyzone.setRegistroSeleccionado(this.beanRegistroSeleccionado);
					this.mantSTODuplaCyzone.inicializarValores();					
					ventanaPopup = "mantenimientoSTODuplaCyzoneForm.xhtml";
					this.getRequestContext().addCallbackParam("ventanaPopup", ventanaPopup);				
				}
				if(StringUtils.equals(f.getTipoDocumento(), "SAD")){					
					this.mantSTOActualizacionDatos.setRegistroSeleccionado(this.beanRegistroSeleccionado);
					this.mantSTOActualizacionDatos.inicializarValores();					
					ventanaPopup = "mantenimientoSTOActualizacionDatosForm.xhtml";
					this.getRequestContext().addCallbackParam("ventanaPopup", ventanaPopup);					
				}
				if(StringUtils.equals(f.getTipoDocumento(), "CUP")){					
					this.mantSTOCuponPago.setRegistroSeleccionado(this.beanRegistroSeleccionado);
					this.mantSTOCuponPago.inicializarValores();					
					ventanaPopup = "mantenimientoSTOCuponPagoForm.xhtml";
					this.getRequestContext().addCallbackParam("ventanaPopup", ventanaPopup);					
				}
				if(StringUtils.equals(f.getTipoDocumento(), "SCC")){					
					this.mantSTOSolicitudCredito.setRegistroSeleccionado(this.beanRegistroSeleccionado);
					this.mantSTOSolicitudCredito.inicializarValores();					
					ventanaPopup = "mantenimientoSTOSolicitudCreditoForm.xhtml";
					this.getRequestContext().addCallbackParam("ventanaPopup", ventanaPopup);				
				}
				if(StringUtils.equals(f.getTipoDocumento(), "FAS")){					
					this.mantSTOFamiliaSegura.setRegistroSeleccionado(this.beanRegistroSeleccionado);
					this.mantSTOFamiliaSegura.inicializarValores();					
					ventanaPopup = "mantenimientoSTOFamiliaSeguraForm.xhtml";
					this.getRequestContext().addCallbackParam("ventanaPopup", ventanaPopup);					
				}
				if(StringUtils.equals(f.getTipoDocumento(), "CIF")){					
					this.mantSTOCartaInvitacionFlexipago.setRegistroSeleccionado(this.beanRegistroSeleccionado);
					this.mantSTOCartaInvitacionFlexipago.inicializarValores();					
					ventanaPopup = "mantenimientoSTOCartaInvitacionFlexipagoForm.xhtml";
					this.getRequestContext().addCallbackParam("ventanaPopup", ventanaPopup);						
				}				
				if(StringUtils.equals(f.getTipoDocumento(), "SIM")){					
					this.mantSTOIngresoMetas.setRegistroSeleccionado(this.beanRegistroSeleccionado);
					this.mantSTOIngresoMetas.inicializarValores();					
					ventanaPopup = "mantenimientoSTOIngresoMetasForm.xhtml";
					this.getRequestContext().addCallbackParam("ventanaPopup", ventanaPopup);
					
				}
				if(StringUtils.equals(f.getTipoDocumento(), "OT")){					
					this.mantSTOOrdenTransporte.setRegistroSeleccionado(this.beanRegistroSeleccionado);
					this.mantSTOOrdenTransporte.inicializarValores();					
					ventanaPopup = "mantenimientoSTOOrdenTransporteForm.xhtml";
					this.getRequestContext().addCallbackParam("ventanaPopup", ventanaPopup);					
				}				
				if(StringUtils.equals(f.getTipoDocumento(), "CED")){					
					this.mantSTOContratoEjecutiva.setRegistroSeleccionado(this.beanRegistroSeleccionado);
					this.mantSTOContratoEjecutiva.inicializarValores();					
					ventanaPopup = "mantenimientoSTOContratoEjecutivaForm.xhtml";
					this.getRequestContext().addCallbackParam("ventanaPopup", ventanaPopup);						
				}
				if(StringUtils.equals(f.getTipoDocumento(), "SPVC") && StringUtils.equals(gestion.getDocumento(), "SPVC")){					
					this.mantSTOSolicitudPostVentaCabecera.setRegistroSeleccionado(this.beanRegistroSeleccionado);
					this.mantSTOSolicitudPostVentaCabecera.inicializarValores();					
					ventanaPopup = "mantenimientoSTOSolicitudPostVentaCabeceraForm.xhtml";
					this.getRequestContext().addCallbackParam("ventanaPopup", ventanaPopup);					
				}
				if(StringUtils.equals(f.getTipoDocumento(), "SPVC") && StringUtils.equals(gestion.getDocumento(), "SPVD")){					
					this.mantSTOSolicitudPostVentaDetalle.setRegistroSeleccionado(this.beanRegistroSeleccionado);
					this.mantSTOSolicitudPostVentaDetalle.inicializarValores();					
					ventanaPopup = "mantenimientoSTOSolicitudPostVentaDetalleForm.xhtml";
					this.getRequestContext().addCallbackParam("ventanaPopup", ventanaPopup);						
				}
				this.isPopupModificar=false;
			}
			if (StringUtils.isBlank(ventanaPopup)) {
				this.getRequestContext().addCallbackParam("ventanaPopup", "");
			}
				
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	//Metodo que ejecuta el boton buscar--carga la grilla principal
	public void busquedaPrincipal(ActionEvent event){
		try {
			this.find(event);
			this.nuevaLista=this.listaBusqueda;
			this.nuevaListaModel = new DataTableModel(this.nuevaLista);			
			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)throws Exception {
		
		ProcesoSTOLevantamientoErroresValidacionForm f = (ProcesoSTOLevantamientoErroresValidacionForm) this.formProceso;
		Usuario usuario=this.mPantallaPrincipalBean.getCurrentUser();
		List validacionesErrorList = this.stoLevantamientoErroresValidacionList;
		
		String valorAccion= f.getAccion();		
		List lista = getListaGestion(f, validacionesErrorList, valorAccion);
		GestionDocumento gestionDocumento = (GestionDocumento) validacionesErrorList.get(0);
    	String codigoTipoDocumento = gestionDocumento.getCodTipoDocu();
		
    	String codigoPais = this.codigoPais;
		
		/*parametros STO*/
		params.put("codigoPais",codigoPais );
		params.put("usuario", usuario);
		params.put("codigoPeriodo", "");
		params.put("codTipoDocu", codigoTipoDocumento);
		
		if (codigoTipoDocumento.equalsIgnoreCase("OCC")) {
			params.put("codigoPeriodo", f.getCodigoPeriodo());
		}
		
		//Valida  que no haya un proceso en ejecución    	
    	ProcesoSTOHistoricoService serviceHistorico = (ProcesoSTOHistoricoService) getBean("spusicc.procesoSTOHistoricoService");	
		
		List historicoList = serviceHistorico.getProcesoValidacionEjecucionByDocumento(params);
		if (historicoList.size() > 0) {
			HistoricoTipoDocumento historicoTipoDocumento = (HistoricoTipoDocumento) historicoList.get(0);			
			throw new Exception(this.getResourceMessage("procesoSTOLevantamientoErroresValidacionForm.validacionCargaEjecucion"));
		}
		
		AccionTipoDocumento accionTipoDocumento = new AccionTipoDocumento(codigoPais, codigoTipoDocumento,valorAccion);
		ProcesoSTOExecutionService service = (ProcesoSTOExecutionService) getBean("spusicc.procesoSTOExecutionService");
		service.execute(accionTipoDocumento, params, lista);
		

		Map listaParams = getListaGestionParams(f);
		ProcesoSTOLevantamientoErroresValidacionService serviceGestion = (ProcesoSTOLevantamientoErroresValidacionService) getBean("spusicc.procesoSTOLevantamientoErroresValidacionService");
		List levantamientoErroresValidacionList = serviceGestion.getGestionDocumentoList(listaParams);		
		this.stoLevantamientoErroresValidacionList=levantamientoErroresValidacionList;	
		this.nuevaListaModel = new DataTableModel(levantamientoErroresValidacionList);
		f.setObservaciones("");
		return params;
	}
	
	@Override
	protected List setFindAttributes() throws Exception {
		//Validar Fecha--Hora:
		this.activarHotkeyEstandar=true;
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		String codigoPais   = pais.getCodigo();	
		
		ProcesoSTOLevantamientoErroresValidacionForm f = (ProcesoSTOLevantamientoErroresValidacionForm)this.formProceso;
		ProcesoSTOLevantamientoErroresValidacionService service = (ProcesoSTOLevantamientoErroresValidacionService) getBean("spusicc.procesoSTOLevantamientoErroresValidacionService");
		ProcesoSTOEjecucionValidacionesService procesoSTOEjecucionValidacionesService = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
		f.setZonasSearch(Constants.SI);


		Map listaParams =  getListaGestionParams(f);
		Map criteria = new HashMap();
		criteria.put("codigoPais",codigoPais);
		criteria.put("tipoDocumento",f.getTipoDocumento());		
		
		if(log.isDebugEnabled())
			log.debug("mi tipo de documento es: "+ f.getTipoDocumento() );
		
		this.tipoDocumentoSTO = procesoSTOEjecucionValidacionesService.getValorTipoDocumento(criteria);		
		List levantamientoErroresValidacionList =  service.getGestionDocumentoList(listaParams);		
		f.setIndex(levantamientoErroresValidacionList.size());	
		this.stoLevantamientoErroresValidacionList=levantamientoErroresValidacionList;
		
		
		this.mostrarAccion=true;		
		return levantamientoErroresValidacionList;		
		
	}

	@Override
	protected void setViewAtributes() throws Exception {
		ProcesoSTOLevantamientoErroresValidacionForm f = (ProcesoSTOLevantamientoErroresValidacionForm)this.formProceso;
		this.mostrarBotonExecute=false;
		this.mostrarListaBusqueda=false;
		this.mostrarBotonBuscar=false;	
		this.mostrarPanelAdicionalProceso=true;
		this.mostrarPaginacionDatatableSpinner = false;
		this.mostrarBotonActualizarDatos = false;
		this.mostrarSeleccion=false;
		this.nroPaginacionDatatable = 25;
		this.mostrarAccion=false;
		this.nuevaListaModel = new DataTableModel();
		this.nuevaLista = new ArrayList();
		this.registroSeleccionadoObject=null;
		this.isPopupModificar=false;
		this.activarHotkeyEstandar=true;

		
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();				
		Usuario usuario=this.mPantallaPrincipalBean.getCurrentUser();
		this.codigoPais   = pais.getCodigo();		
		
//		Services
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		ProcesoSTOLevantamientoErroresValidacionService procesoSTOLevantamientoErroresValidacionService = (ProcesoSTOLevantamientoErroresValidacionService) getBean("spusicc.procesoSTOLevantamientoErroresValidacionService");
		ProcesoSTOEjecucionValidacionesService procesoSTOEjecucionValidacionesService = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
		MantenimientoSTOBloqueoControlService serviceSTOBC = (MantenimientoSTOBloqueoControlService) getBean("spusicc.mantenimientoSTOBloqueoControlService");
		
			
		Map criteria = new HashMap();		
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoUsuario", usuario.getLogin());
		criteria.put("indicadorPantalla", Constants.STO_INDICADOR_PANTALLA_GESTION);		
		
		this.siccRegionList=reporteService.getListaGenerico("getRegionesByPais",criteria);
		this.stoTipoDocumentoList=procesoSTOEjecucionValidacionesService.getTiposDocumentosSTO(criteria);		
		
		Map criteriaPeriodo = new HashMap();
		criteriaPeriodo.put("codigoPais", codigoPais);
		criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO); // Indica Campanha Activa
		criteriaPeriodo.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente
	     
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteriaPeriodo);
	
		f.setZonasSearch(Constants.NO);
		f.setIndicadorDocumento(Constants.STO_TIPO_DOCUMENTO_TODOS);
		f.setIndicadorGestionable(Constants.STO_TIPO_GESTIONABLE_TODOS);
		f.setIndicadorAprobado(Constants.STO_TIPO_APROBADO_TODOS);
		f.setFechaProceso(controlFacturacion.getFechaProceso());
		
		f.setCodigoPeriodoActual(controlFacturacion.getCodigoPeriodo());
		
		f.setCodigoUsuario(usuario.getLogin());
		f.setDocumentoIdentidad("");
		
		ClienteUAGenerarService clienteService = (ClienteUAGenerarService) getBean("sisicc.clienteUAGenerarService");		
		this.longitudCampoClientes=clienteService.getTamanhoNumeroCliente(codigoPais);
		
		this.stoResumenClientesList=new ArrayList();
		f.setClienteList(null);		
		
		this.urlOCR = procesoSTOEjecucionValidacionesService.getURLOCRParametro(criteria);
		String PaisOCRyMarca=procesoSTOEjecucionValidacionesService.getMarcaPais(criteria);
		
		this.paisOCR=PaisOCRyMarca.split("-")[0];//Captura del Pais OCR
		this.marcaOCR=PaisOCRyMarca.split("-")[1];//Captura del Pais OCR		
		
		this.stoLevantamientoErroresValidacionList=new ArrayList();

		this.mostrarFecha=false;
		this.mostrarMotivoRechazo=false;
		this.nuevaLista = new ArrayList();
		this.nuevaListaModel = new DataTableModel(this.nuevaLista);
		
		/*Agregado por Sebastian Guerra 19/08/2014 por RCR CHI-SiCC-2014-0078*/
		MantenimientoBASParametroPaisService serviceBaspp = (MantenimientoBASParametroPaisService) getBean("bas.mantenimientoBASParametroPaisService");
		BASParametroPais baspp;
		
		baspp = new BASParametroPais();
		baspp.setCodigoPais(codigoPais);
		baspp.setCodigoSistema(Constants.STO_CODIGO_SISTEMA);
		baspp.setCodigoParametro(Constants.STO_PARAMETRO_PORCENTAJE_DESVIACION);
		List list1 = serviceBaspp.getParametroPais(baspp);
		BASParametroPais baspp1 = (BASParametroPais) list1.get(0);
		f.setIndActPorcentajeDesviacion(baspp1.getValorParametro());
		
		baspp = new BASParametroPais();
		baspp.setCodigoPais(codigoPais);
		baspp.setCodigoSistema(Constants.STO_CODIGO_SISTEMA);
		baspp.setCodigoParametro(Constants.STO_PARAMETRO_PROMEDIO_PEDIDO);
		List list2 = serviceBaspp.getParametroPais(baspp);
		BASParametroPais baspp2 = (BASParametroPais) list2.get(0);
		f.setIndActPromedioPedido(baspp2.getValorParametro());
		
		baspp = new BASParametroPais();
		baspp.setCodigoPais(codigoPais);
		baspp.setCodigoSistema(Constants.STO_CODIGO_SISTEMA);
		baspp.setCodigoParametro(Constants.STO_PARAMETRO_MONTO_REAL);
		List list3 = serviceBaspp.getParametroPais(baspp);
		BASParametroPais baspp3 = (BASParametroPais) list3.get(0);
		f.setIndActMontoReal(baspp3.getValorParametro());
		/*Agregado por Sebastian Guerra 19/08/2014 por RCR CHI-SiCC-2014-0078*/
		
	}
	
	//Obtiene la lista de Parametros en un map los almacena.
	private Map  getListaGestionParams(ProcesoSTOLevantamientoErroresValidacionForm f){

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		ProcesoSTOEjecucionValidacionesService procesoSTOEjecucionValidacionesService = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
		
		String indDocu=  f.getIndicadorDocumento();
		if (StringUtils.isEmpty(indDocu))indDocu = "";
		log.debug("indDocu = " +indDocu);
		
		String fechaInicio,fechaFin,fechaInicioProceso,fechaFinProceso=null;
		
		String aMask = "dd/MM/yyyy HH:mm";
		fechaInicio=DateUtil.convertDateToString(aMask,f.getFechaInicioDate());
		log.debug("fechaInicio " + fechaInicio);
		
		fechaFin=DateUtil.convertDateToString(aMask,f.getFechaFinDate());
		log.debug("fechaFin " + fechaFin);
		
		fechaInicioProceso = DateUtil.convertDateToString(aMask ,f.getFechaInicioProcesoDate());
		log.debug("fechaInicioProceso " + fechaInicioProceso);
		
		fechaFinProceso=DateUtil.convertDateToString(aMask ,f.getFechaFinProcesoDate());
		log.debug("fechaFinProceso " + fechaFinProceso);
		
		String codigoCliente = f.getCodigoCliente();
		
		if(f.getRegionList()!=null){
		    if(f.getRegionList().length==1){
		    	if(f.getRegionList()[0].equals(""))	f.setRegionList(null);	
		    }
		}
		
		if(f.getZonaList()!=null){
		    if(f.getZonaList().length==1){
		    	if(f.getZonaList()[0].equals("")) f.setZonaList(null);	
		    }
		}
		
		List listaClientes = this.stoLevantamientoErroresCLientesList;		
		String [] listaClientes2 = new String[0];
		if (codigoCliente.length()>0) listaClientes2 = f.getCodigoCliente().split(",+");
	
		Map map  = new HashMap();
		String codCliente = "";

		int size = 0;
		if (listaClientes != null) size = listaClientes.size();	
		
		 
		Long longitudPais=pais.getLongitudCodigoCliente();		
		String [] clienteList= new String[size + listaClientes2.length];
		
		for (int i = 0; i < size; i++) {			
			map = (Map)listaClientes.get(i);
			codCliente = (String)map.get("codigoCliente");
			clienteList[i] = codCliente;
		}

		for (int i = 0; i < listaClientes2.length; i++) {
			clienteList[i+size] = StringUtils.leftPad(listaClientes2[i], longitudPais.intValue(), '0');
		}

		f.setClienteList(clienteList);
		if(StringUtils.isBlank(f.getIndicadorDocumento()))
			f.setIndicadorDocumento(Constants.STO_TIPO_DOCUMENTO_TODOS);
		if(StringUtils.isBlank(f.getIndicadorGestionable()))
			f.setIndicadorGestionable(Constants.STO_TIPO_GESTIONABLE_TODOS);
		if(StringUtils.isBlank(f.getIndicadorAprobado()))
			f.setIndicadorAprobado(Constants.STO_TIPO_APROBADO_TODOS);
		
		Map params = new HashMap();
		params.put("codigoPais",pais.getCodigo());
		params.put("tipoDocumento",f.getTipoDocumento());
		params.put("validacion",f.getCodigoValidacion());
		params.put("numLote",f.getNumeroLote());
		params.put("numDocu",f.getNumeroDocumento());
		params.put("indDocu",indDocu);
		params.put("indGest",f.getIndicadorGestionable());
		params.put("indAprob",f.getIndicadorAprobado());
		params.put("fechaInicio",fechaInicio);
		params.put("fechaFin",fechaFin);
		params.put("fechaInicioProceso",fechaInicioProceso);
		params.put("fechaFinProceso",fechaFinProceso);
		params.put("idioma", usuario.getIdioma().getCodigo());
		params.put("numeroDocumento",f.getNumDocumento());
		params.put("codigoCliente",codigoCliente);
		params.put("codigoPeriodo",f.getCodigoPeriodo());
		params.put("codigoOrigen",f.getCodigoOrigen());
		params.put("codigoZonaArribo",f.getCodigoZonaArribo());
		params.put("regionList",(f.getRegionList()== null) ? new ArrayList(): Arrays.asList(f.getRegionList()));
		params.put("zonaList",(f.getZonaList()== null) ? new ArrayList(): Arrays.asList(f.getZonaList()));
		params.put("clienteList",(f.getClienteList()== null) ? new ArrayList(): Arrays.asList(f.getClienteList()));
		params.put("documentoIdentidad", f.getDocumentoIdentidad());
		params.put("rechazo", "");
		params.put("montoPedido", "");
		
		
		params.put("codigoParametro", Constants.STO_IND_CALC_PED_CDR);
		String indicadorCalculo = procesoSTOEjecucionValidacionesService.getParametroSTO(params);
			
		f.setIndPedCdr("0");
		
		if (indicadorCalculo.equals("S")){
			if( (f.getTipoDocumento().equals(Constants.STO_TIPO_DOCUMENTO_SPVC) || f.getTipoDocumento().equals(Constants.STO_TIPO_DOCUMENTO_OCC)) && indDocu.equals(stoTipoDocumentoCabecera)){
				f.setIndPedCdr("1");
			}
		}
		
		params.put("indPedCdr",f.getIndPedCdr());

		return params;
	}
	
	//Obtiene la lista del tipo GestionDocumento
	private List getListaGestion(ProcesoSTOLevantamientoErroresValidacionForm f, List validacionesErrorList,String valorAccion) throws Exception{
		boolean isOnlySelectedItems=false;
		if(valorAccion.equals("VA") || valorAccion.equals("AP")	|| valorAccion.equals("DP") || valorAccion.equals("RE"))
    		 isOnlySelectedItems = true;
		
    	List padre=validacionesErrorList;    	
    	
    	if(!isOnlySelectedItems) return validacionesErrorList;
    	else {    		
    		List lista = new ArrayList();
    		for (int j = 0; j < this.registroSeleccionadoObject.length; j++) { 
    			Object item=this.registroSeleccionadoObject[j];
    			lista.add(item); 
    		}
    			return lista;    		
    	} 
	}
	
	
	//Muestra las zonas segun la Región 
	public void loadZonas(ValueChangeEvent val) {
		log.debug(">>loadZonas ");
		log.debug("val: " + val.getNewValue().toString());	
		
		ProcesoSTOLevantamientoErroresValidacionForm f = (ProcesoSTOLevantamientoErroresValidacionForm)this.formProceso;
		String[] regiones = (String[]) val.getNewValue();
		if (!val.equals(null) && regiones.length > 0) {
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			this.setSiccZonaList(aSvc.getZonasMultipleByPaisMarcaCanalRegion(
					this.codigoPais, Constants.CODIGO_MARCA_DEFAULT,Constants.CODIGO_CANAL_DEFAULT, regiones,
					Constants.FORMATO_TOTAL));			
			f.setZonaList(null);
		} else {
			this.siccZonaList = null;
			f.setZonaList(null);
		}
	}
	
	//Muestrael panel de Motivos Rechazo cuando se seleccione uan accion del accion.
	public void loadMotivosRechazo(ValueChangeEvent val){
		ProcesoSTOLevantamientoErroresValidacionForm f = (ProcesoSTOLevantamientoErroresValidacionForm)this.formProceso;
		String accion=val.getNewValue().toString();
		this.mostrarMotivoRechazo=false;
		if(StringUtils.isNotBlank(accion)){
			if(StringUtils.equals(accion, "RE") ||StringUtils.equals(accion, "RET")){
				AjaxService ajax = (AjaxService) getBean("ajaxService");
				this.stoMotivosRechazoList=ajax.getMotivosRechazo( this.codigoPais,f.getTipoDocumento());
				this.mostrarMotivoRechazo=true;
				f.setMotivoRechazo(null);
				f.setObservaciones("");
			}
		}else{
			this.stoMotivosRechazoList=null;
			f.setMotivoRechazo(null);
			f.setObservaciones("");
		}		
	}
	
	//muestra las cambios de los componentes al cambiar el tipo de documento
	public void loadTipoDocumento(ValueChangeEvent val) {
		ProcesoSTOLevantamientoErroresValidacionForm f = (ProcesoSTOLevantamientoErroresValidacionForm)this.formProceso;		
		Usuario usuario=this.mPantallaPrincipalBean.getCurrentUser();
		String tipoDocu=val.getNewValue().toString();
		
		this.stoValidacionesByDocumento=null;
		this.stoOrigenesList=null;
		this.stoZonaArriboList=null;
		this.stoAccionesGestionList=null;
		this.mostrarFecha=false;
		
		if(StringUtils.isNotEmpty(tipoDocu)){						
			if(StringUtils.equals(tipoDocu, "OCC")) {				
				 f.setCodigoPeriodo(f.getCodigoPeriodoActual());
				 this.mostrarFecha=true;
			}			
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			this.stoValidacionesByDocumento=ajax.getValidacionesByDocumento(this.codigoPais, tipoDocu);
			this.stoOrigenesList=ajax.getOrigenSTOByTipoDocumento(this.codigoPais, tipoDocu);
			this.stoZonaArriboList=ajax.getZonaArriboSTOByTipoDocumento(this.codigoPais, tipoDocu);
			this.stoAccionesGestionList=ajax.getAccionesByDocumentoByUsuario(this.codigoPais, tipoDocu,usuario.getLogin());
			this.parametroRechazo=ajax.getParametroRechazoByDocumento(this.codigoPais,tipoDocu);
		}
		
		f.setCodigoValidacion(null);
		f.setCodigoOrigen(null);
		f.setCodigoZonaArribo(null);
		f.setAccion(null);
		
		f.setIndicadorDocumento(Constants.STO_TIPO_DOCUMENTO_TODOS);
		f.setIndicadorGestionable(Constants.STO_TIPO_GESTIONABLE_TODOS);
		f.setIndicadorAprobado(Constants.STO_TIPO_APROBADO_TODOS);
		
		this.nuevaLista = new ArrayList();
		this.nuevaListaModel = new DataTableModel(this.nuevaLista);
		
	}
	
	
	//Subir Archivo
	public void uploadArchivo() throws Exception {	
		try {
		log.debug("Entering 'load Clientes from file' method");	
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		ProcesoSTOLevantamientoErroresValidacionForm f = (ProcesoSTOLevantamientoErroresValidacionForm)this.formProceso;
		MantenimientoRECIngresoAtencionesService service = (MantenimientoRECIngresoAtencionesService)getBean("spusicc.mantenimientoRECIngresoAtencionesService");
		GenericoService genericoService = (GenericoService) getBean("genericoService");

		List listaClientes = new ArrayList();
		String indValidaCodConsultoraDocIdentidad = null;
		
		ParametroPais parametroPais = new ParametroPais();
		parametroPais.setCodigoPais(pais.getCodigo());
		parametroPais.setCodigoSistema("STO");
		parametroPais.setNombreParametro("indValidaCodConsultoraDocIdentidad");
		
		List lstParametros = genericoService.getParametrosPais(parametroPais);
		
		if(lstParametros != null && lstParametros.size() > 0){
			ParametroPais ps = (ParametroPais)lstParametros.get(0);
			indValidaCodConsultoraDocIdentidad = ps.getValorParametro();
		}
		
		Map criteria = new HashMap();
		UploadedFile archivo =f.getClienteFile();				
		InputStream is = archivo.getInputstream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));		
	
		Long longitudPais=pais.getLongitudCodigoCliente();
		String linea = "";
		String codigoConsultora = "";
		int cont = 0;
		int numRegistros = 0;

		while (true) {
			linea = br.readLine();
			if (linea == null)
				break;

			codigoConsultora = StringUtils.leftPad(linea.trim(), longitudPais.intValue(), '0');
			criteria.put("codigoConsultora",codigoConsultora);
			LabelValue bean = new LabelValue(codigoConsultora, service.getCodigoConsultora(criteria));
			
			if(bean.getValue() == null && StringUtils.equals(indValidaCodConsultoraDocIdentidad, Constants.SI)){
				criteria.put("documentoIdentidad", codigoConsultora);
				String codigoConsultoraPorDocIden = service.getCodigoConsultoraPorDocumentoIdentidad(criteria);
				
				if(codigoConsultoraPorDocIden == null){
					bean = new LabelValue(codigoConsultora, service.getCodigoConsultoraPorDocumentoIdentidad(criteria));
				}else{
					bean = new LabelValue(codigoConsultoraPorDocIden, service.getCodigoConsultoraPorDocumentoIdentidad(criteria));
				}
			}
			
			listaClientes.add(bean);

			if(bean.getValue() == null)
				cont++;
			
			numRegistros++;
		}

		//Se inserta en la tabla temporal
		String oidCarga = service.getOidCargaCliente();
		criteria.put("oid", oidCarga);		
		service.insertaClienteFile(listaClientes, criteria);		

		//Se obtiene la lista de la tabla temporal
		List list = new ArrayList();
		list = service.getCargaClienteList(criteria);

		f.setCodigosErradosFile("");

		if(cont != 0)
			f.setCodigosErradosFile("Existe(n) "+cont+" codigo(s) errado(s)");

		criteria.put("numRegistros",  numRegistros);
		List list2 = new ArrayList();
		list2 = service.getResumenCargaClienteList(criteria);

		this.stoLevantamientoErroresCLientesList=list;
		this.stoResumenClientesList=list2;
		
		} catch (Exception e) {	
			this.attachment="";				
			this.addError("Error:", e.getCause().getMessage());
		}
	}
	
	public void handleFileUpload(FileUploadEvent event) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("handleFileUpload");
		}
		ProcesoSTOLevantamientoErroresValidacionForm f = (ProcesoSTOLevantamientoErroresValidacionForm)this.formProceso;
		if (event != null) {			
			f.setClienteFile(event.getFile());
			this.setAttachment(event.getFile().getFileName());
			this.uploadArchivo();
		}
	}
	
	@Override
	public String setValidarProceso(){	
		ProcesoSTOLevantamientoErroresValidacionForm f = (ProcesoSTOLevantamientoErroresValidacionForm)this.formProceso;
		this.parametroRechazo="";     
		if(StringUtils.isEmpty(f.getAccion()))
				return "Debe seleccionar una acción";
		
		if(StringUtils.equals(f.getAccion(), "RE") || StringUtils.equals(f.getAccion(), "RET")){
			if(StringUtils.isEmpty(f.getMotivoRechazo()))
				return "Debe seleccionar un motivo de rechazo";
			if(StringUtils.isEmpty(f.getObservaciones()) && StringUtils.equals(this.parametroRechazo, "1"))
				return "Debe llenar el campo Observaciones Rechazo";		
		}
		
		
		if(f.getTipoDocumento()=="OCC"){
			if(f.getAccion()=="VA" || f.getAccion()=="VAT"){
				AjaxService ajax = (AjaxService) getBean("ajaxService");
				String data=ajax.getValidarEnvioValidacionesSTOGP3(f.getCodigoPais());
				if(StringUtils.equals(data, "1"))
					return this.getResourceMessage("procesoSTOLevantamientoErroresValidacionForm.msj.controlConcurrencia");				
			}
		}		
		
		if(StringUtils.equals(f.getAccion(), "APT") ||StringUtils.equals(f.getAccion(), "DPT")||
				StringUtils.equals(f.getAccion(), "RET") ||StringUtils.equals(f.getAccion(), "VAT")){
			return null;
        }else{
        	if(this.registroSeleccionadoObject==null || this.registroSeleccionadoObject.length==0)        	
    			return this.getResourceMessage("errors.select.item");
        }		
		return null;		
	}
	
	//Validaciones antes de ejecutar el método setFindAtributtes
	public String setValidarFind(){
		ProcesoSTOLevantamientoErroresValidacionForm f = (ProcesoSTOLevantamientoErroresValidacionForm)this.formProceso;
		if(f.getFechaInicioDate()!=null && f.getFechaFinDate()!=null){
			String fInicio=DateUtil.convertDateToString(f.getFechaInicioDate());
			String fFin=DateUtil.convertDateToString(f.getFechaFinDate());
			int valor1 = DateUtil.compareDates(fInicio, fFin, "dd/MM/yyyy");
			if (valor1 == 1)
				return "Carga:"+this.getResourceMessage("errors.compare.dates");
			
		}
		
		if(f.getFechaInicioProcesoDate()!=null && f.getFechaFinProcesoDate()!=null){
			String fInicio=DateUtil.convertDateToString(f.getFechaInicioProcesoDate());
			String fFin=DateUtil.convertDateToString(f.getFechaFinProcesoDate());
			int valor1 = DateUtil.compareDates(fInicio, fFin, "dd/MM/yyyy");
			if (valor1 == 1)
				return "Proceso:"+this.getResourceMessage("errors.compare.dates");
			
		}		
		return "";		
	}
	
	//Recorre toda la lista y muestra los elementos seleccionados
	public void mostrarSeleccionable(ValueChangeEvent val) throws Exception{
		
		Boolean valor=(Boolean)val.getNewValue();
		log.debug("mostrarSeleccionable - valor: " + valor);
		List seleccionable=new ArrayList();
		for(int i=0;i<this.listaBusqueda.size();i++){
			GestionDocumentoNssicc gestionNssicc=new GestionDocumentoNssicc();
			gestionNssicc=(GestionDocumentoNssicc)this.listaBusqueda.get(i);
			gestionNssicc.setSeleccionable(valor);
			seleccionable.add(gestionNssicc);		
		}
		this.listaBusqueda=seleccionable;		
		this.datatableBusqueda = new DataTableModel(this.listaBusqueda);
		this.mostrarSeleccion=valor;
		
	}
	
	//Método que se ejecuta al hacer click en los link de la grilla principal
	public void modificarPopup(ActionEvent event){
		try {
			ExternalContext externalContext = FacesContext.getCurrentInstance()	.getExternalContext();
			String valor = externalContext.getRequestParameterMap().get("parametroFila").toString();
			int index=Integer.parseInt(valor);
			//List principalList = this.stoLevantamientoErroresValidacionList;			
			this.beanRegistroSeleccionado = this.stoLevantamientoErroresValidacionList.get(index);	
			this.isPopupModificar=true;
			ejecutarModificar(event);
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	//Método para Limpiar las listas que se cargan al subir los archivos
	public void limpiaCargaArchivos(ActionEvent event){
		ProcesoSTOLevantamientoErroresValidacionForm f = (ProcesoSTOLevantamientoErroresValidacionForm)this.formProceso;
		f.setCodigoCliente("");
		this.stoLevantamientoErroresCLientesList= new ArrayList();		
		this.stoResumenClientesList=new ArrayList();
	}
	
	public List getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	public List getStoTipoDocumentoList() {
		return stoTipoDocumentoList;
	}

	public void setStoTipoDocumentoList(List stoTipoDocumentoList) {
		this.stoTipoDocumentoList = stoTipoDocumentoList;
	}

	public List getStoLevantamientoErroresCLientesList() {
		return stoLevantamientoErroresCLientesList;
	}

	public void setStoLevantamientoErroresCLientesList(
			List stoLevantamientoErroresCLientesList) {
		this.stoLevantamientoErroresCLientesList = stoLevantamientoErroresCLientesList;
	}

	public List getStoLevantamientoErroresValidacionList() {
		return stoLevantamientoErroresValidacionList;
	}

	public void setStoLevantamientoErroresValidacionList(
			List stoLevantamientoErroresValidacionList) {
		this.stoLevantamientoErroresValidacionList = stoLevantamientoErroresValidacionList;
	}

	public List getStoResumenClientesList() {
		return stoResumenClientesList;
	}

	public void setStoResumenClientesList(List stoResumenClientesList) {
		this.stoResumenClientesList = stoResumenClientesList;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public LabelValue[] getStoValidacionesByDocumento() {
		return stoValidacionesByDocumento;
	}

	public void setStoValidacionesByDocumento(
			LabelValue[] stoValidacionesByDocumento) {
		this.stoValidacionesByDocumento = stoValidacionesByDocumento;
	}

	public LabelValue[] getStoOrigenesList() {
		return stoOrigenesList;
	}

	public void setStoOrigenesList(LabelValue[] stoOrigenesList) {
		this.stoOrigenesList = stoOrigenesList;
	}

	public LabelValue[] getStoZonaArriboList() {
		return stoZonaArriboList;
	}

	public void setStoZonaArriboList(LabelValue[] stoZonaArriboList) {
		this.stoZonaArriboList = stoZonaArriboList;
	}

	public LabelValue[] getStoAccionesGestionList() {
		return stoAccionesGestionList;
	}

	public void setStoAccionesGestionList(LabelValue[] stoAccionesGestionList) {
		this.stoAccionesGestionList = stoAccionesGestionList;
	}

	public LabelValue[] getStoMotivosRechazoList() {
		return stoMotivosRechazoList;
	}

	public void setStoMotivosRechazoList(LabelValue[] stoMotivosRechazoList) {
		this.stoMotivosRechazoList = stoMotivosRechazoList;
	}

	public String getStoTipoDocumentoCabecera() {
		return stoTipoDocumentoCabecera;
	}

	public void setStoTipoDocumentoCabecera(String stoTipoDocumentoCabecera) {
		this.stoTipoDocumentoCabecera = stoTipoDocumentoCabecera;
	}

	public String getStoTipoDocumentoDetalle() {
		return stoTipoDocumentoDetalle;
	}

	public void setStoTipoDocumentoDetalle(String stoTipoDocumentoDetalle) {
		this.stoTipoDocumentoDetalle = stoTipoDocumentoDetalle;
	}

	public String getStoTipoDocumentoTodos() {
		return stoTipoDocumentoTodos;
	}

	public void setStoTipoDocumentoTodos(String stoTipoDocumentoTodos) {
		this.stoTipoDocumentoTodos = stoTipoDocumentoTodos;
	}

	public String getStoTipoGestionableSi() {
		return stoTipoGestionableSi;
	}

	public void setStoTipoGestionableSi(String stoTipoGestionableSi) {
		this.stoTipoGestionableSi = stoTipoGestionableSi;
	}

	public String getStoTipoGestionableNo() {
		return stoTipoGestionableNo;
	}

	public void setStoTipoGestionableNo(String stoTipoGestionableNo) {
		this.stoTipoGestionableNo = stoTipoGestionableNo;
	}

	public String getStoTipoGestionableTodos() {
		return stoTipoGestionableTodos;
	}

	public void setStoTipoGestionableTodos(String stoTipoGestionableTodos) {
		this.stoTipoGestionableTodos = stoTipoGestionableTodos;
	}

	public String getStoTipoAprobadoSi() {
		return stoTipoAprobadoSi;
	}

	public void setStoTipoAprobadoSi(String stoTipoAprobadoSi) {
		this.stoTipoAprobadoSi = stoTipoAprobadoSi;
	}

	public String getStoTipoAprobadoNo() {
		return stoTipoAprobadoNo;
	}

	public void setStoTipoAprobadoNo(String stoTipoAprobadoNo) {
		this.stoTipoAprobadoNo = stoTipoAprobadoNo;
	}

	public String getStoTipoAprobadoTodos() {
		return stoTipoAprobadoTodos;
	}

	public void setStoTipoAprobadoTodos(String stoTipoAprobadoTodos) {
		this.stoTipoAprobadoTodos = stoTipoAprobadoTodos;
	}

	public Integer getLongitudCampoClientes() {
		return longitudCampoClientes;
	}

	public void setLongitudCampoClientes(Integer longitudCampoClientes) {
		this.longitudCampoClientes = longitudCampoClientes;
	}

	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public boolean isMostrarFecha() {
		return mostrarFecha;
	}

	public void setMostrarFecha(boolean mostrarFecha) {
		this.mostrarFecha = mostrarFecha;
	}

	public boolean isMostrarMotivoRechazo() {
		return mostrarMotivoRechazo;
	}

	public void setMostrarMotivoRechazo(boolean mostrarMotivoRechazo) {
		this.mostrarMotivoRechazo = mostrarMotivoRechazo;
	}

	public String getParametroRechazo() {
		return parametroRechazo;
	}

	public void setParametroRechazo(String parametroRechazo) {
		this.parametroRechazo = parametroRechazo;
	}

	public MantenimientoSTOOrdenCompraCabeceraAction getMantSTOOrdenCompraCabecera() {
		return mantSTOOrdenCompraCabecera;
	}

	public void setMantSTOOrdenCompraCabecera(
			MantenimientoSTOOrdenCompraCabeceraAction mantSTOOrdenCompraCabecera) {
		this.mantSTOOrdenCompraCabecera = mantSTOOrdenCompraCabecera;
	}

	public MantenimientoSTOSolicitudCreditoAction getMantSTOSolicitudCredito() {
		return mantSTOSolicitudCredito;
	}

	public void setMantSTOSolicitudCredito(
			MantenimientoSTOSolicitudCreditoAction mantSTOSolicitudCredito) {
		this.mantSTOSolicitudCredito = mantSTOSolicitudCredito;
	}

	public MantenimientoSTOSolicitudPostVentaCabeceraAction getMantSTOSolicitudPostVentaCabecera() {
		return mantSTOSolicitudPostVentaCabecera;
	}

	public void setMantSTOSolicitudPostVentaCabecera(
			MantenimientoSTOSolicitudPostVentaCabeceraAction mantSTOSolicitudPostVentaCabecera) {
		this.mantSTOSolicitudPostVentaCabecera = mantSTOSolicitudPostVentaCabecera;
	}
	
	public MantenimientoSTOSolicitudPostVentaDetalleAction getMantSTOSolicitudPostVentaDetalle() {
		return mantSTOSolicitudPostVentaDetalle;
	}

	public void setMantSTOSolicitudPostVentaDetalle(
			MantenimientoSTOSolicitudPostVentaDetalleAction mantSTOSolicitudPostVentaDetalle) {
		this.mantSTOSolicitudPostVentaDetalle = mantSTOSolicitudPostVentaDetalle;
	}

	public MantenimientoSTOOrdenCompraDetalleAction getMantSTOOrdenCompraDetalle() {
		return mantSTOOrdenCompraDetalle;
	}

	public void setMantSTOOrdenCompraDetalle(
			MantenimientoSTOOrdenCompraDetalleAction mantSTOOrdenCompraDetalle) {
		this.mantSTOOrdenCompraDetalle = mantSTOOrdenCompraDetalle;
	}

	public MantenimientoSTODuplaCyzoneAction getMantSTODuplaCyzone() {
		return mantSTODuplaCyzone;
	}

	public void setMantSTODuplaCyzone(
			MantenimientoSTODuplaCyzoneAction mantSTODuplaCyzone) {
		this.mantSTODuplaCyzone = mantSTODuplaCyzone;
	}

	public MantenimientoSTOActualizacionDatosAction getMantSTOActualizacionDatos() {
		return mantSTOActualizacionDatos;
	}

	public void setMantSTOActualizacionDatos(
			MantenimientoSTOActualizacionDatosAction mantSTOActualizacionDatos) {
		this.mantSTOActualizacionDatos = mantSTOActualizacionDatos;
	}

	public MantenimientoSTOFamiliaSeguraAction getMantSTOFamiliaSegura() {
		return mantSTOFamiliaSegura;
	}

	public void setMantSTOFamiliaSegura(
			MantenimientoSTOFamiliaSeguraAction mantSTOFamiliaSegura) {
		this.mantSTOFamiliaSegura = mantSTOFamiliaSegura;
	}

	public MantenimientoSTOCuponPagoAction getMantSTOCuponPago() {
		return mantSTOCuponPago;
	}

	public void setMantSTOCuponPago(MantenimientoSTOCuponPagoAction mantSTOCuponPago) {
		this.mantSTOCuponPago = mantSTOCuponPago;
	}

	public MantenimientoSTOCartaInvitacionFlexipagoAction getMantSTOCartaInvitacionFlexipago() {
		return mantSTOCartaInvitacionFlexipago;
	}

	public void setMantSTOCartaInvitacionFlexipago(
			MantenimientoSTOCartaInvitacionFlexipagoAction mantSTOCartaInvitacionFlexipago) {
		this.mantSTOCartaInvitacionFlexipago = mantSTOCartaInvitacionFlexipago;
	}

	public MantenimientoSTOIngresoMetasAction getMantSTOIngresoMetas() {
		return mantSTOIngresoMetas;
	}

	public void setMantSTOIngresoMetas(
			MantenimientoSTOIngresoMetasAction mantSTOIngresoMetas) {
		this.mantSTOIngresoMetas = mantSTOIngresoMetas;
	}

	public MantenimientoSTOOrdenTransporteAction getMantSTOOrdenTransporte() {
		return mantSTOOrdenTransporte;
	}

	public void setMantSTOOrdenTransporte(
			MantenimientoSTOOrdenTransporteAction mantSTOOrdenTransporte) {
		this.mantSTOOrdenTransporte = mantSTOOrdenTransporte;
	}

	public MantenimientoSTOContratoEjecutivaAction getMantSTOContratoEjecutiva() {
		return mantSTOContratoEjecutiva;
	}

	public void setMantSTOContratoEjecutiva(
			MantenimientoSTOContratoEjecutivaAction mantSTOContratoEjecutiva) {
		this.mantSTOContratoEjecutiva = mantSTOContratoEjecutiva;
	}

	public List getNuevaLista() {
		return nuevaLista;
	}

	public void setNuevaLista(List nuevaLista) {
		this.nuevaLista = nuevaLista;
	}

	public String getUrlOCR() {
		return urlOCR;
	}

	public void setUrlOCR(String urlOCR) {
		this.urlOCR = urlOCR;
	}

	public String getPaisOCR() {
		return paisOCR;
	}

	public void setPaisOCR(String paisOCR) {
		this.paisOCR = paisOCR;
	}

	public String getMarcaOCR() {
		return marcaOCR;
	}

	public void setMarcaOCR(String marcaOCR) {
		this.marcaOCR = marcaOCR;
	}

	public String getTipoDocumentoSTO() {
		return tipoDocumentoSTO;
	}

	public void setTipoDocumentoSTO(String tipoDocumentoSTO) {
		this.tipoDocumentoSTO = tipoDocumentoSTO;
	}

	public boolean isMostrarSeleccion() {
		return mostrarSeleccion;
	}

	public void setMostrarSeleccion(boolean mostrarSeleccion) {
		this.mostrarSeleccion = mostrarSeleccion;
	}

	public boolean isMostrarAccion() {
		return mostrarAccion;
	}

	public void setMostrarAccion(boolean mostrarAccion) {
		this.mostrarAccion = mostrarAccion;
	}

	public DataTableModel getNuevaListaModel() {
		return nuevaListaModel;
	}

	public void setNuevaListaModel(DataTableModel nuevaListaModel) {
		this.nuevaListaModel = nuevaListaModel;
	}

	public Object[] getRegistroSeleccionadoObject() {
		return registroSeleccionadoObject;
	}

	public void setRegistroSeleccionadoObject(Object[] registroSeleccionadoObject) {
		this.registroSeleccionadoObject = registroSeleccionadoObject;
	}

	public boolean isPopupModificar() {
		return isPopupModificar;
	}

	public void setPopupModificar(boolean isPopupModificar) {
		this.isPopupModificar = isPopupModificar;
	}
	
	
	

}
