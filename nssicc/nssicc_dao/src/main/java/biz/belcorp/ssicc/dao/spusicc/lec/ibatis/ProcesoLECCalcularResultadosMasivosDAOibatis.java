package biz.belcorp.ssicc.dao.spusicc.lec.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.lec.ProcesoLECCalcularResultadosMasivosDAO;

@Repository("spusicc.procesoLECCalcularResultadosMasivosDAO")
public class ProcesoLECCalcularResultadosMasivosDAOibatis  extends BaseDAOiBatis implements ProcesoLECCalcularResultadosMasivosDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.ProcesoLECCalcularObjetivosBonosDAO#executeProcesoLECCalcularObjetivosBonos(java.util.Map)
	 */
	public void executeProcesoLECCalcularResultadosMasivos(Map params) {
		log.info("Entro a ProcesoLECCalcularResultadosMasivosDAOibatis - executeProcesoLECCalcularResultadosMasivos(java.util.Map)");
		getSqlMapClientTemplate().update("spusicc.lec.ProcesosLECSQL.executeProcesoLECCalcularResultadosMasivos", params);
		log.info("Salio a ProcesoLECCalcularResultadosMasivosDAOibatis - executeProcesoLECCalcularResultadosMasivos(java.util.Map)");
	}
}
