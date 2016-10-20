package biz.belcorp.ssicc.dao.spusicc.mav.ibatis;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.mav.ProcesoMAVCargaMasivaDAO;

import com.ibatis.sqlmap.client.SqlMapExecutor;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
@Repository("spusicc.procesoMAVCargaMasivaDAO")
public class ProcesoMAVCargaMasivaDAOIbatis extends BaseDAOiBatis implements
	ProcesoMAVCargaMasivaDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.dao.ProcesoMAVCargaMasivaDAO#deleteCargaMasiva()
	 */
	public void deleteCargaMasiva() {
		getSqlMapClientTemplate().insert("spusicc.ProcesosMAVSQL.deleteCargaMasiva");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.dao.ProcesoMAVCargaMasivaDAO#insertCargaMasiva(java.util.Map)
	 */
	public void insertCargaMasiva(Map params) {
		getSqlMapClientTemplate().insert("spusicc.ProcesosMAVSQL.insertCargaMasiva", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.dao.ProcesoMAVCargaMasivaDAO#insertCargaMasivaBatch(java.util.List)
	 */
	public void insertCargaMasivaBatch(final List list) throws Exception {
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
					getSqlMapClientTemplate().insert("spusicc.ProcesosMAVSQL.insertCargaMasiva", dataInsert);
				}
				int rowsaffected = executor.executeBatch();
				logger.debug("Inicio->" + timestamp.toString()
						+ "Fin-->"
						+ new Timestamp(System.currentTimeMillis()));
				System.out.println("rows afftected by insertCargaMasiva: " + rowsaffected);
				return null;
			}
		});
			
		}catch(Exception e){
			throw new Exception(e);
		}
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.dao.ProcesoMAVCargaMasivaDAO#executeValidarCargaMasiva(java.util.Map)
	 */
	public void executeValidarCargaMasiva(Map params) {
		getSqlMapClientTemplate().update("spusicc.ProcesosMAVSQL.executeValidarCargaMasiva", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.dao.ProcesoMAVCargaMasivaDAO#getResumenCargaMasiva(java.util.Map)
	 */
	public List getResumenCargaMasiva(Map params) {
		 return getSqlMapClientTemplate().queryForList("spusicc.ProcesosMAVSQL.getResumenCargaMasiva", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.dao.ProcesoMAVCargaMasivaDAO#getErroresCargaMasiva(java.util.Map)
	 */
	public List getErroresCargaMasiva(Map params) {
		 return getSqlMapClientTemplate().queryForList("spusicc.ProcesosMAVSQL.getErroresCargaMasiva", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.dao.ProcesoMAVCargaMasivaDAO#executeActualizarCargaMasiva(java.util.Map)
	 */
	public void executeActualizarCargaMasiva(Map params) {
		getSqlMapClientTemplate().update("spusicc.ProcesosMAVSQL.executeActualizarCargaMasiva", params);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.dao.ProcesoMAVCargaMasivaDAO#getNumeroCarga()
	 */
	public String getNumeroCarga() {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosMAVSQL.getNumeroCarga");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.dao.ProcesoMAVCargaMasivaDAO#executeActualizarGerentesDirectorio(java.util.Map)
	 */
	public void executeActualizarGerentesDirectorio(Map params) {
		getSqlMapClientTemplate().update("spusicc.ProcesosMAVSQL.executeActualizarGerentesDirectorio", params);
	}
}

