package biz.belcorp.ssicc.dao.spusicc.sto.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.sto.MantenimientoSTOEstadoEntregaOrdenTransporteDAO;

/**
 * @author peextdoliva
 */
@Repository("spusicc.mantenimientoSTOEstadoEntregaOrdenTransporteDAO")
public class MantenimientoSTOEstadoEntregaOrdenTransporteDAOiBatis extends BaseDAOiBatis implements MantenimientoSTOEstadoEntregaOrdenTransporteDAO{


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.MantenimientoSTOEstadoEntregaOrdenTransporteDAO#getTiposOrdenTransporte()
	 */
	public List getTiposOrdenTransporte() {
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getTiposOrdenTransporte", null);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.MantenimientoSTOEstadoEntregaOrdenTransporteDAO#getEstadosOrdenTransporte(java.util.Map)
	 */
	public List getEstadosOrdenTransporte(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getEstadosOrdenTransporte", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.MantenimientoSTOEstadoEntregaOrdenTransporteDAO#insertEstadoOrdenTransporte(java.util.Map)
	 */
	public void insertEstadoOrdenTransporte(Map criteria){
		getSqlMapClientTemplate().insert("spusicc.ProcesosSTOSQL.insertEstadoOrdenTransporte", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.MantenimientoSTOEstadoEntregaOrdenTransporteDAO#updateEstadoOrdenTransporte(java.util.Map)
	 */
	public void updateEstadoOrdenTransporte(Map criteria){
		getSqlMapClientTemplate().insert("spusicc.ProcesosSTOSQL.updateEstadoOrdenTransporte", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.MantenimientoSTOEstadoEntregaOrdenTransporteDAO#deleteEstadoOrdenTransporte(java.util.Map)
	 */
	public void deleteEstadoOrdenTransporte(Map criteria){
		getSqlMapClientTemplate().insert("spusicc.ProcesosSTOSQL.deleteEstadoOrdenTransporte", criteria);
	}
	
}