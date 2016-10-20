package biz.belcorp.ssicc.dao.spusicc.dto.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.dto.MantenimientoDTOGrupoDescuentoDAO;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
@Repository("spusicc.mantenimientoDTOGrupoDescuentoDAO")
public class MantenimientoDTOGrupoDescuentoDAOIbatis extends BaseDAOiBatis implements
	MantenimientoDTOGrupoDescuentoDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dto.dao.MantenimientoDTOGrupoDescuentoDAO#getListGrupoDescuento(java.util.Map)
	 */
	public List getListGrupoDescuento(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.dto.MantenimientoDTOSQL.getListGrupoDescuento", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dto.dao.MantenimientoDTOGrupoDescuentoDAO#getMaxGrupoDescuento()
	 */
	public String getMaxGrupoDescuento() {
		return (String) getSqlMapClientTemplate().queryForObject(
				"spusicc.dto.MantenimientoDTOSQL.getMaxGrupoDescuento");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dto.dao.MantenimientoDTOGrupoDescuentoDAO#insertGrupoDescuento(java.util.Map)
	 */
	public void insertGrupoDescuento(Map criteria) {
		getSqlMapClientTemplate().insert(
				"spusicc.dto.MantenimientoDTOSQL.insertGrupoDescuento", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dto.dao.MantenimientoDTOGrupoDescuentoDAO#updateGrupoDescuento(java.util.Map)
	 */
	public void updateGrupoDescuento(Map criteria) {
		getSqlMapClientTemplate().update(
				"spusicc.dto.MantenimientoDTOSQL.updateGrupoDescuento", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dto.dao.MantenimientoDTOGrupoDescuentoDAO#deleteGrupoDescuento(java.util.Map)
	 */
	public void deleteGrupoDescuento(Map criteria) {
		getSqlMapClientTemplate().update(
				"spusicc.dto.MantenimientoDTOSQL.deleteGrupoDescuento", criteria);
	}

}
