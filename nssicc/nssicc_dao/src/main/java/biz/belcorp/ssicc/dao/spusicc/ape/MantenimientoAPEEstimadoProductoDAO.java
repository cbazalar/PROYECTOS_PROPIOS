package biz.belcorp.ssicc.dao.spusicc.ape;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.spusicc.ape.model.EstimadoProducto;

/**
 * @author David Ramos
 */

public interface MantenimientoAPEEstimadoProductoDAO extends DAO{

	/**
	 * Retorna La Lista de Linea de Armado de Centro de Distribucion Seleccionado
	 * @param criteria
	 * @return
	 */
	public List getLineaArmadobyOidCentro(Map criteria);
	
	/**
	 * Retorna el Codigo de Linea de Armado por defecto del Centro de Distribucion
	 * Seleccionado
	 * @param criteria
	 * @return
	 */
	public String getCodLineaArmadaDefecto(Map criteria);
	
	/**
	 * Retorna la Lista de Sublinea
	 * @param criteria
	 * @return
	 */
	public List getEstimadoProductoList(Map criteria);
	
	/**
	 * Devuelve el Oid de la Linea de Armado
	 * @param criteria
	 * @return
	 */
	public String getOidLineaArmadobyCodigo(Map criteria);
	

	
	/**
	 * Obtiene la lista de Linea de Armado
	 * @param criteria
	 * @return
	 */
	public List getLineaArmadoComboList(Map criteria);
	
	/**
	 * Obtiene la linea de armado por defecto del centro de distribucin
	 * @param criteria
	 * @return
	 */
	public String getCodLineaArmadaDefectoList(Map criteria);
	
	/**
	 * Obtiene el objeto SubLinea de Armado
	 * @param criteria
	 * @return
	 */
	public EstimadoProducto getSubLineaArmadoObject(Map criteria);
	

	/**
	 * Obtiene el siguiente nuero de oid de la sublinea
	 * @return
	 */
	public int getNextOidEstimadoProducto();
	

	/**
	 * Inserta una Estimado Producto
	 * @param criteria
	 */
	public void insertEstimadoProducto(Map criteria);
	
	/**
	 * Actuliza los datos de Estimado Producto
	 * @param criteria
	 */
	public void updateEstimadoProducto(Map criteria);

	/**
	 * @param criteria
	 * Elimina el registro en la tabla Unidad Administrativa por Linea
	 */
	public void deleteEstimadoProducto(Map criteria);

	/**
	 * Valida si existe la letra del anaquel en el centro de
	 * distribucion 
	 * @param criteria
	 * @return
	 */
	public int getExisteEstimadoProductoCD(Map criteria);
	
		/**
	 * Devuelve el codigo de Linea de Armado por el Oid
	 * @param criteria
	 * @return
	 */
	public String getCodigoLineaArmadobyOid(Map criteria);

	/**
	 * Devuelve el codigo de Linea de Armado por el Oid
	 * @param criteria
	 * @return
	 */
	public String getOidProductoByCodigoyPais(Map criteria);	
	
	/**
	 * Valida si existe la letra del anaquel en el centro de
	 * distribucion 
	 * @param criteria
	 * @return
	 */
	public int getExisteProductoCD(Map criteria);	
	
	/**
	 * Obtiene la lista de Linea de Armado
	 * @param criteria
	 * @return
	 */
	public EstimadoProducto getProductoByCodSapyOidPaisObject(Map criteria);
	
}
