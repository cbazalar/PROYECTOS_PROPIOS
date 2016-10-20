package biz.belcorp.ssicc.dao.spusicc.inc.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCGenerarReporteIncentivosDAO;

/**
 * @author <a href="mailto:sbuchelli@csigcomt.com">Sergio Buchelli</a>
 *
 */
@Repository("spusicc.procesoINCGenerarReporteIncentivosDAO")
public class ProcesoINCGenerarReporteIncentivosDAOIbatis extends BaseDAOiBatis implements
				ProcesoINCGenerarReporteIncentivosDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCGenerarReporteIncentivosDAO#executeGenerarReporteIncentivos(java.util.Map)
	 */
	public void executeGenerarReporteIncentivos(Map map) {
		getSqlMapClientTemplate()
		.update(
				"spusicc.incentivos.ProcesoINCSQL.executeGenerarReporteIncentivos",
				map);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCGenerarReporteIncentivosDAO#getEstadoPremioDespacho(java.util.Map)
	 */
	public Integer getSizeEstadoPremioDespacho(Map map) {		
		return (Integer)getSqlMapClientTemplate()
		  .queryForObject("spusicc.incentivos.ProcesoINCSQL.getSizeEstadoPremioDespacho", 
				  		 map);
	}

	
}
