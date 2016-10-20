/**
 * 
 */
package biz.belcorp.ssicc.service.aco.ws.impl;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.rpc.ServiceException;

import org.apache.axis.MessageContext;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.aco.ws.ACOProcesoCCCCargarLotesBancariosWebService;
import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ProcesoCCCCargarLotesBancariosService;
import biz.belcorp.ssicc.service.util.FileUtil;

/**
 * 
 * TODO Include class description here. 
 * 
 * <p>
 * <a href="ACOProcesoCCCCargarLotesBancariosWebServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@sigcomt.com">Ivan Tocto Jaimes</a>
 */

public class ACOProcesoCCCCargarLotesBancariosWebServiceImpl extends
		BaseProcesoHiloAbstractWebService implements
		ACOProcesoCCCCargarLotesBancariosWebService {

	ProcesoCCCCargarLotesBancariosService procesoCCCCargarLotesBancariosService;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.impl.BaseProcesoHiloAbstractWebService#onInit()
	 */
	@Override
	protected void onInit() throws ServiceException {
		super.onInit();
		procesoCCCCargarLotesBancariosService = (ProcesoCCCCargarLotesBancariosService) getApplicationContext().getBean("spusicc.procesoCCCCargarLotesBancariosService");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.ACOProcesoCCCCargarLotesBancariosWebService#ejecutarProcesoCCCCargarLotesBancarios(java.lang.String, java.lang.String)
	 */
	public ACOWebServiceResponse ejecutarProcesoCCCCargarLotesBancarios(String codigoPais, String codigoUsuario) throws RemoteException {
		ACOWebServiceResponse objetoRespuesta = new ACOWebServiceResponse();
		boolean estado = false;
		String mensajeError = "";		
		final String CODIGO_BATCH = Constants.CCC_CODIGO_PROCESO_BATCH_CARGAR_LOTES_BANCARIOS;
		final String CODIGO_INTERFAZ = Constants.CCC_CODIGO_INTERFAZ_CARGAR_LOTES_BANCARIOS;
		final String CODIGO_SISTEMA = Constants.CCC_CODIGO_SISTEMA;
		
		if(log.isDebugEnabled()){
			log.debug("ejecutarProcesoCCCCargarLotesBancarios");
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
	        criteria.put("codigoInterfaz", CODIGO_INTERFAZ);
			criteria.put("codigoProcesoBatch", CODIGO_BATCH);
	        criteria.put("codigoSistema", CODIGO_SISTEMA);
		    criteria.put("codigoUsuario", codigoUsuario);
		    criteria.put("usuario", usuario);
	        	        	        
		    criteria.put("codigoModulo", CODIGO_SISTEMA);
		    criteria.put("codigoProceso", CODIGO_BATCH);	

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
		
		Usuario usuario = (Usuario)MapUtils.getObject(params, "usuario");
		
		//params.put("rutaPath", this.usuarioService.getRutaPath());
		 MessageContext msgCtx = MessageContext.getCurrentContext();
		 HttpServletRequest httpServletRequest = (HttpServletRequest)msgCtx.  
	        	    getProperty("transport.http.servletRequest");
		params.put("usuarioTemp", usuario);
		params.put("rutaPath",FileUtil.formatDirectory(httpServletRequest.getRealPath("/")));
		
		procesoCCCCargarLotesBancariosService.executeCargarLotesBancarios(params);
		
		return params;

	}
}
