/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.let.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.let.ProcesoLETCalculoObjetivoRetencion33SeccionDAO;

/**
 * Proceso que realiza el calculo Objetivo Retencion 3/3 Seccion
 * 
 * <p>
 * <a href="ProcesoLETCalculoObjetivoRetencion33SeccionDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author Danny Amaro
 *         
 */
@Repository("spusicc.procesoLETCalculoObjetivoRetencion33SeccionDAO")
public class ProcesoLETCalculoObjetivoRetencion33SeccionDAOiBatis extends BaseDAOiBatis implements ProcesoLETCalculoObjetivoRetencion33SeccionDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.ProcesoLETCalculoObjetivoRetencion33SeccionDAO#executeProcesoLETCalculoObjetivoRetencion33Seccion(java.util.Map)
	 */
	public void executeProcesoLETCalculoObjetivoRetencion33Seccion(Map params) {
		log.info("Entro a ProcesoLETCalculoObjetivoRetencion33SeccionDAOiBatis - executeProcesoLETCalculoObjetivoRetencion33Seccion(java.util.Map)");
		getSqlMapClientTemplate().update("spusicc.let.ProcesosLETSQL.executeProcesoLETCalculoObjetivoRetencion33Seccion", params);
		log.info("Salio a ProcesoLETCalculoObjetivoRetencion33SeccionDAOiBatis - executeProcesoLETCalculoObjetivoRetencion33Seccion(java.util.Map)");		
	}

}
