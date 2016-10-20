package biz.belcorp.ssicc.dao.spusicc.dto.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.dto.MantenimientoDTORangoDescuentoDAO;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
@Repository("spusicc.mantenimientoDTORangoDescuentoDAO")
public class MantenimientoDTORangoDescuentoDAOIbatis extends BaseDAOiBatis implements
	MantenimientoDTORangoDescuentoDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dto.dao.MantenimientoDTORangoDescuentoDAO#getListRangoDescuento(java.util.Map)
	 */
	public List getListRangoDescuento(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.dto.MantenimientoDTOSQL.getListRangoDescuento", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dto.dao.MantenimientoDTORangoDescuentoDAO#getGruposDescuento(java.util.Map)
	 */
	public List getGruposDescuento(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.dto.MantenimientoDTOSQL.getGruposDescuento", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dto.dao.MantenimientoDTORangoDescuentoDAO#getMaxRangoDescuento(java.lang.String)
	 */
	public String getMaxRangoDescuento(String codigoGrupo) {
		return (String) getSqlMapClientTemplate().queryForObject(
				"spusicc.dto.MantenimientoDTOSQL.getMaxRangoDescuento", codigoGrupo);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dto.dao.MantenimientoDTORangoDescuentoDAO#insertRangoDescuento(java.util.Map)
	 */
	public void insertRangoDescuento(Map criteria) {
		getSqlMapClientTemplate().insert(
				"spusicc.dto.MantenimientoDTOSQL.insertRangoDescuento", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dto.dao.MantenimientoDTORangoDescuentoDAO#updateRangoDescuento(java.util.Map)
	 */
	public void updateRangoDescuento(Map criteria) {
		getSqlMapClientTemplate().update(
				"spusicc.dto.MantenimientoDTOSQL.updateRangoDescuento", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dto.dao.MantenimientoDTORangoDescuentoDAO#deleteRangoDescuento(java.util.Map)
	 */
	public void deleteRangoDescuento(Map criteria) {
		getSqlMapClientTemplate().update(
				"spusicc.dto.MantenimientoDTOSQL.deleteRangoDescuento", criteria);
	}

}
