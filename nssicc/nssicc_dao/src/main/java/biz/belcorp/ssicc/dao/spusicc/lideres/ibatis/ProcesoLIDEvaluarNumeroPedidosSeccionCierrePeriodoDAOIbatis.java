package biz.belcorp.ssicc.dao.spusicc.lideres.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.lideres.ProcesoLIDEvaluarNumeroPedidosSeccionCierrePeriodoDAO;

/**
 * Implementacion del DAO que ejecutara los metodos del proceso de Evaluar
 * Numero de Pedidos por Periodo
 * <p>
 * <a href="ProcesoLIDEvaluarNumeroPedidosSeccionCierrePeriodoDAOIbatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Repository("spusicc.procesoLIDEvaluarNumeroPedidosSeccionCierrePeriodoDAO")
public class ProcesoLIDEvaluarNumeroPedidosSeccionCierrePeriodoDAOIbatis extends BaseDAOiBatis implements 
				ProcesoLIDEvaluarNumeroPedidosSeccionCierrePeriodoDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.dao.ProcesoLIDEvaluarNumeroPedidosSeccionCierrePeriodoDAO#executeEvaluarNumeroPedidosSeccionCierrePeriodo(java.util.Map)
	 */
	public void executeEvaluarNumeroPedidosSeccionCierrePeriodo(Map params) {
		getSqlMapClientTemplate().update("spusicc.lideres.ProcesosLIDSQL.executeEvaluarNumeroPedidosSeccionCierrePeriodo",params);
		
	}

}