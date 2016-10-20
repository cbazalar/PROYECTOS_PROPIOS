package biz.belcorp.ssicc.dao.spusicc.lec.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.lec.ProcesoLECGenerarCodigoVentaPremioCanastaDAO;

/**
 * The Class ProcesoLECGenerarCodigoVentaPremioCanastaDAOiBatis.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 31/07/2014
 */
@Repository("spusicc.procesoLECGenerarCodigoVentaPremioCanastaDAO")
public class ProcesoLECGenerarCodigoVentaPremioCanastaDAOiBatis extends BaseDAOiBatis implements ProcesoLECGenerarCodigoVentaPremioCanastaDAO {

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.lec.ProcesoLECGenerarCodigoVentaPremioCanastaDAO#executeProcesoLECGenerarCodigoVentaPremioCanasta(java.util.Map)
	 */
	public void executeProcesoLECGenerarCodigoVentaPremioCanasta(Map params) {
		log.info("Entro a ProcesoLECGenerarCodigoVentaPremioCanastaDAOiBatis - executeProcesoLECGenerarCodigoVentaPremioCanasta(java.util.Map)");
		getSqlMapClientTemplate().update("spusicc.lec.ProcesosLECSQL.executeProcesoLECGenerarCodigoVentaPremioCanasta", params);
		log.info("Salio a ProcesoLECGenerarCodigoVentaPremioCanastaDAOiBatis - executeProcesoLECGenerarCodigoVentaPremioCanasta(java.util.Map)");
	}
}