package biz.belcorp.ssicc.service.spusicc.let;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * Clase de la declaracin de la capa BO (Bussiness Object)
 * 
 * <p>
 * <a href="MantenimientoLETParametroConcursoService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="cdiaz@csigcomt.com">Carlos Diaz Valverde</a>
 *         
 */
public interface MantenimientoLETParametroConcursoService extends Service{
	
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
	 * Mtodo que inserta Parametro Concurso 
	 * @param criteria
	 */
	public String insertParametroConcurso(Map criteria);

	/**
	 * Mtodo que actualiza Parametro Concurso
	 * @param criteria
	 */
	public void updateParametroConcurso(Map criteria);
	
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
	 * Mtodo que elimina logicamente Parametros Concurso Lider, Parametros Nivel Campaa, Parametros Nivel Concurso, Parametros Rango Pedidos. Estado 9
	 * @param criteria
	 */
	public void deleteParametroConcurso(Map criteria);

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
	/* FIN JJ PER-SiCC-2012-0201 */
}
