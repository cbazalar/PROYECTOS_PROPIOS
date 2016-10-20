package biz.belcorp.ssicc.dao.spusicc.sto.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.sto.MantenimientoSTOFacturacionAdicionalDAO;

/**
 * @author Jose Luis Rodriguez
 *
 */
@Repository("spusicc.mantenimientoSTOFacturacionAdicionalDAO")
public class MantenimientoSTOFacturacionAdicionalDAOiBatis extends BaseDAOiBatis implements MantenimientoSTOFacturacionAdicionalDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.MantenimientoSTOFacturacionAdicionalDAO#getFacturaAdicionalList(java.util.Map)
	 */
	public List getFacturaAdicionalList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getFacturaAdicionalList", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.MantenimientoSTOFacturacionAdicionalDAO#deleteFacturaAdicional(java.util.Map)
	 */
	public void deleteFacturaAdicional(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.deleteFacturaAdicional", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.MantenimientoSTOFacturacionAdicionalDAO#insertFacturaAdicional(java.util.Map)
	 */
	public void insertFacturaAdicional(Map criteria) {
		getSqlMapClientTemplate().insert("spusicc.ProcesosSTOSQL.insertFacturaAdicional",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.MantenimientoSTOFacturacionAdicionalDAO#getOidUltimoFacturaAdicionalCabecera()
	 */
	public String getOidUltimoFacturaAdicionalCabecera() {
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.ProcesosSTOSQL.getOidUltimoFacturaAdicionalCabecera", null);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.MantenimientoSTOFacturacionAdicionalDAO#insertFacturaAdicionalDetalle(java.util.Map)
	 */
	public void insertFacturaAdicionalDetalle(Map criteria) {
		getSqlMapClientTemplate().insert("spusicc.ProcesosSTOSQL.insertFacturaAdicionalDetalle",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.MantenimientoSTOFacturacionAdicionalDAO#deleteFacturaAdicionalDetalle(java.util.Map)
	 */
	public void deleteFacturaAdicionalDetalle(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.deleteFacturaAdicionalDetalle", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.MantenimientoSTOFacturacionAdicionalDAO#executeInsertFADDetalle(java.util.Map)
	 */
	public void executeInsertFADDetalle(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.executeInsertFADDetalle", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.MantenimientoSTOFacturacionAdicionalDAO#getValidFacturaAdicional()
	 */
	public String getValidFacturaAdicional(Map criteria) {
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.ProcesosSTOSQL.getValidateParamMantFacturacionAdicional", criteria);
	}
}