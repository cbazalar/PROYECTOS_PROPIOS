/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.reclamos.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.reclamos.MantenimientoExcepcionesValidacionesDAO;
import biz.belcorp.ssicc.dao.spusicc.reclamos.model.ExcepcionesValidaciones;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoExcepcionesValidacionesService;

/**
 * @author Danny Amaro
 *
 */
@Service("spusicc.mantenimientoExcepcionesValidacionesService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoExcepcionesValidacionesServiceImpl extends BaseService implements MantenimientoExcepcionesValidacionesService{

	@Resource(name="spusicc.mantenimientoExcepcionesValidacionesDAO")
	MantenimientoExcepcionesValidacionesDAO mantenimientoExcepcionesValidacionesDAO;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoExcepcionesValidacionesService#insertExcepcionesValidaciones(biz.belcorp.ssicc.spusicc.reclamos.model.ExcepcionesValidaciones, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertExcepcionesValidaciones(
			ExcepcionesValidaciones excepcionesValidaciones, Usuario usuario) {
		
		Long id = mantenimientoExcepcionesValidacionesDAO.getDevuelveIdSgteCodExcepcionesValidaciones();
		excepcionesValidaciones.setCodigo(id);
		mantenimientoExcepcionesValidacionesDAO.insertExcepcionesValidaciones(excepcionesValidaciones, usuario);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoExcepcionesValidacionesService#insertExcepcionesValidacionesHistorico(biz.belcorp.ssicc.spusicc.reclamos.model.ExcepcionesValidaciones, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertExcepcionesValidacionesHistorico(
			ExcepcionesValidaciones excepcionesValidaciones, Usuario usuario) {
		
		mantenimientoExcepcionesValidacionesDAO.insertExcepcionesValidacionesHistorico(excepcionesValidaciones, usuario);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoExcepcionesValidacionesService#removeExcepcionesValidacione(java.util.Map)
	 */
	public void removeExcepcionesValidacione(Map criteria) {
		
		mantenimientoExcepcionesValidacionesDAO.removeExcepcionesValidacione(criteria);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoExcepcionesValidacionesService#getExcepcionesValidaciones(java.util.Map)
	 */
	public List getExcepcionesValidaciones(Map criteria) {
		
		return mantenimientoExcepcionesValidacionesDAO.getExcepcionesValidaciones(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoExcepcionesValidacionesService#getExcepcionesValidacionesExist(java.util.Map)
	 */
	public List getExcepcionesValidacionesExist(Map criteria) {
		
		return mantenimientoExcepcionesValidacionesDAO.getExcepcionesValidacionesExist(criteria);				
	}

}
