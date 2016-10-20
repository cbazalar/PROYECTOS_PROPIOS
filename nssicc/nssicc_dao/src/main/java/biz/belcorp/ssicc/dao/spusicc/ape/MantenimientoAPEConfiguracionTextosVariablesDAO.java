package biz.belcorp.ssicc.dao.spusicc.ape;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author David Ramos
 */

public interface MantenimientoAPEConfiguracionTextosVariablesDAO extends DAO{

	/**
	 * Obtiene el siguiente nuero de oid de la sublinea
	 * @return
	 */
	public int getNextOidConfiguracionTextosVariables();
	/**
	 * Inserta una Estimado Producto
	 * @param criteria
	 */
	public void insertConfiguracionTextosVariables(Map criteria);
	
	/**
	 * Actuliza los datos de Estimado Producto
	 * @param criteria
	 */
	public void updateConfiguracionTextosVariables(Map criteria);

	/**
	 * @param criteria
	 * Elimina el registro en la tabla Unidad Administrativa por Linea
	 */
	public void deleteConfiguracionTextosVariables(Map criteria);

	/**
	 * Retorna La Lista de Linea de Armado de Centro de Distribucion Seleccionado
	 * @return
	 */
	public List getTipoClienteList();
	
	/**
	 * Retorna La Lista de Linea de Armado de Centro de Distribucion Seleccionado
	 * @param criteria
	 * @return
	 */
	public List getSubTiClienteList(Map criteria);
	
	/**
	 * Retorna La Lista de Linea de Armado de Centro de Distribucion Seleccionado
	 * @param criteria
	 * @return
	 */
	public List getTipoClasificacionByOidSubTipoClienteList(Map criteria);

	/**
	 * Retorna La Lista de Linea de Armado de Centro de Distribucion Seleccionado
	 * @param criteria
	 * @return
	 */
	public List getClasificacionByOidTipoClasificacionList(Map criteria);
	
	/**
	 * Devuelve la lista de Productos 
	 * @param criteria
	 * @return
	 */
	public List getConfiTextoVariaList(Map criteria);

	/**
	 * @param criteria
	 * @return
	 */
	public int getExisteConfiguracionTextosVariablesCD(Map criteria);
	
	
}
