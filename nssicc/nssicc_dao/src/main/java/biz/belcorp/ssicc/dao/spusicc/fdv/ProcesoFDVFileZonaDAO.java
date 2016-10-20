package biz.belcorp.ssicc.dao.spusicc.fdv;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.ProcesoFDVClusterizacion;
import biz.belcorp.ssicc.dao.model.Usuario;

/**
 * <p>
 * <a href="ProcesoFDVFileZonaDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:fayala@belcorp.biz">Frank Ayala</a>
 */

public interface ProcesoFDVFileZonaDAO extends DAO{

	/**
	 * Obtiene el siguiente correlativo ID para registrar un nuevo proceso
	 * 
	 * @return codigo generado
	 */
	public String getNextID();

	/**
	 * Obtiene los datos del proceso correspondientes al codigo de proceso pasado como parametro
	 * 
	 * @param codProc
	 *            codigo del proceso
	 */
	public ProcesoFDVClusterizacion getProcesoCluster(String codProc);

	/**
	 * Obtiene un listado de los procesos filtrados por los parametros de busqueda del Map 'criteria'
	 * 
	 * @param criteria
	 *            datos de busqueda que filtran los datos
	 */
	public List getProcesosClusterByCriteria(Map criteria);

	/**
	 * Guarda los datos del proceso
	 * 
	 * @param procesoFDVClusterizacion
	 *            datos del proceso a guardar
	 * @param usuario
	 * 			  el usuario que inserta o actualiza la informacin
	 * @throws DataIntegrityViolationException 
	 */
	public void insertProcesoClusterizacion(
			ProcesoFDVClusterizacion procesoFDVClusterizacion, Usuario usuario) throws DataIntegrityViolationException;

	/**
	 * Guarda la informacion capturada del archivo de zonas
	 * 
	 * @param zonasList
	 *            lista de objetos a ser insertados
     * @param codProc
     *            el codigo del proceso
     * @param cleanData
     * 			  campo que nos indicara si se desea limpiar los datos de la tabla
     * 			  antes de realizar las inserciones.           
	 * @throws Exception 
	 */
	public void insertZonasFDV(final List zonasList, final String codProc, final boolean cleanData) throws Exception;

	/**
	 * Guarda la relacin de los parametros existentes en FDV_PARAM con el registro del proceso
	 * 
	 * @param procesoFDVClusterizacion
	 *            entidad con el codigo del proceso a relacionarse
	 * @throws DataIntegrityViolationException 
	 */
	public void insertProcesoParametro(ProcesoFDVClusterizacion procesoFDVClusterizacion) throws DataIntegrityViolationException;

}
