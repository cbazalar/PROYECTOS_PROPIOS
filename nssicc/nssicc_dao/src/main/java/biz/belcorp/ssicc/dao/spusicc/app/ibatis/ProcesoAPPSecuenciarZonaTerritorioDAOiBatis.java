package biz.belcorp.ssicc.dao.spusicc.app.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.app.ProcesoAPPSecuenciarZonaTerritorioDAO;

/**
 * <p>
 * <a href="ProcesoAPPSecuenciarZonaTerritorioDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:doliva@belcorp.biz">Dennys Oliva Iriarte</a>
 */
@Repository("spusicc.procesoAPPSecuenciarZonaTerritorioDAO")
public class ProcesoAPPSecuenciarZonaTerritorioDAOiBatis extends BaseDAOiBatis implements ProcesoAPPSecuenciarZonaTerritorioDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.ProcesoAPESecuenciarZonaTerritorioDAO#getRegionZonaSecuenciarList(java.util.Map)
	 */
	public List getRegionZonaSecuenciarList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.app.ProcesosAPPSQL.getRegionZonaSecuenciarList", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.app.dao.ProcesoAPPSecuenciarZonaTerritorioDAO#executeResetearSecuencia(java.util.Map)
	 */
	public String executeResetearSecuencia(Map criteria){
		getSqlMapClientTemplate().update("spusicc.app.ProcesosAPPSQL.executeResetearSecuencia", criteria);
		return criteria.get("mensajeError").toString();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.app.dao.ProcesoAPPSecuenciarZonaTerritorioDAO#getTerritoriosSecuenciarList(java.util.Map)
	 */
	public List getTerritoriosSecuenciarList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.app.ProcesosAPPSQL.getTerritoriosSecuenciarList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.app.dao.ProcesoAPPSecuenciarZonaTerritorioDAO#getCantidadZonasSinSecuencia(java.util.Map)
	 */
	public String getCantidadZonasSinSecuencia(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.app.ProcesosAPPSQL.getCantidadZonasSinSecuencia", criteria).get(0).toString();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.app.dao.ProcesoAPPSecuenciarZonaTerritorioDAO#getCantidadTerritoriosSinSecuencia(java.util.Map)
	 */
	public String getCantidadTerritoriosSinSecuencia(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.app.ProcesosAPPSQL.getCantidadTerritoriosSinSecuencia", criteria).get(0).toString();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.app.dao.ProcesoAPPSecuenciarZonaTerritorioDAO#getEmailSecuenciarList(java.util.Map)
	 */
	public List getEmailSecuenciarList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.app.ProcesosAPPSQL.getEmailSecuenciarList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.app.dao.ProcesoAPPSecuenciarZonaTerritorioDAO#grabarSecuenciacionZonas(java.util.Map)
	 */
	public void grabarSecuenciacionZonas(Map criteria){
		getSqlMapClientTemplate().update("spusicc.app.ProcesosAPPSQL.grabarSecuenciacionZonas", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.app.dao.ProcesoAPPSecuenciarZonaTerritorioDAO#getZonasSinSecuenciarList()
	 */
	public List getZonasSinSecuenciarList(){
		return getSqlMapClientTemplate().queryForList("spusicc.app.ProcesosAPPSQL.getZonasSinSecuenciarList", null);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.app.dao.ProcesoAPPSecuenciarZonaTerritorioDAO#getTerritoriosSinSecuenciarList()
	 */
	public List getTerritoriosSinSecuenciarList(){
		return getSqlMapClientTemplate().queryForList("spusicc.app.ProcesosAPPSQL.getTerritoriosSinSecuenciarList", null);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.app.dao.ProcesoAPPSecuenciarZonaTerritorioDAO#executeResetearSecuenciaTerritorios(java.util.Map)
	 */
	public void executeResetearSecuenciaTerritorios(Map criteria){
		getSqlMapClientTemplate().update("spusicc.app.ProcesosAPPSQL.executeResetearSecuenciaTerritorios", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.app.dao.ProcesoAPPSecuenciarZonaTerritorioDAO#grabarSecuenciacionTerritorios(java.util.Map)
	 */
	public void grabarSecuenciacionTerritorios(Map criteria){
		getSqlMapClientTemplate().update("spusicc.app.ProcesosAPPSQL.grabarSecuenciacionTerritorios", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.app.dao.ProcesoAPPSecuenciarZonaTerritorioDAO#deleteRutasTerri()
	 */
	public void deleteRutasTerri(){
		getSqlMapClientTemplate().delete("spusicc.app.ProcesosAPPSQL.deleteRutasTerri");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.app.dao.ProcesoAPPSecuenciarZonaTerritorioDAO#getCantidadSecuenciasExistentes(java.util.Map)
	 */
	public List getCantidadSecuenciasExistentes(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.app.ProcesosAPPSQL.getCantidadSecuenciasExistentes", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.app.dao.ProcesoAPPSecuenciarZonaTerritorioDAO#getSecuenciasZonasList(java.util.Map)
	 */
	public List getSecuenciasZonasList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.app.ProcesosAPPSQL.getSecuenciasZonasList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.app.dao.ProcesoAPPSecuenciarZonaTerritorioDAO#getSecuenciasZonasHomologadasList(java.util.Map)
	 */
	public List getSecuenciasZonasHomologadasList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.app.ProcesosAPPSQL.getSecuenciasZonasHomologadasList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.app.dao.ProcesoAPPSecuenciarZonaTerritorioDAO#executeResetearSecuenciaTerritorios2(java.util.Map)
	 */
	public void executeResetearSecuenciaTerritorios2(Map criteria){
		getSqlMapClientTemplate().update("spusicc.app.ProcesosAPPSQL.executeResetearSecuenciaTerritorios2", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.app.dao.ProcesoAPPSecuenciarZonaTerritorioDAO#getAuditoriaList(java.util.Map)
	 */
	public List getAuditoriaList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.app.ProcesosAPPSQL.getAuditoriaList", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.app.dao.ProcesoAPPSecuenciarZonaTerritorioDAO#insertaAuditoria(java.util.Map)
	 */
	public void insertaAuditoria(Map criteria){
		getSqlMapClientTemplate().insert("spusicc.app.ProcesosAPPSQL.insertaAuditoria", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.app.dao.ProcesoAPPSecuenciarZonaTerritorioDAO#getSecuenciaZonasList(java.util.Map)
	 */
	public List getSecuenciaZonasList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.app.ProcesosAPPSQL.getSecuenciaZonasList", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.app.dao.ProcesoAPPSecuenciarZonaTerritorioDAO#getSecuenciaTerritorioList(java.util.Map)
	 */
	public List getSecuenciaTerritorioList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.app.ProcesosAPPSQL.getSecuenciaTerritorioList", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.app.dao.ProcesoAPPSecuenciarZonaTerritorioDAO#executeProcesoSecuenciarZonaTerritorio(java.util.Map)
	 */
	public void executeProcesoSecuenciarZonaTerritorio(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.app.ProcesosAPPSQL.executeProcesoSecuenciarZonaTerritorio", criteria);
	}
	
}