package biz.belcorp.ssicc.dao.spusicc.sessionexperte.ibatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.sessionexperte.MantenimientoSSEProgramaSessionExperteDAO;
import biz.belcorp.ssicc.dao.spusicc.sessionexperte.model.ProgramaSessionExperte;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="MantenimientoCUPProgDsctoDAOiBatis.java.html"> <i>View Source</i>
 * </a>
 * </p>
 * 
 * @author <a href="mailto:ivega@belcorp.biz">Isabel Vega Palomino</a>
 * 
 */
@Repository("spusicc.sessionexperte.mantenimientoSSEProgramaSessionExperteDAO")
public class MantenimientoSSEProgramaSessionExperteDAOiBatis extends BaseDAOiBatis 
	implements MantenimientoSSEProgramaSessionExperteDAO {

	/**
	 * Obtiene un listado de todos los programas session experte segun pais.  
	 * @param pais Objeto Pais cuyo atributo de cdigo se usa en la consulta.
	 * @return Lista de Objetos
	 */
	public List getProgramasSessionExperteByPais(Pais pais) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.sessionexperte.SessionExperteSQL.getProgramasSessionExperteByPais", pais);
	}
	
	/**
	 * Obtiene un listado de todos los programas session experte segun criteria.
	 * @param criteria Objeto Map cuyos atributos son usados como criterios de bsqueda.
	 * @return Lista de objetos Map, poblados.
	 */
	public List getProgramasSessionExperteByCriteria(ProgramaSessionExperte progSSE) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.sessionexperte.SessionExperteSQL.getProgramasSessionExperteByCriteria", progSSE);
	}

	/**
	 * Obtiene un listado de todos los programas session experte activos.
	 * @param pais Cdigo del pais en base al cual se buscar el Programa.
	 * @return Una cadena de caracteres que contiene el Codigo del Programa.
	 */
	public String getProgramaActivoByPais(Pais pais) {
		return (String) getSqlMapClientTemplate().queryForObject(
				"spusicc.sessionexperte.SessionExperteSQL.getProgramaActivoByPais", pais);
	}

	/**
	 * @param codigoPais cadena de caracteres que contiene el id del pais. 
	 * @return El siguiente codigo de programa para un pais dado.
	 */
	public String getCodigoProgramaByPais(Pais pais) {
		return (String) getSqlMapClientTemplate().queryForObject(
				"spusicc.sessionexperte.SessionExperteSQL.getCodigoProgramaByPais", pais);
	}
	
	/**
	 * @param codigoPais cadena de caracteres que contiene el id del pais. 
	 * @return El siguiente codigo de programa para un pais dado.
	 */
	public String getNextCodigoAnhoByPais(Pais pais) {
		return (String) getSqlMapClientTemplate().queryForObject(
				"spusicc.sessionexperte.SessionExperteSQL.getNextCodigoAnhoByPais", pais);
	}
	
	/**
	 * Inserta un Programa SessionExperte.
	 * @param progSSE Programa SessionExperte.
	 * @param usuario Usuario que inserta.
	 */
	public void insertProgramaSessionExperte(ProgramaSessionExperte progSSE, Usuario usuario) {
		getSqlMapClientTemplate().insert(
				"spusicc.sessionexperte.SessionExperteSQL.insertProgramaSessionExperte", progSSE);
	}

	/**
	 * Actualiza un Programa SessionExperte.
	 * @param progSSE Programa SessionExperte.
	 * @param usuario Usuario que actualiza.
	 */
	public void updateProgramaSessionExperte(ProgramaSessionExperte progSSE, Usuario usuario) {
		getSqlMapClientTemplate().update(
				"spusicc.sessionexperte.SessionExperteSQL.updateProgramaSessionExperte", progSSE);
	}
	
	/**
	 * Inserta un Programa SessionExperte.
	 * @param progSSE Programa SessionExperte.
	 * @param usuario Usuario que inserta.
	 */
	public void insertProgramaSessionExperteAnho(ProgramaSessionExperte progSSE, Usuario usuario) {
		getSqlMapClientTemplate().insert(
				"spusicc.sessionexperte.SessionExperteSQL.insertProgramaSessionExperteAnho", progSSE);
	}

	/**
	 * Actualiza un Programa SessionExperte.
	 * @param progSSE Programa SessionExperte.
	 * @param usuario Usuario que actualiza.
	 */
	public void updateProgramaSessionExperteAnho(ProgramaSessionExperte progSSE, Usuario usuario) {
		getSqlMapClientTemplate().update(
				"spusicc.sessionexperte.SessionExperteSQL.updateProgramaSessionExperteAnho", progSSE);
	}

	/**
	 * Actualiza un Programa SessionExperte a estado INACTIVO.
	 * @param progSSE Programa SessionExperte.
	 * @param usuario Usuario que actualiza.
	 */
	public void updateInactivarProgramaSessionExperte(ProgramaSessionExperte progSSE, Usuario usuario) {
		getSqlMapClientTemplate().update(
				"spusicc.sessionexperte.SessionExperteSQL.deleteProgramaSessionExperte", progSSE);
	}
	
	/**
	 * Obtiene un listado de todos los programas session experte segun pais.  
	 * @param pais Objeto Pais cuyo atributo de cdigo se usa en la consulta.
	 * @return Lista de Objetos
	 */
	public List getAnhoSessionExperteByPais(Pais pais) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.sessionexperte.SessionExperteSQL.getAnhoSessionExperteByPais", pais);
	}

	
	public String getCodigoAnhoActivo(Pais pais){
		return (String) getSqlMapClientTemplate().queryForObject(
				"spusicc.sessionexperte.SessionExperteSQL.getCodigoAnhoActivo", pais);

	}
	/**/
}
