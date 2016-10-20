package biz.belcorp.ssicc.dao.spisicc.ibatis;
	    

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spisicc.ProcesoIMPGeneracionDocumentosLaserDAO;

import com.ibatis.sqlmap.client.SqlMapExecutor;

/**
 * 
 * <p>
 * <a href="ProcesoIMPGeneracionDocumentosLaserDAO.java.html"> <i>View Source</i> </a>
 * </p>
 * @author <a href="mailto:leonardo.lch@gmail.com">Leonardo Lizana Chauca</a>
 *
 */
@Repository("spisicc.procesoIMPGeneracionDocumentosLaserDAO")            
public class ProcesoIMPGeneracionDocumentosLaserDAOIbatis extends BaseDAOiBatis
		implements ProcesoIMPGeneracionDocumentosLaserDAO {

	public void executeGeneracionDocumentoLaserFactura(Map params) {
		 getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeGeneracionDocumentosLaserFactura", params);

	}

	public List getDocumentoLaserFacturaCabeceraList() {
		return getSqlMapClientTemplate().queryForList("ProcesoImpresionSQL.getDocumentoLaserCabeceraList");
	}

	public List getDocumentoLaserFacturaDetalleList(Map documentoLaserFacturaCabecera) {
		return getSqlMapClientTemplate().queryForList("ProcesoImpresionSQL.getDocumentoLaserFacturaDetalleList",documentoLaserFacturaCabecera);
	}

	public String getParametroPaginacionDetalle(Map params) {
		return (String) getSqlMapClientTemplate().queryForObject("ProcesoImpresionSQL.getParametroPaginacionDetalle",params);
		
	}

	public void saveDocumentoLaserFacturaXML(Map params) {
		getSqlMapClientTemplate().insert("ProcesoImpresionSQL.saveDocumentoLaserXML",params);
		
	}

	public void generacionDocumentosLaserFacturaXML() {
		getSqlMapClientTemplate().update("ProcesoImpresionSQL.generacionDocumentosLaserFacturaXML");
		
	}

	
	public void executeGeneracionDocumentoLaserGuia(Map params) {
		getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeGeneracionDocumentosLaserGuia", params);
		
	}

	public List getDocumentoLaserGuiaDetalleList(Map documentoLaserGuiaCabecera) {
		return getSqlMapClientTemplate().queryForList("ProcesoImpresionSQL.getDocumentoLaserGuiaDetalleList",documentoLaserGuiaCabecera);
																		   
	}

	
	public void saveDocumentoLaserGuiaXML(Map params) {
		getSqlMapClientTemplate().insert("ProcesoImpresionSQL.saveDocumentoLaserXML",params);
		
	}

	
	public void generacionDocumentosLaseGuiaXML() {
		getSqlMapClientTemplate().update("ProcesoImpresionSQL.generacionDocumentosLaserGuiaXML");
		
	}


	public List getDocumentoLaserGuiaCabeceraList() {
		return getSqlMapClientTemplate().queryForList("ProcesoImpresionSQL.getDocumentoLaserCabeceraList");
		
	}

	
	public List getDocumentoLaserCuentaCorrienteCabeceraList(Map params) {
		return getSqlMapClientTemplate().queryForList("ProcesoImpresionSQL.getDocumentoLaserCuentaCorrienteCabeceraList",params);
	}


	public String getSaldoActualConsultora(Map params) {
		return (String) getSqlMapClientTemplate().queryForObject("ProcesoImpresionSQL.getSaldoActualConsultora",params);
	}


	public List getDocumentoLaserCuentaCorrienteDetalleList(Map documentoLaserCuentaCorrienteCabecera){
			return getSqlMapClientTemplate().queryForList("ProcesoImpresionSQL.getDocumentoLaserCuentaCorrienteDetalleList",documentoLaserCuentaCorrienteCabecera);
	}


	public void saveDocumentoLaserCuentaCorrienteXML(Map params) {
		getSqlMapClientTemplate().insert("ProcesoImpresionSQL.saveDocumentoLaserXML",params);
		
	}


	public void generacionDocumentosLaserCuentaCorrienteXML() {
		getSqlMapClientTemplate().update("ProcesoImpresionSQL.generacionDocumentosLaserCuentaCorrienteXML");
		
	}

	
	public void deleteDocumentoLaserCuentaCorrienteXML() {
	getSqlMapClientTemplate().update("ProcesoImpresionSQL.deleteDocumentoLaserCuentaCorrienteXML");
		
	}

	public void deleteTablaDocumentoLaser() {
		getSqlMapClientTemplate().update("ProcesoImpresionSQL.deleteTablaDocumentosLaser");
		
	}

	public void executeGeneracionDocumentoLaserNotaCredito(Map params) {
		 getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeGeneracionDocumentoLaserNotaCredito", params);
		
	}

	public List getDocumentoLaserNotaCreditoCabeceraList() {
		return getSqlMapClientTemplate().queryForList("ProcesoImpresionSQL.getDocumentoLaserCabeceraList");
	}

	public List getDocumentoLaserNotaCreditoDetalleList(Map documentoLaserNotaCreditoCabecera) {
		return getSqlMapClientTemplate().queryForList("ProcesoImpresionSQL.getDocumentoLaserNotaCreditoDetalleList",documentoLaserNotaCreditoCabecera);
	}

	public void executeGeneracionDocumentoLaserNotaDebito(Map params) {
		getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeGeneracionDocumentoLaserNotaDebito",params);
	}


	public List getDocumentoLaserNotaDebitoCabeceraList() {
		return getSqlMapClientTemplate().queryForList("ProcesoImpresionSQL.getDocumentoLaserCabeceraList");
	}
	
	public void generacionDocumentosLaserNotaCretidoXML() {
		getSqlMapClientTemplate().update("ProcesoImpresionSQL.generacionDocumentosLaserNotaCretidoXML");
	}
	

	public List getDocumentoLaserNotaDebitoDetalleList(	Map documentoLaserNotaDebitoCabecera) {
		return getSqlMapClientTemplate().queryForList("ProcesoImpresionSQL.getDocumentoLaserNotaDebitoDetalleList",documentoLaserNotaDebitoCabecera);
	}
	
	public void generacionDocumentosLaserNotaDebitoXML() {
		getSqlMapClientTemplate().update("ProcesoImpresionSQL.generacionDocumentosLaserNotaDebitoXML");
		
	}

	public void saveDocumentoLaserNotaCreditoXML(Map params) {
		getSqlMapClientTemplate().insert("ProcesoImpresionSQL.saveDocumentoLaserNotaCreditoAndNotaDebitoXML",params);
	}

	public void saveDocumentoLaserNotaDebitoXML(Map documentoLaserNotaDebitoCabecera) {
		getSqlMapClientTemplate().insert("ProcesoImpresionSQL.saveDocumentoLaserNotaCreditoAndNotaDebitoXML",documentoLaserNotaDebitoCabecera);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.ProcesoIMPGeneracionDocumentosLaserDAO#getSaldoInicialCtaCte(java.util.Map)
	 */
	public String getSaldoInicialCtaCte(Map criteria) {
		return (String) getSqlMapClientTemplate().queryForObject("ProcesoImpresionSQL.getSaldoInicialCtaCte",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.ProcesoIMPGeneracionDocumentosLaserDAO#executeProcesaDetallesFacturaLaser(java.util.Map)
	 */
	public void executeProcesaDetallesFacturaLaser(Map params) {
		 getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeProcesaDetallesFacturaLaser", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.ProcesoIMPGeneracionDocumentosLaserDAO#executeReemplazaCaracteresDetallesFacturaLaser(java.util.Map)
	 */
	public void executeReemplazaCaracteresDetallesFacturaLaser(Map params) {
         getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeReemplazaCaracteresDetallesFacturaLaser", null);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.ProcesoIMPGeneracionDocumentosLaserDAO#executeGeneraDetallesFacturaLaser(java.util.Map)
	 */
	public void executeGeneraDetallesFacturaLaser(Map params) {
		 getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeGeneraDetallesFacturaLaser", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.ProcesoIMPGeneracionDocumentosLaserDAO#getCantidadDetallesFacturaLaser(java.util.Map)
	 */
	public int getCantidadDetallesFacturaLaser(Map params) {
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject(
				"ProcesoImpresionSQL.getCantidadDetallesFacturaLaser", params);
		return count.intValue();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.ProcesoIMPGeneracionDocumentosLaserDAO#executeProcesaDetallesCuentaCorrienteLaser(java.util.Map)
	 */
	public void executeProcesaDetallesCuentaCorrienteLaser(Map params) {
		 getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeProcesaDetallesCuentaCorrienteLaser", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.ProcesoIMPGeneracionDocumentosLaserDAO#executeGeneraDetallesCuentaCorrienteLaser(java.util.Map)
	 */
	public void executeGeneraDetallesCuentaCorrienteLaser(Map params) {
		 getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeGeneraDetallesCuentaCorrienteLaser", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.ProcesoIMPGeneracionDocumentosLaserDAO#getCantidadDetallesCuentaCorrienteLaser(java.util.Map)
	 */
	public int getCantidadDetallesCuentaCorrienteLaser(Map params) {
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject(
				"ProcesoImpresionSQL.getCantidadDetallesCuentaCorrienteLaser",
				params);
		return count.intValue();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.ProcesoIMPGeneracionDocumentosLaserDAO#executeReemplazaCaracteresUltimasNoticiasPrivilegeLaser(java.util.Map)
	 */
	public void executeReemplazaCaracteresUltimasNoticiasPrivilegeLaser(
			Map params) {
        getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeReemplazaCaracteresUltimasNoticiasPrivilegeLaser", null);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.ProcesoIMPGeneracionDocumentosLaserDAO#executeGeneraUltimasNoticiasPrivilegeLaser(java.util.Map)
	 */
	public void executeGeneraUltimasNoticiasPrivilegeLaser(Map params) {
		 getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeGeneraUltimasNoticiasPrivilegeLaser", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.ProcesoIMPGeneracionDocumentosLaserDAO#getCantidadUltimasNoticiasPrivilegeLaser(java.util.Map)
	 */
	public int getCantidadUltimasNoticiasPrivilegeLaser(Map params) {
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject(
				"ProcesoImpresionSQL.getCantidadUltimasNoticiasPrivilegeLaser",
				params);
		return count.intValue();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.ProcesoIMPGeneracionDocumentosLaserDAO#executeCargarPaquetesDocumetariosLaser(java.util.Map)
	 */
	public void executeCargarPaquetesDocumetariosLaser(Map params) {
		 getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeCargarBlobPaqueteDocumentarioSiCC", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.ProcesoIMPGeneracionDocumentosLaserDAO#executeProcesaPaquetesDocumentariosLaser(java.util.Map)
	 */
	public void executeProcesaPaquetesDocumentariosLaser(Map params) {
		 getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeProcesaPaquetesDocumentariosLaser", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.ProcesoIMPGeneracionDocumentosLaserDAO#executeReemplazaCaracteresPaquetesDocumentariosLaser(java.util.Map)
	 */
	public void executeReemplazaCaracteresPaquetesDocumentariosLaser(Map params) {
        getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeReemplazaCaracteresPaquetesDocumentariosLaser", null);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.ProcesoIMPGeneracionDocumentosLaserDAO#executeGeneraPaquetesDocumentariosLaser(java.util.Map)
	 */
	public void executeGeneraPaquetesDocumentariosLaser(Map params) {
		 getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeGeneraPaquetesDocumentariosLaser", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.ProcesoIMPGeneracionDocumentosLaserDAO#getCantidadPaquetesDocumentariosLaser(java.util.Map)
	 */
	public int getCantidadPaquetesDocumentariosLaser(Map params) {
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject(
				"ProcesoImpresionSQL.getCantidadPaquetesDocumentariosLaser",
				params);
		return count.intValue();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.ProcesoIMPGeneracionDocumentosLaserDAO#generacionDocumentoLaserBoletaDespachoXML(java.util.Map)
	 */
	public void generacionDocumentoLaserBoletaDespachoXML(Map params) {
		 getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeGeneraDocumentoLaserBoletaDespacho", params);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.ProcesoIMPGeneracionDocumentosLaserDAO#generacionDocumentoLaserOrdenCompraSimplificadaXML(java.util.Map)
	 */
	public void generacionDocumentoLaserOrdenCompraSimplificadaXML(Map params) {
		 getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeGeneraDocumentoLaserOrdenCompraSimplificada", params);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.ProcesoIMPGeneracionDocumentosLaserDAO#executeGeneracionDocumentosLaserColor(java.util.Map)
	 */
	public void executeGeneracionDocumentosLaserColor(Map params) {
		getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeGeneracionDocumentosLaserColor", params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.ProcesoIMPGeneracionDocumentosLaserDAO#getConcursosBonificacion(java.util.Map)
	 */
	public List getConcursosBonificacion(Map criteria) {
		return getSqlMapClientTemplate().queryForList("ProcesoImpresionSQL.getConcursosBonificacion", criteria);
	}	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.ProcesoIMPGeneracionDocumentosLaserDAO#getCantidadSeccionVentas(java.util.Map)
	 */
	public Integer getCantidadSeccionVentas(Map params) {
		return (Integer) getSqlMapClientTemplate().queryForObject("ProcesoImpresionSQL.getCantidadSeccionVentas",	params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.ProcesoIMPGeneracionDocumentosLaserDAO#deleteSeccionVentas(java.util.Map)
	 */
	public void deleteSeccionVentas(Map params) {
		this.getSqlMapClientTemplate().delete("ProcesoImpresionSQL.deleteSeccionVentas",params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.ProcesoIMPGeneracionDocumentosLaserDAO#insertListSeccionVentas(java.util.List)
	 */
	public void insertListSeccionVentas(final List list) throws Exception {
	  try{	
		final Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				Iterator listIterator = list.iterator();
				while (listIterator.hasNext()) {
					Map dataInsert = (Map) listIterator.next();
					logger.debug("dataInsewrt " + dataInsert);
					getSqlMapClientTemplate()
							.insert(
									"ProcesoImpresionSQL.insertListSeccionVentas",
									dataInsert);
				}
				int rowsaffected = executor.executeBatch();
				logger.debug("Inicio->" + timestamp.toString()
						+ "Fin-->"
						+ new Timestamp(System.currentTimeMillis()));
				System.out.println("rows afftected by insertListSeccionVentas: "
								+ rowsaffected);
				return null;
			}
		});
	  }catch(Exception e){
		  throw new Exception(e);
	  }
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.ProcesoIMPGeneracionDocumentosLaserDAO#getCantidadSeccionCompartamos(java.util.Map)
	 */
	public Integer getCantidadSeccionCompartamos(Map params) {
		return (Integer) getSqlMapClientTemplate().queryForObject("ProcesoImpresionSQL.getCantidadSeccionCompartamos",	params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.ProcesoIMPGeneracionDocumentosLaserDAO#getValidacionSeccionCompartamos(java.util.Map)
	 */
	public String getValidacionSeccionCompartamos(Map params) {
		return (String) getSqlMapClientTemplate().queryForObject("ProcesoImpresionSQL.getValidacionSeccionCompartamos",	params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.ProcesoIMPGeneracionDocumentosLaserDAO#deleteSeccionCompartamos(java.util.Map)
	 */
	public void deleteSeccionCompartamos(Map params) {
		this.getSqlMapClientTemplate().delete("ProcesoImpresionSQL.deleteSeccionCompartamos",params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.ProcesoIMPGeneracionDocumentosLaserDAO#insertListSeccionCompartamos(java.util.List)
	 */
	public void insertListSeccionCompartamos(final List list) throws Exception {
	  try{	
		final Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				Iterator listIterator = list.iterator();
				while (listIterator.hasNext()) {
					Map dataInsert = (Map) listIterator.next();
					logger.debug("dataInsewrt " + dataInsert);
					getSqlMapClientTemplate()
							.insert(
									"ProcesoImpresionSQL.insertListSeccionCompartamos",
									dataInsert);
				}
				int rowsaffected = executor.executeBatch();
				logger.debug("Inicio->" + timestamp.toString()
						+ "Fin-->"
						+ new Timestamp(System.currentTimeMillis()));
				System.out.println("rows afftected by insertListSeccionCompartamos: "
								+ rowsaffected);
				return null;
			}
		});
	  }catch(Exception e){
		  throw new Exception(e);
	  }
		
	}		

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.ProcesoIMPGeneracionDocumentosLaserDAO#getCantidadPlanPiloto(java.util.Map)
	 */
	public Integer getCantidadPlanPiloto(Map params) {
		return (Integer) getSqlMapClientTemplate().queryForObject("ProcesoImpresionSQL.getCantidadPlanPiloto",	params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.ProcesoIMPGeneracionDocumentosLaserDAO#deletePlanPiloto(java.util.Map)
	 */
	public void deletePlanPiloto(Map params) {
		this.getSqlMapClientTemplate().delete("ProcesoImpresionSQL.deletePlanPiloto", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.ProcesoIMPGeneracionDocumentosLaserDAO#insertListPlanPiloto(java.util.List)
	 */
	public void insertListPlanPiloto(final List list) throws Exception {
		try{	
			final Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				Iterator listIterator = list.iterator();
				while (listIterator.hasNext()) {
					Map dataInsert = (Map) listIterator.next();
					logger.debug("dataInsewrt " + dataInsert);
					getSqlMapClientTemplate()
							.insert(
									"ProcesoImpresionSQL.insertListPlanPiloto",
									dataInsert);
				}
				int rowsaffected = executor.executeBatch();
				logger.debug("Inicio->" + timestamp.toString()
						+ "Fin-->"
						+ new Timestamp(System.currentTimeMillis()));
				System.out.println("rows afftected by insertListPlanPiloto: "
								+ rowsaffected);
				return null;
			}
		});
	  }catch(Exception e){
		  throw new Exception(e);
	  }
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.ProcesoIMPGeneracionDocumentosLaserDAO#getCantidadSeccionFocalizados(java.util.Map)
	 */
	public Integer getCantidadSeccionFocalizados(Map params) {
		return (Integer) getSqlMapClientTemplate().queryForObject("ProcesoImpresionSQL.getCantidadSeccionFocalizados",	params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.ProcesoIMPGeneracionDocumentosLaserDAO#getValidacionSeccionFocalizados(java.util.Map)
	 */
	public String getValidacionSeccionFocalizados(Map params) {
		return (String) getSqlMapClientTemplate().queryForObject("ProcesoImpresionSQL.getValidacionSeccionFocalizados",	params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.ProcesoIMPGeneracionDocumentosLaserDAO#deleteSeccionFocalizados(java.util.Map)
	 */
	public void deleteSeccionFocalizados(Map params) {
		this.getSqlMapClientTemplate().delete("ProcesoImpresionSQL.deleteSeccionFocalizados",params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.ProcesoIMPGeneracionDocumentosLaserDAO#insertListSeccionFocalizados(java.util.List)
	 */
	public void insertListSeccionFocalizados(final List list) throws Exception {
	  try{	
		final Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				Iterator listIterator = list.iterator();
				while (listIterator.hasNext()) {
					Map dataInsert = (Map) listIterator.next();
					logger.debug("dataInsewrt " + dataInsert);
					getSqlMapClientTemplate()
							.insert(
									"ProcesoImpresionSQL.insertListSeccionFocalizados",
									dataInsert);
				}
				int rowsaffected = executor.executeBatch();
				logger.debug("Inicio->" + timestamp.toString()
						+ "Fin-->"
						+ new Timestamp(System.currentTimeMillis()));
				System.out.println("rows afftected by insertListSeccionFocalizados: "
								+ rowsaffected);
				return null;
			}
		});
	  }catch(Exception e){
		  throw new Exception(e);
	  }
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.ProcesoIMPGeneracionDocumentosLaserDAO#generacionDocumentoLaserProcesoFacturaXML(java.util.Map)
	 */
	public void generacionDocumentoLaserProcesoFacturaXML(Map params) {
		getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeGeneraDocumentoLaserProcesoFacturaXML", params);
		
	}		

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.ProcesoIMPGeneracionDocumentosLaserDAO#executeGeneraFacturaLaser(java.util.Map)
	 */
	public void executeGeneraFacturaLaser(Map params) {
		getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeGeneraFacturaLaser", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.ProcesoIMPGeneracionDocumentosLaserDAO#getCantidadFacturaLaser(java.util.Map)
	 */
	public Integer getCantidadFacturaLaser(Map params) {
		return (Integer) getSqlMapClientTemplate().queryForObject("ProcesoImpresionSQL.getCantidadFacturaLaser",	params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.ProcesoIMPGeneracionDocumentosLaserDAO#executeGeneraNotaCreditoLaser(java.util.Map)
	 */
	public void executeGeneraNotaCreditoLaser(Map params) {
		getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeGeneraNotaCreditoLaser", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.ProcesoIMPGeneracionDocumentosLaserDAO#getCantidadNotaCreditoLaser(java.util.Map)
	 */
	public Integer getCantidadNotaCreditoLaser(Map params) {
		return (Integer) getSqlMapClientTemplate().queryForObject("ProcesoImpresionSQL.getCantidadNotaCreditoLaser",	params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.ProcesoIMPGeneracionDocumentosLaserDAO#executeGeneraNotaDebitoLaser(java.util.Map)
	 */
	public void executeGeneraNotaDebitoLaser(Map params) {
		getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeGeneraNotaDebitoLaser", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.ProcesoIMPGeneracionDocumentosLaserDAO#getCantidadNotaDebitoLaser(java.util.Map)
	 */
	public Integer getCantidadNotaDebitoLaser(Map params) {
		return (Integer) getSqlMapClientTemplate().queryForObject("ProcesoImpresionSQL.getCantidadNotaDebitoLaser",	params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.ProcesoIMPGeneracionDocumentosLaserDAO#executeEnviarAlmacen(java.util.Map)
	 */
	public void executeEnviarAlmacen(Map params) {
		getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeEnviarAlmacen", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.ProcesoIMPGeneracionDocumentosLaserDAO#generacionDocumentoLaserProcesoNotaCreditoXML(java.util.Map)
	 */
	public void generacionDocumentoLaserProcesoNotaCreditoXML(Map params) {
		getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeGeneraDocumentoLaserProcesoNotCreditoXML", params);
		
	}
}