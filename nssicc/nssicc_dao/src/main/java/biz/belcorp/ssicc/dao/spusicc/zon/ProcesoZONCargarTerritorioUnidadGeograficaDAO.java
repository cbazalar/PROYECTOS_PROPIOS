/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.zon;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="ProcesoZONCargarTerritorioUnidadGeograficaDAO.java"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@sigcomt.com">Ivan Tocto</a>
 * 
 */
public interface ProcesoZONCargarTerritorioUnidadGeograficaDAO extends DAO {

	String obtenerPathUpload(String codigoPais);

	void deleteCargarTerritorioUnidadGeografica(String codigoUsuario);

	void insertCargarTerritorioUnidadGeografica(Map params);

	List getCargarTerritorioUnidadGeografica(String codigoUsuario);

	void executeProcesarCargaTerritorioUnidadGeografica(String codigoUsuario);

	void executeValidarCargaTerritorioUnidadGeografica(String codigoUsuario);

}
