package biz.belcorp.ssicc.service.spusicc.pedidos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author Jos Luis Rodrguez
 */

public interface MantenimientoPEDConsultoraChequeoService extends Service{

	/**
	 * Retorna la lista de tipo de chequeo para un pais dado
	 * @param criteria
	 * @return
	 */
	public List getTipoChequeoPais(Map criteria);
	
	/**
	 * Inserta una consultora a chequear
	 * @param criteria
	 */
	public void insertConsultoraChequear(Map criteria);
	
	/**
	 * Elimina una consultora a chequear  
	 * @param criteria
	 */
	public void deleteConsultoraChequear(Map criteria);
	
	/**
	 * Se obtienen la lisa de consultoras a chequear
	 * @param criteria
	 */
	public List getConsultoraChequear(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 */
	public int getExisteConsultoraChequear(Map criteria);
}
