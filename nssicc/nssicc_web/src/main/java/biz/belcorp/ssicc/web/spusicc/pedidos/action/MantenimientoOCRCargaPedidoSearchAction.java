package biz.belcorp.ssicc.web.spusicc.pedidos.action;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelDatosConsultoraValue;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.sisicc.model.Cliente;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.ClienteUAGenerarService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRCargaPedidoService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.hip.action.BusquedaConsultoraPOPUPSearchAction;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.MantenimientoOCRCargaPedidosDigitadosForm;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.MantenimientoOCRCargaPedidosForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"unchecked", "rawtypes"})
public class MantenimientoOCRCargaPedidoSearchAction extends BaseMantenimientoSearchAbstractAction
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -949111537742371567L;
	
	private String longitudCampoClientes;
	private List siccRegionList;
	private LabelValue[] siccZonaList;
	private List consultorasList;
	
	// segunda pantalla
	private List listaCampanhas;
	private String codigoConsultora;
	
	// popup
	private List consultorasDetalleList;
	
	private static final String POPUP_CONSULTORA = "CONSULTORA";
	private boolean mostrarPopupConsultora;
	
	@ManagedProperty(value="#{busquedaConsultoraPOPUPSearchAction}")
	private BusquedaConsultoraPOPUPSearchAction busquedaConsultoraPOPUPSearchAction;

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
		MantenimientoOCRCargaPedidosDigitadosForm searchForm = new MantenimientoOCRCargaPedidosDigitadosForm();
		return searchForm;
	}
	
	@Override
	protected List setFindAttributes() throws Exception 
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'search' method");
		}

		MantenimientoOCRCargaPedidoService service = (MantenimientoOCRCargaPedidoService) getBean("spusicc.pedidos.mantenimientoOCRCargaPedidoService");
		List lista = service.getSearchPedidosByCriteria(getCriteria());
		this.consultorasList = lista;

		return lista;
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
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception 
	{
		return null;
	}

	
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'view' method de cargaPedidos");
		}
		
		this.mostrarBotonNuevo = false;
		this.mostrarBotonModificar = false;
		this.mostrarBotonConsultar = false;
		this.mostrarBotonEliminar = false;
		
		MantenimientoOCRCargaPedidosDigitadosForm f = (MantenimientoOCRCargaPedidosDigitadosForm) this.formBusqueda;
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
		// ----------------Cargar combos ---------------------------
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Map criteriaOperacion = new HashMap();

		criteriaOperacion.put("codigoPais", pais.getCodigo());

		this.siccRegionList = reporteService.getListaGenerico("getRegionesByPais", criteriaOperacion);
		// request.getSession().setAttribute(Constants.SICC_ZONA_LIST,new ArrayList());

		// request.getSession().removeAttribute(Constants.CONSULTORAS_LIST);

		ClienteUAGenerarService clienteService = (ClienteUAGenerarService) getBean("sisicc.clienteUAGenerarService");

		this.longitudCampoClientes = clienteService.getTamanhoNumeroCliente(pais.getCodigo()).toString();
	}
	
	public void loadZonas(ValueChangeEvent event)
	{
		String valor = (String) event.getNewValue();
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		this.siccZonaList = ajax.getZonasByPaisRegion(pais.getCodigo(), valor);
	}

	@Override
	protected void setInvocarPopup(String accion) 
	{
		this.mostrarProcesoBatch = false;
		if (accion.equals(this.POPUP_CONSULTORA)){ 
			this.mostrarPopupConsultora = true;
		}
	}
	
	@Override
	protected void setSalirPopup() 
	{
		this.mostrarPopupConsultora = false;
		this.busquedaConsultoraPOPUPSearchAction.setBeanRegistroSeleccionado(null);
	}
	
	@Override
	protected void setAceptarPopup(ActionEvent event, String accion) 
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setAceptarPopupHipCliente' method");
		}
		if (accion.equals(this.POPUP_CONSULTORA)) {
			this.busquedaConsultoraPOPUPSearchAction.verificarRegistro(event);
			if (this.busquedaConsultoraPOPUPSearchAction.isSeleccionoRegistro()) {
				Cliente cliente = (Cliente) this.busquedaConsultoraPOPUPSearchAction.getBeanRegistroSeleccionado();
				MantenimientoOCRCargaPedidosDigitadosForm f = (MantenimientoOCRCargaPedidosDigitadosForm) this.formBusqueda;
				f.setCodigoConsultora(cliente.getCodigo());
//				String apellido2 = cliente.getApellidoMaterno();
//				String nombre1 = cliente.getNombre();
//				f.setDescripcionConsultora(nombre1 + " " + apellido2);
				this.busquedaConsultoraPOPUPSearchAction.setBeanRegistroSeleccionado(null);
			}
		}
	}
	
	public void verDetalle(ActionEvent event)
	{
        if (log.isDebugEnabled()) {
            log.debug("Entering 'verDetalle' method de MantenimientoDetallePedidosDigitados");
        }
        
//        String id = request.getParameter("id");
        try{
	        LabelDatosConsultoraValue registroSeleccionado = (LabelDatosConsultoraValue) this.beanRegistroSeleccionado;
	        
	        if(registroSeleccionado != null)
	        {
		        String codigoPais = registroSeleccionado.getCodPais();
		        String codigoPeriodo = registroSeleccionado.getPeriodoFacturacion();
		        String codigoCliente = registroSeleccionado.getCodigoConsultora();
		        String numLote = registroSeleccionado.getNumLote();
				// Si el id ha sido enviado, buscamos la informacion
				if (codigoPais != null && codigoPeriodo != null && codigoCliente != null && numLote != null) 
				{
					if (log.isDebugEnabled()) {
						log.debug("Id seleccionado de la lista: " + codigoPais);
					}				
					Map filter = new HashMap();			
					filter.put("codigoPais", codigoPais);
					filter.put("codigoPeriodo", codigoPeriodo);			
					filter.put("codigoCliente", codigoCliente);
					filter.put("numLote", numLote);
					
					MantenimientoOCRCargaPedidoService service = (MantenimientoOCRCargaPedidoService)getBean("spusicc.pedidos.mantenimientoOCRCargaPedidoService");        	
					//List list = service.getDetallePedidoByFilter(filter);
					List lista = service.getTodosDetallesPedidoByFilter(filter);
			        this.consultorasDetalleList = lista;
			        
			        String ventana = "PF('dialogMantenimientoForm2').show()";
					this.getRequestContext().execute(ventana);
					this.mostrarBotonSalir = false;
					this.mostrarBotonSave = false;
					return;
				}
	        }else
				this.addWarn("Advertencia: ", this.getResourceMessage("errors.select.item"));
        }catch(Exception e)
        {
        	this.addError("", this.obtieneMensajeErrorException(e));
        }	
	}
	
	//metodo que sale del popup
	public void salirUA(ActionEvent event)
	{
		if(log.isDebugEnabled()){
			log.debug("Entering my method 'salirUA'");
		}
		String ventana = "PF('dialogMantenimientoForm2').hide()";
		this.getRequestContext().execute(ventana);
		this.mostrarBotonSalir = true;
	}
	
	private Map getCriteria() throws Exception{
	    	MantenimientoOCRCargaPedidosDigitadosForm cargaPedidoForm = (MantenimientoOCRCargaPedidosDigitadosForm)this.formBusqueda;    	
	        Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
	    	Map criteria = BeanUtils.describe(cargaPedidoForm); //form
	    	
	        criteria.put("estado", Constants.ESTADO_ACTIVO);
			criteria.put("codigoPaisSearch", pais.getCodigo());
			
			log.debug("---> INDICADOR = "+cargaPedidoForm.getIndicador());		
			log.debug("---> region "+cargaPedidoForm.getCodRegion());
			log.debug("---> zona "+cargaPedidoForm.getCodZona());
			log.debug("---> monto "+cargaPedidoForm.getMonto());
					
			if(cargaPedidoForm.getPeriodoSearch()!=null){
				if(StringUtils.isNotBlank(cargaPedidoForm.getPeriodoSearch())) {
		            criteria.put("periodoSearch", cargaPedidoForm.getPeriodoSearch());
		        }
			}
			
	        if(cargaPedidoForm.getFechaFacturacionSearch()!=null){
	        	criteria.put("fechaFacturacionSearch", DateUtil.convertDateToString(Constants.DEFAULT_DATE_FORMAT, cargaPedidoForm.getFechaFacturacionSearch()));
	        }
	        
	        if(cargaPedidoForm.getMonto()!= null) {
		        if(StringUtils.isNotBlank(cargaPedidoForm.getMonto())) {            
		        	criteria.put("check", "1");        	        	        	
		        }        
	        }
	        
	        if(cargaPedidoForm.getMonto()!=null){
	        	criteria.put("maximo", cargaPedidoForm.getMonto());
	        }
	        
	        if(cargaPedidoForm.getCodRegion()!=null){
		        if(!cargaPedidoForm.getCodRegion().equals(Constants.OPCION_TODOS)){	        	
		        	if(!cargaPedidoForm.getCodRegion().equals("")){
		        		criteria.put("region", cargaPedidoForm.getCodRegion());        		
		        	}
		        }
	        }
	        
	        if(cargaPedidoForm.getCodZona() != null){
		        if(!cargaPedidoForm.getCodZona().equals(Constants.OPCION_TODOS)){        	
		        	if(!cargaPedidoForm.getCodZona().equals("")){  
		        		log.debug("_________________________cargo la zona al criteria");
		        		criteria.put("zona", cargaPedidoForm.getCodZona());
		        	}
		        }
	        }
	        
	        if(cargaPedidoForm.getIndicador()!=null){
		        if(!cargaPedidoForm.getIndicador().equals(Constants.OPCION_TODOS)){          	
		        	if(cargaPedidoForm.getIndicador().equals(Constants.INDICADOR_ENVIADO)){     //Enviadas   	
		        		criteria.put("indicadorOCS", Constants.INDICADOR_ENVIADO);
		        	}
		        	else{
		        		criteria.put("indicadorOCS", Constants.INDICADOR_NO_ENVIADO);  //No enviadas
		        	}
		        }                        
	        }        
	        criteria.put("codigoConsultora", cargaPedidoForm.getCodigoConsultora());         
	        
	        return criteria;
		}

	public String getLongitudCampoClientes() {
		return longitudCampoClientes;
	}

	public void setLongitudCampoClientes(String longitudCampoClientes) {
		this.longitudCampoClientes = longitudCampoClientes;
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

	public List getConsultorasList() {
		return consultorasList;
	}

	public void setConsultorasList(List consultorasList) {
		this.consultorasList = consultorasList;
	}

	public boolean isMostrarPopupConsultora() {
		return mostrarPopupConsultora;
	}

	public void setMostrarPopupConsultora(boolean mostrarPopupConsultora) {
		this.mostrarPopupConsultora = mostrarPopupConsultora;
	}

	public BusquedaConsultoraPOPUPSearchAction getBusquedaConsultoraPOPUPSearchAction() {
		return busquedaConsultoraPOPUPSearchAction;
	}

	public void setBusquedaConsultoraPOPUPSearchAction(
			BusquedaConsultoraPOPUPSearchAction busquedaConsultoraPOPUPSearchAction) {
		this.busquedaConsultoraPOPUPSearchAction = busquedaConsultoraPOPUPSearchAction;
	}

	public static String getPopupConsultora() {
		return POPUP_CONSULTORA;
	}

	public List getConsultorasDetalleList() {
		return consultorasDetalleList;
	}

	public void setConsultorasDetalleList(List consultorasDetalleList) {
		this.consultorasDetalleList = consultorasDetalleList;
	}
}
