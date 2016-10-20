/**
 * 
 */
package biz.belcorp.ssicc.service.aco.ws.impl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.rpc.ServiceException;

import org.apache.axis.MessageContext;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazPK;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.aco.ws.ACOProcesoGENEjecutarInterfazWebService;
import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;
import biz.belcorp.ssicc.service.aco.ws.beans.ParametroACOWebService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazResult;
import biz.belcorp.ssicc.service.sisicc.framework.beans.SSiCC_Desfasado_InterfazExecutionResult;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.util.FileUtil;

/**
 * 
 * TODO Include class description here. 
 * 
 * <p>
 * <a href="ACOProcesoGENEjecutarInterfazWebServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@sigcomt.com">Ivan Tocto Jaimes</a>
 */

public class ACOProcesoGENEjecutarInterfazWebServiceImpl extends BaseInterfazHiloAbstractWebService implements ACOProcesoGENEjecutarInterfazWebService {

	MantenimientoOCRPedidoControlFacturacionService mantenimientoOCRPedidoControlFacturacionService;
		
	@Override
	protected void onInit() throws ServiceException {
		super.onInit();
		this.mantenimientoOCRPedidoControlFacturacionService = (MantenimientoOCRPedidoControlFacturacionService)getWebApplicationContext().getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.ACOProcesoGENEjecutarInterfazWebService#ejecutarProceso(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public ACOWebServiceResponse ejecutarProceso(String codigoPais,
			String codigoPeriodo, String fechaFacturacion, String codigoInterfaz,
			String codigoUsuario) throws RemoteException {

		ACOWebServiceResponse objetoRespuesta = new ACOWebServiceResponse();
		boolean estado = false;
		ParametroACOWebService parametroACOWebService = null;
		SSiCC_Desfasado_InterfazExecutionResult executionResult = null;
		InterfazResult interfazResult = null;
		String mensajeError = "";
		String CODIGO_BATCH = Constants.GEN_CODIGO_PROCESO_BATCH_EJECUTAR_INTERFAZ;
		String codigoSistema = "";
		
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
			 * Valida codigoInterfaz
			 */
			
			if(StringUtils.isBlank(codigoInterfaz) || StringUtils.isEmpty(codigoInterfaz))
			{
		    	mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarInterfaz",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
	        	throw new Exception(mensajeError);
			}
			else
			{
				codigoSistema = StringUtils.splitPreserveAllTokens(codigoInterfaz, "-")[0];
				
				if(codigoInterfaz.equals("PED-P1")) CODIGO_BATCH = "04";
				
				if(codigoInterfaz.equals("POL-P1")) CODIGO_BATCH = "01";
				
				if(codigoInterfaz.equals("GEN-P9")) CODIGO_BATCH = "12";
				
				if(codigoInterfaz.equals("PED-P2")) CODIGO_BATCH = "11";
				
			}
			
			try
			{
				InterfazPK primaryKey = new InterfazPK(codigoPais, codigoSistema, codigoInterfaz);
				Interfaz interfaz = interfazService.getInterfaz(primaryKey);				
			}
			catch(Exception ex)
			{
		    	mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.mensajeNotExisteInterfaz",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
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
	        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
		    criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente
		    criteria.put("codigoISO",usuario.getIdioma().getCodigoISO());
	        criteria.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
	        criteria.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
			criteria.put("codigoProcesoBatch", CODIGO_BATCH);
	        criteria.put("codigoSistema", codigoSistema);
	        criteria.put("codigoInterfaz", codigoInterfaz);
	    	criteria.put("codigoUsuario", codigoUsuario);		
	    	
	    	 MessageContext msgCtx = MessageContext.getCurrentContext();
		        HttpServletRequest httpServletRequest = (HttpServletRequest)msgCtx.  
		        	    getProperty("transport.http.servletRequest"); 
		        
	    	//HttpServletRequest request = (HttpServletRequest)MessageContext.getCurrentContext().getProperty(HTTPConstants.MC_HTTP_SERVLETREQUEST);			
	    	criteria.put("rutaPath", FileUtil.formatDirectory(httpServletRequest.getRealPath("/")));
	        
			PedidoControlFacturacion controlFacturacion = mantenimientoOCRPedidoControlFacturacionService.getControlFacturacionById(criteria);
			if (StringUtils.isBlank(codigoPeriodo) || StringUtils.isEmpty(codigoPeriodo)) {
	        	codigoPeriodo = controlFacturacion.getCodigoPeriodo();
			}
			if(StringUtils.isBlank(fechaFacturacion) || StringUtils.isEmpty(fechaFacturacion)){
	        	fechaFacturacion = controlFacturacion.getFechaProceso();
	        }

			criteria.put("codigoPeriodo", codigoPeriodo);
			criteria.put("fechaFacturacion", fechaFacturacion);
			criteria.put("codigoIdiomaIso", pais.getCodigoIdiomaIso());
			
	        executionResult = this.executeInterfaz(criteria);
	        
	        List list = executionResult.getInterfazResults();
			interfazResult = (InterfazResult) list.get(0);
		
			if (interfazResult != null) {
				if(StringUtils.equals(this.estadoProceso, Constants.OK)){
					estado = true;
				}
				objetoRespuesta.setEjecucionExitosa(estado);
				if("ACO-04".equals(codigoInterfaz))
					objetoRespuesta.setMensaje(this.mensajeError +" >>>>>>> " + interfazResult.getMensaje());
				else
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
