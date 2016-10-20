/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.cuentacorriente;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.LabelValueCUV;

/**
 * @author Cristhian Roman
 *
 */
public interface MantenimientoCCCDiferenciaPreciosDAO extends DAO {

	/**
	 * @return
	 * Devuelve el codigo de venta
	 */
	public LabelValueCUV getCodigoVentaPrecio(Map criteria);
	
	
	/**Retorna los abonos y cargos
	 * @param criteria
	 * @return
	 */
	public List getCargosAbonosList(Map criteria);
	
	/**
	 * Realiza el proceso de abonos y cargos
	 * @param criteria
	 */
	public void executeProcesoCargosAbonos(Map criteria);
	
	/**
	 * Retorna la lista de registros ya procesados
	 * @param criteria
	 * @return
	 */
	public List getRegistrosProcesar(Map criteria);


	/**
	 * Genera la data de la busqueda realizada de cargos y abonos
	 * @param criteria
	 */
	public void executeGenerarDataCargosAbonos(Map criteria);
			
}
