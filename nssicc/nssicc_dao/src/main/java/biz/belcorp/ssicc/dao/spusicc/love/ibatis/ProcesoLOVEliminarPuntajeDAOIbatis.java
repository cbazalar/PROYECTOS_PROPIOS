package biz.belcorp.ssicc.dao.spusicc.love.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.love.ProcesoLOVEliminarPuntajeDAO;

/**
 * Implementacion del DAO que ejecutara los metodos del proceso que elimina Punaje por incumplir con el numero 
 * maximo de campaas sin pasar pedido
 * <p>
 * <a href="ProcesoLOVEliminarPuntajeDAOIbatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Repository("spusicc.procesoLOVEliminarPuntajeDAO")
public class ProcesoLOVEliminarPuntajeDAOIbatis extends BaseDAOiBatis implements 
				ProcesoLOVEliminarPuntajeDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.love.dao.ProcesoLOVEliminarPuntajeDAO#executeEliminarPuntaje(java.util.Map)
	 */
	public void executeEliminarPuntaje(Map params) {
		getSqlMapClientTemplate().update("spusicc.love.ProcesosLOVSQL.executeEliminarPuntaje",params);
		
	}

}