package biz.belcorp.ssicc.dao.spusicc.inc.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCCancelacionPuntosProgramaPuntosDAO;

/**
 * @author itocto
 *
 */
@Repository("spusicc.procesoINCCancelacionPuntosProgramaPuntosDAO")
public class ProcesoINCCancelacionPuntosProgramaPuntosDAOIbatis extends BaseDAOiBatis implements ProcesoINCCancelacionPuntosProgramaPuntosDAO  {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCCancelacionPuntosProgramaPuntosDAO#executeCancelacionPuntosProgramaPuntos(java.util.Map)
	 */
	public void executeCancelacionPuntosProgramaPuntos(Map params) {
		getSqlMapClientTemplate().update("spusicc.incentivos.ProcesoINCSQL.executeCancelacionPuntosProgramaPuntos",params);
	}

}
