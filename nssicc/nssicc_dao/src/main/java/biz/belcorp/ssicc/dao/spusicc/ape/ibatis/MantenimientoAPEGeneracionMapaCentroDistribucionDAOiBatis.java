package biz.belcorp.ssicc.dao.spusicc.ape.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPEGeneracionMapaCentroDistribucionDAO;

/**
 * @author Jose Luis Rodriguez
 *
 */
@Repository("spusicc.mantenimientoAPEGeneracionMapaCentroDistribucionDAO")
public class MantenimientoAPEGeneracionMapaCentroDistribucionDAOiBatis extends BaseDAOiBatis implements MantenimientoAPEGeneracionMapaCentroDistribucionDAO{

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEGeneracionMapaCentroDistribucionDAO#getNextCodigoMapaCentroDistribucion()
	 */
	public String getNextCodigoMapaCentroDistribucion(){
		return(String)getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getNextCodigoMapaCentroDistribucion");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPEGeneracionMapaCentroDistribucionDAO#executeGenerarMapaCentroDistribucion(java.util.Map)
	 */
	public void executeGenerarMapaCentroDistribucion(Map criteria){
		getSqlMapClientTemplate().update("spusicc.ape.MantenimientoAPESQL.executeGenerarMapaCentroDistribucion", criteria);
	}
	
}