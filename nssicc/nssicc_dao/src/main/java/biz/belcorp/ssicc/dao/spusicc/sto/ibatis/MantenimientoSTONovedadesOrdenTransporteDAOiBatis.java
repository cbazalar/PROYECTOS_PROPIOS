package biz.belcorp.ssicc.dao.spusicc.sto.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.sto.MantenimientoSTONovedadesOrdenTransporteDAO;

/**
 * @author peextdoliva
 */
@Repository("spusicc.mantenimientoSTONovedadesOrdenTransporteDAO")
public class MantenimientoSTONovedadesOrdenTransporteDAOiBatis extends BaseDAOiBatis implements MantenimientoSTONovedadesOrdenTransporteDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.MantenimientoSTONovedadesOrdenTransporteDAO#getNovedadesOrdenTransporte(java.util.Map)
	 */
	public List getNovedadesOrdenTransporte(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getNovedadesOrdenTransporte", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.MantenimientoSTONovedadesOrdenTransporteDAO#deleteNovedadesOrdenTransporte(java.util.Map)
	 */
	public void deleteNovedadesOrdenTransporte(Map criteria){
		getSqlMapClientTemplate().insert("spusicc.ProcesosSTOSQL.deleteNovedadesOrdenTransporte", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.MantenimientoSTONovedadesOrdenTransporteDAO#insertNovedadesOrdenTransporte(java.util.Map)
	 */
	public void insertNovedadesOrdenTransporte(Map criteria){
		getSqlMapClientTemplate().insert("spusicc.ProcesosSTOSQL.insertNovedadesOrdenTransporte", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.MantenimientoSTONovedadesOrdenTransporteDAO#updateNovedadesOrdenTransporte(java.util.Map)
	 */
	public void updateNovedadesOrdenTransporte(Map criteria){
		getSqlMapClientTemplate().insert("spusicc.ProcesosSTOSQL.updateNovedadesOrdenTransporte", criteria);
	}
	
}