package biz.belcorp.ssicc.service.spusicc.sessionexperte.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.sessionexperte.MantenimientoSSEProgramaSessionExperteDAO;
import biz.belcorp.ssicc.dao.spusicc.sessionexperte.model.ProgramaSessionExperte;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.sessionexperte.MantenimientoSSEProgramaSessionExperteService;

/**
 * <p>
 * <a href="MantenimientoSSEProgramaSessionExperteService.java.html"> <i>View Source</i>
 * </a>
 * </p>
 * 
 * @author <a href="mailto:ivega@belcorp.biz">Isabel Vega Palomino</a>
 */
@Service("spusicc.mantenimientoSSEProgramaSessionExperteService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoSSEProgramaSessionExperteServiceImpl extends BaseService implements MantenimientoSSEProgramaSessionExperteService {

	@Resource(name="spusicc.sessionexperte.mantenimientoSSEProgramaSessionExperteDAO")
    MantenimientoSSEProgramaSessionExperteDAO mantenimientoDAO;

	/**
	 * Obtiene un listado de todos los programas session experte.  
	 * @param pais Objeto Pais cuyo atributo de cdigo se usa en la consulta.
	 * @return Lista de Objetos
	 */
    public List getProgramasSessionExperteByPais(Pais pais) {
        return mantenimientoDAO.getProgramasSessionExperteByPais(pais);
    }

	/**
	 * Obtiene un listado de todos los programas session experte.
	 * @param criteria Objeto Map cuyos atributos son usados como criterios de bsqueda.
	 * @return Lista de objetos Map, poblados.
	 */
    public List getProgramasSessionExperteByCriteria(ProgramaSessionExperte progSSE) {
        return mantenimientoDAO.getProgramasSessionExperteByCriteria(progSSE);
    }
    
	/**
	 * Obtiene un listado de todos los programas session experte.
	 * @param criteria Objeto Map cuyos atributos son usados como criterios de bsqueda.
	 * @return Lista de objetos Map, poblados.
	 */
    public ProgramaSessionExperte getFirstFromProgramasSessionExperteByCriteria(ProgramaSessionExperte progSSE) {
        return (ProgramaSessionExperte)(mantenimientoDAO.getProgramasSessionExperteByCriteria(progSSE)).get(0);
    }

	/**
	 * @param codigoPais Cdigo del pais en base al cual se buscar el Programa.
	 * @return Una cadena de caracteres que contiene el Codigo del Programa.
	 */
	public String getProgramaActivoByPais(Pais pais) {
		return mantenimientoDAO.getProgramaActivoByPais(pais);
	}

	/**
	 * @param codigoPais cadena de caracteres que contiene el id del pais. 
	 * @return El siguiente codigo de programa para un pais dado.
	 */
	public String getCodigoProgramaByPais(Pais pais){
		return mantenimientoDAO.getCodigoProgramaByPais(pais);
	} 
	
	/**
	 * @param codigoPais cadena de caracteres que contiene el id del pais. 
	 * @return El siguiente codigo de programa para un pais dado.
	 */
	public String getNextCodigoAnhoByPais(Pais pais){
		return mantenimientoDAO.getNextCodigoAnhoByPais(pais);
	} 
	
	/**
	 * Inserta un Programa SessionExperte
	 * @param progSSE Programa SessionExperte.
	 * @param usuario Usuario que inserta.
	 */
	public void insertProgramaSessionExperte(ProgramaSessionExperte progSSE, Usuario usuario){
		progSSE.setEstadoRegistro(Constants.ESTADO_ACTIVO);
		mantenimientoDAO.insertProgramaSessionExperte(progSSE, usuario);
	}
	
	/**
	 * Inserta un Programa SessionExperte Anho
	 * @param progSSE Programa SessionExperte.
	 * @param usuario Usuario que inserta.
	 */
	public void insertProgramaSessionExperteAnho(ProgramaSessionExperte progSSE, Usuario usuario){
		progSSE.setEstadoRegistro(Constants.ESTADO_ACTIVO);
		mantenimientoDAO.insertProgramaSessionExperteAnho(progSSE, usuario);
	}
	
	/**
	 * Actualiza un Programa SessionExperte Anho
	 * @param progSSE Programa SessionExperte.
	 * @param usuario Usuario que actualiza.
	 */
	public void updateProgramaSessionExperteAnho(ProgramaSessionExperte progSSE, Usuario usuario){
		mantenimientoDAO.updateProgramaSessionExperteAnho(progSSE, usuario);
	}
	
	/**
	 * Actualiza un Programa SessionExperte
	 * @param progSSE Programa SessionExperte.
	 * @param usuario Usuario que actualiza.
	 */
	public void updateProgramaSessionExperte(ProgramaSessionExperte progSSE, Usuario usuario){
		mantenimientoDAO.updateProgramaSessionExperte(progSSE, usuario);
	}
	
	/**
	 * Cambia el estado a INACTIVO del Programa SessionExperte (No lo elimina).
	 * @param progSSE Programa SessionExperte.
	 * @param usuario Usuario que elimina.
	 */
	public void deleteProgramaSessionExperte(ProgramaSessionExperte progSSE, Usuario usuario){
		progSSE.setEstadoRegistro(Constants.ESTADO_INACTIVO);
		mantenimientoDAO.updateInactivarProgramaSessionExperte(progSSE, usuario);
	}
	
	/**
	 * Obtiene un listado de todos los Aos session experte.
	 * @param criteria Objeto Map cuyos atributos son usados como criterios de bsqueda.
	 * @return Lista de objetos Map, poblados.
	 */ 
	public List getAnhoSessionExperteByPais(Pais pais) {
	        return mantenimientoDAO.getAnhoSessionExperteByPais(pais);
	}   
    
    public String getCodigoAnhoActivo(Pais pais ){
    	return mantenimientoDAO.getCodigoAnhoActivo(pais);
    }
	
}
