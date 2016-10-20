/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.pre;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pre.model.MatrizRecuperacion;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author Sigcomt
 *
 */
public interface MantenimientoPREMatrizRecuperacionesService extends Service {

	List getRecuperaciones(Map params);

	void updateRecuperacion(MatrizRecuperacion mr, Usuario usuario);

	void insertRecuperacion(MatrizRecuperacion matrizRecuperacion, Usuario usuario);

}
