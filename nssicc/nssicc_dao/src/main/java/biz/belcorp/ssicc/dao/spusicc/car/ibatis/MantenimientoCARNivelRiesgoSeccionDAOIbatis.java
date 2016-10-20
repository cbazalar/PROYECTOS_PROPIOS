package biz.belcorp.ssicc.dao.spusicc.car.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.car.MantenimientoCARNivelRiesgoSeccionDAO;

/**
 * Implementacion del DAO que ejecutara los metodos de mantenimiento de nivel de riesgo x seccion
 * <p>
 * <a href="MantenimientoCARNivelRiesgoSeccionDAOIbatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Repository("spusicc.mantenimientoCARNivelRiesgoSeccionDAO")
public class MantenimientoCARNivelRiesgoSeccionDAOIbatis extends BaseDAOiBatis 
	implements MantenimientoCARNivelRiesgoSeccionDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.car.dao.MantenimientoCARNivelRiesgoSeccionDAO#getNivelRiesgoSeccionList(java.util.Map)
	 */
	public List getNivelRiesgoSeccionList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.car.mantenimientoCARSQL.getNivelRiesgoSeccionList", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.car.dao.MantenimientoCARNivelRiesgoSeccionDAO#getNivelesRiesgos(java.util.Map)
	 */
	public List getNivelesRiesgos(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
						"spusicc.car.mantenimientoCARSQL.getNivelesRiesgos", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.car.dao.MantenimientoCARNivelRiesgoSeccionDAO#existeNivelRiesgoSeccion(java.util.Map)
	 */
	public boolean existeNivelRiesgoSeccion(Map params) {
        String result = (String) getSqlMapClientTemplate().queryForObject(
        		"spusicc.car.mantenimientoCARSQL.getNivelRiesgoSeccion", params);

        if(Integer.parseInt(result)>0)
        	return true;
        else
        	return false;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.car.dao.MantenimientoCARNivelRiesgoSeccionDAO#insertNivelRiesgoSeccion(java.util.Map)
	 */
	public void insertNivelRiesgoSeccion(Map params) {
		getSqlMapClientTemplate().update("spusicc.car.mantenimientoCARSQL.insertNivelRiesgoSeccion", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.car.dao.MantenimientoCARNivelRiesgoSeccionDAO#updateNivelRiesgoSeccion(java.util.Map)
	 */
	public void updateNivelRiesgoSeccion(Map params) {
		getSqlMapClientTemplate().update("spusicc.car.mantenimientoCARSQL.updateNivelRiesgoSeccion", params);
	}
	
}
