package biz.belcorp.ssicc.web.spusicc.sgr.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValueCUV;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.MantenimientoPRECambioCodigoVentaService;
import biz.belcorp.ssicc.service.spusicc.sgr.MantenimientoSGRGenericoService;
import biz.belcorp.ssicc.service.spusicc.sto.MantenimientoSTOBloqueoControlService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.sgr.form.MantenimientoSGRPolizaForm;
import biz.belcorp.ssicc.web.spusicc.sgr.form.MantenimientoSGRPolizaSearchForm;

/**
 * @author Giovanni Ascarza
 */
@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MantenimientoSGRPolizaSearchAction extends BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = -2039671691149644047L;
	
	private List sgrPolizaList;
    private List sgrEstatusList;
    private List sgrAseguradoraList;
    
    private List sgrPolizaCampaniaGratuitasList;
    private DataTableModel dataModelCampaniaGratuita;
    private Object beanRegistroSeleccionadoCampGratuita;
    
    private List sgrPolizaEstatusList;
    private DataTableModel dataModelEstatus;
    private Object beanRegistroSeleccionadoEstatus;

    private List sgrPolizaKitList;
    private DataTableModel dataModelKit;
    private Object beanRegistroSeleccionadoKit;
    
    private List sgrPolizaVigenciaList;
    private List sgrPolizaDsctoList;
    
    private String campanhaActual;
    private String codigoPolizaSession;
    private boolean indicadorHabilitaPrecio;
    private Boolean ocultarPrecios;
    
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoSGRPolizaForm";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		return new MantenimientoSGRPolizaSearchForm();
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		log.debug("'setViewAttributes'");

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoSGRPolizaSearchForm  f = (MantenimientoSGRPolizaSearchForm) this.formBusqueda; 		

		f.setCodigoPais(pais.getCodigo());							
	
        ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
        MantenimientoPRECambioCodigoVentaService mantenimientoPRECambioCodigoVentaService = (MantenimientoPRECambioCodigoVentaService)getBean("spusicc.mantenimientoPRECambioCodigoVentaService");
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		reporteService.getListaGenerico("getRegionesByPais", criteriaOperacion);
		
		this.sgrEstatusList = mantenimientoPRECambioCodigoVentaService.getEstatusClienteList(null);
		
		MantenimientoSGRGenericoService service = (MantenimientoSGRGenericoService) this.getBean("spusicc.mantenimientoSGRGenericoService");		
		List resultado = service.getPoliza(null);
		
		this.sgrAseguradoraList = resultado;
		this.ocultarPrecios  = false;
		this.sgrPolizaVigenciaList = new ArrayList();
		this.sgrPolizaKitList = new ArrayList();
		this.sgrPolizaDsctoList = new ArrayList();
		this.sgrPolizaEstatusList = new ArrayList();
		this.sgrPolizaCampaniaGratuitasList = new ArrayList();
	        
		this.dataModelKit = new DataTableModel(this.sgrPolizaKitList);
		this.dataModelEstatus = new DataTableModel(this.sgrPolizaEstatusList);
	    this.dataModelCampaniaGratuita = new DataTableModel(this.sgrPolizaCampaniaGratuitasList);

		this.log.debug("Todo Ok: Redireccionando");	
	}
	
	/**
	 * @param f
	 */
	public void limpiar(MantenimientoSGRPolizaForm f){
		f.setIndicadorNuevoKit(Constants.NRO_UNO);
        f.setIndicadorNuevoDscto(Constants.NRO_UNO);
        f.setIndicadorNuevoVigencia(Constants.NRO_UNO);
        f.setIndicadorNuevoEstatus(Constants.NRO_UNO);
        f.setIndicadorNuevoCampaniasGratuitas(Constants.NRO_UNO);
        f.setTabSeleccion(Constants.SGR_TAB_POLIZA_KIT);
        f.setCampanhaActual(f.getCodigoPeriodo());
        f.setIndicadorActivo(Constants.NUMERO_UNO);
        f.setCoberturaPEGS(Constants.NUMERO_CERO);

        f.setCodigoEstatus("7");
        f.setCodigoPoliza("");
        f.setDescripcionPoliza("");
        f.setPrecioPoliza("");
        f.setEdadMinima("");
        f.setEdadMaxima("");

        this.campanhaActual = f.getCodigoPeriodo();

        this.sgrPolizaVigenciaList = new ArrayList();
        this.sgrPolizaKitList = new ArrayList();
	    this.sgrPolizaDsctoList = new ArrayList();
	    this.sgrPolizaCampaniaGratuitasList = new ArrayList();
	    
	    this.dataModelKit = new DataTableModel(sgrPolizaKitList);
	    this.dataModelCampaniaGratuita = new DataTableModel(sgrPolizaCampaniaGratuitasList);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setAddAttributes()
	 */
	@Override
	protected void setAddAttributes() throws Exception {
		log.debug("'setAddAttributes'");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();	
		MantenimientoSGRPolizaForm f = (MantenimientoSGRPolizaForm) this.formMantenimiento;
		 InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		
		f.setNumDiasFacturacion(String.valueOf(0));
		f.setCodigoPeriodo(service.getPeriodoDefaultByPaisCanal(pais.getCodigo(),Constants.CODIGO_CANAL_DEFAULT));
        f.setCampanhaProceso(service.getPeriodoDefaultByPaisCanal(pais.getCodigo(),Constants.CODIGO_CANAL_DEFAULT));
        f.setCampInicialDscto(service.getPeriodoDefaultByPaisCanal(pais.getCodigo(),Constants.CODIGO_CANAL_DEFAULT));        
       
        limpiar(f);  
        this.sgrPolizaEstatusList = new ArrayList();
        this.dataModelEstatus = new DataTableModel(sgrPolizaEstatusList);
	 
        if (!insertEstatus(f)) {
        	this.addWarn("", this.getResourceMessage("mantenimientoSGRPolizaForm.existe.estatus.registro"));
		}
        this.setMostrarBotonSave(false);
	}

	/**
	 * @param f
	 * @return
	 * @throws Exception
	 */
	public boolean insertEstatus(MantenimientoSGRPolizaForm f)
			throws Exception {
				if (log.isDebugEnabled()) {
					log.debug("Entering 'insert Estatus' method");
				}
				Map map = BeanUtils.describe(f);
				
				List list = this.sgrPolizaEstatusList;
				List estatusList = this.sgrEstatusList;
				
				map.put("indicadorAccion",Constants.NUMERO_CERO);//0:INSERTAR 1:ACTUALIZA 2:ELIMINAS
				if(isValidoEstatus(map,list)){//es registro valido cuando no se encuntra en la lista o se encuentra como eliminado
					
					for(int i=0;i<estatusList.size();i++){
						Map estatus = (Map)estatusList.get(i);
						if(String.valueOf(estatus.get("oidEstatus")).equals((String)map.get("codigoEstatus"))){
							map.put("descripcionEstatus", estatus.get("valI18n"));
						}
					}
					this.sgrPolizaEstatusList.add(map);
					
					return true;
				}
					
				return false;
			}
	
	/**
	 * El registro es valido si no se encunetra en la lista o ya se encuntra eliminado
	 * @param map
	 * @param list
	 * @return
	 */
	private boolean isValidoEstatus(Map map, List list) {
		Iterator it = list.iterator();
		
		String codigoEstatus = (String)map.get("codigoEstatus");
		
		while(it.hasNext()){
			Map mapAux = (Map)it.next();
			String codigoEstatusAux = mapAux.get("oidEstatusCliente").toString();
			String indicadorAccionAux = (String)mapAux.get("indicadorAccion");
			
			if(codigoEstatus.equals(codigoEstatusAux) && (indicadorAccionAux.equals(Constants.NUMERO_CERO) || indicadorAccionAux.equals(Constants.NUMERO_UNO))) 
				return false;
		}
		
		return true;
	}	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		log.debug("'setFindAttributes'");
	
		try {
			
			MantenimientoSGRPolizaSearchForm  f = (MantenimientoSGRPolizaSearchForm) this.formBusqueda;
			MantenimientoSGRGenericoService service = (MantenimientoSGRGenericoService) this.getBean("spusicc.mantenimientoSGRGenericoService");
			
			/* obteniendo valores */
			Map criteria = BeanUtils.describe(f);		
			/* Obteniendo Lista */
			this.sgrPolizaList = service.getPoliza(criteria);


		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
		
		return sgrPolizaList;
	}


	/**
	 * Se valida que la poliza no tenga registros por cliente
	 * @param map
	 * @return
	 */
	private boolean isValidoPoliza(Map map) {
		MantenimientoSGRGenericoService service = (MantenimientoSGRGenericoService) 
									this.getBean("spusicc.mantenimientoSGRGenericoService");
		Integer num=service.getNumPolizasRegistradas(map);
		return num >0?false:true;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {	
		log.debug("'setDeleteAttributes'");
		 Map map = (Map) this.beanRegistroSeleccionado;
		 MantenimientoSGRPolizaSearchForm f = (MantenimientoSGRPolizaSearchForm) this.formBusqueda;
	
		 Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

			try {
				
				MantenimientoSGRGenericoService service = (MantenimientoSGRGenericoService) this.getBean("spusicc.mantenimientoSGRGenericoService");								
				map.put("codigoPais", f.getCodigoPais());
				map.put("login", usuario.getLogin());
						
				if(isValidoPoliza(map)){
					service.deletePoliza(map);	
				}else{
					addWarn("", this.getResourceMessage("mantenimientoSGRPolizaSearchForm.existe.polizas.registradas"));
					return false;
				}
				
			//enviamos el mensaje de satisfaccion
			/*addInfo("", this.getResourceMessage("mantenimientoSGRPolizaSearchForm.cabecera.deleted"));*/
		
			}catch (Exception e) {
				this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

		return true;
	}
	
	@Override
	protected String getSalirForward() {
		return "mantenimientoSGRPolizaList";
	}
	/**
	 * Validaque qhaya un dato no eliminado 
	 * @param listVigencia
	 * @return
	 */
	private boolean validarEstatus(List list) {

		if(list.size()==0) return false;
		for(int i=0;i<list.size();i++){
			Map map=(Map)list.get(i);
			String indicadorAccion = (String)map.get("indicadorAccion");
			if(!indicadorAccion.equals(Constants.NUMERO_DOS)) return true; //hay datos 
		}
		//todos son eliminados
		return false;
	}
	
	/**
	 * @param f
	 * @return
	 */
	public boolean validarMante(MantenimientoSGRPolizaForm f){
		
		BigDecimal bd = new BigDecimal(f.getPrecioPoliza());
		int precioPoliza = bd.intValue();
		int edadMinima = NumberUtils.toInt(f.getEdadMinima());
		int edadMaxima = NumberUtils.toInt(f.getEdadMaxima());
		
		if(precioPoliza*1 <=0){
			this.addWarn("", this.getResourceMessage("mantenimientoSGRPolizaForm.precioPoliza.mayor.cero"));
			return false;
		}
	   if(edadMinima*1 <=0){
		   this.addWarn("", this.getResourceMessage("mantenimientoSGRPolizaForm.edadMinima.mayor.cero"));
		   return false;
		}

	  if(edadMaxima*1 <=0){
		  this.addWarn("", this.getResourceMessage("mantenimientoSGRPolizaForm.edadMaxima.mayor.cero"));
		   return false;
	  }

	  if(edadMinima*1 >=edadMaxima*1){
			this.addWarn("", this.getResourceMessage("mantenimientoSGRPolizaForm.edadMaxima.mayor.edadMinima"));
		return false;
	  }

		return true;
		
	}
	
	/**
	 * @param actionEvent
	 */
	public void validarGrabar(ActionEvent actionEvent) {
		
		MantenimientoSGRPolizaForm f = (MantenimientoSGRPolizaForm) this.formMantenimiento;
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'validarGrabar' method");
		}
		
		if(this.validarMante(f)){
			this.getRequestContext().execute("PF('idConfirmDialogSaveSGRPoliza_confirmationDialogConfirmar').show()");
			return ;
		}
		
		return;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setEditAttributes()
	 */
	@Override
	protected void setEditAttributes() throws Exception {
		log.debug("entrando a: 'setEditAttributes'");
		try {
			 cargarData();
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}  
		this.setMostrarBotonSave(false);
	}
	
	/**
	 * @throws Exception
	 */
	public void cargarData(){       
		MantenimientoSGRPolizaForm f = (MantenimientoSGRPolizaForm) this.formMantenimiento;
        Pais pais = this.mPantallaPrincipalBean.getCurrentCountry(); 
		try{

			if (f != null) {
			
				this.obtenerRegistro(f);
		        InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		        f.setCodigoPeriodo(service.getPeriodoDefaultByPaisCanal(
						pais.getCodigo(),
						Constants.CODIGO_CANAL_DEFAULT));
		        f.setCampanhaActual(f.getCodigoPeriodo());
		        f.setCampanhaProceso(f.getCodigoPeriodo());
		        f.setCampInicialDscto(f.getCodigoPeriodo());
		        campanhaActual = f.getCodigoPeriodo();	        
		        codigoPolizaSession = f.getCodigoPoliza();
			}
			f.setTabSeleccion(Constants.SGR_TAB_POLIZA_KIT);
			
			/* INI SA PER-SiCC-2012-0903 */
	        Map criteriaParam = new HashMap();
			criteriaParam.put("codigoPais", pais.getCodigo());
			criteriaParam.put("codigoSistema", "SGR");
			criteriaParam.put("nombreParametro", "indHabilitaPrecio");
		     
			MantenimientoSTOBloqueoControlService stoService = (MantenimientoSTOBloqueoControlService)getBean("spusicc.mantenimientoSTOBloqueoControlService");
		    String indicadorHabilitaPrecio = stoService.getParametroGenericoSistema(criteriaParam);
			this.indicadorHabilitaPrecio = indicadorHabilitaPrecio != null && Integer.valueOf(indicadorHabilitaPrecio) == 1 ? Boolean.TRUE : Boolean.FALSE;

		    dataModelKit = new DataTableModel(sgrPolizaKitList);
		    
		}catch(Exception e){
			this.addError("Error : ", this.obtieneMensajeErrorException(e));			
		}
	}
	
	/**
	 * @param id
	 * @param form
	 * @param request 
	 * @throws Exception
	 */
	private void obtenerRegistro(MantenimientoSGRPolizaForm f) {
		
		try {
			MantenimientoSGRGenericoService service = (MantenimientoSGRGenericoService)getBean("spusicc.mantenimientoSGRGenericoService");		
			
			Map bean = BeanUtils.describe(f);	
			
			this.sgrPolizaVigenciaList  = service.getVigencia(bean);
			this.sgrPolizaKitList	   = service.getKit(bean);
			this.sgrPolizaDsctoList = service.getDscto(bean);
			this.sgrPolizaEstatusList   = service.getEstatus(bean);
			this.sgrPolizaCampaniaGratuitasList = service.getCampaniaGratuita(bean);

			//Agregando correlativo
			int i = 0;
			for (Object cg : this.sgrPolizaCampaniaGratuitasList) {
				Map map = (Map)cg;
				map.put("idCorrelativo", String.valueOf(System.currentTimeMillis()));
				this.sgrPolizaCampaniaGratuitasList.set(i, map);
				i++;
			}
			
			this.dataModelKit = new DataTableModel(this.sgrPolizaKitList);
			this.dataModelEstatus = new DataTableModel(this.sgrPolizaEstatusList);
			this.dataModelCampaniaGratuita = new DataTableModel(this.sgrPolizaCampaniaGratuitasList);

	        f.setIndicadorNuevoKit(Constants.NRO_UNO);
	        f.setIndicadorNuevoDscto(Constants.NRO_UNO);
	        f.setIndicadorNuevoVigencia(Constants.NRO_UNO);
	        f.setIndicadorNuevoEstatus(Constants.NRO_UNO);
	        f.setIndicadorNuevoCampaniasGratuitas(Constants.NRO_UNO);
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}		
	}
	
	/**
	 * @param lista
	 * @param idCorrelativo
	 * @return
	 */
	public String getIndex(List lista, String idCorrelativo){
		int i = 1;
		for (Object object : lista) {
			Map map = (Map)object;
			if (map.get("idCorrelativo").equals(idCorrelativo)) {
				break;
			}
			i++;
		}
		return String.valueOf(i);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setConsultarAttributes()
	 */
	@Override
	protected void setConsultarAttributes() throws Exception{
		
		log.debug("entrando a: 'setConsultarAttributes'");
		try {
			 cargarData();
		} catch (Exception e) {

			String error = e.getMessage();
			if (StringUtils.isBlank(error)) {
				error = e.getLocalizedMessage();
			}
			throw new Exception(this.getResourceMessage("errors.detail", new Object[]{ error }));
		}  
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setObtenerRegistroAttributes()
	 */
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		this.ocultarPrecios  = false;
		Map mapa = (Map) this.beanRegistroSeleccionado;
		MantenimientoSGRPolizaSearchForm form = (MantenimientoSGRPolizaSearchForm) this.formBusqueda;
		MantenimientoSGRPolizaForm mForm = new MantenimientoSGRPolizaForm();
		
		BeanUtils.copyProperties(mForm, form);
		BeanUtils.populate(mForm, mapa);

		if(this.accion.equals(this.ACCION_CONSULTAR)){
			this.setMostrarBotonSave(false);
		}else{
			this.setMostrarBotonSave(true);
		}
		
		if(!this.accion.equals(this.ACCION_NUEVO)){
			mForm.setNewRecord(false);
		}
		
		return mForm;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoAbstractAction#devuelveMensajeKeySaveOK()
	 */
	@Override
	protected String devuelveMensajeKeySaveOK() {
		MantenimientoSGRPolizaForm mantenimientoForm = (MantenimientoSGRPolizaForm) this.formMantenimiento;
		boolean isNew = mantenimientoForm.isNewRecord();
		if(isNew) {
			return "mantenimientoSGRPoliza.created";
		}else{
			return "mantenimientoSGRPoliza.updated";
		}	
	}
		
	/*Insertar Registro Tab - Kit */
	public void insertarRegistroKit(ActionEvent actionEvent) {
		  log.debug("'insertarRegistroKit'");
       try{
			MantenimientoSGRPolizaForm f = (MantenimientoSGRPolizaForm) this.formMantenimiento;
			f.setIndicadorNuevoKit(Constants.NRO_UNO);
			f.setIndicadorNuevoDscto(Constants.NRO_UNO);
			f.setIndicadorNuevoEstatus(Constants.NRO_UNO);
			f.setIndicadorNuevoCampaniasGratuitas(Constants.NRO_UNO);
			f.setTabSeleccion(Constants.SGR_TAB_POLIZA_KIT);
	
			Map map = BeanUtils.describe(f);
			AjaxService ajaxService = (AjaxService) getBean("ajaxService");		
			//validacion del codigo de venta
			LabelValueCUV lb=ajaxService.getCodigoVentaPrecio(f.getCodigoVenta(), f.getCampanhaProceso());
			//se valida que exista en matriz de facturacion
			if(lb==null || lb.getLabel() == null){	
				addWarn("", this.getResourceMessage("mantenimientoMENMensajeCodigoVentaForm.noexiste.campa.factu", new String[]{f.getCampanhaProceso()}));
				return;					
			}
			
			List list = this.sgrPolizaKitList;
			if(CollectionUtils.isEmpty(list)) list = new ArrayList();
			
			map.put("codigoSap", lb.getLabel());
			map.put("descripcionProducto", lb.getValue());
			map.put("codigoTipoOferta", lb.getOferta());
			map.put("indicadorAccion",Constants.NUMERO_CERO);//0:INSERTAR 1:ACTUALIZA 2:ELIMINAS
			if(isValido(map,list)){//es registro valido cuando no se encuntra en la lista o se encuentra como eliminado
				sgrPolizaKitList.add(map);
			}		
			else{
				this.addWarn("", this.getResourceMessage("mantenimientoSGRPolizaForm.existe.kit.registro"));
				return;				
			}
				
			f.setCampanhaProceso("");
			f.setCodigoVenta("");
			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
       
	}

	/**
	 * El registro es valido si no se encunetra en la lista o ya se encuntra eliminado
	 * @param map
	 * @param list
	 * @return
	 */
	private boolean isValido(Map map, List list) {
		Iterator it = list.iterator();
		//String codigoSap = (String)map.get("codigoVenta");
		String codigoVenta = (String)map.get("codigoVenta");
		String campanhaProceso = (String)map.get("campanhaProceso");	

		while(it.hasNext()){
			Map mapAux = (Map)it.next();
			String codigoVentaAux = (String)mapAux.get("codigoVenta");
			String campanhaProcesoAux = (String)mapAux.get("campanhaProceso");
			
			String indicadorAccionAux = (String)mapAux.get("indicadorAccion");
			if(codigoVenta.equals(codigoVentaAux) &&
					campanhaProceso.equals(campanhaProcesoAux) && 
							(indicadorAccionAux.equals(Constants.NUMERO_CERO) || indicadorAccionAux.equals(Constants.NUMERO_UNO))
					) return false;
		}
		return true;
	}
	
	
	public void onRowSelect(SelectEvent event) { }
    public void onRowUnselect(UnselectEvent event){
    	this.beanRegistroSeleccionadoKit = null;
    	this.beanRegistroSeleccionadoCampGratuita = null;
    	this.beanRegistroSeleccionadoEstatus = null;
    }
	
	/**
	 * Elimina fila seleccionado en tab KIT
	 * 
	 * @param actionEvent
	 */
	public void eliminarRegistroKit(ActionEvent actionEvent) {
	       log.debug("'eliminarRegistroKit'");
	       
			MantenimientoSGRPolizaForm f = (MantenimientoSGRPolizaForm) this.formMantenimiento;		
			f.setIndicadorNuevoKit(Constants.NRO_UNO);
			f.setIndicadorNuevoDscto(Constants.NRO_UNO);
			f.setIndicadorNuevoCampaniasGratuitas(Constants.NRO_UNO);
			f.setTabSeleccion(Constants.SGR_TAB_POLIZA_KIT);		
        
			try{
				
				if(this.beanRegistroSeleccionadoKit == null){
					this.addError("Error: ", this.getResourceMessage("errors.select.item"));
					return; 
					}
				
			Map obj = (Map) this.beanRegistroSeleccionadoKit;
			String codigoVenta = (String)obj.get("codigoVenta");
			String campanhaProceso = (String)obj.get("campanhaProceso");
			if (obj != null) {														
				for (int i = 0; i < this.sgrPolizaKitList.size(); i++) {
					Map lista = (Map) this.sgrPolizaKitList.get(i);
					String codigoVentaLista = (String)lista.get("codigoVenta");
					String campanhaProcesoLista = (String)lista.get("campanhaProceso");
					if((StringUtils.equals(codigoVenta, codigoVentaLista))&&(StringUtils.equals(campanhaProceso, campanhaProcesoLista))){
						this.sgrPolizaKitList.remove(obj);
						obj.put("indicadorAccion", Constants.NUMERO_DOS);
						this.sgrPolizaKitList.add(obj);	
						this.beanRegistroSeleccionadoKit = null;
					}
					
				}
			}else{
				this.addWarn("Warning: ", this.getResourceMessage("errors.select.item"));
				return;
			}
			
		 } catch (Exception e) {
				this.addError("Error: ", this.obtieneMensajeErrorException(e));
		 }	
				
			this.dataModelKit = new DataTableModel(this.sgrPolizaKitList);
	}
	
	
	/**
	 * El registro es valido si no se encunetra en la lista o ya se encuntra eliminado
	 * o que no exista traslape
	 * @param map
	 * @param list
	 * @return
	 */
	private boolean isValidoCampaniasGratuitas(Map map, List list,String idCampaniasGratuitas) {
		Iterator it = list.iterator();
		String campInicialCampGratuitas = (String)map.get("campInicialCampGratuitas");
		String campFinalCampGratuitas = (String)map.get("campFinalCampGratuitas");
		String correlativoCampaniaGratuita = String.valueOf(map.get("correlativoCampaniaGratuita")==null?"":map.get("correlativoCampaniaGratuita"));
		int index = Integer.parseInt(StringUtils.isNotEmpty(idCampaniasGratuitas)?idCampaniasGratuitas:"0") -1 ;
		int pos=0;	
		
		it = list.iterator();
		 
	    while(it.hasNext()){
		  log.debug("tarslape de CAMPANHAS");
		  Map mapAux = (Map)it.next();
			String campInicialCampGratuitasAux = (String)mapAux.get("campInicialCampGratuitas");
			String campFinalCampGratuitasAux = (String)mapAux.get("campFinalCampGratuitas");
			String indicadorAccionAux = (String)mapAux.get("indicadorAccion");		
			String correlativoCampaniaGratuitaAux = String.valueOf(mapAux.get("correlativoCampaniaGratuita")==null?"":mapAux.get("correlativoCampaniaGratuita"));
			//si son distintos se valida traslape de campanhas						
			if( index != pos &&
			    (Integer.parseInt(campInicialCampGratuitas) >= Integer.parseInt(campInicialCampGratuitasAux) &&  
			    		Integer.parseInt(campInicialCampGratuitas) <= Integer.parseInt(campFinalCampGratuitasAux)
				 ||
				 Integer.parseInt(campFinalCampGratuitas) >= Integer.parseInt(campInicialCampGratuitasAux) &&  
				         Integer.parseInt(campFinalCampGratuitas) <= Integer.parseInt(campFinalCampGratuitasAux)
				 ||
					 ( Integer.parseInt(campInicialCampGratuitas) < Integer.parseInt(campInicialCampGratuitasAux) &&
							 Integer.parseInt(campInicialCampGratuitas) > Integer.parseInt(campFinalCampGratuitasAux))
				 )		 
				 &&		 
				  (indicadorAccionAux.equals(Constants.NUMERO_CERO) || indicadorAccionAux.equals(Constants.NUMERO_UNO))							 
				  					     					     							
			   ){
				
				if(StringUtils.isNotEmpty(correlativoCampaniaGratuita) && StringUtils.isNotEmpty(correlativoCampaniaGratuitaAux)
				    && StringUtils.equals(correlativoCampaniaGratuita, correlativoCampaniaGratuitaAux)) 
					continue;
				
			  return false;
			}
					
		 pos++;		
		}		 
				
		return true;
	}

	
	public boolean validarRegistroCampGrat(MantenimientoSGRPolizaForm f){
		int campaniaProceso = NumberUtils.toInt(f.getCampanhaProceso());
		int campInicialCampGratuitas = NumberUtils.toInt(f.getCampInicialCampGratuitas());
		int campFinalCampGratuitas = NumberUtils.toInt(f.getCampFinalCampGratuitas());
		int numeroPeriodosEvaluar = NumberUtils.toInt(f.getNumeroPeriodosEvaluar()); 
		int numeroMaximoDescuento = NumberUtils.toInt(f.getNumeroMaximoDescuento());
		
		if(campInicialCampGratuitas < campaniaProceso){
			this.addWarn("", this.getResourceMessage("mantenimientoSGRPolizaForm.mayorCampInicialCampProceso"));	
			return false;
		}
		
		if(campInicialCampGratuitas > campFinalCampGratuitas){
			this.addWarn("", this.getResourceMessage("mantenimientoSGRPolizaForm.mayorCampInicialCampFinal"));	
			return false;
		}
		
		if(f.getCodigoBeneficiario().equals("N")){
			if(!f.getNumeroPeriodosEvaluar().equals("")){
				if(!(numeroPeriodosEvaluar >=1 && numeroPeriodosEvaluar<=4)){
					this.addWarn("", this.getResourceMessage("mantenimientoSGRPolizaForm.numeroPeriodosEvaluar.rango"));
					//alert('<fmt:message key="mantenimientoSGRPolizaForm.numeroPeriodosEvaluar.rango"/>');
					return false;
				}
				if(numeroMaximoDescuento > numeroPeriodosEvaluar){
					this.addWarn("", this.getResourceMessage("mantenimientoSGRPolizaForm.numeroMaximoDescuento.mayor.numeroPeriodosEvaluar"));
					return false;
				}	
			} else {
				if(!f.getNumeroMaximoDescuento().equals("")){
					this.addWarn("", this.getResourceMessage("mantenimientoSGRPolizaForm.numeroMaximoDescuento.mayor.numeroPeriodosEvaluar"));
					return false;
				}
			}	
		}
		
		return true;
	}
	
	/**
	 * @param actionEvent
	 */
	public void insertarRegistroCampGrat(ActionEvent actionEvent) {
		  log.debug("'insertarRegistroCampGrat'");
          
		MantenimientoSGRPolizaForm f = (MantenimientoSGRPolizaForm) this.formMantenimiento;
		
		if (!validarRegistroCampGrat(f)) {
			return;
		}
		
		try {
			f.setIndicadorNuevoDscto(Constants.NRO_UNO);
			f.setIndicadorNuevoKit(Constants.NUMERO_UNO);
			f.setIndicadorNuevoEstatus(Constants.NRO_UNO);
			f.setIndicadorNuevoCampaniasGratuitas(Constants.NRO_UNO);
			f.setTabSeleccion(Constants.SGR_TAB_POLIZA_CAMPANIAS_GRATUITAS);
			
			Map map = BeanUtils.describe(f);
			
			List list = this.sgrPolizaCampaniaGratuitasList;
			if(CollectionUtils.isEmpty(list)) list = new ArrayList();
			
			
			map.put("indicadorAccion",Constants.NUMERO_CERO);//0:INSERTAR 1:ACTUALIZA 2:ELIMINAS
			
			if(isValidoCampaniasGratuitas(map,list,"")){//es registro valido cuando no se encuntra en la lista o se encuentra como eliminado						
				
				if(String.valueOf((String)map.get("codigoBeneficiario")).equals("T")){
					map.put("descripcionBeneficiario", "Todos");
				}
				
				if(String.valueOf((String)map.get("codigoBeneficiario")).equals("L")){
					map.put("descripcionBeneficiario", "Lideres");
				}
				
				if(String.valueOf((String)map.get("codigoBeneficiario")).equals("N")){
					map.put("descripcionBeneficiario", "Nuevas");
				}
			
				if(String.valueOf((String)map.get("codigoBeneficiario")).equals("P")){
					map.put("descripcionBeneficiario", "Migración Póliza");
				}
				
				if (this.accion.equals(ACCION_NUEVO)) {
					map.put("idCorrelativo", String.valueOf(System.currentTimeMillis()));
				}
				
				this.sgrPolizaCampaniaGratuitasList.add(map);
			}		
			else{
				this.addWarn("", this.getResourceMessage("mantenimientoSGRPolizaForm.existe.campGratuita.registro"));
				return;			
			}
				
			f.setCampInicialCampGratuitas("");
			f.setCampFinalCampGratuitas("");
			f.setCodigoBeneficiario("");
			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}		
			
	}
	
	/**
	 * @param actionEvent
	 */
	public void editarRegistroCampGrat(ActionEvent actionEvent){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'modifica Campañas Gratutias' method");
		}
		MantenimientoSGRPolizaForm f = (MantenimientoSGRPolizaForm) this.formMantenimiento;
		try {
			
		  Object obj = this.beanRegistroSeleccionadoCampGratuita;
	
		  if (obj != null) {
																
				Map map = (Map)obj;
				
				f.setCampInicialCampGratuitas((String)map.get("campInicialCampGratuitas"));
				f.setCampFinalCampGratuitas((String)map.get("campFinalCampGratuitas"));
				f.setCodigoBeneficiario((String)map.get("codigoBeneficiario"));
				
				/* INI SA PER-SiCC-2012-0786 */
				f.setNumeroPeriodosEvaluar((String)map.get("numeroPeriodosEvaluar"));
				f.setNumeroMaximoDescuento((String)map.get("numeroMaximoDescuento"));
				/* FIN SA PER-SiCC-2012-0786 */
				
				f.setIndicadorNuevoKit(Constants.NUMERO_UNO);
				f.setIndicadorNuevoCampaniasGratuitas(Constants.NUMERO_CERO);
				f.setIndicadorNuevoDscto(Constants.NUMERO_UNO);
				f.setCorrelativoCampaniaGratuita(String.valueOf(map.get("correlativoCampaniaGratuita")));
			
			  }else{
				this.addWarn("Warning: ", this.getResourceMessage("errors.select.item"));	
			  }
		 
		  } catch (Exception e) {
			  this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}

	}
	
	/**
	 * @param actionEvent
	 */
	public void deshacerCampaniasGratuitas(ActionEvent actionEvent){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'deshacerCampaniasGratuitas' method");
		}
		MantenimientoSGRPolizaForm f = (MantenimientoSGRPolizaForm) this.formMantenimiento;
		//limpiando 
        f.setCampInicialCampGratuitas("");
		f.setCampFinalCampGratuitas("");
		f.setCodigoBeneficiario("");
		f.setNumeroPeriodosEvaluar("");
		f.setNumeroMaximoDescuento("");

		f.setIndicadorNuevoCampaniasGratuitas(Constants.NUMERO_UNO);
		
		this.beanRegistroSeleccionadoCampGratuita = null;
	}
	
	/**
	 * @param actionEvent
	 */
	public void saveCampaniasGratuitas(ActionEvent actionEvent){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'saveCampaniasGratuitas' method");
		}	
		
		MantenimientoSGRPolizaForm f = (MantenimientoSGRPolizaForm) this.formMantenimiento;
		
		if (!validarRegistroCampGrat(f)) {
			return;
		}
		
	   try {	
		Map criteria = BeanUtils.describe(f);
		//ActionMessages messages = new ActionMessages();
		List list=this.sgrPolizaCampaniaGratuitasList;
		Object obj = this.beanRegistroSeleccionadoCampGratuita;		
		Map map = (Map)obj;	
		String idCorrelativo = map.get("idCorrelativo").toString();
		String index = this.getIndex(list, idCorrelativo);
		
		if(isValidoCampaniasGratuitas(criteria,list,index)){//es registro valido cuando no se encuntra en la lista o se encuentra como eliminado						
			map.put("campInicialCampGratuitas", f.getCampInicialCampGratuitas());
			map.put("campFinalCampGratuitas", f.getCampFinalCampGratuitas());
			map.put("codigoBeneficiario", f.getCodigoBeneficiario());
			
			if(f.getCodigoBeneficiario().equals("T"))
				map.put("descripcionBeneficiario", "Todos");
			
			if(f.getCodigoBeneficiario().equals("L"))
				map.put("descripcionBeneficiario", "Lideres");
			
			if (f.getCodigoBeneficiario().equals("N"))
				map.put("descripcionBeneficiario", "Nuevas");
			
			if(f.getCodigoBeneficiario().equals("P"))
				map.put("descripcionBeneficiario", "Migración Póliza");
			
			
			/* INI SA PER-SiCC-2012-0786 */
			map.put("numeroPeriodosEvaluar", f.getNumeroPeriodosEvaluar());
			map.put("numeroMaximoDescuento", f.getNumeroMaximoDescuento());
			/* FIN SA PER-SiCC-2012-0786 */
			
			map.put("indicadorModificacion", Constants.NUMERO_UNO);
			
			//Guardamos en la lista.
			this.sgrPolizaCampaniaGratuitasList.set(NumberUtils.toInt(index)-1, map);
		}		
		else{
			f.setIndicadorNuevoDscto(Constants.NUMERO_UNO);
			f.setIndicadorNuevoKit(Constants.NUMERO_UNO);
			f.setIndicadorNuevoCampaniasGratuitas(Constants.NUMERO_CERO);
			this.addWarn("", this.getResourceMessage("mantenimientoSGRPolizaForm.existe.campGratuita.registro"));
			return;		
		}				
			
		f.setIndicadorNuevoDscto(Constants.NUMERO_UNO);
		f.setIndicadorNuevoKit(Constants.NUMERO_UNO);
		f.setIndicadorNuevoCampaniasGratuitas(Constants.NUMERO_UNO);
		//limpiando 
        f.setCampInicialCampGratuitas("");
		f.setCampFinalCampGratuitas("");
		f.setCodigoBeneficiario("");
		
		/* INI SA PER-SiCC-2012-0786 */
		f.setNumeroPeriodosEvaluar("");
		f.setNumeroMaximoDescuento("");
		/* FIN SA PER-SiCC-2012-0786 */
	  
	   } catch (Exception e) {
		  this.addError("Error: ", this.obtieneMensajeErrorException(e));
	  }
	   
	}
	
	/**
	 * @param actionEvent
	 */
	public void eliminarRegistroCampGrat(ActionEvent actionEvent) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'delete Campañas Gratuitas' method");
		}

		MantenimientoSGRPolizaForm f = (MantenimientoSGRPolizaForm) this.formMantenimiento;		
		f.setIndicadorNuevoKit(Constants.NRO_UNO);
		f.setIndicadorNuevoDscto(Constants.NRO_UNO);
		f.setIndicadorNuevoEstatus(Constants.NRO_UNO);
		f.setIndicadorNuevoCampaniasGratuitas(Constants.NRO_UNO);
		f.setTabSeleccion(Constants.SGR_TAB_POLIZA_CAMPANIAS_GRATUITAS);		

        try{
        	
        	if(this.beanRegistroSeleccionadoCampGratuita == null){
				this.addError("Error: ", this.getResourceMessage("errors.select.item"));
				return; 
				}
        	
			Map obj = (Map) this.beanRegistroSeleccionadoCampGratuita; 
			String campInicialCampGratuitasAux = (String)obj.get("campInicialCampGratuitas");
			String campFinalCampGratuitasAux = (String)obj.get("campFinalCampGratuitas");
			
			if (obj != null) {																			
				for (int i = 0; i < this.sgrPolizaCampaniaGratuitasList.size(); i++) {
				Map lista = (Map) this.sgrPolizaCampaniaGratuitasList.get(i);
				String campInicialCampGratuitasAuxLista = (String)lista.get("campInicialCampGratuitas");
				String campFinalCampGratuitasAuxLista = (String)lista.get("campFinalCampGratuitas");
				
					if((StringUtils.equals(campInicialCampGratuitasAux, campInicialCampGratuitasAuxLista))&&(StringUtils.equals(campFinalCampGratuitasAux, campFinalCampGratuitasAuxLista))){
						this.sgrPolizaCampaniaGratuitasList.remove(obj);
						obj.put("indicadorAccion", Constants.NUMERO_DOS);
						this.sgrPolizaCampaniaGratuitasList.add(obj);	
						this.beanRegistroSeleccionadoCampGratuita = null;
					}
				
				}	
			}else{
				this.addWarn("Warning: ", this.getResourceMessage("errors.select.item"));	
			}
			
		 } catch (Exception e) {
					this.addError("Error: ", this.obtieneMensajeErrorException(e));
		 }		
        
        
	}	

	/**
	 * @return the sgrPolizaList
	 */
	public List getSgrPolizaList() {
		return sgrPolizaList;
	}

	/**
	 * @param sgrPolizaList the sgrPolizaList to set
	 */
	public void setSgrPolizaList(List sgrPolizaList) {
		this.sgrPolizaList = sgrPolizaList;
	}

	/**
	 * @return the sgrEstatusList
	 */
	public List getSgrEstatusList() {
		return sgrEstatusList;
	}

	/**
	 * @param sgrEstatusList the sgrEstatusList to set
	 */
	public void setSgrEstatusList(List sgrEstatusList) {
		this.sgrEstatusList = sgrEstatusList;
	}

	/**
	 * @return the sgrAseguradoraList
	 */
	public List getSgrAseguradoraList() {
		return sgrAseguradoraList;
	}

	/**
	 * @param sgrAseguradoraList the sgrAseguradoraList to set
	 */
	public void setSgrAseguradoraList(List sgrAseguradoraList) {
		this.sgrAseguradoraList = sgrAseguradoraList;
	}

	/**
	 * @return the sgrPolizaCampaniaGratuitasList
	 */
	public List getSgrPolizaCampaniaGratuitasList() {
		return sgrPolizaCampaniaGratuitasList;
	}

	/**
	 * @param sgrPolizaCampaniaGratuitasList the sgrPolizaCampaniaGratuitasList to set
	 */
	public void setSgrPolizaCampaniaGratuitasList(
			List sgrPolizaCampaniaGratuitasList) {
		this.sgrPolizaCampaniaGratuitasList = sgrPolizaCampaniaGratuitasList;
	}

	/**
	 * @return the sgrPolizaEstatusList
	 */
	public List getSgrPolizaEstatusList() {
		return sgrPolizaEstatusList;
	}

	/**
	 * @param sgrPolizaEstatusList the sgrPolizaEstatusList to set
	 */
	public void setSgrPolizaEstatusList(List sgrPolizaEstatusList) {
		this.sgrPolizaEstatusList = sgrPolizaEstatusList;
	}

	/**
	 * @return the sgrPolizaVigenciaList
	 */
	public List getSgrPolizaVigenciaList() {
		return sgrPolizaVigenciaList;
	}

	/**
	 * @param sgrPolizaVigenciaList the sgrPolizaVigenciaList to set
	 */
	public void setSgrPolizaVigenciaList(List sgrPolizaVigenciaList) {
		this.sgrPolizaVigenciaList = sgrPolizaVigenciaList;
	}

	/**
	 * @return the sgrPolizaKitList
	 */
	public List getSgrPolizaKitList() {
		return sgrPolizaKitList;
	}

	/**
	 * @param sgrPolizaKitList the sgrPolizaKitList to set
	 */
	public void setSgrPolizaKitList(List sgrPolizaKitList) {
		this.sgrPolizaKitList = sgrPolizaKitList;
	}

	/**
	 * @return the sgrPolizaDsctoList
	 */
	public List getSgrPolizaDsctoList() {
		return sgrPolizaDsctoList;
	}

	/**
	 * @param sgrPolizaDsctoList the sgrPolizaDsctoList to set
	 */
	public void setSgrPolizaDsctoList(List sgrPolizaDsctoList) {
		this.sgrPolizaDsctoList = sgrPolizaDsctoList;
	}

	/**
	 * @return the campanhaActual
	 */
	public String getCampanhaActual() {
		return campanhaActual;
	}

	/**
	 * @param campanhaActual the campanhaActual to set
	 */
	public void setCampanhaActual(String campanhaActual) {
		this.campanhaActual = campanhaActual;
	}

	/**
	 * @return the codigoPolizaSession
	 */
	public String getCodigoPolizaSession() {
		return codigoPolizaSession;
	}

	/**
	 * @param codigoPolizaSession the codigoPolizaSession to set
	 */
	public void setCodigoPolizaSession(String codigoPolizaSession) {
		this.codigoPolizaSession = codigoPolizaSession;
	}

	/**
	 * @return the indicadorHabilitaPrecio
	 */
	public boolean isIndicadorHabilitaPrecio() {
		return indicadorHabilitaPrecio;
	}

	/**
	 * @param indicadorHabilitaPrecio the indicadorHabilitaPrecio to set
	 */
	public void setIndicadorHabilitaPrecio(boolean indicadorHabilitaPrecio) {
		this.indicadorHabilitaPrecio = indicadorHabilitaPrecio;
	}

	/**
	 * @return the dataModelCampaniaGratuita
	 */
	public DataTableModel getDataModelCampaniaGratuita() {
		return dataModelCampaniaGratuita;
	}

	/**
	 * @param dataModelCampaniaGratuita the dataModelCampaniaGratuita to set
	 */
	public void setDataModelCampaniaGratuita(
			DataTableModel dataModelCampaniaGratuita) {
		this.dataModelCampaniaGratuita = dataModelCampaniaGratuita;
	}

	/**
	 * @return the dataModelEstatus
	 */
	public DataTableModel getDataModelEstatus() {
		return dataModelEstatus;
	}

	/**
	 * @param dataModelEstatus the dataModelEstatus to set
	 */
	public void setDataModelEstatus(DataTableModel dataModelEstatus) {
		this.dataModelEstatus = dataModelEstatus;
	}

	/**
	 * @return the dataModelKit
	 */
	public DataTableModel getDataModelKit() {
		return dataModelKit;
	}

	/**
	 * @param dataModelKit the dataModelKit to set
	 */
	public void setDataModelKit(DataTableModel dataModelKit) {
		this.dataModelKit = dataModelKit;
	}

	/**
	 * @return the beanRegistroSeleccionadoKit
	 */
	public Object getBeanRegistroSeleccionadoKit() {
		return beanRegistroSeleccionadoKit;
	}

	/**
	 * @param beanRegistroSeleccionadoKit the beanRegistroSeleccionadoKit to set
	 */
	public void setBeanRegistroSeleccionadoKit(Object beanRegistroSeleccionadoKit) {
		this.beanRegistroSeleccionadoKit = beanRegistroSeleccionadoKit;
	}

	/**
	 * @return the beanRegistroSeleccionadoCampGratuita
	 */
	public Object getBeanRegistroSeleccionadoCampGratuita() {
		return beanRegistroSeleccionadoCampGratuita;
	}

	/**
	 * @param beanRegistroSeleccionadoCampGratuita the beanRegistroSeleccionadoCampGratuita to set
	 */
	public void setBeanRegistroSeleccionadoCampGratuita(
			Object beanRegistroSeleccionadoCampGratuita) {
		this.beanRegistroSeleccionadoCampGratuita = beanRegistroSeleccionadoCampGratuita;
	}

	/**
	 * @return the beanRegistroSeleccionadoEstatus
	 */
	public Object getBeanRegistroSeleccionadoEstatus() {
		return beanRegistroSeleccionadoEstatus;
	}

	/**
	 * @param beanRegistroSeleccionadoEstatus the beanRegistroSeleccionadoEstatus to set
	 */
	public void setBeanRegistroSeleccionadoEstatus(
			Object beanRegistroSeleccionadoEstatus) {
		this.beanRegistroSeleccionadoEstatus = beanRegistroSeleccionadoEstatus;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		

		log.debug("saveAttributes");
        Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		MantenimientoSGRPolizaForm f = (MantenimientoSGRPolizaForm) this.formMantenimiento;
		this.ocultarPrecios  = false;
			
			MantenimientoSGRGenericoService service = (MantenimientoSGRGenericoService)getBean("spusicc.mantenimientoSGRGenericoService");
			f.setIndicadorNuevoKit(Constants.NRO_UNO);
	        f.setIndicadorNuevoDscto(Constants.NRO_UNO);
	        f.setIndicadorNuevoVigencia(Constants.NRO_UNO);				
			f.setIndicadorNuevoCampaniasGratuitas(Constants.NRO_UNO);
			
			Map map = BeanUtils.describe(f);
			map.put("login", usuario.getLogin());
			
			if (f.isNewRecord()) {
				//service.updateBancos(map);
				//verificar que no haya una poliza activa SIEMPRE Y CUANDO SE REGISTRE ACTIVA, con esto se permite
				//registrar polizas inactivas
			  if(Constants.NRO_UNO.equals(f.getIndicadorActivo())){	
				Map criteria = new HashMap();
				criteria.put("indicadorActivo", Constants.NRO_UNO);
				List resultado = service.getPoliza(criteria);
				if(resultado.size()>0){
					this.addWarn("", this.getResourceMessage("mantenimientoSGRPolizaForm.poliza.activa"));
					return false;					
				}
			  }
				//se verificara que haya dado en la pestanaha de vigencias
				List listVigencia= sgrPolizaVigenciaList;
				List listKit = sgrPolizaKitList;
				List listDescuento = sgrPolizaDsctoList;
				List listEstatus = sgrPolizaEstatusList;
				List listCampaniaGratuitas = sgrPolizaCampaniaGratuitasList;	
				
				/*if(!validarVigencias(listVigencia) ){
					messages.add(ActionErrors.GLOBAL_MESSAGE,new ActionMessage("mantenimientoSGRPolizaForm.poliza.vigencia.vacia"));
			        saveErrors(request, messages);	        
					return false;				
				}*/
				
				
				if(!validarEstatus(listEstatus) ){
			        this.addWarn("", this.getResourceMessage("mantenimientoSGRPolizaForm.poliza.estatus.vacia"));
					return false;				
				}
				
				log.debug("listKit " + listKit.size() + " listDesc " + listDescuento.size() + " listEstatus " + listEstatus.size());
				map.put("listVigencia", listVigencia);
				map.put("listKit", listKit);
				map.put("listDescuento", listDescuento);
				map.put("listEstatus", listEstatus);
				map.put("listCampaniaGratuitas", listCampaniaGratuitas);
				map.put("indicadorAccionPoliza", Constants.NRO_CERO);
				
				service.savePoliza(map);//este inserta tb en la tabla de parametros
			
//				this.addInfo("", this.getResourceMessage("mantenimientoSGRPoliza.created"));
				
			}else{
				
				//actualizacion			
					//if(isValidoPoliza(map)){

				        //si la poliza es inactiva permitir modificar,SOLO S EVALIDA SI ES ACTIVA
						if(Constants.NRO_UNO.equals(f.getIndicadorActivo())){
				
							//verificar que no haya una poliza activa distinta a esta poliza
							Map criteria = new HashMap();
							criteria.put("indicadorActivo", Constants.NRO_UNO);
							criteria.put("indicadorActivoPoliza", Constants.NRO_UNO);
							criteria.put("codPoliza", f.getCodigoPoliza());
							log.debug("validando si hay otra activa");
							List resultado = service.getPoliza(criteria);
							if(resultado.size()>0){
								this.ocultarPrecios  = true;
								this.addWarn("", this.getResourceMessage("mantenimientoSGRPolizaForm.poliza.activa"));
								return false;					
							}	
							
						}
						
						//se verificara que haya dado en la pestanaha de vigencias
						List listVigencia= this.sgrPolizaVigenciaList;
						List listKit = this.sgrPolizaKitList;
						List listDescuento = this.sgrPolizaDsctoList;
						List listEstatus = this.sgrPolizaEstatusList;
						List listCampaniaGratuitas = this.sgrPolizaCampaniaGratuitasList;	
						
						/*
						if(listVigencia.size()==0){
							messages.add(ActionErrors.GLOBAL_MESSAGE,new ActionMessage("mantenimientoSGRPolizaForm.poliza.vigencia.vacia"));
							
							saveErrors(request, messages);	
							
							return false;				
						}*/
						
						if(!validarEstatus(listEstatus) ){
					        this.addWarn("", this.getResourceMessage("mantenimientoSGRPolizaForm.poliza.estatus.activa"));
							return false;				
						}
						
						map.put("listVigencia", listVigencia);
						map.put("listKit", listKit);
						map.put("listDescuento", listDescuento);
						map.put("listEstatus", listEstatus);
						map.put("listCampaniaGratuitas", listCampaniaGratuitas);
						map.put("indicadorAccionPoliza", Constants.NRO_UNO);
						
						service.savePoliza(map);//este inserta tb en la tabla de parametros	
		
//						this.addInfo("", this.getResourceMessage("mantenimientoSGRPoliza.updated"));
			}
			//List resultado = service.getBancos(map);
			/*List resultado = service.getPoliza(map);
			
			session.setAttribute(Constants.SGR_MANT_POLIZA_LIST, resultado);
			session.setAttribute(Constants.SGR_ASEGURADORA_LIST, service.getPoliza(null));
*/
		
		limpiar(f);
		
		return true;

	
	}
	
	

	/**
	 * @return the ocultarPrecios
	 */
	public Boolean getOcultarPrecios() {
		return ocultarPrecios;
	}

	/**
	 * @param ocultarPrecios the ocultarPrecios to set
	 */
	public void setOcultarPrecios(Boolean ocultarPrecios) {
		this.ocultarPrecios = ocultarPrecios;
	}
}