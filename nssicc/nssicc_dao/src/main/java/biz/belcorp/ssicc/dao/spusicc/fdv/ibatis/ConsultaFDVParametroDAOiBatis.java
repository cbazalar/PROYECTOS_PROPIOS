package biz.belcorp.ssicc.dao.spusicc.fdv.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.fdv.ConsultaFDVParametroDAO;

/**
 * <p>
 * <a href="ConsultaFDVParametroDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:fayala@belcorp.biz">Frank Ayala</a>
 */
@Repository("spusicc.consultaFDVParametroDAO")
public class ConsultaFDVParametroDAOiBatis extends BaseDAOiBatis implements ConsultaFDVParametroDAO{

	/**
     * 
     * @see biz.belcorp.ssicc.spusicc.fdv.dao.ConsultaFDVParametroDAO#getParametrosFDVByCriteria(java.util.Map)
     */
	public List getParametrosFDVByCriteria(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
		"spusicc.fdv.ProcesoFDVClusterizacionSQL.getParametrosFDVByCriteria", criteria);
	}
}
