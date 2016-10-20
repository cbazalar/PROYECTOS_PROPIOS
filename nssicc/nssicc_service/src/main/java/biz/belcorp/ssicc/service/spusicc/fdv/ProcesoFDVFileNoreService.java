package biz.belcorp.ssicc.service.spusicc.fdv;

import biz.belcorp.ssicc.dao.model.ProcesoFDVClusterizacion;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.fdv.ProcesoFDVFileNoreDAO;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * <p>
 * <a href="ProcesoFDVFileNoreService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:fayala@belcorp.biz">Frank Ayala</a>
 */

public interface ProcesoFDVFileNoreService extends Service{

	/**
	 * Setter del DAO, conveniente para pruebas unitarias
	 */
	public void setProcesoFDVFileNoreDAO(ProcesoFDVFileNoreDAO procesoFDVFileNoreDAO);

	/**
	 * Guarda la informacion capturada del archivo de bd no reconstruida
	 * 
	 * @param procesoFDVClusterizacion
	 *            el objeto a ser insertado
     * @param usuario
     *            el usuario que registra la informacin
	 * @throws Exception 
	 */
	public void saveFileNoReconstruida(
			ProcesoFDVClusterizacion procesoFDVClusterizacion, Usuario usuario) throws Exception;
}
