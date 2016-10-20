package biz.belcorp.ssicc.dao.spusicc.inc.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCAcumulacionPuntosProgramaPuntosDAO;

/**
 * @author itocto
 *
 */
@Repository("spusicc.procesoINCAcumulacionPuntosProgramaPuntosDAO")
public class ProcesoINCAcumulacionPuntosProgramaPuntosDAOIbatis extends BaseDAOiBatis implements 
				ProcesoINCAcumulacionPuntosProgramaPuntosDAO  {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCAcumulacionPuntosProgramaPuntosDAO#executeAcumulacionPuntosProgramaPuntos(java.util.Map)
	 */
	public void executeAcumulacionPuntosProgramaPuntos(Map params) {
		getSqlMapClientTemplate().update("spusicc.incentivos.ProcesoINCSQL.executeAcumulacionPuntosProgramaPuntos",params);
	}

}
