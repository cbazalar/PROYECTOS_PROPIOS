package biz.belcorp.ssicc.dao.spusicc.comision.retail.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.comision.retail.ProcesoRETCalculoComisionRetailDAO;

/**
 * Implementacion del DAO que ejecutara los metodos del proceso de Calculo
 * de Comision Retail a gerentes de Zona
 * <p>
 * <a href="ProcesoRETCalculoComisionRetailDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Repository("spusicc.procesoRETCalculoComisionRetailDAO")
public class ProcesoRETCalculoComisionRetailDAOiBatis extends BaseDAOiBatis
		implements ProcesoRETCalculoComisionRetailDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.retail.dao.ProcesoRETCalculoComisionRetailDAO#executeCalculoComisionRetail(java.util.Map)
	 */
	public void executeCalculoComisionRetail(Map criteria) {
        getSqlMapClientTemplate().update("spusicc.comision.retail.procesoRETSQL.executeCalculoComisionRetail", criteria);
 	}

}

