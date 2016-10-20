package biz.belcorp.ssicc.dao.spusicc.lec.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.lec.ProcesoLECActualizarIndicadoresLETDAO;

/**
 * Proceso que realiza la Actualizacion de Indicadores LET
 * 
 * <p>
 * <a href="ProcesoLECActualizarIndicadoresLETDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author Ivan Tocto.
 *         
 */
@Repository("spusicc.procesoLECActualizarIndicadoresLETDAO")
public class ProcesoLECActualizarIndicadoresLETDAOiBatis extends BaseDAOiBatis implements ProcesoLECActualizarIndicadoresLETDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.ProcesoLECActualizarIndicadoresLETDAO#executeProcesoLECActualizarIndicadoresLET(java.util.Map)
	 */
	public void executeProcesoLECActualizarIndicadoresLET(Map params) {
		getSqlMapClientTemplate().update("spusicc.lec.ProcesosLECSQL.executeProcesoLECActualizarIndicadoresLET", params);
	}

}
