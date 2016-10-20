package biz.belcorp.ssicc.dao.spusicc.ape;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author Nicols Lpez
 *
 */

public interface MantenimientoAPEActualizaIndProdDentroCajaDAO extends DAO{
	
	/**
	 * Devuelve los datos de la tabla APE_PRODU_IND_CAJA
	 * @param criteria
	 * @return
	 */
	public List getIndProductoCajaDentroList(Map criteria);
	
	
	/**
	 * Obtiene el oid de la tabla APE_PRODU_IND_CAJA
	 * @return
	 */
	public String getOidIndProductoCajaDentro(Map criteria);
	
	/**
	 * @param criteria
	 * @return Lista de Productos con Indicador Dentro de Caja
	 */
	public List getProductoIndicadorDentroCajaList(Map criteria);
	
	/**
	 * @param criteria
	 * @return Los datos de Indicador Producuto Dentro Caja
	 */
	public List getIndProdDentroCajaObject(Map criteria);
	
	/**
	 * @param criteria
	 * Permite actualizar el indicador de dentro/fuera de Caja
	 */
	public void updateIndicadorProducto(Map criteria);

	/**
	 * @param criteria
	 * @return 1 Si ya existe el indicador, 0 si no existe
	 */
	public String getValidaExisteIndicadorCaja(Map criteria);
	
	/**
	 * @param criteria
	 * Permite actualizar el indicador de producto con el codigo SAP
	 */
	public void updateIndProductoDFCaja(Map criteria);
	
}
