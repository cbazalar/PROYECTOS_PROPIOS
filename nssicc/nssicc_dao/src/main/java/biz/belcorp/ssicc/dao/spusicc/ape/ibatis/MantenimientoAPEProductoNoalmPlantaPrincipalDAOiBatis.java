/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.ape.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPEProductoNoalmPlantaPrincipalDAO;
import biz.belcorp.ssicc.dao.spusicc.ape.model.MantenimientoAPEProductoNoalmPlantaPrincipal;

/**
 * @author csoberon
 *
 */
@Repository("spusicc.mantenimientoAPEProductoNoalmPlantaPrincipalDAO")
public class MantenimientoAPEProductoNoalmPlantaPrincipalDAOiBatis extends
		BaseDAOiBatis implements
		MantenimientoAPEProductoNoalmPlantaPrincipalDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEProductoNoalmPlantaPrincipalDAO#getEmpresasExternas()
	 */
	public List getEmpresasExternas() {
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getEmpresasExternas", null);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEProductoNoalmPlantaPrincipalDAO#getProductosNoalmPlantaPrincipalByCriteria(java.util.Map)
	 */
	public List getProductosNoalmPlantaPrincipalByCriteria(Map params) {
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getProductosNoalmPlantaPrincipalByCriteria", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEProductoNoalmPlantaPrincipalDAO#getProductoNoalmPlantaPrincipal(java.lang.String)
	 */
	public MantenimientoAPEProductoNoalmPlantaPrincipal getProductoNoalmPlantaPrincipal(String id) {
		
		MantenimientoAPEProductoNoalmPlantaPrincipal ret = (MantenimientoAPEProductoNoalmPlantaPrincipal)getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getProductoNoalmPlantaPrincipal",id);
		
		if(ret == null)
			throw new ObjectRetrievalFailureException(MantenimientoAPEProductoNoalmPlantaPrincipal.class, id);
		
		return ret; 
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEProductoNoalmPlantaPrincipalDAO#updateProductoNoalmPlantaPrincipal(biz.belcorp.ssicc.spusicc.ape.model.MantenimientoAPEProductoNoalmPlantaPrincipal)
	 */
	public void updateProductoNoalmPlantaPrincipal(
			MantenimientoAPEProductoNoalmPlantaPrincipal producto) {		
		getSqlMapClientTemplate().update("spusicc.ape.MantenimientoAPESQL.updateProductoNoalmPlantaPrincipal", producto);
	}
}
