/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.zon;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="ProcesoZONCargarTerritorioUnidadGeograficaService.java"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@sigcomt.com">Ivan Tocto</a>
 * 
 */
public interface ProcesoZONCargarTerritorioUnidadGeograficaService extends Service {

	String obtenerPathUpload(String codigoPais);

	int cargarArchivoCSV(Map criteria) throws Exception;

	List executeValidarCargaTerritorioUnidadGeografica(String codigoUsuario);

	void executeProcesarCargaTerritorioUnidadGeografica(String codigoUsuario);

}
