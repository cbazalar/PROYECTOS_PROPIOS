package biz.belcorp.ssicc.dao.spusicc.men.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.men.ProcesoMENGenerarMensajesDAO;
/**
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli</a>
 *
 */
@Repository("spusicc.procesoMENGenerarMensajesDAO")
public class ProcesoMENGenerarMensajesDAOIbatis extends BaseDAOiBatis implements
				ProcesoMENGenerarMensajesDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.ProcesoMENGenerarMensajesDAO#executaProcesoMensajes(java.util.Map)
	 */
	public void executaProcesoMensajes(Map params) {
		getSqlMapClientTemplate().update("spusicc.mensaje.ProcesoMENSQL.executaProcesoMensajes", params);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.ProcesoMENGenerarMensajesDAO#getPlantillaProceso(java.util.Map)
	 */
	public List getPlantillaProceso(Map params) {
		return getSqlMapClientTemplate().
					queryForList("spusicc.mensaje.ProcesoMENSQL.getPlantillaProceso",params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.ProcesoMENGenerarMensajesDAO#getCorreosGz(java.util.Map)
	 */
	public List getCorreosGz(Map params) {
		return getSqlMapClientTemplate().
				queryForList("spusicc.mensaje.ProcesoMENSQL.getCorreosGz",params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.ProcesoMENGenerarMensajesDAO#executeProcesarEliminarBuzonMensaje(java.util.Map)
	 */
	public void executeProcesarEliminarBuzonMensaje(Map params) {
		getSqlMapClientTemplate().update("spusicc.mensaje.ProcesoMENSQL.executeProcesarEliminarBuzonMensaje", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.ProcesoMENGenerarMensajesDAO#executeProcesoActualizarMensajes(java.util.Map)
	 */
	public void executeProcesoActualizarMensajes(Map params) {
		if(params.get("indicadorCruce")==null)
			params.put("indicadorCruce", "0");
		getSqlMapClientTemplate().update("spusicc.mensaje.ProcesoMENSQL.executeProcesoActualizarMensajes", params);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.ProcesoMENGenerarMensajesDAO#getDatosProductoCUVPeriodo(java.util.Map)
	 */
	public Map getDatosProductoCUVPeriodo(Map criteria) {
		return (HashMap) getSqlMapClientTemplate().queryForObject(
				"spusicc.mensaje.ProcesoMENSQL.getDatosProductoCUVPeriodo", criteria);
	}	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.ProcesoMENGenerarMensajesDAO#deleteCargaMasivaFaltanteAnunciado(java.util.Map)
	 */
	public void deleteCargaMasivaFaltanteAnunciado(Map params) {
		getSqlMapClientTemplate().update("spusicc.mensaje.ProcesoMENSQL.deleteCargaMasivaFaltanteAnunciado", params);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.ProcesoMENGenerarMensajesDAO#executeProcesoActualizarFaltanteAnunciado(java.util.Map)
	 */
	public void executeProcesoActualizarFaltanteAnunciado(Map params) {
		getSqlMapClientTemplate().update("spusicc.mensaje.ProcesoMENSQL.executeProcesoActualizarFaltanteAnunciado", params);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.ProcesoMENGenerarMensajesDAO#getDevuelveFaltanteAnunciado(java.util.Map)
	 */
	public List getDevuelveFaltanteAnunciado(Map params) {
		return getSqlMapClientTemplate().
				queryForList("spusicc.mensaje.ProcesoMENSQL.getDevuelveFaltanteAnunciado",params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.ProcesoMENGenerarMensajesDAO#deleteFaltanteAnunciado(java.util.Map)
	 */
	public void deleteFaltanteAnunciado(Map params) {
		//getSqlMapClientTemplate().update("spusicc.mensaje.ProcesoMENSQL.deleteFaltanteAnunciadoUnidadAdministrativa", params);
		getSqlMapClientTemplate().update("spusicc.mensaje.ProcesoMENSQL.deleteFaltanteAnunciado", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.ProcesoMENGenerarMensajesDAO#deleteConferencias(java.util.Map)
	 */
	public void deleteConferencias(Map params) {
		getSqlMapClientTemplate().update("spusicc.mensaje.ProcesoMENSQL.deleteConferencias", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.ProcesoMENGenerarMensajesDAO#getDevuelveFaltanteAnunciadoCodigoCUV(java.util.Map)
	 */
	public Map getDevuelveFaltanteAnunciadoCodigoCUV(Map params) {
		return (HashMap) getSqlMapClientTemplate().queryForObject(
				"spusicc.mensaje.ProcesoMENSQL.getDevuelveFaltanteAnunciadoCodigoCUV", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.ProcesoMENGenerarMensajesDAO#getDevuelveCodigoCampanha(java.util.Map)
	 */
	public String getDevuelveCodigoCampanha(Map params) {
		return (String) getSqlMapClientTemplate().queryForObject(
				"spusicc.mensaje.ProcesoMENSQL.getDevuelveCodigoCampanha", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.ProcesoMENGenerarMensajesDAO#getDatosRegion(java.util.Map)
	 */
	public Map getDatosRegion(Map criteria) {
		return (HashMap) getSqlMapClientTemplate().queryForObject("spusicc.mensaje.ProcesoMENSQL.getDatosRegion", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.ProcesoMENGenerarMensajesDAO#getDatosZonaByRegion(java.util.Map)
	 */
	public Map getDatosZonaByRegion(Map criteria) {
		return (HashMap) getSqlMapClientTemplate().queryForObject("spusicc.mensaje.ProcesoMENSQL.getDatosZonaByRegion", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.ProcesoMENGenerarMensajesDAO#deleteCargaMasivaConferencias(java.util.Map)
	 */
	public void deleteCargaMasivaConferencias(Map params) {
		getSqlMapClientTemplate().update("spusicc.mensaje.ProcesoMENSQL.deleteCargaMasivaConferencias", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.ProcesoMENGenerarMensajesDAO#executeProcesoActualizarConferencias(java.util.Map)
	 */
	public void executeProcesoActualizarConferencias(Map params) {
		getSqlMapClientTemplate().update("spusicc.mensaje.ProcesoMENSQL.executeProcesoActualizarConferencias", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.ProcesoMENGenerarMensajesDAO#getDevuelveConferencias(java.util.Map)
	 */
	public List getDevuelveConferencias(Map params) {
		return getSqlMapClientTemplate().queryForList("spusicc.mensaje.ProcesoMENSQL.getDevuelveConferencias",params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.ProcesoMENGenerarMensajesDAO#getDevuelveCodigoRegionZona(java.util.Map)
	 */
	public String getDevuelveCodigoRegionZona(Map criteria) {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.mensaje.ProcesoMENSQL.getDevuelveCodigoRegionZona",criteria);
	}
	
}