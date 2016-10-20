package biz.belcorp.ssicc.service.aco.ws.impl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import org.apache.commons.beanutils.BeanPredicate;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.functors.EqualPredicate;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.aco.ws.ACOProcesoRECProductosReclamadosWebService;
import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;
import biz.belcorp.ssicc.service.aco.ws.beans.ParametroACOWebService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazResult;
import biz.belcorp.ssicc.service.sisicc.framework.beans.SSiCC_Desfasado_InterfazExecutionResult;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECOperacionService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ACOProcesoRECProductosReclamadosWebServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@sigcomt.com">Ivan Tocto</a>
 */
public class ACOProcesoRECProductosReclamadosWebServiceImpl extends BaseInterfazHiloAbstractWebService implements ACOProcesoRECProductosReclamadosWebService {

	InterfazSiCCService interfazSiCCService;
	MantenimientoRECOperacionService mantenimientoRECOperacionService;
	AjaxService ajaxService;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.impl.BaseInterfazHiloAbstractWebService#onInit()
	 */
	@Override
	protected void onInit() throws ServiceException {
		super.onInit();
		interfazSiCCService = (InterfazSiCCService) getWebApplicationContext().getBean("sisicc.interfazSiCCService");
		mantenimientoRECOperacionService = (MantenimientoRECOperacionService) getWebApplicationContext().getBean("spusicc.reclamos.mantenimientoRECOperacionService");
		ajaxService = (AjaxService) getWebApplicationContext().getBean("ajaxService");
	}	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.ACOProcesoRECProductosReclamadosWebService#ejecutarProcesoRECProductosReclamados(java.lang.String, java.lang.String[], java.lang.String, java.lang.String, java.lang.String[], java.lang.String[], java.lang.String, java.lang.String, java.lang.String)
	 */
	public ACOWebServiceResponse ejecutarProcesoRECProductosReclamados(
			String codigoPais, String[] subAccesos, String tipoIngreso,
			String operacion, String[] regiones, String[] zonas,
			String fechaInicio, String fechaFin, String codigoUsuario)
			throws RemoteException {

		ACOWebServiceResponse objetoRespuesta = new ACOWebServiceResponse();
		boolean estado = false;
		ParametroACOWebService parametroACOWebService = null;
		SSiCC_Desfasado_InterfazExecutionResult executionResult = null;
		InterfazResult interfazResult = null;
		String mensajeError = "";
		String CODIGO_BATCH = Constants.REC_CODIGO_PROCESO_BATCH_ENVIAR_PRODUCTO_RECLAMADOS_P1;
		String CODIGO_INTERFAZ = Constants.REC_CODIGO_INTERFAZ_ENVIAR_PRODUCTO_RECLAMADOS_P1;
		String CODIGO_SISTEMA = Constants.CODIGO_SISTEMA_REC;
		
		Usuario usuario = this.obtenerUsuarioByDefault(codigoUsuario);

		if(log.isDebugEnabled()){
			log.debug("ejecutar ejecutarProcesoRECProductosReclamados");
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
			
	        // subAccesos
	        if(subAccesos != null && subAccesos.length > 0)
	        {
	        	List subAccesosList = interfazSiCCService.getInterfazRECProductosReclamadosSubAccesos(null);
	        	
				for(int i=0; i<subAccesos.length; i++)
				{
					EqualPredicate nameEqlPredicate = new EqualPredicate(subAccesos[i]);
					BeanPredicate beanPredicate = new BeanPredicate("codigo", nameEqlPredicate);					
					if(subAccesosList.size() != 0)
					{
						if(!CollectionUtils.exists(subAccesosList, beanPredicate))
						{
							mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.mensajeNotExisteCodigoSubAcceso",new String[]{subAccesos[i]},getLocaleIdioma(pais.getCodigoIdiomaIso()));
							throw new Exception(mensajeError);
						}
					}
					else
					{
						log.debug("Listado de codigo de tipoDocumento vacio.");
					}
				}				
	        }
	        //
	        
	        // validar tipoIngreso
	        if(StringUtils.isNotBlank(tipoIngreso))
	        {
		        Map criteriaTipoIngreso = new HashMap();
		        criteriaTipoIngreso.put("codigoISO", usuario.getIdioma().getCodigoISO());
		        criteriaTipoIngreso.put("codigoTipoIng", Constants.CODIGO_TIPO_INGRESO_M);

		        List tipoIngresoList = interfazSiCCService.getTiposIngresoByCodigoISO(criteriaTipoIngreso);
		        
		        if(!this.existeCodigoEnLista(tipoIngresoList, tipoIngreso))
		        {
					mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.mensajeNotExisteTipoIngreso",new String[]{tipoIngreso},getLocaleIdioma(pais.getCodigoIdiomaIso()));
					throw new Exception(mensajeError);
		        }
	        }
	        //
	        
	        // validar operacion
	        if(StringUtils.isNotBlank(operacion))
	        {
	            Map criteriaOperacion = new HashMap();
	            criteriaOperacion.put("codigoPais", codigoPais);
	            List operacionList = this.mantenimientoRECOperacionService.getOperacionesHomologadasByCodigoPais(criteriaOperacion);
		        
		        if(!this.existeCodigoEnLista(operacionList, operacion))
		        {
					mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.mensajeNotExisteOperacion",new String[]{operacion},getLocaleIdioma(pais.getCodigoIdiomaIso()));
					throw new Exception(mensajeError);
		        }
	        }
	        //
	        
	        // validar regionList
			if(regiones != null && regiones.length > 0)
			{
				LabelValue []listaRegion = ajaxService.getRegionesByPais(codigoPais);
				List regionList = Arrays.asList(listaRegion); 
						
				for(int i=0; i<regiones.length; i++)
				{
					EqualPredicate nameEqlPredicate = new EqualPredicate(regiones[i]);
					BeanPredicate beanPredicate = new BeanPredicate("value", nameEqlPredicate);					
					if(regionList != null && regionList.size() != 0)
					{
						if(!CollectionUtils.exists(regionList, beanPredicate))
						{
							mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.mensajeNotExisteCodigoRegion.1",new String[]{regiones[i]},getLocaleIdioma(pais.getCodigoIdiomaIso()));
							throw new Exception(mensajeError);
						}
					}
					else
					{
						log.debug("Listado de codigo de region vacio.");
					}
				}				
			}
			//
	        
	        // Validar zonaList
			if(zonas != null && zonas.length > 0)
			{
				LabelValue[] zonasRegiones = ajaxService.getZonasMultipleByPaisMarcaCanalRegion(codigoPais, Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, regiones, "T");				
				List zonasRegionesList = new ArrayList(); 
				zonasRegionesList.addAll(Arrays.asList(zonasRegiones));
				for(int i=0; i<zonas.length; i++){
					EqualPredicate nameEqlPredicate = new EqualPredicate(zonas[i]);
					BeanPredicate beanPredicate = new BeanPredicate("value", nameEqlPredicate);					
					if(zonasRegionesList.size() != 0){
						if(!CollectionUtils.exists(zonasRegionesList, beanPredicate)){
							mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.mensajeNotExisteCodigoZona.1",new String[]{zonas[i]},getLocaleIdioma(pais.getCodigoIdiomaIso()));
							throw new Exception(mensajeError);
						}
					}else{
						log.debug("Listado de codigo de zona vacio.");
					}
				}				
			}
	        //			
			
	        //validar fechas
			if(StringUtils.isBlank(fechaInicio) || StringUtils.isEmpty(fechaInicio)){
				mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarFechaInicio", null,
				getLocaleIdioma(pais.getCodigoIdiomaIso()));
				throw new Exception(mensajeError);
			}

			if(StringUtils.isBlank(fechaFin) || StringUtils.isEmpty(fechaFin)){
				mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarFechaFin", null,
				getLocaleIdioma(pais.getCodigoIdiomaIso()));
				throw new Exception(mensajeError);
			}
			
			Integer c = DateUtil.compararFechas(fechaInicio, fechaFin);
			
			if(c > 0)
			{
				mensajeError = getWebApplicationContext().getMessage("errors.compare.dates", null,
				getLocaleIdioma(pais.getCodigoIdiomaIso()));
				throw new Exception(mensajeError);
			}
			//			
	        
			Map criteria = new HashMap();
			
			// Asignamos al codigo del periodo el valor por defecto
	        criteria.put("codigoPais", pais.getCodigo());
			criteria.put("codigoUsuario", codigoUsuario);
			criteria.put("codigoProcesoBatch", CODIGO_BATCH);
	        criteria.put("codigoSistema", CODIGO_SISTEMA);
	        criteria.put("codigoInterfaz", CODIGO_INTERFAZ);
	        criteria.put("tipoIngreso", tipoIngreso);
	        criteria.put("operacion", operacion);
	        criteria.put("usuario", usuario);
	        criteria.put("fechaInicio", fechaInicio);
	        criteria.put("fechaFin", fechaFin);
	        
	        criteria.put("regionList",(regiones == null) ? new ArrayList(): Arrays.asList(regiones));
	        criteria.put("zonaList",(zonas == null) ? new ArrayList(): Arrays.asList(zonas));
	        
	        if (subAccesos != null && subAccesos.length > 0 && subAccesos[0].equalsIgnoreCase(Constants.REC_SUBACCESO_DEFAULT) ){
	        	criteria.put("subacList",new ArrayList());
	        	criteria.put("subacGZ",Constants.NUMERO_UNO);        	
	        }
	        else 
	        {
	        	criteria.put("subacList", (subAccesos == null) ? new ArrayList() : Arrays.asList(subAccesos));
	        	criteria.put("subacGZ",null);            
	        }
	        
	        Map criteriaOperacion = new HashMap();
	        criteriaOperacion.put("codigoPais", codigoPais);
	        criteriaOperacion.put("codigoOperacionHomologada", operacion);        	        
	        
	        criteria.put("operacionList", mantenimientoRECOperacionService.getOperacionesByOperacionHomologada(criteriaOperacion));
	        
	        if (StringUtils.equals(operacion, "E")){
	        	criteria.put("errorSacado", "SI");
	        }else{
	        	criteria.put("errorSacado", null);
	        }
	        
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

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.impl.BaseInterfazHiloAbstractWebService#prepareParamsBeforeExecute(java.util.Map, biz.belcorp.ssicc.model.Pais)
	 */
	@Override
	protected Map prepareParamsBeforeExecute(Map params, Pais pais) throws Exception {
		
       //Se obtiene el numero de secuencia de la sesion para asignarlo al usuario
        String numSecUsuario = mantenimientoRECOperacionService.getNumeroSecuenciaUsuario();
        //Guardamos el numero de secuencia en el map
        params.put("numeroSecuencia", numSecUsuario);


		return super.prepareParamsBeforeExecute(params, pais);
	}	
	
	
}
