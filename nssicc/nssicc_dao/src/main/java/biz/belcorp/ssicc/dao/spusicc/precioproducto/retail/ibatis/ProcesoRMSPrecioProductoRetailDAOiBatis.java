/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.precioproducto.retail.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.precioproducto.retail.ProcesoRMSPrecioProductoRetailDAO;

/**
 * @author Richar
 * @date   28/12/2015
 */
@Repository("spusicc.procesoRMSPrecioProductoRetailDAO")
public class ProcesoRMSPrecioProductoRetailDAOiBatis extends BaseDAOiBatis 
	implements ProcesoRMSPrecioProductoRetailDAO{

	
	@Override
	public List getPrecioProductoRetail(Map map) {
				
		return this.getSqlMapClientTemplate().queryForList("spusicc.comision.retail.procesoRETSQL.getPrecioProductoRetail", map);
		
	}

	
	
	
}
