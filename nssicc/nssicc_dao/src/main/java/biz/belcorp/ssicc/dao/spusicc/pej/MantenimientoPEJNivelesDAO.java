package biz.belcorp.ssicc.dao.spusicc.pej;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.spusicc.pej.model.NivelEjecutiva;

/**
 * @author Jesse James Rios Franco
 *
 */
public interface MantenimientoPEJNivelesDAO extends DAO{

	/**
	 * Retorna la lista de niveles de ejecutivas
	 * @param criteria
	 * @return
	 */
	public List getNivelesList(Map criteria);

	/**
	 * Elimina niveles de ejecutivas
	 * @param criteria
	 */
	public void deleteNivelesEjecutivas(Map criteria);

	/**
	 * Obtiene el Nivel de Ejecutiva
	 * @param codigoPais
	 * @param id
	 * @return
	 */
	public NivelEjecutiva getNivelEjecutiva(String codigoPais, String id);

	/**
	 * Inserta un Nivel de Ejecutiva
	 * @param nivelEjecutiva
	 */
	public void insertNivelEjecutiva(NivelEjecutiva nivelEjecutiva);

	/**
	 * Actualiza un Nivel de Ejecutiva
	 * @param nivelEjecutiva
	 */
	public void updateNivelEjecutiva(NivelEjecutiva nivelEjecutiva);

}