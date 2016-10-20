package biz.belcorp.ssicc.dao.spusicc.let;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * Clase de la declaracin de la capa DAO (Data Access Object)
 * 
 * <p>
 * <a href="MantenimientoLETPremioConcursoDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="cdiaz@csigcomt.com">Carlos Diaz Valverde</a>
 *         
 */
public interface MantenimientoLETPremioConcursoDAO extends DAO {
	
	/**
	 * Mtodo que inserta Premio por Concurso
	 * @param criteria
	 */
	public void insertPremioConcurso(Map criteria);
	
	/**
	 * Mtodo que elimina Premio por Concurso de acuerdo al codigo concurso y codigo de periodo de despacho
	 * @param criteria
	 */
	public void deletePremioConcurso(Map criteria);
	
	/**
	 * Mtodo que captura el Nivel Concurso y Rango Pedido
	 * @param criteria
	 * @return
	 */
	public List getNivelConcursoRangoList(Map criteria);

	/**
	 * Mtodo que lista Premios por Concurso
	 * @param criteria
	 * @return
	 */
	public List getPremioConcursoList(Map criteria);

	/**
	 * Valida que un Concurso y Campaa no se repitan el Mantenimiento Premios por Concurso
	 * @param criteria
	 * @return
	 */
	public String getValidaPremioConcursoExiste(Map criteria);
}
