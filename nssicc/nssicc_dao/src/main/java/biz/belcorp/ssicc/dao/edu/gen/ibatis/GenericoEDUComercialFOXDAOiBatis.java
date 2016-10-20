package biz.belcorp.ssicc.dao.edu.gen.ibatis;

import java.io.File;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.orm.ibatis.SqlMapClientCallback;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.edu.gen.GenericoEDUComercialDAO;
import biz.belcorp.ssicc.dao.edu.model.CronogramaDictado;
import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.framework.util.CustomerContextHolder;
import biz.belcorp.ssicc.dao.framework.util.TypesafeEnum;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.scsicc.bean.LibreriasFOXBean;
import biz.belcorp.ssicc.dao.util.DateUtil;

import com.ibatis.sqlmap.client.SqlMapExecutor;


public class GenericoEDUComercialFOXDAOiBatis extends BaseDAOiBatis
		implements GenericoEDUComercialDAO {

	List listComercial = new ArrayList();
	
	public LibreriasFOXBean libreriasBean;	
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
						"edu.GenericoEDUComercialFOXSQL.getPedidosConsultoraNombreCompleto",
						params);
			else
				listComercial = getSqlMapClientTemplate().queryForList(
						"edu.GenericoEDUComercialFOXSQL.getPedidosConsultora",
						params);
			
		}
		catch (UncategorizedSQLException e) {
			throw obtenerMensajeError(e,params);
		}
		catch (Exception e) {
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
								"edu.GenericoEDUComercialFOXSQL.getPedidosConsultoraFacturados",
								params);
			}
			catch (UncategorizedSQLException e) {
				throw obtenerMensajeError(e,params);
			}
			catch (Exception e) {
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
								"edu.GenericoEDUComercialFOXSQL.getPedidosCursosFacturados",
								params);
			} 
			catch (UncategorizedSQLException e) {
				throw obtenerMensajeError(e,params);
			}
			catch (Exception e) {
				String mensajeError = this.getKeyMessage("genericoEDUComercial.error.pedidoConsultoraCursosFacturados", (Usuario)params.get("usuario"));
				throw new Exception(
						mensajeError+""
								+ e.getMessage());
			} finally {
				CustomerContextHolder.clearCustomerType();
			}
			return listComercial;
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
							"edu.GenericoEDUComercialFOXSQL.getPedidosCursosNoFacturados",
							params);
		}
		catch (UncategorizedSQLException e) {
			throw obtenerMensajeError(e,params);
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
	
	public List getConsultorasNuevas(String dataSource, Map params) {
		return null;
	}

	public List getDetalleProducto(String dataSource, Map params) {
		return null;
	}

	public List getMatrizProducto(String dataSource, Map params)
			throws Exception {
		List matrizProducto = null;
		try {
			CustomerContextHolder.setCustomerType(TypesafeEnum
					.getDataSource(dataSource));
			params = populateBeans(params);
			
			//activamos el flag de copiado solo archivos equivalente
			params.put("copiarArchivosEquivalentes",Constants.SI);
			
			//Si el flag de copia esta activado, copiamos los archivos
			if(params.get("flagCopiar")!=null){
			//copiamos
			copyParams(params);
			}				
			
			//desactivamos el falg de copiado de archivos equivalentes
			params.put("copiarArchivosEquivalentes",Constants.NO);
			matrizProducto = getSqlMapClientTemplate().queryForList(
					"edu.GenericoEDUComercialFOXSQL.getMatrizProducto",
					params);
		}
		catch (UncategorizedSQLException e) {
			throw obtenerMensajeError(e,params);
		}
		catch (Exception e) {
			String mensajeError = this.getKeyMessage("genericoEDUComercial.error.MatrizProducto", (Usuario)params.get("usuario"));
			throw new Exception(
					mensajeError+""
							+ e.getMessage());
		} finally {
			CustomerContextHolder.clearCustomerType();
		}
		return matrizProducto;
	}
	
	public List getRegionesComercial(String dataSource, Map params)
			throws Exception {
		try {

			CustomerContextHolder.setCustomerType(TypesafeEnum
					.getDataSource(dataSource));
			params=populateBeans(params);
			//se tien copiado por getControlFacturacionComercial
			//es el que inicia el proceso de recpcion maestros
			//copyServidorToLocal(params);
			listComercial = getSqlMapClientTemplate().queryForList(
					"edu.GenericoEDUComercialFOXSQL.getRegionesComercial",
					params);
		}
		catch (UncategorizedSQLException e) {
			throw obtenerMensajeError(e,params);
		}
		catch (Exception e) {
			String mensajeError = this.getKeyMessage("genericoEDUComercial.error.RegionesComercial", (Usuario)params.get("usuario"));
			throw new Exception(
					mensajeError+""
							+ e.getMessage());
		} finally {
			CustomerContextHolder.clearCustomerType();
		}

		return listComercial;
	}

	public List getZonasComercial(String dataSource, Map params)
			throws Exception {
		try {
			CustomerContextHolder.setCustomerType(TypesafeEnum
					.getDataSource(dataSource));
			populateBeans(params);
			//se tien copiado por getControlFacturacionComercial
			//es el que inicia el proceso de recpcion maestros
			//copyServidorToLocal(params);
			listComercial = getSqlMapClientTemplate().queryForList(
					"edu.GenericoEDUComercialFOXSQL.getZonasComercial",
					params);
		}
		catch (UncategorizedSQLException e) {
			throw obtenerMensajeError(e,params);
		}
		catch (Exception e) {
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
			populateBeans(params);
			//activamos el flag que indica que debe opiar los archivo solo
			//de recepcion maestro
			params.put("copiarArchivosRecepcionMaestro",Constants.SI);
			copyParams(params);
			//DESACTIVAMOS EL FLAG
			params.put("copiarArchivosRecepcionMaestro",Constants.NO);
			
			listComercial = getSqlMapClientTemplate()
					.queryForList(
							"edu.GenericoEDUComercialFOXSQL.getControlFacturacionComercial",
							params);
		}
		catch (UncategorizedSQLException e) {
			throw obtenerMensajeError(e,params);
		}
		catch (Exception e) {
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
			final String numeroLote, Map params) throws Exception {

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
						//logger.debug("dataInsewrt " + dataInsert);
						dataInsert=populateMaps(dataInsert,param);			
						
						dataInsert.put("numeroLote", numeroLote);
						dataInsert.put("tipoDespacho",
								Constants.EDU_TIPO_DESPACHO_INVITACION);
						getSqlMapClientTemplate()
								.insert(
										"edu.GenericoEDUComercialFOXSQL.insertarAptas",
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
			
//			paramsConex = populateBeans(paramsConex);
//			this.getSqlMapClientTemplate().update(
//		 			"edu.GenericoEDUComercialFOXSQL.lockAptas",
//		 			params);
//			this.getSqlMapClientTemplate().update(
//		 			"edu.GenericoEDUComercialFOXSQL.reindexAptas",
//		 			params);
//			this.getSqlMapClientTemplate().update(
//		 			"edu.GenericoEDUComercialFOXSQL.unlockAptas",
//		 			params);
			
		}
		catch (UncategorizedSQLException e) {
			throw obtenerMensajeError(e,params);
		}
		catch (Exception e) {
			String mensajeError = this.getKeyMessage("genericoEDUComercial.error.InsertConsultoras", (Usuario)params.get("usuario"));
			throw new Exception(
					mensajeError+""
							+ e.getMessage());

		} finally {
			CustomerContextHolder.clearCustomerType();
		}
	}

	public void insertConsultorasAptasCosto(String dataSource, final List list,
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
						//logger.debug("dataInsewrt " + dataInsert);
						
						dataInsert.put("numeroLote", numeroLote);
						dataInsert.put("tipoDespacho",
								Constants.EDU_TIPO_DESPACHO_INVITACION);
						getSqlMapClientTemplate()
								.insert(
										"edu.GenericoEDUComercialFOXSQL.insertarAptasCosto",
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
		}
		catch (UncategorizedSQLException e) {
			throw obtenerMensajeError(e,params);
		}
		catch (Exception e) {
			String mensajeError = this.getKeyMessage("genericoEDUComercial.error.InsertConsultorasAptasCosto", (Usuario)params.get("usuario"));
			throw new Exception(
					mensajeError+""
							+ e.getMessage());

		} finally {
			CustomerContextHolder.clearCustomerType();
		}
		
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
			 			"edu.GenericoEDUComercialFOXSQL.getExisteMensajeEducacion",
			 			params);
			if (contador == null) contador = new Integer(0);
		}
		catch (UncategorizedSQLException e) {
			throw obtenerMensajeError(e,params);
		}
		catch (Exception e) {
			throw new Exception("Error al verificar registros en el Sistema Comercial: "
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
			
			/* Verifica el tipo de mensaje a buscar */
			this.getSqlMapClientTemplate().insert(
			 			"edu.GenericoEDUComercialFOXSQL.insertMensajeEducacion",
			 			params);
		}
		catch (UncategorizedSQLException e) {
			throw obtenerMensajeError(e,params);
		}
		catch (Exception e) {
			throw new Exception("Error al insertar registro en el Sistema Comercial: Tabla Abierta ");
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
				 			"edu.GenericoEDUComercialFOXSQL.updateMensajeEducacion",
				 			params);
		}
		catch (UncategorizedSQLException e) {
			throw obtenerMensajeError(e,params);
		}
		catch (Exception e) {
			throw new Exception("Error al actualizar registro en el Sistema Comercial: "
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
				 			"edu.GenericoEDUComercialFOXSQL.deleteMensajeEducacion",
				 			params);
		}
		catch (UncategorizedSQLException e) {
			throw obtenerMensajeError(e,params);
		}
		catch (Exception e) {
			throw new Exception("Error al eliminar registro en el Sistema Comercial: "
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
    	Integer contador = new Integer(0);
		try {
			
			CustomerContextHolder.setCustomerType(TypesafeEnum
					.getDataSource(dataSource));
			params=populateBeans(params);			
			
			contador = (Integer) getSqlMapClientTemplate().queryForObject(
						 		"edu.GenericoEDUComercialFOXSQL.getExisteEmpresaComercializadora",
						 	params);
			if (contador == null) contador = new Integer(0);
		}
		catch (UncategorizedSQLException e) {
			throw obtenerMensajeError(e,params);
		}
		catch (Exception e) {
			throw new Exception("Error al verificar registros en el Sistema Comercial: "
					+ e.getMessage());
		}
		finally {
			CustomerContextHolder.clearCustomerType();
		}
		return contador;
    	
    }

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#insertEmpresaComercializadora(java.lang.String, java.util.Map)
	 */
	public void insertEmpresaComercializadora(String dataSource, Map params) throws Exception {
		try {
			CustomerContextHolder.setCustomerType(TypesafeEnum
					.getDataSource(dataSource));
			params=populateBeans(params);			

			this.getSqlMapClientTemplate().insert(
						 		"edu.GenericoEDUComercialFOXSQL.insertEmpresaComercializadora",
						 	params);
			
		}
		catch (UncategorizedSQLException e) {
			throw obtenerMensajeError(e,params);
		}
		catch (Exception e) {
			throw new Exception("Error al insertar registro en el Sistema Comercial:" 
					+ e.getMessage());
		}
		finally {
			CustomerContextHolder.clearCustomerType();
		}
		return;
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#updateEmpresaComercializadora(java.lang.String, java.util.Map)
	 */
	public void updateEmpresaComercializadora(String dataSource, Map params) throws Exception {
		try {
			CustomerContextHolder.setCustomerType(TypesafeEnum
					.getDataSource(dataSource));
			params=populateBeans(params);			
			
			this.getSqlMapClientTemplate().update(
					 		"edu.GenericoEDUComercialFOXSQL.updateEmpresaComercializadora",
						 	params);
		}
		catch (UncategorizedSQLException e) {
			throw obtenerMensajeError(e,params);
		}
		catch (Exception e) {
			throw new Exception("Error al actualizar registro en el Sistema Comercial:"
					+ e.getMessage());
		}
		finally {
			CustomerContextHolder.clearCustomerType();
		}
	}
	
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#deleteEmpresaComercializadora(java.lang.String, java.util.Map)
     */
    public void deleteEmpresaComercializadora(String dataSource, Map params) throws Exception {
    	try {
			CustomerContextHolder.setCustomerType(TypesafeEnum
					.getDataSource(dataSource));
			params=populateBeans(params);			

			this.getSqlMapClientTemplate().delete(
						 		"edu.GenericoEDUComercialFOXSQL.deleteEmpresaComercializadora",
						 	params);
		}
    	catch (UncategorizedSQLException e) {
			throw obtenerMensajeError(e,params);
		}
		catch (Exception e) {
			throw new Exception("Error al eliminar registro en el Sistema Comercial: "
					+ e.getMessage());
		}
		finally {
			CustomerContextHolder.clearCustomerType();
		}
	}
	
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#getExisteEmpresaComercializadora(java.lang.String, java.util.Map)
     */
    public Integer getBloqueoConsultora(String dataSource, Map params) throws Exception {
    	return null;
    }
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#deleteBloqueoConsultora(java.lang.String, java.util.Map)
     */
    public void deleteBloqueoConsultora(String dataSource, Map params) throws Exception {
    	return;
    }
	
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#insertBloqueoConsultora(java.lang.String, java.util.List)
     */
    public void insertBloqueoConsultora(String dataSource,final List list, Map params) throws Exception
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
										"edu.GenericoEDUComercialFOXSQL.insertBloqueoConsultora",
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
		}
		catch (UncategorizedSQLException e) {
			throw obtenerMensajeError(e,params);
		}
		catch (Exception e) {
			throw new Exception(
					"Error al cargar los registros de la tabla Historico de Bloqueo de Consultoras del Sistema Comercial: "
							+ e.getMessage());
		} finally {
			CustomerContextHolder.clearCustomerType();
		}
	}

    
	
	public void insertConsultorasAptasporProgramar(String dataSource, final List list,String numeroLote, final String periodo,Map params) {
		
	}
	
	public List getConsultorasAptasporProgramar(String dataSource, Map params)
	{
		return null;
	}

	public void insertParametrosCursoCapacitacion(String dataSource,
			 Map params) throws Exception{

		try {
			CustomerContextHolder.setCustomerType(TypesafeEnum
					.getDataSource(dataSource));
			params=populateBeans(params);			
			
			getSqlMapClientTemplate()
					.insert(
							"edu.GenericoEDUComercialFOXSQL.insertParametrosCursoCapacitacion",
							params);
		}
		catch (UncategorizedSQLException e) {
			throw obtenerMensajeError(e,params);
		}
		catch (Exception e) {
			logger
					.debug("Error GenericoEDUComercialFOXSQL--insertParametrosCursoCapacitacion : "
							+ e.getMessage());
			throw new Exception(
					"Error al cargar los registros de Curso de Capacitacion del Sistema Comercial: Tabla Abieta"); 
							
			
		} finally {
			CustomerContextHolder.clearCustomerType();
		}

	}
	
	/**
	 * Actualiza el Parametros Curso Capacitacion.
	 * 
	 * @param
	 */
	public void updateParametrosCursoCapacitacion(String dataSource, Map params) throws Exception {
		try {
			CustomerContextHolder.setCustomerType(TypesafeEnum
					.getDataSource(dataSource));
			params=populateBeans(params);			
			
			getSqlMapClientTemplate()
					.update(
							"edu.GenericoEDUComercialFOXSQL.updateParametrosCursoCapacitacion",
							params);

		}
		catch (UncategorizedSQLException e) {
			throw obtenerMensajeError(e,params);
		}
		catch (Exception e) {
			
			logger
					.debug("Error GenericoEDUComercialFOXSQL--updateParametrosCursoCapacitacion : "
							+ e.getMessage()+ "code "+e.hashCode());
			String mensajeError = this.getKeyMessage("genericoEDUComercial.error.updateParametros", (Usuario)params.get("usuario"));
			throw new Exception(
					mensajeError+""
							+ e.getMessage());
							
		} finally {
			CustomerContextHolder.clearCustomerType();
		}
	}
	
	public void deleteConsultorasAptas(String dataSource, Map params) throws Exception {
		
	}

	public void deleteConsultorasAptasCosto(String dataSource, Map params) {
		CustomerContextHolder.setCustomerType(TypesafeEnum
				.getDataSource(dataSource));
		params=populateBeans(params);
		
		getSqlMapClientTemplate().update(
				"edu.GenericoEDUComercialFOXSQL.deleteAptasCosto", params);
		//borramos fisicamente los registros de la tabla EDCURCST
		getSqlMapClientTemplate().update(
				"edu.GenericoEDUComercialFOXSQL.packAptasCosto", params);
		
		CustomerContextHolder.clearCustomerType();

	}
	
	public void deleteConsultorasAptasProgramar(String dataSource, Map params){
		
	}
	
	public void insertAptasHistoricas(String dataSource,final List list,Map params) throws Exception	{
		
	}

	public void deleteAptasHistoricas(String dataSource, Map params){
		
	}

	public void deleteBeneficiosCapacitadas(String dataSource, Map params){
		
	}
	
	public List getBeneficiosCapacitadas(String dataSource, Map params)throws Exception{
		
		return null;
	}
	
	public void insertBeneficiosCapacitadas(String dataSource,final List list,Map params) throws Exception
	{
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
					int i=0;
					while (listIterator.hasNext()) {
						i=i+1;
						Map dataInsert = (Map) listIterator.next();
						logger.debug("dataInsewrt " + dataInsert);
						dataInsert=populateMaps(dataInsert,param);			
						
						getSqlMapClientTemplate()
								.insert(
										"edu.GenericoEDUComercialFOXSQL.insertarBeneficiosCapacitadas",
										dataInsert);
					}
					int rowsaffected = executor.executeBatch();
					logger.debug("Inicio->" + timestamp.toString()
							+ "Fin-->"
							+ new Timestamp(System.currentTimeMillis()));
					System.out
							.println("rows afftected by insertBeneficiosCapacitadas: "
									+ rowsaffected);
					return null;
				}

			});
		}
		catch (UncategorizedSQLException e) {
			throw obtenerMensajeError(e,params);
		}
		catch (Exception e) {
		    String mensajeError = this.getKeyMessage("genericoEDUComercial.error.insertComercial", (Usuario)params.get("usuario"));
			throw new Exception(
					mensajeError+""
							+ e.getMessage());			
		} finally {
			CustomerContextHolder.clearCustomerType();
		}
	}
	
	public void updateBeneficiosCapacitadas(String dataSource, Map params){
		
	}
	
	
	public void insertMantenimientoClasificacion(String dataSource,final List list,Map params) throws Exception{
		
	}
	
	public void insertBeneficiosCapacitadas(String dataSource, Map params) {
		
	}

	public List getMantenimientoClasificacion(String dataSource, Map params)throws Exception	{
		try {
			CustomerContextHolder.setCustomerType(TypesafeEnum
					.getDataSource(dataSource));
			params=populateBeans(params);
			listComercial = getSqlMapClientTemplate()
					.queryForList(
							"edu.GenericoEDUComercialFOXSQL.getMantenimientoClasificacion",
							params);
		}
		catch (UncategorizedSQLException e) {
			throw obtenerMensajeError(e,params);
		}
		catch (Exception e) {
			String mensajeError = this.getKeyMessage("genericoEDUComercial.error.verificarRegistros", (Usuario)params.get("usuario"));
			throw new Exception(
					mensajeError+""
							+ e.getMessage());
		} finally {
			CustomerContextHolder.clearCustomerType();
		}

		return listComercial;
	}
	
	public void updateMantenimientoClasificacion(String dataSource, Map params)throws Exception {
		try {
			CustomerContextHolder.setCustomerType(TypesafeEnum
					.getDataSource(dataSource));
			params=populateBeans(params);
			getSqlMapClientTemplate()
					.update(
							"edu.GenericoEDUComercialFOXSQL.updateMantenimientoClasificacion",
							params);

		}
		catch (UncategorizedSQLException e) {
			throw obtenerMensajeError(e,params);
		}
		catch (Exception e) {
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
	
	public void deleteMantenimientoClasificacion(String dataSource, Map params)throws Exception {
		try {
			CustomerContextHolder.setCustomerType(TypesafeEnum
					.getDataSource(dataSource));
			params=populateBeans(params);
			getSqlMapClientTemplate()
					.update(
							"edu.GenericoEDUComercialFOXSQL.deleteMantenimientoClasificacion",
							params);

		}
		catch (UncategorizedSQLException e) {
			throw obtenerMensajeError(e,params);
		}
		catch (Exception e) {
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
	
	public void insertMantenimientoClasificacion(String dataSource, Map params)throws Exception {
		try {
			CustomerContextHolder.setCustomerType(TypesafeEnum
					.getDataSource(dataSource));
			params=populateBeans(params);
			getSqlMapClientTemplate()
					.update(
							"edu.GenericoEDUComercialFOXSQL.insertMantenimientoClasificacion",
							params);

		}
		catch (UncategorizedSQLException e) {
			throw obtenerMensajeError(e,params);
		}
		catch (Exception e) {
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

	public List getMantenimientoClasificacionInvi(String dataSource, Map params)throws Exception	{
		try {
			CustomerContextHolder.setCustomerType(TypesafeEnum
					.getDataSource(dataSource));
			params=populateBeans(params);
			listComercial = getSqlMapClientTemplate()
					.queryForList(
							"edu.GenericoEDUComercialFOXSQL.getMantenimientoClasificacionInvi",
							params);
		}
		catch (UncategorizedSQLException e) {
			throw obtenerMensajeError(e,params);
		}
		catch (Exception e) {
			String mensajeError = this.getKeyMessage("genericoEDUComercial.error.verificarRegistros", (Usuario)params.get("usuario"));
			throw new Exception(
					mensajeError+""
							+ e.getMessage());
		} finally {
			CustomerContextHolder.clearCustomerType();
		}

		return listComercial;
	}
	
	public void updateMantenimientoClasificacionInvi(String dataSource, Map params)throws Exception {
		try {
			CustomerContextHolder.setCustomerType(TypesafeEnum
					.getDataSource(dataSource));
			params=populateBeans(params);
			getSqlMapClientTemplate()
					.update(
							"edu.GenericoEDUComercialFOXSQL.updateMantenimientoClasificacionInvi",
							params);

		}
		catch (UncategorizedSQLException e) {
			throw obtenerMensajeError(e,params);
		}
		catch (Exception e) {
			logger
					.debug("Error ProcesoEDUComercialDAOiBatis--updateBeneficiosCapacitadasInvi : "
							+ e.getMessage());
				String mensajeError = this.getKeyMessage("genericoEDUComercial.error.updateComercial", (Usuario)params.get("usuario"));
				throw new Exception(
						mensajeError+""
								+ e.getMessage());	
		} finally {
			CustomerContextHolder.clearCustomerType();
		}
	}
	
	public void deleteMantenimientoClasificacionInvi(String dataSource, Map params)throws Exception {
		try {
			CustomerContextHolder.setCustomerType(TypesafeEnum
					.getDataSource(dataSource));
			params=populateBeans(params);
			getSqlMapClientTemplate()
					.update(
							"edu.GenericoEDUComercialFOXSQL.deleteMantenimientoClasificacionInvi",
							params);

		}
		catch (UncategorizedSQLException e) {
			throw obtenerMensajeError(e,params);
		}
		catch (Exception e) {
			logger
					.debug("Error ProcesoEDUComercialDAOiBatis--deleteMantenimientoClasificacionInvi : "
							+ e.getMessage());
			String mensajeError = this.getKeyMessage("genericoEDUComercial.error.deleteComercial", (Usuario)params.get("usuario"));
			throw new Exception(
					mensajeError+""
							+ e.getMessage());
		} finally {
			CustomerContextHolder.clearCustomerType();
		}
	}
	
	public void insertMantenimientoClasificacionInvi(String dataSource, Map params) throws Exception {
		try {
			CustomerContextHolder.setCustomerType(TypesafeEnum
					.getDataSource(dataSource));
			params=populateBeans(params);
			getSqlMapClientTemplate()
					.update(
							"edu.GenericoEDUComercialFOXSQL.insertMantenimientoClasificacionInvi",
							params);

		}
		catch (UncategorizedSQLException e) {
			throw obtenerMensajeError(e,params);
		}
		catch (Exception e) {
			logger
					.debug("Error ProcesoEDUComercialDAOiBatis--insertMantenimientoClasificacionInvi : "
							+ e.getMessage());
		    String mensajeError = this.getKeyMessage("genericoEDUComercial.error.insertComercial", (Usuario)params.get("usuario"));
			throw new Exception(
					mensajeError+""
							+ e.getMessage());		
		} finally {
			CustomerContextHolder.clearCustomerType();
		}
	}

	public void insertMantenimientoCodVenta(String dataSource, Map params) {
		try {
			CustomerContextHolder.setCustomerType(TypesafeEnum
					.getDataSource(dataSource));
			params=populateBeans(params);
			getSqlMapClientTemplate()
					.update(
							"edu.GenericoEDUComercialFOXSQL.insertMantenimientoCodVenta",
							params);

		}
		catch (Exception e) {
			logger
					.debug("Error ProcesoEDUComercialDAOiBatis--insertMantenimientoCodVenta : "
							+ e.getMessage());
		} finally {
			CustomerContextHolder.clearCustomerType();
		}
	}
	
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#getRecodificacionConsultora(java.lang.String, java.util.Map)
	 */
	public List getRecodificacionConsultora(String dataSource, Map params) throws Exception {
		return null;
	}

	@Autowired
	public void setLibreriasBean(LibreriasFOXBean libreriasBean) {
		this.libreriasBean = libreriasBean;
	}
	
	private Map populateBeans(Map params) 
	{	
//		String codigoPais = (String)params.get("codigoPais");
		params.put("libLogistica", params.get("FOX01"));
		params.put("libComercial", params.get("FOX02"));
		params.put("libProgsComercial", params.get("FOX03"));		
		params.put("libProgsLogistica", params.get("FOX04"));		
		params.put("servidorPais", params.get("FOX05") );
		//adicon de parametros
		params.put("libEducacion", params.get("FOX06") );
		params.put("cmdCopy",params.get("FOX07"));
		params.put("cmdRename",params.get("FOX08"));
		params.put("cmdDelete",params.get("FOX09"));
		params.put("archivos.origen",params.get("FOX10"));
		params.put("archivos.destino",params.get("FOX11"));
		params.put("sistema.operativo",params.get("FOX12"));
		
		params.put("servidorEDU","/ssicc/edu/");
		//log.debug("populate Beans params "+ params);
		return params;
	}
	
//	private Map populateMaps(Map dataInsert) {
//		dataInsert.putAll(this.paramsConex);
//		dataInsert = this.populateBeans(dataInsert);
//		return dataInsert;
//	}
	
	private Map populateMaps(Map dataInsert, Map params) {
		//dataInsert.putAll(params);
		dataInsert.put("libLogistica", params.get("FOX01"));
		dataInsert.put("libComercial", params.get("FOX02"));
		dataInsert.put("libProgsComercial", params.get("FOX03"));		
		dataInsert.put("libProgsLogistica", params.get("FOX04"));		
		dataInsert.put("servidorPais", params.get("FOX05") );
		//adicon de parametros
		dataInsert.put("libEducacion", params.get("FOX06") );
		dataInsert.put("cmdCopy",params.get("FOX07"));
		dataInsert.put("cmdRename",params.get("FOX08"));
		dataInsert.put("cmdDelete",params.get("FOX09"));
		dataInsert.put("archivos.origen",params.get("FOX10"));
		dataInsert.put("archivos.destino",params.get("FOX11"));
		dataInsert.put("sistema.operativo",params.get("FOX12"));
		return dataInsert;
	}
	
	private void copyParams(Map params) throws Exception{
		
		String archivos=(String)params.get("archivos.origen");
		String archRen=(String)params.get("archivos.destino");
		String copiarSoloControlFacturacion=(String)params.get("copiarSoloControlFacturacion");
		String copiarArchivosEquivalentes=(String)params.get("copiarArchivosEquivalentes");
		String copiarArchivosRecepcionMaestro=(String)params.get("copiarArchivosRecepcionMaestro");
		
		String noCopiarArchivos =(String)params.get("noCopiarArchivos");
		
		String [] arrArchivos=archivos.split(",");
		String [] arrArchivosRen=archRen.split(",");
		StringBuffer lineaSource=null;
		StringBuffer lineaDest=null;
		 
		if(arrArchivos!=null && arrArchivos.length>0){
			
			try{
			  int cantidad = arrArchivos.length; //numero de archivos a copiar
			  int i = 0; //posi de donde copiar
			
			  if (Constants.SI.equals(noCopiarArchivos)) {
				  return;
			  }
			  
			  if (Constants.SI.equals(copiarArchivosRecepcionMaestro)) {
				  cantidad = 3;
			  }
			  			  
			  if (Constants.SI.equals(copiarArchivosEquivalentes)) {
				    i=3;
				    cantidad = i+1;
			  }
	
			  if (Constants.SI.equals(copiarSoloControlFacturacion)) {
				  cantidad = 1; 
			  }
			  
					  
			  while(i< cantidad){
				  lineaSource=new StringBuffer("");
				  lineaDest=new StringBuffer("");
				  
				  switch (i) {
						case 4: //posLibLogistica
							  lineaSource.append(""+params.get("servidorPais")+params.get("libLogistica")+"/");  
							  lineaSource.append(arrArchivos[i]);
							
							break;
		
						default:
				  lineaSource.append(""+params.get("servidorPais")+params.get("libComercial")+"/");  
				  lineaSource.append(arrArchivos[i]);
							  		
							break;
						}
				  File fileSource = new File(lineaSource.toString());
				  lineaDest.append(""+params.get("servidorPais")+params.get("libEducacion")+"/");
			      lineaDest.append(""+arrArchivosRen[i]);
			      File fileDest = new File(lineaDest.toString());
			  
			      FileUtils.copyFile(fileSource,fileDest);
			      i++;					
		
			  }
			  
			}  
			catch (UncategorizedSQLException e) {
				throw obtenerMensajeError(e,params);
			}
		    catch(Exception e){
				String mensajeError = this.getKeyMessage("genericoEDUComercial.error.copyFile", (Usuario)params.get("usuario"));
				throw new Exception(
						mensajeError+""
								+ e.getMessage());
		    }
		}
		
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
			 			"edu.GenericoEDUComercialFOXSQL.getExisteCronogramaDictado",
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
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#insertCronogramaDictado(java.lang.String, java.util.Map)
	 */
	public void insertCronogramaDictado(String dataSource, Map params) throws Exception {
		try {
			CustomerContextHolder.setCustomerType(TypesafeEnum
					.getDataSource(dataSource));
			params=populateBeans(params);
			/*Completando las zonas*/
			String[] listaZonas = (String[]) params.get("listaZonas");
			for (int i=0; i < listaZonas.length; i++) {
				String codigoZona = listaZonas[i];
				params.put("codigoZona", codigoZona);
				/* Insertando  cronograma de Dictado */
				this.getSqlMapClientTemplate().insert(
				 			"edu.GenericoEDUComercialFOXSQL.insertCronogramaDictado",
				 			params);
			}
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
		log.debug("updateCronogramaDictado");
		//eliminamos el dictado con oid correspondiente al que Pasa como parametro
		this.getSqlMapClientTemplate().delete(
	 			"edu.GenericoEDUComercialFOXSQL.deleteCronogramaDictado",
	 			params);
		this.getSqlMapClientTemplate().update(
			"edu.GenericoEDUComercialFOXSQL.packCronogramaDictado",
			params);
		//insertamos otra vez informacion actualizada
		insertCronogramaDictado(dataSource,params);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#getEnvioExisteCronogramaDictado(java.lang.String, java.util.Map)
	 */
	public Integer getEnvioExisteCronogramaDictado(String dataSource, Map params) throws Exception {
		Integer contador = new Integer(0);
		try {
			CustomerContextHolder.setCustomerType(TypesafeEnum
					.getDataSource(dataSource));
			params=populateBeans(params);
			
			contador = (Integer) getSqlMapClientTemplate().queryForObject(
			 			"edu.GenericoEDUComercialFOXSQL.getExisteCronogramaDictadoEnvio",
			 			params);
			if (contador == null) contador = new Integer(0);
		}
		catch (UncategorizedSQLException e) {
			throw obtenerMensajeError(e,params);
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
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#deleteEnvioCronogramaDictado(java.lang.String, java.util.Map)
	 */
	public void deleteEnvioCronogramaDictado(String dataSource, Map params) throws Exception {
		try {	
			CustomerContextHolder.setCustomerType(TypesafeEnum
					.getDataSource(dataSource));
			
			/* Borrando previamente las planillas generadas anteriormente */ 
			params=populateBeans(params);
			this.getSqlMapClientTemplate().delete(
				 			"edu.GenericoEDUComercialFOXSQL.deleteEnvioCronogramaDictado",
				 			params);
			this.getSqlMapClientTemplate().update(
		 			"edu.GenericoEDUComercialFOXSQL.packCronogramaDictado",
		 			params);
		}
		catch (UncategorizedSQLException e) {
			throw obtenerMensajeError(e,params);
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
		return;
	 }	
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#deleteCronogramaDictado(java.lang.String, java.util.Map)
	 */
	public void deleteCronogramaDictado(String dataSource, Map params) throws Exception {
		log.debug("deleteCronogramaDictado");
		params=populateBeans(params);
		//eliminamos el dictado con oid correspondiente al que Pasa como parametro
		this.getSqlMapClientTemplate().delete(
	 			"edu.GenericoEDUComercialFOXSQL.deleteCronogramaDictado",
	 			params);
		this.getSqlMapClientTemplate().update(
			"edu.GenericoEDUComercialFOXSQL.packCronogramaDictado",
			params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#insertEnvioCronogramaDictado(java.lang.String, java.util.Map, java.util.List)
	 */
	public void insertEnvioCronogramaDictado(String dataSource, Map params, final List lista) throws Exception {
		 try {	
			CustomerContextHolder.setCustomerType(TypesafeEnum
					.getDataSource(dataSource));
			
			final Map param=populateBeans(params);
			this.getSqlMapClientTemplate().delete(
				 			"edu.GenericoEDUComercialFOXSQL.deleteEnvioCronogramaDictado",
				 			params);
			this.getSqlMapClientTemplate().update(
		 			"edu.GenericoEDUComercialFOXSQL.packCronogramaDictado",
		 			params);
			
			
			final Timestamp timestamp = new Timestamp(System
					.currentTimeMillis());
			getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
				public Object doInSqlMapClient(SqlMapExecutor executor)
						throws SQLException {
					executor.startBatch();
					Iterator listIterator = lista.iterator();
					while (listIterator.hasNext()) {
						CronogramaDictado crono = (CronogramaDictado) listIterator.next();
						Map dataInsert = new HashMap();
						try {
							Date fechaDictado = crono.getFechaDictado();
							String strFechaDictado=DateUtil.convertDateToString(fechaDictado);
							dataInsert = BeanUtils.describe(crono);
							dataInsert.put("fechaDictado",strFechaDictado);
						}	
						catch (Exception e)  {
							logger.error("error en insertEnvioCronogramaDictado " +e.getLocalizedMessage());
						}
						logger.debug("dataInsewrt " + dataInsert);
						dataInsert=populateMaps(dataInsert,param);			
						getSqlMapClientTemplate()
								.insert(
										"edu.GenericoEDUComercialFOXSQL.insertCronogramaDictado",
										dataInsert);
					}
					int rowsaffected = executor.executeBatch();
					logger.debug("Inicio->" + timestamp.toString()
							+ "Fin-->"
							+ new Timestamp(System.currentTimeMillis()));
					System.out
							.println("rows afftected by insertCronogramaDictado: "
									+ rowsaffected);
					return null;
				}
			});
			this.getSqlMapClientTemplate().update(
		 			"edu.GenericoEDUComercialFOXSQL.lockCronogramaDictado",
		 			params);
			this.getSqlMapClientTemplate().update(
		 			"edu.GenericoEDUComercialFOXSQL.reindexEnvioCronogramaDictado",
		 			params);
			this.getSqlMapClientTemplate().update(
		 			"edu.GenericoEDUComercialFOXSQL.unlockCronogramaDictado",
		 			params);
			
		}
		 catch (UncategorizedSQLException e) {
				throw obtenerMensajeError(e,params);
			}
		 catch (Exception e) {
			    String mensajeError = this.getKeyMessage("genericoEDUComercial.error.insertComercial", (Usuario)params.get("usuario"));
				throw new Exception(
						mensajeError+""
								+ e.getMessage());			
		 }finally {
			CustomerContextHolder.clearCustomerType();
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
			final String codigoPeriodoSgte = (String) params.get("codigoPeriodoSgte");
			this.getSqlMapClientTemplate().delete(
				 			"edu.GenericoEDUComercialFOXSQL.deleteGenerarPlanillaProgramacion",
				 			params);
			this.getSqlMapClientTemplate().update(
		 			"edu.GenericoEDUComercialFOXSQL.packGenerarPlanillaProgramacion",
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
						
						dataInsert.put("codigoPeriodoSgte", codigoPeriodoSgte);
						logger.debug("dataInsert " + dataInsert);
						
						getSqlMapClientTemplate()
								.insert(
										"edu.GenericoEDUComercialFOXSQL.insertGenerarPlanillaProgramacion",
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
			
			this.getSqlMapClientTemplate().update(
	 			"edu.GenericoEDUComercialFOXSQL.lockGenerarPlanillaProgramacion",
	 			params);
			this.getSqlMapClientTemplate().update(
	 			"edu.GenericoEDUComercialFOXSQL.reindexGenerarPlanillaProgramacion",
	 			params);
			this.getSqlMapClientTemplate().update(
	 			"edu.GenericoEDUComercialFOXSQL.unlockGenerarPlanillaProgramacion",
	 			params);
			
		}
		catch (UncategorizedSQLException e) {
			throw obtenerMensajeError(e,params);
		}
		catch (Exception e) {
		    String mensajeError = this.getKeyMessage("genericoEDUComercial.error.insertComercial", (Usuario)params.get("usuario"));
			throw new Exception(
					mensajeError+""
							+ e.getMessage());			

		} finally {
			CustomerContextHolder.clearCustomerType();
		}		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#limpiarCronogramaDictado(java.lang.String, java.util.Map, java.util.List)
	 */
	public void limpiarCronogramaDictado(String dataSource, Map params) throws Exception {
		try {	
			CustomerContextHolder.setCustomerType(TypesafeEnum
					.getDataSource(dataSource));
			
			/* Borrando cronograma de dictado generadas anteriormente */ 
			params=populateBeans(params);
			this.getSqlMapClientTemplate().delete(
				 			"edu.GenericoEDUComercialFOXSQL.deleteEnvioCronogramaDictado",
				 			params);
		}
		catch (UncategorizedSQLException e) {
			throw obtenerMensajeError(e,params);
		}
		catch (Exception e) {
			String mensajeError = this.getKeyMessage("genericoEDUComercial.error.limpiarCronograma", (Usuario)params.get("usuario"));
			throw new Exception(
					mensajeError+""
							+ e.getMessage());
		}
		finally {
			CustomerContextHolder.clearCustomerType();
		}
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#insertEnvioStatusConsultora(java.lang.String, java.util.Map, java.util.List)
	 */
	public void insertEnvioStatusConsultora(String dataSource, Map params, final List lista) throws Exception {
		try {
			CustomerContextHolder.setCustomerType(TypesafeEnum
					.getDataSource(dataSource));
			
			/* Insertando o actualizando status Consultora */
			params = populateBeans(params);
			
			this.getSqlMapClientTemplate().delete(
		 			"edu.GenericoEDUComercialFOXSQL.deleteStatusConsultora",
		 			params);
			this.getSqlMapClientTemplate().update(
					"edu.GenericoEDUComercialFOXSQL.packStatusConsultora",
 			params);

			String codigoPais = (String) params.get("codigoPais");
			Iterator listIterator = lista.iterator();
//			Integer contador = new Integer(0);
			while (listIterator.hasNext()) {
				Map dataInsert = (Map) listIterator.next();
				dataInsert.put("codigoPais", codigoPais);
				dataInsert=populateMaps(dataInsert,params);							
				logger.debug("dataInsert " + dataInsert);
				
				/* Verificando si existe Status consultora */
//				contador = (Integer) getSqlMapClientTemplate().queryForObject(
//			 			"edu.GenericoEDUComercialFOXSQL.getExisteStatusConsultora",
//			 			dataInsert);
//				if (contador == null) contador = new Integer(0);
				
				/* Actualizando Status Consultora */
//				if (contador.intValue() == 0 )
					getSqlMapClientTemplate()
							.insert(
									"edu.GenericoEDUComercialFOXSQL.insertEnvioStatusConsultora",
									dataInsert);
//				else
//					getSqlMapClientTemplate()
//							.update(
//									"edu.GenericoEDUComercialFOXSQL.updateEnvioStatusConsultora",
//									dataInsert);
			}
			/* Reindexando Tabla */
			getSqlMapClientTemplate()
				.update("edu.GenericoEDUComercialFOXSQL.lockStatusConsultora",params);
			getSqlMapClientTemplate()
				.update("edu.GenericoEDUComercialFOXSQL.reindexEnvioStatusConsultora",params);
			getSqlMapClientTemplate()
				.update("edu.GenericoEDUComercialFOXSQL.unlockStatusConsultora",params);
			
			
		}
		catch (UncategorizedSQLException e) {
			throw obtenerMensajeError(e,params);
		}
		catch (Exception e) {
		    String mensajeError = this.getKeyMessage("genericoEDUComercial.error.insertComercial", (Usuario)params.get("usuario"));
			throw new Exception(
					mensajeError+""
							+ e.getMessage());		
		} finally {
			CustomerContextHolder.clearCustomerType();
		}		
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#getMigracionComercialConsultora(java.lang.String, java.util.Map)
	 */
	public List getMigracionComercialConsultora(String dataSource, Map params) throws Exception {
		try {
			CustomerContextHolder.setCustomerType(TypesafeEnum
					.getDataSource(dataSource));
			populateBeans(params);
			listComercial = getSqlMapClientTemplate()
					.queryForList(
							"edu.GenericoEDUComercialFOXSQL.getMigracionComercialConsultora",
							params);
		}
		catch (UncategorizedSQLException e) {
			throw obtenerMensajeError(e,params);
		}
		catch (Exception e) {
			String mensajeError = this.getKeyMessage("genericoEDUComercial.error.verificarRegistros", (Usuario)params.get("usuario"));
			throw new Exception(
					mensajeError+""
							+ e.getMessage());
		} finally {
			CustomerContextHolder.clearCustomerType();
		}
		return listComercial;
	}



	/**
	 * @param params The params to set.
	 */
	public void setParams(Map params) {
		this.params = params;
	}



	public Integer getExisteCursoCapacitacion(String fuenteDatos, Map params)  throws Exception{
		Integer contador = new Integer(0);
		try {
			CustomerContextHolder.setCustomerType(TypesafeEnum
					.getDataSource(fuenteDatos));
			params=populateBeans(params);
			 
			contador = (Integer) getSqlMapClientTemplate().queryForObject(
			 			"edu.GenericoEDUComercialFOXSQL.getExisteCursoCapacitacion",
			 			params);
			if (contador == null) contador = new Integer(0);
		}
		catch (UncategorizedSQLException e) {
			throw obtenerMensajeError(e,params);
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
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#executeBorrarEquivalenciaMensaje(java.lang.String, java.util.Map)
	 */
	public void executeBorrarEquivalenciaMensaje(String fuenteDatos, Map params) throws Exception {
		
	}
	
	/**
	 * Devuelve Mensaje de Error Personalizado
	 * @param e
	 * @param params 
	 * @return
	 * @throws Exception
	 */
	private Exception obtenerMensajeError(UncategorizedSQLException e, Map params) {
		int valorException = e.getSQLException().getErrorCode();
		if (valorException == Constants.EDU_NRO_ERROR_ABRIR_TABLA_FOX){
			String mensajeError = this.getKeyMessage("genericoEDUComercial.error.tablaAbierta", (Usuario)params.get("usuario"));
			return new Exception(mensajeError);
		}	
		else {
			
			String mensajeError = this.getKeyMessage("genericoEDUComercial.error.verificarRegistros", (Usuario)params.get("usuario"));
			return new Exception(
					mensajeError+""
							+ e.getSQLException().getMessage());
			
			
		}
 
	}


	public List getCodigoMensajeEquivalencia(String fuenteDatos, Map params) throws Exception {
		return null;
	}



	public void insertConsultorasConfirmanCursoCosto(String dataSource, final List list, final String numeroLote,Map params) throws Exception {
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
						//logger.debug("dataInsewrt " + dataInsert);
						
						dataInsert.put("numeroLote", numeroLote);
						dataInsert.put("tipoDespacho",
								Constants.EDU_TIPO_DESPACHO_INVITACION);
						getSqlMapClientTemplate()
								.insert(
										"edu.GenericoEDUComercialFOXSQL.insertarConfirmacionCursoCosto",
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
		}
		catch (UncategorizedSQLException e) {
			throw obtenerMensajeError(e,params);
		}
		catch (Exception e) {
			String mensajeError = this.getKeyMessage("genericoEDUComercial.error.InsertConsultorasAptasCosto", (Usuario)params.get("usuario"));
			throw new Exception(
					mensajeError+""
							+ e.getMessage());

		} finally {
			CustomerContextHolder.clearCustomerType();
		}
		
	}

	public void deleteConsultorasConfirmanCursoCosto(String dataSource, Map params) {
		this.deleteConsultorasAptasCosto(dataSource,params);
		
	}

	public String executeFakeProcesoCursoNoFacturados(String fuenteDatos, Map params) {
		return "1";
	}

	public List getPedidosComercialCUV(String dataSource, Map params) throws Exception{
		try {

			CustomerContextHolder.setCustomerType(TypesafeEnum
					.getDataSource(dataSource));
			params=populateBeans(params);			
			String indicadorNombreCompleto = (String) params.get("indicadorNombreCompleto");
			if (Constants.EDU_INDICADOR_NOMBRE_COMPLETO.equals(indicadorNombreCompleto))
				listComercial = getSqlMapClientTemplate().queryForList(
						"edu.GenericoEDUComercialFOXSQL.getPedidosConsultoraNombreCompletoCUV",
						params);
			else
				listComercial = getSqlMapClientTemplate().queryForList(
						"edu.GenericoEDUComercialFOXSQL.getPedidosConsultoraCUV",
						params);
			
		}
		catch (UncategorizedSQLException e) {
			throw obtenerMensajeError(e,params);
		}
		catch (Exception e) {
			String mensajeError = this.getKeyMessage("genericoEDUComercial.error.pedidoConsultora", (Usuario)params.get("usuario"));
			throw new Exception(mensajeError + "" + e.getMessage());
		} finally {
			CustomerContextHolder.clearCustomerType();
		}
		return listComercial;
	}


	public List getListRegionesACerrar(String fuenteDatos, Map params) {
		return null;
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#getZONRegionesComercial(java.lang.String, java.util.Map)
	 */
	public List getZONRegionesComercial(String dataSource, Map params) throws Exception  {
		try {

			CustomerContextHolder.setCustomerType(TypesafeEnum.getDataSource(dataSource));
			params=populateBeans(params);
			listComercial = getSqlMapClientTemplate().queryForList("edu.GenericoEDUComercialFOXSQL.getZONRegionesComercial",
					params);
		}
		catch (UncategorizedSQLException e) {
			throw obtenerMensajeError(e,params);
		}
		catch (Exception e) {
			String mensajeError = this.getKeyMessage("genericoEDUComercial.error.ZONRegionesComercial", (Usuario)params.get("usuario"));
			throw new Exception(
					mensajeError+""
							+ e.getMessage());
		} finally {
			CustomerContextHolder.clearCustomerType();
		}

		return listComercial;
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#getZONZonasComercial(java.lang.String, java.util.Map)
	 */
	public List getZONZonasComercial(String dataSource, Map params) throws Exception {
		try {

			CustomerContextHolder.setCustomerType(TypesafeEnum.getDataSource(dataSource));
			params=populateBeans(params);
			listComercial = getSqlMapClientTemplate().queryForList("edu.GenericoEDUComercialFOXSQL.getZONZonasComercial",
					params);
		}
		catch (UncategorizedSQLException e) {
			throw obtenerMensajeError(e,params);
		}
		catch (Exception e) {
			String mensajeError = this.getKeyMessage("genericoEDUComercial.error.ZONZonasComercial", (Usuario)params.get("usuario"));
			throw new Exception(
					mensajeError+""
							+ e.getMessage());
		} finally {
			CustomerContextHolder.clearCustomerType();
		}

		return listComercial;
    }

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#getZONControlFacturacionComercial(java.lang.String, java.util.Map)
	 */
	public List getZONControlFacturacionComercial(String dataSource, Map params) throws Exception {
		try {

			CustomerContextHolder.setCustomerType(TypesafeEnum.getDataSource(dataSource));
			params=populateBeans(params);
			listComercial = getSqlMapClientTemplate().queryForList("edu.GenericoEDUComercialFOXSQL.getZONControlFacturacionComercial",
					params);
		}
		catch (UncategorizedSQLException e) {
			throw obtenerMensajeError(e,params);
		}
		catch (Exception e) {
			String mensajeError = this.getKeyMessage("genericoEDUComercial.error.ZONControlFacturacionComercial", (Usuario)params.get("usuario"));
			throw new Exception(
					mensajeError+""
							+ e.getMessage());
		} finally {
			CustomerContextHolder.clearCustomerType();
		}

		return listComercial;
    }
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#getZONCampanhaComercial(java.lang.String, java.util.Map)
	 */
	public List getZONCampanhaComercial(String dataSource, Map params) throws Exception {
		try {

			CustomerContextHolder.setCustomerType(TypesafeEnum.getDataSource(dataSource));
			params=populateBeans(params);
			listComercial = getSqlMapClientTemplate().queryForList("edu.GenericoEDUComercialFOXSQL.getZONCampanhaComercial",
					params);
		}
		catch (UncategorizedSQLException e) {
			throw obtenerMensajeError(e,params);
		}
		catch (Exception e) {
			String mensajeError = this.getKeyMessage("genericoEDUComercial.error.ZONCampanhaComercial", (Usuario)params.get("usuario"));
			throw new Exception(
					mensajeError+""
							+ e.getMessage());
		} finally {
			CustomerContextHolder.clearCustomerType();
		}

		return listComercial;
    }
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#getZONConsultorasComercial(java.lang.String, java.util.Map)
	 */
	public List getZONConsultorasComercial(String dataSource, Map params, int skip, int max) throws Exception {
		try {

			CustomerContextHolder.setCustomerType(TypesafeEnum.getDataSource(dataSource));
			params=populateBeans(params);
			listComercial = getSqlMapClientTemplate().queryForList("edu.GenericoEDUComercialFOXSQL.getZONConsultoraComercial",
					params, skip, max);
		}
		catch (UncategorizedSQLException e) {
			throw obtenerMensajeError(e,params);
		}
		catch (Exception e) {
			String mensajeError = this.getKeyMessage("genericoEDUComercial.error.ZONConsultorasComercial", (Usuario)params.get("usuario"));
			throw new Exception(
					mensajeError+""
							+ e.getMessage());
		} finally {
			CustomerContextHolder.clearCustomerType();
		}

		return listComercial;
    }

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#insertZONRegionesComercial(java.lang.String, java.util.Map, java.util.List)
	 */
	public void insertZONRegionesComercial(String dataSource, final Map params, final List list) throws Exception {
		try {
			CustomerContextHolder.setCustomerType(TypesafeEnum
					.getDataSource(dataSource));
			final Map param=populateBeans(params);
			final Timestamp timestamp = new Timestamp(System
					.currentTimeMillis());
			
			getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
				public Object doInSqlMapClient(SqlMapExecutor executor)
						throws SQLException {
					Date fecha = new Date(System.currentTimeMillis());
					Map dataDelete = populateBeans(params);
					getSqlMapClientTemplate().delete("edu.GenericoEDUComercialFOXSQL.deleteZONRegionesComercial", dataDelete);
					executor.startBatch();
					Iterator listIterator = list.iterator();
					while (listIterator.hasNext()) {
						Map dataInsert = (Map) listIterator.next();
						dataInsert=populateMaps(dataInsert,param);							
						logger.debug("dataInsewrt " + dataInsert);
						dataInsert.put("codigoPais", (String) params.get("codigoPais"));
						dataInsert.put("codigoUsuario", (String) params.get("codigoUsuario"));
						dataInsert.put("fecha", fecha);
						getSqlMapClientTemplate()
								.insert("edu.GenericoEDUComercialFOXSQL.insertZONRegionesComercial", dataInsert);
					}
					int rowsaffected = executor.executeBatch();
					logger.debug("Inicio->" + timestamp.toString()
							+ "Fin-->"
							+ new Timestamp(System.currentTimeMillis()));
					System.out
							.println("rows afftected by insertZONRegionesComercial: "
									+ rowsaffected);
					return null;
				}
			});
		}
		catch (UncategorizedSQLException e) {
			throw obtenerMensajeError(e,params);
		}
		catch (Exception e) {
			String mensajeError = this.getKeyMessage("genericoEDUComercial.error.insertZONRegionesComercial", (Usuario)params.get("usuario"));
			throw new Exception(
					mensajeError+""
							+ e.getMessage());

		} finally {
			CustomerContextHolder.clearCustomerType();
		}
    	
    }
	
	
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.edu.service.gen.GenericoEDUFacadeService#insertZONZonasComercial(java.lang.String, java.util.Map, java.util.List)
     */
    public void insertZONZonasComercial(String dataSource, final Map params, final List list) throws Exception {
    	try {
			CustomerContextHolder.setCustomerType(TypesafeEnum
					.getDataSource(dataSource));
			final Map param=populateBeans(params);
			final Timestamp timestamp = new Timestamp(System
					.currentTimeMillis());
			
			getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
				public Object doInSqlMapClient(SqlMapExecutor executor)
						throws SQLException {
					Date fecha = new Date(System.currentTimeMillis());
					Map dataDelete = populateBeans(params);
					getSqlMapClientTemplate().delete("edu.GenericoEDUComercialFOXSQL.deleteZONRZonasComercial", dataDelete);
					executor.startBatch();
					Iterator listIterator = list.iterator();
					while (listIterator.hasNext()) {
						Map dataInsert = (Map) listIterator.next();
						dataInsert=populateMaps(dataInsert,param);							
						logger.debug("dataInsewrt " + dataInsert);
						dataInsert.put("codigoPais", (String) params.get("codigoPais"));
						dataInsert.put("codigoUsuario", (String) params.get("codigoUsuario"));
						dataInsert.put("fecha", fecha);
						getSqlMapClientTemplate()
								.insert("edu.GenericoEDUComercialFOXSQL.insertZONZonasComercial", dataInsert);
					}
					int rowsaffected = executor.executeBatch();
					logger.debug("Inicio->" + timestamp.toString()
							+ "Fin-->"
							+ new Timestamp(System.currentTimeMillis()));
					System.out
							.println("rows afftected by insertZONZonasComercial: "
									+ rowsaffected);
					return null;
				}
			});
		}
		catch (UncategorizedSQLException e) {
			throw obtenerMensajeError(e,params);
		}
		catch (Exception e) {
			String mensajeError = this.getKeyMessage("genericoEDUComercial.error.insertZONZonasComercial", (Usuario)params.get("usuario"));
			throw new Exception(
					mensajeError+""
							+ e.getMessage());

		} finally {
			CustomerContextHolder.clearCustomerType();
		}
    	
    	
    }
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.gen.GenericoEDUFacadeService#insertZONHistorialGerentesComercial(java.lang.String, java.util.Map, java.util.List)
	 */
	public void insertZONHistorialGerentesComercial(String dataSource, final Map params, final List list) throws Exception {
		try {
			CustomerContextHolder.setCustomerType(TypesafeEnum
					.getDataSource(dataSource));
			final Map param=populateBeans(params);
			final Timestamp timestamp = new Timestamp(System
					.currentTimeMillis());
			
			getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
				public Object doInSqlMapClient(SqlMapExecutor executor)
						throws SQLException {
					Date fecha = new Date(System.currentTimeMillis());
					Map dataDelete = populateBeans(params);
					getSqlMapClientTemplate().delete("edu.GenericoEDUComercialFOXSQL.deleteZONHistorialGerentesComercial", dataDelete);
					executor.startBatch();
					Iterator listIterator = list.iterator();
					while (listIterator.hasNext()) {
						Map dataInsert = (Map) listIterator.next();
						dataInsert=populateMaps(dataInsert,param);							
						logger.debug("dataInsewrt " + dataInsert);
						dataInsert.put("codigoPais", (String) params.get("codigoPais"));
						dataInsert.put("codigoUsuario", (String) params.get("codigoUsuario"));
						dataInsert.put("fecha", fecha);
						getSqlMapClientTemplate()
								.insert("edu.GenericoEDUComercialFOXSQL.insertZONHistorialGerentesComercial", dataInsert);
					}
					int rowsaffected = executor.executeBatch();
					logger.debug("Inicio->" + timestamp.toString()
							+ "Fin-->"
							+ new Timestamp(System.currentTimeMillis()));
					System.out
							.println("rows afftected by HistorialGerentes: "
									+ rowsaffected);
					return null;
				}
			});
		}
		catch (UncategorizedSQLException e) {
			throw obtenerMensajeError(e,params);
		}
		catch (Exception e) {
			String mensajeError = this.getKeyMessage("genericoEDUComercial.error.insertZONHistorialGerentesComercial", (Usuario)params.get("usuario"));
			throw new Exception(
					mensajeError+""
							+ e.getMessage());

		} finally {
			CustomerContextHolder.clearCustomerType();
		}
		
	}

}
