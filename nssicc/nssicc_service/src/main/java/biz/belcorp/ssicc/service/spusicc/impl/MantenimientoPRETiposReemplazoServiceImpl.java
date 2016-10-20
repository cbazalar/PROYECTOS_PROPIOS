/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.MantenimientoPRETiposReemplazoDAO;
import biz.belcorp.ssicc.dao.spusicc.model.TiposReemplazo;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.MantenimientoPRETiposReemplazoService;


/**
 * @author Gonzalo Javier Huertas Agurto
 *
 */
@Service("spusicc.mantenimientoPRETiposReemplazoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoPRETiposReemplazoServiceImpl extends BaseService implements MantenimientoPRETiposReemplazoService {
	
	@Resource(name="spusicc.mantenimientoPRETiposReemplazoDAO")
	MantenimientoPRETiposReemplazoDAO mantenimientoPRETiposReemplazoDAO;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECPRETiposReemplazoService#getPRETiposReemplazo(java.util.Map)
	 */
	public List getTiposReemplazoList(Map criteria) {
		return mantenimientoPRETiposReemplazoDAO.getTiposReemplazoList(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECPRETiposReemplazoService#getPRETiposReemplazo(java.lang.String)
	 */
	public TiposReemplazo getTiposReemplazo(String id) {
		return mantenimientoPRETiposReemplazoDAO.getTiposReemplazo(id);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECPRETiposReemplazoService#insertPRETiposReemplazo(biz.belcorp.ssicc.spusicc.reclamos.model.PRETiposReemplazo)
	 */
	public void insertTiposReemplazo(TiposReemplazo tiposReemplazo, Usuario usuario) {
		Integer oid = mantenimientoPRETiposReemplazoDAO.getNextOidTiposReemplazo();
		tiposReemplazo.setOid(String.valueOf(oid));
		
		mantenimientoPRETiposReemplazoDAO.insertTiposReemplazo(tiposReemplazo, usuario);
		
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECPRETiposReemplazoService#updatePRETiposReemplazo(biz.belcorp.ssicc.spusicc.reclamos.model.PRETiposReemplazo)
	 */
	public void updateTiposReemplazo(TiposReemplazo tiposReemplazo, Usuario usuario) {
		mantenimientoPRETiposReemplazoDAO.updateTiposReemplazo(tiposReemplazo, usuario);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECPRETiposReemplazoService#deletePRETiposReemplazo(java.lang.String)
	 */
	public void deleteTiposReemplazo(String id) {
		mantenimientoPRETiposReemplazoDAO.deleteTiposReemplazo(id);
		
	}
}
