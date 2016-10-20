package biz.belcorp.ssicc.service.spusicc.ape;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * <p>
 * <a href="MantenimientoAPEEstimadoProductoService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="">David Ramos</a>
 */
public interface MantenimientoAPEEstimadoProductoService extends Service{

	/**
	 * Retorna La Lista de Linea de Armado de Centro de Distribucion Seleccionado
	 * @param criteria
	 * @return
	 */
	public List getLineaArmadobyOidCentro(Map criteria);
	
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
	 * Obtiene el siguiente nuero de oid de la sublinea
	 * @return
	 */
	public int getNextOidEstimadoProducto();

	/**
	 * Inserta una SubLinea de Armado
	 * @param criteria
	 */
	public void insertEstimadoProducto(Map criteria);
	

	/**
	 * Actualiza los datos de estimado producto
	 * @param criteria
	 */
	public void updateEstimadoProducto(Map criteria);
	
	
	/**
	 * @param criteria
	 * Elimina el registro en la tabla estimado producto
	 */
	public void deleteEstimadoProducto(Map criteria,String[] items);	
	
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
}