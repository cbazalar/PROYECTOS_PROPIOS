package biz.belcorp.ssicc.dao.spusicc.let;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * Clase de la declaracin de la capa DAO (Data Access Object)
 * 
 * <p>
 * <a href="MantenimientoLETParametroConcursoDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="cdiaz@csigcomt.com">Carlos Diaz Valverde</a>
 *         
 */
public interface MantenimientoLETParametroConcursoDAO extends DAO {
	
	/**
	 * Mtodo que lista los Parametros Concurso
	 * @param criteria
	 * @return
	 */
	public List getParametroConcursoList(Map criteria);
	
	/**
	 * Mtodo que lista el nmero de niveles por campaa
	 * @return
	 */
	public List getNivelCampaniaList();
	
	/**
	 * Mtodo que lista el nmero de rango de pedidos
	 * @return
	 */
	public List getRangoPedidoList();
	
	/**
	 * Mtodo que lista el nmero de niveles por concurso
	 * @return
	 */
	public List getNivelConcursoList();
	
	/**
	 * Mtodo que autogenera el codigo de concurso
	 * @return
	 */
	public String getCodigoConcurso(Map criteria);
	
	/**
	 * Mtodo que inserta Parametro Concurso 
	 * @param criteria
	 */
	public void insertParametroConcurso(Map criteria);
	
	/**
	 * Mtodo que inserta Parametro Nivel Campaa
	 * @param criteria
	 */
	public void insertNivelCampania(Map criteria);
	
	/**
	 * Mtodo que inserta Parametro Rango Pedido Premiacin
	 * @param criteria
	 */
	public void insertRangoPedido(Map criteria);

	/**
	 * Mtodo que inserta Parametro Nivel Concurso
	 * @param criteria
	 */
	public void insertNivelConcurso(Map criteria);

	/**
	 * Mtodo que actualiza Parametro Concurso
	 * @param criteria
	 */
	public void updateParametroConcurso(Map criteria);
	
	/**
	 * Mtodo que elimina Parametro Nivel Campaa
	 * @param criteria
	 */
	public void deleteNivelCampania(Map criteria);
	
	/**
	 * Mtodo que elimina Parametro Rango Pedido Premiacion
	 * @param criteria
	 */
	public void deleteRangoPedido(Map criteria);
	
	/**
	 * Mtodo que elimina Parametro Nivel Concurso
	 * @param criteria
	 */
	public void deleteNivelConcurso(Map criteria);
	
	/**
	 * Mtodo que lista Parametro Concurso consultar
	 * @param criteria
	 * @return
	 */
	public List getParametroConcursoConsult(Map criteria);

	/**
	 * Mtodo que lista Parametro Nivel Campaa consultar
	 * @param criteria
	 * @return
	 */
	public List getNivelCampaniaConsult(Map criteria);

	/**
	 * Mtodo que lista Parametro Rango Pedido consultar
	 * @param criteria
	 * @return
	 */
	public List getRangoPedidoConsult(Map criteria);

	/**
	 * Mtodo que lista Parametro Nivel Concurso consultar
	 * @param criteria
	 * @return
	 */
	public List getNivelConcursoConsult(Map criteria);

	/**
	 * Mtodo que valida si existe el Cod. Periodo en la tabla LET_PARAM_CONCU_LIDER. Si se devuelve mayor a 0, entonces existe el Cod. Periodo
	 * @param criteria
	 */
	public String getValidaPeriodoExiste(Map criteria);
	
	/**
	 * Mtodo que valida si es editable los campos periodo inicial y final del Mantenimiento Parametros Concursos Lideres
	 * @param criteria
	 * @return
	 */
	public String getValidaPeriodoEditable(Map criteria);
	
	/**
	 * Mtodo que valida si es editable los campos nivel campaa y pestaa nivel campaa del Mantenimiento Parametros Concursos Lideres
	 * @param criteria
	 * @return
	 */
	public String getValidaNivelCampaEditable(Map criteria);
	
	/**
	 * Mtodo que valida si es editable los campos nivel concurso y pestaa nivel concurso del Mantenimiento Parametros Concursos Lideres
	 * @param criteria
	 * @return
	 */
	public String getValidaNivelConcuEditable(Map criteria);
	
	/**
	 * Mtodo que valida si es editable los campos nivel minimo, rango pedido y pestaa rango pedido del Mantenimiento Parametros Concursos Lideres
	 * @param criteria
	 * @return
	 */
	public String getValidaRangoPedidoEditable(Map criteria);
	
	/**
	 * Mtodo que elimina logicamente la entidad Parametro Concurso Lider. Estado 9
	 * @param criteria
	 */
	public void getEliminaParametroConcursoLider(Map criteria);

	/**
	 * Mtodo que elimina logicamente la entidad Parametro Nivel Campaa. Estado 9
	 * @param criteria
	 */
	public void getEliminaParametroNivelCampania(Map criteria);
	
	/**
	 * Mtodo que elimina logicamente la entidad Parametro Rango Pedido. Estado 9
	 * @param criteria
	 */
	public void getEliminaParametroRangoPedido(Map criteria);
	
	/**
	 * Mtodo que elimina logicamente la entidad Parametro Nivel Concurso. Estado 9
	 * @param criteria
	 */
	public void getEliminaParametroNivelConcurso(Map criteria);

	/**
	 * Obtiene los tipos de variables LET
	 * @return
	 */
	public List getTipoVariablesList();
	
	/* INI JJ PER-SiCC-2012-0201 */
	/**
	 * Mtodo que obtiene la lista de descripciones de los niveles de campaa apartir de la posicion 7
	 * @return
	 */
	public List getDescripcionNivelesCampaniaList();

	/**
	 * Mtodo que actualiza los niveles de campaa de un concurso
	 * @param criteria_incremento
	 */
	public void updateNivelCampania(Map criteria_incremento);
	/* FIN JJ PER-SiCC-2012-0201 */
}
