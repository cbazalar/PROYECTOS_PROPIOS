package biz.belcorp.ssicc.dao.sisicc.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.sisicc.InterfazEDUDAO;

/**
 * Implementacion iBatis del DAO de la Interfaz Educacion.
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli</a>
 */
@Repository("sisicc.interfazEDUDAO")
public class InterfazEDUDAOiBatis extends BaseDAOiBatis implements
		InterfazEDUDAO {

	public void insertInterfazEDURecepcionarConsultoraDemanda(Map params) {
		getSqlMapClientTemplate().insert(
				"sisicc.InterfazSQL.insertInterfazEDURecepcionarConsultoraDemanda", params);
	}

	public void executeProcessRegistroAsistencia(Map map) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazSQL.executeProcessRegistroAsistencia",map);
	}

	public void insertInterfazEDURecepcionarConsultoraEstablecidas(Map map) {
		getSqlMapClientTemplate().insert(
				"sisicc.InterfazSQL.insertInterfazEDURecepcionarConsultoraEstablecidas", map);
		
	}

	public void deleteInterfazEDURecepcionarConsultoraEstablecidas(Map map) {
		getSqlMapClientTemplate().delete(
				"sisicc.InterfazSQL.deleteInterfazEDURecepcionarConsultoraEstablecidas", map);
		
	}
}