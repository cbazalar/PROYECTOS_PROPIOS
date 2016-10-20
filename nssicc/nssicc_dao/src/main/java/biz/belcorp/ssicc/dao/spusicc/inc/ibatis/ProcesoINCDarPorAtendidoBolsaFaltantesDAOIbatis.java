package biz.belcorp.ssicc.dao.spusicc.inc.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCDarPorAtendidoBolsaFaltantesDAO;
/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
@Repository("spusicc.procesoINCDarPorAtendidoBolsaFaltantesDAO")
public class ProcesoINCDarPorAtendidoBolsaFaltantesDAOIbatis extends BaseDAOiBatis implements
					ProcesoINCDarPorAtendidoBolsaFaltantesDAO {


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCDarPorAtendidoBolsaFaltantesDAO#getListConcursoVigentes()
	 */
	public List getListConcursoVigentes() {
		return getSqlMapClientTemplate().queryForList("spusicc.incentivos.ProcesoINCSQL.getListConcursoVigentes");	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCDarPorAtendidoBolsaFaltantesDAO#executeActualizarBolsaFaltantes(java.util.Map)
	 */
	public void executeActualizarBolsaFaltantes(Map params) {
		getSqlMapClientTemplate().update(
				"spusicc.incentivos.ProcesoINCSQL.executeActualizarBolsaFaltantes", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCDarPorAtendidoBolsaFaltantesDAO#insertBolsaAtendido(java.util.Map)
	 */
	public void insertBolsaAtendido(Map params) {
		getSqlMapClientTemplate().insert(
				"spusicc.incentivos.ProcesoINCSQL.insertBolsaAtendido", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCDarPorAtendidoBolsaFaltantesDAO#getNumeroLote()
	 */
	public String getNumeroLote() {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.incentivos.ProcesoINCSQL.getNumeroLoteBolsaAtendidos");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCDarPorAtendidoBolsaFaltantesDAO#getPeriodosProductosNoAtendidos(java.util.Map)
	 */
	public List getPeriodosProductosNoAtendidos(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.incentivos.ProcesoINCSQL.getPeriodosProductosNoAtendidos", criteria);	
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCDarPorAtendidoBolsaFaltantesDAO#getProductosNoAtendidos(java.util.Map)
	 */
	public List getProductosNoAtendidos(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.incentivos.ProcesoINCSQL.getProductosNoAtendidos", criteria);	
	}
	
}
