package biz.belcorp.ssicc.dao.spusicc.inc.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCAplicarRequisitosPremiacionDAO;

/**
 * Implementacion del DAO que ejecutara los metodos del proceso de Aplicar Requisitos Premiacion
 *  
 * <p>
 * <a href="ProcesoINCAplicarRequisitosPremiacionDAOIbatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Repository("spusicc.procesoINCAplicarRequisitosPremiacionDAO")
public class ProcesoINCAplicarRequisitosPremiacionDAOIbatis extends BaseDAOiBatis implements 
				ProcesoINCAplicarRequisitosPremiacionDAO  {
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCAplicarRequisitosPremiacionDAO#executeAplicarRequisitosPremiacion(java.util.Map)
	 */
	public void executeAplicarRequisitosPremiacion(Map params) {
		getSqlMapClientTemplate().update("spusicc.incentivos.ProcesoINCSQL.executeAplicarRequisitosPremiacion",params);
	}

}