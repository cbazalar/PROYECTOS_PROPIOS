package biz.belcorp.ssicc.dao.spusicc.lec.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.lec.ProcesoLECCalculoBajaDAO;

@Repository("spusicc.procesoLECCalculoBajaDAO")
public class ProcesoLECCalculoBajaDAOibatis  extends BaseDAOiBatis implements ProcesoLECCalculoBajaDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.ProcesoLECCalcularObjetivosBonosDAO#executeProcesoLECCalcularObjetivosBonos(java.util.Map)
	 */
	public void executeProcesoLECCalculoBaja(Map params) {
		log.info("Entro a ProcesoLECCalculoBajaDAOibatis - executeProcesoLECCalculoBaja(java.util.Map)");
		getSqlMapClientTemplate().update("spusicc.lec.ProcesosLECSQL.executeProcesoLECCalculoBaja", params);
		log.info("Salio a ProcesoLECCalculoBajaDAOibatis - executeProcesoLECCalculoBaja(java.util.Map)");
	}

}
