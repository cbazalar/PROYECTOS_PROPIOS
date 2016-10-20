package biz.belcorp.ssicc.dao.spusicc.comision.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.comision.ProcesoCOMCalculoCalificacionTramoDAO;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza </a>
 *
 */
@Repository("spusicc.procesoCOMCalculoCalificacionTramoDAO")
public class ProcesoCOMCalculoCalificacionTramoDAOiBatis extends BaseDAOiBatis
	implements ProcesoCOMCalculoCalificacionTramoDAO {
		
	public List getTiposComisionistas(String codigoPais) {
		List tipos = getSqlMapClientTemplate().queryForList(
				"sisicc.ProcesosCOMSQL.getTiposComisionistas", codigoPais);
		return tipos;			
	}
	
	public List getTramos(String codigoPais) {
		List tipos = getSqlMapClientTemplate().queryForList("sisicc.ProcesosCOMSQL.getTramos", codigoPais);
		return tipos;			
	}

	public void executeCalculoCalificacionTramo(Map criteria) {
        getSqlMapClientTemplate().update("sisicc.ProcesosCOMSQL.executeCalculoCalificacionTramo", criteria);
	}

	public List getTipoCalculoList(String codigoPais) {
		return (List)getSqlMapClientTemplate().queryForList("sisicc.ProcesosCOMSQL.getTipoCalculoList", codigoPais);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.ProcesoCOMCalculoCalificacionTramoDAO#getCampanasRango(java.util.Map)
	 */
	public List getCampanasRango(Map criteria){
		return (List)getSqlMapClientTemplate().queryForList("sisicc.ProcesosCOMSQL.getCampanasRango", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSiCCDAO#getComisionByTipo(java.util.Map)
	 */
	public List getComisionByTipo(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ProcesosCOMSQL.getComisionByTipo",criteria);
	}
	
}
