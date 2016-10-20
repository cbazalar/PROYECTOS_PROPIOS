package biz.belcorp.ssicc.dao.spusicc.sto.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.sto.MantenimientoSTOExcepcionesPaseFueraFechaDAO;

/**
 * @author peextdoliva
 */
@Repository("spusicc.mantenimientoSTOExcepcionesPaseFueraFechaDAO")
public class MantenimientoSTOExcepcionesPaseFueraFechaDAOiBatis extends BaseDAOiBatis implements MantenimientoSTOExcepcionesPaseFueraFechaDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.MantenimientoSTOExcepcionesPaseFueraFechaDAO#getExcepcionesPaseFueraFecha(java.util.Map)
	 */
	public List getExcepcionesPaseFueraFecha(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getExcepcionesPaseFueraFecha", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.MantenimientoSTOExcepcionesPaseFueraFechaDAO#deleteExcepciones(java.util.Map)
	 */
	public void deleteExcepciones(Map criteria) {
		getSqlMapClientTemplate().delete("spusicc.ProcesosSTOSQL.deleteExcepciones", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.MantenimientoSTOExcepcionesPaseFueraFechaDAO#insertExcepciones(java.util.Map)
	 */
	public void insertExcepciones(Map criteria){
		getSqlMapClientTemplate().insert("spusicc.ProcesosSTOSQL.insertExcepciones", criteria);
	}
}