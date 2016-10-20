package biz.belcorp.ssicc.dao.edu.ibatis;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.edu.ProcesoEDUComercialDAO;
import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;

import com.ibatis.sqlmap.client.SqlMapExecutor;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoEDUComercialDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:msilva@belcorp.biz">Marco Silva Moreno</a>
 */
@Repository("edu.procesoEDUComercialDAO")
public class ProcesoEDUComercialDAOiBatis extends
		BaseDAOiBatis implements ProcesoEDUComercialDAO{

	public void deleteTemporalPedidos(Map params) { 
		getSqlMapClientTemplate().delete("edu.ProcesoEDUComercialSQL.deleteTemporalPedidos",
				params);
	}

	public void deleteTemporalPedidosCursos() { 
		getSqlMapClientTemplate().delete("edu.ProcesoEDUComercialSQL.deleteTemporalPedidosCursos",
				null);
	}
	
	/**
	 *  Inserta pedidos desde el Comercial
	 *	@param  
	 */
	public void insertTemporalPedidos(final List list) {

			final Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
				public Object doInSqlMapClient(SqlMapExecutor executor)
						throws SQLException {
					executor.startBatch();
					Iterator listIterator = list.iterator();
					while (listIterator.hasNext()) {
						Map dataInsert = (Map) listIterator.next();
						logger.debug("dataInsert: " + dataInsert);
						getSqlMapClientTemplate().insert("edu.ProcesoEDUComercialSQL.insertPedidosConsultora", dataInsert);
					}
					int rowsaffected = executor.executeBatch();
					logger.debug("Inicio->" + timestamp.toString() + "Fin-->" + new Timestamp(System.currentTimeMillis()));
					logger.debug("rows afftected by insertTemporalPedidos: " + rowsaffected);
					return null;
				}
			});
		
	}
	
	
	/**
	 *  Inserta pedidos desde el Comercial
	 *	@param  
	 */
	public void insertTemporalPedidosNombreCompleto(final List list) {

			final Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
				public Object doInSqlMapClient(SqlMapExecutor executor)
						throws SQLException {
					executor.startBatch();
					Iterator listIterator = list.iterator();
					while (listIterator.hasNext()) {
						Map dataInsert = (Map) listIterator.next();
						logger.debug("dataInsert: " + dataInsert);
						getSqlMapClientTemplate().insert("edu.ProcesoEDUComercialSQL.insertPedidosConsultoraNombreCompleto", dataInsert);
					}
					int rowsaffected = executor.executeBatch();
					logger.debug("Inicio->" + timestamp.toString() + "Fin-->" + new Timestamp(System.currentTimeMillis()));
					logger.debug("rows afftected by insertTemporalPedidos: " + rowsaffected);
					return null;
				}
			});
		
	}

	/**
	 *  Inserta pedidos Facturados desde el Comercial
	 *	@param  
	 */
	public void insertTemporalPedidosFacturados(final List list) {

			final Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
				public Object doInSqlMapClient(SqlMapExecutor executor)
						throws SQLException {
					executor.startBatch();
					Iterator listIterator = list.iterator();
					while (listIterator.hasNext()) {
						Map dataInsert = (Map) listIterator.next();
						getSqlMapClientTemplate().insert("edu.ProcesoEDUComercialSQL.insertPedidosConsultoraFacturadas", dataInsert);
					}
					int rowsaffected = executor.executeBatch();
					logger.debug("Inicio->" + timestamp.toString() + "Fin-->" + new Timestamp(System.currentTimeMillis()));
					logger.debug("rows afftected by insertTemporalPedidosFacturados: " + rowsaffected);
					return null;
				}
			});
	}

	/**
	 *  Inserta cursos Facturados desde el Comercial
	 *	@param  
	 */
	public void insertTemporalPedidosCursosFacturados(final List list, final String codigoPais, final String codigoEmpresa) {

			final Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
				public Object doInSqlMapClient(SqlMapExecutor executor)
						throws SQLException {
					executor.startBatch();
					Iterator listIterator = list.iterator();
					while (listIterator.hasNext()) {
						Map dataInsert = (Map) listIterator.next();
						dataInsert.put("codigoPais", codigoPais );
						dataInsert.put("codigoEmpresa", codigoEmpresa );
						getSqlMapClientTemplate().insert("edu.ProcesoEDUComercialSQL.insertPedidosCursosFacturadas", dataInsert);
					}
					int rowsaffected = executor.executeBatch();
					logger.debug("Inicio->" + timestamp.toString() + "Fin-->" + new Timestamp(System.currentTimeMillis()));
					logger.debug("rows afftected by insertTemporalPedidosCursosFacturados: " + rowsaffected);
					return null;
				}
			});
	}
	
	/**
	 *  Carga Pedidos desde Temporal hacia Historico
	 *	@param  
	 */
	public void updateHistoricoPedidos(String codigoPais, Map params){
		getSqlMapClientTemplate().update("edu.ProcesoEDUComercialSQL.updateHistoricoPedidos", params);
		
	}

	/**
	 *  Carga Pedidos Facturados desde Temporal hacia Historico
	 *	@param  
	 */
	public void updateHistoricoPedidosFacturados(String codigoPais, Map params){
		getSqlMapClientTemplate().update("edu.ProcesoEDUComercialSQL.updateHistoricoPedidosFacturados", params);
		
	}

	/**
	 *  Carga Cursos Facturados desde Temporal hacia Historico
	 *	@param  
	 */
	public void updatePedidosCursosFacturados(String codigoPais, Map params){
		getSqlMapClientTemplate().update("edu.ProcesoEDUComercialSQL.updatePedidosCursosFacturados", params);
		
	}

	/**
	 *  Carga Cursos No Facturados desde Temporal hacia Historico
	 *	@param  
	 */
	public void updatePedidosCursosNoFacturados(String codigoPais, Map params){
		getSqlMapClientTemplate().update("edu.ProcesoEDUComercialSQL.updatePedidosCursosNoFacturados", params);
		
	}
	
	public void updateMaestroClientes(String codigoPais, Map params){
		getSqlMapClientTemplate().update("edu.ProcesoEDUComercialSQL.updateMaestroClientes", params);
	}
	
	/**
	 *  Inserta Consultoras Nuevas desde el Comercial
	 *	@param  
	 */
	public void insertMaestroClientes(final List list, final Map params) throws Exception{
			try{
			final Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	
			getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
				public Object doInSqlMapClient(SqlMapExecutor executor)
						throws SQLException {
					executor.startBatch();
					Iterator listIterator = list.iterator();
					int i=0;
					while (listIterator.hasNext()) {
						i=i+1;
						
						Map dataInsert = (Map) listIterator.next();
						dataInsert.put("codigoPais"	,params.get("codigoPais"))	;
						dataInsert.put("codigoEmpresa"  ,params.get("codigoEmpresa"  )) ;
						dataInsert.put("codusuario"  ,params.get("codusuario"  )) ;
						logger.debug("dataInsertClietnes " + dataInsert);
						getSqlMapClientTemplate().insert("edu.ProcesoEDUComercialSQL.insertMaestroClientes", dataInsert);
					}
					int rowsaffected = executor.executeBatch();
					logger.debug("Inicio->" + timestamp.toString() + "Fin-->" + new Timestamp(System.currentTimeMillis()));
					logger.debug("rows afftected by insertMaestroClientes: " + rowsaffected);
					return null;
				}
			});
			} catch (Exception e) {
				throw new Exception(
						"Error al cargar los registros de la tabla Maestro de Clientes Comercial: "
								+ e.getMessage());
			}
	}
	
	public void deleteTemporalZonas() {
		getSqlMapClientTemplate().delete(
				"edu.ProcesoEDUComercialSQL.deleteTemporalZonas",
				null);
	}

	public void deleteTemporalConsultorasAptasporProgramar() {
		getSqlMapClientTemplate().delete(
				"edu.ProcesoEDUComercialSQL.deleteTemporalConsultorasAptasporProgramar",
				null);
	}
	
	/**
	 *  Inserta Zonas desde el Comercial
	 *	@param  
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
						dataInsert.put("codigoEmpresa"  ,params.get("codigoEmpresa"  )) ;
						getSqlMapClientTemplate().insert("edu.ProcesoEDUComercialSQL.insertTemporalZonas", dataInsert);
					}
					int rowsaffected = executor.executeBatch();
					logger.debug("Inicio->" + timestamp.toString() + "Fin-->" + new Timestamp(System.currentTimeMillis()));
					logger.debug("rows afftected by insertTempZonas: " + rowsaffected);
					return null;
				}
			});
		
	}

	/**
	 *  Carga Zonas desde Temporal hacia Maestro
	 *	@param  
	 */
	public void updateMaestroZonas(String codigoPais, Map params){
		getSqlMapClientTemplate().update("edu.ProcesoEDUComercialSQL.updateMaestroZonas", params);
		
	}

	public void deleteTemporalRegiones() {
		getSqlMapClientTemplate().delete(
				"edu.ProcesoEDUComercialSQL.deleteTemporalRegiones",
				null);
	}

	/**
	 *  Inserta Regiones desde el Comercial
	 *	@param  
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
						dataInsert.put("codigoEmpresa"  ,params.get("codigoEmpresa"  )) ;
						getSqlMapClientTemplate().insert("edu.ProcesoEDUComercialSQL.insertTemporalRegiones", dataInsert);
					}
					int rowsaffected = executor.executeBatch();
					logger.debug("Inicio->" + timestamp.toString() + "Fin-->" + new Timestamp(System.currentTimeMillis()));
					logger.debug("rows afftected by insertTempRegiones: " + rowsaffected);
					return null;
				}
			});
	}

	/**
	 *  Carga Regiones desde Temporal hacia Maestro
	 *	@param  
	 */
	public void updateMaestroRegiones(String codigoPais, Map params){
		getSqlMapClientTemplate().update("edu.ProcesoEDUComercialSQL.updateMaestroRegiones", params);
		
	}

	public void deleteTemporalControlFacturacion() {
		getSqlMapClientTemplate().delete(
				"edu.ProcesoEDUComercialSQL.deleteTemporalControlFacturacion",
				null);
	}

	/**
	 *  Inserta ControlFacturacion desde el Comercial
	 *	@param  
	 */
	public void insertTemporalControlFacturacion(final List list,final Map params) {

			final Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
				public Object doInSqlMapClient(SqlMapExecutor executor)
						throws SQLException {
					executor.startBatch();
					Iterator listIterator = list.iterator();
				
					while (listIterator.hasNext()) {
						Map dataInsert = (Map) listIterator.next();
						dataInsert.put("codigoPais"	,params.get("codigoPais"))	;
						dataInsert.put("codigoEmpresa"  ,params.get("codigoEmpresa"  )) ;
						getSqlMapClientTemplate().insert("edu.ProcesoEDUComercialSQL.insertTemporalControlFacturacion", dataInsert);
					}
					int rowsaffected = executor.executeBatch();
					logger.debug("Inicio->" + timestamp.toString() + "Fin-->" + new Timestamp(System.currentTimeMillis()));
					logger.debug("rows afftected by insertTempArchivoControl: " + rowsaffected);
					return null;
				}
			});
	}
	
	
	/**
	 *  Actualiza el Envio de Aptas para el Historico de Aptas.
	 *	@param  
	 */
	public void updateEnvioHistoricoAptas(Map params)	{
		getSqlMapClientTemplate().update("edu.ProcesoEDUComercialSQL.updateEnvioHistoricoAptas", params);
	}

	/**
	 *  Actualiza el Envio de Aptas Curso con Costo para el Historico de Aptas.
	 *	@param  
	 */
	public void updateEnvioHistoricoAptasCosto(Map params)	{
		getSqlMapClientTemplate().update("edu.ProcesoEDUComercialSQL.updateEnvioHistoricoAptasCosto", params);
	}
	
	/**
	 *  Carga ControlFacturacion desde Temporal hacia Maestro
	 *	@param  
	 */
	public void updateControlFacturacion(String codigoPais, Map params){
		getSqlMapClientTemplate().update("edu.ProcesoEDUComercialSQL.updateControlFacturacion", params);
		
	}

	/**
	 *  Obtiene las Consultoras Aptas
	 *	@param  
	 */
	public List getConsultorasAptas(Map params){
		List consultorasAptas = getSqlMapClientTemplate().queryForList(
				"edu.ProcesoEDUComercialSQL.getConsultorasAptas",
				params);
		return consultorasAptas;
	}

	/**
	 *  Obtiene las Consultoras Aptas con Costo
	 *	@param  
	 */
	public List getConsultorasAptasCosto(Map params){
		List consultorasAptas = getSqlMapClientTemplate().queryForList(
				"edu.ProcesoEDUComercialSQL.getConsultorasAptasCosto",
				params);
		return consultorasAptas;
	}

	/**
	 *  Obtiene las Consultoras Aptas para Programar
	 *	@param  
	 */
	public List getConsultorasAptasporProgramar(Map params){
		List enviarConsultorasAptas = getSqlMapClientTemplate().queryForList(
				"edu.ProcesoEDUComercialSQL.getConsultorasAptasporProgramar",
				params);
		return enviarConsultorasAptas;
	}

	/**
	 *  Inserta las Consultoras Aptas por Programar en la tabla Temporal Planificacion de Programacin de Cursos.
	 *	@param  
	 */
	public void insertTemporalConsultorasAptasporProgramar(final List list, final Map params)
	{
			final Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
				public Object doInSqlMapClient(SqlMapExecutor executor)
						throws SQLException {
					executor.startBatch();
					Iterator listIterator = list.iterator();
					while (listIterator.hasNext()) {
						Map dataInsert = (Map) listIterator.next();
						dataInsert.put("codigoPais"	,params.get("codigoPais"))	;
						dataInsert.put("codigoEmpresa"  ,params.get("codigoEmpresa"  )) ;
						getSqlMapClientTemplate().insert("edu.ProcesoEDUComercialSQL.insertTemporalAptasporProgramar", dataInsert);
					}
					int rowsaffected = executor.executeBatch();
					logger.debug("Inicio->" + timestamp.toString() + "Fin-->" + new Timestamp(System.currentTimeMillis()));
					logger.debug("rows afftected by insertTemporalConsultorasAptasporProgramar: " + rowsaffected);
					return null;
				}
			});
	}
	 
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDUComercialDAO#insertMaestroProgramacionCursos(java.util.Map)
	 */
	public void insertMaestroProgramacionCursos(final Map params)
			throws Exception {
		getSqlMapClientTemplate().insert("edu.ProcesoEDUComercialSQL.insertMaestroProgramacionCursos",
				params);
	}

	/**
	 *  Actualiza el Envio de Aptas Programadas para el Historico de Aptas.
	 *	@param  
	 */
	public void updateEnvioHistoricoAptasProgramadas(Map params) {
		getSqlMapClientTemplate()
				.update("edu.ProcesoEDUComercialSQL.updateEnvioHistoricoAptasProgramadas",
						params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDUComercialDAO#updateNumeroLoteSgte(java.util.Map)
	 */
	public void updateNumeroLoteSgte(Map params) {
		getSqlMapClientTemplate().update(
				"edu.ProcesoEDUComercialSQL.updateNumeroLote", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDUComercialDAO#getNumeroLote(java.util.Map)
	 */
	public String getNumeroLote(Map params) {
		return (String) getSqlMapClientTemplate().queryForObject(
				"edu.ProcesoEDUComercialSQL.getNumeroLoteEducacion", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDUComercialDAO#getAptasHistoricasFacturacion(java.util.Map)
	 */
	public List getAptasHistoricasFacturacion(Map params) throws Exception{
		List consultorasAptas = getSqlMapClientTemplate().queryForList(
				"edu.ProcesoEDUComercialSQL.getResultCierreFacturacion",
				params);
		return consultorasAptas;
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDUComercialDAO#getResultBeneficiosCapacitadas(java.util.Map)
	 */
	public List getResultBeneficiosCapacitadas(Map params) throws Exception{
		String indicadorDespachoClasificacion = (String)params.get("indicadorDespachoClasificacion");
		List beneficiosCapacitadas = new ArrayList();
		if (Constants.EDU_INDICADOR_DESPACHO_CLASIFICACION_SI.equals(indicadorDespachoClasificacion))
			beneficiosCapacitadas = getSqlMapClientTemplate().queryForList(
					"edu.ProcesoEDUComercialSQL.getResultBeneficiosCapacitadasxDespacho",params);
		else
			beneficiosCapacitadas = getSqlMapClientTemplate().queryForList(
					"edu.ProcesoEDUComercialSQL.getResultBeneficiosCapacitadas",params);
		return beneficiosCapacitadas;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDUComercialDAO#updateEnvioBeneficiosCapacitadas(java.util.Map)
	 */
	public void updateEnvioBeneficiosCapacitadas(Map params)	{
		getSqlMapClientTemplate().update("edu.ProcesoEDUComercialSQL.updateEnvioBeneficios", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDUComercialDAO#updateCargarBeneficiosCapacitadas(java.lang.String, java.util.Map)
	 */
	public void updateCargarBeneficiosCapacitadas(String codigoPais, Map params){
		getSqlMapClientTemplate().update("edu.ProcesoEDUComercialSQL.updateCargarBeneficios", params);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDUComercialDAO#deleteHistoricoBloqueoConsultoraTemporal(java.util.Map)
	 */
	public void deleteHistoricoBloqueoConsultoraTemporal(Map params) throws Exception {
		getSqlMapClientTemplate().delete("edu.ProcesoEDUComercialSQL.deleteHistoricoBloqueoConsultoraTemporal", 
					params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDUComercialDAO#getHistoricoBloqueoConsultoraTemporal(java.util.Map)
	 */
	public List getHistoricoBloqueoConsultoraTemporal(Map params) throws Exception {
		List lista = getSqlMapClientTemplate().queryForList(
				"edu.ProcesoEDUComercialSQL.getHistoricoBloqueoConsultoraTemporal",
				params);
		return lista;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDUComercialDAO#getGenerarPlanillaProgramacion(java.util.Map)
	 */
	public List getGenerarPlanillaProgramacion(Map params){
		List lista = getSqlMapClientTemplate().queryForList(
				"edu.ProcesoEDUComercialSQL.getGenerarPlanillaProgramacion",
				params);
		return lista;
	}
			
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDUComercialDAO#insertMigracionComercialConsultoraTemporal(java.lang.String, java.util.List)
	 */
	public void insertMigracionComercialConsultoraTemporal(String codigoPais, final List lista) throws Exception {
		try {
			final Timestamp timestamp = new Timestamp(System
					.currentTimeMillis());
			getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
				public Object doInSqlMapClient(SqlMapExecutor executor)
						throws SQLException {
					executor.startBatch();
					Iterator listIterator = lista.iterator();
					while (listIterator.hasNext()) {
						Map dataInsert = (Map) listIterator.next();
						logger.debug("dataInsewrt " + dataInsert);
						getSqlMapClientTemplate()
								.insert(
										"edu.ProcesoEDUComercialSQL.insertMigracionComercialConsultoraTemporal",
										dataInsert);
					}
					int rowsaffected = executor.executeBatch();
					logger.debug("Inicio->" + timestamp.toString()
							+ "Fin-->"
							+ new Timestamp(System.currentTimeMillis()));
					logger.debug("rows afftected by insertMigracionComercialConsultoraTemporal: "
									+ rowsaffected);
					return null;
				}
			});
		} catch (Exception e) {
			throw new Exception(
					"Error al cargar los registros de la tabla Aptas del Sistema Comercial: "
							+ e.getMessage());

		} 
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDUComercialDAO#executeInsertaEquivalenciaClasificacion(java.util.List)
	 */
	public void executeInsertaEquivalenciaClasificacion(final List lista) throws Exception {
		Iterator listIterator = lista.iterator();
		while (listIterator.hasNext()) {
			Map data = (Map) listIterator.next();
//			String codigoPais = (String)data.get("codigoPais");
//			String codigoEmpresa = (String)data.get("codigoEmpresa");
//			String codigoCurso   = (String)data.get("codigoCurso");
			BigDecimal indicadorEnviaInvit =(BigDecimal)data.get("indicadorEnviaInvit");
			if(indicadorEnviaInvit!=null ){
				log.debug("indicadorEnviaInvit sin costo "+ indicadorEnviaInvit.intValue());
				if(indicadorEnviaInvit.intValue()==1)
				   data.put("indicadorEnviaInvit","1");
				else
				  data.put("indicadorEnviaInvit","0");
			}else{
			  //es un envio de aptas con costo ;)
				data.put("indicadorEnviaInvit","1");
				log.debug("indicadorEnviaInvit con costo "+ data.get("indicadorEnviaInvit"));
				
			} 
			
			
//			Map verificaCurso = new HashMap();
//			verificaCurso.put("codigoPais", codigoPais);
//			verificaCurso.put("codigoEmpresa", codigoEmpresa);
//			verificaCurso.put("codigoCurso", codigoCurso);
//			Integer verifica = (Integer) getSqlMapClientTemplate().queryForObject("edu.ProcesoEDUComercialSQL.esCursoNoPrimerPedido", verificaCurso);
//			if (verifica != null && verifica.intValue() > 0 ) {
//	Se quita la validacion del curso de Primer Pedido			
				logger.debug("dataInsert: " + data);
				getSqlMapClientTemplate().update("edu.ProcesoEDUComercialSQL.executeInsertaEquivalenciaClasificacion", data);
//			}	
			
		}
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDUComercialDAO#executeProcesoConsultorasAptasMixtoBloqueo(java.util.Map)
	 */
	public void executeProcesoConsultorasAptasMixtoBloqueo(Map params) throws Exception  {
		getSqlMapClientTemplate().update("edu.ProcesoEDUComercialSQL.executeProcesoConsultorasAptasMixtoBloqueo", params);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDUComercialDAO#getConsultorasAptasMixtoBloqueo(java.util.Map)
	 */
	public List getConsultorasAptasMixtoBloqueo(Map params) throws Exception {
		List consultorasAptas = getSqlMapClientTemplate().queryForList(
				"edu.ProcesoEDUComercialSQL.getConsultorasAptasMixtoBloqueo",
				params);
		return consultorasAptas;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDUComercialDAO#getListaCursosByTipoCurso(java.util.Map)
	 */
	public String[] getListaCursosByTipoCurso(Map params) throws Exception {
		String [] listCurso = new String[0];
		List list = getSqlMapClientTemplate().queryForList(
				"edu.ProcesoEDUComercialSQL.getListaCursosByTipoCurso",
				params);
		log.debug(" getListaCursosByTipoCurso "+ list.size());
		
		Iterator it = list.iterator();
		int i=0;
		listCurso = new String[list.size()];
		while(it.hasNext()){
			String codigoCurso = (String)it.next();
			listCurso[i++]=codigoCurso;
		}
		return listCurso;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDUComercialDAO#executeProcesoConsultorasCompraCursoCosto(java.util.Map)
	 */
	public void executeProcesoConsultorasCompraCursoCosto(Map params) throws Exception {
		getSqlMapClientTemplate().update("edu.ProcesoEDUComercialSQL.executeProcesoConsultorasCompraCursoCosto", params);
		
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDUComercialDAO#getConsultorasConfirmCursoCosto(java.util.Map)
	 */
	public List getConsultorasConfirmCursoCosto(Map params) {
		List consultorasAptas = getSqlMapClientTemplate().queryForList(
				"edu.ProcesoEDUComercialSQL.getConsultorasConfirmCursoCosto",
				params);
		return consultorasAptas;
	}	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDUComercialDAO#getPedidosCursosNoFacturadosMixtoYCosto(java.lang.String, java.util.Map)
	 */
	public List getPedidosCursosNoFacturadosMixtoYCosto(String codigoPais, Map params) {
		List listCursosNoFacturados = getSqlMapClientTemplate().queryForList(
				"edu.ProcesoEDUComercialSQL.getPedidosCursosNoFacturadosMixtoYCosto",
				params);
		return listCursosNoFacturados;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDUComercialDAO#getPedidosCursosNoFacturadosMixto(java.lang.String, java.util.Map)
	 */
	public List getPedidosCursosNoFacturadosMixto(String codigoPais, Map params) {
		List listCursosNoFacturados = getSqlMapClientTemplate().queryForList(
				"edu.ProcesoEDUComercialSQL.getPedidosCursosNoFacturadosMixto",
				params);
		return listCursosNoFacturados;
	}	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDUComercialDAO#deleteTemporalPedidosCUV(java.util.Map)
	 */
	public void deleteTemporalPedidosCUV(Map params) throws Exception {
		getSqlMapClientTemplate().delete("edu.ProcesoEDUComercialSQL.deleteTemporalPedidosCUV",
				params);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDUComercialDAO#insertTemporalPedidosNombreCompletoCUV(java.util.List)
	 */
	public void insertTemporalPedidosNombreCompletoCUV(final List list) throws Exception {
		final Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				Iterator listIterator = list.iterator();
				while (listIterator.hasNext()) {
					Map dataInsert = (Map) listIterator.next();
					logger.debug("dataInsert: " + dataInsert);
					getSqlMapClientTemplate().insert("edu.ProcesoEDUComercialSQL.insertPedidosConsultoraNombreCompletoCUV", dataInsert);
				}
				int rowsaffected = executor.executeBatch();
				logger.debug("Inicio->" + timestamp.toString() + "Fin-->" + new Timestamp(System.currentTimeMillis()));
				logger.debug("rows afftected by insertTemporalPedidos: " + rowsaffected);
				return null;
			}
		});
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDUComercialDAO#insertTemporalPedidosCUV(java.util.List)
	 */
	public void insertTemporalPedidosCUV(final List list) throws Exception {
		final Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				Iterator listIterator = list.iterator();
				while (listIterator.hasNext()) {
					Map dataInsert = (Map) listIterator.next();
					logger.debug("dataInsert: " + dataInsert);
					getSqlMapClientTemplate().insert("edu.ProcesoEDUComercialSQL.insertPedidosConsultoraCUV", dataInsert);
				}
				int rowsaffected = executor.executeBatch();
				logger.debug("Inicio->" + timestamp.toString() + "Fin-->" + new Timestamp(System.currentTimeMillis()));
				logger.debug("rows afftected by insertTemporalPedidos: " + rowsaffected);
				return null;
			}
		});
		
	}	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDUComercialDAO#validaExecuteProcesoCalificacion(java.lang.String, java.util.Map)
	 */
	public Integer validaExecuteProcesoCalificacion(String codigoPais, Map params) throws Exception {
		Integer numConsultoras = (Integer) getSqlMapClientTemplate().queryForObject(
												"edu.ProcesoEDUComercialSQL.validaExecuteProcesoCalificacion",params);
		log.debug("validaExecuteProcesoCalificacion " + numConsultoras);
		return numConsultoras;
	}	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDUComercialDAO#deleteRegionesACerrar(java.lang.String, java.util.Map)
	 */
	public void deleteRegionesACerrar(String codigoPais, Map params) {
		getSqlMapClientTemplate().delete("edu.ProcesoEDUComercialSQL.deleteRegionesACerrar",
				params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDUComercialDAO#insertarRegionesACerrar(java.util.List)
	 */
	public void insertarRegionesACerrar(final List list) throws Exception {
		final Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				Iterator listIterator = list.iterator();
				while (listIterator.hasNext()) {
					Map dataInsert = (Map) listIterator.next();
					//logger.debug("dataInsert: " + dataInsert);
					getSqlMapClientTemplate().insert("edu.ProcesoEDUComercialSQL.insertarRegionesACerrar", dataInsert);
				}
				int rowsaffected = executor.executeBatch();
				logger.debug("Inicio->" + timestamp.toString() + "Fin-->" + new Timestamp(System.currentTimeMillis()));
				logger.debug("rows afftected by insertarRegionesACerrar: " + rowsaffected);
				return null;
			}
		});
		
	}	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDUComercialDAO#getValidaCampanha(java.util.Map)
	 */
	public Integer getValidaCampanha(Map params) throws Exception {
		Integer numCampanas = (Integer) getSqlMapClientTemplate().queryForObject(
				"edu.ProcesoEDUComercialSQL.getValidaCampanha",params);
		return numCampanas;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDUComercialDAO#updateToControlFacturacion(java.lang.String, java.util.Map)
	 */
	public void updateToControlFacturacion(String codigoPais, Map params) {
		getSqlMapClientTemplate().update("edu.ProcesoEDUComercialSQL.updateToControlFacturacion", params);
		
	}	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDUComercialDAO#getHistoricoBloqueoConsultora(java.util.Map)
	 */
	public List getHistoricoBloqueoConsultora(Map params) {
		List lista = getSqlMapClientTemplate().queryForList(
				"edu.ProcesoEDUComercialSQL.getHistoricoBloqueoConsultora",
				params);
		return lista;
	}	
}
