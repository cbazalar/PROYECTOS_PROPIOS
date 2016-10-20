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
 * <a href="ProcesoCCCRecepcionarPagosWebDAO.java.html"> <i>View Source </i>
 * </a>
 * </p>
 * 
 * @author <a href="mailto:jflorencio@belcorp.biz">JFA </a>
 */
public interface ProcesoCCCRecepcionarPagosWebDAO extends DAO {

	/**
	 * Otiene el ultimo OID de Pago Cargado
	 * 
	 * @param criteria
	 * @return
	 */
	public Integer getUltimoOidPagoWeb(Map criteria);

	/**
	 * Actualiza el ultimo OID de Pago Procesado
	 * 
	 * @param criteria
	 * @return
	 */
	public void updateUltimoOidPagoWeb(Map criteria);

	/**
	 * Proceso que realiza la insercin del pago web	 
	 * @param criteria
	 */
	public void insertPagoWeb(Map criteria);
		
	/**
	 * Metodo que devuelve Lista de los pagos web desde IPM	 
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List getListPagosWeb(ConexionOCRWrapper conexionOCRWrapper,
			Map params) throws Exception;

	/**
	 * Proceso que realiza la carga de los pagos en linea
	 * 
	 * @param params
	 */
	public void executeCargaPagosBancoLinea(Map params);

}
