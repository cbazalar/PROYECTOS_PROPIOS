package biz.belcorp.ssicc.dao.spusicc.cronograma.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.cronograma.MantenimientoCRAActividadDAO;


/**  
 * <p>
 * <a href="MantenimientoCRAActividadDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:rramirez@belcorp.biz"> Rosalvina Ram+irez Guardia </a>
 */
@Repository("spusicc.mantenimientoCRAActividadDAO")
public class MantenimientoCRAActividadDAOiBatis extends BaseDAOiBatis implements MantenimientoCRAActividadDAO {


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.dao.MantenimientoCRAActividadDAO#getActividades(java.util.Map)
	 */
	public List getActividades(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.cronograma.mantenimientoCRASQL.getActividades",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.dao.MantenimientoCRAActividadDAO#deleteActividad(java.util.Map)
	 */
	public void deleteActividad(Map criteria){
		getSqlMapClientTemplate().update("spusicc.cronograma.mantenimientoCRASQL.deleteActividad", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.dao.MantenimientoCRAActividadDAO#getClaseActividades(java.util.Map)
	 */
	public List getClaseActividades(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.cronograma.mantenimientoCRASQL.getClaseActividades",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.dao.MantenimientoCRAActividadDAO#insertActividad(java.util.Map)
	 */
	public void insertActividad(Map criteria) {
		getSqlMapClientTemplate().insert("spusicc.cronograma.mantenimientoCRASQL.insertActividad", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.dao.MantenimientoCRAActividadDAO#updateActividad(java.util.Map)
	 */
	public void updateActividad(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.cronograma.mantenimientoCRASQL.updateActividad", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.dao.MantenimientoCRAActividadDAO#getCodigoActividadExistente(java.util.Map)
	 */
	public int getCodigoActividadExistente(Map criteria) {
	 return ((Integer)getSqlMapClientTemplate().queryForObject("spusicc.cronograma.mantenimientoCRASQL.getCodigoActividadExistente",criteria)).intValue();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.dao.MantenimientoCRAActividadDAO#getActividadesExcepto(java.util.Map)
	 */
	public List getActividadesExcepto(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.cronograma.mantenimientoCRASQL.getActividadesExcepto",criteria);
	}	
}
