package biz.belcorp.ssicc.dao.spusicc.fdv.ibatis;

import java.io.File;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.ProcesoFDVClusterizacion;
import biz.belcorp.ssicc.dao.model.ProcesoFDVDistribucionMeta;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.fdv.ProcesoFDVClusterizacionDAO;
import biz.belcorp.ssicc.dao.spusicc.fdv.model.GenericBean;

import com.ibatis.common.jdbc.exception.NestedSQLException;
import com.ibatis.sqlmap.client.SqlMapExecutor;

/**
 * <p>
 * <a href="ProcesoFDVClusterizacionDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:fayala@belcorp.biz">Frank Ayala</a>
 */
@Repository("spusicc.procesoFDVClusterizacionDAO")
public class ProcesoFDVClusterizacionDAOiBatis extends BaseDAOiBatis implements ProcesoFDVClusterizacionDAO{

	/**
     * 
     * @see biz.belcorp.ssicc.spusicc.fdv.service.ProcesoFDVClusterizacionService#executeProcedureClusterizacion(
     * biz.belcorp.ssicc.model.ProcesoFDVClusterizacion)
     * @throws Exception 
     */
	public String executeProcedureClusterizacion(
		ProcesoFDVClusterizacion procesoFDVClusterizacion) throws Exception {

		try{
		
			Map params = new HashMap();
			params.put("pcodpais", procesoFDVClusterizacion.getCodigoPais());
			params.put("procCodProc", procesoFDVClusterizacion.getCodProc());
			this.getSqlMapClientTemplate().update("spusicc.fdv.ProcesoFDVClusterizacionSQL." +
			"executePropuestaClusterizacion", params);
			
			return (String)params.get("mensajeError");			
			
		}catch(Exception e){
			
			if(e instanceof UncategorizedSQLException){
				if(e.getCause() instanceof NestedSQLException){
					if(e.getCause().getCause() instanceof SQLException){
						return e.getCause().getCause().getMessage();
					}
				}
			}
			
			throw new Exception(e);
		}
	}

	/**
     * 
     * @see biz.belcorp.ssicc.spusicc.fdv.service.ProcesoFDVClusterizacionService#executeProcedureDistMetas()
     * @throws Exception 
     */
	public String executeProcedureDistMetas(String codProc, String codPais) throws Exception{

		try{
			
			Map params = new HashMap();
			params.put("pcodproc", codProc);
			params.put("pcodpais", codPais);
			this.getSqlMapClientTemplate().update("spusicc.fdv.ProcesoFDVClusterizacionSQL." +
			"executeProcedureDistMetas", params);
			
			return (String)params.get("mensajeError");			
			
		}catch(Exception e){
			
			if(e instanceof UncategorizedSQLException){
				if(e.getCause() instanceof NestedSQLException){
					if(e.getCause().getCause() instanceof SQLException){
						return e.getCause().getCause().getMessage();
					}
				}
			}
			
			throw new Exception(e);
		}
	}

	/**
     * 
     * @see biz.belcorp.ssicc.spusicc.fdv.service.ProcesoFDVClusterizacionService#executeProcedureIniDistMetasSecciones()
     * @throws Exception 
     */
	public String executeProcedureIniDistMetasSecciones(String codProc) throws Exception{

		try{
			
			Map params = new HashMap();
			params.put("pcodproc", codProc);
			this.getSqlMapClientTemplate().update("spusicc.fdv.ProcesoFDVClusterizacionSQL." +
			"executeProcedureIniDistMetasSecciones", params);
			
			return (String)params.get("mensajeError");			
			
		}catch(Exception e){
			
			if(e instanceof UncategorizedSQLException){
				if(e.getCause() instanceof NestedSQLException){
					if(e.getCause().getCause() instanceof SQLException){
						return e.getCause().getCause().getMessage();
					}
				}
			}
			
			throw new Exception(e);
		}
	}

	/**
     * 
     * @see biz.belcorp.ssicc.spusicc.fdv.service.ProcesoFDVClusterizacionService#getCluAsigSistList(
     * biz.belcorp.ssicc.model.ProcesoFDVClusterizacion)
     * @throws Exception 
     */
	public List getCluAsigSistList(
			ProcesoFDVClusterizacion procesoFDVClusterizacion) throws Exception {

		List procesosCluster = this.getSqlMapClientTemplate().queryForList(
		"spusicc.fdv.ProcesoFDVClusterizacionSQL.getCluAsigSistList", procesoFDVClusterizacion);
        return procesosCluster;
	}

	/**
     * 
     * @see biz.belcorp.ssicc.spusicc.fdv.service.ProcesoFDVClusterizacionService#getParamDistribucionList()
     * @throws Exception
     */
	public List getParamDistribucionList(String codProc) throws Exception {

		Map map = new HashMap();
		map.put("procCodProc", codProc);
		map.put("flagDistribucion", Constants.ESTADO_ACTIVO);
		map.put("dist1", Constants.DISTRIBUCION_CX1);
		map.put("dist2", Constants.DISTRIBUCION_CX2);
		map.put("dist3", Constants.DISTRIBUCION_CX3);
		map.put("dist4", Constants.DISTRIBUCION_CX4);
		map.put("dist5", Constants.DISTRIBUCION_CX5);
		map.put("dist6", Constants.DISTRIBUCION_CX6);
		
		return this.getSqlMapClientTemplate().queryForList(
		"spusicc.fdv.ProcesoFDVClusterizacionSQL.getParamDistribucionList", map);
	}

	/**
     * 
     * @see biz.belcorp.ssicc.spusicc.fdv.dao.ProcesoFDVClusterizacionDAO#getProcesoCluster(java.lang.String)
     */
	public ProcesoFDVClusterizacion getProcesoCluster(String codigo) {
		
		ProcesoFDVClusterizacion procesoFDVClusterizacion = (ProcesoFDVClusterizacion)
		this.getSqlMapClientTemplate().queryForObject("spusicc.fdv.ProcesoFDVClusterizacionSQL.getProcesoCluster", codigo);
		
        if (procesoFDVClusterizacion == null) {
            throw new ObjectRetrievalFailureException(ProcesoFDVClusterizacion.class, codigo);
        }
        return procesoFDVClusterizacion;
	}

	/**
     * 
     * @see biz.belcorp.ssicc.spusicc.fdv.dao.ProcesoFDVClusterizacionDAO#getProcesosClusterByCriteria(java.util.Map)
     */
	public List getProcesosClusterByCriteria(Map criteria) {
		List procesosCluster = this.getSqlMapClientTemplate().queryForList(
		"spusicc.fdv.ProcesoFDVClusterizacionSQL.getProcesosClusterByCriteria", criteria);
        return procesosCluster;
	}

	/**
     * 
     * @see biz.belcorp.ssicc.spusicc.fdv.service.ProcesoFDVClusterizacionService#getZonaSeccionList()
     * @throws Exception
     */
	public List getZonaSeccionList(String codProc) throws Exception {
		return this.getSqlMapClientTemplate().queryForList(
		"spusicc.fdv.ProcesoFDVClusterizacionSQL.getZonaSeccionList", codProc);
	}

	/**
     * 
     * @see biz.belcorp.ssicc.spusicc.fdv.service.ProcesoFDVClusterizacionService#getZoneNoReliableDataList(java.lang.String)
     */
	public List getZoneNoReliableDataList(String codProc) {

		List procesosCluster = this.getSqlMapClientTemplate().queryForList(
		"spusicc.fdv.ProcesoFDVClusterizacionSQL.getZoneNoReliableDataList", codProc);
        return procesosCluster;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fdv.dao.ProcesoFDVClusterizacionDAO#getZoneNoReliableDataVarVentaList(java.lang.String)
	 */
	public List getZoneNoReliableDataVarVentaList(String codProc) {
		List zonas = this.getSqlMapClientTemplate().queryForList(
				"spusicc.fdv.ProcesoFDVClusterizacionSQL.getZoneNoReliableDataVarVentaList", codProc);
		return zonas;
	}

	/**
     * 
     * @see biz.belcorp.ssicc.spusicc.fdv.service.ProcesoFDVClusterizacionService#getZoneOfficeList(java.lang.String)
     */
	public List getZoneOfficeList(String codProc) {

		List procesosCluster = this.getSqlMapClientTemplate().queryForList(
		"spusicc.fdv.ProcesoFDVClusterizacionSQL.getZoneOfficeList", codProc);
        return procesosCluster;
	}

	/**
     * 
     * @see biz.belcorp.ssicc.spusicc.fdv.service.ProcesoFDVClusterizacionService#getZonesAsigPaisList(
     * biz.belcorp.ssicc.model.ProcesoFDVClusterizacion)
     * @throws Exception 
     */
	public List getZonesAsigPaisList(
			ProcesoFDVClusterizacion procesoFDVClusterizacion) throws Exception {

		List procesosCluster = this.getSqlMapClientTemplate().queryForList(
		"spusicc.fdv.ProcesoFDVClusterizacionSQL.getZonesAsigPaisList", procesoFDVClusterizacion);
        return procesosCluster;
	}

	/**
     * 
     * @see biz.belcorp.ssicc.spusicc.fdv.service.ProcesoFDVClusterizacionService#getZonesAsigSistList(
     * biz.belcorp.ssicc.model.ProcesoFDVClusterizacion)
     * @throws Exception 
     */
	public List getZonesAsigSistList(
			ProcesoFDVClusterizacion procesoFDVClusterizacion) throws Exception {

		List procesosCluster = this.getSqlMapClientTemplate().queryForList(
		"spusicc.fdv.ProcesoFDVClusterizacionSQL.getZonesAsigSistList", procesoFDVClusterizacion);
        return procesosCluster;
	}

	/**
     * 
     * @see biz.belcorp.ssicc.spusicc.fdv.dao.ProcesoFDVClusterizacionDAO#obtenerPathUpload(java.lang.String)
     */
	public String obtenerPathUpload(String codigoPais) {
		return (String) this.getSqlMapClientTemplate().
		queryForObject("spusicc.fdv.ProcesoFDVClusterizacionSQL.getPathUpload", codigoPais);	
	}

	/**
     * 
     * @see biz.belcorp.ssicc.spusicc.fdv.service.ProcesoFDVClusterizacionService#updateClusterAsigPais()
     * @throws Exception
     */
	public void updateClusterAsigPais(final String[] arrayCountryAsigSist,
			final String[] arrayCountryAsigPais, final String[] arrayZoneAsigPais,
			final String[] arrayZoneAsigSist, final String codProc) throws Exception {

		try{
			
			this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {				
				public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
					
					executor.startBatch();
					
					for (int i = 0; i < arrayZoneAsigSist.length; i++) {
						
						GenericBean genericBean = new GenericBean();
						String codZona = arrayZoneAsigSist[i];
						String cluAsigPais = arrayCountryAsigSist[i];
						
						if("".equals(cluAsigPais)) {
							cluAsigPais = null;
						}
						
						genericBean.setCluAsigPais(cluAsigPais);
						genericBean.setProcCodProc(codProc);
						genericBean.setCodZona(codZona);						
						
						ProcesoFDVClusterizacionDAOiBatis.this.getSqlMapClientTemplate().update(
						"spusicc.fdv.ProcesoFDVClusterizacionSQL.updateZonaSistAsigPaisFDV",
						genericBean);
					}
					
					for (int i = 0; i < arrayZoneAsigPais.length; i++) {
					
						GenericBean genericBean = new GenericBean();
						String codZona = arrayZoneAsigPais[i];
						String cluAsigPais = arrayCountryAsigPais[i];

						if("".equals(cluAsigPais)) {
							cluAsigPais = null;
						}
						
						genericBean.setCluAsigPais(cluAsigPais);
						genericBean.setProcCodProc(codProc);
						genericBean.setCodZona(codZona);
						
						ProcesoFDVClusterizacionDAOiBatis.this.getSqlMapClientTemplate().update(
						"spusicc.fdv.ProcesoFDVClusterizacionSQL.updateZonaSistAsigPaisFDV",
						genericBean);
					}
					
					executor.executeBatch();
					return null;
				}
			});
			
		  }catch(Exception e){
			  throw new Exception(e);
		  }
	}

	/**
     * 
     * @see biz.belcorp.ssicc.spusicc.fdv.service.ProcesoFDVClusterizacionService#updateFinalUpload(
     * 		biz.belcorp.ssicc.model.ProcesoFDVClusterizacion,
     *      biz.belcorp.ssicc.model.Usuario)
     * @throws Exception
     */
	public void updateFinalUpload(
			ProcesoFDVClusterizacion procesoFDVClusterizacion, Usuario usuario) throws Exception {
		
		File zonaFile = procesoFDVClusterizacion.getZonaFile();
		File seccFile = procesoFDVClusterizacion.getSeccionFile();
		File variFile = procesoFDVClusterizacion.getVariablesExogFile();
		
		boolean executeProcedure = false;		
		if(StringUtils.isNotBlank(procesoFDVClusterizacion.getCodProc())){
			if(Constants.PROCESO_CLUSTER_COD_NUEVO.equals(procesoFDVClusterizacion.getStaProc()) && 
				StringUtils.isNotBlank(procesoFDVClusterizacion.getArcZona()) &&
			   	StringUtils.isNotBlank(procesoFDVClusterizacion.getArcSecc()) &&
			   	StringUtils.isNotBlank(procesoFDVClusterizacion.getArcVari())){
				
				executeProcedure = true;
				
				// Si ya se llegaron a cargar todas las BDs y el estado estaba en nuevo, se cambia al estado BD cargadas
				procesoFDVClusterizacion.setStaProc(Constants.PROCESO_CLUSTER_COD_BD_CARGADAS);
				procesoFDVClusterizacion.setUpdatedByProcess(usuario.getLogin());
				procesoFDVClusterizacion.setLastUpdatedProcess(new Timestamp(System.currentTimeMillis()));
			}
			
			this.getSqlMapClientTemplate().update("spusicc.fdv.ProcesoFDVClusterizacionSQL.updateFinalUpload", 
			procesoFDVClusterizacion);
			
			if(Constants.PROCESO_CLUSTER_COD_BD_CARGADAS.equals(procesoFDVClusterizacion.getStaProc()) &&
			((!"".equals(zonaFile.getName())) || (!"".equals(seccFile.getName())) || (!"".equals(variFile.getName())))){
				executeProcedure = true;
			}
			
			try{
				if(executeProcedure){				
					Map map = new HashMap();
					map.put("procCodProc", procesoFDVClusterizacion.getCodProc());
					this.getSqlMapClientTemplate().update("spusicc.fdv.ProcesoFDVClusterizacionSQL.executeProcedureCons", map);
				}
			}catch(Exception e){
				throw new Exception(e);
			}
		}
		
	}
	
	/**
     * 
     * @see biz.belcorp.ssicc.spusicc.fdv.dao.ProcesoFDVClusterizacionDAO#updateProcesoCluster(
     * 		biz.belcorp.ssicc.model.ProcesoFDVClusterizacion, biz.belcorp.ssicc.model.Usuario)
     */
	public void updateInactiveProcesoCluster(
		String codigo, Usuario usuario) {
		
		ProcesoFDVClusterizacion bean = new ProcesoFDVClusterizacion();
		bean.setCodProc(codigo);
		
		this.getSqlMapClientTemplate().update("spusicc.fdv.ProcesoFDVClusterizacionSQL.updateInactiveProcesoCluster", 
		bean);		
	}

	public String updateListaParamDist(final String[] listCodParaDist,
			final String[] listValParaDist, final String codProc) throws Exception {
		
		try{
			
			this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {				
				public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
					
					executor.startBatch();
					ProcesoFDVDistribucionMeta bean = null;
					BigDecimal numPara = BigDecimal.ZERO;
					
					for (int i = 0; i < listCodParaDist.length; i++) {
					
						bean = new ProcesoFDVDistribucionMeta();
						String codPara = listCodParaDist[i];
						String valPara = listValParaDist[i];
						
						//Se divide solo para los valores porcentuales
						if(!StringUtils.equals(codPara, Constants.CODIGO_PARAMETRO_CLUSTERIZACION_TAMANYO_MINIMO_GRUPO))
							numPara = new BigDecimal(valPara).divide(new BigDecimal("100"));
						else
							numPara = new BigDecimal(valPara);
						
						bean.setCodProc(codProc);
						bean.setCodPara(codPara);
						bean.setValPara(numPara);
						
						ProcesoFDVClusterizacionDAOiBatis.this.getSqlMapClientTemplate().update(
						"spusicc.fdv.ProcesoFDVClusterizacionSQL.updateListaParamDist",
						bean);
					}
					
					executor.executeBatch();
					return null;
				}
			});
			
			return "";
			
		  }catch(Exception e){
			  
			  if(e instanceof UncategorizedSQLException){
					if(e.getCause() instanceof NestedSQLException){
						if(e.getCause().getCause() instanceof SQLException){
							return e.getCause().getCause().getMessage();
						}
					}
			  }
			  
			  throw new Exception(e);
		  }
	}

	/**
     * 
     * @see biz.belcorp.ssicc.spusicc.fdv.service.ProcesoFDVClusterizacionService#updateProcesoClusterizacion(
     * 		biz.belcorp.ssicc.model.ProcesoFDVClusterizacion,
     *      biz.belcorp.ssicc.model.Usuario)
     * @throws DataIntegrityViolationException 
     */
	public void updateProcesoClusterizacion(
			ProcesoFDVClusterizacion procesoFDVClusterizacion, Usuario usuario) throws DataIntegrityViolationException{

		this.getSqlMapClientTemplate().update("spusicc.fdv.ProcesoFDVClusterizacionSQL.updateProcesoClusterizacion", 
		procesoFDVClusterizacion);
	}

	/**
     * 
     * @see biz.belcorp.ssicc.spusicc.fdv.service.ProcesoFDVClusterizacionService#updatePropuestaClusterizacion()
     * @throws Exception 
     */
	public void updatePropuestaClusterizacion(
			final String[] listSelectedOffice, final String[] listSelectedNoReliableData,
			final String[] listCodZonaOffice, final String[] listCodZonaNoReliableData,
			final ProcesoFDVClusterizacion procesoFDVClusterizacion, Usuario usuario)
			throws Exception {

		try{
			
			this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {				
				public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
					
					executor.startBatch();
					
					// Actualizando los datos de la tabla principal fdv_proc
					ProcesoFDVClusterizacionDAOiBatis.this.getSqlMapClientTemplate().update("spusicc.fdv.ProcesoFDVClusterizacionSQL." +
					"updatePropuestaClusterizacion", procesoFDVClusterizacion);
					
					GenericBean genericBean = null;
					String codProc = procesoFDVClusterizacion.getCodProc();
					List listZonaOffice = Arrays.asList(listCodZonaOffice);
					List listSlctOffice = Arrays.asList(listSelectedOffice);
					List listZonaNoReliable = Arrays.asList(listCodZonaNoReliableData);
					List listSelectedNoReli = Arrays.asList(listSelectedNoReliableData);					
					
					for (int i = 0; i < listZonaOffice.size(); i++) {						
						if(!listSlctOffice.contains(listZonaOffice.get(i))){
							
							genericBean = new GenericBean();
							genericBean.setCodZona(String.valueOf(listZonaOffice.get(i)));
							genericBean.setProcCodProc(codProc);						
							genericBean.setFlaZofi(Constants.NO);
							genericBean.setFlaZonc(Constants.NO);
							genericBean.setFlaZoco(Constants.YES);
							
							ProcesoFDVClusterizacionDAOiBatis.this.getSqlMapClientTemplate().update(
							"spusicc.fdv.ProcesoFDVClusterizacionSQL.updateZonasOfficeFDV",
							genericBean);
						}					
					}
					
					for (int i = 0; i < listSlctOffice.size(); i++) {
						
						genericBean = new GenericBean();
						genericBean.setCodZona(String.valueOf(listSlctOffice.get(i)));
						genericBean.setProcCodProc(codProc);						
						genericBean.setFlaZofi(Constants.YES);
						genericBean.setFlaZonc(Constants.YES);
						genericBean.setFlaZoco(Constants.NO);
						
						ProcesoFDVClusterizacionDAOiBatis.this.getSqlMapClientTemplate().update(
						"spusicc.fdv.ProcesoFDVClusterizacionSQL.updateZonasOfficeFDV",
						genericBean);
					}
					
					for (int i = 0; i < listZonaNoReliable.size(); i++) {
						if(!listSelectedNoReli.contains(listZonaNoReliable.get(i)) &&
						  (!listSlctOffice.contains(listZonaNoReliable.get(i)))){
							
							genericBean = new GenericBean();
							genericBean.setCodZona(String.valueOf(listZonaNoReliable.get(i)));
							genericBean.setProcCodProc(codProc);						
							genericBean.setFlaZonc(Constants.NO);
							genericBean.setFlaZoco(Constants.YES);
							
							ProcesoFDVClusterizacionDAOiBatis.this.getSqlMapClientTemplate().update(
							"spusicc.fdv.ProcesoFDVClusterizacionSQL.updateZonasNoReliableDataFDV",
							genericBean);
						}					
					}
					
					for (int j = 0; j < listSelectedNoReli.size(); j++) {
					
						genericBean = new GenericBean();
						genericBean.setCodZona(String.valueOf(listSelectedNoReli.get(j)));
						genericBean.setProcCodProc(codProc);						
						genericBean.setFlaZonc(Constants.YES);
						genericBean.setFlaZoco(Constants.NO);
						
						ProcesoFDVClusterizacionDAOiBatis.this.getSqlMapClientTemplate().update(
						"spusicc.fdv.ProcesoFDVClusterizacionSQL.updateZonasNoReliableDataFDV",
						genericBean);
					}
					
					executor.executeBatch();
					return null;
				}
			});
			
		  }catch(Exception e){
			  throw new Exception(e);
		  }
	}

	/**
     * 
     * @see biz.belcorp.ssicc.spusicc.fdv.service.ProcesoFDVClusterizacionService#updateStatusClusterizacion()
     * @throws Exception 
     */
	public void updateStatusClusterizacion(String codProc, Usuario usuario, String staProc)
			throws Exception {

		ProcesoFDVClusterizacion procesoFDVClusterizacion = new ProcesoFDVClusterizacion();	
		procesoFDVClusterizacion.setLastUpdatedProcess(new Timestamp(System.currentTimeMillis()));
		procesoFDVClusterizacion.setStaProc(staProc);
		procesoFDVClusterizacion.setUpdatedByProcess(usuario.getLogin());
		procesoFDVClusterizacion.setCodProc(codProc);
		
		this.getSqlMapClientTemplate().update("spusicc.fdv.ProcesoFDVClusterizacionSQL.updateStatusClusterizacion", 
		procesoFDVClusterizacion);
	}
	
	public String updateValMevd(BigDecimal valMevd, String codProc, Usuario usuario) throws Exception {
		
		try{
		
			ProcesoFDVClusterizacion procesoFDVClusterizacion = new ProcesoFDVClusterizacion();
			procesoFDVClusterizacion.setCodProc(codProc);
			procesoFDVClusterizacion.setValMevd(valMevd);
			procesoFDVClusterizacion.setUpdatedByProcess(usuario.getLogin());
			procesoFDVClusterizacion.setLastUpdatedProcess(new Timestamp(System.currentTimeMillis()));
			
			this.getSqlMapClientTemplate().update("spusicc.fdv.ProcesoFDVClusterizacionSQL.updateValMevd", 
			procesoFDVClusterizacion);
			
			return "";
		
		}catch(Exception e){
			  
		  if(e instanceof UncategorizedSQLException){
				if(e.getCause() instanceof NestedSQLException){
					if(e.getCause().getCause() instanceof SQLException){
						return e.getCause().getCause().getMessage();
					}
				}
		  }
		  
		  throw new Exception(e);
		}
	}

	public String updateZonaSeccion(final String[] listCodZonaDist,
			final String[] listCamCaseDist, final String[] listNroSecoDist, final String codProc)
			throws Exception {
		
		try{
			
			this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {				
				public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
					
					executor.startBatch();
					ProcesoFDVDistribucionMeta bean = null;
					
					for (int i = 0; i < listCodZonaDist.length; i++) {
					
						bean = new ProcesoFDVDistribucionMeta();
						String codZona = listCodZonaDist[i];
						String camCase = listCamCaseDist[i];
						String nroSeco = listNroSecoDist[i];
						
						bean.setCodProc(codProc);
						bean.setCodZona(codZona);
						bean.setCamCase(camCase);
						bean.setNroSeco(new Long(nroSeco));
						
						ProcesoFDVClusterizacionDAOiBatis.this.getSqlMapClientTemplate().update(
						"spusicc.fdv.ProcesoFDVClusterizacionSQL.updateZonaSeccion",
						bean);
					}
					
					executor.executeBatch();
					return null;
				}
			});
			
			return "";
			
		  }catch(Exception e){
			  
			  if(e instanceof UncategorizedSQLException){
					if(e.getCause() instanceof NestedSQLException){
						if(e.getCause().getCause() instanceof SQLException){
							return e.getCause().getCause().getMessage();
						}
					}
			  }
			  
			  throw new Exception(e);
		  }
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fdv.dao.ProcesoFDVClusterizacionDAO#updateZonasNoReliableDataVarVenta(java.lang.String[], java.lang.String)
	 */
	public void updateZonasNoReliableDataVarVenta(
			final String[] listSelectedNoReliableDataVarVenta, final String codProc)
			throws Exception {
		try{
			
			this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {				
				public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
					
					executor.startBatch();
					GenericBean bean = null;
					
					for (int i = 0; i < listSelectedNoReliableDataVarVenta.length; i++) {
					
						bean = new GenericBean();
						
						bean.setProcCodProc(codProc);
						bean.setCodZona(listSelectedNoReliableDataVarVenta[i]);
						bean.setFlaZonc(Constants.YES);
												
						ProcesoFDVClusterizacionDAOiBatis.this.getSqlMapClientTemplate().update(
						"spusicc.fdv.ProcesoFDVClusterizacionSQL.updateZonasNoReliableDataVarVentaFDV",
						bean);
					}
					
					executor.executeBatch();
					return null;
				}
			});
		  }catch(Exception e){
			  throw new Exception(e);
		  }
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fdv.dao.ProcesoFDVClusterizacionDAO#getAnyosProcesosDistribucionRealizadaByPais(java.lang.String)
	 */
	public List getAnyosProcesosDistribucionRealizadaByPais(String codigoPais) {
		List procesos = this.getSqlMapClientTemplate().queryForList(
				"spusicc.fdv.ProcesoFDVClusterizacionSQL.getAnyosProcesosDistribucionRealizadaByPais", codigoPais);
		return procesos;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fdv.dao.ProcesoFDVClusterizacionDAO#getRegionesByProceso(java.lang.String)
	 */
	public List getRegionesByProceso(String codigoProceso) {
		
		List regiones = this.getSqlMapClientTemplate().queryForList(
				"spusicc.fdv.ProcesoFDVClusterizacionSQL.getRegionesByProceso", codigoProceso);
		
		return regiones;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fdv.dao.ProcesoFDVClusterizacionDAO#getZonasByProcesoRegion(java.lang.String, java.lang.String)
	 */
	public List getZonasByProcesoRegion(String codigoRegion, String codigoProceso) {

		Map criteria = new HashMap();
		criteria.put("codigoRegion", codigoRegion);
		criteria.put("codigoProceso", codigoProceso);
		
		List zonas = this.getSqlMapClientTemplate().queryForList(
				"spusicc.fdv.ProcesoFDVClusterizacionSQL.getZonasByProcesoRegion", criteria);
		
		return zonas;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fdv.dao.ProcesoFDVClusterizacionDAO#getValorParametroProceso(java.lang.String, java.lang.String)
	 */
	public Double getValorParametroProceso(String codigoProceso, String codigoParametro) {

		Map criteria = new HashMap();
		criteria.put("codigoProceso", codigoProceso);
		criteria.put("codigoParametro", codigoParametro);
		
		Double valor = (Double) this.getSqlMapClientTemplate().queryForObject("spusicc.fdv.ProcesoFDVClusterizacionSQL.getValorParametroProceso", criteria);
		
        return valor;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fdv.dao.ProcesoFDVClusterizacionDAO#getParametrosProceso(java.lang.String)
	 */
	public List getParametrosProceso(String codigoProceso) {
		Map map = new HashMap();
		map.put("procCodProc", codigoProceso);
		return this.getSqlMapClientTemplate().queryForList("spusicc.fdv.ProcesoFDVClusterizacionSQL.getParamDistribucionList", map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fdv.dao.ProcesoFDVClusterizacionDAO#getZonasAjusteDV(java.lang.String)
	 */
	public List getZonasAjusteDV(String codigoProceso) {
		return this.getSqlMapClientTemplate().queryForList("spusicc.fdv.ProcesoFDVClusterizacionSQL.getZonasAjusteDV", codigoProceso);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fdv.dao.ProcesoFDVClusterizacionDAO#executeProcesoAjusteMetas(java.lang.String)
	 */
	public String executeProcesoAjusteMetas(String codigoProceso) throws Exception {
		try{
			
			Map params = new HashMap();
			params.put("codigoProceso", codigoProceso);
			params.put("mensajeError", null);
			
			this.getSqlMapClientTemplate().update("spusicc.fdv.ProcesoFDVClusterizacionSQL.executeProcesoAjusteMetas", params);
			
			return (String)params.get("mensajeError");			
			
		}catch(Exception e){
			
			if(e instanceof UncategorizedSQLException){
				if(e.getCause() instanceof NestedSQLException){
					if(e.getCause().getCause() instanceof SQLException){
						return e.getCause().getCause().getMessage();
					}
				}
			}
			
			throw new Exception(e);
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fdv.dao.ProcesoFDVClusterizacionDAO#updateValoresAjustados(java.lang.String[], java.lang.String[], java.lang.String)
	 */
	public void updateValoresAjustados(final String[] listCodZonaAjus,
			final String[] listValZonaAjus, final String codigoProceso) throws Exception {
		
		try{
			
			this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {				
				public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
					
					executor.startBatch();
					GenericBean bean = null;
					
					for (int i = 0; i < listCodZonaAjus.length; i++) {
					
						bean = new GenericBean();
						
						bean.setProcCodProc(codigoProceso);
						bean.setCodZona(listCodZonaAjus[i]);
						bean.setValor(new BigDecimal(listValZonaAjus[i]));
												
						ProcesoFDVClusterizacionDAOiBatis.this.getSqlMapClientTemplate().update(
						"spusicc.fdv.ProcesoFDVClusterizacionSQL.updateValorAjustado",
						bean);
					}
					
					executor.executeBatch();
					return null;
				}
			});
		  }catch(Exception e){
			  throw new Exception(e);
		  }
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fdv.dao.ProcesoFDVClusterizacionDAO#getVersionProceso(java.lang.String, java.lang.String, java.lang.String)
	 */
	public Integer getVersionProceso(String codigoProceso,
			String anyoPeriodoProceso, String periodoProceso) {
		
		Map parms = new HashMap();
		parms.put("codigoProceso", codigoProceso);
		parms.put("anyoPeriodoProceso", anyoPeriodoProceso);
		parms.put("periodoProceso", periodoProceso);
		
		Integer version = (Integer) this.getSqlMapClientTemplate().queryForObject("spusicc.fdv.ProcesoFDVClusterizacionSQL.getVersionProceso", parms);
		
		if(version == null)
			version = new Integer(0);
		
		return version;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fdv.dao.ProcesoFDVClusterizacionDAO#executeProcedureClusterizacionDesdeArchivo(biz.belcorp.ssicc.model.ProcesoFDVClusterizacion)
	 */
	public String executeProcedureClusterizacionDesdeArchivo(
			ProcesoFDVClusterizacion procesoFDVClusterizacion) throws Exception {
		
		try{
			
			Map params = new HashMap();
			params.put("pcodpais", procesoFDVClusterizacion.getCodigoPais());
			params.put("procCodProc", procesoFDVClusterizacion.getCodProc());
			this.getSqlMapClientTemplate().update("spusicc.fdv.ProcesoFDVClusterizacionSQL." +
			"executeProcedureClusterizacionDesdeArchivo", params);
			
			return (String)params.get("mensajeError");			
			
		}catch(Exception e){
			
			if(e instanceof UncategorizedSQLException){
				if(e.getCause() instanceof NestedSQLException){
					if(e.getCause().getCause() instanceof SQLException){
						return e.getCause().getCause().getMessage();
					}
				}
			}
			
			throw new Exception(e);
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.fdv.dao.ProcesoFDVClusterizacionDAO#updateAjusteMeta(java.lang.String, java.lang.String)
	 */
	public void updateValorAjustadoArchivo(final List listValAjusteMeta, final String codigoProceso)throws Exception {
		
		try {
			this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {				
				public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
					
					executor.startBatch();
					GenericBean bean = null;
					for (int i = 0; i < listValAjusteMeta.size(); i++) {
					
						bean = (GenericBean) listValAjusteMeta.get(i);
						bean.setProcCodProc(codigoProceso);
												
						ProcesoFDVClusterizacionDAOiBatis.this.getSqlMapClientTemplate().update(
						"spusicc.fdv.ProcesoFDVClusterizacionSQL.updateValorAjustado",
						bean);
					}
					
					executor.executeBatch();
					return null;
				}
			});
		} catch (Exception e) {
			throw new Exception(e);
		}
	}
}
