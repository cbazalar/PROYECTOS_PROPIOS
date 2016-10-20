package biz.belcorp.ssicc.service.spusicc.cuentacorriente;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;


/**
* @author <a href="mailto:jflorencio@belcorp.biz">Jorge FLorencio</a>
*/

public interface MantenimientoCCCGastosAdministrativosService extends Service {
	
	/**Retorna la lista de gastos administrativos
	 * @param criteria
	 * @return
	 */
	public List getGastosAdministrativosList(Map criteria);
	
	/**Inserta parametra de gastos administrativos
	 * @param criteria
	 * @return
	 */
	public int insertCCCGastosAdministrativos(Map criteria);
	
	/**Eliminacin Lgica de la parametra de gastos administrativos
	 * @param parametros
	 */
	public void deleteLogicoGastosAdministrativos(Map parametros);
}
