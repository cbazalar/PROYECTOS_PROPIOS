package biz.belcorp.ssicc.dao.spusicc.sto.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.sto.MantenimientoSTOLimiteVentaFocalizadoConsejeraDAO;

/**
 * @author Jesse James Rios Franco
 *
 */
@Repository("spusicc.mantenimientoSTOLimiteVentaFocalizadoConsejeraDAO")
public class MantenimientoSTOLimiteVentaFocalizadoConsejeraDAOiBatis extends BaseDAOiBatis implements MantenimientoSTOLimiteVentaFocalizadoConsejeraDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.MantenimientoSTOLimiteVentaFocalizadoConsejeraDAO#deleteLimiteVentaFocalizadoConsejera(java.util.Map)
	 */
	public void deleteLimiteVentaFocalizadoConsejera(Map parametros) {
		getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.deleteLimiteVentaFocalizadoConsejera", parametros);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.MantenimientoSTOLimiteVentaFocalizadoConsejeraDAO#getLimiteVentaFocalizadoConsejeraList(java.util.Map)
	 */
	public List getLimiteVentaFocalizadoConsejeraList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getLimiteVentaFocalizadoConsejeraList", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.MantenimientoSTOLimiteVentaFocalizadoConsejeraDAO#insertSTOLimiteVentaFocalizadoConsejera(java.util.Map)
	 */
	public void insertSTOLimiteVentaFocalizadoConsejera(Map criteria) {
		getSqlMapClientTemplate().insert("spusicc.ProcesosSTOSQL.insertSTOLimiteVentaFocalizadoConsejera", criteria);
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.MantenimientoSTOLimiteVentaFocalizadoConsejeraDAO#getExisteSTOLimiteVentaFocalizadoConsejera(java.util.Map)
	 */
	public HashMap getExisteSTOLimiteVentaFocalizadoConsejera(Map criteria) {
		return (HashMap)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosSTOSQL.getExisteSTOLimiteVentaFocalizadoConsejera", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.MantenimientoSTOLimiteVentaFocalizadoConsejeraDAO#updateSTOLimiteVentaFocalizadoConsejeraEstadoUnidadLimite(java.util.Map)
	 */
	public void updateSTOLimiteVentaFocalizadoConsejeraEstadoUnidadLimite(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.updateSTOLimiteVentaFocalizadoConsejeraEstadoUnidadLimite",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.MantenimientoSTOLimiteVentaFocalizadoConsejeraDAO#getObjectoSTOLimiteVentaFocalizadoConsejera(java.util.Map)
	 */
	public Map getObjectoSTOLimiteVentaFocalizadoConsejera(Map criteria) {
		
		Map result = (HashMap)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosSTOSQL.getObjectoSTOLimiteVentaFocalizadoConsejera", criteria);
		
		return result;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.MantenimientoSTOLimiteVentaFocalizadoConsejeraDAO#updateObjetoSTOLimiteVentaFocalizadoConsejera(java.util.Map)
	 */
	public void updateObjetoSTOLimiteVentaFocalizadoConsejera(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.updateObjetoSTOLimiteVentaFocalizadoConsejera",criteria);
	}
}