package biz.belcorp.ssicc.dao.spusicc.ape.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPECopiarMapaCDDAO;

/**
 * @author Nicols Lpez
 *
 */
@Repository("spusicc.mantenimientoAPECopiarMapaCDDAO")
public class MantenimientoAPECopiarMapaCDDAOiBatis extends BaseDAOiBatis implements MantenimientoAPECopiarMapaCDDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPECopiarMapaCDDAO#executeGenerarMapaCDDetalle(java.util.Map)
	 */
	public void executeGenerarMapaCDDetalle(Map criteria) {
		this.getSqlMapClientTemplate().update("spusicc.ape.MantenimientoAPESQL.executeGenerMapaCDDetal", criteria);
	}

}
