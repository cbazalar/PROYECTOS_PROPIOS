/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ibatis;


import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ProcesoCCCActualizarSaldosSeguimientoLevantamientosServiceDAO;

/**
 * @author Jose Luis Rodriguez
 *
 */
@Repository("spusicc.procesoCCCActualizarSaldosSeguimientoLevantamientosServiceDAO")
public class ProcesoCCCActualizarSaldosSeguimientoLevantamientosServiceDAOiBatis extends BaseDAOiBatis implements ProcesoCCCActualizarSaldosSeguimientoLevantamientosServiceDAO {
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ProcesoCCCActualizarSaldosSeguimientoLevantamientosServiceDAO#executeProcesarSaldosSeguimientoLevantamientos(java.util.Map)
	 */
	public void executeProcesarSaldosSeguimientoLevantamientos(Map criteria){
		getSqlMapClientTemplate().update("spusicc.cuentacorriente.procesosCCCSQL.executeProcesarSaldosSeguimientoLevantamientos", criteria);
	}
}
