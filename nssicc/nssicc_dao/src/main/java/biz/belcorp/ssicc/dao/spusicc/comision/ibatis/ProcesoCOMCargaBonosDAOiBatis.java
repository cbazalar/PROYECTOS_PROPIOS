package biz.belcorp.ssicc.dao.spusicc.comision.ibatis;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.comision.ProcesoCOMCargaBonosDAO;
import biz.belcorp.ssicc.dao.spusicc.comision.model.DetalleBonos;

import com.ibatis.sqlmap.client.SqlMapExecutor;


/**
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli</a>
 *
 */
@Repository("spusicc.procesoCOMCargaBonosDAO")
public class ProcesoCOMCargaBonosDAOiBatis extends BaseDAOiBatis 
    implements ProcesoCOMCargaBonosDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.ProcesoCOMCargaBonosDAO#deleteDetalleBonos(biz.belcorp.ssicc.spusicc.comision.dao.model.DetalleBonos)
	 */
	public void deleteDetalleBonos(DetalleBonos detalle) {
		this.getSqlMapClientTemplate().delete("spusicc.comision.mantenimientoCOMSQL.deleteDetalleBonos",detalle);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.ProcesoCOMCargaBonosDAO#insertListDetalleBonos(java.util.List)
	 */
	public void insertListDetalleBonos(final List list) throws Exception {
	  try{	
		final Timestamp timestamp = new Timestamp(System
				.currentTimeMillis());
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
									"spusicc.comision.mantenimientoCOMSQL.insertListDetalleBonos",
									dataInsert);
				}
				int rowsaffected = executor.executeBatch();
				logger.debug("Inicio->" + timestamp.toString()
						+ "Fin-->"
						+ new Timestamp(System.currentTimeMillis()));
				System.out
						.println("rows afftected by insertListDetalleBonos: "
								+ rowsaffected);
				return null;
			}
		});
	  }catch(Exception e){
		  throw new Exception(e);
	  }
		
	}
	

}
