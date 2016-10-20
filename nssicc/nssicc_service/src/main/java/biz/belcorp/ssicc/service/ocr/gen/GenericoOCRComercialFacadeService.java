package biz.belcorp.ssicc.service.ocr.gen;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.ocr.model.ConexionOCRWrapper;

/**
 * Interface Service correspondiente al OCR Comercial
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar</a>
 * 
 */

public interface GenericoOCRComercialFacadeService {

	/**
	 * Genera Lista OCR obtenido del Comercial y lo graba en Tabla del SSiCC 
	 * @param fuenteDatos
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List getListProcesoCarga(ConexionOCRWrapper conexionOCRWrapper, Map params) throws Exception;
	
	/**
	 * Genera Lista Cupon obtenido del Comercial y lo graba en Tabla del SSiCC 
	 * @param fuenteDatos
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List getListProcesoCargaCupon(ConexionOCRWrapper conexionOCRWrapper, Map params) throws Exception;
}
