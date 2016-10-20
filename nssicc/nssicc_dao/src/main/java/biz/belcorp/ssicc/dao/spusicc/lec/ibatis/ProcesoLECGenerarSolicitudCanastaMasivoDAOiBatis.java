package biz.belcorp.ssicc.dao.spusicc.lec.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.lec.ProcesoLECGenerarSolicitudCanastaMasivoDAO;


/**
 * The Class ProcesoLECGenerarSolicitudCanastaMasivoDAOiBatis.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 31/07/2014
 */

@Repository("spusicc.procesoLECGenerarSolicitudCanastaMasivoDAO")
public class ProcesoLECGenerarSolicitudCanastaMasivoDAOiBatis extends BaseDAOiBatis implements ProcesoLECGenerarSolicitudCanastaMasivoDAO {

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.lec.ProcesoLECGenerarSolicitudCanastaMasivoDAO#executeProcesoLECGenerarSolicitudCanastaMasivo(java.util.Map)
	 */
	public void executeProcesoLECGenerarSolicitudCanastaMasivo(Map params) {
		log.info("Entro a ProcesoLECGenerarSolicitudCanastaMasivoDAOiBatis - executeProcesoLECGenerarSolicitudCanastaMasivo(java.util.Map)");
		getSqlMapClientTemplate().update("spusicc.lec.ProcesosLECSQL.executeProcesoLECGenerarSolicitudCanastaMasivo", params);
		log.info("Salio a ProcesoLECGenerarSolicitudCanastaMasivoDAOiBatis - executeProcesoLECGenerarSolicitudCanastaMasivo(java.util.Map)");
	}
}