package biz.belcorp.ssicc.dao.spusicc.ape;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author nlopez@csigcomt.com
 *
 */

public interface ConsultaAPESistemaPicadoDAO extends DAO {

	/**
	 * Recupera la lista de Sistema de Picado
	 * 
	 * @param criteria
	 * @return
	 */
	
	public List getSistemaPicadoLista(Map criteria);
	
	
}
