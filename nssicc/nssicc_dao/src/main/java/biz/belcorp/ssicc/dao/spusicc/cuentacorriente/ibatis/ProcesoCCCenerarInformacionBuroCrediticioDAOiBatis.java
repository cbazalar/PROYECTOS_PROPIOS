package biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ProcesoCCCenerarInformacionBuroCrediticioDAO;

@Repository("spusicc.procesoCCCenerarInformacionBuroCrediticioDAO")
public class ProcesoCCCenerarInformacionBuroCrediticioDAOiBatis extends	BaseDAOiBatis implements ProcesoCCCenerarInformacionBuroCrediticioDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ProcesoCCCEnvioCargosEFTDAO#executeGenerarInformacionBuroCrediticia(java.util.Map)
	 */
	public void executeGenerarInformacionBuroCrediticia(Map params) {
		getSqlMapClientTemplate().update("spusicc.cuentacorriente.procesosCCCSQL.executeGenerarInformacionBuroCrediticia", params);
	}

	
}
