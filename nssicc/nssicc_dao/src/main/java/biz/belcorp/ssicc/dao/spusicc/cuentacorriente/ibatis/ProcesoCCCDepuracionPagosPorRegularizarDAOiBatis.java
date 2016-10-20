package biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ProcesoCCCDepuracionPagosPorRegularizarDAO;

/**
 * Implementacion del DAO que ejecutara la Depuracion de Saldos Menores Deudoes en Cuenta Corriente 
 * <p>
 * <a href="ProcesoCCCDepuracionPagosPorRegularizarDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:jflorencio@belcorp.biz">Jorge Florencio Arias</a>
 */


/**
 * @author pejflorencio
 *
 */
@Repository("spusicc.procesoCCCDepuracionPagosPorRegularizarDAO")
public class ProcesoCCCDepuracionPagosPorRegularizarDAOiBatis extends BaseDAOiBatis implements ProcesoCCCDepuracionPagosPorRegularizarDAO {

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ProcesoCCCDepuracionPagosPorRegularizarDAO#executeDepuracionPagosPorRegularizar(java.util.Map)
	 */
	public void executeDepuracionPagosPorRegularizar(Map criteria) {
		getSqlMapClientTemplate().update(
				"spusicc.cuentacorriente.procesosCCCSQL.executeDepuracionPagosPorRegularizar", criteria);
	}
			
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ProcesoCCCDepuracionPagosPorRegularizarDAO#executeInsertarPago(java.util.Map)
	 */
	public void executeInsertarPago(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.cuentacorriente.procesosCCCSQL.executeInsertarPago", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ProcesoCCCDepuracionPagosPorRegularizarDAO#executeRevertirPago(java.util.Map)
	 */
	public void executeRevertirPago(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.cuentacorriente.procesosCCCSQL.executeRevertirPago", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ProcesoCCCDepuracionPagosPorRegularizarDAO#executeConsultarPago(java.util.Map)
	 */
	public void executeConsultarPago(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.cuentacorriente.procesosCCCSQL.executeConsultarPago", criteria);
	}
	
}
