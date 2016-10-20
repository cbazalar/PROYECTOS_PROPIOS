package biz.belcorp.ssicc.dao.spusicc.zon.ibatis;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.zon.ProcesoZONActualizarUnidadesAdministrativasDAO;
import biz.belcorp.ssicc.dao.spusicc.zon.model.ConsultoraATrasvasar;
import biz.belcorp.ssicc.dao.spusicc.zon.model.UnidadAdministrativa;
import biz.belcorp.ssicc.dao.spusicc.zon.model.UnidadAdministrativaACrear;

import com.ibatis.sqlmap.client.SqlMapExecutor;

/**
 * Implementacion del DAO que ejecutara los metodos de proceso de unidades administrativas
 * <p>
 * <a href="ProcesoZONActualizarUnidadesAdministrativasDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Repository("spusicc.procesoZONUniAdmDAO")
public class ProcesoZONActualizarUnidadesAdministrativasDAOiBatis extends BaseDAOiBatis implements
		ProcesoZONActualizarUnidadesAdministrativasDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.ProcesoZONActualizarUnidadesAdministrativasDAO#validarRegionCerrada(java.util.Map)
	 */
	public void validarRegionCerrada(Map params) {
		getSqlMapClientTemplate().update("spusicc.zon.ProcesosZONSQL.validarRegionCerradaActUniAdm", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.ProcesoZONActualizarUnidadesAdministrativasDAO#validarRegionFacturando(java.util.Map)
	 */
	public void validarRegionFacturando(Map params) {
		getSqlMapClientTemplate().update("spusicc.zon.ProcesosZONSQL.validarRegionFacturadaActUniAdm", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.ProcesoZONActualizarUnidadesAdministrativasDAO#iniciarTablasUnidadAdministrativa(java.util.Map)
	 */
	public void iniciarTablasUnidadAdministrativa(Map params) {
		getSqlMapClientTemplate().update("spusicc.zon.ProcesosZONSQL.iniciarTablasActualizaUnidadAdministrativa");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.ProcesoZONActualizarUnidadesAdministrativasDAO#insertUnidadAdministrativa(biz.belcorp.ssicc.spusicc.zon.model.UnidadAdministrativa)
	 */
	public void insertUnidadAdministrativa(UnidadAdministrativa unidadAdministrativa) {
		getSqlMapClientTemplate().update("spusicc.zon.ProcesosZONSQL.insertarUnidadAdministrativaTmp", unidadAdministrativa);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.ProcesoZONActualizarUnidadesAdministrativasDAO#eliminarRegiones(java.util.List)
	 */
	public void eliminarRegiones(List params) {
		getSqlMapClientTemplate().update("spusicc.zon.ProcesosZONSQL.eliminarRegionesTmp", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.ProcesoZONActualizarUnidadesAdministrativasDAO#completarSGV(java.util.Map)
	 */
	public void completarSGV(Map params) {
		getSqlMapClientTemplate().update("spusicc.zon.ProcesosZONSQL.completarSGV");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.ProcesoZONActualizarUnidadesAdministrativasDAO#primeraValidacion(java.util.Map)
	 */
	public void primeraValidacion(Map params) {
		getSqlMapClientTemplate().update("spusicc.zon.ProcesosZONSQL.ejecutarPrimeraValidacionActuUnidAdmi", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.ProcesoZONActualizarUnidadesAdministrativasDAO#ejecutarValidacion(java.util.Map)
	 */
	public void ejecutarValidacion(Map params) {
		getSqlMapClientTemplate().update("spusicc.zon.ProcesosZONSQL.ejecutarValidacionActuUnidAdmi", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.ProcesoZONActualizarUnidadesAdministrativasDAO#obtenerUniAdmACrear()
	 */
	public List obtenerUniAdmACrear() {
		return getSqlMapClientTemplate().queryForList("spusicc.zon.ProcesosZONSQL.getUnidadesAdministrativasACrear");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.ProcesoZONActualizarUnidadesAdministrativasDAO#grabaDescripcionUniAdmACrear(biz.belcorp.ssicc.spusicc.zon.model.UnidadAdministrativaACrear)
	 */
	public void grabaDescripcionUniAdmACrear(UnidadAdministrativaACrear uniAdm) {
		getSqlMapClientTemplate().update("spusicc.zon.ProcesosZONSQL.grabaDescripcionUniAdmACrear", uniAdm);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.ProcesoZONActualizarUnidadesAdministrativasDAO#ejecutarActualizacionUnidadesAdministrativas(java.util.Map)
	 */
	public void ejecutarActualizacionUnidadesAdministrativas(Map params){
		getSqlMapClientTemplate().update("spusicc.zon.ProcesosZONSQL.ejecutarActualizacionUnidadesAdministrativas",params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.ProcesoZONActualizarUnidadesAdministrativasDAO#obtenerConsultorasATrasvasar()
	 */
	public List obtenerConsultorasATrasvasar() {
		return getSqlMapClientTemplate().queryForList("spusicc.zon.ProcesosZONSQL.getConsultorasATrasvasarActUniAdm");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.ProcesoZONActualizarUnidadesAdministrativasDAO#ejecutarTrasvaseConsultorasErrorEliminacion(java.util.Map)
	 */
	public void ejecutarTrasvaseConsultorasErrorEliminacion(Map params) {
		getSqlMapClientTemplate().update("spusicc.zon.ProcesosZONSQL.ejecutarTrasvaseConsErroElim", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.ProcesoZONActualizarUnidadesAdministrativasDAO#grabaCodigoTerritorioNuevoConsultoraTrasvasada(biz.belcorp.ssicc.spusicc.zon.model.ConsultoraATrasvasar)
	 */
	public void grabaCodigoTerritorioNuevoConsultoraTrasvasada(ConsultoraATrasvasar consuTrasv){
		getSqlMapClientTemplate().update("spusicc.zon.ProcesosZONSQL.grabaCodigoTerritorioNuevoConsultoraTrasvasada", consuTrasv);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.ProcesoZONActualizarUnidadesAdministrativasDAO#obtenerZonasDefinitivas(java.util.Map)
	 */
	public List obtenerZonasDefinitivas(Map params) {
		return getSqlMapClientTemplate().queryForList("spusicc.zon.ProcesosZONSQL.obtenerZonasDefinitivas", params);
	}	

	/**
	 * @param criteria
	 * @return
	 */
	public String obtenerCantidadRegistrosOperacion(Map criteria) {
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.zon.ProcesosZONSQL.obtenerCantidadRegistrosOperacion", criteria);
	}

	/**
	 * @param params
	 */
	public void insertTerritorioConsultora(Map params) {
		getSqlMapClientTemplate().insert("spusicc.zon.ProcesosZONSQL.insertTerritorioConsultoraTmp", params);
	}
	
	/**
	 * @param params
	 */
	public void deleteTerritorioConsultoraTmp() {
		getSqlMapClientTemplate().delete("spusicc.zon.ProcesosZONSQL.deleteTerritorioConsultoraTmp");
	}

	/**
	 * @param params
	 */
	public void execValidacionesModTerritorioConsultoras(Map params){
		getSqlMapClientTemplate().update("spusicc.zon.ProcesosZONSQL.execValidacionesModTerritorioConsultoras",params);
	}	

	/**
	 * @param params
	 */
	public void execActualizarModTerritorioConsultoras(Map params){
		getSqlMapClientTemplate().update("spusicc.zon.ProcesosZONSQL.execActualizarModTerritorioConsultoras",params);
	}	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.ProcesoZONActualizarUnidadesAdministrativasDAO#obtenerRegionProcesar(java.lang.String)
	 */
	public String obtenerRegionProcesar(String codRegion) {
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.zon.ProcesosZONSQL.obtenerRegionProcesar", codRegion);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.ProcesoZONActualizarUnidadesAdministrativasDAO#insertarUnidadAdministrativaDemanda(java.util.Map)
	 */
	public void insertarUnidadAdministrativaDemanda(Map params) {
		getSqlMapClientTemplate().insert("spusicc.zon.ProcesosZONSQL.insertarUnidadAdministrativaDemandaTmp", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.ProcesoZONActualizarUnidadesAdministrativasDAO#deleteUnidadAdministrativaDemanda()
	 */
	public void deleteUnidadAdministrativaDemanda() {
		getSqlMapClientTemplate().delete("spusicc.zon.ProcesosZONSQL.deleteUnidadAdministrativaDemandaTmp");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.ProcesoZONActualizarUnidadesAdministrativasDAO#getRegionesDemandaByPais(java.util.Map)
	 */
	public List getRegionesDemandaByPais(Map params) {
		return getSqlMapClientTemplate().queryForList("spusicc.zon.ProcesosZONSQL.getRegionesDemandaByPais", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.ProcesoZONActualizarUnidadesAdministrativasDAO#getUnidadAdministrativaDemanda(java.util.Map)
	 */
	public List getUnidadAdministrativaDemanda(Map params) {
		return getSqlMapClientTemplate().queryForList("spusicc.zon.ProcesosZONSQL.getUnidadAdministrativaDemanda", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.ProcesoZONActualizarUnidadesAdministrativasDAO#getErroresUnidadAdministrativaDemanda(java.util.Map)
	 */
	public List getErroresUnidadAdministrativaDemanda(Map params) {
		return getSqlMapClientTemplate().queryForList("spusicc.zon.ProcesosZONSQL.getErroresUnidadAdministrativaDemanda", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.ProcesoZONActualizarUnidadesAdministrativasDAO#executeValidarUnidadAdministrativaDemanda(java.util.Map)
	 */
	public void executeValidarUnidadAdministrativaDemanda(Map params){
		getSqlMapClientTemplate().update("spusicc.zon.ProcesosZONSQL.executeValidarUnidadAdministrativaDemanda",params);
	}	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.ProcesoZONActualizarUnidadesAdministrativasDAO#executeProcesarUnidadAdministrativaDemanda(java.util.Map)
	 */
	public void executeProcesarUnidadAdministrativaDemanda(Map params){
		getSqlMapClientTemplate().update("spusicc.zon.ProcesosZONSQL.executeProcesarUnidadAdministrativaDemanda",params);
	}	

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.ProcesoZONActualizarUnidadesAdministrativasDAO#getUnidadAdministrativaConsultora(java.util.Map)
	 */
	public List getUnidadAdministrativaConsultora(Map params) {
		return getSqlMapClientTemplate().queryForList("spusicc.zon.ProcesosZONSQL.getUnidadAdministrativaConsultora", params);
	}

	public Integer getUnidadAdministrativaConsultoraTotal(Map params) {
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.zon.ProcesosZONSQL.getUnidadAdministrativaConsultoraTotal", params);
	}
	
	
	public Integer getUnidadAdministrativaConsultoraTotalError(Map params) {
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.zon.ProcesosZONSQL.getUnidadAdministrativaConsultoraTotalError", params);
	}
	
	public Integer getUnidadAdministrativaConsultoraTotalOK(Map params) {
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.zon.ProcesosZONSQL.getUnidadAdministrativaConsultoraTotalOK", params);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.ProcesoZONActualizarUnidadesAdministrativasDAO#deleteTemporalRegiones()
	 */
	public void deleteTemporalRegiones() {
		getSqlMapClientTemplate().delete("spusicc.zon.ProcesosZONSQL.deleteTemporalRegiones", null);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.ProcesoZONActualizarUnidadesAdministrativasDAO#deleteTemporalZonas()
	 */
	public void deleteTemporalZonas() {
		getSqlMapClientTemplate().delete("spusicc.zon.ProcesosZONSQL.deleteTemporalZonas", null);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.ProcesoZONActualizarUnidadesAdministrativasDAO#deleteTemporalCampanha()
	 */
	public void deleteTemporalCampanha() {
		getSqlMapClientTemplate().delete("spusicc.zon.ProcesosZONSQL.deleteTemporalCampanha", null);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.ProcesoZONActualizarUnidadesAdministrativasDAO#deleteTemporalControlFacturacion()
	 */
	public void deleteTemporalControlFacturacion() {
		getSqlMapClientTemplate().delete("spusicc.zon.ProcesosZONSQL.deleteTemporalControlFacturacion", null);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.ProcesoZONActualizarUnidadesAdministrativasDAO#deleteTemporalConsultoras()
	 */
	public void deleteTemporalConsultoras() {
		getSqlMapClientTemplate().delete("spusicc.zon.ProcesosZONSQL.deleteTemporalConsultoras", null);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.ProcesoZONActualizarUnidadesAdministrativasDAO#insertTemporalRegiones(java.util.List, java.util.Map)
	 */
	public void insertTemporalRegiones(final List list, final Map params) {

		final Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				Iterator listIterator = list.iterator();
				while (listIterator.hasNext()) {
					Map dataInsert = (Map) listIterator.next();
					dataInsert.put("codigoPais"	,params.get("codigoPais"))	;
					dataInsert.put("codigoUsuario"	,params.get("codigoUsuario"))	;
					getSqlMapClientTemplate().insert("spusicc.zon.ProcesosZONSQL.insertTemporalRegiones", dataInsert);
				}
				int rowsaffected = executor.executeBatch();
				logger.debug("Inicio->" + timestamp.toString() + "Fin-->" + new Timestamp(System.currentTimeMillis()));
				logger.debug("rows afftected by insertTempRegiones: " + rowsaffected);
				return null;
			}
		});
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.ProcesoZONActualizarUnidadesAdministrativasDAO#insertTemporalZonas(java.util.List, java.util.Map)
	 */
	public void insertTemporalZonas(final List list, final Map params) {
		final Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				Iterator listIterator = list.iterator();
				while (listIterator.hasNext()) {
					Map dataInsert = (Map) listIterator.next();
					dataInsert.put("codigoPais"	,params.get("codigoPais"))	;
					dataInsert.put("codigoUsuario"	,params.get("codigoUsuario"))	;
					getSqlMapClientTemplate().insert("spusicc.zon.ProcesosZONSQL.insertTemporalZonas", dataInsert);
				}
				int rowsaffected = executor.executeBatch();
				logger.debug("Inicio->" + timestamp.toString() + "Fin-->" + new Timestamp(System.currentTimeMillis()));
				logger.debug("rows afftected by insertTempRegiones: " + rowsaffected);
				return null;
			}
		});
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.ProcesoZONActualizarUnidadesAdministrativasDAO#insertTemporalControlFacturacion(java.util.List, java.util.Map)
	 */
	public void insertTemporalControlFacturacion(final List list, final Map params) {
		final Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				Iterator listIterator = list.iterator();
				while (listIterator.hasNext()) {
					Map dataInsert = (Map) listIterator.next();
					dataInsert.put("codigoPais"	,params.get("codigoPais"))	;
					dataInsert.put("codigoUsuario"	,params.get("codigoUsuario"))	;
					getSqlMapClientTemplate().insert("spusicc.zon.ProcesosZONSQL.insertTemporalControlFacturacion", dataInsert);
				}
				int rowsaffected = executor.executeBatch();
				logger.debug("Inicio->" + timestamp.toString() + "Fin-->" + new Timestamp(System.currentTimeMillis()));
				logger.debug("rows afftected by insertTempRegiones: " + rowsaffected);
				return null;
			}
		});
	}
	
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.ProcesoZONActualizarUnidadesAdministrativasDAO#insertTemporalCampanha(java.util.List, java.util.Map)
	 */
	public void insertTemporalCampanha(final List list, final Map params) {
		final Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				Iterator listIterator = list.iterator();
				while (listIterator.hasNext()) {
					Map dataInsert = (Map) listIterator.next();
					dataInsert.put("codigoPais"	,params.get("codigoPais"))	;
					dataInsert.put("codigoUsuario"	,params.get("codigoUsuario"))	;
					getSqlMapClientTemplate().insert("spusicc.zon.ProcesosZONSQL.insertTemporalCampanha", dataInsert);
				}
				int rowsaffected = executor.executeBatch();
				logger.debug("Inicio->" + timestamp.toString() + "Fin-->" + new Timestamp(System.currentTimeMillis()));
				logger.debug("rows afftected by insertTempRegiones: " + rowsaffected);
				return null;
			}
		});
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.ProcesoZONActualizarUnidadesAdministrativasDAO#insertTemporalConsultoras(java.util.List, java.util.Map)
	 */
	public void insertTemporalConsultoras(final List list, final Map params) {
		final Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				Iterator listIterator = list.iterator();
				while (listIterator.hasNext()) {
					Map dataInsert = (Map) listIterator.next();
					dataInsert.put("codigoPais"	,params.get("codigoPais"))	;
					dataInsert.put("codigoUsuario"	,params.get("codigoUsuario"))	;
					getSqlMapClientTemplate().insert("spusicc.zon.ProcesosZONSQL.insertTemporalConsultoras", dataInsert);
				}
				int rowsaffected = executor.executeBatch();
				logger.debug("Inicio->" + timestamp.toString() + "Fin-->" + new Timestamp(System.currentTimeMillis()));
				logger.debug("rows afftected by insertTempRegiones: " + rowsaffected);
				return null;
			}
		});
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.ProcesoZONActualizarUnidadesAdministrativasDAO#updateMaestroRegiones(java.lang.String, java.util.Map)
	 */
	public void updateMaestroRegiones(String codigoPais, Map params){
		getSqlMapClientTemplate().update("spusicc.zon.ProcesosZONSQL.updateMaestroRegiones", params);
		
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.ProcesoZONActualizarUnidadesAdministrativasDAO#updateMaestroZonas(java.lang.String, java.util.Map)
	 */
	public void updateMaestroZonas(String codigoPais, Map params){
		getSqlMapClientTemplate().update("spusicc.zon.ProcesosZONSQL.updateMaestroZonas", params);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.ProcesoZONActualizarUnidadesAdministrativasDAO#updateMaestroCampanha(java.lang.String, java.util.Map)
	 */
	public void updateMaestroCampanha(String codigoPais, Map params){
		getSqlMapClientTemplate().update("spusicc.zon.ProcesosZONSQL.updateMaestroCampanha", params);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.ProcesoZONActualizarUnidadesAdministrativasDAO#updateMaestroControlFacturacion(java.lang.String, java.util.Map)
	 */
	public void updateMaestroControlFacturacion(String codigoPais, Map params){
		getSqlMapClientTemplate().update("spusicc.zon.ProcesosZONSQL.updateMaestroControlFacturacion", params);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.ProcesoZONActualizarUnidadesAdministrativasDAO#updateMaestroConsultoras(java.lang.String, java.util.Map)
	 */
	public void updateMaestroConsultoras(String codigoPais, Map params){
		getSqlMapClientTemplate().update("spusicc.zon.ProcesosZONSQL.updateMaestroConsultoras", params);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.ProcesoZONActualizarUnidadesAdministrativasDAO#getZONRegionesDirectorioVentas(java.lang.String, java.util.Map)
	 */
	public List getZONRegionesDirectorioVentas(String codigoPais, Map params){
		return getSqlMapClientTemplate().queryForList("spusicc.zon.ProcesosZONSQL.getZONRegionesDirectorioVentas", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.ProcesoZONActualizarUnidadesAdministrativasDAO#getZONZonasDirectorioVentas(java.lang.String, java.util.Map)
	 */
	public List getZONZonasDirectorioVentas(String codigoPais, Map params){
		return getSqlMapClientTemplate().queryForList("spusicc.zon.ProcesosZONSQL.getZONZonasDirectorioVentas", params);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.ProcesoZONActualizarUnidadesAdministrativasDAO#getZONHistorialGerentesDirectorioVentas(java.lang.String, java.util.Map, int, int)
	 */
	public List getZONHistorialGerentesDirectorioVentas(String codigoPais, Map params, int contador, int cantidadRegistros){
		return getSqlMapClientTemplate().queryForList("spusicc.zon.ProcesosZONSQL.getZONHistorialGerentesDirectorioVentas", params, contador, cantidadRegistros);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.zon.ProcesoZONActualizarUnidadesAdministrativasDAO#validarZonaCerrada(java.util.Map)
	 */
	public void validarZonaCerrada(Map params) {
		getSqlMapClientTemplate().update("spusicc.zon.ProcesosZONSQL.validarZonaCerradaActUniAdm", params);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.zon.ProcesoZONActualizarUnidadesAdministrativasDAO#validarZonaFacturando(java.util.Map)
	 */
	public void validarZonaFacturando(Map params) {
		getSqlMapClientTemplate().update("spusicc.zon.ProcesosZONSQL.validarZonaFacturadaActUniAdm", params);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.zon.ProcesoZONActualizarUnidadesAdministrativasDAO#obtenerZonaProcesar(java.lang.String)
	 */	
	public String obtenerZonaProcesar(String codZona) {
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.zon.ProcesosZONSQL.obtenerZonaProcesar", codZona);
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.zon.ProcesoZONActualizarUnidadesAdministrativasDAO#eliminarZonas(java.util.List)
	 */
	public void eliminarZonas(List params) {
		getSqlMapClientTemplate().update("spusicc.zon.ProcesosZONSQL.eliminarZonasTmp", params);
		
	}
	
	
	
	
	
	
}
