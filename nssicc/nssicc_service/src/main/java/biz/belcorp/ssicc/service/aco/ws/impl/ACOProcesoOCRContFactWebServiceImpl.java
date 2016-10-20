/*
 * Created on 07/05/2007 10:59:05 AM biz.belcorp.ssicc.sisicc.web.ws.InterfazWebService
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
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.aco.ws.ACOProcesoOCRContFactWebService;
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
public class ACOProcesoOCRContFactWebServiceImpl extends BaseProcesoHiloAbstractWebService implements ACOProcesoOCRContFactWebService {

	
	MantenimientoOCRPedidoControlFacturacionService mantenimientoOCRPedidoControlFacturacionService;
	
	
	private static final String CODIGO_SISTEMA = "OCR";
	private static final String CODIGO_PROCESO_BATCH = "05";
	

	protected void onInit() throws ServiceException {
		super.onInit();
		this.mantenimientoOCRPedidoControlFacturacionService = (MantenimientoOCRPedidoControlFacturacionService)getWebApplicationContext().getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
	}

	public ACOWebServiceResponse ejecutarProcesoOCRContFact(
				String codigoUsuario,
				String codigoPais,
				String codigoPeriodo) throws RemoteException {
		ACOWebServiceResponse aCOWebServiceResponse = new ACOWebServiceResponse();

		String mensajeError = "";
		String codigoSistema = CODIGO_SISTEMA;
		String codigoProcesoBatch = CODIGO_PROCESO_BATCH;
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
		    
//		    Usuario usuario = this.usuarioService.getUsuarioByUsername(codigoUsuario);
		    Usuario usuario = this.obtenerUsuarioByDefault(codigoUsuario);
		    
			Map params = new HashMap();
			params.put("codigoPais", codigoPais);
			params.put("codigoSistema", codigoSistema);
			params.put("codigoProcesoBatch", codigoProcesoBatch);
			params.put("estadoCampanha", Constants.NUMERO_CERO); // Indica Campanha Activa
			params.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente
			params.put("codigoUsuario", codigoUsuario);
			params.put("usuario", usuario);
			
			PedidoControlFacturacion pedidoControlFacturacion = this.mantenimientoOCRPedidoControlFacturacionService.getControlFacturacionById(params);
			codigoPeriodo = pedidoControlFacturacion.getCodigoPeriodo();
			params.put("codigoPeriodo", codigoPeriodo);
			
			
			String indVisualizar = pais.getIndicadorVisualizarMontosFacturacion();
			
			// Invocando a la calificaci�n y Envio de Aptas automatica
			this.executeProceso(params);
			

		} catch (Exception e) {
			mensajeError = e.getMessage();
			aCOWebServiceResponse.setEjecucionExitosa(false);
			aCOWebServiceResponse.setMensaje(mensajeError);
		}

		return aCOWebServiceResponse;
	}

	protected Map prepareParamsBeforeExecute(Map params, Pais pais) throws Exception {
		params.put("codigoTipoDocumento", Constants.STO_TIPO_DOCUMENTO_OCC);
		return super.prepareParamsBeforeExecute(params, pais);
	}

	protected Map executeProcess(Map params) throws Exception {
		this.mantenimientoOCRPedidoControlFacturacionService.cerrarPeriodo(params);
		return params;
	}
}
