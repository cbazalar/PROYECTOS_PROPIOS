package biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ibatis;


import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ProcesoCCCReprocesarInformacionSAPFIDAO;

/**
 * Implementacion del DAO que ejecutara el Reproceso de la informacin para SAPFI
 * <p>
 * <a href="ProcesoCCCReprocesarInformacionSAPFIDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:jflorencio@belcorp.biz">Jorge Florencio Arias </a>
 */
@Repository("spusicc.procesoCCCReprocesarInformacionSAPFIDAO")
public class ProcesoCCCReprocesarInformacionSAPFIDAOiBatis extends BaseDAOiBatis implements ProcesoCCCReprocesarInformacionSAPFIDAO {



	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ProcesoCCCReprocesarInformacionSAPFIDAO#executeReprocesarInformacionSAPFI(java.util.Map)
	 */
	public void executeReprocesarInformacionSAPFI(Map criteria) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazCCCSQL.executeInterfazCCCReprocesarInformacionSAPFI", criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ProcesoCCCReprocesarInformacionSAPFIDAO#executeCierreFacturacion(java.util.Map)
	 */
	public void executeCierreFacturacion (Map criteria) {
		getSqlMapClientTemplate().update("spusicc.cuentacorriente.procesosCCCSQL.executeCierreFacturacionSAPFI", criteria);
	}
	
}
