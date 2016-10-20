package biz.belcorp.ssicc.service.aco.ws.impl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.aco.ws.ACOProcesoRETRecepcionarVentasRetailCabDetWebService;
import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;
import biz.belcorp.ssicc.service.aco.ws.beans.ParametroACOWebService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazResult;
import biz.belcorp.ssicc.service.sisicc.framework.beans.SSiCC_Desfasado_InterfazExecutionResult;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ACOProcesoRETRecepcionarVentasRetailCabDetWebServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@sigcomt.com">Ivan Tocto</a>
 */
public class ACOProcesoRETRecepcionarVentasRetailCabDetWebServiceImpl extends BaseInterfazHiloAbstractWebService implements ACOProcesoRETRecepcionarVentasRetailCabDetWebService {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.ACOProcesoRETRecepcionarVentasRetailCabDetWebService#ejecutarProcesoRETRecepcionarVentasRetailCabDet(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public ACOWebServiceResponse ejecutarProcesoRETRecepcionarVentasRetailCabDet(
			String codigoPais, String indicadorReproceso, String fechaInicio,
			String fechaFinal, String codigoUsuario) throws RemoteException {

		ACOWebServiceResponse objetoRespuesta = new ACOWebServiceResponse();
		boolean estado = false;
		ParametroACOWebService parametroACOWebService = null;
		SSiCC_Desfasado_InterfazExecutionResult executionResult = null;
		InterfazResult interfazResult = null;
		String mensajeError = "";		
		final String CODIGO_BATCH = Constants.RET_CODIGO_PROCESO_BATCH_RECEPCIONAR_VENTAS;
		final String CODIGO_INTERFAZ = Constants.RET_CODIGO_INTERFAZ_RECEPCIONAR_VENTAS;
		final String CODIGO_SISTEMA = Constants.RET_CODIGO_SISTEMA;
		
		if(log.isDebugEnabled()){
			log.debug("ejecutar Proceso Interfaz ejecutarProcesoRETRecepcionarVentasRetailCabDet");
		}
		try{
			Pais pais = this.paisService.getPais(codigoPais);
			if(StringUtils.isBlank(codigoPais) || StringUtils.isEmpty(codigoPais)){
				mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarPais", null, getLocaleIdioma(pais.getCodigoIdiomaIso()));
				throw new Exception(mensajeError);
			}
			
			/**
		     * Valida usuario
		     */
		    if(StringUtils.isBlank(codigoUsuario)|| StringUtils.isEmpty(codigoUsuario)){
		    	mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarUsuario",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
	        	throw new Exception(mensajeError);
		    }
			
		    if(StringUtils.isBlank(indicadorReproceso) || StringUtils.isEmpty(indicadorReproceso))
		    {
		    	indicadorReproceso = Constants.NUMERO_CERO;
		    }
		    else
		    {
		    	if(!(StringUtils.equals(indicadorReproceso, Constants.NUMERO_CERO) || StringUtils.equals(indicadorReproceso, Constants.NUMERO_UNO)))
		    	{
					mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.mensajeNotExisteIndicador", null, getLocaleIdioma(pais.getCodigoIdiomaIso()));
					throw new Exception(mensajeError);
		    	}
		    	else
		    	{
		    		if(StringUtils.isBlank(fechaInicio)|| StringUtils.isEmpty(fechaInicio))
		    		{
						mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarFechaInicio", null, getLocaleIdioma(pais.getCodigoIdiomaIso()));
						throw new Exception(mensajeError);
		    		}
		    		if(StringUtils.isBlank(fechaFinal)|| StringUtils.isEmpty(fechaFinal))
		    		{
						mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarFechaFin", null, getLocaleIdioma(pais.getCodigoIdiomaIso()));
						throw new Exception(mensajeError);
		    		}
		    		//
		    		
		    		Integer compare = DateUtil.compararFechas(fechaInicio, fechaFinal);		    		
		    		if(compare > 0)
		    		{
						mensajeError = getWebApplicationContext().getMessage("interfazRETRecepcionarVentasRetailCabDetForm.validar.fechas", null, getLocaleIdioma(pais.getCodigoIdiomaIso()));
						throw new Exception(mensajeError);
		    		}
		    	}
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
	    	criteria.put("indicadorReproceso", indicadorReproceso);
	    	criteria.put("fechaInicio", fechaInicio);
	    	criteria.put("fechaFinal", fechaFinal);
	        
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
	
}
