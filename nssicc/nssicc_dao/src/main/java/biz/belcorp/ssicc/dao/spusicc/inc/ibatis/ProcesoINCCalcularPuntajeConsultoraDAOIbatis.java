package biz.belcorp.ssicc.dao.spusicc.inc.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCCalcularPuntajeConsultoraDAO;

/**
 * Implementacion del DAO que ejecutara los metodos del proceso de Calcular Puntaje Consultoras
 *  
 * <p>
 * <a href="ProcesoINCCalcularPuntajeConsultoraDAOIbatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Repository("spusicc.procesoINCCalcularPuntajeConsultoraDAO")
public class ProcesoINCCalcularPuntajeConsultoraDAOIbatis extends BaseDAOiBatis implements 
				ProcesoINCCalcularPuntajeConsultoraDAO  {
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCCalcularPuntajeConsultoraDAO#executeCalcularPuntajeConsultora(java.util.Map)
	 */
	public void executeCalcularPuntajeConsultora(Map params) {
		getSqlMapClientTemplate().update("spusicc.incentivos.ProcesoINCSQL.executeCalcularPuntajeConsultora",params);
	}

}