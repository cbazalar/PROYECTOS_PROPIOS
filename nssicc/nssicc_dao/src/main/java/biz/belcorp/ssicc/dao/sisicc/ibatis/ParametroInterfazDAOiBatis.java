/*
 * Created on 12-dic-2005
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
import biz.belcorp.ssicc.dao.sisicc.ParametroInterfazDAO;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazPK;
import biz.belcorp.ssicc.dao.sisicc.model.ParametroInterfaz;
import biz.belcorp.ssicc.dao.sisicc.model.Sistema;
import biz.belcorp.ssicc.dao.sisicc.model.SistemaPK;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="ParametroInterfazDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */

@Repository("sisicc.parametroInterfazDAO")
public class ParametroInterfazDAOiBatis extends BaseDAOiBatis implements ParametroInterfazDAO {

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
	
	/* 
	 * @see biz.belcorp.ssicc.dao.ParametroInterfazDAO#insertParametroInterfaz(biz.belcorp.ssicc.model.ParametroInterfaz, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertParametroInterfaz(ParametroInterfaz parametroInterfaz, Usuario usuario) {
		getSqlMapClientTemplate().update("sisicc.ParametroInterfazSQL.insertParametroInterfaz", parametroInterfaz);
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

	/* 
	 * @see biz.belcorp.ssicc.dao.ParametroInterfazDAO#removeParametrosByPKInterfaz(biz.belcorp.ssicc.model.InterfazPK)
	 */
	public void removeParametrosByPKInterfaz(InterfazPK interfazPK) {
		getSqlMapClientTemplate().update("sisicc.ParametroInterfazSQL.removeParametrosByPKInterfaz", interfazPK);
	}

	/* 
	 * @see biz.belcorp.ssicc.dao.ParametroInterfazDAO#getParametrosByPKInterfaz(biz.belcorp.ssicc.model.InterfazPK)
	 */
	public List getParametrosByPKInterfaz(InterfazPK interfazPK) {
		List parametros = getSqlMapClientTemplate().queryForList("sisicc.ParametroInterfazSQL.getParametrosByPKInterfaz", interfazPK);
		return parametros;
	}

	/* 
	 * @see biz.belcorp.ssicc.dao.ParametroInterfazDAO#getParametroByCriteria(biz.belcorp.ssicc.model.ParametroInterfaz)
	 */
	public ParametroInterfaz getParametroByCriteria(ParametroInterfaz criteria) {
		// TODO Auto-generated method stub
		return (ParametroInterfaz)getSqlMapClientTemplate().queryForObject("sisicc.ParametroInterfazSQL.getParametroByCriteria", criteria);
	}
}
