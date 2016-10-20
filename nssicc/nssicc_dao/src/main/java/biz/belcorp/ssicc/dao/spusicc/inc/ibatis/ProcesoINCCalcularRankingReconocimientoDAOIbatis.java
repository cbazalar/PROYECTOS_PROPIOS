package biz.belcorp.ssicc.dao.spusicc.inc.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCCalcularRankingReconocimientoDAO;

/**
 * Implementacion del DAO que ejecutara los metodos del proceso que Calcula Ranking 
 * para los concursos de Reconocimiento. 
 * 
 * <p>
 * <a href="ProcesoINCCalcularRankingReconocimientoDAOIbatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Repository("spusicc.procesoINCCalcularRankingReconocimientoDAO")
public class ProcesoINCCalcularRankingReconocimientoDAOIbatis extends BaseDAOiBatis implements 
				ProcesoINCCalcularRankingReconocimientoDAO  {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCCalcularRankingReconocimientoDAO#executeCalcularRankingReconocimiento(java.util.Map)
	 */
	public void executeCalcularRankingReconocimiento(Map params) {
		getSqlMapClientTemplate().update("spusicc.incentivos.ProcesoINCSQL.executeCalcularRankingReconocimiento",params);
		
	}

}
