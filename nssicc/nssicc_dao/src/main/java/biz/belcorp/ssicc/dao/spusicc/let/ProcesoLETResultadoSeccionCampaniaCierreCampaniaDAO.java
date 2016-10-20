package biz.belcorp.ssicc.dao.spusicc.let;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * Clase de la declaracin de la capa DAO (Data Access Object)
 * 
 * <p>
 * <a href="ProcesoLETResultadoSeccionCampaniaCierreCampaniaDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="cdiaz@csigcomt.com">Carlos Diaz Valverde</a>
 *         
 */
public interface ProcesoLETResultadoSeccionCampaniaCierreCampaniaDAO extends DAO {
	
	/**
	 * Mtodo que realiza el proceso Resultados de secciones por campaa al cierre de campaa
	 * @param params
	 */
	public void executeProcesoLETResultadoSeccionesCampaniaCieCam(Map params);
	
	/**
	 * Mtodo que valida si ha ejecutado el proceso para la misma Regin. Al cierre de Regin
	 * @param criteria
	 * @return
	 */
	public String getValidaProcesoCierreRegionLET(Map criteria);
}
