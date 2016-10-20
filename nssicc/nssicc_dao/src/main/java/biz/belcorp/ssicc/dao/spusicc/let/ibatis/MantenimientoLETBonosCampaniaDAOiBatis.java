package biz.belcorp.ssicc.dao.spusicc.let.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.let.MantenimientoLETBonosCampaniaDAO;

/**
 * @author Jesse James Rios Franco
 *
 */
@Repository("spusicc.mantenimientoLETBonosCampaniaDAO")
public class MantenimientoLETBonosCampaniaDAOiBatis extends BaseDAOiBatis implements MantenimientoLETBonosCampaniaDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETBonosCampaniaDAO#getBonoCampaniaList(java.util.Map)
	 */
	public List getBonoCampaniaList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.let.MantenimientoLETSQL.getBonoCampaniaList", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETBonosCampaniaDAO#getValidaBonoCampaniaExiste(java.util.Map)
	 */
	public int getValidaBonoCampaniaExiste(Map criteria) {
		return ((Integer)getSqlMapClientTemplate().queryForObject("spusicc.let.MantenimientoLETSQL.getValidaBonoCampaniaExiste", criteria)).intValue();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETBonosCampaniaDAO#deleteBonoCampania(java.util.Map)
	 */
	public void deleteBonoCampania(Map criteria) {
		getSqlMapClientTemplate().delete("spusicc.let.MantenimientoLETSQL.deleteBonoCampania", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETBonosCampaniaDAO#insertBonoCampania(java.util.Map)
	 */
	public void insertBonoCampania(Map criteria) {
		getSqlMapClientTemplate().delete("spusicc.let.MantenimientoLETSQL.insertBonoCampania", criteria);
	}
}