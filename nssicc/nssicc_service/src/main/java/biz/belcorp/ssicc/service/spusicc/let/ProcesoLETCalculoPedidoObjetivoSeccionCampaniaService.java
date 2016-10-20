package biz.belcorp.ssicc.service.spusicc.let;

import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * Clase de la declaracin de la capa BO (Bussiness Object)
 * 
 * <p>
 * <a href="ProcesoLETCalculoPedidoObjetivoSeccionCampaniaService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="cdiaz@csigcomt.com">Carlos Diaz Valverde</a>
 *         
 */
public interface ProcesoLETCalculoPedidoObjetivoSeccionCampaniaService extends Service{
	
	/**
	 * Mtodo que realiza el proceso Calculo de Pedidos Objetivos por Seccin y Campaa
	 * @param params
	 */
	public void executeProcesoLETCalculoPedidoObjetivoSeccionCampania(Map params);

}
