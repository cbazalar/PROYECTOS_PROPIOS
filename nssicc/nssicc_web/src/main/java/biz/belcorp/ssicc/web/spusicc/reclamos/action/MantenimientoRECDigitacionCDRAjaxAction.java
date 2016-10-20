package biz.belcorp.ssicc.web.spusicc.reclamos.action;

import java.util.ArrayList;
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

import org.apache.commons.lang.StringUtils;
import org.primefaces.event.SelectEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValueCDR;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.dao.model.ReclamoDigitadoCabecera;
import biz.belcorp.ssicc.dao.model.ReclamoDigitadoDetalle;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.sisicc.model.OperacionesResultado;
import biz.belcorp.ssicc.dao.spusicc.lec.model.Ranking;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.spusicc.sto.model.AccionTipoDocumento;
import biz.belcorp.ssicc.dao.spusicc.sto.model.DocumentoDigitadoPK;
import biz.belcorp.ssicc.dao.spusicc.sto.model.DocumentoReferencia;
import biz.belcorp.ssicc.dao.spusicc.sto.model.GestionDocumento;
import biz.belcorp.ssicc.dao.spusicc.sto.model.TipoDocumentoDigitadoPK;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECDigitacionCDRService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEjecucionValidacionesService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOLevantamientoErroresValidacionService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOService;
import biz.belcorp.ssicc.service.spusicc.sto.framework.ProcesoSTOExecutionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.reclamos.form.MantenimientoRECDigitacionCDRAjaxForm;

@ManagedBean
@SessionScoped
public class MantenimientoRECDigitacionCDRAjaxAction extends BaseMantenimientoSearchAbstractAction {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	private String devolucionEspecial;
	
	private List mantenimientoRECDigitacionCDRDetallesDigitadosList;
	private List recOperacionParametrosList;
	private List lstCodMotRechazo;
	
	private static final String POPUP_RECDOCREF = "RECDOCREF";
	
	/** The busqueda rec documento referencia search action. */
	@ManagedProperty(value="#{busquedaRECDocumentoReferenciaSearchAction}")
	private BusquedaRECDocumentoReferenciaSearchAction busquedaRECDocumentoReferenciaSearchAction;
	
	private boolean mostrarBotonBuscarDOCREF;
	
	private List stoDevolucionesList = null;
	private List stoCambiosList = null;
	private List stoFfneList =null;
	private List stoOtrosList = null;
	private List stoDocumentosReferenciaList = null;
	private List stoCodigoVentaPedidoList = null;
	private List stoCodigoVentaMatrizList = null;
	
	/* CAMPOS DE LA PANTALLA BUSQUEDA CODIGO DE VENTA DE PEDIDO*/
	private String indice;
	private String cajaTexto;
	private String numeroCruce;
	private String numPedido;
	private String consejera;
	private String codigoSAP;
	private String codigoVenta;
	private String descripcion;
	private String indicadorBOLELEC;
	private String numeroCruceX;
	
	/* CAMPOS DE LA PANTALLA BUSQUEDA CODIGO DE MATRIZ O PREMIO*/
	private String metParam;
	private String indiceMatriz;
	private String cajaTextoMatriz;
	private String numeroCruceMatriz;
	private String codigoPeriodo;
	private String codigoSAPMatriz;
	private String codigoVentaMatriz;
	private String descripcionMatriz;
	private String matriz;
	private String precioInicial;
	private String precioFinal;
	
	private boolean seleccionable;
	
	private String paginaPadre="";
	private boolean activarBuscar = false;
	private boolean rellenarCeroConsultoraSoloconEnter = false;
	
	@Override
	protected void setInvocarPopup(String accion) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setAceptarPopup' method");
		}
		
		if(StringUtils.equals(accion,this.POPUP_RECDOCREF)){
			this.busquedaRECDocumentoReferenciaSearchAction.view();
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setAceptarPopup(javax.faces.event.ActionEvent, java.lang.String)
	 */
	@Override
	protected void setAceptarPopup(ActionEvent event, String accion) {		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setAceptarPopup' method");
		}
		
		if(StringUtils.equals(accion,this.POPUP_RECDOCREF)){
			MantenimientoRECDigitacionCDRAjaxForm f = (MantenimientoRECDigitacionCDRAjaxForm)this.formBusqueda;
			this.busquedaRECDocumentoReferenciaSearchAction.verificarRegistro(event);
			
			if(this.busquedaRECDocumentoReferenciaSearchAction.isSeleccionoRegistro()){
				DocumentoReferencia documentoReferencia = (DocumentoReferencia)this.busquedaRECDocumentoReferenciaSearchAction.getBeanRegistroSeleccionado();
				f.setNumeroBoletaDespacho(documentoReferencia.getNumeroSolicitud());
				this.busquedaRECDocumentoReferenciaSearchAction.setBeanRegistroSeleccionado(null);
				this.mostrarBotonBuscarDOCREF = false;
				this.getRequestContext().execute("setDatos()");
			}
		}

	}
	
	public void seleccionarDocumentoReferencia(SelectEvent event) {
		this.beanRegistroSeleccionado = event.getObject();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setSalirPopup()
	 */
	@Override
	protected void setSalirPopup() {		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setSalirPopup' method");
		}	
		if(StringUtils.equals(accion,this.POPUP_RECDOCREF)){
			this.busquedaRECDocumentoReferenciaSearchAction.setBeanRegistroSeleccionado(null);
		}
		
	}
	
	
	/**
	 * Metodo que se ejecuta para cargar data inicial del Manage a traves de AJAX
	 * @param actionEvent
	 */
	public void viewAjax(ActionEvent actionEvent) throws Exception {
		try {
			log.debug("Entering view (Ajax)' - method");
			this.viewLogicaNegocio();
			if(this.activarBuscar)
				this.find();
		}
		catch(Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAtributes' method");
		}
		this.activarHotkeyEstandar = false;
		this.mostrarCriteriosBusqueda = false;
		
		MantenimientoRECDigitacionCDRAjaxForm f = (MantenimientoRECDigitacionCDRAjaxForm)this.formBusqueda;
		f.setCodigoPais(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
		
		if(this.getParametrosPantalla().containsKey("indicadorOnline")){
			f.setIndicadorOnline((String)this.getParametrosPantalla().get("indicadorOnline"));
		}

		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
        criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente  

        MantenimientoOCRPedidoControlFacturacionService serviceFact = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");        
        PedidoControlFacturacion controlFacturacion = serviceFact.getControlFacturacionById(criteria);
        f.setPeriodoActivo(controlFacturacion.getCodigoPeriodo());

        this.mantenimientoRECDigitacionCDRDetallesDigitadosList = null;
        f.setIndicadorModifica("0");
		this.loadCombos();

		MantenimientoRECDigitacionCDRService service = 
				(MantenimientoRECDigitacionCDRService)getBean("spusicc.mantenimientoRECDigitacionCDRService");
		this.recOperacionParametrosList = service.getParametrosOperacionesReclamos();
		

		ProcesoSTOEjecucionValidacionesService procesoSTOEjecucionValidacionesService = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
		Map criteriaMinimo = new HashMap();
    	criteriaMinimo.put("codigoPais",f.getCodigoPais());
    	criteriaMinimo.put("codigoParametro",Constants.REC_INDICADOR_VALIDA_DEVOLUCION);
    	f.setIndicadorValidaDevolucion(procesoSTOEjecucionValidacionesService.getParametroSTO(criteriaMinimo));
    	
    	criteriaMinimo.put("codigoParametro",Constants.REC_INDICADOR_VALIDA_FALTANTES);
    	f.setIndicadorValidaFaltantes(procesoSTOEjecucionValidacionesService.getParametroSTO(criteriaMinimo));
    	
    	criteriaMinimo.put("codigoParametro",Constants.REC_INDICADOR_VALIDA_CAMBIOS);
    	f.setIndicadorValidaCambios(procesoSTOEjecucionValidacionesService.getParametroSTO(criteriaMinimo));

    	//Se obtiene el parametro de indicador de pedido chequeado, solo para la opcion en linea
    	if (StringUtils.isNotEmpty(f.getIndicadorOnline())
    			&& StringUtils.equals(f.getIndicadorOnline(), "S")){
    		String sIndPedido = service.getIndicadorPedidoChequeado();
        	f.setIndicadorPedidoChequeado(sIndPedido);	
    	}
    	limpiar(f);
    	f.setNumeroCDR("");
    	f.setNumeroBoletaDespacho("");
    	f.setCodigoConsejera("");
    	f.setNombreConsejera("");
    	f.setPeriodo("");
    	f.setZona("");

    	/* INI JR PER-SiCC-2012-0304 */
    	//Obteniendo el parametro para mostra el Rechazo CDR
    	f.setMuestraIndicador(service.getIndicadorCDRRechazo(criteria));
    	f.setObservacionCDR(null);
    	f.setIndicadorCDRRechazo(Constants.NUMERO_CERO);
    	/* FIN JR PER-SiCC-2012-0304 */
    	
    	f.setPeriodoCDR("");
    	criteria.put("codigoParametro", Constants.STO_IND_CDR_LINEA);
    	
    	String valorParametro = procesoSTOEjecucionValidacionesService.getParametroSTO(criteria);
    	f.setIndicadorValCDRLinea(valorParametro);
    	
    	//Obteniendo el dato de Envio de BR
		Map criteria1 = new HashMap();
		criteria1.put("codigoPais", this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
		criteria1.put("codigoParametro", Constants.STO_IND_VAL_ACEP_CDR);
		String valor = service.getSTOParametroGeneralOCR(criteria1);
		
		f.setIndicadorValorAceptaCDR(valor);
    	
    	// PER-SiCC-2012-0642 
    	this.lstCodMotRechazo = service.getCodigoMotivoRechazo();
    	f.setCodigoMotivoRechazoDef(null);
    	
    	f.setUsuario(this.getmPantallaPrincipalBean().getCodigoUsuario());
    	
    	//Obtenemos el parametro de validacion de oferta
    	criteria.put("codigoParametro", Constants.STO_IND_DEV_OFERTA);
    	String indicadorDevolucionOferta = procesoSTOEjecucionValidacionesService.getParametroSTO(criteria);
    	
    	f.setIndicadorDevolucionOferta(StringUtils.isBlank(indicadorDevolucionOferta)? Constants.NUMERO_CERO:indicadorDevolucionOferta);
    	//
    	
    	//Obtenemos los parametros Adicionales para validar TRUEQUES
    	criteria.put("codigoParametro", Constants.STO_IND_CDR_LINEA_18);
    	String indicadorLinea18 = procesoSTOEjecucionValidacionesService.getParametroSTO(criteria);    	
    	f.setIndicadorLinea18(StringUtils.isBlank(indicadorLinea18)?"":indicadorLinea18);
    	
    	criteria.put("codigoParametro", Constants.STO_DESV_TRQ);
    	String valorDesviacionPrecioUnitarioTrueque = procesoSTOEjecucionValidacionesService.getParametroSTO(criteria);
    	f.setValorDesviacionPrecioUnitarioTrueque(StringUtils.isBlank(valorDesviacionPrecioUnitarioTrueque)?"":valorDesviacionPrecioUnitarioTrueque);
    	//
    	
    	//Obtenemos el parametro de validacion PRODUCTO QUE CAMBIA IGUAL DE PORDUCTO QUE DESEA
    	criteria.put("codigoParametro", Constants.STO_IND_CAMB_IGUA);
    	String indicadorProductoCambiaIgualDesea = procesoSTOEjecucionValidacionesService.getParametroSTO(criteria);
    	f.setIndicadorProductoCambiaIgualDesea(StringUtils.isBlank(indicadorProductoCambiaIgualDesea)?"":indicadorProductoCambiaIgualDesea);
    	//
    	
    	//Obtenemos el parametro codigo de cliente que luego sera enviado en la busqueda de documentos de referencia (Nro perido)
    	if(this.getParametrosPantalla().containsKey("codigoCliente")){
    		String codigoCliente = (String)this.getParametrosPantalla().get("codigoCliente");
    		f.setCodigoClienteDocumentoReferencia(codigoCliente);
    	} else
    		f.setCodigoClienteDocumentoReferencia("");
    	//
    	
    	//Obtenemos el parametro de operacion de TRUEQUE con valor absoluto
    	criteria.put("codigoParametro", Constants.STO_DESV_TRQ_OPER);
    	String indicadorDesviacionPrecioUnitarioTruequeValorAbsoluto = procesoSTOEjecucionValidacionesService.getParametroSTO(criteria);
    	f.setIndicadorDesviacionPrecioUnitarioTruequeValorAbsoluto(StringUtils.isBlank(indicadorDesviacionPrecioUnitarioTruequeValorAbsoluto)?"":indicadorDesviacionPrecioUnitarioTruequeValorAbsoluto);
		
		this.mostrarBotonEliminar = false;
		this.mostrarBotonModificar = false;
		this.mostrarBotonConsultar = false;
		this.mostrarBotonNuevo = false;
		this.mostrarBotonSalir = false;
		this.mostrarBotonBuscar = false;
		
		//Obtenes valor del parametro para la pantalla de Digitacion Simplificada
		GenericoService genericoService1 = (GenericoService)this.getBeanService("genericoService");	
		List lstParametros1 = new ArrayList();
		ParametroPais parametroPais1 = new ParametroPais();
		parametroPais1.setCodigoPais(this.mPantallaPrincipalBean.getCountryCode());
		parametroPais1.setCodigoSistema(Constants.HIP_CODIGO_SISTEMA);
		parametroPais1.setCodigoParametro(Constants.HIP_CODIGO_PARAMETRO_RELLENAR_SOLO_ENTER);
		parametroPais1.setNombreParametro(Constants.HIP_NOMBRE_PARAMETRO_RELLENAR_SOLO_ENTER);
        lstParametros1 = genericoService1.getParametrosPais(parametroPais1);
		
		String rellenarConsultoraSoloEnter = Constants.NO;
		if(lstParametros1 != null && lstParametros1.size() > 0){			
			ParametroPais ps = (ParametroPais)lstParametros1.get(0);
			rellenarConsultoraSoloEnter = ps.getValorParametro();
		}
		if (StringUtils.equals(Constants.SI, rellenarConsultoraSoloEnter))
			this.rellenarCeroConsultoraSoloconEnter = true;
		
	}
	
	/**
	 * Load combos.
	 */
	private void loadCombos() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'loadCombos' method");
		}
		MantenimientoRECDigitacionCDRAjaxForm form = (MantenimientoRECDigitacionCDRAjaxForm)this.formBusqueda;
		List listaOperaciones = new ArrayList();
		List listaMotivo = new ArrayList();
		
		Map criteria = new HashMap();
		
		MantenimientoRECDigitacionCDRService service = (MantenimientoRECDigitacionCDRService)getBean("spusicc.mantenimientoRECDigitacionCDRService");
		listaOperaciones = service.getOperacionesReclamos(criteria);
		listaMotivo = service.getMotivosReclamos(criteria);
		
		//--------------------------------------
		String selectOperacionDeuda = "";
		
		String selectOperacionDeudaPrimero  = "<select styleId='listaOperacion' name='listaOperacion' id='listaOperacion' onchange='bloquearComponentes(this); ejecutarFunciones(1);' onFocus='enfocar(1);' onKeyDown='return keyDownOperacion(this, event);'>";
		String selectOperacion = "<select styleId='listaOperacion' name='listaOperacion' id='listaOperacion' onchange='bloquearComponentes(this); ejecutarFunciones(1);'  onFocus='enfocar(1);' onKeyDown='return keyDownOperacion(this, event);'>";
		for (int i = 0; i < listaOperaciones.size(); i++) {					
			//Base base = new Base(); Se cambio por OperacionesREsultado donde se encuentra mas data
			OperacionesResultado base=(OperacionesResultado)listaOperaciones.get(i);
			selectOperacion+="<option value='"+base.getCodigo()+"|"+base.getTipoOperSicc() +"|"+base.getCodOperSicc()  +"|"+base.getCambVali()+ "|" + base.getCambObli() + "|" + base.getDeseVali()  +"'>";	//se enlaza para obtener el tipo de operacion		
			selectOperacion+=base.getDescripcion();
			selectOperacion+="</option>";
			
			if(base.getCodigo().equals("D")){
				selectOperacionDeudaPrimero+="<option value='"+base.getCodigo()+"|"+base.getTipoOperSicc() +"|"+base.getCodOperSicc()  +"|"+base.getCambVali()+ "|" + base.getCambObli() + "|" + base.getDeseVali() +"'>";	//se enlaza para obtener el tipo de operacion		
				selectOperacionDeudaPrimero+=base.getDescripcion();
				selectOperacionDeudaPrimero+="</option>";
			}else{
				if(base.getCodigo().equals("N")){
					this.devolucionEspecial = base.getCodigo()+"|"+base.getTipoOperSicc() +"|"+base.getCodOperSicc()  +"|"+base.getCambVali()+ "|" + base.getCambObli() + "|" + base.getDeseVali();
				}
				selectOperacionDeuda+="<option value='"+base.getCodigo()+"|"+base.getTipoOperSicc() +"|"+base.getCodOperSicc()  +"|"+base.getCambVali()+ "|" + base.getCambObli() + "|" + base.getDeseVali() +"'>";	//se enlaza para obtener el tipo de operacion		
				selectOperacionDeuda+=base.getDescripcion();
				selectOperacionDeuda+="</option>";
			}
		}
		selectOperacionDeudaPrimero += selectOperacionDeuda;
		selectOperacionDeudaPrimero+="</select>";
		
		selectOperacion+="</select>";
		
		form.setStrComboOperacion(selectOperacion);
		form.setStrComboOperacionDeuda(selectOperacionDeudaPrimero);
		log.debug(selectOperacion);
		//--------------------------------------
		String selectMotivo = "<select styleId='listaMotivo' name='listaMotivo' id='listaMotivo' onFocus='enfocar(6);' onKeyDown='return keyDownMotivo(this, event);' onchange='verificaExcepcionMotivo();'>";
		for (int i = 0; i < listaMotivo.size(); i++) {					
			Base base = new Base(); 
			base =(Base)listaMotivo.get(i);
			selectMotivo+="<option value='"+base.getCodigo()+"'>";
			selectMotivo+=base.getDescripcion();
			selectMotivo+="</option>";
		}
		selectMotivo += "</select>";
		form.setStrComboMotivo(selectMotivo);
		log.debug(selectMotivo);
	}

	/**
	 * @param f
	 */
	private void limpiar(MantenimientoRECDigitacionCDRAjaxForm f) {
		f.setListaOperacion(null);
		f.setListaCUVCambia(null);
		f.setListaCantidadCambia(null);
		f.setListaMotivo(null);
		f.setListaIgualEnvio(null);
		f.setListaCUVDesea(null);
		f.setListaCantidadDesea(null);
		
		f.setNumeroCDR("");
		f.setNumeroBoletaDespacho("");
		f.setCodigoConsejera("");
		f.setPeriodo("");
		f.setNombreConsejera("");
		f.setIndicadorModifica("0");
		f.setIndicadorHayRegistros("0");
		f.setIndicadorExpress(null);
		f.setIndicadorExpressHidden(null);
		f.setIndicadorPedidoChequeado(null);
		f.setCodigoResultadoChequeo("");
		
		f.setDireccionDomicilio("");
		f.setUbicacionGeografica("");
		
		f.setListaOperacion(null);
	    f.setListaCUVCambia(null);
	    f.setListaCantidadCambia(null);
	    f.setListaMotivo(null);
	    f.setListaCUVDesea(null);
	    f.setListaCantidadDesea(null);
	}
	
	public void guardar(ActionEvent actionEvent)  {
		if (log.isWarnEnabled()) {
			log.warn("Entering 'guardar' method");
		}
		
		try{
			
			MantenimientoRECDigitacionCDRAjaxForm f = (MantenimientoRECDigitacionCDRAjaxForm) this.formBusqueda;
			
			ReclamoDigitadoCabecera reclamoDigitCabec = new ReclamoDigitadoCabecera();
			List detallesList = new ArrayList();		
			
			//Carga todos los datos de la cabecera
			setCabecera(f, reclamoDigitCabec);
			
			//OBTENEMOS LOS HIDDENS QUE CONTIENEN LAS LISTAS DE LA GRILLA Y LO SETEAMOS
			//EN LAS VARIABLES DE ARREGLOS DEL FORMULARIO
			setearListas(f);
			
			//Carga los detalles
			if(this.mantenimientoRECDigitacionCDRDetallesDigitadosList != null)
				detallesList = this.mantenimientoRECDigitacionCDRDetallesDigitadosList;
			// Si es 0, es un nuevo registro, si es diferente de 0, es una modificacion
			int indicadorModificar = detallesList.size();			
			setDetalles(f, detallesList);
			// Extraemos el usuario de la sesión
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			
	        String indicadorOnline = f.getIndicadorOnline();
			MantenimientoRECDigitacionCDRService service = (MantenimientoRECDigitacionCDRService)getBean("spusicc.mantenimientoRECDigitacionCDRService");

			Map criteria = new HashMap();
			criteria.put("codigoPais", pais.getCodigo());
			String indicadorCDR = service.getIndicadorCDRRechazo(criteria);

			//Estraemos el Indicador Online
			if (indicadorOnline!=null && indicadorOnline.equals("S")){
				
				String codigoDocumento=Constants.STO_TIPO_DOCUMENTO_SPVC;
				
				Map params = new HashMap();
				
				params.put("codigoPais", pais.getCodigo());
				params.put("codigoDocumento",codigoDocumento);
				
				
				InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
				
				TipoDocumentoDigitadoPK tipoDocumentoDigitadoPK = new TipoDocumentoDigitadoPK(pais.getCodigo(),Constants.STO_TIPO_DOCUMENTO_SPVC);
				ProcesoSTOService stoService = (ProcesoSTOService)getBean("spusicc.procesoSTOService");
				String numLoteSTO=stoService.updateLoteSTO(tipoDocumentoDigitadoPK);
				
				params.put("usuario",usuario);
				params.put("numLoteSTO",numLoteSTO);
				
				
				reclamoDigitCabec.setIndicadorOrigen(Constants.REC_DIGI_CDRS_ORIG_CC);
				
				// Inserta la informacion en las tablas temporales y consolida la informacion en tablas de consolidado
				service.insertReclamoDigitadoOnline(reclamoDigitCabec, detallesList, params);
						
				
				
				//Ejecutando Validaciones STO
				params.put("codPais", pais.getCodigo());
				params.put("codTipoDocu", codigoDocumento);			
				params.put("numLote", numLoteSTO);
				
				params.put("codigoPeriodo", "");
				
				
				ProcesoSTOService procesoSTOService = (ProcesoSTOService) getBean("spusicc.procesoSTOService");
				
				List lista = procesoSTOService.getDocumentoDigitadoPKByLote(params);
				
				List listaSTO = new ArrayList();
				for (Iterator iterator = lista.iterator(); iterator.hasNext();) {
		    		 DocumentoDigitadoPK documentoDigitadoPK = (DocumentoDigitadoPK) iterator.next();
		    		 GestionDocumento gestionDocumento = new GestionDocumento();
		    		 gestionDocumento.setCodigoPais(documentoDigitadoPK.getCodPais());
		    		 gestionDocumento.setNumeroDocumento(documentoDigitadoPK.getSecNumeDocu());
		    		 gestionDocumento.setLote(documentoDigitadoPK.getNumLote());
		    		 gestionDocumento.setDocumento(documentoDigitadoPK.getCodTipoDocu());
		    		 listaSTO.add(gestionDocumento);
		    		 
		  		}		
				
				ProcesoSTOExecutionService execService = (ProcesoSTOExecutionService) getBean("spusicc.procesoSTOExecutionService");
				
				AccionTipoDocumento accionTipoDocumento = new AccionTipoDocumento(pais.getCodigo(),codigoDocumento,Constants.STO_ACCI_VALI_ON_LINE);
				execService.execute(accionTipoDocumento,params,listaSTO);
				
				ProcesoSTOLevantamientoErroresValidacionService serviceGestion = (ProcesoSTOLevantamientoErroresValidacionService) getBean("spusicc.procesoSTOLevantamientoErroresValidacionService");
				
				//Verificamos si  Existen Errores STO En caso existan los mostramos
				
				params.put("tipoDocumento",codigoDocumento);
				List listaErroresValidacion =  serviceGestion.getGestionDocumentoList(params);
				if (listaErroresValidacion.size()>0){
					String errores = getResourceMessage("mantenimientoRECDigitacionCDRForm.errors.gestion");

					for (Iterator iterator = listaErroresValidacion.iterator(); iterator.hasNext();) {
			    		 GestionDocumento gestionDocumento = (GestionDocumento) iterator.next();
			    		 errores = errores + "<br> " + getResourceMessage("errors.detail", new Object[] {gestionDocumento.getDesValidacionLarga()});
			    		 
			  		}
					
					addError("", errores);
				}
				else{
					if(Constants.NUMERO_UNO.equals(indicadorCDR)){
						addInfo("",	getResourceMessage("mantenimientoRECDigitacionCDRForm.msj.registrarCCS.CDR"));
					}else{
						addInfo("",	getResourceMessage("mantenimientoRECDigitacionCDRForm.msj.registrarCCS"));
					}

				}
			}
			else{
				reclamoDigitCabec.setIndicadorOrigen(Constants.REC_DIGI_CDRS_ORIG_DIG);
				service.insertReclamoDigitado(reclamoDigitCabec, detallesList, usuario, indicadorModificar);
				addInfo("",	getResourceMessage("mantenimientoRECDigitacionCDRForm.msj.registrar"));

			}
			
			limpiar(f);
		    //f.setIndicadorModifica("0");
		    
		    this.mantenimientoRECDigitacionCDRDetallesDigitadosList = null;

		    /* INI JR PER-SiCC-2012-0304 */
		    f.setObservacionCDR(null);
	    	f.setIndicadorCDRRechazo(Constants.NUMERO_CERO);
	    	/* FIN JR PER-SiCC-2012-0304 */
	    	
	    	// PER-SiCC-2012-0642 
	    	f.setCodigoMotivoRechazoDef(null);
		}
		catch (Exception e) {
 			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}

	}
	
	@Override
	protected boolean setSaveAttributes() throws Exception {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'save' method");
		}
		
		MantenimientoRECDigitacionCDRAjaxForm f = (MantenimientoRECDigitacionCDRAjaxForm) this.formBusqueda;
		
		ReclamoDigitadoCabecera reclamoDigitCabec = new ReclamoDigitadoCabecera();
		List detallesList = new ArrayList();		
		
		//Carga todos los datos de la cabecera
		setCabecera(f, reclamoDigitCabec);
		
		//OBTENEMOS LOS HIDDENS QUE CONTIENEN LAS LISTAS DE LA GRILLA Y LO SETEAMOS
		//EN LAS VARIABLES DE ARREGLOS DEL FORMULARIO
		setearListas(f);
		
		//Carga los detalles
		if(this.mantenimientoRECDigitacionCDRDetallesDigitadosList != null)
			detallesList = this.mantenimientoRECDigitacionCDRDetallesDigitadosList;
		// Si es 0, es un nuevo registro, si es diferente de 0, es una modificacion
		int indicadorModificar = detallesList.size();			
		setDetalles(f, detallesList);
		// Extraemos el usuario de la sesión
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
        String indicadorOnline = f.getIndicadorOnline();
		MantenimientoRECDigitacionCDRService service = (MantenimientoRECDigitacionCDRService)getBean("spusicc.mantenimientoRECDigitacionCDRService");

		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		String indicadorCDR = service.getIndicadorCDRRechazo(criteria);

		//Estraemos el Indicador Online
		if (indicadorOnline!=null && indicadorOnline.equals("S")){
			
			String codigoDocumento=Constants.STO_TIPO_DOCUMENTO_SPVC;
			
			Map params = new HashMap();
			
			params.put("codigoPais", pais.getCodigo());
			params.put("codigoDocumento",codigoDocumento);
			
			
			InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
			
			TipoDocumentoDigitadoPK tipoDocumentoDigitadoPK = new TipoDocumentoDigitadoPK(pais.getCodigo(),Constants.STO_TIPO_DOCUMENTO_SPVC);
			ProcesoSTOService stoService = (ProcesoSTOService)getBean("spusicc.procesoSTOService");
			String numLoteSTO=stoService.updateLoteSTO(tipoDocumentoDigitadoPK);
			
			params.put("usuario",usuario);
			params.put("numLoteSTO",numLoteSTO);
			
			
			reclamoDigitCabec.setIndicadorOrigen(Constants.REC_DIGI_CDRS_ORIG_CC);
			
			// Inserta la informacion en las tablas temporales y consolida la informacion en tablas de consolidado
			service.insertReclamoDigitadoOnline(reclamoDigitCabec, detallesList, params);
					
			
			
			//Ejecutando Validaciones STO
			params.put("codPais", pais.getCodigo());
			params.put("codTipoDocu", codigoDocumento);			
			params.put("numLote", numLoteSTO);
			
			params.put("codigoPeriodo", "");
			
			
			ProcesoSTOService procesoSTOService = (ProcesoSTOService) getBean("spusicc.procesoSTOService");
			
			List lista = procesoSTOService.getDocumentoDigitadoPKByLote(params);
			
			List listaSTO = new ArrayList();
			for (Iterator iterator = lista.iterator(); iterator.hasNext();) {
	    		 DocumentoDigitadoPK documentoDigitadoPK = (DocumentoDigitadoPK) iterator.next();
	    		 GestionDocumento gestionDocumento = new GestionDocumento();
	    		 gestionDocumento.setCodigoPais(documentoDigitadoPK.getCodPais());
	    		 gestionDocumento.setNumeroDocumento(documentoDigitadoPK.getSecNumeDocu());
	    		 gestionDocumento.setLote(documentoDigitadoPK.getNumLote());
	    		 gestionDocumento.setDocumento(documentoDigitadoPK.getCodTipoDocu());
	    		 listaSTO.add(gestionDocumento);
	    		 
	  		}		
			
			ProcesoSTOExecutionService execService = (ProcesoSTOExecutionService) getBean("spusicc.procesoSTOExecutionService");
			
			AccionTipoDocumento accionTipoDocumento = new AccionTipoDocumento(pais.getCodigo(),codigoDocumento,Constants.STO_ACCI_VALI_ON_LINE);
			execService.execute(accionTipoDocumento,params,listaSTO);
			
			ProcesoSTOLevantamientoErroresValidacionService serviceGestion = (ProcesoSTOLevantamientoErroresValidacionService) getBean("spusicc.procesoSTOLevantamientoErroresValidacionService");
			
			//Verificamos si  Existen Errores STO En caso existan los mostramos
			
			params.put("tipoDocumento",codigoDocumento);
			List listaErroresValidacion =  serviceGestion.getGestionDocumentoList(params);
			if (listaErroresValidacion.size()>0){
				String errores = getResourceMessage("mantenimientoRECDigitacionCDRForm.errors.gestion");

				for (Iterator iterator = listaErroresValidacion.iterator(); iterator.hasNext();) {
		    		 GestionDocumento gestionDocumento = (GestionDocumento) iterator.next();
		    		 errores = errores + "<br> " + getResourceMessage("errors.detail", new Object[] {gestionDocumento.getDesValidacionLarga()});
		    		 
		  		}
				
				addError("", errores);
			}
			else{
				if(Constants.NUMERO_UNO.equals(indicadorCDR)){
					addInfo("",	getResourceMessage("mantenimientoRECDigitacionCDRForm.msj.registrarCCS.CDR"));
				}else{
					addInfo("",	getResourceMessage("mantenimientoRECDigitacionCDRForm.msj.registrarCCS"));
				}

			}
		}
		else{
			reclamoDigitCabec.setIndicadorOrigen(Constants.REC_DIGI_CDRS_ORIG_DIG);
			service.insertReclamoDigitado(reclamoDigitCabec, detallesList, usuario, indicadorModificar);
			addInfo("",	getResourceMessage("mantenimientoRECDigitacionCDRForm.msj.registrar"));

		}
		
		limpiar(f);
	    //f.setIndicadorModifica("0");
	    
	    this.mantenimientoRECDigitacionCDRDetallesDigitadosList = null;

	    /* INI JR PER-SiCC-2012-0304 */
	    f.setObservacionCDR(null);
    	f.setIndicadorCDRRechazo(Constants.NUMERO_CERO);
    	/* FIN JR PER-SiCC-2012-0304 */
    	
    	// PER-SiCC-2012-0642 
    	f.setCodigoMotivoRechazoDef(null);
    	
    	return true;
	}
	
	private void setearListas(MantenimientoRECDigitacionCDRAjaxForm f) {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		
	    String alistaOperacion = ec.getRequestParameterMap().get("alistaOperacion");
	    String alistaCUVCambia = ec.getRequestParameterMap().get("alistaCUVCambia");
	    String alistaCantidadCambia = ec.getRequestParameterMap().get("alistaCantidadCambia");
	    String alistaMotivo = ec.getRequestParameterMap().get("alistaMotivo");
	    String alistaCUVDesea = ec.getRequestParameterMap().get("alistaCUVDesea");
	    String alistaCantidadDesea = ec.getRequestParameterMap().get("alistaCantidadDesea");
	    
	    f.setListaOperacion(StringUtils.splitByWholeSeparatorPreserveAllTokens(alistaOperacion, "__"));
	    f.setListaCUVCambia(StringUtils.splitByWholeSeparatorPreserveAllTokens(alistaCUVCambia, "__"));
	    f.setListaCantidadCambia(StringUtils.splitByWholeSeparatorPreserveAllTokens(alistaCantidadCambia, "__"));
	    f.setListaMotivo(StringUtils.splitByWholeSeparatorPreserveAllTokens(alistaMotivo, "__"));
	    f.setListaCUVDesea(StringUtils.splitByWholeSeparatorPreserveAllTokens(alistaCUVDesea, "__"));
	    f.setListaCantidadDesea(StringUtils.splitByWholeSeparatorPreserveAllTokens(alistaCantidadDesea, "__"));
	}
	
	/**
	 * @param f
	 * @param detallesList
	 * Setea los detalles del reclamo
	 */
	private void setDetalles(MantenimientoRECDigitacionCDRAjaxForm f,List detallesList) {
		String[]listaCodigos   = f.getListaCUVCambia();
		MantenimientoRECDigitacionCDRService service = (MantenimientoRECDigitacionCDRService)getBean("spusicc.mantenimientoRECDigitacionCDRService");
		String codigoOperacion = "";
		for (int i = 0; i < listaCodigos.length; i++) {
			ReclamoDigitadoDetalle reclamoDigitDetal = new ReclamoDigitadoDetalle();
			reclamoDigitDetal.setCodigoPais(f.getCodigoPais());
			reclamoDigitDetal.setNumeroDocumento(f.getNumeroCDR());
			reclamoDigitDetal.setCodigoCliente(f.getCodigoConsejera());
			Map criteria = new HashMap();
			
			if(StringUtils.isNotEmpty(f.getListaOperacion()[i])){
			String[] strCodigos = StringUtils.split(f.getListaOperacion()[i], '|'); 
			codigoOperacion = strCodigos[0];
			}else{
				codigoOperacion = f.getListaOperacion()[i];
			}
			reclamoDigitDetal.setTipoReferencia(codigoOperacion);
			if(codigoOperacion.equals("C") || codigoOperacion.equals("T")){ //Cambio Producto
				criteria.put("codigoItemDuplicado", Constants.CODIGO_PRODUCTO_DUPLICADO);
				if(f.getListaCUVCambia()[i].equals(f.getListaCUVDesea()[i]) && f.getListaCantidadCambia()[i].equals(f.getListaCantidadDesea()[i]))
					criteria.put("indicadorIgual", Constants.REC_INDICADOR_IGUAL);									
				else
					criteria.put("indicadorIgual", Constants.REC_INDICADOR_DIFERENTE);
				reclamoDigitDetal.setTipoReferencia(service.getCodigoOperacion(criteria));
			}
			if(codigoOperacion.equals("S") || codigoOperacion.equals("P")){ //Cambio Premio
				criteria.put("codigoItemDuplicado", Constants.CODIGO_PREMIO_DUPLICADO);
				if(f.getListaCUVCambia()[i].equals(f.getListaCUVDesea()[i]) && f.getListaCantidadCambia()[i].equals(f.getListaCantidadDesea()[i]))
					criteria.put("indicadorIgual", Constants.REC_INDICADOR_IGUAL);				
				else
					criteria.put("indicadorIgual", Constants.REC_INDICADOR_DIFERENTE);		
				reclamoDigitDetal.setTipoReferencia(service.getCodigoOperacion(criteria));
			}	
			//reclamoDigitDetal.setCodigoOperador(f.getListaOperacion()[i]);
			reclamoDigitDetal.setCodigoPeriodo(f.getPeriodo());			
			reclamoDigitDetal.setMotivoSPV(f.getListaMotivo()[i]);
			reclamoDigitDetal.setCodigoVentaDevuelve(f.getListaCUVCambia()[i]);
			reclamoDigitDetal.setCodigoVentaDesea(f.getListaCUVDesea()[i]);
			reclamoDigitDetal.setCantidadProductosDevuelve(f.getListaCantidadCambia()[i]);
			reclamoDigitDetal.setCantidadProductosDesea(f.getListaCantidadDesea()[i]);
			reclamoDigitDetal.setEstadoProceso(Constants.ESTADO_PROCESO_01);
			reclamoDigitDetal.setCodigoTipoDocumento(Constants.TIPO_DOCUMENTO_SPVD);	
			reclamoDigitDetal.setCodigoMotivoRechazoDef(f.getCodigoMotivoRechazoDef()); //PER-SiCC-2012-0642 
			detallesList.add(reclamoDigitDetal);
		}
	}

	/**
	 * @param f
	 * @param reclamoDigitCabec
	 * Setea los datos de la cabecera del reclamo
	 */
	private void setCabecera(MantenimientoRECDigitacionCDRAjaxForm f,ReclamoDigitadoCabecera reclamoDigitCabec) {
		reclamoDigitCabec.setCodigoPais(f.getCodigoPais());
		reclamoDigitCabec.setNumeroDocumento(f.getNumeroCDR());
		reclamoDigitCabec.setCodigoPeriodo(f.getPeriodo());
		reclamoDigitCabec.setCodigoCliente(f.getCodigoConsejera());
		reclamoDigitCabec.setNumeroDocumentoCruce(f.getNumeroBoletaDespacho());
		reclamoDigitCabec.setTipoSolicitud(Constants.TIPO_SOLICITUD_SOC);
		reclamoDigitCabec.setCodigoSubAcceso(Constants.CODIGO_SUBACCESO_000);
		reclamoDigitCabec.setAccesoFisico(Constants.ACCESO_FISICO_01);
		reclamoDigitCabec.setEstadoProceso(Constants.ESTADO_PROCESO_01);
		reclamoDigitCabec.setCodigoTipoDocumento(Constants.TIPO_DOCUMENTO_SPVC);
		if("true".equals(f.getIndicadorExpress()))
			reclamoDigitCabec.setIndicadorExpress(Constants.IND_EXPRESS_ACTIVO);
		else
			reclamoDigitCabec.setIndicadorExpress(Constants.IND_EXPRESS_INACTIVO);
		
		if ("true".equals(f.getIndicadorCDRRechazo())){
			reclamoDigitCabec.setCodigoMotivoRechazoDef(f.getCodigoMotivoRechazoDef());//PER-SiCC-2012-0642 
			reclamoDigitCabec.setIndicadorCDRRechazo(Constants.NUMERO_UNO);
			reclamoDigitCabec.setObservacionCDR(f.getObservacionCDR());
		}
		else{
			reclamoDigitCabec.setCodigoMotivoRechazoDef(null); //PER-SiCC-2012-0642 
			reclamoDigitCabec.setIndicadorCDRRechazo(Constants.NUMERO_CERO);
			reclamoDigitCabec.setObservacionCDR(null);
		}
	}
	
	/**
	 * Metodo que permite visualizar el Historico de Solicitudes Aprobados
	 */
	 
	public void showHistoricoSolicitudAprobados(ActionEvent event) {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'showHistoricoSolicitudAprobados' method");
		}
		
		MantenimientoRECDigitacionCDRAjaxForm f = (MantenimientoRECDigitacionCDRAjaxForm) this.formBusqueda;
		Map criteria = new HashMap();
		criteria.put("codigoCliente", f.getCodigoConsejera());
		
		ProcesoSTOEjecucionValidacionesService service = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
		
		Map lstGrupos = service.getListaHistoricoSolicitudAprobados(criteria);
		
		this.stoDevolucionesList = (List)lstGrupos.get("lstDevoluciones");
		this.stoCambiosList = (List)lstGrupos.get("lstCambios");
		this.stoFfneList = (List)lstGrupos.get("lstFFNE");
		this.stoOtrosList = (List)lstGrupos.get("lstOtros");
		
	}
	
	
	/**
	 * Metodo que permite visualizar los Documentos de Referencias STO
	 */
	 
	public void showDocumentoReferenciaSTO(ActionEvent event) {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'showDocumentoReferenciaSTO' method");
		}
		
		try {
			MantenimientoRECDigitacionCDRAjaxForm f = (MantenimientoRECDigitacionCDRAjaxForm) this.formBusqueda;
			Map criteria = new HashMap();
			criteria.put("codigoCliente", f.getCodigoConsejera());
			
			ProcesoSTOEjecucionValidacionesService service = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
			
			List lista=service.getDocumentosReferenciaConsultora(criteria);
			
			this.stoDocumentosReferenciaList = lista;
			
		} catch (Exception e) {
			addError("Error", obtieneMensajeErrorException(e));
		}	
			
	}
	

	public void showCodigoVentaPedido(ActionEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'showCodigoVentaPedido' method");
		}
		
		try {
			MantenimientoRECDigitacionCDRAjaxForm f = (MantenimientoRECDigitacionCDRAjaxForm) this.formBusqueda;
		
			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
			
		    String numeroCruceX = ec.getRequestParameterMap().get("numeroCruce");
		    
			//---------------------------------------
			String numeroCruce = StringUtils.split(numeroCruceX, "|")[0];
			try {
				String indice = StringUtils.split(numeroCruceX, "|")[1];
				this.indice = indice;
				String cajaTexto = StringUtils.split(numeroCruceX, "|")[2];
				this.cajaTexto = cajaTexto;
			} catch (Exception e) {
				// TODO: handle exception
			}		
			//---------------------------------------
			
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			String codigoIdiomaISO = pais.getCodigoIdiomaIso();
			
			Map criteria = new HashMap();
			criteria.put("codigoIdiomaISO",codigoIdiomaISO);
			this.numeroCruceX = numeroCruce;
			criteria.put("numeroCruce",numeroCruce);
		
			this.stoCodigoVentaPedidoList = null;
	
			ProcesoSTOEjecucionValidacionesService service = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
			MantenimientoRECDigitacionCDRService serviceCDR = (MantenimientoRECDigitacionCDRService) getBean("spusicc.mantenimientoRECDigitacionCDRService");
			criteria.put("numeroBoleta", numeroCruce);
			LabelValueCDR result = serviceCDR.getConsultoraReclamoByCodigo(criteria);
			
			this.numPedido = numeroCruce;
			if (result!=null){
				if (StringUtils.isNotBlank(result.getLabel())){
					criteria.put("codigoCliente",result.getLabel());
					this.consejera = result.getLabel()+"  "+result.getValue();
				}else{
					criteria.put("codigoCliente",null);
					this.consejera = " ";
				}
			}else{
				criteria.put("codigoCliente",null);
				this.consejera = " ";
			}
			
			List lista=service.getCodigoVentaPedidoList(criteria);
			this.stoCodigoVentaPedidoList = lista;
			
			this.codigoSAP = "";
			this.codigoVenta = "";
			this.descripcion = "";
			
			//Obtenemos el valor del parametro STO_IND_BOLELEC
			criteria.put("codigoPais", pais.getCodigo());
			criteria.put("codigoParametro", Constants.STO_IND_BOLELEC);
			String valorParametro = service.getParametroSTO(criteria);
			this.indicadorBOLELEC = valorParametro;
			
		} catch (Exception e) {
			addError("Error", obtieneMensajeErrorException(e));
		}	
	}	

	public void findCodigoVentaPedido(ActionEvent event) {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'findCodigoVentaPedido' method");
		}
		
		try {
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			Map criteria = new HashMap();
			criteria.put("descripcion", this.getDescripcion());
			criteria.put("codigoVenta", this.getCodigoVenta());
			criteria.put("codigoSAP", this.codigoSAP);
			criteria.put("numeroCruce", this.numeroCruceX);		
			criteria.put("codigoIdiomaISO", pais.getCodigoIdiomaIso());
			
			this.stoCodigoVentaPedidoList = null;

			ProcesoSTOEjecucionValidacionesService service = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
			MantenimientoRECDigitacionCDRService serviceCDR = (MantenimientoRECDigitacionCDRService) getBean("spusicc.mantenimientoRECDigitacionCDRService");
			criteria.put("numeroBoleta", numeroCruce);
			LabelValueCDR result = serviceCDR.getConsultoraReclamoByCodigo(criteria);
			criteria.put("codigoCliente",result.getLabel());

			List lista=service.getCodigoVentaPedidoList(criteria);
			
			this.stoCodigoVentaPedidoList = lista;
			
		} catch (Exception e) {
			addError("Error", obtieneMensajeErrorException(e));
		}	
			
	}
	
	public void showCodigoVentaMatriz(ActionEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'showCodigoVentaMatriz' method");
		}
		
		try {
			MantenimientoRECDigitacionCDRAjaxForm f = (MantenimientoRECDigitacionCDRAjaxForm) this.formBusqueda;
		
			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
			
		    String numeroCruceX = ec.getRequestParameterMap().get("numeroCruce");
		    this.metParam = ec.getRequestParameterMap().get("metParam");
		    
			//---------------------------------------
			String numeroCruce = StringUtils.split(numeroCruceX, "|")[0];
			try {
				String indice = StringUtils.split(numeroCruceX, "|")[1];
				this.indiceMatriz = indice;
				String cajaTexto = StringUtils.split(numeroCruceX, "|")[2];
				this.cajaTextoMatriz = cajaTexto;
			} catch (Exception e) {
				// TODO: handle exception
			}		
			//---------------------------------------
			Map criteria = new HashMap();
			
			this.numeroCruceMatriz = numeroCruce;
			criteria.put("numeroCruce",numeroCruce);

			this.stoCodigoVentaMatrizList = null;

			ProcesoSTOEjecucionValidacionesService service = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
			this.codigoPeriodo = service.getPeriodoReferencia(criteria);
			if(StringUtils.equalsIgnoreCase(this.metParam, "asignar")){
				this.codigoPeriodo = ec.getRequestParameterMap().get("campanaDespacho");
			}
			List lista=service.getCodigoVentaMatrizPrecioList(criteria);
			
			this.stoCodigoVentaMatrizList = lista;
			
			this.codigoSAPMatriz = "";
			this.codigoVentaMatriz = "";
			this.descripcionMatriz = "";
			this.matriz = "1";
			this.precioInicial = "";
			this.precioFinal = "";
			
		} catch (Exception e) {
			addError("Error", obtieneMensajeErrorException(e));
		}	
	}

	public void findCodigoVentaMatriz(ActionEvent event) {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'findCodigoVentaMatriz' method");
		}
		
		try {
			Map criteria = new HashMap();
			criteria.put("descripcion", this.descripcionMatriz);
			criteria.put("codigoVenta", this.codigoVentaMatriz);
			criteria.put("codigoSAP", this.codigoSAPMatriz);
			criteria.put("numeroCruce", this.numeroCruceMatriz);		
			criteria.put("precioInicial", this.precioInicial);
			criteria.put("precioFinal", this.precioFinal);
			
			this.stoCodigoVentaMatrizList = null;
			List lista=new ArrayList();
			
			ProcesoSTOEjecucionValidacionesService service = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
			if(this.matriz==null){
				lista=service.getCodigoVentaMatrizList(criteria);
			}
			else{
				if(this.matriz.compareToIgnoreCase("1")==0){
					lista=service.getCodigoVentaMatrizPrecioList(criteria);
				}
				else
					lista=service.getCodigoVentaMatrizIncentivoList(criteria);
			}
			
			this.stoCodigoVentaMatrizList = lista;
			
		} catch (Exception e) {
			addError("Error", obtieneMensajeErrorException(e));
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
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoRECDigitacionCDRAjaxForm form = new MantenimientoRECDigitacionCDRAjaxForm();
		return form;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * @return the devolucionEspecial
	 */
	public String getDevolucionEspecial() {
		return devolucionEspecial;
	}

	/**
	 * @param devolucionEspecial the devolucionEspecial to set
	 */
	public void setDevolucionEspecial(String devolucionEspecial) {
		this.devolucionEspecial = devolucionEspecial;
	}

	/**
	 * @return the mantenimientoRECDigitacionCDRDetallesDigitadosList
	 */
	public List getMantenimientoRECDigitacionCDRDetallesDigitadosList() {
		return mantenimientoRECDigitacionCDRDetallesDigitadosList;
	}

	/**
	 * @param mantenimientoRECDigitacionCDRDetallesDigitadosList the mantenimientoRECDigitacionCDRDetallesDigitadosList to set
	 */
	public void setMantenimientoRECDigitacionCDRDetallesDigitadosList(
			List mantenimientoRECDigitacionCDRDetallesDigitadosList) {
		this.mantenimientoRECDigitacionCDRDetallesDigitadosList = mantenimientoRECDigitacionCDRDetallesDigitadosList;
	}

	/**
	 * @return the recOperacionParametrosList
	 */
	public List getRecOperacionParametrosList() {
		return recOperacionParametrosList;
	}

	/**
	 * @param recOperacionParametrosList the recOperacionParametrosList to set
	 */
	public void setRecOperacionParametrosList(List recOperacionParametrosList) {
		this.recOperacionParametrosList = recOperacionParametrosList;
	}

	/**
	 * @return the lstCodMotRechazo
	 */
	public List getLstCodMotRechazo() {
		return lstCodMotRechazo;
	}

	/**
	 * @param lstCodMotRechazo the lstCodMotRechazo to set
	 */
	public void setLstCodMotRechazo(List lstCodMotRechazo) {
		this.lstCodMotRechazo = lstCodMotRechazo;
	}

	/**
	 * @return the busquedaRECDocumentoReferenciaSearchAction
	 */
	public BusquedaRECDocumentoReferenciaSearchAction getBusquedaRECDocumentoReferenciaSearchAction() {
		return busquedaRECDocumentoReferenciaSearchAction;
	}

	/**
	 * @param busquedaRECDocumentoReferenciaSearchAction the busquedaRECDocumentoReferenciaSearchAction to set
	 */
	public void setBusquedaRECDocumentoReferenciaSearchAction(
			BusquedaRECDocumentoReferenciaSearchAction busquedaRECDocumentoReferenciaSearchAction) {
		this.busquedaRECDocumentoReferenciaSearchAction = busquedaRECDocumentoReferenciaSearchAction;
	}

	/**
	 * @return the mostrarBotonBuscarDOCREF
	 */
	public boolean isMostrarBotonBuscarDOCREF() {
		return mostrarBotonBuscarDOCREF;
	}

	/**
	 * @param mostrarBotonBuscarDOCREF the mostrarBotonBuscarDOCREF to set
	 */
	public void setMostrarBotonBuscarDOCREF(boolean mostrarBotonBuscarDOCREF) {
		this.mostrarBotonBuscarDOCREF = mostrarBotonBuscarDOCREF;
	}

	/**
	 * @return the stoDevolucionesList
	 */
	public List getStoDevolucionesList() {
		return stoDevolucionesList;
	}

	/**
	 * @param stoDevolucionesList the stoDevolucionesList to set
	 */
	public void setStoDevolucionesList(List stoDevolucionesList) {
		this.stoDevolucionesList = stoDevolucionesList;
	}

	/**
	 * @return the stoCambiosList
	 */
	public List getStoCambiosList() {
		return stoCambiosList;
	}

	/**
	 * @param stoCambiosList the stoCambiosList to set
	 */
	public void setStoCambiosList(List stoCambiosList) {
		this.stoCambiosList = stoCambiosList;
	}

	/**
	 * @return the stoFfneList
	 */
	public List getStoFfneList() {
		return stoFfneList;
	}

	/**
	 * @param stoFfneList the stoFfneList to set
	 */
	public void setStoFfneList(List stoFfneList) {
		this.stoFfneList = stoFfneList;
	}

	/**
	 * @return the stoOtrosList
	 */
	public List getStoOtrosList() {
		return stoOtrosList;
	}

	/**
	 * @param stoOtrosList the stoOtrosList to set
	 */
	public void setStoOtrosList(List stoOtrosList) {
		this.stoOtrosList = stoOtrosList;
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
	 * @return the stoDocumentosReferenciaList
	 */
	public List getStoDocumentosReferenciaList() {
		return stoDocumentosReferenciaList;
	}

	/**
	 * @param stoDocumentosReferenciaList the stoDocumentosReferenciaList to set
	 */
	public void setStoDocumentosReferenciaList(List stoDocumentosReferenciaList) {
		this.stoDocumentosReferenciaList = stoDocumentosReferenciaList;
	}

	/**
	 * @return the stoCodigoVentaPedidoList
	 */
	public List getStoCodigoVentaPedidoList() {
		return stoCodigoVentaPedidoList;
	}

	/**
	 * @param stoCodigoVentaPedidoList the stoCodigoVentaPedidoList to set
	 */
	public void setStoCodigoVentaPedidoList(List stoCodigoVentaPedidoList) {
		this.stoCodigoVentaPedidoList = stoCodigoVentaPedidoList;
	}

	/**
	 * @return the indice
	 */
	public String getIndice() {
		return indice;
	}

	/**
	 * @param indice the indice to set
	 */
	public void setIndice(String indice) {
		this.indice = indice;
	}

	/**
	 * @return the cajaTexto
	 */
	public String getCajaTexto() {
		return cajaTexto;
	}

	/**
	 * @param cajaTexto the cajaTexto to set
	 */
	public void setCajaTexto(String cajaTexto) {
		this.cajaTexto = cajaTexto;
	}

	/**
	 * @return the numeroCruce
	 */
	public String getNumeroCruce() {
		return numeroCruce;
	}

	/**
	 * @param numeroCruce the numeroCruce to set
	 */
	public void setNumeroCruce(String numeroCruce) {
		this.numeroCruce = numeroCruce;
	}

	/**
	 * @return the numPedido
	 */
	public String getNumPedido() {
		return numPedido;
	}

	/**
	 * @param numPedido the numPedido to set
	 */
	public void setNumPedido(String numPedido) {
		this.numPedido = numPedido;
	}

	/**
	 * @return the consejera
	 */
	public String getConsejera() {
		return consejera;
	}

	/**
	 * @param consejera the consejera to set
	 */
	public void setConsejera(String consejera) {
		this.consejera = consejera;
	}

	/**
	 * @return the codigoSAP
	 */
	public String getCodigoSAP() {
		return codigoSAP;
	}

	/**
	 * @param codigoSAP the codigoSAP to set
	 */
	public void setCodigoSAP(String codigoSAP) {
		this.codigoSAP = codigoSAP;
	}

	/**
	 * @return the codigoVenta
	 */
	public String getCodigoVenta() {
		return codigoVenta;
	}

	/**
	 * @param codigoVenta the codigoVenta to set
	 */
	public void setCodigoVenta(String codigoVenta) {
		this.codigoVenta = codigoVenta;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the indicadorBOLELEC
	 */
	public String getIndicadorBOLELEC() {
		return indicadorBOLELEC;
	}

	/**
	 * @param indicadorBOLELEC the indicadorBOLELEC to set
	 */
	public void setIndicadorBOLELEC(String indicadorBOLELEC) {
		this.indicadorBOLELEC = indicadorBOLELEC;
	}

	/**
	 * @return the numeroCruceX
	 */
	public String getNumeroCruceX() {
		return numeroCruceX;
	}

	/**
	 * @param numeroCruceX the numeroCruceX to set
	 */
	public void setNumeroCruceX(String numeroCruceX) {
		this.numeroCruceX = numeroCruceX;
	}

	/**
	 * @return the stoCodigoVentaMatrizList
	 */
	public List getStoCodigoVentaMatrizList() {
		return stoCodigoVentaMatrizList;
	}

	/**
	 * @param stoCodigoVentaMatrizList the stoCodigoVentaMatrizList to set
	 */
	public void setStoCodigoVentaMatrizList(List stoCodigoVentaMatrizList) {
		this.stoCodigoVentaMatrizList = stoCodigoVentaMatrizList;
	}

	/**
	 * @return the metParam
	 */
	public String getMetParam() {
		return metParam;
	}

	/**
	 * @param metParam the metParam to set
	 */
	public void setMetParam(String metParam) {
		this.metParam = metParam;
	}

	/**
	 * @return the indiceMatriz
	 */
	public String getIndiceMatriz() {
		return indiceMatriz;
	}

	/**
	 * @param indiceMatriz the indiceMatriz to set
	 */
	public void setIndiceMatriz(String indiceMatriz) {
		this.indiceMatriz = indiceMatriz;
	}

	/**
	 * @return the cajaTextoMatriz
	 */
	public String getCajaTextoMatriz() {
		return cajaTextoMatriz;
	}

	/**
	 * @param cajaTextoMatriz the cajaTextoMatriz to set
	 */
	public void setCajaTextoMatriz(String cajaTextoMatriz) {
		this.cajaTextoMatriz = cajaTextoMatriz;
	}

	/**
	 * @return the numeroCruceMatriz
	 */
	public String getNumeroCruceMatriz() {
		return numeroCruceMatriz;
	}

	/**
	 * @param numeroCruceMatriz the numeroCruceMatriz to set
	 */
	public void setNumeroCruceMatriz(String numeroCruceMatriz) {
		this.numeroCruceMatriz = numeroCruceMatriz;
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
	 * @return the codigoSAPMatriz
	 */
	public String getCodigoSAPMatriz() {
		return codigoSAPMatriz;
	}

	/**
	 * @param codigoSAPMatriz the codigoSAPMatriz to set
	 */
	public void setCodigoSAPMatriz(String codigoSAPMatriz) {
		this.codigoSAPMatriz = codigoSAPMatriz;
	}

	/**
	 * @return the codigoVentaMatriz
	 */
	public String getCodigoVentaMatriz() {
		return codigoVentaMatriz;
	}

	/**
	 * @param codigoVentaMatriz the codigoVentaMatriz to set
	 */
	public void setCodigoVentaMatriz(String codigoVentaMatriz) {
		this.codigoVentaMatriz = codigoVentaMatriz;
	}

	/**
	 * @return the descripcionMatriz
	 */
	public String getDescripcionMatriz() {
		return descripcionMatriz;
	}

	/**
	 * @param descripcionMatriz the descripcionMatriz to set
	 */
	public void setDescripcionMatriz(String descripcionMatriz) {
		this.descripcionMatriz = descripcionMatriz;
	}

	/**
	 * @return the matriz
	 */
	public String getMatriz() {
		return matriz;
	}

	/**
	 * @param matriz the matriz to set
	 */
	public void setMatriz(String matriz) {
		this.matriz = matriz;
	}

	/**
	 * @return the precioInicial
	 */
	public String getPrecioInicial() {
		return precioInicial;
	}

	/**
	 * @param precioInicial the precioInicial to set
	 */
	public void setPrecioInicial(String precioInicial) {
		this.precioInicial = precioInicial;
	}

	/**
	 * @return the precioFinal
	 */
	public String getPrecioFinal() {
		return precioFinal;
	}

	/**
	 * @param precioFinal the precioFinal to set
	 */
	public void setPrecioFinal(String precioFinal) {
		this.precioFinal = precioFinal;
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
	 * @return the activarBuscar
	 */
	public boolean isActivarBuscar() {
		return activarBuscar;
	}

	/**
	 * @param activarBuscar the activarBuscar to set
	 */
	public void setActivarBuscar(boolean activarBuscar) {
		this.activarBuscar = activarBuscar;
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
