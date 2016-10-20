package biz.belcorp.ssicc.service.spusicc.fdv;

import biz.belcorp.ssicc.dao.model.ProcesoFDVClusterizacion;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.fdv.ProcesoFDVFileVariDAO;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * <p>
 * <a href="ProcesoFDVFileVariService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:fayala@belcorp.biz">Frank Ayala</a>
 */

public interface ProcesoFDVFileVariService extends Service{

	/**
	 * Setter del DAO, conveniente para pruebas unitarias
	 */
	public void setProcesoFDVFileVariDAO(ProcesoFDVFileVariDAO procesoFDVFileVariDAO);

	/**
	 * Guarda la informacion capturada del archivo de variables exogenas
	 * 
	 * @param procesoFDVClusterizacion
	 *            el objeto a ser insertado
     * @param usuario
     *            el usuario que registra la informacin
	 * @throws Exception 
	 */
	public void saveFileVariablesExog(
			ProcesoFDVClusterizacion procesoFDVClusterizacion, Usuario usuario) throws Exception;
}
