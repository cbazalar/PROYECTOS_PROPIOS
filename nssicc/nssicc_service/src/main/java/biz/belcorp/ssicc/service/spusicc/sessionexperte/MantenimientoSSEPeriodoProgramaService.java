/**
 *
 */
package biz.belcorp.ssicc.service.spusicc.sessionexperte;

import java.util.List;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.sessionexperte.model.PeriodoPrograma;
import biz.belcorp.ssicc.dao.spusicc.sessionexperte.model.ProgramaSessionExperte;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * <p>
 * <a href="MantenimientoSSEPeriodoProgramaService.java.html"> <i>View Source</i>
 * </a>
 * </p>
 * 
 * @author <a href="mailto:ivega@belcorp.biz">Isabel Vega Palomino</a>
 * 
 */
public interface MantenimientoSSEPeriodoProgramaService extends Service {

	/**
	 * Obtiene un listado de todos los periodos de los programas session experte con sus respectivos productos segun criteria.
	 * @param criteria Objeto PeriodoPrograma cuyos atributos son usados como criterios de bsqueda.
	 * @return Lista de objetos PeriodoPrograma, poblados.
	 */
    public List getPeriodosProgramaByCriteria(PeriodoPrograma periodo);
    
	/**
	 * Obtiene el primer objeto del listado de todos los programas session experte.
	 * @param periodo PeriodoPrograma cuyos atributos son usados como criterios de bsqueda.
	 * @return Un objeto PeriodoPrograma.
	 */
    public PeriodoPrograma getFirstFromPeriodosProgramaByCriteria(PeriodoPrograma periodo);

	/**
	 * Inserta un Periodo de Programa y sus productos.
	 * @param periodo PeriodoPrograma, coge los datos de Pais, Program y periodo para crear un nuevo periodo.
	 * @param usuario Usuario que inserta.
	 */
	public void insertPeriodoPrograma(PeriodoPrograma periodo, Usuario usuario);

	/**
	 * Actualiza un Periodo de Programa y sus productos.
	 * @param periodo PeriodoPrograma, coge los datos de Pais, Program y periodo para crear un nuevo periodo.
	 * @param usuario Usuario que inserta.
	 */
	public void updatePeriodoPrograma(PeriodoPrograma periodo, Usuario usuario);
	
	/**
	 * Cambia el estado a INACTIVO del Periodo de Programa (No lo elimina).
	 * @param periodo PeriodoPrograma, coge los datos de Pais, Program y periodo para crear un nuevo periodo.
	 * @param usuario Usuario que elimina.
	 */
	public void deletePeriodoPrograma(PeriodoPrograma periodo, Usuario usuario);
	
	/**
	 * Obtiene el MAXIMO codigo de periodo existente para un programa
	 * @param periodo Periodo que contiene el codigo de pais y programa
	 * @return Maximo Cdigo de Periodo para un programa 
	 */
	public String getPeriodoDefaultByPrograma(PeriodoPrograma periodo);
	
	/**
	 * Obtiene el SIGUIENTE codigo de periodo para un programa
	 * @param periodo Periodo que contiene el codigo de pais y programa
	 * @return SIGUIETNE Cdigo de Periodo para un programa 
	 */
	public String getNextPeriodoBySSEProgPerio(PeriodoPrograma periodo, ProgramaSessionExperte programa);
}
