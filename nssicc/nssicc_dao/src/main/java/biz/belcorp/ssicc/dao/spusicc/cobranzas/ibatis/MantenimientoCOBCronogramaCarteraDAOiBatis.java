package biz.belcorp.ssicc.dao.spusicc.cobranzas.ibatis;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.MantenimientoCOBCronogramaCarteraDAO;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.CronogramaCartera;


/**
  * <p>
 * <a href="MantenimientoCOBCronogramaCarteraDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:jflorencio@belcorp.biz"></a>
 */
@Repository("spusicc.mantenimientoCOBCronogramaCarteraDAO")
public class MantenimientoCOBCronogramaCarteraDAOiBatis extends BaseDAOiBatis implements MantenimientoCOBCronogramaCarteraDAO {


	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.scsicc.dao.ReporteDAO#executeAsignacionCartera(java.util.Map)
	 */
	public List  getCronogramaCarteraList(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.ProcesosCOBSQL.getCronogramaCarteraByFilter", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.MantenimientoCOBCronogramaCarteraDAO#getCronogramaCarteraById(biz.belcorp.ssicc.spusicc.cobranzas.model.CronogramaCartera)
	 */
	public CronogramaCartera getCronogramaCarteraById(CronogramaCartera cronogramaCartera) {
		CronogramaCartera resultado = (CronogramaCartera) getSqlMapClientTemplate().queryForObject(
				"sisicc.ProcesosCOBSQL.getCronogramaCarteraById",
				cronogramaCartera);
		return resultado;
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.MantenimientoCOBCronogramaCarteraDAO#updateCronogramaCartera(java.util.Map)
	 */
	public void updateCronogramaCartera(Map criteria) {
        getSqlMapClientTemplate().update(
                "sisicc.ProcesosCOBSQL.executeActualizarCronogramaCartera", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.MantenimientoCOBCronogramaCarteraDAO#existeCarteraAsignada(java.util.Map)
	 */
	public int existeCarteraAsignada(Map criteria) {
		return (Integer)getSqlMapClientTemplate().queryForObject("sisicc.ProcesosCOBSQL.existeCarteraAsignada", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.MantenimientoCOBCronogramaCarteraDAO#cierreCartera(java.util.Map)
	 */
	public void cierreCartera(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.ProcesosCOBSQL.cierreCarteraDetalleMovimiento", criteria);
		getSqlMapClientTemplate().update("sisicc.ProcesosCOBSQL.cierreCarteraDetalleAsignacion", criteria);
		getSqlMapClientTemplate().update("sisicc.ProcesosCOBSQL.cierreCarteraCabaceraAsignacion", criteria);
	}
	
}
