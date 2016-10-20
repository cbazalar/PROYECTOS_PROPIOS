package biz.belcorp.ssicc.dao.spusicc.sto;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author Jesse James Rios Franco
 *
 */
public interface MantenimientoSTOExcepcionValidaDeudaDAO extends DAO{
	
	/**
	 * Devuelve la lista de Exceciones Deuda de acuerdo a los filtros
	 * @param criteria
	 * @return
	 */
	public List getExcepcionValidaDeudaList(Map criteria);

	/**
	 * Inserta Excepcion Deuda
	 * @param criteria
	 */
	public void insertExcepValidDeuda(Map criteria);

	/**
	 * Elimina Excepcion Deuda
	 * @param criteria
	 */
	public void deleteExcepcionValidaDeuda(Map criteria);

}