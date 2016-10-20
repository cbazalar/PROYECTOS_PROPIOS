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

import biz.belcorp.ssicc.dao.spusicc.reclamos.MantenimientoRECMovitoDevolucionDAO;
import biz.belcorp.ssicc.dao.spusicc.reclamos.model.MotivoDevolucion;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECMotivoDevolucionService;

/**
 * @author Giovanni Ascarza
 *
 */
@Service("spusicc.mantenimientoRECMotivoDevolucionService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoRECMotivoDevolucionServiceImpl extends BaseService implements MantenimientoRECMotivoDevolucionService {
	
	@Resource(name="spusicc.mantenimientoRECMotivoDevolucionDAO")
	MantenimientoRECMovitoDevolucionDAO mantenimientoRECMovitoDevolucionDAO;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECMovitoDevolucionService#getMovitoDevolucion(java.util.Map)
	 */
	public List getMovitoDevolucionList(Map criteria) {
		return mantenimientoRECMovitoDevolucionDAO.getMovitoDevolucionList(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECMotivoDevolucionService#getMovitoDevolucion(java.lang.String)
	 */
	public MotivoDevolucion getMovitoDevolucion(String id) {
		return mantenimientoRECMovitoDevolucionDAO.getMovitoDevolucion(id);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECMotivoDevolucionService#insertMotivoDevolucion(biz.belcorp.ssicc.spusicc.reclamos.model.MotivoDevolucion)
	 */
	public void insertMotivoDevolucion(MotivoDevolucion motivo) {
		Integer oid = mantenimientoRECMovitoDevolucionDAO.getNextOidMovitoDevolucion();
		motivo.setOidMotiDevo(String.valueOf(oid));
		
		mantenimientoRECMovitoDevolucionDAO.insertMotivoDevolucion(motivo);
		
		mantenimientoRECMovitoDevolucionDAO.insertMovitoDescripcion(motivo);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECMotivoDevolucionService#updateMotivoDevolucion(biz.belcorp.ssicc.spusicc.reclamos.model.MotivoDevolucion)
	 */
	public void updateMotivoDevolucion(MotivoDevolucion motivo) {
		mantenimientoRECMovitoDevolucionDAO.updateMotivoDevolucion(motivo);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECMotivoDevolucionService#updateMotivoDevolucionIndicador(biz.belcorp.ssicc.spusicc.reclamos.model.MotivoDevolucion)
	 */
	public void updateMotivoDevolucionIndicador(MotivoDevolucion motivo) {
		mantenimientoRECMovitoDevolucionDAO.updateMotivoDevolucionIndicador(motivo);
		
	}



	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECMotivoDevolucionService#deleteMotivoDevolucion(java.lang.String)
	 */
	public void deleteMotivoDevolucion(String id) {
		mantenimientoRECMovitoDevolucionDAO.deleteMotivoDevolucion(id);
		
	}


	
}
