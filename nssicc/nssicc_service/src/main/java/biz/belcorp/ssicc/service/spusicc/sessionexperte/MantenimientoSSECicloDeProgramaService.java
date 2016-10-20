/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.sessionexperte;

import java.util.List;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.sessionexperte.model.CicloDePrograma;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="MantenimientoSSECicloDeProgramaSSEService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author peextrramirez
 * 
 */

public interface MantenimientoSSECicloDeProgramaService extends Service {

	/**
	 * Obtiene un listado de los ciclos del Programa Session Experte indicado.
	 * @param producto Objeto de tipo Ciclo de Programa session experte.
	 * @return Lista de objetos de tipo CicloDePrograma.
	 * */
	public List getCiclosDeProgramaSessionExperteByCriteria(CicloDePrograma ciclo);
	
	public List getTipoCiclosSessionExperte();
	
	/**
	 * Inserta un Ciclo de Programa SessionExperte
	 * @param ciclo Ciclo de Programa SessionExperte.
	 * @param usuario Usuario que inserta.
	 */
	public void insertCicloDeProgramaSessionExperte(CicloDePrograma ciclo, Usuario usuario);
	
	/**
	 * Actualiza un Ciclo de Programa SessionExperte
	 * @param ciclo Ciclo de Programa SessionExperte.
	 * @param usuario Usuario que inserta.
	 */
	public void updateCicloDeProgramaSessionExperte(CicloDePrograma ciclo, Usuario usuario);

	/**
	 * Delete un Ciclo de Programa SessionExperte
	 * @param ciclo Ciclo de Programa SessionExperte.
	 * @param usuario Usuario que inserta.
	 */
	public void deleteCicloDeProgramaSessionExperte(CicloDePrograma ciclo, Usuario usuario);

	/**
	 * Consigue un Ciclo de Programa SessionExperte
	 * @param ciclo Ciclo de Programa SessionExperte.
	 */
	public CicloDePrograma getFirstFromCiclosDeProgramaSessionExperteByCriteria(CicloDePrograma ciclo);

	public String getNextCodigoCiclo(CicloDePrograma ciclo);

	

}
