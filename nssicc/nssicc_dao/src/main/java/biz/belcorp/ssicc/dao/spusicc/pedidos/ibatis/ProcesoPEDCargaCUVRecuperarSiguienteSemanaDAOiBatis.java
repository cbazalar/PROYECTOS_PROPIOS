package biz.belcorp.ssicc.dao.spusicc.pedidos.ibatis;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.pedidos.ProcesoPEDCargaCUVRecuperarSiguienteSemanaDAO;

/**
 * @author <a href="mailto:itocto@sigcomt.com">Ivan Tocto</a>
 *
 */
@Repository("spusicc.procesoPEDCargaCUVRecuperarSiguienteSemanaDAO")
public class ProcesoPEDCargaCUVRecuperarSiguienteSemanaDAOiBatis extends BaseDAOiBatis implements
	ProcesoPEDCargaCUVRecuperarSiguienteSemanaDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.ProcesoPEDCargaCUVRecuperarSiguienteSemanaDAO#insertCargaCUVRecuperarSiguienteSemana(java.util.Map)
	 */
	public void insertCargaCUVRecuperarSiguienteSemana(Map params) {
		getSqlMapClientTemplate().insert(
				"spusicc.pedidos.PedidosSQL.insertCargaCUVRecuperarSiguienteSemana", params);

	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.ProcesoPEDCargaCUVRecuperarSiguienteSemanaDAO#executeValidarCargaCUVRecuperarSiguienteSemana(java.util.Map)
	 */
	public Map getDatosProductoCUVRecuperarSemana(Map params) {
		return (HashMap) getSqlMapClientTemplate().queryForObject(
				"spusicc.pedidos.PedidosSQL.getDatosProductoCUVRecuperarSemana", params);
		
	}
	

	
}
