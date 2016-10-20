/*
 * Created on 21-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.dao.sisicc.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.BloqueoLiderDAO;
import biz.belcorp.ssicc.dao.sisicc.model.BloqueoLider;
import biz.belcorp.ssicc.dao.sisicc.model.BloqueoLiderPK;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="BloqueoLiderDAOiBatis.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */
@Repository("sisicc.bloqueoLiderDAO")
public class BloqueoLiderDAOiBatis extends BaseDAOiBatis implements BloqueoLiderDAO {

	/* 
	 * @see biz.belcorp.ssicc.dao.BloqueoLiderDAO#getBloqueoLiders(biz.belcorp.ssicc.model.BloqueoLider)
	 */
	public List getBloqueoLiders(BloqueoLider bloqueoLider) {
		List bloqueoLiders = getSqlMapClientTemplate().queryForList("sisicc.ProcesosCOMSQL.getBloqueoLidersByCriteria", bloqueoLider);
		return bloqueoLiders;
	}

	/* 
	 * @see biz.belcorp.ssicc.dao.BloqueoLiderDAO#getBloqueoLidersByCriteria(java.util.Map)
	 */
	public List getBloqueoLidersByCriteria(Map criteria) {
		List bloqueoLiders = getSqlMapClientTemplate().queryForList("sisicc.ProcesosCOMSQL.getBloqueoLidersByCriteria", criteria);
		return bloqueoLiders;
	}

	/* 
	 * @see biz.belcorp.ssicc.dao.BloqueoLiderDAO#getBloqueoLider(biz.belcorp.ssicc.model.BloqueoLiderPK)
	 */
	public BloqueoLider getBloqueoLider(BloqueoLiderPK primaryKey) {
		BloqueoLider bloqueoLider = (BloqueoLider)getSqlMapClientTemplate().queryForObject("sisicc.ProcesosCOMSQL.getBloqueoLider", primaryKey);
		
		if(bloqueoLider == null){
			throw new ObjectRetrievalFailureException(BloqueoLider.class, primaryKey);
		}
		return bloqueoLider;
	}

	/* 
	 * @see biz.belcorp.ssicc.dao.BloqueoLiderDAO#insertBloqueoLider(biz.belcorp.ssicc.model.BloqueoLider, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertBloqueoLider(BloqueoLider bloqueoLider, Usuario usuario) {
		getSqlMapClientTemplate().update("sisicc.ProcesosCOMSQL.insertBloqueoLider", bloqueoLider);
	}
	
	/* 
	 * @see biz.belcorp.ssicc.dao.BloqueoLiderDAO#updateBloqueoLider(biz.belcorp.ssicc.model.BloqueoLider, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateBloqueoLider(BloqueoLider bloqueoLider, Usuario usuario) {
		getSqlMapClientTemplate().update("sisicc.ProcesosCOMSQL.updateBloqueoLider", bloqueoLider);
	}

	/* 
	 * @see biz.belcorp.ssicc.dao.BloqueoLiderDAO#removeBloqueoLider(biz.belcorp.ssicc.model.BloqueoLiderPK)
	 */
	public void removeBloqueoLider(BloqueoLiderPK primaryKey) {
		getSqlMapClientTemplate().update("sisicc.ProcesosCOMSQL.removeBloqueoLider", primaryKey);
	}
  
}