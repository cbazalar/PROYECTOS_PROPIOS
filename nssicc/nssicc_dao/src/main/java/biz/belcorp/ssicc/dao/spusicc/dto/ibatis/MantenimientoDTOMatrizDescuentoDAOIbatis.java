package biz.belcorp.ssicc.dao.spusicc.dto.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.dto.MantenimientoDTOMatrizDescuentoDAO;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
@Repository("spusicc.mantenimientoDTOMatrizDescuentoDAO")
public class MantenimientoDTOMatrizDescuentoDAOIbatis extends BaseDAOiBatis implements
	MantenimientoDTOMatrizDescuentoDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dto.dao.MantenimientoDTOMatrizDescuentoDAO#getListMatrizDescuento(java.util.Map)
	 */
	public List getListMatrizDescuento(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.dto.MantenimientoDTOSQL.getListMatrizDescuento", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dto.dao.MantenimientoDTOMatrizDescuentoDAO#getGruposDescuento(java.util.Map)
	 */
	public List getGruposDescuento(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.dto.MantenimientoDTOSQL.getGruposDescuento", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dto.dao.MantenimientoDTOMatrizDescuentoDAO#getCategoriasDescuento(java.util.Map)
	 */
	public List getCategoriasDescuento(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.dto.MantenimientoDTOSQL.getCategoriasDescuento", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dto.dao.MantenimientoDTOMatrizDescuentoDAO#getTiposOferta(java.util.Map)
	 */
	public List getTiposOferta(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.dto.MantenimientoDTOSQL.getTiposOferta", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dto.dao.MantenimientoDTOMatrizDescuentoDAO#getNegocios(java.util.Map)
	 */
	public List getNegocios(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.dto.MantenimientoDTOSQL.getNegocios", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dto.dao.MantenimientoDTOMatrizDescuentoDAO#getUnidadesNegocio(java.util.Map)
	 */
	public List getUnidadesNegocio(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.dto.MantenimientoDTOSQL.getUnidadesNegocio", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dto.dao.MantenimientoDTOMatrizDescuentoDAO#existeMatrizDescuento(java.util.Map)
	 */
	public boolean existeMatrizDescuento(Map criteria) {
    	Integer result = (Integer) getSqlMapClientTemplate().queryForObject(
        							"spusicc.dto.MantenimientoDTOSQL.getExisteMatrizDescuento", criteria);
        
        if(result.intValue()>0)
        	return true;
        else
        	return false;
    }	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dto.dao.MantenimientoDTOMatrizDescuentoDAO#insertMatrizDescuento(java.util.Map)
	 */
	public void insertMatrizDescuento(Map criteria) {
		getSqlMapClientTemplate().insert(
				"spusicc.dto.MantenimientoDTOSQL.insertMatrizDescuento", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dto.dao.MantenimientoDTOMatrizDescuentoDAO#updateMatrizDescuento(java.util.Map)
	 */
	public void updateMatrizDescuento(Map criteria) {
		getSqlMapClientTemplate().update(
				"spusicc.dto.MantenimientoDTOSQL.updateMatrizDescuento", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dto.dao.MantenimientoDTOMatrizDescuentoDAO#deleteMatrizDescuento(java.util.Map)
	 */
	public void deleteMatrizDescuento(Map criteria) {
		getSqlMapClientTemplate().update(
				"spusicc.dto.MantenimientoDTOSQL.deleteMatrizDescuento", criteria);
	}

}
