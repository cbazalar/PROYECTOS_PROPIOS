/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.reclamos.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.reclamos.MantenimientoRECAtencionesMasivasDAO;

/**
 * @author peextrramirez
 *
 */
@Repository("spusicc.mantenimientoRECAtencionesMasivasDAO")
public class MantenimientoRECAtencionesMasivasDAOIbatis extends BaseDAOiBatis implements  MantenimientoRECAtencionesMasivasDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECAtencionesMasivasDAO#eliminarAtencionesMasivas()
	 */
	public void eliminarAtencionesMasivas() {
		getSqlMapClientTemplate().update("spusicc.reclamos.ReclamosSQL.eliminarAtencionesMasivas", null);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECAtencionesMasivasDAO#insertAtencionesMasivasError(java.util.Map)
	 */
	public void insertAtencionesMasivasError(Map criteria) {
		getSqlMapClientTemplate().insert("spusicc.reclamos.ReclamosSQL.insertAtencionesMasivasError",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECAtencionesMasivasDAO#getValidarCodigoConsultora(java.util.Map)
	 */
	public String getValidarCodigoConsultora(Map criteria) {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.getValidarCodigoConsultora",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECAtencionesMasivasDAO#getAtencionesMasivasPorMatrizList(java.util.Map)
	 */
	public List getAtencionesMasivasPorMatrizList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getAtencionesMasivasPorMatrizList",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECAtencionesMasivasDAO#getAtencionesMasivasPorPremioList(java.util.Map)
	 */
	public List getAtencionesMasivasPorPremioList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getAtencionesMasivasPorPremioList",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECAtencionesMasivasDAO#insertAtencionesMasivasConsultoraVenta(java.util.Map)
	 */
	public void insertAtencionesMasivasConsultoraVenta(Map criteria) {
		getSqlMapClientTemplate().insert("spusicc.reclamos.ReclamosSQL.insertAtencionesMasivasConsultoraVenta",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECAtencionesMasivasDAO#getNumeroLoteAtencionesMasivas()
	 */
	public String getNumeroLoteAtencionesMasivas() {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.getNumeroLoteAtencionesMasivas", null);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECAtencionesMasivasDAO#procesarIngresoAtencionesMasivas(java.util.Map)
	 */
	public String procesarIngresoAtencionesMasivas(Map criteria) {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.procesarIngresoAtencionesMasivas",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECAtencionesMasivasDAO#getGTTDetalleIngresoAtencionesMasivasList(java.util.Map)
	 */
	public List getGTTDetalleIngresoAtencionesMasivasList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getGTTDetalleIngresoAtencionesMasivasList",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECAtencionesMasivasDAO#getObtenerCodigoConsultora(java.lang.String)
	 */
	public String getObtenerCodigoConsultora(String documentoIdentidad) {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.getObtenerCodigoConsultora", documentoIdentidad);
	}
	
}