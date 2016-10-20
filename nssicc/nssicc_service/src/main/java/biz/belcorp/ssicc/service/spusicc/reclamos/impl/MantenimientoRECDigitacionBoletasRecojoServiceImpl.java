/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.reclamos.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import biz.belcorp.ssicc.dao.model.DatosConsultora;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.reclamos.MantenimientoRECDigitacionBoletasRecojoDAO;
import biz.belcorp.ssicc.dao.spusicc.reclamos.model.BoletaRecojoCabecera;
import biz.belcorp.ssicc.dao.spusicc.reclamos.model.BoletaRecojoDetalle;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECDigitacionBoletasRecojoService;



/**
 * @author peextjcairampoma
 *
 */
@Service("spusicc.mantenimientoRECDigitacionBoletasRecojoService")
public class MantenimientoRECDigitacionBoletasRecojoServiceImpl extends BaseService implements MantenimientoRECDigitacionBoletasRecojoService {
	
	@Resource(name="spusicc.mantenimientoRECDigitacionBoletasRecojoDAO")
	MantenimientoRECDigitacionBoletasRecojoDAO mantenimientoRECDigitacionBoletasRecojoDAO;
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.web.action.MantenimientoRECDigitacionBoletasRecojoService#getCalificacionesComisionesList(biz.belcorp.ssicc.spusicc.comision.web.form.MantenimientoRECDigitacionBoletasRecojoSearchForm)
	 */
	public List getBoletasRecojoCabeceraList(Map params){
		return mantenimientoRECDigitacionBoletasRecojoDAO.getBoletasRecojoCabeceraList(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECDigitacionBoletasRecojoService#getBoletaRecojoDetalleList(biz.belcorp.ssicc.spusicc.reclamos.model.BoletaRecojoCabecera)
	 */
	public List getBoletaRecojoDetalleList(	BoletaRecojoCabecera boletaRecojoCabecera) {
		return mantenimientoRECDigitacionBoletasRecojoDAO.getBoletaRecojoDetalleList(boletaRecojoCabecera);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECDigitacionBoletasRecojoService#getProductosByCriteria(java.util.Map)
	 */
	public List getProductosByCriteria(Map params){
		return mantenimientoRECDigitacionBoletasRecojoDAO.getProductosByCriteria(params);
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECDigitacionBoletasRecojoService#getBoletaRecojoDetalleDiscrepanteList(biz.belcorp.ssicc.spusicc.reclamos.model.BoletaRecojoDetalle)
	 */
	public List getBoletaRecojoDetalleDiscrepanteList(BoletaRecojoDetalle boletaRecojoDetalle) {
		return mantenimientoRECDigitacionBoletasRecojoDAO.getBoletaRecojoDetalleDiscrepanteList(boletaRecojoDetalle);
	}


	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECDigitacionBoletasRecojoService#updateBoletaRecojoCabeceraDetalle(biz.belcorp.ssicc.spusicc.reclamos.model.BoletaRecojoCabecera, java.util.List, java.util.List, java.util.List)
	 */
	public void updateBoletaRecojoCabeceraDetalle(BoletaRecojoCabecera boletaRecojoCabecera,List detalList,	List detalElimList, Map detalDiscList, Usuario usuario) {
		mantenimientoRECDigitacionBoletasRecojoDAO.updateBoletaRecojoCabeceraDetalle(boletaRecojoCabecera, detalList,	 detalElimList,  detalDiscList, usuario);
		
		Map params = new HashMap();
		params.put("numeroBoleta", boletaRecojoCabecera.getBoletaRecojo());
		params.put("usuario", usuario.getLogin());
		
		mantenimientoRECDigitacionBoletasRecojoDAO.executeReclamoProcesoAbono(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECDigitacionBoletasRecojoService#getCabeceraBoletaRecojo(java.util.Map)
	 */
	public List getCabeceraBoletaRecojo(Map criteria){
		return mantenimientoRECDigitacionBoletasRecojoDAO.getCabeceraBoletaRecojo(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECDigitacionBoletasRecojoService#getBloqueoConsultora(java.util.Map)
	 */
	public String getBloqueoConsultora(Map criteria){
		return mantenimientoRECDigitacionBoletasRecojoDAO.getBloqueoConsultora(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECDigitacionBoletasRecojoService#getDatosConsultora(java.util.Map)
	 */
	public DatosConsultora getDatosConsultora(Map criteria){
		return mantenimientoRECDigitacionBoletasRecojoDAO.getDatosConsultora(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECDigitacionBoletasRecojoService#getDetallesBoletaRecojo(java.util.Map)
	 */
	public List getDetallesBoletaRecojo(Map criteria){
		return mantenimientoRECDigitacionBoletasRecojoDAO.getDetallesBoletaRecojo(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECDigitacionBoletasRecojoService#getNovedadesRecojo(java.util.Map)
	 */
	public List getNovedadesRecojo(Map criteria){
		return mantenimientoRECDigitacionBoletasRecojoDAO.getNovedadesRecojo(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECDigitacionBoletasRecojoService#getCodigoCabeceraBoletaRecojo(java.util.Map)
	 */
	public String getCodigoCabeceraBoletaRecojo(Map criteria){
		return mantenimientoRECDigitacionBoletasRecojoDAO.getCodigoCabeceraBoletaRecojo(criteria);
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECDigitacionBoletasRecojoService#getCabeceraBoletaRecojoById(biz.belcorp.ssicc.spusicc.reclamos.model.BoletaRecojoCabecera)
	 */
	public BoletaRecojoCabecera getCabeceraBoletaRecojoById(BoletaRecojoCabecera boletaRecojoCabecera){
		return mantenimientoRECDigitacionBoletasRecojoDAO.getCabeceraBoletaRecojoById(boletaRecojoCabecera);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECDigitacionBoletasRecojoService#activarBoletaRecojoProcess(java.util.Map)
	 */
	public void activarBoletaRecojoProcess(Map params) {
		mantenimientoRECDigitacionBoletasRecojoDAO.activarBoletaRecojoProcess(params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECDigitacionBoletasRecojoService#eliminarBoletaRecojoProcess(java.util.Map)
	 */
	public void eliminarBoletaRecojoProcess(Map params) {
		mantenimientoRECDigitacionBoletasRecojoDAO.eliminarBoletaRecojoProcess(params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECDigitacionBoletasRecojoService#getConsultaRECBoletaRecojoDetail(java.lang.String)
	 */
	public List getConsultaRECBoletaRecojoDetail(String numeroBoleta) {
		return mantenimientoRECDigitacionBoletasRecojoDAO.getConsultaRECBoletaRecojoDetail(numeroBoleta);		
	}
}
