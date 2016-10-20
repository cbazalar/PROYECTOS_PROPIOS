/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.pre;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pre.model.MatrizRecuperacion;
import biz.belcorp.ssicc.dao.spusicc.pre.model.MatrizRecuperacionAuditoria;
import biz.belcorp.ssicc.dao.spusicc.pre.model.ProductoMatriz;

/**
 * @author Sigcomt
 *
 */
public interface MantenimientoPREMatrizRecuperacionesDAO extends DAO {

	List getRecuperaciones(Map params);

	void updateRecuperacion(MatrizRecuperacion mr, Usuario usuario);

	void insertRecuperacionAuditoria(MatrizRecuperacionAuditoria audi, Usuario usuario);

	ProductoMatriz getProductoPREMatrizRecuperaciones(String codigoPeriodo, String cuv, String flagRecuperacion);

	void insertRecuperacion(MatrizRecuperacion matrizRecuperacion, Usuario usuario);

	List getRecuperacionList(MatrizRecuperacion matrizRecuperacion);
}
