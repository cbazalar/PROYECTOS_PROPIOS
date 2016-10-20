package biz.belcorp.ssicc.dao.spusicc.let.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.let.ProcesoLETCalculoObjetivosRetencionesDAO;

/**
 * Proceso que realiza el calculo de Objetivos de Retenciones
 * 
 * <p>
 * <a href="ProcesoLETCalculoObjetivosRetencionesDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author Danny Amaro
 *         
 */
@Repository("spusicc.procesoLETCalculoObjetivosRetencionesDAO")
public class ProcesoLETCalculoObjetivosRetencionesDAOiBatis extends BaseDAOiBatis implements ProcesoLETCalculoObjetivosRetencionesDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.ProcesoLETCalculoObjetivosRetencionesDAO#executeProcesoLETCalculoObjetivosRetenciones(java.util.Map)
	 */
	public void executeProcesoLETCalculoObjetivosRetenciones(Map params) {
		log.info("Entro a ProcesoLETCalculoObjetivosRetencionesDAOiBatis - executeProcesoLETCalculoObjetivosRetenciones(java.util.Map)");
		getSqlMapClientTemplate().update("spusicc.let.ProcesosLETSQL.executeProcesoLETCalculoObjetivosRetenciones", params);
		log.info("Salio a ProcesoLETCalculoObjetivosRetencionesDAOiBatis - executeProcesoLETCalculoObjetivosRetenciones(java.util.Map)");		
	}

}
