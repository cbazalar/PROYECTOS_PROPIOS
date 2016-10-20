package biz.belcorp.ssicc.dao.soa.gen.ibatis;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.ocr.model.ConexionOCRWrapper;
import biz.belcorp.ssicc.dao.soa.gen.GenericoSOAGenericoDAO;

import com.ibatis.sqlmap.client.SqlMapExecutor;

/**
 * ImplementacionDAO correspondiente al OCR Comercial proveniente de SQL Server
 * 
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar</a>
 */
@Repository("soa.genericoSOASQLServerDAO")
public class GenericoSOASQLServerDAOiBatis extends BaseDAOiBatis
		implements GenericoSOAGenericoDAO {



	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.ocr.dao.gen.GenericoOCRComercialDAO#getListProcesoCargaCupon(biz.belcorp.ssicc.ocr.dao.model.ConexionOCRWrapper, java.util.Map)
	 */
	public List getListPedidosDatamart(ConexionOCRWrapper conexionOCRWrapper,
			Map params) throws Exception {
		//Connection connection = this.obtenerConexion(conexionOCRWrapper);

		List list= null;
		DriverManagerDataSource ds = new DriverManagerDataSource();
		try {
			
			ds.setDriverClassName("net.sourceforge.jtds.jdbc.Driver");
			ds.setUrl(conexionOCRWrapper.getHost());
			ds.setUsername(conexionOCRWrapper.getUsuario());
			ds.setPassword(conexionOCRWrapper.getPassword());
			setDataSource(ds);

			list = getSqlMapClientTemplate().queryForList(
						"soa.GenericoSOASQLServerSQL.getListPedidosDatamart",
						params);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} 
		finally {
			ds=null;
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.soa.dao.gen.GenericoSOAGenericoDAO#getFechaUltimaActualizacionDatamart(biz.belcorp.ssicc.ocr.dao.model.ConexionOCRWrapper)
	 */
	public Date getFechaUltimaActualizacionDatamart(ConexionOCRWrapper conexion,Map criteria) throws Exception {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		Date date=null;
		try {
			
			ds.setDriverClassName("net.sourceforge.jtds.jdbc.Driver");
			ds.setUrl(conexion.getHost());
			ds.setUsername(conexion.getUsuario());
			ds.setPassword(conexion.getPassword());
			setDataSource(ds);

			date = (Date)getSqlMapClientTemplate().queryForObject(
						"soa.GenericoSOASQLServerSQL.getFechaUltimaActualizacionDatamart",criteria);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} 
		finally {
			ds=null;
		}
		return date;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.soa.dao.gen.GenericoSOAGenericoDAO#updateFlagPedidosDatamart(biz.belcorp.ssicc.ocr.dao.model.ConexionOCRWrapper, java.util.Map)
	 */
	public void updateFlagPedidosDatamart(ConexionOCRWrapper conexion,
			Map criteria) throws Exception {
		DriverManagerDataSource ds = new DriverManagerDataSource();
			try {
			
			ds.setDriverClassName("net.sourceforge.jtds.jdbc.Driver");
			ds.setUrl(conexion.getHost());
			ds.setUsername(conexion.getUsuario());
			ds.setPassword(conexion.getPassword());
			setDataSource(ds);

			getSqlMapClientTemplate().update(
						"soa.GenericoSOASQLServerSQL.updateFlagPedidosDatamart",criteria);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} 
		finally {
			ds=null;
		}		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.soa.dao.gen.GenericoSOAGenericoDAO#getListIndicadoresDatamart(biz.belcorp.ssicc.ocr.dao.model.ConexionOCRWrapper, java.util.Map)
	 */
	public List getListIndicadoresDatamart(ConexionOCRWrapper conexionOCRWrapper, Map params) throws Exception {
		//Connection connection = this.obtenerConexion(conexionOCRWrapper);

		List list= null;
		DriverManagerDataSource ds = new DriverManagerDataSource();
		try {
			
			ds.setDriverClassName("net.sourceforge.jtds.jdbc.Driver");
			ds.setUrl(conexionOCRWrapper.getHost());
			ds.setUsername(conexionOCRWrapper.getUsuario());
			ds.setPassword(conexionOCRWrapper.getPassword());
			setDataSource(ds);

			list = getSqlMapClientTemplate().queryForList(
						"soa.GenericoSOASQLServerSQL.getListIndicadoresDatamart",
						params);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} 
		finally {
			ds=null;
		}
		return list;
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.soa.dao.gen.GenericoSOAGenericoDAO#updateFlagPedidosDatamart(java.util.List, biz.belcorp.ssicc.ocr.dao.model.ConexionOCRWrapper, java.util.Map)
	 */
	public void updateFlagPedidosDatamart(final List list,
			ConexionOCRWrapper conexion, Map criteria) throws Exception{
		DriverManagerDataSource ds = new DriverManagerDataSource();
		try {
		
		ds.setDriverClassName("net.sourceforge.jtds.jdbc.Driver");
		ds.setUrl(conexion.getHost());
		ds.setUsername(conexion.getUsuario());
		ds.setPassword(conexion.getPassword());
		setDataSource(ds);

		log.debug("actualizando datamart");
		
		final Timestamp timestamp = new Timestamp(System
				.currentTimeMillis());	

				  getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
						public Object doInSqlMapClient(SqlMapExecutor executor)	throws SQLException {
							//OBTENEMOS EL OID D EPROCESO
							//Long oidProceso = (Long)getSqlMapClientTemplate().queryForObject("soa.PedidoSQL.getOidProcesoDatamart");
							//criteria.put("oidProceso", oidProceso);
							
							 executor.startBatch();
							 Iterator listIterator = list.iterator();
								while (listIterator.hasNext()) {
									Map dataInsert = (Map) listIterator.next();
									//dataInsert.put("oidProceso", oidProceso);
									getSqlMapClientTemplate().update(
											"soa.GenericoSOASQLServerSQL.updateFlagPedidosDatamart",dataInsert);
								}
								executor.executeBatch();
							return null;
							}
						});							  
					logger.debug("Inicio->" + timestamp.toString()
							+ "Fin-->"
							+ new Timestamp(System.currentTimeMillis()));			
		
	} catch (Exception e) {
		throw new Exception(e.getMessage());
	} 
	finally {
		ds=null;
	}		
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.soa.dao.gen.GenericoSOAGenericoDAO#getNumpaginas(biz.belcorp.ssicc.ocr.dao.model.ConexionOCRWrapper, java.util.Map)
	 */
	public int getNumpaginas(ConexionOCRWrapper conexionOCRWrapper, Map criteria) {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		int result=0;
		try {
			
			ds.setDriverClassName("net.sourceforge.jtds.jdbc.Driver");
			ds.setUrl(conexionOCRWrapper.getHost());
			ds.setUsername(conexionOCRWrapper.getUsuario());
			ds.setPassword(conexionOCRWrapper.getPassword());
			setDataSource(ds);

			result = (Integer)getSqlMapClientTemplate().queryForObject(
						"soa.GenericoSOASQLServerSQL.getNumpaginas",
						criteria);
		} 
		finally {
			ds=null;
		}
		return result;
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.soa.dao.gen.GenericoSOAGenericoDAO#getObtenerPedidosWeb(biz.belcorp.ssicc.ocr.dao.model.ConexionOCRWrapper, java.util.Map)
	 */
	public List getPedidosWeb(ConexionOCRWrapper conexionOCRWrapper,
			Map criteria) throws Exception {
		List list= null;
		DriverManagerDataSource ds = new DriverManagerDataSource();
		try {
			
			ds.setDriverClassName("net.sourceforge.jtds.jdbc.Driver");
			ds.setUrl(conexionOCRWrapper.getHost());
			ds.setUsername(conexionOCRWrapper.getUsuario());
			ds.setPassword(conexionOCRWrapper.getPassword());
			setDataSource(ds);

			list = getSqlMapClientTemplate().queryForList(
						"soa.GenericoSOASQLServerSQL.getPedidosWeb",criteria);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} 
		finally {
			ds=null;
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.soa.dao.gen.GenericoSOAGenericoDAO#getDetallePedidoWeb(biz.belcorp.ssicc.ocr.dao.model.ConexionOCRWrapper, java.util.Map)
	 */
	public List getDetallePedidoWeb(ConexionOCRWrapper conexionOCRWrapper,
			Map criteria) throws Exception {
		List list= null;
		DriverManagerDataSource ds = new DriverManagerDataSource();
		try {
			
			ds.setDriverClassName("net.sourceforge.jtds.jdbc.Driver");
			ds.setUrl(conexionOCRWrapper.getHost());
			ds.setUsername(conexionOCRWrapper.getUsuario());
			ds.setPassword(conexionOCRWrapper.getPassword());
			setDataSource(ds);

			list = getSqlMapClientTemplate().queryForList(
						"soa.GenericoSOASQLServerSQL.getDetallePedidoWeb",criteria);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} 
		finally {
			ds=null;
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.soa.dao.gen.GenericoSOAGenericoDAO#getConsultoraDatamart(biz.belcorp.ssicc.ocr.dao.model.ConexionOCRWrapper, java.util.Map)
	 */
	public Map getConsultoraDatamart(ConexionOCRWrapper conexion, Map criteria) {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		Map result=null;
		try {
			if(!conexion.isOpen()){
				ds.setDriverClassName("net.sourceforge.jtds.jdbc.Driver");
				ds.setUrl(conexion.getHost());
				ds.setUsername(conexion.getUsuario());
				ds.setPassword(conexion.getPassword());
				setDataSource(ds);
				conexion.setOpen(true);
				conexion.setDs(ds);
		     }

			result = (Map)getSqlMapClientTemplate().queryForObject(
						"soa.GenericoSOASQLServerSQL.getConsultoraDatamart",
						criteria);
		} 
		finally {
			ds=null;
		}
		return result;
	}
	
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.soa.dao.gen.GenericoSOAGenericoDAO#getConsultoraDatamart(biz.belcorp.ssicc.ocr.dao.model.ConexionOCRWrapper, java.util.Map, java.util.List)
	 */
	public List getConsultoraDatamart(ConexionOCRWrapper conexion, Map criteria,final List list, final String codigoNivelConsultora, final String codigoPais){
		DriverManagerDataSource ds = new DriverManagerDataSource();
		final List listDatamart = new ArrayList();
		try {
			if(!conexion.isOpen()){
				ds.setDriverClassName("net.sourceforge.jtds.jdbc.Driver");
				ds.setUrl(conexion.getHost());
				ds.setUsername(conexion.getUsuario());
				ds.setPassword(conexion.getPassword());
				setDataSource(ds);
				conexion.setOpen(true);
				conexion.setDs(ds);
		     }
			
			int cantidadList = Constants.MAX_VALUE_OF_LIST_PERMIT;
			int numGrupos = (list.size()/ cantidadList) + 1 ;	
			int inicio = 0;
			int fin = 0;
			
			for(int j=0; j< numGrupos ; j++){
				fin = inicio + cantidadList -1 ;
				if(list.size() < fin) fin = list.size();
				
				List sublst = list.subList(inicio, fin);
				
				//logica del mtodo
				
			int i=0;
			String [] arrCod = new String[sublst.size()];
			Iterator it = sublst.iterator();	
			String codCampania=null;
			while(it.hasNext()){
				Map obj = (Map)it.next();
				arrCod[i] = (String)obj.get("codigoCliente");
				if(i==0) codCampania =  (String)obj.get("codigoCampania");
				i++;
			}
			
			criteria.put("codigoPais", codigoPais);
			criteria.put("listCodCliente", arrCod);
			criteria.put("codigoCampania", codCampania);
			criteria.put("codigoNivelConsultora",codigoNivelConsultora);
			
			 List listDM = (List) getSqlMapClientTemplate().queryForList(
					"soa.GenericoSOASQLServerSQL.getListConsultoraDatamart",
					criteria);
			 
			 it = sublst.iterator();	
			
				while(it.hasNext()){
					Map obj = (Map)it.next();	
					String codCliente = (String)obj.get("codigoCliente");
					 Iterator itDM = listDM.iterator();
					while(itDM.hasNext()){
						Map objDM = (Map)itDM.next();	
						String codClienteDM = (String)objDM.get("codigoCliente");
						if(codCliente.equals(codClienteDM))
						{
							String codigoSegmento = objDM!=null?String.valueOf(objDM.get("codigoSegmento")):"";
							String nombreNivelConsultoraDatamart = objDM!=null?(String)objDM.get("nombreSegmento"):"";
							String pegDatamart = objDM!=null?String.valueOf(objDM.get("flagPeg")):"";
							
							obj.put("codigoSegmento", codigoSegmento);
							obj.put("nombreSegmento", nombreNivelConsultoraDatamart);
							obj.put("pegDatamart", pegDatamart);
				            listDatamart.add(obj);
				            
				            break;
						}
					}
					
				}
			
				
				//
				inicio = fin + 1;				
			}
			
//			getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
//				public Object doInSqlMapClient(SqlMapExecutor executor)
//						throws SQLException {
//					executor.startBatch();
//					Iterator listIterator = list.iterator();
//					while (listIterator.hasNext()) {
//						Map dataList = (Map) listIterator.next();
//
//						String codCliente = (String)dataList.get("codigoCliente");
//						String codCampania = (String)dataList.get("codigoCampania");
//						Map criteria = new HashMap();
//						criteria.put("codigoPais", codigoPais);
//						criteria.put("codCliente", codCliente);
//						criteria.put("codigoCampania", codCampania);
//						
//						 Map od = (Map) getSqlMapClientTemplate().queryForObject(
//								"soa.GenericoSOASQLServerSQL.getConsultoraDatamart",
//								criteria);
//						
//						String codigoSegmento = od!=null?String.valueOf(od.get("codigoSegmento")):"";
//						String nombreNivelConsultoraDatamart = od!=null?(String)od.get("nombreSegmento"):"";
//						//
//			          	if(!StringUtils.equals(codigoNivelConsultora, codigoSegmento)){
//			            		continue;
//			            	}
//			          	dataList.put("codigoSegmento", codigoSegmento);
//			          	dataList.put("nombreSegmento", nombreNivelConsultoraDatamart);
//			            listDatamart.add(dataList);
//					}	
//					return null;
//				}
//			});	
		}	
		finally {
			ds=null;
		}
		log.debug("Lista Datamart size: " + listDatamart.size());
		return listDatamart;
		
	}
	
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.soa.dao.gen.GenericoSOAGenericoDAO#getConsultoraSicc(biz.belcorp.ssicc.ocr.dao.model.ConexionOCRWrapper, java.util.Map, java.util.List, java.lang.String, java.lang.String)
	 */
	public List getConsultoraSicc(ConexionOCRWrapper conexion, Map criteria,final List list, final String codigoPais){
		DriverManagerDataSource ds = new DriverManagerDataSource();
		try {
			if(!conexion.isOpen()){
				ds.setDriverClassName("net.sourceforge.jtds.jdbc.Driver");
				ds.setUrl(conexion.getHost());
				ds.setUsername(conexion.getUsuario());
				ds.setPassword(conexion.getPassword());
				setDataSource(ds);
				conexion.setOpen(true);
				conexion.setDs(ds);
		     }
			
			int cantidadList = Constants.MAX_VALUE_OF_LIST_PERMIT;
			int numGrupos = (list.size()/ cantidadList) + 1 ;	
			int inicio = 0;
			int fin = 0;
			
			for(int j=0; j< numGrupos ; j++){
				fin = inicio + cantidadList -1 ;
				if(list.size() < fin) fin = list.size();
				
				List sublst = list.subList(inicio, fin);
				
				//logica del mtodo
				
			int i=0;
			String [] arrCod = new String[sublst.size()];
			Iterator it = sublst.iterator();	
			String codCampania=null;
			while(it.hasNext()){
				Map obj = (Map)it.next();
				arrCod[i] = (String)obj.get("codigoCliente");
				if(i==0) codCampania =  (String)obj.get("codigoCampania");
				i++;
			}
			
			criteria.put("codigoPais", codigoPais);
			criteria.put("listCodCliente", arrCod);
			criteria.put("codigoCampania", codCampania);
			
			 List listDM = (List) getSqlMapClientTemplate().queryForList(
					"soa.GenericoSOASQLServerSQL.getListConsultoraDatamart",
					criteria);
			 
			 it = sublst.iterator();	
			 int index = 0;
				while(it.hasNext()){
					Map obj = (Map)it.next();	
					String codCliente = (String)obj.get("codigoCliente");
					 Iterator itDM = listDM.iterator();
					 
					while(itDM.hasNext()){
						Map objDM = (Map)itDM.next();	
						String codClienteDM = (String)objDM.get("codigoCliente");
						if(codCliente.equals(codClienteDM))
						{
							String codigoSegmento = objDM!=null?String.valueOf(objDM.get("codigoSegmento")):"";
							String nombreNivelConsultoraDatamart = objDM!=null?(String)objDM.get("nombreSegmento"):"";
							String pegDatamart = objDM!=null?String.valueOf(objDM.get("flagPeg")):"";
							
							obj.put("codigoSegmento", codigoSegmento);
							obj.put("nombreSegmento", nombreNivelConsultoraDatamart);
							obj.put("pegDatamart", pegDatamart);
							list.set(index, obj);
							
				            break;
						}
					}
					index++;
				}
				//
				inicio = fin + 1;				
			}

		}	
		finally {
			ds=null;
		}
		log.debug("Lista Ssicc size: " + list.size());
		return list;
		
	}

	public int updatePedidoWeb(ConexionOCRWrapper conexionOCRWrapper, Map criteria) {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		int result=0;
		try {
			
			ds.setDriverClassName("net.sourceforge.jtds.jdbc.Driver");
			ds.setUrl(conexionOCRWrapper.getHost());
			ds.setUsername(conexionOCRWrapper.getUsuario());
			ds.setPassword(conexionOCRWrapper.getPassword());
			setDataSource(ds);

			result = (Integer)getSqlMapClientTemplate().update(
						"soa.GenericoSOASQLServerSQL.updatePedidoWeb",
						criteria);
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		finally {
			ds=null;
		}
		return result;
	}	
}
