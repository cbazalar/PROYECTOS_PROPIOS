package biz.belcorp.ssicc.dao.spusicc.let.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.let.MantenimientoLETLideresDAO;

/**
 * Clase de la implementacin de la capa DAO (Data Access Object)
 * 
 * <p>
 * <a href="MantenimientoLETParametroConcursoDAOIbatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="cdiaz@csigcomt.com">Carlos Diaz Valverde</a>
 *         
 */
@Repository("spusicc.mantenimientoLETLideresDAO")
public class MantenimientoLETLideresDAOIbatis extends BaseDAOiBatis implements MantenimientoLETLideresDAO {

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETLideresDAO#getUltimaCampanaCierreRegionxZona(java.util.Map)
     */
    public Base getUltimaCampanaCierreRegionxZona(Map criteria) {
		log.info("Entro a MantenimientoLETLideresDAOIbatis - getUltimaCampanaCierreRegionxZona(java.util.Map)");
		Base resultado = (Base) getSqlMapClientTemplate().queryForObject("spusicc.let.MantenimientoLETSQL.getUltimaCampanaCierreRegionxZona", criteria);
		log.info("Salio a MantenimientoLETLideresDAOIbatis - getUltimaCampanaCierreRegionxZona(java.util.Map) - Resultado:" + resultado);
        return resultado;
    }	

    /* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETLideresDAO#getSeccionesByCriteria(java.util.Map)
	 */
	public List getSeccionesByCriteria(Map criteria) {
		log.info("Entro a MantenimientoLETLideresDAOIbatis - getSeccionesByCriteria(java.util.Map)");
		
		//-- anexar
		/*String codigoPeriodoActual = (String)getSqlMapClientTemplate().queryForObject("spusicc.let.MantenimientoLETSQL.getLETCampaniaActual", criteria);
		criteria.put("codigoPeriodoActual", codigoPeriodoActual);*/
		criteria.put("codigoPeriodoActual", criteria.get("codigoPeriodo"));
		
		//-- Listado
		List lista = getSqlMapClientTemplate().queryForList("spusicc.let.MantenimientoLETSQL.getSeccionesByCriteria",criteria);
		
		log.info("Salio a MantenimientoLETLideresDAOIbatis - getSeccionesByCriteria(java.util.Map) - Resultado:"+lista);
		return lista;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETLideresDAO#validarAsignacionLiderSeccion(java.util.Map)
        */
	public String validarAsignacionLiderSeccion(Map params) {
		log.info("Entro a MantenimientoLETLideresDAOIbatis - validarAsignacionLiderSeccion(java.util.Map)");
		
		//-- validacion
		String resultado = (String) getSqlMapClientTemplate().queryForObject("spusicc.let.MantenimientoLETSQL.validarAsignacionLiderSeccion", params); 
		
		log.info("Salio a MantenimientoLETLideresDAOIbatis - validarAsignacionLiderSeccion(java.util.Map) - Resultado:"+resultado);
		return resultado;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETLideresDAO#getConsultoraClasificacionLider(java.util.Map)
	 */
	public String getConsultoraClasificacionLider(Map params) {
		log.info("Entro a MantenimientoLETLideresDAOIbatis - getConsultoraClasificacionLider(java.util.Map)");
		String resultado = (String) getSqlMapClientTemplate().queryForObject("spusicc.let.MantenimientoLETSQL.getConsultoraClasificacionLider", params); 
		log.info("Salio a MantenimientoLETLideresDAOIbatis - getConsultoraClasificacionLider(java.util.Map) - Resultado:" + resultado);
		return resultado;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETLideresDAO#insertClasificacionConsultoraLider(java.util.Map)
	 */
	public void insertClasificacionConsultoraLider(Map criteria) {
		log.info("Entro a MantenimientoLETLideresDAOIbatis - insertClasificacionConsultoraLider(java.util.Map)");
	    getSqlMapClientTemplate().insert("spusicc.let.MantenimientoLETSQL.insertClasificacionConsultoraLider", criteria);
		log.info("Salio a MantenimientoLETLideresDAOIbatis - insertClasificacionConsultoraLider(java.util.Map)");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETLideresDAO#getUltimoResponsableSeccion(java.util.Map)
	 */
	public Map getUltimoResponsableSeccion(Map criteria) {
		log.info("Entro a MantenimientoLETLideresDAOIbatis - getUltimoResponsableSeccion(java.util.Map)");
		Map resultado = (HashMap) getSqlMapClientTemplate().queryForObject("spusicc.let.MantenimientoLETSQL.getUltimoResponsableSeccion", criteria);
		log.info("Salio a MantenimientoLETLideresDAOIbatis - getUltimoResponsableSeccion(java.util.Map) - Resultado:" + resultado);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETLideresDAO#updateFechaHastaHistoricoGerente(java.util.Map)
	 */
	public void updateFechaHastaHistoricoGerente(Map criteria) {
		log.info("Entro a MantenimientoLETLideresDAOIbatis - updateFechaHastaHistoricoGerente(java.util.Map)");
		getSqlMapClientTemplate().update("spusicc.let.MantenimientoLETSQL.updateFechaHastaHistoricoGerente",criteria);
		log.info("Salio a MantenimientoLETLideresDAOIbatis - updateFechaHastaHistoricoGerente(java.util.Map)");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETLideresDAO#deleteClasificacionLiderConsultoraAnterior(java.util.Map)
	 */
	public void deleteClasificacionLiderConsultoraAnterior(Map criteria) {
		log.info("Entro a MantenimientoLETLideresDAOIbatis - deleteClasificacionLiderConsultoraAnterior(java.util.Map)");
        getSqlMapClientTemplate().delete("spusicc.let.MantenimientoLETSQL.deleteClasificacionLiderConsultoraAnterior", criteria);
		log.info("Salio a MantenimientoLETLideresDAOIbatis - deleteClasificacionLiderConsultoraAnterior(java.util.Map)");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETLideresDAO#updateLiderSeccion(java.util.Map)
	 */
	public void updateLiderSeccion(Map criteria) {
		log.info("Entro a MantenimientoLETLideresDAOIbatis - updateLiderSeccion(java.util.Map)");
		getSqlMapClientTemplate().update("spusicc.let.MantenimientoLETSQL.updateLiderSeccion",criteria);
		log.info("Salio a MantenimientoLETLideresDAOIbatis - updateLiderSeccion(java.util.Map)");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETLideresDAO#insertHistoricoGerente(java.util.Map)
	 */
	public void insertHistoricoGerente(Map criteria) {
		log.info("Entro a MantenimientoLETLideresDAOIbatis - insertHistoricoGerente(java.util.Map)");
	    getSqlMapClientTemplate().insert("spusicc.let.MantenimientoLETSQL.insertHistoricoGerente", criteria);
		log.info("Salio a MantenimientoLETLideresDAOIbatis - insertHistoricoGerente(java.util.Map)");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETLideresDAO#insertBuzonMensajeResponsable(java.util.Map)
	 */
	public void insertBuzonMensajeResponsable(Map criteria) {
		log.info("Entro a MantenimientoLETLideresDAOIbatis - insertBuzonMensajeResponsable(java.util.Map)");
		getSqlMapClientTemplate().insert("spusicc.let.MantenimientoLETSQL.insertBuzonMensajeResponsable", criteria);
		log.info("Salio a MantenimientoLETLideresDAOIbatis - insertBuzonMensajeResponsable(java.util.Map)");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETLideresDAO#getOidCanalByCodigoCanalGenericoLET(java.util.Map)
	 */
	public String getOidCanalByCodigoCanalGenericoLET(Map criteria) {
		log.info("Entro a MantenimientoLETLideresDAOIbatis - getOidCanalByCodigoCanalGenericoLET(java.util.Map)");
		String resultado = (String) getSqlMapClientTemplate().queryForObject("spusicc.let.MantenimientoLETSQL.getOidCanalByCodigoCanalGenericoLET", criteria);
		log.info("Salio a MantenimientoLETLideresDAOIbatis - getOidCanalByCodigoCanalGenericoLET(java.util.Map) - Resultado:" + resultado);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETLideresDAO#getOidMarcaByCodigoMarcaGenericoLET(java.util.Map)
	 */
	public String getOidMarcaByCodigoMarcaGenericoLET(Map criteria) {
		log.info("Entro a MantenimientoLETLideresDAOIbatis - getOidMarcaByCodigoMarcaGenericoLET(java.util.Map)");
		String resultado = (String) getSqlMapClientTemplate().queryForObject("spusicc.let.MantenimientoLETSQL.getOidMarcaByCodigoMarcaGenericoLET", criteria);
		log.info("Salio a MantenimientoLETLideresDAOIbatis - getOidMarcaByCodigoMarcaGenericoLET(java.util.Map) - Resultado:" + resultado);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETLideresDAO#getOidPaisByCodigoPaisLET(java.util.Map)
	 */
	public String getOidPaisByCodigoPaisLET(Map criteria) {
		log.info("Entro a MantenimientoLETLideresDAOIbatis - getOidPaisByCodigoPaisLET(java.util.Map)");
		String resultado = (String) getSqlMapClientTemplate().queryForObject("spusicc.let.MantenimientoLETSQL.getOidPaisByCodigoPaisLET", criteria);
		log.info("Salio a MantenimientoLETLideresDAOIbatis - getOidPaisByCodigoPaisLET(java.util.Map) - Resultado:" + resultado);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETLideresDAO#getIndicadorProgramaLet(java.lang.String)
	 */
	public Integer getIndicadorProgramaLet(String codigoPais) {
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.let.MantenimientoLETSQL.getIndicadorProgramaLet", codigoPais);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETLideresDAO#getEncontrarConcursoLet(java.lang.String)
	 */
	public Integer getEncontrarConcursoLet(String periodoActual) {
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.let.MantenimientoLETSQL.getEncontrarConcursoLet", periodoActual);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETLideresDAO#getOidPeriodoByCodigoPeriodo(java.lang.String)
	 */
	public Integer getOidPeriodoByCodigoPeriodo(String codigoPerido) {
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.let.MantenimientoLETSQL.getOidPeriodoByCodigoPeriodo", codigoPerido);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETLideresDAO#getOidPeriodoAnteriorByCodigoPeriodo(java.util.Map)
	 */
	public Integer getOidPeriodoAnteriorByCodigoPeriodo(Map criteria) {
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.let.MantenimientoLETSQL.getOidPeriodoAnteriorByCodigoPeriodo", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETLideresDAO#getCodigoPeriodoByOidPeriodo(java.lang.Integer)
	 */
	public String getCodigoPeriodoByOidPeriodo(Integer oidPeriodo) {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.let.MantenimientoLETSQL.getCodigoPeriodoByOidPeriodo", oidPeriodo);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETLideresDAO#mantenimientoLETLideresDAO(java.lang.Integer)
	 */
	public void deleteHistoricoGerente(Integer oidHistoricoGerente) {
		getSqlMapClientTemplate().delete("spusicc.let.MantenimientoLETSQL.deleteHistoricoGerente",oidHistoricoGerente);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETLideresDAO#getIndicadorAsignarLider(java.util.Map)
	 */
	public String getIndicadorAsignarLider(Map criteria) {
		log.info("Entro a MantenimientoLETLideresDAOIbatis - getIndicadorAsignarLider(java.util.Map)");
		String resultado = (String)getSqlMapClientTemplate().queryForObject("spusicc.let.MantenimientoLETSQL.getIndicadorAsignarLider", criteria);
		log.info("Salio a MantenimientoLETLideresDAOIbatis - getIndicadorAsignarLider(java.util.Map) - Resultado:" + resultado);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETLideresDAO#executeActualizarLETLiderPendiente(java.util.Map)
	 */
	public void executeActualizarLETLiderPendiente(Map criteria) {
		log.info("Entro a MantenimientoLETLideresDAOIbatis - executeActualizarLETLiderPendiente(java.util.Map)");
		getSqlMapClientTemplate().update("spusicc.let.ProcesosLETSQL.executeActualizarLETLiderPendiente", criteria);
		log.info("Salio a MantenimientoLETLideresDAOIbatis - executeActualizarLETLiderPendiente(java.util.Map)");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETLideresDAO#getValidarAsignarLider(java.util.Map)
	 */
	public String getValidarAsignarLider(Map criteria) {
		log.info("Entro a MantenimientoLETLideresDAOIbatis - getValidarAsignarLider(java.util.Map)");
		String resultado = (String)getSqlMapClientTemplate().queryForObject("spusicc.let.MantenimientoLETSQL.getValidarAsignarLider", criteria);
		log.info("Salio a MantenimientoLETLideresDAOIbatis - getValidarAsignarLider(java.util.Map) - Resultado:" + resultado);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETLideresDAO#getUltimoResponsableSeccionValAsi(java.util.Map)
	 */
	public Map getUltimoResponsableSeccionValAsi(Map criteria) {
		log.info("Entro a MantenimientoLETLideresDAOIbatis - getUltimoResponsableSeccionValAsi(java.util.Map)");
		Map resultado = (HashMap) getSqlMapClientTemplate().queryForObject("spusicc.let.MantenimientoLETSQL.getUltimoResponsableSeccionValAsi", criteria);
		log.info("Salio a MantenimientoLETLideresDAOIbatis - getUltimoResponsableSeccionValAsi(java.util.Map) - Resultado:" + resultado);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETLideresDAO#updateHistoricoGerenteValAsi(java.util.Map)
	 */
	public void updateHistoricoGerenteValAsi(Map criteria) {
		log.info("Entro a MantenimientoLETLideresDAOIbatis - updateHistoricoGerenteValAsi(java.util.Map)");
		getSqlMapClientTemplate().update("spusicc.let.MantenimientoLETSQL.updateHistoricoGerenteValAsi", criteria);
		log.info("Salio a MantenimientoLETLideresDAOIbatis - updateHistoricoGerenteValAsi(java.util.Map)");
	}
	
	/* INI JJ PER-SiCC-2012-0201  */
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETLideresDAO#getOidPeriodoSiguienteByCodigoPeriodo(java.util.Map)
	 */
	public Integer getOidPeriodoSiguienteByCodigoPeriodo(Map criteria) {
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.let.MantenimientoLETSQL.getOidPeriodoSiguienteByCodigoPeriodo", criteria);
	}	
	/* FIN JJ PER-SiCC-2012-0201  */
	
	/* INI SA PER-SiCC-2012-0476  */
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETLideresDAO#getOidClienteByCodigoCliente(java.lang.String)
	 */
	public String getOidClienteByCodigoCliente(String codigoConsultora) {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.let.MantenimientoLETSQL.getOidClienteByCodigoCliente", codigoConsultora);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETLideresDAO#getOidLider(java.lang.String)
	 */
	public String getOidLider(String oidCliente) {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.let.MantenimientoLETSQL.getOidLider", oidCliente);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETLideresDAO#getDatosLider(java.lang.String)
	 */
	public Map getDatosLider(String oidLider) {
		return (Map) getSqlMapClientTemplate().queryForObject("spusicc.let.MantenimientoLETSQL.getDatosLider", oidLider);
	}
	/* FIN SA PER-SiCC-2012-0476  */

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETLideresDAO#getIndicadorZonaRegion(java.util.Map)
	 */
	public String getIndicadorZonaRegion(Map criteria) {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.let.MantenimientoLETSQL.validarAsignacionLiderZonaRegion", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETLideresDAO#executeValidarAsignacionLiderRegionZona(java.util.Map)
	 */
	public void executeValidarAsignacionLiderRegionZona(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.let.MantenimientoLETSQL.executeValidarAsignacionLiderRegionZona", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETLideresDAO#executeProcesoDesvinculacion(java.util.Map)
	 */
	public void executeProcesoDesvinculacion(Map criteria) {
		getSqlMapClientTemplate().queryForObject("spusicc.let.MantenimientoLETSQL.executeProcesoDesvinculacion", criteria);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETLideresDAO#validarDesvinculacion(java.util.Map)
	 */
	public String validarDesvinculacion(Map criteria) {
		// TODO Auto-generated method stub
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.let.MantenimientoLETSQL.validarDesvinculacion", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETLideresDAO#getLideres(java.lang.String)
	 */
	public List getLideres(String codigoPais) {
		return getSqlMapClientTemplate().queryForList("spusicc.let.MantenimientoLETSQL.getLideres", codigoPais);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETLideresDAO#getEncontrarProgramaLetCorporativo(java.lang.String)
	 */
	public Integer getEncontrarProgramaLetCorporativo(String periodoActual) {
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.let.MantenimientoLETSQL.getEncontrarProgramaLetCorporativo", periodoActual);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETLideresDAO#getMaximoPeriodoHastaLider(java.lang.String)
	 */
	public List getMaximoPeriodoHastaLider(String codigoCliente) {
		return getSqlMapClientTemplate().queryForList("spusicc.let.MantenimientoLETSQL.getMaximoPeriodoHastaLider", codigoCliente);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETLideresDAO#getDescripcionDesvinculacion(java.lang.String)
	 */
	public String getDescripcionDesvinculacion(String codigo) {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.let.MantenimientoLETSQL.getDescripcionDesvinculacion", codigo);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETLideresDAO#getIndicadorClienteCorreo(java.lang.String)
	 */
	public Integer getIndicadorClienteCorreo(String oidCliente) {
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.let.MantenimientoLETSQL.getIndicadorClienteCorreo", oidCliente);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETLideresDAO#getConcursoTramoPrograma(java.util.Map)
	 */
	public Map getConcursoTramoPrograma(Map criteria) {
		Map resultado = (HashMap) getSqlMapClientTemplate().queryForObject("spusicc.let.MantenimientoLETSQL.getConcursoTramoPrograma", criteria);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETLideresDAO#getCampanaCerrada(java.lang.String)
	 */
	public Integer getCampanaCerrada(String codigoPeriodo) {
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.let.MantenimientoLETSQL.getCampanaCerrada", codigoPeriodo);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETLideresDAO#getObjetivosAll()
	 */
	public List getObjetivosAll() {
		return getSqlMapClientTemplate().queryForList("spusicc.let.MantenimientoLETSQL.getObjetivosAll");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETLideresDAO#getObjetivosRetencion22All()
	 */
	public List getObjetivosRetencion22All() {
		return getSqlMapClientTemplate().queryForList("spusicc.let.MantenimientoLETSQL.getObjetivosRetencion22All");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETLideresDAO#getObjetivosRetencion3344All()
	 */
	public List getObjetivosRetencion3344All() {
		return getSqlMapClientTemplate().queryForList("spusicc.let.MantenimientoLETSQL.getObjetivosRetencion3344All");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETLideresDAO#insertObjetivo(java.util.Map)
	 */
	public void insertObjetivo(Map temp) {
		getSqlMapClientTemplate().insert("spusicc.let.MantenimientoLETSQL.insertObjetivo", temp);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETLideresDAO#updateObjetivo(java.util.Map)
	 */
	public void updateObjetivo(Map temp) {
		getSqlMapClientTemplate().update("spusicc.let.MantenimientoLETSQL.updateObjetivo", temp);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETLideresDAO#insertLiderObjetivo(java.util.Map)
	 */
	public void insertLiderObjetivo(Map temp) {
		getSqlMapClientTemplate().insert("spusicc.let.MantenimientoLETSQL.insertLiderObjetivo", temp);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETLideresDAO#updateLiderObjetivo(java.util.Map)
	 */
	public void updateLiderObjetivo(Map temp) {
		getSqlMapClientTemplate().update("spusicc.let.MantenimientoLETSQL.updateLiderObjetivo", temp);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETLideresDAO#insertSeccionObjetivo(java.util.Map)
	 */
	public void insertSeccionObjetivo(Map temp) {
		getSqlMapClientTemplate().insert("spusicc.let.MantenimientoLETSQL.insertSeccionObjetivo", temp);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETLideresDAO#updateSeccionObjetivo(java.util.Map)
	 */
	public void updateSeccionObjetivo(Map temp) {
		getSqlMapClientTemplate().update("spusicc.let.MantenimientoLETSQL.updateSeccionObjetivo", temp);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETLideresDAO#validarCodigoLiderRegistrado(java.util.Map)
	 */
	public String validarCodigoLiderRegistrado(Map criteria) {

		return String.valueOf(getSqlMapClientTemplate().queryForObject("spusicc.let.MantenimientoLETSQL.validarCodigoLiderRegistrado", criteria));
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETLideresDAO#getObjetivosRetencion3344(java.util.Map)
	 */
	public String getObjetivosRetencion3344(Map criteria) {
		
		return String.valueOf(getSqlMapClientTemplate().queryForObject("spusicc.let.MantenimientoLETSQL.getObjetivosRetencion3344", criteria));
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETLideresDAO#updateSeccionObjetivo44(java.util.Map)
	 */
	public void updateSeccionObjetivo44(Map temp) {
		getSqlMapClientTemplate().update("spusicc.let.MantenimientoLETSQL.updateSeccionObjetivo44", temp);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETLideresDAO#getOidCliente(java.util.Map)
	 */
	public String getOidCliente(Map criteria) {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.getOidCliente", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETLideresDAO#getEncontrarProgramaLecCorporativo(java.lang.String)
	 */
	/*public Map getEncontrarProgramaLecCorporativo(String periodoActual) {
		return (Map) getSqlMapClientTemplate().queryForObject("spusicc.let.MantenimientoLETSQL.getEncontrarProgramaLecCorporativo", periodoActual);
	}*/
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.MantenimientoLETLideresService#getIndicadorPosibleLider(java.util.Map)
	 */
	/*public Integer getIndicadorPosibleLider(Map criteria){
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.let.MantenimientoLETSQL.getIndicadorPosibleLider", criteria);
	}*/
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.MantenimientoLETLideresService#getIndicadorPosibleLiderHisto(java.util.Map)
	 */
	/*public Integer getIndicadorPosibleLiderHisto(Map criteria){
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.let.MantenimientoLETSQL.getIndicadorPosibleLiderHisto", criteria);
		
	}*/

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.MantenimientoLETLideresDAO#getVerificarLiderSeccionHistoGeren(java.util.Map)
	 */
	public Integer getVerificarLiderSeccionHistoGeren(Map criteria) {
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.let.MantenimientoLETSQL.getVerificarLiderSeccionHistoGeren", criteria);
	}


}