package biz.belcorp.ssicc.dao.spusicc.inc.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCCargaOrdenesRetailDAO;

/**
 * Implementacion del DAO que ejecutara los metodos del proceso de Carga Ordenes Retail
 * 
 * <p>
 * <a href="ProcesoINCCargaOrdenesRetailDAOIbatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Repository("spusicc.procesoINCCargaOrdenesRetailDAO")
public class ProcesoINCCargaOrdenesRetailDAOIbatis extends BaseDAOiBatis implements 
				ProcesoINCCargaOrdenesRetailDAO  {
	
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCCargaOrdenesRetailDAO#existeCargaOrdenesRetailProcesado(java.util.Map)
     */
    public boolean existeCargaOrdenesRetailProcesado(Map criteria) {
        String result = (String) getSqlMapClientTemplate().queryForObject(
        							"spusicc.incentivos.ProcesoINCSQL.getCargaOrdenesRetailProcesado", criteria);
        
        if(Integer.parseInt(result)>0)
        	return true;
        else
        	return false;
    }	
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCCargaOrdenesRetailDAO#executeCargaOrdenesRetail(java.util.Map)
	 */
	public void executeCargaOrdenesRetail(Map params) {
		getSqlMapClientTemplate().update("spusicc.incentivos.ProcesoINCSQL.executeCargaOrdenesRetail",params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCCargaOrdenesRetailDAO#getCargasEjecutadasECM(java.util.Map)
	 */
	public List getCargasEjecutadasECM(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.incentivos.ProcesoINCSQL.getCargasEjecutadasECM", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCCargaOrdenesRetailDAO#executeAnularCargaOrdenesRetail(java.util.Map)
	 */
	public void executeAnularCargaOrdenesRetail(Map params) {
		getSqlMapClientTemplate().update("spusicc.incentivos.ProcesoINCSQL.executeAnularCargaOrdenesRetail",params);		
	}
}

