package biz.belcorp.ssicc.dao.edu.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.edu.MantenimientoEDUMultiEntidadDAO;
import biz.belcorp.ssicc.dao.edu.model.EntidadGenerico;
import biz.belcorp.ssicc.dao.jdbc.BaseDAOJdbc;
import biz.belcorp.ssicc.dao.model.Usuario;
/**
 * TODO Include class description here.
 * <p>
 * <a href="MantenimientoEDUMultiEntidadDAOJdbc.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli </a>
 */
public class MantenimientoEDUMultiEntidadDAOJdbc extends
	BaseDAOJdbc implements MantenimientoEDUMultiEntidadDAO{

	private static final Log logger = LogFactory.getLog(MantenimientoEDUMultiEntidadDAOJdbc.class);
	
	
	public List getMultiEntidadByCriteria(EntidadGenerico entidadGenerico) {
		logger.debug("inicio getMultiEntidadByCriteria");
		List list = new ArrayList();
		try{
			List listColumnas=getListColumnas(entidadGenerico);
			
			String sql=getQueryMultiEntidadByCriteria(entidadGenerico,listColumnas);
			ResultSetExtractor extractor = getExtractorMultiEntidad(entidadGenerico,listColumnas);
			
	        list =(List) getJdbcTemplate().query(sql,extractor);
		    logger.debug("fin getMultiEntidadByCriteria");
		}catch(Exception e){
			logger.error("error getMultiEntidadByCriteria "+e.getMessage());
			
		}
		
		return list;
	}
	
	private ResultSetExtractor getExtractorMultiEntidad(final EntidadGenerico entidadGenerico, final List listColumnas ) {
		return new ResultSetExtractor() {
	        public Object extractData(ResultSet resultSet)
	            throws SQLException, DataAccessException {
	            List resultList = new ArrayList();
	            while (resultSet.next()) {
	            	int i=1;
	                EntidadGenerico entidad = new EntidadGenerico();
	                entidad.setNombreEntidad(entidadGenerico.getNombreEntidad());
	                entidad.setDescripcionEntidad(entidadGenerico.getDescripcionEntidad());
	                entidad.setCodigo(resultSet.getString(i++));
	                entidad.setDescripcion(resultSet.getString(i++));
	                if(listColumnas.size()>2)//solo si se escapa del esquema
	                	entidad.setCodigoTipo(resultSet.getString(i++));
	                resultList.add(entidad);
	            }
	            return resultList;
	        }
	    };
	}

	public void updateEstadoMultiEntidad(EntidadGenerico entidadGenerico, final Usuario usuario) {
		logger.debug("updateEstadoMultiEntidad");
		
		try{
			
			List listColumnas=getListColumnas(entidadGenerico);
			String sql=getQueryUpdateEstadoEntidad(entidadGenerico,listColumnas);
			logger.debug("query update estado  "+sql);
			
			int row=getJdbcTemplate().update(sql,
					new PreparedStatementSetter() {
					 public void setValues(PreparedStatement pre) throws SQLException {
						 pre.setString(1,Constants.ESTADO_INACTIVO);
				         pre.setString(2,usuario.getLogin());   
					 }                     
					});
            
            logger.debug("("+row+") filas  afectadas");
		}catch(Exception e){
			logger.error("error getMultiEntidadByCriteria "+e.getMessage());
			
		}
	}

	public String updateMultiEntidad(final EntidadGenerico entidadGenerico, final Usuario usuario) {
		logger.debug("updateMultiEntidad");
	
		String cadError="";
		try{
			if(isEntidadEspecial(entidadGenerico)) {
				updateEntidadEspecial(entidadGenerico,usuario);
				return cadError;
			}
			List listColumnas=getListColumnas(entidadGenerico);
			String sql=getQueryUpdateEntidad(entidadGenerico,listColumnas);
			logger.debug("query update ENTIDAD  "+sql);
			
			int row=getJdbcTemplate().update(sql,
					new PreparedStatementSetter() {
					 public void setValues(PreparedStatement pre) throws SQLException {
						 pre.setString(1,entidadGenerico.getDescripcion());
				         pre.setString(2,usuario.getLogin()); 
					 }                     
					});
			
            logger.debug("("+row+") filas  afectadas");
		    
		}catch(Exception e){
			logger.error("error getMultiEntidadByCriteria "+e.getMessage());
			
			cadError=e.getMessage();
		}
		return cadError;
	}

	private void updateEntidadEspecial(final EntidadGenerico entidadGenerico,final Usuario usuario) {
		if(entidadGenerico.getNombreEntidad().equals(Constants.EDU_TABLA_EDU_ESTAD_CAPAC)){
			String sql=
				"UPDATE EDU_ESTAD_CAPAC SET DES_ESTA_CAPA=? , IND_ESTA_CAPA=?, USU_MODI=?, FEC_MODI=SYSDATE "+
				"WHERE COD_ESTA_CAPA = ?";
			log.debug("sql esp "+sql+ " cod "+entidadGenerico.getCodigo());
			int row=getJdbcTemplate().update(sql,
					new PreparedStatementSetter() {
					 public void setValues(PreparedStatement pre) throws SQLException {
						 int i=1;
				            
				            pre.setString(i++,entidadGenerico.getDescripcion());
				            pre.setString(i++,entidadGenerico.getIndicadorEstado());
				            pre.setString(i++,usuario.getLogin());
				            pre.setString(i++,entidadGenerico.getCodigo());
				            
					 }                     
					});
          
            logger.debug("("+row+") filas  afectadas");
			
		}
		if( entidadGenerico.getNombreEntidad().equals(Constants.EDU_TABLA_FRECU_CALIF)){
			String sql=
				"UPDATE EDU_FRECU_CALIF SET DES_FREC_CALI=?, TIP_CALI=?, USU_MODI=?, FEC_MODI=SYSDATE "+
				"WHERE COD_FREC_CALI=?";
			log.debug("sql esp frec "+sql + "cod "+entidadGenerico.getCodigo());	
			int row=getJdbcTemplate().update(sql,
					new PreparedStatementSetter() {
					 public void setValues(PreparedStatement pre) throws SQLException {
						 int i=1;
				           
				            pre.setString(i++,entidadGenerico.getDescripcion());
				            pre.setString(i++,entidadGenerico.getTipoCalificacion());
				            pre.setString(i++,usuario.getLogin());
				            pre.setString(i++,entidadGenerico.getCodigo());
				          
					 }                     
					});
          
            logger.debug("("+row+") filas  afectadas");
		}
			
		
	}

	private boolean isEntidadEspecial(EntidadGenerico entidadGenerico) {
		if(entidadGenerico.getNombreEntidad().equals(Constants.EDU_TABLA_EDU_ESTAD_CAPAC) || 
				entidadGenerico.getNombreEntidad().equals(Constants.EDU_TABLA_FRECU_CALIF))
			 return true;
		return false;
	}

	public String insertMultiEntidad(final EntidadGenerico entidadGenerico, final Usuario usuario) {
		logger.debug("insertMultiEntidad");
		
		String cadError="";
		try{
			if(isEntidadEspecial(entidadGenerico)) {
				 log.debug("aqui");
				 insertEntidadEspecial(entidadGenerico,usuario);
				 return cadError;
			}
			final List listColumnas=getListColumnas(entidadGenerico);
			String sql=getQueryInsertEntidad(entidadGenerico,listColumnas);
			logger.debug("query insert ENTIDAD  "+sql);
			
			int row=getJdbcTemplate().update(sql,
					new PreparedStatementSetter() {
					 public void setValues(PreparedStatement pre) throws SQLException {
						 int i=1;
				            pre.setString(i++,entidadGenerico.getCodigo());
				            pre.setString(i++,entidadGenerico.getDescripcion());
				            if(listColumnas.size()>2)
				             pre.setString(i++,entidadGenerico.getCodigoTipo());
				            
				            pre.setString(i++,usuario.getLogin());
				            pre.setString(i++,Constants.ESTADO_ACTIVO);
					 }                     
					});
          
            logger.debug("("+row+") filas  afectadas");
            
		    logger.debug("fin getMultiEntidadByCriteria");
		}catch(Exception e){
			logger.error("error getMultiEntidadByCriteria "+e.getMessage());
			cadError=e.getMessage();
		}
		return cadError;
	}
	
	

	private void insertEntidadEspecial(final EntidadGenerico entidadGenerico,final Usuario usuario) {
		if(entidadGenerico.getNombreEntidad().equals(Constants.EDU_TABLA_EDU_ESTAD_CAPAC)){
			String sql=
				"INSERT INTO EDU_ESTAD_CAPAC(COD_ESTA_CAPA, DES_ESTA_CAPA, IND_ESTA_CAPA, USU_DIGI, FEC_DIGI, USU_MODI, FEC_MODI, EST_REGI)"+
				"VALUES(?,?,?,?,SYSDATE,NULL,NULL,?)";
			int row=getJdbcTemplate().update(sql,
					new PreparedStatementSetter() {
					 public void setValues(PreparedStatement pre) throws SQLException {
						 int i=1;
				            pre.setString(i++,entidadGenerico.getCodigo());
				            pre.setString(i++,entidadGenerico.getDescripcion());
				            pre.setString(i++,entidadGenerico.getIndicadorEstado());
				            pre.setString(i++,usuario.getLogin());
				            pre.setString(i++,Constants.ESTADO_ACTIVO);
					 }                     
					});
          
            logger.debug("("+row+") filas  afectadas");
			
		}
		if(entidadGenerico.getNombreEntidad().equals(Constants.EDU_TABLA_FRECU_CALIF)){
			String sql=
				"INSERT INTO EDU_FRECU_CALIF(COD_FREC_CALI, DES_FREC_CALI, TIP_CALI, USU_DIGI, FEC_DIGI, USU_MODI, FEC_MODI, EST_REGI)"+
				"VALUES (?,?,?,?,SYSDATE,NULL,NULL,?)";
			int row=getJdbcTemplate().update(sql,
					new PreparedStatementSetter() {
					 public void setValues(PreparedStatement pre) throws SQLException {
						 int i=1;
				            pre.setString(i++,entidadGenerico.getCodigo());
				            pre.setString(i++,entidadGenerico.getDescripcion());
				            pre.setString(i++,entidadGenerico.getTipoCalificacion());
				            pre.setString(i++,usuario.getLogin());
				            pre.setString(i++,Constants.ESTADO_ACTIVO);
					 }                     
					});
          
            logger.debug("("+row+") filas  afectadas");
		}
			
		
	}

	public  List getListColumnas( EntidadGenerico entidadGenerico) {
		
		List list =null;
		try{
			String sql=getQueryColummn(entidadGenerico);
			logger.debug("query getListColumnas "+sql);
			ResultSetExtractor extractor = getExtractorColumns();
	        list =(List) getJdbcTemplate().query(sql,extractor);
		    logger.debug("fin getListColumnas " + list);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("error getListColumnas");
		}
		return list;
	}

	public ResultSetExtractor getExtractorColumns() {
		return new ResultSetExtractor() {
	        public Object extractData(ResultSet resultSet)
	            throws SQLException, DataAccessException {
	            List resultList = new ArrayList();
	            while (resultSet.next()) {
	               resultList.add(resultSet.getString("COLUMN_NAME"));
	            }
	            return resultList;
	        }
	    };
		 
	}
	public  String getQueryColummn(EntidadGenerico entidadGenerico) {
		java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("jdbc");
		String owner=bundle.getString("jdbc.username");
		StringBuffer sb=new StringBuffer("SELECT COLUMN_NAME FROM ALL_TAB_COLUMNS WHERE UPPER(TABLE_NAME)='");
		sb.append(entidadGenerico.getNombreEntidad().toUpperCase().trim()+"'");
		sb.append(" AND upper(OWNER)='");
		sb.append(owner.toUpperCase().trim()+"'");
		sb.append(" AND (COLUMN_NAME LIKE '%COD%' OR COLUMN_NAME LIKE '%DES%') ");
		sb.append(" ORDER BY COLUMN_NAME ");			   
		return sb.toString();
	}
	
	public  String getQueryMultiEntidadByCriteria(EntidadGenerico entidadGenerico, List listColumnas) {
		StringBuffer sb= new StringBuffer("");
		
		 if(listColumnas.size()>0){
			 if(listColumnas.size()>2)
			  sb.append("SELECT "+listColumnas.get(0)+","+listColumnas.get(1)+","+ listColumnas.get(2)+ " FROM ");
			 else
			  sb.append("SELECT "+listColumnas.get(0)+","+listColumnas.get(1)+  " FROM ");	 
			 sb.append(entidadGenerico.getNombreEntidad());
			 sb.append(" WHERE (1=1)");
			//en el primer indice la columna de codigo, en el segundo la descripcion , si hay tercero la llave foranea
			if(StringUtils.isNotEmpty(entidadGenerico.getCodigo())){
				sb.append(" AND ");
				sb.append(listColumnas.get(0));//nombre columna 1:COD
				sb.append("= '"+entidadGenerico.getCodigo()+"' ");
			}
			if(StringUtils.isNotEmpty(entidadGenerico.getDescripcion())){
				sb.append(" AND ");
				sb.append(listColumnas.get(1));//nombre columna 2 :DES
				sb.append("= '"+entidadGenerico.getDescripcion()+"' ");
			}
	
			if(entidadGenerico.getNombreEntidad().equals("EDU_TIPO_INDIC_DESPA")){//tabla q se escapa de esquema
			 if(StringUtils.isNotEmpty(entidadGenerico.getCodigoTipo())){	
				sb.append(" AND ");
				sb.append(listColumnas.get(2));//nombre columna 2 :TDES
				sb.append("= '"+entidadGenerico.getCodigoTipo()+"' ");
			 }
			}
			sb.append(" AND EST_REGI='1' ");
			sb.append(" ORDER BY 1");
		 }	
			return sb.toString();
		}
	
	public  String getQueryUpdateEstadoEntidad(EntidadGenerico entidadGenerico, List listColumnas) {
		StringBuffer sb= new StringBuffer("UPDATE " + entidadGenerico.getNombreEntidad());
		if(listColumnas.size()>0){
			sb.append(" SET EST_REGI=? , USU_MODI=? , FEC_MODI=SYSDATE ");
			sb.append(" WHERE " );
		 if(listColumnas.size()>2)	
			sb.append(listColumnas.get(0)+"='"+entidadGenerico.getCodigo()+"' AND "+
					  listColumnas.get(2)+"='"+entidadGenerico.getCodigoTipo()+"' ");
		 else
			 sb.append(listColumnas.get(0)+"='"+entidadGenerico.getCodigo()+"'");
		}
		return sb.toString();
	}
	
	public  String getQueryUpdateEntidad(EntidadGenerico entidadGenerico, List listColumnas) {
		StringBuffer sb= new StringBuffer("UPDATE " + entidadGenerico.getNombreEntidad());
		if(listColumnas.size()>0){
			sb.append(" SET "+ listColumnas.get(1) +"=? , USU_MODI=? , FEC_MODI=SYSDATE ");
			sb.append(" WHERE " );
		 if(listColumnas.size()>2)	
			sb.append(listColumnas.get(0)+"='"+entidadGenerico.getCodigo()+"' AND "+
					  listColumnas.get(2)+"='"+entidadGenerico.getCodigoTipo()+"' ");
		 else
			 sb.append(listColumnas.get(0)+"='"+entidadGenerico.getCodigo()+"'");
		}
		return sb.toString();
	}
	
	public  String getQueryInsertEntidad(EntidadGenerico entidadGenerico, List listColumnas) {
		 StringBuffer sb= new StringBuffer("INSERT INTO " + entidadGenerico.getNombreEntidad());
		 if(listColumnas.size()>2){
			 sb.append(" ( "+ listColumnas.get(0) +"," + listColumnas.get(1)+"," + listColumnas.get(2) + ",");
			 sb.append("USU_DIGI,FEC_DIGI,USU_MODI,FEC_MODI,EST_REGI)");
			 sb.append("VALUES(?,?,?,?,SYSDATE,NULL,NULL,?)");
		 }
		 else{
			 sb.append(" ( "+ listColumnas.get(0) +"," + listColumnas.get(1)+ ",");
			 sb.append("USU_DIGI,FEC_DIGI,USU_MODI,FEC_MODI,EST_REGI)");
			 sb.append("VALUES(?,?,?,SYSDATE,NULL,NULL,?)");
		 }	 
				 
		return sb.toString();
	}

}
