package biz.belcorp.ssicc.dao.spusicc.let.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.let.ProcesoLETActualizacionClasificacionLiderMasivoDAO;

/**
 * Proceso que realiza la Actualizacion de Clasificacion de Lider Masivo
 * 
 * <p>
 * <a href="ProcesoLETActualizacionClasificacionLiderMasivoDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author Danny Amaro
 *         
 */
@Repository("spusicc.procesoLETActualizacionClasificacionLiderMasivoDAO")
public class ProcesoLETActualizacionClasificacionLiderMasivoDAOiBatis extends BaseDAOiBatis implements ProcesoLETActualizacionClasificacionLiderMasivoDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.ProcesoLETActualizacionClasificacionLiderMasivoDAO#executeProcesoLETActualizacionClasificacionLiderMasivo(java.util.Map)
	 */
	public void executeProcesoLETActualizacionClasificacionLiderMasivo(
			Map params) {
		log.info("Entro a ProcesoLETActualizacionClasificacionLiderMasivoDAOiBatis - executeProcesoLETActualizacionClasificacionLiderMasivo(java.util.Map)");
		getSqlMapClientTemplate().update("spusicc.let.ProcesosLETSQL.executeProcesoLETActualizacionClasificacionLiderMasivo", params);
		log.info("Salio a ProcesoLETActualizacionClasificacionLiderMasivoDAOiBatis - executeProcesoLETActualizacionClasificacionLiderMasivo(java.util.Map)");		
	}

}
