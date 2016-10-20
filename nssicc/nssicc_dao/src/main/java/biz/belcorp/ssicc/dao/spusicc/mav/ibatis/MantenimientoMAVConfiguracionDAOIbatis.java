package biz.belcorp.ssicc.dao.spusicc.mav.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.mae.model.CriterioBusqueda;
import biz.belcorp.ssicc.dao.spusicc.mav.MantenimientoMAVConfiguracionDAO;
import biz.belcorp.ssicc.dao.spusicc.mav.model.ActividadTipoOferta;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
@Repository("spusicc.mantenimientoMAVConfiguracionDAO")
public class MantenimientoMAVConfiguracionDAOIbatis extends BaseDAOiBatis implements
	MantenimientoMAVConfiguracionDAO {
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.dao.MantenimientoMAVConfiguracionDAO#getActividades()
	 */
	public List getActividades() {
		return getSqlMapClientTemplate().queryForList("spusicc.mav.MantenimientoMAVSQL.getActividades");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.dao.MantenimientoMAVConfiguracionDAO#getTiposOferta(java.lang.String)
	 */
	public List getTiposOferta(String oidActividad) {
		return getSqlMapClientTemplate().queryForList("spusicc.mav.MantenimientoMAVSQL.getTiposOferta", oidActividad);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.dao.MantenimientoMAVConfiguracionDAO#getConfiguracionesByCriteria(java.util.Map)
	 */
	public List getConfiguracionesByCriteria(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.mav.MantenimientoMAVSQL.getConfiguracionesByCriteria", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.dao.MantenimientoMAVConfiguracionDAO#getDatosActividad(java.lang.String)
	 */
	public List getDatosActividad(String oidActividad) {
		return getSqlMapClientTemplate().queryForList("spusicc.mav.MantenimientoMAVSQL.getDatosActividad", oidActividad);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.dao.MantenimientoMAVConfiguracionDAO#getMaxConfiguracion()
	 */
	public String getMaxConfiguracion() {
		return (String) getSqlMapClientTemplate().queryForObject(
				"spusicc.mav.MantenimientoMAVSQL.getMaxConfiguracion");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.dao.MantenimientoMAVConfiguracionDAO#insertConfiguracion(java.util.Map)
	 */
	public void insertConfiguracion(Map params) {
		getSqlMapClientTemplate().insert(
				"spusicc.mav.MantenimientoMAVSQL.insertConfiguracion", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.dao.MantenimientoMAVConfiguracionDAO#updateConfiguracion(java.util.Map)
	 */
	public void updateConfiguracion(Map params) {
		getSqlMapClientTemplate().update(
				"spusicc.mav.MantenimientoMAVSQL.updateConfiguracion", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.dao.MantenimientoMAVConfiguracionDAO#deleteConfiguracion(java.util.Map)
	 */
	public void deleteConfiguracion(Map params) {
		getSqlMapClientTemplate().update(
				"spusicc.mav.MantenimientoMAVSQL.deleteConfiguracion", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.dao.MantenimientoMAVConfiguracionDAO#getConfiguracion(java.util.Map)
	 */
	public Map getConfiguracion(Map criteria) {
		return (Map) getSqlMapClientTemplate().queryForObject(
				"spusicc.mav.MantenimientoMAVSQL.getConfiguracion", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.dao.MantenimientoMAVConfiguracionDAO#getConsideracion(java.util.Map)
	 */
	public List getConsideracion(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.mav.MantenimientoMAVSQL.getConsideracion", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.dao.MantenimientoMAVConfiguracionDAO#getRestriccion(java.util.Map)
	 */
	public List getRestriccion(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.mav.MantenimientoMAVSQL.getRestriccion", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.dao.MantenimientoMAVConfiguracionDAO#saveConRestCabecera(java.util.Map)
	 */
	public void saveConRestCabecera(Map params) {
		getSqlMapClientTemplate().update("spusicc.mav.MantenimientoMAVSQL.saveConRestCabecera", params);			
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.dao.MantenimientoMAVConfiguracionDAO#saveConRestDetalle(java.util.Map)
	 */
	public void saveConRestDetalle(Map params) {
		getSqlMapClientTemplate().update("spusicc.mav.MantenimientoMAVSQL.saveConRestDetalle", params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.dao.MantenimientoMAVConfiguracionDAO#updateConRestCabecera(java.util.Map)
	 */
	public void updateConRestCabecera(Map params) {
		getSqlMapClientTemplate().update("spusicc.mav.MantenimientoMAVSQL.updateConRestCabecera", params);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.dao.MantenimientoMAVConfiguracionDAO#updateConRestDetalle(java.util.Map)
	 */
	public void updateConRestDetalle(Map params) {
		getSqlMapClientTemplate().update("spusicc.mav.MantenimientoMAVSQL.updateConRestDetalle", params);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.dao.MantenimientoMAVConfiguracionDAO#deleteConRestCabecera(java.util.Map)
	 */
	public void deleteConRestCabecera(Map params) {
		getSqlMapClientTemplate().delete("spusicc.mav.MantenimientoMAVSQL.deleteConRestCabecera", params);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.dao.MantenimientoMAVConfiguracionDAO#deleteConRestDetalle(java.util.Map)
	 */
	public void deleteConRestDetalle(Map params) {
		getSqlMapClientTemplate().delete("spusicc.mav.MantenimientoMAVSQL.deleteConRestDetalle", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.dao.MantenimientoMAVConfiguracionDAO#getRestConsideracion(java.util.Map)
	 */
	public List getRestConsideracion(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.mav.MantenimientoMAVSQL.getRestConsideracion", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.dao.MantenimientoMAVConfiguracionDAO#getDetalleConsRest(java.util.Map)
	 */
	public List getDetalleConsRest(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.mav.MantenimientoMAVSQL.getDetalleConsRest", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.dao.MantenimientoMAVConfiguracionDAO#getDetalleClientesConsRest(java.util.Map)
	 */
	public List getDetalleClientesConsRest(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.mav.MantenimientoMAVSQL.getDetalleClientesConsRest", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.dao.MantenimientoMAVConfiguracionDAO#getEstados()
	 */
	public List getEstados() {
		return getSqlMapClientTemplate().queryForList("spusicc.mav.MantenimientoMAVSQL.getEstados");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.dao.MantenimientoMAVConfiguracionDAO#existeRegion(java.lang.String)
	 */
	public boolean existeRegion(String codigoRegion) {
		Integer result = (Integer) getSqlMapClientTemplate().
    						queryForObject("spusicc.mav.MantenimientoMAVSQL.getExisteRegion", codigoRegion);

		if(result.intValue()>0)
    		return true;
    	else
    		return false;
    }
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.dao.MantenimientoMAVConfiguracionDAO#existeZona(java.lang.String)
	 */
	public boolean existeZona(String codigoZona) {
		Integer result = (Integer) getSqlMapClientTemplate().
    						queryForObject("spusicc.mav.MantenimientoMAVSQL.getExisteZona", codigoZona);

		if(result.intValue()>0)
    		return true;
    	else
    		return false;
    }
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.dao.MantenimientoMAVConfiguracionDAO#existeRelacionRegionZona(java.lang.String, java.lang.String)
	 */
	public boolean existeRelacionRegionZona(String codigoRegion, String codigoZona) {
		Map criteria = new HashMap();
		criteria.put("codigoRegion", codigoRegion);
		criteria.put("codigoZona", codigoZona);
		
		Integer result = (Integer) getSqlMapClientTemplate().
    						queryForObject("spusicc.mav.MantenimientoMAVSQL.getExisteRelacionRegionZona", criteria);

		if(result.intValue()>0)
    		return true;
    	else
    		return false;
    }

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.dao.MantenimientoMAVConfiguracionDAO#getCatalogos()
	 */
	public List getCatalogos() {
		return getSqlMapClientTemplate().queryForList("spusicc.mav.MantenimientoMAVSQL.getCatalogos");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.dao.MantenimientoMAVConfiguracionDAO#getActividadesGerente()
	 */
	public List getActividadesGerente() {
		return getSqlMapClientTemplate().queryForList("spusicc.mav.MantenimientoMAVSQL.getActividadesGerente");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.dao.MantenimientoMAVConfiguracionDAO#getTieneEnvioDespachado(java.util.Map)
	 */
	public boolean getTieneEnvioDespachado(Map criteria) {
		Integer envioDespachado = (Integer) getSqlMapClientTemplate().queryForObject(
							"spusicc.mav.MantenimientoMAVSQL.getTieneEnvioDespachado", criteria);
		if(envioDespachado!=null)
			return true;
		else
			return false;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.dao.MantenimientoMAVConfiguracionDAO#updateEnviosNoAtendidos(java.util.Map)
	 */
	public void updateEnviosNoAtendidos(Map params) {
		getSqlMapClientTemplate().update("spusicc.mav.MantenimientoMAVSQL.updateEnviosNoAtendidos", params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.dao.MantenimientoMAVConfiguracionDAO#updateConRestDetalleCampanya(java.util.Map)
	 */
	public void updateConRestDetalleCampanya(Map bean) {
		getSqlMapClientTemplate().update("spusicc.mav.MantenimientoMAVSQL.updateConRestDetalleCampanya", bean);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.dao.MantenimientoMAVConfiguracionDAO#getDetalleConsRestEnvio(java.util.Map)
	 */
	public List getDetalleConsRestEnvio(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.mav.MantenimientoMAVSQL.getDetalleConsRestEnvio", criteria);			
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.dao.MantenimientoMAVConfiguracionDAO#updateConRestDetalleUnidades(java.util.Map)
	 */
	public void updateConRestDetalleUnidades(Map params) {
		getSqlMapClientTemplate().update("spusicc.mav.MantenimientoMAVSQL.updateConRestDetalleUnidades", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.dao.MantenimientoMAVConfiguracionDAO#updateEnvioUnidades(java.util.Map)
	 */
	public void updateEnvioUnidades(Map params) {
		getSqlMapClientTemplate().update("spusicc.mav.MantenimientoMAVSQL.updateEnvioUnidades", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.dao.MantenimientoMAVConfiguracionDAO#executeInsertarEnvioGerente(java.util.Map)
	 */
	public void executeInsertarEnvioGerente(Map params) {
		getSqlMapClientTemplate().insert("spusicc.mav.MantenimientoMAVSQL.executeInsertarEnvioGerente", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.dao.MantenimientoMAVConfiguracionDAO#getDetalleClientesConsRestEnvio(java.util.Map)
	 */
	public List getDetalleClientesConsRestEnvio(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.mav.MantenimientoMAVSQL.getDetalleClientesConsRestEnvio", criteria);	
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.dao.MantenimientoMAVConfiguracionDAO#executeInsertarEnvioConsultora(java.util.Map)
	 */
	public void executeInsertarEnvioConsultora(Map params) {
		getSqlMapClientTemplate().insert("spusicc.mav.MantenimientoMAVSQL.executeInsertarEnvioConsultora", params);
	}

	public List getActividadesTipoOferta(Map criteria) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("spusicc.mav.MantenimientoMAVSQL.getActividadesTipoOferta", criteria);
	}

	public void insertActividadesTipoOferta(Map params, Usuario usuario) {
		// TODO Auto-generated method stub
		String oid = (String) getSqlMapClientTemplate().queryForObject("spusicc.mav.MantenimientoMAVSQL.obtieneIdActividadTipoOferta");
		params.put("oidTipoOfer", oid);	
		
		getSqlMapClientTemplate().insert("spusicc.mav.MantenimientoMAVSQL.insertActividadTipoOferta", params);
	}

	public void updateActividadesTipoOferta(Map params, Usuario usuario) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().insert("spusicc.mav.MantenimientoMAVSQL.updateActividadTipoOferta", params);
	}

	public ActividadTipoOferta getObtieneActividadesTipoOferta(Map params) {
		// TODO Auto-generated method stub
		
		ActividadTipoOferta  actividadTipoOferta = (ActividadTipoOferta) getSqlMapClientTemplate().queryForObject("spusicc.mav.MantenimientoMAVSQL.getObtieneActividadesTipoOferta", params);
		
        if (actividadTipoOferta == null) {
            throw new ObjectRetrievalFailureException(CriterioBusqueda.class, params);
        }
        
        return actividadTipoOferta;
	}

	public String getExisteActividadesTipoOferta(Map params) {
		// TODO Auto-generated method stub
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.mav.MantenimientoMAVSQL.getExisteActividadTipoOferta", params);
	}

	public String getDescripcionTipoActividad(Map params) {
		// TODO Auto-generated method stub
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.mav.MantenimientoMAVSQL.getDecripcionTipoActividad", params);
	}

	public String getDescripcionActividad(Map params) {
		// TODO Auto-generated method stub
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.mav.MantenimientoMAVSQL.getDescripcionActividad", params);
	}

	public String getDescripcionTipoOferta(Map params) {
		// TODO Auto-generated method stub
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.mav.MantenimientoMAVSQL.getDescripcionTipoOferta", params);
	}

	public List getTiposOfertaId(String oidActividad) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("spusicc.mav.MantenimientoMAVSQL.getTiposOfertaId", oidActividad);
	}

	public String getValidaEditActividadOferta(Map params) {
		// TODO Auto-generated method stub
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.mav.MantenimientoMAVSQL.getValidaEditActividadesTipoOferta", params);
	}

	public String getCodigoTipoOferta(Map params) {
		// TODO Auto-generated method stub
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.mav.MantenimientoMAVSQL.getCodigoTipoOferta", params);
	}

	public List getActividadesbyTipoCliente(String oidTipoActividad) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("spusicc.mav.MantenimientoMAVSQL.getActividadesbyTipoCliente",oidTipoActividad);
}
	public void DesactivarActividadTipoOferta(Map params, Usuario usuario) {
		// TODO Auto-generated method stub
		String estado = (String) getSqlMapClientTemplate().queryForObject("spusicc.mav.MantenimientoMAVSQL.getEstadoActividadTipoOferta", params);
		
		if(estado.equals("1")){
			params.put("estado", "0");
		}else{
			params.put("estado", "1");
		}
		
		getSqlMapClientTemplate().insert("spusicc.mav.MantenimientoMAVSQL.DesactivarActividadTipoOferta", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.dao.MantenimientoMAVConfiguracionDAO#insertExternaConsultora(java.util.Map)
	 */
	public void insertExternaConsultora(Map params) {
		getSqlMapClientTemplate().insert("spusicc.mav.MantenimientoMAVSQL.insertExternaConsultora", params);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.dao.MantenimientoMAVConfiguracionDAO#executeValidarExternaConsultora(java.lang.String)
	 */
	public void executeValidarExternaConsultora(String codigoUsuario) {
		Map params = new HashMap();
		params.put("codigoUsuario", codigoUsuario);
		
		getSqlMapClientTemplate().update("spusicc.mav.MantenimientoMAVSQL.executeValidarExternaConsultora", params);

	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.dao.MantenimientoMAVConfiguracionDAO#getCargarExternaConsultora(java.lang.String)
	 */
	public List getCargarExternaConsultora(String codigoUsuario) {
		return getSqlMapClientTemplate().queryForList("spusicc.mav.MantenimientoMAVSQL.getCargarExternaConsultora", codigoUsuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.dao.MantenimientoMAVConfiguracionDAO#getErroresTempoExterConsul(java.lang.String)
	 */
	public Integer getErroresTempoExterConsul(String codigoUsuario) {
		return (Integer) getSqlMapClientTemplate().queryForObject("spusicc.mav.MantenimientoMAVSQL.getErroresTempoExterConsul", codigoUsuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.dao.MantenimientoMAVConfiguracionDAO#deleteExternaCultora(java.lang.String)
	 */
	public void deleteExternaCultora(String codigoUsuario) {

		getSqlMapClientTemplate().update("spusicc.mav.MantenimientoMAVSQL.deleteExternaCultora", codigoUsuario);		
	}	
	
	
}


