package biz.belcorp.ssicc.dao.ocr.gen.ibatis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.context.MessageSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.ocr.gen.GenericoOCRComercialDAO;
import biz.belcorp.ssicc.dao.ocr.model.ConexionOCRWrapper;

/**
 * ImplementacionDAO correspondiente al OCR Comercial proveniente de SQL Server
 * 
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar</a>
 */
public class GenericoOCRComercialSQLServerDAOiBatis extends BaseDAOiBatis 
		implements GenericoOCRComercialDAO {

	List listComercial = new ArrayList();
	private MessageSource messageSource;

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.ocr.dao.gen.GenericoOCRComercialDAO#getListProcesoCarga(java.lang.String, java.util.Map)
	 */
	public List getListProcesoCarga(ConexionOCRWrapper conexionOCRWrapper, Map params) throws Exception {
		Connection connection = this.obtenerConexion(conexionOCRWrapper);
		try {
			getSqlMapClient().setUserConnection(connection);
			listComercial = getSqlMapClientTemplate().queryForList(
						"ocr.GenericoOCRComercialSQLServerSQL.getListProcesoCarga",
						params);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} 
		finally {
			connection.close();
		}
		return listComercial;
	}
	
	/**
	 * Metodo que obtiene el objeto Conexion 
	 * @param conexionOCRWrapper
	 * @return
	 * @throws Exception
	 */
	private Connection obtenerConexion (ConexionOCRWrapper conexionOCRWrapper) throws Exception {
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
			Connection connection =  DriverManager.getConnection (conexionOCRWrapper.getHost(),
							conexionOCRWrapper.getUsuario(),
							conexionOCRWrapper.getPassword());
			return connection;
		} 
		catch (Exception e) {
		   throw new Exception(e.getMessage());
		} 	
	}
	
	

	/**
	 * @return Returns the messageSource.
	 */
	public MessageSource getMessageSource() {
		return messageSource;
	}

	/**
	 * @param messageSource The messageSource to set.
	 */
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.ocr.dao.gen.GenericoOCRComercialDAO#getListProcesoCargaCupon(biz.belcorp.ssicc.ocr.dao.model.ConexionOCRWrapper, java.util.Map)
	 */
	public List getListProcesoCargaCupon(ConexionOCRWrapper conexionOCRWrapper,
			Map params) throws Exception {
		//Connection connection = this.obtenerConexion(conexionOCRWrapper);

		List listCupon= null;
		DriverManagerDataSource ds = new DriverManagerDataSource();
		try {
			
			ds.setDriverClassName("net.sourceforge.jtds.jdbc.Driver");
			ds.setUrl(conexionOCRWrapper.getHost());
			ds.setUsername(conexionOCRWrapper.getUsuario());
			ds.setPassword(conexionOCRWrapper.getPassword());
			setDataSource(ds);

			listCupon = getSqlMapClientTemplate().queryForList(
						"ocr.GenericoOCRComercialSQLServerSQL.getListProcesoCargaCupon",
						params);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} 
		finally {
			ds=null;
		}
		return listCupon;
	}
	
}
