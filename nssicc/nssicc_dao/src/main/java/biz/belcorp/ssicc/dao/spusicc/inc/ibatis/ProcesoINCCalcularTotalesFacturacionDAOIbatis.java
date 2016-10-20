package biz.belcorp.ssicc.dao.spusicc.inc.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCCalcularTotalesFacturacionDAO;
/**
 * @author peextsapaza
 *
 */


@Repository("spusicc.procesoINCCalcularTotalesFacturacionDAO")
public class ProcesoINCCalcularTotalesFacturacionDAOIbatis extends BaseDAOiBatis implements 
				ProcesoINCCalcularTotalesFacturacionDAO  {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCGenerarSolicitudBolsaFaltantesDAO#executeCalcularTotalesFacturacion(java.util.Map)
	 */
	public void executeCalcularTotalesFacturacion(Map params) {
		getSqlMapClientTemplate().update("spusicc.incentivos.ProcesoINCSQL.executeCalcularTotalesFacturacion",params);
	}

}
