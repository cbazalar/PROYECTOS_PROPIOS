package biz.belcorp.ssicc.dao.spusicc.reclamos.ibatis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.ocr.model.ConexionOCRWrapper;
import biz.belcorp.ssicc.dao.spusicc.reclamos.GenericoRECAnulacionesNMPSORAServerDAO;

/**
 * ImplementacionDAO correspondiente al OCR Comercial proveniente de SQL Server
 * 
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar</a>
 */
@Repository("spusicc.genericoRECAnulacionesNMPSORAServerDAO")
public class GenericoRECAnulacionesNMPSORAServerDAOiBatis extends BaseDAOiBatis
		implements GenericoRECAnulacionesNMPSORAServerDAO {

	List listComercial = new ArrayList();
	private MessageSource messageSource;

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.ocr.dao.gen.GenericoOCRComercialDAO#getListProcesoCarga(java.lang.String, java.util.Map)
	 */
	public void excuteSpWCSAPE(Connection connection, Map params) throws Exception {
		try {
			getSqlMapClient().setUserConnection(connection);
			getSqlMapClientTemplate().update("spusicc.reclamos.GenericoRECAnulacionesNMPSORAServerSQL.excuteSpWCSAPE",params);
			
		} catch (Exception e) {
			log.error("Error en excuteSpWCSAPE " + e.getMessage());
			throw new Exception(e.getMessage());			
		} 
		
	}
	
	/**
	 * Metodo que obtiene el objeto Conexion 
	 * @param conexionOCRWrapper
	 * @return
	 * @throws Exception
	 */
	public Connection obtenerConexion (ConexionOCRWrapper conexionOCRWrapper) throws Exception {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
			Connection connection =  DriverManager.getConnection (conexionOCRWrapper.getHost(),
							conexionOCRWrapper.getUsuario(),
							conexionOCRWrapper.getPassword());
			return connection;
		} 
		catch (Exception e) {
			log.error("Error en obtenerConexion " + e.getMessage());
		   throw new Exception(e.getMessage());
		} 	
	}
	
	public void cerrarConexion (Connection conexion) throws Exception{
		try {
			conexion.close();
		}catch (Exception e) {
			log.error("Error en cerrarConexion " + e.getMessage());
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
}
