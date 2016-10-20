package biz.belcorp.ssicc.dao.spusicc.fdv.ibatis;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.fdv.ProcesoFDVFileVariDAO;

import com.ibatis.sqlmap.client.SqlMapExecutor;

/**
 * <p>
 * <a href="ProcesoFDVFileVariDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:fayala@belcorp.biz">Frank Ayala</a>
 */
@Repository("spusicc.procesoFDVFileVariDAO")
public class ProcesoFDVFileVariDAOiBatis extends BaseDAOiBatis implements ProcesoFDVFileVariDAO{

	/**
     * 
     * @throws Exception 
	 * @see biz.belcorp.ssicc.spusicc.fdv.service.ProcesoFDVClusterizacionService#insertVariablesExogFDV(java.util.List)
     */
	public void insertVariablesExogFDV(final List varList, final String codProc, final boolean cleanData) throws Exception {
		
		try{
			
			getSqlMapClientTemplate().execute(new SqlMapClientCallback() {				
				public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
					
					int contador = 0;
					executor.startBatch();
					
					Iterator listIterator = varList.iterator();
					while (listIterator.hasNext()) {
						
						if(cleanData){
							if(contador == 0){
								// Eliminamos los datos cargados para este proceso anteriormente
								getSqlMapClientTemplate().delete(
								"spusicc.fdv.ProcesoFDVClusterizacionSQL.cleanVariablesExogFDV", codProc);
							}
						}
						
						contador++;
						Map dataInsert = (Map) listIterator.next();
						getSqlMapClientTemplate().insert(
						"spusicc.fdv.ProcesoFDVClusterizacionSQL.insertVariablesExogFDV",
						dataInsert);
					}
					
					int rowsaffected = executor.executeBatch();
					System.out.println("rows afftected by insertVariablesExogFDV: "+rowsaffected);
					
					return null;
				}
			});
			
		  }catch(Exception e){
			  throw new Exception(e);
		  }
	}
}
