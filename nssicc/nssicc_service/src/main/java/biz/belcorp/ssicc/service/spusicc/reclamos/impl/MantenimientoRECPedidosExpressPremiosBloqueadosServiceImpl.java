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

import biz.belcorp.ssicc.dao.spusicc.reclamos.MantenimientoRECPedidosExpressPremiosBloqueadosDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECPedidosExpressPremiosBloqueadosService;

/**
 * @author vcupe - Vidal Cupe Quispe
 *
 */
@Service("spusicc.mantenimientoRECPedidosExpressPremiosBloqueadosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoRECPedidosExpressPremiosBloqueadosServiceImpl extends BaseService implements MantenimientoRECPedidosExpressPremiosBloqueadosService{

	@Resource(name="spusicc.mantenimientoRECPedidosExpressPremiosBloqueadosDAO")
	MantenimientoRECPedidosExpressPremiosBloqueadosDAO mantenimientoRECPedidosExpressPremiosBloqueadosDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECPedidosExpressPremiosBloqueadosServiceImpl#getConcursosList()
	 */
	public List getConcursosList() {
		return this.mantenimientoRECPedidosExpressPremiosBloqueadosDAO.getConcursosList();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECPedidosExpressPremiosBloqueadosServiceImpl#getPremiosList()
	 */
	public List getPremiosList() {
		return this.mantenimientoRECPedidosExpressPremiosBloqueadosDAO.getPremiosList();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECPedidosExpressPremiosBloqueadosServiceImpl#getRegionesList()
	 */
	public List getRegionesList() {
		return this.mantenimientoRECPedidosExpressPremiosBloqueadosDAO.getRegionesList();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECPedidosExpressPremiosBloqueadosServiceImpl#getPedidosExpressPremBloqList(java.util.Map)
	 */
	public List getPedidosExpressPremBloqList(Map criteria){
		return this.mantenimientoRECPedidosExpressPremiosBloqueadosDAO.getPedidosExpressPremBloqList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECIngresoMasivoOperacionesServiceImpl#getObtenerCampahniaActual(java.util.Map)
	 */
	public String getObtenerCampahniaActual(Map criteria) {
		return this.mantenimientoRECPedidosExpressPremiosBloqueadosDAO.getObtenerCampahniaActual(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECIngresoMasivoOperacionesServiceImpl#getObtenerCampahniaActiva(java.util.Map)
	 */
	public String getObtenerCampahniaActiva(Map criteria) {
		return this.mantenimientoRECPedidosExpressPremiosBloqueadosDAO.getObtenerCampahniaActiva(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECIngresoMasivoOperacionesServiceImpl#ginsertIngresoMasivoOperaciones()
	 */
	public void insertIngresoMasivoOperaciones(List cabeceraList, List detalleList, Map params){
		mantenimientoRECPedidosExpressPremiosBloqueadosDAO.insertIngresoMasivoOperaciones(cabeceraList, detalleList, params);
	}
	
	
}