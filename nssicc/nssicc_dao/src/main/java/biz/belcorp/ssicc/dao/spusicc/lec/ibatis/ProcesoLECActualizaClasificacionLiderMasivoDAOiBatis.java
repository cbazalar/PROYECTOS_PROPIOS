package biz.belcorp.ssicc.dao.spusicc.lec.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.lec.ProcesoLECActualizaClasificacionLiderMasivoDAO;

/**
 * Proceso que realiza la Actualizacion de Clasificacion de Lider Masivo
 * 
 * <p>
 * <a href="ProcesoLECActualizaClasificacionLiderMasivoDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author Yahir Rivas L.
 *         
 */
@Repository("spusicc.procesoLECActualizaClasificacionLiderMasivoDAO")
public class ProcesoLECActualizaClasificacionLiderMasivoDAOiBatis extends BaseDAOiBatis implements ProcesoLECActualizaClasificacionLiderMasivoDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.ProcesoLECActualizaClasificacionLiderMasivoDAO#executeProcesoLECActualizaClasificacionLiderMasivo(java.util.Map)
	 */
	public void executeProcesoLECActualizaClasificacionLiderMasivo(
			Map params) {
		log.info("Entro a ProcesoLECActualizaClasificacionLiderMasivoDAOiBatis - executeProcesoLECActualizaClasificacionLiderMasivo(java.util.Map)");
		getSqlMapClientTemplate().update("spusicc.lec.ProcesosLECSQL.executeProcesoLECActualizaClasificacionLiderMasivo", params);
		log.info("Salio a ProcesoLECActualizaClasificacionLiderMasivoDAOiBatis - executeProcesoLECActualizaClasificacionLiderMasivo(java.util.Map)");		
	}

}
