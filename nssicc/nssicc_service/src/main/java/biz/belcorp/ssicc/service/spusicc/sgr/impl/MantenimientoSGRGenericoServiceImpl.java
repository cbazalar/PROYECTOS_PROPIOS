package biz.belcorp.ssicc.service.spusicc.sgr.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.spusicc.sgr.MantenimientoSGRGenericoDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.sgr.MantenimientoSGRGenericoService;

/**
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli</a>
 *
 */
@Service("spusicc.mantenimientoSGRGenericoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoSGRGenericoServiceImpl extends BaseService implements MantenimientoSGRGenericoService {

	@Resource(name="spusicc.mantenimientoSGRGenericoDAO")
	MantenimientoSGRGenericoDAO mantenimientoSGRGenericoDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sgr.service.MantenimientoSGRGenericoService#getPoliza(java.util.Map)
	 */
	public List getPoliza(Map criteria) {	
		return mantenimientoSGRGenericoDAO.getPoliza(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sgr.service.MantenimientoSGRGenericoService#deletePoliza(java.util.Map)
	 */
	public void deletePoliza(Map map) {
		log.debug("delete Poliza");
		mantenimientoSGRGenericoDAO.deletePoliza(map);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sgr.service.MantenimientoSGRGenericoService#savePoliza(java.util.Map)
	 */
	public void savePoliza(Map map) {
		log.debug("save Poliza inicio");
		mantenimientoSGRGenericoDAO.savePoliza(map);
		//se insertara las pestanahs asociadas
		List listVigencia= (List)map.get("listVigencia");
		List listKit = (List)map.get("listKit");
		List listDescuento = (List)map.get("listDescuento");
		List listEstatus = (List)map.get("listEstatus");
		List listCampaniaGratuitas = (List)map.get("listCampaniaGratuitas");
		//
		String codigoPoliza =(String)map.get("codigoPoliza");
		Iterator it = listVigencia.iterator();
		while(it.hasNext()){
			Map hmap =(Map)it.next();
			String indicadorAccion = (String)hmap.get("indicadorAccion");
			String indicadorModificacion = (String)hmap.get("indicadorModificacion");
			hmap.put("login", (String)map.get("login"));
			hmap.put("codigoPoliza", codigoPoliza);
			if(Constants.NUMERO_CERO.equals(indicadorAccion))
				mantenimientoSGRGenericoDAO.saveVigencia(hmap);
			
			if(Constants.NUMERO_UNO.equals(indicadorAccion) &&
					Constants.NUMERO_UNO.equals(indicadorModificacion))
				mantenimientoSGRGenericoDAO.updateVigencia(hmap);
			
			if(Constants.NUMERO_DOS.equals(indicadorAccion))
				mantenimientoSGRGenericoDAO.deleteVigencia(hmap);
		}
		
		it = listKit.iterator();
		while(it.hasNext()){
			Map hmap =(Map)it.next();
			String indicadorAccion = (String)hmap.get("indicadorAccion");
			String indicadorModificacion = (String)hmap.get("indicadorModificacion");
			
			hmap.put("login", (String)map.get("login"));
			hmap.put("codigoPoliza", codigoPoliza);
			if(Constants.NUMERO_CERO.equals(indicadorAccion))
				mantenimientoSGRGenericoDAO.saveKit(hmap);
			
			if(Constants.NUMERO_UNO.equals(indicadorAccion) &&
					Constants.NUMERO_UNO.equals(indicadorModificacion))
				mantenimientoSGRGenericoDAO.updateKit(hmap);
			
			if(Constants.NUMERO_DOS.equals(indicadorAccion))
				mantenimientoSGRGenericoDAO.deleteKit(hmap);
		}
		
		it = listDescuento.iterator();
		while(it.hasNext()){
			Map hmap =(Map)it.next();
			String indicadorAccion = (String)hmap.get("indicadorAccion");
			String indicadorModificacion = (String)hmap.get("indicadorModificacion");
			
			hmap.put("login", (String)map.get("login"));
			hmap.put("codigoPoliza", codigoPoliza);
			if(Constants.NUMERO_CERO.equals(indicadorAccion))
					mantenimientoSGRGenericoDAO.saveDescto(hmap);
			
			if(Constants.NUMERO_UNO.equals(indicadorAccion) &&
					Constants.NUMERO_UNO.equals(indicadorModificacion))
				mantenimientoSGRGenericoDAO.updateDescto(hmap);
			
			if(Constants.NUMERO_DOS.equals(indicadorAccion))
				mantenimientoSGRGenericoDAO.deleteDescto(hmap);
		}
		
		it = listEstatus.iterator();
		while(it.hasNext()){
			Map hmap =(Map)it.next();
			String indicadorAccion = (String)hmap.get("indicadorAccion");
			String indicadorModificacion = (String)hmap.get("indicadorModificacion");
			
			hmap.put("login", (String)map.get("login"));
			hmap.put("codigoPoliza", codigoPoliza);
			
			if(Constants.NUMERO_CERO.equals(indicadorAccion))
				mantenimientoSGRGenericoDAO.saveEstatus(hmap);
			
			if(Constants.NUMERO_DOS.equals(indicadorAccion))
				mantenimientoSGRGenericoDAO.deleteEstatus(hmap);
		}
		
		it = listCampaniaGratuitas.iterator();
		while(it.hasNext()){
			Map hmap =(Map)it.next();
			String indicadorAccion = (String)hmap.get("indicadorAccion");
			String indicadorModificacion = (String)hmap.get("indicadorModificacion");
			
			hmap.put("login", (String)map.get("login"));
			hmap.put("codigoPoliza", codigoPoliza);
			
			if(Constants.NUMERO_CERO.equals(indicadorAccion))
				mantenimientoSGRGenericoDAO.saveCampaniaGratuitas(hmap);
			
			if(Constants.NUMERO_UNO.equals(indicadorAccion) &&
					Constants.NUMERO_UNO.equals(indicadorModificacion))
				mantenimientoSGRGenericoDAO.updateCampaniaGratuitas(hmap);
			
			if(Constants.NUMERO_DOS.equals(indicadorAccion))
				mantenimientoSGRGenericoDAO.deleteCampaniaGratuitas(hmap);
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sgr.service.MantenimientoSGRGenericoService#getDscto(java.util.Map)
	 */
	public List getDscto(Map bean) {
		return mantenimientoSGRGenericoDAO.getDscto(bean);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sgr.service.MantenimientoSGRGenericoService#getKit(java.util.Map)
	 */
	public List getKit(Map bean) {
		return mantenimientoSGRGenericoDAO.getKit(bean);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sgr.service.MantenimientoSGRGenericoService#getVigencia(java.util.Map)
	 */
	public List getVigencia(Map bean) {
		return mantenimientoSGRGenericoDAO.getVigencia(bean);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sgr.service.MantenimientoSGRGenericoService#getNumPolizasRegistradas(java.util.Map)
	 */
	public Integer getNumPolizasRegistradas(Map map) {		
		return mantenimientoSGRGenericoDAO.getNumPolizasRegistradas(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sgr.service.MantenimientoSGRGenericoService#getValidarTraslapeDescuento(java.util.Map)
	 */
	public Integer getValidarTraslapeDescuento(Map map) {
		return mantenimientoSGRGenericoDAO.getValidarTraslapeDescuento(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sgr.service.MantenimientoSGRGenericoService#getValidarTraslapeFechas(java.util.Map)
	 */
	public Integer getValidarTraslapeFechas(Map map) {
		return mantenimientoSGRGenericoDAO.getValidarTraslapeFechas(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sgr.service.MantenimientoSGRGenericoService#executeValidacionesInscripcionPoliza(java.util.Map)
	 */
	public void executeValidacionesInscripcionPoliza(Map map) {
		mantenimientoSGRGenericoDAO.executeValidacionesInscripcionPoliza(map);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sgr.service.MantenimientoSGRGenericoService#saveInscripcionPoliza(java.util.Map)
	 */
	public void saveInscripcionPoliza(Map map) {
		log.debug("saveInscripcionPoliza");
		mantenimientoSGRGenericoDAO.saveInscripcionPoliza(map);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sgr.service.MantenimientoSGRGenericoService#deleteInscripcionPoliza(java.util.Map)
	 */
	public void deleteInscripcionPoliza(Map map) {
		mantenimientoSGRGenericoDAO.deleteInscripcionPoliza(map);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sgr.service.MantenimientoSGRGenericoService#getBeneficiarios(java.util.Map)
	 */
	public List getBeneficiarios(Map map) {
		return mantenimientoSGRGenericoDAO.getBeneficiarios(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sgr.service.MantenimientoSGRGenericoService#getInscripcionPoliza(java.util.Map)
	 */
	public List getInscripcionPoliza(Map criteria) {
		return mantenimientoSGRGenericoDAO.getInscripcionPoliza(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sgr.service.MantenimientoSGRGenericoService#updateInscripcionPoliza(java.util.Map)
	 */
	public void updateInscripcionPoliza(Map map) {
		mantenimientoSGRGenericoDAO.updateInscripcionPoliza(map);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sgr.service.MantenimientoSGRGenericoService#executeGenerarReporteControlAbonos(java.util.Map)
	 */
	public void executeGenerarReporteControlAbonos(Map map) {
		mantenimientoSGRGenericoDAO.executeGenerarReporteControlAbonos(map);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sgr.service.MantenimientoSGRGenericoService#getEstatus(java.util.Map)
	 */
	public List getEstatus(Map bean) {
		return mantenimientoSGRGenericoDAO.getEstatus(bean);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sgr.service.MantenimientoSGRGenericoService#getCampaniaGratuita(java.util.Map)
	 */
	public List getCampaniaGratuita(Map bean) {
		return mantenimientoSGRGenericoDAO.getCampaniaGratuita(bean);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sgr.service.MantenimientoSGRGenericoService#updateInscripcionPolizaActiva(java.util.Map)
	 */
	public void updateInscripcionPolizaActiva(Map map) {
		mantenimientoSGRGenericoDAO.updateInscripcionPolizaActiva(map);
		
	}
}