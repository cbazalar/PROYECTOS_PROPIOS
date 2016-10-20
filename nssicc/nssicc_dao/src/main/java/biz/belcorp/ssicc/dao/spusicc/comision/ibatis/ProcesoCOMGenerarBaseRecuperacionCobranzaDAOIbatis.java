package biz.belcorp.ssicc.dao.spusicc.comision.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.comision.ProcesoCOMGenerarBaseRecuperacionCobranzaDAO;

/**
 * Implementacion del DAO que ejecutara los metodos del proceso que genera Base de Recuperacion de Cobranza
 *  
 * <p>
 * <a href="ProcesoCOMGenerarBaseRecuperacionCobranzaDAOIbatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Repository("spusicc.procesoCOMGenerarBaseRecuperacionCobranza")
public class ProcesoCOMGenerarBaseRecuperacionCobranzaDAOIbatis extends BaseDAOiBatis implements 
	ProcesoCOMGenerarBaseRecuperacionCobranzaDAO  {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.ProcesoCOMGenerarBaseRecuperacionCobranzaDAO#executeGenerarBaseRecuperacionCobranza(java.util.Map)
	 */
	public void executeGenerarBaseRecuperacionCobranza(Map params) {
		getSqlMapClientTemplate().update("sisicc.ProcesosCOMSQL.executeGenerarBaseRecuperacionCobranza",params);
	}

}