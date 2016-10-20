package biz.belcorp.ssicc.dao.spusicc.let.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.let.MantenimientoLETBonosConcursoDAO;

/**
 * @author Jesse James Rios Franco
 *
 */
@Repository("spusicc.mantenimientoLETBonosConcursoDAO")
public class MantenimientoLETBonosConcursoDAOiBatis extends BaseDAOiBatis implements MantenimientoLETBonosConcursoDAO{
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETBonosConcursoDAO#getBonoConcursoList(java.util.Map)
	 */
	public List getBonoConcursoList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.let.MantenimientoLETSQL.getBonoConcursoList", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETBonosConcursoDAO#getValidaBonoConcursoExiste(java.util.Map)
	 */
	public int getValidaBonoConcursoExiste(Map criteria) {
		return ((Integer)getSqlMapClientTemplate().queryForObject("spusicc.let.MantenimientoLETSQL.getValidaBonoConcursoExiste", criteria)).intValue();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETBonosConcursoDAO#deleteBonoConcurso(java.util.Map)
	 */
	public void deleteBonoConcurso(Map criteria) {
		getSqlMapClientTemplate().delete("spusicc.let.MantenimientoLETSQL.deleteBonoConcurso", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETBonosConcursoDAO#insertBonoConcurso(java.util.Map)
	 */
	public void insertBonoConcurso(Map criteria) {
		getSqlMapClientTemplate().delete("spusicc.let.MantenimientoLETSQL.insertBonoConcurso", criteria);
	}
}