package biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ibatis;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.MantenimientoCCCCondonacionDeudasCastigadasDAO;

/**
 * @author 
 *
 */
@Repository("spusicc.mantenimientoCCCCondonacionDeudasCastigadasDAO")
public class MantenimientoCCCCondonacionDeudasCastigadasDAOiBatis extends BaseDAOiBatis implements MantenimientoCCCCondonacionDeudasCastigadasDAO {
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.MantenimientoCCCCondonacionDeudasCastigadasDAO#getCondonacionDeudasCastigadasList(java.util.Map)
	 */
	public List  getCondonacionDeudasCastigadasList(Map datos) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.cuentacorriente.procesosCCCSQL.getCondonacionDeudasCastigadasList", datos);
	}
	

								
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.MantenimientoCCCCondonacionDeudasCastigadasDAO#executeCondonacionDeudaCastigo(java.util.Map)
	 */
	public void executeCondonacionDeudaCastigo(Map datos) {
        getSqlMapClientTemplate().update("spusicc.cuentacorriente.procesosCCCSQL.executeCondonacionDeudaCastigo", datos);
	}
}
