package biz.belcorp.ssicc.dao.spusicc.pedidos.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.pedidos.ProcesoPREMatrizCargaEstimadosDAO;

/**
 * @author <a href="mailto:sguerra@sigcomt.com">Sebastian Guerra</a>
 *
 */
@Repository("spusicc.procesoPREMatrizCargaEstimadosDAO")
public class ProcesoPREMatrizCargaEstimadosDAOiBatis extends BaseDAOiBatis implements ProcesoPREMatrizCargaEstimadosDAO
{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.ProcesoPREMatrizCargaEstimadosDAO#deleteCargaEstimados(java.lang.String)
	 */
	public void deleteCargaEstimados(String codUsuario) {
		getSqlMapClientTemplate().delete("spusicc.pedidos.PedidosSQL.deleteCargaEstimados", codUsuario);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.ProcesoPREMatrizCargaEstimadosDAO#insertCargaEstimados(java.util.Map)
	 */
	public void insertCargaEstimados(Map params) {
		getSqlMapClientTemplate().insert("spusicc.pedidos.PedidosSQL.insertCargaEstimados", params);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.ProcesoPREMatrizCargaEstimadosDAO#executeValidarCargaEstimados(java.util.Map)
	 */
	public void executeValidarCargaEstimados(Map params) {
		getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.executeValidarCargaEstimados", params);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.ProcesoPREMatrizCargaEstimadosDAO#getCargaEstimadosList(java.util.Map)
	 */
	public List getCargaEstimadosList(Map params) {
		return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getCargaEstimadosList", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.ProcesoPREMatrizCargaEstimadosDAO#executeActualizarCargaEstimados(java.util.Map)
	 */
	public void executeActualizarCargaEstimados(Map params) {
		getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.executeActualizarCargaEstimados", params);
		
	}

}
