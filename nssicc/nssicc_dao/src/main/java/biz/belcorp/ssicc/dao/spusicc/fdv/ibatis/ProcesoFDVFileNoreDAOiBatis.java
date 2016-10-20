package biz.belcorp.ssicc.dao.spusicc.fdv.ibatis;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.fdv.ProcesoFDVFileNoreDAO;

import com.ibatis.sqlmap.client.SqlMapExecutor;

/**
 * <p>
 * <a href="ProcesoFDVFileNoreDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:fayala@belcorp.biz">Frank Ayala</a>
 */
@Repository("spusicc.procesoFDVFileNoreDAO")
public class ProcesoFDVFileNoreDAOiBatis extends BaseDAOiBatis implements ProcesoFDVFileNoreDAO{

	/**
     * 
     * @throws Exception 
	 * @see biz.belcorp.ssicc.spusicc.fdv.service.ProcesoFDVFileNoreService#insertNoReconstruidaFDV(java.util.List)
     */
	public void insertNoReconstruidaFDV(final List noreList, final String codProc,
			final boolean cleanData) throws Exception{

		try{
			
			getSqlMapClientTemplate().execute(new SqlMapClientCallback() {				
				public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
					
					int contador = 0;
					executor.startBatch();
					
					Iterator listIterator = noreList.iterator();
					while (listIterator.hasNext()) {
						
						if(cleanData){
							if(contador == 0){
								// Eliminamos los datos cargados para este proceso anteriormente
								getSqlMapClientTemplate().delete(
								"spusicc.fdv.ProcesoFDVClusterizacionSQL.cleanNoReconstruidaFDV", codProc);
							}
						}
						
						contador++;
						Map dataInsert = (Map) listIterator.next();
						getSqlMapClientTemplate().insert(
						"spusicc.fdv.ProcesoFDVClusterizacionSQL.insertNoReconstruidaFDV",
						dataInsert);
					}
					
					int rowsaffected = executor.executeBatch();
					System.out.println("rows afftected by insertNoReconstruidaFDV: "+rowsaffected);
					
					return null;
				}
			});
			
		  }catch(Exception e){
			  throw new Exception(e);
		  }
	}

	
}
