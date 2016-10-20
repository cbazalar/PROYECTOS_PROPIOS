package biz.belcorp.ssicc.dao.spusicc.sto.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.sto.MantenimientoSTOBloqueoControlDAO;


/**
 * @author peextjrios
 */
@Repository("spusicc.mantenimientoSTOBloqueoControlDAO")
public class MantenimientoSTOBloqueoControlDAOiBatis extends BaseDAOiBatis implements MantenimientoSTOBloqueoControlDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.MantenimientoSTOBloqueoControlServiceDAO#getBloqueoControlList(java.util.Map)
	 */
	public List getBloqueoControlList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getBloqueoControlList", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.MantenimientoSTOBloqueoControlServiceDAO#insertSTOBloqueoControl(java.util.Map)
	 */
	public void insertSTOBloqueoControl(Map criteria) {
		getSqlMapClientTemplate().insert("spusicc.ProcesosSTOSQL.insertSTOBloqueoControl", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.MantenimientoSTOBloqueoControlServiceDAO#deleteBloqueoControl(java.util.Map)
	 */
	public void deleteBloqueoControl(Map criteria) {
		getSqlMapClientTemplate().delete("spusicc.ProcesosSTOSQL.deleteBloqueoControl", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.MantenimientoSTOBloqueoControlDAO#updateEstadoInactivoBloqueoControl(java.util.Map)
	 */
	public void updateEstadoInactivoBloqueoControl(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.updateEstadoInactivoBloqueoControl", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.MantenimientoSTOBloqueoControlDAO#getParametroGenericoSistema(java.util.Map)
	 */
	public String getParametroGenericoSistema(Map criteria) {
		return (String)getSqlMapClientTemplate().
		                      queryForObject("spusicc.ProcesosSTOSQL.getParametroGenericoSistema", 
		                    		   criteria);
	}

}