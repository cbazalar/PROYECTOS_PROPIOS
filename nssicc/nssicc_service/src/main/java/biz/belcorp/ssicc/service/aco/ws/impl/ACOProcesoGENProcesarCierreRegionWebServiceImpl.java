package biz.belcorp.ssicc.service.aco.ws.impl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.aco.ws.ACOProcesoGENProcesarCierreRegionWebService;
import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;
import biz.belcorp.ssicc.service.aco.ws.beans.ParametroACOWebService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazResult;
import biz.belcorp.ssicc.service.sisicc.framework.beans.SSiCC_Desfasado_InterfazExecutionResult;
import biz.belcorp.ssicc.service.spusicc.fac.MantenimientoFACGenericoService;
import biz.belcorp.ssicc.service.spusicc.gen.ProcesoGENProcesarCierreService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.util.StringUtil;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ACOProcesoRECEnviaMsgReclamoWebServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cbazalar@sigcomt.com">Carlos Bazalar</a>
 */
public class ACOProcesoGENProcesarCierreRegionWebServiceImpl extends BaseInterfazHiloAbstractWebService implements ACOProcesoGENProcesarCierreRegionWebService {

	ProcesoGENProcesarCierreService procesoGENProcesarCierreService;
	InterfazSiCCService interfazSiCCService;
	MantenimientoOCRPedidoControlFacturacionService mantenimientoOCRPedidoControlFacturacionService;
	MantenimientoFACGenericoService mantenimientoFACGenericoService;
	
	protected void onInit() throws ServiceException {
		super.onInit();
		this.interfazSiCCService = (InterfazSiCCService) getWebApplicationContext().getBean("sisicc.interfazSiCCService");
		this.mantenimientoOCRPedidoControlFacturacionService = (MantenimientoOCRPedidoControlFacturacionService)getWebApplicationContext().getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		this.procesoGENProcesarCierreService = (ProcesoGENProcesarCierreService)getWebApplicationContext().getBean("spusicc.procesoGENProcesarCierreService");
		this.mantenimientoFACGenericoService = (MantenimientoFACGenericoService)getWebApplicationContext().getBean("spusicc.mantenimientoFACGenericoService");
	}

	
	public ACOWebServiceResponse ejecutarProcesoGENProcesarCierreRegion(
			String codigoUsuario,
			String codigoPais,
			String codigoMarca,
			String codigoCanal,
			String codigoPeriodo,
			String fechaFacturacion,
			String actualizarRegiones) throws RemoteException {
		
		ACOWebServiceResponse objetoRespuesta = new ACOWebServiceResponse();
		boolean estado = false;
		ParametroACOWebService parametroACOWebService = null;
		SSiCC_Desfasado_InterfazExecutionResult executionResult = null;
		InterfazResult interfazResult = null;
		String mensajeError = "";		
		final String CODIGO_BATCH = "05";
		final String CODIGO_INTERFAZ = "GEN-P6";
		final String CODIGO_SISTEMA = "GEN";
		final String FRECUENCIA_SGR = "2";
		final String TIPO_PROCESO = "R";		
		
		if(log.isDebugEnabled()){
			log.debug("ejecutar Proceso Interfaz ejecutarProcesoGENProcesarCierreRegion");
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
		    
			Usuario usuario = this.obtenerUsuarioByDefault(codigoUsuario);
			
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
				codigoCanal = Constants.CODIGO_MARCA_DEFAULT;				
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
			
			Map criteria = new HashMap();
			
			// Asignamos al codigo del periodo el valor por defecto
	        criteria.put("codigoPais", pais.getCodigo());
	        criteria.put("codigoISO",usuario.getIdioma().getCodigoISO());
			criteria.put("codigoUsuario", codigoUsuario);
		    
			criteria.put("codigoProcesoBatch", CODIGO_BATCH);
	        criteria.put("codigoSistema", CODIGO_SISTEMA);
	        criteria.put("codigoInterfaz", CODIGO_INTERFAZ);
	        criteria.put("frecuenciaSGR", FRECUENCIA_SGR);
	        criteria.put("tipoProceso", TIPO_PROCESO);
	        criteria.put("tipoCierre", Constants.CODIGO_TIPO_CIERRE_REGION);
	        criteria.put("indicadorEjecucionSICC", Constants.SI);
	        
	        List lista = interfazSiCCService.getListaProcesosGenCierreCampania(criteria);
			
	        if(!"1".equals(actualizarRegiones)) {
	        	criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
			    criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente 
	        
			    PedidoControlFacturacion controlFacturacion = (PedidoControlFacturacion) this.mantenimientoOCRPedidoControlFacturacionService.getControlFacturacionById(criteria);
			
		        if (StringUtils.isBlank(codigoPeriodo) || StringUtils.isEmpty(codigoPeriodo)) {
		        	codigoPeriodo = controlFacturacion.getCodigoPeriodo();
				}    
		        
		        if(StringUtils.isBlank(fechaFacturacion) || StringUtils.isEmpty(fechaFacturacion)){
		        	fechaFacturacion = controlFacturacion.getFechaProceso();
		        }
	        }
	        
	        criteria.put("codigoPeriodo", codigoPeriodo);
	        criteria.put("fechaFacturacion", fechaFacturacion);
	        
	        List listaProcesosTemp = procesoGENProcesarCierreService.getProcesosCierreRegion(criteria);
	        List listaProcesos = new ArrayList();
			Base base;
			for(int i=0;i<listaProcesosTemp.size();i++){
				Map baseResult = (HashMap)listaProcesosTemp.get(i);
				
				base = new Base();
				base.setCodigo((String)baseResult.get("value"));
				base.setDescripcion((String)baseResult.get("label"));
				
				listaProcesos.add(base);
			}
			
			criteria.put("totalProcesosSICC", listaProcesos.size());
			listaProcesos.addAll(lista);
			criteria.put("genInterfazPaquete", listaProcesos);
	        
	        List regiones =  procesoGENProcesarCierreService.getRegionesACerrar(criteria);
	        criteria.put("siccRegionList", regiones);
	        
	        if(regiones.size() == 0) {
	        	mensajeError = getWebApplicationContext().getMessage("procesoGENProcesarCierreRegionForm.msg.validacionRegionesAProcesar.procesoSatisfactorio",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
				estado=true;
	        	throw new Exception(mensajeError);
			} else {
				for(int i=0; i<regiones.size(); i++) {
					Map mapRegion = (Map)regiones.get(i);
					String oidRegion = mapRegion.get("value").toString();
					String descripcionRegion = mapRegion.get("label").toString();
					
					criteria.put("oidRegion", oidRegion);
					boolean validar = procesoGENProcesarCierreService.existeZonasxRegionSinProcesar(criteria);
					
					if(!validar) {
						mensajeError = getWebApplicationContext().getMessage("procesoGENProcesarCierreRegionForm.msg.validacionZonasAbiertas", null,
								getLocaleIdioma(pais.getCodigoIdiomaIso()));
						throw new Exception(mensajeError);
					}  
				}				
			}
	        	        
	        criteria.put("codigoMarca", codigoMarca);
	        criteria.put("codigoCanal", codigoCanal);
	        
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
	
	protected Map prepareParamsBeforeExecute(Map params, Pais pais) throws Exception {
		
		List regiones = (List)params.get("siccRegionList");
		Long []oidRegiones = new Long[regiones.size()];
		String []codigoRegiones = new String[regiones.size()];
		
		for(int i=0; i<regiones.size(); i++) {
			Map mapRegion = (Map)regiones.get(i);
			oidRegiones[i] = new Long(mapRegion.get("value").toString());
			codigoRegiones[i] = mantenimientoFACGenericoService.getCodigoRegionByOidRegion(Integer.valueOf(oidRegiones[i].toString()));
		}
		
		Integer contCierreZona;
		String indicadorEjecucionSICC = MapUtils.getString(params, "indicadorEjecucionSICC");
		if(Constants.NO.equals(indicadorEjecucionSICC)) {
			for(int i=0;i<oidRegiones.length;i++){
				contCierreZona = interfazSiCCService.getContCierreRegionByCodigoPeriodoOidRegion((String)params.get("codigoPeriodo"),
													Integer.valueOf(oidRegiones[i].toString()));
				if(contCierreZona == 0){
					
					String codigoRegionNoCerrada = mantenimientoFACGenericoService.getCodigoRegionByOidRegion(Integer.valueOf(oidRegiones[i].toString()));
					
					String parametrosMsg[] = {codigoRegionNoCerrada,(String)params.get("codigoPeriodo")};
					String mensaje = getWebApplicationContext().getMessage("procesoGENProcesarCierreRegionForm.msg.validacionCierreRegion", parametrosMsg,
							getLocaleIdioma(pais.getCodigoIdiomaIso()));
					
					throw new Exception(mensaje);
				}
			}
		}
		
		String tipoProceso = (String) params.get("tipoProceso");
		params.put("tipoProceso", tipoProceso);
		params.put("regionList", oidRegiones);
		params.put("codigoRegionList", codigoRegiones);
		
		if(codigoRegiones != null && codigoRegiones.length > 0) {
			params.put("regionListAux", StringUtil.obtieneListaCodigos(codigoRegiones, "", ""));
		}
		
		return super.prepareParamsBeforeExecute(params, pais);
	}


	@Override
	protected Map executeProcessBeforeInterfaz(Map params) throws Exception {
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'executeProcessBeforeInterfaz' method");
		}

		params = super.executeProcessBeforeInterfaz(params);	
		String indicadorEjecucionSICC = (String)params.get("indicadorEjecucionSICC");
		if(Constants.SI.equals(indicadorEjecucionSICC)) {
			procesoGENProcesarCierreService.executeProcesarCierreRegion(params);
		}
		
		return params;
		
	}
	
}
