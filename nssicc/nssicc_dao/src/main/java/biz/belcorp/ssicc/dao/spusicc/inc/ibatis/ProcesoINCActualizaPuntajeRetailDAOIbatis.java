package biz.belcorp.ssicc.dao.spusicc.inc.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCActualizaPuntajeRetailDAO;

/**
 * @author Sergio Apaza
 *
 */
@Repository("spusicc.procesoINCActualizaPuntajeRetailDAO")
public class ProcesoINCActualizaPuntajeRetailDAOIbatis extends BaseDAOiBatis implements ProcesoINCActualizaPuntajeRetailDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCActualizaPuntajeRetailDAO#executeActualizaPuntajeRetail(java.util.Map)
	 */
	public void executeActualizaPuntajeRetail(Map params) {
		getSqlMapClientTemplate().update("spusicc.incentivos.ProcesoINCSQL.executeActualizaPuntajeRetail", params);
	}
}