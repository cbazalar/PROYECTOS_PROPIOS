package biz.belcorp.ssicc.dao.spusicc.let;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * Clase de la declaracin de la capa DAO (Data Access Object)
 * 
 * <p>
 * <a href="ProcesoLETEntregaPremioCampaniaCierreCampaniaDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="cdiaz@csigcomt.com">Carlos Diaz Valverde</a>
 *         
 */
public interface ProcesoLETEntregaPremioCampaniaCierreCampaniaDAO extends DAO {
	
	/**
	 * Mtodo que realiza el proceso Entrega de premios por campaa al cierre de campaa
	 * @param params
	 */
	public void executeProcesoLETEntregaPremioCampaniaCierreCampania(Map params);
	
}
