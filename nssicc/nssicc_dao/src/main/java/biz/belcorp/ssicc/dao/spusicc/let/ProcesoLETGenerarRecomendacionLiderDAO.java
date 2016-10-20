package biz.belcorp.ssicc.dao.spusicc.let;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * Clase de la declaracin de la capa DAO (Data Access Object)
 * 
 * <p>
 * <a href="ProcesoLETGenerarRecomendacionLiderDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="cdiaz@csigcomt.com">Carlos Diaz Valverde</a>
 *         
 */
public interface ProcesoLETGenerarRecomendacionLiderDAO extends DAO {
	
	/**
	 * Mtodo que realiza el proceso Generar Recomendaciones de Lideres
	 * @param params
	 */
	public void executeProcesoLETGenerarRecomendacionesLider(Map params);
	
	

}
