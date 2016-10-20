package biz.belcorp.ssicc.dao.spusicc.men.ibatis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.spusicc.men.MantenimientoMENGenericoDAO;
/**
 * @author <a href="mailto:sbuchelli@csigcomt.com">Sergio Buchelli</a>
 *
 */
@Repository("spusicc.mantenimientoMENGenericoDAO")
public class MantenimientoMENGenericoDAOIbatis extends BaseDAOiBatis implements
						MantenimientoMENGenericoDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENGenericoDAO#deletePatronMensaje(java.util.Map)
	 */
	public void deletePatronMensaje(Map map) {
			getSqlMapClientTemplate().update("spusicc.mensaje.MantenimientoMENSQL.deletePatronMensaje", 
											map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENGenericoDAO#getDocumentosMensaje(java.util.Map)
	 */
	public List getDocumentosMensaje(Map map) {
		return getSqlMapClientTemplate().
		queryForList("spusicc.mensaje.MantenimientoMENSQL.getDocumentosMensaje",
					 map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENGenericoDAO#getPatronMensaje(java.util.Map)
	 */
	public List getPatronMensaje(Map map) {
		return getSqlMapClientTemplate().
			queryForList("spusicc.mensaje.MantenimientoMENSQL.getPatronMensaje",
					 map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENGenericoDAO#getModulos(java.util.Map)
	 */
	public List getModulos(Map map) {
		return getSqlMapClientTemplate().
			queryForList("spusicc.mensaje.MantenimientoMENSQL.getModulos",
					 map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENGenericoDAO#getSeccionPatron(java.util.Map)
	 */
	public List getSeccionPatron(Map map) {
		return getSqlMapClientTemplate().
			queryForList("spusicc.mensaje.MantenimientoMENSQL.getSeccionPatron",
					 map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENGenericoDAO#getConsideracion(java.util.Map)
	 */
	public List getConsideracion(Map map) {
		return getSqlMapClientTemplate().
		queryForList("spusicc.mensaje.MantenimientoMENSQL.getConsideracion",
				 map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENGenericoDAO#getRestriccion(java.util.Map)
	 */
	public List getRestriccion(Map map) {
		return getSqlMapClientTemplate().
		queryForList("spusicc.mensaje.MantenimientoMENSQL.getRestriccion",
				 map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENGenericoDAO#getMensajePatron(java.util.Map)
	 */
	public List getMensajePatron(Map map) {
		return getSqlMapClientTemplate().
			queryForList("spusicc.mensaje.MantenimientoMENSQL.getMensajePatron",
				 map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENGenericoDAO#getCodigoMensajeByModulo(java.util.Map)
	 */
	public String getCodigoMensajeByModulo(Map map) {
		return (String)getSqlMapClientTemplate().
			queryForObject("spusicc.mensaje.MantenimientoMENSQL.getCodigoMensajeByModulo",
					map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENGenericoDAO#getOidPeriodoCorpo(java.util.Map)
	 */
	public String getOidPeriodoCorpo(Map map) {
		return (String)getSqlMapClientTemplate().
		queryForObject("spusicc.mensaje.MantenimientoMENSQL.getOidPeriodoCorpo",
				map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENGenericoDAO#getCorrelativoDocumentoSecccion(java.util.Map)
	 */
	public String getCorrelativoDocumentoSecccion(Map map) {
		return (String)getSqlMapClientTemplate().
		queryForObject("spusicc.mensaje.MantenimientoMENSQL.getCorrelativoDocumentoSecccion",
				map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENGenericoDAO#savePatronMensansaje(java.util.Map)
	 */
	public void savePatronMensaje(Map map) {
		getSqlMapClientTemplate().update("spusicc.mensaje.MantenimientoMENSQL.executaSavePatronMensaje", map);	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENGenericoDAO#updatePatronMensaje(java.util.Map)
	 */
	public void updatePatronMensaje(Map map) {
		getSqlMapClientTemplate().update("spusicc.mensaje.MantenimientoMENSQL.updatePatronMensaje", map);	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENGenericoDAO#saveConRestCabecera(java.util.Map)
	 */
	public void saveConRestCabecera(Map bean) {
		getSqlMapClientTemplate().update("spusicc.mensaje.MantenimientoMENSQL.saveConRestCabecera",bean);			
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENGenericoDAO#saveConRestDetalle(java.util.Map)
	 */
	public void saveConRestDetalle(Map bean) {
		getSqlMapClientTemplate().update("spusicc.mensaje.MantenimientoMENSQL.saveConRestDetalle",bean);		
	}

	public String getAbrevSeccion(Map map) {
		return (String)getSqlMapClientTemplate().
		queryForObject("spusicc.mensaje.MantenimientoMENSQL.getAbrevSeccion",
				map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENGenericoDAO#getExisteCodigoMensaje(java.lang.String, java.lang.String)
	 */
	public int getExisteCodigoMensaje(String codigoMensaje,String campanhaProceso) {
		Map criteria = new HashMap();
		criteria.put("codigoMensaje", codigoMensaje);
		criteria.put("campanhaProceso", campanhaProceso);
		return (Integer)getSqlMapClientTemplate().
			queryForObject("spusicc.mensaje.MantenimientoMENSQL.getExisteCodigoMensaje",
					criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENGenericoDAO#getCodigoPremio(java.util.Map)
	 */
	public LabelValue getCodigoPremio(Map criteria) {
		List l = new ArrayList(); 
		LabelValue lb = new LabelValue();
		l=getSqlMapClientTemplate().queryForList("spusicc.mensaje.MantenimientoMENSQL.getCodigoPremio",criteria);
		if(l.size()!=0)
			lb = (LabelValue)l.get(0);		
		return lb;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENGenericoDAO#getRestConsideracion(java.util.Map)
	 */
	public List getRestConsideracion(Map map) {
		return getSqlMapClientTemplate().
			queryForList("spusicc.mensaje.MantenimientoMENSQL.getRestConsideracion",
					map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENGenericoDAO#getClasificaciones(java.util.Map)
	 */
	public List getClasificaciones(Map criteria) {
		return getSqlMapClientTemplate().
		queryForList("spusicc.mensaje.MantenimientoMENSQL.getClasificaciones",
				criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENGenericoDAO#getDetalleConsRest(java.util.Map)
	 */
	public List getDetalleConsRest(Map criteria) {
		return getSqlMapClientTemplate().
		queryForList("spusicc.mensaje.MantenimientoMENSQL.getDetalleConsRest",
				criteria);
	}

	public List getSeccion(Map criteria) {
		return getSqlMapClientTemplate().
		queryForList("spusicc.mensaje.MantenimientoMENSQL.getSeccion",
				criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENGenericoDAO#getSubTiposClientes(java.util.Map)
	 */
	public List getSubTiposClientes(Map criteria) {
		return getSqlMapClientTemplate().
		queryForList("spusicc.mensaje.MantenimientoMENSQL.getSubTiposClientes",
				criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENGenericoDAO#getTerritorio(java.util.Map)
	 */
	public List getTerritorio(Map criteria) {
		return getSqlMapClientTemplate().
		queryForList("spusicc.mensaje.MantenimientoMENSQL.getTerritorio",
				criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENGenericoDAO#getTiposClasificaciones(java.util.Map)
	 */
	public List getTiposClasificaciones(Map criteria) {
		return getSqlMapClientTemplate().
		queryForList("spusicc.mensaje.MantenimientoMENSQL.getTiposClasificaciones",
				criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENGenericoDAO#getZona(java.util.Map)
	 */
	public List getZona(Map criteria) {
		return getSqlMapClientTemplate().
		queryForList("spusicc.mensaje.MantenimientoMENSQL.getZona",
				criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENGenericoDAO#updateConRestCabecera(java.util.Map)
	 */
	public void updateConRestCabecera(Map bean) {
		getSqlMapClientTemplate().update("spusicc.mensaje.MantenimientoMENSQL.updateConRestCabecera",bean);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENGenericoDAO#updateConRestDetalle(java.util.Map)
	 */
	public void updateConRestDetalle(Map bean) {
		getSqlMapClientTemplate().update("spusicc.mensaje.MantenimientoMENSQL.updateConRestDetalle",bean);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENGenericoDAO#deletePatronMensajeDetalle(java.util.Map)
	 */
	public void deletePatronMensajeDetalle(Map beanPatron) {
		getSqlMapClientTemplate().update("spusicc.mensaje.MantenimientoMENSQL.updatePatronMensaje",beanPatron);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENGenericoDAO#getCodigoVentaReemplazado(java.util.Map)
	 */
	public LabelValue getCodigoVentaReemplazado(Map criteria) {
		List l = new ArrayList(); 
		LabelValue lb = new LabelValue();
		l=getSqlMapClientTemplate().queryForList("spusicc.mensaje.MantenimientoMENSQL.getCodigoVentaReemplazado",criteria);
		if(l.size()!=0)
			lb = (LabelValue)l.get(0);		
		return lb;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENGenericoDAO#getCodigoVentaReemplazo(java.util.Map)
	 */
	public LabelValue getCodigoVentaReemplazo(Map criteria) {
		List l = new ArrayList(); 
		LabelValue lb = new LabelValue();
		l=getSqlMapClientTemplate().queryForList("spusicc.mensaje.MantenimientoMENSQL.getCodigoVentaReemplazo",criteria);
		if(l.size()!=0)
			lb = (LabelValue)l.get(0);		
		return lb;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENGenericoDAO#saveConRestBuzonDetalle(java.util.Map)
	 */
	public void saveConRestBuzonDetalle(Map m) {
		getSqlMapClientTemplate().insert("spusicc.mensaje.MantenimientoMENSQL.saveConRestBuzonDetalle",m);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENGenericoDAO#updateConRestBuzonDetalle(java.util.Map)
	 */
	public void updateConRestBuzonDetalle(Map bean) {
		getSqlMapClientTemplate().update("spusicc.mensaje.MantenimientoMENSQL.updateConRestBuzonDetalle",bean);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENGenericoDAO#getDocumentosPatron(java.util.Map)
	 */
	public List getDocumentosPatron(Map criteria) {
		return getSqlMapClientTemplate().
					queryForList("spusicc.mensaje.MantenimientoMENSQL.getDocumentosPatron",
											criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENGenericoDAO#getMensajesSeccionDocumentoPatron(java.util.Map)
	 */
	public List getMensajesSeccionDocumentoPatron(Map criteria) {
		return getSqlMapClientTemplate().
				queryForList("spusicc.mensaje.MantenimientoMENSQL.getMensajesSeccionDocumentoPatron",
								criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENGenericoDAO#getSeccionDocumentoPatron(java.util.Map)
	 */
	public List getSeccionDocumentoPatron(Map criteria) {
		return getSqlMapClientTemplate().
			queryForList("spusicc.mensaje.MantenimientoMENSQL.getSeccionDocumentoPatron",
						criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENGenericoDAO#replicarPatron(java.util.Map)
	 */
	public void replicarPatron(Map map) {
		//elimina temporal de replica patron
		int cont = (Integer)map.get("cont");
		if(cont == 0){
			getSqlMapClientTemplate().
			update("spusicc.mensaje.MantenimientoMENSQL.deleteReplicaPatron",map);
		}		
		getSqlMapClientTemplate().
				update("spusicc.mensaje.MantenimientoMENSQL.insertReplicaPatron",map);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENGenericoDAO#getValidaPatron(java.util.Map)
	 */
	public Integer getValidaPatron(Map criteria) {
		return (Integer)getSqlMapClientTemplate().
			queryForObject("spusicc.mensaje.MantenimientoMENSQL.getValidaPatron",
					criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENGenericoDAO#updateImpresionPatronMensaje(java.util.Map)
	 */
	public void updateImpresionPatronMensaje(Map criteria) {
		getSqlMapClientTemplate().
				update("spusicc.mensaje.MantenimientoMENSQL.updateImpresionPatronMensaje",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENGenericoDAO#executeReplicarpatron()
	 */
	public void executeReplicarPatron() {
		getSqlMapClientTemplate().
			update("spusicc.mensaje.MantenimientoMENSQL.executeReplicarPatron");
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENGenericoDAO#getMensajeByOid(java.util.Map)
	 */
	public List getMensajeByOid(Map map) {
		return getSqlMapClientTemplate().
			queryForList("spusicc.mensaje.MantenimientoMENSQL.getMensajeByOid",
					map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENGenericoDAO#updateImpresionPatronSeccion(java.util.Map)
	 */
	public void updateImpresionPatronSeccion(Map criteria) {
		getSqlMapClientTemplate().
			update("spusicc.mensaje.MantenimientoMENSQL.updateImpresionPatronSeccion",criteria);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENGenericoDAO#updatePatron(java.util.Map)
	 */
	public void updatePatron(Map beanPatron) {
		getSqlMapClientTemplate().
		update("spusicc.mensaje.MantenimientoMENSQL.updatePatron",beanPatron);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENGenericoDAO#getRegistrosBuzon(java.util.Map)
	 */
	public int getRegistrosBuzon(Map map) {
		return (Integer)getSqlMapClientTemplate().
		queryForObject("spusicc.mensaje.MantenimientoMENSQL.getRegistrosBuzon",
				map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENGenericoDAO#getNumeroLote()
	 */
	public String getNumeroLote() {
		return (String)getSqlMapClientTemplate().
				queryForObject("spusicc.mensaje.MantenimientoMENSQL.getNumeroLote");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENGenericoDAO#getLoteBuzon(java.util.Map)
	 */
	public List getLoteBuzon(Map map) {
		return getSqlMapClientTemplate().
					queryForList("spusicc.mensaje.MantenimientoMENSQL.getLoteBuzon",
						map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENGenericoDAO#deleteConRestCabecera(java.util.Map)
	 */
	public void deleteConRestCabecera(Map bean) {
		getSqlMapClientTemplate().delete("spusicc.mensaje.MantenimientoMENSQL.deleteConRestCabecera", 
				bean);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENGenericoDAO#deleteConRestDetalle(java.util.Map)
	 */
	public void deleteConRestDetalle(Map bean) {
		getSqlMapClientTemplate().delete("spusicc.mensaje.MantenimientoMENSQL.deleteConRestDetalle", 
				bean);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENGenericoDAO#getNumRegLoteBuzon(java.util.Map)
	 */
	public int getNumRegLoteBuzon(Map bean) {
		return (Integer)getSqlMapClientTemplate().
				queryForObject("spusicc.mensaje.MantenimientoMENSQL.getNumRegLoteBuzon",bean);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENGenericoDAO#getVariables(java.util.Map)
	 */
	public List getVariables(Map map) {
		return getSqlMapClientTemplate().
					queryForList("spusicc.mensaje.MantenimientoMENSQL.getVariables",
								map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENGenericoDAO#updateAsignacion(java.util.Map)
	 */
	public void updateAsignacion(Map map) {
		getSqlMapClientTemplate().
			update("spusicc.mensaje.MantenimientoMENSQL.updateAsignacion",map);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENGenericoDAO#getMensajesDisponibles(java.util.Map)
	 */
	public List getMensajesDisponibles(Map criteria) {
		return getSqlMapClientTemplate().
				queryForList("spusicc.mensaje.MantenimientoMENSQL.getMensajesDisponibles",
						criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENGenericoDAO#anahdeMensajePatronMensaje(java.util.Map)
	 */
	public void anahdeMensajePatronMensaje(Map criteria) {
		getSqlMapClientTemplate().
					insert("spusicc.mensaje.MantenimientoMENSQL.insertImpresionPatronMensaje",criteria);
		
		getSqlMapClientTemplate().
					insert("spusicc.mensaje.MantenimientoMENSQL.insertImpresionMensaje",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENGenericoDAO#deleteImpresionPatronMensaje(java.util.Map)
	 */
	public void deleteImpresionPatronMensaje(Map criteria) {
		getSqlMapClientTemplate().
					update("spusicc.mensaje.MantenimientoMENSQL.deleteImpresionPatronMensaje",criteria);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENGenericoDAO#getDescripcionMensaje(java.util.Map)
	 */
	public String getDescripcionMensaje(Map criteria) {
		return (String)getSqlMapClientTemplate().
				queryForObject("spusicc.mensaje.MantenimientoMENSQL.getDescripcionMensaje", criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.men.MantenimientoMENGenericoDAO#updateAnulacionMensajeImagen(java.util.Map)
	 */
	public void updateAnulacionMensajeImagen(Map map) {
		getSqlMapClientTemplate().update("spusicc.mensaje.MantenimientoMENSQL.updateAnulacionMensajeImagen", map);	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.men.MantenimientoMENGenericoDAO#insertaMensajeImagen(java.util.Map)
	 */
	public void insertaMensajeImagen(Map map) {
		getSqlMapClientTemplate().update("spusicc.mensaje.MantenimientoMENSQL.insertaMensajeImagen", map);	
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.men.MantenimientoMENGenericoDAO#leerMensajeImagen(java.util.Map)
	 */
	public void leerMensajeImagen(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.mensaje.MantenimientoMENSQL.leerMensajeImagen", criteria);	
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.men.MantenimientoMENGenericoDAO#getListaBandejaFlyers(java.util.Map)
	 */
	public List getListaBandejaFlyers(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.mensaje.MantenimientoMENSQL.getListaBandejaFlyers",	criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.men.MantenimientoMENGenericoDAO#executaSavePatronFlyersMensaje(java.util.Map)
	 */
	public void executaSavePatronFlyersMensaje(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.mensaje.MantenimientoMENSQL.executaSavePatronFlyersMensaje", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.men.MantenimientoMENGenericoDAO#getListaFlyers(java.util.Map)
	 */
	public List getListaFlyers(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.mensaje.MantenimientoMENSQL.getListaFlyers",	criteria);
	}
}
