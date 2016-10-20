package biz.belcorp.ssicc.dao.spusicc.pej.ibatis;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.pej.ProcesoPEJCargaConsultorasNivel1DAO;
import biz.belcorp.ssicc.dao.spusicc.pej.model.ConsultoraNivel1;

/**
 * @author Jesse James Rios Franco
 *
 */

public class ProcesoPEJCargaConsultorasNivel1DAOiBatis extends BaseDAOiBatis implements ProcesoPEJCargaConsultorasNivel1DAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.ProcesoPEJCargaConsultorasNivel1DAO#getExisteCliente(java.lang.String)
	 */
	public Integer getExisteCliente(String codigoCliente) {
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosPEJSQL.getExisteCliente", codigoCliente);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.ProcesoPEJCargaConsultorasNivel1DAO#insertConsultoraNivel1(biz.belcorp.ssicc.spusicc.pej.model.ConsultoraNivel1)
	 */
	public void insertConsultoraNivel1(ConsultoraNivel1 consultoraNivel1) {
		getSqlMapClientTemplate().insert("spusicc.ProcesosPEJSQL.insertConsultoraNivel1", consultoraNivel1);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.ProcesoPEJCargaConsultorasNivel1DAO#deleteCargaConsultorasNivel1ByEtapa(java.lang.String, java.lang.String)
	 */
	public void deleteCargaConsultorasNivel1ByEtapa(String codigoPais,String codigoEtapa) {
		Map criteria = new HashMap();
		
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoEtapa", codigoEtapa);
		
		getSqlMapClientTemplate().delete("spusicc.ProcesosPEJSQL.deleteCargaConsultorasNivel1ByEtapa", criteria);
	}
}