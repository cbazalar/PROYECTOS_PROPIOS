package biz.belcorp.ssicc.dao.spusicc.pre.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.pre.ProcesoPRERenombrarMatrizFacturacionDAO;

/**
 * @author <a href="mailto:aoviedo@sigcomt.com">Aurelio Oviedo</a>
 */
@Repository("spusicc.procesoPRERenombrarMatrizFacturacionDAO")
public class ProcesoPRERenombrarMatrizFacturacionDAOIbatis extends BaseDAOiBatis implements ProcesoPRERenombrarMatrizFacturacionDAO {
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.pre.ProcesoPRERenombrarMatrizFacturacionDAO#getOidCabeceraCampanaDestino(java.lang.String)
	 */
	@Override
	public String getOidCabeceraCampanaDestino(String codigoPeriodo) {
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoPRESQL.getOidCabeceraCampanaDestino", codigoPeriodo);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.pre.ProcesoPRERenombrarMatrizFacturacionDAO#getExisteOfertaEnMatrizFacturacionDestino(java.lang.String)
	 */
	@Override
	public int getExisteOfertaEnMatrizFacturacionDestino(String oidCabecera) {
		return (Integer) getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoPRESQL.getExisteOfertaEnMatrizFacturacionDestino", oidCabecera);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.pre.ProcesoPRERenombrarMatrizFacturacionDAO#executeRenombrarMatrizFacturacion(java.util.Map)
	 */
	@Override
	public void executeRenombrarMatrizFacturacion(Map params) {
		getSqlMapClientTemplate().update("spusicc.MantenimientoPRESQL.executeRenombrarMatrizFacturacion", params);
	}
}