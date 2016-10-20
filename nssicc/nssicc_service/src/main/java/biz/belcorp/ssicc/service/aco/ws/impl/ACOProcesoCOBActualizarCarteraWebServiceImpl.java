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
import biz.belcorp.ssicc.service.aco.ws.ACOProcesoCOBActualizarCarteraWebService;
import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.cobranzas.ProcesoCOBActualizarCarteraService;

/**
 * 
 * TODO Include class description here. 
 * 
 * <p>
 * <a href="ACOProcesoCOBActualizarCarteraWebServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@sigcomt.com">Ivan Tocto Jaimes</a>
 */

public class ACOProcesoCOBActualizarCarteraWebServiceImpl extends
		BaseProcesoHiloAbstractWebService implements
		ACOProcesoCOBActualizarCarteraWebService {

	InterfazSiCCService interfazSiCCService;
	ProcesoCOBActualizarCarteraService procesoCOBActualizarCarteraService;
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.impl.BaseProcesoHiloAbstractWebService#onInit()
	 */
	@Override
	protected void onInit() throws ServiceException {
		super.onInit();
		interfazSiCCService = (InterfazSiCCService)getApplicationContext().getBean("sisicc.interfazSiCCService");
		procesoCOBActualizarCarteraService = (ProcesoCOBActualizarCarteraService)getApplicationContext().getBean("spusicc.procesoCOBActualizarCarteraService");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.ACOProcesoCOBActualizarCarteraWebService#ejecutarProcesoCOBActualizarCartera(java.lang.String, java.lang.String, java.lang.String)
	 */
	public ACOWebServiceResponse ejecutarProcesoCOBActualizarCartera(
			String codigoPais, String codigoSociedad, String codigoUsuario)
			throws RemoteException {

		ACOWebServiceResponse objetoRespuesta = new ACOWebServiceResponse();
		boolean estado = false;
		String mensajeError = "";		
		final String CODIGO_BATCH = Constants.COB_CODIGO_PROCESO_BATCH_ACTUALIZAR_CARTERA;
		final String CODIGO_PROCESO = Constants.COB_CODIGO_PROCESO_ACTUALIZAR_CARTERA;
		final String CODIGO_SISTEMA = Constants.CODIGO_SISTEMA_COB;
		
		if(log.isDebugEnabled()){
			log.debug("ejecutar Proceso Interfaz ejecutarProcesoCOBActualizarCartera");
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
	        List sociedades = interfazSiCCService.getSociedadesByCodigoPais(pais.getCodigo());
			
			if(StringUtils.isBlank(codigoSociedad) || StringUtils.isEmpty(codigoSociedad)){
				mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarCodigoSociedad", null,
						getLocaleIdioma(pais.getCodigoIdiomaIso()));
						throw new Exception(mensajeError);
			}else{
				EqualPredicate nameEqlPredicate = new EqualPredicate(codigoSociedad);
				BeanPredicate beanPredicate = new BeanPredicate("codigo", nameEqlPredicate);					
				if(sociedades.size()!=0){
					if(!CollectionUtils.exists(sociedades, beanPredicate)){
						mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.mensajeNotExisteCodigoSociedad",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
						throw new Exception(mensajeError);
					}
				}else{
					log.debug("Listado de codigo sociedad vacio.");
				}
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
			criteria.put("codigoSociedad", codigoSociedad);
		    criteria.put("codigoUsuario", codigoUsuario);
		    criteria.put("usuario", usuario);
			criteria.put("codigoProceso", CODIGO_PROCESO);
			criteria.put("codigoProcesoBatch", CODIGO_BATCH);
	        criteria.put("codigoSistema", CODIGO_SISTEMA);
	        criteria.put("codigoModulo", CODIGO_SISTEMA);
	        	        	        
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
		
		procesoCOBActualizarCarteraService.executeActualizarCartera(params);
		procesoCOBActualizarCarteraService.executeEnvioMail(params);
		
		return params;
	}
	
}
