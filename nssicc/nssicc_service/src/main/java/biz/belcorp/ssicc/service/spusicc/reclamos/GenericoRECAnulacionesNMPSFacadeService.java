package biz.belcorp.ssicc.service.spusicc.reclamos;


import java.sql.Connection;
import java.util.Map;

import biz.belcorp.ssicc.dao.ocr.model.ConexionOCRWrapper;

/**
 * Interface Service correspondiente al OCR Comercial
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar</a>
 * 
 */

public interface GenericoRECAnulacionesNMPSFacadeService {

	/** 
	 * @param fuenteDatos
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public void excuteSpWCSAPE (ConexionOCRWrapper conexionOCRWrapper, Connection conexion, Map params) throws Exception;
	
	public Connection obtenerConexion (ConexionOCRWrapper conexionOCRWrapper, Map params) throws Exception;
	
	public void cerrarConexion(ConexionOCRWrapper conexionOCRWrapper, Connection conexion, Map params) throws Exception;
}
