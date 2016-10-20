package biz.belcorp.ssicc.dao.spusicc.cobranzas.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.ProcesoCOBActualizarCarteraDAO;

/**
 * Implementacion del DAO que ejecutara la Actualizacin de Cartera
 * <p>
 * <a href="ProcesoCOBActualizarCarteraDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:jflorencio@belcorp.biz">JFA</a>
 */
/**
 * @author pejflorencio
 *
 */
/**
 * @author pejflorencio
 *
 */
@Repository("spusicc.procesoCOBActualizarCarteraDAO")
public class ProcesoCOBActualizarCarteraDAOiBatis extends BaseDAOiBatis implements ProcesoCOBActualizarCarteraDAO {


	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeActualizarCartera(java.util.Map)
	 */
	public void executeActualizarCartera(Map criteria) {
		getSqlMapClientTemplate().update(
				"sisicc.ProcesosCOBSQL.executeActualizarCartera", criteria);
	}

		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ProcesoCOBActualizarCarteraDAO#getEnvioMailsActualizarCarteraParams(java.util.Map)
	 */
	public List getEnvioMailsActualizarCarteraParams(Map criteria){
		return getSqlMapClientTemplate().queryForList("sisicc.ProcesosCOBSQL.getEnvioMailsActualizarCarteraParams",criteria);
	}
	
}
