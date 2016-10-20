package biz.belcorp.ssicc.dao.soa.ibatis;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.soa.PedidoDAO;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.ibatis.sqlmap.client.SqlMapSession;

@Repository("soa.pedidoDAO")
public class PedidoDAOiBatis extends BaseDAOiBatis implements PedidoDAO{
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.soa.dao.PedidoDAO#getInformePedidos(java.util.Map)
	 */
	public List getInformePedidos (Map criteria){
		return (List)getSqlMapClientTemplate().queryForList("soa.PedidoSQL.getInformePedidos", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.soa.dao.PedidoDAO#getResumenPedidos(java.util.Map)
	 */
	public List getResumenPedidos (Map criteria){
		return (List)getSqlMapClientTemplate().queryForList("soa.PedidoSQL.getResumenPedidos", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.soa.dao.PedidoDAO#getConsolidaPedidos(java.util.List, java.util.Map)
	 */
	public List getConsolidaPedidos(List list, Map criteria){
		
		//insertPedidosDatamart(list,criteria);				
		//getSqlMapClientTemplate().update("soa.PedidoSQL.executeProcesoConsoInformePedidos", criteria);
		return (List)getSqlMapClientTemplate().queryForList("soa.PedidoSQL.getInformePedidos", criteria);
	}

	public void insertPedidosDatamart(final List list,final Map criteria) throws Exception {		
		
		logger.debug("insert pedidos datamar");
		final String usuario = (String)criteria.get("usuario");
		final Timestamp timestamp = new Timestamp(System
				.currentTimeMillis());
		SqlMapSession session=null;
		 try{
			        
//			      session = getSqlMapClientTemplate().getSqlMapClient().openSession();
//			      session.startTransaction();
				  getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
						public Object doInSqlMapClient(SqlMapExecutor executor)	throws SQLException {
							//OBTENEMOS EL OID D EPROCESO
							//Long oidProceso = (Long)getSqlMapClientTemplate().queryForObject("soa.PedidoSQL.getOidProcesoDatamart");
							//criteria.put("oidProceso", oidProceso);
							
							 executor.startBatch();
							 Iterator listIterator = list.iterator();
								while (listIterator.hasNext()) {
									Map dataInsert = (Map) listIterator.next();
									dataInsert.put("usuario", usuario);
									Integer count = getPedidoDatam(dataInsert);
									   if(count == 0 ){
										   insertPedidoDatam(dataInsert);
									   }else{
										   updatePedidoDatam(dataInsert);
									   }									
//									getSqlMapClientTemplate()
//											.insert(
//													"soa.PedidoSQL.insertPedidosDatamart",
//													dataInsert);
								}
								executor.executeBatch();
							return null;
							}
						});				  
				   updateFechaUltimaActualizacion(criteria);
					logger.debug("Inicio->" + timestamp.toString()
							+ "Fin-->"
							+ new Timestamp(System.currentTimeMillis()));
			//		session.commitTransaction();	
		 }catch(Exception e){
			  e.printStackTrace();
		 }
//		  }finally{
//			  try {
//				     session.endTransaction();
//				   } finally {
//				     session.close();
//				   }
//		  }			
//										  
							  
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.soa.dao.PedidoDAO#getDetalleInformePedidos(java.util.Map)
	 */
	public List getDetalleInformePedidos(Map criteria) {
		return (List)getSqlMapClientTemplate().queryForList("soa.PedidoSQL.getDetalleInformePedidos", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.soa.dao.PedidoDAO#getFechaUltimaActualizacion()
	 */
	public Date getFechaUltimaActualizacion() {
		return (Date)getSqlMapClientTemplate().queryForObject("soa.PedidoSQL.getFechaUltimaActualizacion");
	}

	public Integer getPedidoDatam(Map map) {
		return (Integer)getSqlMapClientTemplate().queryForObject("soa.PedidoSQL.getPedidoDatam",map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.soa.dao.PedidoDAO#insertPedidoDatam(java.util.Map)
	 */
	public void insertPedidoDatam(Map map) {
		getSqlMapClientTemplate()
		.insert(
				"soa.PedidoSQL.insertPedidosDatamart",
				map);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.soa.dao.PedidoDAO#updatePedidoDatam(java.util.Map)
	 */
	public void updatePedidoDatam(Map map) {
		getSqlMapClientTemplate()
		.update(
				"soa.PedidoSQL.updatePedidosDatamart",
				map);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.soa.dao.PedidoDAO#updateFechaUltimaActualizacion()
	 */
	public void updateFechaUltimaActualizacion(Map map) throws Exception {
	  try{	
		getSqlMapClientTemplate()
		.update(
				"soa.PedidoSQL.updateFechaUltimaActualizacion",map);

	  }catch(Exception e){
		  e.printStackTrace();
	  }finally{
	  }
	}
	
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.soa.dao.PedidoDAO#getMoneda(java.util.Map)
	 */
	public List getMoneda(Map criteria) {
		return getSqlMapClientTemplate().queryForList("soa.PedidoSQL.getMoneda", criteria);
	}

	
}
