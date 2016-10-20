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

import biz.belcorp.ssicc.dao.spusicc.reclamos.MantenimientoRECGestionBoletasRecojoDiscrepanteDAO;
import biz.belcorp.ssicc.dao.spusicc.reclamos.model.BoletaRecojoCabecera;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECGestionBoletasRecojoDiscrepanteService;

/**
 * @author peextjcairampoma
 *
 */
@Service("spusicc.mantenimientoRECGestionBoletasRecojoDiscrepanteService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoRECGestionBoletasRecojoDiscrepanteServiceImpl extends BaseService implements MantenimientoRECGestionBoletasRecojoDiscrepanteService {
	
	@Resource(name="spusicc.mantenimientoRECGestionBoletasRecojoDiscrepanteDAO")
	MantenimientoRECGestionBoletasRecojoDiscrepanteDAO mantenimientoRECGestionBoletasRecojoDiscrepanteDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.web.action.MantenimientoRECGestionBoletasRecojoDiscrepanteService#getCalificacionesComisionesList(biz.belcorp.ssicc.spusicc.comision.web.form.MantenimientoRECGestionBoletasRecojoDiscrepanteSearchForm)
	 */
	public List getBoletasRecojoDiscrepantesList(Map params){
		return mantenimientoRECGestionBoletasRecojoDiscrepanteDAO.getBoletasRecojoDiscrepantesList(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECGestionBoletasRecojoDiscrepanteService#updateBoletaRecojoDiscrepanteCabeceraDetalle(biz.belcorp.ssicc.spusicc.reclamos.model.BoletaRecojoCabecera, java.util.List, java.util.Map)
	 */
	public void updateBoletaRecojoDiscrepanteCabeceraDetalle(BoletaRecojoCabecera boletaRecojoCabecera, List detalList,	Map detalDiscList) {
		mantenimientoRECGestionBoletasRecojoDiscrepanteDAO.updateBoletaRecojoDiscrepanteCabeceraDetalle(boletaRecojoCabecera, detalList,detalDiscList);
	}



	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECGestionBoletasRecojoDiscrepanteService#executeProcesoBoletaRecojoDiscrepante(java.util.Map)
	 */
	public void executeProcesoBoletaRecojoDiscrepante(Map params) {
		
		mantenimientoRECGestionBoletasRecojoDiscrepanteDAO.executeProcesoBoletaRecojoDiscrepante(params);
		
	}


	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECGestionBoletasRecojoDiscrepanteService#getCodigoVentaListByCriteria(java.util.Map)
	 */
	public List getCodigoVentaListByCriteria(Map params) {
		return mantenimientoRECGestionBoletasRecojoDiscrepanteDAO.getCodigoVentaListByCriteria(params);
	}


	

	
	
}
