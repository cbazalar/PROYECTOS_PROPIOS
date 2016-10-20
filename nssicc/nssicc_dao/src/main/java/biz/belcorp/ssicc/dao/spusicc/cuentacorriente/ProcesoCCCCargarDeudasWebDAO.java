/*
 * Created on 02/08/2005 06:34:26 PM biz.belcorp.ssicc.dao.StickerDAO
 */
package biz.belcorp.ssicc.dao.spusicc.cuentacorriente;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.ocr.model.ConexionOCRWrapper;

/**
 * <p>
 * <a href="ProcesoCCCCargarDeudasWebDAO.java.html"> <i>View Source </i>
 * </a>
 * </p>
 * 
 * @author <a href="mailto:jflorencio@belcorp.biz">JFA </a>
 */
public interface ProcesoCCCCargarDeudasWebDAO extends DAO {

	
	/**
	 *  Metodo que genera las consultora a actualizarse.
	 * @param criteria
	 */
	public void executeGeneraNovedadesDeudasWeb(Map criteria);
	
	/**
	 * Metodo que obtiene la lista de las deudas a cargarse en IPM
	 * @param criteria
	 */	
	public List getListInsertDeudasWeb(Map criteria) throws Exception;
	
	/**
	 * Metodo que obtiene la lista de las deudas a actualizarse en IPM
	 * @param criteria
	 */
	public List getListUpdateDeudasWeb(Map criteria) throws Exception;
	
	/**
	 * Proceso que realiza la insercin del pago web	 
	 * @param criteria
	 */
	public void insertDeudasWeb(ConexionOCRWrapper conexionOCRWrapper,
			Map params) throws Exception;
	
	/**
	 * Proceso que realiza la insercin de la deuda web	 
	 * @param criteria
	 */
	public void updateDeudasWeb(ConexionOCRWrapper conexionOCRWrapper,
			Map params) throws Exception;
	

}
