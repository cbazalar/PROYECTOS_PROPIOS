package biz.belcorp.ssicc.dao.spusicc.inc.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCDeterminarGanadorasRecomendacionDAO;

/**
 * Implementacion del DAO que ejecutara los metodos del proceso de Determinar Ganadoras Recomendacion
 *  
 * <p>
 * <a href="ProcesoINCDeterminarGanadorasRecomendacionDAOIbatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Repository("spusicc.procesoINCDeterminarGanadorasRecomendacionDAO")
public class ProcesoINCDeterminarGanadorasRecomendacionDAOIbatis extends BaseDAOiBatis implements 
				ProcesoINCDeterminarGanadorasRecomendacionDAO  {
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCDeterminarGanadorasRecomendacionDAO#executeDeterminarGanadorasRecomendacion(java.util.Map)
	 */
	public void executeDeterminarGanadorasRecomendacion(Map params) {
		getSqlMapClientTemplate().update("spusicc.incentivos.ProcesoINCSQL.executeDeterminarGanadorasRecomendacion",params);
	}

}