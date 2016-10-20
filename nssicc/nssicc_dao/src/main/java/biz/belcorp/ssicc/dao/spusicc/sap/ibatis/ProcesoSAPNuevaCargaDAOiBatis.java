package biz.belcorp.ssicc.dao.spusicc.sap.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.sap.ProcesoSAPNuevaCargaDAO;

/**
* @author <a href="mailto:croman@belcorp.biz">Christian Roman</a>
**/
@Repository("spusicc.procesoSAPNuevaCargaDAO")
public class ProcesoSAPNuevaCargaDAOiBatis extends BaseDAOiBatis implements ProcesoSAPNuevaCargaDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sap.dao.ProcesoSAPNuevaCargaDAO#getCantidadRegistros(java.util.Map)
	 */
	public Base getCantidadRegistros(Map criteria){
		return (Base)getSqlMapClientTemplate().queryForObject("sisicc.InterfazSAFSQL.getCantidadRegistros", criteria);
    }

  
   
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.dao.spusicc.sap.ProcesoSAPNuevaCargaDAO#executeUpdateRegistros(java.util.Map)
     */
    public void executeUpdateRegistros(Map params){
    	getSqlMapClientTemplate().update("sisicc.InterfazSAFSQL.executeUpdateRegistros", params);
    }
    
   
}