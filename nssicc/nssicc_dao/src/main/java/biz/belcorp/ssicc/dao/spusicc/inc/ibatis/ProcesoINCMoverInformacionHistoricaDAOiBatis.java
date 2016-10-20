package biz.belcorp.ssicc.dao.spusicc.inc.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCMoverInformacionHistoricaDAO;

/**
 * Implementacion del DAO que ejecutara los metodos del proceso que mueve Informacion Historica de Incentivos
 * 
 * <p>
 * <a href="ProcesoINCMoverInformacionHistoricaDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar</a>
 */
@Repository("spusicc.procesoINCMoverInformacionHistoricaDAO")
public class ProcesoINCMoverInformacionHistoricaDAOiBatis extends BaseDAOiBatis implements 
		ProcesoINCMoverInformacionHistoricaDAO  {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCMoverInformacionHistoricaDAO#executeMoverInformacionHistorica(java.util.Map)
	 */
	public void executeMoverInformacionHistorica(Map params) {
		getSqlMapClientTemplate().update("spusicc.incentivos.ProcesoINCSQL.executeMoverInformacionHistorica",params);
		
	}

}
