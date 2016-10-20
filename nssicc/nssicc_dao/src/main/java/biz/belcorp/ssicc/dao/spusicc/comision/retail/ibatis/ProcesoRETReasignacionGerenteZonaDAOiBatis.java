package biz.belcorp.ssicc.dao.spusicc.comision.retail.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.comision.retail.ProcesoRETReasignacionGerenteZonaDAO;

/**
 * Implementacion del DAO que ejecutara los metodos del proceso de Reasignacion
 * de Gerentes de Zona
 * <p>
 * <a href="ProcesoRETReasignacionGerenteZonaDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Repository("spusicc.procesoRETReasignacionGerenteZonaDAO")
public class ProcesoRETReasignacionGerenteZonaDAOiBatis extends BaseDAOiBatis
		implements ProcesoRETReasignacionGerenteZonaDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.retail.dao.ProcesoRETReasignacionGerenteZonaDAO#executeReasignacionGerenteZona(java.util.Map)
	 */
	public void executeReasignacionGerenteZona(Map criteria) {
        getSqlMapClientTemplate().update("spusicc.comision.retail.procesoRETSQL.executeReasignacionGerenteZona", criteria);
 	}

}
