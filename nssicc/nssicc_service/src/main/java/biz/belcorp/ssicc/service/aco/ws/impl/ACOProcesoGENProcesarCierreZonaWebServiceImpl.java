/*
 * Created on 07/05/2007 10:59:05 AM biz.belcorp.ssicc.sisicc.web.ws.InterfazWebService
 */
package biz.belcorp.ssicc.service.aco.ws.impl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.aco.ws.ACOProcesoGENProcesarCierreZonaWebService;
import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;
import biz.belcorp.ssicc.service.aco.ws.beans.ParametroACOWebService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazResult;
import biz.belcorp.ssicc.service.sisicc.framework.beans.SSiCC_Desfasado_InterfazExecutionResult;
import biz.belcorp.ssicc.service.spusicc.fac.MantenimientoFACGenericoService;
import biz.belcorp.ssicc.service.spusicc.gen.ProcesoGENProcesarCierreService;
import biz.belcorp.ssicc.service.util.StringUtil;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ACOProcesoGENProcesarGP3WebServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 */
public class ACOProcesoGENProcesarCierreZonaWebServiceImpl extends BaseInterfazHiloAbstractWebService implements ACOProcesoGENProcesarCierreZonaWebService {

	
	ProcesoGENProcesarCierreService procesoGENProcesarCierreService;
	InterfazSiCCService interfazSiCCService;
	MantenimientoFACGenericoService mantenimientoFACGenericoService;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.remoting.jaxrpc.ServletEndpointSupport#onInit()
	 */
	protected void onInit() throws ServiceException {
		super.onInit();
		this.procesoGENProcesarCierreService = (ProcesoGENProcesarCierreService) getWebApplicationContext().getBean("spusicc.procesoGENProcesarCierreService");
		this.interfazSiCCService = (InterfazSiCCService) getWebApplicationContext().getBean("sisicc.interfazSiCCService");
		this.mantenimientoFACGenericoService = (MantenimientoFACGenericoService) getWebApplicationContext().getBean("spusicc.mantenimientoFACGenericoService");
	}

   
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.ACOProcesoGENProcesarCierreZonaWebService#ejecutarProcesoGENProcesarCierreZona(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public ACOWebServiceResponse ejecutarProcesoGENProcesarCierreZona(String codigoPais,
			String codigoUsuario,
			String marca,
			String canal,
			String fechaFacturacion,
			String codigoPeriodo)throws RemoteException{
		ACOWebServiceResponse objetoRespuesta = new ACOWebServiceResponse();
		boolean estado = false;
		ParametroACOWebService parametroACOWebService = null;
		SSiCC_Desfasado_InterfazExecutionResult executionResult = null;
		InterfazResult interfazResult = null;
		String mensajeError = "";
		String CODIGO_BATCH = "04";
		String INDICADOR_PROCESO = "Z";
		String CODIGO_INTERFAZ = "GEN-P5";
		String CODIGO_SISTEMA = "GEN";
		
		if(log.isDebugEnabled()){
			log.debug("ejecutarProcesoINCEjecutarProcesos");
		}
		
		try{
			Pais pais = this.paisService.getPais(codigoPais);
			if(StringUtils.isBlank(codigoPais) || StringUtils.isEmpty(codigoPais)){
				mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarPais", null,
				getLocaleIdioma(pais.getCodigoIdiomaIso()));
				throw new Exception(mensajeError);
			}
		
			if (StringUtils.isBlank(codigoUsuario) || StringUtils.isEmpty(codigoUsuario)) {
				mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarUsuario", null,
				getLocaleIdioma(pais.getCodigoIdiomaIso()));
				throw new Exception(mensajeError);
			}
		
			List siccMarcaList = this.interfazSiCCService.getMarcas();
			List siccCanalList = this.interfazSiCCService.getCanalesByCodigoISO(pais.getCodigoIdiomaIso());
		
			/**
			 * Si marca es vacio o en blanco, traer el de por defecto
			 */
			if(StringUtils.isBlank(marca) || StringUtils.isEmpty(marca)){
				marca = Constants.CODIGO_MARCA_DEFAULT;				
			}else{
				if(siccMarcaList.size()!=0){
					if(!existeCodigoEnLista(siccMarcaList, marca)){
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
			if(StringUtils.isBlank(canal) || StringUtils.isEmpty(canal)){
				canal = Constants.CODIGO_CANAL_DEFAULT;
			}else{
				if(siccCanalList.size()!=0){
					/**
					* Si canal no existe en la lista, enviar execpcion.
					*/
					if(!existeCodigoEnLista(siccCanalList, canal)){
						mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.mensajeNotExisteCanal",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
						throw new Exception(mensajeError);
					}
				}else{
					log.debug("Listado de canal vacio.");
				}
			}
		
			Map criteria = new HashMap();
			criteria.put("codigoPais", codigoPais);
			criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
			criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente
		
			PedidoControlFacturacion controlFacturacion = this.mantenimientoOCRPedidoControlFacturacionService.getControlFacturacionById(criteria);
		
			if (StringUtils.isBlank(codigoPeriodo)) {
				codigoPeriodo = controlFacturacion.getCodigoPeriodo();
			}
		
			if (StringUtils.isBlank(fechaFacturacion)) {
				fechaFacturacion = controlFacturacion.getFechaProceso();
			}
		
			criteria.put("codigoPeriodo", codigoPeriodo);
			criteria.put("fechaFacturacion", fechaFacturacion);
			criteria.put("tipoCierre", Constants.CODIGO_TIPO_CIERRE_ZONA);
		
			List regiones =  this.procesoGENProcesarCierreService.getRegionesACerrar(criteria);
			List zonas =  this.procesoGENProcesarCierreService.getZonasACerrar(criteria);
		
			criteria.put("codigoProcesoBatch", CODIGO_BATCH);
			criteria.put("codigoSistema", CODIGO_SISTEMA);
			criteria.put("codigoInterfaz", CODIGO_INTERFAZ);
			criteria.put("codigoUsuario", codigoUsuario);
			criteria.put("descripcion", getDescripcionInterfaz(criteria));
			criteria.put("zonas",zonas);
			criteria.put("regiones",regiones);
			criteria.put("codigoMarca", marca);
			criteria.put("codigoCanal", canal);

			criteria.put("indicadorProceso", INDICADOR_PROCESO);
			criteria.put("indicadorEjecucionSICC", Constants.SI);	
			if(zonas == null || zonas.size() == 0) { 
				mensajeError = getWebApplicationContext().getMessage("procesoGENProcesarCierreZonaForm.msg.procesoSatisfactorio",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
				estado=true;
				throw new Exception(mensajeError);
			} 
			
			executionResult = this.executeInterfaz(criteria);
		
			List list = executionResult.getInterfazResults();
			interfazResult = (InterfazResult) list.get(0);
		
			if (interfazResult != null) {
				if(StringUtils.equals(this.estadoProceso, Constants.OK)){
					estado = true;
				}
				objetoRespuesta.setEjecucionExitosa(estado);
				objetoRespuesta.setMensaje(interfazResult.getMensaje());
				
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


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.impl.BaseInterfazHiloAbstractWebService#prepareParamsBeforeExecute(java.util.Map, biz.belcorp.ssicc.model.Pais)
	 */
	protected Map prepareParamsBeforeExecute(Map params, Pais pais) throws Exception {
        params = super.prepareParamsBeforeExecute(params, pais);
			
		List zonas = (List) params.get("zonas");
		
		Long []oidZonas = new Long[zonas.size()];
		String []codigoZonas = new String[zonas.size()];
		String oidZonasString[] = new String[zonas.size()];
		
		for(int i=0; i<zonas.size(); i++) {
			Map mapZona = (Map)zonas.get(i);
			oidZonas[i] = new Long(mapZona.get("value").toString());
			oidZonasString[i] = new String(mapZona.get("value").toString());
			
			codigoZonas[i] = this.mantenimientoFACGenericoService.getCodigoZonaByOidZona(Integer.valueOf(oidZonas[i].toString()));
		}
		
		Integer contCierreZona;
		String indicadorEjecucionSICC = (String) params.get("indicadorEjecucionSICC");
		if(Constants.NO.equals(indicadorEjecucionSICC)) {
			
			for(int i=0;i<oidZonas.length;i++){
				contCierreZona = this.interfazSiCCService.getContCierreZonaByCodigoPeriodoOidZona((String)params.get("codigoPeriodo"),Integer.valueOf(oidZonas[i].toString()));
				if(contCierreZona == 0){
					
					String codigoZonaNoCerrada = this.mantenimientoFACGenericoService.getCodigoZonaByOidZona(Integer.valueOf(oidZonas[i].toString()));
					
					String parametrosMsg[] = {codigoZonaNoCerrada,(String)params.get("codigoPeriodo")};
					String mensaje = getWebApplicationContext().getMessage("procesoGENProcesarCierreZonaForm.msg.validacionCierreZona",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
				    throw new Exception(mensaje);
				}
			}
		} 
		params.put("zonaList", oidZonas);
		if(oidZonasString != null && oidZonasString.length > 0) {
			params.put("zonaListAux", StringUtil.obtieneListaCodigos(codigoZonas, "", ""));
		}
		String codigoInterfaz = (String) params.get("codigoInterfaz");
		params.put("codigoPaquete", codigoInterfaz);
		return params;
		
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.impl.BaseInterfazHiloAbstractWebService#executeProcessBeforeInterfaz(java.util.Map)
	 */
	protected Map executeProcessBeforeInterfaz(Map params) throws Exception {
		params = super.executeProcessBeforeInterfaz(params);
		log.debug("Exectuting action : executeProcess.");
		
		String indicadorEjecucionSICC = (String)params.get("indicadorEjecucionSICC");
		/*if(Constants.SI.equals(indicadorEjecucionSICC)) {
			this.procesoGENProcesarCierreService.executeProcesarCierreZona(params);
		}*/
		return params;
	}

}
