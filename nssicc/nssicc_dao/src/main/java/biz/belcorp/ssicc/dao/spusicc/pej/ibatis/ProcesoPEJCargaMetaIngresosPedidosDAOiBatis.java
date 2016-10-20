package biz.belcorp.ssicc.dao.spusicc.pej.ibatis;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.pej.ProcesoPEJCargaMetaIngresosPedidosDAO;
import biz.belcorp.ssicc.dao.spusicc.pej.model.MetaEjecutivaIngresosPedidos;

/**
 * @author Jesse James Rios Franco
 *
 */

public class ProcesoPEJCargaMetaIngresosPedidosDAOiBatis extends BaseDAOiBatis implements ProcesoPEJCargaMetaIngresosPedidosDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.ProcesoPEJCargaMetaIngresosPedidosDAO#getExisteZona(java.lang.String)
	 */
	public Integer getExisteZona(String codigoZona) {
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosPEJSQL.getExisteZona", codigoZona);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.ProcesoPEJCargaMetaIngresosPedidosDAO#getExisteSeccionByZona(java.lang.String, java.lang.String)
	 */
	public Integer getExisteSeccionByZona(String codigoZona,String codigoSeccion) {
		Map criteria = new HashMap();
		
		criteria.put("codigoZona", codigoZona);
		criteria.put("codigoSeccion", codigoSeccion);		
		
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosPEJSQL.getExisteSeccionByZona",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.ProcesoPEJCargaMetaIngresosPedidosDAO#getExisteCampInicialFinal(java.lang.String, java.lang.String)
	 */
	public Integer getExisteCampInicialFinal(String campIncial, String campFinal) {
		Map criteria = new HashMap();
		
		criteria.put("campIncial", campIncial);
		criteria.put("campFinal", campFinal);
		
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosPEJSQL.getExisteCampInicialFinal",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.ProcesoPEJCargaMetaIngresosPedidosDAO#saveTempoMetaIngresosPedidos(biz.belcorp.ssicc.spusicc.pej.model.MetaEjecutivaIngresosPedidos)
	 */
	public void saveTempoMetaIngresosPedidos(MetaEjecutivaIngresosPedidos metaEjecutivaIngresosPedidos) {
		getSqlMapClientTemplate().insert("spusicc.ProcesosPEJSQL.saveTempoMetaIngresosPedidos",metaEjecutivaIngresosPedidos);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.ProcesoPEJCargaMetaIngresosPedidosDAO#deleteTempoMetaIngresosPedidos()
	 */
	public void deleteTempoMetaIngresosPedidos() {
		getSqlMapClientTemplate().delete("spusicc.ProcesosPEJSQL.deleteTempoMetaIngresosPedidos");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.dao.ProcesoPEJCargaMetaIngresosPedidosDAO#executeCargaMetaIngresosPedidos(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void executeCargaMetaIngresosPedidos(String codigoPais,String anioInicial, String codigoEtapa) {
		
		Map params = new HashMap();
		
		params.put("codigoPais", codigoPais);
		params.put("anioInicial", anioInicial);
		params.put("codigoEtapa", codigoEtapa);
		
		getSqlMapClientTemplate().update("spusicc.ProcesosPEJSQL.executeCargaMetaIngresosPedidos",params);
	}
}