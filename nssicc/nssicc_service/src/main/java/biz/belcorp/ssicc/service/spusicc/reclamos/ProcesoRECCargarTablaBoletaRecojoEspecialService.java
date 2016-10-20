/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.reclamos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author Cristhian Roman
 *
 */
public interface ProcesoRECCargarTablaBoletaRecojoEspecialService extends Service {

	void executeProcesoRECCargarTablaBoletaRecojoEspecial(Map params);
	
	void executeProcesoRECCargarTablaBoletaRecojoEspecial(List detalles, String periodo, String idioma, String usuario,String pais); 
	
	List executeVerificacionTablaBoletaRecojoEspecial(Map params);
	
	String getDescripcionProducto(Map params);
	
	
	void insertTablaCodigosVenta(Map params);

}
