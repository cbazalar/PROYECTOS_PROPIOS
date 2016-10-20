package biz.belcorp.ssicc.dao.spusicc.love.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.love.ProcesoLOVEliminarPremiosElegidosNoAtendidosDAO;

/**
 * Implementacion del DAO que ejecutara los metodos del proceso de Eliminar Premios Elegidos no Atendidos para el programa LOVE
 * <p>
 * <a href="ProcesoLOVEliminarPremiosElegidosNoAtendidosDAOIbatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Repository("spusicc.procesoLOVEliminarPremiosElegidosNoAtendidosDAO")
public class ProcesoLOVEliminarPremiosElegidosNoAtendidosDAOIbatis extends BaseDAOiBatis implements 
				ProcesoLOVEliminarPremiosElegidosNoAtendidosDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.love.dao.ProcesoLOVEliminarPremiosElegidosNoAtendidosDAO#executeEliminarPremiosElegidosNoAtendidos(java.util.Map)
	 */
	public void executeEliminarPremiosElegidosNoAtendidos(Map params) {
		getSqlMapClientTemplate().update("spusicc.love.ProcesosLOVSQL.executeEliminarPremiosElegidosNoAtendidos",params);
		
	}

}