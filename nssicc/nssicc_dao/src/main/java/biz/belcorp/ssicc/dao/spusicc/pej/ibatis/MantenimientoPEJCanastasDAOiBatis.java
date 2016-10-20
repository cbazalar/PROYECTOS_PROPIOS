package biz.belcorp.ssicc.dao.spusicc.pej.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.pej.MantenimientoPEJCanastasDAO;
import biz.belcorp.ssicc.dao.spusicc.pej.model.Canasta;

/**
 * @author Jesse James Rios Franco
 *
 */
public class MantenimientoPEJCanastasDAOiBatis extends BaseDAOiBatis implements MantenimientoPEJCanastasDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.MantenimientoPEJCanastasDAO#validaCodigoVenta(java.lang.String, java.lang.String)
	 */
	public Integer validaCodigoVenta(String codigoPeriodo, String codigoVenta) {
		
		Map criteria = new HashMap();
		
		criteria.put("codigoPeriodo", codigoPeriodo);
		criteria.put("codigoVenta", codigoVenta);
		
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosPEJSQL.validaCodigoVenta", criteria);
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.MantenimientoPEJCanastasDAO#getCanastasList(java.util.Map)
	 */
	public List getCanastasList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosPEJSQL.getCanastasList", criteria);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.MantenimientoPEJCanastasDAO#deleteCanastas(java.util.Map)
	 */
	public void deleteCanastas(Map criteria) {
		getSqlMapClientTemplate().delete("spusicc.ProcesosPEJSQL.deleteCanastas", criteria);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.MantenimientoPEJCanastasDAO#getCanasta(java.lang.String, java.lang.String, java.lang.String)
	 */
	public Canasta getCanasta(String codigoPais, String campanhaDespachoId,String codigoVentaId) {
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("campanhaDespachoId", campanhaDespachoId);
		criteria.put("codigoVentaId", codigoVentaId);
		
		return (Canasta)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosPEJSQL.getCanasta", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.MantenimientoPEJCanastasDAO#inserCanasta(biz.belcorp.ssicc.spusicc.pej.model.Canasta)
	 */
	public void inserCanasta(Canasta canasta) {
		getSqlMapClientTemplate().insert("spusicc.ProcesosPEJSQL.inserCanasta", canasta);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.MantenimientoPEJCanastasDAO#updateCanasta(biz.belcorp.ssicc.spusicc.pej.model.Canasta)
	 */
	public void updateCanasta(Canasta canasta) {
		getSqlMapClientTemplate().update("spusicc.ProcesosPEJSQL.updateCanasta", canasta);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.MantenimientoPEJCanastasDAO#validaCodigoVentaDuplicadoCamp(java.lang.String, java.lang.String, java.lang.String)
	 */
	public Integer validaCodigoVentaDuplicadoCamp(String codigoPais,String codigoPeriodo,String codigoVenta) {
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoPeriodo", codigoPeriodo);
		criteria.put("codigoVenta", codigoVenta);
		
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosPEJSQL.validaCodigoVentaDuplicadoCamp", criteria);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.MantenimientoPEJCanastasDAO#validaOfertaDigitable(java.lang.String, java.lang.String)
	 */
	public Integer validaOfertaDigitable(String codigoPeriodo,String codigoVenta) {
		
		Map criteria = new HashMap();
		criteria.put("codigoPeriodo", codigoPeriodo);
		criteria.put("codigoVenta", codigoVenta);
		
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosPEJSQL.validaOfertaDigitable", criteria);
	}
}