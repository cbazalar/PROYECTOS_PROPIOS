/**
 * 
 */
package biz.belcorp.ssicc.dao.soa.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.soa.CDRDAO;

/**
 * @author Danny Amaro
 *
 */
@Repository("soa.cdrDAO")
public class CDRDAOiBatis extends BaseDAOiBatis implements CDRDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.soa.dao.CDRDAO#getListaCDR(java.util.Map)
	 */
	public List getListaCDR(Map criteria) {
		
		return getSqlMapClientTemplate().queryForList("soa.CDRSQL.getCDR", criteria);
		
	}

}
