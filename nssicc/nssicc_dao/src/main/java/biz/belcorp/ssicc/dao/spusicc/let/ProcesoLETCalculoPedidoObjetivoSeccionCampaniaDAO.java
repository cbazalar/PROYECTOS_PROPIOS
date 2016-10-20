package biz.belcorp.ssicc.dao.spusicc.let;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * Clase de la declaracin de la capa DAO (Data Access Object)
 * 
 * <p>
 * <a href="ProcesoLETCalculoPedidoObjetivoSeccionCampaniaDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="cdiaz@csigcomt.com">Carlos Diaz Valverde</a>
 *         
 */
public interface ProcesoLETCalculoPedidoObjetivoSeccionCampaniaDAO extends DAO {
	
	/**
	 * Mtodo que realiza el proceso Calculo de Pedidos Objetivos por Seccin y Campaa
	 * @param params
	 */
	public void executeProcesoLETCalculoPedidoObjetivoSeccionCampania(Map params);

}
