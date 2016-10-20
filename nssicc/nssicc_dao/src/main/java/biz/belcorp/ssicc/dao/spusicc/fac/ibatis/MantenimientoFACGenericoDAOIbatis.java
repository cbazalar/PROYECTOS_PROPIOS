package biz.belcorp.ssicc.dao.spusicc.fac.ibatis;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.fac.MantenimientoFACGenericoDAO;

/**
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli</a>
 *
 */
@Repository("spusicc.mantenimientoFACGenericoDAO")
public class MantenimientoFACGenericoDAOIbatis extends BaseDAOiBatis implements
														MantenimientoFACGenericoDAO {

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.dao.MantenimientoFACGenericoDAO#getCierreFacturacion(java.util.Map)
	 */
	public List getCierreFacturacion(Map criteria) {
		List result =(List)getSqlMapClientTemplate().queryForList("spusicc.facturacion.MantenimientoFACSQL.getCierreFacturacion",criteria);
		return result;
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.dao.MantenimientoFACGenericoDAO#deleteCierreFacturacion(java.util.Map)
	 */
	public void deleteCierreFacturacion(Map map) {
		getSqlMapClientTemplate().update("spusicc.facturacion.MantenimientoFACSQL.deleteCierreFacturacion",map);		
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.dao.MantenimientoFACGenericoDAO#saveCierreFacturacion(java.util.Map)
	 */
	public void saveCierreFacturacion(Map map) {
			getSqlMapClientTemplate().insert("spusicc.facturacion.MantenimientoFACSQL.saveCierreFacturacion",map);			
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.dao.MantenimientoFACGenericoDAO#getConsolidadoCierreFacturacion(java.util.Map)
	 */
	public List getConsolidadoCierreFacturacion(Map criteria) {
		List listConso = new ArrayList();
		List result =(List)getSqlMapClientTemplate().queryForList("spusicc.facturacion.MantenimientoFACSQL.getConsolidadoCierreFacturacion",criteria);
		//ME TIENE LA LISTA A RECCORE PARA AGRUPAR LAS CANTIDADDES DE REGION Y ZONA QUE SE CONSOLIDO
		Iterator it = result.iterator();
		while(it.hasNext()){
			Map map = (Map)it.next();
			Map bean=(Map)getSqlMapClientTemplate().
					queryForObject("spusicc.facturacion.MantenimientoFACSQL.getAgruparConsolidadoCierreFacturacion",map);
			listConso.add(bean);
		}
		return listConso;
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.dao.MantenimientoFACGenericoDAO#updateCierreFacturacion(java.util.Map)
	 */
	public void updateCierreFacturacion(Map map) {
		getSqlMapClientTemplate().update("spusicc.facturacion.MantenimientoFACSQL.updateCierreFacturacion",map);
		
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.dao.MantenimientoFACGenericoDAO#getRegionesPorCerrar(java.util.Map)
	 */
	public List getRegionesPorCerrar(Map map) {
		List result =(List)getSqlMapClientTemplate().queryForList("spusicc.facturacion.MantenimientoFACSQL.getRegionesPorCerrar",map);
		return result;
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.dao.MantenimientoFACGenericoDAO#getZonasPorCerrar(java.util.Map)
	 */
	public List getZonasPorCerrar(Map map) {
		List result =(List)getSqlMapClientTemplate().queryForList("spusicc.facturacion.MantenimientoFACSQL.getZonasPorCerrar",map);
		return result;
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.dao.MantenimientoFACGenericoDAO#getCierreFacturacionRegion(java.util.Map)
	 */
	public List getCierreFacturacionRegion(Map map) {
		List result =(List)getSqlMapClientTemplate().queryForList("spusicc.facturacion.MantenimientoFACSQL.getCierreFacturacionRegion",map);
		Iterator it = result.iterator();
		while(it.hasNext()){
			Map bean =(Map)it.next();
			saveTemporalRegion(bean);
		}
		return result;
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.dao.MantenimientoFACGenericoDAO#getCierreFacturacionZona(java.util.Map)
	 */
	public List getCierreFacturacionZona(Map map) {
		log.debug("getCierreFacturacionZona "+map);
		List result =(List)getSqlMapClientTemplate().queryForList("spusicc.facturacion.MantenimientoFACSQL.getCierreFacturacionZona",map);
		Iterator it = result.iterator();
		while(it.hasNext()){
			Map bean =(Map)it.next();
			saveTemporalZona(bean);
		}		
		return result;
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.dao.MantenimientoFACGenericoDAO#getCodigoRegion(java.lang.String)
	 */
	public String getCodigoRegion(String codigoZona) {
		String result =(String)getSqlMapClientTemplate().queryForObject("spusicc.facturacion.MantenimientoFACSQL.getCodigoRegion",codigoZona);
		return result;
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.dao.MantenimientoFACGenericoDAO#getDescripcionRegion(java.lang.String)
	 */
	public String getDescripcionRegion(String codigoRegion) {
		String result =(String)getSqlMapClientTemplate().queryForObject("spusicc.facturacion.MantenimientoFACSQL.getDescripcionRegion",codigoRegion);
		return result;
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.dao.MantenimientoFACGenericoDAO#getDescripcionZona(java.lang.String)
	 */
	public String getDescripcionZona(String codigoZona) {
		String result =(String)getSqlMapClientTemplate().queryForObject("spusicc.facturacion.MantenimientoFACSQL.getDescripcionZona",codigoZona);
		return result;
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.dao.MantenimientoFACGenericoDAO#deleteTemporalRegion(java.util.Map)
	 */
	public void deleteTemporalRegion(Map bean) {
		getSqlMapClientTemplate().update("spusicc.facturacion.MantenimientoFACSQL.deleteTemporalRegion",bean);
		
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.dao.MantenimientoFACGenericoDAO#deleteTemporalZona(java.util.Map)
	 */
	public void deleteTemporalZona(Map bean) {
		getSqlMapClientTemplate().update("spusicc.facturacion.MantenimientoFACSQL.deleteTemporalZona",bean);
		
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.dao.MantenimientoFACGenericoDAO#deleteTemporales()
	 */
	public void deleteTemporales() {
		getSqlMapClientTemplate().update("spusicc.facturacion.MantenimientoFACSQL.deleteTempRegion");
		getSqlMapClientTemplate().update("spusicc.facturacion.MantenimientoFACSQL.deleteTempZona");
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.dao.MantenimientoFACGenericoDAO#saveTemporalRegion(java.util.Map)
	 */
	public void saveTemporalRegion(Map bean) {
		getSqlMapClientTemplate().insert("spusicc.facturacion.MantenimientoFACSQL.saveTemporalRegion",bean);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.dao.MantenimientoFACGenericoDAO#saveTemporalZona(java.util.Map)
	 */
	public void saveTemporalZona(Map bean) {
		getSqlMapClientTemplate().insert("spusicc.facturacion.MantenimientoFACSQL.saveTemporalZona",bean);
		
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.dao.MantenimientoFACGenericoDAO#getAprobadasAnteriores(java.util.Map)
	 */
	public List getAprobadasAnteriores(Map map) {
		List result =(List)getSqlMapClientTemplate().queryForList("spusicc.facturacion.MantenimientoFACSQL.getAprobadasAnteriores",map);
		return result;
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.dao.MantenimientoFACGenericoDAO#getCanceladasPosterior(java.util.Map)
	 */
	public List getCanceladasPosterior(Map map) {
		List result =(List)getSqlMapClientTemplate().queryForList("spusicc.facturacion.MantenimientoFACSQL.getCanceladasPosterior",map);
		return result;
	}

	/* INI JJ  PER-SiCC-2012-0388 */
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.dao.MantenimientoFACGenericoDAO#getCodigoZonaByOidZona(java.lang.Integer)
	 */
	public String getCodigoZonaByOidZona(Integer oidZona) {
		String result =(String)getSqlMapClientTemplate().queryForObject("spusicc.facturacion.MantenimientoFACSQL.getCodigoZonaByOidZona",oidZona);
		return result;
	}
	/* FIN JJ  PER-SiCC-2012-0388 */
	
	/* INI SA PER-SiCC-2012-1120 */
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.dao.MantenimientoFACGenericoDAO#getCodigoRegionByOidRegion(java.lang.Integer)
	 */
	public String getCodigoRegionByOidRegion(Integer oidRegion) {
		String result =(String)getSqlMapClientTemplate().queryForObject("spusicc.facturacion.MantenimientoFACSQL.getCodigoRegionByOidRegion",oidRegion);
		return result;
	}
	/* FIN SA PER-SiCC-2012-1120 */
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.dao.MantenimientoFACGenericoDAO#updateAutorizacionFacturacionElectronica(java.util.Map)
	 */
	public void updateAutorizacionFacturacionElectronica(Map map) {
		getSqlMapClientTemplate().update("spusicc.facturacion.MantenimientoFACSQL.updateAutorizacionFacturacionElectronica",map);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.dao.MantenimientoFACGenericoDAO#getTipoDocumento(java.util.Map)
	 */
	public String getTipoDocumento(Map criteria) {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.facturacion.MantenimientoFACSQL.getTipoDocumento", criteria);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.dao.MantenimientoFACGenericoDAO#insertDocumentoElectronico(java.util.Map)
	 */
	public void insertDocumentoElectronico(Map criteria) {
		getSqlMapClientTemplate().insert("spusicc.facturacion.MantenimientoFACSQL.insertDocumentoElectronico", criteria);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.dao.MantenimientoFACGenericoDAO#updateIndicadorFacturacionElectronica(java.util.Map)
	 */
	public void updateIndicadorFacturacionElectronica(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.facturacion.MantenimientoFACSQL.updateIndicadorFacturacionElectronica", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.dao.MantenimientoFACGenericoDAO#deleteZonasDeshabilitadasParaEnvio()
	 */
	public void deleteZonasDeshabilitadasParaEnvio() {
		getSqlMapClientTemplate().update("spusicc.facturacion.MantenimientoFACSQL.deleteZonasDeshabilitadasParaEnvio", null);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.dao.MantenimientoFACGenericoDAO#insertZonasDeshabilitadasParaEnvio(java.util.Map)
	 */
	public void insertZonasDeshabilitadasParaEnvio(Map params) {
		getSqlMapClientTemplate().insert("spusicc.facturacion.MantenimientoFACSQL.insertZonasDeshabilitadasParaEnvio", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.dao.MantenimientoFACGenericoDAO#getZonasDeshabilitadas()
	 */
	public List getZonasDeshabilitadas() {
		List result = (List)getSqlMapClientTemplate().queryForList("spusicc.facturacion.MantenimientoFACSQL.getZonasDeshabilitadas", null);
		return result;
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.dao.MantenimientoFACGenericoDAO#updateIndicadorProcesoNCRetail(java.util.Map)
	 */
	public void updateIndicadorProcesoNCRetail(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.facturacion.MantenimientoFACSQL.updateIndicadorProcesoNCRetail", criteria);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.dao.MantenimientoFACGenericoDAO#getListaEstadoFacturacionDetalladoBF(java.util.Map)
	 */
	public List getListaEstadoFacturacionDetalladoBF(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.facturacion.MantenimientoFACSQL.getListaEstadoFacturacionDetalladoBF", criteria);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.dao.MantenimientoFACGenericoDAO#getListaEstadoFacturacionDetalladoNCND(java.util.Map)
	 */
	public List getListaEstadoFacturacionDetalladoNCND(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.facturacion.MantenimientoFACSQL.getListaEstadoFacturacionDetalladoNCND", criteria);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.dao.MantenimientoFACGenericoDAO#getListaEstadoFacturacionDetalladoNCRetailBF(java.util.Map)
	 */
	public List getListaEstadoFacturacionDetalladoNCRetailBF(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.facturacion.MantenimientoFACSQL.getListaEstadoFacturacionDetalladoNCRetailBF", criteria);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.dao.MantenimientoFACGenericoDAO#getListaEstadoFacturacionDetalladoTodos(java.util.Map)
	 */
	public List getListaEstadoFacturacionDetalladoTodos(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.facturacion.MantenimientoFACSQL.getListaEstadoFacturacionDetalladoTodos", criteria);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.dao.MantenimientoFACGenericoDAO#getListaEstadoFacturacionConsolidadoBF(java.util.Map)
	 */
	public List getListaEstadoFacturacionConsolidadoBF(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.facturacion.MantenimientoFACSQL.getListaEstadoFacturacionConsolidadoBF", criteria);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.dao.MantenimientoFACGenericoDAO#getListaEstadoFacturacionConsolidadoNCND(java.util.Map)
	 */
	public List getListaEstadoFacturacionConsolidadoNCND(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.facturacion.MantenimientoFACSQL.getListaEstadoFacturacionConsolidadoNCND", criteria);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.dao.MantenimientoFACGenericoDAO#getListaEstadoFacturacionConsolidadoNCRetailBF(java.util.Map)
	 */
	public List getListaEstadoFacturacionConsolidadoNCRetailBF(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.facturacion.MantenimientoFACSQL.getListaEstadoFacturacionConsolidadoNCRetailBF", criteria);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.dao.MantenimientoFACGenericoDAO#getListaEstadoFacturacionConsolidadoTodos(java.util.Map)
	 */
	public List getListaEstadoFacturacionConsolidadoTodos(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.facturacion.MantenimientoFACSQL.getListaEstadoFacturacionConsolidadoTodos", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.dao.MantenimientoFACGenericoDAO#getExisteCierreFacturacionRegion(java.util.Map)
	 */
	public Integer getExisteCierreFacturacionRegion(Map criteria) {
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.facturacion.MantenimientoFACSQL.getExisteCierreFacturacionRegion", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.dao.MantenimientoFACGenericoDAO#deleteTablasTemporalesCabecera()
	 */
	public void deleteTablasTemporalesCabecera() {
		getSqlMapClientTemplate().delete("spusicc.facturacion.MantenimientoFACSQL.deleteTablasTemporalesCabecera",null);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fac.dao.MantenimientoFACGenericoDAO#deleteTablasTemporalesDetalle()
	 */
	public void deleteTablasTemporalesDetalle() {
		getSqlMapClientTemplate().delete("spusicc.facturacion.MantenimientoFACSQL.deleteTablasTemporalesDetalle",null);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.fac.MantenimientoFACGenericoDAO#getZonasAbiertas(java.util.Map)
	 */
	@Override
	public List getZonasAbiertas(Map map) {
		List result =(List)getSqlMapClientTemplate().queryForList("spusicc.facturacion.MantenimientoFACSQL.getZonasAbiertas",map);
		return result;
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.fac.MantenimientoFACGenericoDAO#getRegionesAbiertas(java.util.Map)
	 */
	@Override
	public List getRegionesAbiertas(Map map) {
		List result =(List)getSqlMapClientTemplate().queryForList("spusicc.facturacion.MantenimientoFACSQL.getRegionesAbiertas",map);
		return result;
	}
	
}