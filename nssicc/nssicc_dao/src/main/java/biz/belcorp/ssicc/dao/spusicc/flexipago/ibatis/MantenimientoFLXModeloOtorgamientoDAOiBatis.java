package biz.belcorp.ssicc.dao.spusicc.flexipago.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.flexipago.MantenimientoFLXModeloOtorgamientoDAO;
import biz.belcorp.ssicc.dao.spusicc.flexipago.model.EstatusRechazoFLX;
import biz.belcorp.ssicc.dao.spusicc.flexipago.model.EstatusRecomendacionFLX;
import biz.belcorp.ssicc.dao.spusicc.flexipago.model.GrupoFLX;
import biz.belcorp.ssicc.dao.spusicc.flexipago.model.GrupoRegionFLX;
import biz.belcorp.ssicc.dao.spusicc.flexipago.model.GrupoVariableFLX;
import biz.belcorp.ssicc.dao.spusicc.flexipago.model.MotivoRechazoFLX;
import biz.belcorp.ssicc.dao.spusicc.flexipago.model.MotivoRecomendacionFLX;
import biz.belcorp.ssicc.dao.spusicc.flexipago.model.ParametroFLX;
import biz.belcorp.ssicc.dao.spusicc.flexipago.model.RangoLDC;

/**
 * <p>
 * <a href="MantenimientoFLXModeloOtorgamientoDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:aoviedo@sigcomt.com">Aurelio Oviedo</a>
 *
 */
@Repository("spusicc.mantenimientoFLXModeloOtorgamientoDAO")
public class MantenimientoFLXModeloOtorgamientoDAOiBatis extends BaseDAOiBatis implements MantenimientoFLXModeloOtorgamientoDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.dao.MantenimientoFLXModeloOtorgamientoDAO#getVariables(java.lang.String)
	 */
	public List getVariables(String tipoVariable) {
		return getSqlMapClientTemplate().queryForList("spusicc.flexipago.MantenimientoFLXModeloOtorgamientoSQL.getVariables", tipoVariable);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.dao.MantenimientoFLXModeloOtorgamientoDAO#insertGrupo(biz.belcorp.ssicc.spusicc.flexipago.dao.model.GrupoFLX, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertGrupo(GrupoFLX grupo, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.flexipago.MantenimientoFLXModeloOtorgamientoSQL.insertGrupo", grupo);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.dao.MantenimientoFLXModeloOtorgamientoDAO#getIdGrupo()
	 */
	public String getIdGrupo() {
		Long idGrupo =(Long)getSqlMapClientTemplate().queryForObject("spusicc.flexipago.MantenimientoFLXModeloOtorgamientoSQL.getIdGrupo");
		return idGrupo.toString();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.dao.MantenimientoFLXModeloOtorgamientoDAO#insertGrupoVariable(biz.belcorp.ssicc.spusicc.flexipago.dao.model.GrupoVariableFLX, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertGrupoVariable(GrupoVariableFLX grupoVariable, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.flexipago.MantenimientoFLXModeloOtorgamientoSQL.insertGrupoVariable", grupoVariable);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.dao.MantenimientoFLXModeloOtorgamientoDAO#getGrupos(java.lang.String)
	 */
	public List getGrupos(String descripcion) {
		return getSqlMapClientTemplate().queryForList("spusicc.flexipago.MantenimientoFLXModeloOtorgamientoSQL.getGrupos", descripcion);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.dao.MantenimientoFLXModeloOtorgamientoDAO#getGrupo(java.lang.String)
	 */
	public GrupoFLX getGrupo(String id) {
		return (GrupoFLX)getSqlMapClientTemplate().queryForObject("spusicc.flexipago.MantenimientoFLXModeloOtorgamientoSQL.getGrupo", id);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.dao.MantenimientoFLXModeloOtorgamientoDAO#getVariablesGrupo(java.lang.String, java.lang.String)
	 */
	public List getVariablesGrupo(String tipoVariable, String codigoGrupo) {
		Map params = new HashMap();
		params.put("tipoVariable", tipoVariable);
		params.put("codigoGrupo", codigoGrupo);
		
		return getSqlMapClientTemplate().queryForList("spusicc.flexipago.MantenimientoFLXModeloOtorgamientoSQL.getVariablesGrupo", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.dao.MantenimientoFLXModeloOtorgamientoDAO#updateGrupo(biz.belcorp.ssicc.spusicc.flexipago.dao.model.GrupoFLX, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateGrupo(GrupoFLX grupo, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.flexipago.MantenimientoFLXModeloOtorgamientoSQL.updateGrupo", grupo);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.dao.MantenimientoFLXModeloOtorgamientoDAO#updateGrupoVariable(biz.belcorp.ssicc.spusicc.flexipago.dao.model.GrupoVariableFLX, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateGrupoVariable(GrupoVariableFLX grupoVariable, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.flexipago.MantenimientoFLXModeloOtorgamientoSQL.updateGrupoVariable", grupoVariable);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.dao.MantenimientoFLXModeloOtorgamientoDAO#getGruposRegiones(java.lang.String)
	 */
	public List getGruposRegiones(String codigoGrupo) {
		return getSqlMapClientTemplate().queryForList("spusicc.flexipago.MantenimientoFLXModeloOtorgamientoSQL.getGruposRegiones", codigoGrupo);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.dao.MantenimientoFLXModeloOtorgamientoDAO#getRegionesAgrupadas(java.lang.String, boolean)
	 */
	public List getRegionesAgrupadas(String codigoGrupo, boolean flagAgrupadas) {
		List regiones = null;
		
		if(flagAgrupadas)
		{
			regiones = getSqlMapClientTemplate().queryForList("spusicc.flexipago.MantenimientoFLXModeloOtorgamientoSQL.getRegionesAgrupadas", codigoGrupo);
		}
		else
		{
			regiones = getSqlMapClientTemplate().queryForList("spusicc.flexipago.MantenimientoFLXModeloOtorgamientoSQL.getRegionesNoAgrupadas");
		}
		return regiones;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.dao.MantenimientoFLXModeloOtorgamientoDAO#removeRegionesGrupo(java.lang.String, biz.belcorp.ssicc.model.Usuario)
	 */
	public void removeRegionesGrupo(String codigoGrupo, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.flexipago.MantenimientoFLXModeloOtorgamientoSQL.removeRegionesGrupo", codigoGrupo);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.dao.MantenimientoFLXModeloOtorgamientoDAO#insertRegionGrupo(biz.belcorp.ssicc.spusicc.flexipago.dao.model.GrupoRegionFLX, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertRegionGrupo(GrupoRegionFLX grupoRegion, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.flexipago.MantenimientoFLXModeloOtorgamientoSQL.insertRegionGrupo", grupoRegion);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.dao.MantenimientoFLXModeloOtorgamientoDAO#getParametrosByGrupo(java.lang.String)
	 */
	public List getParametrosByGrupo(String codigoGrupo) {
		return getSqlMapClientTemplate().queryForList("spusicc.flexipago.MantenimientoFLXModeloOtorgamientoSQL.getParametrosByGrupo", codigoGrupo);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.dao.MantenimientoFLXModeloOtorgamientoDAO#updateParametro(biz.belcorp.ssicc.spusicc.flexipago.dao.model.ParametroFLX, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateParametro(ParametroFLX parametroFLX, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.flexipago.MantenimientoFLXModeloOtorgamientoSQL.updateParametro", parametroFLX);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.dao.MantenimientoFLXModeloOtorgamientoDAO#getMotivoRechazo(java.lang.String)
	 */
	public MotivoRechazoFLX getMotivoRechazo(String codigo) {
		return (MotivoRechazoFLX)getSqlMapClientTemplate().queryForObject("spusicc.flexipago.MantenimientoFLXModeloOtorgamientoSQL.getMotivoRechazo", codigo);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.dao.MantenimientoFLXModeloOtorgamientoDAO#getMotivosRechazoByCriteria(java.util.Map)
	 */
	public List getMotivosRechazoByCriteria(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.flexipago.MantenimientoFLXModeloOtorgamientoSQL.getMotivosRechazoByCriteria", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.dao.MantenimientoFLXModeloOtorgamientoDAO#updateMotivoRechazo(biz.belcorp.ssicc.spusicc.flexipago.dao.model.MotivoRechazoFLX, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateMotivoRechazo(MotivoRechazoFLX motivo, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.flexipago.MantenimientoFLXModeloOtorgamientoSQL.updateMotivoRechazo", motivo);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.dao.MantenimientoFLXModeloOtorgamientoDAO#insertMotivoRechazo(biz.belcorp.ssicc.spusicc.flexipago.dao.model.MotivoRechazoFLX, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertMotivoRechazo(MotivoRechazoFLX motivo, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.flexipago.MantenimientoFLXModeloOtorgamientoSQL.insertMotivoRechazo", motivo);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.dao.MantenimientoFLXModeloOtorgamientoDAO#getMotivosRecomendacionByCriteria(java.util.Map)
	 */
	public List getMotivosRecomendacionByCriteria(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.flexipago.MantenimientoFLXModeloOtorgamientoSQL.getMotivosRecomendacionByCriteria", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.dao.MantenimientoFLXModeloOtorgamientoDAO#getMotivoRecomendacion(java.lang.String)
	 */
	public MotivoRecomendacionFLX getMotivoRecomendacion(String codigo) {
		return (MotivoRecomendacionFLX)getSqlMapClientTemplate().queryForObject("spusicc.flexipago.MantenimientoFLXModeloOtorgamientoSQL.getMotivoRecomendacion", codigo);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.dao.MantenimientoFLXModeloOtorgamientoDAO#updateMotivoRecomendacion(biz.belcorp.ssicc.spusicc.flexipago.dao.model.MotivoRecomendacionFLX, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateMotivoRecomendacion(MotivoRecomendacionFLX motivo, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.flexipago.MantenimientoFLXModeloOtorgamientoSQL.updateMotivoRecomendacion", motivo);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.dao.MantenimientoFLXModeloOtorgamientoDAO#insertMotivoRecomendacion(biz.belcorp.ssicc.spusicc.flexipago.dao.model.MotivoRecomendacionFLX, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertMotivoRecomendacion(MotivoRecomendacionFLX motivo, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.flexipago.MantenimientoFLXModeloOtorgamientoSQL.insertMotivoRecomendacion", motivo);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.dao.MantenimientoFLXModeloOtorgamientoDAO#getEstatusRecomendacionByCriteria(java.util.Map)
	 */
	public List getEstatusRecomendacionByCriteria(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.flexipago.MantenimientoFLXModeloOtorgamientoSQL.getEstatusRecomendacionByCriteria", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.dao.MantenimientoFLXModeloOtorgamientoDAO#getEstatusRecomendacion(java.lang.String)
	 */
	public EstatusRecomendacionFLX getEstatusRecomendacion(String codigo) {
		return (EstatusRecomendacionFLX)getSqlMapClientTemplate().queryForObject("spusicc.flexipago.MantenimientoFLXModeloOtorgamientoSQL.getEstatusRecomendacion", codigo);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.dao.MantenimientoFLXModeloOtorgamientoDAO#updateEstatusRecomendacion(biz.belcorp.ssicc.spusicc.flexipago.dao.model.EstatusRecomendacionFLX, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateEstatusRecomendacion(EstatusRecomendacionFLX estatus, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.flexipago.MantenimientoFLXModeloOtorgamientoSQL.updateEstatusRecomendacion", estatus);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.dao.MantenimientoFLXModeloOtorgamientoDAO#insertEstatusRecomendacion(biz.belcorp.ssicc.spusicc.flexipago.dao.model.EstatusRecomendacionFLX, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertEstatusRecomendacion(EstatusRecomendacionFLX estatus, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.flexipago.MantenimientoFLXModeloOtorgamientoSQL.insertEstatusRecomendacion", estatus);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.dao.MantenimientoFLXModeloOtorgamientoDAO#getEstatusRechazoByCriteria(java.util.Map)
	 */
	public List getEstatusRechazoByCriteria(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.flexipago.MantenimientoFLXModeloOtorgamientoSQL.getEstatusRechazoByCriteria", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.dao.MantenimientoFLXModeloOtorgamientoDAO#getEstatusRechazo(java.lang.String)
	 */
	public EstatusRechazoFLX getEstatusRechazo(String codigo) {
		return (EstatusRechazoFLX)getSqlMapClientTemplate().queryForObject("spusicc.flexipago.MantenimientoFLXModeloOtorgamientoSQL.getEstatusRechazo", codigo);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.dao.MantenimientoFLXModeloOtorgamientoDAO#updateEstatusRechazo(biz.belcorp.ssicc.spusicc.flexipago.dao.model.EstatusRechazoFLX, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateEstatusRechazo(EstatusRechazoFLX estatus, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.flexipago.MantenimientoFLXModeloOtorgamientoSQL.updateEstatusRechazo", estatus);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.dao.MantenimientoFLXModeloOtorgamientoDAO#insertEstatusRechazo(biz.belcorp.ssicc.spusicc.flexipago.dao.model.EstatusRechazoFLX, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertEstatusRechazo(EstatusRechazoFLX estatus, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.flexipago.MantenimientoFLXModeloOtorgamientoSQL.insertEstatusRechazo", estatus);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.dao.MantenimientoFLXModeloOtorgamientoDAO#getParametro(java.lang.String)
	 */
	public ParametroFLX getParametro(String codigo) {
		return (ParametroFLX)getSqlMapClientTemplate().queryForObject("spusicc.flexipago.MantenimientoFLXModeloOtorgamientoSQL.getParametro", codigo);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.dao.MantenimientoFLXModeloOtorgamientoDAO#getGrupoVariable(java.lang.String)
	 */
	public GrupoVariableFLX getGrupoVariable(String codigo) {
		return (GrupoVariableFLX)getSqlMapClientTemplate().queryForObject("spusicc.flexipago.MantenimientoFLXModeloOtorgamientoSQL.getGrupoVariable", codigo);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.dao.MantenimientoFLXModeloOtorgamientoDAO#getRegionesByGrupo(java.lang.String)
	 */
	public List getRegionesByGrupo(String codigoGrupo) {
		return getSqlMapClientTemplate().queryForList("spusicc.flexipago.MantenimientoFLXModeloOtorgamientoSQL.getRegionesByGrupo", codigoGrupo);
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.dao.MantenimientoFLXModeloOtorgamientoDAO#getZonasExcluidasByCriteria(java.util.Map)
	 */
	public List getZonasExcluidasByCriteria(Map params) {
		return getSqlMapClientTemplate().queryForList("spusicc.flexipago.MantenimientoFLXModeloOtorgamientoSQL.getZonasExcluidasByCriteria", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.dao.MantenimientoFLXModeloOtorgamientoDAO#removeZonaExcluida(java.util.Map)
	 */
	public void removeZonaExcluida(Map params) {
		getSqlMapClientTemplate().delete("spusicc.flexipago.MantenimientoFLXModeloOtorgamientoSQL.removeZonaExcluida", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.dao.MantenimientoFLXModeloOtorgamientoDAO#insertZonaExcluida(java.util.Map)
	 */
	public void insertZonaExcluida(Map zonaExcluida) {
		getSqlMapClientTemplate().insert("spusicc.flexipago.MantenimientoFLXModeloOtorgamientoSQL.insertZonaExcluida", zonaExcluida);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.dao.MantenimientoFLXModeloOtorgamientoDAO#getRangosLDC()
	 */
	public List getRangosLDC() {
		return getSqlMapClientTemplate().queryForList("spusicc.flexipago.MantenimientoFLXModeloOtorgamientoSQL.getRangosLDC", null);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.dao.MantenimientoFLXModeloOtorgamientoDAO#getRangoLDC(java.lang.String)
	 */
	public RangoLDC getRangoLDC(String oid) {
		Map params = new HashMap();
		params.put("oid", oid);
		
		List rangos = getSqlMapClientTemplate().queryForList("spusicc.flexipago.MantenimientoFLXModeloOtorgamientoSQL.getRangosLDC", params);
		RangoLDC rango = null;
		if(rangos != null && rangos.size() > 0)
		{
			rango = (RangoLDC)rangos.get(0);
		}
		
		return rango;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.dao.MantenimientoFLXModeloOtorgamientoDAO#updateRangoLDC(biz.belcorp.ssicc.spusicc.flexipago.dao.model.RangoLDC, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateRangoLDC(RangoLDC rangoLDC, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.flexipago.MantenimientoFLXModeloOtorgamientoSQL.updateRangoLDC", rangoLDC);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.dao.MantenimientoFLXModeloOtorgamientoDAO#insertRangoLDC(biz.belcorp.ssicc.spusicc.flexipago.dao.model.RangoLDC, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertRangoLDC(RangoLDC rango, Usuario usuario) {
		getSqlMapClientTemplate().insert("spusicc.flexipago.MantenimientoFLXModeloOtorgamientoSQL.insertRangoLDC", rango);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.dao.MantenimientoFLXModeloOtorgamientoDAO#deleteTemporalLineasCredito(java.lang.String)
	 */
	public void deleteTemporalLineasCredito(String usuario) {
		getSqlMapClientTemplate().delete("spusicc.flexipago.MantenimientoFLXModeloOtorgamientoSQL.deleteTemporalLineasCredito", usuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.dao.MantenimientoFLXModeloOtorgamientoDAO#insertTemporalLineasCredito(java.util.Map)
	 */
	public void insertTemporalLineasCredito(Map params) {
		getSqlMapClientTemplate().insert("spusicc.flexipago.MantenimientoFLXModeloOtorgamientoSQL.insertTemporalLineasCredito", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.dao.MantenimientoFLXModeloOtorgamientoDAO#executeValidarArchivoLineasCredito(java.util.Map)
	 */
	public void executeValidarArchivoLineasCredito(Map params) {
		getSqlMapClientTemplate().update("spusicc.flexipago.MantenimientoFLXModeloOtorgamientoSQL.executeValidarArchivoLineasCredito", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.dao.MantenimientoFLXModeloOtorgamientoDAO#getErroresArchivoLineasCredito(java.util.Map)
	 */
	public List getErroresArchivoLineasCredito(Map params) {
		return getSqlMapClientTemplate().queryForList("spusicc.flexipago.MantenimientoFLXModeloOtorgamientoSQL.getErroresArchivoLineasCredito", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.dao.MantenimientoFLXModeloOtorgamientoDAO#executeProcesarArchivoLineasCredito(java.util.Map)
	 */
	public void executeProcesarArchivoLineasCredito(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.flexipago.MantenimientoFLXModeloOtorgamientoSQL.executeProcesarArchivoLineasCredito", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.dao.MantenimientoFLXModeloOtorgamientoDAO#deleteTemporalConsultorasDeshabilitar(java.lang.String)
	 */
	public void deleteTemporalConsultorasDeshabilitar(String codigoUsuario) {
		getSqlMapClientTemplate().delete("spusicc.flexipago.MantenimientoFLXModeloOtorgamientoSQL.deleteTemporalConsultorasDeshabilitar", codigoUsuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.dao.MantenimientoFLXModeloOtorgamientoDAO#insertTemporalConsultorasDeshabilitar(java.util.Map)
	 */
	public void insertTemporalConsultorasDeshabilitar(Map params) {
		getSqlMapClientTemplate().insert("spusicc.flexipago.MantenimientoFLXModeloOtorgamientoSQL.insertTemporalConsultorasDeshabilitar", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.dao.MantenimientoFLXModeloOtorgamientoDAO#executeValidarArchivoConsultorasDeshabilitar(java.util.Map)
	 */
	public void executeValidarArchivoConsultorasDeshabilitar(Map params) {
		getSqlMapClientTemplate().update("spusicc.flexipago.MantenimientoFLXModeloOtorgamientoSQL.executeValidarArchivoConsultorasDeshabilitar", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.dao.MantenimientoFLXModeloOtorgamientoDAO#getErroresArchivoConsultorasDeshabilitar(java.util.Map)
	 */
	public List getErroresArchivoConsultorasDeshabilitar(Map params) {
		return getSqlMapClientTemplate().queryForList("spusicc.flexipago.MantenimientoFLXModeloOtorgamientoSQL.getErroresArchivoConsultorasDeshabilitar", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.dao.MantenimientoFLXModeloOtorgamientoDAO#executeProcesarArchivoConsultorasDeshabilitar(java.util.Map)
	 */
	public void executeProcesarArchivoConsultorasDeshabilitar(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.flexipago.MantenimientoFLXModeloOtorgamientoSQL.executeProcesarArchivoConsultorasDeshabilitar", criteria);
	}
	
}