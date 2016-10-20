/**
 *
 */
package biz.belcorp.ssicc.service.spusicc.sessionexperte;

import java.util.List;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.sessionexperte.model.ProgramaSessionExperte;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * <p>
 * <a href="MantenimientoSSEProgramaSessionExperteService.java.html"> <i>View Source</i>
 * </a>
 * </p>
 * 
 * @author <a href="mailto:ivega@belcorp.biz">Isabel Vega Palomino</a>
 * 
 */
public interface MantenimientoSSEProgramaSessionExperteService extends Service {
	
	/**
	 * Obtiene un listado de todos los programas session experte segun pais.  
	 * @param pais Objeto Pais cuyo atributo de cdigo se usa en la consulta.
	 * @return Lista de Objetos
	 */
	public List getProgramasSessionExperteByPais(Pais pais);
	
	/**
	 * Obtiene un listado de todos los programas session experte segun criteria.
	 * @param criteria Objeto Map cuyos atributos son usados como criterios de bsqueda.
	 * @return Lista de objetos Map, poblados.
	 */
	public List getProgramasSessionExperteByCriteria(ProgramaSessionExperte progSSE);

	/**
	 * Obtiene el primer elemento de un listado de programas session experte segun criteria. Segun los parametros indicados 
	 * @param criteria Objeto Map cuyos atributos son usados como criterios de bsqueda.
	 * @return Lista de objetos Map, poblados.
	 */
	public ProgramaSessionExperte getFirstFromProgramasSessionExperteByCriteria(ProgramaSessionExperte progSSE);
	
	/**
	 * Obtiene un listado de todos los programas session experte activos.
	 * @param pais Cdigo del pais en base al cual se buscar el Programa.
	 * @return Una cadena de caracteres que contiene el Codigo del Programa.
	 */
	public String getProgramaActivoByPais(Pais pais);
	
	/**
	 * @param codigoPais cadena de caracteres que contiene el id del pais. 
	 * @return El siguiente codigo de programa para un pais dado.
	 */
	public String getCodigoProgramaByPais(Pais pais);
	
	/**
	 * @param codigoPais cadena de caracteres que contiene el id del pais. 
	 * @return El siguiente codigo de anho para un pais dado.
	 */
	public String getNextCodigoAnhoByPais(Pais pais);

	/**
	 * Inserta un Programa SessionExperte
	 * @param progSSE Programa SessionExperte.
	 * @param usuario Usuario que inserta.
	 */
	public void insertProgramaSessionExperte(ProgramaSessionExperte progSSE, Usuario usuario);

	/**
	 * Inserta un Programa SessionExperte Anho
	 * @param progSSE Programa SessionExperte.
	 * @param usuario Usuario que inserta.
	 */
	public void insertProgramaSessionExperteAnho(ProgramaSessionExperte progSSE, Usuario usuario);

	
	/**
	 * Actualiza un Programa SessionExperte
	 * @param progSSE Programa SessionExperte.
	 * @param usuario Usuario que actualiza.
	 */
	public void updateProgramaSessionExperte(ProgramaSessionExperte progSSE, Usuario usuario);
	/**
	 * Actualiza un Programa SessionExperte Anho
	 * @param progSSE Programa SessionExperte.
	 * @param usuario Usuario que actualiza.
	 */
	public void updateProgramaSessionExperteAnho(ProgramaSessionExperte progSSE, Usuario usuario);
	
	/**
	 * Cambia el estado a INACTIVO del Programa SessionExperte (No lo elimina).
	 * @param progSSE Programa SessionExperte.
	 * @param usuario Usuario que elimina.
	 */
	public void deleteProgramaSessionExperte(ProgramaSessionExperte progSSE, Usuario usuario);
	
	/**
	 * Obtiene un listado de todos los Aos session experte segun criteria.
	 * @param criteria Objeto Map cuyos atributos son usados como criterios de bsqueda.
	 * @return Lista de objetos Map, poblados.
	 */
	public List getAnhoSessionExperteByPais(Pais pais);

	public String getCodigoAnhoActivo(Pais pais);



}
