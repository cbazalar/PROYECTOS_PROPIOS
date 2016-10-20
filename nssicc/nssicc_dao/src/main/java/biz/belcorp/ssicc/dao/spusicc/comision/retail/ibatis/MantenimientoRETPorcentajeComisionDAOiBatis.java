package biz.belcorp.ssicc.dao.spusicc.comision.retail.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.comision.retail.MantenimientoRETPorcentajeComisionDAO;


/**
 * @author <a href="mailto:sbuchelli@csigcomt.com">Sergio Buchelli Silve</a>
 *
 */
@Repository("spusicc.mantenimientoRETPorcentajeComisionDAO")
public class MantenimientoRETPorcentajeComisionDAOiBatis extends BaseDAOiBatis 
    implements MantenimientoRETPorcentajeComisionDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.retail.dao.MantenimientoRETPorcentajeComisionDAO#getPorcentajeComision(java.util.Map)
	 */
	public List getPorcentajeComision(Map map) {
		return this.getSqlMapClientTemplate().queryForList(
				"spusicc.comision.retail.mantenimientoRETSQL.getPorcentajeComision",map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.retail.dao.MantenimientoRETPorcentajeComisionDAO#updatePorcentajeComision(java.util.Map)
	 */
	public void updatePorcentajeComision(Map map) {
		this.getSqlMapClientTemplate().update(
				"spusicc.comision.retail.mantenimientoRETSQL.updatePorcentajeComision", map);
		
	}
	


}
