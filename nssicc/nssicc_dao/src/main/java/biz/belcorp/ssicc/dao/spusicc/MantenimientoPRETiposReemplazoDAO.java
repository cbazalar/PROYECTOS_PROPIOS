/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.model.TiposReemplazo;

public interface MantenimientoPRETiposReemplazoDAO extends DAO {

	List getTiposReemplazoList(Map criteria);

	TiposReemplazo getTiposReemplazo(String id);

	void insertTiposReemplazo(TiposReemplazo tiposReemplazo, Usuario usuario);

	void updateTiposReemplazo(TiposReemplazo tiposReemplazo, Usuario usuario);

	void deleteTiposReemplazo(String id);

	Integer getNextOidTiposReemplazo();
	
}
