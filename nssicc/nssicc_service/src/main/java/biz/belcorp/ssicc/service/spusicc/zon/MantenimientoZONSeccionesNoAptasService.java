/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.zon;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.zon.model.SeccionNoApta;
import biz.belcorp.ssicc.service.framework.Service;

public interface MantenimientoZONSeccionesNoAptasService extends Service {
	
	void executeProcesarArchivoExcel(String directorioTemporal, String nombreArchivo, Usuario usuario) throws Exception;
	
	List getSeccionesAptasNoAptasByCriteria(Map criteria);
	
	SeccionNoApta getSeccionNoApta(String codigoRegion, String codigoZona, String codigoSeccion);
	
	void deleteSeccionNoApta(String codigoRegion, String codigoZona, String codigoSeccion);
	
	void insertSeccionNoApta(SeccionNoApta seccion, Usuario usuario);
}
