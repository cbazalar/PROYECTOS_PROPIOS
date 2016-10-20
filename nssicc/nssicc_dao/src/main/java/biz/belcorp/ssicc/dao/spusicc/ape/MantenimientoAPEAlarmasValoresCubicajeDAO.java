package biz.belcorp.ssicc.dao.spusicc.ape;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
/**
 * @author David Ramos
 */

public interface MantenimientoAPEAlarmasValoresCubicajeDAO extends DAO{
	/**
	 * Devuelve la lista de Productos 
	 * @param criteria
	 * @return
	 */
	public List getAlarmasValoresCubicajeList(Map criteria);
	
}
