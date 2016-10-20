package biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ibatis;


import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ProcesoCCCGenerarInformacionSAPFIDAO;

/**
 * Implementacion del DAO que ejecutara la Generacion de la informacin para SAPFI
 * <p>
 * <a href="ProcesoCCCGenerarInformacionSAPFIDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:jflorencio@belcorp.biz">Jorge Florencio Arias </a>
 */
@Repository("spusicc.procesoCCCGenerarInformacionSAPFIDAO")
public class ProcesoCCCGenerarInformacionSAPFIDAOiBatis extends BaseDAOiBatis implements ProcesoCCCGenerarInformacionSAPFIDAO {



	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ProcesoCCCGenerarInformacionSAPFIDAO#executeGenerarInformacionSAPFI(java.util.Map)
	 */
	public void executeGenerarInformacionSAPFI(Map criteria) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazCCCSQL.executeInterfazCCCGenerarInformacionSAPFI", criteria);
	}
	
	
}
