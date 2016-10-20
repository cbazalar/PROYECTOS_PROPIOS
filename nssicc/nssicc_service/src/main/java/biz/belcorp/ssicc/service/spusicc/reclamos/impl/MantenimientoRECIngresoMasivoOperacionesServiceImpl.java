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

import biz.belcorp.ssicc.dao.spusicc.reclamos.MantenimientoRECIngresoMasivoOperacionesDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECIngresoMasivoOperacionesService;

/**
 * @author vcupe - Vidal Cupe Quispe
 *
 */
@Service("spusicc.mantenimientoRECIngresoMasivoOperacionesService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoRECIngresoMasivoOperacionesServiceImpl extends BaseService implements MantenimientoRECIngresoMasivoOperacionesService{

	@Resource(name="spusicc.mantenimientoRECIngresoMasivoOperacionesDAO")
	MantenimientoRECIngresoMasivoOperacionesDAO mantenimientoRECIngresoMasivoOperacionesDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECIngresoMasivoOperacionesServiceImpl#getTipoOperacionList(java.util.Map)
	 */
	public List getTipoOperacionList(Map params) {
		return this.mantenimientoRECIngresoMasivoOperacionesDAO.getTipoOperacionList(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECIngresoMasivoOperacionesServiceImpl#getObtenerCampahniaActual(java.util.Map)
	 */
	public String getObtenerCampahniaActual(Map criteria) {
		return this.mantenimientoRECIngresoMasivoOperacionesDAO.getObtenerCampahniaActual(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECIngresoMasivoOperacionesServiceImpl#getObtenerCampahniaActiva(java.util.Map)
	 */
	public String getObtenerCampahniaActiva(Map criteria) {
		return this.mantenimientoRECIngresoMasivoOperacionesDAO.getObtenerCampahniaActiva(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECIngresoMasivoOperacionesServiceImpl#getTipoMasivoOperacionesList()
	 */
	public List getTipoMasivoOperacionesList(){
		return mantenimientoRECIngresoMasivoOperacionesDAO.getTipoMasivoOperacionesList();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECIngresoMasivoOperacionesServiceImpl#ginsertIngresoMasivoOperaciones()
	 */
	public void insertIngresoMasivoOperaciones(List cabeceraList, List detalleList, Map params){
		 mantenimientoRECIngresoMasivoOperacionesDAO.insertIngresoMasivoOperaciones(cabeceraList, detalleList, params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECIngresoMasivoOperacionesService#getCuv(java.util.Map)
	 */
	public List getCuv(Map criteria) {
		// TODO Auto-generated method stub
		return  mantenimientoRECIngresoMasivoOperacionesDAO.getCuv(criteria);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECIngresoMasivoOperacionesService#getMotivoReclamoList(java.util.Map)
	 */
	public List getMotivoReclamoList(Map criteria) {
		return this.mantenimientoRECIngresoMasivoOperacionesDAO.getMotivoReclamoList(criteria);
	}
	
	
}