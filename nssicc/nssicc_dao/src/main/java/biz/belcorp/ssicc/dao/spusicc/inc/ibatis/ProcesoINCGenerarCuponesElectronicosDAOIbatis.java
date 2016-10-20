package biz.belcorp.ssicc.dao.spusicc.inc.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCGenerarCuponesElectronicosDAO;
/**
 * @author peextsapaza
 *
 */
@Repository("spusicc.procesoINCGenerarCuponesElectronicosDAO")
public class ProcesoINCGenerarCuponesElectronicosDAOIbatis extends BaseDAOiBatis implements 
				ProcesoINCGenerarCuponesElectronicosDAO  {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCGenerarCuponesElectronicosDAO#executeGenerarCuponesElectronicos(java.util.Map)
	 */
	public void executeGenerarCuponesElectronicos(Map params) {
		getSqlMapClientTemplate().update("spusicc.incentivos.ProcesoINCSQL.executeGenerarCuponesElectronicos",params);
		
	}

}
