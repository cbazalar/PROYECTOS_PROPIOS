package biz.belcorp.ssicc.dao.sisicc.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.sisicc.InterfazRUVDAO;

/**
 * Implementacion del DAO que ejecutara las Validadciones
 * <p>
 * <a href="InterfazRUVDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:ttataje@csigcomt.com">Telly Tataje</a>
 */
@Repository("sisicc.interfazRUVDAO")
public class InterfazRUVDAOiBatis extends BaseDAOiBatis implements
		InterfazRUVDAO {


	/* (non-Javadoc)
	 * @see  biz.belcorp.ssicc.sisicc.dao.InterfazRUVDAO#executeInterfazRUVEnviarRegistroUnicoVentas(java.util.Map)
	 */
	public void executeInterfazRUVEnviarRegistroUnicoVentas(Map params) {
		getSqlMapClientTemplate().queryForList("sisicc.InterfazRUVSQL.executeInterfazRUVEnviarRegistroUnicoVentas", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazRUVDAO#ejecutarReprocesoInterfazRUV(java.util.Map)
	 */
	public void ejecutarReprocesoInterfazRUV(Map params) {
		getSqlMapClientTemplate().delete("sisicc.InterfazRUVSQL.ejecutarReprocesoInterfazRUV", params);
	}
	
	
}