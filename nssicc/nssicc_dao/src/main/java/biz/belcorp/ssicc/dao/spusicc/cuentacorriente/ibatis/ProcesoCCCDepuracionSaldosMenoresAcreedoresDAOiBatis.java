package biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ProcesoCCCDepuracionSaldosMenoresAcreedoresDAO;

/**
 * Implementacion del DAO que ejecutara la Depuracion de los Saldos Menores Acreedores en Cuenta Corriente
 * <p>
 * <a href="ProcesoCCCDepuracionSaldosMenoresAcreedoresDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:jflorencio@belcorp.biz">Jorge Florencio Arias</a>
 */


/**
 * @author pejflorencio
 *
 */
@Repository("spusicc.procesoCCCDepuracionSaldosMenoresAcreedoresDAO")
public class ProcesoCCCDepuracionSaldosMenoresAcreedoresDAOiBatis extends BaseDAOiBatis implements ProcesoCCCDepuracionSaldosMenoresAcreedoresDAO {

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ProcesoCCCDepuracionSaldosMenoresAcreedoresDAO#executeDepuracionSaldosMenoresAcreedores(java.util.Map)
	 */
	public void executeDepuracionSaldosMenoresAcreedores(Map criteria) {
		getSqlMapClientTemplate().update(
				"spusicc.cuentacorriente.procesosCCCSQL.executeDepuracionSaldosMenoresAcreedores", criteria);
	}
			
	
}
