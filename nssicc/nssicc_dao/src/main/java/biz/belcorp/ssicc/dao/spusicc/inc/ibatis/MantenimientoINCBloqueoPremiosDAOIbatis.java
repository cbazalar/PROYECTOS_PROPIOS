package biz.belcorp.ssicc.dao.spusicc.inc.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.inc.MantenimientoINCBloqueoPremiosDAO;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
@Repository("spusicc.mantenimientoINCBloqueoPremiosDAO")
public class MantenimientoINCBloqueoPremiosDAOIbatis extends BaseDAOiBatis implements
	MantenimientoINCBloqueoPremiosDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCBloqueoPremiosConcursoDAO#getConcursosBloqueoPremios()
	 */
	public List getConcursosBloqueoPremios(Map criteria) {
		return getSqlMapClientTemplate().
				queryForList("spusicc.incentivos.MantenimientoINCSQL.getConcursosBloqueoPremios", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCBloqueoPremiosConcursoDAO#getPremiosxConcurso(java.lang.String)
	 */
	public List getPremiosxConcurso(Map criteria) {
		return getSqlMapClientTemplate().
				queryForList("spusicc.incentivos.MantenimientoINCSQL.getPremiosxConcurso", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCBloqueoPremiosConcursoDAO#getRegionesBloqueoPremios()
	 */
	public List getRegionesBloqueoPremios() {
		return getSqlMapClientTemplate().
				queryForList("spusicc.incentivos.MantenimientoINCSQL.getRegionesBloqueoPremios");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCBloqueoPremiosConcursoDAO#getBloqueoPremios(java.util.Map)
	 */
	public List getBloqueoPremios(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.incentivos.MantenimientoINCSQL.getBloqueoPremios", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCBloqueoPremiosConcursoDAO#insertBloqueoPremios(java.util.Map)
	 */
	public void insertBloqueoPremios(Map criteria) {
		getSqlMapClientTemplate().insert(
				"spusicc.incentivos.MantenimientoINCSQL.insertBloqueoPremios", criteria);
	}
	
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCBloqueoPremiosDAO#existeBloqueoPremio(java.util.Map)
     */
    public boolean existeBloqueoPremio(Map criteria) {
    	Integer result = (Integer) getSqlMapClientTemplate().queryForObject(
        							"spusicc.incentivos.MantenimientoINCSQL.getExisteBloqueoPremios", criteria);
        
        if(result.intValue()>0)
        	return true;
        else
        	return false;
    }	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCBloqueoPremiosConcursoDAO#updateBloqueoPremios(java.util.Map)
	 */
	public void updateBloqueoPremios(Map criteria) {
		getSqlMapClientTemplate().update(
				"spusicc.incentivos.MantenimientoINCSQL.updateBloqueoPremios", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.MantenimientoINCBloqueoPremiosConcursoDAO#deleteBloqueoPremios(java.util.Map)
	 */
	public void deleteBloqueoPremios(Map criteria) {
		getSqlMapClientTemplate().update(
				"spusicc.incentivos.MantenimientoINCSQL.deleteBloqueoPremios", criteria);
	}

}
