package biz.belcorp.ssicc.dao.spusicc.reclamos;

import java.sql.Connection;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.ocr.model.ConexionOCRWrapper;

/**
 * 
 * DAO correspondiente al OCR Comercial 
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar</a>
 * 
 */
public interface GenericoRECAnulacionesNMPSORAServerDAO extends DAO {

	/**
	 * @param fuenteDatos
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public void excuteSpWCSAPE(Connection connection, Map params) throws Exception;
	public Connection obtenerConexion (ConexionOCRWrapper conexionOCRWrapper) throws Exception;
	public void cerrarConexion (Connection conexion) throws Exception;

	
}
