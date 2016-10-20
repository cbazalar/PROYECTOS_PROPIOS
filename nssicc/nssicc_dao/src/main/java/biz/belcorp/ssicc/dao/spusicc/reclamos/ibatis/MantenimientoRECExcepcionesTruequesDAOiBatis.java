package biz.belcorp.ssicc.dao.spusicc.reclamos.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.reclamos.MantenimientoRECExcepcionesTruequesDAO;

/**
 * <p>
 * <a href="MantenimientoRECExcepcionesTruequesDAOiBatis.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="itocto@sigcomt.com">Ivan Tocto Jaimes</a>
 * 
 */
@Repository("spusicc.mantenimientoRECExcepcionesTruequesDAO")
public class MantenimientoRECExcepcionesTruequesDAOiBatis extends BaseDAOiBatis implements MantenimientoRECExcepcionesTruequesDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECExcepcionesTruequesDAO#deleteExcepcionTrueque(java.util.Map)
	 */
	public void deleteExcepcionTrueque(Map criteria) {
		getSqlMapClientTemplate().insert("spusicc.reclamos.ReclamosSQL.deleteExcepcionTrueque", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECExcepcionesTruequesDAO#getExcepcionesTrueques(java.util.Map)
	 */
	public List getExcepcionesTrueques(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getExcepcionesTrueques", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECExcepcionesTruequesDAO#insertExcepcionTrueque(java.util.Map)
	 */
	public void insertExcepcionTrueque(Map criteria) {
		getSqlMapClientTemplate().insert("spusicc.reclamos.ReclamosSQL.insertExcepcionTrueque", criteria);		
	}
}
