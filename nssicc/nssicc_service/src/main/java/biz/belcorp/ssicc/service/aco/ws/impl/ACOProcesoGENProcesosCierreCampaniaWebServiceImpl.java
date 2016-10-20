package biz.belcorp.ssicc.service.aco.ws.impl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.xml.rpc.ServiceException;

import org.apache.axis.MessageContext;
import org.apache.commons.lang.StringUtils;
import org.springframework.remoting.jaxws.SimpleJaxWsServiceExporter;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.edu.model.EmpresaComercializadora;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.aco.ws.ACOProcesoGENProcesosCierreCampaniaWebService;
import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;
import biz.belcorp.ssicc.service.aco.ws.beans.ParametroACOWebService;
import biz.belcorp.ssicc.service.edu.MantenimientoEDUGenericoService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazResult;
import biz.belcorp.ssicc.service.sisicc.framework.beans.SSiCC_Desfasado_InterfazExecutionResult;
import biz.belcorp.ssicc.service.spusicc.gen.ProcesoGENProcesarCierreService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ACOProcesoRECEnviaMsgReclamoWebServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cbazalar@sigcomt.com">Carlos Bazalar</a>
 */
public class ACOProcesoGENProcesosCierreCampaniaWebServiceImpl extends BaseInterfazHiloAbstractWebService implements ACOProcesoGENProcesosCierreCampaniaWebService {

	ProcesoGENProcesarCierreService procesoGENProcesarCierreService;
	InterfazSiCCService interfazSiCCService;
	MantenimientoOCRPedidoControlFacturacionService mantenimientoOCRPedidoControlFacturacionService;
	MantenimientoEDUGenericoService mantenimientoEDUGenericoService;
	AjaxService ajaxService;
	
	
	protected void onInit() throws ServiceException {
		super.onInit();
		this.interfazSiCCService = (InterfazSiCCService) getWebApplicationContext().getBean("sisicc.interfazSiCCService");
		this.mantenimientoOCRPedidoControlFacturacionService = (MantenimientoOCRPedidoControlFacturacionService)getWebApplicationContext().getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		this.procesoGENProcesarCierreService = (ProcesoGENProcesarCierreService)getWebApplicationContext().getBean("spusicc.procesoGENProcesarCierreService");
		this.mantenimientoEDUGenericoService = (MantenimientoEDUGenericoService)getWebApplicationContext().getBean("edu.mantenimientoEDUGenericoService");
		this.ajaxService = (AjaxService)getWebApplicationContext().getBean("ajaxService");
	}

	
	public ACOWebServiceResponse ejecutarProcesoGENProcesosCierreCampania(
			String codigoUsuario,
			String codigoPais,
			String codigoMarca,
			String codigoCanal,
			String codigoPeriodo) throws RemoteException {
		
		ACOWebServiceResponse objetoRespuesta = new ACOWebServiceResponse();
		boolean estado = false;
		ParametroACOWebService parametroACOWebService = null;
		SSiCC_Desfasado_InterfazExecutionResult executionResult = null;
		InterfazResult interfazResult = null;
		String mensajeError = "";		
		final String CODIGO_BATCH = "03";
		final String CODIGO_INTERFAZ = "GEN-P2";
		final String CODIGO_SISTEMA = "GEN";
		final String IND_TIPO_VALID = "2";
		final String FRECUENCIA_SGR = "3";
		final String TIPO_PROCESO = "P";
		String indicadorSeleccionInterfaces = "S";		
		String indicadorEjecucionSICC = "S";
		
		if(log.isDebugEnabled()){
			log.debug("ejecutar Proceso ejecutarProcesoGENProcesosCierreCampania");
		}
		try{
			Pais pais = this.paisService.getPais(codigoPais);
			if(StringUtils.isBlank(codigoPais) || StringUtils.isEmpty(codigoPais)){
				mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarPais", null,
				getLocaleIdioma(pais.getCodigoIdiomaIso()));
				throw new Exception(mensajeError);
			}
			
			/**
		     * Valida usuario
		     */
		    if(StringUtils.isBlank(codigoUsuario)|| StringUtils.isEmpty(codigoUsuario)){
		    	mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarUsuario",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
	        	throw new Exception(mensajeError);
		    }
			
			String conexionExterna = pais.getCodigoConexionExterna();
//			Usuario usuario = this.usuarioService.getUsuarioByUsername(codigoUsuario);
			Usuario usuario = this.obtenerUsuarioByDefault(codigoUsuario);
			
			Map criteria = new HashMap();
			
			// Asignamos al codigo del periodo el valor por defecto
	        criteria.put("codigoPais", pais.getCodigo());
	        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
		    criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente
		    criteria.put("codigoISO",usuario.getIdioma().getCodigoISO());
			criteria.put("codigoProcesoBatch", CODIGO_BATCH);
	        criteria.put("codigoSistema", CODIGO_SISTEMA);
	        criteria.put("codigoInterfaz", CODIGO_INTERFAZ);
	        criteria.put("indTipoValid", IND_TIPO_VALID);
	        criteria.put("frecuenciaSGR", FRECUENCIA_SGR);	        
	        criteria.put("indicadorProceso", TIPO_PROCESO);
	    	criteria.put("codigoUsuario", codigoUsuario);
	    	criteria.put("indicadorEjecucionInterfaces", "S");
	    	
	    	criteria.put("usuario", usuario);
	    	
	    	//
	    	criteria.put("indTipoPrev", "N");		      
	        MessageContext msgCtx = MessageContext.getCurrentContext();
	        HttpServletRequest httpServletRequest = (HttpServletRequest)msgCtx.  
	        	    getProperty("transport.http.servletRequest");  
	        criteria.put("nombreServidor", httpServletRequest.getServerName());
	        criteria.put("entorno", httpServletRequest.getContextPath());
	        //
			List siccMarcaList = this.interfazSiCCService.getMarcas();
			List siccCanalList = this.interfazSiCCService.getCanalesByCodigoISO(pais.getCodigoIdiomaIso());
				
			/**
			 * Si marca es vacio o en blanco, traer el de por defecto
			 */
			if(StringUtils.isBlank(codigoMarca) || StringUtils.isEmpty(codigoMarca)){
				codigoMarca = Constants.CODIGO_MARCA_DEFAULT;
			}else{
				if(siccMarcaList.size()!=0){
					if(!existeCodigoEnLista(siccMarcaList, codigoMarca)){
						mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.mensajeNotExisteMarca",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
						throw new Exception(mensajeError);
					}
				}else{
					log.debug("Listado de marca vacio.");
				}
			}
				
			/**
			 * Si canal es vacio o en blanco, traer el de por defecto
			 */
			if(StringUtils.isBlank(codigoCanal) || StringUtils.isEmpty(codigoCanal)){
				codigoCanal = Constants.CODIGO_CANAL_DEFAULT;				
			}else{
				if(siccCanalList.size()!=0){
					if(!existeCodigoEnLista(siccCanalList, codigoCanal)){
						mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.mensajeNotExisteCanal",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
						throw new Exception(mensajeError);
					}
				}else{
					log.debug("Listado de canal vacio.");
				}
			}			
			
			PedidoControlFacturacion controlFacturacion = mantenimientoOCRPedidoControlFacturacionService.getControlFacturacionById(criteria);
			if (StringUtils.isBlank(codigoPeriodo) || StringUtils.isEmpty(codigoPeriodo)) {
	        	codigoPeriodo = controlFacturacion.getCodigoPeriodo();
			}
			
			criteria.put("fechaFacturacion", controlFacturacion.getFechaProceso());
			criteria.put("codigoPeriodo", codigoPeriodo);
			criteria.put("codigoCanal", codigoCanal);
			criteria.put("codigoMarca", codigoMarca);
			
			criteria.put("periodoProceso", controlFacturacion.getCodigoPeriodo());
			criteria.put("fechaProceso", controlFacturacion.getFechaProceso());
			
			List lista = interfazSiCCService.getListaProcesosGenCierreCampania(criteria);			
			List listaProcesos = this.procesoGENProcesarCierreService.getProcesosCierrePeriodo(criteria);
			
			criteria.put("totalProcesosSICC", listaProcesos.size());
			listaProcesos.addAll(lista);
			boolean validacionCierre =  this.procesoGENProcesarCierreService.validaPeriodoACerrar(criteria);
			
			if(!validacionCierre) { 
				if(Constants.NO.equals(indicadorSeleccionInterfaces)) {					
					mensajeError = getWebApplicationContext().getMessage("procesoGENProcesosCierreCampaniaForm.msg.validacionPeriodoAProcesar",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
					throw new Exception(mensajeError);
				}	
			} else {
				boolean validacionRegiones =  this.procesoGENProcesarCierreService.existeRegionesSinProcesar(criteria);
				if(!validacionRegiones) {
					mensajeError = getWebApplicationContext().getMessage("procesoGENProcesosCierreCampaniaForm.msg.validacionRegionesAbiertas",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
					throw new Exception(mensajeError);
				}
			}
			
			criteria.put("indicadorEjecucionSICC", indicadorEjecucionSICC);
			criteria.put("indicadorModEducacion",this.procesoGENProcesarCierreService.getIndicadorModEducacion(codigoPais));
	        executionResult = this.executeInterfaz(criteria);
	        
	        List list = executionResult.getInterfazResults();
			interfazResult = (InterfazResult) list.get(0);
		
			if (interfazResult != null) {
				if(StringUtils.equals(this.estadoProceso, Constants.OK)){
					estado = true;
				}
				objetoRespuesta.setEjecucionExitosa(estado);
				objetoRespuesta.setMensaje(this.mensajeError);
				log.debug(objetoRespuesta.getMensaje());
				
				List<ParametroACOWebService> respuestaWebService =  new ArrayList<ParametroACOWebService>();				
				
				parametroACOWebService = new ParametroACOWebService();
				parametroACOWebService.setNombre("numeroRegistros");
				parametroACOWebService.setValor(String.valueOf(interfazResult.getRegistrosProcesados()));
				respuestaWebService.add(parametroACOWebService);
				
				parametroACOWebService = new ParametroACOWebService();
				parametroACOWebService.setNombre("nombreArchivoEntradaSalida");
				parametroACOWebService.setValor(getNombreArchivoEntradaSalida(interfazResult));
				respuestaWebService.add(parametroACOWebService);
				
				parametroACOWebService = new ParametroACOWebService();
				parametroACOWebService.setNombre("numeroLote");
				parametroACOWebService.setValor(interfazResult.getNumeroLote());
				respuestaWebService.add(parametroACOWebService);
				
				objetoRespuesta.setRespuestaWebService(respuestaWebService.toArray(new ParametroACOWebService[respuestaWebService.size()]));				
			}
	     
		}catch (Exception e) {			
			log.error(e.getMessage());
			objetoRespuesta.setMensaje(e.getMessage());
			objetoRespuesta.setRespuestaWebService(null);			
			objetoRespuesta.setEjecucionExitosa(estado);
		}finally{
			log.debug("Estado del servicio: " + estado);
			if(estado){
				log.info("Se ejecuto el servicio con exito.");					
			}else{
				log.error("Excepcion en el servicio.");				
			}						
		}	
		return objetoRespuesta;
	}	
	
	protected Map executeProcessBeforeInterfaz(Map params) throws Exception {
		String indicadorEjecucionSICC = (String)params.get("indicadorEjecucionSICC");
		if(Constants.SI.equals(indicadorEjecucionSICC)) {			
			this.procesoGENProcesarCierreService.executeProcesarCierrePeriodo(params);
		}
		return super.executeProcessBeforeInterfaz(params);
	}


	protected boolean continueExecuteInterfaz(Map params) {
		boolean flag = true;
		
		String indicadorSeleccion = (String)params.get("indicadorSeleccionInterfaces");
		if(Constants.SI.equals(indicadorSeleccion)) {
			String indicadorEjecucionInterfaces = (String)params.get("indicadorEjecucionInterfaces");
			
			if(Constants.SI.equals(indicadorEjecucionInterfaces)) 
				flag = true;
			else
				flag = false;
		}
		return super.continueExecuteInterfaz(params);
	}


	protected Map prepareParamsBeforeExecute(Map params, Pais pais) throws Exception {
		if(Constants.NO.equals((String)params.get("indicadorEjecucionSICC"))) {
			Integer contCierreCampa = interfazSiCCService.getContCierreCampaByCodigoPeriodo((String)params.get("codigoPeriodo"));
			if(contCierreCampa == 0){				
				Object obj[] = {(String)params.get("codigoPeriodo")};
				mensajeError = getWebApplicationContext().getMessage("procesos.gen.mensaje.error.cierrePeriodo",obj,getLocaleIdioma(pais.getCodigoIdiomaIso()));
				throw new Exception(mensajeError);
			}
		}else{
			Map criteria = new HashMap();
			criteria.put("codigoPeriodo", (String)params.get("codigoPeriodo"));
			boolean validacionCierre =  this.procesoGENProcesarCierreService.validaPeriodoACerrar(criteria);
			
			if(!validacionCierre) {
				mensajeError = getWebApplicationContext().getMessage("procesoGENProcesosCierreCampaniaForm.msg.validacionPeriodoAProcesar",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
				throw new Exception(mensajeError);
			}
		}
		
		Usuario usuario = (Usuario)params.get("usuario");
		
		SimpleJaxWsServiceExporter exporter = new SimpleJaxWsServiceExporter();
		log.debug(exporter.DEFAULT_BASE_ADDRESS);
		
		Pattern pattern = Pattern.compile("(https?://)([^:^/]*)(:\\d*)?(.*)?");
		Matcher matcher = pattern.matcher(exporter.DEFAULT_BASE_ADDRESS);

		matcher.find();			
		String servidor = matcher.group(2);
		
		params.put("codigoRegion", "-1");
		/* INI JJ  PER-SiCC-2012-0361 */
		params.put("login", usuario.getLogin());
		
		/* FIN JJ  PER-SiCC-2012-0361 */
		EmpresaComercializadora empresaComercializadora = new EmpresaComercializadora();
		empresaComercializadora.setCodigoPais(pais.getCodigo());
		empresaComercializadora.setEstadoRegistro(Constants.ESTADO_ACTIVO);
		String 	indicadorModEducacion = (String)params.get("indicadorModEducacion");
		if(indicadorModEducacion.equals(Constants.NUMERO_UNO)){
			params.put("codigoEmpresa", ((EmpresaComercializadora)mantenimientoEDUGenericoService.getEmpresasComercializadorasByPais(empresaComercializadora).get(0)).getCodigoEmpresa());
			
			LabelValue [] lv= this.ajaxService.getRegionesEDUByPaisEmpresa(pais.getCodigo(),(String)params.get("codigoEmpresa"));
			String [] regionList = getRegionList(lv);
			
			params.put("regionList", regionList);
		}
				
		params.put("tipoProceso", Constants.CODIGO_TIPO_CIERRE_PERIODO);
		
		return super.prepareParamsBeforeExecute(params, pais);
	}
	
	/**
	 * Obtengo la lista de solo codgos de regiones
	 * @param lv
	 * @return
	 */
	private String[] getRegionList(LabelValue[] lv) {
		String [] regionList = new String[lv.length];
		for(int i=0;i<lv.length;i++){
			LabelValue l =  lv[i];
			regionList[i]=l.getValue();
		}
		log.debug("list region "+regionList);
		return regionList;
	}
	
}
