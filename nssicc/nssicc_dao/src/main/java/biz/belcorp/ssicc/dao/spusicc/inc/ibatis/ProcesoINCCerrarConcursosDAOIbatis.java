package biz.belcorp.ssicc.dao.spusicc.inc.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCCerrarConcursosDAO;

/**
 * Implementacion del DAO que ejecutara los metodos del proceso que Cierran  
 * concursos. 
 * 
 * <p>
 * <a href="ProcesoINCCerrarConcursosDAOIbatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="">Jose Luis Rodriguez</a>
 */
@Repository("spusicc.procesoINCCerrarConcursosDAO")
public class ProcesoINCCerrarConcursosDAOIbatis extends BaseDAOiBatis implements ProcesoINCCerrarConcursosDAO  {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCCerrarConcursosDAO#executeCerrarConcursos(java.util.Map)
	 */
	public void executeCerrarConcursos(Map params) {
		getSqlMapClientTemplate().update("spusicc.incentivos.ProcesoINCSQL.executeCerrarConcursos",params);	
	}

}
