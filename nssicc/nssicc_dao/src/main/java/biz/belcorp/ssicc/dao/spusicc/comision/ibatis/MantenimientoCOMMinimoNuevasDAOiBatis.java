/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.comision.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.MinimoNuevas;
import biz.belcorp.ssicc.dao.spusicc.comision.MantenimientoCOMMinimoNuevasDAO;

/**
 * @author jvelasquez
 *
 */
@Repository("spusicc.mantenimientoCOMMinimoNuevasDAO")
public class MantenimientoCOMMinimoNuevasDAOiBatis extends BaseDAOiBatis
		implements MantenimientoCOMMinimoNuevasDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMMinimoNuevasDAO#getMinimoNuevasList(java.util.Map)
	 */
	public List getMinimoNuevasList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.comision.mantenimientoCOMSQL.getMinimoNuevasList", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMMinimoNuevasDAO#getMinimoNuevas(java.util.Map)
	 */
	public MinimoNuevas getMinimoNuevas(Map criteria) {
		return (MinimoNuevas)getSqlMapClientTemplate().queryForObject("spusicc.comision.mantenimientoCOMSQL.getMinimoNuevas",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMMinimoNuevasDAO#insertMinimoNuevas(java.util.Map)
	 */
	public void insertMinimoNuevas(Map criteria) {
		getSqlMapClientTemplate().insert("spusicc.comision.mantenimientoCOMSQL.insertMinimoNuevas", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMMinimoNuevasDAO#updateMinimoNuevas(java.util.Map)
	 */
	public void updateMinimoNuevas(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.comision.mantenimientoCOMSQL.updateMinimoNuevas",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMMinimoNuevasDAO#deleteMinimoNuevas(java.util.Map)
	 */
	public void deleteMinimoNuevas(Map criteria) {
		getSqlMapClientTemplate().delete("spusicc.comision.mantenimientoCOMSQL.deleteMinimoNuevas",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMMinimoNuevasDAO#getValidarMinimoNuevasRegion(java.util.Map)
	 */
	public List getValidarMinimoNuevasRegion(Map criteria) {
		 return getSqlMapClientTemplate().queryForList("spusicc.comision.mantenimientoCOMSQL.getValidarMinimoNuevasRegion",criteria);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMMinimoNuevasDAO#getValidarMinimoNuevasZonas(java.util.Map)
	 */
	public List getValidarMinimoNuevasZonas(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.comision.mantenimientoCOMSQL.getValidarMinimoNuevasZonas",criteria);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMMinimoNuevasDAO#getMinimoNuevasDuplicada(java.util.Map)
	 */
	public List getMinimoNuevasDuplicada(Map criteria) {
		return  getSqlMapClientTemplate().queryForList("spusicc.comision.mantenimientoCOMSQL.getMinimoNuevasDuplicada",criteria);
	}

}
