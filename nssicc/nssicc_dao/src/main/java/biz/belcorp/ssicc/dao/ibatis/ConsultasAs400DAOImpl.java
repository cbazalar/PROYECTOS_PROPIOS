 package biz.belcorp.ssicc.dao.ibatis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.ConsultasAs400DAO;
import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.framework.util.CustomerContextHolder;
import biz.belcorp.ssicc.dao.framework.util.TypesafeEnum;
import biz.belcorp.ssicc.dao.scsicc.bean.Data400Bean;

import com.ibatis.sqlmap.client.SqlMapExecutor;

@Repository("scsicc.consultasAs400DAO")
public class ConsultasAs400DAOImpl extends BaseDAOiBatis implements ConsultasAs400DAO {

 
	private static Logger logger = Logger.getLogger(ConsultasAs400DAOImpl.class.getName());
	
	ResultSet rs_datos = null; 
	
	Connection conn = null;
	ArrayList datosArray = new ArrayList();
	PreparedStatement stmt = null;
	
	
	public void getProceso400ActuByCriteria(String user,String password) throws Exception
	{
		try {
			try {
				 //this.jdbcTemplate = new JdbcTemplate(getDataSource());
				//conn=this.getJdbcTemplate().getDataSource().getConnection();
				conn=  getDataSource().getConnection();
			} catch (SQLException e) {
				logger.error("Error en ConsultasAs400DAOImpl-getProceso400ActuByCriteria al conectarse AS/400 : --> " + e.getMessage());
			}
			StringBuffer query = new StringBuffer();
			query.append(" select aconrocta,acocodreg,acocodzon,acocmping,acodscpra,acodscsea,	acodscprn,acodscsen,acotel1co,");
			query.append(" acovlrsac,acoindsic,acoanonac,acomesnac,acodianac,acocmpulp,	acoindmor , digits(acodianac)||'/'||digits(acomesnac)|| '/' || digits(acoanonac) as fecha ");
			query.append(" FROM LIBROQUE.accons where acoindsic = ? and acocmping = ? ");
			logger.info(query.toString());
			stmt = conn.prepareStatement(query.toString());
			stmt.setString(1, "1");			
			stmt.setString(2, "200708");
			rs_datos = stmt.executeQuery();
			while (rs_datos.next ()) {
				System.out.println("-------------  "+ 
						rs_datos.getString("aconrocta")+" , " +       
						rs_datos.getString("acocodreg")+" ,  "+       
						rs_datos.getString("acocodzon")+" ,  "+       
						rs_datos.getString("acodscpra")+" ,  "+       
						rs_datos.getString("acodscsea")+" ,  "+       
						rs_datos.getString("acodscprn")+" ,  "+       
						rs_datos.getString("acodscsen")+" ,  "+       
						rs_datos.getString("aconrocta")+" ,  "+
						rs_datos.getString("fecha")	   +" ,  "+            	
						rs_datos.getString("acotel1co")+" ,  "+       
						rs_datos.getString("acocmping")+" ,  "+       
						rs_datos.getString("acocmpulp")+" ,  "+       
						rs_datos.getString("acocmpulp")+" ,  "+       
						rs_datos.getString("acocmpulp")+" ,  "+       
						rs_datos.getString("acoindmor")+" ,  "+       
						rs_datos.getString("acovlrsac")+" ,  "+       
						rs_datos.getString("acoindsic")+" ------------  ");       
			}
		} catch (Exception e) {
			logger.error("QueryException ConsultasAs400DAOImpl-getProceso400ActuByCriteria - : " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				rs_datos.close();
				conn.close();
			} catch (SQLException e) {
				
			}
		}

	}
	
	public List getProcesoConsulta400Ibatis(Map criteria)
	{
		CustomerContextHolder.setCustomerType(TypesafeEnum.CO);
		List datos400=getSqlMapClientTemplate().queryForList("AccesoAS400SQL.getConsulta400", criteria);
		CustomerContextHolder.clearCustomerType();
		return datos400;

}
	public List getProcesoConsulta400IbatisMap(Map criteria)
	{
		CustomerContextHolder.setCustomerType(TypesafeEnum.CO);
		List datos400=getSqlMapClientTemplate().queryForList("AccesoAS400SQL.getConsulta400Map", criteria);
		CustomerContextHolder.clearCustomerType();
		return datos400;
	} 
		
	public void insertarProceso400IbatisMap(final List lista)
	{
		try {
		CustomerContextHolder.setCustomerType(TypesafeEnum.CO);
		CustomerContextHolder.clearCustomerType();
		//List datos400=getSqlMapClientTemplate().queryForList("AccesoAS400SQL.getConsulta400Map", criteria);
	    Iterator listIterator = lista.iterator();
	//    getSqlMapClient().setUserConnection(getDataSource().getConnection());
	    while (listIterator.hasNext()) {
	    	Data400Bean dataInsert=(Data400Bean)listIterator.next();
	    	System.out.println(dataInsert.getCodSac());
	       
			getSqlMapClient().insert("edu.MantenimientoEDUSQL.insertConsulta400",dataInsert);
	    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	    }
		
	} 

	public void insertarProcesoConsulta400Ibatis(final Map criteria)
	{
		
		try{
			final List list =getProcesoConsulta400Ibatis(criteria);
			final Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//			CustomerContextHolder.setCustomerType(TypesafeEnum.AS400);
//			insertarProceso400IbatisMap(list);
			
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				executor.startBatch();
				Iterator listIterator = list.iterator();
			    while (listIterator.hasNext()) {
			    	Date fecha = null ;
			        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
			    	Map dataInsert=(Map)listIterator.next();
			    	dataInsert.put("codigoEmpresa",(String)criteria.get("codigoEmpresa"));
			    	dataInsert.put("codigoPais", (String)criteria.get("codigoPais"));
			        try {
			            fecha = df.parse((String)dataInsert.get("codFecha"));
			        } catch (ParseException pe) {
			            //log.error("ParseException: " + pe);
			        	fecha = null;
			        }
					dataInsert.put("codFecha",fecha);	
			    	System.out.println(dataInsert);
			        getSqlMapClientTemplate().insert("AccesoAS400SQL.insertConsulta400",dataInsert);
			}
			int rowsaffected = executor.executeBatch();
//			CustomerContextHolder.clearCustomerType();
			System.out.println("Inicio->"+timestamp.toString()+"Fin-->"+  new Timestamp(System.currentTimeMillis()));			
			logger.info("rows afftected by insertUserRolesById: " + rowsaffected);
			return null;
			}
			}) ;
			
		}catch(Exception e)
		{
			logger.debug("Error ConsultasAs400DAOImpl--insertarProcesoConsulta400Ibatis : " + e.getMessage());
		}

	}
	
	
}
