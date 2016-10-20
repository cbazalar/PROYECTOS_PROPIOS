package biz.belcorp.ssicc.dao.soa.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.soa.CronogramaDAO;

@Repository("soa.cronogramaDAO")
public class CronogramaDAOiBatis extends BaseDAOiBatis  implements CronogramaDAO  {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.soa.dao.CronogramaDAO#getCronograma(java.util.Map)
	 */
	public List getCronograma(Map criteria) {
		List result= null;
		String rol = (String)criteria.get("rol");
		log.debug("rol "+rol);
		if (Constants.SOA_GERENTE_ZONA.equals(rol)){
			result = (List)getSqlMapClientTemplate().queryForList(
											"soa.CronogramaSQL.getCronogramaGZ", criteria); 
		}

		if (Constants.SOA_GERENTE_REGION.equals(rol)){
			result = (List)getSqlMapClientTemplate().queryForList(
											"soa.CronogramaSQL.getCronogramaGR", criteria); 
		}
		
		if (Constants.SOA_DIRECTOR_VENTAS.equals(rol)){
			result = (List)getSqlMapClientTemplate().queryForList(
											"soa.CronogramaSQL.getCronogramaDV", criteria); 
		}
		
		return result;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.soa.dao.CronogramaDAO#getDiasFaltantesCierreFacturacion(java.util.Map)
	 */
	public Integer getDiasFaltantesCierreFacturacion(Map criteria) {
		Integer result= 0;
		String rol = (String)criteria.get("rol");
		log.debug("rol "+rol);
		if (Constants.SOA_GERENTE_ZONA.equals(rol)){
			result = (Integer)getSqlMapClientTemplate().queryForObject(
					"soa.CronogramaSQL.getDiasFaltantesCierreFacturacion", criteria);
		}

		if (Constants.SOA_GERENTE_REGION.equals(rol)){
			result = (Integer)getSqlMapClientTemplate().queryForObject(
					"soa.CronogramaSQL.getDiasFaltantesCierreFacturacionGR", criteria);
		}
		
		if (Constants.SOA_DIRECTOR_VENTAS.equals(rol)){
			result = (Integer)getSqlMapClientTemplate().queryForObject(
					"soa.CronogramaSQL.getDiasFaltantesCierreFacturacionDV", criteria); 
		}
		
		return result;		
		
		 
	}
}
