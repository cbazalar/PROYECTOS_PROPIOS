package biz.belcorp.ssicc.dao.spusicc.comision.ibatis;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.comision.ProcesoCOMCargaCuentasDetraccionDAO;

import com.ibatis.sqlmap.client.SqlMapExecutor;


/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
@Repository("spusicc.procesoCOMCargaCuentasDetraccionDAO")
public class ProcesoCOMCargaCuentasDetraccionDAOiBatis extends BaseDAOiBatis 
    implements ProcesoCOMCargaCuentasDetraccionDAO {
	
	public boolean isValidaEjecutiva(String codigoCliente) {
		String contador = (String) getSqlMapClientTemplate().queryForObject(
							"spusicc.comision.mantenimientoCOMSQL.getValidaEjecutiva", codigoCliente);
		
		if(contador.equals("0"))
			return false;
		else
			return true;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.ProcesoCOMCargaBonosDAO#insertListDetalleBonos(java.util.List)
	 */
	public void updateListCuentasDetraccion(final List list) throws Exception {
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
									"spusicc.comision.mantenimientoCOMSQL.updateCuentaDetraccion",
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
