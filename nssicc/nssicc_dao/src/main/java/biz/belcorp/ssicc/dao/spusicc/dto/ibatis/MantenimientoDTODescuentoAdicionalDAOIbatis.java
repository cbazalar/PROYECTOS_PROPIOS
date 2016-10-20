package biz.belcorp.ssicc.dao.spusicc.dto.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.dto.MantenimientoDTODescuentoAdicionalDAO;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
@Repository("spusicc.mantenimientoDTODescuentoAdicionalDAO")
public class MantenimientoDTODescuentoAdicionalDAOIbatis extends BaseDAOiBatis implements
	MantenimientoDTODescuentoAdicionalDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dto.dao.MantenimientoDTODescuentoAdicionalDAO#getListDescuentoAdicional(java.util.Map)
	 */
	public List getListDescuentoAdicional(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.dto.MantenimientoDTOSQL.getListDescuentoAdicional", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dto.dao.MantenimientoDTODescuentoAdicionalDAO#getMaxDescuentoAdicional()
	 */
	public String getMaxDescuentoAdicional() {
		return (String) getSqlMapClientTemplate().queryForObject(
				"spusicc.dto.MantenimientoDTOSQL.getMaxDescuentoAdicional");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dto.dao.MantenimientoDTODescuentoAdicionalDAO#insertDescuentoAdicional(java.util.Map)
	 */
	public void insertDescuentoAdicional(Map criteria) {
		getSqlMapClientTemplate().insert(
				"spusicc.dto.MantenimientoDTOSQL.insertDescuentoAdicional", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dto.dao.MantenimientoDTODescuentoAdicionalDAO#updateDescuentoAdicional(java.util.Map)
	 */
	public void updateDescuentoAdicional(Map criteria) {
		getSqlMapClientTemplate().update(
				"spusicc.dto.MantenimientoDTOSQL.updateDescuentoAdicional", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dto.dao.MantenimientoDTODescuentoAdicionalDAO#deleteDescuentoAdicional(java.util.Map)
	 */
	public void deleteDescuentoAdicional(Map criteria) {
		getSqlMapClientTemplate().update(
				"spusicc.dto.MantenimientoDTOSQL.deleteDescuentoAdicional", criteria);
	}

}
