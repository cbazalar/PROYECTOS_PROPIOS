package biz.belcorp.ssicc.dao.spusicc.inc.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCCalculaPuntajeRetailDAO;

/**
 * Implementacion del DAO que ejecutara los metodos del proceso de Calcula Puntaje Retail
 * 
 * <p>
 * <a href="ProcesoINCCalculaPuntajeRetailDAOIbatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Repository("spusicc.procesoINCCalculaPuntajeRetailDAO")
public class ProcesoINCCalculaPuntajeRetailDAOIbatis extends BaseDAOiBatis implements 
				ProcesoINCCalculaPuntajeRetailDAO  {
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCCalculaPuntajeRetailDAO#executeCalculaPuntajeRetail(java.util.Map)
	 */
	public void executeCalculaPuntajeRetail(Map params) {
		getSqlMapClientTemplate().update("spusicc.incentivos.ProcesoINCSQL.executeCalculaPuntajeRetail",params);
	}

}

