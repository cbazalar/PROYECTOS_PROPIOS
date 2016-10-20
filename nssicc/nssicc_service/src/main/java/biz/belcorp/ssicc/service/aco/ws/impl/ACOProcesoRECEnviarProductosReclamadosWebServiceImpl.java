/**
 * 
 */
package biz.belcorp.ssicc.service.aco.ws.impl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import org.apache.commons.beanutils.BeanPredicate;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.collections.functors.EqualPredicate;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.aco.ws.ACOProcesoRECEnviarProductosReclamadosWebService;
import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;
import biz.belcorp.ssicc.service.aco.ws.beans.ParametroACOWebService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazResult;
import biz.belcorp.ssicc.service.sisicc.framework.beans.SSiCC_Desfasado_InterfazExecutionResult;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECOperacionService;

/**
 * 
 * TODO Include class description here. 
 * 
 * <p>
 * <a href="ACOProcesoRECEnviarProductosReclamadosWebServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@sigcomt.com">Ivan Tocto Jaimes</a>
 */

public class ACOProcesoRECEnviarProductosReclamadosWebServiceImpl extends
		BaseInterfazHiloAbstractWebService implements
		ACOProcesoRECEnviarProductosReclamadosWebService {

	MantenimientoRECOperacionService mantenimientoRECOperacionService;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.impl.BaseInterfazHiloAbstractWebService#onInit()
	 */
	@Override
	protected void onInit() throws ServiceException {
		super.onInit();
		mantenimientoRECOperacionService = (MantenimientoRECOperacionService) getApplicationContext().getBean("spusicc.reclamos.mantenimientoRECOperacionService");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.ACOProcesoRECEnviarProductosReclamadosWebService#ejecutarProceso(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public ACOWebServiceResponse ejecutarProceso(String codigoPais,
			String operacion, String fechaInicio, String fechaFin,
			String codigoUsuario) throws RemoteException {

		ACOWebServiceResponse objetoRespuesta = new ACOWebServiceResponse();
		boolean estado = false;
		ParametroACOWebService parametroACOWebService = null;
		SSiCC_Desfasado_InterfazExecutionResult executionResult = null;
		InterfazResult interfazResult = null;
		String mensajeError = "";		
		final String CODIGO_BATCH = Constants.REC_CODIGO_PROCESO_BATCH_PRODUCTOS_RECLAMADOS;
		final String CODIGO_INTERFAZ = Constants.REC_CODIGO_INTERFAZ_PRODUCTOS_RECLAMADOS;
		final String CODIGO_SISTEMA = Constants.CODIGO_SISTEMA_REC;
		
		if(log.isDebugEnabled()){
			log.debug("ejecutarProceso");
		}
		try{
			Pais pais = this.paisService.getPais(codigoPais);
			if(StringUtils.isBlank(codigoPais) || StringUtils.isEmpty(codigoPais)){
				mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarPais", null,
				getLocaleIdioma(pais.getCodigoIdiomaIso()));
				throw new Exception(mensajeError);
			}
			
			 /**
		     * Valida operacion
		     */
	        Map criteriaOperacion = new HashMap();
	        criteriaOperacion.put("codigoPais", codigoPais);			
	        List operaciones = mantenimientoRECOperacionService.getOperacionesHomologadasByCodigoPais(criteriaOperacion);
			
			if(StringUtils.isBlank(operacion) || StringUtils.isEmpty(operacion)){
				operacion = Constants.CODIGO_OPERACION_FALTANTE_NMPS;
			}else{
				EqualPredicate nameEqlPredicate = new EqualPredicate(operacion);
				BeanPredicate beanPredicate = new BeanPredicate("codigo", nameEqlPredicate);					
				if(operaciones.size()!=0){
					if(!CollectionUtils.exists(operaciones, beanPredicate)){
						mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.mensajeNotExisteCodigoOperacion",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
						throw new Exception(mensajeError);
					}
				}else{
					log.debug("Listado de codigo de empresa vacio.");
				}
			}

			 /**
		     * Valida fechaInicio
		     */
		    if(StringUtils.isBlank(fechaInicio)|| StringUtils.isEmpty(fechaInicio)){
		    	mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarFechaInicio",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
	        	throw new Exception(mensajeError);
		    }

			 /**
		     * Valida fechaFin
		     */
		    if(StringUtils.isBlank(fechaFin)|| StringUtils.isEmpty(fechaFin)){
		    	mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarFechaFin",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
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
			
			Map criteria = new HashMap();
			
			// Asignamos al codigo del periodo el valor por defecto
	        criteria.put("codigoPais", pais.getCodigo());
		    criteria.put("codigoProcesoBatch", CODIGO_BATCH);
	        criteria.put("codigoSistema", CODIGO_SISTEMA);
	        criteria.put("codigoInterfaz", CODIGO_INTERFAZ);
	        criteria.put("codigoUsuario", codigoUsuario);
	        criteria.put("usuario", usuario);
	        criteria.put("operacion", operacion);
	        criteria.put("fechaInicio", fechaInicio);
	        criteria.put("fechaFin", fechaFin);
			    		
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

		params =  super.prepareParamsBeforeExecute(params, pais);
		
        params.put("regionList", null);
        params.put("zonaList", null);
        
        List subacList = new ArrayList();
        subacList.add(Constants.REC_SUBACCESO_DEFAULT);
        params.put("subacList", subacList);
        params.put("subacGZ", Constants.NUMERO_UNO);
        
        Map criteriaOperacion = new HashMap();
        String operacion = MapUtils.getString(params, "operacion");
        criteriaOperacion.put("codigoPais", pais.getCodigo());        
        criteriaOperacion.put("codigoOperacionHomologada", operacion);        
        
        params.put("operacionList", mantenimientoRECOperacionService.getOperacionesByOperacionHomologada(criteriaOperacion));
        
        params.put("tipoIngreso", Constants.CODIGO_TIPO_INGRESO_LINEA);
        
        if (operacion.equalsIgnoreCase("E")){
        	params.put("errorSacado", "SI");
        }else{
        	params.put("errorSacado", null);
        }
        
       //Se obtiene el numero de secuencia de la sesion para asignarlo al usuario
        String numSecUsuario = mantenimientoRECOperacionService.getNumeroSecuenciaUsuario();
        params.put("numeroSecuencia", numSecUsuario);
        
        return params;
		
	}

}
