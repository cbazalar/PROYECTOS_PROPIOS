package biz.belcorp.ssicc.dao.spusicc.dto.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.dto.MantenimientoDTODescuentoAdicionalGrupoDAO;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
@Repository("spusicc.mantenimientoDTODescuentoAdicionalGrupoDAO")
public class MantenimientoDTODescuentoAdicionalGrupoDAOIbatis extends BaseDAOiBatis implements
	MantenimientoDTODescuentoAdicionalGrupoDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dto.dao.MantenimientoDTODescuentoAdicionalGrupoDAO#getListDescuentoAdicionalGrupo(java.util.Map)
	 */
	public List getListDescuentoAdicionalGrupo(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.dto.MantenimientoDTOSQL.getListDescuentoAdicionalGrupo", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dto.dao.MantenimientoDTODescuentoAdicionalGrupoDAO#insertDescuentoAdicionalGrupo(java.util.Map)
	 */
	public void insertDescuentoAdicionalGrupo(Map criteria) {
		getSqlMapClientTemplate().insert(
				"spusicc.dto.MantenimientoDTOSQL.insertDescuentoAdicionalGrupo", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dto.dao.MantenimientoDTODescuentoAdicionalGrupoDAO#updateDescuentoAdicionalGrupo(java.util.Map)
	 */
	public void updateDescuentoAdicionalGrupo(Map criteria) {
		getSqlMapClientTemplate().update(
				"spusicc.dto.MantenimientoDTOSQL.updateDescuentoAdicionalGrupo", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dto.dao.MantenimientoDTODescuentoAdicionalGrupoDAO#deleteDescuentoAdicionalGrupo(java.util.Map)
	 */
	public void deleteDescuentoAdicionalGrupo(Map criteria) {
		getSqlMapClientTemplate().update(
				"spusicc.dto.MantenimientoDTOSQL.deleteDescuentoAdicionalGrupo", criteria);
	}

}
