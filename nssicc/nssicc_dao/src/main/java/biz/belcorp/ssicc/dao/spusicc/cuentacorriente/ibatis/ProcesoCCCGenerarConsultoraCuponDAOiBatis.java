package biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ibatis;


import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ProcesoCCCGenerarConsultoraCuponDAO;

/**
 * Implementacion del DAO que ejecutara la Generar Consultora Cupon
 * <p>
 * <a href="ProcesoCCCGenerarConsultoraCuponDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:jflorencio@belcorp.biz">Jorge Florencio Arias </a>
 */
@Repository("spusicc.procesoCCCGenerarConsultoraCuponDAO")
public class ProcesoCCCGenerarConsultoraCuponDAOiBatis extends BaseDAOiBatis implements ProcesoCCCGenerarConsultoraCuponDAO {



	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ProcesoCCCGenerarConsultoraCuponDAO#executeGenerarConsultoraCupon(java.util.Map)
	 */
	public void executeGenerarConsultoraCupon(Map criteria) {
		getSqlMapClientTemplate().update(
				"spusicc.cuentacorriente.procesosCCCSQL.executeGenerarConsultoraCupon", criteria);
	}
	
	
}
