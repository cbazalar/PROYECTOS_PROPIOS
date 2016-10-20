package biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ProcesoCCCDepuracionSaldosMenoresDeudoresDAO;

/**
 * Implementacion del DAO que ejecutara la Depuracion de Saldos Menores Deudoes en Cuenta Corriente 
 * <p>
 * <a href="ProcesoCCCDepuracionSaldosMenoresDeudoresDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:jflorencio@belcorp.biz">Jorge Florencio Arias</a>
 */


/**
 * @author pejflorencio
 *
 */
@Repository("spusicc.procesoCCCDepuracionSaldosMenoresDeudoresDAO")
public class ProcesoCCCDepuracionSaldosMenoresDeudoresDAOiBatis extends BaseDAOiBatis implements ProcesoCCCDepuracionSaldosMenoresDeudoresDAO {

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ProcesoCCCDepuracionSaldosMenoresDeudoresDAO#executeDepuracionSaldosMenoresDeudores(java.util.Map)
	 */
	public void executeDepuracionSaldosMenoresDeudores(Map criteria) {
		getSqlMapClientTemplate().update(
				"spusicc.cuentacorriente.procesosCCCSQL.executeDepuracionSaldosMenoresDeudores", criteria);
	}
			
	
}
