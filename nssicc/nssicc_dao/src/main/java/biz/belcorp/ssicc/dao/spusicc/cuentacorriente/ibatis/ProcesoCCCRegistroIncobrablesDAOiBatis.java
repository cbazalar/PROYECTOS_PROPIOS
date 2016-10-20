package biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ProcesoCCCRegistroIncobrablesDAO;

/**
 * <p>
 * <a href="ProcesoCCCRegistroIncobrablesDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:aoviedo@sigcomt.com">Aurelio Oviedo</a>
 *  
 */
@Repository("spusicc.procesoCCCRegistroIncobrablesDAO")
public class ProcesoCCCRegistroIncobrablesDAOiBatis extends BaseDAOiBatis implements ProcesoCCCRegistroIncobrablesDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ProcesoCCCRegistroIncobrablesDAO#executeRegistroIncobrables(java.util.Map)
	 */
	public void executeRegistroIncobrables(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.cuentacorriente.procesosCCCSQL.executeRegistroIncobrables", criteria);
	}
}