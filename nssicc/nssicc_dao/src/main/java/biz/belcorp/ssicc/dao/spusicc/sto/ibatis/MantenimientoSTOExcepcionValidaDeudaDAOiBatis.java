package biz.belcorp.ssicc.dao.spusicc.sto.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.sto.MantenimientoSTOExcepcionValidaDeudaDAO;

/**
 * @author Jesse James Rios Franco
 *
 */
@Repository("spusicc.mantenimientoSTOExcepcionValidaDeudaDAO")
public class MantenimientoSTOExcepcionValidaDeudaDAOiBatis extends BaseDAOiBatis implements MantenimientoSTOExcepcionValidaDeudaDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.MantenimientoSTOExcepcionValidaDeudaDAO#getExcepcionValidaDeudaList(java.util.Map)
	 */
	public List getExcepcionValidaDeudaList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getExcepcionValidaDeudaList", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.MantenimientoSTOExcepcionValidaDeudaDAO#insertExcepValidDeuda(java.util.Map)
	 */
	public void insertExcepValidDeuda(Map criteria) {
		getSqlMapClientTemplate().insert("spusicc.ProcesosSTOSQL.insertExcepValidDeuda",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.MantenimientoSTOExcepcionValidaDeudaDAO#deleteExcepcionValidaDeuda(java.util.Map)
	 */
	public void deleteExcepcionValidaDeuda(Map criteria) {
		getSqlMapClientTemplate().delete("spusicc.ProcesosSTOSQL.deleteExcepcionValidaDeuda", criteria);
	}
}