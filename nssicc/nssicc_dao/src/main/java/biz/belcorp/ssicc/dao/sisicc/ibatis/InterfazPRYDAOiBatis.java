package biz.belcorp.ssicc.dao.sisicc.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.sisicc.InterfazPRYDAO;

/**
 * Implementacion iBatis del DAO de la Interfaz Proyeccion Parcial.
 * 
 * @author <a href="mailto:lshimokawa@belcorp.biz">Lennon Shimokawa</a>
 */
@Repository("sisicc.interfazPRYDAO")
public class InterfazPRYDAOiBatis extends BaseDAOiBatis implements
		InterfazPRYDAO {

	public List getInterfazPRYEnviarCronogramaFacturacion(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getInterfazPRYEnviarCronogramaFacturacion",
				params);
	}

	public List getInterfazPRYEnviarDatosProyeccionParcial(Map params) {
		return getSqlMapClientTemplate()
				.queryForList(
						"sisicc.InterfazSQL.getInterfazPRYEnviarDatosProyeccionParcial",
						params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazPRYDAO#getInterfazPRYEnviarInformacionVentaProyeccionParcial(java.util.Map)
	 */
	public List getInterfazPRYEnviarInformacionVentaProyeccionParcial(Map params) {
		
		return getSqlMapClientTemplate().queryForList("sisicc.InterfazSQL.getInterfazPRYEnviarInformacionVentaProyeccionParcial",params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazPRYDAO#getInterfazPRYEnviarPedidosDiaPedidosAcumulados(java.util.Map)
	 */
	public List getInterfazPRYEnviarPedidosDiaPedidosAcumulados(Map params) {
		return getSqlMapClientTemplate().queryForList("sisicc.InterfazSQL.getInterfazPRYEnviarPedidosDiaPedidosAcumulados",params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazPRYDAO#executeEnvioDatosProyeccionParcialCentro(java.util.Map)
	 */
	public void executeEnvioDatosProyeccionParcialCentro(Map params){
		getSqlMapClientTemplate().update("sisicc.InterfazPRYSQL.executeEnvioDatosProyeccionParcialCentro",params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazPRYDAO#executeEnvioCronogramaFacturacionCentro(java.util.Map)
	 */
	public void executeEnvioCronogramaFacturacionCentro(Map params){
		getSqlMapClientTemplate().update("sisicc.InterfazPRYSQL.executeEnvioCronogramaFacturacionCentro",params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazPRYDAO#executeEnvioInformacionVentaProyeccionParcialCentro(java.util.Map)
	 */
	public void executeEnvioInformacionVentaProyeccionParcialCentro(Map params){
		getSqlMapClientTemplate().update("sisicc.InterfazPRYSQL.executeEnvioInformacionVentaProyeccionParcialCentro",params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazPRYDAO#executeEnvioPedidosDiaPedidosAcumuladosCentro(java.util.Map)
	 */
	public void executeEnvioPedidosDiaPedidosAcumuladosCentro(Map params){
		getSqlMapClientTemplate().update("sisicc.InterfazPRYSQL.executeEnvioPedidosDiaPedidosAcumuladosCentro",params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazPRYDAO#executeInterfazPRYEnviarInformacionNuevosFaltantes(java.util.Map)
	 */
	public void executeInterfazPRYEnviarInformacionNuevosFaltantes(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazPRYSQL.executeInterfazPRYEnviarInformacionNuevosFaltantes", params);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.sisicc.InterfazPRYDAO#executeEnvioDemandaCodigoCerrado(java.util.Map)
	 */
	public void executeEnvioDemandaCodigoCerrado(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazPRYSQL.executeEnvioDemandaCodigoCerrado",params);		
	}	
	
}