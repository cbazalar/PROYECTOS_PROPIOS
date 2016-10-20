package biz.belcorp.ssicc.dao.spusicc.lideres.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.lideres.MantenimientoLIDObjetivosPorVariableAsignacionPuntajeDAO;

/**
 * 
 * <p>
 * <a href="MantenimientoLIDObjetivosPorVariableAsignacionPuntajeDAOIbatis.java.html"> <i>View
 * Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:etovar@csigcomt.com">Eder Tovar
 *         </a>
 */
@Repository("spusicc.mantenimientoLIDObjetivosPorVariableAsignacionPuntajeDAO")
public class MantenimientoLIDObjetivosPorVariableAsignacionPuntajeDAOIbatis extends BaseDAOiBatis 
	implements MantenimientoLIDObjetivosPorVariableAsignacionPuntajeDAO {

	/**
	 * Metodo que obtiene la lista de tipos de asignacion.
	 * @return Lista de tipo de asignacion
	 */
	public List getTipoAsignacionList() {
		return getSqlMapClientTemplate().queryForList("spusicc.lideres.MantenimientoLIDSQL.getTipoAsignacionList", null);
	}
	/**
	 * Metodo que obtiente la lista de los objetivos de asignacin puntaje que coinciden
	 * con los parametros de bsqueda
	 * @param criteria
	 * @return Lista de ObjetivosAsignacionPuntaje
	 */
	public List getObjetivoAsignacionPuntajeList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.lideres.MantenimientoLIDSQL.getObjetivoAsignacionPuntajeList", criteria);
	}
	/**
	 * Registra un nuevo Objetivo de asginacion Puntaje
	 * @param criteria
	 */	
	public void insertObjetivoAsignacionPuntaje(Map criteria){
		getSqlMapClientTemplate().insert("spusicc.lideres.MantenimientoLIDSQL.saveObjetivoAsignacionPuntaje", criteria);
	}
	/**
	 * Actualiza un Objetivo de asginacion Puntaje
	 * @param criteria
	 */
	public void udpateObjetivoAsignacionPuntaje(Map criteria){
		getSqlMapClientTemplate().update("spusicc.lideres.MantenimientoLIDSQL.updateObjetivoAsignacionPuntaje", criteria);
	}
	/**
	 * Elimina un Objetivo de asginacion Puntaje
	 * @param criteria
	 */
	public boolean deleteObjetivoAsignacionPuntaje(Map criteria){
		try{
			getSqlMapClientTemplate().delete("spusicc.lideres.MantenimientoLIDSQL.deleteObjetivoAsignacionPuntaje", criteria);
			return true;
		}catch (Exception e){
			return false;
		}
	}
	/**
	 * Mtodo que obtiene la cantidad de cruces que existe entre el periodo ingresado por parametro 
	 * y los rangos de periodos ya registrados en el sistema.
	 * @param criteria
	 */
	public Long getCantCrucesRangoPeriodo(Map criteria){	
		return (Long) getSqlMapClientTemplate().queryForObject("spusicc.lideres.MantenimientoLIDSQL.getCantCrucesRangoPeriodo", criteria);
	}
	
}
