package biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ProcesoCCCDepuracionDeudasIncobrablesDAO;

/**
 * Implementacion del DAO que ejecutara la Depuracion de Deudas Incobrables 
 * <p>
 * <a href="ProcesoCCCDepuracionDeudasIncobrablesDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:jflorencio@belcorp.biz">Jorge Florencio Arias</a>
 */


/**
 * @author pejflorencio
 *
 */
@Repository("spusicc.procesoCCCDepuracionDeudasIncobrablesDAO")
public class ProcesoCCCDepuracionDeudasIncobrablesDAOiBatis extends BaseDAOiBatis implements ProcesoCCCDepuracionDeudasIncobrablesDAO {

		
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ProcesoCCCDepuracionDeudasIncobrablesDAO#executeDepuracionDeudasIncobrables(java.util.Map)
	 */
	public void executeDepuracionDeudasIncobrables(Map criteria) {
		getSqlMapClientTemplate().update(
				"spusicc.cuentacorriente.procesosCCCSQL.executeDepuracionDeudasIncobrables", criteria);
	}
			
	
}
