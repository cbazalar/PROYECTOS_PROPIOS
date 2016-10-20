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
import biz.belcorp.ssicc.dao.sisicc.SistemaDAO;
import biz.belcorp.ssicc.dao.sisicc.model.Sistema;
import biz.belcorp.ssicc.dao.sisicc.model.SistemaPK;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="SistemaDAOiBatis.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */
@Repository("sisicc.sistemaDAO")
public class SistemaDAOiBatis extends BaseDAOiBatis implements SistemaDAO {

	/* 
	 * @see biz.belcorp.ssicc.dao.SistemaDAO#getSistemas(biz.belcorp.ssicc.model.Sistema)
	 */
	public List getSistemas(Sistema sistema) {
		List sistemas = getSqlMapClientTemplate().queryForList("sisicc.SistemaSQL.getSistemas", sistema);
		return sistemas;
	}

	/* 
	 * @see biz.belcorp.ssicc.dao.SistemaDAO#getSistemasByCriteria(java.util.Map)
	 */
	public List getSistemasByCriteria(Map criteria) {
		List sistemas = getSqlMapClientTemplate().queryForList("sisicc.SistemaSQL.getSistemasByCriteria", criteria);
		return sistemas;
	}
	
	public List getSistemasByCriteria2(Map criteria) {
		List sistemas = getSqlMapClientTemplate().queryForList("sisicc.SistemaSQL.getSistemasByCriteria2", criteria);
		return sistemas;
	}

	/* 
	 * @see biz.belcorp.ssicc.dao.SistemaDAO#getSistema(biz.belcorp.ssicc.model.SistemaPK)
	 */
	public Sistema getSistema(SistemaPK primaryKey) {
		Sistema sistema = (Sistema)getSqlMapClientTemplate().queryForObject("sisicc.SistemaSQL.getSistema", primaryKey);
		
		if(sistema == null){
			throw new ObjectRetrievalFailureException(Sistema.class, primaryKey);
		}
		return sistema;
	}

	/* 
	 * @see biz.belcorp.ssicc.dao.SistemaDAO#insertSistema(biz.belcorp.ssicc.model.Sistema, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertSistema(Sistema sistema, Usuario usuario) {
		getSqlMapClientTemplate().update("sisicc.SistemaSQL.insertSistema", sistema);
	}
	
	/* 
	 * @see biz.belcorp.ssicc.dao.SistemaDAO#updateSistema(biz.belcorp.ssicc.model.Sistema, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateSistema(Sistema sistema, Usuario usuario) {
		getSqlMapClientTemplate().update("sisicc.SistemaSQL.updateSistema", sistema);
	}

	/* 
	 * @see biz.belcorp.ssicc.dao.SistemaDAO#removeSistema(biz.belcorp.ssicc.model.SistemaPK)
	 */
	public void removeSistema(SistemaPK primaryKey) {
		getSqlMapClientTemplate().update("sisicc.SistemaSQL.removeSistema", primaryKey);
	}
}