/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.ventadevolucion.retail.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.ventadevolucion.retail.ProcesoRMSVentaDevolucionRetailDAO;

/**
 * @author Richar Cruzado
 * @date   30/12/2015
 */
@Repository("spusicc.procesoRMSVentaDevolucionRetailDAO")
public class ProcesoRMSVentaDevolucionRetailDAOiBatis extends BaseDAOiBatis 
	implements ProcesoRMSVentaDevolucionRetailDAO{

	@Override
	public void insertaVentaDevolucion(Map criteria) {
		
		getSqlMapClientTemplate().update("spusicc.comision.retail.procesoRETSQL.insertaVentaDevolucion", criteria);
	
	}

	
}
