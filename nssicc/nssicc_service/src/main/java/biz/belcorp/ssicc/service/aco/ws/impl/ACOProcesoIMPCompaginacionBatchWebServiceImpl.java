/**
 * 
 */
package biz.belcorp.ssicc.service.aco.ws.impl;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import org.apache.commons.beanutils.BeanPredicate;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.functors.EqualPredicate;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.aco.ws.ACOProcesoIMPCompaginacionBatchWebService;
import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;
import biz.belcorp.ssicc.service.spisicc.ProcesoImpresionService;
import biz.belcorp.ssicc.service.spisicc.framework.ProcesoImpresionExecutionService;

/**
 * 
 * TODO Include class description here. 
 * 
 * <p>
 * <a href="ACOProcesoIMPCompaginacionBatchWebServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@sigcomt.com">Ivan Tocto Jaimes</a>
 */

public class ACOProcesoIMPCompaginacionBatchWebServiceImpl extends BaseProcesoHiloAbstractWebService implements ACOProcesoIMPCompaginacionBatchWebService {

	ProcesoImpresionService procesoImpresionService;
	ProcesoImpresionExecutionService procesoImpresionExecutionService;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.impl.BaseProcesoHiloAbstractWebService#onInit()
	 */
	@Override
	protected void onInit() throws ServiceException {
		super.onInit();
		this.procesoImpresionService = (ProcesoImpresionService)getWebApplicationContext().getBean("spisicc.procesoImpresionService");
		this.procesoImpresionExecutionService = (ProcesoImpresionExecutionService)getWebApplicationContext().getBean("spisicc.procesoImpresionExecutionService");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.ACOProcesoIMPCompaginacionBatchWebService#ejecutarProcesoIMPCompaginacionBatch(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public ACOWebServiceResponse ejecutarProcesoIMPCompaginacionBatch(String codigoPais, String codigoProcesoImpresion, String codigoPeriodo, String fechaFacturacion, String codigoUsuario) throws RemoteException {
		
		ACOWebServiceResponse objetoRespuesta = new ACOWebServiceResponse();
		boolean estado = false;
		String mensajeError = "";		
		final String CODIGO_BATCH = Constants.IMP_CODIGO_PROCESO_BATCH_COMPAGINACION_PAQUETE_DOCUMENTARIO;
		final String CODIGO_SISTEMA = Constants.IMP_CODIGO_SISTEMA;
		
		if(log.isDebugEnabled()){
			log.debug("ejecutar Proceso Interfaz ejecutarProcesoIMPCompaginacionBatch");
		}
		try{
			Pais pais = this.paisService.getPais(codigoPais);
			if(StringUtils.isBlank(codigoPais) || StringUtils.isEmpty(codigoPais)){
				mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarPais", null,
				getLocaleIdioma(pais.getCodigoIdiomaIso()));
				throw new Exception(mensajeError);
			}
						
	        /**
			 * Validar codigoProcesoImpresion
			 */
	        List procesos = procesoImpresionService.getProcesosImpresion(null);
			
			if(StringUtils.isBlank(codigoProcesoImpresion) || StringUtils.isEmpty(codigoProcesoImpresion)){
				mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarCodigoProcesoImpresion", null,
						getLocaleIdioma(pais.getCodigoIdiomaIso()));
						throw new Exception(mensajeError);
			}else{
				EqualPredicate nameEqlPredicate = new EqualPredicate(codigoProcesoImpresion);
				BeanPredicate beanPredicate = new BeanPredicate("codigo", nameEqlPredicate);					
				if(procesos.size()!=0){
					if(!CollectionUtils.exists(procesos, beanPredicate)){
						mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.mensajeNotExisteCodigoProcesoImpresion",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
						throw new Exception(mensajeError);
					}
				}else{
					log.debug("Listado de codigo proceso de impresion vacio.");
				}
			}
			
		    /**
		     * Valida campa�a
		     */
		    if(StringUtils.isBlank(codigoPeriodo)|| StringUtils.isEmpty(codigoPeriodo)){
		    	mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarCodigoPeriodo",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
	        	throw new Exception(mensajeError);
		    }

		    /**
		     * Valida fechaFacturacion
		     */
		    if(StringUtils.isBlank(fechaFacturacion)|| StringUtils.isEmpty(fechaFacturacion)){
		    	mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarFechaFacturacion",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
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
			criteria.put("codigoProcesoImpresion", codigoProcesoImpresion);
			criteria.put("fechaFacturacion", fechaFacturacion);
			criteria.put("codigoPeriodo", codigoPeriodo);
		    criteria.put("codigoUsuario", codigoUsuario);
		    criteria.put("usuario", usuario);
			criteria.put("codigoProcesoBatch", CODIGO_BATCH);
	        criteria.put("codigoSistema", CODIGO_SISTEMA);
	        	        	        
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
		
		Usuario usuario = (Usuario) params.get("usuario");
		procesoImpresionExecutionService.executeProcesoImpresion(params, usuario);

		return params;
	}	
}
