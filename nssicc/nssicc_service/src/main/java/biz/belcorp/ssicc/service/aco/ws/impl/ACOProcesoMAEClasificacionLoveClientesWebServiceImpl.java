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
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.aco.ws.ACOProcesoMAEClasificacionLoveClientesWebService;
import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.ProcesoMAEClasificacionClientesService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;

/**
 * 
 * TODO Include class description here. 
 * 
 * <p>
 * <a href="ACOProcesoMAEClasificacionLoveClientesWebServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@sigcomt.com">Ivan Tocto Jaimes</a>
 */

public class ACOProcesoMAEClasificacionLoveClientesWebServiceImpl extends
		BaseProcesoHiloAbstractWebService implements
		ACOProcesoMAEClasificacionLoveClientesWebService {

	InterfazSiCCService interfazSiCCService;	
	MantenimientoOCRPedidoControlFacturacionService mantenimientoOCRPedidoControlFacturacionService;
	ProcesoMAEClasificacionClientesService procesoMAEClasificacionClientesService;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.impl.BaseProcesoHiloAbstractWebService#onInit()
	 */
	@Override
	protected void onInit() throws ServiceException {
		super.onInit();
		interfazSiCCService = (InterfazSiCCService) getApplicationContext().getBean("sisicc.interfazSiCCService");
		mantenimientoOCRPedidoControlFacturacionService = (MantenimientoOCRPedidoControlFacturacionService) getWebApplicationContext().getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		procesoMAEClasificacionClientesService = (ProcesoMAEClasificacionClientesService) getWebApplicationContext().getBean("spusicc.procesoMAEClasificacionClientesService");		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.ACOProcesoMAEClasificacionLoveClientesWebService#ejecutarProcesoMAEClasificacionLoveClientes(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public ACOWebServiceResponse ejecutarProcesoMAEClasificacionLoveClientes(
			String codigoPais, String codigoMarca, String codigoPeriodo,
			String codigoUsuario) throws RemoteException {

		ACOWebServiceResponse objetoRespuesta = new ACOWebServiceResponse();
		boolean estado = false;
		String mensajeError = "";		
		final String CODIGO_BATCH = Constants.MAE_CODIGO_PROCESO_BATCH_CLASIFICACION_LOVE_CLIENTES;
		final String CODIGO_SISTEMA = Constants.MAE_CODIGO_SISTEMA;
		
		if(log.isDebugEnabled()){
			log.debug("ejecutar Proceso Interfaz ejecutarProcesoMAEClasificacionLoveClientes");
		}
		try{
			Pais pais = this.paisService.getPais(codigoPais);
			if(StringUtils.isBlank(codigoPais) || StringUtils.isEmpty(codigoPais)){
				mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarPais", null,
				getLocaleIdioma(pais.getCodigoIdiomaIso()));
				throw new Exception(mensajeError);
			}
				
		    List siccMarcaList = this.interfazSiCCService.getMarcas();
		    
			/**
			 * Si marca es vacio o en blanco, traer el de por defecto
			 */
			if(StringUtils.isBlank(codigoMarca) || StringUtils.isEmpty(codigoMarca)){
				codigoMarca = Constants.CODIGO_MARCA_DEFAULT;
			}else{
				if(siccMarcaList.size()!=0){
					if(!existeCodigoEnLista(siccMarcaList, codigoMarca)){
						mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.mensajeNotExisteMarca",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
						throw new Exception(mensajeError);
					}
				}else{
					log.debug("Listado de marca vacio.");
				}
			}

			Map criteria = new HashMap();
			criteria.put("codigoPais", codigoPais);
			criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
			criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente			
			PedidoControlFacturacion controlFacturacion = this.mantenimientoOCRPedidoControlFacturacionService.getControlFacturacionById(criteria);
			
			if (StringUtils.isBlank(codigoPeriodo)) {
				codigoPeriodo = controlFacturacion.getCodigoPeriodo();
			}
			
			/**
		     * Valida usuario
		     */
		    if(StringUtils.isBlank(codigoUsuario)|| StringUtils.isEmpty(codigoUsuario)){
		    	mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarUsuario",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
	        	throw new Exception(mensajeError);
		    }			
			
		    Usuario usuario = this.obtenerUsuarioByDefault(codigoUsuario);

			Map criteria1 = new HashMap();
			
	        criteria1.put("codigoPais", pais.getCodigo());
		    criteria1.put("codigoMarca", codigoMarca);
		    criteria1.put("codigoPeriodo", codigoPeriodo);
		    criteria1.put("codigoUsuario", codigoUsuario);
		    criteria1.put("usuario", usuario);
			criteria1.put("codigoProcesoBatch", CODIGO_BATCH);
	        criteria1.put("codigoSistema", CODIGO_SISTEMA);
	        
    		this.executeProceso(criteria1);
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

		procesoMAEClasificacionClientesService.executeProcesarClasificacionLove(params);
		
		return params;	
	}
	
}
