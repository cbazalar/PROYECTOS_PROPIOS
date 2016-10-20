package biz.belcorp.ssicc.dao.spusicc.inc;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
public interface MantenimientoINCCampanaDespachoDiferidaDAO extends DAO {

	/**
	 * Retorna la lista de Concurso con Despacho Deferido
	 * 
	 * @return
	 */
	List getListConcursoDespachoDiferido();

	/**
	 * Retorna la lista de niveles asociado al Concurso
	 * 
	 * @return
	 */
	List getNivelesConcurso(Map criteria);

	/**
	 * Recupera datos del periodo de inicio de un registro de la entidad Nivel Despacho Diferido
	 * 
	 * @param criteria
	 */
	Map getPeriodoNivelDespachoDiferido(Map criteria);

	/**
	 * Inserta un registro en la entidad Nivel Despacho Diferido
	 * 
	 * @param criteria
	 */
	void insertNivelDespachoDiferido(Map criteria);
	
	/**
	 * Actualiza un registro en la entidad Nivel Despacho Diferido
	 * 
	 * @param criteria
	 */
	void updateNivelDespachoDiferido(Map criteria);

	/**
	 * Elimina un registro en la entidad Nivel Despacho Diferido
	 * 
	 * @param criteria
	 */
	public void deleteNivelDespachoDiferido(Map criteria);

	/**
	 * Verifica si Existe le registro en la entidad Nivel Despacho Diferido
	 * 
	 * @param criteria
	 */
	boolean getExistePeriodoNivelDespachoDiferido(Map criteria);
	
}