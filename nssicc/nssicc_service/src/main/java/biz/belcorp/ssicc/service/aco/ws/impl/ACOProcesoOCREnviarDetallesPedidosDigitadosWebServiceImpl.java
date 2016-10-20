/**
 * 
 */
package biz.belcorp.ssicc.service.aco.ws.impl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.spusicc.sto.model.AccionTipoDocumento;
import biz.belcorp.ssicc.dao.spusicc.sto.model.TipoDocumentoDigitadoPK;
import biz.belcorp.ssicc.service.aco.ws.ACOProcesoOCREnviarDetallesPedidosDigitadosWebService;
import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;
import biz.belcorp.ssicc.service.aco.ws.beans.ParametroACOWebService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazResult;
import biz.belcorp.ssicc.service.sisicc.framework.beans.SSiCC_Desfasado_InterfazExecutionResult;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.pedidos.ProcesoPEDService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOService;
import biz.belcorp.ssicc.service.spusicc.sto.framework.ProcesoSTOExecutionService;

/**
 * 
 * TODO Include class description here. 
 * 
 * <p>
 * <a href="ACOProcesoOCREnviarDetallesPedidosDigitadosWebServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@sigcomt.com">Ivan Tocto Jaimes</a>
 */

public class ACOProcesoOCREnviarDetallesPedidosDigitadosWebServiceImpl extends
		BaseInterfazHiloAbstractWebService implements
		ACOProcesoOCREnviarDetallesPedidosDigitadosWebService {

	MantenimientoOCRPedidoControlFacturacionService mantenimientoOCRPedidoControlFacturacionService;
	ProcesoPEDService procesoPEDService;
	ProcesoSTOService procesoSTOService;
	InterfazSiCCService interfazSiCCService;
	ProcesoSTOExecutionService procesoSTOExecutionService;
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.impl.BaseInterfazHiloAbstractWebService#onInit()
	 */
	@Override
	protected void onInit() throws ServiceException {
		super.onInit();		
		mantenimientoOCRPedidoControlFacturacionService = (MantenimientoOCRPedidoControlFacturacionService) getApplicationContext().getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		procesoPEDService = (ProcesoPEDService) getApplicationContext().getBean("spusicc.procesoPEDService");
		procesoSTOService = (ProcesoSTOService) getApplicationContext().getBean("spusicc.procesoSTOService");
		interfazSiCCService = (InterfazSiCCService) getApplicationContext().getBean("sisicc.interfazSiCCService");
		procesoSTOExecutionService = (ProcesoSTOExecutionService) getApplicationContext().getBean("spusicc.procesoSTOExecutionService");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.ACOProcesoOCREnviarDetallesPedidosDigitadosWebService#ejecutarProceso(java.lang.String, java.lang.String, java.lang.String)
	 */
	public ACOWebServiceResponse ejecutarProceso(
				String codigoPais, 
				String indValidacionSTO, 
				String codigoUsuario,
				String fechaFacturacion) throws RemoteException {

		boolean estado = false;
		ACOWebServiceResponse objetoRespuesta = new ACOWebServiceResponse();
		ParametroACOWebService parametroACOWebService = null;
		SSiCC_Desfasado_InterfazExecutionResult executionResult = null;
		InterfazResult interfazResult = null;
		String mensajeError = "";
		String CODIGO_BATCH = Constants.OCR_CODIGO_PROCESO_BATCH_ENVIAR_DETALLES_PEDIDOS_DIGITADOS;
		String CODIGO_INTERFAZ = Constants.OCR_CODIGO_INTERFAZ_ENVIAR_DETALLES_PEDIDOS_DIGITADOS;
		String CODIGO_SISTEMA = Constants.OCR_CODIGO_SISTEMA;

		if (log.isDebugEnabled()) {
			log.debug("ejecutarProceso");
		}

		try {
			Pais pais = this.paisService.getPais(codigoPais);
			if (StringUtils.isBlank(codigoPais)	|| StringUtils.isEmpty(codigoPais)) {
				mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarPais", null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
				throw new Exception(mensajeError);
			}

			if (StringUtils.isBlank(codigoUsuario) || StringUtils.isEmpty(codigoUsuario)) {
				mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarUsuario", null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
				throw new Exception(mensajeError);
			}
			
			if (StringUtils.isBlank(indValidacionSTO) || StringUtils.isEmpty(indValidacionSTO)) {
				indValidacionSTO = Constants.SI;
			}
						
			Usuario usuario = this.obtenerUsuarioByDefault(codigoUsuario);

			Map criteria = new HashMap();
			criteria.put("codigoPais", pais.getCodigo());
			criteria.put("codigoUsuario", codigoUsuario);
			criteria.put("codigoProcesoBatch", CODIGO_BATCH);
			criteria.put("codigoSistema", CODIGO_SISTEMA);
			criteria.put("codigoInterfaz", CODIGO_INTERFAZ);
			criteria.put("descripcion", getDescripcionInterfaz(criteria));
			criteria.put("usuario", usuario);	
			criteria.put("indValidacionSTO", indValidacionSTO);
			criteria.put("fechaFacturacion", fechaFacturacion);
									
			criteria.put("codigoPais", codigoPais);
			criteria.put("estadoCampanha", Constants.NUMERO_CERO); // Indica Campanha Activa
			criteria.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente
			PedidoControlFacturacion controlFacturacion = mantenimientoOCRPedidoControlFacturacionService.getControlFacturacionById(criteria);

			criteria.put("codigoPeriodo", controlFacturacion.getCodigoPeriodo());
			
			criteria.put("indOrigen", Constants.STO_ORIGEN_DIGITADO);
			criteria.put("indicadorCarga", Constants.SI);
			
			List pedidos = procesoPEDService.getPedidosDigitadosByCriteria(criteria);
			if(pedidos == null || pedidos.size() == 0){
				mensajeError = getWebApplicationContext().getMessage("procesoOCREnviarDetallesPedidosDigitadosForm.sinPedidos", null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
				throw new Exception(mensajeError);				
			}else{
				criteria.put("registrosProcesados", pedidos.size());
			}
			
			executionResult = this.executeInterfaz(criteria);

			List list = executionResult.getInterfazResults();
			interfazResult = (InterfazResult) list.get(0);

			if (interfazResult != null) {
				if(StringUtils.equals(this.estadoProceso, Constants.OK)){
					estado = true;
				}
				objetoRespuesta.setEjecucionExitosa(estado);
				objetoRespuesta.setMensaje(this.mensajeError);
				log.debug(objetoRespuesta.getMensaje());

				List<ParametroACOWebService> respuestaWebService = new ArrayList<ParametroACOWebService>();

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
				
				parametroACOWebService = new ParametroACOWebService();
				parametroACOWebService.setNombre("indicadorProceso");
				parametroACOWebService.setValor((String) criteria.get("indicadorProceso"));
				respuestaWebService.add(parametroACOWebService);

				objetoRespuesta.setRespuestaWebService(respuestaWebService.toArray(new ParametroACOWebService[respuestaWebService.size()]));
			}


		} catch (Exception e) {
			log.error(e.getMessage());
			objetoRespuesta.setMensaje(e.getMessage());
			objetoRespuesta.setRespuestaWebService(null);
			objetoRespuesta.setEjecucionExitosa(estado);
		} finally {
			log.debug("Estado del servicio: " + estado);
			if (estado) {
				log.info("Se ejecuto el servicio con exito.");
			} else {
				log.error("Excepcion en el servicio.");
			}
		}

		return objetoRespuesta;
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.impl.BaseInterfazHiloAbstractWebService#prepareParamsBeforeExecute(java.util.Map, biz.belcorp.ssicc.model.Pais)
	 */
	@Override
	protected Map prepareParamsBeforeExecute(Map params, Pais pais) throws Exception {

		params = super.prepareParamsBeforeExecute(params, pais);
		
		params.put("indOrigen", Constants.STO_ORIGEN_DIGITADO);		
		params.put("codTipoDocu", Constants.STO_TIPO_DOCUMENTO_OCC);

		String numLoteSTO = procesoSTOService.updateLoteSTO(new TipoDocumentoDigitadoPK(pais.getCodigo(), Constants.STO_TIPO_DOCUMENTO_OCC));
		
		params.put("numLoteSTO",numLoteSTO);
				
		interfazSiCCService.updateInterfazProcesoBatch(params);
				
		return params;
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.impl.BaseInterfazAbstractWebService#afterExecuteInterfaz(java.util.Map, biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazExecutionResult)
	 */
	@Override
	protected void afterExecuteInterfaz(Map params, SSiCC_Desfasado_InterfazExecutionResult interfazExecutionResult) throws Exception {
		super.afterExecuteInterfaz(params, interfazExecutionResult);
		
        if (interfazExecutionResult.ejecucionCompletada()){
            String	indValidacionSTO = (String)params.get("indValidacionSTO");
            if (indValidacionSTO!=null && indValidacionSTO.equals(Constants.SI)){
            	String codigoPais = (String)params.get("codigoPais");            	
        	   	AccionTipoDocumento accionTipoDocumento = new AccionTipoDocumento(codigoPais,Constants.STO_TIPO_DOCUMENTO_OCC,Constants.STO_ACCI_VALI_AUTO);    			
            	procesoSTOExecutionService.execute(accionTipoDocumento,params, null);
            }
        }
		
	}

}
