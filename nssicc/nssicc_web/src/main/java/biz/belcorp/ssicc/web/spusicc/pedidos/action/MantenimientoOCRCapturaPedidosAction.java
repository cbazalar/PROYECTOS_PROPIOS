package biz.belcorp.ssicc.web.spusicc.pedidos.action;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
import org.primefaces.context.RequestContext;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelDatosConsultoraValue;
import biz.belcorp.ssicc.dao.model.LabelPedidosValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Cliente;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.spusicc.sto.model.AccionTipoDocumento;
import biz.belcorp.ssicc.dao.spusicc.sto.model.DocumentoDigitadoPK;
import biz.belcorp.ssicc.dao.spusicc.sto.model.GestionDocumento;
import biz.belcorp.ssicc.dao.spusicc.sto.model.TipoDocumentoDigitadoPK;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.ClienteUAGenerarService;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRCapturaPedidoService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRCargaPedidoService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.pedidos.ProcesoPEDService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOLevantamientoErroresValidacionService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOService;
import biz.belcorp.ssicc.service.spusicc.sto.framework.ProcesoSTOExecutionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.scsicc.hip.action.BusquedaConsultoraPOPUPSearchAction;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.MantenimientoOCRCapturaPedidosForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"rawtypes", "unchecked"})
public class MantenimientoOCRCapturaPedidosAction extends BaseMantenimientoSearchAbstractAction 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 598707578277124720L;
	
	private String longitudCampoClientes;
	private List listaCampanhas;
	private List listaCodigosMatriz;
	private String fecha_facturacion_cte;
	
	// Llenan tabla Detalle Pedido
	private List listaDetallePedido;
	private List listaDetallePedSeleccionado;
	private DataTableModel tablaDetallePedidoModel;
	
	// utilizados en el popup busqueda cliente
	private boolean mostrarPopupOCRCliente = false;
	private static final String POPUP_OCRCLIENTE = "OCRCLIENTE";
	
	@ManagedProperty(value = "#{busquedaConsultoraPOPUPSearchAction}")
	private BusquedaConsultoraPOPUPSearchAction busquedaConsultoraPOPUPSearchAction;
	
	//Llenan la tabla Detalle
	private List listaTablaDetalle;
	private DataTableModel tablaDetalleModel; 
	private List listaTablaDetaSeleccionado;
	
	//flag para habilitar/deshabilitar
	private Boolean deshabilitaCampos = false; 
	
	//flag oculta botones
	private Boolean idPROL;
	private Boolean idPedido;
	private Boolean idPedido2;
	
	//muestra/oculta panel de detalle Pedido
	private Boolean panelpedidosExistentes = true;
	
	private String grabar = "";
	private String prol = "0";
	
	//Almacena el total Pedido de la BD
	private String totalPedidoGuardado;
	
	//Almacena lista de eliminados
	private List listaEliminados = new ArrayList();
	
	//padre Hiperconsulta
	private Boolean padreHiperconsulta = false;
	
	private String codigoConsultoraHiperConsulta = new String("");
	

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
	protected BaseSearchForm devuelveFormBusqueda() throws Exception 
	{
		MantenimientoOCRCapturaPedidosForm f = new MantenimientoOCRCapturaPedidosForm();		
		return f;
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
	 * Metodo que se ejecuta para cargar data inicial del Manage a traves de AJAX
	 * @param actionEvent
	 */
	public void viewAjax(ActionEvent actionEvent) throws Exception {
		try {
			log.debug("Entering view (Ajax)' - method");
			this.viewLogicaNegocio();
			this.seteaConsultora();
		}
		catch(Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	@Override
	protected void setViewAtributes() throws Exception 
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'view' method");
		}
		
		this.mostrarBotonBuscar = false;
		this.mostrarBotonConsultar = false;
		this.mostrarBotonEliminar = false;
		this.mostrarBotonModificar = false;
		this.mostrarBotonNuevo = false;
		this.getRequestContext().execute("PrimeFaces.focus('codigoConsultora')");
		
    	MantenimientoOCRCapturaPedidosForm f = (MantenimientoOCRCapturaPedidosForm)this.formBusqueda;
    	Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
    	
    	//INI metodo reset
    	reset(f);
    	//FIN metodo reset
   
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa
        criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente
        MantenimientoOCRPedidoControlFacturacionService serviceFact = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
        PedidoControlFacturacion controlFacturacion = serviceFact.getControlFacturacionById(criteria);
		ClienteUAGenerarService clienteService = (ClienteUAGenerarService) getBean("sisicc.clienteUAGenerarService");

		this.longitudCampoClientes = clienteService.getTamanhoNumeroCliente(pais.getCodigo()).toString();
		
		// Carga Fecha y Periodo
		String fechaFact = this.fecha_facturacion_cte;
		log.debug("__fechaFact = "+fechaFact);
		
		if(fechaFact != null){
			if(fechaFact.equals("")){
				f.setFechaFacturacion(controlFacturacion.getFechaProceso());
			}
			else{
				f.setFechaFacturacion(fechaFact);
			}
		}
		else{
			f.setFechaFacturacion(controlFacturacion.getFechaProceso());
		}
		
		//seteamos la fecha
		f.setFechaFacturacionDate(DateUtil.convertStringToDate(f.getFechaFacturacion()));
		
        f.setPeriodo(controlFacturacion.getCodigoPeriodo());
        
		// Carga Numero de lotes
        Map map = new HashMap();
        map.put("periodo",controlFacturacion.getCodigoPeriodo()); 
        map.put("pais",pais.getCodigo());
                
        f.setNumLote(Constants.PED_LOTE_FIJO_DIGIT_PEDIDOS);
        
        //Lista de campanhas activas
        criteria.put("indicadorActiva","");
        List listaCampanhas = serviceFact.getCampanhasActivasById(criteria);
        
        this.listaCampanhas = listaCampanhas;
        
        f.setUnidadesMaximas(serviceFact.getMaximoUnidades(map));
        f.setLongitudUnidadesMaximas(serviceFact.getLongitudMaximoUnidades(map));
        
        //se muestra la consultora, si es enviado
        if (StringUtils.isBlank(this.codigoConsultoraHiperConsulta)) {
 	        String codigoConsultora = getRequest().getParameter("codigoConsultora");
	        if(codigoConsultora != null) {
	        	f.setMostrarConsultora(true);
	        	f.setCodigoConsultora(codigoConsultora);
	        } else {
	        	f.setMostrarConsultora(false);
	        }
        }
        else {
        	f.setCodigoConsultora(this.codigoConsultoraHiperConsulta);
        }
        
        String indicadorHiperConsulta = getRequest().getParameter("indicadorHiperConsulta");
        f.setIndicadorHiperConsulta(Constants.NO);
        f.setIndicadorCerrarPopup(Constants.NO);
        if (StringUtils.isNotBlank(indicadorHiperConsulta))
        	f.setIndicadorHiperConsulta(Constants.SI);
        
        /* INI PER-SiCC-2014-0494 */        
        MantenimientoMAEClienteService serviceMae = (MantenimientoMAEClienteService)getBean("spusicc.mantenimientoMAEClienteService");
		
		String indicadorCompletarCerosNumDocumento  = serviceMae.getValorModuloxPaisTipoValidacion(f.getCodigoPais(), Constants.HIP_VALID_COMPLETA_CEROS_DOCUMENTO_IDENTIDAD);
			
		if(StringUtils.isEmpty(indicadorCompletarCerosNumDocumento)) {
			f.setIndicadorCompletarCerosNumDocumento(true);
			//longitud de número de documento de identidad para el paÍs
			f.setLongitudNumeroDocIdentidad(serviceMae.getLongitudNumeroDocIdentidad(criteria));
		}
		else
			f.setIndicadorCompletarCerosNumDocumento(false);
		/* FIN PER-SiCC-2014-0494 */
        
        //--------------------------------------------------
        //Cargo la matriz en memoria
        MantenimientoOCRCapturaPedidoService service = (MantenimientoOCRCapturaPedidoService)getBean("spusicc.pedidos.mantenimientoOCRCapturaPedidoService");
        this.listaCodigosMatriz = service.getListaCodigosVentaMatriz(map);
        //-------------------------------------------------
        //Setea el indicador de parametria (Mostrar detalle) BAS_PAIS -> IND_DIGI_PEDI [S]/[N]
        f.setIndicadorMostrarDetalle(pais.getIndicadorMostrarDetalleDigitacionPedidos());
        //-------------------------------------------------
        f.setIndicadorDatos("N");
        
        f.setIndicadorActiva("");
        f.setEstadoConsultora("");
        f.setIndicadorBloqueo("");	
        
        //lista que llena la tabla detalle
        List aux = new ArrayList();
        aux.add(f);
        this.listaTablaDetalle = aux;
        this.tablaDetalleModel = new DataTableModel(this.listaTablaDetalle);
        
        //carga el monto minimo en la tabla detalle
        cargarMontoMinimo();        
	}
	
	@Override
	public String setValidarConfirmar(String accion) 
	{
		String mensaje = null;
		if(accion.equals("eliminarDetallePedido")){
			if(this.listaDetallePedSeleccionado == null || this.listaDetallePedSeleccionado.size() == 0)
			{
				mensaje = this.getResourceMessage("errors.select.item");
				return mensaje;
			}
		}
		
		if(accion.equals("EliminarTablaN")){
			if(this.listaTablaDetaSeleccionado == null || this.listaTablaDetaSeleccionado.size() == 0)
			{
				mensaje = this.getResourceMessage("errors.select.item");
				return mensaje;								
			}
		}
		
		if(accion.equals("EliminarTablaS")){
			if(this.listaTablaDetaSeleccionado == null || this.listaTablaDetaSeleccionado.size() == 0)
			{
				mensaje = this.getResourceMessage("errors.select.item");
				return mensaje;								
			}
		}
		
		return mensaje;
	}
	
	/**
	 * Inicializa valores del formulario
	 * @param f
	 */
	private void reset(MantenimientoOCRCapturaPedidosForm f){
		f.setCodigoConsultora(null);           
        f.setNombreConsultora(null);           
        f.setTelefono(null);                   
        f.setEstatus(null);                    
        f.setChkBloqueado(null);               
        f.setPrimerPedido(null);	              
        f.setUltimoPedido(null);	              
        f.setRegion(null);	              
        f.setZona(null);                       
        f.setTotalUnid("0");              
        f.setTotalTot("0");
    	f.setTxtnumPedidos("0");// numero de Items
    	f.setNumPedidosRegistrados("0");
    	f.setDeshabilitaCamposDetalle(true);
    	f.setTotalPedido(new BigDecimal("0").setScale(2, RoundingMode.HALF_UP).toString());
	}
		
	/**
	 * Elimina uno o mas detalles de un pedido
	 * @param event
	 */
	public void eliminar(ActionEvent event){
		try {

	    	log.debug("Entro al Eliminar Detalle Pedido");

	    	MantenimientoOCRCapturaPedidosForm f = (MantenimientoOCRCapturaPedidosForm)this.formBusqueda;
	    	Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
	    	MantenimientoOCRCapturaPedidoService service = 
	    		(MantenimientoOCRCapturaPedidoService)getBean("spusicc.pedidos.mantenimientoOCRCapturaPedidoService");

	    	if(this.listaDetallePedSeleccionado != null && this.listaDetallePedSeleccionado.size() > 0)
	    	{
	    		String[] items = new String[this.listaDetallePedSeleccionado.size()+1];
	    		int j = 1;
	    		for (int i = 0; i < this.listaDetallePedSeleccionado.size(); i++) {
	    			
	    			Map aux = (Map)this.listaDetallePedSeleccionado.get(i);
	    			String temp = aux.get("codPais").toString()+"|"+aux.get("numLote").toString()+"|"+aux.get("codCliente").toString()
	    							+"|"+aux.get("codPeriodo").toString()+"|"+aux.get("codigoVenta").toString();
	    			items[j] = temp;
	    			j++;
				}
		    	 f.setSelectedItems2(items);
		    	service.deleteDetallePedidoOnLine(items);
	
		    	//se verifica si el pedido tiene detalles sino los tiene se elimina la cabecera
		    	Map filter = new HashMap();
		 	    filter.put("codigoPais", f.getCodigoPais());
		 	    filter.put("codigoConsultora", f.getCodigoConsultora());
		 	    filter.put("codigoPeriodo", f.getPeriodo());
		 	    filter.put("numLote", f.getNumLote());
		 	    String detalles = service.verificarDetallePedido(filter);
		 	    if (detalles.equals(Constants.NUMERO_CERO)){
		 	    	service.deleteCabeceraPedidoOnLine(filter);
		 	    }
		 	    
				this.listaDetallePedido = new ArrayList();
				String codigoPais = pais.getCodigo();
		    	cargaDetallePedido(service, codigoPais, f.getPeriodo(), f.getCodigoConsultora(), f);
		    
		    	f.setIndicadorDatos("S");
		    	f.setTotalPedido("");
	    	}	    				
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}		
	}
	
	/**
	 * Accion que guarda el pedido
	 */	
	public void guardar() 
	{
		log.debug("Entering 'save' method");
		MantenimientoOCRCapturaPedidosForm f = (MantenimientoOCRCapturaPedidosForm) this.formBusqueda;

		f.setCodigoConsultora(f.getCodigoConsultora2());
		String[] arrCodigosEliminar = new String[]{""};
		
		if(this.listaEliminados != null && this.listaEliminados.size() > 0){
			arrCodigosEliminar = new String[this.listaEliminados.size()];
			for (int i = 0; i < this.listaEliminados.size(); i++) {
				MantenimientoOCRCapturaPedidosForm elim = (MantenimientoOCRCapturaPedidosForm)this.listaEliminados.get(i);
				arrCodigosEliminar[i] = elim.getLabel();				
			}
		}
			
		
		if (f.getRepinta().equals("N")) {
			// aqui se cargara los datos de consultora procedente de la eleccion
			// de una de ellas
			//return mapping.findForward("edit");
		} else {
			LabelDatosConsultoraValue objDatosConsultora = new LabelDatosConsultoraValue();
			// seteo los datos de la cabecera de la consultora
			objDatosConsultora.setPeriodoFacturacion(f.getPeriodo());
			objDatosConsultora.setNumeroPedidosRegistrado(f.getTxtnumPedidos());
			objDatosConsultora.setCodigoConsultora(f.getCodigoConsultora());
			objDatosConsultora.setNombreConsultora(f.getNombreConsultora());
			objDatosConsultora.setTelefono(f.getTelefono());
			objDatosConsultora.setEstatus(f.getEstatus());
			objDatosConsultora.setBloqueado(f.getChkBloqueado());
			objDatosConsultora.setPrimerPedido(f.getPrimerPedido());
			objDatosConsultora.setUltimoPedido(f.getUltimoPedido());
			objDatosConsultora.setRegion(f.getRegion());
			objDatosConsultora.setZona(f.getCodZona());
			objDatosConsultora.setFechaFacturacion(f.getFechaFacturacion());
			objDatosConsultora.setTotalUnidades(f.getTotalUnid());
			objDatosConsultora.setCodPais(f.getCodigoPais());
			objDatosConsultora.setNumLote(f.getNumLote());
			objDatosConsultora.setCodRegion(f.getCodRegion());
			objDatosConsultora.setCodZona(f.getCodZona());
			
			// Setea los datos de los detalles
			ArrayList objListaPedidos = new ArrayList();
			if(this.listaTablaDetalle != null && this.listaTablaDetalle.size() >0)
			{
				String[] objlistaCodigos = new String[this.listaTablaDetalle.size()];
				String[] objlistaUnidades = new String[this.listaTablaDetalle.size()];
				for (int i = 0; i < this.listaTablaDetalle.size(); i++) {
					MantenimientoOCRCapturaPedidosForm f1 = (MantenimientoOCRCapturaPedidosForm) this.listaTablaDetalle.get(i);
					objlistaCodigos[i] = f1.getLabel();
					objlistaUnidades[i] = f1.getLabel4();				
				}
				
				int tamcodigos = objlistaCodigos.length;					
				int j = 0;
				String auxPosi = "";
				
				for (int i = 0; i < tamcodigos; i++) 
				{
					String codigo = objlistaCodigos[i];
					String unidades = objlistaUnidades[i];
					
					if (!codigo.equals("")) 
					{
						auxPosi = String.valueOf(j);
						LabelPedidosValue objPedido = new LabelPedidosValue();
						objPedido.setCodigoVta(codigo);
						objPedido.setUnidades(unidades);
						objPedido.setCodPais(f.getCodigoPais());
						objPedido.setCodPeriodo(f.getPeriodo());
						objPedido.setCodCliente(f.getCodigoConsultora());
						objPedido.setFechaSolicitud(f.getFechaFacturacion());
						objPedido.setNumPosicion(auxPosi);
						objPedido.setNumLote(f.getNumLote());
						objListaPedidos.add(objPedido);
						j++;
					}
				}
			}			
			
			MantenimientoOCRCapturaPedidoService service = (MantenimientoOCRCapturaPedidoService) getBean("spusicc.pedidos.mantenimientoOCRCapturaPedidoService");
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			Map filter = new HashMap();
			filter.put("codPais", f.getCodigoPais());
			filter.put("codCliente", f.getCodigoConsultora());
			filter.put("periodo", f.getPeriodo());
			filter.put("numeroLote", f.getNumLote());

			service.insertarPedido(objDatosConsultora, objListaPedidos, usuario, filter, arrCodigosEliminar);
			this.addInfo("", this.getResourceMessage("mantenimientoOCRCargaPedidosForm.msj.registrar"));
			this.fecha_facturacion_cte = f.getFechaFacturacion();

			// //////////////////////////

			f.setCodigoConsultora("");
			f.setNombreConsultora("");
			f.setRegion("");
			f.setZona("");
			f.setIndicadorActiva("");
			f.setEstadoConsultora("");
			f.setIndicadorBloqueo("");
			f.setIndicadorDatos("N");

			String indicadorHiperConsulta = f.getIndicadorHiperConsulta();
			if (StringUtils.equals(indicadorHiperConsulta, Constants.SI))
				f.setIndicadorCerrarPopup(Constants.SI);
			
			reiniciarPantalla();
		}
	}
	
	public void ejecutar()
	{
		try {
	    	log.debug("Entro AL ejecutarPROL");

	    	MantenimientoOCRCapturaPedidosForm f = (MantenimientoOCRCapturaPedidosForm)this.formBusqueda;
	    	MantenimientoOCRCapturaPedidoService service =
	    		(MantenimientoOCRCapturaPedidoService)getBean("spusicc.pedidos.mantenimientoOCRCapturaPedidoService");
	    	MantenimientoOCRCargaPedidoService service2 =
	    		(MantenimientoOCRCargaPedidoService) getBean("spusicc.pedidos.mantenimientoOCRCargaPedidoService");
	    	ProcesoSTOExecutionService procesoSTOExecutionService =
	    		(ProcesoSTOExecutionService) getBean("spusicc.procesoSTOExecutionService");
	    	ProcesoSTOService procesoSTOService = (ProcesoSTOService) getBean("spusicc.procesoSTOService");
	    	ProcesoSTOLevantamientoErroresValidacionService serviceGestion = 
	    		(ProcesoSTOLevantamientoErroresValidacionService) getBean("spusicc.procesoSTOLevantamientoErroresValidacionService");
	    	ProcesoPEDService servicePed = (ProcesoPEDService) getBean("spusicc.procesoPEDService");
	    	Map criteria = new HashMap();
	    	Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
	    	Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
	    	String codigoDocumento = Constants.STO_TIPO_DOCUMENTO_OCC;

	    	//////////////////////////////////////////////////////////////////////
	    	//Se inserta la cabecera y detalle del pedido
	    	f.setCodigoConsultora(f.getCodigoConsultora2());
	    	String[] arrCodigosEliminar = null;
	    	
	    	if (f.getListaEliminar() != null && f.getListaEliminar().length != 0) {
				arrCodigosEliminar = f.getListaEliminar()[0].split(",");
			}else
				arrCodigosEliminar = new String[]{""};
	    	
	    	LabelDatosConsultoraValue objDatosConsultora= new LabelDatosConsultoraValue();
			//seteo los datos de la cabecera de la consultora
			objDatosConsultora.setPeriodoFacturacion(f.getPeriodo());
		    objDatosConsultora.setNumeroPedidosRegistrado(f.getTxtnumPedidos());
		    objDatosConsultora.setCodigoConsultora(f.getCodigoConsultora());
		    objDatosConsultora.setNombreConsultora(f.getNombreConsultora());
		    objDatosConsultora.setTelefono(f.getTelefono());
		    objDatosConsultora.setEstatus(f.getEstatus());
		    objDatosConsultora.setBloqueado(f.getChkBloqueado());
		    objDatosConsultora.setPrimerPedido(f.getPrimerPedido());
		    objDatosConsultora.setUltimoPedido(f.getUltimoPedido());
		    objDatosConsultora.setRegion(f.getRegion());
		    objDatosConsultora.setZona(f.getCodZona());
		    objDatosConsultora.setFechaFacturacion(f.getFechaFacturacion());
		    objDatosConsultora.setTotalUnidades(f.getTotalUnid());
		    objDatosConsultora.setCodPais(f.getCodigoPais());
		    objDatosConsultora.setNumLote(f.getNumLote());
		    objDatosConsultora.setCodRegion(f.getCodRegion());
		    objDatosConsultora.setCodZona(f.getCodZona());

			// Setea los datos de los detalles
		    ArrayList objListaPedidos = new ArrayList();
			if(this.listaTablaDetalle != null && this.listaTablaDetalle.size() >0)
			{
				String[]objlistaCodigos   = new String[this.listaTablaDetalle.size()];
				String[]objlistaUnidades  = new String[this.listaTablaDetalle.size()];
				
				for (int i = 0; i < this.listaTablaDetalle.size(); i++) {
					MantenimientoOCRCapturaPedidosForm f1 = (MantenimientoOCRCapturaPedidosForm) this.listaTablaDetalle.get(i);
					objlistaCodigos[i] = f1.getLabel();
					objlistaUnidades[i] = f1.getLabel4();				
				}
				
				int tamcodigos = objlistaCodigos.length;
				int j = 0;
				String auxPosi = "";

				for (int i=0; i<tamcodigos; i++){
			    	String codigo      = objlistaCodigos[i];
			        String unidades    = objlistaUnidades[i];

			        if (!codigo.equals("")){
			        	auxPosi = String.valueOf(j);
			        	LabelPedidosValue objPedido = new LabelPedidosValue();
		    	        objPedido.setCodigoVta(codigo);
		    	        objPedido.setUnidades(unidades);
		    	        objPedido.setCodPais(f.getCodigoPais());
		    	        objPedido.setCodPeriodo(f.getPeriodo());
		    	        objPedido.setCodCliente(f.getCodigoConsultora());
		    	        objPedido.setFechaSolicitud(f.getFechaFacturacion());
		    	        objPedido.setNumPosicion(auxPosi);
		    	        objPedido.setNumLote(f.getNumLote());
		    	        objListaPedidos.add(objPedido);
		    	        j++;
			        }
			    }				
			}

			//********************************************************************
			// Se verifica si existe pedido para la consultora, Si existe se elimina el pedido
	    	Map params = new HashMap();
	    	String secuencia =  procesoSTOService.getSecuenciaConsultaDocumento();
	    	params.put("codigoPais", f.getCodigoPais());
	    	params.put("tipoDocumento", codigoDocumento);
	    	params.put("secuencia", secuencia);
	    	params.put("codigoOrigen", "OL");
	    	params.put("codigoCliente", f.getCodigoConsultora());
	    	params.put("codPeriodo",f.getPeriodo());
	    	params.put("codigoPeriodo",f.getPeriodo());
	    	/*params.put("fechaInicio","");
	    	params.put("fechaFin","");
	    	params.put("fechaInicioProceso","");
	    	params.put("fechaFinProceso","");
	    	params.put("fechaInicioProgramadaFacturacion","");
	    	params.put("fechaFinProgramadaFacturacion","");
	    	params.put("numLote","");
	    	params.put("estadoGP1","");
	    	params.put("estadoGP2","");
	    	params.put("estadoGP3","");
	    	params.put("estadoGP4","");
	    	params.put("estadoGP5","");
	    	params.put("estadoFacturadas","");
	    	params.put("estadoError","");
	    	params.put("estadoRechazadas","");
	    	params.put("estadoSinValidar","");
	    	params.put("oidClienteCarga","");
	    	params.put("clienteList",new ArrayList());
	    	params.put("regionList",new ArrayList());
			params.put("zonaList",new ArrayList());*/
			params.put("usuario",usuario);

			int existePedido = 0;
			existePedido = service.existePedido(params);
			
			if (existePedido > 0){
				List consultaValidacionList =  procesoSTOService.getConsultaPedidosOnlineList(params);
		    	
		    	if (consultaValidacionList.size() > 0){
		    		eliminarPedido(consultaValidacionList, params, f, procesoSTOExecutionService);
		    	}	
			}

	    	//********************************************************************

		    service.insertarPedido(objDatosConsultora, objListaPedidos,	usuario, criteria, arrCodigosEliminar);

		    this.fecha_facturacion_cte = f.getFechaFacturacion();

		    //////////////////////////////////////////////////////////////////////
		    criteria.put("codigoPais", f.getCodigoPais());
	    	criteria.put("codPais", f.getCodigoPais());
	    	criteria.put("codCliente", f.getCodigoConsultora());
	    	criteria.put("codigoPeriodo", f.getPeriodo());
	    	criteria.put("numLote", f.getNumLote());
	    	criteria.put("codigoDocumento", codigoDocumento);
	    	criteria.put("codTipoDocu", codigoDocumento);
	    	criteria.put("tipoDocumento",codigoDocumento);
	    	criteria.put("usuario", usuario);
	    	criteria.put("codigoUsuario", usuario.getLogin());
	    	criteria.put("periodo", f.getPeriodo());
	    	criteria.put("numeroLote", f.getNumLote());

	    	//Se actualiza el indicador de envio OCS
	    	service.actualizaIndicadorOCS(criteria);

	    	//obteniendo el numero de lote actual de la campana
	    	String numeroLote = service.getNumeroLoteByPk(criteria);
	    	criteria.put("numLote", numeroLote);

	    	//Obteniendo el numero de lote sto
	    	String numLoteSTO = procesoSTOService.updateLoteSTO(new TipoDocumentoDigitadoPK(f.getCodigoPais(),Constants.STO_TIPO_DOCUMENTO_OCC));
	    	criteria.put("numLoteSTO", numLoteSTO);
	    	criteria.put("indOrigen","L");

	    	//Consolidando los pedidos
	    	//service2.executeEnviarDetallesDigitados(criteria);
	    	service2.executeConsolidaPedidoOnline(criteria);
	    	servicePed.executeConsolidadoPedidos(criteria);

	    	//Se llama a STO
	    	List lista = procesoSTOService.getDocumentoDigitadoPKByLote(criteria);
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

	    	AccionTipoDocumento accionTipoDocumento =
	    		new AccionTipoDocumento(pais.getCodigo(), codigoDocumento, Constants.STO_ACCI_VALI_ON_LINE);
	    	criteria.put("accionTipoDocumento", accionTipoDocumento);

	    	procesoSTOExecutionService.execute(accionTipoDocumento,criteria, listaSTO);

	    	//Verificamos si  Existen Errores STO En caso existan los mostramos
	    	criteria.put("tipoDocumento",codigoDocumento);
	    	List listaErroresValidacion =  serviceGestion.getGestionDocumentoList(criteria);
			if (listaErroresValidacion.size()>0){
				this.addError("Error: ", this.getResourceMessage("mantenimientoOCRCargaPedidosForm.errors.gestion"));
				this.grabar = "";
				
				for (Iterator iterator = listaErroresValidacion.iterator(); iterator.hasNext();) {
		    		 GestionDocumento gestionDocumento = (GestionDocumento) iterator.next();
		    		 this.addError("Error: ", this.getResourceMessage("errors.detail", new Object[]{gestionDocumento.getDesValidacionLarga()}));
		  		}
			}

			
			//Se obtiene la fecha de facturacion
			String fecha = service.getFechaFacturacion(criteria);
			criteria.put("fechaProceso", fecha);

			//Se obtiene la secuencia del documento
			String secuenciaDoc = service.getNumeroSecuenciaDocumento(criteria);
			criteria.put("secNumDoc", secuenciaDoc);

			//Se obtiene el oid de la solicitud
			String oidSolicitud = service.getOidSolicitud(criteria);
			if (oidSolicitud != null){
				criteria.put("oidSolicitud", oidSolicitud);
			}
			else{
				criteria.put("oidSolicitud", "0");
			}

			//Se llama a Sicc
			service.executeEjecutarGP2(criteria);
			f.setIndicadorDatos("N");
			
			reiniciarPantalla();
	    		
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
			this.grabar = "";
		}
	}
	
	public void ejecutaPROL(ActionEvent event)
	{
		try {
			if (this.listaTablaDetalle == null){
				this.addError("Error: ", this.getResourceMessage("mensaje.error.ingresoDetallePedido"));
				this.grabar = "";
				return;
			}
			
			setConsultora();
		    this.prol = "1";
		    setValidarGuardar();			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}		
	}
	
	/**
	 * Metodo que se encarga de reiniciar la pantalla
	 */
	public void reiniciarPantalla()
	{
		MantenimientoOCRCapturaPedidosForm f = (MantenimientoOCRCapturaPedidosForm)this.formBusqueda;
		reiniciarDatatable(f);
		f.setLabel("");
		f.setLabel2("");
		f.setLabel3("");
		f.setLabel4("");
		f.setLabel5("");
		f.setTotalPedido(new BigDecimal("0").setScale(2, RoundingMode.HALF_UP).toString());
		f.setTxtnumPedidos("0");
		f.setTotalUnid("0");
		this.listaDetallePedido = new ArrayList();
		this.tablaDetallePedidoModel = new DataTableModel(this.listaDetallePedido);
		this.idPROL = false;
		this.idPedido = false;
		this.idPedido2 = false;	
		this.deshabilitaCampos = false;
		this.grabar = "";
	}
	
	/**
	 * Metodo que permite elegir a que metodo direccionar deacuerdo al parametro
	 * @param event
	 */
	public void setEscogerMetodo(ActionEvent event)
	{
		try{
			if(this.prol.equals("1"))
				ejecutar();
			else
				guardar();
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	/**
	 * Metodo que se ejecuta cuando damos click en aceptar de la ventana de confirmacion
	 * que se muestra al ingresar un codigo de consultora con pedido existente.
	 *  
	 * Lista el detalle de pedido de una consultora
	 * @param event
	 */
	public void listar(ActionEvent event){
	   	try {
	   		log.debug("Entro al Listar Detalle Pedido");

	    	MantenimientoOCRCapturaPedidosForm f = (MantenimientoOCRCapturaPedidosForm)this.formBusqueda;
	    	MantenimientoOCRCapturaPedidoService service = 
	    		(MantenimientoOCRCapturaPedidoService)getBean("spusicc.pedidos.mantenimientoOCRCapturaPedidoService");
	    	
	    	Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

	    	String codigoPais = pais.getCodigo();
	    	cargaDetallePedido(service, codigoPais, f.getPeriodo(), f.getCodigoConsultora(), f);
	    	
	    	//Direcciona el cursor a la primera fila de la tabla detalle
	    	 this.getRequestContext().execute("PrimeFaces.focus('tablaDetalleN:0:label')");
	    	
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	public void setValidarGuardar() {
		try {
			MantenimientoOCRCapturaPedidosForm f = (MantenimientoOCRCapturaPedidosForm) this.formBusqueda;
			this.grabar = "OK";

			if (!validaCantidad()) {
				return;
			}

			// Validamos el monto minimo del pedido

			String montoMinimo = f.getMontoMinimo();
			BigDecimal mtp = recalcularMontoTotalPedido();

			if (mtp.compareTo(new BigDecimal(montoMinimo)) < 1) {
				String ventana = "confirmDialogMontoMinimo";
				String ventanaConfirmar = "PF('" + ventana
						+ "_confirmationDialogConfirmarSalir').show()";
				this.getRequestContext().execute(ventanaConfirmar);
				return;
			}

			validarMetodo();
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	/**
	 * Valida que se haya ingresado una cantidad
	 * @return
	 */
	public boolean validaCantidad() 
	{
		int flag = -1;

		if (this.listaTablaDetalle != null && this.listaTablaDetalle.size() > 0) 
		{
			for (int i = 0; i < this.listaTablaDetalle.size(); i++) 
			{
				MantenimientoOCRCapturaPedidosForm f = (MantenimientoOCRCapturaPedidosForm) this.listaTablaDetalle.get(i);
				if (StringUtils.isBlank(f.getLabel4())) 
				{
					if (StringUtils.isNotBlank(f.getLabel()))
						flag = i;
				}
			}
			
			if (flag != -1) {
				this.addWarn("Advertencia: ", this.getResourceMessage("mensaje.ingreseCantidades"));
				this.grabar = "";
				return false;
			}

		} else {
			this.addWarn("Advertencia: ", this.getResourceMessage("mensaje.ingreseCantidades"));
			this.grabar = "";
			return false;
		}

		return true;
	}
	
	private BigDecimal recalcularMontoTotalPedido()
	{
		//Recalculamos el monto total del pedido: montoTotalPedido VIENE DE BD		
		BigDecimal montoTPAux = new BigDecimal(StringUtils.isNotBlank(this.totalPedidoGuardado)?this.totalPedidoGuardado:"0");		
		
		for(int j = 0; j < this.listaTablaDetalle.size() ; j++)
		{
			MantenimientoOCRCapturaPedidosForm f = (MantenimientoOCRCapturaPedidosForm) this.listaTablaDetalle.get(j);
			if (StringUtils.isNotBlank(f.getLabel5())){
				montoTPAux = montoTPAux.add(new BigDecimal(f.getLabel5())).setScale(2, RoundingMode.HALF_UP);
		    }   
	    }   
		
		return montoTPAux;
	}
	
	public void validarMetodo()
	{
		MantenimientoOCRCapturaPedidosForm f = (MantenimientoOCRCapturaPedidosForm)this.listaTablaDetalle.
				get(this.listaTablaDetalle.size()-1);
		//-------------------------------------
		int unidMaximas = Integer.parseInt(f.getUnidadesMaximas());
		
		//--Cambio validacion unidades con CUVs acumulados--
		int acumulado = StringUtils.isNotBlank(f.getLabel4())?Integer.parseInt(f.getLabel4()): 0;
		//--------------------------------------------------
		for (int j = 0; j < this.listaTablaDetalle.size()-1; j++) 
		{
			MantenimientoOCRCapturaPedidosForm f1 = (MantenimientoOCRCapturaPedidosForm)this.listaTablaDetalle.get(j);
			if(f.getLabel().equals(f1.getLabel())){
				acumulado = acumulado + Integer.parseInt(f.getLabel4());
			}				
		}
		
		 if(StringUtils.isNotBlank(f.getLabel4()) && Integer.parseInt(f.getLabel4()) > unidMaximas){
			 String mensaje = this.getResourceMessage("mensaje.confirm.mayor99") +unidMaximas+ this.getResourceMessage("mensaje.alert.modificarRegistro");
			 this.addWarn("Advertencia: ", mensaje);
			 this.grabar = "";
			 return;
		 }else{
			 //--Cambio validacion unidades con CUVs acumulados--
			 if(acumulado > unidMaximas){
				 String mensaje = this.getResourceMessage("mensaje.confirm.mayor99") +unidMaximas+ this.getResourceMessage("mensaje.alert.modificarRegistro");
				 this.addWarn("Advertencia: ", mensaje);
				 this.grabar = "";
				 return;
			 }
		 }
		 
		// Validación de Bloqueo en Digitacion de Pedidos
		String codigoCliente = f.getCodigoConsultora();
		
		if(StringUtils.isNotBlank(codigoCliente)){
			
			AjaxService ajax = (AjaxService) getBean("ajaxService");			
			String validacionBloqueo = ajax.validarBloqueoDigitacionPedidos(codigoCliente);
			
			if(validacionBloqueo.equals("S"))
			{				
				String indicadorHiperConsulta= f.getIndicadorHiperConsulta();
				MantenimientoOCRCapturaPedidosForm formPrincipal = (MantenimientoOCRCapturaPedidosForm)this.formBusqueda;
		    	  if (indicadorHiperConsulta.equals("S")) 
		    	  {		    		  
		    		  formPrincipal.setRepinta("R");
		    		  formPrincipal.setCodigoConsultora2(formPrincipal.getCodigoConsultora());
		    		  
		    		  String ventanaConfirmar = "PF('confirmarPopup_confirmationDialogConfirmar').show()";
		    		  this.getRequestContext().execute(ventanaConfirmar);
		    	  }else{
		    		  formPrincipal.setRepinta("R");
		    		  formPrincipal.setCodigoConsultora2(formPrincipal.getCodigoConsultora());
		    		  
		    		  String ventanaConfirmar = "PF('confirmarEjecucion_confirmationDialogConfirmarSalir').show()";
		    		  this.getRequestContext().execute(ventanaConfirmar);		    		  
		    	  }		    	  
			}else
			{
				this.grabar = "";
				this.addWarn("", this.getResourceMessage("mensaje.consultora.bloqueada"));				
			}
		}		
	}
	
	 /**
     * Carga la lista con Los detalles del pedido
     * @param service
     * @param codPais
     * @param codPeriodo
     * @param codConsultora
     */
    public void cargaDetallePedido(MantenimientoOCRCapturaPedidoService service, String codPais, String codPeriodo, String codConsultora, MantenimientoOCRCapturaPedidosForm f )
    {
    	Map criteria = new HashMap();

    	criteria.put("codigoPais",  codPais);
    	criteria.put("codigoPeriodo", codPeriodo);
    	criteria.put("codigoConsultora", codConsultora);

    	List lista = service.getListaDetallePedido(criteria);
    	this.listaDetallePedido = null;
    	this.listaDetallePedido = lista;
    	this.tablaDetallePedidoModel = new DataTableModel(this.listaDetallePedido);
    	 
    	String totalPedido = service.getSumaTotalPedidoListaDetallePedido(criteria);
    	this.totalPedidoGuardado = totalPedido;
    	f.setTotalPedido(totalPedido);
    }
    
    /**
     * Elimina el pedido de una consultora
     * 
     * @param lista
     * @param params
     * @param f
     * @param service
     * @throws Exception
     */
    private void eliminarPedido(List lista, Map params, MantenimientoOCRCapturaPedidosForm f, ProcesoSTOExecutionService service)
      throws Exception {
    	log.debug("Entro al Eliminar Pedido PROL");

    	String accion = "ELS";
    	String codPais = f.getCodigoPais();
    	String codTipoDocumento = Constants.STO_TIPO_DOCUMENTO_OCC;
    	params.put("codigoAccionEjecutada", Constants.STO_INDICADOR_ELIMINACION + accion);
    	params.put("codTipoDocu", codTipoDocumento);

    	AccionTipoDocumento accionTipoDocumento = new AccionTipoDocumento(codPais, codTipoDocumento, accion);
    	service.execute(accionTipoDocumento, params, lista);
    }
	
	@Override
	protected void setInvocarPopup(String accion) 
	{
		this.mostrarProcesoBatch = false;
		if (accion.equals(POPUP_OCRCLIENTE)) {
			this.mostrarPopupOCRCliente = true;
		}		
	}
	
	@Override
	protected void setAceptarPopup(ActionEvent event, String accion) 
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setAceptarPopupHipCliente' method");
		}

		if (accion.equals(POPUP_OCRCLIENTE)) 
		{
			this.busquedaConsultoraPOPUPSearchAction.verificarRegistro(event);
			if (this.busquedaConsultoraPOPUPSearchAction.isSeleccionoRegistro()) 
			{
				Cliente cliente = (Cliente) this.busquedaConsultoraPOPUPSearchAction.getBeanRegistroSeleccionado();
				MantenimientoOCRCapturaPedidosForm busquedaForm = (MantenimientoOCRCapturaPedidosForm) this.formBusqueda;
				busquedaForm.setCodigoConsultora(cliente.getCodigo());
				
				this.busquedaConsultoraPOPUPSearchAction.setBeanRegistroSeleccionado(null);
				
				this.getRequestContext().execute("PrimeFaces.focus('codigoConsultora')");
			}
		}
		if (log.isDebugEnabled()) {
			log.debug("Finish 'PopupHipCliente' method");
		}
	}
	
	@Override
	protected void setSalirPopup() 
	{
		this.mostrarPopupOCRCliente = false;
		this.busquedaConsultoraPOPUPSearchAction.setBeanRegistroSeleccionado(null);
	}
	
	public void searchConsultoraOnEnter()
	{
		MantenimientoOCRCapturaPedidosForm f = (MantenimientoOCRCapturaPedidosForm)this.formBusqueda;
		if(StringUtils.isNotBlank(f.getCodigoConsultora()))
		{
			f.setCodigoConsultora(StringUtils.leftPad(f.getCodigoConsultora(), Integer.parseInt(this.longitudCampoClientes), "0"));
			seteaCodigoConsultora(f.getCodigoConsultora());
		}		
	}
	
	public void seteaConsultora()
	{
		MantenimientoOCRCapturaPedidosForm f = (MantenimientoOCRCapturaPedidosForm)this.formBusqueda;
		if(StringUtils.isNotBlank(f.getCodigoConsultora()))
		{
			if(f.getCodigoConsultora().length() == Integer.parseInt(this.longitudCampoClientes))
				seteaCodigoConsultora(f.getCodigoConsultora());
		}
	}
	
	public void seteaCodigoConsultora(String valor)
	{
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoOCRCapturaPedidosForm f = (MantenimientoOCRCapturaPedidosForm)this.formBusqueda;
		
		LabelDatosConsultoraValue[] resultado = ajax.getCabeceraConsultoraSimple(pais.getCodigo(), valor);
		
		limpiarCampos();
		
		if(resultado != null){
			f.setNombreConsultora(resultado[0].getNombreConsultora());
			f.setRegion(resultado[0].getRegion());
			f.setZona(resultado[0].getZona());
			f.setIndicadorActiva(resultado[0].getIndicadorActiva());
			f.setEstadoConsultora(resultado[0].getEstadoConsultora());
			f.setIndicadorBloqueo(resultado[0].getIndicadorBloqueo());
			
			Integer bloqueo = ajax.getBloqueoOnline(pais.getCodigo(), f.getCodigoConsultora(), f.getPeriodo());
			
			if(bloqueo != null)
			{
				if(bloqueo > 0){
					this.idPROL = true;
					this.idPedido = false;
					this.idPedido2 = false;					
				}else{
					this.idPROL = false;
					this.idPedido = true;
					this.idPedido2 = true;						
				}
			}else{
				this.idPROL = false;
				this.idPedido = true;
				this.idPedido2 = true;
			}
			
			if(!this.grabar.equals("OK")){
				String existePedido = ajax.getExistePedidoConsultora(pais.getCodigo(), f.getCodigoConsultora(), f.getPeriodo(), f.getNumLote());
				
				if(StringUtils.isNotBlank(existePedido)){
					String strUser = existePedido.split("\\|")[0];
					String strFecha = existePedido.split("\\|")[1];
					String ventana = "confirmDialogConsultora";
					String retornoMensaje = "";
					
					RequestContext context = RequestContext.getCurrentInstance(); 
					
					retornoMensaje = "El pedido ya existe \n Usuario Digitador :" 
							+ strUser+ "\n Fecha de digitacion : " +
				             strFecha + "\n¿Desea continuar?";
						
					context.addCallbackParam("retornoMensaje", retornoMensaje);
					
					String ventanaConfirmar = "PF('" + ventana + "_confirmationDialogConfirmarSalir').show()";
					this.getRequestContext().execute(ventanaConfirmar);
					this.panelpedidosExistentes = true;
				}else
				this.panelpedidosExistentes = false;
				
				this.getRequestContext().execute("PrimeFaces.focus('tablaDetalleN:0:label')");
				
			}
			
			cargarMontoMinimo();
		}else {
			this.addWarn("Advertencia: ", this.getResourceMessage("mensaje.noExisteConsultora"));
			f.setCodigoConsultora("");
			this.idPROL = false;
			this.idPedido = false;
			this.idPedido2 = false;
		}	
	}
	
	public void setConsultora()
	{
		try {
			AjaxService ajax = (AjaxService)getBean("ajaxService");
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			MantenimientoOCRCapturaPedidosForm f = (MantenimientoOCRCapturaPedidosForm)this.formBusqueda;
			
			LabelDatosConsultoraValue[] resultado = ajax.getCabeceraConsultoraSimple(pais.getCodigo(), f.getCodigoConsultora());
			
			limpiarCampos();
			
			if(resultado != null){
				f.setNombreConsultora(resultado[0].getNombreConsultora());
				f.setRegion(resultado[0].getRegion());
				f.setZona(resultado[0].getZona());
				f.setIndicadorActiva(resultado[0].getIndicadorActiva());
				f.setEstadoConsultora(resultado[0].getEstadoConsultora());
				f.setIndicadorBloqueo(resultado[0].getIndicadorBloqueo());
				
				Integer bloqueo = ajax.getBloqueoOnline(pais.getCodigo(), f.getCodigoConsultora(), f.getPeriodo());
				
				if(bloqueo != null)
				{
					if(bloqueo > 0){
						this.idPROL = true;
						this.idPedido = false;
						this.idPedido2 = false;					
					}else{
						this.idPROL = false;
						this.idPedido = true;
						this.idPedido2 = true;						
					}
				}else{
					this.idPROL = false;
					this.idPedido = true;
					this.idPedido2 = true;
				}
				
				if(!this.grabar.equals("OK")){
					String existePedido = ajax.getExistePedidoConsultora(pais.getCodigo(), f.getCodigoConsultora(), f.getPeriodo(), f.getNumLote());
					
					if(StringUtils.isNotBlank(existePedido)){
						String strUser = existePedido.split("\\|")[0];
						String strFecha = existePedido.split("\\|")[1];
						String ventana = "confirmDialogConsultoraGuardar";
						String retornoMensaje = "";
						
						RequestContext context = RequestContext.getCurrentInstance(); 
						
						retornoMensaje = "El pedido ya existe \n Usuario Digitador :" 
								+ strUser+ "\n Fecha de digitacion : " +
					             strFecha + "\n¿Desea continuar?";
							
						context.addCallbackParam("retornoMensaje", retornoMensaje);
						
						String ventanaConfirmar = "PF('" + ventana + "_confirmationDialogConfirmarSalir').show()";
						this.getRequestContext().execute(ventanaConfirmar);
						
					}else
						setValidarGuardar();
				}
				
				cargarMontoMinimo();
			}else {
				this.addWarn("Advertencia: ", this.getResourceMessage("mensaje.noExisteConsultora"));
				f.setCodigoConsultora("");
				this.idPROL = false;
				this.idPedido = false;
				this.idPedido2 = false;
			}				
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	/**
	 * Metodo que se ejecuta cuando damos click en cancelar de la ventana de confirmacion
	 * que se muestra al ingresar un codigo de consultora con pedido existente
	 * @param event
	 */
	public void limpiarCabecera(ActionEvent event)
	{
		try {
			MantenimientoOCRCapturaPedidosForm f = (MantenimientoOCRCapturaPedidosForm)this.formBusqueda;
			f.setIndicadorDatos("N");
			
			f.setNombreConsultora("");
			f.setRegion("");
			f.setZona("");
			f.setCodigoConsultora("");
			f.setIndicadorActiva("");
			f.setIndicadorBloqueo("");
			f.setEstadoConsultora("");
			f.setTotalPedido(new BigDecimal("0").setScale(2, RoundingMode.HALF_UP).toString());
			reiniciarDatatable(f);

			this.grabar = "";
			this.idPROL = false;
			this.idPedido = false;
			this.idPedido2 = false;	
			this.deshabilitaCampos = false;
			this.listaDetallePedido = new ArrayList();
			this.tablaDetallePedidoModel = new DataTableModel(this.listaDetallePedido);
			
			this.getRequestContext().execute("PrimeFaces.focus('codigoConsultora')");
			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
		
	}
		
	public void completarNumeroDocIdentidad()
	{
		MantenimientoOCRCapturaPedidosForm f = (MantenimientoOCRCapturaPedidosForm)this.formBusqueda;
		if(f.isIndicadorCompletarCerosNumDocumento() && StringUtils.isNotBlank(f.getNumeroDocIdentidadBuscar()))
		{
			if(f.getNumeroDocIdentidadBuscar().length() <= 10)
				f.setNumeroDocIdentidadBuscar(StringUtils.leftPad(f.getNumeroDocIdentidadBuscar(), 10, "0"));
			else
			{
				int tamanio = f.getNumeroDocIdentidadBuscar().length() - 10;
				String numFinal = f.getNumeroDocIdentidadBuscar().substring(tamanio);
				f.setNumeroDocIdentidadBuscar(numFinal);
			}
		}		
	}
	
	public void buscarCliente()
	{
		MantenimientoOCRCapturaPedidosForm f = (MantenimientoOCRCapturaPedidosForm)this.formBusqueda;
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		LabelDatosConsultoraValue[] resultado = new LabelDatosConsultoraValue[]{};
		limpiarCampos();
		
		if(StringUtils.isNotBlank(f.getNumeroDocIdentidadBuscar()))
		{
			f.setCodigoConsultora(ajax.getCodigoClienteByDocumentoIdentidad(f.getNumeroDocIdentidadBuscar()));
			if(StringUtils.isBlank(f.getCodigoConsultora())){
				this.addWarn("", "Documento No Existe");
				return;
			}
		}
		else{
			this.addWarn("", "Ingrese Numero de Documento");
			return;
		}
		
		if(StringUtils.isNotBlank(f.getCodigoConsultora()))
//			ajax.getConsultoraExistenteByCriteria(codigoPais, periodo, codigoConsultora, fechaFacturacion);
			resultado = ajax.getCabeceraConsultoraSimple(pais.getCodigo(), f.getCodigoConsultora());
				
		if(resultado != null && resultado.length > 0){
			f.setNombreConsultora(resultado[0].getNombreConsultora());
			f.setRegion(resultado[0].getRegion());
			f.setZona(resultado[0].getZona());
			f.setIndicadorActiva(resultado[0].getIndicadorActiva());
			f.setEstadoConsultora(resultado[0].getEstadoConsultora());
			f.setIndicadorBloqueo(resultado[0].getIndicadorBloqueo());
			
			Integer bloqueo = ajax.getBloqueoOnline(pais.getCodigo(), f.getCodigoConsultora(), f.getPeriodo());
			
			if(bloqueo != null)
			{
				if(bloqueo > 0){
					this.idPROL = true;
					this.idPedido = false;
					this.idPedido2 = false;					
				}else{
					this.idPROL = false;
					this.idPedido = true;
					this.idPedido2 = true;						
				}
			}else{
				this.idPROL = false;
				this.idPedido = true;
				this.idPedido2 = true;
			}
			
			if(!this.grabar.equals("OK")){
				String existePedido = ajax.getExistePedidoConsultora(pais.getCodigo(), f.getCodigoConsultora(), f.getPeriodo(), f.getNumLote());
				
				if(StringUtils.isNotBlank(existePedido)){
					String strUser = existePedido.split("\\|")[0];
					String strFecha = existePedido.split("\\|")[1];
					String ventana = "confirmDialogConsultora";
					String retornoMensaje = "";
					
					RequestContext context = RequestContext.getCurrentInstance(); 
					
					retornoMensaje = "El pedido ya existe \n Usuario Digitador :" 
							+ strUser+ "\n Fecha de digitacion : " +
				             strFecha + "\n¿Desea continuar?";
						
					context.addCallbackParam("retornoMensaje", retornoMensaje);
					
					String ventanaConfirmar = "PF('" + ventana + "_confirmationDialogConfirmarSalir').show()";
					this.getRequestContext().execute(ventanaConfirmar);
					
				}
			}
			
			cargarMontoMinimo();
		}		
	}
	
	public void searchCodigoVentaOnEnter()
	{
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		int  i = Integer.parseInt(externalContext.getRequestParameterMap().get("index"));
		
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoOCRCapturaPedidosForm f = (MantenimientoOCRCapturaPedidosForm)this.listaTablaDetalle.get(i);
		MantenimientoOCRCapturaPedidosForm formPrincipal = (MantenimientoOCRCapturaPedidosForm)this.formBusqueda;
		
		if(StringUtils.isNotBlank(f.getLabel())){
			f.setLabel(StringUtils.leftPad(f.getLabel(), 5, "0"));
			
			if(StringUtils.isBlank(formPrincipal.getCodigoConsultora())){
				f.setLabel("");				
				this.addWarn("Advertencia:", "Seleccione una Consultora.");
			}else
			{
				f.setCodigoConsultora(formPrincipal.getCodigoConsultora());
				
				if(!validaCodigoVentaMemoria(f))
					return;
				
				if(!validarCUVExistente(f.getLabel(), i))
				{
					String statusFaltanteAnunciado = ajax.validarCUVFaltanteAnunciado(f.getLabel(), f.getPeriodo(), f.getCodigoConsultora());
					if(statusFaltanteAnunciado.equalsIgnoreCase("false"))
					{
						LabelPedidosValue[] resultado = ajax.getDescPrecio2(pais.getCodigo(), f.getPeriodo(), f.getCodigoConsultora(), 
								f.getNumLote(), f.getLabel());
						
						pasarDatos(f, resultado);
						
						this.getRequestContext().execute("PrimeFaces.focus('tablaDetalleN:"+(i)+":label4')");
						
						if(StringUtils.isNotBlank(f.getLabel3())){
							String ofertaComp = ajax.validarCUVCompuesta(f.getLabel(), f.getPeriodo());
							if(Double.parseDouble(ofertaComp) > 0)
								f.setLabel3(ofertaComp);
						}
					}else
					{
						this.addWarn("Advertencia", statusFaltanteAnunciado);
						f.setLabel("");
					}
				}else
				{
					f.setLabel("");
					this.addWarn("Advertencia: ", "El producto ya esta registrado");
				}
			}
		}
	}
	
	public void searchCodigoVentaOnPress()
	{
//		int i = new Integer(indice).intValue();
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		int  i = Integer.parseInt(externalContext.getRequestParameterMap().get("index"));
		
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoOCRCapturaPedidosForm f = (MantenimientoOCRCapturaPedidosForm)this.listaTablaDetalle.get(i);
		MantenimientoOCRCapturaPedidosForm formPrincipal = (MantenimientoOCRCapturaPedidosForm)this.formBusqueda;
		
		if(f.getLabel().length() == 5)
		{
			if(StringUtils.isBlank(formPrincipal.getCodigoConsultora())){
				f.setLabel("");				
				this.addWarn("Advertencia:", "Seleccione una Consultora.");
			}else
			{
				f.setCodigoConsultora(formPrincipal.getCodigoConsultora());
				
				if(!validaCodigoVentaMemoria(f))
					return;
				
				if(!validarCUVExistente(f.getLabel(), i))
				{
					String statusFaltanteAnunciado = ajax.validarCUVFaltanteAnunciado(f.getLabel(), f.getPeriodo(), f.getCodigoConsultora());
					if(statusFaltanteAnunciado.equalsIgnoreCase("false"))
					{
						LabelPedidosValue[] resultado = ajax.getDescPrecio2(pais.getCodigo(), f.getPeriodo(), f.getCodigoConsultora(), 
								f.getNumLote(), f.getLabel());
						
						pasarDatos(f, resultado);
						
						this.getRequestContext().execute("PrimeFaces.focus('tablaDetalleN:"+(i)+":label4')");
						
						if(StringUtils.isNotBlank(f.getLabel3())){
							String ofertaComp = ajax.validarCUVCompuesta(f.getLabel(), f.getPeriodo());
							if(Double.parseDouble(ofertaComp) > 0)
								f.setLabel3(ofertaComp);
						}	
					}else
					{
						this.addWarn("Advertencia", statusFaltanteAnunciado);
						f.setLabel("");
					}
				}else
				{
					f.setLabel("");
					this.addWarn("Advertencia: ", "El producto ya esta registrado");
				}				
			}			
		}		
	}
	
	private void pasarDatos(MantenimientoOCRCapturaPedidosForm f, LabelPedidosValue[] resultado)
	{
		if(resultado != null){
			f.setLabel2(resultado[0].getDescripcion());
			f.setLabel3(resultado[0].getPrecioCat());
			
			if(!resultado[0].getUnidades().equals("0")){
				f.setLabel4(resultado[0].getUnidades());
			}
			
			f.setDeshabilitaCamposDetalle(false);
			f.setLabel5(resultado[0].getTotal());
			//Label6 es solo creado en la vista
			// label6 = resultado[0].getTotal();	
			
			if(Integer.parseInt(resultado[0].getUnidades()) > 0)
			{
				this.setMensajeAlertaDefault(this.getResourceMessage("mensaje.codigoVtaExiste"));
				String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
				this.getRequestContext().execute(ventanaConfirmar);
//				this.addWarn("Advertencia: ", this.getResourceMessage("mensaje.codigoVtaExiste"));
			}			 
		}else{
			f.setLabel("");							
			this.addError("Error: ", this.getResourceMessage("mensaje.codVtaNoExiste"));
		}		
	}
	
	/**
	 * Metodo encargado de agregar una nueva fila en la tabla Detalle
	 */
	public void agregarDetalle()
	{		
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		int  i = Integer.parseInt(externalContext.getRequestParameterMap().get("index"));
			
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		MantenimientoOCRCapturaPedidosForm f = (MantenimientoOCRCapturaPedidosForm)this.listaTablaDetalle.get(i);
		
		boolean statusIgualZero = false;
		boolean statusLimiteVenta = false;
		boolean agregar = false;
		
		if(StringUtils.isNotBlank(f.getLabel4()))
		{	
			String resultado = ajax.validarCUVLimiteVenta(f.getLabel(), f.getPeriodo(), f.getCodigoConsultora(), f.getLabel4());
			String msg = "";
			
			if(!resultado.equalsIgnoreCase("false")){
				int valid = resultado.lastIndexOf("|");
				if(valid != -1){
					statusIgualZero = true;  
					msg = resultado.split("\\|")[0];
				}else{
					   msg = resultado;
					}
				
				this.addWarn("Advertencia:", msg);
//				this.setMensajeAlertaDefault(msg);
//				String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
//				this.getRequestContext().execute(ventanaConfirmar);
				statusLimiteVenta = true;
				this.getRequestContext().execute("PrimeFaces.focus('tablaDetalleN:"+(i)+":label')");
			}
			
			if(statusLimiteVenta){
				f.setLabel4("");
				// valida si el resultado es igual a 0 = true
				if(statusIgualZero){
					f.setLabel("");
					f.setLabel3("");
					f.setLabel2("");
				}
				
				calcularTotales();
				return;				
			}
			
			int unidMaximas = Integer.parseInt(f.getUnidadesMaximas());
			
			//--Cambio validacion unidades con CUVs acumulados--
			int acumulado = StringUtils.isNotBlank(f.getLabel4())?Integer.parseInt(f.getLabel4()): 0;
			for (int j = 0; j < this.listaTablaDetalle.size()-1; j++) 
			{
				MantenimientoOCRCapturaPedidosForm f1 = (MantenimientoOCRCapturaPedidosForm)this.listaTablaDetalle.get(j);
				if(f.getLabel().equals(f1.getLabel())){
					acumulado = acumulado + Integer.parseInt(f.getLabel4());
				}				
			}
			//--------------------------------------------------
			
			 if(Integer.parseInt(f.getLabel4()) > unidMaximas){
				 String mensaje = this.getResourceMessage("mensaje.confirm.mayor99") +unidMaximas+ this.getResourceMessage("mensaje.alert.modificarRegistro");
				 this.addWarn("Advertencia: ", mensaje);
				 agregar = true;
			 }else{
				 //--Cambio validacion unidades con CUVs acumulados--
				 if(acumulado > unidMaximas){
					 String mensaje = this.getResourceMessage("mensaje.confirm.mayor99") +unidMaximas+ this.getResourceMessage("mensaje.alert.modificarRegistro");
					 this.addWarn("Advertencia: ", mensaje);
					 agregar = true;
				 } else agregar = false;
			 }
			 
			 //Ultima Fila
			 MantenimientoOCRCapturaPedidosForm ultimo = (MantenimientoOCRCapturaPedidosForm)this.listaTablaDetalle.get(this.listaTablaDetalle.size()-1);
			 
			 if(!agregar && StringUtils.isNotBlank(ultimo.getLabel4())){
				 // Aqui se agrega una nueva fila a la tabla Detalle
				 MantenimientoOCRCapturaPedidosForm nuevo = new MantenimientoOCRCapturaPedidosForm();
				 valoresIniciales(f, nuevo);
				 MantenimientoOCRCapturaPedidosForm formPrincipal = (MantenimientoOCRCapturaPedidosForm)this.formBusqueda;
				 formPrincipal.setTxtnumPedidos(Integer.toString(this.listaTablaDetalle.size()));
				 this.listaTablaDetalle.add(nuevo);
				 this.tablaDetalleModel = new DataTableModel(this.listaTablaDetalle);
				 //direcciona el cursor
				 this.getRequestContext().execute("PrimeFaces.focus('tablaDetalleN:"+ String.valueOf(this.listaTablaDetalle.size()-1) +":label')");
				 
				 calcularMonto(i);
			 }else{
				 ultimo.setLabel4("");
				 this.getRequestContext().execute("PrimeFaces.focus('tablaDetalleN:"+ String.valueOf(this.listaTablaDetalle.size()-1) +":label')");
			 }
		}		
		
		calcularTotales();
	}
	
	/**
	 * Metodo que cargara el monto minimo en la tabla Detalle 
	 * de acuerdo a la consultora ingresada
	 */
	private void cargarMontoMinimo()
	{
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		MantenimientoOCRCapturaPedidosForm f = (MantenimientoOCRCapturaPedidosForm)this.formBusqueda;
		
		if(StringUtils.equals(f.getIndicadorCerrarPopup(), Constants.SI))
	  		return;
		
		if(StringUtils.isNotBlank(f.getCodigoConsultora()))
			f.setMontoMinimo(ajax.getMontoMinimoConsultora(f.getCodigoConsultora()));		
	}	
	
	
	/**
	 * Metodo que calcula el monto por producto ingresado en la tabla Detalle
	 * @param index
	 */
	private void calcularMonto(int index)
	{
		MantenimientoOCRCapturaPedidosForm f = (MantenimientoOCRCapturaPedidosForm)this.listaTablaDetalle.get(index);
		BigDecimal total = new BigDecimal(f.getLabel3()).multiply(new BigDecimal(f.getLabel4())).setScale(2, RoundingMode.HALF_UP); 
		f.setLabel5(total.toString());
	}
		
	private void valoresIniciales(MantenimientoOCRCapturaPedidosForm f, MantenimientoOCRCapturaPedidosForm nuevo)
	{
		reset(nuevo);
		nuevo.setFechaFacturacion(f.getFechaFacturacion());
		nuevo.setFechaFacturacionDate(f.getFechaFacturacionDate());
		nuevo.setPeriodo(f.getPeriodo());
		nuevo.setNumLote(f.getNumLote());
		nuevo.setUnidadesMaximas(f.getUnidadesMaximas());
		nuevo.setLongitudUnidadesMaximas(f.getLongitudUnidadesMaximas());
		nuevo.setMostrarConsultora(f.isMostrarConsultora());
		nuevo.setIndicadorHiperConsulta(f.getIndicadorHiperConsulta());
		nuevo.setIndicadorCerrarPopup(f.getIndicadorCerrarPopup());
		nuevo.setIndicadorCompletarCerosNumDocumento(f.isIndicadorCompletarCerosNumDocumento());
		nuevo.setLongitudNumeroDocIdentidad(f.getLongitudNumeroDocIdentidad());
		nuevo.setIndicadorMostrarDetalle(f.getIndicadorMostrarDetalle());
		nuevo.setIndicadorDatos(f.getIndicadorDatos());	        
		nuevo.setIndicadorActiva(f.getIndicadorActiva());
		nuevo.setEstadoConsultora(f.getEstadoConsultora());
		nuevo.setIndicadorBloqueo(f.getIndicadorBloqueo());	
		
		nuevo.setCodigoConsultora(f.getCodigoConsultora());
		nuevo.setDeshabilitaCamposDetalle(true);
	}
	
	public void calcularTotales()
	{
		MantenimientoOCRCapturaPedidosForm formPrincipal = (MantenimientoOCRCapturaPedidosForm)this.formBusqueda;
		int totUni = 0;
		BigDecimal totTot = new BigDecimal("0");
		
		for (int i = 0; i < this.listaTablaDetalle.size(); i++) {
			MantenimientoOCRCapturaPedidosForm f = (MantenimientoOCRCapturaPedidosForm)this.listaTablaDetalle.get(i);
			if(StringUtils.isNotBlank(f.getLabel4())){
				//Actualizan el total por Producto
				BigDecimal total = new BigDecimal(f.getLabel3()).multiply(new BigDecimal(f.getLabel4())).setScale(2, RoundingMode.HALF_UP); 
				f.setLabel5(total.toString());
				
				//Actualizan Total y TotalPedido
				totUni = totUni + Integer.parseInt(f.getLabel4());
				totTot = totTot.add(new BigDecimal(f.getLabel3()).multiply(new BigDecimal(f.getLabel4())).setScale(2, RoundingMode.HALF_UP));
			}
			else
				f.setLabel5("0");
		}
		
		formPrincipal.setTotalUnid(Integer.toString(totUni));		
		totTot = totTot.add(new BigDecimal(StringUtils.isNotBlank(this.totalPedidoGuardado)?this.totalPedidoGuardado:"0"));
		formPrincipal.setTotalPedido(totTot.toString());
	}
		
	private boolean validaCodigoVentaMemoria(MantenimientoOCRCapturaPedidosForm f){
		int index = -1;
		
		for (int i = 0; i < this.listaCodigosMatriz.size(); i++) {
			LabelPedidosValue aux = (LabelPedidosValue)this.listaCodigosMatriz.get(i);
			if(f.getLabel().equals(aux.getCodigoVta())){
				index = i;
				break;
			}			
		}
		
		if(index == -1){		
			this.deshabilitaCampos = false;
			f.setLabel("");
			
			this.addError("Error: ", this.getResourceMessage("mensaje.codVtaNoExiste"));			
			return false;
		}else{
			if(f.getIndicadorMostrarDetalle().equals("S")){
				f.setLabel2(((LabelPedidosValue)this.listaCodigosMatriz.get(index)).getDescripcion());
				f.setLabel3(((LabelPedidosValue)this.listaCodigosMatriz.get(index)).getPrecioCat());
			}		
			
			f.setDeshabilitaCamposDetalle(false);
			this.deshabilitaCampos = true;
			
			return true;
		}	
	}
	
	private boolean validarCUVExistente(String cuv, int posicion)
	{
		boolean ret = false;
		for (int i = 0; i < this.listaTablaDetalle.size(); i++) {
			if(posicion != i){
				MantenimientoOCRCapturaPedidosForm f = (MantenimientoOCRCapturaPedidosForm)this.listaTablaDetalle.get(i);
				if(f.getLabel().equals(cuv)){
					ret = true;
					break;
				}
			}
		}
		return ret;
	}
	
	private void limpiarCampos()
	{
		MantenimientoOCRCapturaPedidosForm f = (MantenimientoOCRCapturaPedidosForm)this.formBusqueda;
		f.setNombreConsultora("");
		f.setRegion("");
		f.setZona("");
		f.setIndicadorActiva("");
		f.setEstadoConsultora("");
		f.setIndicadorBloqueo("");
	}
	
	/**
	 * Elimina una fila de la tabla dinamica
	 */
	public void deleteX(ActionEvent event)
	{
		try {
			
			if(this.listaTablaDetalle.size() > 1)
			{
				MantenimientoOCRCapturaPedidosForm formPrincipal = (MantenimientoOCRCapturaPedidosForm) this.formBusqueda;
				if (this.listaTablaDetalle.size() == this.listaTablaDetaSeleccionado.size()) {
					
					//almacenados los seleccionados para eliminarlos posteriormente
					this.listaEliminados.addAll(this.listaTablaDetaSeleccionado);
					
					reiniciarDatatable(formPrincipal);
					calcularTotales();
					formPrincipal.setTxtnumPedidos("0");
				} else 
				{					
					//almacenados los seleccionados para eliminarlos posteriormente
					this.listaEliminados.addAll(this.listaTablaDetaSeleccionado);
					
					this.listaTablaDetalle.removeAll(this.listaTablaDetaSeleccionado);
					this.tablaDetalleModel = new DataTableModel(this.listaTablaDetalle);
					calcularTotales();
					int tamanio =  0;
					for (Object lista : this.listaTablaDetalle) {
						MantenimientoOCRCapturaPedidosForm aux =(MantenimientoOCRCapturaPedidosForm)lista;
						if(StringUtils.isNotBlank(aux.getLabel4()))
							tamanio++;
					}
					
					formPrincipal.setTxtnumPedidos(Integer.toString((tamanio)));
				}				
			}			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}		
	}
	
	
	/**
	 * Metodo que reinicia la tabla a una fila 
	 * @param f
	 */
	private void reiniciarDatatable(MantenimientoOCRCapturaPedidosForm f)
	{
		MantenimientoOCRCapturaPedidosForm nuevo = new MantenimientoOCRCapturaPedidosForm();
		valoresIniciales(f, nuevo);
		
		List aux = new ArrayList();
		aux.add(nuevo);
		this.listaTablaDetalle = aux;
		this.tablaDetalleModel = new DataTableModel(this.listaTablaDetalle);		
	}
	
	public void metodoVacio(ActionEvent event)
	{
		
	}	
	
	
	/* GET - SET */
	
	public String getLongitudCampoClientes() {
		return longitudCampoClientes;
	}

	public void setLongitudCampoClientes(String longitudCampoClientes) {
		this.longitudCampoClientes = longitudCampoClientes;
	}

	public List getListaCampanhas() {
		return listaCampanhas;
	}

	public void setListaCampanhas(List listaCampanhas) {
		this.listaCampanhas = listaCampanhas;
	}

	public List getListaCodigosMatriz() {
		return listaCodigosMatriz;
	}

	public void setListaCodigosMatriz(List listaCodigosMatriz) {
		this.listaCodigosMatriz = listaCodigosMatriz;
	}

	public String getFecha_facturacion_cte() {
		return fecha_facturacion_cte;
	}

	public void setFecha_facturacion_cte(String fecha_facturacion_cte) {
		this.fecha_facturacion_cte = fecha_facturacion_cte;
	}

	public List getListaTablaDetalle() {
		return listaTablaDetalle;
	}

	public void setListaTablaDetalle(List listaTablaDetalle) {
		this.listaTablaDetalle = listaTablaDetalle;
	}

	public DataTableModel getTablaDetalleModel() {
		return tablaDetalleModel;
	}

	public void setTablaDetalleModel(DataTableModel tablaDetalleModel) {
		this.tablaDetalleModel = tablaDetalleModel;
	}

	public List getListaDetallePedido() {
		return listaDetallePedido;
	}

	public void setListaDetallePedido(List listaDetallePedido) {
		this.listaDetallePedido = listaDetallePedido;
	}

	public List getListaTablaDetaSeleccionado() {
		return listaTablaDetaSeleccionado;
	}

	public void setListaTablaDetaSeleccionado(List listaTablaDetaSeleccionado) {
		this.listaTablaDetaSeleccionado = listaTablaDetaSeleccionado;
	}

	public List getListaDetallePedSeleccionado() {
		return listaDetallePedSeleccionado;
	}

	public void setListaDetallePedSeleccionado(List listaDetallePedSeleccionado) {
		this.listaDetallePedSeleccionado = listaDetallePedSeleccionado;
	}

	public boolean isMostrarPopupOCRCliente() {
		return mostrarPopupOCRCliente;
	}

	public void setMostrarPopupOCRCliente(boolean mostrarPopupOCRCliente) {
		this.mostrarPopupOCRCliente = mostrarPopupOCRCliente;
	}

	public BusquedaConsultoraPOPUPSearchAction getBusquedaConsultoraPOPUPSearchAction() {
		return busquedaConsultoraPOPUPSearchAction;
	}

	public void setBusquedaConsultoraPOPUPSearchAction(
			BusquedaConsultoraPOPUPSearchAction busquedaConsultoraPOPUPSearchAction) {
		this.busquedaConsultoraPOPUPSearchAction = busquedaConsultoraPOPUPSearchAction;
	}

	public static String getPopupOcrcliente() {
		return POPUP_OCRCLIENTE;
	}

	public Boolean getDeshabilitaCampos() {
		return deshabilitaCampos;
	}

	public void setDeshabilitaCampos(Boolean deshabilitaCampos) {
		this.deshabilitaCampos = deshabilitaCampos;
	}

	public Boolean getIdPROL() {
		return idPROL;
	}

	public void setIdPROL(Boolean idPROL) {
		this.idPROL = idPROL;
	}

	public Boolean getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Boolean idPedido) {
		this.idPedido = idPedido;
	}

	public Boolean getIdPedido2() {
		return idPedido2;
	}

	public void setIdPedido2(Boolean idPedido2) {
		this.idPedido2 = idPedido2;
	}

	public String getGrabar() {
		return grabar;
	}

	public void setGrabar(String grabar) {
		this.grabar = grabar;
	}

	public DataTableModel getTablaDetallePedidoModel() {
		return tablaDetallePedidoModel;
	}

	public void setTablaDetallePedidoModel(DataTableModel tablaDetallePedidoModel) {
		this.tablaDetallePedidoModel = tablaDetallePedidoModel;
	}

	public String getTotalPedidoGuardado() {
		return totalPedidoGuardado;
	}

	public void setTotalPedidoGuardado(String totalPedidoGuardado) {
		this.totalPedidoGuardado = totalPedidoGuardado;
	}

	public String getProl() {
		return prol;
	}

	public void setProl(String prol) {
		this.prol = prol;
	}

	public Boolean getPanelpedidosExistentes() {
		return panelpedidosExistentes;
	}

	public void setPanelpedidosExistentes(Boolean panelpedidosExistentes) {
		this.panelpedidosExistentes = panelpedidosExistentes;
	}

	public Boolean getPadreHiperconsulta() {
		return padreHiperconsulta;
	}

	public void setPadreHiperconsulta(Boolean padreHiperconsulta) {
		this.padreHiperconsulta = padreHiperconsulta;
	}

	public List getListaEliminados() {
		return listaEliminados;
	}

	public void setListaEliminados(List listaEliminados) {
		this.listaEliminados = listaEliminados;
	}

	/**
	 * @return the codigoConsultoraHiperConsulta
	 */
	public String getCodigoConsultoraHiperConsulta() {
		return codigoConsultoraHiperConsulta;
	}

	/**
	 * @param codigoConsultoraHiperConsulta the codigoConsultoraHiperConsulta to set
	 */
	public void setCodigoConsultoraHiperConsulta(
			String codigoConsultoraHiperConsulta) {
		this.codigoConsultoraHiperConsulta = codigoConsultoraHiperConsulta;
	}
	
	
}