package biz.belcorp.ssicc.dao.spusicc.lideres.ibatis;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.lideres.MantenimientoLIDParametroDAO;

/**
 * Implementacion del DAO que ejecutara los metodos de mantenimiento de parametro de Lideres
 * <p>
 * <a href="MantenimientoLIDParametroDAOIbatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Repository("spusicc.mantenimientoLIDParametroDAO")
public class MantenimientoLIDParametroDAOIbatis extends BaseDAOiBatis implements
	MantenimientoLIDParametroDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.dao.MantenimientoLIDParametroDAO#getParametro(java.lang.String)
	 */
	public Map getParametro(String codigoPais) {
		return (HashMap) getSqlMapClientTemplate().queryForObject(
				"spusicc.lideres.MantenimientoLIDSQL.getParametro", codigoPais);
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.dao.MantenimientoLIDParametroDAO#insertParametro(java.util.Map)
	 */
	public void insertParametro(Map params){
		getSqlMapClientTemplate().insert(
				"spusicc.lideres.MantenimientoLIDSQL.insertParametro", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.dao.MantenimientoLIDParametroDAO#updateParametro(java.util.Map)
	 */
	public void updateParametro(Map params) {
		getSqlMapClientTemplate().update(
				"spusicc.lideres.MantenimientoLIDSQL.updateParametro", params);
	}

}
