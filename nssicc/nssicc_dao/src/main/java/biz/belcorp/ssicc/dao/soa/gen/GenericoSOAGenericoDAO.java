package biz.belcorp.ssicc.dao.soa.gen;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.ocr.model.ConexionOCRWrapper;

/**
 * 
 * DAO correspondiente al OCR Comercial 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli</a>
 * 
 */
public interface GenericoSOAGenericoDAO extends DAO {

	/**
	 * Metodo que devuelve Lista de Pedidos de datamart
	 * @param fuenteDatos
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List getListPedidosDatamart(ConexionOCRWrapper conexionOCRWrapper, Map params) throws Exception;

	/**
	 * retorna la fecha de actualizaciond e datamart
	 * @param conexion
	 * @param criteria 
	 * @return
	 */
	public Date getFechaUltimaActualizacionDatamart(ConexionOCRWrapper conexion, Map criteria)throws Exception;

	/**
	 * actualiza el flag a 1 , que indica qye ase cago los registros
	 * @param conexion
	 * @param criteria
	 */
	public void updateFlagPedidosDatamart(ConexionOCRWrapper conexion,
			Map criteria)throws Exception;
	

		
	/**
	 * Metodo que devuelve la Lista de Indicadores de datamart
	 * @param conexionOCRWrapper
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List getListIndicadoresDatamart(ConexionOCRWrapper conexionOCRWrapper, Map params) throws Exception;
	/**
	 * actualiza de bloeque en bloque los pedidos ed datmart
	 * @param list
	 * @param conexion
	 * @param criteria
	 */
	public void updateFlagPedidosDatamart(final List list,ConexionOCRWrapper conexion, Map criteria)throws Exception;

	/**retorna el numero de bloqeus que sera dividio la data d edatamart
	 * @param conexion
	 * @param criteria
	 * @return
	 */
	public int getNumpaginas(ConexionOCRWrapper conexion, Map criteria);
	
	/**
	 * Metodo que devuelve la Lista de Pedidos web
	 * @param conexionOCRWrapper
	 * @param criteria
	 * @return
	 * @throws Exception
	 */
	public List getPedidosWeb(ConexionOCRWrapper conexionOCRWrapper, Map criteria) throws Exception;

	/**
	 * Metodo que devuelve la Lista del Detalle del Pedido web
	 * @param conexionOCRWrapper
	 * @param criteria
	 * @return
	 * @throws Exception
	 */
	public List getDetallePedidoWeb(ConexionOCRWrapper conexionOCRWrapper, Map criteria) throws Exception;

	/**
	 * Obtiene la consultora que proviene de datamart
	 * @param conexion
	 * @param criteria
	 * @return
	 */
	public Map getConsultoraDatamart(ConexionOCRWrapper conexionOCRWrapper, Map criteria);


	/**
	 *  Obtiene la consultora que proviene de datamart a partir de una lista
	 * @param conexionOCRWrapper
	 * @param criteria
	 * @param list
	 * @param codigoNivelConsultora
	 * @param codigoPais
	 * @return
	 */
	public List getConsultoraDatamart(ConexionOCRWrapper conexionOCRWrapper, Map criteria, List list, String codigoNivelConsultora, String codigoPais);
	
	
	
	
	/**
	 *  Obtiene la consultora que proviene de datamart a partir de una lista
	 * @param conexionOCRWrapper
	 * @param criteria
	 * @param list
	 * @param codigoNivelConsultora
	 * @param codigoPais
	 * @return
	 */
	public List getConsultoraSicc(ConexionOCRWrapper conexionOCRWrapper, Map criteria, List list, String codigoPais);
	
	
	
	/**
	 * @param conexion
	 * @param criteria
	 * @return
	 */
	public int updatePedidoWeb(ConexionOCRWrapper conexion, Map criteria);
	

}
