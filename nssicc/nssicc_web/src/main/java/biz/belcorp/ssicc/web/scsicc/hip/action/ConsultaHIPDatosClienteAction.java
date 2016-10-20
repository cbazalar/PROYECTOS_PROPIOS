/**
 * 
 */
package biz.belcorp.ssicc.web.scsicc.hip.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Idioma;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.scsicc.model.ConsultaHIPDatosCliente;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.scsicc.ConsultaHIPDatosClienteService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.cobranzas.ConsultaCOBTelecobroService;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.pej.MantenimientoPEJProgramaEjecutivasService;
import biz.belcorp.ssicc.service.spusicc.sto.MantenimientoSTOBloqueoControlService;
import biz.belcorp.ssicc.service.util.ImagenPDFUtil;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.hip.form.ConsultaHIPDatosClienteForm;
import biz.belcorp.ssicc.web.spusicc.pedidos.action.MantenimientoOCRCapturaPedidosAction;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.MantenimientoOCRCapturaPedidosForm;
import biz.belcorp.ssicc.web.spusicc.reclamos.action.MantenimientoRECDigitacionCDRAjaxAction;
import biz.belcorp.ssicc.web.spusicc.reclamos.form.MantenimientoRECDigitacionCDRAjaxForm;

/**
 *  @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza </a> 
 */
@ManagedBean
@SessionScoped
public class ConsultaHIPDatosClienteAction extends BaseMantenimientoSearchAbstractAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -474012632610622594L;
	public static final int HIP_FILAS_ULTIMOS_MOVIMIENTOS = 4; //Filas a Mostrarse
	
	private boolean mostrarPopupHIPCliente = false;
	private static final String POPUP_HIPCLIENTE = "HIPCLIENTE";
			
	private List hipTipoClienteList = new ArrayList();
	private List hipSubTipoClienteList = new ArrayList();
	private List hipTipoClasificacionList = new ArrayList();
	private List hipClasificacionList = new ArrayList();
	private List hipMediosContactosList = new ArrayList();
	private List hipEtapasCobroList = new ArrayList();
	
	private List hipUnidadesAdministrativasList = new ArrayList();
	private List hipOpcionesList = new ArrayList();
	
	private List hipReclamosCabeceraList = new ArrayList();
	private List hipCuentaCorrientesMovimientosList = new ArrayList();
	private List hipPedidosConsultoraList = new ArrayList();
	private List hipConcursosBolsaFaltantesList = new ArrayList();
	private List hipVinculosReferenciasList = new ArrayList();
	
	private List hipSolicitudesPolizaList = new ArrayList();
	private List hipHistoricoCargosPolizaList = new ArrayList();
	
	private List lstParametros;
	
	private String mostrarDigitacionSimplificada;
	private String indicadorTipo;
	private String nroColumnas;
	
	private boolean mostrarPDFEscaneoImagenes;
	private boolean valPara;	
	private boolean viewResults;
	private boolean mostrarReporteImagenesSC;
				
	//Datos generales de Hiperconsulta
	private ConsultaHIPDatosCliente hipDtoDatosCliente;	
	private String opcionConsulta;
	private String pageOpcionConsulta;
	
	private String outfileMedia = "";
	protected boolean viewReporteMedia;
	
	@ManagedProperty(value="#{busquedaHIPClientePOPUPSearchAction}")
	private BusquedaHIPClientePOPUPSearchAction busquedaHIPClientePOPUPSearchAction;
	
	@ManagedProperty(value="#{mantenimientoOCRCapturaPedidosAction}")
	private MantenimientoOCRCapturaPedidosAction mantenimientoOCRCapturaPedidosAction;
	
	@ManagedProperty(value="#{mantenimientoRECDigitacionCDRAjaxAction}")
	private MantenimientoRECDigitacionCDRAjaxAction mantenimientoRECDigitacionCDRAjaxAction;
	
	private String paginaPadre;
	private String indicadorDatosPoliza;
	private String indicadorSituacionFlx;
		
	private int numeroColumnasDatosGenerales;
	private int numeroColumnasDatosDespacho;
	private int numeroColumnasDatosDomicilio;
	
	private boolean indicadorLider;
	private boolean indicadorProgramaEjecutivasActivo;
	
	private List cabeceraConsultoraCastigadaList;
	private List detalleConsultoraCastigadaList;
	private List cuentaCorrienteConsultoraCastigadaList;
	
	private String codigoCastigada;
	private String cedulaCastigada;
	private String nombresApellidosCastigada;
	private String regionCastigada;
	private String zonaCastigada;
	private boolean rellenarCeroConsultoraSoloconEnter = false;
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {		
		ConsultaHIPDatosClienteForm consultaHIPDatosClienteForm = new ConsultaHIPDatosClienteForm();
		return consultaHIPDatosClienteForm;
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setInvocarPopup(java.lang.String)
	 */
	@Override
	protected void setInvocarPopup(String accion) {
		if (accion.equals(this.POPUP_HIPCLIENTE)){ 
			this.mostrarPopupHIPCliente = true;
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setAceptarPopup(javax.faces.event.ActionEvent, java.lang.String)
	 */
	@Override
	protected void setAceptarPopup(ActionEvent event, String accion) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setAceptarPopupHipCliente' method");
		}
		this.mostrarProcesoBatch = true;
		this.mostrarPopupHIPCliente = false;
		if (accion.equals(this.POPUP_HIPCLIENTE)) {
			this.busquedaHIPClientePOPUPSearchAction.verificarRegistro(event);
			if (this.busquedaHIPClientePOPUPSearchAction.isSeleccionoRegistro()) {	
				
				Map clienteHipMap = (Map)this.busquedaHIPClientePOPUPSearchAction.getBeanRegistroSeleccionado(); 
				ConsultaHIPDatosClienteForm busquedaForm = (ConsultaHIPDatosClienteForm)this.formBusqueda;				
				busquedaForm.setCodigoClienteBuscar((String)clienteHipMap.get("codigoCliente"));
				busquedaForm.setNumeroDocIdentidadBuscar((String)clienteHipMap.get("numeroDocumento"));
				this.busquedaHIPClientePOPUPSearchAction.setBeanRegistroSeleccionado(null);
				this.formBusqueda =  busquedaForm;
	            this.getRequestContext().execute("PrimeFaces.focus('codigoClienteBuscar')");
			}
		}	
		if (log.isDebugEnabled()) {
			log.debug("Finish 'PopupHipCliente' method");
		}
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setValidarFind()
	 */
	@Override
	public String setValidarFind(){
		String error = "";
		ConsultaHIPDatosClienteForm searchForm = (ConsultaHIPDatosClienteForm) this.formBusqueda;
		String codigoCliente = searchForm.getCodigoClienteBuscar();
		String numeroDocumento = searchForm.getNumeroDocIdentidadBuscar();
		if (StringUtils.isBlank(codigoCliente) && StringUtils.isBlank(numeroDocumento)) {
			error = this.getResourceMessage("consultaHIPDatosClienteForm.errorCriterioBusqueda");
			
		}
		return error;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setSalirPopup()
	 */
	@Override
	protected void setSalirPopup() {
		this.mostrarProcesoBatch = true;
		this.mostrarPopupHIPCliente = false;
		this.busquedaHIPClientePOPUPSearchAction.setBeanRegistroSeleccionado(null);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		
		if (log.isDebugEnabled()) {
            log.debug("Entering 'ConsultaHIPDatosClienteAction - setViewAtributes' method");
        }
		
		this.mostrarBotonNuevo = false;
		this.mostrarBotonModificar = false;
		this.mostrarBotonEliminar = false;
		this.mostrarBotonConsultar = false;	
		this.mostrarCabeceraFija = true;
		this.mostrarPDFEscaneoImagenes = false;
		this.activarHotkeyEstandar = true;
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService)this.getBeanService("spusicc.mantenimientoMAEClienteService");				
		ReporteService reporteService = (ReporteService)this.getBeanService("scsicc.reporteService");
				
		ConsultaHIPDatosClienteForm searchForm = (ConsultaHIPDatosClienteForm) this.formBusqueda;
		
		//Recuperamos el pais logueado
		searchForm.setCodigoPais(this.mPantallaPrincipalBean.getCountryCode());
		
		//Recuperamos el idioma		
		Idioma idioma = this.mPantallaPrincipalBean.getCurrentIdioma();
		Map parameterMap = new HashMap();
		parameterMap.put("codigoIdiomaIso", idioma.getCodigoSiCC());
		parameterMap.put("codigoIdioma", idioma.getCodigoISO());
		String oidIdiomaIso = reporteService.getOidString("getOidIdiomaByCodigoIdiomaIso", parameterMap);
		searchForm.setOidIdioma(oidIdiomaIso);
		
		//limpiando los datos de la pantalla
		limpiar(searchForm);
		
		//Si el pais es Colombia, se muestra el campo Barrio
		if(searchForm.getCodigoPais().substring(0,2).equals(Constants.HIP_PAIS_COLOMBIA)) {
			searchForm.setMostrarBarrio(true);
		} else {
			searchForm.setMostrarBarrio(false);
		}		
		
		/* INI SA PER-SiCC-2012-0545 */
		String mostrarCiudad = clienteService.getValorModuloxPaisTipoValidacion(searchForm.getCodigoPais(), Constants.MAE_VALID_CIUDAD);
		String mostrarVillaPoblacion = clienteService.getValorModuloxPaisTipoValidacion(searchForm.getCodigoPais(), Constants.MAE_VALID_VILLA);
		
		if(mostrarCiudad != null) 
			searchForm.setMostrarCiudad(true);
		else
			searchForm.setMostrarCiudad(false);
		
		if(mostrarVillaPoblacion != null) 
			searchForm.setMostrarVillaPoblacion(true);
		else
			searchForm.setMostrarVillaPoblacion(false);
		/* FIN SA PER-SiCC-2012-0545 */
		
		Map criteria = new HashMap();
        criteria.put("codigoPais", searchForm.getCodigoPais());
        
        //verificamos el indicador de BasParamPais
        ConsultaHIPDatosClienteService service = (ConsultaHIPDatosClienteService) getBean("scsicc.consultaHIPDatosClienteService");
        searchForm.setIndicadorBasparampais(service.getIndicadorBasparampais(criteria));
        
		//longitud de codigo de cliente para el pais
        searchForm.setLongitudCodigoCliente(clienteService.getLongitudCodigoCliente(criteria));
		
        String indicadorCompletarCerosNumDocumento  = clienteService.getValorModuloxPaisTipoValidacion(this.mPantallaPrincipalBean.getCurrentCountry().getCodigo(), Constants.HIP_VALID_COMPLETA_CEROS_DOCUMENTO_IDENTIDAD);
		
		if(StringUtils.isEmpty(indicadorCompletarCerosNumDocumento)) {
			searchForm.setIndicadorCompletarCerosNumDocumento(true);
			//longitud de número de documento de identidad para el paÍs
			searchForm.setLongitudNumeroDocIdentidad(clienteService.getLongitudNumeroDocIdentidad(criteria));
		}
		else{
			searchForm.setIndicadorCompletarCerosNumDocumento(false);
		}
		
        
		//Obtenes valor del parametro para la pantalla de Digitacion Simplificada
		GenericoService genericoService1 = (GenericoService)this.getBeanService("genericoService");				
		
		ParametroPais parametroPais1 = new ParametroPais();
		parametroPais1.setCodigoPais(this.mPantallaPrincipalBean.getCountryCode());
		parametroPais1.setCodigoSistema(Constants.HIP_CODIGO_SISTEMA);
		parametroPais1.setCodigoParametro(Constants.HIP_CODIGO_PARAMETRO_DIGI_SIMP);
		parametroPais1.setNombreParametro(Constants.HIP_NOMBRE_PARAMETRO_MOSTRAR_DIGI_SIMP);
		
		List lstParametros1 = genericoService1.getParametrosPais(parametroPais1);
		
		String mostrarDigiSimp = Constants.SI;
		
		if(lstParametros1 != null && lstParametros1.size() > 0){			
			ParametroPais ps = (ParametroPais)lstParametros1.get(0);
			mostrarDigiSimp = ps.getValorParametro();
		}
		
		lstParametros1 = new ArrayList();
		parametroPais1.setCodigoParametro(Constants.HIP_CODIGO_PARAMETRO_RELLENAR_SOLO_ENTER);
		parametroPais1.setNombreParametro(Constants.HIP_NOMBRE_PARAMETRO_RELLENAR_SOLO_ENTER);
        lstParametros1 = genericoService1.getParametrosPais(parametroPais1);
		
		String rellenarConsultoraSoloEnter = Constants.NO;
		if(lstParametros1 != null && lstParametros1.size() > 0){			
			ParametroPais ps = (ParametroPais)lstParametros1.get(0);
			rellenarConsultoraSoloEnter = ps.getValorParametro();
		}		
		
		//Datos Poliza - PER-SiCC-2015-0177
		ParametroPais parametroPaisPoliza = new ParametroPais();
		parametroPaisPoliza.setCodigoPais(this.mPantallaPrincipalBean.getCountryCode());
		//INI PER-SiCC-2015-0368
		parametroPaisPoliza.setCodigoSistema(Constants.HIP_CODIGO_SISTEMA);
		//FIN PER-SiCC-2015-0368
		parametroPaisPoliza.setNombreParametro(Constants.HIP_NOMBRE_PARAMETRO_FAMILIA_PROTEGIDA);
		parametroPaisPoliza.setIndicadorActivo(Constants.ESTADO_ACTIVO);
		List parametrosPoliza = genericoService1.getParametrosPais(parametroPaisPoliza);
				
		String indicadorDatosPoliza = Constants.NUMERO_CERO;
		
		if(parametrosPoliza != null && parametrosPoliza.size() > 0){
			ParametroPais p = (ParametroPais)parametrosPoliza.get(0);
			indicadorDatosPoliza = p.getValorParametro();
		}
		this.setIndicadorDatosPoliza(indicadorDatosPoliza);
		//Datos Poliza - PER-SiCC-2015-0177
		
		this.setMostrarDigitacionSimplificada(mostrarDigiSimp);
		this.setIndicadorTipo(Constants.NUMERO_CERO);
		
		if (StringUtils.equals(Constants.SI, rellenarConsultoraSoloEnter))
			this.rellenarCeroConsultoraSoloconEnter = true;
		this.setNroColumnas("0"); 
		this.viewReporteMedia = false;
		
		if (log.isDebugEnabled()) {
            log.debug("End 'ConsultaHIPDatosClienteAction - setViewAtributes' method");
            log.debug("Valor de Service: " + clienteService);
        }
		
	}

	/**
	 * 
	 */
	private void verificarLider()
	{
		this.indicadorLider = false;

		ConsultaHIPDatosClienteService consultaHIPDatosClienteService = (ConsultaHIPDatosClienteService) getBean("scsicc.consultaHIPDatosClienteService");		
		MantenimientoOCRPedidoControlFacturacionService mantenimientoOCRPedidoControlFacturacionService = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService" );
		ConsultaHIPDatosClienteForm searchForm = (ConsultaHIPDatosClienteForm) this.formBusqueda;
		
		String campanyaProceso = null;
		Map criteria = new HashMap();			
		criteria.put("codigoPais", this.mPantallaPrincipalBean.getCurrentCountry().getCodigo()); 
		criteria.put("estadoCampanha", Constants.NUMERO_CERO); 
		criteria.put("indicadorActiva", Constants.NUMERO_UNO);
		
		List lista = mantenimientoOCRPedidoControlFacturacionService.getCampanhasActivasByCriteria(criteria); 
		if (lista != null && lista.size() > 0) { 
			campanyaProceso = (String) lista.get(0); 
		}
		
		if(StringUtils.isNotBlank(campanyaProceso))
		{
			String codigoPrograma = consultaHIPDatosClienteService.getCodigoProgramaLET(campanyaProceso);
			
			criteria.put("campanyaProceso", campanyaProceso);
			criteria.put("codigoPrograma", codigoPrograma);
			criteria.put("codigoConsultora", searchForm.getCodigoCliente());
			
			Map datos = consultaHIPDatosClienteService.getDatosSociaEmpresaria(criteria);
		
			if(datos != null)
			{
				this.indicadorLider = true;
			}
		}
	}
	
	/**
	 * 
	 */
	private void verificarProgramaEjecutivasActivo()
	{

		MantenimientoPEJProgramaEjecutivasService servicePEJ = (MantenimientoPEJProgramaEjecutivasService)this.getBean("spusicc.mantenimientoPEJProgramaEjecutivasService");
		
		Map mapProgramaActivo = new HashMap();
		mapProgramaActivo.put("codigoPais", this.mPantallaPrincipalBean.getCurrentCountry().getCodigo());
		mapProgramaActivo.put("estado", "1");
		List listProgramaActivo = servicePEJ.getProgramasByCriteria(mapProgramaActivo);
		
		this.indicadorProgramaEjecutivasActivo = false;
		if(listProgramaActivo.size() == 1)
			this.indicadorProgramaEjecutivasActivo = true;	
	}
	
	/**
	 * 
	 * @param actionEvent
	 */
	public void buscaClientexCodigo(ActionEvent actionEvent) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ConsultaHIPDatosClienteAction - buscaClientexCodigo' method");
		}
		
		ConsultaHIPDatosClienteForm searchForm = (ConsultaHIPDatosClienteForm) this.formBusqueda;			
		ConsultaHIPDatosClienteService service = (ConsultaHIPDatosClienteService)this.getBeanService("scsicc.consultaHIPDatosClienteService");
		ReporteService reporteService = (ReporteService)this.getBeanService("scsicc.reporteService");
		
		//Recuperamos el idioma		
		Idioma idioma = this.mPantallaPrincipalBean.getCurrentIdioma();
		Map parameterMap = new HashMap();
		parameterMap.put("codigoIdiomaIso", idioma.getCodigoSiCC());
		parameterMap.put("codigoIdioma", idioma.getCodigoISO());
		String oidIdiomaIso = reporteService.getOidString("getOidIdiomaByCodigoIdiomaIso", parameterMap);
		searchForm.setOidIdioma(oidIdiomaIso);
				
		Map param = new HashMap();
		
		param.put("oidIdioma", oidIdiomaIso);
		param.put("codigoPais", this.mPantallaPrincipalBean.getCountryCode());
		param.put("codigoCliente", searchForm.getCodigoClienteBuscar());
		
		List resultado = service.getClientesByCriteria(param);
		log.debug("Pintando el tamaño de la lista " + resultado.size());
		
		if(resultado.size() > 0){
			for(int i=0; i<resultado.size(); i++) {
				Map mapResul = (Map)resultado.get(i);
				searchForm.setNumeroDocIdentidadBuscar((String)mapResul.get("numeroDocumento"));
			}
		}else{
			searchForm.setNumeroDocIdentidadBuscar("");
		}
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		 if (log.isDebugEnabled()) {
	            log.debug("Entering 'ConsultaHIPDatosClienteAction - setFindAttributes' method");
	     }
		 ConsultaHIPDatosClienteForm searchForm = (ConsultaHIPDatosClienteForm) this.formBusqueda;
		 ConsultaHIPDatosClienteService service = (ConsultaHIPDatosClienteService) this.getBeanService("scsicc.consultaHIPDatosClienteService");
		 GenericoService genericoService1 = (GenericoService)this.getBeanService("genericoService");			
		//limpiando los datos de la pantalla
		 this.limpiar(searchForm);
		
		
		/* obteniendo valores */
		Map criterios = new HashMap();
		this.mostrarPDFEscaneoImagenes = false;
		criterios.put("codigoPais", searchForm.getCodigoPais());
		criterios.put("codigoCliente", searchForm.getCodigoClienteBuscar());
		criterios.put("oidIdioma", searchForm.getOidIdioma());
		criterios.put("numeroDocIdentidad", searchForm.getNumeroDocIdentidadBuscar());
		
		//obtenemos la cantidad de clientes con el codigo de cliente y numero de documento como filtro
		List resultado = service.getClientesByCriteria(criterios);
		boolean esClienteUnico = true;
		
		if(resultado.size() > 1) {
			Map mapCliente = (Map)resultado.get(0);
			String codigoCliente = (String)mapCliente.get("codigoCliente");
			
			for(int i=1;i<resultado.size();i++) {
				mapCliente = (Map)resultado.get(i);
				String codigoClienteAux = (String)mapCliente.get("codigoCliente");
				
				if(!codigoCliente.equalsIgnoreCase(codigoClienteAux)) {
					esClienteUnico = false;
					break;
				}
			}
			
			if(!esClienteUnico) {
				searchForm.setMostrarPantallaBusqueda(true);
				return null;
			}	
		} else {
			searchForm.setMostrarPantallaBusqueda(false);
		}
		 
		/* Obteniendo los datos generales del Cliente */
		Map mapDatosGenerales = service.getDatosGenerales(criterios);
		
		List mockList = new ArrayList();
		
		if(mapDatosGenerales != null) {
			
			mockList.add(new LabelValue());
			this.setMostrarListaBusqueda(false);
			
			this.setViewResults(true);
			
			searchForm.setOidCliente(reemplazarNulo(mapDatosGenerales.get("oidCliente")));
			searchForm.setCodigoCliente(reemplazarNulo(mapDatosGenerales.get("codigoCliente")));
			
			String nombreCompleto = reemplazarNulo(mapDatosGenerales.get("nombre1")) + " " +
									reemplazarNulo(mapDatosGenerales.get("nombre2")) + " " +
									reemplazarNulo(mapDatosGenerales.get("apellido1")) + " " + 
									reemplazarNulo(mapDatosGenerales.get("apellido2"));
			
			searchForm.setNombreCompleto(nombreCompleto);
			searchForm.setNumeroDocIdentidad(reemplazarNulo(mapDatosGenerales.get("numeroDocIdentidad")));
			searchForm.setTipoDocIdentidad(reemplazarNulo(mapDatosGenerales.get("tipoDocIdentidad")));
			searchForm.setEstatus(reemplazarNulo(mapDatosGenerales.get("estatus")));
			searchForm.setTelefono(reemplazarNulo(mapDatosGenerales.get("telefono")));
			searchForm.setEmail(reemplazarNulo(mapDatosGenerales.get("email")));
			searchForm.setFechaIngreso(reemplazarNulo(mapDatosGenerales.get("fechaIngreso")));
			searchForm.setPeriodoIngreso(reemplazarNulo(mapDatosGenerales.get("periodoIngreso")));
			searchForm.setFechaNacimiento(reemplazarNulo(mapDatosGenerales.get("fechaNacimiento")));
			searchForm.setCodigoDigitoControl(reemplazarNulo(mapDatosGenerales.get("codigoDigitoControl")));
			this.setNroColumnas((searchForm.getCodigoDigitoControl()!=null)?"8":"7");			
			searchForm.setIndicadorActivo(reemplazarNulo(mapDatosGenerales.get("indicadorActivo")));
			searchForm.setNivelRiesgo(reemplazarNulo(mapDatosGenerales.get("nivelRiesgo")));
			//ini sb numero contaro
			searchForm.setNumeroContrato(reemplazarNulo(mapDatosGenerales.get("numeroContrato")));
			//fin sb numero de contrato
			criterios.put("oidCliente", searchForm.getOidCliente());
			criterios.put("oidNivelRiesgo", mapDatosGenerales.get("oidNivelRiesgo"));
			criterios.put("codigoCliente", mapDatosGenerales.get("codigoCliente"));
			//			
			ParametroPais parametroPaisAdicional = new ParametroPais();
			parametroPaisAdicional.setCodigoPais(this.mPantallaPrincipalBean.getCountryCode());
			parametroPaisAdicional.setCodigoSistema("MAE");
			parametroPaisAdicional.setNombreParametro("indCamposAdicionales");
			parametroPaisAdicional.setIndicadorActivo(Constants.ESTADO_ACTIVO);
			List parametrosAdicionales= genericoService1.getParametrosPais(parametroPaisAdicional);
			ParametroPais beanAdicional=(ParametroPais)parametrosAdicionales.get(0);
			String valor=beanAdicional.getValorParametro();
			searchForm.setIndCamposAdicion(beanAdicional.getValorParametro());
			if(StringUtils.equals(searchForm.getIndCamposAdicion(), Constants.ESTADO_ACTIVO)){
				String tipoPersona=service.getTipoPersonaxOidCliente(criterios);
				String origenIngresos=service.getOrigenIngresosxOidCliente(criterios);
				searchForm.setTipoPersona(tipoPersona);
				searchForm.setOrigenIngresos(origenIngresos);
			}
			
			
			//construimos el objeto que estara en sesion, disponible para las demas pantallas
			ConsultaHIPDatosCliente dtoDatosCliente = new ConsultaHIPDatosCliente();
			dtoDatosCliente.setCodigoPais(searchForm.getCodigoPais());
			dtoDatosCliente.setOidIdioma(searchForm.getOidIdioma());
			dtoDatosCliente.setOidCliente(searchForm.getOidCliente());
			dtoDatosCliente.setCodigoCliente(searchForm.getCodigoCliente());
			dtoDatosCliente.setNombreCompleto(searchForm.getNombreCompleto());
			dtoDatosCliente.setTipoDocIdentidad(searchForm.getTipoDocIdentidad());
			dtoDatosCliente.setNumeroDocIdentidad(searchForm.getNumeroDocIdentidad());
			dtoDatosCliente.setStatus(searchForm.getEstatus());
			dtoDatosCliente.setCampanaIngreso(searchForm.getPeriodoIngreso());
			dtoDatosCliente.setTelefonoCasa(searchForm.getTelefono());
			dtoDatosCliente.setTelefonoCelular(reemplazarNulo(mapDatosGenerales.get("celular")));
			dtoDatosCliente.setMail(searchForm.getEmail());
			dtoDatosCliente.setFechaNacimiento(searchForm.getFechaNacimiento());
			dtoDatosCliente.setApellido1(reemplazarNulo(mapDatosGenerales.get("apellido1")));
			dtoDatosCliente.setApellido2(reemplazarNulo(mapDatosGenerales.get("apellido2")));
			dtoDatosCliente.setNombre1(reemplazarNulo(mapDatosGenerales.get("nombre1")));
			dtoDatosCliente.setNombre2(reemplazarNulo(mapDatosGenerales.get("nombre2")));
			dtoDatosCliente.setOidTipoDocIdentidad(reemplazarNulo(mapDatosGenerales.get("oidTipoDocIdentidad")));
			dtoDatosCliente.setIndicadorActivo(searchForm.getIndicadorActivo());
			
			//obtenemos el oidPais
			MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService)this.getBeanService("spusicc.mantenimientoMAEClienteService");
    		criterios.put("codigoPais", this.mPantallaPrincipalBean.getCountryCode());
    		dtoDatosCliente.setOidPais(clienteService.getOidPais(criterios));
    		
    		//Obtenemos la direccion de domicilio de la consultora
			Map mapDireccionDomicilio = service.getDireccionDomicilio(criterios);
			String direccion="";
			if(mapDireccionDomicilio != null) {
				if(StringUtils.equals(searchForm.getIndCamposAdicion(), Constants.ESTADO_ACTIVO)){
					direccion = reemplazarNulo(mapDireccionDomicilio.get("barrio")) + " " +
							   reemplazarNulo(mapDireccionDomicilio.get("manzana")) + " " +
							   reemplazarNulo(mapDireccionDomicilio.get("etapaConj")) + " " +
							   reemplazarNulo(mapDireccionDomicilio.get("callePrincipal")) + " " +
							   reemplazarNulo(mapDireccionDomicilio.get("numeroAuxiliar")) + " " + 
							   reemplazarNulo(mapDireccionDomicilio.get("calleSecundaria")) + " " +
							   reemplazarNulo(mapDireccionDomicilio.get("observaciones")) + " Territorio: " +
							   reemplazarNulo(mapDireccionDomicilio.get("codigoTerritorio"));
				
				}else{
					direccion = reemplazarNulo(mapDireccionDomicilio.get("tipoVia")) + " " +
								   reemplazarNulo(mapDireccionDomicilio.get("nombreVia")) + " " +
								   reemplazarNulo(mapDireccionDomicilio.get("numero")) + " " + 
								   reemplazarNulo(mapDireccionDomicilio.get("observaciones")) + " Territorio: " +
								   reemplazarNulo(mapDireccionDomicilio.get("codigoTerritorio"));
				}
				
				searchForm.setDireccionDomicilio(direccion);
				searchForm.setUbigeoDomicilio(reemplazarNulo(mapDireccionDomicilio.get("ubigeo")));
				searchForm.setBarrioDomicilio(reemplazarNulo(mapDireccionDomicilio.get("barrio")));
				searchForm.setFechaUltimaActualizacion(reemplazarNulo(mapDireccionDomicilio.get("fechaUltimaActualizacion")));
				
				/* INI SA PER-SiCC-2015-0100 */
				searchForm.setCodigoPostal(reemplazarNulo(mapDireccionDomicilio.get("codigoPostal")));
				/* FIN SA PER-SiCC-2015-0100 */
								
				/* INI SA PER-SiCC-2012-0545 */
				String ciudadDomicilio = reemplazarNulo(mapDireccionDomicilio.get("ciudad"));
				if(searchForm.isMostrarCiudad() &&  ciudadDomicilio.length() > 0)
					searchForm.setUbigeoDomicilio(searchForm.getUbigeoDomicilio() + "/" + ciudadDomicilio);
				
				String villaPoblacionDomicilio = reemplazarNulo(mapDireccionDomicilio.get("villaPoblacion"));
				if(searchForm.isMostrarVillaPoblacion() &&  villaPoblacionDomicilio.length() > 0)
					searchForm.setUbigeoDomicilio(searchForm.getUbigeoDomicilio() + "/" + villaPoblacionDomicilio);
				/* FIN SA PER-SiCC-2012-0545 */
			}
			
			//Obtenemos el Origen de la consultora
			String origenConsu = service.getOrigenxCodCliente(criterios);
			searchForm.setOrigenCliente(origenConsu);
			
			//Obtenemos la direccion de despacho de la consultora
			Map mapDireccionDespacho = service.getDireccionDespacho(criterios);
			String direccionDes="";
			if(mapDireccionDespacho != null) {
				if(StringUtils.equals(searchForm.getIndCamposAdicion(), Constants.ESTADO_ACTIVO)){
					direccionDes = reemplazarNulo(mapDireccionDespacho.get("barrio")) + " " +
								   reemplazarNulo(mapDireccionDespacho.get("manzana")) + " " +
								   reemplazarNulo(mapDireccionDespacho.get("etapaConj")) + " " +
								   reemplazarNulo(mapDireccionDespacho.get("callePrincipal")) + " " +
								   reemplazarNulo(mapDireccionDespacho.get("numeroAuxiliar")) + " " + 
								   reemplazarNulo(mapDireccionDespacho.get("calleSecundaria")) + " " +
								   reemplazarNulo(mapDireccionDespacho.get("observaciones")) + " Territorio: " +
								   reemplazarNulo(mapDireccionDespacho.get("codigoTerritorio"));
					
				}else{
					direccionDes = reemplazarNulo(mapDireccionDespacho.get("tipoVia")) + " " +
									   reemplazarNulo(mapDireccionDespacho.get("nombreVia")) + " " +
									   reemplazarNulo(mapDireccionDespacho.get("numero")) + " " + 
									   reemplazarNulo(mapDireccionDespacho.get("observaciones")) + " Territorio: " +
									   reemplazarNulo(mapDireccionDespacho.get("codigoTerritorio"));
				}
				
				searchForm.setDireccionDespacho(direccionDes);
				searchForm.setUbigeoDespacho(reemplazarNulo(mapDireccionDespacho.get("ubigeo")));
				searchForm.setBarrioDespacho(reemplazarNulo(mapDireccionDespacho.get("barrio")));
				
				/* INI SA PER-SiCC-2012-0545 */
				String ciudadDespacho = reemplazarNulo(mapDireccionDespacho.get("ciudad"));
				if(searchForm.isMostrarCiudad() &&  ciudadDespacho.length() > 0)
					searchForm.setUbigeoDespacho(searchForm.getUbigeoDespacho() + "/" + ciudadDespacho);
				
				String villaPoblacionDespacho = reemplazarNulo(mapDireccionDespacho.get("villaPoblacion"));
				if(searchForm.isMostrarVillaPoblacion() &&  villaPoblacionDespacho.length() > 0)
					searchForm.setUbigeoDespacho(searchForm.getUbigeoDespacho() + "/" + villaPoblacionDespacho);
				/* FIN SA PER-SiCC-2012-0545 */
			}
			
			//obtenemos el motivo del bloqueo de la consultora
			String bloqueo = reemplazarNulo(service.getMotivoBloqueo(criterios));
			if(!bloqueo.equals("")) {
				StringTokenizer st = new StringTokenizer(bloqueo, "__");
				dtoDatosCliente.setCodigoBloqueo(st.nextToken());
				dtoDatosCliente.setBloqueo(st.nextToken());
				searchForm.setBloqueo(dtoDatosCliente.getBloqueo());
			} else
				searchForm.setBloqueo("");
			
			//obtenemos los impedimentis
			searchForm.setImpedidaPasarCdr(StringUtils.isNotEmpty(service.getImpedidaPasarCdr(criterios))?Constants.NUMERO_UNO:Constants.NUMERO_CERO);
			searchForm.setImpedidaPasarPedido(StringUtils.isNotEmpty(service.getImpedidaPasarPedido(criterios))?Constants.NUMERO_UNO:Constants.NUMERO_CERO);
			
			//obtenemos la fecha castigada
			searchForm.setFechaCastigada(reemplazarNulo(service.getFechaCastigada(criterios)));
			
			//Obtenemos los datos del ultimo pedido de la Consultora
			Map mapUltimoPedido = service.getUltimoPedido(criterios);
			if(mapUltimoPedido != null) {
				searchForm.setPeriodoUltimoPedido(reemplazarNulo(mapUltimoPedido.get("periodoUltimoPedido")));
				searchForm.setBoletaUltimoPedido(reemplazarNulo(mapUltimoPedido.get("boletaUltimoPedido")));
				searchForm.setImporteUltimoPedido(reemplazarNulo(mapUltimoPedido.get("importeUltimoPedido")));
				
				dtoDatosCliente.setPeriodoUltimoPedido(reemplazarNulo(mapUltimoPedido.get("periodoUltimoPedido")));
			}
			
			//obtenemos el saldo unico de la consultora
			searchForm.setSaldoUnicoUltimoPedido(reemplazarNulo(service.getSaldoUnico(criterios)));
			dtoDatosCliente.setSaldoUnico(searchForm.getSaldoUnicoUltimoPedido());
			
			//Obtenemos las etapas de cobro de la consultora
			List listEtapasCobro = service.getEtapasCobro(criterios);
			this.setHipEtapasCobroList(listEtapasCobro);
			
			//obtenemos las unidades administrativas de la consultora
			List listUnidadesAdministrativas = service.getUnidadesAdministrativas(criterios);
			this.setHipUnidadesAdministrativasList(listUnidadesAdministrativas);
			
			if(listUnidadesAdministrativas!=null && listUnidadesAdministrativas.size()>0) {
				
				for(int i=0; i<listUnidadesAdministrativas.size(); i++) {
					Map mapUnidadAdministrativa = (Map)listUnidadesAdministrativas.get(i);
					
					String indicadorActividad = reemplazarNulo(mapUnidadAdministrativa.get("indicadorActividad"));

					if(indicadorActividad.equals("1")) {
						dtoDatosCliente.setCodigoMarca(reemplazarNulo(mapUnidadAdministrativa.get("codigoMarca")));
						dtoDatosCliente.setCodigoCanal(reemplazarNulo(mapUnidadAdministrativa.get("codigoCanal")));
						dtoDatosCliente.setCodigoSubGerencia(reemplazarNulo(mapUnidadAdministrativa.get("codigoSubGerencia")));
						dtoDatosCliente.setCodigoRegion(reemplazarNulo(mapUnidadAdministrativa.get("codigoRegion")));
						dtoDatosCliente.setCodigoZona(reemplazarNulo(mapUnidadAdministrativa.get("codigoZona")));
						dtoDatosCliente.setCodigoSeccion(reemplazarNulo(mapUnidadAdministrativa.get("codigoSeccion")));
						dtoDatosCliente.setCodigoTerritorio(reemplazarNulo(mapUnidadAdministrativa.get("codigoTerritorio")));
						
						dtoDatosCliente.setDescripcionRegion(reemplazarNulo(mapUnidadAdministrativa.get("descripcionRegion")));
						dtoDatosCliente.setDescripcionZona(reemplazarNulo(mapUnidadAdministrativa.get("descripcionZona")));
						dtoDatosCliente.setDescripcionSeccion(reemplazarNulo(mapUnidadAdministrativa.get("descripcionSeccion")));
						
						//////////////////////////////////////////////////////////
						GenericoService genericoService0 = (GenericoService) getBean("genericoService");
						ParametroPais parametroPais0 = new ParametroPais();
						
						parametroPais0.setCodigoPais(searchForm.getCodigoPais());
						parametroPais0.setCodigoSistema(Constants.HIP_CODIGO_SISTEMA);
						parametroPais0.setCodigoParametro(Constants.HIP_CODIGO_PARAMETRO_DATOS_BUZON);
						parametroPais0.setIndicadorActivo(Constants.ESTADO_ACTIVO);
						
						List lstParametrosConfig = genericoService0.getParametrosPais(parametroPais0);
						
						if(lstParametrosConfig != null && lstParametrosConfig.size() > 0){
							ParametroPais param = (ParametroPais)lstParametrosConfig.get(0);
							
							if(StringUtils.equals(param.getValorParametro(), Constants.ESTADO_ACTIVO)){
								
								this.setLstParametros(lstParametrosConfig);
								
								Map map = new HashMap();
								map.put("codigoRegion", MapUtils.getString(mapUnidadAdministrativa, "codigoRegion"));
								map.put("codigoZona", MapUtils.getInteger(mapUnidadAdministrativa, "codigoZona"));
								map.put("codigoTerritorio", MapUtils.getInteger(mapUnidadAdministrativa, "codigoTerritorio"));
								map.put("codigoSeccion", MapUtils.getString(mapUnidadAdministrativa, "codigoSeccion"));
								List lstCoberturaCentroAcopio = service.getListCoberturaCentroAcopio(map);
								
								if(lstCoberturaCentroAcopio != null && lstCoberturaCentroAcopio.size() > 0)
								{
									Map result =(Map)lstCoberturaCentroAcopio.get(0);

									searchForm.setCodigoBuzon(MapUtils.getString(result, "codigoBuzon", ""));
									searchForm.setDireccionBuzon(MapUtils.getString(result, "direccionBuzon", ""));
									searchForm.setTelefonoBuzon(MapUtils.getString(result, "telefonoBuzon", ""));
									searchForm.setCelularBuzon(MapUtils.getString(result, "celularBuzon", ""));
									searchForm.setDescripcionBuzon(MapUtils.getString(result, "descripcionBuzon", ""));
								}
							}
						}
						
						////////////////////////////////////////////////////////
						//OBTENER GERENTE ZONA
						dtoDatosCliente.setNombreCompletoGerenteZona("");
						dtoDatosCliente.setCelularGerenteZona("");
						searchForm.setGerenteZona(dtoDatosCliente.getNombreCompletoGerenteZona());
						searchForm.setCelularGerenteZona(dtoDatosCliente.getCelularGerenteZona());
						
						
						Map criteria = new HashMap();
						criteria.put("codZona", reemplazarNulo(mapUnidadAdministrativa.get("codigoZona")));
						criteria.put("codSeccion", reemplazarNulo(mapUnidadAdministrativa.get("codigoSeccion")));
						
						List listGerenteZona = service.getGerenteZonaList(criteria);		
						if(listGerenteZona != null && listGerenteZona.size() > 0) {
							Map mapGerenteZona = (Map)listGerenteZona.get(0);
							
							String nombreCompletoGerenteZona =
									reemplazarNulo(mapGerenteZona.get("apellido1"))+" "+reemplazarNulo(mapGerenteZona.get("apellido2"))+", "+
									reemplazarNulo(mapGerenteZona.get("nombre1"))+" "+reemplazarNulo(mapGerenteZona.get("nombre2")); 
							
							dtoDatosCliente.setNombreCompletoGerenteZona(nombreCompletoGerenteZona);
							dtoDatosCliente.setCelularGerenteZona(reemplazarNulo(mapGerenteZona.get("celular")));
							
							searchForm.setGerenteZona(dtoDatosCliente.getNombreCompletoGerenteZona());
							searchForm.setCelularGerenteZona(dtoDatosCliente.getCelularGerenteZona());
						}
						//--------------------
						
						//OBTENER LIDER SECCION
						dtoDatosCliente.setNombreCompletoLiderSeccion("");
						dtoDatosCliente.setCelularLiderSeccion("");
						searchForm.setLiderSeccion(dtoDatosCliente.getNombreCompletoLiderSeccion());
						searchForm.setCelularLiderSeccion(dtoDatosCliente.getCelularLiderSeccion());
						List listLiderSeccion = service.getLiderSeccionList(criteria);
						
						if(listLiderSeccion != null && listLiderSeccion.size() > 0) {
							Map mapLiderSeccion = (Map)listLiderSeccion.get(0);
							
							String nombreCompletoLiderSeccion = 
									reemplazarNulo(mapLiderSeccion.get("apellido1"))+" "+reemplazarNulo(mapLiderSeccion.get("apellido2"))+", "+
									reemplazarNulo(mapLiderSeccion.get("nombre1"))+" "+reemplazarNulo(mapLiderSeccion.get("nombre2"));
							
							dtoDatosCliente.setNombreCompletoLiderSeccion(nombreCompletoLiderSeccion);
							dtoDatosCliente.setCelularLiderSeccion(reemplazarNulo(mapLiderSeccion.get("celular")));
							
							searchForm.setLiderSeccion(dtoDatosCliente.getNombreCompletoLiderSeccion());
							searchForm.setCelularLiderSeccion(dtoDatosCliente.getCelularLiderSeccion());
						}
						//--------------------
						
						break;
					}
				}
			}

			this.setHipDtoDatosCliente(dtoDatosCliente);
						
			//obtenemos el saldo a pagar y Pagos posteriores
			criterios.put("codigoMarca", dtoDatosCliente.getCodigoMarca());
			criterios.put("codigoCanal", dtoDatosCliente.getCodigoCanal());
			criterios.put("codigoCliente", dtoDatosCliente.getCodigoCliente());
			criterios.put("codigoRegion", dtoDatosCliente.getCodigoRegion());
			criterios.put("codigoZona", dtoDatosCliente.getCodigoZona());
			criterios.put("codigoPeriodo", searchForm.getPeriodoUltimoPedido());
			
			try {
				searchForm.setSaldoPagarUltimoPedido(service.getSaldoPagar(criterios));
				
				try {
					searchForm.setPagosPosterioresUltimoPedido(String.valueOf(Double.parseDouble(searchForm.getSaldoUnicoUltimoPedido()) - 
														 Double.parseDouble(searchForm.getSaldoPagarUltimoPedido())));
				} catch (Exception ex) {
					searchForm.setPagosPosterioresUltimoPedido("");
				}
			} catch (Exception ex) {
				searchForm.setSaldoPagarUltimoPedido("");
			}
			
			String numeroPedidos = "";
			ParametroPais parametroPais1 = new ParametroPais();
			parametroPais1.setCodigoPais(dtoDatosCliente.getCodigoPais());
			parametroPais1.setCodigoSistema(Constants.HIP_CODIGO_SISTEMA);
			parametroPais1.setCodigoParametro(Constants.HIP_CODIGO_PARAMETRO_NUMERO_PEDIDO);
			parametroPais1.setNombreParametro(Constants.HIP_NOMBRE_PARAMETRO_NUMERO_PEDIDO);
			
			GenericoService genericoService = (GenericoService) getBean("genericoService");
			List lstParametros = genericoService.getParametrosPais(parametroPais1);
			
			if(lstParametros != null && lstParametros.size() > 0){
				ParametroPais ps = (ParametroPais)lstParametros.get(0);
				numeroPedidos = ps.getValorParametro();
			}
			
			criterios.put("numeroPedidos", numeroPedidos);
			
			//obtenemos el promedio de ventas de las ultimas 5 campañas de la consultora
			if(criterios.get("codigoRegion")!=null)
				searchForm.setPromedioVentas(service.getPromedioVentasxCampanhas(criterios));
			
			//obtenemos los medios de comunicacion del cliente
			List listMediosComunicacion = service.getMediosComunicacion(criterios);
			this.setHipMediosContactosList(listMediosComunicacion);
						
			//obtenemos la tipificacion de la consultora (tipoCliente, Subtipocliente, tipoClasificacion, Clasificacion)
			List listTipificacionCliente = service.getTipificacionCliente(criterios);
			agruparTipificaciones(listTipificacionCliente);
			
			//recuperamos los ultimos datos de cuenta corriente, pedidos, reclamos
			obtenerResumenDatosCliente(service, dtoDatosCliente, searchForm);
			
			//verificamos si el cliente tiene asignado la clasificacion de LOVE
			String clasificacionLove = service.getClasificacionLove(criterios);
			dtoDatosCliente.setClasificacionLove(reemplazarNulo(clasificacionLove));
			
			//Obtenemos el limite de credito asociado al Nivel de Riesgo de la Consejera			
			searchForm.setLimiteCredito(service.getLimiteCredito(criterios));

			//Obtenemos los datos del usuario Logueado
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser(); 
								
			//recuperamos los datos de Familia Protegida
			Map mapDatosFamilia = service.getDatosFamiliaProtegida(criterios);
			String estadoSolicitudPoliza = reemplazarNulo(mapDatosFamilia.get("estadoSolicitudPoliza"));
			if(!estadoSolicitudPoliza.equalsIgnoreCase("")) {
				String mensaje = this.getResourceMessage(estadoSolicitudPoliza);						
				searchForm.setEstadoSolicitudPoliza(mensaje);
			}	
			searchForm.setFechaInicioCobertura(reemplazarNulo(mapDatosFamilia.get("fechaInicioCobertua")));
			searchForm.setFechaFinCobertura(reemplazarNulo(mapDatosFamilia.get("fechaFinCobertura")));
			
			//validamos si se muestra el icono de reporte de imagenes escaneadas de S/C
			searchForm.setMostrarReporteImagenesSC(service.validarImagenesEscaneoSC(dtoDatosCliente.getCodigoCliente()));
			
			//obtenemos las opciones de consulta que podra realizar el usuario logueado			
			String mostrarDigiSimp = this.getMostrarDigitacionSimplificada();					
			
			Map criteria = new HashMap();
			criteria.put("codigoPais", searchForm.getCodigoPais());
			criteria.put("codigoUsuario", usuario.getLogin());
			List listOpciones = service.getOpcionesPermitidas(criteria);
			//validarOpciones(listOpciones, searchForm.getCodigoCliente(), mostrarDigiSimp);
			this.setHipOpcionesList(listOpciones);
			
			//TIPO CONSULTORA
			ParametroPais parametroPais = new ParametroPais();
			
			parametroPais.setCodigoPais(searchForm.getCodigoPais());
			parametroPais.setCodigoSistema(Constants.HIP_CODIGO_SISTEMA);
			parametroPais.setNombreParametro(Constants.HIP_NOMBRE_PARAMETRO_TIPO_CONSULTORA);
			parametroPais.setIndicadorActivo(Constants.ESTADO_ACTIVO);
			
			List parametros = genericoService.getParametrosPais(parametroPais);
			
			String indicadorTipo = Constants.NUMERO_CERO;
			
			if(parametros != null && parametros.size() > 0)
			{
				Map criteria2 = new HashMap(); 
				criteria2.put("codigoPais", reemplazarNulo(searchForm.getCodigoPais()));
				criteria2.put("oidCliente", reemplazarNulo(mapDatosGenerales.get("oidCliente")));
				
				String tipoConsultora = service.getTipoConsultora(criteria2); 
				
				if(StringUtils.isNotBlank(tipoConsultora)){
					searchForm.setTipoConsultora(tipoConsultora);
				}
				
				ParametroPais p = (ParametroPais)parametros.get(0);
				indicadorTipo = p.getValorParametro();
			}
			this.setIndicadorTipo(indicadorTipo);
			
			//Situacion en FLEXIPAGO
			ParametroPais parametroPaisFlx = new ParametroPais();
			
			parametroPaisFlx.setCodigoPais(searchForm.getCodigoPais());
			parametroPaisFlx.setCodigoSistema(Constants.HIP_CODIGO_SISTEMA);
			parametroPaisFlx.setNombreParametro(Constants.HIP_NOMBRE_PARAMETRO_SITUACION_FLEXIPAGO);
			parametroPaisFlx.setIndicadorActivo(Constants.ESTADO_ACTIVO);
			
			List parametrosFlx = genericoService.getParametrosPais(parametroPaisFlx);
			
			String indicadorSituacionFlx = Constants.NUMERO_CERO;
			
			if(parametrosFlx != null && parametrosFlx.size() > 0)
			{
				ParametroPais p = (ParametroPais)parametrosFlx.get(0);
				indicadorSituacionFlx = p.getValorParametro();
				
				MantenimientoPEJProgramaEjecutivasService servicePEJProgramaEjecutivas = (MantenimientoPEJProgramaEjecutivasService)getBean("spusicc.mantenimientoPEJProgramaEjecutivasService");
				Map result = servicePEJProgramaEjecutivas.getPeriodoDefault();
				String codigoPeriodo = (String) result.get("codigoPeriodo");
				
				searchForm.setSituacionEnFlx(service.getSituacionFlexipago(searchForm.getCodigoPais(), dtoDatosCliente.getCodigoCliente(), codigoPeriodo));
			}
			this.setIndicadorSituacionFlx(indicadorSituacionFlx);
			//--------------------
			
			//Datos Poliza - PER-SiCC-2015-0177
			ParametroPais parametroPaisPoliza = new ParametroPais();
			parametroPaisPoliza.setCodigoPais(searchForm.getCodigoPais());
			//INI PER-SiCC-2015-0368
			parametroPaisPoliza.setCodigoSistema(Constants.HIP_CODIGO_SISTEMA);
			//FIN PER-SiCC-2015-0368
			parametroPaisPoliza.setNombreParametro(Constants.HIP_NOMBRE_PARAMETRO_FAMILIA_PROTEGIDA);
			parametroPaisPoliza.setIndicadorActivo(Constants.ESTADO_ACTIVO);
			
			List parametrosPoliza = genericoService.getParametrosPais(parametroPaisPoliza);
			
			
			String indicadorDatosPoliza = Constants.NUMERO_CERO;
			
			if(parametrosPoliza != null && parametrosPoliza.size() > 0){
				ParametroPais p = (ParametroPais)parametrosPoliza.get(0);
				indicadorDatosPoliza = p.getValorParametro();
			}
			this.setIndicadorDatosPoliza(indicadorDatosPoliza);
			//Datos Poliza - PER-SiCC-2015-0177
				
			calcularNumeroColumnas(searchForm);		
			verificarLider();
			verificarProgramaEjecutivasActivo();
		}else {
			
			this.setViewResults(false);
			
			//limpiando los datos de la pantalla
			limpiar(searchForm);
			
			List listaCabecera =service.getCabeceraConsultoraCastigada(criterios);
			List listaDetalle =service.getDetalleConsultoraCastigada(criterios);
			if((listaCabecera != null && listaCabecera.size() > 0)||(listaDetalle != null && listaDetalle.size() > 0)){
				this.cabeceraConsultoraCastigadaList = service.getCabeceraConsultoraCastigada(criterios);
				this.detalleConsultoraCastigadaList = service.getDetalleConsultoraCastigada(criterios);
				this.cuentaCorrienteConsultoraCastigadaList = service.getCuentaCorrienteConsultoraCastigada(criterios);
				
				Map consultoraCastigada = (HashMap)cabeceraConsultoraCastigadaList.get(0);
				
				this.codigoCastigada = (String)consultoraCastigada.get("codigo");
				this.cedulaCastigada = (String)consultoraCastigada.get("cedula");
				this.nombresApellidosCastigada = (String)consultoraCastigada.get("nombresApellidos");
				this.regionCastigada = (String)consultoraCastigada.get("region");
				this.zonaCastigada = (String)consultoraCastigada.get("zona");

				this.getRequestContext().execute("openPopupConsultoraCastigada()");
				
				//f.setMostrarPantallaConsulta(true);
			}else{
				//f.setMostrarPantallaConsulta(false);
			}
			
			/* TODO*/
			/*
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("consultaHIPDatosClienteForm.msg.clienteNoEncontrado"));
			saveErrors(request, messages);
			*/
		}		
	    
		this.viewReporteMedia = false;
		//this.ejecutarReporteMain();
		
		log.debug("Valor de Service: " + service.toString());
		return mockList;	    
	}

	/**
	 * 
	 * @param searchForm
	 */
	private void calcularNumeroColumnas(ConsultaHIPDatosClienteForm searchForm) {
		//--DatosGenerales
		int nro = 6;
		if(StringUtils.isNotBlank(searchForm.getCodigoDigitoControl()))
			nro = 7;
		
		if(StringUtils.equals(this.getIndicadorSituacionFlx(), Constants.NUMERO_UNO))
			nro++;
		
		if(StringUtils.equals(searchForm.getIndCamposAdicion(), Constants.ESTADO_ACTIVO))
			nro=nro+2;
		
		this.setNumeroColumnasDatosGenerales(nro);
		//

		//--Despacho
		nro = 2;
		if(searchForm.isMostrarBarrio())
			nro = 3;
		
		if(StringUtils.equals(this.getIndicadorDatosPoliza(), Constants.NUMERO_UNO))
			nro += 3;
			
		this.setNumeroColumnasDatosDespacho(nro);
		//		
		
		// --DatosDomicilio
		nro = 4;
		if(searchForm.isMostrarBarrio())
			nro +=2;
		
		this.setNumeroColumnasDatosDomicilio(nro);
		//
	}


	/**
	 * @param form
	 */
	private void limpiar(ConsultaHIPDatosClienteForm f) {
				
		f.setCodigoCliente("");
		f.setCodigoDigitoControl("");
		f.setOidCliente("");
		f.setTipoDocIdentidad("");
		f.setNumeroDocIdentidad("");
		f.setNombreCompleto("");
		
		f.setEstatus("");
		f.setTelefono("");
		f.setEmail("");
		f.setFechaNacimiento("");
		f.setPromedioVentas("");
		f.setIndicadorActivo("");
		
		f.setFechaIngreso("");
		f.setPeriodoIngreso("");
		f.setBloqueo("");
		
		f.setDireccionDespacho("");
		f.setBarrioDespacho("");
		f.setUbigeoDespacho("");
		
		f.setDireccionDomicilio("");
		f.setBarrioDomicilio("");
		f.setUbigeoDomicilio("");
		
		f.setPeriodoUltimoPedido("");
		f.setBoletaUltimoPedido("");
		f.setImporteUltimoPedido("");
		f.setSaldoPeriodoUltimoPedido("");
		f.setSaldoUnicoUltimoPedido("");		
		f.setPagosPosterioresUltimoPedido("");
		f.setMostrarPantallaBusqueda(false);
		f.setFechaCastigada("");

		f.setEstadoSolicitudPoliza("");
		f.setFechaInicioCobertura("");
		f.setFechaFinCobertura("");
		f.setMostrarReporteImagenesSC(false);
			
		this.setHipTipoClienteList(null);
		this.setHipSubTipoClienteList(null);
		this.setHipTipoClasificacionList(null);
		this.setHipClasificacionList(null);		
		this.setHipMediosContactosList(null);
				
		this.setHipUnidadesAdministrativasList(null);
		this.setHipOpcionesList(null);
		
		this.setHipReclamosCabeceraList(null);
		this.setHipCuentaCorrientesMovimientosList(null);
		this.setHipPedidosConsultoraList(null);
		this.setHipConcursosBolsaFaltantesList(null);
		this.setHipVinculosReferenciasList(null);
		
		this.setHipSolicitudesPolizaList(null);
		this.setHipHistoricoCargosPolizaList(null);
				
		/* INI SA PER-SiCC-2012-0545 */
		f.setCiudadDespacho("");
		f.setVillaPoblacionDespacho("");
		f.setCiudadDomicilio("");
		f.setVillaPoblacionDomicilio("");
		/* FIN SA PER-SiCC-2012-0545 */
		
		this.setIndicadorTipo(Constants.NUMERO_CERO);
		this.setIndicadorSituacionFlx(Constants.NUMERO_CERO);
		this.setLstParametros(null);
		
		this.setViewResults(false);		
		this.outfileMedia = "";
		this.viewReporteMedia = false;
		f.setCodigoPostal("");
		f.setOpcionConsulta("");
	}
	
	/**
	 * metodo auxiliar que me permite recuperar en cadena el valor de un objeto
	 * 
	 * @param obj
	 * @return
	 */
	private String reemplazarNulo(Object obj) {
		if(obj == null)
			return "";
		else
			return (String)obj;
	}
	
	/**
	 * Agrupa el tipo/subtipo de clientes y sus clasificaciones de la consultora
	 * 
	 * @param session
	 * @param listTipificacionCliente
	 */
	private void agruparTipificaciones(List listTipificacionCliente) {
		
		List listTipoCliente = new ArrayList();
		List listSubTipoCliente = new ArrayList();
		List listTipoClasificacion = new ArrayList();
		List listClasificacion = new ArrayList();
		
		Base baseVacio = new Base();
		baseVacio.setCodigo("");
		baseVacio.setDescripcion("");
		/*listTipoCliente.add(baseVacio);
		listSubTipoCliente.add(baseVacio);
		listTipoClasificacion.add(baseVacio);
		listClasificacion.add(baseVacio);*/
		
		if(listTipificacionCliente!= null && listTipificacionCliente.size() > 0) {
			for(int i=0; i<listTipificacionCliente.size(); i++) {
				Map mapTipificacion = (Map)listTipificacionCliente.get(i);
				
				//formamos la lista de tipos de clientes de la consultora
				Base baseTipoCliente = new Base();
				baseTipoCliente.setCodigo(reemplazarNulo(mapTipificacion.get("tipoCliente")));
				baseTipoCliente.setDescripcion(reemplazarNulo(mapTipificacion.get("tipoCliente")));
				if(!existeElementoLista(listTipoCliente, baseTipoCliente)) {
					listTipoCliente.add(baseTipoCliente);	
				}
								
				//formamos la lista de subtipos de clientes de la consultora
				Base baseSubTipoCliente = new Base();
				baseSubTipoCliente.setCodigo(reemplazarNulo(mapTipificacion.get("tipoCliente")));
				baseSubTipoCliente.setDescripcion(reemplazarNulo(mapTipificacion.get("subtipoCliente")));
				if(!existeElementoLista(listSubTipoCliente, baseSubTipoCliente)) {
					listSubTipoCliente.add(baseSubTipoCliente);	
				}

				//formamos la lista de tipos de clasificaciones de la consultora
				Base baseTipoClasificacion = new Base();
				baseTipoClasificacion.setCodigo(reemplazarNulo(mapTipificacion.get("subtipoCliente")));
				baseTipoClasificacion.setDescripcion(reemplazarNulo(mapTipificacion.get("tipoClasificacion")));
				if(!baseTipoClasificacion.getDescripcion().equals("")) {
					listTipoClasificacion.add(baseTipoClasificacion);
					
					//formamos la lista de clasificaciones de la consultora
					Base baseClasificacion = new Base();
					baseClasificacion.setCodigo(reemplazarNulo(mapTipificacion.get("tipoClasificacion")));
					baseClasificacion.setDescripcion(reemplazarNulo(mapTipificacion.get("clasificacion")));
					listClasificacion.add(baseClasificacion);
				}	
			}
		}
		
		if(listTipoCliente.size()==0)
			listTipoCliente.add(baseVacio);
		if(listSubTipoCliente.size()==0)
			listSubTipoCliente.add(baseVacio);
		if(listTipoClasificacion.size()==0)
			listTipoClasificacion.add(baseVacio);
		if(listClasificacion.size()==0)
			listClasificacion.add(baseVacio);
		
		this.setHipTipoClienteList(listTipoCliente);
		this.setHipSubTipoClienteList(listSubTipoCliente);
		this.setHipTipoClasificacionList(listTipoClasificacion);
		this.setHipClasificacionList(listClasificacion);
		
	}

	/**
	 * metodo auxiliar que permite verificar si un elemento ya fue ingresado a la lista
	 * 
	 * @param listElementos
	 * @param baseElemento
	 * @return
	 */
	private boolean existeElementoLista(List listElementos, Base baseElemento) {
		boolean existe = false;
		
		for(int i=0; i<listElementos.size(); i++) {
			Base baseAux = (Base)listElementos.get(i);
			
			if((baseAux.getCodigo().equalsIgnoreCase(baseElemento.getCodigo())) &&
			   (baseAux.getDescripcion().equalsIgnoreCase(baseElemento.getDescripcion()))) {
				existe = true;
				break;
			}
		}
		
		return existe;
	}
	
	/**
	 * Recuperamos los ultimos movimientos de Cuenta Corriente, Ultimos Pedidos, Ultimos Reclamos,
	 * premios pendientes de despacho, Vinculos y referencias
	 * 
	 * @param service
	 * @param session
	 * @param dtoDatosCliente
	 */
	private void obtenerResumenDatosCliente(ConsultaHIPDatosClienteService service, 
											ConsultaHIPDatosCliente dtoDatosCliente, ConsultaHIPDatosClienteForm searchForm) {
		
		
		//obtenemos los reclamos de la consultora
		Map criteria = new HashMap();
		criteria.put("oidCliente", dtoDatosCliente.getOidCliente());
		criteria.put("oidIdioma", dtoDatosCliente.getOidIdioma());
		criteria.put("filas", String.valueOf(HIP_FILAS_ULTIMOS_MOVIMIENTOS+1));
		criteria.put("codigoPais", dtoDatosCliente.getCodigoPais());
		
		//RECLAMOS
		List listCabeceraReclamos = service.getCabeceraReclamos(criteria);
		this.setHipReclamosCabeceraList(listCabeceraReclamos);
		
		//recorremos los reclamos pa determinar el estado de cada uno de ellos en base a los campos EnviaReclamado y EnviaAtendido
		/*
		for(int i=0; i<listCabeceraReclamos.size(); i++) {
			Map mapReclamo = (Map)listCabeceraReclamos.get(i);
			String enviaReclamado = reemplazarNulo(mapReclamo.get("enviaReclamado"));
			String enviaAtendido = reemplazarNulo(mapReclamo.get("enviaAtendido"));
			
			if(enviaReclamado.equals("") || enviaReclamado.equals("0")) {
				//Queda como esta el campo [estado Reclamo]
			} else {
				if(!enviaAtendido.equals(""))
					mapReclamo.put("estadoReclamo", Constants.HIP_RECLAMOS_ESTADO_FACTURADO);
				else
					mapReclamo.put("estadoReclamo", Constants.HIP_RECLAMOS_ESTADO_PENDIENTE);
			}
		}
		*/
		
		//CUENTA CORRIENTES
		ConsultaCOBTelecobroService serviceCobranza = (ConsultaCOBTelecobroService)getBean("spusicc.consultaCOBTelecobroService");
		criteria.put("codigoConsultora", dtoDatosCliente.getCodigoCliente());
		criteria.put("codigoPais", dtoDatosCliente.getCodigoPais());

		List listMovimientosCuentaCorriente = serviceCobranza.getDetalleConsultora(criteria);
		Iterator itCuentaCorriente = listMovimientosCuentaCorriente.iterator();
		List listMovimientosCuentaCorrienteAux = new ArrayList();
				
		int contador = 1;
		while(contador < (HIP_FILAS_ULTIMOS_MOVIMIENTOS + 1) && itCuentaCorriente.hasNext()) {
			listMovimientosCuentaCorrienteAux.add(itCuentaCorriente.next());
			contador = contador + 1;
		}
		this.setHipCuentaCorrientesMovimientosList(listMovimientosCuentaCorrienteAux);
		
		//PEDIDOS
		//Seteo los parametros de la consulta
		criteria.put("codigoCliente", dtoDatosCliente.getCodigoCliente());
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
				
		ParametroPais parametroPais = new ParametroPais();
		parametroPais.setCodigoPais(pais.getCodigo());
		parametroPais.setCodigoSistema(Constants.CODIGO_SISTEMA_PED);
		parametroPais.setCodigoParametro(Constants.CODIGO_PARAMETRO_CONSULTA_HIP);				
		GenericoService genericoService = (GenericoService) getBean("genericoService");
		List lstParametroPais = genericoService.getParametrosPais(parametroPais);
		if(lstParametroPais.size()!=0){
			for (int i = 0; i < lstParametroPais.size(); i++) {
				ParametroPais pPais = (ParametroPais)lstParametroPais.get(i);
				if (pPais.getNombreParametro().equals("indMostGastoAdminTot")) {
					if(pPais.getValorParametro().equals("1")){
						this.setValPara(true);
					}else{
						this.setValPara(false);
					}
					break;
				}
			}
		}else{
			this.setValPara(false);					
		}
		
		//Obtengo las listas a mostrar
		List pedidosConsultora = service.getPedidosConsultora(criteria);
		
		//reviso se habilito el indicador
		ParametroPais parametroPaisFactura = new ParametroPais();
		parametroPaisFactura.setCodigoPais(pais.getCodigo());
		parametroPaisFactura.setCodigoSistema(Constants.HIP_CODIGO_SISTEMA);
		parametroPaisFactura.setNombreParametro("indMuestraNroFactura");
		parametroPaisFactura.setValorParametro(Constants.NUMERO_UNO);
		parametroPaisFactura.setIndicadorActivo(Constants.NUMERO_UNO);
		
		List lstParametroPaisFactura = genericoService.getParametrosPais(parametroPaisFactura);
		
		if (lstParametroPaisFactura != null && lstParametroPaisFactura.size() > 0){
			searchForm.setIndicadorMostrarNumeroFactura(Constants.NUMERO_UNO);
		}else{
			searchForm.setIndicadorMostrarNumeroFactura(Constants.NUMERO_CERO);
		} 
		
		this.setHipPedidosConsultoraList(pedidosConsultora);
		
		//PREMIOS PENDIENTES DE DESPACHO
		List listBolsaFaltantes = service.getBolsaFaltantes(criteria);
		this.setHipConcursosBolsaFaltantesList(listBolsaFaltantes);
		
		//VINCULOS Y REFERENCIAS
		List listVinculosReferencias = service.getVinculosReferencias(criteria);
		this.setHipVinculosReferenciasList(listVinculosReferencias);
		
		/*
		 */
		//PER-SiCC-2013-0832 HIP - Mejoras I (Requerimiento) @ghuertasa inicio
		ParametroPais parametroPaisPedido = new ParametroPais();
		parametroPaisPedido.setCodigoPais(pais.getCodigo());
		parametroPaisPedido.setCodigoSistema(Constants.HIP_CODIGO_SISTEMA);
		//aqui hay que agregar una constante para el indicador con valor '004'
		parametroPaisPedido.setCodigoParametro(Constants.INDICADOR_CANT_DATOS_GRILLA);
		parametroPaisPedido.setIndicadorActivo(Constants.ESTADO_ACTIVO);
		List lstParametros = genericoService.getParametrosPais(parametroPaisPedido);
		
		ParametroPais parametro = null;
		
		if(CollectionUtils.size(lstParametros)==1){
			parametro = (ParametroPais) lstParametros.get(0);
			searchForm.setValParam(parametro.getValorParametro());
			//comparar con lo que devuelve la grilla para mostrar scroll.
			searchForm.setMostrarDiv(false);
			if(listVinculosReferencias.size()>Integer.parseInt(searchForm.getValParam().trim())){
				searchForm.setMostrarDiv(true);
			}
		}
		
		//PER-SiCC-2013-0832 HIP - Mejoras I (Requerimiento) @ghuertasa fin
	}
	
	
	/**
	 * @param valueChangeEvent
	 */
	public void selectOpcionConsultaChangeListener(ValueChangeEvent event)throws Exception{
	
		log.debug("selectOpcionConsultaChangeListener method");		
		this.opcionConsulta = (String)event.getNewValue();
		if (StringUtils.isBlank(this.opcionConsulta)) return;
		this.validarOpcionConsulta(this.opcionConsulta);
		
		log.debug("Opcion Consulta : "+this.opcionConsulta);
		
   	}
	
	/**
	 * Valida la opcion de consulta y setea dinamicamente el url de la pagina y el manage de control
	 * @param opcionConsultaValid
	 */
	private void validarOpcionConsulta(String opcionConsultaValid) throws IOException{
		
		log.debug("validarOpcionConsulta method");	
		
		if (opcionConsultaValid.equals(Constants.HIP_OPCION_CONCURSOS)){//"HIP-2"
			this.pageOpcionConsulta = this.getResourceMessage("opcionConsulta.page.hip2");
			this.opcionConsulta = Constants.HIP_OPCION_CONCURSOS;
			
		}else if (opcionConsultaValid.equals(Constants.HIP_OPCION_CUENTA_CORRIENTE)){//"HIP-3" 
			this.pageOpcionConsulta = this.getResourceMessage("opcionConsulta.page.hip3");
			this.opcionConsulta = Constants.HIP_OPCION_CUENTA_CORRIENTE;
			
		}else if (opcionConsultaValid.equals(Constants.HIP_OPCION_CUENTA_CORRIENTE_HISTORICA)){//"HIP-3_1" 
			this.pageOpcionConsulta = this.getResourceMessage("opcionConsulta.page.hip3_1");
			this.opcionConsulta = Constants.HIP_OPCION_CUENTA_CORRIENTE_HISTORICA;
			
		}else if(opcionConsultaValid.equals(Constants.HIP_OPCION_CRONOGRAMA_ACTIVIDADES)){//"HIP-4"
			this.pageOpcionConsulta = this.getResourceMessage("opcionConsulta.page.hip4");
			this.opcionConsulta = Constants.HIP_OPCION_CRONOGRAMA_ACTIVIDADES;
			
		}else if(opcionConsultaValid.equals(Constants.HIP_OPCION_ENVIOS_PREFERENCIALES)){//"HIP-5"
			this.pageOpcionConsulta = this.getResourceMessage("opcionConsulta.page.hip5");
			this.opcionConsulta = Constants.HIP_OPCION_ENVIOS_PREFERENCIALES;
			
			
		}else if(opcionConsultaValid.equals(Constants.HIP_OPCION_HISTORIAL_RESPONSABLES_SECCION)){//"HIP-6"
			this.pageOpcionConsulta = this.getResourceMessage("opcionConsulta.page.hip6");
			this.opcionConsulta = Constants.HIP_OPCION_HISTORIAL_RESPONSABLES_SECCION;
			
		}else if(opcionConsultaValid.equals(Constants.HIP_OPCION_HISTORIAL_POST_VENTAS)){//"HIP-7"
			this.pageOpcionConsulta = this.getResourceMessage("opcionConsulta.page.hip7");
			this.opcionConsulta = Constants.HIP_OPCION_HISTORIAL_POST_VENTAS;
			
		}else if(opcionConsultaValid.equals(Constants.HIP_OPCION_CONSULTA_BOLETA_RECOJO)){//"HIP-22"
			this.pageOpcionConsulta = this.getResourceMessage("opcionConsulta.page.hip22");
			this.opcionConsulta = Constants.HIP_OPCION_CONSULTA_BOLETA_RECOJO;
		
		}else if(opcionConsultaValid.equals(Constants.HIP_OPCION_HISTORIAL_BLOQUEO)){//"HIP-8"
			this.pageOpcionConsulta = this.getResourceMessage("opcionConsulta.page.hip8");
			this.opcionConsulta = (String) Constants.HIP_OPCION_HISTORIAL_BLOQUEO;
			
		}else if(opcionConsultaValid.equals(Constants.HIP_OPCION_SISTEMA_INTEGRAL_EDUCACION)){//"HIP-9" 
			this.pageOpcionConsulta = this.getResourceMessage("opcionConsulta.page.hip9");
			this.opcionConsulta = (String) Constants.HIP_OPCION_SISTEMA_INTEGRAL_EDUCACION;
			
		}else if(opcionConsultaValid.equals(Constants.HIP_OPCION_SOLICITUD_CERTIFICACIONES)){//"HIP-10" 
			this.pageOpcionConsulta = this.getResourceMessage("opcionConsulta.page.hip10");
			this.opcionConsulta = (String) Constants.HIP_OPCION_SOLICITUD_CERTIFICACIONES;
			
		}else if(opcionConsultaValid.equals(Constants.HIP_OPCION_PROGRAMA_NUEVAS)){//"HIP-11" 
			this.pageOpcionConsulta = this.getResourceMessage("opcionConsulta.page.hip11");
			this.opcionConsulta = (String) Constants.HIP_OPCION_PROGRAMA_NUEVAS;
			
		}else if(opcionConsultaValid.equals(Constants.HIP_OPCION_ACTUALIZACION_DATOS)){//"HIP-12" 
			this.pageOpcionConsulta = this.getResourceMessage("opcionConsulta.page.hip12");
			this.opcionConsulta = (String) Constants.HIP_OPCION_ACTUALIZACION_DATOS;	
			
		}else if(opcionConsultaValid.equals(Constants.HIP_OPCION_ACTUALIZACION_DUPLA_CYZONE)){//"HIP-13" 
			this.pageOpcionConsulta = this.getResourceMessage("opcionConsulta.page.hip13");
			this.opcionConsulta = (String) Constants.HIP_OPCION_ACTUALIZACION_DUPLA_CYZONE;
		
		}else if(opcionConsultaValid.equals(Constants.HIP_OPCION_CREAR_PEDIDOS)){//"HIP-14" 
			this.opcionConsulta = (String) Constants.HIP_OPCION_CREAR_PEDIDOS;
			this.pageOpcionConsulta = this.getResourceMessage("opcionConsulta.page.hip14");
			
			ConsultaHIPDatosClienteForm f = (ConsultaHIPDatosClienteForm) this.formBusqueda;
			String codigo = f.getCodigoClienteBuscar();
			((MantenimientoOCRCapturaPedidosForm)this.getMantenimientoOCRCapturaPedidosAction().getFormBusqueda()).
			       setCodigoConsultora(codigo);
			
			this.getMantenimientoOCRCapturaPedidosAction().setCodigoConsultoraHiperConsulta(codigo);
			//this.enlaceOtrasPaginas();

		}else if(opcionConsultaValid.equals(Constants.HIP_OPCION_CREAR_RECLAMOS)){//"HIP-15" 
			Map parametrosPantallaCDR = new HashMap<String, String>();
			parametrosPantallaCDR.put("indicadorOnline", Constants.SI);
			parametrosPantallaCDR.put("codigoCliente", ((ConsultaHIPDatosClienteForm)this.formBusqueda).getCodigoCliente());
			
			this.opcionConsulta = (String) Constants.HIP_OPCION_CREAR_RECLAMOS; 
			this.pageOpcionConsulta = this.getResourceMessage("opcionConsulta.page.hip15");
			((MantenimientoRECDigitacionCDRAjaxForm)this.getMantenimientoRECDigitacionCDRAjaxAction().getFormBusqueda()).setCodigoClienteDocumentoReferencia(
					((ConsultaHIPDatosClienteForm)this.formBusqueda).getCodigoCliente());
			((MantenimientoRECDigitacionCDRAjaxForm)this.getMantenimientoRECDigitacionCDRAjaxAction().getFormBusqueda()).setIndicadorOnline(Constants.SI);
			
			this.getMantenimientoRECDigitacionCDRAjaxAction().setParametrosPantalla(parametrosPantallaCDR);
			//this.enlaceOtrasPaginas();
			
		}else if(opcionConsultaValid.equals(Constants.HIP_OPCION_PEDIDOS)){//"HIP-16" 
			this.pageOpcionConsulta = this.getResourceMessage("opcionConsulta.page.hip16");
			this.opcionConsulta = (String) Constants.HIP_OPCION_PEDIDOS;
			
		}else if(opcionConsultaValid.equals(Constants.HIP_OPCION_CDR_RECHAZADOS)){//"HIP-17" 
			this.pageOpcionConsulta = this.getResourceMessage("opcionConsulta.page.hip17");
			this.opcionConsulta = (String) Constants.HIP_OPCION_CDR_RECHAZADOS;			
			
		}else if(opcionConsultaValid.equals(Constants.HIP_OPCION_CONSULTA_HISTORICO_VINCULOS)){//"HIP-18" 
			this.pageOpcionConsulta = this.getResourceMessage("opcionConsulta.page.hip18");
			this.opcionConsulta = (String) Constants.HIP_OPCION_CONSULTA_HISTORICO_VINCULOS;	
			
		}else if(opcionConsultaValid.equals(Constants.HIP_OPCION_CONSULTA_SOLICITUDES_POLIZA)){//"HIP-19" 
			this.pageOpcionConsulta = this.getResourceMessage("opcionConsulta.page.hip19");
			this.opcionConsulta = (String) Constants.HIP_OPCION_CONSULTA_SOLICITUDES_POLIZA;	
			
		}else if(opcionConsultaValid.equals(Constants.HIP_OPCION_SEGUIMIENTO_PEDIDOS)){//"HIP-20" 
			this.pageOpcionConsulta = this.getResourceMessage("opcionConsulta.page.hip20");
			this.opcionConsulta = (String) Constants.HIP_OPCION_SEGUIMIENTO_PEDIDOS;		
		
		}else if(opcionConsultaValid.equals(Constants.HIP_OPCION_FLEXIPAGO)){//"HIP-21" 
			this.pageOpcionConsulta = this.getResourceMessage("opcionConsulta.page.hip21");
			this.opcionConsulta = (String) Constants.HIP_OPCION_FLEXIPAGO;
			
		}else if(opcionConsultaValid.equals(Constants.HIP_OPCION_SEGUROS)){//"HIP-23" 
			this.pageOpcionConsulta = this.getResourceMessage("opcionConsulta.page.hip23");
			this.opcionConsulta = (String) Constants.HIP_OPCION_SEGUROS;			
		}else if(opcionConsultaValid.equals(Constants.HIP_OPCION_METAS)){//"HIP-24" 
			this.pageOpcionConsulta = this.getResourceMessage("opcionConsulta.page.hip24");
			this.opcionConsulta = (String) Constants.HIP_OPCION_METAS;
			
		}else if(opcionConsultaValid.equals(Constants.HIP_OPCION_CONSULTA_FAD)){//"HIP-25" 
			this.pageOpcionConsulta = this.getResourceMessage("opcionConsulta.page.hip25");
			this.opcionConsulta = (String) Constants.HIP_OPCION_CONSULTA_FAD;
		
		}else if(opcionConsultaValid.equals(Constants.HIP_OPCION_RETAIL)){//"HIP-26" 
			this.pageOpcionConsulta = this.getResourceMessage("opcionConsulta.page.hip26");
			this.opcionConsulta = (String) Constants.HIP_OPCION_RETAIL;
					
		}else if(opcionConsultaValid.equals(Constants.HIP_OPCION_EJECUTIVAS)){//"HIP-27" 
			
			if(this.indicadorProgramaEjecutivasActivo)
			{
				this.pageOpcionConsulta = this.getResourceMessage("opcionConsulta.page.hip27");
				this.opcionConsulta = (String) Constants.HIP_OPCION_EJECUTIVAS;
			}
			else
			{
				this.pageOpcionConsulta = "";
				this.opcionConsulta = "";
				this.addError("Error: ", this.getResourceMessage("consultaHIPEjecutivasForm.valida.programa"));
			}
			
		}else if(opcionConsultaValid.equals(Constants.HIP_OPCION_ACTUALIZACION_DATOS_CLIENTE)){//"HIP-28" 
			this.pageOpcionConsulta = this.getResourceMessage("opcionConsulta.page.hip28");
			this.opcionConsulta = (String) Constants.HIP_OPCION_ACTUALIZACION_DATOS_CLIENTE;			
		}else if(opcionConsultaValid.equals(Constants.HIP_OPCION_ACTUALIZACION_DIRECCION_CLIENTE)){//"HIP-29" 
			this.pageOpcionConsulta = this.getResourceMessage("opcionConsulta.page.hip29");
			this.opcionConsulta = (String) Constants.HIP_OPCION_ACTUALIZACION_DIRECCION_CLIENTE;
			
		}else if(opcionConsultaValid.equals(Constants.HIP_OPCION_CUENTA_CORRIENTE_CAMPANHA)){//"HIP-30" 
			this.pageOpcionConsulta = this.getResourceMessage("opcionConsulta.page.hip30");
			this.opcionConsulta = (String) Constants.HIP_OPCION_CUENTA_CORRIENTE_CAMPANHA;
			
		}else if(opcionConsultaValid.equals(Constants.HIP_OPCION_RECUPERACION_ANULACION)){//"HIP-31" 
			this.pageOpcionConsulta = this.getResourceMessage("opcionConsulta.page.hip31");
			this.opcionConsulta = (String) Constants.HIP_OPCION_RECUPERACION_ANULACION;
			
		}else if(opcionConsultaValid.equals(Constants.HIP_OPCION_SOCIAS_EMPRESARIAS)){//"HIP-32"
			
			if(this.indicadorLider)
			{
				this.pageOpcionConsulta = this.getResourceMessage("opcionConsulta.page.hip32");
				this.opcionConsulta = (String) Constants.HIP_OPCION_SOCIAS_EMPRESARIAS;
			}
			else
			{
				this.pageOpcionConsulta = "";
				this.opcionConsulta = "";
				this.addError("Error: ", this.getResourceMessage("consultaHIPSociasEmpresariasForm.error.no.lider"));
			}
		}
		
	}
	
	/**
	 * @param actionEvent
	 */
	public void findConsulta(ActionEvent actionEvent) {
		
		log.debug("findConsulta actionEvent method");
		
		if(this.opcionConsulta.equals(Constants.HIP_OPCION_CUENTA_CORRIENTE)){
			ConsultaHIPCuentaCorrientesAction consultaHIPCuentaCorrientesAction = new ConsultaHIPCuentaCorrientesAction();
			consultaHIPCuentaCorrientesAction.find();
		}
		/*
		if (opcionConsultaValid.equals(Constants.HIP_OPCION_CUENTA_CORRIENTE)){ 
			this.pageOpcionConsulta = this.getResourceMessage("opcionConsulta.page.hip3");
			this.manageOpcionConsulta = "consultaHIPCuentaCorrientesAction";
		}
		this.find();
		*/
	}

	
	/**
	 * Se recupera las acciones que seran invocadas por las opciones autorizadas para el usuario logueado
	 * 
	 * @param listOpciones
	 */
	private void validarOpciones(List listOpciones, String codigoCliente, String mostrarDigiSimp) {
		/* TODO */
		String urlString = "";
		for(int i=0; i< listOpciones.size(); i++) {
			Base base = (Base)listOpciones.get(i);
			if (base.getCodigo().equals(Constants.HIP_OPCION_SEGUIMIENTO_PEDIDO)) 
				base.setCodigo("");
			if (base.getCodigo().equals(Constants.HIP_OPCION_CONCURSOS)) 
				base.setCodigo("viewConsultaHIPConcursos.do");
			if (base.getCodigo().equals(Constants.HIP_OPCION_CUENTA_CORRIENTE))
				this.pageOpcionConsulta = this.getResourceMessage("opcionConsulta.page.hip3");				
				//base.setCodigo("viewConsultaHIPCuentaCorrientes.do");
			if (base.getCodigo().equals(Constants.HIP_OPCION_CUENTA_CORRIENTE_HISTORICA)) 
				base.setCodigo("viewConsultaHIPCuentaCorrientesHistorica.do");
			if (base.getCodigo().equals(Constants.HIP_OPCION_CRONOGRAMA_ACTIVIDADES)) 
				base.setCodigo("viewConsultaHIPCronogramaActividades.do");
			if (base.getCodigo().equals(Constants.HIP_OPCION_ENVIOS_PREFERENCIALES)) 
				base.setCodigo("viewConsultaHIPEnviosPreferenciales.do");
			if (base.getCodigo().equals(Constants.HIP_OPCION_HISTORIAL_RESPONSABLES_SECCION)) 
				base.setCodigo("viewConsultaHIPResponsablesSeccion.do");
			if (base.getCodigo().equals(Constants.HIP_OPCION_HISTORIAL_POST_VENTAS)) //Reclamos
				base.setCodigo("viewConsultaHIPReclamos.do");
			if (base.getCodigo().equals(Constants.HIP_OPCION_HISTORIAL_BLOQUEO)) 
				base.setCodigo("viewConsultaHIPHistoriaBloqueo.do");
			if (base.getCodigo().equals(Constants.HIP_OPCION_SISTEMA_INTEGRAL_EDUCACION)) 
				base.setCodigo("viewConsultaHIPSistemaIntegralEducacion.do");
			if (base.getCodigo().equals(Constants.HIP_OPCION_SOLICITUD_CERTIFICACIONES)) 
				base.setCodigo("viewConsultaHIPSolicitudCertificacion.do");
			if (base.getCodigo().equals(Constants.HIP_OPCION_PROGRAMA_NUEVAS)) 
				base.setCodigo("viewConsultaHIPProgramasNuevas.do");
			if (base.getCodigo().equals(Constants.HIP_OPCION_ACTUALIZACION_DATOS)) 
				base.setCodigo("viewConsultaHIPActualizacionDatosConsultora.do");
			if (base.getCodigo().equals(Constants.HIP_OPCION_ACTUALIZACION_DUPLA_CYZONE)) 
				base.setCodigo("viewConsultaHIPActualizacionDuplaCyzone.do");			
			if (base.getCodigo().equals(Constants.HIP_OPCION_CREAR_PEDIDOS)){
				if(StringUtils.equals(mostrarDigiSimp, Constants.SI)){
					base.setCodigo("viewMantenimientoOCRCapturaPedidos.do?codigoConsultora="+codigoCliente);
				}else{
					base.setCodigo("viewMantenimientoOCRCargaPedidos.do?codigoConsultora="+codigoCliente);
				}
			}
			if (base.getCodigo().equals(Constants.HIP_OPCION_CREAR_RECLAMOS)) 
				base.setCodigo("viewMantenimientoRECDigitacionCDR.do?indicadorOnline=S");
			if (base.getCodigo().equals(Constants.HIP_OPCION_PEDIDOS)) 
				base.setCodigo("viewConsultaHIPPedidos.do");
			if (base.getCodigo().equals(Constants.HIP_OPCION_CDR_RECHAZADOS)) 
				base.setCodigo("viewConsultaHIPCdrRechazados.do");
			if (base.getCodigo().equals(Constants.HIP_OPCION_HISTORICO_VINCULOS)) 
				base.setCodigo("viewConsultaHIPHistoricoVinculos.do");
			if (base.getCodigo().equals(Constants.HIP_OPCION_SOLICITUDES_POLIZA)) 
				base.setCodigo("viewConsultaHIPSolicitudesPoliza.do");
			if (base.getCodigo().equals(Constants.HIP_OPCION_SEGUIMIENTO_PEDIDOS)) 
				base.setCodigo("viewConsultaSTOSeguimientoPedidos.do?codigoConsultora="+codigoCliente);
			if (base.getCodigo().equals(Constants.HIP_OPCION_FLEXIPAGO)) 
				base.setCodigo("viewConsultaFLXConsultora.do?codConsultora="+codigoCliente);
		}
	}
	

	/**
	 * @param ajaxBehaviorEvent
	 */
	public void findHIPDatosCliente (AjaxBehaviorEvent ajaxBehaviorEvent){		
		if (log.isDebugEnabled()) {
            log.debug("Entering 'ConsultaHIPDatosClienteAction - findHIPDatosCliente(AjaxBehaviorEvent)' method");
        }
		this.find();
	}
	
	public void enlaceOtrasPaginas() throws IOException{
		StringBuilder cadena = new StringBuilder();
		if(StringUtils.isNotBlank(this.getOpcionConsulta()) 
				&& StringUtils.isNotEmpty(this.getOpcionConsulta())){
			if(StringUtils.equals(this.getOpcionConsulta(),Constants.HIP_OPCION_CREAR_PEDIDOS)){
				//this.getMantenimientoOCRCapturaPedidosAction().setEnlaceOrigen("HIP");
				((MantenimientoOCRCapturaPedidosForm)this.getMantenimientoOCRCapturaPedidosAction().getFormBusqueda()).setCodigoConsultora(((ConsultaHIPDatosClienteForm)this.formBusqueda).getCodigoClienteBuscar());
				cadena.append(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath())
					.append(this.getResourceMessage("opcionConsulta.page.hip14"));
				FacesContext.getCurrentInstance().getExternalContext().redirect(cadena.toString());
			}
			
			if(StringUtils.equals(this.getOpcionConsulta(),Constants.HIP_OPCION_CREAR_RECLAMOS)){
				//this.getMantenimientoOCRCapturaPedidosAction().setEnlaceOrigen("HIP");
				((MantenimientoRECDigitacionCDRAjaxForm)this.getMantenimientoRECDigitacionCDRAjaxAction().getFormBusqueda()).setCodigoClienteDocumentoReferencia(
						((ConsultaHIPDatosClienteForm)this.formBusqueda).getCodigoClienteBuscar());
				((MantenimientoRECDigitacionCDRAjaxForm)this.getMantenimientoRECDigitacionCDRAjaxAction().getFormBusqueda()).setIndicadorOnline("N");
				this.getMantenimientoRECDigitacionCDRAjaxAction().setPaginaPadre(
						FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/pages/scsicc/hip/consultaHIPDatosClienteForm");
				
				cadena.append(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath())
					.append(this.getResourceMessage("opcionConsulta.page.hip15"));
				FacesContext.getCurrentInstance().getExternalContext().redirect(cadena.toString());
			}
		}
	}
	
	/**
	 * @param query
	 * @return
	 */
	public List<String> cerosIzquierdaCodigoClienteBuscar(String query){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'cerosIzquierdaCodigoClienteBuscar' method");
		}
		ConsultaHIPDatosClienteForm f = (ConsultaHIPDatosClienteForm)this.formBusqueda;
		List<String> listado = new ArrayList<String>();
		listado.add(StringUtils.leftPad(query, Integer.parseInt(f.getLongitudCodigoCliente()),"0"));
		f.setCodigoClienteBuscar(StringUtils.leftPad(query, Integer.parseInt(f.getLongitudCodigoCliente()),"0"));
		return listado;
	}
	
	/**
	 * @param query
	 * @return
	 */
	public List<String> cerosIzquierdaNumDocIden(String query){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'cerosIzquierdaNumDocIden' method");
		}
		ConsultaHIPDatosClienteForm f = (ConsultaHIPDatosClienteForm)this.formBusqueda;
		List<String> listado = new ArrayList<String>();
		listado.add(StringUtils.leftPad(query, Integer.parseInt(f.getLongitudNumeroDocIdentidad()),"0"));
		f.setNumeroDocIdentidad(StringUtils.leftPad(query, Integer.parseInt(f.getLongitudNumeroDocIdentidad()),"0"));
		return listado;
	}
	
	/**
	 * @param event
	 */
	public void onChangePDFEscaneoImagenes(ActionEvent event) {
		if (this.mostrarPDFEscaneoImagenes) return;
		this.ejecutarReporteMain();
	}
	
	/**
	 * @param event
	 */
	public void ejecutarReporteMain(){
		if(log.isDebugEnabled()){
			log.debug("ejecutarReporteMain");
		}
		
		ConsultaHIPDatosClienteService service = (ConsultaHIPDatosClienteService) this.getBeanService("scsicc.consultaHIPDatosClienteService");
		if(service.validarImagenesEscaneoSC(((ConsultaHIPDatosClienteForm)this.formBusqueda).getCodigoCliente())){		
			if(StringUtils.isNotBlank(((ConsultaHIPDatosClienteForm)this.formBusqueda).getCodigoCliente())
					&& StringUtils.isNotEmpty(((ConsultaHIPDatosClienteForm)this.formBusqueda).getCodigoCliente())){
				this.executeReporte();
				this.mostrarPDFEscaneoImagenes = true;
			}
		}
	}

	
	/**
	 * @param actionEvent
	 */
	public void executeReporte() {
		if(log.isDebugEnabled()){
			log.debug("executeReporte");
		}
		this.viewReporteMedia = false;
		this.outfileMedia = "";
		
		MantenimientoSTOBloqueoControlService stoService = (MantenimientoSTOBloqueoControlService)getBean("spusicc.mantenimientoSTOBloqueoControlService");
		ConsultaHIPDatosClienteForm consultaHIPDatosClienteForm = (ConsultaHIPDatosClienteForm) this.formBusqueda;
		
		String codigoCliente = consultaHIPDatosClienteForm.getCodigoCliente();
		
		//-- Seteamos el Codigo de Pais y Codigo de Sistema
		Map criteria = new HashMap();
		criteria.put("codigoPais", this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
		criteria.put("codigoSistema", "OCR");
		
		//-- obtenemos los valores para comunicarse con el servidor FTP 
		criteria.put("nombreParametro", "servidorFtpSC");
		String servidorFtpSC = stoService.getParametroGenericoSistema(criteria);
		criteria.put("nombreParametro", "puertoFtpSC");
		String puertoFtpSC = stoService.getParametroGenericoSistema(criteria);
		criteria.put("nombreParametro", "directorioFtpSC");
		String directorioFtpSC = stoService.getParametroGenericoSistema(criteria);
		criteria.put("nombreParametro", "usuarioFtpSC");
		String usuarioFtpSC = stoService.getParametroGenericoSistema(criteria);
		criteria.put("nombreParametro", "passwordFtpSC");
		String passwordFtpSC = stoService.getParametroGenericoSistema(criteria);
		criteria.put("nombreParametro", "directorioTempSC");
		String directorioTempSC = stoService.getParametroGenericoSistema(criteria);
		criteria.put("nombreParametro", "scaleFitSC");
		String scaleFitSC = stoService.getParametroGenericoSistema(criteria);
		
		//Generamos el pdf en base a las imagenes recuperas del servidor FTP
		ImagenPDFUtil imagenUtil = new ImagenPDFUtil();
		try {
			imagenUtil.generarPdfFtpToLocal(servidorFtpSC, puertoFtpSC, directorioFtpSC + codigoCliente, 
											usuarioFtpSC, passwordFtpSC, "jpg",	directorioTempSC, 
											codigoCliente, Long.parseLong(scaleFitSC));
		
		
			//Una vez generado el archivo PDF, lo mostramos.
			String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/") + "/resources/";
			
			File inputFile = new File(directorioTempSC + codigoCliente + ".pdf");
			File tempFile = new File(path + codigoCliente + ".pdf");
			FileUtils.copyFile(inputFile, tempFile);
			
			this.outfileMedia = "/resources/"+ codigoCliente + ".pdf";
			this.viewReporteMedia = true;

			
		}
		catch(Exception e){
			e.printStackTrace();
			this.viewReporteMedia = false;
			this.outfileMedia = "";
			log.error("No se pudo eliminar el archivo :" + codigoCliente + ".pdf");
		}
		
	}
	
	// metodo que sale del popup	
	public void salirAPantallaPadre(ActionEvent actionEvent) {
		try {			
			this.redireccionarPagina(this.paginaPadre);			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}	
			
	}
	

	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return null;
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


	/**
	 * @return the mostrarPopupHIPCliente
	 */
	public boolean isMostrarPopupHIPCliente() {
		return mostrarPopupHIPCliente;
	}

	/**
	 * @param mostrarPopupHIPCliente the mostrarPopupHIPCliente to set
	 */
	public void setMostrarPopupHIPCliente(boolean mostrarPopupHIPCliente) {
		this.mostrarPopupHIPCliente = mostrarPopupHIPCliente;
	}

	/**
	 * @return the busquedaHIPClientePOPUPSearchAction
	 */
	public BusquedaHIPClientePOPUPSearchAction getBusquedaHIPClientePOPUPSearchAction() {
		return busquedaHIPClientePOPUPSearchAction;
	}

	/**
	 * @param busquedaHIPClientePOPUPSearchAction the busquedaHIPClientePOPUPSearchAction to set
	 */
	public void setBusquedaHIPClientePOPUPSearchAction(
			BusquedaHIPClientePOPUPSearchAction busquedaHIPClientePOPUPSearchAction) {
		this.busquedaHIPClientePOPUPSearchAction = busquedaHIPClientePOPUPSearchAction;
	}
	
	/**
	 * @return the hipOpcionesList
	 */
	public List getHipOpcionesList() {
		return hipOpcionesList;
	}

	/**
	 * @param hipOpcionesList the hipOpcionesList to set
	 */
	public void setHipOpcionesList(List hipOpcionesList) {
		this.hipOpcionesList = hipOpcionesList;
	}

	/**
	 * @return the hipTipoClienteList
	 */
	public List getHipTipoClienteList() {
		return hipTipoClienteList;
	}

	/**
	 * @param hipTipoClienteList the hipTipoClienteList to set
	 */
	public void setHipTipoClienteList(List hipTipoClienteList) {
		this.hipTipoClienteList = hipTipoClienteList;
	}

	/**
	 * @return the hipSubTipoClienteList
	 */
	public List getHipSubTipoClienteList() {
		return hipSubTipoClienteList;
	}

	/**
	 * @param hipSubTipoClienteList the hipSubTipoClienteList to set
	 */
	public void setHipSubTipoClienteList(List hipSubTipoClienteList) {
		this.hipSubTipoClienteList = hipSubTipoClienteList;
	}

	/**
	 * @return the hipTipoClasificacionList
	 */
	public List getHipTipoClasificacionList() {
		return hipTipoClasificacionList;
	}

	/**
	 * @param hipTipoClasificacionList the hipTipoClasificacionList to set
	 */
	public void setHipTipoClasificacionList(List hipTipoClasificacionList) {
		this.hipTipoClasificacionList = hipTipoClasificacionList;
	}

	/**
	 * @return the hipClasificacionList
	 */
	public List getHipClasificacionList() {
		return hipClasificacionList;
	}

	/**
	 * @param hipClasificacionList the hipClasificacionList to set
	 */
	public void setHipClasificacionList(List hipClasificacionList) {
		this.hipClasificacionList = hipClasificacionList;
	}

	/**
	 * @return the hipMediosContactosList
	 */
	public List getHipMediosContactosList() {
		return hipMediosContactosList;
	}

	/**
	 * @param hipMediosContactosList the hipMediosContactosList to set
	 */
	public void setHipMediosContactosList(List hipMediosContactosList) {
		this.hipMediosContactosList = hipMediosContactosList;
	}

	/**
	 * @return the hipUnidadesAdministrativasList
	 */
	public List getHipUnidadesAdministrativasList() {
		return hipUnidadesAdministrativasList;
	}

	/**
	 * @param hipUnidadesAdministrativasList the hipUnidadesAdministrativasList to set
	 */
	public void setHipUnidadesAdministrativasList(
			List hipUnidadesAdministrativasList) {
		this.hipUnidadesAdministrativasList = hipUnidadesAdministrativasList;
	}

	/**
	 * @return the hipReclamosCabeceraList
	 */
	public List getHipReclamosCabeceraList() {
		return hipReclamosCabeceraList;
	}

	/**
	 * @param hipReclamosCabeceraList the hipReclamosCabeceraList to set
	 */
	public void setHipReclamosCabeceraList(List hipReclamosCabeceraList) {
		this.hipReclamosCabeceraList = hipReclamosCabeceraList;
	}

	/**
	 * @return the hipCuentaCorrientesMovimientosList
	 */
	public List getHipCuentaCorrientesMovimientosList() {
		return hipCuentaCorrientesMovimientosList;
	}

	/**
	 * @param hipCuentaCorrientesMovimientosList the hipCuentaCorrientesMovimientosList to set
	 */
	public void setHipCuentaCorrientesMovimientosList(
			List hipCuentaCorrientesMovimientosList) {
		this.hipCuentaCorrientesMovimientosList = hipCuentaCorrientesMovimientosList;
	}

	/**
	 * @return the hipPedidosConsultoraList
	 */
	public List getHipPedidosConsultoraList() {
		return hipPedidosConsultoraList;
	}

	/**
	 * @param hipPedidosConsultoraList the hipPedidosConsultoraList to set
	 */
	public void setHipPedidosConsultoraList(List hipPedidosConsultoraList) {
		this.hipPedidosConsultoraList = hipPedidosConsultoraList;
	}

	/**
	 * @return the hipConcursosBolsaFaltantesList
	 */
	public List getHipConcursosBolsaFaltantesList() {
		return hipConcursosBolsaFaltantesList;
	}

	/**
	 * @param hipConcursosBolsaFaltantesList the hipConcursosBolsaFaltantesList to set
	 */
	public void setHipConcursosBolsaFaltantesList(
			List hipConcursosBolsaFaltantesList) {
		this.hipConcursosBolsaFaltantesList = hipConcursosBolsaFaltantesList;
	}

	/**
	 * @return the hipVinculosReferenciasList
	 */
	public List getHipVinculosReferenciasList() {
		return hipVinculosReferenciasList;
	}

	/**
	 * @param hipVinculosReferenciasList the hipVinculosReferenciasList to set
	 */
	public void setHipVinculosReferenciasList(List hipVinculosReferenciasList) {
		this.hipVinculosReferenciasList = hipVinculosReferenciasList;
	}

	/**
	 * @return the hipSolicitudesPolizaList
	 */
	public List getHipSolicitudesPolizaList() {
		return hipSolicitudesPolizaList;
	}

	/**
	 * @param hipSolicitudesPolizaList the hipSolicitudesPolizaList to set
	 */
	public void setHipSolicitudesPolizaList(List hipSolicitudesPolizaList) {
		this.hipSolicitudesPolizaList = hipSolicitudesPolizaList;
	}

	/**
	 * @return the hipHistoricoCargosPolizaList
	 */
	public List getHipHistoricoCargosPolizaList() {
		return hipHistoricoCargosPolizaList;
	}

	/**
	 * @param hipHistoricoCargosPolizaList the hipHistoricoCargosPolizaList to set
	 */
	public void setHipHistoricoCargosPolizaList(List hipHistoricoCargosPolizaList) {
		this.hipHistoricoCargosPolizaList = hipHistoricoCargosPolizaList;
	}

	/**
	 * @return the mostrarDigitacionSimplificada
	 */
	public String getMostrarDigitacionSimplificada() {
		return mostrarDigitacionSimplificada;
	}

	/**
	 * @param mostrarDigitacionSimplificada the mostrarDigitacionSimplificada to set
	 */
	public void setMostrarDigitacionSimplificada(
			String mostrarDigitacionSimplificada) {
		this.mostrarDigitacionSimplificada = mostrarDigitacionSimplificada;
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
	 * @return the nroColumnas
	 */
	public String getNroColumnas() {
		return nroColumnas;
	}

	/**
	 * @param nroColumnas the nroColumnas to set
	 */
	public void setNroColumnas(String nroColumnas) {
		this.nroColumnas = nroColumnas;
	}

	/**
	 * @return the lstParametros
	 */
	public List getLstParametros() {
		return lstParametros;
	}

	/**
	 * @param lstParametros the lstParametros to set
	 */
	public void setLstParametros(List lstParametros) {
		this.lstParametros = lstParametros;
	}

	/**
	 * @return the viewResults
	 */
	public boolean isViewResults() {
		return viewResults;
	}

	/**
	 * @param viewResults the viewResults to set
	 */
	public void setViewResults(boolean viewResults) {
		this.viewResults = viewResults;
	}

	/**
	 * @return the valPara
	 */
	public boolean isValPara() {
		return valPara;
	}

	/**
	 * @param valPara the valPara to set
	 */
	public void setValPara(boolean valPara) {
		this.valPara = valPara;
	}

	/**
	 * @return the hipDtoDatosCliente
	 */
	public ConsultaHIPDatosCliente getHipDtoDatosCliente() {
		return hipDtoDatosCliente;
	}

	/**
	 * @param hipDtoDatosCliente the hipDtoDatosCliente to set
	 */
	public void setHipDtoDatosCliente(ConsultaHIPDatosCliente hipDtoDatosCliente) {
		this.hipDtoDatosCliente = hipDtoDatosCliente;
	}

	/**
	 * @return the pageOpcionConsulta
	 */
	public String getPageOpcionConsulta() {
		return pageOpcionConsulta;
	}

	/**
	 * @param pageOpcionConsulta the pageOpcionConsulta to set
	 */
	public void setPageOpcionConsulta(String pageOpcionConsulta) {
		this.pageOpcionConsulta = pageOpcionConsulta;
	}

	/**
	 * @return the opcionConsulta
	 */
	public String getOpcionConsulta() {
		return opcionConsulta;
	}

	/**
	 * @param opcionConsulta the opcionConsulta to set
	 */
	public void setOpcionConsulta(String opcionConsulta) {
		this.opcionConsulta = opcionConsulta;
	}

	public MantenimientoOCRCapturaPedidosAction getMantenimientoOCRCapturaPedidosAction() {
		return mantenimientoOCRCapturaPedidosAction;
	}

	public void setMantenimientoOCRCapturaPedidosAction(
			MantenimientoOCRCapturaPedidosAction mantenimientoOCRCapturaPedidosAction) {
		this.mantenimientoOCRCapturaPedidosAction = mantenimientoOCRCapturaPedidosAction;
	}

	
	public boolean isMostrarReporteImagenesSC() {
		return mostrarReporteImagenesSC;
	}

	public void setMostrarReporteImagenesSC(boolean mostrarReporteImagenesSC) {
		this.mostrarReporteImagenesSC = mostrarReporteImagenesSC;
	}

	

	/**
	 * @return the outfileMedia
	 */
	public String getOutfileMedia() {
		return outfileMedia;
	}

	/**
	 * @param outfileMedia the outfileMedia to set
	 */
	public void setOutfileMedia(String outfileMedia) {
		this.outfileMedia = outfileMedia;
	}

	/**
	 * @return the viewReporteMedia
	 */
	public boolean isViewReporteMedia() {
		return viewReporteMedia;
	}

	/**
	 * @param viewReporteMedia the viewReporteMedia to set
	 */
	public void setViewReporteMedia(boolean viewReporteMedia) {
		this.viewReporteMedia = viewReporteMedia;
	}

	/**
	 * @return the mostrarPDFEscaneoImagenes
	 */
	public boolean isMostrarPDFEscaneoImagenes() {
		return mostrarPDFEscaneoImagenes;
	}

	/**
	 * @param mostrarPDFEscaneoImagenes the mostrarPDFEscaneoImagenes to set
	 */
	public void setMostrarPDFEscaneoImagenes(boolean mostrarPDFEscaneoImagenes) {
		this.mostrarPDFEscaneoImagenes = mostrarPDFEscaneoImagenes;
	}

	/**
	 * @return the paginaPadre
	 */
	public String getPaginaPadre() {
		return paginaPadre;
	}

	/**
	 * @param paginaPadre the paginaPadre to set
	 */
	public void setPaginaPadre(String paginaPadre) {
		this.paginaPadre = paginaPadre;
	}

	/**
	 * @return the indicadorDatosPoliza
	 */
	public String getIndicadorDatosPoliza() {
		return indicadorDatosPoliza;
	}

	/**
	 * @param indicadorDatosPoliza the indicadorDatosPoliza to set
	 */
	public void setIndicadorDatosPoliza(String indicadorDatosPoliza) {
		this.indicadorDatosPoliza = indicadorDatosPoliza;
	}

	/**
	 * @return the indicadorSituacionFlx
	 */
	public String getIndicadorSituacionFlx() {
		return indicadorSituacionFlx;
	}

	/**
	 * @param indicadorSituacionFlx the indicadorSituacionFlx to set
	 */
	public void setIndicadorSituacionFlx(String indicadorSituacionFlx) {
		this.indicadorSituacionFlx = indicadorSituacionFlx;
	}

	/**
	 * @return the numeroColumnasDatosGenerales
	 */
	public int getNumeroColumnasDatosGenerales() {
		return numeroColumnasDatosGenerales;
	}

	/**
	 * @param numeroColumnasDatosGenerales the numeroColumnasDatosGenerales to set
	 */
	public void setNumeroColumnasDatosGenerales(int numeroColumnasDatosGenerales) {
		this.numeroColumnasDatosGenerales = numeroColumnasDatosGenerales;
	}

	/**
	 * @return the numeroColumnasDatosDespacho
	 */
	public int getNumeroColumnasDatosDespacho() {
		return numeroColumnasDatosDespacho;
	}

	/**
	 * @param numeroColumnasDatosDespacho the numeroColumnasDatosDespacho to set
	 */
	public void setNumeroColumnasDatosDespacho(int numeroColumnasDatosDespacho) {
		this.numeroColumnasDatosDespacho = numeroColumnasDatosDespacho;
	}

	/**
	 * @return the indicadorLider
	 */
	public boolean isIndicadorLider() {
		return indicadorLider;
	}

	/**
	 * @param indicadorLider the indicadorLider to set
	 */
	public void setIndicadorLider(boolean indicadorLider) {
		this.indicadorLider = indicadorLider;
	}

	/**
	 * @return the indicadorProgramaEjecutivasActivo
	 */
	public boolean isIndicadorProgramaEjecutivasActivo() {
		return indicadorProgramaEjecutivasActivo;
	}

	/**
	 * @param indicadorProgramaEjecutivasActivo the indicadorProgramaEjecutivasActivo to set
	 */
	public void setIndicadorProgramaEjecutivasActivo(
			boolean indicadorProgramaEjecutivasActivo) {
		this.indicadorProgramaEjecutivasActivo = indicadorProgramaEjecutivasActivo;
	}

	/**
	 * @return the mantenimientoRECDigitacionCDRAjaxAction
	 */
	public MantenimientoRECDigitacionCDRAjaxAction getMantenimientoRECDigitacionCDRAjaxAction() {
		return mantenimientoRECDigitacionCDRAjaxAction;
	}

	/**
	 * @param mantenimientoRECDigitacionCDRAjaxAction the mantenimientoRECDigitacionCDRAjaxAction to set
	 */
	public void setMantenimientoRECDigitacionCDRAjaxAction(
			MantenimientoRECDigitacionCDRAjaxAction mantenimientoRECDigitacionCDRAjaxAction) {
		this.mantenimientoRECDigitacionCDRAjaxAction = mantenimientoRECDigitacionCDRAjaxAction;
	}		
	
	/**
	 * @return the numeroColumnasDatosDomicilio
	 */
	public int getNumeroColumnasDatosDomicilio() {
		return numeroColumnasDatosDomicilio;
	}

	/**
	 * @param numeroColumnasDatosDomicilio the numeroColumnasDatosDomicilio to set
	 */
	public void setNumeroColumnasDatosDomicilio(int numeroColumnasDatosDomicilio) {
		this.numeroColumnasDatosDomicilio = numeroColumnasDatosDomicilio;
	}

	/**
	 * @return the hipEtapasCobroList
	 */
	public List getHipEtapasCobroList() {
		return hipEtapasCobroList;
	}

	/**
	 * @param hipEtapasCobroList the hipEtapasCobroList to set
	 */
	public void setHipEtapasCobroList(List hipEtapasCobroList) {
		this.hipEtapasCobroList = hipEtapasCobroList;
	}

	/**
	 * @return the cabeceraConsultoraCastigadaList
	 */
	public List getCabeceraConsultoraCastigadaList() {
		return cabeceraConsultoraCastigadaList;
	}

	/**
	 * @param cabeceraConsultoraCastigadaList the cabeceraConsultoraCastigadaList to set
	 */
	public void setCabeceraConsultoraCastigadaList(
			List cabeceraConsultoraCastigadaList) {
		this.cabeceraConsultoraCastigadaList = cabeceraConsultoraCastigadaList;
	}

	/**
	 * @return the detalleConsultoraCastigadaList
	 */
	public List getDetalleConsultoraCastigadaList() {
		return detalleConsultoraCastigadaList;
	}

	/**
	 * @param detalleConsultoraCastigadaList the detalleConsultoraCastigadaList to set
	 */
	public void setDetalleConsultoraCastigadaList(
			List detalleConsultoraCastigadaList) {
		this.detalleConsultoraCastigadaList = detalleConsultoraCastigadaList;
	}

	/**
	 * @return the cuentaCorrienteConsultoraCastigadaList
	 */
	public List getCuentaCorrienteConsultoraCastigadaList() {
		return cuentaCorrienteConsultoraCastigadaList;
	}

	/**
	 * @param cuentaCorrienteConsultoraCastigadaList the cuentaCorrienteConsultoraCastigadaList to set
	 */
	public void setCuentaCorrienteConsultoraCastigadaList(
			List cuentaCorrienteConsultoraCastigadaList) {
		this.cuentaCorrienteConsultoraCastigadaList = cuentaCorrienteConsultoraCastigadaList;
	}

	/**
	 * @return the codigoCastigada
	 */
	public String getCodigoCastigada() {
		return codigoCastigada;
	}

	/**
	 * @param codigoCastigada the codigoCastigada to set
	 */
	public void setCodigoCastigada(String codigoCastigada) {
		this.codigoCastigada = codigoCastigada;
	}

	/**
	 * @return the cedulaCastigada
	 */
	public String getCedulaCastigada() {
		return cedulaCastigada;
	}

	/**
	 * @param cedulaCastigada the cedulaCastigada to set
	 */
	public void setCedulaCastigada(String cedulaCastigada) {
		this.cedulaCastigada = cedulaCastigada;
	}

	/**
	 * @return the nombresApellidosCastigada
	 */
	public String getNombresApellidosCastigada() {
		return nombresApellidosCastigada;
	}

	/**
	 * @param nombresApellidosCastigada the nombresApellidosCastigada to set
	 */
	public void setNombresApellidosCastigada(String nombresApellidosCastigada) {
		this.nombresApellidosCastigada = nombresApellidosCastigada;
	}

	/**
	 * @return the regionCastigada
	 */
	public String getRegionCastigada() {
		return regionCastigada;
	}

	/**
	 * @param regionCastigada the regionCastigada to set
	 */
	public void setRegionCastigada(String regionCastigada) {
		this.regionCastigada = regionCastigada;
	}

	/**
	 * @return the zonaCastigada
	 */
	public String getZonaCastigada() {
		return zonaCastigada;
	}

	/**
	 * @param zonaCastigada the zonaCastigada to set
	 */
	public void setZonaCastigada(String zonaCastigada) {
		this.zonaCastigada = zonaCastigada;
	}

	/**
	 * @return the rellenarCeroConsultoraSoloconEnter
	 */
	public boolean isRellenarCeroConsultoraSoloconEnter() {
		return rellenarCeroConsultoraSoloconEnter;
	}

	/**
	 * @param rellenarCeroConsultoraSoloconEnter the rellenarCeroConsultoraSoloconEnter to set
	 */
	public void setRellenarCeroConsultoraSoloconEnter(
			boolean rellenarCeroConsultoraSoloconEnter) {
		this.rellenarCeroConsultoraSoloconEnter = rellenarCeroConsultoraSoloconEnter;
	}
	
	
	
}
