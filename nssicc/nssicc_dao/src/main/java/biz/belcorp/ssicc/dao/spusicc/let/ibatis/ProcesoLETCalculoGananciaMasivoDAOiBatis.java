package biz.belcorp.ssicc.dao.spusicc.let.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.let.ProcesoLETCalculoGananciaMasivoDAO;

/**
 * <p>
 * <a href="ProcesoLETCalculoGananciaMasivoDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author Juan Altamirano
 *         
 */
@Repository("spusicc.procesoLETCalculoGananciaMasivoDAO")
public class ProcesoLETCalculoGananciaMasivoDAOiBatis extends BaseDAOiBatis implements ProcesoLETCalculoGananciaMasivoDAO{
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.ProcesoLETCalculoGananciaMasivoDAO#executeProcesoLETCalculoGananciaMasivo(java.util.Map)
	 */
	public void executeProcesoLETCalculoGananciaMasivo(Map params) {
		getSqlMapClientTemplate().update("spusicc.let.ProcesosLETSQL.executeProcesoLETCalculoGananciaMasivo", params);
		
	}

}
