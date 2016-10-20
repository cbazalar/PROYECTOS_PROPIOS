package biz.belcorp.ssicc.dao.spusicc.dto.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.dto.MantenimientoDTODescuentoAdicionalDetalleDAO;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
@Repository("spusicc.mantenimientoDTODescuentoAdicionalDetalleDAO")
public class MantenimientoDTODescuentoAdicionalDetalleDAOIbatis extends BaseDAOiBatis implements
	MantenimientoDTODescuentoAdicionalDetalleDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dto.dao.MantenimientoDTODescuentoAdicionalDetalleDAO#getListDescuentoAdicionalDetalle(java.util.Map)
	 */
	public List getListDescuentoAdicionalDetalle(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.dto.MantenimientoDTOSQL.getListDescuentoAdicionalDetalle", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dto.dao.MantenimientoDTODescuentoAdicionalDetalleDAO#getDescuentosAdicionales(java.util.Map)
	 */
	public List getDescuentosAdicionales(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.dto.MantenimientoDTOSQL.getDescuentosAdicionales", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dto.dao.MantenimientoDTODescuentoAdicionalDetalleDAO#getMaxDescuentoAdicionalDetalle()
	 */
	public String getMaxDescuentoAdicionalDetalle() {
		return (String) getSqlMapClientTemplate().queryForObject(
				"spusicc.dto.MantenimientoDTOSQL.getMaxDescuentoAdicionalDetalle");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dto.dao.MantenimientoDTODescuentoAdicionalDetalleDAO#insertDescuentoAdicionalDetalle(java.util.Map)
	 */
	public void insertDescuentoAdicionalDetalle(Map criteria) {
		getSqlMapClientTemplate().insert(
				"spusicc.dto.MantenimientoDTOSQL.insertDescuentoAdicionalDetalle", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dto.dao.MantenimientoDTODescuentoAdicionalDetalleDAO#updateDescuentoAdicionalDetalle(java.util.Map)
	 */
	public void updateDescuentoAdicionalDetalle(Map criteria) {
		getSqlMapClientTemplate().update(
				"spusicc.dto.MantenimientoDTOSQL.updateDescuentoAdicionalDetalle", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dto.dao.MantenimientoDTODescuentoAdicionalDetalleDAO#deleteDescuentoAdicionalDetalle(java.util.Map)
	 */
	public void deleteDescuentoAdicionalDetalle(Map criteria) {
		getSqlMapClientTemplate().update(
				"spusicc.dto.MantenimientoDTOSQL.deleteDescuentoAdicionalDetalle", criteria);
	}

}
