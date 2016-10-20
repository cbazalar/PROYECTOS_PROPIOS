package biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ibatis;


import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ProcesoCCCGenerarInformacionDataCreditoDAO;

/**
 * Implementacion del DAO que ejecutara la Generar Consultora Cupon
 * <p>
 * <a href="ProcesoCCCGenerarInformacionDataCreditoDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:jflorencio@belcorp.biz">Jorge Florencio Arias </a>
 */
@Repository("spusicc.procesoCCCGenerarInformacionDataCreditoDAO")
public class ProcesoCCCGenerarInformacionDataCreditoDAOiBatis extends BaseDAOiBatis implements ProcesoCCCGenerarInformacionDataCreditoDAO {



	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ProcesoCCCGenerarInformacionDataCreditoDAO#executeGenerarInformacionDataCredito(java.util.Map)
	 */
	public void executeGenerarInformacionDataCredito(Map criteria) {
		getSqlMapClientTemplate().update(
				"spusicc.cuentacorriente.procesosCCCSQL.executeGenerarInformacionDataCredito", criteria);
	}
	
	
}
