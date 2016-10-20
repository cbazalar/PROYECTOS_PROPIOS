package biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ibatis;


import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ProcesoCCCBloqueoFinancieroPorDiasDeAtrasoDAO;

/**
 * Implementacion del DAO que ejecutara la Generar Consultora Cupon
 * <p>
 * <a href="ProcesoCCCBloqueoFinancieroPorDiasDeAtrasoDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:jflorencio@belcorp.biz">Jorge Florencio Arias </a>
 */
@Repository("spusicc.procesoCCCBloqueoFinancieroPorDiasDeAtrasoDAO")
public class ProcesoCCCBloqueoFinancieroPorDiasDeAtrasoDAOiBatis extends BaseDAOiBatis implements ProcesoCCCBloqueoFinancieroPorDiasDeAtrasoDAO {



	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ProcesoCCCBloqueoFinancieroPorDiasDeAtrasoDAO#executeBloqueoFinancieroPorDiasDeAtraso(java.util.Map)
	 */
	public void executeBloqueoFinancieroPorDiasDeAtraso(Map criteria) {
		getSqlMapClientTemplate().update(
				"spusicc.cuentacorriente.procesosCCCSQL.executeBloqueoFinancieroPorDiasDeAtraso", criteria);
	}
	
	
}
