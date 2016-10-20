package biz.belcorp.ssicc.dao.spusicc.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.ProcesoGENBloquearConsultorasPrimerPedidoDAO;

/**
 * @author Gonzalo Javier Huertas Agurto
 *
 */
@Repository("spusicc.procesoGENBloquearConsultorasPrimerPedidoDAO")
public class ProcesoGENBloquearConsultorasPrimerPedidoDAOiBatis extends BaseDAOiBatis implements ProcesoGENBloquearConsultorasPrimerPedidoDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dao.ProcesoGENGenerarInformacionSolicitudesDAO#executeProcesoGENGenerarInformacionSolicitudes(java.util.Map)
	 */
	public void executeProcesoGENBloquearConsultorasPrimerPedido(Map params) {
		getSqlMapClientTemplate().update("spusicc.ProcesosMAESQL.executeProcesoGENBloquearConsultorasPrimerPedido", params);
	}
}