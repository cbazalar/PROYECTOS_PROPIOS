package biz.belcorp.ssicc.dao.spusicc.love.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.love.ProcesoLOVGenerarPuntajeComprasDAO;

/**
 * Implementacion del DAO que ejecutara los metodos del proceso que Genera Puntaje por Compras para el programa LOVE
 * <p>
 * <a href="ProcesoLOVGenerarPuntajeComprasDAOIbatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Repository("spusicc.procesoLOVGenerarPuntajeComprasDAO")
public class ProcesoLOVGenerarPuntajeComprasDAOIbatis extends BaseDAOiBatis implements 
				ProcesoLOVGenerarPuntajeComprasDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.love.dao.ProcesoLOVGenerarPuntajeComprasDAO#executeGenerarPuntajeCompras(java.util.Map)
	 */
	public void executeGenerarPuntajeCompras(Map params) {
		getSqlMapClientTemplate().update("spusicc.love.ProcesosLOVSQL.executeGenerarPuntajeCompras",params);
		
	}

}
