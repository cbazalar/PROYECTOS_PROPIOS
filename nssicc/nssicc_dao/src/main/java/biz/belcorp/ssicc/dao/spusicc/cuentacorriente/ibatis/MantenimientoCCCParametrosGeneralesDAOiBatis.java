package biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.MantenimientoCCCParametrosGeneralesDAO;

/**
  * <p>
 * <a href="MantenimientoCCCParametrosGeneralesDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:aoviedo@sigcomt.com"></a>
 */
@Repository("spusicc.mantenimientoCCCParametrosGeneralesDAO")
public class MantenimientoCCCParametrosGeneralesDAOiBatis extends BaseDAOiBatis implements MantenimientoCCCParametrosGeneralesDAO {
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.cuentacorriente.MantenimientoCCCParametrosGeneralesDAO#getParametroGeneralByCriteria(java.util.Map)
	 */
	@Override
	public List getParametroGeneralByCriteria(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.cuentacorriente.MantenimientoCCCSQL.getParametroGeneralByCriteria", criteria);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.cuentacorriente.MantenimientoCCCParametrosGeneralesDAO#insertParametroGeneral(java.util.Map)
	 */
	@Override
	public void insertParametroGeneral(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.cuentacorriente.MantenimientoCCCSQL.insertParametroGeneral", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.cuentacorriente.MantenimientoCCCParametrosGeneralesDAO#updateParametroGeneral(java.util.Map)
	 */
	@Override
	public void updateParametroGeneral(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.cuentacorriente.MantenimientoCCCSQL.updateParametroGeneral", criteria);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.cuentacorriente.MantenimientoCCCParametrosGeneralesDAO#deleteParametroGeneral(java.util.Map)
	 */
	@Override
	public void deleteParametroGeneral(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.cuentacorriente.MantenimientoCCCSQL.deleteParametroGeneral", criteria);
	}
}