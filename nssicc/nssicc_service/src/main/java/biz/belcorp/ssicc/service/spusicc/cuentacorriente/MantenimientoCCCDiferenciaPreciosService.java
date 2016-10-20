package biz.belcorp.ssicc.service.spusicc.cuentacorriente;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author Cristhian Roman
 *
 */
public interface MantenimientoCCCDiferenciaPreciosService extends Service {

	
	
	/**Retorna la lista de abonos y cargos
	 * @param criteria
	 * @return
	 */
	public List getCargosAbonosList(Map criteria);
		
	/**Realiza el proceso de abonos y cargos
	 * @param lista
	 * @param criteria
	 */
	public void executeProcesoCargosAbonos(List lista, Map criteria);

	/**
	 * Genera la data de la busqueda realizada de cargos y abonos
	 * @param criteria
	 */
	public void executeGenerarDataCargosAbonos(Map criteria);

}
