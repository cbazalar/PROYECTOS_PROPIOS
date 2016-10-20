package biz.belcorp.ssicc.service.spusicc.fdv;

import biz.belcorp.ssicc.dao.model.ProcesoFDVClusterizacion;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.fdv.ProcesoFDVFileSeccDAO;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * <p>
 * <a href="ProcesoFDVFileSeccService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:fayala@belcorp.biz">Frank Ayala</a>
 */

public interface ProcesoFDVFileSeccService extends Service{

	/**
	 * Setter del DAO, conveniente para pruebas unitarias
	 */
	public void setProcesoFDVFileSeccDAO(ProcesoFDVFileSeccDAO procesoFDVFileSeccDAO);

	/**
	 * Guarda la informacion capturada del archivo de secciones
	 * 
	 * @param procesoFDVClusterizacion
	 *            el objeto a ser insertado
     * @param usuario
     *            el usuario que registra la informacin
	 * @throws Exception 
	 */
	public void saveFileSecciones(
			ProcesoFDVClusterizacion procesoFDVClusterizacion, Usuario usuario) throws Exception;
}
