package biz.belcorp.ssicc.service.spusicc.inc;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
public interface MantenimientoINCPagoConcursoService  extends Service {

	/**
	 * retorna la lista de Pago Concurso
	 * 
	 * @return
	 */
	public List getListPagoConcurso(Map criteria);

	/**
	 * recupera el ultima secuencia + 1 para Pago Concurso
	 * 
	 * @param params
	 */
	public String getMaxPagoConcurso();
	
	/**
	 * Inserta registro a la entidad Pago Concurso
	 * 
	 * @param params
	 */
	public void insertPagoConcurso(Map params);
	
	/**
	 * Actualiza registro a la entidad Pago Concurso
	 * 
	 * @param params
	 */
	public void updatePagoConcurso(Map params);
	
	/**
	 * Elimina registro a la entidad Pago Concurso
	 * 
	 * @param calificacionComisionDetalle
	 */
	public void deletePagoConcurso(Map params);

	/**
	 * Cierra registro a la entidad Pago Concurso
	 * 
	 * @param params
	 */
	public void cerrarPagoConcurso(Map params);

	/**
	 * retorna la lista Clasificaciones para Pago Concurso
	 * 
	 * @return
	 */
	public List getListClasificacionesPagoConcurso(Map criteria);
	
	/**
	 * retorna los periodos de despacho de Concurso
	 *  
	 * @return
	 */
	public Map getPeriodosDespachoConcurso(Map criteria);
	
	/**
	 * Obtiene el Oid Periodo relacionado a un Codigo Periodo
	 * 
	 * @param oidPeriodo
	 * @return
	 */
	public String getOidPeriodoByCodigoPeriodo(String codigoPeriodo);
	
	/**
	 * Retorna la lista de Pago Bono Premio
	 * 
	 * @return
	 */
	public List getListPagoBonoPremio(Map criteria);
	
	/**
	 * Retorna la lista de Concurso de Pago
	 * 
	 * @param criteria
	 * @return
	 */
	public List getListConcursosPago(Map criteria);
	
}
