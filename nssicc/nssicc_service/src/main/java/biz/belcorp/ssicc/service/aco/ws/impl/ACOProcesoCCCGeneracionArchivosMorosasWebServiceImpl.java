/**
 * 
 */
package biz.belcorp.ssicc.service.aco.ws.impl;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.aco.ws.ACOProcesoCCCGeneracionArchivosMorosasWebService;
import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ProcesoCCCGeneracionArchivosMorosasService;

/**
 * 
 * TODO Include class description here. 
 * 
 * <p>
 * <a href="ACOProcesoCCCGeneracionArchivosMorosasWebServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@sigcomt.com">Ivan Tocto Jaimes</a>
 */

public class ACOProcesoCCCGeneracionArchivosMorosasWebServiceImpl extends
		BaseProcesoHiloAbstractWebService implements
		ACOProcesoCCCGeneracionArchivosMorosasWebService {

	ProcesoCCCGeneracionArchivosMorosasService procesoCCCGeneracionArchivosMorosasService;	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.impl.BaseProcesoHiloAbstractWebService#onInit()
	 */
	@Override
	protected void onInit() throws ServiceException {
		super.onInit();
		
		procesoCCCGeneracionArchivosMorosasService = (ProcesoCCCGeneracionArchivosMorosasService)getApplicationContext().getBean("spusicc.procesoCCCGeneracionArchivosMorosasService");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.ACOProcesoCCCGeneracionArchivosMorosasWebService#ejecutarProcesoCCCGeneracionArchivosMorosas(java.lang.String, java.lang.String)
	 */
	public ACOWebServiceResponse ejecutarProcesoCCCGeneracionArchivosMorosas(
			String codigoPais, String codigoUsuario) throws RemoteException {

		ACOWebServiceResponse objetoRespuesta = new ACOWebServiceResponse();
		boolean estado = false;
		String mensajeError = "";		
		final String CODIGO_BATCH = Constants.CCC_CODIGO_PROCESO_BATCH_GENERACION_ARCHIVOS_MOROSAS;
		final String CODIGO_SISTEMA = Constants.CCC_CODIGO_SISTEMA;
		
		if(log.isDebugEnabled()){
			log.debug("ejecutar Proceso Interfaz ejecutarProcesoMAVFacturacionGerentes");
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

		procesoCCCGeneracionArchivosMorosasService.executeGeneracionArchivosMorosas(params);
		
		return params;	
	}
	
}
