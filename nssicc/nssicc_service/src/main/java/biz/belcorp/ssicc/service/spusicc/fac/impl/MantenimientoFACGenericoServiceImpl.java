package biz.belcorp.ssicc.service.spusicc.fac.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.spusicc.fac.MantenimientoFACGenericoDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.fac.MantenimientoFACGenericoService;

/**
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli</a>
 *
 */
@Service("spusicc.mantenimientoFACGenericoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoFACGenericoServiceImpl extends BaseService implements
	MantenimientoFACGenericoService {

	@Resource(name = "spusicc.mantenimientoFACGenericoDAO")
	MantenimientoFACGenericoDAO mantenimientoFACGenericoDAO;


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.service.MantenimientoFACGenericoService#deleteCierreFacturacion(java.util.Map)
	 */
	public void deleteCierreFacturacion(Map map) {
		mantenimientoFACGenericoDAO.deleteCierreFacturacion(map);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.service.MantenimientoFACGenericoService#getCierreFacturacion(java.util.Map)
	 */
	public List getCierreFacturacion(Map criteria) {
		return mantenimientoFACGenericoDAO.getCierreFacturacion(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.service.MantenimientoFACGenericoService#getConsolidadoCierreFacturacion(java.util.Map)
	 */
	public List getConsolidadoCierreFacturacion(Map criteria) {		
		return mantenimientoFACGenericoDAO.getConsolidadoCierreFacturacion(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.service.MantenimientoFACGenericoService#saveCierreFacturacion(java.util.Map)
	 */
	public void saveCierreFacturacion(Map map) {
		List listCierreZona=(List)map.get("listCierreZona");		
		List listCierreRegion=(List)map.get("listCierreRegion");
		String flagCierreCampanha = (String)map.get("flagCierreCampanha");
		String campanhaProceso=(String)map.get("campanhaProceso");
				
		Iterator it = listCierreZona.iterator();
		while(it.hasNext()){
			Map bean = (Map)it.next();
			String indicadorAccion = (String)bean.get("indicadorAccion");
			if(Constants.NRO_CERO.equals(indicadorAccion)){
				map.put("codigoRegion", bean.get("codigoRegion"));
				map.put("codigoZona", bean.get("codigoZona"));
				map.put("tipoCierre", "Z");
				mantenimientoFACGenericoDAO.saveCierreFacturacion(map);
			}
			
		}
		
		it = listCierreRegion.iterator();
		while(it.hasNext()){
			Map bean = (Map)it.next();
			String indicadorAccion = (String)bean.get("indicadorAccion");
			if(Constants.NRO_CERO.equals(indicadorAccion)){
				map.put("codigoRegion", bean.get("codigoRegion"));
				map.put("codigoZona", null);	
				map.put("tipoCierre", "R");
				mantenimientoFACGenericoDAO.saveCierreFacturacion(map);
			}
			
		}
		
		if("true".equals(flagCierreCampanha)){
			map.put("codigoCampanha", campanhaProceso);
			map.put("tipoCierre", "C");
			map.put("codigoRegion", null);
			map.put("codigoZona", null);				
			mantenimientoFACGenericoDAO.saveCierreFacturacion(map);
		}
		
		
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.service.MantenimientoFACGenericoService#updateCierreFacturacion(java.util.Map)
	 */
	public void updateCierreFacturacion(Map map) {
		List listCierreZona=(List)map.get("listCierreZona");		
		List listCierreRegion=(List)map.get("listCierreRegion");
		String flagCierreCampanha = (String)map.get("flagCierreCampanha");
		String campanhaProceso=(String)map.get("campanhaProceso");
				
		Iterator it = listCierreZona.iterator();
		while(it.hasNext()){
			Map bean = (Map)it.next();
			String indicadorAccion = (String)bean.get("indicadorAccion");
			if(Constants.NRO_CERO.equals(indicadorAccion)){
				map.put("codigoRegion", bean.get("codigoRegion"));
				map.put("codigoZona", bean.get("codigoZona"));
				map.put("tipoCierre", "Z");
				mantenimientoFACGenericoDAO.saveCierreFacturacion(map);
			}
//			if(Constants.NRO_UNO.equals(indicadorAccion)){
//				map.put("codigoRegion", bean.get("codigoRegion"));
//				map.put("codigoZona", bean.get("codigoZona"));
//				map.put("tipoCierre", "Z");
//				map.put("estadoRegistro", Constants.ESTADO_ACTIVO);
//				mantenimientoFACGenericoDAO.updateCierreFacturacion(map);
//			}
			
			if(Constants.NUMERO_DOS.equals(indicadorAccion)){
				map.put("codigoRegion", bean.get("codigoRegion"));
				map.put("codigoZona", bean.get("codigoZona"));
				map.put("tipoCierre", "Z");				
				map.put("estadoRegistro", Constants.ESTADO_INACTIVO);
				mantenimientoFACGenericoDAO.updateCierreFacturacion(map);
			}
			
		}
		
		it = listCierreRegion.iterator();
		while(it.hasNext()){
			Map bean = (Map)it.next();
			String indicadorAccion = (String)bean.get("indicadorAccion");
			if(Constants.NRO_CERO.equals(indicadorAccion)){
				map.put("codigoRegion", bean.get("codigoRegion"));
				map.put("codigoZona", null);
				map.put("tipoCierre", "R");
				mantenimientoFACGenericoDAO.saveCierreFacturacion(map);
			}
//			if(Constants.NRO_UNO.equals(indicadorAccion)){
//				map.put("codigoRegion", bean.get("codigoRegion"));
//				map.put("tipoCierre", "R");
//				mantenimientoFACGenericoDAO.updateCierreFacturacion(map);
//			}
			
			if(Constants.NUMERO_DOS.equals(indicadorAccion)){
				map.put("codigoRegion", bean.get("codigoRegion"));
				map.put("codigoZona", null);
				map.put("tipoCierre", "R");
				map.put("estadoRegistro", Constants.ESTADO_INACTIVO);
				mantenimientoFACGenericoDAO.updateCierreFacturacion(map);
			}			
		}
		String indicadorCierreCampanha = (String)map.get("indicadorCierreCampanha");
		if(Constants.NRO_UNO.equals(indicadorCierreCampanha) && "false".equals(flagCierreCampanha)){
			map.put("codigoRegion", null);
			map.put("codigoZona", null);
			map.put("tipoCierre", "C");
			map.put("estadoRegistro", Constants.ESTADO_INACTIVO);
			mantenimientoFACGenericoDAO.updateCierreFacturacion(map);
		}
		
		if(Constants.NRO_CERO.equals(indicadorCierreCampanha) && "true".equals(flagCierreCampanha)){
			map.put("codigoRegion", null);
			map.put("codigoZona", null);			
			map.put("codigoCampanha", campanhaProceso);
			map.put("tipoCierre", "C");
			mantenimientoFACGenericoDAO.saveCierreFacturacion(map);
		}
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.service.MantenimientoFACGenericoService#getRegionesPorCerrar(java.util.Map)
	 */
	public List getRegionesPorCerrar(Map map) {
		return mantenimientoFACGenericoDAO.getRegionesPorCerrar(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.service.MantenimientoFACGenericoService#getZonasPorCerrar(java.util.Map)
	 */
	public List getZonasPorCerrar(Map map) {
		return mantenimientoFACGenericoDAO.getZonasPorCerrar(map);
	}

	public List getCierreFacturacionRegion(Map map) {
		return mantenimientoFACGenericoDAO.getCierreFacturacionRegion(map);
	}

	public List getCierreFacturacionZona(Map map) {
		return mantenimientoFACGenericoDAO.getCierreFacturacionZona(map);
	}

	public String getCodigoRegion(String codigoZona) {
		return mantenimientoFACGenericoDAO.getCodigoRegion(codigoZona);
	}

	public String getDescripcionRegion(String codigoRegion) {
		return mantenimientoFACGenericoDAO.getDescripcionRegion(codigoRegion);
	}

	public String getDescripcionZona(String codigoZona) {
		return mantenimientoFACGenericoDAO.getDescripcionZona(codigoZona);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.service.MantenimientoFACGenericoService#deleteTemporalRegion(java.util.Map)
	 */
	public void deleteTemporalRegion(Map bean) {
		mantenimientoFACGenericoDAO.deleteTemporalRegion(bean);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.service.MantenimientoFACGenericoService#deleteTemporalZona(java.util.Map)
	 */
	public void deleteTemporalZona(Map bean) {
		mantenimientoFACGenericoDAO.deleteTemporalZona(bean);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.service.MantenimientoFACGenericoService#deleteTemporales()
	 */
	public void deleteTemporales() {
		mantenimientoFACGenericoDAO.deleteTemporales();
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.service.MantenimientoFACGenericoService#saveTemporalRegion(java.util.Map)
	 */
	public void saveTemporalRegion(Map bean) {
		mantenimientoFACGenericoDAO.saveTemporalRegion(bean);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.service.MantenimientoFACGenericoService#saveTemporalZona(java.util.Map)
	 */
	public void saveTemporalZona(Map bean) {
		mantenimientoFACGenericoDAO.saveTemporalZona(bean);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.service.MantenimientoFACGenericoService#updateConsolidadoCierreFacturacion(java.util.Map)
	 */
	public void updateConsolidadoCierreFacturacion(Map map) {
		mantenimientoFACGenericoDAO.updateCierreFacturacion(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.service.MantenimientoFACGenericoService#getAprobadasAnteriores(java.util.Map)
	 */
	public List getAprobadasAnteriores(Map map) {
		return mantenimientoFACGenericoDAO.getAprobadasAnteriores(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.service.MantenimientoFACGenericoService#getCanceladasPosterior(java.util.Map)
	 */
	public List getCanceladasPosterior(Map map) {
		return mantenimientoFACGenericoDAO.getCanceladasPosterior(map);
	}
	
	/* INI JJ  PER-SiCC-2012-0388 */
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.service.MantenimientoFACGenericoService#getCodigoZonaByOidZona(java.lang.Integer)
	 */
	public String getCodigoZonaByOidZona(Integer oidZona) {
		return mantenimientoFACGenericoDAO.getCodigoZonaByOidZona(oidZona);
	}
	/* FIN JJ  PER-SiCC-2012-0388 */
	
	/* INI SA PER-SiCC-2012-1120 */
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.service.MantenimientoFACGenericoService#getCodigoRegionByOidRegion(java.lang.Integer)
	 */
	public String getCodigoRegionByOidRegion(Integer oidRegion) {
		return mantenimientoFACGenericoDAO.getCodigoRegionByOidRegion(oidRegion);
	}
	/* FIN SA PER-SiCC-2012-1120 */

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.service.MantenimientoFACGenericoService#updateAutorizacionFacturacionElectronica(java.util.Map)
	 */
	public void updateAutorizacionFacturacionElectronica(Map map) {
		mantenimientoFACGenericoDAO.updateAutorizacionFacturacionElectronica(map);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.service.MantenimientoFACGenericoService#deleteZonasDeshabilitadasParaEnvio()
	 */
	public void deleteZonasDeshabilitadasParaEnvio() {
		mantenimientoFACGenericoDAO.deleteZonasDeshabilitadasParaEnvio();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.service.MantenimientoFACGenericoService#insertZonasDeshabilitadasParaEnvio(java.util.Map)
	 */
	public void insertZonasDeshabilitadasParaEnvio(Map params) {
		mantenimientoFACGenericoDAO.insertZonasDeshabilitadasParaEnvio(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.service.MantenimientoFACGenericoService#getZonasDeshabilitadas()
	 */
	public List getZonasDeshabilitadas() {
		return mantenimientoFACGenericoDAO.getZonasDeshabilitadas();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.service.MantenimientoFACGenericoService#getListaEstadoFacturacionDetalladoBF(java.util.Map)
	 */
	public List getListaEstadoFacturacionDetalladoBF(Map criteria) {
		return mantenimientoFACGenericoDAO.getListaEstadoFacturacionDetalladoBF(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.service.MantenimientoFACGenericoService#getListaEstadoFacturacionDetalladoNCND(java.util.Map)
	 */
	public List getListaEstadoFacturacionDetalladoNCND(Map criteria) {
		String  tipoDocumento = (String)criteria.get("tipoDocumento");
		if(StringUtils.equals(tipoDocumento, "020") || StringUtils.equals(tipoDocumento, "021") || StringUtils.equals(tipoDocumento, "022"))
			criteria.put("credito", new String("0"));
		else
			criteria.put("credito", new String("1"));
		
		return mantenimientoFACGenericoDAO.getListaEstadoFacturacionDetalladoNCND(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.service.MantenimientoFACGenericoService#getListaEstadoFacturacionDetalladoNCRetailBF(java.util.Map)
	 */
	public List getListaEstadoFacturacionDetalladoNCRetailBF(Map criteria) {
		return mantenimientoFACGenericoDAO.getListaEstadoFacturacionDetalladoNCRetailBF(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.service.MantenimientoFACGenericoService#getListaEstadoFacturacionDetalladoTodos(java.util.Map)
	 */
	public List getListaEstadoFacturacionDetalladoTodos(Map criteria) {
		String  tipoDocumento = (String)criteria.get("tipoDocumento");
		if(StringUtils.equals(tipoDocumento, "020") || StringUtils.equals(tipoDocumento, "021") || StringUtils.equals(tipoDocumento, "023"))
			criteria.put("credito", new String("0"));
		else
			criteria.put("credito", new String("1"));
		
		return mantenimientoFACGenericoDAO.getListaEstadoFacturacionDetalladoTodos(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.service.MantenimientoFACGenericoService#getListaEstadoFacturacionConsolidadoBF(java.util.Map)
	 */
	public List getListaEstadoFacturacionConsolidadoBF(Map criteria) {
		return mantenimientoFACGenericoDAO.getListaEstadoFacturacionConsolidadoBF(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.service.MantenimientoFACGenericoService#getListaEstadoFacturacionConsolidadoNCND(java.util.Map)
	 */
	public List getListaEstadoFacturacionConsolidadoNCND(Map criteria) {
		return mantenimientoFACGenericoDAO.getListaEstadoFacturacionConsolidadoNCND(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.service.MantenimientoFACGenericoService#getListaEstadoFacturacionConsolidadoNCRetailBF(java.util.Map)
	 */
	public List getListaEstadoFacturacionConsolidadoNCRetailBF(Map criteria) {
		return mantenimientoFACGenericoDAO.getListaEstadoFacturacionConsolidadoNCRetailBF(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.service.MantenimientoFACGenericoService#getListaEstadoFacturacionConsolidadoTodos(java.util.Map)
	 */
	public List getListaEstadoFacturacionConsolidadoTodos(Map criteria) {
		return mantenimientoFACGenericoDAO.getListaEstadoFacturacionConsolidadoTodos(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.service.MantenimientoFACGenericoService#getExisteCierreFacturacionRegion(java.util.Map)
	 */
	public Integer getExisteCierreFacturacionRegion(Map criteria) {
		return mantenimientoFACGenericoDAO.getExisteCierreFacturacionRegion(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.service.MantenimientoFACGenericoService#deleteTablasTemporales()
	 */
	public void deleteTablasTemporales() {
		mantenimientoFACGenericoDAO.deleteTablasTemporalesCabecera();
		mantenimientoFACGenericoDAO.deleteTablasTemporalesDetalle();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.fac.MantenimientoFACGenericoService#getZonasAbiertas(java.util.Map)
	 */
	@Override
	public List getZonasAbiertas(Map map) {
		return mantenimientoFACGenericoDAO.getZonasAbiertas(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.fac.MantenimientoFACGenericoService#getRegionesAbiertas(java.util.Map)
	 */
	@Override
	public List getRegionesAbiertas(Map map) {
		return mantenimientoFACGenericoDAO.getRegionesAbiertas(map);
	}
	
	
}
