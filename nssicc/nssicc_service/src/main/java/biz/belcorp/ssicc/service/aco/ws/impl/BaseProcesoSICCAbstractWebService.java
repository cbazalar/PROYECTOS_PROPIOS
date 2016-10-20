package biz.belcorp.ssicc.service.aco.ws.impl;

import java.util.Locale;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.remoting.jaxrpc.ServletEndpointSupport;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.sisicc.InterfazSiCCDAO;
import biz.belcorp.ssicc.service.PaisService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import es.indra.sicc.cmn.negocio.integracion.DTOEntradaSICC;
import es.indra.sicc.cmn.negocio.integracion.DTOSalidaSICC;
import es.indra.sicc.cmn.negocio.integracion.MONIntegracionSICC;

/**
 * Clase Service abstracta para la ejecucion de Procesos SICC usando WS
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *                   
 */
public abstract class BaseProcesoSICCAbstractWebService extends ServletEndpointSupport {
	
	Log log = LogFactory.getLog(BaseProcesoHiloAbstractWebService.class);

	protected MantenimientoOCRPedidoControlFacturacionService mantenimientoOCRPedidoControlFacturacionService;
	protected PaisService paisService;
	protected InterfazSiCCDAO interfazSiCCDAO;
	protected MONIntegracionSICC monIntegracionSICC;
	protected Long tiempoRefrescoBatch;
	
	/* (non-Javadoc)
	 * @see org.springframework.remoting.jaxrpc.ServletEndpointSupport#onInit()
	 */
	protected void onInit() throws ServiceException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'onInit BaseProcesoSICCAbstractWebService' method");
		}
		this.mantenimientoOCRPedidoControlFacturacionService = (MantenimientoOCRPedidoControlFacturacionService) getWebApplicationContext().getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		this.paisService = (PaisService) getWebApplicationContext().getBean("paisService");
		this.interfazSiCCDAO = (InterfazSiCCDAO)getWebApplicationContext().getBean("sisicc.interfazSiCCDAO");
		this.monIntegracionSICC = (MONIntegracionSICC) getWebApplicationContext().getBean("ejb.integracionSICC");
		this.tiempoRefrescoBatch = new Long(500);
	}
	
	/**
	 * Ejecuta el proceso del lado de SICC
	 * 
	 * @param dtoEntrada
	 * @return
	 */
	protected DTOSalidaSICC ejecutarProceso(DTOEntradaSICC dtoEntrada) throws Exception {
		DTOSalidaSICC dtoSalida = monIntegracionSICC.ejecutarProceso(dtoEntrada);
		return dtoSalida;
	}	
	
	/**
	 * Obtiene los datos de Parametros de Entrada SICC
	 * 
	 * @param params
	 * @return
	 */
	protected DTOEntradaSICC obtenerDTOEntradaSICC(String codigoPais, String codigoUsuario, String codigoPeriodo, 
													String fechaProceso, String codigoIdiomaIso) {
		DTOEntradaSICC dto = new DTOEntradaSICC();
		
		//obtenemos el codigoPais y codigoIsoIdioma
		dto.setCodigoPais(codigoPais);
		
		//obtenemos el codigo Usuario
		dto.setCodigoUsuario(codigoUsuario);

		//obtenemos el idioma ISO
		dto.setCodigoIsoIdioma(codigoIdiomaIso);

		//obtenemos el codigo de Periodo y Fecha de Proceso
		dto.setCodigoPeriodo(codigoPeriodo);
		dto.setFechaProceso(fechaProceso);
		
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
	protected Map esperarProcesamiento(String codigoProcesoBatch) throws Exception {
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

	/**
	 * @param codigoIsoIdioma
	 * @return
	 */
	protected final Locale getLocaleIdioma(String codigoIsoIdioma) {
		if (StringUtils.isNotEmpty(codigoIsoIdioma)) {
			if (Constants.EDU_IDIOMA_DEFAULT_ES.equals(codigoIsoIdioma.toLowerCase()))
				return new Locale(Constants.EDU_IDIOMA_DEFAULT_ES);
			else {
				log.debug("codigoIsoIdioma " + codigoIsoIdioma);
				return new Locale(codigoIsoIdioma.toLowerCase());
			}
		}
		log.debug("default " + codigoIsoIdioma);
		return new Locale(Constants.EDU_IDIOMA_DEFAULT_ES);
	}
	
}
