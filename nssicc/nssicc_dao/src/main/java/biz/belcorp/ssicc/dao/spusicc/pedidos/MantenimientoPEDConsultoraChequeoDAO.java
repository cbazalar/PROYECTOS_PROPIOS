package biz.belcorp.ssicc.dao.spusicc.pedidos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author Jos Luis Rodrguez
 */

public interface MantenimientoPEDConsultoraChequeoDAO extends DAO{

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
