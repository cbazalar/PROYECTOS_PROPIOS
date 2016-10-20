package biz.belcorp.ssicc.dao.spusicc.inc.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCDeterminarGanadorasRecomendacionZonaDAO;

/**
 * Implementacion del DAO que ejecutara los metodos del proceso de Determinar Ganadoras Recomendacion en Cierre de Zona
 *  
 * <p>
 * <a href="ProcesoINCDeterminarGanadorasRecomendacionZonaDAOIbatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Repository("spusicc.procesoINCDeterminarGanadorasRecomendacionZonaDAO")
public class ProcesoINCDeterminarGanadorasRecomendacionZonaDAOIbatis extends BaseDAOiBatis implements 
				ProcesoINCDeterminarGanadorasRecomendacionZonaDAO  {
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCDeterminarGanadorasRecomendacionDAO#executeDeterminarGanadorasRecomendacionZona(java.util.Map)
	 */
	public void executeDeterminarGanadorasRecomendacionZona(Map params) {
		getSqlMapClientTemplate().update("spusicc.incentivos.ProcesoINCSQL.executeDeterminarGanadorasRecomendacionZona",params);
	}

}