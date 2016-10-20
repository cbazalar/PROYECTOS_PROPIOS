/*
 * Created on 07/05/2007 10:59:05 AM biz.belcorp.ssicc.sisicc.web.ws.InterfazWebService
 */
package biz.belcorp.ssicc.service.aco.ws.impl;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionMessages;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.aco.ws.ACOProcesoOCRControlFacturacionWebService;
import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoEDUWebServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cbazalar@sigcomt.com">Carlos Bazalar</a>
 */
public class ACOProcesoOCRControlFacturacionWebServiceImpl extends BaseProcesoHiloAbstractWebService implements ACOProcesoOCRControlFacturacionWebService {

	
	MantenimientoOCRPedidoControlFacturacionService mantenimientoOCRPedidoControlFacturacionService;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.remoting.jaxrpc.ServletEndpointSupport#onInit()
	 */
	protected void onInit() throws ServiceException {
		super.onInit();
		this.mantenimientoOCRPedidoControlFacturacionService = (MantenimientoOCRPedidoControlFacturacionService)getWebApplicationContext().getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
	}
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.aco.ws.ProcesoACOWebService#
	 * ejecutarProcesoEDUCalificacionEnviarAptasAutomatica(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public ACOWebServiceResponse ejecutarProcesoOCRControlFacturacion(
					String codigoUsuario,
					String codigoPais,
					String codigoPeriodo) throws RemoteException {
		ACOWebServiceResponse objetoRespuesta = new ACOWebServiceResponse();
		
		if(log.isDebugEnabled()){
			log.debug("ejecutar Proceso Interfaz ejecutarProcesoOCRControlFacturacion");
		}
		
		String mensajeError = "";
		String CODIGO_BATCH = Constants.OCR_CODIGO_PROCESO_BATCH_ACTUALIZAR_NUMERO_LOTE;
		String CODIGO_SISTEMA = Constants.OCR_CODIGO_SISTEMA;
		
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
		    
		    Usuario usuario = this.obtenerUsuarioByDefault(codigoUsuario);
		    
			Map params = new HashMap();
			params.put("codigoPais", codigoPais);
			params.put("codigoSistema", CODIGO_SISTEMA);
			params.put("codigoProcesoBatch", CODIGO_BATCH);
			params.put("estadoCampanha", Constants.NUMERO_CERO); // Indica Campanha Activa
			params.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente
			params.put("codigoUsuario", codigoUsuario);
			params.put("usuario", usuario);
			
			PedidoControlFacturacion pedidoControlFacturacion = this.mantenimientoOCRPedidoControlFacturacionService.getControlFacturacionById(params);
			if (StringUtils.isBlank(codigoPeriodo) || StringUtils.isEmpty(codigoPeriodo)) {
	        	codigoPeriodo = pedidoControlFacturacion.getCodigoPeriodo();
			}
			params.put("codigoPeriodo", codigoPeriodo);
			
			this.executeProceso(params);
		    objetoRespuesta.setEjecucionExitosa(true);

		} catch (Exception e) {
			mensajeError = e.getMessage();
			objetoRespuesta.setEjecucionExitosa(false);
			objetoRespuesta.setMensaje(mensajeError);
		}

		return objetoRespuesta;
	}

	protected Map prepareParamsBeforeExecute(Map params, Pais pais) throws Exception {
		params.put("codigoTipoDocumento", Constants.STO_TIPO_DOCUMENTO_OCC);
		return super.prepareParamsBeforeExecute(params, pais);
	}

	protected Map executeProcess(Map params) throws Exception {

        ActionMessages messages = new ActionMessages();        
        
        PedidoControlFacturacion controlFacturacion = new PedidoControlFacturacion();
        controlFacturacion.setCodigoPais(MapUtils.getString(params, "codigoPais"));
        controlFacturacion.setCodigoPeriodo(MapUtils.getString(params, "codigoPeriodo"));
        controlFacturacion.setEstadoCampanha(Constants.ESTADO_ACTIVO);

        Map map = BeanUtils.describe(controlFacturacion);

        controlFacturacion.setUsuario(MapUtils.getString(params, "codigoUsuario"));
        map.put("controlFacturacion", controlFacturacion);
        
        mantenimientoOCRPedidoControlFacturacionService.executeActualizaNumeroLote(map);
        		
		return params;
	}
}
