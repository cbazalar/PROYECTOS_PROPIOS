package biz.belcorp.ssicc.dao.spusicc.let;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author Jesse James Rios Franco
 *
 */
public interface MantenimientoLETBonosConcursoDAO extends DAO{
     /**
	 * Obtiene la lista de Bonos
	 * @param criteria
	 * @return
	 */
	public List getBonoConcursoList(Map criteria);

	/**
	 * Valida que un Concurso y Concurso no se repitan el Mantenimiento Bonos por Campaa
	 * @param criteria
	 * @return
	 */
	public int getValidaBonoConcursoExiste(Map criteria);

	/**
	 * Mtodo que elimina Bono por Concurso de acuerdo al codigo concurso y codigo de periodo de despacho
	 * @param criteria
	 */
	public void deleteBonoConcurso(Map criteria);

	/**
	 *  Mtodo que inserta Bono por Concurso
	 * @param criteria
	 */
	public void insertBonoConcurso(Map criteria);
}