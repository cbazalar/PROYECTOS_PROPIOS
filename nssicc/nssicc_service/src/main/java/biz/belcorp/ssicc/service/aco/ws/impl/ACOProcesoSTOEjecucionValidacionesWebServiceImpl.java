/**
 * 
 */
package biz.belcorp.ssicc.service.aco.ws.impl;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.sto.model.AccionTipoDocumento;
import biz.belcorp.ssicc.dao.spusicc.sto.model.TipoDocumentoDigitado;
import biz.belcorp.ssicc.service.aco.ws.ACOProcesoSTOEjecucionValidacionesWebService;
import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEjecucionValidacionesService;
import biz.belcorp.ssicc.service.spusicc.sto.framework.ProcesoSTOExecutionService;

/**
 * 
 * TODO Include class description here. 
 * 
 * <p>
 * <a href="ACOProcesoSTOEjecucionValidacionesWebServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@sigcomt.com">Ivan Tocto Jaimes</a>
 */

public class ACOProcesoSTOEjecucionValidacionesWebServiceImpl extends
		BaseProcesoHiloAbstractWebService implements
		ACOProcesoSTOEjecucionValidacionesWebService {

	ProcesoSTOEjecucionValidacionesService procesoSTOEjecucionValidacionesService;
	ProcesoSTOExecutionService procesoSTOExecutionService;
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.impl.BaseProcesoHiloAbstractWebService#onInit()
	 */
	@Override
	protected void onInit() throws ServiceException {
		super.onInit();
		this.procesoSTOEjecucionValidacionesService = (ProcesoSTOEjecucionValidacionesService) getApplicationContext().getBean("spusicc.procesoSTOEjecucionValidacionesService");
		this.procesoSTOExecutionService = (ProcesoSTOExecutionService) getApplicationContext().getBean("spusicc.procesoSTOExecutionService");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.ACOProcesoSTOEjecucionValidacionesWebService#ejecutarProcesoSTOEjecucionValidaciones(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public ACOWebServiceResponse ejecutarProcesoSTOEjecucionValidaciones(
			String codigoPais, String codigoTipoDocumento,
			String codigoPeriodo, String fechaProceso, String codigoUsuario)
			throws RemoteException {

		ACOWebServiceResponse objetoRespuesta = new ACOWebServiceResponse();
		boolean estado = false;
		String mensajeError = "";		
		final String CODIGO_BATCH = Constants.STO_CODIGO_PROCESO_BATCH_EJECUTAR_VALIDACIONES;
		final String CODIGO_SISTEMA = Constants.STO_CODIGO_SISTEMA;
		
		if(log.isDebugEnabled()){
			log.debug("ejecutar Proceso Interfaz ejecutarProcesoSTOEjecucionValidaciones");
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

			Map criteria = new HashMap();
			
	        criteria.put("codigoPais", pais.getCodigo());
		    criteria.put("codigoUsuario", codigoUsuario);
		    criteria.put("usuario", usuario);
			criteria.put("codigoProcesoBatch", CODIGO_BATCH);
	        criteria.put("codigoSistema", CODIGO_SISTEMA);
	        
			/**
			 * Validamos el tipo de documento
			 */
			List documentos = procesoSTOEjecucionValidacionesService.getTiposDocumentosSTO(criteria);

			if(StringUtils.isBlank(codigoTipoDocumento) || StringUtils.isEmpty(codigoTipoDocumento)){				
				mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarTipoDocumento",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
				throw new Exception(mensajeError);				
			}else{
				if(documentos.size()!=0){
					if(!existeCodigoEnLista(documentos, codigoTipoDocumento)){
						mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.mensajeNotExisteTipoDocumento",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
						throw new Exception(mensajeError);
					}
				}else{
					log.debug("Listado de TipoDocumento vacio.");
				}
			}
	        criteria.put("tipoDocumento", codigoTipoDocumento);

			TipoDocumentoDigitado tipoDocumentoDigitado = procesoSTOEjecucionValidacionesService.getTipoDocumentoDigitado(codigoTipoDocumento);
			// Setea el proceso Batch Actual a ejecutarse
			String codigoProcesoBatch = tipoDocumentoDigitado.getCodigoProcesoBatch();
			
			criteria.put("codigoProcesoBatch", codigoProcesoBatch);

			AccionTipoDocumento accionTipoDocumento = new AccionTipoDocumento(pais.getCodigo(), codigoTipoDocumento, Constants.STO_ACCI_VALI_MASI);
			criteria.put("accionTipoDocumento", accionTipoDocumento);
			
			if(codigoTipoDocumento.equalsIgnoreCase("OCC")){				
			    /**
			     * Valida codigoPeriodo
			     */
			    if(StringUtils.isBlank(codigoPeriodo)|| StringUtils.isEmpty(codigoPeriodo)){
			    	mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarCodigoPeriodo",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
		        	throw new Exception(mensajeError);
			    }
			    
			    /**
			     * Valida fechaProceso
			     */
			    if(StringUtils.isBlank(fechaProceso)|| StringUtils.isEmpty(fechaProceso)){
			    	mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarFechaProceso",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
		        	throw new Exception(mensajeError);
			    }				

			    criteria.put("codigoPeriodo", codigoPeriodo);
			    criteria.put("fechaProceso", fechaProceso);
	    	}
	        else {
	        	criteria.put("codigoPeriodo", "");
	        	criteria.put("fechaProceso", "");
	        }
						
    		this.executeProceso(criteria);
 		    objetoRespuesta.setEjecucionExitosa(true);
	     
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
	 * @see biz.belcorp.ssicc.aco.ws.impl.BaseProcesoHiloAbstractWebService#executeProcess(java.util.Map)
	 */
	@Override
	protected Map executeProcess(Map params) throws Exception {
		
    	AccionTipoDocumento accionTipoDocumento = (AccionTipoDocumento)params.get("accionTipoDocumento");    	
    	procesoSTOExecutionService.execute(accionTipoDocumento,params, null);

    	return params;
	}
	
}
