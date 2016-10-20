package biz.belcorp.ssicc.dao.spusicc.fdv.ibatis;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.ProcesoFDVClusterizacion;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.fdv.ProcesoFDVFileZonaDAO;

import com.ibatis.sqlmap.client.SqlMapExecutor;

/**
 * <p>
 * <a href="ProcesoFDVFileZonaDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:fayala@belcorp.biz">Frank Ayala</a>
 */
@Repository("spusicc.procesoFDVFileZonaDAO")
public class ProcesoFDVFileZonaDAOiBatis extends BaseDAOiBatis implements ProcesoFDVFileZonaDAO{

	/**
     * 
     * @see biz.belcorp.ssicc.spusicc.fdv.service.ProcesoFDVClusterizacionService#getNextID()
     */
	public String getNextID() {
		
		String codigoProc = (String) getSqlMapClientTemplate().
		queryForObject("spusicc.fdv.ProcesoFDVClusterizacionSQL.getNextID", null);		

		int lengthField = 10;
		int lengthProc  = codigoProc.length();
		int lengthZero  = 0;
		
		if(lengthProc <= lengthField){
			lengthZero = lengthField - lengthProc;
		}
		
		for (int i = 0; i < lengthZero; i++) {
			codigoProc = "0"+codigoProc;
		}
		
		return codigoProc;
	}

	/**
     * 
     * @see biz.belcorp.ssicc.spusicc.fdv.dao.ProcesoFDVClusterizacionDAO#getProcesoCluster(java.lang.String)
     */
	public ProcesoFDVClusterizacion getProcesoCluster(String codigo) {
		
		ProcesoFDVClusterizacion procesoFDVClusterizacion = (ProcesoFDVClusterizacion)
		getSqlMapClientTemplate().queryForObject("spusicc.fdv.ProcesoFDVClusterizacionSQL.getProcesoCluster", codigo);
		
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
		List procesosCluster = getSqlMapClientTemplate().queryForList(
		"spusicc.fdv.ProcesoFDVClusterizacionSQL.getProcesosClusterByCriteria", criteria);
        return procesosCluster;
	}

	/** 
	 * 
	 * @see biz.belcorp.ssicc.spusicc.fdv.service.ProcesoFDVClusterizacionService#insertProcesoClusterizacion(
     * 		biz.belcorp.ssicc.model.ProcesoFDVClusterizacion,
     *      biz.belcorp.ssicc.model.Usuario)
     */
	public void insertProcesoClusterizacion(
			ProcesoFDVClusterizacion procesoFDVClusterizacion, Usuario usuario) throws DataIntegrityViolationException{

		getSqlMapClientTemplate().insert("spusicc.fdv.ProcesoFDVClusterizacionSQL.insertProcesoClusterizacion", 
		procesoFDVClusterizacion);
	}

	/**
     * 
     * @throws Exception 
	 * @see biz.belcorp.ssicc.spusicc.fdv.service.ProcesoFDVClusterizacionService#insertZonasFDV(java.util.List)
     */
	public void insertZonasFDV(final List zonasList, final String codProc, final boolean cleanData) throws Exception {
		
		try{
			
			getSqlMapClientTemplate().execute(new SqlMapClientCallback() {				
				public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
					
					int contador = 0;
					executor.startBatch();
					
					Iterator listIterator = zonasList.iterator();
					while (listIterator.hasNext()) {
						
						if(cleanData){
							if(contador == 0){
								// Eliminamos los datos cargados para este proceso anteriormente
								getSqlMapClientTemplate().delete(
								"spusicc.fdv.ProcesoFDVClusterizacionSQL.cleanZonasFDV", codProc);
							}
						}
						
						contador++;
						Map dataInsert = (Map) listIterator.next();
						getSqlMapClientTemplate().insert(
						"spusicc.fdv.ProcesoFDVClusterizacionSQL.insertZonasFDV",
						dataInsert);
					}
					
					int rowsaffected = executor.executeBatch();
					System.out.println("rows afftected by insertZonasFDV: "+rowsaffected);
					
					return null;
				}
			});
			
		  }catch(Exception e){
			  throw new Exception(e);
		  }
	}

	/** 
	 * 
	 * @see biz.belcorp.ssicc.spusicc.fdv.service.ProcesoFDVClusterizacionService#insertProcesoParametro(
     * 		biz.belcorp.ssicc.model.ProcesoFDVClusterizacion)
     */
	public void insertProcesoParametro(ProcesoFDVClusterizacion procesoFDVClusterizacion) throws DataIntegrityViolationException{
		
		getSqlMapClientTemplate().insert("spusicc.fdv.ProcesoFDVClusterizacionSQL.insertProcesoParametro", 
		procesoFDVClusterizacion);
	}
}
