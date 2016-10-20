package biz.belcorp.ssicc.service.spusicc.let;

import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * Clase de la declaracin de la capa BO (Bussiness Object)
 * 
 * <p>
 * <a href="ProcesoLETCalculoAcumuladoMetaPedidoService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="cdiaz@csigcomt.com">Carlos Diaz Valverde</a>
 *         
 */
public interface ProcesoLETCalculoAcumuladoMetaPedidoService extends Service{
	
	/**
	 * Mtodo que realiza el proceso Calculo de Acumulado de Metas de Pedidos por Concurso
	 * @param params
	 */
	public void executeProcesoLETCalculoAcumuladoMetaPedido(Map params);

}
