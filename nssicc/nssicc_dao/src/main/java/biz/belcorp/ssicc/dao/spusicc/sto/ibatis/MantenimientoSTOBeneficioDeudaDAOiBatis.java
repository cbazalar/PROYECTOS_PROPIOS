package biz.belcorp.ssicc.dao.spusicc.sto.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.sto.MantenimientoSTOBeneficioDeudaDAO;

/**
 * @author Jesse James Rios Franco
 *
 */
@Repository("spusicc.mantenimientoSTOBeneficioDeudaDAO")
public class MantenimientoSTOBeneficioDeudaDAOiBatis extends BaseDAOiBatis implements MantenimientoSTOBeneficioDeudaDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.MantenimientoSTOBeneficioDeudaDAO#getBeneficioDeudaList(java.util.Map)
	 */
	public List getBeneficioDeudaList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getBeneficioDeudaList", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.MantenimientoSTOBeneficioDeudaDAO#deleteBeneficioDeuda(java.util.Map)
	 */
	public void deleteBeneficioDeuda(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.ProcesosSTOSQL.deleteBeneficioDeuda", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.MantenimientoSTOBeneficioDeudaDAO#insertDeudaBenficio(java.util.Map)
	 */
	public void insertDeudaBenficio(Map criteria) {
		getSqlMapClientTemplate().insert("spusicc.ProcesosSTOSQL.insertDeudaBenficio",criteria);
	}
}