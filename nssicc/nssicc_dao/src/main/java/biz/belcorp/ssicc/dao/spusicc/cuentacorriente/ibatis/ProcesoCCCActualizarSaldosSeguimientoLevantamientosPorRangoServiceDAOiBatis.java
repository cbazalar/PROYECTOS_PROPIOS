/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ibatis;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ProcesoCCCActualizarSaldosSeguimientoLevantamientosPorRangoServiceDAO;

/**
 * @author Diego Torres
 *
 */
@Repository("spusicc.procesoCCCActualizarSaldosSeguimientoLevantamientosPorRangoServiceDAO")
public class ProcesoCCCActualizarSaldosSeguimientoLevantamientosPorRangoServiceDAOiBatis extends BaseDAOiBatis implements ProcesoCCCActualizarSaldosSeguimientoLevantamientosPorRangoServiceDAO {
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ProcesoCCCActualizarSaldosSeguimientoLevantamientosPorRangoServiceDAO#executeProcesarSaldosSeguimientoLevantamientosPorRango(java.util.Map)
	 */
	public void executeProcesarSaldosSeguimientoLevantamientosPorRango(Map criteria){
		getSqlMapClientTemplate().update("spusicc.cuentacorriente.procesosCCCSQL.executeProcesarSaldosSeguimientoLevantamientosPorRango", criteria);
	}

	public List getRangoPeriodos(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.cuentacorriente.procesosCCCSQL.getRangoPeriodos", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ProcesoCCCActualizarSaldosSeguimientoLevantamientosPorRangoServiceDAO#executeProcesarSaldosActual(java.util.Map)
	 */
	public void executeProcesarSaldosActual(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.cuentacorriente.procesosCCCSQL.executeProcesarSaldosActual", criteria);
	}
}
