package biz.belcorp.ssicc.dao.edu;

import java.util.Map;

import biz.belcorp.ssicc.dao.edu.model.ParametroProceso;
import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoEDUGenerarPlanillaProgramacionDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:rvela@belcorp.biz">Robinson Vela Bardales</a>
 */

public interface ProcesoEDUGenerarPlanillaProgramacionDAO extends DAO {

	/**
	 * Realiza el proceso de Actualizar Planilla Programacin 
	 * @param criteria
	 */
	public void executeGenerarPlanillaProgramacion(Map criteria);
	
	/**
	 * Inserta los parametros en la Tabla Temporal 
	 * @param criteria
	 */
	public void insertParametroProceso(ParametroProceso parametroProceso);
	
	/**
	 * Devuelve cantidad de planillas en una region
	 * @param criteria
	 * @return
	 */
	public Integer getCantidadPlanillasRegion(Map criteria);
	
	/**
	 * Ejecuta proceso de pedidios rezagados
	 * @param params
	 * @throws Exception
	 */
	public void executeProcesoConsultoraRezagadas(Map params)throws Exception;
	
	/**
	 * ejecuta actualizacion de estados de consultora
	 * @param params
	 */
	public void executeActualizacionStatusConsultora(Map params);
	
}
