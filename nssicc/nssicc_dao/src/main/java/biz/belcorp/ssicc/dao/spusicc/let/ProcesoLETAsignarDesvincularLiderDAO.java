package biz.belcorp.ssicc.dao.spusicc.let;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * Clase de la declaracin de la capa DAO (Data Access Object)
 * 
 * <p>
 * <a href="ProcesoLETAsignarDesvincularLiderDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="cdiaz@csigcomt.com">Carlos Diaz Valverde</a>
 *         
 */
public interface ProcesoLETAsignarDesvincularLiderDAO extends DAO {
	
	/**
	 * Mtodo que realiza el proceso Asignar o Desasginar Lideres al inicio de campaa
	 * @param params
	 */
	public void executeAsignarDesvincularLider(Map params);

}
