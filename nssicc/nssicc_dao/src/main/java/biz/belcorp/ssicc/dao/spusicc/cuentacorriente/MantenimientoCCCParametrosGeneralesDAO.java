package biz.belcorp.ssicc.dao.spusicc.cuentacorriente;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * <p>
 * <a href="MantenimientoCCCParametrosGeneralesDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:aoviedo@sigcomt.com"> </a>
 */
public interface MantenimientoCCCParametrosGeneralesDAO extends DAO { 
			
	/**
	 * Obtiene en una lista de los Paramatros Generales segun Filtro
	 * @param criteria
	 * @return
	 */
	public List getParametroGeneralByCriteria(Map criteria);
		    		
	/**
	 * Inserta un Parametro General
	 * @param criteria
	 */
	public void insertParametroGeneral(Map criteria);
	
	/**
	 * Actualiza un Parametro General
	 * @param criteria
	 */
	public void updateParametroGeneral(Map criteria);
	
	/**
	 * Elimina un Parametro General
	 * @param criteria
	 */
	public void deleteParametroGeneral(Map criteria);
}