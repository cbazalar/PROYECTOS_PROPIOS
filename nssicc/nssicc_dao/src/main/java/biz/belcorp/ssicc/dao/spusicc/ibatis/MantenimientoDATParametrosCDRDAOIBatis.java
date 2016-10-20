package biz.belcorp.ssicc.dao.spusicc.ibatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.MantenimientoDATParametrosCDRDAO;
import biz.belcorp.ssicc.dao.spusicc.ventas.model.ParametroCDR;

@Repository("spusicc.mantenimientoDATParametrosCDRDAO")
public class MantenimientoDATParametrosCDRDAOIBatis extends BaseDAOiBatis
		implements MantenimientoDATParametrosCDRDAO {

	public List getParametrosCDR(ParametroCDR parametroCDR) {
		return getSqlMapClientTemplate().queryForList("spusicc.InterfazDATSQL.getParametrosCDR", parametroCDR);
		
	}

	public ParametroCDR getParametroCDRById(ParametroCDR parametroCDR) {
		ParametroCDR resultado = (ParametroCDR) getSqlMapClientTemplate().queryForObject(
				"spusicc.InterfazDATSQL.getParametroCDRById",
				parametroCDR);
		return resultado;
	}

	public void insertParametroCDR(ParametroCDR parametroCDR) {
		getSqlMapClientTemplate().insert(
				"spusicc.InterfazDATSQL.insertParametroCDR",
				parametroCDR);
		
	}

	public void updateParametroCDR(ParametroCDR parametroCDR) {
		getSqlMapClientTemplate().update(
				"spusicc.InterfazDATSQL.updateParametroCDR",
				parametroCDR);
		
	}

	public void deleteParametroCDR(ParametroCDR parametroCDR) {
		getSqlMapClientTemplate().delete(
				"spusicc.InterfazDATSQL.deleteParametroCDR",
				parametroCDR);
		
	}

}
