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

import biz.belcorp.ssicc.dao.spusicc.reclamos.MantenimientoRECOperacionesReclamoDAO;
import biz.belcorp.ssicc.dao.spusicc.reclamos.model.OperacionReclamo;
import biz.belcorp.ssicc.dao.spusicc.reclamos.model.TipoOperacion;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECOperacionesReclamoService;


/**
 * @author <a href="aoviedo@sigcomt.com">Aurelio Oviedo</a>
 *
 */
@Service("spusicc.mantenimientoRECOperacionesReclamoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoRECOperacionesReclamoServiceImpl extends BaseService implements MantenimientoRECOperacionesReclamoService {
	
	@Resource(name="spusicc.mantenimientoRECOperacionesReclamoDAO")
	MantenimientoRECOperacionesReclamoDAO mantenimientoRECOperacionesReclamoDAO;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECOperacionesReclamoService#getOperacionesReclamoList(java.util.Map)
	 */
	public List getOperacionesReclamoList(Map criteria) {
		return mantenimientoRECOperacionesReclamoDAO.getOperacionesReclamoList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECOperacionesReclamoService#getMotivosBloqueoList(java.util.Map)
	 */
	public List getMotivosBloqueoList(Map criteria) {
		return mantenimientoRECOperacionesReclamoDAO.getMotivosBloqueoList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECOperacionesReclamoService#getMovimientosAlmacenList(java.util.Map)
	 */
	public List getMovimientosAlmacenList(Map criteria) {
		return mantenimientoRECOperacionesReclamoDAO.getMovimientosAlmacenList(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECOperacionesReclamoService#getOperacionReclamo(java.util.Map)
	 */
	public OperacionReclamo getOperacionReclamo(Map criteria) {
		return mantenimientoRECOperacionesReclamoDAO.getOperacionReclamo(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECOperacionesReclamoService#getExisteOperacionReclamoByCodigo(java.util.Map)
	 */
	public int getExisteOperacionReclamoByCodigo(Map criteria) {
		return mantenimientoRECOperacionesReclamoDAO.getExisteOperacionReclamoByCodigo(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECOperacionesReclamoService#getNextOidOperacionReclamo(java.util.Map)
	 */
	public int getNextOidOperacionReclamo(Map criteria) {
		return mantenimientoRECOperacionesReclamoDAO.getNextOidOperacionReclamo(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECOperacionesReclamoService#insertOperacionReclamo(biz.belcorp.ssicc.spusicc.reclamos.model.OperacionReclamo)
	 */
	public void insertOperacionReclamo(OperacionReclamo operacionReclamo) {
		mantenimientoRECOperacionesReclamoDAO.insertOperacionReclamo(operacionReclamo);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECOperacionesReclamoService#updateOperacionReclamo(biz.belcorp.ssicc.spusicc.reclamos.model.OperacionReclamo)
	 */
	public void updateOperacionReclamo(OperacionReclamo operacionReclamo) {
		mantenimientoRECOperacionesReclamoDAO.updateOperacionReclamo(operacionReclamo);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECOperacionesReclamoService#deleteOperacionReclamo(java.lang.String)
	 */
	public void deleteOperacionReclamo(String id) {
		mantenimientoRECOperacionesReclamoDAO.deleteOperacionReclamo(id);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECOperacionesReclamoService#getMotivosRechazoDesbloqueoList(java.util.Map)
	 */
	public List getMotivosRechazoDesbloqueoList(Map criteria) {
		return mantenimientoRECOperacionesReclamoDAO.getMotivosRechazoDesbloqueoList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECOperacionesReclamoService#getNextOidTipoOperacion(java.util.Map)
	 */
	public int getNextOidTipoOperacion(Map criteria) {
		return mantenimientoRECOperacionesReclamoDAO.getNextOidTipoOperacion(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECOperacionesReclamoService#getExisteTipoOperacionByCodigo(java.util.Map)
	 */
	public int getExisteTipoOperacionByCodigo(Map criteria) {
		return mantenimientoRECOperacionesReclamoDAO.getExisteTipoOperacionByCodigo(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECOperacionesReclamoService#getTipoOperacionListByOperacion(java.util.Map)
	 */
	public List getTipoOperacionListByOperacion(Map criteria) {
		return mantenimientoRECOperacionesReclamoDAO.getTipoOperacionListByOperacion(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECOperacionesReclamoService#insertTipoOperacion(biz.belcorp.ssicc.spusicc.reclamos.model.TipoOperacion)
	 */
	public void insertTipoOperacion(TipoOperacion tipoOperacion) {
		mantenimientoRECOperacionesReclamoDAO.insertTipoOperacion(tipoOperacion);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECOperacionesReclamoService#updateTipoOperacion(biz.belcorp.ssicc.spusicc.reclamos.model.TipoOperacion)
	 */
	public void updateTipoOperacion(TipoOperacion tipoOperacion) {
		mantenimientoRECOperacionesReclamoDAO.updateTipoOperacion(tipoOperacion);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECOperacionesReclamoService#deleteTipoOperacion(java.util.Map)
	 */
	public void deleteTipoOperacion(Map criteria) {
		mantenimientoRECOperacionesReclamoDAO.deleteTipoOperacion(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECOperacionesReclamoService#getTiposSolicitudList()
	 */
	public List getTiposSolicitudList() {
		return mantenimientoRECOperacionesReclamoDAO.getTiposSolicitudList();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECOperacionesReclamoService#getTiposSolicitudGeneraList()
	 */
	public List getTiposSolicitudGeneraList() {
		return mantenimientoRECOperacionesReclamoDAO.getTiposSolicitudGeneraList();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECOperacionesReclamoService#getAlmacenList(java.util.Map)
	 */
	public List getAlmacenList(Map criteria) {
		return mantenimientoRECOperacionesReclamoDAO.getAlmacenList(criteria);
	}
}