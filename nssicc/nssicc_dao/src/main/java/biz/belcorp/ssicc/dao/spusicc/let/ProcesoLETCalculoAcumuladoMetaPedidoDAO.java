package biz.belcorp.ssicc.dao.spusicc.let;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * Clase de la declaracin de la capa DAO (Data Access Object)
 * 
 * <p>
 * <a href="ProcesoLETCalculoAcumuladoMetaPedidoDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="cdiaz@csigcomt.com">Carlos Diaz Valverde</a>
 *         
 */
public interface ProcesoLETCalculoAcumuladoMetaPedidoDAO extends DAO {
	
	/**
	 * Mtodo que realiza el proceso Calculo de Acumulado de Metas de Pedidos por Concurso
	 * @param params
	 */
	public void executeProcesoLETCalculoAcumuladoMetaPedido(Map params);

}
