package biz.belcorp.ssicc.dao.spusicc.gen.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.gen.ProcesoGENActualizarNivelConsultoraDuplaKitDAO;

@Repository("spusicc.procesoGENActualizarNivelConsultoraDuplaKitDAO")
public class ProcesoGENActualizarNivelConsultoraDuplaKitDAOIBatis  extends BaseDAOiBatis implements ProcesoGENActualizarNivelConsultoraDuplaKitDAO {

	public void executeProcesoGENActualizar1erNivel2doNivelConsultoraDuplaKit(
			Map params) {
		getSqlMapClientTemplate().update("spusicc.ProcesosMAESQL.executeProcesoGENActualizar1erNivel2doNivelConsultoraDuplaKit", params);
	}

	public void executeProcesoGENActualizar1erNivelSinNivelConsultoraDuplaKit(
			Map params) {
		getSqlMapClientTemplate().update("spusicc.ProcesosMAESQL.executeProcesoGENActualizar1erNivelSinNivelConsultoraDuplaKit", params);
	}

	public void executeProcesoGENActualizar2doNivel3erNivelConsultoraDuplaKit(
			Map params) {
		getSqlMapClientTemplate().update("spusicc.ProcesosMAESQL.executeProcesoGENActualizar2doNivel3erNivelConsultoraDuplaKit", params);
	}

	public void executeProcesoGENActualizar3erNivelSinNivelConsultoraDuplaKit(
			Map params) {
		getSqlMapClientTemplate().update("spusicc.ProcesosMAESQL.executeProcesoGENActualizar3erNivelSinNivelConsultoraDuplaKit", params);
	}

	public void executeProcesoGENRegistrarClasificacion1erNivelDuplaKit(
			Map params) {
		getSqlMapClientTemplate().update("spusicc.ProcesosMAESQL.executeProcesoGENRegistrarClasificacion1erNivelDuplaKit", params);
	}
}
