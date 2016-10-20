package biz.belcorp.ssicc.web.spusicc.pedidos.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.ProcesoOCRActualizarUnidadesMaximas;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.ClienteUAGenerarService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRCargaPedidoService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.sto.MantenimientoSTOBloqueoControlService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEjecucionValidacionesService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.ProcesoOCRActualizarUnidadesMaximasForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"rawtypes","unchecked"})
public class ProcesoOCRActualizarUnidadesMaximasSearchAction extends BaseMantenimientoSearchAbstractAction
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1078554268576954910L;
	
	private List siccRegionList;
	private LabelValue[] siccZonaList = {};
	private String urlOCR;
	private String paisOCR;
	private String marcaOCR;
	private String flagUpdate;
	private String longitudCampoClientes;

	private List consultorasList;

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
		ProcesoOCRActualizarUnidadesMaximasForm formSearch = new ProcesoOCRActualizarUnidadesMaximasForm();
		return formSearch;
	}

	@Override
	protected List setFindAttributes() throws Exception 
	{
        if (log.isDebugEnabled()) {
            log.debug("Entering 'search' method");
        }
        ProcesoOCRActualizarUnidadesMaximasForm procesoOCRActualizarUnidadesMaximasForm = (ProcesoOCRActualizarUnidadesMaximasForm)this.formBusqueda;              
        MantenimientoOCRCargaPedidoService service = (MantenimientoOCRCargaPedidoService)getBean("spusicc.pedidos.mantenimientoOCRCargaPedidoService");        
        Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
        
        List lista = service.getSearchDetallesByCriteria(getCriteria());
        int tamanno = lista.size();
        String[] listaId =  new String[tamanno];
        String[] listaUnidadDemanda =  new String[tamanno];
        String[] listaUnidadDemandaIni =  new String[tamanno];
        String[] listaIndicadoresProl =  new String[tamanno];
        Map mapRow = null;
        
        procesoOCRActualizarUnidadesMaximasForm.setIndicadorPROL("NO");
        
        /* INI SA PER-SiCC-2013-0399 */
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoSistema", "PED");
        criteria.put("nombreParametro", "indRestActualizacionPROL");
		
		MantenimientoSTOBloqueoControlService mantenimientoSTOBloqueoControlService = (MantenimientoSTOBloqueoControlService)getBean("spusicc.mantenimientoSTOBloqueoControlService");		
		String indActualizacionPROL = mantenimientoSTOBloqueoControlService.getParametroGenericoSistema(criteria); 		
		/* INI SA PER-SiCC-2013-0399 */
                
        for(int i=0; i< lista.size(); i++) {
        	mapRow = (Map) lista.get(i);
        	listaId[i] = (String)mapRow.get("id");
        	listaUnidadDemanda[i] = (String)mapRow.get("valUnidadDemanda").toString().trim();  
        	listaUnidadDemandaIni[i] = (String)mapRow.get("valUnidadDemanda").toString().trim();
        	
        	String indProl = (String)mapRow.get("indicadorPROL").toString().trim();
        	if (indProl!=null && indProl.equals("SI")){
        		/* INI SA PER-SiCC-2013-0399 */
        		listaIndicadoresProl[i] = "NO";
        		
        		if("S".equals(indActualizacionPROL)) {
        			procesoOCRActualizarUnidadesMaximasForm.setIndicadorPROL("SI");
        			listaIndicadoresProl[i] = "SI";
        		}	
        		/* FIN SA PER-SiCC-2013-0399 */
        	}else{
        		listaIndicadoresProl[i] = "NO";
        	}        	
        }
        procesoOCRActualizarUnidadesMaximasForm.setListaUnidadDemanda(listaUnidadDemanda);
        procesoOCRActualizarUnidadesMaximasForm.setListaUnidadDemandaIni(listaUnidadDemandaIni);
        procesoOCRActualizarUnidadesMaximasForm.setListaIndicadoresProl(listaIndicadoresProl);
        procesoOCRActualizarUnidadesMaximasForm.setListaId(listaId);
        
        this.consultorasList =  lista;
        this.flagUpdate = null;
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
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setViewAtributes() throws Exception 
	{
        if (log.isDebugEnabled()) {
            log.debug("Entering 'setViewAtributes' method de cargaPedidos");
        }
        
        //----------------Cargar combos ---------------------------
        ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
        Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
       
        Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais",pais.getCodigo());
									     
        this.siccRegionList = reporteService.getListaGenerico("getRegionesByPais",criteriaOperacion);
		
		//request.getSession().setAttribute(Constants.INDICADOR_LIST,reporteService.getIndicadores());
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
        criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente  
        
        MantenimientoOCRPedidoControlFacturacionService serviceFact = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");        
        PedidoControlFacturacion controlFacturacion = serviceFact.getControlFacturacionById(criteria);
        
        //---------------------Carga parametros configuración popup Visor Documentos---------------------------------------
        ProcesoSTOEjecucionValidacionesService procesoSTOEjecucionValidacionesService = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
		String urlOCR = procesoSTOEjecucionValidacionesService.getURLOCRParametro(criteria);
		String PaisOCRyMarca=procesoSTOEjecucionValidacionesService.getMarcaPais(criteria);
        
		this.urlOCR = urlOCR;
		this.paisOCR = PaisOCRyMarca.split("-")[0]; //Captura del Pais OCR
		this.marcaOCR = PaisOCRyMarca.split("-")[1]; //Captura de Marca OCR
		
        // Obtiene el periodo de la tabla de control
        ProcesoOCRActualizarUnidadesMaximasForm procesoOCRActualizarUnidadesMaximasForm = (ProcesoOCRActualizarUnidadesMaximasForm)this.formBusqueda;
        //if(procesoOCRActualizarUnidadesMaximasForm.getPeriodoSearch()==null)
        
        if(this.flagUpdate == null || !this.flagUpdate.equals("OK"))        	
        		procesoOCRActualizarUnidadesMaximasForm.setPeriodoSearch(controlFacturacion.getCodigoPeriodo());        
        
        // Numero de unidades segun el pais y el periodo
        Map filter = new HashMap();
        filter.put("pais", pais.getCodigo());
        filter.put("periodo", procesoOCRActualizarUnidadesMaximasForm.getPeriodoSearch());
        if(this.flagUpdate == null || !this.flagUpdate.equals("OK"))
        //if(procesoOCRActualizarUnidadesMaximasForm.getUnidades()==null)
        	procesoOCRActualizarUnidadesMaximasForm.setUnidades(serviceFact.getNumeroUnidades(filter));
		
        procesoOCRActualizarUnidadesMaximasForm.setRepeticion("");
        procesoOCRActualizarUnidadesMaximasForm.setCodigoConsultora("");
        procesoOCRActualizarUnidadesMaximasForm.setCodVenta("");
        procesoOCRActualizarUnidadesMaximasForm.setCodVenta2("");
        procesoOCRActualizarUnidadesMaximasForm.setCodVenta3("");
        procesoOCRActualizarUnidadesMaximasForm.setCodVenta4("");
        procesoOCRActualizarUnidadesMaximasForm.setCodVenta5("");
        procesoOCRActualizarUnidadesMaximasForm.setCodVenta6("");
        procesoOCRActualizarUnidadesMaximasForm.setCodVenta7("");
        procesoOCRActualizarUnidadesMaximasForm.setCodVenta8("");
        procesoOCRActualizarUnidadesMaximasForm.setCodVenta9("");
        procesoOCRActualizarUnidadesMaximasForm.setCodVenta10("");    
        
        procesoOCRActualizarUnidadesMaximasForm.setCodRegion(null);
        procesoOCRActualizarUnidadesMaximasForm.setCodZonas(null);
        
        procesoOCRActualizarUnidadesMaximasForm.setIndicadorPROL("NO");
        
        procesoOCRActualizarUnidadesMaximasForm.setTelefono(null);
        procesoOCRActualizarUnidadesMaximasForm.setEstatus(null);
        procesoOCRActualizarUnidadesMaximasForm.setChkBloqueado(null);
        procesoOCRActualizarUnidadesMaximasForm.setPrimerPedido(null);
        procesoOCRActualizarUnidadesMaximasForm.setUltimoPedido(null);
        procesoOCRActualizarUnidadesMaximasForm.setRegion(null);
        procesoOCRActualizarUnidadesMaximasForm.setZona(null);
        
        //Longitud del campo codigo consultora de la tabla
        ClienteUAGenerarService clienteService = (ClienteUAGenerarService) getBean("sisicc.clienteUAGenerarService");
  
        this.longitudCampoClientes = clienteService.getTamanhoNumeroCliente(pais.getCodigo()).toString();
        log.debug("------- "+clienteService.getTamanhoNumeroCliente(pais.getCodigo()));
//        request.getSession().removeAttribute(Constants.CONSULTORAS_LIST);
       
        this.flagUpdate = null;	
        this.mostrarBotonConsultar = false;
        this.mostrarBotonEliminar = false;
        this.mostrarBotonModificar = false;
        this.mostrarBotonNuevo = false;
        this.mostrarListaBusqueda = false;        
	}

	public void guardar(ActionEvent event)
	{
        if (log.isDebugEnabled()) {
            log.debug("Entering 'save' method");
        }
        try{
        Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
        MantenimientoOCRPedidoControlFacturacionService serviceFact = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
        MantenimientoOCRCargaPedidoService service = (MantenimientoOCRCargaPedidoService)getBean("spusicc.pedidos.mantenimientoOCRCargaPedidoService");
        
        String[] listaUnidadDemanda = new String[this.consultorasList.size()];
        String[] listaUnidadDemandaIni = new String[this.consultorasList.size()];
        String[] listaIndicadoresProl = new String[this.consultorasList.size()];
        String[] listaId = new String[this.consultorasList.size()];
        int j = 0;
        
        for (Object objeto : this.consultorasList) {
			Map params = (Map)objeto;
			listaUnidadDemanda[j] = params.get("valUnidadDemanda").toString();
			listaUnidadDemandaIni[j] = params.get("valUnidadDemandaInicial").toString();
			listaIndicadoresProl[j] = params.get("indicadorPROL").toString().trim();
			listaId[j] = params.get("id").toString();
			j++;
		}
        
        int tamanno = listaId.length;
        String[] listaCodigoPais = new String[tamanno];
        String[] listaCodigoPeriodo = new String[tamanno];
        String[] listaCodigoCliente = new String[tamanno];
        String[] listaNumLote = new String[tamanno];
        String[] listaCodigoVta = new String[tamanno];
        String[] listaIndicadorPROL = new String[tamanno];
        
        for (int i=0; i<tamanno; i++) {
	    	Map params = this.getFilter(listaId[i]);
	    	listaCodigoPais[i] = (String)params.get("codigoPais");
	    	listaCodigoPeriodo[i] = (String)params.get("codigoPeriodo");
	    	listaCodigoCliente[i] = (String)params.get("codigoCliente");
	    	listaNumLote[i] = (String)params.get("numLote");
	    	listaCodigoVta[i] = (String)params.get("codigoVta");	    	
	    	listaIndicadorPROL[i] = listaIndicadoresProl[i];
	    }
        
        ProcesoOCRActualizarUnidadesMaximas procesoOCRActualizarUnidadesMaximas = new ProcesoOCRActualizarUnidadesMaximas();
        procesoOCRActualizarUnidadesMaximas.setListaCodigoPais(listaCodigoPais);
        procesoOCRActualizarUnidadesMaximas.setListaCodigoCliente(listaCodigoCliente);
        procesoOCRActualizarUnidadesMaximas.setListaCodigoPeriodo(listaCodigoPeriodo);
        procesoOCRActualizarUnidadesMaximas.setListaCodigoVta(listaCodigoVta);
        procesoOCRActualizarUnidadesMaximas.setListaId(listaId);
        procesoOCRActualizarUnidadesMaximas.setListaNumLote(listaNumLote);
        procesoOCRActualizarUnidadesMaximas.setListaUnidadDemanda(listaUnidadDemanda);
        procesoOCRActualizarUnidadesMaximas.setListaUnidadDemandaIni(listaUnidadDemandaIni);
        procesoOCRActualizarUnidadesMaximas.setListaIndicadorPROL(listaIndicadorPROL);
        procesoOCRActualizarUnidadesMaximas.setUsuario(usuario.getLogin());
                
        boolean modificar = serviceFact.updateUnidadesTotal(procesoOCRActualizarUnidadesMaximas);
        if (modificar) {         	
        	this.addInfo("", this.getResourceMessage("unidades.updates"));
        	this.find();
        }        
        else {
        	this.addError("Error: ", this.getResourceMessage("unidades.sin_actualizar.updates"));
        }
        }catch(Exception e)
        {
        	this.addError("Error: ", this.obtieneMensajeErrorException(e));
        }
	}
	
	/**
     * @return
     * @throws Exception
     */
    private Map getCriteria() throws Exception
    {
    	ProcesoOCRActualizarUnidadesMaximasForm cargaPedidoForm = (ProcesoOCRActualizarUnidadesMaximasForm) this.formBusqueda;    	
        Map criteria = BeanUtils.describe(this.formBusqueda);
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		criteria.put("codigoPais", pais.getCodigo());
        criteria.put("estado", Constants.ESTADO_ACTIVO);
		criteria.put("codigoPaisSearch", pais.getCodigo());

        criteria.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
        criteria.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);

		log.debug("---> region "+cargaPedidoForm.getCodRegion());		
		
		ArrayList arrZonas = new ArrayList();
		if(cargaPedidoForm.getCodZonas()!=null){
			for (int i = 0; i < cargaPedidoForm.getCodZonas().length; i++) {
				if(StringUtils.isNotBlank(cargaPedidoForm.getCodZonas()[i])){
					log.debug("---> zona "+cargaPedidoForm.getCodZonas()[i]);		
					arrZonas.add(cargaPedidoForm.getCodZonas()[i]);
				}
			}
		}	
		
		log.debug("size = "+arrZonas.size());
				
		if(StringUtils.isNotBlank(cargaPedidoForm.getPeriodoSearch())) {
            criteria.put("periodoSearch", cargaPedidoForm.getPeriodoSearch());
        }
		
        if(StringUtils.isNotBlank(cargaPedidoForm.getFechaFacturacionSearch())) {
            criteria.put("fechaFacturacionSearch", cargaPedidoForm.getFechaFacturacionSearch());
        }
        
        if(StringUtils.isNotBlank(cargaPedidoForm.getCodigoConsultora())) {
            criteria.put("codConsultora", cargaPedidoForm.getCodigoConsultora());
        }
        
        if(StringUtils.isNotBlank(cargaPedidoForm.getUnidades())) {            
        	criteria.put("unidades", cargaPedidoForm.getUnidades());        	        	        	
        }
        
        if(StringUtils.isNotBlank(cargaPedidoForm.getRepeticion())) {            
        	criteria.put("repeticion", cargaPedidoForm.getRepeticion());        	        	        	
        } 
                        
        if(cargaPedidoForm.getCodRegion()!=null){
	        if(!cargaPedidoForm.getCodRegion().equals(Constants.OPCION_TODOS)){	        	
	        	if(!cargaPedidoForm.getCodRegion().equals("")){
	        		criteria.put("region", cargaPedidoForm.getCodRegion());        		
	        	}
	        }
        }
               
        if(arrZonas.size()!=0){                	        	
        	criteria.put("zona", arrZonas);	        		        
        }
        
        /*if(StringUtils.isNotBlank(cargaPedidoForm.getCodVenta())) {            
        	criteria.put("codigoVenta", cargaPedidoForm.getCodVenta());        	        	        	
        }*/
        
        ArrayList arrCodVenta = new ArrayList();
        if(StringUtils.isNotBlank(cargaPedidoForm.getCodVenta())) arrCodVenta.add(cargaPedidoForm.getCodVenta());
        if(StringUtils.isNotBlank(cargaPedidoForm.getCodVenta2())) arrCodVenta.add(cargaPedidoForm.getCodVenta2());
        if(StringUtils.isNotBlank(cargaPedidoForm.getCodVenta3())) arrCodVenta.add(cargaPedidoForm.getCodVenta3());
        if(StringUtils.isNotBlank(cargaPedidoForm.getCodVenta4())) arrCodVenta.add(cargaPedidoForm.getCodVenta4());
        if(StringUtils.isNotBlank(cargaPedidoForm.getCodVenta5())) arrCodVenta.add(cargaPedidoForm.getCodVenta5());
        if(StringUtils.isNotBlank(cargaPedidoForm.getCodVenta6())) arrCodVenta.add(cargaPedidoForm.getCodVenta6());
        if(StringUtils.isNotBlank(cargaPedidoForm.getCodVenta7())) arrCodVenta.add(cargaPedidoForm.getCodVenta7());
        if(StringUtils.isNotBlank(cargaPedidoForm.getCodVenta8())) arrCodVenta.add(cargaPedidoForm.getCodVenta8());
        if(StringUtils.isNotBlank(cargaPedidoForm.getCodVenta9())) arrCodVenta.add(cargaPedidoForm.getCodVenta9());
        if(StringUtils.isNotBlank(cargaPedidoForm.getCodVenta10())) arrCodVenta.add(cargaPedidoForm.getCodVenta10());
        
        if(arrCodVenta.size()!=0)
        	criteria.put("arrCodigoVenta", arrCodVenta);
        
        return criteria;
	}
	
    /**
   	 * @param id
   	 * @return
   	 */
   	private Map getFilter(String id) {
   		Map criteria = new HashMap();
           criteria.put("codigoPais",StringUtils.split(id, "|")[0]);
           criteria.put("codigoPeriodo",StringUtils.split(id, "|")[1]);
           criteria.put("codigoCliente",StringUtils.split(id, "|")[2]);
           criteria.put("numLote",StringUtils.split(id, "|")[3]);
           criteria.put("codigoVta",StringUtils.split(id, "|")[4]);
   		return criteria;
   	}
	
   	public void loadZonas(ValueChangeEvent event)
   	{
   		AjaxService ajax = (AjaxService) getBean("ajaxService");
   		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
   		String valor = (String) event.getNewValue();
   		
   		if(!valor.equals("Todos"))
   			this.siccZonaList = ajax.getZonasByPaisRegion(pais.getCodigo(), valor);
   		else
   			this.siccZonaList = null;
   	}

   	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setAlertasAntesdeConfirmar(java.lang.String)
	 */
	@Override
	public String setAlertasAntesdeConfirmar(String accion) {
		
		String mensaje = null;
		ProcesoOCRActualizarUnidadesMaximasForm f = (ProcesoOCRActualizarUnidadesMaximasForm)this.formBusqueda;
		
		if(f.getIndicadorPROL().equals("SI")){            
	        mensaje = this.getResourceMessage("procesoOCREliminarSolicitudesCreditoForm.msg.existProl");	
		}	
		
		return mensaje;
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

	public String getFlagUpdate() {
		return flagUpdate;
	}

	public void setFlagUpdate(String flagUpdate) {
		this.flagUpdate = flagUpdate;
	}

	public String getLongitudCampoClientes() {
		return longitudCampoClientes;
	}

	public void setLongitudCampoClientes(String longitudCampoClientes) {
		this.longitudCampoClientes = longitudCampoClientes;
	}

	public List getConsultorasList() {
		return consultorasList;
	}

	public void setConsultorasList(List consultorasList) {
		this.consultorasList = consultorasList;
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

}
