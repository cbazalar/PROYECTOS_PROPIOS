package biz.belcorp.ssicc.dao.spisicc.ibatis;
	    

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spisicc.ProcesoIMPSpoolDocumentoInternoDAO;

/**
 * 
 * <p>
 * <a href="ProcesoIMPSpoolDocumentoInternoDAO.java.html"> <i>View Source</i> </a>
 * </p>
 * @author <a href="">Jose Luis Rodriguez</a>
 *
 */
@Repository("spisicc.procesoIMPSpoolDocumentoInternoDAO")
public class ProcesoIMPSpoolDocumentoInternoDAOIbatis extends BaseDAOiBatis implements ProcesoIMPSpoolDocumentoInternoDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.ProcesoIMPSpoolDocumentoInternoDAO#executeGeneraDocumentoInterno(java.util.Map)
	 */
	public void executeGeneraDocumentoInterno(Map params) {
		getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeGeneraDocumentoInterno", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.ProcesoIMPSpoolDocumentoInternoDAO#executeGeneraPaqueteLaser(java.util.Map)
	 */
	public void executeGeneraPaqueteLaser(Map params) {
		getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeGeneraPaqueteLaser", params);
	}
}