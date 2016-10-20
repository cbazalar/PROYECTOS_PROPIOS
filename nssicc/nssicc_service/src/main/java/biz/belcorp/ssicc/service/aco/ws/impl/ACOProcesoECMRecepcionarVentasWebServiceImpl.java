package biz.belcorp.ssicc.service.aco.ws.impl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.aco.ws.ACOProcesoECMRecepcionarVentasWebService;
import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;
import biz.belcorp.ssicc.service.aco.ws.beans.ParametroACOWebService;
import es.indra.sicc.cmn.negocio.integracion.ConstantesIntegracion;
import es.indra.sicc.cmn.negocio.integracion.DTOEntradaSICC;
import es.indra.sicc.cmn.negocio.integracion.DTOSalidaSICC;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ACOProcesoINTRecepcionarVentasECMWebServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
public class ACOProcesoECMRecepcionarVentasWebServiceImpl extends BaseProcesoSICCAbstractWebService 
		implements ACOProcesoECMRecepcionarVentasWebService {

	/**
	 * @param codigoPais
	 * @param codigoUsuario
	 * @param codigoPeriodo
	 * @param fechaFacturacion
	 * @return
	 * @throws RemoteException
	 */
	public ACOWebServiceResponse ejecutarProcesoECMRecepcionarVentas(
			String codigoPais, String codigoUsuario) throws RemoteException {
		String mensajeError = "";
		boolean estado = false;
		ACOWebServiceResponse objetoRespuesta = new ACOWebServiceResponse();

		if (log.isDebugEnabled()) {
			log.debug("ejecutarProcesoECMRecepcionarVentas");
		}

		try {
			//1) Validamos los parametros recibidos del WebService
			Pais pais = this.paisService.getPais(codigoPais);
			if (StringUtils.isBlank(codigoPais)
					|| StringUtils.isEmpty(codigoPais)) {
				mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarPais", null, 
														getLocaleIdioma(pais.getCodigoIdiomaIso()));
				throw new Exception(mensajeError);
			}

			if (StringUtils.isBlank(codigoUsuario)
					|| StringUtils.isEmpty(codigoUsuario)) {
				mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarUsuario", null, 
														getLocaleIdioma(pais.getCodigoIdiomaIso()));
				throw new Exception(mensajeError);
			}

			Map criteria = new HashMap();
			criteria.put("codigoPais", codigoPais);
			criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
			criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente
		
			PedidoControlFacturacion controlFacturacion = this.mantenimientoOCRPedidoControlFacturacionService.getControlFacturacionById(criteria);
			String codigoPeriodo = controlFacturacion.getCodigoPeriodo();
			String fechaFacturacion = controlFacturacion.getFechaProceso();

			//2) Creamos el DTO de Entrada para ser enviado al EJB de SICC
			DTOEntradaSICC dtoEntrada = obtenerDTOEntradaSICC(codigoPais, codigoUsuario, codigoPeriodo, 
													fechaFacturacion, pais.getCodigoIdiomaIso());
			dtoEntrada.setProcesoNegocio(ConstantesIntegracion.PROCESO_INTERFAZ_RECEPCIONAR_ECM);
			
			//3) Ejecutamos el proceso SICC invocando al EJB puente 
			log.info("DTO enviado a SICC: ");
			log.info(dtoEntrada.toString());
	        
			log.info("INVOCANDO PROCESO SICC: " + dtoEntrada.getProcesoNegocio());
			DTOSalidaSICC dtoSalida = this.ejecutarProceso(dtoEntrada);
			
			//4) Obtenemos el codigoProcesoBatch para consultar en BD su finalizacion
			log.info("DTO recibido de SICC: ");
			log.info(dtoSalida.toString());
			
			String idProcesosBatch = dtoSalida.getIdProcesoBatch();
			StringTokenizer st = new StringTokenizer(idProcesosBatch, ",");
			
			while(st.hasMoreTokens()) {
				String idProcesoBatch = st.nextToken();
				Map mapDatosProceso = this.esperarProcesamiento(idProcesoBatch);
			
				//5) pasamos los codigos de finalizacion de Proceso de SICC al proceso de SSICC
				String codRetorno = (String)mapDatosProceso.get("codigoFinalizacion");
				String msgRetorno = (String)mapDatosProceso.get("mensajeFinalizacion");
				
				log.info("codRetornoSICC: " + codRetorno);
				log.info("msgRetornoSICC: " + msgRetorno);
			}
			
			//6)Respuesta al WebService
			objetoRespuesta.setMensaje("");
			estado = true;
			objetoRespuesta.setEjecucionExitosa(estado);
			
			//Mandamos el numero de lote de la interfaz
			List<ParametroACOWebService> respuestaWebService =  new ArrayList<ParametroACOWebService>();				
			ParametroACOWebService parametroACOWebService = new ParametroACOWebService();
			parametroACOWebService.setNombre("numeroLote");
			parametroACOWebService.setValor(dtoSalida.getNumeroLote());
			respuestaWebService.add(parametroACOWebService);
			objetoRespuesta.setRespuestaWebService(respuestaWebService.toArray(new ParametroACOWebService[respuestaWebService.size()]));			
			
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

}

