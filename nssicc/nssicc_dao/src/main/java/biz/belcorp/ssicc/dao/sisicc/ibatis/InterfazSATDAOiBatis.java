package biz.belcorp.ssicc.dao.sisicc.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.InterfazSATDAO;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazAPEDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:doliva@csigcomt.com">Dennys Oliva Iriarte</a>
 */
@Repository("sisicc.interfazSATDAO")
public class InterfazSATDAOiBatis extends BaseDAOiBatis implements InterfazSATDAO {
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSATDAO#executeInterfazSATRecepcionarCentrosAcopio(java.util.Map)
	 */
	public void executeInterfazSATRecepcionarCentrosAcopio(Map params) {
        getSqlMapClientTemplate().update("sisicc.InterfazSATSQL.executeInterfazSATRecepcionarCentrosAcopio", params);
    }

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSATDAO#executeInterfazSATRecepcionarCoberturaCodigoTerritorial(java.util.Map)
	 */
	public void executeInterfazSATRecepcionarCoberturaCodigoTerritorial(Map params) {
        getSqlMapClientTemplate().update("sisicc.InterfazSATSQL.executeInterfazSATRecepcionarCoberturaCodigoTerritorial", params);
    }
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSATDAO#deleteInterfazSATRecepcionarCentrosAcopio()
	 */
	public void deleteInterfazSATRecepcionarCentrosAcopio() {
		getSqlMapClientTemplate().delete("sisicc.InterfazSATSQL.deleteInterfazSATRecepcionarCentrosAcopio",null);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSATDAO#deleteInterfazSATRecepcionarCoberturaCodigoTerritorial()
	 */
	public void deleteInterfazSATRecepcionarCoberturaCodigoTerritorial(){
		getSqlMapClientTemplate().delete("sisicc.InterfazSATSQL.deleteInterfazSATRecepcionarCoberturaCodigoTerritorial",null);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSATDAO#insertCentrosAcopio(java.util.Map, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertCentrosAcopio(Map params, Usuario usuario) {
        getSqlMapClientTemplate().insert("sisicc.InterfazSATSQL.insertCentrosAcopio", params);
    }
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSATDAO#insertCoberturaCodigoTerritorial(java.util.Map, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertCoberturaCodigoTerritorial(Map params, Usuario usuario) {
        getSqlMapClientTemplate().insert("sisicc.InterfazSATSQL.insertCoberturaCodigoTerritorial", params);
    }
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSATDAO#executeInterfazSATRecepcionarDivisionArmadoCDP(java.util.Map)
	 */
	public void executeInterfazSATRecepcionarDivisionArmadoCDP(Map params) {
        getSqlMapClientTemplate().update("sisicc.InterfazSATSQL.executeInterfazSATRecepcionarDivisionArmadoCDP", params);
    }

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSATDAO#executeInterfazSATRecepcionarOrdenImpresionAPESAT(java.util.Map)
	 */
	public void executeInterfazSATRecepcionarOrdenImpresionAPESAT(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazSATSQL.executeInterfazSATRecepcionarOrdenImpresionAPESAT", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSATDAO#executeInterfazSATRecepcionarParametrizacionCalculoFechaEntregaExacta(java.util.Map)
	 */
	public void executeInterfazSATRecepcionarParametrizacionCalculoFechaEntregaExacta(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazSATSQL.executeInterfazSATRecepcionarParametrizacionCalculoFechaEntregaExacta", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSATDAO#executeInterfazSATRecepcionarExcepcionesFechaEntregaExacta(java.util.Map)
	 */
	public void executeInterfazSATRecepcionarExcepcionesFechaEntregaExacta(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazSATSQL.executeInterfazSATRecepcionarExcepcionesFechaEntregaExacta", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSATDAO#executeInterfazSATRecepcionarSeguimientoPedido(java.util.Map)
	 */
	public void executeInterfazSATRecepcionarSeguimientoPedido(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazSATSQL.executeInterfazSATRecepcionarSeguimientoPedido", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSATDAO#executeInterfazSATRecepcionarImpresionBoletasEntrega(java.util.Map)
	 */
	public void executeInterfazSATRecepcionarImpresionBoletasEntrega(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazSATSQL.executeInterfazSATRecepcionarImpresionBoletasEntrega", params);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSATDAO#getInterfazSATRecepcionarOrdenImpresionAPESAT(java.util.Map)
	 */
	public int getInterfazSATRecepcionarOrdenImpresionAPESAT(Map params) {
		Integer val = (Integer)getSqlMapClientTemplate().queryForObject("sisicc.InterfazSATSQL.getInterfazSATRecepcionarOrdenImpresionAPESAT", params);
		
		return val.intValue();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSATDAO#insertInterfazSATRecepcionarOrdenImpresionAPESATHistorico(java.util.Map)
	 */
	public void insertInterfazSATRecepcionarOrdenImpresionAPESATHistorico(Map params) {
		getSqlMapClientTemplate().insert("sisicc.InterfazSATSQL.insertInterfazSATRecepcionarOrdenImpresionAPESATHistorico", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSATDAO#deleteInterfazSATRecepcionarOrdenImpresionAPESAT(java.util.Map)
	 */
	public void deleteInterfazSATRecepcionarOrdenImpresionAPESAT(Map params) {
		getSqlMapClientTemplate().delete("sisicc.InterfazSATSQL.deleteInterfazSATRecepcionarOrdenImpresionAPESAT", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSATDAO#insertInterfazSATRecepcionarOrdenImpresionAPESAT(java.util.Map)
	 */
	public void insertInterfazSATRecepcionarOrdenImpresionAPESAT(Map params) {
		getSqlMapClientTemplate().insert("sisicc.InterfazSATSQL.insertInterfazSATRecepcionarOrdenImpresionAPESAT", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSATDAO#getInterfazSATRecepcionarParametrizacionCalculoFechaEntregaExacta(java.util.Map)
	 */
	public int getInterfazSATRecepcionarParametrizacionCalculoFechaEntregaExacta(Map params) {
		Integer val = (Integer)getSqlMapClientTemplate().queryForObject("sisicc.InterfazSATSQL.getInterfazSATRecepcionarParametrizacionCalculoFechaEntregaExacta", params);
		
		return val.intValue();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSATDAO#insertInterfazSATRecepcionarParametrizacionCalculoFechaEntregaExactaHistorico(java.util.Map)
	 */
	public void insertInterfazSATRecepcionarParametrizacionCalculoFechaEntregaExactaHistorico(Map params) {
		getSqlMapClientTemplate().insert("sisicc.InterfazSATSQL.insertInterfazSATRecepcionarParametrizacionCalculoFechaEntregaExactaHistorico", params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSATDAO#deleteInterfazSATRecepcionarParametrizacionCalculoFechaEntregaExacta(java.util.Map)
	 */
	public void deleteInterfazSATRecepcionarParametrizacionCalculoFechaEntregaExacta(Map params) {
		getSqlMapClientTemplate().delete("sisicc.InterfazSATSQL.deleteInterfazSATRecepcionarParametrizacionCalculoFechaEntregaExacta", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSATDAO#insertInterfazSATRecepcionarParametrizacionCalculoFechaEntregaExacta(java.util.Map)
	 */
	public void insertInterfazSATRecepcionarParametrizacionCalculoFechaEntregaExacta(Map params) {
		getSqlMapClientTemplate().insert("sisicc.InterfazSATSQL.insertInterfazSATRecepcionarParametrizacionCalculoFechaEntregaExacta", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSATDAO#insertInterfazSATRecepcionarExcepcionesFechaEntregaExacta(java.util.Map)
	 */
	public void insertInterfazSATRecepcionarExcepcionesFechaEntregaExacta(Map params) {
		getSqlMapClientTemplate().insert("sisicc.InterfazSATSQL.insertInterfazSATRecepcionarExcepcionesFechaEntregaExacta", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSATDAO#insertInterfazSATRecepcionarSeguimientoPedido(java.util.Map)
	 */
	public void insertInterfazSATRecepcionarSeguimientoPedido(Map params) {
		getSqlMapClientTemplate().insert("sisicc.InterfazSATSQL.insertInterfazSATRecepcionarSeguimientoPedido", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSATDAO#insertInterfazSATRecepcionarImpresionBoletasEntrega(java.util.Map)
	 */
	public void insertInterfazSATRecepcionarImpresionBoletasEntrega(Map params) {
		getSqlMapClientTemplate().insert("sisicc.InterfazSATSQL.insertInterfazSATRecepcionarImpresionBoletasEntrega", params);
	}
}