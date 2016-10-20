package biz.belcorp.ssicc.dao.spusicc.zon.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.zon.MantenimientoZONParametrosRutasDAO;

/**
 * Implementacion del DAO que ejecutara los metodos de proceso de parametros de rutas
 * <p>
 * <a href="MantenimientoZONParametrosRutasDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:doliva@belcorp.biz">Dennys Oliva Iriarte</a>
 */
@Repository("spusicc.mantenimientoZONParametrosRutasDAO")
public class MantenimientoZONParametrosRutasDAOiBatis extends BaseDAOiBatis implements
			MantenimientoZONParametrosRutasDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONParametrosRutasDAO#getParametrosRutasList(java.util.Map)
	 */
	public List getParametrosRutasList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.zon.MantenimientoZONSQL.getParametrosRutasList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONParametrosRutasDAO#deleteParametroRuta(java.util.Map)
	 */
	public void deleteParametroRuta(Map criteria){
		getSqlMapClientTemplate().delete("spusicc.zon.MantenimientoZONSQL.deleteParametroRuta", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONParametrosRutasDAO#updateParametroRutaDirectorio(java.util.Map)
	 */
	public void updateParametroRutaDirectorio(Map params){
		getSqlMapClientTemplate().update("spusicc.zon.MantenimientoZONSQL.updateParametroRutaDirectorio", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONParametrosRutasDAO#insertParametroRutaDirectorio(java.util.Map)
	 */
	public void insertParametroRutaDirectorio(Map params){
		getSqlMapClientTemplate().insert("spusicc.zon.MantenimientoZONSQL.insertParametroRutaDirectorio", params);
	}
}