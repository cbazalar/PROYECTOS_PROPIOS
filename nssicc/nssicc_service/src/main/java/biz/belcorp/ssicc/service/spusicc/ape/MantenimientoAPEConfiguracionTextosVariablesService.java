package biz.belcorp.ssicc.service.spusicc.ape;


import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * <p>
 * <a href="MantenimientoAPEConfiguracionTextosVariablesService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="">David Ramos</a>
 */
public interface MantenimientoAPEConfiguracionTextosVariablesService extends Service{

	
	/**
	 * Obtiene el siguiente nuero de oid de la sublinea
	 * @return
	 */
	public int getNextOidConfiguracionTextosVariables();

	/**
	 * Inserta una SubLinea de Armado
	 * @param criteria
	 */
	public void insertConfiguracionTextosVariables(Map criteria);
	

	/**
	 * Actualiza los datos de estimado producto
	 * @param criteria
	 */
	public void updateConfiguracionTextosVariables(Map criteria);
	
	
	/**
	 * @param criteria
	 * Elimina el registro en la tabla estimado producto
	 */
	public void deleteConfiguracionTextosVariables(Map criteria,String[] items);	
	
	/**
	 * Retorna La Lista de Linea de Armado de Centro de Distribucion Seleccionado
	 * @param criteria
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
	 * Devuelve la lista de productos Emitir Alarma
	 * @param criteria
	 * @return
	 */
	public List getConfiTextoVariaList(Map criteria);

	/**
	 * Valida si existe la letra del anaquel en el centro de
	 * distribucion 
	 * @param criteria
	 * @return
	 */
	public int getExisteConfiguracionTextosVariablesCD(Map criteria);
	
}
