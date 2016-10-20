package biz.belcorp.ssicc.dao.spusicc.love.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.love.ProcesoLOVAtencionPremiosDAO;

/**
 * Implementacion del DAO que ejecutara los metodos del proceso que Genera las solicitudes de premiacion del programa Love
 * <p>
 * <a href="ProcesoLOVAtencionPremiosDAOIbatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Repository("spusicc.procesoLOVAtencionPremiosDAO")
public class ProcesoLOVAtencionPremiosDAOIbatis extends BaseDAOiBatis implements 
				ProcesoLOVAtencionPremiosDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.love.dao.ProcesoLOVAtencionPremiosDAO#executeAtencionPremios(java.util.Map)
	 */
	public void executeAtencionPremios(Map params) {
		getSqlMapClientTemplate().update("spusicc.love.ProcesosLOVSQL.executeAtencionPremios",params);
		
	}

}