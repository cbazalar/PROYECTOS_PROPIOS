package biz.belcorp.ssicc.dao.spusicc.datosconsultora.retail.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.datosconsultora.retail.ProcesoRMSDatosConsultoraRetailDAO;


/**
 * @author Richar Cruzado
 * @date   23/12/2015	
 */
@Repository("spusicc.procesoRMSDatosConsultoraRetailDAO")
public class ProcesoRMSDatosConsultoraRetailDAOiBatis extends BaseDAOiBatis 
    implements ProcesoRMSDatosConsultoraRetailDAO {

	
	@Override
	public List getDatosConsultoraRetail(Map map) {
		
		return this.getSqlMapClientTemplate().queryForList("spusicc.comision.retail.procesoRETSQL.getDatosConsultoraRetail", map);
	}

	@Override
	public void executeDatosConsultoraRetail(Map criteria) {
		
		getSqlMapClientTemplate().update("spusicc.comision.retail.procesoRETSQL.executeDatosConsultoraRetail", criteria);
		
	}
	

}
