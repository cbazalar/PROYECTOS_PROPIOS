package biz.belcorp.ssicc.dao.spusicc.cuentacorriente;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
* @author <a href="mailto:jflorencio@belcorp.biz">Jorge FLorencio</a>
 *
 */

public interface MantenimientoCCCGastosAdministrativosDAO extends DAO{
	
	/**Retorna los gastos administrativos
	 * @param criteria
	 * @return
	 */
	public List getGastosAdministrativosList(Map criteria);
	
	/**Inserta parametra de gastos administrativos
	 * @param criteria
	 */
	public void insertCCCGastosAdministrativos(Map criteria);
	
	/**Eliminacin lgica de la parametra de gastos administrativos
	 * @param criteria
	 */
	public void deleteLogicoGastosAdministrativos(Map criteria);

}
