/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.sto;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author itocto
 *
 */
public interface ProcesoSTOEnviarInformeEstatusSolicitudesCreditoDAO extends DAO {

	List getRegiones(Map params);

	List getZonas(Map params);

	String getCorreo(Map params);

}
