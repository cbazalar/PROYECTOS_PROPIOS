package biz.belcorp.ssicc.service.spusicc.ape;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoAPEImportarOrdenAnaquelesService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza </a>
 */
public interface ProcesoAPEImportarOrdenAnaquelesService extends Service {

	/**
	 * Recupera la informacion de un Orden de Anaquel
	 * 
	 * @param criteria
	 * @return
	 */
	public Map getOrdenAnaquel(Map criteria);
	
	/**
	 * Recupera las subLineas de Armado relacionado a un Orden de Anaquel
	 * 
	 * @param criteria
	 * @return
	 */
	public List getSubLineaOrdenAnaquel(Map criteria);

	/**
	 * Carga la informacion del archivo Excel
	 * 
	 * @param criteria
	 * @return
	 * @throws Exception
	 */
	public List cargarArchivoExcel(Map criteria) throws Exception;
	
	/**
	 * Valida la informacion del archivo Excel, y si todo esta correcto lo guarda en BD
	 * 
	 * @param criteria
	 * @throws Exception
	 */
	public void executeImportarOrdenAnaqueles(Map criteria) throws Exception;
	
	/**
	 * Obtiene una lista de Tipo de solicitud, solo consolidados .
	 * 
	 * @param params
	 *            codigoPais,
	 * 
	 * @return Lista de objetos de tipo Base, poblados.
	 */
	public List getTipoSolConsolidado(Map params);
	/**
	 * @param params
	 * Actualiza indicador
	 */
	public void actualizarIndicador(Map params);
}
