/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ibatis;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ProcesoCCCCargarLotesBancariosDAO;

/**
 * @author Jorge Florencio Arias
 *
 */
@Repository("spusicc.procesoCCCCargarLotesBancariosDAO")
public class ProcesoCCCCargarLotesBancariosDAOiBatis extends BaseDAOiBatis implements ProcesoCCCCargarLotesBancariosDAO {
	
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ProcesoCCCCargarLotesBancariosDAO#executeCargarLotesBancarios(java.util.Map)
	 */
	public void executeCargarLotesBancarios(Map criteria){
		getSqlMapClientTemplate().update("spusicc.cuentacorriente.procesosCCCSQL.executeCargarLotesBancarios", criteria);
	}
	
	
	public List getEnvioMailsConfirmacionCargaLotesBancariosParams(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.cuentacorriente.procesosCCCSQL.getEnvioMailsConfirmacionCargaLotesBancariosParams",criteria);
	}
	
	
	public List getLotesCargados(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.cuentacorriente.procesosCCCSQL.getLotesCargados",criteria);
	}
}
