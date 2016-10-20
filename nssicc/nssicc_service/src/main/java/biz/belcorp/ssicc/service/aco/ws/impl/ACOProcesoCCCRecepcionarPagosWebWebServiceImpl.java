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
import biz.belcorp.ssicc.service.aco.ws.ACOProcesoCCCRecepcionarPagosWebWebService;
import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ProcesoCCCRecepcionarPagosWebService;

/**
 * 
 * TODO Include class description here. 
 * 
 * <p>
 * <a href="ACOProcesoCCCRecepcionarPagosWebWebServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@sigcomt.com">Ivan Tocto Jaimes</a>
 */

public class ACOProcesoCCCRecepcionarPagosWebWebServiceImpl extends
		BaseProcesoHiloAbstractWebService implements
		ACOProcesoCCCRecepcionarPagosWebWebService {

	ProcesoCCCRecepcionarPagosWebService procesoCCCRecepcionarPagosWebService;
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.impl.BaseProcesoHiloAbstractWebService#onInit()
	 */
	@Override
	protected void onInit() throws ServiceException {
		super.onInit();
		procesoCCCRecepcionarPagosWebService = (ProcesoCCCRecepcionarPagosWebService) getApplicationContext().getBean("spusicc.procesoCCCRecepcionarPagosWebService");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.ACOProcesoCCCRecepcionarPagosWebWebService#ejecutarProcesoCCCRecepcionarPagosWeb(java.lang.String, java.lang.String)
	 */
	public ACOWebServiceResponse ejecutarProcesoCCCRecepcionarPagosWeb(String codigoPais, String codigoUsuario) throws RemoteException {
		ACOWebServiceResponse objetoRespuesta = new ACOWebServiceResponse();
		boolean estado = false;
		String mensajeError = "";		
		final String CODIGO_BATCH = Constants.CCC_CODIGO_PROCESO_BATCH_RECEPCIONAR_PAGOS_WEB;
		final String CODIGO_PROGRAMA = Constants.CCC_CODIGO_PROGRAMA_RECEPCIONAR_PAGOS_WEB;
		final String CODIGO_SISTEMA = Constants.CCC_CODIGO_SISTEMA;
		
		if(log.isDebugEnabled()){
			log.debug("ejecutarProcesoCCCRecepcionarPagosWeb");
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
			
			// Asignamos al codigo del periodo el valor por defecto
	        criteria.put("codigoPais", pais.getCodigo());
	        criteria.put("codigoPrograma", CODIGO_PROGRAMA);
			criteria.put("codigoProcesoBatch", CODIGO_BATCH);
	        criteria.put("codigoSistema", CODIGO_SISTEMA);
		    criteria.put("codigoUsuario", codigoUsuario);
		    criteria.put("usuario", usuario);
	        	        	        
		    criteria.put("codigoModulo", CODIGO_SISTEMA);

		    criteria.put("descripcionPais", pais.getDescripcion());
		    criteria.put("usuarioProceso", codigoUsuario);
			
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
		
		procesoCCCRecepcionarPagosWebService.executeRecepcionarPagosWeb(params);
		
		return params;

	}
}
