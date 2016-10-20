package biz.belcorp.ssicc.dao.scsicc.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.scsicc.ProcesoSICGenerarPedidosDigitadosZonaDAO;
/**
 * @author <a href="">Jose Luis Rodriguez</a>
 *
 */
@Repository("scsicc.procesoSICGenerarPedidosDigitadosZonaDAO")
public class ProcesoSICGenerarPedidosDigitadosZonaDAOIbatis extends BaseDAOiBatis implements
					ProcesoSICGenerarPedidosDigitadosZonaDAO{

	
	/**
	 * @param criteria
	 * @return
	 */
	public String getCorreoGerenteRegion(Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("sisicc.ReportesSQL.getCorreoGerenteRegion",criteria); 
	}
	
	/**
	 * @param criteria
	 * @return
	 */
	public String getCorreoGerenteZona(Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("sisicc.ReportesSQL.getCorreoGerenteZona",criteria); 
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.dao.ProcesoSICGenerarPedidosDigitadosZonaDAO#getCorreoGerenteRegionByCodigoZona(java.util.Map)
	 */
	public String getCorreoGerenteRegionByCodigoZona(Map map) {
		return (String) getSqlMapClientTemplate().queryForObject("sisicc.ReportesSQL.getCorreoGerenteRegionByCodigoZona",map);		
	}
	
}
