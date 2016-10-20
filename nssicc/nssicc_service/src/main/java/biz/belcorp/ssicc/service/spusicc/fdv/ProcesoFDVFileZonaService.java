package biz.belcorp.ssicc.service.spusicc.fdv;

import biz.belcorp.ssicc.dao.model.ProcesoFDVClusterizacion;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.fdv.ProcesoFDVFileZonaDAO;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * <p>
 * <a href="ProcesoFDVFileZonaService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:fayala@belcorp.biz">Frank Ayala</a>
 */

public interface ProcesoFDVFileZonaService extends Service{

	/**
	 * Setter del DAO, conveniente para pruebas unitarias
	 */
	public void setProcesoFDVFileZonaDAO(ProcesoFDVFileZonaDAO procesoFDVFileZonaDAO);

	/**
	 * Registra la informacin de un nuevo proceso clusterizacion, y tambien guarda la informacion
	 * capturada del archivo de zonas
	 * 
	 * @param procesoFDVClusterizacion
	 *            el objeto a ser insertado
     * @param usuario
     *            el usuario que registra la informacin
	 * @throws Exception 
	 */
	public void saveFileZonasAndProcesoCluster(
			ProcesoFDVClusterizacion procesoFDVClusterizacion, Usuario usuario) throws Exception;

	/**
	 * Guarda la informacion capturada del archivo de zonas
	 * 
	 * @param procesoFDVClusterizacion
	 *            el objeto a ser insertado
     * @param usuario
     *            el usuario que registra la informacin
	 * @throws Exception 
	 * @throws Exception 
	 */
	public void saveFileZonas(
			ProcesoFDVClusterizacion procesoFDVClusterizacion, Usuario usuario) throws Exception;
}
