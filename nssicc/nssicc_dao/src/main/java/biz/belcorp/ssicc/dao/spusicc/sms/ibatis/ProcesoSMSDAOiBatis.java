/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.sms.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.sms.ProcesoSMSDAO;

/**
 * 
 * <p>
 * <a href="ProcesoSMSDAOiBatis.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author Danny Amaro
 * 
 */
@Repository("spusicc.procesoSMSDAO")
public class ProcesoSMSDAOiBatis extends BaseDAOiBatis implements ProcesoSMSDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sms.dao.ProcesoSMSDAO#getSMSList(java.util.Map)
	 */
	public List getSMSList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.sms.ProcesoSMSSQL.getSMSList", criteria);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sms.dao.ProcesoSMSDAO#insertSMS(java.util.Map)
	 */
	public void insertSMS(Map criteria, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.sms.ProcesoSMSSQL.insertSMS", criteria);				
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sms.dao.ProcesoSMSDAO#removeSMS(java.util.Map)
	 */
	public void removeSMS(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.sms.ProcesoSMSSQL.removeSMS", criteria);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sms.dao.ProcesoSMSDAO#activarSMS(java.util.Map)
	 */
	public void activarSMS(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.sms.ProcesoSMSSQL.activarSMS", criteria);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sms.dao.ProcesoSMSDAO#desactivarSMS(java.util.Map)
	 */
	public void desactivarSMS(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.sms.ProcesoSMSSQL.desactivarSMS", criteria);		
	}

}
