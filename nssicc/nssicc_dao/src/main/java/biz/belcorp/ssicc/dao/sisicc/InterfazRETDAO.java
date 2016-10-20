package biz.belcorp.ssicc.dao.sisicc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * DAO de la Interfaz Retail
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli</a>
 */
public interface InterfazRETDAO {

	/**
	 * Elimina registros correspondientes a una fecha inicial y final
	 * @param queryParams
	 */
	void deleteInterfazRETRecepcionarVentasRetail(Map queryParams);

	/**
	 * Inserta los registros leidos
	 * @param row
	 */
	void insertInterfazRETRecepcionarVentasRetail(HashMap row);

	/**
	 * Devuelve 1 si existe en cabecera con los filtrs pasados en el map, 
	 * devuelve 0 si no existe en cabecera
	 * @param row
	 * @return
	 */
	Integer getExisteCabeceraVentasRetail(HashMap row);

	/**
	 * Insert los detalles de ventas Retail
	 * @param row
	 */
	void insertInterfazRETRecepcionarVentasRetailDetalle(HashMap row);

	/**
	 * Retorna la lista de pago de comisiones para ser enviada
	 * @param params
	 * @return
	 */
	List getInterfazRETEnviarPagoComisionesRetailGZ(Map params);

	
	/**
	 * Interfaz de Envio de Detalle Facturas de Venta Directa
	 * @param params
	 */
	public void executeInterfazRETEnviarDetalleFacturasVD(Map params);
	
	/**
	 * 
	 * @param params
	 */
	public void executeInterfazRETEnviarInformacionRetail(Map params);

	/**
	 * 
	 * @param params
	 */
	public void executeInterfazRETEnviarInformacionClientesRetail(Map params);
}
