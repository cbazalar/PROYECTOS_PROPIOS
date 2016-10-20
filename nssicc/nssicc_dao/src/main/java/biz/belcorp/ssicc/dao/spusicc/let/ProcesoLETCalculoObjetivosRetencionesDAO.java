package biz.belcorp.ssicc.dao.spusicc.let;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * Clase de la declaracin de la capa DAO (Data Access Object)
 * 
 * <p>
 * <a href="ProcesoLETCalculoObjetivosRetencionesDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author Danny Amaro
 *         
 */
public interface ProcesoLETCalculoObjetivosRetencionesDAO extends DAO{
	
	/**
	 * Mtodo que realiza el calculo de Objetivos de  Retencion
	 * @param params
	 */
	public void executeProcesoLETCalculoObjetivosRetenciones(Map params);

}
