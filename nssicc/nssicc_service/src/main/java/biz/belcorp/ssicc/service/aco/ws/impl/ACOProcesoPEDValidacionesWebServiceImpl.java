package biz.belcorp.ssicc.service.aco.ws.impl;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.aco.ws.ACOProcesoPEDValidacionesWebService;
import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;
import es.indra.sicc.cmn.negocio.integracion.ConstantesIntegracion;
import es.indra.sicc.cmn.negocio.integracion.DTOEntradaSICC;
import es.indra.sicc.cmn.negocio.integracion.DTOSalidaSICC;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ACOProcesoPEDValidacionesWebServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
public class ACOProcesoPEDValidacionesWebServiceImpl extends BaseProcesoSICCAbstractWebService 
		implements ACOProcesoPEDValidacionesWebService {

	/**
	 * @param codigoPais
	 * @param codigoUsuario
	 * @param codigoPeriodo
	 * @param fechaFacturacion
	 * @return
	 * @throws RemoteException
	 */
	public ACOWebServiceResponse ejecutarProcesoPEDValidaciones(
			String codigoPais, String codigoUsuario, String codigoPeriodo, String fechaFacturacion) throws RemoteException {
		String mensajeError = "";
		boolean estado = false;
		ACOWebServiceResponse objetoRespuesta = new ACOWebServiceResponse();

		if (log.isDebugEnabled()) {
			log.debug("ejecutarProcesoPEDValidaciones");
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
		
			if (StringUtils.isBlank(codigoPeriodo)) {
				codigoPeriodo = controlFacturacion.getCodigoPeriodo();
			}
		
			if (StringUtils.isBlank(fechaFacturacion)) {
				fechaFacturacion = controlFacturacion.getFechaProceso();
			}

			//2) Creamos el DTO de Entrada para ser enviado al EJB de SICC
			DTOEntradaSICC dtoEntrada = obtenerDTOEntradaSICC(codigoPais, codigoUsuario, codigoPeriodo, 
													fechaFacturacion, pais.getCodigoIdiomaIso());
			dtoEntrada.setProcesoNegocio(ConstantesIntegracion.PROCESO_MONITOR_GP2);
			
			//3) Ejecutamos el proceso SICC invocando al EJB puente 
			log.info("DTO enviado a SICC: ");
			log.info(dtoEntrada.toString());
	        
			log.info("INVOCANDO PROCESO SICC: " + dtoEntrada.getProcesoNegocio());
			DTOSalidaSICC dtoSalida = this.ejecutarProceso(dtoEntrada);
			
			//4) Obtenemos el codigoProcesoBatch para consultar en BD su finalizacion
			log.info("DTO recibido de SICC: ");
			log.info(dtoSalida.toString());
			
			Map mapDatosProceso = this.esperarProcesamiento(dtoSalida.getIdProcesoBatch());
			
			//5) pasamos los codigos de finalizacion de Proceso de SICC al proceso de SSICC
			String codRetorno = (String)mapDatosProceso.get("codigoFinalizacion");
			String msgRetorno = (String)mapDatosProceso.get("mensajeFinalizacion");
			
			log.info("codRetornoSICC: " + codRetorno);
			log.info("msgRetornoSICC: " + msgRetorno);

			//SE RECUPERA ERROR, EN CASO DE QUE SE HAYA CAIDO PROCESOS ESPECIALES DE GP3 Y SE
			//VUELVE A EJECUTAR, PORQUE LA MAYORIA DE CASOS ES POR DESCOMPILACION DE PAQUETES
			String errorProcesoBatch = interfazSiCCDAO.getErrorProcesoBatchSICC(dtoSalida.getIdProcesoBatch());
			
			log.info("errorProcesoBatch: " + errorProcesoBatch);
			if(errorProcesoBatch!=null && "ERROR PROCESOS ESPECIALES".equals(errorProcesoBatch)) {
				Map paramsAux = new HashMap();
				
				paramsAux.put("codigoPais", codigoPais);
				paramsAux.put("periodo", codigoPeriodo);
				paramsAux.put("fechaFact", fechaFacturacion);
				paramsAux.put("psusuario", codigoUsuario);
		    	
				mantenimientoOCRPedidoControlFacturacionService.executeProcesoOCRProcesosEspecialesOCS(paramsAux);
			}
			
			//6)Respuesta al WebService
			objetoRespuesta.setMensaje("");
			estado = true;
			objetoRespuesta.setEjecucionExitosa(estado);
			
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
