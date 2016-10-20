package biz.belcorp.ssicc.dao.ocr.gen;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.ocr.model.ConexionOCRWrapper;

/**
 * 
 * DAO correspondiente al OCR Comercial 
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar</a>
 * 
 */
public interface GenericoOCRComercialDAO extends DAO { 

	/**
	 * Metodo que devuelve Lista de Proceso de Carga proveniente del OCR Comercial
	 * @param fuenteDatos
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List getListProcesoCarga(ConexionOCRWrapper conexionOCRWrapper, Map params) throws Exception;
	
	/**
	 * Metodo que devuelve Lista de Proceso de Carga Cupon proveniente del OCR Comercial
	 * @param fuenteDatos
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List getListProcesoCargaCupon(ConexionOCRWrapper conexionOCRWrapper, Map params) throws Exception;
	
}
