/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.zon;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.zon.model.SeccionNoApta;

/**
 * @author tokkto
 *
 */
public interface MantenimientoZONSeccionesNoAptasDAO extends DAO {
	
	Base getZonaByRegionZona(String codigoRegion, String codigoZona);
	Base getSeccionByRegionZonaSeccion(String codigoRegion, String codigoZona, String codigoSeccion);
	List getSeccionesByRegionZona(String codigoRegion, String codigoZona);
	
	void insertSeccionNoApta(SeccionNoApta seccion, Usuario usuario);
	void deleteSeccionNoApta();
	void deleteSeccionNoAptaById(String codigoRegion, String codigoZona, String codigoSeccion);
	SeccionNoApta getSeccionNoApta(String codigoRegion, String codigoZona, String codigoSeccion);
	
	List getSeccionesAptasNoAptasByCriteria(Map criteria);
}
