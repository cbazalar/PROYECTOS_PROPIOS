package biz.belcorp.ssicc.dao.edu.gen.ibatis;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientCallback;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.edu.gen.GenericoEDUComercialDAO;
import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.framework.util.CustomerContextHolder;
import biz.belcorp.ssicc.dao.framework.util.TypesafeEnum;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.scsicc.bean.LibreriasAS400Bean;

import com.ibatis.sqlmap.client.SqlMapExecutor;


public class GenericoEDUComercialAS400DAOiBatis extends BaseDAOiBatis
		implements GenericoEDUComercialDAO {

	List listComercial = new ArrayList();
	
	public LibreriasAS400Bean libreriasBean;
	
	public Map params;
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#getPedidosComercial(java.lang.String, java.util.Map)
	 */
	public List getPedidosComercial(String dataSource, Map params)
			throws Exception {
		try {

			CustomerContextHolder.setCustomerType(TypesafeEnum
					.getDataSource(dataSource));
			params=populateBeans(params);
			String indicadorNombreCompleto = (String) params.get("indicadorNombreCompleto");
			if (Constants.EDU_INDICADOR_NOMBRE_COMPLETO.equals(indicadorNombreCompleto))
				listComercial = getSqlMapClientTemplate().queryForList(
						"edu.GenericoEDUComercialAS400SQL.getPedidosConsultoraNombreCompleto",
						params);
			else	
				listComercial = getSqlMapClientTemplate().queryForList(
						"edu.GenericoEDUComercialAS400SQL.getPedidosConsultora",
						params);
		} catch (Exception e) {
			String mensajeError = this.getKeyMessage("genericoEDUComercial.error.pedidoConsultora", (Usuario)params.get("usuario"));
			throw new Exception(mensajeError + "" + e.getMessage());
		} finally {
			CustomerContextHolder.clearCustomerType();
		}
		return listComercial;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#getPedidosComercialFacturados(java.lang.String, java.util.Map)
	 */
	public List getPedidosComercialFacturados(String dataSource, Map params)
			throws Exception {
		try {
			CustomerContextHolder.setCustomerType(TypesafeEnum
					.getDataSource(dataSource));
			params=populateBeans(params);
			listComercial = getSqlMapClientTemplate()
					.queryForList(
							"edu.GenericoEDUComercialAS400SQL.getPedidosConsultoraFacturados",
							params);
		} catch (Exception e) {
			String mensajeError = this.getKeyMessage("genericoEDUComercial.error.pedidoConsultoraFacturadas", (Usuario)params.get("usuario"));
			throw new Exception(mensajeError+""					
							+ e.getMessage());
		} finally {
			CustomerContextHolder.clearCustomerType();
		}
		return listComercial;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#getPedidosCursosFacturados(java.lang.String, java.util.Map)
	 */
	public List getPedidosCursosFacturados(String dataSource, Map params)
		throws Exception {
			try {

				CustomerContextHolder.setCustomerType(TypesafeEnum
						.getDataSource(dataSource));
				params=populateBeans(params);
				listComercial = getSqlMapClientTemplate()
						.queryForList(
								"edu.GenericoEDUComercialAS400SQL.getPedidosCursosFacturados",
								params);
								
			} catch (Exception e) {
				String mensajeError = this.getKeyMessage("genericoEDUComercial.error.pedidoConsultoraCursosFacturados", (Usuario)params.get("usuario"));
				throw new Exception(
						mensajeError+""
								+ e.getMessage());
			} finally {
				CustomerContextHolder.clearCustomerType();
			}
			return listComercial;
			//return null;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#getPedidosCursosNoFacturados(java.lang.String, java.util.Map)
	 */
	public List getPedidosCursosNoFacturados(String dataSource, Map params)
			throws Exception {
		try {
			CustomerContextHolder.setCustomerType(TypesafeEnum
					.getDataSource(dataSource));
			params=populateBeans(params);			
			
			listComercial = getSqlMapClientTemplate()
					.queryForList(
							"edu.GenericoEDUComercialAS400SQL.getPedidosCursosNoFacturados",
							params);
		}
		catch (Exception e) {
			String mensajeError = this.getKeyMessage("genericoEDUComercial.error.pedidoConsultoraCursosNoFacturados", (Usuario)params.get("usuario"));
			throw new Exception(
					mensajeError+""
							+ e.getMessage());
		} finally {
			CustomerContextHolder.clearCustomerType();
		}
		return listComercial;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#getConsultorasNuevas(java.lang.String, java.util.Map)
	 */
	public List getConsultorasNuevas(String dataSource, Map params)
			throws Exception {
		try {
			CustomerContextHolder.setCustomerType(TypesafeEnum
					.getDataSource(dataSource));
			params=populateBeans(params);
			listComercial = getSqlMapClientTemplate().queryForList(
					"edu.GenericoEDUComercialAS400SQL.getConsultorasNuevas",
					params);
		} catch (Exception e) {
			String mensajeError = this.getKeyMessage("genericoEDUComercial.error.pedidoConsultoraNuevas", (Usuario)params.get("usuario"));
			throw new Exception(
					mensajeError+""
							+ e.getMessage());
		} finally {
			CustomerContextHolder.clearCustomerType();
		}
		return listComercial;

	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#getDetalleProducto(java.lang.String, java.util.Map)
	 */
	public List getDetalleProducto(String dataSource, Map params)
			throws Exception {
		List detalleProducto = null;
		try {
			CustomerContextHolder.setCustomerType(TypesafeEnum
					.getDataSource(dataSource));
			params=populateBeans(params);
			detalleProducto = getSqlMapClientTemplate().queryForList(
					"edu.GenericoEDUComercialAS400SQL.getDetalleProducto",
					params);
		} catch (Exception e) {
			String mensajeError = this.getKeyMessage("genericoEDUComercial.error.detalleProducto", (Usuario)params.get("usuario"));
			throw new Exception(
					mensajeError+""
							+ e.getMessage());
		} finally {
			CustomerContextHolder.clearCustomerType();
		}
		return detalleProducto;

	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#getMatrizProducto(java.lang.String, java.util.Map)
	 */
	public List getMatrizProducto(String dataSource, Map params)
			throws Exception {
		List matrizProducto = null;
		try {
			CustomerContextHolder.setCustomerType(TypesafeEnum
					.getDataSource(dataSource));
			params=populateBeans(params);
			matrizProducto = getSqlMapClientTemplate().queryForList(
					"edu.GenericoEDUComercialAS400SQL.getMatrizProducto",
					params);
		} catch (Exception e) {
			String mensajeError = this.getKeyMessage("genericoEDUComercial.error.MatrizProducto", (Usuario)params.get("usuario"));
			throw new Exception(
					mensajeError+""
							+ e.getMessage());
		} finally {
			CustomerContextHolder.clearCustomerType();
		}
		return matrizProducto;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#getRegionesComercial(java.lang.String, java.util.Map)
	 */
	public List getRegionesComercial(String dataSource, Map params)
			throws Exception {
		try {

			CustomerContextHolder.setCustomerType(TypesafeEnum
					.getDataSource(dataSource));
			params=populateBeans(params);
			listComercial = getSqlMapClientTemplate().queryForList(
					"edu.GenericoEDUComercialAS400SQL.getRegionesComercial",
					params);
		} catch (Exception e) {
			String mensajeError = this.getKeyMessage("genericoEDUComercial.error.RegionesComercial", (Usuario)params.get("usuario"));
			throw new Exception(
					mensajeError+""
							+ e.getMessage());
		} finally {
			CustomerContextHolder.clearCustomerType();
		}

		return listComercial;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#getZonasComercial(java.lang.String, java.util.Map)
	 */
	public List getZonasComercial(String dataSource, Map params)
			throws Exception {
		try {
			CustomerContextHolder.setCustomerType(TypesafeEnum
					.getDataSource(dataSource));
			params=populateBeans(params);
			listComercial = getSqlMapClientTemplate().queryForList(
					"edu.GenericoEDUComercialAS400SQL.getZonasComercial",
					params);
		} catch (Exception e) {
			String mensajeError = this.getKeyMessage("genericoEDUComercial.error.ZonasComercial", (Usuario)params.get("usuario"));
			throw new Exception(
					mensajeError+""
							+ e.getMessage());
		} finally {
			CustomerContextHolder.clearCustomerType();
		}
		return listComercial;
	}

	public List getControlFacturacionComercial(String dataSource, Map params)
			throws Exception {
		try {
			CustomerContextHolder.setCustomerType(TypesafeEnum
					.getDataSource(dataSource));
			params=populateBeans(params);
			listComercial = getSqlMapClientTemplate()
					.queryForList(
							"edu.GenericoEDUComercialAS400SQL.getControlFacturacionComercial",
							params);
		} catch (Exception e) {
			String mensajeError = this.getKeyMessage("genericoEDUComercial.error.ControlFacturacion", (Usuario)params.get("usuario"));
			throw new Exception(
					mensajeError+""
							+ e.getMessage());
		} finally {
			CustomerContextHolder.clearCustomerType();
		}

		return listComercial;
	}

	/**
	 * Inserta Consultoras Aptas al Comercial
	 * 
	 * @param
	 */
	public void insertConsultorasAptas(String dataSource, final List list,
			final String numeroLote,Map params) throws Exception {

		try {
			CustomerContextHolder.setCustomerType(TypesafeEnum
					.getDataSource(dataSource));
			final Map param=populateBeans(params);
			final Timestamp timestamp = new Timestamp(System
					.currentTimeMillis());
			getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
				public Object doInSqlMapClient(SqlMapExecutor executor)
						throws SQLException {
					executor.startBatch();
					Iterator listIterator = list.iterator();
					while (listIterator.hasNext()) {
						Map dataInsert = (Map) listIterator.next();
						dataInsert=populateMaps(dataInsert,param);							
						logger.debug("dataInsewrt " + dataInsert);
						
						dataInsert.put("numeroLote", numeroLote);
						dataInsert.put("tipoDespacho",
								Constants.EDU_TIPO_DESPACHO_INVITACION);
						getSqlMapClientTemplate()
								.insert(
										"edu.GenericoEDUComercialAS400SQL.insertarAptas",
										dataInsert);
					}
					int rowsaffected = executor.executeBatch();
					logger.debug("Inicio->" + timestamp.toString()
							+ "Fin-->"
							+ new Timestamp(System.currentTimeMillis()));
					System.out
							.println("rows afftected by insertConsultorasAptas: "
									+ rowsaffected);
					return null;
				}
			});
		} catch (Exception e) {
			String mensajeError = this.getKeyMessage("genericoEDUComercial.error.InsertConsultoras", (Usuario)params.get("usuario"));
			throw new Exception(
					mensajeError+""
							+ e.getMessage());

		} finally {
			CustomerContextHolder.clearCustomerType();
		}
	}

	/**
	 * Inserta Consultoras Aptas Curso con Costo al Comercial
	 * 
	 * @param
	 */
	public void insertConsultorasAptasCosto(String dataSource, final List list,
			final String numeroLote,Map params) throws Exception {

		try {
			CustomerContextHolder.setCustomerType(TypesafeEnum
					.getDataSource(dataSource));
			
			final Timestamp timestamp = new Timestamp(System
					.currentTimeMillis());
			final Map param=populateBeans(params);
			
			getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
				public Object doInSqlMapClient(SqlMapExecutor executor)
						throws SQLException {
					executor.startBatch();
					Iterator listIterator = list.iterator();
					while (listIterator.hasNext()) {
						Map dataInsert = (Map) listIterator.next();
						dataInsert=populateMaps(dataInsert,param);							
						logger.debug("dataInsewrt " + dataInsert);
						
						dataInsert.put("numeroLote", numeroLote);
						dataInsert.put("tipoDespacho",
								Constants.EDU_TIPO_DESPACHO_INVITACION);
						getSqlMapClientTemplate()
								.insert(
										"edu.GenericoEDUComercialAS400SQL.insertarAptasCosto",
										dataInsert);
					}
					int rowsaffected = executor.executeBatch();
					logger.debug("Inicio->" + timestamp.toString()
							+ "Fin-->"
							+ new Timestamp(System.currentTimeMillis()));
					System.out
							.println("rows afftected by insertConsultorasAptasCosto: "
									+ rowsaffected);
					return null;
				}
			});
		} catch (Exception e) {
			String mensajeError = this.getKeyMessage("genericoEDUComercial.error.InsertConsultorasAptasCosto", (Usuario)params.get("usuario"));
			throw new Exception(
					mensajeError+""
							+ e.getMessage());

		} finally {
			CustomerContextHolder.clearCustomerType();
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#insertConsultorasAptasporProgramar(java.lang.String, java.util.List, java.lang.String, java.lang.String)
	 */
	public void insertConsultorasAptasporProgramar(String dataSource,
			final List list, final String numeroLote, final String periodo,Map params)
			throws Exception {

		try {
			CustomerContextHolder.setCustomerType(TypesafeEnum
					.getDataSource(dataSource));
			
			final Timestamp timestamp = new Timestamp(System
					.currentTimeMillis());
			final Map param=populateBeans(params);
			
			getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
				public Object doInSqlMapClient(SqlMapExecutor executor)
						throws SQLException {
					executor.startBatch();
					Iterator listIterator = list.iterator();
					while (listIterator.hasNext()) {
						Map dataInsert = (Map) listIterator.next();
						dataInsert=populateMaps(dataInsert,param);
						logger.debug("dataInsewrt " + dataInsert);
						dataInsert.put("numeroLote", numeroLote);
						dataInsert.put("codigoPeriodo", periodo);
						getSqlMapClientTemplate()
								.insert(
										"edu.GenericoEDUComercialAS400SQL.insertarConsultorasAptasporProgramar",
										dataInsert);
					}
					int rowsaffected = executor.executeBatch();
					logger.debug("Inicio->" + timestamp.toString()
							+ "Fin-->"
							+ new Timestamp(System.currentTimeMillis()));
					System.out
							.println("rows afftected by insertUserRolesById: "
									+ rowsaffected);
					return null;
				}
			});
		} catch (Exception e) {
			String mensajeError = this.getKeyMessage("genericoEDUComercial.error.InsertConsultorasAptasProgramar", (Usuario)params.get("usuario"));
			throw new Exception(
					mensajeError+""
							+ e.getMessage());
		} finally {
			CustomerContextHolder.clearCustomerType();
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#getConsultorasAptasporProgramar(java.lang.String, java.util.Map)
	 */
	public List getConsultorasAptasporProgramar(String dataSource, Map params)
			throws Exception {
		try {
			CustomerContextHolder.setCustomerType(TypesafeEnum
					.getDataSource(dataSource));
			params=populateBeans(params);
			listComercial = getSqlMapClientTemplate()
					.queryForList(
							"edu.GenericoEDUComercialAS400SQL.getConsultorasMaestroAptasporProgramar",
							params);
		} catch (Exception e) {
			String mensajeError = this.getKeyMessage("genericoEDUComercial.error.ConsultorasAptas", (Usuario)params.get("usuario"));
			throw new Exception(
					mensajeError+""
							+ e.getMessage());
		} finally {
			CustomerContextHolder.clearCustomerType();
		}

		return listComercial;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#insertParametrosCursoCapacitacion(java.lang.String, java.util.Map)
	 */
	public void insertParametrosCursoCapacitacion(String dataSource,
			 Map params) throws Exception{

		try {
			CustomerContextHolder.setCustomerType(TypesafeEnum
					.getDataSource(dataSource));
			params=populateBeans(params);
			getSqlMapClientTemplate()
					.insert(
							"edu.GenericoEDUComercialAS400SQL.insertParametrosCursoCapacitacion",
							params);
		} catch (Exception e) {
			logger
					.debug("Error ProcesoEDUComercialDAOiBatis--insertParametrosCursoCapacitacion : "
							+ e.getMessage());
		} finally {
			CustomerContextHolder.clearCustomerType();
		}

	}

	/**
	 * Actualiza el Parametros Curso Capacitacion.
	 * 
	 * @param
	 */
	public void updateParametrosCursoCapacitacion(String dataSource, Map params) throws Exception{
		try {
			CustomerContextHolder.setCustomerType(TypesafeEnum
					.getDataSource(dataSource));
			params=populateBeans(params);
			getSqlMapClientTemplate()
					.update(
							"edu.GenericoEDUComercialAS400SQL.updateParametrosCursoCapacitacion",
							params);

		} catch (Exception e) {
		
			logger
					.debug("Error GenericoEDUComercialFOXSQL--updateParametrosCursoCapacitacion : "
							+ e.getMessage());
			
			String mensajeError = this.getKeyMessage("genericoEDUComercial.error.updateParametros", (Usuario)params.get("usuario"));
			throw new Exception(
					mensajeError+""
							+ e.getMessage());
							
		} finally {
			CustomerContextHolder.clearCustomerType();
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#deleteConsultorasAptas(java.lang.String, java.util.Map)
	 */
	public void deleteConsultorasAptas(String dataSource, Map params) throws Exception {
		try {	
			CustomerContextHolder.setCustomerType(TypesafeEnum
					.getDataSource(dataSource));
			params=populateBeans(params);
			getSqlMapClientTemplate().update(
					"edu.GenericoEDUComercialAS400SQL.deleteAptasLote", params);
		} catch (Exception e) {
			String mensajeError = this.getKeyMessage("genericoEDUComercial.error.deleteConsultora", (Usuario)params.get("usuario"));
			throw new Exception(
					mensajeError+""
							+ e.getMessage());
			
		} finally {
			CustomerContextHolder.clearCustomerType();
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#deleteConsultorasAptasCosto(java.lang.String, java.util.Map)
	 */
	public void deleteConsultorasAptasCosto(String dataSource, Map params) {
		try {		
			CustomerContextHolder.setCustomerType(TypesafeEnum
					.getDataSource(dataSource));
			params=populateBeans(params);
			getSqlMapClientTemplate().update(
					"edu.GenericoEDUComercialAS400SQL.deleteAptasCosto", params);
		} catch (Exception e) {
			logger
					.debug("Error ProcesoEDUComercialDAOiBatis--insertParametrosCursoCapacitacion : "
							+ e.getMessage());
			
			
		} finally {
			CustomerContextHolder.clearCustomerType();
		}
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#deleteConsultorasAptasProgramar(java.lang.String, java.util.Map)
	 */
	public void deleteConsultorasAptasProgramar(String dataSource, Map params) {
		CustomerContextHolder.setCustomerType(TypesafeEnum
				.getDataSource(dataSource));
		params=populateBeans(params);
			getSqlMapClientTemplate()
				.update(
						"edu.GenericoEDUComercialAS400SQL.deleteAptasLoteporProgramar",
						params);
		CustomerContextHolder.clearCustomerType();

	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#insertAptasHistoricas(java.lang.String, java.util.List)
	 */
	public void insertAptasHistoricas(String dataSource,final List list,Map params) throws Exception
	{
		try {
			CustomerContextHolder.setCustomerType(TypesafeEnum
					.getDataSource(dataSource));
			
			final Timestamp timestamp = new Timestamp(System
					.currentTimeMillis());
			final Map param=populateBeans(params);
			
			getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
				public Object doInSqlMapClient(SqlMapExecutor executor)
						throws SQLException {
					executor.startBatch();
					Iterator listIterator = list.iterator();
					int i=0;
					while (listIterator.hasNext()) {
						i=i+1;
						Map dataInsert = (Map) listIterator.next();
						dataInsert=populateMaps(dataInsert,param);
						logger.debug("dataInsewrt " + dataInsert);
						getSqlMapClientTemplate()
								.insert(
										"edu.GenericoEDUComercialAS400SQL.insertarResultCierreFacturacion",
										dataInsert);
					}
					int rowsaffected = executor.executeBatch();
					logger.debug("Inicio->" + timestamp.toString()
							+ "Fin-->"
							+ new Timestamp(System.currentTimeMillis()));
					System.out
							.println("rows afftected by insertUserRolesById: "
									+ rowsaffected);
					return null;
				}

			
			});
		} catch (Exception e) {
			String mensajeError = this.getKeyMessage("genericoEDUComercial.error.insertAptasHistorico", (Usuario)params.get("usuario"));
			throw new Exception(
					mensajeError+""
							+ e.getMessage());
		} finally {
			CustomerContextHolder.clearCustomerType();
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#deleteAptasHistoricas(java.lang.String, java.util.Map)
	 */
	public void deleteAptasHistoricas(String dataSource, Map params) throws Exception{
		try {
		CustomerContextHolder.setCustomerType(TypesafeEnum
				.getDataSource(dataSource));
			params=populateBeans(params);
			getSqlMapClientTemplate().update(
				"edu.GenericoEDUComercialAS400SQL.deleteAptasCierreFacturacion", params);
		} catch (Exception e) {
			String mensajeError = this.getKeyMessage("genericoEDUComercial.error.deleteAptasHistorico", (Usuario)params.get("usuario"));
			throw new Exception(
					mensajeError+""
							+ e.getMessage());
		} finally {
			CustomerContextHolder.clearCustomerType();
		}
	}

	public void deleteBeneficiosCapacitadas(String dataSource, Map params) throws Exception{
		try {
		CustomerContextHolder.setCustomerType(TypesafeEnum
				.getDataSource(dataSource));
			params=populateBeans(params);
			getSqlMapClientTemplate().update(
				"edu.GenericoEDUComercialAS400SQL.deleteBeneficiosCapacitadas", params);
		} catch (Exception e) {
			String mensajeError = this.getKeyMessage("genericoEDUComercial.error.deleteComercial", (Usuario)params.get("usuario"));
			throw new Exception(
					mensajeError+""
							+ e.getMessage());
		} finally {
			CustomerContextHolder.clearCustomerType();
		}
	}
	
	public List getBeneficiosCapacitadas(String dataSource, Map params)throws Exception	{
		try {
			CustomerContextHolder.setCustomerType(TypesafeEnum
					.getDataSource(dataSource));
			params=populateBeans(params);
			listComercial = getSqlMapClientTemplate()
					.queryForList(
							"edu.GenericoEDUComercialAS400SQL.getBeneficiosCapacitadas",
							params);
		} catch (Exception e) {
			String mensajeError = this.getKeyMessage("genericoEDUComercial.error.beneficiosCapacitadas", (Usuario)params.get("usuario"));
			throw new Exception(
					mensajeError+""
							+ e.getMessage());
		} finally {
			CustomerContextHolder.clearCustomerType();
		}

		return listComercial;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#insertBeneficiosCapacitadas(java.lang.String, java.util.List)
	 */
	public void insertBeneficiosCapacitadas(String dataSource,final List list,Map params) throws Exception
	{
		try {
			CustomerContextHolder.setCustomerType(TypesafeEnum
					.getDataSource(dataSource));
			
			final Timestamp timestamp = new Timestamp(System
					.currentTimeMillis());
			final Map param=populateBeans(params);
			
			getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
				public Object doInSqlMapClient(SqlMapExecutor executor)
						throws SQLException {
					executor.startBatch();
					Iterator listIterator = list.iterator();
					int i=0;
					while (listIterator.hasNext()) {
						i=i+1;
						Map dataInsert = (Map) listIterator.next();
						dataInsert=populateMaps(dataInsert,param);
						logger.debug("dataInsewrt " + dataInsert);
						getSqlMapClientTemplate()
								.insert(
										"edu.GenericoEDUComercialAS400SQL.insertarBeneficiosCapacitadas",
										dataInsert);
					}
					int rowsaffected = executor.executeBatch();
					logger.debug("Inicio->" + timestamp.toString()
							+ "Fin-->"
							+ new Timestamp(System.currentTimeMillis()));
					System.out
							.println("rows afftected by insertUserRolesById: "
									+ rowsaffected);
					return null;
				}

			});
		} catch (Exception e) {
			String mensajeError = this.getKeyMessage("genericoEDUComercial.error.insertComercial", (Usuario)params.get("usuario"));
			throw new Exception(
					mensajeError+""
							+ e.getMessage());
		} finally {
			CustomerContextHolder.clearCustomerType();
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#insertBeneficiosCapacitadas(java.lang.String, java.util.Map)
	 */
	public void insertBeneficiosCapacitadas(String dataSource, Map params) {
		try {
			CustomerContextHolder.setCustomerType(TypesafeEnum
					.getDataSource(dataSource));
			params=populateBeans(params);
			getSqlMapClientTemplate()
					.update(
							"edu.GenericoEDUComercialAS400SQL.insertarBeneficiosCapacitadas",
							params);

		} catch (Exception e) {
			logger
					.debug("Error ProcesoEDUComercialDAOiBatis--insertarBeneficiosCapacitadas : "
							+ e.getMessage());
		} finally {
			CustomerContextHolder.clearCustomerType();
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#updateBeneficiosCapacitadas(java.lang.String, java.util.Map)
	 */
	public void updateBeneficiosCapacitadas(String dataSource, Map params) {
		try {
			CustomerContextHolder.setCustomerType(TypesafeEnum
					.getDataSource(dataSource));
			params=populateBeans(params);
			getSqlMapClientTemplate()
					.update(
							"edu.GenericoEDUComercialAS400SQL.updateBeneficiosCapacitadas",
							params);

		} catch (Exception e) {
			logger
					.debug("Error ProcesoEDUComercialDAOiBatis--updateBeneficiosCapacitadas : "
							+ e.getMessage());
		} finally {
			CustomerContextHolder.clearCustomerType();
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#getMantenimientoClasificacion(java.lang.String, java.util.Map)
	 */
	public List getMantenimientoClasificacion(String dataSource, Map params)throws Exception	{
		try {
			CustomerContextHolder.setCustomerType(TypesafeEnum
					.getDataSource(dataSource));
			params=populateBeans(params);
			listComercial = getSqlMapClientTemplate()
					.queryForList(
							"edu.GenericoEDUComercialAS400SQL.getMantenimientoClasificacion",
							params);
		} catch (Exception e) {
			String mensajeError = this.getKeyMessage("genericoEDUComercial.error.mantenimientoClasificacion", (Usuario)params.get("usuario"));
			throw new Exception(
					mensajeError+""
							+ e.getMessage());
		} finally {
			CustomerContextHolder.clearCustomerType();
		}

		return listComercial;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#getMantenimientoClasificacionInvi(java.lang.String, java.util.Map)
	 */
	public List getMantenimientoClasificacionInvi(String dataSource, Map params)throws Exception	{
		try {
			CustomerContextHolder.setCustomerType(TypesafeEnum
					.getDataSource(dataSource));
			params=populateBeans(params);
			listComercial = getSqlMapClientTemplate()
					.queryForList(
							"edu.GenericoEDUComercialAS400SQL.getMantenimientoClasificacion",
							params);
		} catch (Exception e) {
			String mensajeError = this.getKeyMessage("genericoEDUComercial.error.mantenimientoClasificacion", (Usuario)params.get("usuario"));
			throw new Exception(
					mensajeError+""
							+ e.getMessage());
		} finally {
			CustomerContextHolder.clearCustomerType();
		}

		return listComercial;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#insertMantenimientoClasificacion(java.lang.String, java.util.List)
	 */
	public void insertMantenimientoClasificacion(String dataSource,final List list,Map params) throws Exception
	{
		try {
			CustomerContextHolder.setCustomerType(TypesafeEnum
					.getDataSource(dataSource));
			
			final Timestamp timestamp = new Timestamp(System
					.currentTimeMillis());
			final Map param=populateBeans(params);
			
			getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
				public Object doInSqlMapClient(SqlMapExecutor executor)
						throws SQLException {
					executor.startBatch();
					Iterator listIterator = list.iterator();
					int i=0;
					while (listIterator.hasNext()) {
						i=i+1;
						Map dataInsert = (Map) listIterator.next();
						dataInsert=populateMaps(dataInsert,param);
						logger.debug("dataInsewrt " + dataInsert);
						getSqlMapClientTemplate()
								.insert(
										"edu.GenericoEDUComercialAS400SQL.insertarMantenimientoClasificacion",
										dataInsert);
					}
					int rowsaffected = executor.executeBatch();
					logger.debug("Inicio->" + timestamp.toString()
							+ "Fin-->"
							+ new Timestamp(System.currentTimeMillis()));
					System.out
							.println("rows afftected by insertUserRolesById: "
									+ rowsaffected);
					return null;
				}

			});
		} catch (Exception e) {
			String mensajeError = this.getKeyMessage("genericoEDUComercial.error.insertComercial", (Usuario)params.get("usuario"));
			throw new Exception(
					mensajeError+""
							+ e.getMessage());
		} finally {
			CustomerContextHolder.clearCustomerType();
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#updateMantenimientoClasificacion(java.lang.String, java.util.Map)
	 */
	public void updateMantenimientoClasificacion(String dataSource, Map params) throws Exception {
		try {
			CustomerContextHolder.setCustomerType(TypesafeEnum
					.getDataSource(dataSource));
			params=populateBeans(params);
			getSqlMapClientTemplate()
					.update(
							"edu.GenericoEDUComercialAS400SQL.updateMantenimientoClasificacion",
							params);

		} catch (Exception e) {
			logger
					.debug("Error ProcesoEDUComercialDAOiBatis--updateBeneficiosCapacitadas : "
							+ e.getMessage());
			
			String mensajeError = this.getKeyMessage("genericoEDUComercial.error.updateComercial", (Usuario)params.get("usuario"));
			throw new Exception(
					mensajeError+""
							+ e.getMessage());
			
		} finally {
			CustomerContextHolder.clearCustomerType();
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#updateMantenimientoClasificacionInvi(java.lang.String, java.util.Map)
	 */
	public void updateMantenimientoClasificacionInvi(String dataSource, Map params) throws Exception {
		try {
			CustomerContextHolder.setCustomerType(TypesafeEnum
					.getDataSource(dataSource));
			params=populateBeans(params);
			getSqlMapClientTemplate()
					.update(
							"edu.GenericoEDUComercialAS400SQL.updateMantenimientoClasificacion",
							params);

		} catch (Exception e) {
			logger
					.debug("Error ProcesoEDUComercialDAOiBatis--updateBeneficiosCapacitadas : "
							+ e.getMessage());
			String mensajeError = this.getKeyMessage("genericoEDUComercial.error.updateComercial", (Usuario)params.get("usuario"));
			throw new Exception(
					mensajeError+""
							+ e.getMessage());
		} finally {
			CustomerContextHolder.clearCustomerType();
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#deleteMantenimientoClasificacion(java.lang.String, java.util.Map)
	 */
	public void deleteMantenimientoClasificacion(String dataSource, Map params) throws Exception {
		try {
			CustomerContextHolder.setCustomerType(TypesafeEnum
					.getDataSource(dataSource));
			params=populateBeans(params);
			getSqlMapClientTemplate()
					.update(
							"edu.GenericoEDUComercialAS400SQL.deleteMantenimientoClasificacion",
							params);

		} catch (Exception e) {
			logger
					.debug("Error ProcesoEDUComercialDAOiBatis--deleteMantenimientoClasificacion : "
							+ e.getMessage());
			String mensajeError = this.getKeyMessage("genericoEDUComercial.error.deleteComercial", (Usuario)params.get("usuario"));
			throw new Exception(
					mensajeError+""
							+ e.getMessage());
		} finally {
			CustomerContextHolder.clearCustomerType();
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#deleteMantenimientoClasificacionInvi(java.lang.String, java.util.Map)
	 */
	public void deleteMantenimientoClasificacionInvi(String dataSource, Map params) throws Exception {
		try {
			CustomerContextHolder.setCustomerType(TypesafeEnum
					.getDataSource(dataSource));
			params=populateBeans(params);
			getSqlMapClientTemplate()
					.update(
							"edu.GenericoEDUComercialAS400SQL.deleteMantenimientoClasificacion",
							params);

		} catch (Exception e) {
			logger
					.debug("Error ProcesoEDUComercialDAOiBatis--deleteMantenimientoClasificacion : "
							+ e.getMessage());
			String mensajeError = this.getKeyMessage("genericoEDUComercial.error.deleteComercial", (Usuario)params.get("usuario"));
			throw new Exception(
					mensajeError+""
							+ e.getMessage());
		} finally {
			CustomerContextHolder.clearCustomerType();
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#insertMantenimientoClasificacionInvi(java.lang.String, java.util.Map)
	 */
	public void insertMantenimientoClasificacionInvi(String dataSource, Map params) throws Exception {
		try {
			CustomerContextHolder.setCustomerType(TypesafeEnum
					.getDataSource(dataSource));
			params=populateBeans(params);
			getSqlMapClientTemplate()
					.update(
							"edu.GenericoEDUComercialAS400SQL.insertMantenimientoClasificacion",
							params);

		} catch (Exception e) {
			logger
					.debug("Error ProcesoEDUComercialDAOiBatis--insertMantenimientoClasificacion : "
							+ e.getMessage());
			String mensajeError = this.getKeyMessage("genericoEDUComercial.error.insertComercial", (Usuario)params.get("usuario"));
			throw new Exception(
					mensajeError+""
							+ e.getMessage());
		} finally {
			CustomerContextHolder.clearCustomerType();
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#insertMantenimientoClasificacion(java.lang.String, java.util.Map)
	 */
	public void insertMantenimientoClasificacion(String dataSource, Map params) throws Exception {
		try {
			CustomerContextHolder.setCustomerType(TypesafeEnum
					.getDataSource(dataSource));
			params=populateBeans(params);
			getSqlMapClientTemplate()
					.update(
							"edu.GenericoEDUComercialAS400SQL.insertMantenimientoClasificacion",
							params);

		} catch (Exception e) {
			logger
					.debug("Error ProcesoEDUComercialDAOiBatis--insertMantenimientoClasificacion : "
							+ e.getMessage());
			String mensajeError = this.getKeyMessage("genericoEDUComercial.error.insertComercial", (Usuario)params.get("usuario"));
			throw new Exception(
					mensajeError+""
							+ e.getMessage());
		} finally {
			CustomerContextHolder.clearCustomerType();
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#insertMantenimientoCodVenta(java.lang.String, java.util.Map)
	 */
	public void insertMantenimientoCodVenta(String dataSource, Map params) {
		try {
			CustomerContextHolder.setCustomerType(TypesafeEnum
					.getDataSource(dataSource));
			params=populateBeans(params);
			getSqlMapClientTemplate()
					.update(
							"edu.GenericoEDUComercialAS400SQL.insertMantenimientoCodVenta",
							params);

		} catch (Exception e) {
			logger
					.debug("Error ProcesoEDUComercialDAOiBatis--insertMantenimientoCodVenta : "
							+ e.getMessage());
		} finally {
			CustomerContextHolder.clearCustomerType();
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#executeBorrarEquivalenciaMensaje(java.lang.String, java.util.Map)
	 */
	public void executeBorrarEquivalenciaMensaje(String fuenteDatos, Map params) throws Exception {
		
	}
		
	@Autowired
	public void setLibreriasBean(LibreriasAS400Bean libreriasBean) {
		this.libreriasBean = libreriasBean;
	}
	
	private Map populateBeans(Map params) 
	{
		params.put("libCartera",params.get("AS401") );
		params.put("libComercial",params.get("AS402"));
		params.put("libAtencion",params.get("AS403"));
		params.put("libUbigeo",params.get("AS404"));
		return params;
	}
	
//	private Map populateMaps(Map dataInsert) {
//		dataInsert.putAll(this.params);
//		dataInsert = this.populateBeans(dataInsert);
//		return dataInsert;
//	}

	private Map populateMaps(Map dataInsert, Map params) {
		//dataInsert.putAll(params);
		dataInsert.put("libCartera",params.get("AS401") );
		dataInsert.put("libComercial",params.get("AS402"));
		dataInsert.put("libAtencion",params.get("AS403"));
		dataInsert.put("libUbigeo",params.get("AS404"));
		return dataInsert;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#getExisteMensajeEducacion(java.lang.String, java.util.Map)
	 */
	public Integer getExisteMensajeEducacion(String dataSource, Map params) throws Exception {
		Integer contador = new Integer(0);
		try {
			CustomerContextHolder.setCustomerType(TypesafeEnum
					.getDataSource(dataSource));
			params=populateBeans(params);
			 
			contador = (Integer) getSqlMapClientTemplate().queryForObject(
			 			"edu.GenericoEDUComercialAS400SQL.getExisteMensajeEducacion",
			 			params);
			if (contador == null) contador = new Integer(0);
		}
		catch (Exception e) {
			String mensajeError = this.getKeyMessage("genericoEDUComercial.error.verificarRegistros", (Usuario)params.get("usuario"));
			throw new Exception(
					mensajeError+""
							+ e.getMessage());
		}
		finally {
			CustomerContextHolder.clearCustomerType();
		}
		return contador;
    }
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#insertMensajeEducacion(java.lang.String, java.util.Map)
	 */
	public void insertMensajeEducacion(String dataSource, Map params) throws Exception {
		try {
			CustomerContextHolder.setCustomerType(TypesafeEnum
					.getDataSource(dataSource));
			params=populateBeans(params);
			
			this.getSqlMapClientTemplate().insert(
			 			"edu.GenericoEDUComercialAS400SQL.insertMensajeEducacion",
			 			params);
		}
		catch (Exception e) {
			String mensajeError = this.getKeyMessage("genericoEDUComercial.error.insertComercial", (Usuario)params.get("usuario"));
			throw new Exception(
					mensajeError+""
							+ e.getMessage());
		}
		finally {
			CustomerContextHolder.clearCustomerType();
		}
		return;		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#updateMensajeEducacion(java.lang.String, java.util.Map)
	 */
	public void updateMensajeEducacion(String dataSource, Map params) throws Exception {
		try {
			CustomerContextHolder.setCustomerType(TypesafeEnum
					.getDataSource(dataSource));
			params=populateBeans(params);
			this.getSqlMapClientTemplate().update(
				 			"edu.GenericoEDUComercialAS400SQL.updateMensajeEducacion",
				 			params);
		}
		catch (Exception e) {
			String mensajeError = this.getKeyMessage("genericoEDUComercial.error.updateComercial", (Usuario)params.get("usuario"));
			throw new Exception(
					mensajeError+""
							+ e.getMessage());
		}
		finally {
			CustomerContextHolder.clearCustomerType();
		}
	}
	
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#deleteMensajeEducacion(java.lang.String, java.util.Map)
     */
    public void deleteMensajeEducacion(String dataSource, Map params) throws Exception {
    	try {
			CustomerContextHolder.setCustomerType(TypesafeEnum
					.getDataSource(dataSource));
			params=populateBeans(params);		
			this.getSqlMapClientTemplate().delete(
				 			"edu.GenericoEDUComercialAS400SQL.deleteMensajeEducacion",
				 			params);
		}
		catch (Exception e) {
			String mensajeError = this.getKeyMessage("genericoEDUComercial.error.deleteComercial", (Usuario)params.get("usuario"));
			throw new Exception(
					mensajeError+""
							+ e.getMessage());
		}
		finally {
			CustomerContextHolder.clearCustomerType();
		}
	}
    
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#getExisteEmpresaComercializadora(java.lang.String, java.util.Map)
     */
    public Integer getExisteEmpresaComercializadora(String dataSource, Map params) throws Exception {
    	return null;
    }
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#insertEmpresaComercializadora(java.lang.String, java.util.Map)
	 */
	public void insertEmpresaComercializadora(String dataSource, Map params) throws Exception {
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#updateEmpresaComercializadora(java.lang.String, java.util.Map)
	 */
	public void updateEmpresaComercializadora(String dataSource, Map params) throws Exception {
		
	}
	
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#deleteEmpresaComercializadora(java.lang.String, java.util.Map)
     */
    public void deleteEmpresaComercializadora(String dataSource, Map params) throws Exception {
		
	}
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#getExisteEmpresaComercializadora(java.lang.String, java.util.Map)
     */
    public Integer getBloqueoConsultora(String dataSource, Map params) throws Exception {
    	Integer contador = new Integer(0);
    	try {
			CustomerContextHolder.setCustomerType(TypesafeEnum
					.getDataSource(dataSource));
			params=populateBeans(params);
			contador = (Integer)getSqlMapClientTemplate()
					.queryForObject(
							"edu.GenericoEDUComercialAS400SQL.getBloqueoConsultora",
							params);
		} catch (Exception e) {
			String mensajeError = this.getKeyMessage("genericoEDUComercial.error.verificarRegistros", (Usuario)params.get("usuario"));
			throw new Exception(
					mensajeError+""
							+ e.getMessage());
		} finally {
			CustomerContextHolder.clearCustomerType();
		}
		return contador;
    }
    
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#deleteBloqueoConsultora(java.lang.String, java.util.Map)
     */
    public void deleteBloqueoConsultora(String dataSource, Map params) throws Exception {
    	try {
			CustomerContextHolder.setCustomerType(TypesafeEnum
					.getDataSource(dataSource));
			params=populateBeans(params);
			getSqlMapClientTemplate().delete(
							"edu.GenericoEDUComercialAS400SQL.deleteBloqueoConsultora",
							params);
		} catch (Exception e) {
			String mensajeError = this.getKeyMessage("genericoEDUComercial.error.deleteComercial", (Usuario)params.get("usuario"));
			throw new Exception(
					mensajeError+""
							+ e.getMessage());
		} finally {
			CustomerContextHolder.clearCustomerType();
		}
    }
    
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#insertBloqueoConsultora(java.lang.String, java.util.List)
     */
    public void insertBloqueoConsultora(String dataSource,final List list,Map params) throws Exception
	{
		try {
			CustomerContextHolder.setCustomerType(TypesafeEnum
					.getDataSource(dataSource));
			
			final Timestamp timestamp = new Timestamp(System
					.currentTimeMillis());
			final Map param=populateBeans(params);
			
			getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
				public Object doInSqlMapClient(SqlMapExecutor executor)
						throws SQLException {
					executor.startBatch();
					Iterator listIterator = list.iterator();
					int i=0;
					//populateMaps(new HashMap());
					while (listIterator.hasNext()) {
						i=i+1;
						Map dataInsert = (Map) listIterator.next();
						dataInsert=populateMaps(dataInsert,param);	
						logger.debug("dataInsert " + dataInsert);
						
						getSqlMapClientTemplate()
									.insert(
											"edu.GenericoEDUComercialAS400SQL.insertBloqueoConsultora",
											dataInsert);
							
					}
					int rowsaffected = executor.executeBatch();
					logger.debug("Inicio->" + timestamp.toString()
							+ "Fin-->"
							+ new Timestamp(System.currentTimeMillis()));
					System.out
							.println("rows afftected by insertBloqueoConsultora: "
									+ rowsaffected);
					return null;
				}

			});
		} catch (Exception e) {
			String mensajeError = this.getKeyMessage("genericoEDUComercial.error.insertComercial", (Usuario)params.get("usuario"));
			throw new Exception(
					mensajeError+""
							+ e.getMessage());
		} finally {
			CustomerContextHolder.clearCustomerType();
		}
	}

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#getRecodificacionConsultora(java.lang.String, java.util.Map)
     */
    public List getRecodificacionConsultora(String dataSource, Map params) throws Exception {
	try {
	
		CustomerContextHolder.setCustomerType(TypesafeEnum
				.getDataSource(dataSource));
		params=populateBeans(params);
		listComercial = getSqlMapClientTemplate().queryForList(
				"edu.GenericoEDUComercialAS400SQL.getRecodificacionConsultora",
				params);
	} catch (Exception e) {
		String mensajeError = this.getKeyMessage("genericoEDUComercial.error.verificarRegistros", (Usuario)params.get("usuario"));
		throw new Exception(
				mensajeError+""
						+ e.getMessage());
	} finally {
		CustomerContextHolder.clearCustomerType();
	}
	return listComercial;
	}
    
    
    /* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#getExisteCronogramaDictado(java.lang.String, java.util.Map)
	 */
	public Integer getExisteCronogramaDictado(String dataSource, Map params) throws Exception {
		Integer contador = new Integer(0);
		try {
			CustomerContextHolder.setCustomerType(TypesafeEnum
					.getDataSource(dataSource));
			params=populateBeans(params);
			
			contador = (Integer) getSqlMapClientTemplate().queryForObject(
			 			"edu.GenericoEDUComercialAS400SQL.getExisteCronogramaDictado",
			 			params);
			if (contador == null) contador = new Integer(0);
		}
		catch (Exception e) {
			String mensajeError = this.getKeyMessage("genericoEDUComercial.error.verificarRegistros", (Usuario)params.get("usuario"));
			throw new Exception(
					mensajeError+""
							+ e.getMessage());
		}
		finally {
			CustomerContextHolder.clearCustomerType();
		}
		return contador;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#deleteCronogramaDictado(java.lang.String, java.util.Map)
	 */
	public void deleteCronogramaDictado(String dataSource, Map params) throws Exception {
		try {
			CustomerContextHolder.setCustomerType(TypesafeEnum
					.getDataSource(dataSource));
			params=populateBeans(params);
			
			/* Eliminando en Cronograma Dictado Zona */
			this.getSqlMapClientTemplate().delete(
		 			"edu.GenericoEDUComercialAS400SQL.deleteCronogramaDictadoZona",
		 			params);
			
			/* Eliminando  cronograma de Dictado */
			this.getSqlMapClientTemplate().delete(
			 			"edu.GenericoEDUComercialAS400SQL.deleteCronogramaDictado",
			 			params);
			
		}
		catch (Exception e) {
			String mensajeError = this.getKeyMessage("genericoEDUComercial.error.deleteComercial", (Usuario)params.get("usuario"));
			throw new Exception(
					mensajeError+""
							+ e.getMessage());
		}
		finally {
			CustomerContextHolder.clearCustomerType();
		}
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#insertCronogramaDictado(java.lang.String, java.util.Map)
	 */
	public void insertCronogramaDictado(String dataSource, Map params) throws Exception {
		try {
			CustomerContextHolder.setCustomerType(TypesafeEnum
					.getDataSource(dataSource));
			params=populateBeans(params);
			/* Insertando  cronograma de Dictado */
			this.getSqlMapClientTemplate().insert(
			 			"edu.GenericoEDUComercialAS400SQL.insertCronogramaDictado",
			 			params);
			
			/* Insertando en Cronograma Dictado Zona */
			this.insertCronogramaDictadoZona(params);
		}
		catch (Exception e) {
			String mensajeError = this.getKeyMessage("genericoEDUComercial.error.insertComercial", (Usuario)params.get("usuario"));
			throw new Exception(
					mensajeError+""
							+ e.getMessage());
		}
		finally {
			CustomerContextHolder.clearCustomerType();
		}
		return;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#updateCronogramaDictado(java.lang.String, java.util.Map)
	 */
	public void updateCronogramaDictado(String dataSource, Map params) throws Exception {
		try {
			CustomerContextHolder.setCustomerType(TypesafeEnum
					.getDataSource(dataSource));
			params=populateBeans(params);
			
			this.getSqlMapClientTemplate().update(
				 			"edu.GenericoEDUComercialAS400SQL.updateCronogramaDictado",
				 			params);
			
			/* Insertando en Cronograma Dictado Zona */
			this.insertCronogramaDictadoZona(params);
		}
		catch (Exception e) {
			String mensajeError = this.getKeyMessage("genericoEDUComercial.error.updateComercial", (Usuario)params.get("usuario"));
			throw new Exception(
					mensajeError+""
							+ e.getMessage());
		}
		finally {
			CustomerContextHolder.clearCustomerType();
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#getEnvioExisteCronogramaDictado(java.lang.String, java.util.Map)
	 */
	public Integer getEnvioExisteCronogramaDictado(String dataSource, Map params) throws Exception {
		Integer contador = new Integer(0);
		return contador;
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#insertEnvioCronogramaDictado(java.lang.String, java.util.Map, java.util.List)
	 */
	public void insertEnvioCronogramaDictado(String dataSource, Map params, final List lista) throws Exception {
		return;
	}
	
	
	/**
	 * @param params
	 */
	private void insertCronogramaDictadoZona(Map params) {
		this.getSqlMapClientTemplate().delete(
				"edu.GenericoEDUComercialAS400SQL.deleteCronogramaDictadoZona",
				params);
		String[] listaZonas = (String[]) params.get("listaZonas");
		for (int i=0; i < listaZonas.length; i++) {
			String codigoZona = listaZonas[i];
			params.put("codigoZona", codigoZona);
			this.getSqlMapClientTemplate().insert(
		 			"edu.GenericoEDUComercialAS400SQL.insertCronogramaDictadoZona", params);
		}
	}
    
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#insertGenerarPlanillaProgramacion(java.lang.String, java.util.Map, java.util.List)
	 */
	public void insertGenerarPlanillaProgramacion(String dataSource, Map params, final List lista) throws Exception {
		try {
			CustomerContextHolder.setCustomerType(TypesafeEnum
					.getDataSource(dataSource));
			
			/* Borrando previamente las planillas generadas anteriormente */ 
			final Map param=populateBeans(params);
			this.getSqlMapClientTemplate().delete(
				 			"edu.GenericoEDUComercialAS400SQL.deleteGenerarPlanillaProgramacion",
				 			params);
			
			/* Insertando planillas */
			final Timestamp timestamp = new Timestamp(System
					.currentTimeMillis());
			getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
				public Object doInSqlMapClient(SqlMapExecutor executor)
						throws SQLException {
					executor.startBatch();
					Iterator listIterator = lista.iterator();
					while (listIterator.hasNext()) {
						Map dataInsert = (Map) listIterator.next();
						dataInsert=populateMaps(dataInsert,param);							
						logger.debug("dataInsert " + dataInsert);
						
						getSqlMapClientTemplate()
								.insert(
										"edu.GenericoEDUComercialAS400SQL.insertGenerarPlanillaProgramacion",
										dataInsert);
					}
					int rowsaffected = executor.executeBatch();
					logger.debug("Inicio->" + timestamp.toString()
							+ "Fin-->"
							+ new Timestamp(System.currentTimeMillis()));
					System.out
							.println("rows afftected by insertGenerarPlanillaProgramacion: "
									+ rowsaffected);
					return null;
				}
			});
		} catch (Exception e) {
			String mensajeError = this.getKeyMessage("genericoEDUComercial.error.insertComercial", (Usuario)params.get("usuario"));
			throw new Exception(
					mensajeError+""
							+ e.getMessage());
		} finally {
			CustomerContextHolder.clearCustomerType();
		}		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#deleteEnvioCronogramaDictado(java.lang.String, java.util.Map)
	 */
	public void deleteEnvioCronogramaDictado(String dataSource, Map params) throws Exception {
		
	 }	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#limpiarCronogramaDictado(java.lang.String, java.util.Map, java.util.List)
	 */
	public void limpiarCronogramaDictado(String dataSource, Map params) throws Exception {
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#insertEnvioStatusConsultora(java.lang.String, java.util.Map, java.util.List)
	 */
	public void insertEnvioStatusConsultora(String dataSource, Map params, final List lista) throws Exception {
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#getMigracionComercialConsultora(java.lang.String, java.util.Map)
	 */
	public List getMigracionComercialConsultora(String dataSource, Map params) throws Exception {
		return null;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#getExisteCursoCapacitacion(java.lang.String, java.util.Map)
	 */
	public Integer getExisteCursoCapacitacion(String fuenteDatos, Map params) throws Exception {
		Integer contador = new Integer(0);
		try {
			CustomerContextHolder.setCustomerType(TypesafeEnum
					.getDataSource(fuenteDatos));
			params=populateBeans(params);
			 
			contador = (Integer) getSqlMapClientTemplate().queryForObject(
			 			"edu.GenericoEDUComercialAS400SQL.getExisteCursoCapacitacion",
			 			params);
			if (contador == null) contador = new Integer(0);
		}
		catch (Exception e) {
			String mensajeError = this.getKeyMessage("genericoEDUComercial.error.verificarRegistros", (Usuario)params.get("usuario"));
			throw new Exception(
					mensajeError+""
							+ e.getMessage());
		}
		finally {
			CustomerContextHolder.clearCustomerType();
		}
		return contador;
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#getTipoClasificacionEquivalencia(java.lang.String, java.util.Map)
	 */
	public List getTipoClasificacionEquivalencia(String fuenteDatos, Map params) throws Exception {
		return null;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#getCodigoClasificacionEquivalencia(java.lang.String, java.util.Map)
	 */
	public List getCodigoClasificacionEquivalencia(String fuenteDatos, Map params) throws Exception {
		return null;
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#executeEnvioEquivalenciaClasificacion(java.lang.String, java.util.Map)
	 */
	public void executeEnvioEquivalenciaClasificacion(String dataSource, Map params) throws Exception {
		
	}

	
	
	/**
	 * @param params The params to set.
	 */
	public void setParams(Map params) {
		this.params = params;
	}

	
	public List getCodigoMensajeEquivalencia(String fuenteDatos, Map params) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#insertConsultorasConfirmanCursoCosto(java.lang.String, java.util.List, java.lang.String, java.util.Map)
	 */
	public void insertConsultorasConfirmanCursoCosto(String dataSource, final List list, final String numeroLote,Map params) throws Exception {
		
		try {
			CustomerContextHolder.setCustomerType(TypesafeEnum
					.getDataSource(dataSource));
			
			final Timestamp timestamp = new Timestamp(System
					.currentTimeMillis());
			final Map param=populateBeans(params);
			
			getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
				public Object doInSqlMapClient(SqlMapExecutor executor)
						throws SQLException {
					executor.startBatch();
					Iterator listIterator = list.iterator();
					while (listIterator.hasNext()) {
						Map dataInsert = (Map) listIterator.next();
						dataInsert=populateMaps(dataInsert,param);							
						logger.debug("dataInsewrt " + dataInsert);
		
						dataInsert.put("numeroLote", numeroLote);
						dataInsert.put("tipoDespacho",
								Constants.EDU_TIPO_DESPACHO_INVITACION);
						getSqlMapClientTemplate()
								.insert(
										"edu.GenericoEDUComercialAS400SQL.insertarConfirmacionCursoCosto",
										dataInsert);
					}
					int rowsaffected = executor.executeBatch();
					logger.debug("Inicio->" + timestamp.toString()
							+ "Fin-->"
							+ new Timestamp(System.currentTimeMillis()));
					System.out
							.println("rows afftected by insertarConfirmacionCursoCosto: "
									+ rowsaffected);
					return null;
	}	
			});
		} catch (Exception e) {
			String mensajeError = this.getKeyMessage("genericoEDUComercial.error.insertarConfirmacionCursoCosto", (Usuario)params.get("usuario"));
			throw new Exception(
					mensajeError+""
							+ e.getMessage());

		} finally {
			CustomerContextHolder.clearCustomerType();
	}	

		
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#deleteConsultorasConfirmanCursoCosto(java.lang.String, java.util.Map)
	 */
	public void deleteConsultorasConfirmanCursoCosto(String dataSource, Map params) {
		this.deleteConsultorasAptasCosto(dataSource,params);
		
	}
	
	public String executeFakeProcesoCursoNoFacturados(String fuenteDatos, Map params) {
		return null;
	}
	
	public List getPedidosComercialCUV(String fuenteDatos, Map params) throws Exception{
		// TODO Auto-generated method stub
		return null;
	}
	
	public List getListRegionesACerrar(String dataSource, Map params) throws Exception {
		List listComercial =null;
		try {
			CustomerContextHolder.setCustomerType(TypesafeEnum
					.getDataSource(dataSource));
			params=populateBeans(params);
			String codigoPeriodo = (String)params.get("codigoPeriodo");
			if(StringUtils.isNotEmpty(codigoPeriodo)){
			params.put("anho",codigoPeriodo.substring(0,4));
			params.put("campania",codigoPeriodo.substring(4,6));
			}
			String fechaFacturacion = (String)params.get("fechaFacturacion");
			String []cad=StringUtils.split(fechaFacturacion,"/");
			if(cad!=null && cad.length>0){
				params.put("yyyymmdd",cad[2]+cad[1]+cad[0]);
			}
			log.debug("params >>>> "+ params);
			listComercial = getSqlMapClientTemplate()
					.queryForList(
							"edu.GenericoEDUComercialAS400SQL.getListRegionesACerrar",
							params);
		} catch (Exception e) {
			 log.debug("error " + e.getMessage());
//			String mensajeError = this.getKeyMessage("genericoEDUComercial.error.getListRegionesACerrar", (Usuario)params.get("usuario"));
//			throw new Exception(
//					mensajeError+""
//							+ e.getMessage());
		} finally {
			CustomerContextHolder.clearCustomerType();
		}

		return listComercial;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#getZONRegionesComercial(java.lang.String, java.util.Map)
	 */
	public List getZONRegionesComercial(String dataSource, Map params) throws Exception  {
	    	return null;
	    }
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#getZONZonasComercial(java.lang.String, java.util.Map)
	 */
	public List getZONZonasComercial(String dataSource, Map params) throws Exception {
    	return null;
    }

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#getZONControlFacturacionComercial(java.lang.String, java.util.Map)
	 */
	public List getZONControlFacturacionComercial(String dataSource, Map params) throws Exception {
    	return null;
    }
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#getZONCampanhaComercial(java.lang.String, java.util.Map)
	 */
	public List getZONCampanhaComercial(String dataSource, Map params) throws Exception {
    	return null;
    }
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#getZONConsultorasComercial(java.lang.String, java.util.Map)
	 */
	public List getZONConsultorasComercial(String dataSource, Map params, int skip, int max) throws Exception {
    	return null;
    }
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#insertZONRegionesComercial(java.lang.String, java.util.Map, java.util.List)
	 */
	public void insertZONRegionesComercial(String dataSource, final Map params, final List list) throws Exception {
    	
    }
	
	
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.edu.service.gen.GenericoEDUFacadeService#insertZONZonasComercial(java.lang.String, java.util.Map, java.util.List)
     */
    public void insertZONZonasComercial(String dataSource, final Map params, final List list) throws Exception {
    	
    	
    }
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.gen.GenericoEDUFacadeService#insertZONHistorialGerentesComercial(java.lang.String, java.util.Map, java.util.List)
	 */
	public void insertZONHistorialGerentesComercial(String dataSource, final Map params, final List list) throws Exception {
		
		
	}
	
}
