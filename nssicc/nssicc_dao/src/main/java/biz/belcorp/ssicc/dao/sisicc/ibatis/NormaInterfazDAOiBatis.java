/*
 * Created on 06-dic-2005
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
import biz.belcorp.ssicc.dao.sisicc.NormaInterfazDAO;
import biz.belcorp.ssicc.dao.sisicc.model.NormaInterfaz;
import biz.belcorp.ssicc.dao.sisicc.model.NormaInterfazPK;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="NormaInterfazDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */
@Repository("sisicc.normaInterfazDAO")
public class NormaInterfazDAOiBatis extends BaseDAOiBatis implements NormaInterfazDAO {

	/* 
	 * @see biz.belcorp.ssicc.dao.NormaInterfazDAO#getNormaInterfaz(biz.belcorp.ssicc.model.NormaInterfazPK)
	 */
	public NormaInterfaz getNormaInterfaz(NormaInterfazPK pk) {
		NormaInterfaz norma = (NormaInterfaz)getSqlMapClientTemplate().queryForObject("sisicc.NormaInterfazSQL.getNormaInterfaz", pk);
		if(norma == null){
			throw new ObjectRetrievalFailureException(NormaInterfaz.class, pk);
		}
		return norma;
	}

	/* 
	 * @see biz.belcorp.ssicc.dao.NormaInterfazDAO#getNormaInterfazByCriteria(biz.belcorp.ssicc.model.NormaInterfaz)
	 */
	public NormaInterfaz getNormaInterfazByCriteria(NormaInterfaz criteria) {
		NormaInterfaz norma = (NormaInterfaz)getSqlMapClientTemplate().queryForObject("sisicc.NormaInterfazSQL.getNormaInterfazByCriteria", criteria);
		if(norma == null){
			throw new ObjectRetrievalFailureException(NormaInterfaz.class, criteria);
		}
		return norma;
	}
	
	/* 
	 * @see biz.belcorp.ssicc.dao.NormaInterfazDAO#getNormasByCriteria(java.util.Map)
	 */
	public List getNormasByCriteria(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.NormaInterfazSQL.getNormasByCriteria", criteria);
	}
	
	/* 
	 * @see biz.belcorp.ssicc.dao.NormaInterfazDAO#insertNormaInterfaz(biz.belcorp.ssicc.model.NormaInterfaz, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertNormaInterfaz(NormaInterfaz norma, Usuario usuario) {
		getSqlMapClientTemplate().update("sisicc.NormaInterfazSQL.insertNormaInterfaz", norma);
	}
	/* 
	 * @see biz.belcorp.ssicc.dao.NormaInterfazDAO#removeNormaInterfaz(biz.belcorp.ssicc.model.NormaInterfazPK)
	 */
	public void removeNormaInterfaz(NormaInterfazPK primaryKey) {
		getSqlMapClientTemplate().update("sisicc.NormaInterfazSQL.removeNormaInterfaz", primaryKey);
	}
	/* 
	 * @see biz.belcorp.ssicc.dao.NormaInterfazDAO#updateNormaInterfaz(biz.belcorp.ssicc.model.NormaInterfaz, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateNormaInterfaz(NormaInterfaz norma, Usuario usuario) {
		getSqlMapClientTemplate().update("sisicc.NormaInterfazSQL.updateNormaInterfaz", norma);
	}	
	/* 
	 * @see biz.belcorp.ssicc.dao.NormaInterfazDAO#getSiguienteCodigo(biz.belcorp.ssicc.model.NormaInterfazPK)
	 */
	public String getSiguienteCodigo(NormaInterfazPK primaryKey) {
		return (String)getSqlMapClientTemplate().queryForObject("sisicc.NormaInterfazSQL.getSiguienteCodigo", primaryKey);
	}
}
