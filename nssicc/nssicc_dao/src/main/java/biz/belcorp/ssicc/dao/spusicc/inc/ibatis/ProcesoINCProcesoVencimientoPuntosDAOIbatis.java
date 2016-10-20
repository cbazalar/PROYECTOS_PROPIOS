package biz.belcorp.ssicc.dao.spusicc.inc.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCProcesoVencimientoPuntosDAO;

@Repository("spusicc.procesoINCProcesoVencimientoPuntosDAO")
public class ProcesoINCProcesoVencimientoPuntosDAOIbatis extends BaseDAOiBatis
		implements ProcesoINCProcesoVencimientoPuntosDAO {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCProcesoVencimientoPuntosDAO
	 * #executeProcesoVencimientoPuntos(java.util.Map)
	 */
	public void executeProcesoVencimientoPuntos(Map params) {
		getSqlMapClientTemplate()
				.update("spusicc.incentivos.ProcesoINCSQL.executeProcesoVencimientoPuntos",
						params);
	}

}