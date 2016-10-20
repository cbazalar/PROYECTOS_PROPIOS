package biz.belcorp.ssicc.dao.spusicc.let;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * Clase de la declaracin de la capa DAO (Data Access Object)
 * 
 * <p>
 * <a href="MantenimientoLETPremioCampaniaDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="cdiaz@csigcomt.com">Carlos Diaz Valverde</a>
 *         
 */
public interface MantenimientoLETPremioCampaniaDAO extends DAO {
	
	/**
	 * Mtodo que inserta Premio por Campaa
	 * @param criteria
	 */
	public void insertPremioCampania(Map criteria);
	
	/**
	 * Mtodo que elimina Premio por Campaa de acuerdo al codigo concurso y codigo de periodo de despacho
	 * @param criteria
	 */
	public void deletePremioCampania(Map criteria);
	
	/**
	 * Mtodo que lista los nmeros de concurso
	 * @param criteria
	 * @return
	 */
	public List getNumeroConcursoList(Map criteria);
	
	/**
	 * Mtodo que captura el Nivel Campania y Rango Pedido
	 * @param criteria
	 * @return
	 */
	public List getNivelRangoList(Map criteria);
	
	/**
	 * Mtodo que lista Premios por Campaa
	 * @param criteria
	 * @return
	 */
	public List getPremioCampaniaList(Map criteria);

	/**
	 * Mtodo que valida si el cdigo de venta existe o no
	 * @param criteria
	 * @return
	 */
	public int getValidaCodigoVenta(Map criteria);
	
	/**
	 * Valida que un Concurso y Campaa no se repitan el Mantenimiento Premios por Campaa
	 * @param criteria
	 * @return
	 */
	public String getValidaPremioCampaniaExiste(Map criteria);
}
