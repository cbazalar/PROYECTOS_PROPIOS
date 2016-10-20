package biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.MantenimientoCCCGastosAdministrativosDAO;

/**
* @author <a href="mailto:jflorencio@belcorp.biz">Jorge FLorencio</a>
 *
 */
@Repository("spusicc.mantenimientoCCCGastosAdministrativosDAO")
public class MantenimientoCCCGastosAdministrativosDAOiBatis extends	BaseDAOiBatis implements MantenimientoCCCGastosAdministrativosDAO {

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.MantenimientoCCCGastosAdministrativosDAO#getGastosAdministrativosList(java.util.Map)
	 */
	public List getGastosAdministrativosList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.cuentacorriente.procesosCCCSQL.getGastosAdministrativosList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.MantenimientoCCCGastosAdministrativosDAO#insertCCCGastosAdministrativos(java.util.Map)
	 */
	public void insertCCCGastosAdministrativos(Map criteria) {
		getSqlMapClientTemplate().insert("spusicc.cuentacorriente.procesosCCCSQL.insertCCCGastosAdministrativos", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.MantenimientoCCCGastosAdministrativosDAO#deleteLogicoGastosAdministrativos(java.util.Map)
	 */
	public void deleteLogicoGastosAdministrativos(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.cuentacorriente.procesosCCCSQL.deleteLogicoGastosAdministrativos", criteria);
	}


}
