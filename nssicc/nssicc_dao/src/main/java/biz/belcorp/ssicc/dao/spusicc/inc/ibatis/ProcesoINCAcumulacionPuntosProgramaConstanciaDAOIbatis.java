package biz.belcorp.ssicc.dao.spusicc.inc.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCAcumulacionPuntosProgramaConstanciaDAO;

/**
 * @author itocto
 *
 */
@Repository("spusicc.procesoINCAcumulacionPuntosProgramaConstanciaDAO")
public class ProcesoINCAcumulacionPuntosProgramaConstanciaDAOIbatis extends BaseDAOiBatis implements 
				ProcesoINCAcumulacionPuntosProgramaConstanciaDAO  {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCAcumulacionPuntosProgramaConstanciaDAO#executeAcumulacionPuntosProgramaConstancia(java.util.Map)
	 */
	public void executeAcumulacionPuntosProgramaConstancia(Map params) {
		getSqlMapClientTemplate().update("spusicc.incentivos.ProcesoINCSQL.executeAcumulacionPuntosProgramaConstancia",params);
	}

}
