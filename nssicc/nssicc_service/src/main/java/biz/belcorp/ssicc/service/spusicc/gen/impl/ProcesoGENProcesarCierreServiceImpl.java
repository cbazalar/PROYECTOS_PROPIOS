package biz.belcorp.ssicc.service.spusicc.gen.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Idioma;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.InterfazSiCCDAO;
import biz.belcorp.ssicc.dao.spusicc.fac.ProcesoFACProcesarCierresDAO;
import biz.belcorp.ssicc.dao.spusicc.lec.ProcesoLECCalcularRecuperacionDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.gen.ProcesoGENProcesarCierreService;
import es.indra.mare.common.exception.MareException;
import es.indra.sicc.cmn.negocio.integracion.ConstantesIntegracion;
import es.indra.sicc.cmn.negocio.integracion.DTOEntradaSICC;
import es.indra.sicc.cmn.negocio.integracion.DTOSalidaSICC;
import es.indra.sicc.cmn.negocio.integracion.MONIntegracionSICC;

/**
 * @author Sergio Apaza
 */
@Service("spusicc.procesoGENProcesarCierreService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoGENProcesarCierreServiceImpl extends BaseService implements ProcesoGENProcesarCierreService {
	
	@Resource(name = "sisicc.interfazSiCCDAO")
	private InterfazSiCCDAO interfazSiCCDAO;
	
	@Resource(name="ejb.integracionSICC")
	private MONIntegracionSICC monIntegracionSICC;
	
	private Long tiempoRefrescoBatch = new Long(500);
	
	@Resource(name = "spusicc.ProcesoFACProcesarCierresDAO")
	private ProcesoFACProcesarCierresDAO procesoFACProcesarCierresDAO;
	
	@Resource(name = "spusicc.procesoLECCalcularRecuperacionDAO")
	private ProcesoLECCalcularRecuperacionDAO procesoLECCalcularRecuperacionDAO;
	
	
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
	 * @see biz.belcorp.ssicc.spusicc.gen.service.ProcesoGENProcesarCierreService#getZonasACerrar(java.util.Map)
	 */
	public List getZonasACerrar(Map criteria) {
		return procesoFACProcesarCierresDAO.getZonasACerrar(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.gen.service.ProcesoGENProcesarCierreService#getRegionesACerrar(java.util.Map)
	 */
	public List getRegionesACerrar(Map criteria) {
		return procesoFACProcesarCierresDAO.getRegionesACerrar(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.gen.service.ProcesoGENProcesarCierreService#getProcesosCierreZona(java.util.Map)
	 */
	public List getProcesosCierreZona(Map criteria) {
		return procesoFACProcesarCierresDAO.getProcesosCierreZona(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.gen.service.ProcesoGENProcesarCierreService#getProcesosCierreRegion(java.util.Map)
	 */
	public List getProcesosCierreRegion(Map criteria) {
		return procesoFACProcesarCierresDAO.getProcesosCierreRegion(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.gen.service.ProcesoGENProcesarCierreService#existeZonasxRegionSinProcesar(java.util.Map)
	 */
	public boolean existeZonasxRegionSinProcesar(Map criteria) {
		return procesoFACProcesarCierresDAO.existeZonasxRegionSinProcesar(criteria);
	}	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.gen.service.ProcesoGENProcesarCierreService#executeProcesarCierreZona(java.util.Map)
	 */
	public void executeProcesarCierreZona(Map params) throws Exception {
		Usuario usuario = (Usuario)params.get("usuario");
		
		//1) Creamos el DTO de Entrada para ser enviado al EJB de SICC
		DTOEntradaSICC dtoEntrada = obtenerDTOEntradaSICC(params);
		dtoEntrada.setProcesoNegocio(ConstantesIntegracion.PROCESO_CIERRE_ZONA);
		
		//pasamos por parametro las zonas
		Long []zonaList = (Long[])params.get("zonaList");
		dtoEntrada.getParametros().put("listaZonas", zonaList);
		
		//2) Ejecutamos el proceso SICC invocando al EJB
		log.info("DTO enviado a SICC: ");
		log.info(dtoEntrada.toString());
        
		try {	    		
			log.info("INVOCANDO PROCESO SICC: " + dtoEntrada.getProcesoNegocio());
			DTOSalidaSICC dtoSalida = monIntegracionSICC.ejecutarProceso(dtoEntrada);

			//3) Obtenemos el codigoProcesoBatch para consultar en BD su finalizacion
			log.info("DTO recibido de SICC: ");
			log.info(dtoSalida.toString());
			
			Map mapDatosProceso = esperarProcesamiento(dtoSalida.getIdProcesoBatch());
			
			//4) pasamos los codigos de finalizacion de Proceso de SICC al proceso de SSICC
			String codRetorno = (String)mapDatosProceso.get("codigoFinalizacion");
			String msgRetorno = (String)mapDatosProceso.get("mensajeFinalizacion");
			
			log.info("codRetornoSICC: " + codRetorno);
			log.info("msgRetornoSICC: " + msgRetorno);
			
			params.put("codRetorno", codRetorno);
			params.put("msgRetorno", msgRetorno);
			
			if(codRetorno!=null && !"".equals(codRetorno.trim()) && !Constants.NUMERO_CERO.equals(codRetorno.trim())){
				throw new Exception(msgRetorno);
			}
			
			//5) Verificamos si todas las zonas fueron procesadas satisfactoriamente
			Map criteriaZona = new HashMap();
			criteriaZona.put("codigoPais", params.get("codigoPais"));
			criteriaZona.put("codigoPeriodo", params.get("codigoPeriodo"));
			
			int totalErrores = 0;
			for(int i=0; i<zonaList.length; i++) {
				criteriaZona.put("oidZona", zonaList[i].toString());
				
				boolean valido = procesoFACProcesarCierresDAO.validaCierreZona(criteriaZona);
				if(!valido) {
					log.error("La zona con oid=" + zonaList[i].toString()+ " no se terminaron todos sus procesos correctamente");
					totalErrores ++;
				}	
			}
			
			if(totalErrores > 0) {
				String mensajeError = messageSource.getMessage("procesoGENProcesarCierreZonaForm.msg.errorCierreZona",
										null,getLocale(usuario));
				params.put("codRetorno", Constants.CODIGO_PROCESO_BATCH_ERROR);
				params.put("msgRetorno", mensajeError);
			}
			
		} catch (Exception ex) {
			if(ex instanceof MareException) {
				MareException me = (MareException)ex;
				String mensajeError = messageSource.getMessage("procesoGENProcesarCierreZonaForm.msg.errorSICC." + me.getCode(),
						null,getLocale(usuario));
				
				log.error("Error INVOCANDO AL PROCESO SICC : " + mensajeError);
				throw new Exception(mensajeError);
			} else {
				log.error("Error INVOCANDO AL PROCESO SICC : " + ex.getMessage());
				throw ex;
			}
		}
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.gen.service.ProcesoGENProcesarCierreService#executeProcesarCierreRegion(java.util.Map)
	 */
	public void executeProcesarCierreRegion(Map params) throws Exception {
		Usuario usuario = (Usuario)params.get("usuario");

		//1) Creamos el DTO de Entrada para ser enviado al EJB de SICC
		DTOEntradaSICC dtoEntrada = obtenerDTOEntradaSICC(params);
		dtoEntrada.setProcesoNegocio(ConstantesIntegracion.PROCESO_CIERRE_REGION);
		
		//pasamos por parametro las regiones
		Long []regionList = (Long[])params.get("regionList");
		dtoEntrada.getParametros().put("listaRegiones", regionList);
		
		//2) Ejecutamos el proceso SICC invocando al EJB
		log.info("DTO enviado a SICC: ");
		log.info(dtoEntrada.toString());
        
		try {	    		
			log.info("INVOCANDO PROCESO SICC: " + dtoEntrada.getProcesoNegocio());
			DTOSalidaSICC dtoSalida = monIntegracionSICC.ejecutarProceso(dtoEntrada);

			//3) Obtenemos el codigoProcesoBatch para consultar en BD su finalizacion
			log.info("DTO recibido de SICC: ");
			log.info(dtoSalida.toString());
			
			Map mapDatosProceso = esperarProcesamiento(dtoSalida.getIdProcesoBatch());
			
			//4) pasamos los codigos de finalizacion de Proceso de SICC al proceso de SSICC
			String codRetorno = (String)mapDatosProceso.get("codigoFinalizacion");
			String msgRetorno = (String)mapDatosProceso.get("mensajeFinalizacion");
			
			log.info("codRetornoSICC: " + codRetorno);
			log.info("msgRetornoSICC: " + msgRetorno);
			
			if(codRetorno!=null && !"".equals(codRetorno.trim()) && !Constants.NUMERO_CERO.equals(codRetorno.trim())){
				throw new Exception(msgRetorno);
			}
			
			/*params.put("codRetorno", codRetorno);
			params.put("msgRetorno", msgRetorno);
			
			//5) Verificamos si todas las zonas fueron procesadas satisfactoriamente
			Map criteriaRegion = new HashMap();
			criteriaRegion.put("codigoPais", params.get("codigoPais"));
			criteriaRegion.put("codigoPeriodo", params.get("codigoPeriodo"));
			
			int totalErrores = 0;
			for(int i=0; i<regionList.length; i++) {
				criteriaRegion.put("oidRegion", regionList[i].toString());
				
				boolean valido = procesoFACProcesarCierresDAO.validaCierreRegion(criteriaRegion);
				if(!valido) {
					log.error("La region con oid=" + regionList[i].toString()+ " no se terminaron todos sus procesos correctamente");
					totalErrores ++;
				}	
			}
			
			if(totalErrores > 0) {
				String mensajeError = messageSource.getMessage("procesoGENProcesarCierreRegionForm.msg.errorCierreRegion",
										null,getLocale(usuario));
				params.put("codRetorno", Constants.CODIGO_PROCESO_BATCH_ERROR);
				params.put("msgRetorno", mensajeError);
			}*/
			
		} catch (Exception ex) {
			if(ex instanceof MareException) {
				MareException me = (MareException)ex;
				String mensajeError = messageSource.getMessage("procesoGENProcesarCierreRegionForm.msg.errorSICC." + me.getCode(),
						null,getLocale(usuario));
				
				log.error("Error INVOCANDO AL PROCESO SICC : " + mensajeError);
				throw new Exception(mensajeError);
			} else {
				log.error("Error INVOCANDO AL PROCESO SICC : " + ex.getMessage());
				throw ex;
			}
		}
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.gen.service.ProcesoGENProcesarCierreService#executeProcesarCierrePeriodo(java.util.Map)
	 */
	public void executeProcesarCierrePeriodo(Map params) throws Exception {
		Usuario usuario = (Usuario)params.get("usuario");

		//1) Creamos el DTO de Entrada para ser enviado al EJB de SICC
		DTOEntradaSICC dtoEntrada = obtenerDTOEntradaSICC(params);
		dtoEntrada.setProcesoNegocio(ConstantesIntegracion.PROCESO_CIERRE_PERIODO);
		
		//2) Ejecutamos el proceso SICC invocando al EJB
		log.info("DTO enviado a SICC: ");
		log.info(dtoEntrada.toString());
        
		try {	    		
			log.info("INVOCANDO PROCESO SICC: " + dtoEntrada.getProcesoNegocio());
			DTOSalidaSICC dtoSalida = monIntegracionSICC.ejecutarProceso(dtoEntrada);

			//3) Obtenemos el codigoProcesoBatch para consultar en BD su finalizacion
			log.info("DTO recibido de SICC: ");
			log.info(dtoSalida.toString());
			
			Map mapDatosProceso = esperarProcesamiento(dtoSalida.getIdProcesoBatch());
			
			//4) pasamos los codigos de finalizacion de Proceso de SICC al proceso de SSICC
			String codRetorno = (String)mapDatosProceso.get("codigoFinalizacion");
			String msgRetorno = (String)mapDatosProceso.get("mensajeFinalizacion");
			
			log.info("codRetornoSICC: " + codRetorno);
			log.info("msgRetornoSICC: " + msgRetorno);
			
			if(codRetorno!=null && !"".equals(codRetorno.trim()) && !Constants.NUMERO_CERO.equals(codRetorno.trim())){
				throw new Exception(msgRetorno);
			}
			
			//5) Verificamos si el periodo en SICC fue procesado correctamente
			/*Map criteriaPeriodo = new HashMap();
			criteriaPeriodo.put("codigoPais", params.get("codigoPais"));
			criteriaPeriodo.put("codigoPeriodo", params.get("codigoPeriodo"));
			
			boolean valido = procesoFACProcesarCierresDAO.validaCierrePeriodo(criteriaPeriodo);
			if(!valido) {
				log.error("El periodo " + params.get("codigoPeriodo") + " no se terminaron todos sus procesos correctamente");

				String mensajeError = messageSource.getMessage("procesoGENProcesosCierreCampaniaForm.msg.errorCierrePeriodo",
										null,getLocale(usuario));
				
				throw new Exception(mensajeError);
			}*/
			
		} catch (Exception ex) {
			if(ex instanceof MareException) {
				MareException me = (MareException)ex;
				String mensajeError = messageSource.getMessage("procesoGENProcesosCierreCampaniaForm.msg.errorSICC." + me.getCode(),
						null,getLocale(usuario));
				
				log.error("Error INVOCANDO AL PROCESO SICC : " + mensajeError);
				throw new Exception(mensajeError);
			} else {
				log.error("Error INVOCANDO AL PROCESO SICC : " + ex.getMessage());
				throw ex;
			}
		}
		
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
		if(params.get("codigoPeriodo")!=null)
			dto.setCodigoPeriodo(params.get("codigoPeriodo").toString());
		if(params.get("fechaFacturacion")!=null)
			dto.setFechaProceso(params.get("fechaFacturacion").toString());
		
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



	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.gen.service.ProcesoGENProcesarCierreService#getProcesosCierrePeriodo(java.util.Map)
	 */
	public List getProcesosCierrePeriodo(Map criteria) {
		return procesoFACProcesarCierresDAO.getProcesosCierrePeriodo(criteria);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.gen.service.ProcesoGENProcesarCierreService#validaPeriodoACerrar(java.util.Map)
	 */
	public boolean validaPeriodoACerrar(Map criteria) {
		return procesoFACProcesarCierresDAO.validaPeriodoACerrar(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.gen.service.ProcesoGENProcesarCierreService#existeRegionesSinProcesar(java.util.Map)
	 */
	public boolean existeRegionesSinProcesar(Map criteria) {
		return procesoFACProcesarCierresDAO.existeRegionesSinProcesar(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.gen.service.ProcesoGENProcesarCierreService#getIndicadorModEducacion(java.lang.String)
	 */
	public String getIndicadorModEducacion(String codigoPais) {
		return procesoFACProcesarCierresDAO.getIndicadorModEducacion(codigoPais);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.gen.service.ProcesoGENProcesarCierreService#executeProcesarCierreZona(java.util.Map)
	 */
	public void executeProcesarReemplazos(Map params) throws Exception {
		Usuario usuario = (Usuario)params.get("usuario");
		
		//1) Creamos el DTO de Entrada para ser enviado al EJB de SICC
		DTOEntradaSICC dtoEntrada = obtenerDTOEntradaSICC(params);
		dtoEntrada.setProcesoNegocio(ConstantesIntegracion.PROCESO_REEMPLAZOS_INCENTIVOS);
		
		Long oidConcurso = Long.parseLong((String)params.get("oidConcurso"));
		dtoEntrada.getParametros().put("oidConcurso", oidConcurso);
		
		//2) Ejecutamos el proceso SICC invocando al EJB
		log.info("DTO enviado a SICC: ");
		log.info(dtoEntrada.toString());
        
		try {	    		
			log.info("INVOCANDO PROCESO SICC: " + dtoEntrada.getProcesoNegocio());
			DTOSalidaSICC dtoSalida = monIntegracionSICC.ejecutarProceso(dtoEntrada);

			//3) Obtenemos el codigoProcesoBatch para consultar en BD su finalizacion
			log.info("DTO recibido de SICC: ");
			log.info(dtoSalida.toString());
			
		} catch (Exception ex) {
			if(ex instanceof MareException) {
				MareException me = (MareException)ex;
				String mensajeError = messageSource.getMessage("procesoGENProcesarCierreZonaForm.msg.errorSICC." + me.getCode(),
						null,getLocale(usuario));
				
				log.error("Error INVOCANDO AL PROCESO SICC : " + mensajeError);
				throw new Exception(mensajeError);
			} else {
				log.error("Error INVOCANDO AL PROCESO SICC : " + ex.getMessage());
				throw ex;
			}
		}
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.gen.service.ProcesoGENProcesarCierreService#getDevuelvePeriodoByCodigoPeriodo(java.util.Map)
	 */
	public String getDevuelvePeriodoByCodigoPeriodo(Map params)  {
		return this.procesoLECCalcularRecuperacionDAO.getDevuelvePeriodoByCodigoPeriodo(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.gen.service.ProcesoGENProcesarCierreService#getIndicadorCampannaRecaudo(java.util.Map)
	 */
	public String getIndicadorCampannaRecaudo(Map params)  {
		return this.procesoLECCalcularRecuperacionDAO.getIndicadorCampannaRecaudo(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.gen.service.ProcesoGENProcesarCierreService#getProcesosControlCierre(java.util.Map)
	 */
	public List getProcesosControlCierre(Map criteria) {
		return procesoFACProcesarCierresDAO.getProcesosControlCierre(criteria);
	}

}