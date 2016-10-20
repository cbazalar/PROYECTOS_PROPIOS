package biz.belcorp.ssicc.dao.spusicc.inc.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCDeterminarGanadorasRecomendacionPeriodoDAO;

/**
 * Implementacion del DAO que ejecutara los metodos del proceso de Determinar Ganadoras Recomendacion en Cierre de Periodo
 *  
 * <p>
 * <a href="ProcesoINCDeterminarGanadorasRecomendacionPeriodoDAOIbatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Repository("spusicc.procesoINCDeterminarGanadorasRecomendacionPeriodoDAO")
public class ProcesoINCDeterminarGanadorasRecomendacionPeriodoDAOIbatis extends BaseDAOiBatis implements 
				ProcesoINCDeterminarGanadorasRecomendacionPeriodoDAO  {
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCDeterminarGanadorasRecomendacionDAO#executeDeterminarGanadorasRecomendacionPeriodo(java.util.Map)
	 */
	public void executeDeterminarGanadorasRecomendacionPeriodo(Map params) {
		getSqlMapClientTemplate().update("spusicc.incentivos.ProcesoINCSQL.executeDeterminarGanadorasRecomendacionPeriodo",params);
	}

}