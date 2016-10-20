package biz.belcorp.ssicc.dao.spusicc.love.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.love.ProcesoLOVGenerarPuntajeConstanciaDAO;

/**
 * Implementacion del DAO que ejecutara los metodos del proceso que Genera Puntaje por Constancia para el programa LOVE
 * <p>
 * <a href="ProcesoLOVGenerarPuntajeConstanciaDAOIbatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Repository("spusicc.procesoLOVGenerarPuntajeConstanciaDAO")
public class ProcesoLOVGenerarPuntajeConstanciaDAOIbatis extends BaseDAOiBatis implements 
				ProcesoLOVGenerarPuntajeConstanciaDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.love.dao.ProcesoLOVGenerarPuntajeConstanciaDAO#executeGenerarPuntajeConstancia(java.util.Map)
	 */
	public void executeGenerarPuntajeConstancia(Map params) {
		getSqlMapClientTemplate().update("spusicc.love.ProcesosLOVSQL.executeGenerarPuntajeConstancia",params);
		
	}

}
