package biz.belcorp.ssicc.web.scsicc.hip.action;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.DatosConsultora;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.dao.scsicc.model.ConsultaHIPDatosCliente;
import biz.belcorp.ssicc.dao.spusicc.reclamos.model.BoletaRecojoCabecera;
import biz.belcorp.ssicc.dao.spusicc.reclamos.model.BoletaRecojoDetalle;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECDigitacionBoletasRecojoService;
import biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.scsicc.hip.form.ConsultaRECBoletaRecojoSearchForm;



/**
 * The Class ConsultaRECBoletaRecojoSearchAction.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 14/02/2014
 */
@ManagedBean
@SessionScoped
public class ConsultaRECBoletaRecojoPopupAction extends BasePopupAbstractAction{

	private static final long serialVersionUID = 8795725961471495077L;

	@ManagedProperty(value="#{consultaHIPDatosClienteAction}")
	private ConsultaHIPDatosClienteAction consultaHIPDatosClienteAction;
	
	private List consultaRECBoletaRecojoSearchList;
	private List consultaRECBoletaRecojoDetallesSearchList;
	
	private Integer sumaReclamadasT;
	private Integer sumaRecogidasT;
	private Integer sumaEliminadasT;
	
	private boolean mostrarBotonesEdicion;
	
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("ConsultaRECBoletaRecojoSearchAction - devuelveFormBusqueda" );
		}
		
		ConsultaRECBoletaRecojoSearchForm f = new ConsultaRECBoletaRecojoSearchForm();
		return f;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("ConsultaRECBoletaRecojoSearchAction - setFindAttributes" );
		}    
		ConsultaRECBoletaRecojoSearchForm f = (ConsultaRECBoletaRecojoSearchForm)this.formBusqueda;
	    return this.buscar(f);
	}
	
	/**
	 * Metodo que se ejecuta luego que se ejecuta el Constructor de la clase
	 */
	@PostConstruct
	public void view() {
		if (log.isDebugEnabled()) {
            log.debug("Entering 'ConsultaHIPCuentaCorrientesAction - view' method");
        }
		Map<String, String> parametros = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();;
		this.parametrosPantalla = new HashMap<String, String>();
		this.parametrosPantalla.putAll(parametros);
		try {
			this.formBusqueda = this.devuelveFormBusqueda();
		}
		catch (Exception e) {
			
		}
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("ConsultaRECBoletaRecojoSearchAction - setViewAtributes" );
		}
		
		try {
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			
		    ConsultaRECBoletaRecojoSearchForm f = (ConsultaRECBoletaRecojoSearchForm)this.formBusqueda;
		    ConsultaHIPDatosCliente dtoDatosCliente = this.consultaHIPDatosClienteAction.getHipDtoDatosCliente();
		    
			String codigoConsultora = dtoDatosCliente.getCodigoCliente();
			
			if(StringUtils.isNotBlank(codigoConsultora))
			{
				f.setCodigoConsultora(codigoConsultora);
				
				List cabecerasList = buscar(f);
			}
			else
			{
				this.setConsultaRECBoletaRecojoSearchList(null);
			}		
			f.setNumeroBoletaRecojo("");
			
			//PARAMETRO TEMPORAL, HASTA QUE SALGA SECURITY
			GenericoService genericoService = (GenericoService)this.getBeanService("genericoService");				
			
			ParametroPais parametroPais = new ParametroPais();
			parametroPais.setCodigoPais(this.mPantallaPrincipalBean.getCountryCode());
			parametroPais.setCodigoSistema(Constants.HIP_CODIGO_SISTEMA);
			parametroPais.setCodigoParametro("017");
			parametroPais.setNombreParametro("indMostrarBotonesEdicionBR");
			parametroPais.setIndicadorActivo(Constants.ESTADO_ACTIVO);
			
			List lstParametros = genericoService.getParametrosPais(parametroPais);
			
			if(lstParametros != null && lstParametros.size() > 0){			
				ParametroPais ps = (ParametroPais)lstParametros.get(0);
				String valor = ps.getValorParametro();
				
				if(StringUtils.equals(valor, Constants.ESTADO_ACTIVO))
				{
					mostrarBotonesEdicion = true;
				}
			}
			//
		}
		catch (Exception e) {
			String error = e.getMessage();
			this.addError("Error: ", this.getResourceMessage("errors.detail", new String[]{error}));
		}
		
	    this.beanRegistroSeleccionado = null;

	}

	/**
	 * 
	 * @return
	 */
	private List buscar(ConsultaRECBoletaRecojoSearchForm f)
	{
	    //-- Varibales
	    MantenimientoRECDigitacionBoletasRecojoService service = (MantenimientoRECDigitacionBoletasRecojoService)getBean("spusicc.mantenimientoRECDigitacionBoletasRecojoService"); 
	    String codigoConsultora = f.getCodigoConsultora();
	    
	    //-- Crear pojo
	    Map criteria = new HashMap();
	    criteria.put("codigoConsultora", codigoConsultora.equals("")?"%":codigoConsultora);
	    criteria.put("numeroBoletaRecojo", f.getNumeroBoletaRecojo().equals("")?"%":f.getNumeroBoletaRecojo());
	    
	    //-- Logica para traer listado de boletas recojos de la consultora
	    List cabecerasList = service.getCabeceraBoletaRecojo(criteria);
	    
	    if(cabecerasList.size() > 0 && codigoConsultora.equals("")){
	    	BoletaRecojoCabecera bean = (BoletaRecojoCabecera) cabecerasList.get(0);
	    	codigoConsultora = bean.getCodigoConsultora();
		    criteria.put("codigoConsultora", codigoConsultora);
	    }
	    
	    //-- Logica para traer indicador de Consultora Bloqueada
	    f.setIndicadorBloqueada(service.getBloqueoConsultora(criteria));
	    
	    //-- Logica para traer datos de la consultora
	    DatosConsultora datosConsultora = service.getDatosConsultora(criteria);
	    if(datosConsultora.getNombreCliente()==null)
	    	f.setNombreConsultora(null);
	    else
		    f.setNombreConsultora( 
		    	(
		    		f.getCodigoConsultora().equals("") ?  (codigoConsultora.equals("")?"":codigoConsultora.concat(" - ")) : ""
		    	) + 
		    	datosConsultora.getNombreCliente() 
		    );
	    f.setZona(datosConsultora.getZona());
	    f.setGerente(datosConsultora.getNombreManager());
	    
	    //-- Peticiones
	    this.setConsultaRECBoletaRecojoSearchList(cabecerasList);
		
	    this.datatableBusqueda = new DataTableModel(cabecerasList);
	    	    
	    return cabecerasList;
	}
	
	/**
	 * 
	 * @param actionEvent
	 */
	public void search(ActionEvent actionEvent) {
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'search' method");
		}

		this.verificarRegistro(actionEvent);
		
		if (this.getBeanRegistroSeleccionado() != null) {
			
			String id = "";
			if(this.getBeanRegistroSeleccionado() instanceof BoletaRecojoCabecera)
			{
				id = ((BoletaRecojoCabecera)this.getBeanRegistroSeleccionado()).getBoletaRecojo();
			}
			else if (this.getBeanRegistroSeleccionado() instanceof HashMap)
			{
				id = MapUtils.getString((Map)this.getBeanRegistroSeleccionado(), "numeroBr");
			}			

			log.debug("Id seleccionado de la lista: " + id);				
			Map criteria = new HashMap();			
			criteria.put("codigoBoletaRecojo", id);						//consultaRECBoletaRecojoDetallesSearchList
			
			MantenimientoRECDigitacionBoletasRecojoService service = (MantenimientoRECDigitacionBoletasRecojoService)getBean("spusicc.mantenimientoRECDigitacionBoletasRecojoService");
		    List lista = service.getDetallesBoletaRecojo(criteria);
		    Iterator iLista = lista.iterator();
		    
	    	int sumaReclamadasT = 0;
			int sumaRecogidasT = 0;
			int sumaEliminadasT = 0;

		    while (iLista.hasNext()) {
				BoletaRecojoDetalle object = (BoletaRecojoDetalle) iLista.next();
				object.setIntUnidadesEliminadas(Integer.parseInt(object.getUnidadesEliminadas()));
				object.setIntUnidadesReclamadas(Integer.parseInt(object.getUnidadesReclamadas()));
				object.setIntUnidadesRecogidas(Integer.parseInt(object.getUnidadesRecogidas()));
				
				sumaReclamadasT = sumaReclamadasT + object.getIntUnidadesReclamadas();
				sumaRecogidasT = sumaRecogidasT + object.getIntUnidadesRecogidas();
				sumaEliminadasT = sumaEliminadasT + object.getIntUnidadesEliminadas();				
			}
		    
			this.sumaReclamadasT = sumaReclamadasT;
			this.sumaRecogidasT = sumaRecogidasT;
			this.sumaEliminadasT = sumaEliminadasT;
		    
		    this.setConsultaRECBoletaRecojoDetallesSearchList(lista);
		    
		    List list = service.getConsultaRECBoletaRecojoDetail(id);
		    ConsultaRECBoletaRecojoSearchForm f = (ConsultaRECBoletaRecojoSearchForm)this.formBusqueda;
		    ConsultaHIPDatosCliente dtoDatosCliente = this.consultaHIPDatosClienteAction.getHipDtoDatosCliente();
		    
		    if(list!=null && list.size()>0){
		    	Map map = (Map)list.get(0);		    	
		    	f.setCodigoCliente((String)map.get("codigoMotivo")==null?(String)map.get("codigoCliente"):
		    		(String)map.get("codigoCliente")+"-"+(String)map.get("nombreCliente"));	
		    	f.setNombreConsultora(dtoDatosCliente == null?null:dtoDatosCliente.getNombreCompleto());
		    	f.setNumeroBoleta((String)map.get("numeroBoleta"));
		    	f.setNumeroRecojo((String)map.get("numeroRecojo"));
		    	f.setGestion((String)map.get("gestion"));
		    	f.setResultado((String)map.get("resultado"));
		    	f.setFechaEmision1((String)map.get("fechaEmision1"));
		    	f.setFechaEmision2((String)map.get("fechaEmision2"));
		    	f.setFechaRecojo1((String)map.get("fechaRecojo1"));
		    	f.setFechaRecojo2((String)map.get("fechaRecojo2"));
		    	f.setImpreso1((String)map.get("impreso1"));
		    	f.setImpreso2((String)map.get("impreso2"));
		    	f.setEnviado1((String)map.get("enviado1"));
		    	f.setEnviado2((String)map.get("enviado2"));
		    	f.setRegreso1((String)map.get("regreso1"));
		    	f.setRegreso2((String)map.get("regreso2"));
		    	f.setLoteEnvio1((String)map.get("loteEnvio1"));
		    	f.setLoteEnvio2((String)map.get("loteEnvio2"));
		    	f.setLoteRegreso1((String)map.get("loteRegreso1"));
		    	f.setLoteRegreso2((String)map.get("loteRegreso2"));
		    }
		    	
		}						
		
	}
	
	/**
	 * 
	 * @param actionEvent
	 */
	public void activarBoletaRecojo(ActionEvent actionEvent) {
		
        log.debug("Entering 'activarBoletaRecojo' method");
        
        String id = ((BoletaRecojoCabecera)this.getBeanRegistroSeleccionado()).getBoletaRecojo();
                
        if (log.isDebugEnabled()) {
            log.debug("Id seleccionado de la lista: " + id);
         } 
    
	    MantenimientoRECDigitacionBoletasRecojoService service = (MantenimientoRECDigitacionBoletasRecojoService)getBean("spusicc.mantenimientoRECDigitacionBoletasRecojoService");
	       
	    Map activacionBoletaRecojoMap = new HashMap();	    
	    
		try{
			
			if (StringUtils.isNotEmpty(id)){
				
				activacionBoletaRecojoMap.put("numeroBoleta", id);
				activacionBoletaRecojoMap.put("usuario", this.mPantallaPrincipalBean.getCurrentUser().getLogin());
				service.activarBoletaRecojoProcess(activacionBoletaRecojoMap);		
				
				String mensajeError = (String) activacionBoletaRecojoMap.get("mensajeError");
								
				if (StringUtils.isNotEmpty(mensajeError))
				{
					this.addError("Error: ", mensajeError);
				}
				else
				{
					this.addInfo("Info: ", this.getResourceMessage("consultaRECBoletaRecojoSearchList.activacion.success"));
				}				
			}			
		
		}catch (Exception e){
			log.debug("e" + e);
            String descripcion = e.getMessage();
            
            this.addError("Error: ", this.getResourceMessage("errors.invalid.transaction.clusterizacion", new String []{descripcion}));
		}
		
		ConsultaRECBoletaRecojoSearchForm f = (ConsultaRECBoletaRecojoSearchForm)this.formBusqueda;
		buscar(f);		
	}
	
	/**
	 * 
	 * @param actionEvent
	 */
	public void eliminarBoletaRecojo(ActionEvent actionEvent) {
		
        log.debug("Entering 'eliminarBoletaRecojo' method");
        
        String id = ((BoletaRecojoCabecera)this.getBeanRegistroSeleccionado()).getBoletaRecojo();
                
        if (log.isDebugEnabled()) {
            log.debug("Id seleccionado de la lista: " + id);
         } 
    
	    MantenimientoRECDigitacionBoletasRecojoService service = (MantenimientoRECDigitacionBoletasRecojoService)getBean("spusicc.mantenimientoRECDigitacionBoletasRecojoService");
	       
	    Map eliminacionBoletaRecojoMap = new HashMap();
	    
	    
		try{
			
			if (StringUtils.isNotEmpty(id)){
				
				eliminacionBoletaRecojoMap.put("numeroBoleta", id);
				eliminacionBoletaRecojoMap.put("usuario", this.mPantallaPrincipalBean.getCurrentUser().getLogin());
				service.eliminarBoletaRecojoProcess(eliminacionBoletaRecojoMap);				
				
				String mensajeError = (String) eliminacionBoletaRecojoMap.get("mensajeError");
				
				if (StringUtils.isNotEmpty(mensajeError))
				{					
					this.addError("Error: ", mensajeError);
				}
				else
				{
					this.addInfo("Info: ", this.getResourceMessage("consultaRECBoletaRecojoSearchList.eliminacion.success"));
				}				
			}			
		
		}catch (Exception e){
			log.debug("e" + e);
            String descripcion = e.getMessage();
            this.addError("Error: ", this.getResourceMessage("errors.invalid.transaction.clusterizacion", new String []{descripcion}));
		}
		
		ConsultaRECBoletaRecojoSearchForm f = (ConsultaRECBoletaRecojoSearchForm)this.formBusqueda;
		buscar(f);		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setValidarConfirmar(java.lang.String)
	 */
	@Override
	public String setValidarConfirmar(String accion) {
		
		this.seleccionoRegistro= true;
		try {
			if (this.beanRegistroSeleccionado == null)
				seleccionoRegistro = false;
		}	
		catch (Exception e) {		
			seleccionoRegistro = false;
		}
		
		if (!seleccionoRegistro) 
			return this.getResourceMessage("errors.select.item");	
		else 
			this.mPantallaPrincipalBean.setCriteriosBusqueda(this.formBusqueda);
		
		return null;
	}

	/**
	 * @return the consultaHIPDatosClienteAction
	 */
	public ConsultaHIPDatosClienteAction getConsultaHIPDatosClienteAction() {
		return consultaHIPDatosClienteAction;
	}

	/**
	 * @param consultaHIPDatosClienteAction the consultaHIPDatosClienteAction to set
	 */
	public void setConsultaHIPDatosClienteAction(
			ConsultaHIPDatosClienteAction consultaHIPDatosClienteAction) {
		this.consultaHIPDatosClienteAction = consultaHIPDatosClienteAction;
	}

	/**
	 * @return the consultaRECBoletaRecojoSearchList
	 */
	public List getConsultaRECBoletaRecojoSearchList() {
		return consultaRECBoletaRecojoSearchList;
	}

	/**
	 * @param consultaRECBoletaRecojoSearchList the consultaRECBoletaRecojoSearchList to set
	 */
	public void setConsultaRECBoletaRecojoSearchList(
			List consultaRECBoletaRecojoSearchList) {
		this.consultaRECBoletaRecojoSearchList = consultaRECBoletaRecojoSearchList;
	}

	/**
	 * @return the consultaRECBoletaRecojoDetallesSearchList
	 */
	public List getConsultaRECBoletaRecojoDetallesSearchList() {
		return consultaRECBoletaRecojoDetallesSearchList;
	}

	/**
	 * @param consultaRECBoletaRecojoDetallesSearchList the consultaRECBoletaRecojoDetallesSearchList to set
	 */
	public void setConsultaRECBoletaRecojoDetallesSearchList(
			List consultaRECBoletaRecojoDetallesSearchList) {
		this.consultaRECBoletaRecojoDetallesSearchList = consultaRECBoletaRecojoDetallesSearchList;
	}

	/**
	 * @return the sumaReclamadasT
	 */
	public Integer getSumaReclamadasT() {
		return sumaReclamadasT;
	}

	/**
	 * @param sumaReclamadasT the sumaReclamadasT to set
	 */
	public void setSumaReclamadasT(Integer sumaReclamadasT) {
		this.sumaReclamadasT = sumaReclamadasT;
	}

	/**
	 * @return the sumaRecogidasT
	 */
	public Integer getSumaRecogidasT() {
		return sumaRecogidasT;
	}

	/**
	 * @param sumaRecogidasT the sumaRecogidasT to set
	 */
	public void setSumaRecogidasT(Integer sumaRecogidasT) {
		this.sumaRecogidasT = sumaRecogidasT;
	}

	/**
	 * @return the sumaEliminadasT
	 */
	public Integer getSumaEliminadasT() {
		return sumaEliminadasT;
	}

	/**
	 * @param sumaEliminadasT the sumaEliminadasT to set
	 */
	public void setSumaEliminadasT(Integer sumaEliminadasT) {
		this.sumaEliminadasT = sumaEliminadasT;
	}

	/**
	 * @return the mostrarBotonesEdicion
	 */
	public boolean isMostrarBotonesEdicion() {
		return mostrarBotonesEdicion;
	}

	/**
	 * @param mostrarBotonesEdicion the mostrarBotonesEdicion to set
	 */
	public void setMostrarBotonesEdicion(boolean mostrarBotonesEdicion) {
		this.mostrarBotonesEdicion = mostrarBotonesEdicion;
	}

}
