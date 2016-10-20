package biz.belcorp.ssicc.service.spusicc;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.model.TiposReemplazo;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author Gonzalo Huertas 
 *
 */
public interface MantenimientoPRETiposReemplazoService extends Service {

	public List getTiposReemplazoList(Map criteria);

	public TiposReemplazo getTiposReemplazo(String id);

	public void insertTiposReemplazo(TiposReemplazo tiposReemplazo, Usuario usuario);

	public void updateTiposReemplazo(TiposReemplazo tiposReemplazo, Usuario usuario);

	public void deleteTiposReemplazo(String id);

}