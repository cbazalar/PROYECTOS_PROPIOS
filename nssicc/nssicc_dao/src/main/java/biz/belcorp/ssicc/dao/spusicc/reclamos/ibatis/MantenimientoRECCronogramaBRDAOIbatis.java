/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.reclamos.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.reclamos.MantenimientoRECCronogramaBRDAO;
import biz.belcorp.ssicc.dao.spusicc.reclamos.model.CronogramaBR;


/**
 * Clase de la implementacin de la capa DAO (Data Access Object)
 * 
 * <p>
 * <a href="MantenimientoRECCronogramaBRDAOIbatis.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="cdiaz@csigcomt.com">Carlos Diaz Valverde</a>
 * 
 */
@Repository("spusicc.mantenimientoRECCronogramaBRDAO")
public class MantenimientoRECCronogramaBRDAOIbatis extends BaseDAOiBatis implements MantenimientoRECCronogramaBRDAO {

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECCronogramaBRDAO#deleteCronogramaBR(java.util.Map)
	 */
	public void deleteCronogramaBR(Map map) {
		log.info("Entro a MantenimientoRECCronogramaBRDAOIbatis - deleteCronogramaBR(java.util.Map)");
		getSqlMapClientTemplate().delete("spusicc.reclamos.ReclamosSQL.deleteCronogramaBR", map);
		log.info("Salio a MantenimientoRECCronogramaBRDAOIbatis - deleteCronogramaBR(java.util.Map)");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECCronogramaBRDAO#getCronogramaBRList(java.util.Map)
	 */
	public List getCronogramaBRList(Map map) {
		log.info("Entro a MantenimientoRECCronogramaBRDAOIbatis - getCronogramaBRList(java.util.Map)");
		List lista = getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getCronogramaBRList",map);
		log.info("Salio a MantenimientoRECCronogramaBRDAOIbatis - getCronogramaBRList(java.util.Map) - Resultado:" + lista.size());
		return lista;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECCronogramaBRDAO#insertCronogramaBR(biz.belcorp.ssicc.spusicc.reclamos.model.CronogramaBR, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertCronogramaBR(CronogramaBR cronograma, Usuario usuario) {
		getSqlMapClientTemplate().insert("spusicc.reclamos.ReclamosSQL.insertCronogramaBR", cronograma);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECCronogramaBRDAO#updateCronogramaBR(biz.belcorp.ssicc.spusicc.reclamos.model.CronogramaBR, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateCronogramaBR(CronogramaBR cronograma, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.reclamos.ReclamosSQL.updateCronogramaBR", cronograma);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECCronogramaBRDAO#getCronogramaBRValidarPeriodoActivo(java.util.Map)
	 */
	public int getCronogramaBRValidarPeriodoActivo(Map map) {
		log.info("Entro a MantenimientoRECCronogramaBRDAOIbatis - getCronogramaBRValidarPeriodoActivo(java.util.Map)");
		int resultado = ((Integer)getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.getCronogramaBRValidarPeriodoActivo",map)).intValue();
		log.info("Salio a MantenimientoRECCronogramaBRDAOIbatis - getCronogramaBRValidarPeriodoActivo(java.util.Map) - Resultado:" + resultado);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECCronogramaBRDAO#getCronogramaBRValidarPeriodoExistente(java.util.Map)
	 */
	public int getCronogramaBRValidarPeriodoExistente(Map map) {
		log.info("Entro a MantenimientoRECCronogramaBRDAOIbatis - getCronogramaBRValidarPeriodoExistente(java.util.Map)");
		int resultado = ((Integer)getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.getCronogramaBRValidarPeriodoExistente",map)).intValue();
		log.info("Salio a MantenimientoRECCronogramaBRDAOIbatis - getCronogramaBRValidarPeriodoExistente(java.util.Map) - Resultado:" + resultado);
		return resultado;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECCronogramaBRDAO#getCronogramaBRPeriodoActivo(java.util.Map)
	 */
	public int getCronogramaBRPeriodoActivo(Map map){
		int resultado =((Integer)getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.getCronogramaBRPeriodoActivo",map)).intValue();
		return resultado;
	}
}
