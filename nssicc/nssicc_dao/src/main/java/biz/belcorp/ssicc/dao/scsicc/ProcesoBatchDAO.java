/*
 * Created on 18-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.dao.scsicc;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.NroLoteMultiHilo;
import biz.belcorp.ssicc.dao.sisicc.model.ProcesoBatch;

/**
 * TODO    
 * 
 * <p>
 * <a href="ProcesoBatchDAO"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar</a>
 */

public interface ProcesoBatchDAO extends DAO {

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
     * Devuelve Proceso Batch Activo en Base al Pais, Sistema, Codigo Proceso Batch y Estado del Proceso
     * @param criteria
     * @return
     */
    public ProcesoBatch getProcesoBatchActuByEstado(Map criteria);
    
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
    public void deleteProcesoBatchActu(Map criteria, Usuario usuario);
	
	/**
	 * Actualiza Proceso Batch
	 * @param criteria
	 * @param usuario
	 */
	public void updateProcesoBatchActu(Map criteria, Usuario usuario);
	
	/**
	 * Actualiza el Nro de Lote del Proceso Batch
	 * @param criteria
	 * @param usuario
	 */
	public void updateProcesoBatchActuLote(Map criteria, Usuario usuario);
	
	/**
	 * Actualiza Etapa de Proceso Batch
	 * @param criteria
	 * @param usuario
	 */
	public void updateProcesoBatchEtapaProceso(Map criteria, Usuario usuario);
	
	
	/**
	 * Actualiza Recomendacion en el Proceso Batch
	 * @param criteria
	 * @param usuario
	 */
	public void updateProcesoBatchRecomendacion(Map criteria, Usuario usuario);
	
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
	 * Devuelve Lista de Procesos Batch Actuales que se encuentran ene ejecucion
	 * @param criteria
	 * @return
	 */
	public List getProcesoBatchActuDependientesByCriteria(Map criteria);

	/**
	 * Devueleve la lista de parametros correspondientes al proceso batch
	 * @param criteria
	 * @return
	 */
	public List getParametrosProcesoBatch(Map criteria);
}
