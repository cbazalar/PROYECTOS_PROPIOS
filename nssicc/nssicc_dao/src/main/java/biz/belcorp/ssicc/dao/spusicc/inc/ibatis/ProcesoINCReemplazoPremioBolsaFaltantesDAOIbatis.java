package biz.belcorp.ssicc.dao.spusicc.inc.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCReemplazoPremioBolsaFaltantesDAO;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
@Repository("spusicc.procesoINCReemplazoPremioBolsaFaltantesDAO")
public class ProcesoINCReemplazoPremioBolsaFaltantesDAOIbatis extends BaseDAOiBatis implements
				ProcesoINCReemplazoPremioBolsaFaltantesDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCReemplazoPremioBolsaFaltantesDAO#getListConcursosFaltantes()
	 */
	public List getListConcursosFaltantes() {
		return getSqlMapClientTemplate().queryForList("spusicc.incentivos.ProcesoINCSQL.getListConcursosFaltantes");	
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCReemplazoPremioBolsaFaltantesDAO#getListPremiosFaltantes(java.lang.String)
	 */
	public List getListPremiosFaltantes(String numeroConcurso) {
		return getSqlMapClientTemplate().queryForList("spusicc.incentivos.ProcesoINCSQL.getListPremiosFaltantes", numeroConcurso);	
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCReemplazoPremioBolsaFaltantesDAO#getListPremiosReemplazos(java.lang.String)
	 */
	public List getListPremiosReemplazos(String numeroConcurso) {
		return getSqlMapClientTemplate().queryForList("spusicc.incentivos.ProcesoINCSQL.getListPremiosReemplazos", numeroConcurso);	
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCReemplazoPremioBolsaFaltantesDAO#executeReemplazoPremioBolsaFaltantes(java.util.Map)
	 */
	public void executeReemplazoPremioBolsaFaltantes(Map params) {
		getSqlMapClientTemplate().update(
				"spusicc.incentivos.ProcesoINCSQL.executeReemplazoPremioBolsaFaltantes", params);
	}
	
}
