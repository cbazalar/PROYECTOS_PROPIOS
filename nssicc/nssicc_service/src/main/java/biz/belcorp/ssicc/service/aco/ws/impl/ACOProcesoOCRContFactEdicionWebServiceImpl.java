/*
 * Created on 07/05/2007 10:59:05 AM biz.belcorp.ssicc.sisicc.web.ws.InterfazWebService
 */
package biz.belcorp.ssicc.service.aco.ws.impl;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.aco.ws.ACOProcesoOCRContFactEdicionWebService;
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
public class ACOProcesoOCRContFactEdicionWebServiceImpl extends BaseProcesoHiloAbstractWebService implements ACOProcesoOCRContFactEdicionWebService {

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
			String codigoPeriodo,
			String fechaFacturacion,
			String montoMinimoDeuda,
			String unidadesMaximo) throws RemoteException {
		ACOWebServiceResponse aCOWebServiceResponse = new ACOWebServiceResponse();

		String mensajeError = "";
		String mensajeEXito = "";
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
		    
		    Usuario usuario = this.obtenerUsuarioByDefault(codigoUsuario);
		    
			Map params = new HashMap();
			params.put("codigoPais", codigoPais);
			params.put("codigoSistema", codigoSistema);
			params.put("codigoProcesoBatch", codigoProcesoBatch);
			params.put("estadoCampanha", Constants.NUMERO_CERO); // Indica Campanha Activa
			params.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente
			params.put("codigoUsuario", codigoUsuario);
			params.put("usuario", usuario);
			params.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
			params.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
						
			PedidoControlFacturacion pedidoControlFacturacion = this.mantenimientoOCRPedidoControlFacturacionService.getControlFacturacionById(params);
			
			if(StringUtils.isEmpty(fechaFacturacion) || StringUtils.isBlank(fechaFacturacion)){
				fechaFacturacion = pedidoControlFacturacion.getFechaProceso();
			}
			
			if(StringUtils.isEmpty(montoMinimoDeuda) || StringUtils.isBlank(montoMinimoDeuda)){
				montoMinimoDeuda = pedidoControlFacturacion.getMontoMinimoDeuda().toString();
			}
			
			if(StringUtils.isEmpty(unidadesMaximo) || StringUtils.isBlank(unidadesMaximo)){
				unidadesMaximo = Integer.toString(pedidoControlFacturacion.getUnidadesMaximo());
			}
						
			params.put("fechaFacturacion", fechaFacturacion);
			params.put("fechaProceso", fechaFacturacion);
			params.put("montoMinimoDeuda", montoMinimoDeuda);
			params.put("unidadesMaximo", unidadesMaximo);
			params.put("numeroLote", pedidoControlFacturacion.getNumeroLote());
			
			pedidoControlFacturacion.setFechaProceso(fechaFacturacion);
			pedidoControlFacturacion.setMontoMinimoDeuda(new BigDecimal(montoMinimoDeuda));
			pedidoControlFacturacion.setUnidadesMaximo(Integer.parseInt(unidadesMaximo));
			
			pedidoControlFacturacion.setUsuario(usuario.getLogin());
			this.mantenimientoOCRPedidoControlFacturacionService.updateControlFacturacion(pedidoControlFacturacion, usuario);
			mensajeEXito =  getWebApplicationContext().getMessage("campana.updated", null, getLocaleIdioma(pais.getCodigoIdiomaIso()));
		        aCOWebServiceResponse.setEjecucionExitosa(true);
	                aCOWebServiceResponse.setMensaje(mensajeEXito);

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

	@Override
	protected Map executeProcess(Map params) throws Exception {
		return params;
	}

}
