package biz.belcorp.ssicc.service.aco.ws.impl;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.aco.ws.ACOProcesoAPESecuenciaClientesWebService;
import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;
import biz.belcorp.ssicc.service.spusicc.ape.ProcesoAPESecuenciaClientesService;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ACOProcesoAPESecuenciaClientesWebServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@sigcomt.com">Ivan Tocto</a>
 */
public class ACOProcesoAPESecuenciaClientesWebServiceImpl extends BaseProcesoHiloAbstractWebService implements ACOProcesoAPESecuenciaClientesWebService {

    MantenimientoOCRPedidoControlFacturacionService  mantenimientoOCRPedidoControlFacturacionService;        
	ProcesoAPESecuenciaClientesService procesoAPESecuenciaClientesService;
    MantenimientoMAEClienteService mantenimientoMAEClienteService;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.impl.BaseProcesoHiloAbstractWebService#onInit()
	 */
	@Override
	protected void onInit() throws ServiceException {
		super.onInit();
        mantenimientoOCRPedidoControlFacturacionService = (MantenimientoOCRPedidoControlFacturacionService) getWebApplicationContext().getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");        
		procesoAPESecuenciaClientesService = (ProcesoAPESecuenciaClientesService) getWebApplicationContext().getBean("spusicc.procesoAPESecuenciaClientesService");
        mantenimientoMAEClienteService = (MantenimientoMAEClienteService) getWebApplicationContext().getBean("spusicc.mantenimientoMAEClienteService");

	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.ACOProcesoAPESecuenciaClientesWebService#ejecutarProcesoAPESecuenciaClientes(java.lang.String, java.lang.String)
	 */
	public ACOWebServiceResponse ejecutarProcesoAPESecuenciaClientes(String codigoPais, String codigoUsuario) throws RemoteException {

		ACOWebServiceResponse objetoRespuesta = new ACOWebServiceResponse();
		
		if(log.isDebugEnabled()){
			log.debug("ejecutar Proceso Interfaz ejecutarProcesoAPESecuenciaClientes");
		}
		
		boolean estado = false;
		String mensajeError = "";
		String CODIGO_BATCH = Constants.APE_CODIGO_PROCESO_BATCH_SECUENCIA_CLIENTES;
		String CODIGO_SISTEMA = Constants.APE_CODIGO_SISTEMA;

		try {
			Pais pais = this.paisService.getPais(codigoPais);
			if (StringUtils.isBlank(codigoPais)) {
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
			
			Map params = new HashMap();
			params.put("codigoPais", codigoPais);
			params.put("codigoProcesoBatch", CODIGO_BATCH);
			params.put("codigoSistema", CODIGO_SISTEMA);			
			params.put("codigoUsuario", codigoUsuario);
			
			this.executeProceso(params);
			
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
	 * @see biz.belcorp.ssicc.aco.ws.impl.BaseProcesoHiloAbstractWebService#prepareParamsBeforeExecute(java.util.Map, biz.belcorp.ssicc.model.Pais)
	 */
	@Override
	protected Map prepareParamsBeforeExecute(Map params, Pais pais) throws Exception {
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
        criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente  		
        PedidoControlFacturacion controlFacturacion = mantenimientoOCRPedidoControlFacturacionService.getControlFacturacionById(criteria);        
        //La fecha sera la obtenida del archivo de control
        params.put("fechaFacturacion",controlFacturacion.getFechaProceso());        
        //recuperamos el oid Pais
        String oidPais = mantenimientoMAEClienteService.getOidPais(criteria);
        params.put("oidPais", oidPais);
		params.put("codigoPais", pais.getCodigo());			
		
		return super.prepareParamsBeforeExecute(params, pais);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.impl.BaseProcesoHiloAbstractWebService#executeProcess(java.util.Map)
	 */
	@Override
	protected Map executeProcess(Map params) throws Exception {
		
		log.debug("Executing action : executeProcess");
		
		if(params!=null){
			//procesoAPESecuenciaClientesService.executeSecuenciaClientes(params);
			procesoAPESecuenciaClientesService.executeProcesoAPECargaRutasCliente(params);
		}
		
		return params;
	}

}
