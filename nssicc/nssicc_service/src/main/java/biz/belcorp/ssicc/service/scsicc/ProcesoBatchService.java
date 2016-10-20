/*
 * Created on 22-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.service.scsicc;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.NroLoteMultiHilo;
import biz.belcorp.ssicc.service.framework.Service;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.beans.SSiCC_Desfasado_InterfazExecutionResult;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazAbstractService;
import biz.belcorp.ssicc.service.sisicc.framework.impl.SSiCC_Desfasado_BaseInterfazHiloAbstractServiceImpl;
import biz.belcorp.ssicc.service.spusicc.sto.framework.BaseProcesoSTOAbstractService;
import biz.belcorp.ssicc.service.spusicc.sto.framework.beans.DocumentoSTOParams;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="ProcesoBatchService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar</a>
 * 
 */

public interface ProcesoBatchService extends Service {
	
	/**
     * Devuelve ID Secuencial Siguiente para colocarlo en el ID del Proceso Batch Actual
     * @return
     */
	public Long getSecuenciaSiguienteProcesoBatchActu();
	
	/**
     * Devuelve Lista de Procesos Batch en base a los criterios de seleccion registrados
     * @param criteria
     * @return
     */
	public List getProcesoBatchByCriteria(Map criteria);
	
	/**
     * Devuelve Lista de Procesos Batch Actuales en base a los criterios de seleccion registrados
     * @param criteria
     * @return
     */
	public List getProcesoBatchActuByCriteria(Map criteria);
	
	
	/**
	 * Obtiene los Nro de Lotes generados por un Proceso Batch
	 * @param criteria
	 * @return
	 */
	public List<NroLoteMultiHilo> getObtieneNroLoteProcesoBatchActu(Map criteria);
	
	/**
     * Devuelve Lista de Procesos Batch Historicos en base a los criterios de seleccion registrados
     * @param criteria
     * @return
     */
	public List getProcesoBatchHistoByCriteria(Map criteria); 
	
	/**
     * Inserta un proceso Batch 
     * @param criteria
     * @param usuario
     */
	public void insertProcesoBatchActu(Map criteria, Usuario usuario);
	
	/**
     * Inserta un proceso Batch 
     * @param criteria
     * @param usuario
     */
	public void insertProcesoBatchActu2(Map criteria, Usuario usuario) ;
	
	/**
     * Elimina un proceso Batch 
     * @param criteria
     * @param usuario
     */
	public void deleteProcesoBatchActu(Map criteria, Usuario usuario);
	
	/**
	 * Actualiza Proceso Batch sando una nueva transaccion
	 * @param criteria
	 * @param usuario
	 */
	public void updateProcesoBatchActu(Map criteria, Usuario usuario);
	
	/**
	 * Actualiza Proceso Batch sin usar una nueva transaccion
	 * @param criteria
	 * @param usuario
	 */
	public void updateProcesoBatchActu02(Map criteria, Usuario usuario);
	
	/**
	 * Actualiza Proceso Batch y Recomendacion usando una nueva transaccion
	 * @param criteria
	 * @param usuario
	 */
	public void updateProcesoBatchActu03(Map criteria, Usuario usuario);
	
	/**
	 * Actualiza Etapa de Proceso Batch usando una nueva conexion
	 * @param criteria
	 * @param usuario
	 */
	public void updateProcesoBatchEtapaProceso(Map criteria, Usuario usuario);
	
	public void updateEstadoProcesoBatchActu2(Map criteria, Usuario usuario);
	
	/**
	 * Actualiza Etapa de Proceso Batch sin usar una nueva conexion
	 * @param criteria
	 * @param usuario
	 */
	public void updateProcesoBatchEtapaProceso02(Map criteria, Usuario usuario);
	
	/**
	 * Analiza si existe recomendacion para el error producido
	 * @param criteria
	 */
	public void executeProcesoBatchActuRecomendacionError(Map criteria);
	
	/**
	 * Actualiza Estado de Proceso Batch
	 * @param criteria
	 * @param usuario
	 */
	public void updateEstadoProcesoBatchActu(Map criteria, Usuario usuario);
	
	
	
	/**
	 * @param params
	 * @return
	 * Obtiene la lista de Procesos Batch Dependientes que actualmente se encuentran en ejecucion 
	 */
	public List getProcesoBatchActuDependientesByCriteria(Map criteria);
	
	/**
	 * Devueleve la lista de parametros correspondientes al proceso batch
	 * @param criteria
	 * @return
	 */
	public Map getParametrosProcesoBatch(Map criteria);
	
	
	/* INI FRAMEWORK NSSICC */
	/**
	 * Inserta Nro de Lote en la tabla BAS_PROCE_BATCH_ACTUA
	 * @param criteria
	 * @param usuario
	 */
	public void updateProcesoBatchActuLote(Map criteria, Usuario usuario);
	
	/**
	 * Inserta Recomendacion a realizar en la tabla BAS_PROCE_BATCH_ACTUA
	 * @param criteria
	 * @param usuario
	 */
	public void updateProcesoBatchRecomendacion(Map criteria, Usuario usuario);
	
	/**
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> executeProcesoValidacionesPrevias(Map<String, Object> params) throws Exception;
	
	/**
	 * @param params
	 * @return
	 */
	public String verificarProcesoBatchEnEjecucion(Map<String, Object> params) ;
	/**
	 * @param params
	 * @return
	 */
	public String verificarProcesoBatchEnEjecucionDependientes(Map<String, Object> params);
	
    /**
     * @param interfazParams
     * @param service
     * @throws Exception
     */
    public void beforeNewTransaction(InterfazParams interfazParams, BaseInterfazAbstractService service) throws Exception;
	
	/**
	 * @param interfazParams
	 * @param service
	 * @throws Exception
	 */
	public void afterNewTransaction(InterfazParams interfazParams, BaseInterfazAbstractService service) throws Exception;
	
	/**
	 * @param interfazParams
	 * @param service
	 * @throws Exception
	 */
	public void afterNewProcessInterfaz(InterfazParams interfazParams, BaseInterfazAbstractService service) throws Exception;
	
	/**
	 * @param interfazParams
	 * @param service
	 * @throws Exception
	 */
	public void afterNewExecuteInterfaz(Map params, BaseInterfazAbstractService service) throws Exception;
	
	
	
	/**
	 * @param interfazParams
	 * @param service
	 * @return
	 * @throws Exception
	 */
	public int processInterfazNEW(InterfazParams interfazParams, BaseInterfazAbstractService service) throws Exception;
	
	/**
	 * @param params
	 * @param interfazExecutionResult
	 * @param usuario
	 * @param service
	 */
	public void updateInterfazRegistroProcesoBatch(Map params,			
			SSiCC_Desfasado_InterfazExecutionResult interfazExecutionResult,
			Usuario usuario, SSiCC_Desfasado_BaseInterfazHiloAbstractServiceImpl service);
	
	/**
	 * @param params
	 * @param usuario
	 * @param service
	 */
	public void updateEstadoEjecucionInterfazProcesoBatch(Map params,
			Usuario usuario, SSiCC_Desfasado_BaseInterfazHiloAbstractServiceImpl service) ;
	
	/**
	 * @param params
	 * @param usuario
	 * @param exception
	 */
	public void updateInterfazRegistroProcesoBatch(Map params, Usuario usuario,
			Exception exception, SSiCC_Desfasado_BaseInterfazHiloAbstractServiceImpl service);
	
	
	/**
	 * @param documentoSTOParams
	 * @param service
	 */
	public void afterProcessValidacionDocumentoSTO(DocumentoSTOParams documentoSTOParams, BaseProcesoSTOAbstractService service);
	
	
	/* FIN FRAMEWORK NSSICC */
}


