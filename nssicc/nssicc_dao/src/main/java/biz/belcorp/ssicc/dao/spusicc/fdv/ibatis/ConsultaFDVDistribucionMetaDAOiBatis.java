package biz.belcorp.ssicc.dao.spusicc.fdv.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.fdv.ConsultaFDVDistribucionMetaDAO;

/**
 * <p>
 * <a href="ConsultaFDVDistribucionMetaDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:fayala@belcorp.biz">Frank Ayala</a>
 */
@Repository("spusicc.consultaFDVDistribucionMetaDAO")
public class ConsultaFDVDistribucionMetaDAOiBatis extends BaseDAOiBatis implements ConsultaFDVDistribucionMetaDAO{

	/**
     * 
     * @see biz.belcorp.ssicc.spusicc.fdv.dao.ConsultaFDVDistribucionMetaDAO#getDistribucionMetaFDVByCriteria(java.util.Map)
     */
	public List getDistribucionMetaFDVByCriteria(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
		"spusicc.fdv.ProcesoFDVClusterizacionSQL.getDistribucionMetaFDVByCriteria", criteria);
	}

}
