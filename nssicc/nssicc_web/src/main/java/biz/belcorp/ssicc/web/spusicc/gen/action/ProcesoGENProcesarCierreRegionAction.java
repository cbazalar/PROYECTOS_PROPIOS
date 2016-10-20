package biz.belcorp.ssicc.web.spusicc.gen.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.spusicc.fac.MantenimientoFACGenericoService;
import biz.belcorp.ssicc.service.spusicc.gen.ProcesoGENProcesarCierreService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.spusicc.gen.form.ProcesoGENProcesarCierreRegionForm;
import biz.belcorp.ssicc.web.util.StringUtil;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ProcesoGENProcesarCierreRegionAction extends
		BaseInterfazAbstractAction {

	private static final long serialVersionUID = 6757519628037552419L;
	private List genInterfacesPaquete = new ArrayList();
	private List siccMarcaList = new ArrayList();
	private List siccCanalList = new ArrayList();
	private List siccRegionList = new ArrayList();
	private boolean opcionPeriodoFecha;
	private String indicadorSeleccionInterfaz;
	private List genInterfazPaquete = new ArrayList();
	private int totalProcesosSICC;	
	
	private String tipoProceso;
	
	/**
	 * @return the siccMarcaList
	 */
	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	/**
	 * @param siccMarcaList the siccMarcaList to set
	 */
	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
	}

	/**
	 * @return the siccCanalList
	 */
	public List getSiccCanalList() {
		return siccCanalList;
	}

	/**
	 * @param siccCanalList the siccCanalList to set
	 */
	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
	}

	/**
	 * @return the siccRegionList
	 */
	public List getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList the siccRegionList to set
	 */
	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	/**
	 * @return the indicadorSeleccionInterfaz
	 */
	public String getIndicadorSeleccionInterfaz() {
		return indicadorSeleccionInterfaz;
	}

	/**
	 * @param indicadorSeleccionInterfaz the indicadorSeleccionInterfaz to set
	 */
	public void setIndicadorSeleccionInterfaz(String indicadorSeleccionInterfaz) {
		this.indicadorSeleccionInterfaz = indicadorSeleccionInterfaz;
	}

	/**
	 * @return the genInterfazPaquete
	 */
	public List getGenInterfazPaquete() {
		return genInterfazPaquete;
	}

	/**
	 * @param genInterfazPaquete the genInterfazPaquete to set
	 */
	public void setGenInterfazPaquete(List genInterfazPaquete) {
		this.genInterfazPaquete = genInterfazPaquete;
	}

	/**
	 * @return the totalProcesosSICC
	 */
	public int getTotalProcesosSICC() {
		return totalProcesosSICC;
	}

	/**
	 * @param totalProcesosSICC the totalProcesosSICC to set
	 */
	public void setTotalProcesosSICC(int totalProcesosSICC) {
		this.totalProcesosSICC = totalProcesosSICC;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.action.BaseAbstractAction#setViewAttributes(javax
	 * .servlet.http.HttpServletRequest, org.apache.struts.action.ActionForm)
	 */
	@Override
	protected void setViewAtributes() throws Exception {

		ProcesoGENProcesarCierreRegionForm f = (ProcesoGENProcesarCierreRegionForm) formInterfaz;
		f.setCodigoPais(mPantallaPrincipalBean.getCurrentCountry().getCodigo());
		Usuario usuario = mPantallaPrincipalBean.getCurrentUser();
		
		f.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		f.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);

		Map params = new HashMap();
		params.clear();
		params.put("codigoISO", usuario.getIdioma().getCodigoISO());
		params.put("codigoPais", mPantallaPrincipalBean.getCurrentCountry()
				.getCodigo());

		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		String actualizarRegiones = this.getParametrosPantalla().get(
				"actualizarRegiones");

		// Carga de los combos Marca y Canal
		this.siccMarcaList = interfazSiCCService.getMarcas();
		this.siccCanalList = interfazSiCCService.getCanalesByCodigoISO(usuario
				.getIdioma().getCodigoISO());

		Map criteria = new HashMap();
		criteria.put("codigoPais", mPantallaPrincipalBean.getCurrentCountry()
				.getCodigo());

		if (!"1".equals(actualizarRegiones)) {
			criteria.put("estadoCampanha", Constants.NUMERO_CERO); // Indica
																	// Campanha
																	// Activa
			criteria.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica
																		// Campanha
																		// activa
																		// q se
																		// procesa
																		// actualmente

			MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
			PedidoControlFacturacion controlFacturacion = service
					.getControlFacturacionById(criteria);

			// Carga de PeriodoProceso y Fecha Facturacion
			f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
			f.setFechaFacturacion(controlFacturacion.getFechaProceso());
			SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd/MM/yyyy");

			f.setFechaFacturacionD(formatoDeFecha.parse(controlFacturacion
					.getFechaProceso()));
		}

		ProcesoGENProcesarCierreService procesoService = (ProcesoGENProcesarCierreService) getBean("spusicc.procesoGENProcesarCierreService");

		criteria.put("codigoPeriodo", f.getCodigoPeriodo());
		criteria.put("fechaFacturacion", f.getFechaFacturacion());
		criteria.put("tipoCierre", Constants.CODIGO_TIPO_CIERRE_REGION);

		List regiones = procesoService.getRegionesACerrar(criteria);

		this.siccRegionList = regiones;

		List lista = new ArrayList();

		criteria.put("codigoSistema", "GEN");
		criteria.put("codigoInterfaz", f.getCodigoInterfaz());

		lista = interfazSiCCService.getListaProcesosGenCierreCampania(criteria);

		List listaProcesosTemp = procesoService
				.getProcesosCierreRegion(criteria);
		List listaProcesos = new ArrayList();
		Base base;
		for (int i = 0; i < listaProcesosTemp.size(); i++) {
			Map baseResult = (HashMap) listaProcesosTemp.get(i);

			base = new Base();
			base.setCodigo((String) baseResult.get("value"));
			base.setDescripcion((String) baseResult.get("label"));

			listaProcesos.add(base);
		}
		this.totalProcesosSICC=listaProcesos.size();
		listaProcesos.addAll(lista);
		
		
		this.genInterfacesPaquete=listaProcesos;
		String indicadorSeleccion=f.getIndicadorSeleccionInterfaces();
		f.setIndicadorSeleccionInicial(indicadorSeleccion);
		if (regiones.size() == 0) {
			f.setMostrarBotonProcesar(false);
			this.mostrarBotonExecute = false;

			this.addInfo("Mensaje",
					getResourceMessage("procesoGENProcesarCierreRegionForm.msg.validacionRegionesAProcesar"));
		} else {

			for (int i = 0; i < regiones.size(); i++) {
				Map mapRegion = (Map) regiones.get(i);
				String oidRegion = mapRegion.get("value").toString();
				String descripcionRegion = mapRegion.get("label").toString();

				criteria.put("oidRegion", oidRegion);
				boolean validar = procesoService
						.existeZonasxRegionSinProcesar(criteria);

				if (!validar) {

					this.addInfo("Mensaje",
							getResourceMessage("procesoGENProcesarCierreRegionForm.msg.validacionZonasAbiertas", new String[]{descripcionRegion}));
				}
			}

		}
		
		String frecuenciaSGR = this.parametrosPantalla.get("frecuenciaSGR");
		f.setFrecuenciaSGR(frecuenciaSGR);
		
		//Variable que indica el tipo del Proceso, viaja como un parámetro más a través de la url.
		String tipoProceso = this.parametrosPantalla.get("tipoProceso");
	}
	
	@Override
	public List<Interfaz> setObtenerListaProcesos() {
		ProcesoGENProcesarCierreService procesoService = (ProcesoGENProcesarCierreService) 
				getBean("spusicc.procesoGENProcesarCierreService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		List listaProcesosTemp = procesoService.getProcesosCierreRegion(criteria);
		
		List listaProcesos = new ArrayList();
		Interfaz interfaz = new Interfaz();
		String procesos = "";
		
		for(int i=0;i<listaProcesosTemp.size();i++){
			Map baseResult = (HashMap)listaProcesosTemp.get(i);
			
			if(i>0) {
				procesos = procesos + " \n " + (String)baseResult.get("label");
			} else {
				procesos = (String)baseResult.get("label");
			}
			
		}
		
		interfaz.setCodigo("SICC");
		interfaz.setDescripcion(procesos);
		listaProcesos.add(interfaz);

		return listaProcesos; 
	}
	
	public void actualizar(String actualizarRegiones)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'actualizar' method");
		}		
		
		try {
			ProcesoGENProcesarCierreRegionForm f = (ProcesoGENProcesarCierreRegionForm) this.formInterfaz;
			
			Usuario usuario = mPantallaPrincipalBean.getCurrentUser();
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			
			Map params = new HashMap();
	        params.clear();
	        params.put("codigoISO",usuario.getIdioma().getCodigoISO());
	        params.put("codigoPais",pais.getCodigo());
			
			Map criteria = new HashMap();
			criteria.put("codigoPais", pais.getCodigo());
			
			if(!"1".equals(actualizarRegiones)) {
		        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
		        criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente  
		        
				MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService)
											getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
				PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteria);
				
				// Carga de PeriodoProceso y Fecha Facturacion
				f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
				f.setFechaFacturacion(controlFacturacion.getFechaProceso());
				
				//	request.getSession().setAttribute(Constants.INDICADOR_SELECCION_INTERFAZ, f.getIndicadorSeleccionInicial());

				if("true".equals(f.getHabilitador())){
					f.setMostrarHabilitadorHidden(false);
					setOpcionPeriodoFecha(false);
		
		    	}else{
		    		setOpcionPeriodoFecha(true);
					f.setMostrarHabilitadorHidden(true);
		    		
		    	}
			}
			
			/*if("true".equals(f.getHabilitador())) {
				request.getSession().setAttribute(Constants.INDICADOR_SELECCION_INTERFAZ, Constants.SI);
			}*/	
	
			ProcesoGENProcesarCierreService procesoService = (ProcesoGENProcesarCierreService) 
										getBean("spusicc.procesoGENProcesarCierreService");
			
			criteria.put("codigoPeriodo", f.getCodigoPeriodo());
			criteria.put("fechaFacturacion", f.getFechaFacturacion());
			criteria.put("tipoCierre", Constants.CODIGO_TIPO_CIERRE_REGION);
			
			List regiones =  procesoService.getRegionesACerrar(criteria);
			this.siccRegionList = regiones;
			
			if(regiones.size() == 0) { 
				f.setMostrarBotonProcesar(false);
				this.mostrarBotonExecute = false;
				
				this.addInfo("Mensaje",
						getResourceMessage("procesoGENProcesarCierreRegionForm.msg.validacionRegionesAProcesar"));
				
			} else {
				
				for(int i=0; i<regiones.size(); i++) {
					Map mapRegion = (Map)regiones.get(i);
					String oidRegion = mapRegion.get("value").toString();
					String descripcionRegion = mapRegion.get("label").toString();
					
					criteria.put("oidRegion", oidRegion);
					boolean validar = procesoService.existeZonasxRegionSinProcesar(criteria);
					
					if(!validar) {
						f.setMostrarBotonProcesar(false);
						this.mostrarBotonExecute = false;
						
						this.addInfo("Mensaje",
								getResourceMessage("procesoGENProcesarCierreRegionForm.msg.validacionZonasAbiertas", new String[]{descripcionRegion}));
					}  
				}
				
			}
			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
			
		}	
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#prepareParamsBeforeExecute(java.util.Map, biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm)
	 */
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params,
			BaseForm form) throws Exception {

		params = super.prepareParamsBeforeExecute(params, form);
		ProcesoGENProcesarCierreRegionForm f = (ProcesoGENProcesarCierreRegionForm) form;
		MantenimientoFACGenericoService mantenimientoFACGenericoService = (MantenimientoFACGenericoService) getBean("spusicc.mantenimientoFACGenericoService");
		
		List regiones = this.siccRegionList;

		Long[] oidRegiones = new Long[regiones.size()];
		String[] codigoRegiones = new String[regiones.size()];

		for (int i = 0; i < regiones.size(); i++) {
			Map mapRegion = (Map) regiones.get(i);
			oidRegiones[i] = new Long(mapRegion.get("value").toString());
			codigoRegiones[i] = mantenimientoFACGenericoService
					.getCodigoRegionByOidRegion(Integer.valueOf(oidRegiones[i]
							.toString()));
		}

		if(this.esSeleccionable) {
			if(this.listaProcesosAdicionalesSeleccionadas.size()>0) {
				f.setIndicadorEjecucionSICC(Constants.SI);
			} else {
				f.setIndicadorEjecucionSICC(Constants.NO);
			}
			params.put("indicadorEjecucionSICC", f.getIndicadorEjecucionSICC());
			
			if((f.getListaInterfacesSeleccionadas()!=null) && (f.getListaInterfacesSeleccionadas().length>0)) {
				boolean encontroProcesoSICC = false;
				for(int i=0; i<f.getListaInterfacesSeleccionadas().length; i++) {
					if(f.getListaInterfacesSeleccionadas()[i]==null) {
						encontroProcesoSICC = true;
						break;
					}
				}
				
				if(encontroProcesoSICC && (f.getListaInterfacesSeleccionadas().length==1)) {
					params.put("indicadorEjecucionInterfaces", Constants.NO);
				} else
					params.put("indicadorEjecucionInterfaces", Constants.SI);
				
			}
		} else {
			f.setIndicadorEjecucionSICC(Constants.SI);
			params.put("indicadorEjecucionSICC", Constants.SI);
			params.put("indicadorEjecucionInterfaces", Constants.SI);
		}
		
		Integer contCierreZona;

		if (Constants.NO.equals(f.getIndicadorEjecucionSICC())) {
			InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");

			for (int i = 0; i < oidRegiones.length; i++) {
				contCierreZona = interfazSiCCService
						.getContCierreRegionByCodigoPeriodoOidRegion(
								(String) params.get("codigoPeriodo"),
								Integer.valueOf(oidRegiones[i].toString()));
				if (contCierreZona == 0) {

					String codigoRegionNoCerrada = mantenimientoFACGenericoService
							.getCodigoRegionByOidRegion(Integer
									.valueOf(oidRegiones[i].toString()));

					String parametrosMsg[] = { codigoRegionNoCerrada,
							(String) params.get("codigoPeriodo") };
					this.getResourceMessage("procesoGENProcesarCierreRegionForm.msg.validacionCierreRegion");
					String mensaje =this.getResourceMessage("procesoGENProcesarCierreRegionForm.msg.validacionCierreRegion", parametrosMsg);
								
					throw new Exception(mensaje);
				}
			}
		}

		Usuario usuario= this.mPantallaPrincipalBean.getCurrentUser();
		
		params.put("tipoProceso", this.tipoProceso);
		params.put("regionList", oidRegiones);
		params.put("codigoRegionList", codigoRegiones);

		if (codigoRegiones != null && codigoRegiones.length > 0) {
			params.put("regionListAux",
					StringUtil.obtieneListaCodigos(codigoRegiones, "", ""));
		}
		
		String indicadorSeleccion = f.getIndicadorSeleccionInterfaces();
		if(f.getListaSeleccionadas().size()!=f.getListaInterfaces().length){
			indicadorSeleccion="S";
		}
		
		params.put(Constants.INDICADOR_SELECCION_INTERFAZ, indicadorSeleccion);

		params.put("frecuenciaSGR", f.getFrecuenciaSGR());
		
		String fechaFactura=DateUtil.convertDateToString(f.getFechaFacturacionD());
		params.put("fechaFacturacion", fechaFactura);

		return params;
	}
	
	@Override
	protected Map<String, Object> executeProcessBeforeInterfaz(Map<String, Object> params) throws Exception {
		log.debug("Exectuting action : executeProcessBeforeInterfaz.");
		
		String indicadorEjecucionSICC = (String)params.get("indicadorEjecucionSICC");
		if(Constants.SI.equals(indicadorEjecucionSICC)) {
			ProcesoGENProcesarCierreService service = (ProcesoGENProcesarCierreService)getBean("spusicc.procesoGENProcesarCierreService");
			
			service.executeProcesarCierreRegion(params);
		}
		return params;
	}
	
	@Override
	protected boolean continueExecuteInterfaz(Map params) {
		boolean flag = true;
		
		if(this.esSeleccionable) {
			String indicadorEjecucionInterfaces = (String)params.get("indicadorEjecucionInterfaces");
			
			if(Constants.SI.equals(indicadorEjecucionInterfaces)) 
				flag = true;
			else
				flag = false;
		}
		
		return flag;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#devuelveFormInterfaz()
	 */
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		ProcesoGENProcesarCierreRegionForm form = new ProcesoGENProcesarCierreRegionForm();
		return form;
	}
	

	/**
	 * Metodo para validar nuevo periodo
	 * 
	 * @param val
	 */
	public void loadPeriodoFecha(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadPeriodoFecha");
		}
		try {
			ProcesoGENProcesarCierreRegionForm f = (ProcesoGENProcesarCierreRegionForm)formInterfaz;
			String opcion = (String) val.getNewValue().toString();

			if (opcion.equals("true")) {
				f.setMostrarHabilitadorHidden(false);
				setOpcionPeriodoFecha(false);
			} else {
				setOpcionPeriodoFecha(true);
				f.setMostrarHabilitadorHidden(true);
			}
			
			actualizar("0");
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}		
	}

	/**
	 * @return the genInterfacesPaquete
	 */
	public List getGenInterfacesPaquete() {
		return genInterfacesPaquete;
	}

	/**
	 * @param genInterfacesPaquete
	 *            the genInterfacesPaquete to set
	 */
	public void setGenInterfacesPaquete(List genInterfacesPaquete) {
		this.genInterfacesPaquete = genInterfacesPaquete;
	}

	/**
	 * @return the opcionPeriodoFecha
	 */
	public boolean isOpcionPeriodoFecha() {
		return opcionPeriodoFecha;
	}

	/**
	 * @param opcionPeriodoFecha
	 *            the opcionPeriodoFecha to set
	 */
	public void setOpcionPeriodoFecha(boolean opcionPeriodoFecha) {
		this.opcionPeriodoFecha = opcionPeriodoFecha;
	}
}