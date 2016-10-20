package biz.belcorp.ssicc.service.spusicc.pedidos.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Idioma;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.InterfazSiCCDAO;
import biz.belcorp.ssicc.dao.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.pedidos.ProcesoPEDEjecutarGPService;
import es.indra.sicc.cmn.negocio.integracion.ConstantesIntegracion;
import es.indra.sicc.cmn.negocio.integracion.DTOEntradaSICC;
import es.indra.sicc.cmn.negocio.integracion.DTOSalidaSICC;
import es.indra.sicc.cmn.negocio.integracion.MONIntegracionSICC;

/**
 * @author Sergio Apaza
 */
@Service("spusicc.procesoPEDEjecutarGPService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoPEDEjecutarGPServiceImpl extends BaseService implements ProcesoPEDEjecutarGPService {
	
	@Resource(name="sisicc.interfazSiCCDAO")
	private InterfazSiCCDAO interfazSiCCDAO;
	
	@Resource(name="ejb.integracionSICC")
	private MONIntegracionSICC monIntegracionSICC;
	
	@Resource(name="spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionDAO")
	MantenimientoOCRPedidoControlFacturacionDAO mantenimientoOCRPedidoControlFacturacionDAO;
	
	private Long tiempoRefrescoBatch = new Long(500);
	
	/**
	 * @return the monIntegracionSICC
	 */
	public MONIntegracionSICC getMonIntegracionSICC() {
		return monIntegracionSICC;
	}

	/**
	 * @param monIntegracionSICC the monIntegracionSICC to set
	 */
	public void setMonIntegracionSICC(MONIntegracionSICC monIntegracionSICC) {
		this.monIntegracionSICC = monIntegracionSICC;
	}
	
	/**
	 * @return the tiempoRefrescoBatch
	 */
	public Long getTiempoRefrescoBatch() {
		return tiempoRefrescoBatch;
	}

	/**
	 * @param tiempoRefrescoBatch the tiempoRefrescoBatch to set
	 */
	public void setTiempoRefrescoBatch(Long tiempoRefrescoBatch) {
		this.tiempoRefrescoBatch = tiempoRefrescoBatch;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.ProcesoPEDEjecutarGPService#executeEjecutarGP2(java.util.Map)
	 */
	public void executeEjecutarGP2(Map params) throws Exception {
		//1) Creamos el DTO de Entrada para ser enviado al EJB de SICC
		DTOEntradaSICC dtoEntrada = obtenerDTOEntradaSICC(params);
		dtoEntrada.setProcesoNegocio(ConstantesIntegracion.PROCESO_MONITOR_GP2);
		
		//2) Ejecutamos el proceso SICC invocando al EJB
		log.info("DTO enviado a SICC: ");
		log.info(dtoEntrada.toString());
        
		log.info("INVOCANDO PROCESO SICC: " + dtoEntrada.getProcesoNegocio());
		DTOSalidaSICC dtoSalida = monIntegracionSICC.ejecutarProceso(dtoEntrada);
		
		//3) Obtenemos el codigoProcesoBatch para consultar en BD su finalizacion
		log.info("DTO recibido de SICC: ");
		log.info(dtoSalida.toString());
		
		Map mapDatosProceso = esperarProcesamiento(dtoSalida.getIdProcesoBatch());
		
		//SE RECUPERA ERROR, EN CASO DE QUE SE HAYA CAIDO PROCESOS ESPECIALES DE GP3 Y SE
		//VUELVE A EJECUTAR, PORQUE LA MAYORIA DE CASOS ES POR DESCOMPILACION DE PAQUETES
		String errorProcesoBatch = interfazSiCCDAO.getErrorProcesoBatchSICC(dtoSalida.getIdProcesoBatch());
		
		log.info("errorProcesoBatch: " + errorProcesoBatch);
		if(errorProcesoBatch!=null && "ERROR PROCESOS ESPECIALES".equals(errorProcesoBatch)) {
			Usuario usuario = (Usuario)params.get("usuario");
			Map paramsAux = new HashMap();
			
			paramsAux.put("codigoPais", params.get("codigoPais"));
			paramsAux.put("periodo", params.get("codigoPeriodo"));
			paramsAux.put("fechaFact", params.get("fechaProceso"));
			paramsAux.put("psusuario", usuario.getLogin());
	    	
			mantenimientoOCRPedidoControlFacturacionDAO.executeProcesoOCRProcesosEspecialesOCS(paramsAux);
		}
		
		//4) pasamos los codigos de finalizacion de Proceso de SICC al proceso de SSICC
		String codRetorno = (String)mapDatosProceso.get("codigoFinalizacion");
		String msgRetorno = (String)mapDatosProceso.get("mensajeFinalizacion");
		
		log.info("codRetornoSICC: " + codRetorno);
		log.info("msgRetornoSICC: " + msgRetorno);
		
		params.put("codRetorno", codRetorno);
		params.put("msgRetorno", msgRetorno);
	}

	/**
	 * Obtiene los datos de Parametros de Entrada SICC
	 * 
	 * @param params
	 * @return
	 */
	private DTOEntradaSICC obtenerDTOEntradaSICC(Map params) {
		DTOEntradaSICC dto = new DTOEntradaSICC();
		
		//obtenemos el codigoPais y codigoIsoIdioma
		dto.setCodigoPais(params.get("codigoPais").toString());
		
		//obtenemos el codigo Usuario
		Usuario usuario = (Usuario)params.get("usuario");
		dto.setCodigoUsuario(usuario.getLogin());

		//obtenemos el idioma ISO
		Idioma idioma = usuario.getIdioma();
		dto.setCodigoIsoIdioma(idioma.getCodigoISO());

		//obtenemos el codigo de Periodo y Fecha de Proceso
		dto.setCodigoPeriodo(params.get("codigoPeriodo").toString());
		dto.setFechaProceso(params.get("fechaProceso").toString());
		
		return dto;
	}
	
	/**
	 * Espera el termino del proceso Batch que se esta ejecutando en SICC, para
	 * ello esta leyendo constantemente la tabla GEN_PROCE_BATCH
	 * 
	 * @param codigoProcesoBatch
	 * @return
	 * @throws Exception
	 */
	private Map esperarProcesamiento(String codigoProcesoBatch) throws Exception {
        boolean termino = false;
        Map mapDatos = null;
        
        while (!termino) {
        	mapDatos = interfazSiCCDAO.getDatosProcesoBatchSICC(codigoProcesoBatch);
        	
            if(mapDatos.get("fechaFin") != null) 
                termino = true;
            else {
                try {
                    Thread.sleep (this.tiempoRefrescoBatch);
                }
                catch (InterruptedException ie) {
                    log.info("ProcesoPEDEjecutarGPServiceImpl.esperarProcesamiento(): Salida ");
                    throw new Exception ("Error ejecutando Esperar Procesamiento SICC", ie);
                }
            }                
        }		
        
        return mapDatos;
	}

}
