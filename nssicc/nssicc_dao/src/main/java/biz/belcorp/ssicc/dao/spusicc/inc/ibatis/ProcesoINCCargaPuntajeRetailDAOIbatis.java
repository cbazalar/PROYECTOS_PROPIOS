package biz.belcorp.ssicc.dao.spusicc.inc.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCCargaPuntajeRetailDAO;

/**
 * Implementacion del DAO que ejecutara los metodos del proceso de Carga Puntaje Retail
 * 
 * <p>
 * <a href="ProcesoINCCargaPuntajeRetailDAOIbatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Repository("spusicc.procesoINCCargaPuntajeRetailDAO")
public class ProcesoINCCargaPuntajeRetailDAOIbatis extends BaseDAOiBatis implements 
				ProcesoINCCargaPuntajeRetailDAO  {
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCCargaPuntajeRetailDAO#executeCargaPuntajeRetail(java.util.Map)
	 */
	public void executeCargaPuntajeRetail(Map params) {
		getSqlMapClientTemplate().update("spusicc.incentivos.ProcesoINCSQL.executeCargaPuntajeRetail",params);
	}

}

