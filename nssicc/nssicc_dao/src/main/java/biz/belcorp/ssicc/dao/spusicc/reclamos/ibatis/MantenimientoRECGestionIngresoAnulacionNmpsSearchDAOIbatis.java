/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.reclamos.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.reclamos.MantenimientoRECGestionIngresoAnulacionNmpsSearchDAO;


/**
 * @author peextllizana
 *
 */
@Repository("spusicc.mantenimientoRECGestionIngresoAnulacionNmpsSearchDAO")
public class MantenimientoRECGestionIngresoAnulacionNmpsSearchDAOIbatis extends BaseDAOiBatis implements MantenimientoRECGestionIngresoAnulacionNmpsSearchDAO {


	public List getConsultoraPedidoList(Map params) {
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getConsultoraPedidoList",params);
	}

	public List getCampanaList(Map params) {
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getCampanaList",params);
	}
	
	public void executeProcesoIngresoAnulacionNmps(Map params) {
		getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.executeProcesoIngresoAnulacionNmps",params);
		
	}	
	
	public void executeProcesoGenerarArchivoIngresoAnulacionNmps(Map params) {

		getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.executeProcesoGenerarArchivoIngresoAnulacionNmps",params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECGestionIngresoAnulacionNmpsSearchDAO#getMotivosDevolucion(java.util.Map)
	 */
	public List getMotivosDevolucion(Map params){
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getMotivosDevolucion",params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECGestionIngresoAnulacionNmpsSearchDAO#getTiposOperacionIngresoAnulaciones()
	 */
	public List getTiposOperacionIngresoAnulaciones(Map params){
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getTiposOperacionIngresoAnulaciones",params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECGestionIngresoAnulacionNmpsSearchDAO#getNumeroLote()
	 */
	public String getNumeroLote() {
		return (String)getSqlMapClientTemplate().
			queryForObject("spusicc.reclamos.ReclamosSQL.getNumeroLote");

	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECGestionIngresoAnulacionNmpsSearchDAO#getIndicadorNotaMercaderiaPerdida(java.util.Map)
	 */
	public String getIndicadorNotaMercaderiaPerdida(Map params){
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.getIndicadorNotaMercaderiaPerdida",params);
	}
	
	public List getValTramaPedidoList(Map params) {
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getValTramaPedidoList",params);
	}
	
	public void updateValTramaPedido(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.reclamos.ReclamosSQL.updateValTramaPedido", criteria);	
	}
}
