package biz.belcorp.ssicc.web.spusicc.reclamos.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
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
import biz.belcorp.ssicc.dao.spusicc.reclamos.model.BoletaRecojoCabecera;
import biz.belcorp.ssicc.dao.spusicc.reclamos.model.BoletaRecojoDetalle;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECDigitacionBoletasRecojoService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECDigitacionCDRService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.reclamos.form.MantenimientoRECBusquedaProductosForm;
import biz.belcorp.ssicc.web.spusicc.reclamos.form.MantenimientoRECDigitacionBoletasRecojoForm;
import biz.belcorp.ssicc.web.spusicc.reclamos.form.MantenimientoRECDigitacionBoletasRecojoSearchForm;


@SuppressWarnings({ "unchecked", "rawtypes" })
@ManagedBean
@SessionScoped
public class MantenimientoRECDigitacionBoletasRecojoSearchAction extends BaseMantenimientoSearchAbstractAction{

	
	private static final long serialVersionUID = -985342226275827195L;
	
	private List siccMarcaList;
	private List siccCanalList;
	private List siccEstadosBorecList;
	private List siccResultadosBorecList;
	private List siccMotivosNorecojoBorecList;
	private List siccRegionList;
	private LabelValue[] siccZonaList={};
	private List recDigitacionBoletasRecojoCabeceraList;
	private Map recDigitacionBoletasRecojoDiscList;
	private List recDigitacionBoletasRecojoDetalleElimList;
	
	private List recDigitacionBoletasRecojoDetalleList;	
	private List recDigitacionBoletasRecojoDetalleDiscList;
	
	private DataTableModel detalleBoletasRecojoTableModel;
	private Object beanRegistroDetalleBoletasRecojo;
	
	private DataTableModel detalleDiscrepanteDataModel;
	private Object beanRegistroDetalleDiscrepante;	
	
	private boolean mostrarPopupCliente;
	private static final String POPUP_CLIENTE = "POPUP_CLIENTE";
	
	@ManagedProperty(value="#{mantenimientoRECBusquedaProductosAction}")
	private MantenimientoRECBusquedaProductosAction mantenimientoRECBusquedaProductosAction;
	
	private String numeroRecojo;
	
	//flag que oculta botones insertar,eliminar,modificar de discrepantes
	private Boolean mostrarBotones = true;
	
	private Boolean mostrarBotonSalirP = false; 
	
	//flag acitva/desactiva campo unidades Eliminadas
	private Boolean deshabilitaCampoEliminadas;
	
	@Override
	protected String getSalirForward() {		
		return "mantenimientoRECDigitacionBoletasRecojoList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {		
		return "mantenimientoRECDigitacionBoletasRecojoForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoRECDigitacionBoletasRecojoSearchForm searchForm = new MantenimientoRECDigitacionBoletasRecojoSearchForm();
		return searchForm;
	}	

	@Override
	protected List setFindAttributes() throws Exception {		
		MantenimientoRECDigitacionBoletasRecojoSearchForm f = (MantenimientoRECDigitacionBoletasRecojoSearchForm) this.formBusqueda;		
		
		Map params = new HashMap();
		params.put("codigoPais", f.getCodigoPais());
		params.put("numeroBoleta", f.getNumeroBoleta());
		params.put("codigoCliente", f.getCodigoCliente());
		params.put("codigoPeriodo", f.getCodigoPeriodo());		
		params.put("regionList",(f.getRegionList() == null) ? new ArrayList(): Arrays.asList(f.getRegionList()));
		params.put("zonaList",(f.getZonaList() == null) ? new ArrayList(): Arrays.asList(f.getZonaList()));
		
		params.put("estadoList",(f.getEstadoList() == null) ? new ArrayList(): Arrays.asList(f.getEstadoList()));;
		params.put("resultadoList",(f.getResultadoList() == null) ? new ArrayList(): Arrays.asList(f.getResultadoList()));
		
		MantenimientoRECDigitacionBoletasRecojoService service = (MantenimientoRECDigitacionBoletasRecojoService) getBean("spusicc.mantenimientoRECDigitacionBoletasRecojoService");	
		List boletaRecojoCabeceraList = new ArrayList();
		boletaRecojoCabeceraList = 	service.getBoletasRecojoCabeceraList(params);
		this.recDigitacionBoletasRecojoCabeceraList=boletaRecojoCabeceraList;		
		return boletaRecojoCabeceraList;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		MantenimientoRECDigitacionBoletasRecojoForm f = (MantenimientoRECDigitacionBoletasRecojoForm) this.formMantenimiento;
		MantenimientoRECDigitacionBoletasRecojoService service = (MantenimientoRECDigitacionBoletasRecojoService) getBean("spusicc.mantenimientoRECDigitacionBoletasRecojoService");		
		Usuario usuario =this.mPantallaPrincipalBean.getCurrentUser();		
		List detalList = this.recDigitacionBoletasRecojoDetalleList;
	
		if(f.getFechaEmision1Date()!=null)
			f.setFechaEmision1(DateUtil.convertDateToString(f.getFechaEmision1Date()));
		
		if(f.getFechaEmision2Date()!=null)
			f.setFechaEmision2(DateUtil.convertDateToString(f.getFechaEmision2Date()));
		
		f.setFechaRecojo1(DateUtil.convertDateToString(f.getFechaRecojo1Date()));
		f.setFechaRecojo2(DateUtil.convertDateToString(f.getFechaRecojo2Date()));
		
		List detalElimList = this.recDigitacionBoletasRecojoDetalleElimList;		
		Map detalDiscList = this.recDigitacionBoletasRecojoDiscList;		
		
		try {			
		    BoletaRecojoCabecera boletaRecojoCabecera = new BoletaRecojoCabecera();
			BeanUtils.copyProperties(boletaRecojoCabecera, f);
			log.debug("Entidad : "+boletaRecojoCabecera);
			
			if (!f.isNewRecord()){
				boletaRecojoCabecera.setNumeroRecojoInicio(this.numeroRecojo);
				service.updateBoletaRecojoCabeceraDetalle(boletaRecojoCabecera,detalList,detalElimList,detalDiscList,usuario);
			}
						
			} catch (Exception e) {											
				this.addError("ERROR: ", this.getResourceMessage("mantenimientoRECDigitacionBoletasRecojoForm.msg.codigoExiste"));
				return false;
			}
		
		this.salirGrabarPantallaPadre = true;
		return true;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoRECDigitacionBoletasRecojoForm f = new MantenimientoRECDigitacionBoletasRecojoForm();
		MantenimientoRECDigitacionBoletasRecojoService service = (MantenimientoRECDigitacionBoletasRecojoService)  getBean("spusicc.mantenimientoRECDigitacionBoletasRecojoService");	
	
		if (!this.accion.equals(this.ACCION_NUEVO)) {
			BoletaRecojoCabecera boletaRecojoCabecera= (BoletaRecojoCabecera)this.beanRegistroSeleccionado;			
			String codigo=boletaRecojoCabecera.getCodigoCabecera();			
			String codigoPais=pais.getCodigo();
			if (StringUtils.isNotBlank(codigo) && StringUtils.isNotBlank(codigoPais)) {
				if (log.isDebugEnabled()) {
					log.debug("Id seleccionado de la lista: " + codigo + " "+ codigoPais);
				}				
				
				this.numeroRecojo = boletaRecojoCabecera.getNumeroRecojo();
					
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				String fechaActual = sdf.format(new Date(System.currentTimeMillis()));
					
				if (boletaRecojoCabecera.getFechaRecojo1() == null) {
					boletaRecojoCabecera.setFechaRecojo1(fechaActual);					
				}
					
				if (boletaRecojoCabecera.getFechaRecojo2() == null) {
					boletaRecojoCabecera.setFechaRecojo2(fechaActual);
				}
				
				BeanUtils.copyProperties(f, boletaRecojoCabecera);
				f.setFechaRecojo1Date(DateUtil.convertStringToDate(f.getFechaRecojo1()));
				f.setFechaRecojo2Date(DateUtil.convertStringToDate(f.getFechaRecojo2()));
					
				List boletaRecojoDetalleList= (List)service.getBoletaRecojoDetalleList(boletaRecojoCabecera);
				log.debug("detalList : "+boletaRecojoDetalleList.size());
				Map dicrepantesList = new HashMap();
				 	
				int boletaRecojoSize = boletaRecojoDetalleList.size();
				String[] listaUnidadesEliminadas = new String[boletaRecojoSize];
				String[] listaUnidadesRecogidas  = new String[boletaRecojoSize];
				String[] listaUnidadesReclamadas  = new String[boletaRecojoSize];
				
				for (int i = 0; i < boletaRecojoSize; i++){
					BoletaRecojoDetalle boletaRecojoDetalle= (BoletaRecojoDetalle)boletaRecojoDetalleList.get(i);
					listaUnidadesEliminadas[i] = boletaRecojoDetalle.getUnidadesEliminadas().toString();
					listaUnidadesRecogidas[i] = boletaRecojoDetalle.getUnidadesRecogidas().toString();
					listaUnidadesReclamadas[i] = boletaRecojoDetalle.getUnidadesReclamadas().toString();
					
					List boletaRecojoDetalleDiscrepanteList =service.getBoletaRecojoDetalleDiscrepanteList(boletaRecojoDetalle);
					log.debug("      detalListDiscrepante : "+boletaRecojoDetalleDiscrepanteList.size());
					dicrepantesList.put(boletaRecojoDetalle.getCodigoDetalle(),boletaRecojoDetalleDiscrepanteList);	
				} 			
				
				f.setListaUnidadesEliminadas(listaUnidadesEliminadas);
				f.setListaUnidadesRecogidas(listaUnidadesRecogidas);
				f.setListaUnidadesReclamadas(listaUnidadesReclamadas);
				this.recDigitacionBoletasRecojoDetalleList=boletaRecojoDetalleList;
				this.detalleBoletasRecojoTableModel=new DataTableModel(this.recDigitacionBoletasRecojoDetalleList);
			
				this.recDigitacionBoletasRecojoDiscList=dicrepantesList;
				initDetal(f);
				f.setNewRecord(false);	
				
				//Deshabilita campo Unidades Eliminadas
				this.deshabilitaCampoEliminadas = true;
			}				
		}
		return f;		
	}	

	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarBotonNuevo=false;
		this.mostrarBotonConsultar=false;
		this.mostrarBotonSalir=false;
		this.mostrarBotonSave=false;
		this.mostrarBotonEliminar=false;
		
		Pais pais =this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoRECDigitacionBoletasRecojoSearchForm f = (MantenimientoRECDigitacionBoletasRecojoSearchForm) this.formBusqueda;
		f.setCodigoPais(pais.getCodigo());
				        
	    Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("estadoCampanha", Constants.NUMERO_CERO); // Indica Campanha Activa
		criteria.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente
			
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteria);

		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		loadCombos();
		f.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		f.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
		f.setNumeroBoleta(null);
		f.setCodigoCliente(null);
		f.setRegionList(null);	    	
	    f.setEstadoList(null);
	    f.setResultadoList(null);	
	    this.recDigitacionBoletasRecojoDetalleElimList=new ArrayList();
	}
	
	//leer Combos
	private void loadCombos()throws Exception {
		log.debug("Loading Combos.");
		Pais pais =this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		this.siccMarcaList=service.getMarcas();
		this.siccCanalList=service.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());
		this.siccEstadosBorecList=service.getEstadosBoletasRecojo(pais.getCodigo());
		this.siccResultadosBorecList=service.getResultadosBoletasRecojo(pais.getCodigo());
		this.siccMotivosNorecojoBorecList=service.getMotivosNoRecojoBoletasRecojo(pais.getCodigo());		
				
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		this.siccRegionList=reporteService.getListaGenerico("getRegionesByPais",criteriaOperacion);				
	}
	
	public void loadZonas(ValueChangeEvent val) {
		log.debug(">>loadZonas ");
		log.debug("val: " + val.getNewValue().toString());	
		
		MantenimientoRECDigitacionBoletasRecojoSearchForm f = (MantenimientoRECDigitacionBoletasRecojoSearchForm) this.formBusqueda;
		String[] regiones = (String[]) val.getNewValue();
		if (!val.equals(null) && regiones.length > 0) {
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			this.setSiccZonaList(aSvc.getZonasMultipleByPaisMarcaCanalRegion(
					f.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT,Constants.CODIGO_CANAL_DEFAULT, regiones,
					Constants.FORMATO_TOTAL));			
			f.setZonaList(null);
		} else {
			this.siccZonaList = null;
			f.setZonaList(null);
		}
	}
	
	/**
	 * INicializa los valores de la Boleta de recojo para mostralos en el xhtml
	 * @param f
	 * @throws Exception
	 */
	private void initDetal(MantenimientoRECDigitacionBoletasRecojoForm f) throws Exception{
		MantenimientoRECDigitacionCDRService service = (MantenimientoRECDigitacionCDRService) getBean("spusicc.mantenimientoRECDigitacionCDRService");

		String indicadorPGRBR = service.getIndicadorPGRBR();
		if (indicadorPGRBR == null) 
			indicadorPGRBR = "0";		
	
		f.setIndicadorPGRBR(indicadorPGRBR);
			
		//		CABECERA
		f.setViewCabecera(true);
		f.setEditCabecera(false);
		f.setViewEditRecojo1(false);
		f.setViewEditRecojo2(false);
		f.setEditDiscrepante(false);
		f.setEditResultado(true);		
		
		String numeroRecojo = f.getNumeroRecojo();
		String estado = f.getCodigoEstado();
		String resultado = f.getCodigoResultado();
		String motivoRecojo = f.getMotivoNoRecojo1();
		String fechaEmision2= f.getFechaEmision2();
		String fechaEmision1=f.getFechaEmision1();
		if(!StringUtils.isEmpty(fechaEmision1))
			f.setFechaEmision1Date(DateUtil.convertStringToDate(f.getFechaEmision1()));
		if(!StringUtils.isEmpty(fechaEmision2))
			f.setFechaEmision2Date(DateUtil.convertStringToDate(f.getFechaEmision2()));
		
		if (StringUtils.equals(numeroRecojo, "2") && StringUtils.equals(estado, Constants.REC_BOREC_GESTION)
				&& StringUtils.equals(resultado, Constants.REC_BOREC_NO_EXITOSA) && !StringUtils.isEmpty(motivoRecojo)
				&& StringUtils.isEmpty(fechaEmision2))
			f.setNumeroRecojo("1");
		
		if ((!StringUtils.equals(f.getCodigoEstado(), Constants.REC_BOREC_CERRADA)) || 
			(f.getCodigoEstado().equals(Constants.REC_BOREC_CERRADA) && 
			StringUtils.equals(f.getCodigoResultado(), Constants.REC_BOREC_DISCREPANTE)&&
			StringUtils.equals(f.getIndicadorOCSProcesado(), "F"))) {			
			f.setEditCabecera(true);
			if (StringUtils.equals(f.getNumeroRecojo(), "1")) 
				f.setViewEditRecojo1(true);
			else 
				f.setViewEditRecojo2(true);
		}		
		if (StringUtils.equals(f.getCodigoEstado(), Constants.REC_BOREC_CERRADA)) 
			f.setEditResultado(false);
		
		//		DETALLE 
		f.setViewDetalle(true);
		f.setEditDetalle(true);
				
		//		DISCREPANTE
		f.setViewDiscrepante(false);
		
		//		DATOS
		f.setCodigoVenta(null);
		f.setDescripcionProducto(null);
		f.setUnidadesReclamadas(null);
		f.setUnidadesRecogidas(null);
		f.setUnidadesEliminadas(null);
		f.setCodigoProductoDiscrepante(null);
		f.setDescripcionProductoDisc(null);	
		f.setValorPedido(null);
	}
	
	@Override
	public String setValidarConfirmar(String accion) {
		MantenimientoRECDigitacionBoletasRecojoForm f = (MantenimientoRECDigitacionBoletasRecojoForm) this.formMantenimiento;
		
		if(accion.equals("INSERTAR_DETALLE") ){
			if(StringUtils.isEmpty(f.getCodigoProductoDiscrepante()))
				return this.getResourceMessage("mantenimientoRECDigitacionBoletasRecojoForm.error.notDiscrepantes");
			if(StringUtils.isEmpty(f.getUnidadesRecogidas())|| (Integer.parseInt(f.getUnidadesRecogidas()))<=0)
				return this.getResourceMessage("mantenimientoRECDigitacionBoletasRecojoForm.error.notUnidades");
		}
		
		if(accion.equals("ELIMINAR_DETALLE") ){
			if(this.beanRegistroDetalleDiscrepante==null)
				return this.getResourceMessage("errors.select.item");
		}		
		
		if(accion.equals("GUARDAR_DETALLE") ){
			int unidadesRecogidas = StringUtils.isBlank(f.getUnidadesRecogidas())? 0 :Integer.parseInt(f.getUnidadesRecogidas());
			if(StringUtils.isBlank(f.getCodigoProductoDiscrepante()))
				return this.getResourceMessage("mantenimientoRECDigitacionBoletasRecojoForm.error.notDiscrepantes");
			
			if(StringUtils.isBlank(f.getCodigoProductoDiscrepante()) || unidadesRecogidas <= 0)
				return this.getResourceMessage("mantenimientoRECDigitacionBoletasRecojoForm.error.notUnidades");			
		}
		
		return null;
	}
	
	public void showDiscrepante(ActionEvent actionEvent) throws Exception 
	{
		try {
			
			if(this.beanRegistroDetalleBoletasRecojo == null)
			{
				this.setMensajeAlertaDefault(this.getResourceMessage("errors.select.item"));
				String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
				this.getRequestContext().execute(ventanaConfirmar);
				return;
			}
			else
			{		
				MantenimientoRECDigitacionBoletasRecojoForm f = (MantenimientoRECDigitacionBoletasRecojoForm) this.formMantenimiento;
				if(StringUtils.equals(f.getCodigoResultado(), "CD")){
					f.setViewDiscrepante(true);	
								
					List detalList=this.recDigitacionBoletasRecojoDetalleList;	
					
					BoletaRecojoDetalle boletaRecojoDetalle=(BoletaRecojoDetalle)this.beanRegistroDetalleBoletasRecojo;	
					
					if(StringUtils.isNotBlank(boletaRecojoDetalle.getOidPedido()))
						f.setValorPedido(boletaRecojoDetalle.getOidPedido());
					
					Map dicrepantesList = new HashMap();
					dicrepantesList=this.recDigitacionBoletasRecojoDiscList;			
					
					List dicrepanteActualList = (List)dicrepantesList.get(boletaRecojoDetalle.getCodigoDetalle());
					int unidadesEliminadas=Integer.parseInt(boletaRecojoDetalle.getUnidadesEliminadas());
					int unidadesRecogidas = 0;
					for (int j = 0; j < dicrepanteActualList.size(); j++) {
						BoletaRecojoDetalle boletarecojoDiscrepante = (BoletaRecojoDetalle)dicrepanteActualList.get(j);
						unidadesRecogidas = unidadesRecogidas + Integer.parseInt(boletarecojoDiscrepante.getUnidadesRecogidas());
					}
					
					unidadesRecogidas =unidadesRecogidas-unidadesEliminadas;
					
					f.setUnidadesRecogidas(""+(Integer.parseInt(boletaRecojoDetalle.getUnidadesReclamadas())-unidadesRecogidas));
					this.recDigitacionBoletasRecojoDetalleDiscList=dicrepanteActualList;			
					setFormDiscrepante(f, boletaRecojoDetalle);	
					this.detalleDiscrepanteDataModel=new DataTableModel(this.recDigitacionBoletasRecojoDetalleDiscList);
					
					this.mostrarBotonSalirP = true;
				}
			}
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}		
	}
	
	public void insertDiscrepante(ActionEvent actionEvent){	
		try {		
		
		MantenimientoRECDigitacionBoletasRecojoForm f = (MantenimientoRECDigitacionBoletasRecojoForm) this.formMantenimiento;	
		String codigoProductoDisc =  f.getCodigoProductoDiscrepante();
		boolean existeProd = true;
		
		if (existeProd){
			BoletaRecojoDetalle newBoletaRecojoDiscrepante=new BoletaRecojoDetalle();
			newBoletaRecojoDiscrepante.setCodigoVentaDiscrepante(f.getCodigoVentaDiscrepante().trim());
			newBoletaRecojoDiscrepante.setDescripcionProductoDisc(f.getDescripcionProductoDisc().trim());
			newBoletaRecojoDiscrepante.setPeriodoDiscrepante(f.getCodigoPeriodoReferenciaDiscrepante().trim());
			newBoletaRecojoDiscrepante.setCodigoProductoDiscrepante(codigoProductoDisc.trim());
			newBoletaRecojoDiscrepante.setUnidadesRecogidas(f.getUnidadesRecogidas().trim());
			newBoletaRecojoDiscrepante.setPrecioDiscrepante(new Float(f.getPrecioDiscrepante().trim()));
			
			newBoletaRecojoDiscrepante.setIndicadorDiscrepante("1");
			List boletaRecojoDiscrepanteList=this.recDigitacionBoletasRecojoDetalleDiscList;
			
			log.debug("Boleta de Recojo Detallle List size "+boletaRecojoDiscrepanteList.size());
			boletaRecojoDiscrepanteList.add(newBoletaRecojoDiscrepante);
			this.recDigitacionBoletasRecojoDetalleDiscList=boletaRecojoDiscrepanteList;
			
			List boletaRecojoDetalleList=this.recDigitacionBoletasRecojoDetalleList;
			
			for (int i = 0; i < boletaRecojoDetalleList.size(); i++) {
				BoletaRecojoDetalle boletaRecojoDetalle = (BoletaRecojoDetalle)boletaRecojoDetalleList.get(i);
				int unidadesEliminadas=Integer.parseInt(boletaRecojoDetalle.getUnidadesEliminadas());
				if (StringUtils.equals(boletaRecojoDetalle.getCodigoDetalle(), f.getCodigoDetalle())){
					int unidadesRecogidas = 0;
					for (int j = 0; j < boletaRecojoDiscrepanteList.size(); j++) {
						BoletaRecojoDetalle boletarecojoDiscrepante = (BoletaRecojoDetalle)boletaRecojoDiscrepanteList.get(j);
						unidadesRecogidas = unidadesRecogidas + Integer.parseInt(boletarecojoDiscrepante.getUnidadesRecogidas());
					}
					unidadesRecogidas = unidadesRecogidas-unidadesEliminadas;
					boletaRecojoDetalle.setUnidadesRecogidas(""+unidadesRecogidas);
					f.setUnidadesRecogidas(""+(Integer.parseInt(boletaRecojoDetalle.getUnidadesReclamadas())-unidadesRecogidas));
					boletaRecojoDetalle.setIndicadorDiscrepante("1");
					boletaRecojoDetalle.setDiscrepante("D");
				}
			}
			
			this.recDigitacionBoletasRecojoDetalleList=boletaRecojoDetalleList;
			this.detalleDiscrepanteDataModel=new DataTableModel(this.recDigitacionBoletasRecojoDetalleDiscList);			
			initDiscrepante(f);			
		}
		else 			
			this.addError("ERROR: ", this.getResourceMessage("mantenimientoRECDigitacionBoletasRecojoForm.msg.codigoExiste"));			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}		
	}
	
	public void deleteDiscrepante(ActionEvent actionEvent){
		try {			
		MantenimientoRECDigitacionBoletasRecojoForm f = (MantenimientoRECDigitacionBoletasRecojoForm)this.formMantenimiento;		
		List boletaRecojoDiscrepanteList=this.recDigitacionBoletasRecojoDetalleDiscList;		
		BoletaRecojoDetalle delBoletaRecojoDetalle=(BoletaRecojoDetalle)this.beanRegistroDetalleDiscrepante;		
		
		List eliminar=this.recDigitacionBoletasRecojoDetalleElimList;
		eliminar.add(delBoletaRecojoDetalle);
		for(int i=0;i<boletaRecojoDiscrepanteList.size();i++){
			BoletaRecojoDetalle e= new BoletaRecojoDetalle();
			e=(BoletaRecojoDetalle)boletaRecojoDiscrepanteList.get(i);
			if(e.getCodigoProductoDiscrepante().equals(delBoletaRecojoDetalle.getCodigoProductoDiscrepante())){
				if(e.getPrecioDiscrepante().equals(delBoletaRecojoDetalle.getPrecioDiscrepante()))
					boletaRecojoDiscrepanteList.remove(i);
			}
				
		}
		this.recDigitacionBoletasRecojoDetalleElimList=eliminar;
		this.recDigitacionBoletasRecojoDetalleDiscList=boletaRecojoDiscrepanteList;		
		
		List boletaRecojoDetalleList=this.recDigitacionBoletasRecojoDetalleList;
		
		for (int i = 0; i < boletaRecojoDetalleList.size(); i++) {
			BoletaRecojoDetalle boletaRecojoDetalle = (BoletaRecojoDetalle)boletaRecojoDetalleList.get(i);
			
			int unidadesEliminadas=Integer.parseInt(boletaRecojoDetalle.getUnidadesEliminadas());
			if (boletaRecojoDetalle.getCodigoDetalle().equals(f.getCodigoDetalle())){
				int unidadesRecogidas = 0;
				
				if (boletaRecojoDiscrepanteList.size()==0){
					boletaRecojoDetalle.setIndicadorDiscrepante("0");
					boletaRecojoDetalle.setDiscrepante(null);
				}
				for (int j = 0; j < boletaRecojoDiscrepanteList.size(); j++) {
					BoletaRecojoDetalle boletarecojoDiscrepante = (BoletaRecojoDetalle)boletaRecojoDiscrepanteList.get(j);
					unidadesRecogidas = unidadesRecogidas + Integer.parseInt(boletarecojoDiscrepante.getUnidadesRecogidas());					
				}
				unidadesRecogidas = unidadesRecogidas-unidadesEliminadas;
				boletaRecojoDetalle.setUnidadesRecogidas(""+unidadesRecogidas);
			}
		}		
		this.recDigitacionBoletasRecojoDetalleList=boletaRecojoDetalleList;
		this.detalleDiscrepanteDataModel=new DataTableModel(this.recDigitacionBoletasRecojoDetalleDiscList);		
		initDiscrepante(f);	
		} catch (Exception e) {			
			this.addError("Error", this.obtieneMensajeErrorException(e));
		}
	}
	
	public void editDiscrepante(ActionEvent event){
		try {
			if(this.recDigitacionBoletasRecojoDetalleDiscList == null || this.recDigitacionBoletasRecojoDetalleDiscList.size() == 0){
				this.setMensajeAlertaDefault(this.getResourceMessage("mantenimientoRECDigitacionBoletasRecojoForm.error.notRegistros"));
				String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
				this.getRequestContext().execute(ventanaConfirmar);
				return ;
			}
			if(this.beanRegistroDetalleDiscrepante == null){
				this.setMensajeAlertaDefault(this.getResourceMessage("errors.select.item"));
				String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
				this.getRequestContext().execute(ventanaConfirmar);
				return ;				
			}else{
				if (log.isDebugEnabled()) log.debug("Entering 'editDiscrepante' method");		
				
				MantenimientoRECDigitacionBoletasRecojoForm f = (MantenimientoRECDigitacionBoletasRecojoForm) this.formMantenimiento;
				
//				String id = (String)request.getParameter("idDisc");
				BoletaRecojoDetalle boletaRecojoDiscrepante=(BoletaRecojoDetalle)this.beanRegistroDetalleDiscrepante;
//				List boletaRecojoDiscrepanteList = this.recDigitacionBoletasRecojoDetalleDiscList;
		        
//				BoletaRecojoDetalle boletaRecojoDiscrepante = (BoletaRecojoDetalle)boletaRecojoDiscrepanteList.get(Integer.parseInt(id)-1);
				
				f.setCodigoVentaDiscrepante(boletaRecojoDiscrepante.getCodigoVentaDiscrepante());
				f.setDescripcionProductoDisc(boletaRecojoDiscrepante.getDescripcionProductoDisc());
				f.setCodigoPeriodoReferenciaDiscrepante(boletaRecojoDiscrepante.getPeriodoDiscrepante());
				f.setUnidadesRecogidas(boletaRecojoDiscrepante.getUnidadesRecogidas());
				f.setCodigoProductoDiscrepante(boletaRecojoDiscrepante.getCodigoProductoDiscrepante());
				f.setPrecioDiscrepante(""+boletaRecojoDiscrepante.getPrecioDiscrepante());
				f.setEditDiscrepante(true);
				
//				int idBoletaDiscrepante = boletaRecojoDiscrepante.getid;
//				this.idBoletaDiscrepante = idBoletaDiscrepante;
				this.mostrarBotones = false;
			}
			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	public void updateDiscrepante(ActionEvent event){
		try {			
			if (log.isDebugEnabled()) log.debug("Entering 'updateDetal' method");
			
			MantenimientoRECDigitacionBoletasRecojoForm f = (MantenimientoRECDigitacionBoletasRecojoForm) this.formMantenimiento;			
			String codigoProductoDisc =  f.getCodigoProductoDiscrepante();
//			String id = (String)session.getAttribute("idBoletaDiscrepante");
//			int idBoletaDiscrepante = Integer.parseInt(id);
			
			List boletaRecojoDiscrepanteList= this.recDigitacionBoletasRecojoDetalleDiscList;
			BoletaRecojoDetalle boletaRecojoDiscrepanteSelecc = (BoletaRecojoDetalle)this.beanRegistroDetalleDiscrepante;
			for (int i = 0; i < boletaRecojoDiscrepanteList.size(); i++) {
				BoletaRecojoDetalle boletaRecojoDiscrepante = (BoletaRecojoDetalle)boletaRecojoDiscrepanteList.get(i);
				if(	StringUtils.equals(boletaRecojoDiscrepante.getCodigoProductoDiscrepante(), boletaRecojoDiscrepanteSelecc.getCodigoProductoDiscrepante()) && 
					StringUtils.equals(boletaRecojoDiscrepante.getCodigoVentaDiscrepante(), boletaRecojoDiscrepanteSelecc.getCodigoVentaDiscrepante()) && 
					StringUtils.equals(boletaRecojoDiscrepante.getDescripcionProductoDisc(), boletaRecojoDiscrepanteSelecc.getDescripcionProductoDisc()) &&
					StringUtils.equals(boletaRecojoDiscrepante.getIndicadorDiscrepante(), boletaRecojoDiscrepanteSelecc.getIndicadorDiscrepante()) &&
					StringUtils.equals(boletaRecojoDiscrepante.getPeriodoDiscrepante(), boletaRecojoDiscrepanteSelecc.getPeriodoDiscrepante()) &&
					StringUtils.equals(boletaRecojoDiscrepante.getPeriodoDiscrepante(), boletaRecojoDiscrepanteSelecc.getPeriodoDiscrepante()) &&					
					boletaRecojoDiscrepante.getPrecioDiscrepante().equals(boletaRecojoDiscrepanteSelecc.getPrecioDiscrepante()) &&
					StringUtils.equals(boletaRecojoDiscrepante.getUnidadesRecogidas(), boletaRecojoDiscrepanteSelecc.getUnidadesRecogidas()))		
				{
					boletaRecojoDiscrepante.setCodigoVentaDiscrepante(f.getCodigoVentaDiscrepante().trim());
					boletaRecojoDiscrepante.setDescripcionProductoDisc(f.getDescripcionProductoDisc().trim());
					boletaRecojoDiscrepante.setPeriodoDiscrepante(f.getCodigoPeriodoReferenciaDiscrepante().trim());
					boletaRecojoDiscrepante.setCodigoProductoDiscrepante(codigoProductoDisc.trim());
					boletaRecojoDiscrepante.setUnidadesRecogidas(f.getUnidadesRecogidas().trim());
					boletaRecojoDiscrepante.setPrecioDiscrepante(new Float(f.getPrecioDiscrepante().trim()));
					boletaRecojoDiscrepante.setIndicadorDiscrepante("1");					
				}				
			}
						
			this.recDigitacionBoletasRecojoDetalleDiscList = boletaRecojoDiscrepanteList;
				
			List boletaRecojoDetalleList = this.recDigitacionBoletasRecojoDetalleList;
			
				for (int i = 0; i < boletaRecojoDetalleList.size(); i++) 
				{
					BoletaRecojoDetalle boletaRecojoDetalle = (BoletaRecojoDetalle)boletaRecojoDetalleList.get(i);
					int unidadesEliminadas=Integer.parseInt(boletaRecojoDetalle.getUnidadesEliminadas());
					
					if (StringUtils.equals(boletaRecojoDetalle.getCodigoDetalle(), f.getCodigoDetalle())){
						int unidadesRecogidas = 0;
						for (int j = 0; j < boletaRecojoDiscrepanteList.size(); j++) {
							BoletaRecojoDetalle boletarecojoDiscrepante = (BoletaRecojoDetalle)boletaRecojoDiscrepanteList.get(j);
							unidadesRecogidas = unidadesRecogidas + Integer.parseInt(boletarecojoDiscrepante.getUnidadesRecogidas());
						}
						
						unidadesRecogidas = unidadesRecogidas-unidadesEliminadas;
						boletaRecojoDetalle.setUnidadesRecogidas(""+unidadesRecogidas);
						f.setUnidadesRecogidas(""+(Integer.parseInt(boletaRecojoDetalle.getUnidadesReclamadas())-unidadesRecogidas));
						boletaRecojoDetalle.setIndicadorDiscrepante("1");
						boletaRecojoDetalle.setDiscrepante("D");
					}
				}
			this.recDigitacionBoletasRecojoDetalleList = boletaRecojoDetalleList;
			f.setEditDiscrepante(false);
			initDiscrepante(f);
			this.mostrarBotones = true;
						
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
		
	}
	
	
	private void setFormDiscrepante(MantenimientoRECDigitacionBoletasRecojoForm f, BoletaRecojoDetalle boletaRecojoDetalle) {
		f.setCodigoDetalle(boletaRecojoDetalle.getCodigoDetalle());
		f.setCodigoVenta(boletaRecojoDetalle.getCodigoVenta());
		f.setCodigoProducto(boletaRecojoDetalle.getCodigoProducto());
		f.setDescripcionProducto(boletaRecojoDetalle.getDescripcionProducto());
		f.setUnidadesReclamadas(boletaRecojoDetalle.getUnidadesReclamadas());
		f.setUnidadesEliminadas(boletaRecojoDetalle.getUnidadesEliminadas());	
		f.setCodigoPeriodoReferencia(boletaRecojoDetalle.getCodigoPeriodoReferencia());
		f.setPorcentajeDescuento(boletaRecojoDetalle.getPorcentajeDescuento());
		f.setPrecio(boletaRecojoDetalle.getPrecio());
		
		f.setCodigoVentaDiscrepante(null);
		f.setDescripcionProductoDisc(null);
		f.setCodigoPeriodoReferenciaDiscrepante(null);
		f.setCodigoProductoDiscrepante(null);
		f.setPrecioDiscrepante(null);
		
		f.setViewDiscrepante(true);
		f.setViewCabecera(false);
		f.setViewDetalle(false);
	}	
	
	private void initDiscrepante(MantenimientoRECDigitacionBoletasRecojoForm f) {		
		f.setCodigoVentaDiscrepante(null);
		f.setDescripcionProductoDisc(null);
		f.setCodigoPeriodoReferenciaDiscrepante(null);
		f.setCodigoProductoDiscrepante(null);
		f.setUnidadesRecogidas(null);
		f.setPrecioDiscrepante(null);
	}
	
	public void showCabecera(ActionEvent event)	{
		try {
			MantenimientoRECDigitacionBoletasRecojoForm f = (MantenimientoRECDigitacionBoletasRecojoForm)this.formMantenimiento;	
			f.setViewDiscrepante(false);		
			this.mostrarBotones = true;
			this.mostrarBotonSalirP = false;
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	public void cancelar(ActionEvent event){
		try {			
			MantenimientoRECDigitacionBoletasRecojoForm f = (MantenimientoRECDigitacionBoletasRecojoForm)this.formMantenimiento;
			this.mostrarBotones = true;
			initDiscrepante(f);
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
		
	}
	
	@Override
	public String setValidarMantenimiento() {
		String mensaje = "";
		MantenimientoRECDigitacionBoletasRecojoForm f = (MantenimientoRECDigitacionBoletasRecojoForm)this.formMantenimiento;
		
		if(StringUtils.isBlank(f.getCodigoResultado()))
			mensaje = "'Resultado' es un campo requerido";
		
		return mensaje;
	}
	
	public void onChangeResultado(ValueChangeEvent event){
		String codigoResultado = StringUtils.isNotBlank((String)event.getNewValue())?(String)event.getNewValue(): ""; //codigoResultado
		MantenimientoRECDigitacionBoletasRecojoForm f = (MantenimientoRECDigitacionBoletasRecojoForm)this.formMantenimiento;
		f.setCodigoResultado(codigoResultado);
		String numeroRecojo    = f.getNumeroRecojo();		
		String indicadorPGRBR  = f.getIndicadorPGRBR();

	    //Actualiza los valores de Unidades Recogidas Por Defecto Unidades Reclamasdas 
		if(this.recDigitacionBoletasRecojoDetalleList != null && this.recDigitacionBoletasRecojoDetalleList.size() > 0){
			List aux1 = new ArrayList();
			for (int i = 0; i < recDigitacionBoletasRecojoDetalleList.size(); i++){
				BoletaRecojoDetalle aux = (BoletaRecojoDetalle)this.recDigitacionBoletasRecojoDetalleList.get(i);
				String labelUnidReclamadas = aux.getUnidadesReclamadas();
				String labelUnidRecogidas= aux.getUnidadesRecogidas();
				String labelUnidEliminadas= aux.getUnidadesEliminadas();
				
				int unidadesReclamadas = 0;
				
				if(StringUtils.equals(numeroRecojo, "1")){
					if (StringUtils.equals(codigoResultado, "CD") || StringUtils.equals(codigoResultado, "EX")){
					    labelUnidEliminadas = "0";
						unidadesReclamadas = Integer.parseInt(labelUnidReclamadas);
					}
					
					if (StringUtils.equals(codigoResultado, "NE") || StringUtils.equals(codigoResultado, "NX"))
						labelUnidRecogidas = "0";					
					else 
						labelUnidRecogidas = String.valueOf(unidadesReclamadas - Integer.parseInt(labelUnidEliminadas));					
				}
				
				if (StringUtils.equals(numeroRecojo, "2")  &&  StringUtils.equals(indicadorPGRBR, "1")){
	                if (StringUtils.equals(codigoResultado, "CD") || StringUtils.equals(codigoResultado, "EX"))
	                       unidadesReclamadas = Integer.parseInt(labelUnidReclamadas) - Integer.parseInt(labelUnidEliminadas);
	                
	                if (StringUtils.equals(codigoResultado, "NE") || StringUtils.equals(codigoResultado, "NX"))
	                	unidadesReclamadas = '0';
	                
	                labelUnidRecogidas = String.valueOf(unidadesReclamadas);
	    		}	
				
				aux.setUnidadesRecogidas(labelUnidRecogidas);
				aux1.add(aux);
			}
			this.setRecDigitacionBoletasRecojoDetalleList(aux1);
			this.deshabilitaCampoEliminadas = true;
			habilitaIngresoUnidadesEliminadas();
		}		
	}
	
	private void habilitaIngresoUnidadesEliminadas(){
		MantenimientoRECDigitacionBoletasRecojoForm f = (MantenimientoRECDigitacionBoletasRecojoForm)this.formMantenimiento;
		String codigoResultado = StringUtils.isBlank(f.getCodigoResultado())?"":f.getCodigoResultado();
		String numeroRecojo    = f.getNumeroRecojo();		
		String motivoNoRecojo  = StringUtils.isBlank(f.getCodigoMotivoNoRecojo1())?"":f.getCodigoMotivoNoRecojo1();		
	
		if (StringUtils.equals(numeroRecojo, "2")) 
			motivoNoRecojo =  f.getCodigoMotivoNoRecojo2();
		
		this.deshabilitaCampoEliminadas = true;

		if (StringUtils.equals(codigoResultado, "NX") && StringUtils.equals(motivoNoRecojo, "06")) {
			this.deshabilitaCampoEliminadas = false;
		} else {
			if (StringUtils.equals(numeroRecojo, "1")) {
				if (this.recDigitacionBoletasRecojoDetalleList != null
						&& this.recDigitacionBoletasRecojoDetalleList.size() > 0) {
					List aux1 = new ArrayList();
					for (int i = 0; i < recDigitacionBoletasRecojoDetalleList.size(); i++) {
						BoletaRecojoDetalle aux = (BoletaRecojoDetalle) this.recDigitacionBoletasRecojoDetalleList.get(i);
						aux.setUnidadesEliminadas("0");
						aux1.add(aux);
					}

					this.setRecDigitacionBoletasRecojoDetalleList(aux1);
				}
			}
		}	
	}
	
	public void onChangeMotivoNoRecojo(ValueChangeEvent event){
		String motivoNoRecojo = StringUtils.isNotBlank((String)event.getNewValue())?(String)event.getNewValue(): "";
		MantenimientoRECDigitacionBoletasRecojoForm f = (MantenimientoRECDigitacionBoletasRecojoForm)this.formMantenimiento;
		f.setCodigoMotivoNoRecojo1(motivoNoRecojo);
		habilitaIngresoUnidadesEliminadas();
	}
	
	public void validateUnidadesEliminadas(){
		System.out.println("mostro entroooooooooooooooooooooooooooooooooooooooooooo");
		MantenimientoRECDigitacionBoletasRecojoForm f = (MantenimientoRECDigitacionBoletasRecojoForm)this.formMantenimiento;
		
		String codigoResultado = f.getCodigoResultado();
		int ban = 0 ;
		
		if (this.recDigitacionBoletasRecojoDetalleList != null
				&& this.recDigitacionBoletasRecojoDetalleList.size() > 0){
			List aux1 = new ArrayList();
			for (int j = 0; j < this.recDigitacionBoletasRecojoDetalleList.size(); j++){
				BoletaRecojoDetalle aux = (BoletaRecojoDetalle)this.recDigitacionBoletasRecojoDetalleList.get(j);
				String labelUnidReclamadas = aux.getUnidadesReclamadas();
				String labelUnidRecogidas= aux.getUnidadesRecogidas();
				String labelUnidEliminadas= aux.getUnidadesEliminadas();
								
				if(StringUtils.isNotBlank(labelUnidReclamadas) && StringUtils.isNotBlank(labelUnidRecogidas)
						&& StringUtils.isNotBlank(labelUnidEliminadas))	{
					if (Integer.parseInt(labelUnidEliminadas) > Integer.parseInt(labelUnidReclamadas)) {
						labelUnidEliminadas = labelUnidReclamadas;
						ban += 1;
					}
					
					if (StringUtils.equals(codigoResultado, "NE") || StringUtils.equals(codigoResultado, "NX"))
						labelUnidRecogidas = "0";					
					else{
						int resultado = Integer.parseInt(labelUnidReclamadas) - Integer.parseInt(labelUnidEliminadas);
						labelUnidRecogidas = String.valueOf(resultado);
					}
	
					aux.setUnidadesRecogidas(labelUnidRecogidas);
					aux.setUnidadesEliminadas(labelUnidEliminadas);				
				}
				
				aux1.add(aux);
			} 
			if(ban > 0) 
				this.addWarn("Advertencia: ", this.getResourceMessage("mantenimientoRECDigitacionBoletasRecojoForm.error.unidadesEliminadas"));				
	
			this.setRecDigitacionBoletasRecojoDetalleList(aux1);
		}
	}
	
	@Override
	protected String devuelveMensajeKeySaveOK() {
		MantenimientoRECDigitacionBoletasRecojoForm f = (MantenimientoRECDigitacionBoletasRecojoForm)this.formMantenimiento;		
		return "mantenimientoRECDigitacionBoletasRecojo.updated";	
	}	
	
	//PARTE POPUP PRODUCTO
	@SuppressWarnings("static-access")
	@Override
	protected void setInvocarPopup(String accion) {
		MantenimientoRECDigitacionBoletasRecojoForm f = (MantenimientoRECDigitacionBoletasRecojoForm)this.formMantenimiento;		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		if (accion.equals(this.POPUP_CLIENTE)){ 
			MantenimientoRECBusquedaProductosAction action = findManageBean("mantenimientoRECBusquedaProductosAction");
			MantenimientoRECBusquedaProductosForm busquedaForm = new MantenimientoRECBusquedaProductosForm();	
			busquedaForm.setCodigoPeriodo(f.getCodigoPeriodoReferencia());
			busquedaForm.setCodigoPais(pais.getCodigo());
			busquedaForm.setCodigoProducto(f.getCodigoProductoDiscrepante());
			busquedaForm.setDescripcionProducto(f.getDescripcionProductoDisc());
            action.setFormBusqueda(busquedaForm);
            
            /* Seteando Datatatable */
            List lista = new ArrayList();
            DataTableModel datatableBusqueda = new DataTableModel(lista);
            action.setListaBusqueda(lista);
            action.setDatatableBusqueda(datatableBusqueda);
            
			this.mostrarPopupCliente = true;
		}
	}	
	
	@SuppressWarnings("static-access")
	@Override
	protected void setAceptarPopup(ActionEvent event, String accion) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setAceptarPopupHipCliente' method");
		}
		
		this.mostrarProcesoBatch = true;
		this.mostrarPopupCliente = false;
		if (accion.equals(this.POPUP_CLIENTE)) {
			this.mantenimientoRECBusquedaProductosAction.verificarRegistro(event);			
			if (this.mantenimientoRECBusquedaProductosAction.isSeleccionoRegistro()) {
				Map producto = (Map) this.mantenimientoRECBusquedaProductosAction.getBeanRegistroSeleccionado();				
				MantenimientoRECDigitacionBoletasRecojoForm f = (MantenimientoRECDigitacionBoletasRecojoForm) this.formMantenimiento;
				
				f.setCodigoVentaDiscrepante(producto.get("codigoVentaDiscrepante").toString());
				f.setCodigoPeriodoReferenciaDiscrepante(producto.get("codigoPeriodoDiscrepante").toString());
				f.setCodigoProductoDiscrepante(producto.get("codigoProductoDiscrepante").toString());				
				f.setDescripcionProductoDisc(producto.get("descripcionProductoDisc").toString());				
				f.setPorcentajeDescuento(producto.get("porcentajeDescuentoDiscrepante").toString());
				f.setPrecioDiscrepante(producto.get("precioDiscrepante").toString());
				
				this.mantenimientoRECBusquedaProductosAction.setBeanRegistroSeleccionado(null);
				this.formMantenimiento =  f;				
			}
		}		
	}
	
	@Override
	protected void setSalirPopup() {
		this.mostrarProcesoBatch = true;
		this.mostrarPopupCliente = false;
		this.mantenimientoRECBusquedaProductosAction.setBeanRegistroSeleccionado(null);		
	}	
	
	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
	}

	public List getSiccCanalList() {
		return siccCanalList;
	}

	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
	}

	public List getSiccEstadosBorecList() {
		return siccEstadosBorecList;
	}

	public void setSiccEstadosBorecList(List siccEstadosBorecList) {
		this.siccEstadosBorecList = siccEstadosBorecList;
	}

	public List getSiccResultadosBorecList() {
		return siccResultadosBorecList;
	}

	public void setSiccResultadosBorecList(List siccResultadosBorecList) {
		this.siccResultadosBorecList = siccResultadosBorecList;
	}

	public List getSiccMotivosNorecojoBorecList() {
		return siccMotivosNorecojoBorecList;
	}

	public void setSiccMotivosNorecojoBorecList(List siccMotivosNorecojoBorecList) {
		this.siccMotivosNorecojoBorecList = siccMotivosNorecojoBorecList;
	}

	public List getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	public List getRecDigitacionBoletasRecojoCabeceraList() {
		return recDigitacionBoletasRecojoCabeceraList;
	}

	public void setRecDigitacionBoletasRecojoCabeceraList(
			List recDigitacionBoletasRecojoCabeceraList) {
		this.recDigitacionBoletasRecojoCabeceraList = recDigitacionBoletasRecojoCabeceraList;
	}

	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	public Map getRecDigitacionBoletasRecojoDiscList() {
		return recDigitacionBoletasRecojoDiscList;
	}

	public void setRecDigitacionBoletasRecojoDiscList(
			Map recDigitacionBoletasRecojoDiscList) {
		this.recDigitacionBoletasRecojoDiscList = recDigitacionBoletasRecojoDiscList;
	}

	public List getRecDigitacionBoletasRecojoDetalleList() {
		return recDigitacionBoletasRecojoDetalleList;
	}

	public void setRecDigitacionBoletasRecojoDetalleList(
			List recDigitacionBoletasRecojoDetalleList) {
		this.recDigitacionBoletasRecojoDetalleList = recDigitacionBoletasRecojoDetalleList;
	}

	public DataTableModel getDetalleBoletasRecojoTableModel() {
		return detalleBoletasRecojoTableModel;
	}

	public void setDetalleBoletasRecojoTableModel(
			DataTableModel detalleBoletasRecojoTableModel) {
		this.detalleBoletasRecojoTableModel = detalleBoletasRecojoTableModel;
	}

	public Object getBeanRegistroDetalleBoletasRecojo() {
		return beanRegistroDetalleBoletasRecojo;
	}

	public void setBeanRegistroDetalleBoletasRecojo(
			Object beanRegistroDetalleBoletasRecojo) {
		this.beanRegistroDetalleBoletasRecojo = beanRegistroDetalleBoletasRecojo;
	}

	public DataTableModel getDetalleDiscrepanteDataModel() {
		return detalleDiscrepanteDataModel;
	}

	public void setDetalleDiscrepanteDataModel(
			DataTableModel detalleDiscrepanteDataModel) {
		this.detalleDiscrepanteDataModel = detalleDiscrepanteDataModel;
	}

	public Object getBeanRegistroDetalleDiscrepante() {
		return beanRegistroDetalleDiscrepante;
	}

	public void setBeanRegistroDetalleDiscrepante(
			Object beanRegistroDetalleDiscrepante) {
		this.beanRegistroDetalleDiscrepante = beanRegistroDetalleDiscrepante;
	}

	public boolean isMostrarPopupCliente() {
		return mostrarPopupCliente;
	}

	public void setMostrarPopupCliente(boolean mostrarPopupCliente) {
		this.mostrarPopupCliente = mostrarPopupCliente;
	}

	public MantenimientoRECBusquedaProductosAction getMantenimientoRECBusquedaProductosAction() {
		return mantenimientoRECBusquedaProductosAction;
	}

	public void setMantenimientoRECBusquedaProductosAction(
			MantenimientoRECBusquedaProductosAction mantenimientoRECBusquedaProductosAction) {
		this.mantenimientoRECBusquedaProductosAction = mantenimientoRECBusquedaProductosAction;
	}

	public static String getPopupCliente() {
		return POPUP_CLIENTE;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	public String getNumeroRecojo() {
		return numeroRecojo;
	}

	public void setNumeroRecojo(String numeroRecojo) {
		this.numeroRecojo = numeroRecojo;
	}

	public List getRecDigitacionBoletasRecojoDetalleElimList() {
		return recDigitacionBoletasRecojoDetalleElimList;
	}

	public void setRecDigitacionBoletasRecojoDetalleElimList(
			List recDigitacionBoletasRecojoDetalleElimList) {
		this.recDigitacionBoletasRecojoDetalleElimList = recDigitacionBoletasRecojoDetalleElimList;
	}

	public List getRecDigitacionBoletasRecojoDetalleDiscList() {
		return recDigitacionBoletasRecojoDetalleDiscList;
	}

	public void setRecDigitacionBoletasRecojoDetalleDiscList(
			List recDigitacionBoletasRecojoDetalleDiscList) {
		this.recDigitacionBoletasRecojoDetalleDiscList = recDigitacionBoletasRecojoDetalleDiscList;
	}

	public Boolean getMostrarBotones() {
		return mostrarBotones;
	}

	public void setMostrarBotones(Boolean mostrarBotones) {
		this.mostrarBotones = mostrarBotones;
	}

	public Boolean getDeshabilitaCampoEliminadas() {
		return deshabilitaCampoEliminadas;
	}

	public void setDeshabilitaCampoEliminadas(Boolean deshabilitaCampoEliminadas) {
		this.deshabilitaCampoEliminadas = deshabilitaCampoEliminadas;
	}

	public Boolean getMostrarBotonSalirP() {
		return mostrarBotonSalirP;
	}

	public void setMostrarBotonSalirP(Boolean mostrarBotonSalirP) {
		this.mostrarBotonSalirP = mostrarBotonSalirP;
	}
	
	
}
